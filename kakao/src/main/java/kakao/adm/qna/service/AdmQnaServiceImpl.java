package kakao.adm.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kakao.adm.qna.mapper.AdmQnaMapper;
import kakao.qna.dto.QnaDto;

@Service
public class AdmQnaServiceImpl implements AdmQnaService {
	
	@Autowired
	private AdmQnaMapper admQnaMapper;
	
	@Override
	public List<QnaDto> list(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		return admQnaMapper.list(qnaDto);
	}

	@Override
	public QnaDto data(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		return admQnaMapper.data(qnaDto);
	}

	@Override
	public int update(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		return admQnaMapper.update(qnaDto);
	}

	@Override
	public void delete(QnaDto qnaDto) throws Exception {
		// TODO Auto-generated method stub
		admQnaMapper.delete(qnaDto);
	}
}