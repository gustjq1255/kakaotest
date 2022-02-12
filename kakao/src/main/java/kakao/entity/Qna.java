package kakao.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class Qna {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int seq;
	
	@Column(nullable=false)
	private String queUserId;
	
	@Column(nullable=false)
	private String queTitle;
	
	@Column(nullable=false)
	private String queContent;
	
	@Column
	private LocalDateTime queDate = LocalDateTime.now();
	
	@Column(nullable=false)
	private String status;
	
	@Column
	private String inUserId;
	
	@Column
	private String upUserId;
	
	@Column
	private String ansUserId;
	
	@Column
	private String ansContent;
	
	@Column
	private LocalDateTime ansDate = LocalDateTime.now();
	
	@Column(updatable=false)
	private LocalDateTime inDate = LocalDateTime.now();
	
	@Column(insertable=false)
	private LocalDateTime upDate = LocalDateTime.now();
	
	@Builder
	public Qna(int seq, String queUserId, String queTitle, String queContent, String status, String ansUserId, String ansContent
				, LocalDateTime queDate, LocalDateTime ansDate, LocalDateTime inDate, LocalDateTime upDate, String upUserId) {
		this.seq = seq;
		this.queUserId = queUserId;
	    this.queTitle = queTitle;
	    this.queContent = queContent;
	    this.status = status;
	    this.ansUserId = ansUserId;
	    this.ansContent = ansContent;
	    this.queDate = queDate;
	    this.ansDate = ansDate;
	    this.inDate = inDate;
	    this.upDate = upDate;
	    this.upUserId = upUserId;
	}
}

