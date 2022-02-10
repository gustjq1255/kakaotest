package kakao.qna.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="qna")
@NoArgsConstructor
@Data
public class QnaEntity {
	
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
	private String status;
	
	@Column
	private String inUserId;
	
	@Column(nullable=false)
	private LocalDateTime queDate = LocalDateTime.now();
	
	private LocalDateTime inDate = LocalDateTime.now();

	
}