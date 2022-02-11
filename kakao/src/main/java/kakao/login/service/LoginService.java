package kakao.login.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kakao.entity.Users;
import kakao.entity.UsersRepository;
import kakao.login.dto.LoginDto;
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
	
	public LoginDto findByUserId(String userId) {
		
		LoginDto loginDto = new LoginDto();
		
		Users users = usersRepository.findByUserId(userId);
				
		if(users == null) new IllegalArgumentException("아이디 혹은 비밀번호를 확인해주세요.");
		
		loginDto = new LoginDto(users);
		
		return loginDto;
	}

}