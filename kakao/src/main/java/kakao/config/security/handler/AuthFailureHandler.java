package kakao.config.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
	    String msg = "아이디와 비밀번호를 확인해주세요";
	
	    if(exception instanceof UsernameNotFoundException) {
        	msg = "계정이 없습니다.";
        } else if(exception instanceof BadCredentialsException ) {
        	msg = "비밀번호가 다릅니다.";
        }
	
	    setDefaultFailureUrl("/login?error=true&exception=" + msg);
	
	    super.onAuthenticationFailure(request, response, exception);
	}
}
