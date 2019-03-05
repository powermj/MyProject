package com.zhxg.yqzj.service.impl.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.curd.impl.BaseEntity;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.DateUtil;
import com.zhxg.framework.base.utils.HttpUtil;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.yqzj.dao.v1.MobileCloudCourseDao;
import com.zhxg.yqzj.dao.v1.MobileCloudEventInfoDao;
import com.zhxg.yqzj.dao.v1.MobileCloudIndustryInfoDao;
import com.zhxg.yqzj.dao.v1.MobileCloudRecommendTopicDao;
import com.zhxg.yqzj.dao.v1.MobileCloudRegionalInfoDao;
import com.zhxg.yqzj.dao.v1.MobileCloudSubjectInfoDao;
import com.zhxg.yqzj.dao.v1.UserAreaNewInfoDao;
import com.zhxg.yqzj.dao.v1.UserBaseInfoDao;
import com.zhxg.yqzj.dao.v1.UserDao;
import com.zhxg.yqzj.entities.v1.MobileCloudCourse;
import com.zhxg.yqzj.entities.v1.MobileCloudEventInfo;
import com.zhxg.yqzj.entities.v1.MobileCloudIndustryInfo;
import com.zhxg.yqzj.entities.v1.MobileCloudRecommendTopic;
import com.zhxg.yqzj.entities.v1.MobileCloudRegionalInfo;
import com.zhxg.yqzj.entities.v1.MobileCloudSubjectInfo;
import com.zhxg.yqzj.entities.v1.UserAreaNewInfo;
import com.zhxg.yqzj.entities.v1.UserBaseInfo;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudDelUserInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudSaveSpecialInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudUpdateUserInfoException;
import com.zhxg.yqzj.service.exception.mobilecloud.MobileCloudUserInfoLoseException;
import com.zhxg.yqzj.service.util.MobileCloudVersionControlUtil;
import com.zhxg.yqzj.service.v1.MobileCloudService;

@Service
public class MobileCloudServiceImpl extends BaseServiceImpl<BaseEntity> implements MobileCloudService {

    @Autowired
    private MobileCloudIndustryInfoDao mobileCloudIndustryInfoDao;
    @Autowired
    private MobileCloudRegionalInfoDao mobileCloudRegionalInfoDao;
    @Autowired
    private MobileCloudRecommendTopicDao mobileCloudRecommendTopicDao;
    @Autowired
    private UserBaseInfoDao userBaseInfoDao;
    @Autowired
    private MobileCloudSubjectInfoDao mobileCloudSubjectInfoDao;
    @Autowired
    private MobileCloudEventInfoDao mobileCloudEventInfoDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserAreaNewInfoDao userAreaNewInfoDao;
    @Autowired
    private MobileCloudCourseDao mobileCloudCourseDao;
    
    private static final  String MOBILECLOUD_USER_LOSE = "移动云用户信息丢失";
    private static final  String MOBILECLOUD_UPDATEUSER_ERR = "更新移动云用户信息失败";
    private static final  String MOBILECLOUD_DELUSER_ERR = "移除移动云用户信息失败";
    private static final  String MOBILECLOUD_SAVESPECIAL_ERR = "移除移动云用户信息失败";
    private static final String TYPE_KU_THIRDNAME = "KU_THIRDNAME";
    private static final String TYPE_KU_THIRDNAME_VALUE = "YDY";
    private static final String TABLE_USERBASEINFO_NAME = "userBaseInfo";
    private static final String TABLE_USERSERVICE_NAME = "userService";
    private static final String KK_DINGXIANG_SOURCETYPE = "01,02,04,08,06,03,05,07,09,99,10";
    
    
    @Override
    protected BaseDao<BaseEntity> getBaseDao() {
        return this.mobileCloudIndustryInfoDao;
    }

    /**
     * 获取行业信息
     */
    @Override
    public List<MobileCloudIndustryInfo> getAllIndustryInfo(Map<String, Object> params) {
        List<MobileCloudIndustryInfo> allIndustryInfo = mobileCloudIndustryInfoDao.getAllIndustryInfo(params);
        return allIndustryInfo;
    }

