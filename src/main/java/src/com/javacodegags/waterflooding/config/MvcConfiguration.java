package com.javacodegags.waterflooding.config;

import com.javacodegags.waterflooding.model.CaptionImplemented;
import com.javacodegags.waterflooding.model.CaptionInterface;
import com.javacodegags.waterflooding.model.CriteriaImplemented;
import com.javacodegags.waterflooding.model.CriteriaInterface;
import com.javacodegags.waterflooding.model.FloodingImplemented;
import com.javacodegags.waterflooding.model.FloodingInterface;
import com.javacodegags.waterflooding.model.ParameterInterface;
import com.javacodegags.waterflooding.model.ParametersImplemented;
import com.javacodegags.waterflooding.model.ResultImplemented;
import com.javacodegags.waterflooding.model.ResultInterface;
import com.javacodegags.waterflooding.model.ThermImplemented;
import com.javacodegags.waterflooding.model.ThermInterface;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "com.javacodegags.watefllooding.controller")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/geology?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    public CaptionInterface getCaptionDAO() {
        return new CaptionImplemented(getDataSource());
    }
    
    @Bean
    public CriteriaInterface getCriteriaDAO() {
        return new CriteriaImplemented(getDataSource());
    }
    
    @Bean
    public ParameterInterface getParameterDAO() {
        return new ParametersImplemented(getDataSource());
    }
    
    @Bean
    public FloodingInterface getFloodingDAO() {
        return new FloodingImplemented(getDataSource());
    }
    
    @Bean
    public ResultInterface getResultDAO() {
        return new ResultImplemented(getDataSource());
    }
    
    @Bean
    public ThermInterface getThermDAO() {
        return new ThermImplemented(getDataSource());
    }

}
