package kakao.adm.qna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kakao.adm.qna.dto.AdmQnaDto;
import kakao.qna.entity.Qna;
import kakao.qna.repository.QnaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdmQnaService {
	
	private final QnaRepository qnaRepository;
	
	public AdmQnaDto insert(AdmQnaDto admQnaDto) throws Exception {
		
		AdmQnaDto data = new AdmQnaDto(qnaRepository.save(admQnaDto.toEntity()));
		
		return data;
	}
	
	public List<AdmQnaDto> list(AdmQnaDto admQnaDto) throws Exception {
		
		List<Qna> listEntity = qnaRepository.findAllByQueEmail(admQnaDto.getQueEmail());
		
		List<AdmQnaDto> list = listEntity.stream().map(AdmQnaDto::new).collect(Collectors.toList());
		
		return list;
	}
	
	public AdmQnaDto data(AdmQnaDto admQnaDto) throws Exception {
		
		AdmQnaDto data = new AdmQnaDto(qnaRepository.findBySeq(admQnaDto.getSeq()));
		
		return data;
	}
}