package kakao.adm.qna.service;

import java.util.List;

import kakao.qna.dto.QnaDto;

public interface AdmQnaService {
	
	List<QnaDto> list(QnaDto qnaDto) throws Exception;
	
	QnaDto data(QnaDto qnaDto) throws Exception;
	
	int update(QnaDto qnaDto) throws Exception;
	
	void delete(QnaDto qnaDto) throws Exception;
}