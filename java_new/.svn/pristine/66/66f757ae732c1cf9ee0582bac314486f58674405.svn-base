package com.zhxg.framework.spring.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;

import com.zhxg.framework.base.exception.ParameterException;
import com.zhxg.framework.base.utils.ValidateFiled;
import com.zhxg.framework.base.utils.ValidateGroup;

/**
 * 
 * @author GaoYu 参数校验 使用方法 在 Controller 里面的方法上加上 @ValidateGroup(fileds =
 *         { @ValidateFiled(index = 1,minLen=2,maxLen=3) })
 */
public class ValidateAspectParam {
	private boolean flag = false;
	private String msg = "";

	public void validateAround(JoinPoint joinPoint) throws ParameterException {
		ValidateGroup an = null;
		Object[] args = null;
		Method method = null;
		Object target = null;
		String methodName = null;
		methodName = joinPoint.getSignature().getName();
		target = joinPoint.getTarget();
		method = getMethodByClassAndName(target.getClass(), methodName); // 得到拦截的方法
		args = joinPoint.getArgs(); // 方法的参数
		an = (ValidateGroup) getAnnotationByMethod(method, ValidateGroup.class);
		try {
			if (null != an) {
				validateFiled(an.fileds(), args);
			} else {
				flag = true;
			}
		} catch (Exception e) {
			throw new ParameterException(e.getMessage());
		}
		if (!flag) {
			throw new ParameterException(msg);
		}
	}

	/**
	 * 验证参数是否合法
	 */
	public void validateFiled(ValidateFiled[] valiedatefiles, Object[] args) throws SecurityException,
			IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for (ValidateFiled validateFiled : valiedatefiles) {
			Object arg = null;
			if ("".equals(validateFiled.filedName())) {
				arg = args[validateFiled.index()];
			} else {
				arg = getFieldByObjectAndFileName(args[validateFiled.index()], validateFiled.filedName());
			}
			if (validateFiled.notNull()) { // 判断参数是否为空
				if (arg == null) {
					flag = false;
					msg = validateFiled.msg();
					return;
				} else if (arg instanceof String) {
					if ("".equals(arg.toString().trim())) {
						flag = false;
						msg = validateFiled.msg();
						return;
					}
				}
			} else { // 如果该参数能够为空，并且当参数为空时，就不用判断后面的了 ，直接返回true
				if (arg == null)
					flag = true;
			}

			if (validateFiled.maxLen() > 0) { // 判断字符串最大长度
				if (((String) arg).length() > validateFiled.maxLen()) {
					flag = false;
					msg = validateFiled.msg();
					return;
				}
			}

			if (validateFiled.minLen() > 0) { // 判断字符串最小长度
				if (((String) arg).length() < validateFiled.minLen()) {
					flag = false;
					msg = validateFiled.msg();
					return;
				}
			}

			if (validateFiled.maxVal() != -1) { // 判断数值最大值
				if ((Integer) arg > validateFiled.maxVal())
					flag = false;
				msg = validateFiled.msg();
				return;
			}

			if (validateFiled.minVal() != -1) { // 判断数值最小值
				if ((Integer) arg < validateFiled.minVal()) {
					flag = false;
					msg = validateFiled.msg();
					return;
				}
			}

			if (!"".equals(validateFiled.regStr())) { // 判断正则
				if (arg instanceof String) {
					if (!((String) arg).matches(validateFiled.regStr())) {
						flag = false;
						msg = validateFiled.msg();
						return;
					}

				} else {
					flag = false;
					msg = validateFiled.msg();
					return;
				}
			}
		}
		flag = true;
	}

	/**
	 * 根据对象和属性名得到 属性
	 */
	public Object getFieldByObjectAndFileName(Object targetObj, String fileName) throws SecurityException,
			NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String tmp[] = fileName.split("\\.");
		Object arg = targetObj;
		for (int i = 0; i < tmp.length; i++) {
			Method methdo = arg.getClass().getMethod(getGetterNameByFiledName(tmp[i]));
			arg = methdo.invoke(arg);
		}
		return arg;
	}

	/**
	 * 根据属性名 得到该属性的getter方法名
	 */
	public String getGetterNameByFiledName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * 根据目标方法和注解类型 得到该目标方法的指定注解
	 */
	public Annotation getAnnotationByMethod(Method method, Class<?> annoClass) {
		Annotation all[] = method.getAnnotations();
		for (Annotation annotation : all) {
			if (annotation.annotationType() == annoClass) {
				return annotation;
			}
		}
		return null;
	}

	/**
	 * 根据类和方法名得到方法
	 */
	public Method getMethodByClassAndName(Class<?> c, String methodName) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}
}
