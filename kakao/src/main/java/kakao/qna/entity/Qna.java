package kakao.qna.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Qna {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int seq;
	
	@Column(nullable=false)
	private String queEmail;
	
	@Column(nullable=false)
	private String queTitle;
	
	@Column(nullable=false)
	private String queContent;
	
	@Column(nullable=false)
	private LocalDateTime queDate = LocalDateTime.now();
	
	@Column(nullable=false)
	private String status;
	
	@Column
	private String inUserId;
	
	@Column
	private String upUserId;
	
	@Column(nullable=false)
	private String ansUserId;
	
	@Column(nullable=false)
	private String ansUserName;
	
	@Column(nullable=false)
	private String ansContent;
	
	@Column(nullable=false)
	private LocalDateTime ansDate = LocalDateTime.now();
	
	@Column(nullable=false)
	private LocalDateTime inDate = LocalDateTime.now();
	
	@Column(nullable=false)
	private LocalDateTime upDate = LocalDateTime.now();
	
	@Builder
	public Qna(int seq, String queEmail, String queTitle, String queContent, String status, String ansUserId, String ansUserName, String ansContent) {
		this.seq = seq;
		this.queEmail = queEmail;
	    this.queTitle = queTitle;
	    this.queContent = queContent;
	    this.status = status;
	    this.ansUserId = ansUserId;
	    this.ansUserName =ansUserName;
	    this.ansContent = ansContent;
	}
}

