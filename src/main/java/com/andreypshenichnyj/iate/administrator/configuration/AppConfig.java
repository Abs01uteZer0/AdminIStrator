package com.andreypshenichnyj.iate.administrator.configuration;


import com.andreypshenichnyj.iate.administrator.converter.DepartmentIdToDepartmentConverter;
import com.andreypshenichnyj.iate.administrator.converter.GroupIdToGroupConverter;
import com.andreypshenichnyj.iate.administrator.converter.GroupNameToGroupOrNullConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@PropertySource("classpath:application.properties")
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.andreypshenichnyj.iate.administrator")
public class AppConfig implements WebMvcConfigurer{

    @Autowired
    GroupIdToGroupConverter groupIdToGroupConverter;

    @Autowired
    DepartmentIdToDepartmentConverter departmentIdToDepartmentConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(groupIdToGroupConverter);
        registry.addConverter(departmentIdToDepartmentConverter);
    }

    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}
