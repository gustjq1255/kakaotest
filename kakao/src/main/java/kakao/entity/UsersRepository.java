package kakao.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UsersRepository extends CrudRepository<Users, Integer> {
	
	static final String UPDATE_BOARD = "UPDATE users SET "
			+ "user_nm = :#{#users.userNm} "
			+ ", role = :#{#users.role} "
			+ ", up_user_id = :#{#users.upUserId} "
			+ ", up_date = :#{#users.upDate} "
			+ "WHERE user_id = :#{#users.userId}";
	
	List<Users> findAllByOrderByInDateDesc();
	
	Users findByUserId(String userId);
	
	@Transactional
	@Modifying
	@Query(value = UPDATE_BOARD, nativeQuery = true)
	public int updateUserNotPwd(@Param("users") Users users);
	
}