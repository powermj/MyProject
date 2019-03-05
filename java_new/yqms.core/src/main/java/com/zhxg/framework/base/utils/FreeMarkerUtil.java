package com.zhxg.framework.base.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.zhxg.framework.base.exception.SysException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * FreeMarkerUtil 模板工具
 * 
 * @author Administrator
 *
 */
public class FreeMarkerUtil {
	/**
	 * 
	 * @param fltFile
	 *            flt文件名
	 * @param templatePath
	 *            flt文件路径 src/template
	 * @param datas
	 *            数据集合
	 * @return
	 * @throws SysException
	 */
	public static void createFile(String templateName, Map<String, Object> datas, String outPath) throws SysException {
		OutputStream os = null;
		OutputStreamWriter osw = null;
		BufferedWriter out = null;
		try {
			// 1.0 创建配置对象 //创建Configuration实例，指定FreeMarker的版本
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_28); // 指定模板所在的目录
			cfg.setNumberFormat("#");
			String path = FreeMarkerUtil.class.getResource("").getPath();
			File templatesDir = new File(path);
			File file = templatesDir.getParentFile();
			path = file.getPath() + "/template/";
			cfg.setDirectoryForTemplateLoading(new File(URLDecoder.decode(path, "utf-8")));
			// 设置默认字符集
			cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
			// 设置出现异常处理的方式，开发阶段可以设置为HTML_DEBUG_HANDLER
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);
			// 3.0 获取模板
			Template template = cfg.getTemplate(templateName);
			// 4.0 给模板绑定数据模型
			os = new FileOutputStream(outPath);
			osw = new OutputStreamWriter(os, "UTF-8");
			out = new BufferedWriter(osw);
			template.process(datas, out);
		} catch (Exception e) {
			throw new SysException(e);
		}finally{
			try {
				if(null!=out){
					out.close();
				}
				if(null!=osw){
					osw.close();
				}
				if(null!=os){
					os.close();
				}
			} catch (IOException e) {
				throw new SysException(e);
			}
		}

	}
	public static void createFile(ServletContext servletContext ,String templateName, Map<String, Object> datas, OutputStream os) throws SysException {
		OutputStreamWriter osw = null;
		BufferedWriter out = null;
		try {
			// 1.0 创建配置对象 //创建Configuration实例，指定FreeMarker的版本
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_28); // 指定模板所在的目录
//			String path = FreeMarkerUtil.class.getResource("").getPath();
//			File templatesDir = new File(path);
//			File file = templatesDir.getParentFile();
//			path = file.getPath() + "/template/";
//			cfg.setDirectoryForTemplateLoading(new File(URLDecoder.decode(path, "utf-8")));
			cfg.setNumberFormat("#");
			cfg.setServletContextForTemplateLoading(servletContext,"template");
			// 设置默认字符集
			cfg.setDefaultEncoding(StandardCharsets.UTF_8.name());
			// 设置出现异常处理的方式，开发阶段可以设置为HTML_DEBUG_HANDLER
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			cfg.setLogTemplateExceptions(false);
			cfg.setWrapUncheckedExceptions(true);
			// 3.0 获取模板
			Template template = cfg.getTemplate(templateName);
			// 4.0 给模板绑定数据模型
			osw = new OutputStreamWriter(os, "UTF-8");
			out = new BufferedWriter(osw);
			template.process(datas, out);
		} catch (Exception e) {
			throw new SysException(e);
		}finally{
			try {
				if(null!=out){
					out.close();
				}
				if(null!=osw){
					osw.close();
				}
			} catch (IOException e) {
				throw new SysException(e);
			}
		}

	}

	public static void main(String[] args) {
		System.out.println(FreeMarkerUtil.class.getResource("templateyyytemplate.ftl"));
	}
}
