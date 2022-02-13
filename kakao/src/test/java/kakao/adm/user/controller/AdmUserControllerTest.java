package kakao.adm.user.controller;

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
class AdmUserControllerTest {
	
	@Autowired
    MockMvc mockMvc;
	
	@Test
	@DisplayName("관리자 회원관리 목록화면 : 관리자접근가능, 사용자 접근 불가")
	void testAdmUserListPage() throws Exception {
		mockMvc.perform(get("/adm/user/list")
				.with(user("testadmin").roles("ADMIN")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/adm/user/list")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isForbidden());
    }
	
	@Test
	@DisplayName("관리자 회원관리 조회화면 : 관리자접근가능, 사용자 접근 불가")
	void testAdmUserViewPage() throws Exception {
		mockMvc.perform(get("/adm/user/view?userId=testadmin")
				.with(user("testadmin").roles("ADMIN")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/adm/user/view?userId=testuser")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isForbidden());
	}

	@Test
	@DisplayName("관리자 회원관리 수정화면 : 관리자접근가능, 사용자 접근 불가")
	void testAdmUserInsertPage() throws Exception {
		mockMvc.perform(get("/adm/user/insert?userId=testadmin")
				.with(user("testadmin").roles("ADMIN")))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/adm/user/insert?userId=testuser")
				.with(user("testuser").roles("USER")))
                .andExpect(status().isForbidden());
	}
	
}
