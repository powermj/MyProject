package com.zhxg.yqzj.web.api.v1;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.zhxg.framework.base.utils.CMyEncrypt;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.RedisUtil;
import com.zhxg.yqzj.entities.v1.UserVo;
import com.zhxg.yqzj.service.v1.UserService;




@RestController
@RequestMapping("/api/nsfocus")
public class NsfocusCloudController{
    
    @Autowired
    private UserService userService;
    
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.GET,consumes = "application/json")
    public JSONObject login(HttpServletRequest request,
            @RequestParam(value = "action", required = false)  String action,
            @RequestParam(value = "sign", required = false)  String sign,
            @RequestParam(value = "customer_id", required = false)  String customer_id,
            @RequestParam(value = "timestrap", required = false)  String timestrap) { 
        
            boolean result = verifyTimeStrap(timestrap);
            if(!result){
                return this.returnResult(502,"链接已失效！",null);
            }
        
            Map<String, String> map = new TreeMap<String, String>();
            map.put("action", action);
            map.put("customer_id", customer_id);
            boolean verifyResult = nsfocusVerifySign(sign,timestrap,map);
            if(!verifyResult){
                return this.returnResult(501,"sign的值错误",null);
            }  
            Map<String,Object> userMap = userService.getUserByThirdId(customer_id);
            if(!CollectionUtils.isEmpty(userMap)){
                JSONObject json = (JSONObject) JSONObject.toJSON(userMap);
                return json;
            }
            return new JSONObject();
    }
    
    
    /**
     * nsfocus
     */
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
    @RequestMapping(value = "/customer/create",method = {RequestMethod.GET,RequestMethod.POST})
    public JSONObject customerSync(HttpServletRequest request,
            @RequestParam(value = "sign", required = true)  String sign,
            @RequestParam(value = "action", required = true)  String action,
            @RequestParam(value = "customer_id", required = true)  String customer_id,
            @RequestParam(value = "customer_name", required = true)  String customer_name,
            @RequestParam(value = "service_id", required = true)  String service_id,
            @RequestParam(value = "order_id", required = true)  String order_id,
            @RequestParam(value = "sku_id", required = true)  String sku_id,
            @RequestParam(value = "trial", required = false)  String trial,
            @RequestParam(value = "start_date", required = false)  String start_date,
            @RequestParam(value = "end_date", required = false)  String end_date,
            @RequestParam(value = "email", required = false)  String email,
            @RequestParam(value = "mobil", required = false)  String mobil,
            @RequestParam(value = "timestrap", required = false)  String timestrap
            ) {  
            boolean result = verifyTimeStrap(timestrap);
            if(!result){
                return this.returnResult("false");
            }
            Map<String, String> map = new TreeMap<String, String>();
            map.put("action", action);
            map.put("customer_id", customer_id);
            map.put("customer_name", customer_name);
            map.put("service_id", service_id);
            map.put("order_id", order_id);
            map.put("sku_id", sku_id);
            if(!StringUtils.isEmpty(trial)){
                map.put("trial", trial);
            }
            if(!StringUtils.isEmpty(start_date)){
                map.put("start_date", start_date);
            }
            if(!StringUtils.isEmpty(end_date)){
                map.put("end_date",end_date);
            }
            if(!StringUtils.isEmpty(email)){
                map.put("email", email);
            }
            if(!StringUtils.isEmpty(mobil)){
                map.put("mobil", mobil);
            }
            boolean verifyResult = nsfocusVerifySign(sign,timestrap,map);
            if(!verifyResult){
                return this.returnResult("false");
            } 
            //2,用户信息
            String kulid ="LMY"+customer_name;
            UserVo uservo = new UserVo();
            uservo.setThirdKey("LMY");
            uservo.setKU_LID(kulid);
            uservo.setLogName(kulid);
            uservo.setZsname(customer_name);  //ku_name
            uservo.setUemail(email);     
            uservo.setUserphone(mobil);
            Date ed = DateUtil.toDate(end_date);
            Long edTime = ed.getTime();            
            uservo.setF_TIME(edTime.toString());
            uservo.setThirdId(customer_id);
            uservo.setApplyno(order_id);
            String feeType = getVersion(sku_id);
            uservo.setFeeType(feeType);
            if(!"B4".equals(feeType)){
                uservo.setTrial(String.valueOf(0));
            }else{
                uservo.setTrial(String.valueOf(1));
            }
            String appKey = PropertiesUtil.getValue("nsfocus.app_key");
            String value = RedisUtil.hget("ecloud_uservoset", appKey+"_"+customer_id);
            if(StringUtils.isEmpty(value)){
                RedisUtil.hset("ecloud_uservoset", appKey+"_"+customer_id, "Y");
                RedisUtil.rpush("ecloud_uservoList", JSONObject.toJSON(uservo));
            }
            return this.returnResult("true");
    }  
    
    /**
     * nsfocus校验参数
     */
    private boolean nsfocusVerifySign(String sign,String timestrap,Map<String,String> param){        
        StringBuffer sb = new StringBuffer("");
        for(String key:param.keySet()){
            sb.append(key).append("=").append(param.get(key)).append("&");
        }
        String app_secret = PropertiesUtil.getValue("nsfocus.app_secret");
        sb.append("timestrap=").append(timestrap).append("&");
        sb.append("app_secret=").append(app_secret);
        String signStr = CMyEncrypt.md5(sb.toString());
        if(!signStr.equalsIgnoreCase(sign)){
            return false;//
        }else{
            return true;
        }
    }
    
    private JSONObject returnResult(String result){
        JSONObject json = new JSONObject();
        json.put("success", result);
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
    
    private boolean verifyTimeStrap(String timestrap){
        //校验时间戳
        Date now = new Date();
        Long nowTime = now.getTime()/1000;
        Long time = Long.parseLong(timestrap);
        time = time+3000;  //300
        if(time>nowTime){
            return true;
        }
        return false;
    }
    
    private String getVersion(String code){
        String version = "B4";
        if("yqms-1".equals(code)){
            version ="B1";
        }else if("yqms-2".equals(code)){
            version ="B2";
        }else if("yqms-3".equals(code)){
            version ="B3";
        }
        return version;
    }
    
}
