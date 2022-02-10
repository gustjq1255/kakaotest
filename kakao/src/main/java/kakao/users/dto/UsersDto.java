package kakao.users.dto;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsersDto implements UserDetails{
	
	private static final long serialVersionUID = -6088203313309536121L;
	
	private String userId;
	private String userName;
	private String userGroup;
	private String password;
	private LocalDateTime inDate;
	private String inUserId;
	private LocalDateTime upDate;
	private String upUserId;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getInDate() {
		return inDate;
	}
	public void setInDate(LocalDateTime inDate) {
		this.inDate = inDate;
	}
	public String getInUserId() {
		return inUserId;
	}
	public void setInUserId(String inUserId) {
		this.inUserId = inUserId;
	}
	public LocalDateTime getUpDate() {
		return upDate;
	}
	public void setUpDate(LocalDateTime upDate) {
		this.upDate = upDate;
	}
	public String getUpUserId() {
		return upUserId;
	}
	public void setUpUserId(String upUserId) {
		this.upUserId = upUserId;
	}
	
	//UserDetails 메서드
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUserId();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}