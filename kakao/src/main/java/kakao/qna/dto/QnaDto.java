package kakao.qna.dto;

import java.time.LocalDateTime;

import lombok.Data;

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
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getQueEmail() {
		return queEmail;
	}
	public void setQueEmail(String queEmail) {
		this.queEmail = queEmail;
	}
	public String getQueTitle() {
		return queTitle;
	}
	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}
	public String getQueContent() {
		return queContent;
	}
	public void setQueContent(String queContent) {
		this.queContent = queContent;
	}
	public LocalDateTime getQueDate() {
		return queDate;
	}
	public void setQueDate(LocalDateTime queDate) {
		this.queDate = queDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAnsUserId() {
		return ansUserId;
	}
	public void setAnsUserId(String ansUserId) {
		this.ansUserId = ansUserId;
	}
	public String getAnsUserName() {
		return ansUserName;
	}
	public void setAnsUserName(String ansUserName) {
		this.ansUserName = ansUserName;
	}
	public String getAnsContent() {
		return ansContent;
	}
	public void setAnsContent(String ansContent) {
		this.ansContent = ansContent;
	}
	public LocalDateTime getAnsDate() {
		return ansDate;
	}
	public void setAnsDate(LocalDateTime ansDate) {
		this.ansDate = ansDate;
	}
	public LocalDateTime getInDate() {
		return inDate;
	}
	public void setInDate(LocalDateTime inDate) {
		this.inDate = inDate;
	}
	public String getInUserId() {
		return inUserId;
	}
	public void setInUserId(String inUserId) {
		this.inUserId = inUserId;
	}
	public LocalDateTime getUpDate() {
		return upDate;
	}
	public void setUpDate(LocalDateTime upDate) {
		this.upDate = upDate;
	}
	public String getUpUserId() {
		return upUserId;
	}
	public void setUpUserId(String upUserId) {
		this.upUserId = upUserId;
	}
	
	
}