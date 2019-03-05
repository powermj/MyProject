package com.zhxg.yqzj.service.impl.v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.zhxg.framework.base.constants.Constant;
import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.Pagination;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.exception.SysException;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.HtmlFilter;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.ParamsUtil;
import com.zhxg.yqzj.dao.v1.EventAnalysisDataDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.entities.v1.EventAnalysisData;
import com.zhxg.yqzj.service.exception.myfocus.MyFocusInfoNotFoundException;
import com.zhxg.yqzj.service.v1.AddSendMailService;

/**
 * <p>Description: 我的关注接口实现类</p>
 * @author zyl
 * @date 2017年11月8日
 * @version 1.0
 */
@Service
public class AddSendMailServiceImpl extends BaseServiceImpl<BaseEntity> implements AddSendMailService {
    
    @Autowired
    private EventAnalysisDataDao eventAnalysisDataDao;
    
    @Autowired
    private UserDao userDao;
    
    private static final String EVENT_INFO_NOT_EXIST = "事件信息不存在";
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return userDao;
    }
    private final static Logger log = LoggerFactory.getLogger(AddSendMailServiceImpl.class);
    
    @Override
    public int addSendEmail(Map<String, Object> params) throws SysException, MyFocusInfoNotFoundException {
        String reason = ParamsUtil.nullDeal(params, "reason", "");//上报原因
        String email = ParamsUtil.nullDeal(params, "email", ""); //邮箱地址，多个逗号分隔开
        String kmIds = ParamsUtil.nullDeal(params, "infoIds", ""); //信息id多个逗号分隔开
        StringBuilder sb = new StringBuilder();//邮件内容
        
        List<String> infoIds = new ArrayList<>();//kvuuids集合
        //kv_uuid分隔成数组
        String[] kmIdsArray = "".equals(kmIds) ? null : kmIds.split(",");
        //添加到集合
        if(kmIdsArray != null && kmIdsArray.length > 0){
            for(String str : kmIdsArray){
                infoIds.add(str);
            }
        }
        //查询参数
        params.put("infoIds", infoIds);
        //从关注表中查询信息和正文
        Pagination pageInfo = new Pagination(0,100);
        PageInfo<EventAnalysisData> result = eventAnalysisDataDao.selectEventAnalysisDataList_self(params, pageInfo);
        List<EventAnalysisData> eventAnalysisDatalist = result.getList();
        //信息不存在  返回
        if(eventAnalysisDatalist == null||eventAnalysisDatalist.size() == 0){
            throw new MyFocusInfoNotFoundException(EVENT_INFO_NOT_EXIST);
        }
        for(EventAnalysisData eventAnalysisData:eventAnalysisDatalist){
            sb.setLength(0);//清空内容
            String title = eventAnalysisData.getTitle();//标题
            String content = eventAnalysisData.getContent();//内容
            String url = eventAnalysisData.getUrl();//url
            String site = eventAnalysisData.getWebname();//站点
            String time = eventAnalysisData.getCtime();//时间
            String author = eventAnalysisData.getAuthor();//作者
            String orientation = eventAnalysisData.getOrientationDesc();//倾向性，1.正面，2.负面，3.中性
            String transport = String.valueOf(eventAnalysisData.getRepeatCount());//转载数
                
            if(!"".equals(title)){
                title = HtmlFilter.filterWord(title);
                title = title.replace("\r\n", " ").replace("\r", " ").replaceAll("\\[beginimg\\](.*?)\\[endimg\\]", "").replace("<br />", " ").replace("<br/>", " ").replaceAll("beginimg(.*?)endimg", "");
            }
            if(!"".equals(content)){
                content = HtmlFilter.filterWord(content);
                content = content.replace("\r\n", " ").replace("\r", " ").replaceAll("\\[beginimg\\](.*?)\\[endimg\\]", "").replace("<br />", " ").replace("<br/>", " ").replaceAll("beginimg(.*?)endimg", "");
            }
                
            sb.append("尊敬的领导：您好，监测到以下信息：\n");
            sb.append("    标题：").append(title).append("\n");
            sb.append("    作者：").append(author).append("\n");
            sb.append("    时间：").append(time).append("\n");
            sb.append("    网站：").append(site).append("\n");
            sb.append("    地址：").append(url).append("\n");
            sb.append("    属性：").append(orientation).append("\n");
            sb.append("    转发数：").append(transport).append("\n");
            sb.append("    内容：").append(content).append("\n");
            sb.append("    上报原因：").append(reason);
        }
        
        //多个邮箱分隔成数组
        String[] emailArray = email.split(",");
        for(String str : emailArray){
            try {
                Map<String,String> map = new HashMap<>();
                map.put("title", Base64.encodeBase64URLSafeString("舆情监测数据".getBytes("UTF-8")));
                map.put("content", Base64.encodeBase64URLSafeString(sb.toString().getBytes("UTF-8")));
                map.put("email", str);
                map.put("encode", "base64"); //内容和标题以base64编码格式，默认不编码
                map.put("type", "text"); //以纯文本邮件发送，默认为HTML格式
                
                HttpUtil.post(Constant.PUSH_SERVER_URL,JSONObject.toJSONString(map));
                
            } catch (Exception e) {
                log.error("-------------------" + e.getMessage());
            }
        }
        
        return 1;
    }
}
