package kakao.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Users implements UserDetails{
	
	@Id
	private String userId;
	
	@Column
	private String userNm;
	
	@Column
	private String role;
	
	@Column
	private String pwd;
	
	@Column
	private String inUserId;
	
	@Column
	private String upUserId;
	
	@Column(updatable=false)
	private LocalDateTime inDate = LocalDateTime.now();
	
	@Column(insertable=false)
	private LocalDateTime upDate = LocalDateTime.now();
	
	@Override
	public String getUsername() {
		return this.getUserId();
	}
	
	@Override
	public String getPassword() {
		return this.getPwd();
	}
	
	@Builder
	public Users(String userId, String userNm, String role, String pwd, String inUserId, String upUserId, LocalDateTime inDate, LocalDateTime upDate) {
		this.userId = userId;
		this.userNm = userNm;
	    this.role = role;
	    this.pwd = pwd;
	    this.inUserId = inUserId;
	    this.upUserId = upUserId;
	    this.inDate = inDate;
	    this.upDate = upDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    for (String role : role.split(",")) {
	      roles.add(new SimpleGrantedAuthority(role));
	    }
	    return roles;
	  }

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}

