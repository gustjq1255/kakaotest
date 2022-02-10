package kakao.qna.service;

import java.util.List;

import kakao.qna.dto.QnaDto;
import kakao.qna.entity.QnaEntity;

public interface QnaService {
	
	List<QnaEntity> list(QnaEntity qnaEntity) throws Exception;
	
	QnaDto data(QnaDto qnaDto) throws Exception;
	
	int insert(QnaDto qnaDto) throws Exception;
	
	int update(QnaDto qnaDto) throws Exception;
	
	void delete(QnaDto qnaDto) throws Exception;
}