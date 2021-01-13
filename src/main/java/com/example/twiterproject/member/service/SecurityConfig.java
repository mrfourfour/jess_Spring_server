package com.example.twiterproject.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity//시큐리티 필터가 등록된다
@EnableGlobalMethodSecurity(prePostEnabled = true)// 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 로그인 부분 보안설정 부분
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //페이지 권한 설정하는 부분; 회원 가입한 사람만 친구, 피드, 피드 하나보기가 가능
        http.csrf().disable();
        http.
                authorizeRequests()
                .antMatchers("/account/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() // 로그인 설정
                .loginPage("/account/login")
                .defaultSuccessUrl("/")
                .permitAll();

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

}
