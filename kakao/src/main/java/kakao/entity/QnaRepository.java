package kakao.entity;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface QnaRepository extends CrudRepository<Qna, Integer> {
	
	static final String ADM_LIST = "SELECT * FROM qna "
			+ "WHERE (ans_user_id = :userId OR status = '100') "
			+ "ORDER BY status ASC, que_date desc ";
	
	static final String ADM_UPDATE = "UPDATE qna SET "
			+ "ans_user_id = :#{#qna.ansUserId} "
			+ ", ans_date = :#{#qna.ansDate} "
			+ ", ans_content= :#{#qna.ansContent} "
			+ ", status = :#{#qna.status} "
			+ ", up_user_id = :#{#qna.upUserId} "
			+ ", up_date = :#{#qna.upDate} "
			+ "WHERE seq = :#{#qna.seq}";

	List<Qna> findAllByQueUserId(String userId);
	
	@Query(value = ADM_LIST, nativeQuery = true)
	public List<Qna> admList(@Param("userId") String userId);
	
	Qna findBySeqAndQueUserId(int seq, String userid);
	
	Qna findBySeq(int seq);
	
	@Transactional
	@Modifying
	@Query(value = ADM_UPDATE, nativeQuery = true)
	public int admUpdate(@Param("qna") Qna qna);
	
}