    /**
     * 获取地域信息
     */
    @Override
    public List<MobileCloudRegionalInfo> getAllRegionalInfo(Map<String, Object> params) {
        List<MobileCloudRegionalInfo> allRegionalInfo = mobileCloudRegionalInfoDao.getAllRegionalInfo(params);
        return allRegionalInfo;
    }

    /**
     * 获取推荐专题信息
     */
    @Override
    public List<MobileCloudRecommendTopic> getAllRecommendTopic(Map<String, Object> params) {
        //系统设置的推荐专题需要查询二级行业id
        UserAreaNewInfo userAreaNewInfo = null;
        String userSex = null;
        if(params.get("departmentId") == null){
            String departmentId = userBaseInfoDao.getMobileCloudDepartmentId(params);
            params.put("departmentId", departmentId);
            userAreaNewInfo = userAreaNewInfoDao.getUserAreaNewInfo(params);
            userSex = userBaseInfoDao.getMobileCloudSex(params);
        }
        List<MobileCloudRecommendTopic> allRecommendTopic = mobileCloudRecommendTopicDao.getAllRecommendTopic(params);
        //返回用户地域信息 方便 系统设置添加推荐专题
        if(userAreaNewInfo != null){
            for (MobileCloudRecommendTopic mobileCloudRecommendTopic : allRecommendTopic) {
                mobileCloudRecommendTopic.setProvinceId(userAreaNewInfo.getProvinceId());
                mobileCloudRecommendTopic.setCityId(userAreaNewInfo.getCityId());
                mobileCloudRecommendTopic.setLevel(userAreaNewInfo.getLevel());
                mobileCloudRecommendTopic.setCustomerType(userSex);
            }
        }
        return allRecommendTopic;
    }

    /**
     * 保存推荐专题
     * @throws MobileCloudUserInfoLoseException 
     * @throws MobileCloudUpdateUserInfoException 
     * @throws MobileCloudDelUserInfoException 
     * @throws MobileCloudSaveSpecialInfoException 
     */
    @Override
    public List<Map<String, Object>> setSpecialInfo(Map<String, Object> params) throws MobileCloudUserInfoLoseException, 
    MobileCloudUpdateUserInfoException, MobileCloudDelUserInfoException, MobileCloudSaveSpecialInfoException {
        //处理移动云用户信息
        this.handleUserInfo(params);
        //获取保存专题参数并调用老接口完成推荐专题的添加
        Map<String, Object> addSpecialBackInfoMap = new HashMap<>(); 
        String specialParam = this.getSpecialParam(params);
        addSpecialBackInfoMap.put("addSpecialBackInfo", specialParam);
        //专题添加完成后删除用户第一次登陆信息
        int delFirstLoginInfo = this.delFirstLoginInfo(params);
        addSpecialBackInfoMap.put("delFirstLoginInfo", delFirstLoginInfo);
        //获取用户最新信息
        List<Map<String, Object>> userInfo = this.getNewUserInfo(params);
        userInfo.add(addSpecialBackInfoMap);
        return userInfo;
    }
    
    
    /**
     * 保存推荐专题
     * @throws MobileCloudSaveSpecialInfoException 
     */
    @Override
    public List<Map<String, Object>> setSpecialInfoForInstall(Map<String, Object> params) throws MobileCloudSaveSpecialInfoException {
        //获取保存专题参数并调用老接口完成推荐专题的添加
        Map<String, Object> addSpecialBackInfoMap = new HashMap<>(); 
        String specialParam = this.getSpecialParam(params);
        addSpecialBackInfoMap.put("addSpecialBackInfo", specialParam);
        //获取用户最新信息
        List<Map<String, Object>> userInfo = this.getNewUserInfo(params);
        userInfo.add(addSpecialBackInfoMap);
        return userInfo;
    }
 
