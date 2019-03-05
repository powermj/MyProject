package com.zhxg.yqzj.web.api.v1;

import java.util.HashMap;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.utils.CMyEncrypt;
import com.zhxg.framework.base.utils.HttpsUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.yqzj.entities.v1.UserVo;
import com.zhxg.yqzj.service.v1.UserService;




@RestController
@RequestMapping("/api/saascloud")
public class SaasCloudController{
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private static final String YDZX="YDZX";
    
    private static final String ASYQ="ASYQ";
    
    private final static Logger log = LoggerFactory.getLogger(SaasCloudController.class);
    
    @Autowired
    private UserService userService;
    
    /**
     * 登录，鉴权，获取用户信息
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET,consumes = "application/json")
    public JSONObject login(HttpServletRequest request,
            @RequestParam(value = "client_id", required = false)  String clientId,
            @RequestParam(value = "code", required = false)  String code,
            @RequestParam(value = "appid", required = false)  String appid,
            @RequestParam(value = "packagecode", required = false)  String packagecode) { 
        
            String asyqClientId = PropertiesUtil.getValue("asyq.clientId");
            String ydclientId = PropertiesUtil.getValue("yidianzx.clientId");
            if(!StringUtils.isEmpty(clientId)){
                if(clientId.equals(ydclientId)){
                  //2,获取用户信息
                    String userInfoUrl = PropertiesUtil.getValue("yidianzx.userAuth.url");
                    userInfoUrl = userInfoUrl+"?token="+code;
                    String userInfo = HttpsUtil.httpsGet(userInfoUrl, null); //HttpUtil.post(userInfoUrl, param, heads);//
                    logger.info("-----------userInfo-----------"+userInfo);
                    JSONObject userInfoJson = (JSONObject) JSONObject.parse(userInfo);
                    
                    if(userInfoJson.getJSONObject("result")!=null){
                        String custcode = userInfoJson.getJSONObject("result").getString("ydid");
                        //3,秘书用户校验
                        Map<String,Object> userMap = userService.getUserByThirdId(custcode);
                        if(!CollectionUtils.isEmpty(userMap)){
                            JSONObject json = (JSONObject) JSONObject.toJSON(userMap);
                            return json;
                        }
                    }
                }else if(clientId.equals(asyqClientId)){
                  //2,获取用户信息
                    String userInfoUrl = PropertiesUtil.getValue("asyq.userAuth.url");
                    userInfoUrl = userInfoUrl+"?token="+code;
                    String userInfo = HttpsUtil.httpsGet(userInfoUrl, null); //HttpUtil.post(userInfoUrl, param, heads);//
                    logger.info("ASYQ--userInfo:"+userInfo);
                    JSONObject userInfoJson = (JSONObject) JSONObject.parse(userInfo);                    
                    if(userInfoJson.getJSONObject("result")!=null){
                        String custcode = userInfoJson.getJSONObject("result").getString("msid");
                        //3,秘书用户校验
                        Map<String,Object> userMap = userService.getUserByThirdId(custcode);
                        if(!CollectionUtils.isEmpty(userMap)){
                            JSONObject json = (JSONObject) JSONObject.toJSON(userMap);
                            return json;
                        }
                    }
                }              
            }
            return new JSONObject();
    }
       
    /**
     * 创建用户
     * @param request
     * @param timestamp
     * @param sign
     * @param clientId
     * @param saasCustomer
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/customer/create",method = RequestMethod.POST,produces={"application/json"},consumes={"application/json;charset=utf-8"})
    public JSONObject customerCreate(HttpServletRequest request,
            @RequestParam(value = "timestamp", required = false)  String timestamp,
            @RequestParam(value = "sign", required = false)  String sign,
            @RequestParam(value = "client_id", required = false)  String clientId,
            @RequestBody JSONObject saasCustomer) { 
        String asyqClientId = PropertiesUtil.getValue("asyq.clientId");
        String ydclientId = PropertiesUtil.getValue("yidianzx.clientId");
        if(!StringUtils.isEmpty(clientId)){
            if(clientId.equals(ydclientId)){
                RedisUtil.rpush("ydzx_create_list",saasCustomer);
                //1,签名校验
                boolean verifyResult = verifySign(timestamp,sign,clientId);
                if(!verifyResult){
                    return this.returnResult(501,"sign的值错误",null);
                }   
                //2,用户信息
                String costomerCode = YDZX+saasCustomer.getString("customer_code");
                UserVo uservo = new UserVo();
                uservo.setThirdKey(YDZX);
                uservo.setKU_LID(costomerCode);
                uservo.setLogName(saasCustomer.getString("customer_name"));
                uservo.setZsname(YDZX+saasCustomer.getString("customer_name"));  //ku_name
                uservo.setUemail(saasCustomer.getString("email"));     
                uservo.setUserphone(saasCustomer.getString("mobile"));
                uservo.setKU_NAME(YDZX+saasCustomer.getString("customer_name"));
                uservo.setF_TIME(saasCustomer.getString("end_date"));
                uservo.setThirdId(saasCustomer.getString("customer_code"));
                uservo.setApplyno(saasCustomer.getString("orderno"));
                uservo.setTrial(saasCustomer.getString("trial"));        
                String packageCode= saasCustomer.getString("package_code");
                uservo.setFeeType(getVersion(packageCode));
                String value = RedisUtil.hget("ecloud_uservoset", costomerCode);
                if(StringUtils.isEmpty(value)){
                    RedisUtil.hset("ecloud_uservoset", costomerCode, "Y");
                    RedisUtil.rpush("ecloud_uservoList", JSONObject.toJSON(uservo));
                }
                JSONObject data = new JSONObject();
                data.put("customer_code", saasCustomer.getString("customer_code"));
                data.put("ms_usercode", costomerCode);
                return this.returnResult(200, "success",data);
            }else if(clientId.equals(asyqClientId)){
                RedisUtil.rpush("asyq_create_list",saasCustomer);
                //1,签名校验
                boolean verifyResult = verifySign_asyq(timestamp,sign,clientId);
                if(!verifyResult){
                    return this.returnResult(501,"sign的值错误",null);
                }   
                //2,用户信息
                String costomerCode = ASYQ+saasCustomer.getString("customer_code");
                UserVo uservo = new UserVo();
                uservo.setThirdKey(ASYQ);
                uservo.setKU_LID(costomerCode);
                uservo.setLogName(saasCustomer.getString("customer_name"));
                uservo.setZsname(ASYQ+saasCustomer.getString("customer_name"));  //ku_name
                uservo.setUemail(saasCustomer.getString("email"));     
                uservo.setUserphone(saasCustomer.getString("mobile"));
                uservo.setKU_NAME(ASYQ+saasCustomer.getString("customer_name"));
                uservo.setF_TIME(saasCustomer.getString("end_date"));
                uservo.setThirdId(saasCustomer.getString("customer_code"));
                uservo.setApplyno(saasCustomer.getString("orderno"));
                uservo.setTrial(saasCustomer.getString("trial"));        
                String packageCode= saasCustomer.getString("package_code");
                uservo.setFeeType(getVersion(packageCode));
                String value = RedisUtil.hget("ecloud_uservoset", costomerCode);
                if(StringUtils.isEmpty(value)){
                    RedisUtil.hset("ecloud_uservoset", costomerCode, "Y");
                    RedisUtil.rpush("ecloud_uservoList", JSONObject.toJSON(uservo));
                }
                JSONObject data = new JSONObject();
                data.put("customer_code", saasCustomer.getString("customer_code"));
                data.put("ms_usercode", costomerCode);
                return this.returnResult(200, "success",data);
            }
        }
        return new JSONObject();
    }  
       
    /**
     * 上报接口
     * @param request
     * @param timestamp
     * @param sign
     * @param clientId
     * @param saasCustomer
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/report",method = {RequestMethod.GET,RequestMethod.POST},produces={"application/json"},consumes={"application/json"})
    public JSONObject report(HttpServletRequest request,
            @RequestParam(value = "action_word", required = false)  String action_word,
            @RequestParam(value = "authentication", required = false)  String authentication,
            @RequestParam(value = "cate", required = false)  String cate,
            @RequestParam(value = "content", required = false)  String content,
            @RequestParam(value = "other", required = false)  String other,
            @RequestParam(value = "site", required = false)  String site,
            @RequestParam(value = "site_cate", required = false)  String site_cate,
            @RequestParam(value = "site_num", required = false)  String site_num,
            @RequestParam(value = "site_pos", required = false)  String site_pos,
            @RequestParam(value = "title", required = false)  String title,
            @RequestParam(value = "type", required = false)  String type,
            @RequestParam(value = "url", required = false)  String url,
            @RequestParam(value = "web_pic", required = false)  String web_pic,
            @RequestParam(value = "msid", required = false)  String msid){ 

        String reportUrl = PropertiesUtil.getValue("yidianzx.report.url");
        Map<String, String> param = new HashMap<>();
        param.put("action_word", action_word);
        param.put("authentication", authentication);
        param.put("cate", cate);
        param.put("content", content);
        param.put("other", other);
        param.put("site", site);
        param.put("site_cate", site_cate);
        param.put("site_num", site_num);
        param.put("site_pos", site_pos);
        param.put("title", title);
        param.put("type", type);
        param.put("url", url);
        param.put("web_pic", web_pic);
        param.put("msid", msid);
        String reportReport = HttpsUtil.httpsPost(reportUrl, param);
        log.info("url="+url+",reportReport="+reportReport);  
        JSONObject json = new JSONObject();
        if(!StringUtils.isEmpty(reportReport)){
            JSONObject result = JSONObject.parseObject(reportReport);
            if("success".equals(result.getString("status"))){
                json.put("status", "success");
                json.put("code", 200);
                json.put("result", result.getJSONObject("result").getString("redirect"));
                return json;
            }else{
                json.put("status",result.getString("status"));
                json.put("code",result.getString("errorCode"));
                json.put("result",result.getString("reason"));
                return json;
            }
        }
        json.put("status", "failed");
        json.put("code",500);
        return json;

    } 
    
    private JSONObject returnResult(Integer result,String errmsg,JSONObject data){
        JSONObject json = new JSONObject();
        json.put("code", result);
        json.put("msg", errmsg);
        if(data!=null){
            json.put("data", data);
        }
        return json;
    }   
    
    private boolean verifySign(String timestamp,String sign,String clientId){
        String clientkey = PropertiesUtil.getValue("yidianzx.clientKey");
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
    
    private boolean verifySign_asyq(String timestamp,String sign,String clientId){
        String clientkey = PropertiesUtil.getValue("asyq.clientKey");
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
        if("B1".equals(code)||"B2".equals(code)||"B3".equals(code)||"B4".equals(code)){
            return code;
        }else{
            return "B4";
        }
    }

}
