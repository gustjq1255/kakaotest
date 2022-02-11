package kakao.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kakao.config.security.handler.AuthFailureHandler;
import kakao.config.security.handler.AuthSucessHandler;
import kakao.login.service.LoginService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final LoginService loginService;
	private final AuthSucessHandler authSucessHandler;
	private final AuthFailureHandler authFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 시큐리티 인증을 무시할 경로
		web.ignoring().antMatchers("/css/**", "/img/**", "/js/**", "/lib/**", "/vendor/**");
	}
	
	@Override 
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable() // 사용안함, POST요청안됨 추가설정 필요
        	.authorizeRequests()
			.antMatchers("/adm/user/**").permitAll() // 최초회원가입을 위한 설정 TEST용 
			.antMatchers("/adm/**").hasRole("ADMIN") // ADMIN만 접근 허용
			.antMatchers("/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.formLogin() // 로그인 설정
			.loginPage("/login/login") // 로그인 화면
			.loginProcessingUrl("/login/action") //로그인 처리 URL
			.successHandler(authSucessHandler) // 성공핸들러
			.failureHandler(authFailureHandler) // 실패핸들러
		.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // 로그아웃 URL
		    .logoutSuccessUrl("/") // 로그아웃 후 경로
		    .invalidateHttpSession(true) // 인증정보를 지우하고 세션을 무효화
		    .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
			.permitAll()
		.and()
			.sessionManagement()
	        .maximumSessions(1) // 세션 허용 수 1, -1인 경우 무제한 세션 허용
	        .maxSessionsPreventsLogin(false)
	        .expiredUrl("/login?error=true&exception=세션이 만료되었습니다.")
        .and()
	        .and().rememberMe() // 로그인 유지
	        .alwaysRemember(false) // 항상 기억할 것인지 여부
	        .tokenValiditySeconds(3600); // 1시간 세션 유지
    }
	
	@Override public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService).passwordEncoder(passwordEncoder());
	}
}
