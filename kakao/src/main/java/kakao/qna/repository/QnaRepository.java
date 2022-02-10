package kakao.qna.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kakao.qna.entity.QnaEntity;

public interface QnaRepository extends CrudRepository<QnaEntity, Integer> {
	
	List<QnaEntity> findAllByQueEmail(String eamil);
	
}