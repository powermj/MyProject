package com.zhxg.yqzj.service.impl.v1;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zhxg.framework.base.curd.BaseDao;
import com.zhxg.framework.base.service.impl.BaseServiceImpl;
import com.zhxg.framework.base.utils.PropertiesUtil;
import com.zhxg.framework.base.utils.UploadFileUtil;
import com.zhxg.yqzj.dao.v1.AppImportDataDao;
import com.zhxg.yqzj.entities.v1.AppImportData;
import com.zhxg.yqzj.entities.v1.UsageStatistics;
import com.zhxg.yqzj.service.v1.AppImportDataService;

import sun.misc.BASE64Decoder;

@Service
public class AppImportDataServiceImpl extends BaseServiceImpl<AppImportData> implements AppImportDataService {

    private final String url = PropertiesUtil.getValue("SMB_URL");// 文件服务器地址

    @Autowired
    AppImportDataDao appImportDataDao;

    @Override
    protected BaseDao<AppImportData> getBaseDao() {
        return this.appImportDataDao;
    }

    /**
     * app导入数据业务实现
     */
    @Override
    public int importAppData(Map<String, Object> paramMap, MultipartFile music, String[] picArr) {
        // String userId = paramMap.get("ZHXGUSERID").toString();
        int result = 0;
        String userId = paramMap.get("appUserId").toString();
        // 将图片存储到服务器
        if (picArr != null) {
            String picName = importPicture(picArr, userId);
            String picAddress = picName.substring(0, picName.lastIndexOf(","));
            // 将图片地址存储
            paramMap.put("appPictures", picAddress);
        }
        // // 将音频存储到服务器
        if (music != null) {
            String voiceAddress = importVoice(music, userId);
            // 将音频地址存储到map中
            paramMap.put("appVoices", voiceAddress);
        }
        // 调用dao添加信息到数据库
        result = appImportDataDao.insertAppData(paramMap);
        return result;
    }

    // 将图片上传服务器
    protected String importPicture(String[] picArr, String userId) {

        String fileName = null;
        String returnName = "";
        OutputStream out = null;
        try {
            for (String picName : picArr) {
                if (StringUtils.isNotBlank(picName)) {
                    // Base64解码
                    byte[] b = new BASE64Decoder().decodeBuffer(picName);
                    for (int i = 0; i < b.length; ++i) {
                        if (b[i] < 0) {// 调整异常数据
                            b[i] += 256;
                        }
                    }

                    // 生成图片名称
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                    String steDate = dateFormat.format(date);
                    int random = (int) (Math.random() * 9000) + 1000;
                    fileName = steDate + "_" + random + ".png";
                    returnName += fileName + ",";
                    // 文件位置
                    String absolutePath = userId + "/" + "pic" + "/" + fileName;

                    // 获取输出流
                    out = UploadFileUtil.getInstance().getOut(absolutePath);
                    out.write(b);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnName;
    }

    protected String importVoice(MultipartFile music, String userId) {
        String originalFilename = music.getOriginalFilename();// 获取上传文件源名称
        String fileName = "";
        OutputStream out = null;
        byte[] b = null;
        try {
            b = music.getBytes();
            // 为文件命名
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String steDate = dateFormat.format(date);
            int random = (int) (Math.random() * 9000) + 1000;
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = steDate + "_" + random + ".mp3";
            // 文件位置
            String absolutePath = userId + "/" + "voice" + "/" + fileName;
            // 获取输出流
            out = UploadFileUtil.getInstance().getOut(absolutePath);
            out.write(b);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }

    @Override
    public int saveAppUsageStatistics(List<UsageStatistics> usageStatisticsList) {
        for(UsageStatistics usageStatistics:usageStatisticsList){
            appImportDataDao.saveAppUsageStatistics(usageStatistics);
        }
        return 1;
    }
}
