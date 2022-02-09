package kakao.qna.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kakao.qna.dto.QnaDto;

@Mapper
public interface QnaMapper {
	
	List<QnaDto> list(QnaDto qnaDto) throws Exception;
	
	QnaDto data(QnaDto qnaDto) throws Exception;
	
	int insert(QnaDto qnaDto) throws Exception;
	
	int update(QnaDto qnaDto) throws Exception;
	
	void delete(QnaDto qnaDto) throws Exception;
}