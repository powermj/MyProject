package com.zhxg.yqzj.web.api.v1;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.utils.CMyEncrypt;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HttpsUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.yqzj.entities.v1.UserVo;
import com.zhxg.yqzj.service.util.MobileCloudVersionControlUtil;
import com.zhxg.yqzj.service.v1.UserService;




@RestController
@RequestMapping("/api/ecloud")
public class EcloudController{
    
    private static final String YDY="YDY";
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private UserService userService;
    
    /**
     * 登录，鉴权，获取用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/ecloudLogin",method = RequestMethod.GET,consumes = "application/json")
    public JSONObject ecloudLogin(HttpServletRequest request,
            @RequestParam(value = "code", required = false)  String code,
            @RequestParam(value = "appid", required = false)  String appid,
            @RequestParam(value = "packagecode", required = false)  String packagecode) { 
        
        logger.debug("EcloudController--ecloudLogin---code:"+code+ ",appid:"+appid+",packagecode:"+packagecode);
        //1,鉴权获取token
        String url = PropertiesUtil.getValue("ecloud.saas1001.url");
        String clientId = PropertiesUtil.getValue("ecloud.clientId");
        StringBuffer urlsb = new StringBuffer(url);
        urlsb.append("?grant_type=authorization_code");
        urlsb.append("&client_id=").append(clientId);
        urlsb.append("&scope=&redirect_uri=");
        urlsb.append("&code=").append(code);
        logger.debug("EcloudController--ecloudLogin--url-"+urlsb.toString());
        String responseBody = HttpsUtil.post(urlsb.toString(), null); //HttpUtil.post(url, map, headsMap);//
        logger.debug("EcloudController--ecloudLogin--token-"+responseBody);
        if(!StringUtils.isEmpty(responseBody)){
            JSONObject responseJson = (JSONObject) JSONObject.parse(responseBody);
            String access_token = responseJson.getString("access_token");
            String uid = responseJson.getString("uid");
            RedisUtil.setStr("ecloud_user_token_"+uid,access_token);
            RedisUtil.setExpire("ecloud_user_token_"+uid, 6*60,TimeUnit.MINUTES);
            RedisUtil.hset("ecloud_user_token",uid,responseBody);
            
            //2,获取用户信息
            String userInfoUrl = PropertiesUtil.getValue("ecloud.saas2001.url");
            userInfoUrl=userInfoUrl+"?access_token="+access_token+"&userid="+uid;
            logger.debug("EcloudController--ecloudLogin--userInfoUrl-"+userInfoUrl);
            String userInfo =  HttpsUtil.httpsGet(userInfoUrl, null); //HttpUtil.post(userInfoUrl, param, heads);//
            logger.debug("EcloudController--ecloudLogin--userInfo---"+userInfo);
            JSONObject userInfoJson = (JSONObject) JSONObject.parse(userInfo);
            String custcode = userInfoJson.getString("custcode");
            //3,秘书用户校验
            Map<String,Object> userMap = userService.getUserByKuLid(YDY+custcode);
            logger.debug("EcloudController--ecloudLogin--userMap---"+userMap);
            if(!CollectionUtils.isEmpty(userMap)){
                JSONObject json = (JSONObject) JSONObject.toJSON(userMap);
                return json;
            }
        }  
        return new JSONObject();
    }
       
    /**
     * 企业业务开通与变更接口 (SaaS0001)
     */
    @ResponseBody
    @RequestMapping(value = "/saas0001",method = RequestMethod.POST,produces={"application/json"},consumes={"application/json"})
    public JSONObject saas0001(HttpServletRequest request,
            @RequestParam(value = "timestamp", required = false)  String timestamp,
            @RequestParam(value = "sign", required = false)  String sign,
            @RequestBody JSONObject ecloudOrgVo) {  
        logger.debug("EcloudController--saas0001---"+ecloudOrgVo);
        RedisUtil.rpush("ecloud_saas0001_List",ecloudOrgVo);
        //1,签名校验
        boolean verifyResult = verifySign(timestamp,sign);
        if(!verifyResult){
            return this.returnException(501, "sign的值错误");
        }   
        //2,用户信息
        UserVo uservo = new UserVo();
        uservo.setThirdKey(YDY);
        uservo.setKU_LID(YDY+ecloudOrgVo.getString("custcode"));  //秘书 logName
        uservo.setLogName(ecloudOrgVo.getString("custname"));  //现在没用
        uservo.setZsname(YDY+ecloudOrgVo.getString("custname"));  //ku_name
        uservo.setUemail(ecloudOrgVo.getString("email"));     
        uservo.setUserphone(ecloudOrgVo.getString("mobile"));
        uservo.setKU_NAME(YDY+ecloudOrgVo.getString("username"));
        uservo.setF_TIME(ecloudOrgVo.getString("endtime")); 
        uservo.setThirdId(ecloudOrgVo.getString("custcode"));
        uservo.setApplyno(ecloudOrgVo.getString("applyno"));
        uservo.setTrial(ecloudOrgVo.getString("trial"));
        /**
         * 开通试用   开通正式    都给redis
         * 处理报文    试用转正式   变更(时间，资费) 注销
         */
        Integer opttype = ecloudOrgVo.getInteger("opttype");
        if(opttype==0){   //开通试用  开通正式
            String value = RedisUtil.hget("ecloud_uservoset", ecloudOrgVo.getString("custcode"));
            if(StringUtils.isEmpty(value)){
                if("true".equals(uservo.getTrial())){
                    uservo.setFeeType("B4");
                }else{
                    Map<String,Object> paramMap = new HashMap<String,Object>();
                    initParam(ecloudOrgVo.getJSONArray("services"),paramMap);
                    Object version = paramMap.get("version");
                    uservo.setFeeType(version!=null?version.toString():"B1");
                }
                RedisUtil.hset("ecloud_uservoset", ecloudOrgVo.getString("custcode"), "Y");
                RedisUtil.rpush("ecloud_uservoList", JSONObject.toJSON(uservo));
            }
        }else{
            if(opttype==1||opttype==2){  //变更     //试用转商用
                Map<String, Object> paramMap = new HashMap<String,Object>();
                initParam(ecloudOrgVo.getJSONArray("services"),paramMap);                
                Map<String,Object> userMap = userService.getUserByThirdId(uservo.getThirdId());
                int keyNum = MobileCloudVersionControlUtil.getKeyWordNum(userMap.get("KU_SEX").toString(),paramMap.get("version").toString());
                paramMap.put("KU_KEYWORDNUM",keyNum);
                paramMap.put("KU_ID", userMap.get("KU_ID"));
                Date endTime = new Date(Long.parseLong((String) paramMap.get("endtime")));
                paramMap.put("KU_TRYSDATE",DateUtil.toString(endTime));
                userService.trialTransition(paramMap);
            }
        }
        return this.returnResult(true, "");
    }
    
