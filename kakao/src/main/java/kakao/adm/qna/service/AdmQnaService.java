package kakao.adm.qna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import kakao.adm.qna.dto.AdmQnaDto;
import kakao.entity.Qna;
import kakao.entity.QnaRepository;
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
		
		AdmQnaDto data = new AdmQnaDto();
		
		Qna qna = admQnaDto.getSeq() != null ? qnaRepository.findBySeq(Integer.parseInt(admQnaDto.getSeq())) : null;
		
		if(qna != null) data = new AdmQnaDto(qna);
		
		return data;
	}
}