package kakao.qna.dto;

import java.time.LocalDateTime;

import kakao.qna.entity.Qna;
import lombok.Data;

@Data
public class QnaDto {
	private int seq;
	private String queEmail;
	private String queTitle;
	private String queContent;
	private LocalDateTime queDate;
	private String status;
	private String ansUserId;
	private String ansUserName;
	private String ansContent;
	private LocalDateTime ansDate;
	private LocalDateTime inDate;
	private String inUserId;
	private LocalDateTime upDate;
	private String upUserId;
	
	public QnaDto(Qna entity) {
		this.seq = entity.getSeq();
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
		return Qna.builder()
			.seq(seq)
			.queEmail(queEmail)
			.queTitle(queTitle)
			.queContent(queContent)
			.status(status)
			.build();
	}
}