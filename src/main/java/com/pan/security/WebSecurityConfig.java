package com.pan.security;

import com.pan.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Security配置类
 * <p>
 * Created by FantasticPan on 2018/6/3.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 生成定义一个PasswordEncoder的Bean，配置加密方式，这里生成BCryptPasswordEncoder的Bean对象
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 生成一个自定义的UserDetailsServiceImpl的Bean，交给Spring IOC容器
     */
    @Bean
    UserDetailsService detailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailsService()) // 用户认证
                .passwordEncoder(passwordEncoder()); // 使用加密验证
    }

    /**
     * 不重写configure()的写法
     * @param web
     */
    //@Bean
    //public DaoAuthenticationProvider authenticationProvider() {
    //    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    //    authenticationProvider.setUserDetailsService(detailsService());
    //    authenticationProvider.setPasswordEncoder(passwordEncoder());
    //    return authenticationProvider;
    //}

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/config/**", "/css/**", "/fonts/**", "/img/**", "/js/**");
    }

    /**
     * Security主要配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/registry").permitAll() // 注册请求不需要验证
                .antMatchers("/sign_up").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/sign_in")
                .loginProcessingUrl("/login").defaultSuccessUrl("/personal_center",true)
                .failureUrl("/sign_in?error").permitAll()
                .and().logout().logoutSuccessUrl("/sign_in").permitAll()
                .and().csrf().disable();
    }
}
