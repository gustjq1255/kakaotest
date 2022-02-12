package kakao.login.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kakao.entity.Users;
import kakao.entity.UsersRepository;
import kakao.login.dto.LoginDto;
import kakao.util.StringUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class LoginService implements UserDetailsService {
	
	private final UsersRepository usersRepository;
		
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		Users user = usersRepository.findByUserId(userId);
		
		if(user == null) new UsernameNotFoundException((userId));
		
		return User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
	}
	
	public int countUserId(String userId) throws Exception {
		
		int result = 0;
		
		Users users = StringUtil.isEmpty(userId) ? null : usersRepository.findByUserId(userId);
		
		if(users != null) result = 1;
		
		return result;
	}

	
	public LoginDto save(LoginDto loginDto) {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    
	    if(!StringUtil.isEmpty(loginDto.getPwd())) {
	    	loginDto.setPwd(encoder.encode(loginDto.getPwd()));
	    }
	    
	    LoginDto data = new LoginDto(usersRepository.save(loginDto.toEntity()));
	    
	    return data;
	}	
	
	public LoginDto updateUserNotPwd(LoginDto loginDto) {
	    
		LoginDto data = new LoginDto();
		
		int result = loginDto.getUserId() != null ? usersRepository.updateUserNotPwd(loginDto.toEntity()) : 0;
		
		if(result > 0) {
			data = new LoginDto(usersRepository.findByUserId(loginDto.getUserId()));
		}
	    
	    return data;
	}

}