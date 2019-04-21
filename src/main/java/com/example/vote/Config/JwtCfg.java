package com.example.vote.Config;

import com.example.vote.Filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这个类中声明了一个@Bean ，用于生成一个过滤器类，对 特定 链接下的所有资源访问进行JWT的验证
 * @author white matter
 */
@Configuration
public class JwtCfg {

    @Bean
    public FilterRegistrationBean jwtFilter(){
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        //添加拦截对象
        registrationBean.addUrlPatterns("/changPass");
        registrationBean.addUrlPatterns("");
        return registrationBean;
    }
}
