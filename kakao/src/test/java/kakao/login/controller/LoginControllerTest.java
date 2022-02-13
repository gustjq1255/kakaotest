package kakao.login.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
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
class LoginControllerTest {
	
	@Autowired
    MockMvc mockMvc;
	
	@Test
	@DisplayName("로그인 로그인 화면 : 누구나 접근가능")
	void testGoLogin() throws Exception {
		mockMvc.perform(get("/login/login")
				.with(anonymous()))
				.andExpect(status().isOk());
    }

	@Test
	@DisplayName("메인 화면 : 누구나 접근가능")
	void testLogoutPage() throws Exception {
		mockMvc.perform(get("/")
				.with(anonymous()))
				.andExpect(status().isOk());
	}
	
	@Test
	@DisplayName("로그인 회원가입 화면 : 누구나 접근가능")
	void testJoinPage() throws Exception {
		mockMvc.perform(get("/login/join")
				.with(anonymous()))
				.andExpect(status().isOk());
	}

}
