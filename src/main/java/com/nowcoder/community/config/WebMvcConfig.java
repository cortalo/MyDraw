package com.nowcoder.community.config;

import com.nowcoder.community.filter.EmbedHeaderFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(false); // Must be false when allowedOrigins is *
    }

    @Bean
    public FilterRegistrationBean<EmbedHeaderFilter> embedHeaderFilter() {
        FilterRegistrationBean<EmbedHeaderFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new EmbedHeaderFilter());
        registrationBean.addUrlPatterns("/embed/*", "/draw/embed/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
