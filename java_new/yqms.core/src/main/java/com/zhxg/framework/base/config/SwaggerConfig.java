package com.zhxg.framework.base.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.google.common.base.Optional;
import com.zhxg.framework.base.constants.SysConstants;

import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * CopyRright (c)2012-2016: Azmiu
 * <p>
 * Project: yqms.core
 * <p>
 * Module ID: <模块类编号可以引用系统设计中的类编号>
 * <p>
 * Comments: springfox配置类
 * <p>
 * JDK version used: JDK1.8
 * <p>
 * NameSpace: com.zhxg.framework.base.config.SwaggerConfig.java
 * <p>
 * Author: azmiu
 * <p>
 * Create Date: 2017年3月31日
 * <p>
 * Modified By: <修改人中文名或拼音缩写>
 * <p>
 * Modified Date: <修改日期>
 * <p>
 * Why & What is modified: <修改原因描述>
 * <p>
 * Version: v1.0
 */
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = { "com.zhxg" })
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
        List<Parameter> list = new ArrayList<Parameter>();
        // 增加swagger-ui在调试参数时的header参数
        list.add(new Parameter(SysConstants._ACCESSTOKEN, "用户认证的accessToken", "", true, false, new ModelRef("String"),
                null, null, "header", null, false, null));
        list.add(new Parameter(SysConstants._USERID, "必选参数用户id，使用base64加密", "", true, false, new ModelRef("String"),
                Optional.fromNullable(null), null, "header", null, false, null));
        return new Docket(DocumentationType.SWAGGER_2).globalOperationParameters(list).apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("azmiu", "https://www.istarshine.com/", "peipei.zhang@istarshine.com");
        return new ApiInfo("yqzj-api", // 大标题 title
                "yqzj-api", // 小标题
                "0.0.1", // 版本
                "https://www.istarshine.com", // termsOfServiceUrl
                contact, // 作者
                "舆情专家", // 链接显示文字
                "https://www.istarshine.com/"// 网站链接
        );
    }
}
