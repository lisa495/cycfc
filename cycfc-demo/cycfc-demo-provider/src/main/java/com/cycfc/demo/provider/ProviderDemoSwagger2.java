package com.cycfc.demo.provider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 描述：TODO<br>
 *
 * @author Yanzheng 严正<br>
 * 时间：<br>
 * 2018/9/6 下午2:23<br>
 * 版权：<br>
 * Copyright 2018 <a href="https://github.com/micyo202" target="_blank">https://github.com/micyo202</a>. All rights reserved.
 */
@Configuration
@EnableSwagger2
public class ProviderDemoSwagger2 {

    /**
     * 访问地址：http://localhost:8701/swagger-ui.html
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //设置包路径
                .apis(RequestHandlerSelectors.basePackage("com.cycfc.demo.provider"))
                .paths(PathSelectors.any())
                //.paths(PathSelectors.regex("/user.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("标题：cycfc-demo-provider 使用 Swagger2 构建 API 接口文档")
                //描述
                .description("描述：用于 cycfc-demo-provider 接口查看")
                .termsOfServiceUrl("https://github.com/micyo202")
                //创建人
                .contact(new Contact("Yanzheng", "https://github.com/micyo202", "micyo202@163.com"))
                .version("版本号：1.0")
                .build();
    }
}
