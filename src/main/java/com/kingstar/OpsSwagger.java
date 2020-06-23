package com.kingstar;

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
 * @Description: swagger配置
 * @Author: myl
 * @Date: 2020/6/12 13:15
 */
@Configuration
@EnableSwagger2
public class OpsSwagger {
    @Bean
    public Docket studentInfo() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("学生信息")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kingstar.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title("接口").description("相关接口").termsOfServiceUrl("").contact(new Contact("", "", "")).version("1.0").build();
    }

}
