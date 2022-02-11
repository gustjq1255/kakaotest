package kakao.entity;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface QnaRepository extends CrudRepository<Qna, Integer> {
	
	List<Qna> findAllByQueEmail(String eamil);
	
	Qna findBySeq(int seq);
	
}