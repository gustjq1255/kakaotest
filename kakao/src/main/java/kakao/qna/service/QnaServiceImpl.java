package kakao.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kakao.qna.dto.QnaDto;
import kakao.qna.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	public List<QnaDto> list(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.list(qnaDto);
	}

	@Override
	public QnaDto data(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.data(qnaDto);
	}

	@Override
	public int insert(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.insert(qnaDto);
	}

	@Override
	public int update(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		return qnaMapper.update(qnaDto);
	}

	@Override
	public void delete(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		qnaMapper.delete(qnaDto);
	}
}