    /**
     * 企业业务状态变更接口(SaaS0002)
     */
    @ResponseBody
    @RequestMapping(value = "/saas0002", method = RequestMethod.POST,produces={"application/json"},consumes={"application/json"})
    public JSONObject saas0002(HttpServletRequest request,
            @RequestParam(value = "timestamp", required = true)  String timestamp,
            @RequestParam(value = "sign", required = true)  String sign,
            @RequestBody JSONObject ecloudOrgEditVo    
            ){
        logger.debug("EcloudController--saas0002---"+ecloudOrgEditVo);
        RedisUtil.rpush("ecloud_saas0002_List",ecloudOrgEditVo);            
        //1,签名校验
        boolean verifyResult = verifySign(timestamp,sign);
        if(!verifyResult){
            return this.returnException(501, "sign的值错误");
        }
        //操作类型
        Integer opttype =  ecloudOrgEditVo.getInteger("opttype");
        //企业编码
        String custcode =  ecloudOrgEditVo.getString("custcode");
        Long effecttime =  ecloudOrgEditVo.getLong("effecttime");
        Date endTime = new Date(effecttime);
        if(opttype==0||opttype==1){  //退订或者暂停
            Map<String,Object> userMap = userService.getUserByThirdId(custcode);            
            Map<String, Object> paramMap = new HashMap<String,Object>();
            paramMap.put("KU_ID", userMap.get("KU_ID"));              //用户id
            paramMap.put("KU_TRYSDATE", DateUtil.toString(endTime));  //到期时间
            paramMap.put("userStatus", "2"); //停用
            userService.cancelUser(paramMap);
        }         
        return this.returnResult(true, "");
    }
    
