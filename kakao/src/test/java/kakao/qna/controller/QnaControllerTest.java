package kakao.qna.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
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
class QnaControllerTest {
	
	@Autowired
    MockMvc mockMvc;
	
	@Test
	@DisplayName("질문게시판 목록화면 : 로그인사용자 접근가능, 비로그인시 Redirection")
	void testQnalistPage() throws Exception {
		mockMvc.perform(get("/qna/list")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/qna/list")
				.with(anonymous()))
                .andExpect(status().is3xxRedirection());
	}

	@Test
	@DisplayName("질문게시판 조회화면 : 로그인사용자 접근가능, 비로그인시 Redirection")
	void testQnaViewPage() throws Exception {
		mockMvc.perform(get("/qna/view?seq=1")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/qna/view?seq=1")
				.with(anonymous()))
                .andExpect(status().is3xxRedirection());
	}

	@Test
	@DisplayName("질문게시판 등록화면 : 로그인사용자 접근가능, 비로그인시 Redirection")
	void testQnainsertPage() throws Exception {
		mockMvc.perform(get("/qna/insert")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/qna/insert")
				.with(anonymous()))
                .andExpect(status().is3xxRedirection());
	}

}
