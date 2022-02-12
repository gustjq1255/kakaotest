package kakao.adm.qna.dto;

import java.time.LocalDateTime;

import kakao.entity.Qna;
import lombok.Data;

@Data
public class AdmQnaDto {
	
	private String seq;
	private String queUserId;
	private String queTitle;
	private String queContent;
	private LocalDateTime queDate;
	private String status;
	private String ansUserId;
	private String ansContent;
	private LocalDateTime ansDate = LocalDateTime.now();;
	private LocalDateTime inDate;
	private String inUserId;
	private LocalDateTime upDate = LocalDateTime.now();;
	private String upUserId;
	
	public AdmQnaDto(){}
	
	public AdmQnaDto(Qna entity) {
		this.seq = Integer.toString(entity.getSeq());
		this.queUserId = entity.getQueUserId();
		this.queTitle = entity.getQueTitle();
		this.queContent = entity.getQueContent();
		this.queDate = entity.getQueDate();
		this.status = entity.getStatus();
		this.ansUserId = entity.getAnsUserId();
		this.ansContent = entity.getAnsContent();
		this.ansDate = entity.getAnsDate();
		this.inDate = entity.getInDate();
		this.inUserId = entity.getInUserId();
		this.upDate = entity.getUpDate();
		this.upUserId = entity.getUpUserId();
	}
	
	public Qna toEntity() {
		
		return Qna.builder()
				.seq(Integer.parseInt(seq))
				.ansUserId(ansUserId)
				.upUserId(upUserId)
				.ansContent(ansContent)
				.status(status)
				.ansDate(ansDate)
				.upDate(upDate)
				.build();
		
	}
}