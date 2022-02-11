package kakao.qna.dto;

import java.time.LocalDateTime;

import kakao.entity.Qna;
import kakao.util.StringUtil;
import lombok.Data;

@Data
public class QnaDto {
	
	private String seq;
	private String queEmail;
	private String queTitle;
	private String queContent;
	private LocalDateTime queDate = LocalDateTime.now();
	private String status;
	private String ansUserId;
	private String ansUserName;
	private String ansContent;
	private LocalDateTime ansDate = LocalDateTime.now();
	private LocalDateTime inDate = LocalDateTime.now();
	private String inUserId;
	private LocalDateTime upDate = LocalDateTime.now();
	private String upUserId;
	
	public QnaDto(){}
	
	public QnaDto(Qna entity) {
		this.seq = Integer.toString(entity.getSeq());
		this.queEmail = entity.getQueEmail();
		this.queTitle = entity.getQueTitle();
		this.queContent = entity.getQueContent();
		this.queDate = entity.getQueDate();
		this.status = entity.getStatus();
		this.ansUserId = entity.getAnsUserId();
		this.ansUserName = entity.getAnsUserName();
		this.ansContent = entity.getAnsContent();
		this.ansDate = entity.getAnsDate();
		this.inDate = entity.getInDate();
		this.inUserId = entity.getInUserId();
		this.upDate = entity.getUpDate();
		this.upUserId = entity.getUpUserId();
	}
	
	public Qna toEntity() {
		
		if(StringUtil.isEmpty(seq)) {
			return Qna.builder()
					.queEmail(queEmail)
					.queTitle(queTitle)
					.queContent(queContent)
					.status(status)
					.queDate(this.queDate)
					.inDate(this.queDate)
					.build();
		}else {
			return Qna.builder()
					.seq(Integer.parseInt(seq))
					.queEmail(queEmail)
					.queTitle(queTitle)
					.queContent(queContent)
					.status(status)
					.queDate(this.queDate)
					.upDate(this.queDate)
					.build();
		}
		
	}
}