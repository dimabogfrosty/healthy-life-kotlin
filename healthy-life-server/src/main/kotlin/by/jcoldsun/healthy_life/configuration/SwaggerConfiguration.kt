package by.jcoldsun.healthy_life.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .apiInfo(metaInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("by.jcoldsun.healthy_life"))
            .paths(PathSelectors.any())
            .build()

    private fun metaInfo(): ApiInfo = ApiInfoBuilder()
            .description("Server side REST API for healthy life")
            .title("API for healthy life")
            .version("0.1")
            .license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
            .build()
}