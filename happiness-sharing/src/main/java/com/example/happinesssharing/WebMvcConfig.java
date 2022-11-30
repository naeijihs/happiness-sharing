package com.example.happinesssharing;

import com.example.happinesssharing.interceptor.AdminInterceptor;
import com.example.happinesssharing.interceptor.LoginInterceptor;
import com.example.happinesssharing.interceptor.SharerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private SharerInterceptor sharerInterceptor;
    @Autowired
    private LoginInterceptor loginInterceptor;
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/sharer/**","/admin/**","/modifyPassword");
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");
        registry.addInterceptor(sharerInterceptor)
                .addPathPatterns("/sharer/**");
    }
    //浏览器无法访问本地文件 因此配置虚拟路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/video/**").addResourceLocations("file:///Users/shijiean/Desktop/project/projects/happiness-sharing/happiness-sharing/static/");
    }
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config=new CorsConfiguration();
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.addAllowedOriginPattern("*");
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**",config);
        return new CorsFilter(configSource);
    }
}
