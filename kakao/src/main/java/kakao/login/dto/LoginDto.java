package kakao.login.dto;

import java.time.LocalDateTime;

import kakao.entity.Users;
import lombok.Data;

@Data
public class LoginDto{
	
	private String userId;
	private String userNm;
	private String role;
	private String pwd;
	private String inUserId;
	private String upUserId;
	private LocalDateTime inDate = LocalDateTime.now();;
	private LocalDateTime upDate = LocalDateTime.now();;
	
	public LoginDto(){}
	
	public LoginDto(Users entity) {
		this.userId = entity.getUserId();
		this.userNm = entity.getUserNm();
		this.role = entity.getRole();
		this.pwd = entity.getPassword();
		this.inUserId = entity.getInUserId();
		this.upUserId = entity.getUpUserId();
		this.inDate = entity.getInDate();
		this.upDate = entity.getUpDate();
	}
	
	public Users toEntity() {
		
		return Users.builder()
				.userId(userId)
				.userNm(userNm)
				.role(role)
				.pwd(pwd)
				.inUserId(inUserId)
				.upUserId(upUserId)
				.inDate(inDate)
				.upDate(upDate)
				.build();
		
	}
}