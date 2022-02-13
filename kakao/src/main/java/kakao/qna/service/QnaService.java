package kakao.qna.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kakao.config.websoket.WebsocketClientEndpoint;
import kakao.entity.Qna;
import kakao.entity.QnaRepository;
import kakao.qna.dto.QnaDto;
import kakao.util.StringUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaService {
	
	private final QnaRepository qnaRepository;
	
	//상담 websocket url
	@Value("${kakao.websocket.qna.url}")
	String websocketUrl;
	
	public QnaDto insert(QnaDto qnaDto) throws Exception {
		
		QnaDto data = new QnaDto(qnaRepository.save(qnaDto.toEntity()));
		
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