package com.config;

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
 * @ClassNmae aa
 * @Discription
 * @Author wh
 * @Date 2020/10/2  20:41
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket creatApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())   // 指定构建api文档的详细信息的方法，下面会有实现：apiInfo()
                .select()
                // 指定要生成api接口的包路径，这里把controller作为包路径，生成controller中的所有接口
                .apis(RequestHandlerSelectors.basePackage("com.test.redis.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot集成Swagger2接口测试")
                .description("生成的接口如下")
                .version("1.0")
                .contact(new Contact("浩哥","http://www.baodu.com","haohao@163.com"))
                .build();
    }
}
