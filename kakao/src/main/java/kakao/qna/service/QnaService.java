package kakao.qna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kakao.entity.Qna;
import kakao.entity.QnaRepository;
import kakao.qna.dto.QnaDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {
	
	private final QnaRepository qnaRepository;
	
	public QnaDto insert(QnaDto qnaDto) throws Exception {
		
		QnaDto data = new QnaDto(qnaRepository.save(qnaDto.toEntity()));
		
		return data;
	}
	
	public List<QnaDto> list(QnaDto qnaDto) throws Exception {
		
		List<Qna> listEntity = qnaRepository.findAllByQueUserId(qnaDto.getQueUserId());
		
		List<QnaDto> list = listEntity.stream().map(QnaDto::new).collect(Collectors.toList());
		
		return list;
	}
	
	public QnaDto data(QnaDto qnaDto) throws Exception {
		
		QnaDto data = new QnaDto();
		
		Qna qna = qnaDto.getSeq() != null ? qnaRepository.findBySeqAndQueUserId(Integer.parseInt(qnaDto.getSeq()), qnaDto.getQueUserId()) : null;
		
		if(qna != null) data = new QnaDto(qna);
		
		return data;
	}
}