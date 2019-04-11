package com.es.datamigration.configuration.auth;//
//package com.learn.api.configuration.auth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Value("${login.learn.basicAuthName}")
//    String authUserName;
//
//    @Value("${login.learn.basicAuthPass}")
//    String authPassword;
//
//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//            .inMemoryAuthentication().withUser(authUserName).password(authPassword).roles("BASIC");
//    }
//
//    /**
//     * http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
//     * .antMatchers("/**").hasRole("USER").and().formLogin();
//     *
//     * 控制swagger访问权限，下方注释打开，即需要账号密码登录，账号密码可查看配置文件
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/learn/swagger**").permitAll()
//                .antMatchers("/**")
////                .hasRole("BASIC")
////                .and()
////                .formLogin();
//                .permitAll();
//    }
//
//}
