package kakao.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kakao.util.StringUtil;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class) 
@SpringBootTest
@DisplayName("상담(Qan) JPA CRUD 테스트")
class QnaRepositoryTest {
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@Test
	@Order(1)
	@DisplayName("질문자 등록/수정")
	void testInsert() {
		
		int seq = 1;
		
		Qna date = qnaRepository.findBySeq(1);
		
		if(date == null) {
			Qna qna = Qna.builder()
					.seq(seq)
					.queUserId("testuser") //작성자ID
					.queTitle("등록 테스트") //제목
					.queContent("등록 OK?") //내용
					.status("100") //상태값 100:대기중
					.queDate(LocalDateTime.now()) //작성일
					.build();
		
			Qna resultData = qnaRepository.save(qna);
			assertThat(resultData.getSeq()).isNotNull();
		}
	}
	
	@Test
	@Order(2)
	@DisplayName("질문자 수정")
	void testUpdate() {
		int seq = 1;
		
		Qna beforDate = qnaRepository.findBySeq(seq);
		
		Qna qna = Qna.builder()
				.seq(beforDate.getSeq()) //작성자ID
				.queUserId("testuser") //작성자ID
				.queTitle(beforDate.getQueTitle()+" 수정 테스트") //제목
				.queContent(beforDate.getQueContent()+"수정 OK?") //내용
				.status("100") //상태값 100:대기중
				.queDate(LocalDateTime.now()) //작성일
				.build();
		
		Qna resultData = qnaRepository.save(qna);
		
		assertThat(resultData.getSeq()).isEqualTo(beforDate.getSeq());
		assertThat(resultData.getQueTitle()).isNotEqualTo(beforDate.getQueTitle());
		assertThat(resultData.getQueContent()).isNotEqualTo(beforDate.getQueContent());
	}
	
	@Test
	@Order(3)
	@DisplayName("질문자 조회")
	void testFindBySeqAndQueUserId() {
		
		String userId = "testuser";
		List<Qna> resultList = qnaRepository.findAllByQueUserId(userId);
		int seq = resultList.get(0).getSeq();
		
		
		Qna resultData = qnaRepository.findBySeqAndQueUserId(seq, userId);
		
		assertThat(resultData.getSeq()).isEqualTo(seq);
		assertThat(resultData.getQueUserId()).isEqualTo("testuser");
	}
	
	@Test
	@Order(4)
	@DisplayName("질문자 목록")
	void testFindAllByQueUserId() {
		String userId = "testuser";
		
		List<Qna> resultList = qnaRepository.findAllByQueUserId(userId);
		
		List<String> userIds = new ArrayList<>();
	    for (Qna qna : resultList) {
	    	userIds.add(qna.getQueUserId());
	    }
	    assertThat(resultList.size()).isGreaterThanOrEqualTo(0);
		assertThat(userIds).containsOnly("testuser");
	}
	
	@Test
	@Order(5)
	@DisplayName("관리자 수정")
	void testAdmUpdate() {
		
		int seq = 1;
		
		Qna beforDate = qnaRepository.findBySeq(seq);
		
		Qna qna = Qna.builder()
				.seq(beforDate.getSeq()) //PK
				.ansUserId("testadmin") //상담사ID
				.ansContent(beforDate.getQueContent()+"수정 OK?") //답변내용
				.status("300") //상태값 300:답변완료
				.ansDate(LocalDateTime.now()) //답변일
				.build();
		
		int result = qnaRepository.admUpdate(qna);
		System.out.println("결과 궁금 : " +result);
		Qna resultData = qnaRepository.findBySeq(seq);
		
		assertThat(resultData.getAnsContent()).isNotEqualTo(beforDate.getAnsContent());
		assertThat(resultData.getSeq()).isEqualTo(beforDate.getSeq());
		assertThat(resultData.getAnsUserId()).isEqualTo("testadmin");
		assertThat(resultData.getStatus()).isEqualTo("300");
	}
	
	@Test
	@Order(6)
	@DisplayName("관리자 목록 조회")
	void testAdmList() {
		String userId = "gustjq1255";
		List<Qna> resultList = qnaRepository.admList(userId);
		
		assertThat(resultList.size()).isGreaterThanOrEqualTo(0);
		assertThat(resultList).allMatch(result -> (!StringUtil.isEmpty(result.getAnsUserId()) && result.getAnsUserId().equals(userId)) || result.getStatus().equals("100"));
	}

	@Test
	@Order(7)
	@DisplayName("관리자 질문 조회")
	void testFindBySeq() {
		
		int seq = 1;
		
		Qna resultData = qnaRepository.findBySeq(seq);
		
		assertThat(resultData).isNotNull();
		assertThat(resultData.getSeq()).isEqualTo(seq);
		
	}

}