    /**
     * 成员业务开通与变更接口 (SaaS0003)
     */
    @ResponseBody
    @RequestMapping(value = "/saas0003", method = RequestMethod.POST,produces={"application/json"},consumes={"application/json"})
    public JSONObject saas0003(HttpServletRequest request,
            @RequestParam(value = "timestamp", required = true)  String timestamp,
            @RequestParam(value = "sign", required = true)  String sign,
            @RequestBody JSONObject ecloudUserVo    
            ){
        logger.debug("EcloudController--saas0003---"+ecloudUserVo);
        //1,签名校验
        boolean verifyResult = verifySign(timestamp,sign);
        if(!verifyResult){
            return this.returnException(501, "sign的值错误");
        }
        return this.returnSuccess(true, "");
    }
    
    /**
     * 成员业务状态变更接口(SaaS0004)
     */
    @ResponseBody
    @RequestMapping(value = "/saas0004", method = RequestMethod.POST,produces={"application/json"},consumes={"application/json"})
    public JSONObject saas0004(HttpServletRequest request,
            @RequestParam(value = "timestamp", required = true)  String timestamp,
            @RequestParam(value = "sign", required = true)  String sign,
            @RequestBody JSONObject ecloudUserEditVo    
            ){
        logger.debug("EcloudController--saas0004---"+ecloudUserEditVo);
        //1,签名校验
        boolean verifyResult = verifySign(timestamp,sign);
        if(!verifyResult){
            return this.returnException(501, "sign的值错误");
        }
        return this.returnSuccess(true, "");
    }
    
    /**
     * 业务参数校验接口(SaaS0005)
     */
    @ResponseBody
    @RequestMapping(value = "/saas0005", method = RequestMethod.POST,produces={"application/json"},consumes={"application/json"})
    public JSONObject saas0005(HttpServletRequest request,
            @RequestParam(value = "timestamp", required = true)  String timestamp,
            @RequestParam(value = "sign", required = true)  String sign,
            @RequestBody JSONObject ecloudUserEditVo){
        logger.debug("EcloudController--saas0005---"+ecloudUserEditVo);
        //1,签名校验
        boolean verifyResult = verifySign(timestamp,sign);
        if(!verifyResult){
            return this.returnException(501, "sign的值错误");
        }
        return this.returnResult(true, "");
    }
    
    /**
     * 数据变更通知接口 (SaaS3001)
     */
    @ResponseBody
    @RequestMapping(value = "/saas3001", method = RequestMethod.POST,produces={"application/json"},consumes={"application/json"})
    public JSONObject saas3001(HttpServletRequest request,
            @RequestParam(value = "timestamp", required = true)  String timestamp,
            @RequestParam(value = "sign", required = true) String sign,
            @RequestBody JSONObject ecloudUserEditVo
            ){
        logger.debug("EcloudController--saas3001---"+ecloudUserEditVo);
        //1,签名校验
        boolean verifyResult = verifySign(timestamp,sign);
        if(!verifyResult){
            return this.returnException(501, "sign的值错误");
        }
        return this.returnResult(true, "");
    }
    
    
    private JSONObject returnResult(Boolean result,String errmsg){
        JSONObject json = new JSONObject();
        json.put("result", result);
        json.put("errmsg", errmsg);
        return json;
    }
    
    private JSONObject returnSuccess(Boolean result,String errmsg){
        JSONObject json = new JSONObject();
        json.put("success", result);
        json.put("errmsg", errmsg);
        return json;
    }
    
    private JSONObject returnException(Integer code,String msg){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }
    
    private boolean verifySign(String timestamp,String sign){
        String clientId = PropertiesUtil.getValue("ecloud.clientId");
        String clientkey = PropertiesUtil.getValue("ecloud.clientKey");
        StringBuffer sb = new StringBuffer("client_id=");
        sb.append(clientId);
        sb.append("client_key=");
        sb.append(clientkey);
        sb.append("timestamp=");
        sb.append(timestamp);
        String signStr = CMyEncrypt.md5(sb.toString());
        if(!signStr.equals(sign)){
            return false;//
        }else{
            return true;
        }
    }
    
    private String getVersion(String code){
        String version = "B1";
        if("111800100107".equals(code)){
            version ="B1";
        }else if("111800100108".equals(code)){
            version ="B2";
        }else if("111800100109".equals(code)){
            version ="B3";
        }
        return version;
    }
    
    private void initParam(JSONArray jsonArray,Map<String,Object> map){
        for(int i=0;i<jsonArray.size();i++){
            JSONObject json = jsonArray.getJSONObject(i);
            if("0".equals(json.getString("opttype"))){
                map.put("endtime", json.getString("endtime"));
                map.put("version",getVersion(json.getString("code")));
            }
        }
    }
    
}
