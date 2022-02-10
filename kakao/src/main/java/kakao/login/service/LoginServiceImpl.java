//package kakao.login.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import kakao.login.mapper.LoginMapper;
//import kakao.users.dto.UsersDto;
//
//@Service
//public class LoginServiceImpl implements UserDetailsService {
//	
//	private final PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private LoginMapper loginMapper;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		UsersDto loginDto = loginMapper.selectUserId(username);
//		
////		return User.builder()
////                .username(loginDto.getUserId())
////                .password(passwordEncoder.encode(loginDto.getPassword()))
////                .roles("USER")
////                .build();
//		return SecurityUser();
//	}
//
//}