    /**
     * 处理移动云用户信息
     *
     * @param params
     * @throws MobileCloudUserInfoLoseException 
     * @throws MobileCloudUpdateUserInfoException 
     */
    private void handleUserInfo(Map<String, Object> params) throws MobileCloudUserInfoLoseException, MobileCloudUpdateUserInfoException{
        //查询移动云账户
        Map<String, Object> userBaseInfoMap = this.handleParamMap(params,TABLE_USERBASEINFO_NAME);
        UserBaseInfo userBaseInfo = userBaseInfoDao.getUserBaseInfoByType(userBaseInfoMap);
        if(userBaseInfo == null){
            //若移动云账户不存在 德富入库信息存在问题 2019001
            throw new MobileCloudUserInfoLoseException(MOBILECLOUD_USER_LOSE);
        }
        
        try {
            //移动云账户存在 则更新用户信息
            //1.更新用户 关键词数 、 行业 、后台是否允许设置品牌词
            Map<String, Object> userServiceMap = this.handleParamMap(params, TABLE_USERSERVICE_NAME);
            userBaseInfoDao.updateUserServiceInfo(userServiceMap);
            //2.更新用户 名称 、手机号码 、 用户类型
            userBaseInfoDao.updateUserInfo(params);
            //3.更新用户 地域省市 信息
            userBaseInfoDao.updateUserAreaNewInfo(params);
        } catch (Exception e) {
            //更新移动云用户信息失败2019002
            throw new MobileCloudUpdateUserInfoException(MOBILECLOUD_UPDATEUSER_ERR);
        }
        
    }
   
    /**
     * 整理用户信息参数 
     * @param params
     * @return
     */
    private Map<String, Object> handleParamMap(Map<String, Object> params,String infoType){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.putAll(params);
        if(TABLE_USERBASEINFO_NAME.equals(infoType)){
            //组织参数
            paramMap.put("kuType", TYPE_KU_THIRDNAME);
            paramMap.put("kuValue", TYPE_KU_THIRDNAME_VALUE);
        }
        
        if(TABLE_USERSERVICE_NAME.equals(infoType)){
            //获取用户秘书版本
            String mobileCloudVersion = userBaseInfoDao.getMobileCloudVersion(params);
            String customerType = String.valueOf(params.get("customerType"));
            int keyWordNum = MobileCloudVersionControlUtil.getKeyWordNum(customerType, mobileCloudVersion);
            //若当前用户是企业用户则将用户是否允许设置品牌词开关打开
            if("4".equals(customerType)){
                paramMap.put("kuAreaSet", "1");
            }
            //组织参数
            paramMap.put("keyWordNum", keyWordNum);
        }
        
        return paramMap;
    }
    
