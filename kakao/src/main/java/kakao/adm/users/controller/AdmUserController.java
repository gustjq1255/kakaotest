package kakao.adm.users.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kakao.adm.users.dto.AdmUserDto;
import kakao.adm.users.service.AdmUserService;
import kakao.util.StringUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdmUserController {
	
	private final AdmUserService admUserService;
	
	private static final Logger log = LoggerFactory.getLogger(AdmUserController.class);
	
	@GetMapping("/adm/user/list")
    public ModelAndView admUserListPage(@AuthenticationPrincipal User userInfo, AdmUserDto admUserDto) throws Exception {
    	
		System.out.println(userInfo);
    	List<AdmUserDto> userList = admUserService.list(admUserDto);
    	
        ModelAndView mv = new ModelAndView("/adm/user/admUserList");
        mv.addObject("userList", userList);

        return mv;
    }
	
	@GetMapping("/adm/user/view")
    public ModelAndView admUserViewPage(@AuthenticationPrincipal User userInfo, AdmUserDto admUserDto) throws Exception {
    	
		AdmUserDto userData = admUserService.data(admUserDto);
    	
    	ModelAndView mv = new ModelAndView("/adm/user/admUserView");
    	mv.addObject("userData", userData);
        
        return mv;
    }
	
	@GetMapping("/adm/user/insert")
	public ModelAndView admUserInsertPage(AdmUserDto admUserDto) throws Exception {
		AdmUserDto userData = admUserService.data(admUserDto);
    	
		ModelAndView mv = new ModelAndView("/adm/user/admUserInsert");
		mv.addObject("userData", userData);
		
		return mv;
	}
	
	@PostMapping("/adm/user/insert")
	public String admUserInsert(@AuthenticationPrincipal User userInfo, AdmUserDto admUserDto) {
		
		admUserService.save(admUserDto);
		return "redirect:/adm/user/list";
	}
	
	@PostMapping("/adm/user/update")
	public String admUserUpdate(@AuthenticationPrincipal User userInfo, AdmUserDto admUserDto) {
		
		//비밀번호가 없이다면 update쿼리
		if(StringUtil.isEmpty(admUserDto.getPwd())) {
			admUserService.updateUserNotPwd(admUserDto);
		}else {
			admUserService.save(admUserDto);
		}
		return "redirect:/adm/user/list";
	}
}