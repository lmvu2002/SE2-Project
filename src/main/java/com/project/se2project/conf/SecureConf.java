//package com.project.se2project.conf;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.HttpSecurityDsl;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//
//@Configuration
//public class SecureConf extends WebSecurityConfiguration {
//
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                .antMatchers("/**").authenticated()
//    }
//}
