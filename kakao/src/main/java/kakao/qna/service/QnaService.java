package kakao.qna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kakao.qna.dto.QnaDto;
import kakao.qna.entity.Qna;
import kakao.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {
	
	private final QnaRepository qnaRepository;
	
	public QnaDto insert(QnaDto qnaDto) throws Exception {
		
		System.out.println(qnaDto.toEntity());
		QnaDto data = new QnaDto(qnaRepository.save(qnaDto.toEntity()));
		
		return data;
	}
	
	public List<QnaDto> list(QnaDto qnaDto) throws Exception {
		
		List<Qna> listEntity = qnaRepository.findAllByQueEmail(qnaDto.getQueEmail());
		
		List<QnaDto> list = listEntity.stream().map(QnaDto::new).collect(Collectors.toList());
		
		return list;
	}
	
	public QnaDto data(QnaDto qnaDto) throws Exception {
		
		QnaDto data = new QnaDto(qnaRepository.findBySeq(qnaDto.getSeq()));
		
		return data;
	}
}