    /**
     * 处理保存专题参数 
     * @param params
     * @throws MobileCloudSaveSpecialInfoException 
     */
    private String getSpecialParam(Map<String, Object> params) throws MobileCloudSaveSpecialInfoException{
        //判断当前移动云用户类型 政府/企业
        String customerType = String.valueOf(params.get("customerType"));
        //政府用户需要查询地域词
        List<MobileCloudRegionalInfo> specialRegionalInfo = null;
        String cityId = null;
        if("1".equals(customerType)){
            //查询政府用户推荐专题地域词
            cityId = String.valueOf(params.get("cityId"));
            if(StringUtils.isNotBlank(cityId)){
                params.put("parentId",cityId);
            }else{
                String provinceId = String.valueOf(params.get("provinceId"));
                params.put("parentId",provinceId);
            };
            specialRegionalInfo = mobileCloudRegionalInfoDao.getSpecialRegionalInfo(params);
        }
        String regionalParam = null;
        if(specialRegionalInfo != null){
            regionalParam = handleSpecialParam(specialRegionalInfo,cityId);
        }
        //查询主体词
        //MobileCloudSubjectInfo subjectWord = mobileCloudSubjectInfoDao.getSubjectWord(params);
        //查询推荐专题事件词
        List<MobileCloudEventInfo> eventWord = mobileCloudEventInfoDao.getEventWord(params);
        //调用老接口添加推荐专题
        String resultMsg = this.addSpecial(params, regionalParam, null, eventWord);
        return resultMsg;
    }
    /**
     * 组织推荐专题地域词参数 
     */
    private String handleSpecialParam(List<MobileCloudRegionalInfo> specialRegionalInfo,String cityId){
        String provinceName = "";
        String cityName = "";
        String cityParam = "";
        String countyParam = "";
        String regionalParam = "";
        for (MobileCloudRegionalInfo mobileCloudRegionalInfo : specialRegionalInfo) {
            provinceName = mobileCloudRegionalInfo.getProvinceName();
            cityName = mobileCloudRegionalInfo.getCity();
            String county = mobileCloudRegionalInfo.getCounty();
            if(StringUtils.isNotBlank(cityId)){
                countyParam += " " + county;
            }else{
                cityParam += " " + cityName;
            }
            
        }
        if(StringUtils.isNotBlank(cityId)){
            regionalParam = cityName+countyParam;
        }else{
            regionalParam = provinceName+cityParam;  
        }
        
        return regionalParam;
    }
    /**
     * 调用老接口添加推荐专题 
     *
     * @param params
     * @param regionalParam
     * @param subjectWord
     * @param eventWord
     * @return
     * @throws MobileCloudSaveSpecialInfoException 
     */
    private String addSpecial(Map<String, Object> params,String regionalParam,
            MobileCloudSubjectInfo subjectWord,List<MobileCloudEventInfo> eventWord) throws MobileCloudSaveSpecialInfoException{
        Map<String, String> specialParamMap = new HashMap<>();
        String result = null;
        //设置默认值
        specialParamMap.put("KK_DATASOURCETYPE", "1");
        specialParamMap.put("KK_DINGXIANG_SOURCETYPE", KK_DINGXIANG_SOURCETYPE);
        specialParamMap.put("KK_NOYJ", "0");
        specialParamMap.put("KK_SENDTIME", "0");
        specialParamMap.put("KK_TUISONG", "0");
        specialParamMap.put("KK_TUISONG_HAVEREPEAT", "0");
        specialParamMap.put("KK_TUISONG_ORIENTATION", "2");
        specialParamMap.put("KK_TUISONG_STATE", "xx");
        specialParamMap.put("KK_TYPE", "01");
        specialParamMap.put("KS_INDEX", "0");
        specialParamMap.put("KS_IS_OPEN_PRECISE_REGION", "1");
        specialParamMap.put("KS_STATUS", "1");
        specialParamMap.put("KU_ID", "0");
        specialParamMap.put("KU_MATCHTITLE", "0");
        specialParamMap.put("KU_ONLGMUST", "0");
        specialParamMap.put("KU_WEIBOTIME", "0");
        specialParamMap.put("KU_WORDDISTANCE", "0");
        specialParamMap.put("KU_WORDDISTANCE_LENGTH", "1");
        specialParamMap.put("KU_WORDMUST", "0");
        specialParamMap.put("KU_WORDMUST_LENGTH", "1");
        specialParamMap.put("onlymatchsub", "0");
        specialParamMap.put("parentisshared", "0");
        specialParamMap.put("spaecialtype", "0");
        specialParamMap.put("submitclasstype", "1");
        specialParamMap.put("ISMODIFY", "0");
        String userid = String.valueOf(params.get("_KUID"));
        specialParamMap.put("userid", userid);
        String session = String.valueOf(params.get("accesstoken"));
        specialParamMap.put("session", session);
        //地域词
        if(StringUtils.isNotBlank(regionalParam)){
            specialParamMap.put("KK_MUST", regionalParam);
        }
        //品牌词
        String brandWord = String.valueOf(params.get("brandWord"));
        if(StringUtils.isNotBlank(brandWord)){
            brandWord = brandWord.replaceAll(",", " ");
            specialParamMap.put("KK_MUST", brandWord);
        }
        //主体词
        /*String subjectWords = subjectWord.getSubjectWord();
          specialParamMap.put("KK_SHOULD", subjectWords);*/
        
        //事件词
        for (MobileCloudEventInfo mobileCloudEventInfo : eventWord) {
            //专题名称
            String thematicName = mobileCloudEventInfo.getThematicName();
            //专题主体词
            String subjectWords = mobileCloudEventInfo.getSubjectWord();
            specialParamMap.put("KK_SHOULD", subjectWords);
            //专题事件词
            String eventWords = mobileCloudEventInfo.getEventWord();
            specialParamMap.put("KK_NAME", thematicName);
            specialParamMap.put("KK_EVENT", eventWords);
            
            //调用老接口添加推荐专题
            //本地测试url
            //String url = "http://172.16.253.108:8686/yqms_new2.1/KeyWordSet!saveAddKeyWordSet11.do";
            //线上url
            String url = PropertiesUtil.getValue("yidongyun.setKeyWord.url");
            //String url = "http://192.168.185.97:8080/KeyWordSet!saveAddKeyWordSet11.do";
            try {
                result = HttpUtil.post(url, specialParamMap);
            } catch (Exception e) {
                throw new MobileCloudSaveSpecialInfoException(MOBILECLOUD_SAVESPECIAL_ERR);
            }
        }
        return result;
    }
    
