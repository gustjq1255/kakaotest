package kakao.login.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kakao.login.dto.LoginDto;
import kakao.login.service.LoginService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class LoginController {
	
	private final LoginService loginService;
	
	@GetMapping("/login/login")
	public ModelAndView goLogin(Model model,
			@RequestParam(value = "error", required = false) String error, 
			@RequestParam(value = "exception", required = false) String exception) {
		
		ModelAndView mv = new ModelAndView("login/login");
        mv.addObject("error", error);
		mv.addObject("exception", exception);
		
		return mv;
	}
	
	@GetMapping(value = "/login/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
		return "redirect:/";
	}
	
	@GetMapping("/login/join")
	public ModelAndView joinPage(LoginDto loginDto) throws Exception {
    	
		ModelAndView mv = new ModelAndView("/login/join");
		
		return mv;
	}
	
	@PostMapping("/login/join")
	public ModelAndView join(LoginDto loginDto) {
		
		loginService.save(loginDto);
		
		ModelAndView mv = new ModelAndView("/login/joinComplete");
		
		return mv;
	}
	
	@GetMapping("/login/checkUserId")
	@ResponseBody
	public Map<String,Object> joinCheckUserId(@RequestParam String userId) throws Exception {
    	
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	
		resultMap.put("userCount", loginService.countUserId(userId));
		
		
		return resultMap;
	}
        
}