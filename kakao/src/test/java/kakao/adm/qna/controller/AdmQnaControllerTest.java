package kakao.adm.qna.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AdmQnaControllerTest {
	
	@Autowired
    MockMvc mockMvc;
	
	@Test
	@DisplayName("관리자 질문게시판 목록화면 : 관리자접근가능, 사용자 접근 불가")
	void testQnalistPage() throws Exception {
		mockMvc.perform(get("/adm/qna/list")
				.with(user("testadmin").roles("ADMIN")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/adm/qna/list")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isForbidden());
    }

	@Test
	@DisplayName("관리자 질문게시판 조회화면 : 관리자접근가능, 사용자 접근 불가")
	void testQnaViewPage() throws Exception {
		mockMvc.perform(get("/adm/qna/view?seq=1")
				.with(user("testadmin").roles("ADMIN")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/adm/qna/view?seq=1")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isForbidden());
	}

	@Test
	@DisplayName("관리자 질문게시판 수정화면 : 관리자접근가능, 사용자 접근 불가")
	void testQnainsertPage() throws Exception {
		mockMvc.perform(get("/adm/qna/insert?seq=1")
				.with(user("testadmin").roles("ADMIN")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/adm/qna/insert?seq=1")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isForbidden());
	}

}
