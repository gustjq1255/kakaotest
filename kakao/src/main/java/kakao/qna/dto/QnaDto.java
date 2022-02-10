package kakao.qna.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class QnaDto {
	private String seq;
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
	
	
}