package kakao.qna.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kakao.qna.entity.Qna;

public interface QnaRepository extends CrudRepository<Qna, Integer> {
	
	List<Qna> findAllByQueEmail(String eamil);
	
	Qna findBySeq(int seq);
	
}