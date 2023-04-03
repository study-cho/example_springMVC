package com.example.config;

import com.example.beans.UserBean;
import com.example.interceptor.CheckLoginInterceptor;
import com.example.interceptor.TopMenuInterceptor;
import com.example.mapper.BoardMapper;
import com.example.mapper.TopMenuMapper;
import com.example.mapper.UserMapper;
import com.example.service.TopMenuService;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
@EnableWebMvc
@ComponentScan("com.example.controller")
@ComponentScan("com.example.dao")
@ComponentScan("com.example.service")
@PropertySource("/WEB-INF/properties/db.properties")
public class ServletAppConfig implements WebMvcConfigurer {

    @Value("${db.classname}")
    private String db_classname;

    @Value("${db.url}")
    private String db_url;

    @Value("${db.username}")
    private String db_username;

    @Value("${db.password}")
    private String db_password;

    @Autowired
    private TopMenuService topMenuService;

    @Resource(name = "loginUserBean")
    private UserBean loginUserBean;

    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/resources/");
    }

    //데이터베이스 접속 정보를 관리하는 Bean
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(db_classname);
        dataSource.setUrl(db_url);
        dataSource.setUsername(db_username);
        dataSource.setPassword(db_password);
        return dataSource;
    }

    //쿼리문과 접속 정보 관리하는 객체
    @Bean
    public SqlSessionFactory factory(BasicDataSource source) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(source);
        return factoryBean.getObject();
    }

    //쿼리문 실행을 위한 객체 (Mapper 관리)
    @Bean
    public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<BoardMapper> factoryBean = new MapperFactoryBean<>(BoardMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<TopMenuMapper> getTopMenuMapper(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<TopMenuMapper> factoryBean = new MapperFactoryBean<>(TopMenuMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

    @Bean
    public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) throws Exception {
        MapperFactoryBean<UserMapper> factoryBean = new MapperFactoryBean<>(UserMapper.class);
        factoryBean.setSqlSessionFactory(factory);
        return factoryBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean);
        registry.addInterceptor(topMenuInterceptor).addPathPatterns("/**");

        CheckLoginInterceptor checkLoginInterceptor = new CheckLoginInterceptor(loginUserBean);
        registry.addInterceptor(checkLoginInterceptor)
                .addPathPatterns("/user/modify", "/user/logout", "/board/*")
                .excludePathPatterns("/board/main");
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource res = new ReloadableResourceBundleMessageSource();
        res.setBasenames("/WEB-INF/properties/error_message");
        return res;
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
