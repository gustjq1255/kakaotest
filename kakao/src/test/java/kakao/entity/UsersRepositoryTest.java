package kakao.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class) 
@SpringBootTest
@DisplayName("유저정보(Users) JPA CRUD 테스트")
class UsersRepositoryTest {
	
	@Autowired
	private UsersRepository usersRepository;
	
	final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@Test
	@Order(1)
	@DisplayName("유저 등록")
	void testInsert() {
		String userId = "testuser";
		
		Users date = usersRepository.findByUserId(userId);
		
		if(date == null) {
			Users user = Users.builder()
						.userId(userId) //작성자ID
						.userNm("테스트사용자") //작성자ID
						.pwd(encoder.encode(userId)) //제목
						.role("USER") //권한  USER, ADMIN
						.inUserId(userId) //등록자ID
						.inDate(LocalDateTime.now()) //상태값 100:대기중
						.build();
			
			Users resultUser = usersRepository.save(user);
			assertThat(resultUser.getUserId()).isEqualTo(userId);
		}
		
		String adminId = "testadmin";
		
		Users adminData = usersRepository.findByUserId(adminId);
		
		if(adminData == null) {
			Users admUser = Users.builder()
						.userId(adminId) //작성자ID
						.userNm("테스트관리자") //작성자ID
						.pwd(encoder.encode(adminId)) //제목
						.role("USER") //권한  USER, ADMIN
						.inUserId(adminId) //등록자ID
						.inDate(LocalDateTime.now()) //상태값 100:대기중
						.build();
			
			Users resultAdmin = usersRepository.save(admUser);
			assertThat(resultAdmin.getUserId()).isEqualTo(adminId);
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("유저 수정")
	void testUpdate() {
		String userId = "testadmin";
		
		Users beforDate = usersRepository.findByUserId(userId);
		
		Users user = Users.builder()
				.userId(userId) //작성자ID
				.userNm("테스트관리자") //작성자ID
				.pwd(encoder.encode(userId)) //제목
				.role("ADMIN") //권한  USER, ADMIN
				.upUserId(userId) //등록자ID
				.upDate(LocalDateTime.now()) //상태값 100:대기중
				.build();
		
		Users resultData = usersRepository.save(user);
		
		assertThat(resultData.getUpDate()).isNotEqualTo(beforDate.getUpDate());
	}
	
	@Test
	@Order(3)
	@DisplayName("유저 목록")
	void testFindAllByOrderByInDateDesc() {
		
		List<Users> resultList = usersRepository.findAllByOrderByInDateDesc();
		
	    assertThat(resultList.size()).isGreaterThanOrEqualTo(0);
	}

	@Test
	@Order(4)
	@DisplayName("유저 조회")
	void testFindByUserId() {
		String userId = "testuser";
		
		Users resultData = usersRepository.findByUserId(userId);
		
		assertThat(resultData).isNotNull();
		assertThat(resultData.getUserId()).isEqualTo(userId);
	}

	@Test
	@Order(4)
	@DisplayName("유저 업데이트(비밀번호제외)")
	void testUpdateUserNotPwd() {
		String userId = "testadmin";
		
		Users beforDate = usersRepository.findByUserId(userId);
		
		Users user = Users.builder()
				.userId(userId) //작성자ID
				.userNm("테스트관리자") //작성자ID
				.pwd(encoder.encode("1111")) //제목
				.role("ADMIN") //권한  USER, ADMIN
				.upUserId(userId) //등록자ID
				.upDate(LocalDateTime.now()) //상태값 100:대기중
				.build();
		
		int result = usersRepository.updateUserNotPwd(user);
		
		Users resultData = usersRepository.findByUserId(userId);
		
		assertThat(result).isGreaterThanOrEqualTo(1);
		assertThat(resultData.getPwd()).isEqualTo(beforDate.getPwd());
	}

}
