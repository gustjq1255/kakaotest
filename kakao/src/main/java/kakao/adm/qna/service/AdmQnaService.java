package kakao.adm.qna.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kakao.adm.qna.dto.AdmQnaDto;
import kakao.config.websoket.WebsocketClientEndpoint;
import kakao.entity.Qna;
import kakao.entity.QnaRepository;
import kakao.util.StringUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdmQnaService {
	
	private final QnaRepository qnaRepository;
	
	//상담 websocket url
	@Value("${kakao.websocket.qna.url}")
	String websocketUrl;
	
	public AdmQnaDto insert(AdmQnaDto admQnaDto) throws Exception {
		
		AdmQnaDto data = new AdmQnaDto();
		
	    int result = admQnaDto.getSeq() != null ? qnaRepository.admUpdate(admQnaDto.toEntity()) : 0;
	    
	    if(result > 0) {
			data = new AdmQnaDto(qnaRepository.findBySeq(Integer.parseInt(admQnaDto.getSeq())));
		}
		
	    //관리자 답변 목록 Polling방식 구현
		if(data != null) {
			try {
	            // open websocket
	            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://"+websocketUrl));
	
	            // 웹소켓 메시지 전달
	            clientEndPoint.sendMessage("{\"socketGubun\":\"real\",\"seq\":\""+data.getSeq()+"\",\"status\":\""+data.getStatus()+"\",\"queTitle\":\""+data.getQueTitle()+"\",\"ansUserId\":\""+data.getAnsUserId()+"\",\"queDate\":\""+StringUtil.getDateFormat(data.getQueDate(), "yyyy-MM-dd HH:mm")+"\",\"queUserId\":\""+data.getQueUserId()+"\"}");
	            
	            clientEndPoint.onClose();
			}catch (URISyntaxException ex) {
	            System.err.println("URISyntaxException exception: " + ex.getMessage());
	        }
		}
		
		return data;
	}
	
	public List<AdmQnaDto> list(String userId) throws Exception {
		
		List<Qna> listEntity = qnaRepository.admList(userId);
		
		List<AdmQnaDto> list = listEntity.stream().map(AdmQnaDto::new).collect(Collectors.toList());
		
		return list;
	}
	
	public AdmQnaDto data(AdmQnaDto admQnaDto) throws Exception {
		
		AdmQnaDto data = new AdmQnaDto();
		
		Qna qna = admQnaDto.getSeq() != null ? qnaRepository.findBySeq(Integer.parseInt(admQnaDto.getSeq())) : null;
		
		if(qna != null) data = new AdmQnaDto(qna);
		
		return data;
	}
	
	public AdmQnaDto data(String seq) throws Exception {
		
		AdmQnaDto data = new AdmQnaDto();
		
		Qna qna = seq != null ? qnaRepository.findBySeq(Integer.parseInt(seq)) : null;
		
		if(qna != null) data = new AdmQnaDto(qna);
		
		return data;
	}
}