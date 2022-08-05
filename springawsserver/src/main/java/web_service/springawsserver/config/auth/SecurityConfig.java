package web_service.springawsserver.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import web_service.springawsserver.domain.entity.Role;

@RequiredArgsConstructor
@EnableWebSecurity  //Spring Security 활성화
@Configuration
public class SecurityConfig {
    private final CustomOauth2UserService customOauth2UserService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable() //h2 화면을 사용하기 위해 disable 처리
                .and()
                    .authorizeRequests() //URL 별 권한 설정
                    .antMatchers("/","/posts/","/css/**","/images/**","/js/**","/h2-console/**").permitAll() //permitAll의 경우 전체 열람권한 설정
                    .antMatchers("/api/v1/**","/posts/**").hasRole(Role.USER.name()) //hasRole을 통해 해당 role을 가진 user만 열람 가능하도록 설정
                    .anyRequest().authenticated() //그 외 나머지 url은 인증된 사용자에게만 허용
                .and()
                    .logout()
                        .logoutSuccessUrl("/")  //로그아웃 성공시 리다이렉트할 url
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .userService(customOauth2UserService);  //소셜 서비스 로그인 성공 후 이어지는 후처리 서비스 등록
        return http.build();
    }

}
