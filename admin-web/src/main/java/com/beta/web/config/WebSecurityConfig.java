package com.beta.web.config;

import com.beta.web.filter.JWTAuthenticationFilter;
import com.beta.web.filter.JWTLoginFilter;
import com.beta.web.service.MyPasswordEncoder;
import com.beta.web.voter.RoleBasedVoter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.expression.WebExpressionVoter;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService myCustomUserService;

    @Resource
    private MyPasswordEncoder myPasswordEncoder;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //关闭跨站请求防护
                .cors().and().csrf().disable()
                //允许不登陆就可以访问的方法，多个用逗号分隔
                .authorizeRequests()
                .antMatchers("/testd","/login","/stockServer/{userId}/{stockId}","/stock/getTimeSharingData/{stockCode}","/stock/getKdata/{stockCode}").permitAll()
                //其他的需要授权后访问
                .anyRequest().authenticated()

                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                //增加登录拦截
                .addFilter(new JWTLoginFilter(authenticationManager()))

                //增加是否登陆过滤



                // 前后端分离是无状态的，所以暫時不用session，將登陆信息保存在token中。
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                // 自定义accessDecisionManager
                .accessDecisionManager(accessDecisionManager());


    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //覆盖UserDetailsService类
//        auth.userDetailsService(myCustomUserService)
//                //覆盖默认的密码验证类
//                .passwordEncoder(myPasswordEncoder);
//    }
@Bean
public AccessDecisionManager accessDecisionManager() {
    List<AccessDecisionVoter<? extends Object>> decisionVoters
            = Arrays.asList(
            new WebExpressionVoter(),
            // new RoleVoter(),
             new RoleBasedVoter(),
            new AuthenticatedVoter()


    );
    return new UnanimousBased(decisionVoters);
}

}
