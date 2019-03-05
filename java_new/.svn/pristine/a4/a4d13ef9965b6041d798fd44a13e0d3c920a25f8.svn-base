package com.zhxg.framework.base.exception;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import com.zhxg.framework.base.http.ApiResponseBody;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: 自定义异常处理
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.exception.ExceptionHandler.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月17日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
public class ExceptionHandler extends DefaultHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        try {
            // 使用spring默认异常处理器，处理一些公共的异常，并重写默认异常处理器中的response方法，返回客户端明确的异常信息
            super.doResolveException(request, response, handler, ex);
            if (ex instanceof BusinessException) {
                this.businessExceptionHandler(request, response, handler, (BusinessException) ex);
            } else if (ex instanceof SysException) {
                this.systemExceptionHandler(request, response, handler, (SysException) ex);
            }
        } catch (Exception handlerException) {
            if (this.logger.isWarnEnabled()) {
                this.logger.warn("Handling of [" + ex.getClass().getName() + "] resulted in Exception",
                        handlerException);
            }
        }
        return new ModelAndView();
    }

    /**
     * Handle the case where no request handler method was found.
     * <p>
     * The default implementation logs a warning, sends an HTTP 404 error, and returns
     * an empty {@code ModelAndView}. Alternatively, a fallback view could be chosen,
     * or the NoSuchRequestHandlingMethodException could be rethrown as-is.
     * 
     * @param ex the NoSuchRequestHandlingMethodException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler, or {@code null} if none chosen
     *            at the time of the exception (for example, if multipart resolution failed)
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        pageNotFoundLogger.warn(ex.getMessage());
        // response.sendError(HttpServletResponse.SC_NOT_FOUND);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_NOT_FOUND);
        return new ModelAndView();
    }

    /**
     * Handle the case where no request handler method was found for the particular HTTP request method.
     * <p>
     * The default implementation logs a warning, sends an HTTP 405 error, sets the "Allow" header,
     * and returns an empty {@code ModelAndView}. Alternatively, a fallback view could be chosen,
     * or the HttpRequestMethodNotSupportedException could be rethrown as-is.
     * 
     * @param ex the HttpRequestMethodNotSupportedException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler, or {@code null} if none chosen
     *            at the time of the exception (for example, if multipart resolution failed)
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        pageNotFoundLogger.warn(ex.getMessage());
        String[] supportedMethods = ex.getSupportedMethods();
        if (supportedMethods != null) {
            response.setHeader("Allow", StringUtils.arrayToDelimitedString(supportedMethods, ", "));
        }
        // response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, ex.getMessage());
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return new ModelAndView();
    }

    /**
     * Handle the case where no {@linkplain org.springframework.http.converter.HttpMessageConverter message converters}
     * were found for the PUT or POSTed content.
     * <p>
     * The default implementation sends an HTTP 415 error, sets the "Accept" header,
     * and returns an empty {@code ModelAndView}. Alternatively, a fallback view could
     * be chosen, or the HttpMediaTypeNotSupportedException could be rethrown as-is.
     * 
     * @param ex the HttpMediaTypeNotSupportedException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        List<MediaType> mediaTypes = ex.getSupportedMediaTypes();
        if (!CollectionUtils.isEmpty(mediaTypes)) {
            response.setHeader("Accept", MediaType.toString(mediaTypes));
        }
        return new ModelAndView();
    }

    /**
     * Handle the case where no {@linkplain org.springframework.http.converter.HttpMessageConverter message converters}
     * were found that were acceptable for the client (expressed via the {@code Accept} header.
     * <p>
     * The default implementation sends an HTTP 406 error and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the HttpMediaTypeNotAcceptableException
     * could be rethrown as-is.
     * 
     * @param ex the HttpMediaTypeNotAcceptableException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_NOT_ACCEPTABLE);
        return new ModelAndView();
    }

    /**
     * Handle the case when a declared path variable does not match any extracted URI variable.
     * <p>
     * The default implementation sends an HTTP 500 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the MissingPathVariableException
     * could be rethrown as-is.
     * 
     * @param ex the MissingPathVariableException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     * @since 4.2
     */
    @Override
    protected ModelAndView handleMissingPathVariable(MissingPathVariableException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ex.getMessage());
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }

    /**
     * Handle the case when a required parameter is missing.
     * <p>
     * The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the MissingServletRequestParameterException
     * could be rethrown as-is.
     * 
     * @param ex the MissingServletRequestParameterException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case when an unrecoverable binding exception occurs - e.g. required header, required cookie.
     * <p>
     * The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the exception could be rethrown as-is.
     * 
     * @param ex the exception to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleServletRequestBindingException(ServletRequestBindingException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case when a {@link org.springframework.web.bind.WebDataBinder} conversion cannot occur.
     * <p>
     * The default implementation sends an HTTP 500 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the TypeMismatchException could be rethrown as-is.
     * 
     * @param ex the ConversionNotSupportedException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleConversionNotSupported(ConversionNotSupportedException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn("Failed to convert request element: " + ex);
        }
        // this.sendServerError(ex, request, response);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }

    /**
     * Handle the case when a {@link org.springframework.web.bind.WebDataBinder} conversion error occurs.
     * <p>
     * The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the TypeMismatchException could be rethrown as-is.
     * 
     * @param ex the TypeMismatchException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleTypeMismatch(TypeMismatchException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn("Failed to bind request element: " + ex);
        }
        // response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case where a {@linkplain org.springframework.http.converter.HttpMessageConverter message converter}
     * cannot read from a HTTP request.
     * <p>
     * The default implementation sends an HTTP 400 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the HttpMediaTypeNotSupportedException could be
     * rethrown as-is.
     * 
     * @param ex the HttpMessageNotReadableException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (this.logger.isWarnEnabled()) {
            this.logger.warn("Failed to read HTTP message: " + ex);
        }
        // response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case where a {@linkplain org.springframework.http.converter.HttpMessageConverter message converter}
     * cannot write to a HTTP request.
     * <p>
     * The default implementation sends an HTTP 500 error, and returns an empty {@code ModelAndView}.
     * Alternatively, a fallback view could be chosen, or the HttpMediaTypeNotSupportedException could be
     * rethrown as-is.
     * 
     * @param ex the HttpMessageNotWritableException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (this.logger.isWarnEnabled()) {
            this.logger.warn("Failed to write HTTP message: " + ex);
        }
        // this.sendServerError(ex, request, response);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return new ModelAndView();
    }

    /**
     * Handle the case where an argument annotated with {@code @Valid} such as
     * an {@link RequestBody} or {@link RequestPart} argument fails validation.
     * An HTTP 400 error is sent back to the client.
     * 
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case where an {@linkplain RequestPart @RequestPart}, a {@link MultipartFile},
     * or a {@code javax.servlet.http.Part} argument is required but is missing.
     * An HTTP 400 error is sent back to the client.
     * 
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleMissingServletRequestPartException(MissingServletRequestPartException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        // response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case where an {@linkplain ModelAttribute @ModelAttribute} method
     * argument has binding or validation errors and is not followed by another
     * method argument of type {@link BindingResult}.
     * By default, an HTTP 400 error is sent back to the client.
     * 
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     */
    @Override
    protected ModelAndView handleBindException(BindException ex, HttpServletRequest request,
            HttpServletResponse response, Object handler) throws IOException {
        // response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_BAD_REQUEST);
        return new ModelAndView();
    }

    /**
     * Handle the case where no handler was found during the dispatch.
     * <p>
     * The default implementation sends an HTTP 404 error and returns an empty
     * {@code ModelAndView}. Alternatively, a fallback view could be chosen,
     * or the NoHandlerFoundException could be rethrown as-is.
     * 
     * @param ex the NoHandlerFoundException to be handled
     * @param request current HTTP request
     * @param response current HTTP response
     * @param handler the executed handler, or {@code null} if none chosen
     *            at the time of the exception (for example, if multipart resolution failed)
     * @return an empty ModelAndView indicating the exception was handled
     * @throws IOException potentially thrown from response.sendError()
     * @since 4.0
     */
    @Override
    protected ModelAndView handleNoHandlerFoundException(NoHandlerFoundException ex,
            HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // response.sendError(HttpServletResponse.SC_NOT_FOUND);
        // SysException sys = new SysException();
        this.frameworkExceptionHandler(ex, request, response, handler, HttpServletResponse.SC_NOT_FOUND);
        return new ModelAndView();
    }


    /**
     * 框架异常处理
     *
     * @param request
     * @param response
     * @param handler
     * @param httpStatus
     * @throws IOException
     */
    private void frameworkExceptionHandler(Exception ex, HttpServletRequest request,
            HttpServletResponse response, Object handler, int httpStatus) throws IOException {
        response.setStatus(httpStatus);
        this.sendResponse(ex, request, response, handler,
                SysException.TYPE_CODE + SysException.MODULE_CODE + httpStatus);
    }


    /**
     * 业务异常处理
     *
     * @param request
     * @param response
     * @param handler
     * @param be
     * @throws Exception
     */
    private void businessExceptionHandler(HttpServletRequest request, HttpServletResponse response, Object handler,
            BusinessException be) throws Exception {
        response.setStatus(HttpServletResponse.SC_OK);
        this.sendResponse(be, request, response, handler, be.getErrorCode());
    }


    /**
     * 系统异常处理
     *
     * @param request
     * @param response
     * @param handler
     * @param se
     * @throws Exception
     */
    private void systemExceptionHandler(HttpServletRequest request, HttpServletResponse response, Object handler,
            SysException se) throws Exception {
        if (se instanceof AuthorizationException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        this.sendResponse(se, request, response, handler, se.getErrorCode());
    }

    /**
     * 返回处理后的异常信息
     *
     * @param ex
     * @param request
     * @param response
     * @param handler
     * @param staus
     * @throws IOException
     */
    private void sendResponse(Exception ex, HttpServletRequest request,
            HttpServletResponse response, Object handler, String staus) throws IOException {
        // 重写jsonlib转换JavaBean规则，过滤掉空的JavaBean属性
        JsonConfig jsonConfig = new JsonConfig();
        PropertyFilter filter = new PropertyFilter() {

            @Override
            public boolean apply(Object object, String fieldName, Object fieldValue) {
                if (object instanceof ApiResponseBody) {
                    if (fieldName.equals("result")) {
                        return true;
                    }
                }
                if (fieldValue == null) {
                    return true;
                }
                return false;
            }

        };
        jsonConfig.setJsonPropertyFilter(filter);

        ApiResponseBody responseBody = new ApiResponseBody();
        responseBody.setMsg(ex.getMessage());
        responseBody.setStatus(staus);
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().print(JSONObject.fromObject(responseBody, jsonConfig));
    }
}
