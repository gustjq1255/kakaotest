package kakao.adm.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kakao.adm.user.dto.AdmUserDto;
import kakao.entity.Users;
import kakao.entity.UsersRepository;
import kakao.util.StringUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdmUserService {
	
	private final UsersRepository usersRepository;
	
	public AdmUserDto save(AdmUserDto admUserDto) {
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    
	    if(!StringUtil.isEmpty(admUserDto.getPwd())) {
	    	admUserDto.setPwd(encoder.encode(admUserDto.getPwd()));
	    }
	    
	    AdmUserDto data = new AdmUserDto(usersRepository.save(admUserDto.toEntity()));
	    
	    return data;
	}	
	
	public AdmUserDto updateUserNotPwd(AdmUserDto admUserDto) {
	    
		AdmUserDto data = new AdmUserDto();
		
		int result = admUserDto.getUserId() != null ? usersRepository.updateUserNotPwd(admUserDto.toEntity()) : 0;
		
		if(result > 0) {
			data = new AdmUserDto(usersRepository.findByUserId(admUserDto.getUserId()));
		}
	    
	    return data;
	}
	
	public List<AdmUserDto> list(AdmUserDto admUserDto) {
	    
		List<Users> listEntity = usersRepository.findAllByOrderByInDateDesc();
				
		List<AdmUserDto> list = listEntity.stream().map(AdmUserDto::new).collect(Collectors.toList());
		
		return list;
	}	
	
	public AdmUserDto data(AdmUserDto admUserDto) throws Exception {
		
		AdmUserDto data = new AdmUserDto();
		
		Users users = admUserDto.getUserId() != null ? usersRepository.findByUserId(admUserDto.getUserId()) : null;
		
		if(users != null) data = new AdmUserDto(users);
		
		return data;
	}
}