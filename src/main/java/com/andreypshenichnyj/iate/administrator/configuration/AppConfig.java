package com.andreypshenichnyj.iate.administrator.configuration;


import com.andreypshenichnyj.iate.administrator.converter.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@PropertySource("classpath:application.properties")
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.andreypshenichnyj.iate.administrator")
public class AppConfig implements WebMvcConfigurer{

    @Autowired
    private GroupIdToGroupConverter groupIdToGroupConverter;

    @Autowired
    private DepartmentIdToDepartmentConverter departmentIdToDepartmentConverter;

    @Autowired
    private WorkRoomIdToWorkRoomConverter workRoomIdToWorkRoomConverter;

    @Autowired
    private StudentIdToStudentConverter studentIdToStudentConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(groupIdToGroupConverter);
        registry.addConverter(departmentIdToDepartmentConverter);
        registry.addConverter(workRoomIdToWorkRoomConverter);
        registry.addConverter(studentIdToStudentConverter);
    }

    @Bean
    public FilterRegistrationBean hiddenHttpMethodFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean<>(new HiddenHttpMethodFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/css/**").addResourceLocations("/css");
        registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
    }
}
