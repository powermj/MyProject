package com.zhxg.framework.base.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;


@SuppressWarnings("restriction")
public class ExportPicUtil {

    private static Logger logger = LoggerFactory.getLogger(ExportPicUtil.class);

    public String saveImgToLocal(String img, String imgName, HttpServletRequest request) {
        String absolutePath = "";
        OutputStream out = null;
        try {
            if (StringUtils.isNotBlank(img)) {
                String[] url = img.split(",");
                String u = url[1];
                // Base64解码
                byte[] b = Base64.getDecoder().decode(u);
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
                String fileName = imgName + "_" + steDate + "_" + random;

                // 生成图片
                // File file = new File("d:\\temp\\echarts_img");
                // file.mkdirs();
                // String absolutePath = "d:\\temp\\echarts_img\\" + fileName +
                // ".png";

                @SuppressWarnings("deprecation")
                String projectPath = request.getRealPath("/");
                projectPath = projectPath.substring(0, projectPath.indexOf("webapps") + 8) + "export";

                File file = new File(projectPath);
                if (!file.exists()) {
                    file.mkdir();
                }

                absolutePath = projectPath + "/" + fileName + ".png";

                out = new FileOutputStream(new File(absolutePath));
                out.write(b);
                out.flush();
            }
        } catch (Exception e) {
            logger.error("saveImgToLocal {}", e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error("saveImgToLocal {}", e.getMessage());
                }
            }
        }

        return absolutePath;
    }

    public static Map<String, String> getParamsMapByKeys(Map<String, String> map, HttpServletRequest request,
            String... keys) {
        return getParamsMapByKeysAndStartKey(map, null, request, keys);
    }

    public static Map<String, String> getParamsMapByKeysAndStartKey(Map<String, String> map, String IMAGE_START_KEY,
            HttpServletRequest request,
            String... keys) {
        ExportPicUtil exportPicUtil = new ExportPicUtil();
        Map<String, String> map1 = new HashMap<>();
        for (String paramKey : keys) {
            setParams(map, paramKey, exportPicUtil, map1, request);
        }
        return map1;
    }

    public static boolean isNotEmpty(String param) {
        return param != null && !"".equals(param);
    }

    public static void setParams(Map<String, String> map, String paramKey, ExportPicUtil exportPicUtil,
            Map<String, String> map1, HttpServletRequest request) {
        if (isNotEmpty(paramKey)) {
            if (map1 == null) {
                map1 = new HashMap<>();
            }
            String imgData = map.get(paramKey);
            if (isNotEmpty(imgData)) {
                String imageUrl = exportPicUtil.saveImgToLocal(imgData, paramKey, request);
                map1.put(paramKey, imageUrl);
            }
        }
    }

    // 图片带名称标题 需要对标题文字设置格式
    public static void addDocument(String contextString, Paragraph context, Font contextTitleFont,
            Document document, Image png, String key, float contextWidth, Map<String, String> map,
            float percent) throws DocumentException, MalformedURLException, IOException {
        if (map != null && map.get(key) != null) {
            context = new Paragraph(contextString);
            context.setFont(contextTitleFont);
            context.setAlignment(Element.ALIGN_LEFT);
            document.add(context);
            png = Image.getInstance(map.get(key));
            ITextUtil.setScaleAbsolute(png, contextWidth, percent);
            png.setAlignment(Element.ALIGN_CENTER);
            document.add(png);
        }
    }

    public static void addDocument(String contextString, Paragraph context, Font contextTitleFont,
            Document document, Image png, List<String> keys, float contextWidth, Map<String, String> map,
            float percent) throws DocumentException, MalformedURLException, IOException {
        if (keys != null && !keys.isEmpty()) {
            for (String key : keys) {
                addDocument(contextString, context, contextTitleFont, document, png, key, contextWidth, map,
                        percent);
            }
        }
    }

    // 单独添加一张图片 没有标题文字的
    public static void addDocument(Document document, Image png, String key, float contextWidth,
            Map<String, String> map,
            float percent) throws DocumentException, MalformedURLException, IOException {
        if (map != null && map.get(key) != null) {
            png = Image.getInstance(map.get(key));
            ITextUtil.setScaleAbsolute(png, contextWidth, percent);
            png.setAlignment(Element.ALIGN_CENTER);
            document.add(png);
        }
    }

    // 根据KEY获取图片
    public static Image getImg(Image png, String key, Map<String, String> map,
            float percent) throws BadElementException, MalformedURLException, IOException {
        if (map != null && map.get(key) != null) {
            png = Image.getInstance(map.get(key));
            png.setAlignment(Element.ALIGN_CENTER);
        }
        return png;
    }
}