    /**
     * 获取最新的用户信息 
     *
     * @param params
     * @return
     */
    private List<Map<String,Object>> getNewUserInfo(Map<String,Object> params){
        String kuId = String.valueOf(params.get("_KUID"));
        List<Map<String, Object>> userInfo = userDao.getNewUserByKuId(kuId);
        List<UserBaseInfo> userBaseInfo = userBaseInfoDao.getUserBaseInfo(params);
        Map<String, Object> useBaseInfoMap = this.handleUserBaseInfo(userBaseInfo);
        String areaSet = userBaseInfoDao.getMobileCloudAreaSet(params);
        useBaseInfoMap.put("kuAreaset", areaSet);
        userInfo.add(useBaseInfoMap);
        return userInfo;
    }
    
    /**
     * userBaseInfo是个数表  需要将 ku_type——ku_value转换伟key——value形式  
     *
     * @param userBaseInfo
     * @return
     */
    private Map<String,Object> handleUserBaseInfo(List<UserBaseInfo> userBaseInfo){
        Map<String, Object> userBaseMap = new HashMap<>();
        if (userBaseInfo != null && userBaseInfo.size() > 0) {
            for (UserBaseInfo baseInfo : userBaseInfo) {
                userBaseMap.put(baseInfo.getKuType(), baseInfo.getKuValue());
            }
        }
        return userBaseMap;
    }

    /**
     * 删除用户第一次登陆信息 
     *
     * @param params
     * @return
     * @throws MobileCloudDelUserInfoException
     */
    private int delFirstLoginInfo(Map<String, Object> params) throws MobileCloudDelUserInfoException {
        int result=0;
        try {
            result = userBaseInfoDao.delFirstLoginInfo(params);
        } catch (Exception e) {
            throw new MobileCloudDelUserInfoException(MOBILECLOUD_DELUSER_ERR);
        }
        return result;
    }

    /**
     * 删除用户进入引导信息
     */
    @Override
    public int delFirstGuideInfo(Map<String, Object> params) throws MobileCloudDelUserInfoException {
        int result=0;
        try {
            result = userBaseInfoDao.delFirstGuideInfo(params);
        } catch (Exception e) {
            throw new MobileCloudDelUserInfoException(MOBILECLOUD_DELUSER_ERR);
        }
        return result;
    }

    /**
     * 获取单个课程及课件
     */
    @Override
    public List<MobileCloudCourse> getAllCourse(Map<String, Object> params) {
        List<MobileCloudCourse> allCourse = mobileCloudCourseDao.getAllCourse(params);
        return allCourse;
    }

    /**
     * 获取课程列表
     */
    @Override
    public List<MobileCloudCourse> getCourseList(Map<String, Object> params) {
        //获取当前时间 列表数据按时间给数据
        String nowStrDate = DateUtil.getNowStrDate();
        params.put("onlineTime", nowStrDate);
        List<MobileCloudCourse> courseList = mobileCloudCourseDao.getCourseList(params);
        return courseList;
    }

    @Override
    public List<MobileCloudCourse> getAllCoursewareInfo(Map<String, Object> params) {
        List<MobileCloudCourse> allCoursewareInfo = mobileCloudCourseDao.getAllCoursewareInfo(params);
        return allCoursewareInfo;
    }
    
}
