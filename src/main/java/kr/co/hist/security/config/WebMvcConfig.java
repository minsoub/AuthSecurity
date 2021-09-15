package kr.co.hist.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 정적 자원을 설정하는 클래스
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/static/",
            "classpath:/public/",
            "classpath:/",
            "classpath:/resource/",
            "classpath:/META-INF/resources/",
            "classpath:/META_INF/resources/webjars/"
    };

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //  '/'에 해당하는 URL mapping을 /index로 forward한다.
        registry.addViewController("/").setViewName("forward:/index");

        // 우선 순위 설정
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

}
