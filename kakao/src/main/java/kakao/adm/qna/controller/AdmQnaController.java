package kakao.adm.qna.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kakao.adm.qna.dto.AdmQnaDto;
import kakao.adm.qna.service.AdmQnaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AdmQnaController {

		final private AdmQnaService admQnaService;
        
        final String suffix = "adm/qna";

        @GetMapping(suffix+"/list")
        public ModelAndView qnalistPage(@AuthenticationPrincipal User userInfo, AdmQnaDto admQnaDto) throws Exception {
        	
        	List<AdmQnaDto> qnaList = admQnaService.list(userInfo.getUsername());
        	
            ModelAndView mv = new ModelAndView(suffix+"/admQnaList");
            mv.addObject("qnaList", qnaList);

            return mv;
        }
        
        @GetMapping(suffix+"/view")
        public ModelAndView qnaViewPage(AdmQnaDto admQnaDto) throws Exception {
        	
        	AdmQnaDto qnaData = admQnaService.data(admQnaDto);
        	
        	ModelAndView mv = new ModelAndView(suffix+"/admQnaView");
        	mv.addObject("qnaData", qnaData);
            
            return mv;
        }
        
        @GetMapping(suffix+"/insert")
        public ModelAndView qnainsertPage(@AuthenticationPrincipal User userInfo, AdmQnaDto admQnaDto) throws Exception {
        	
        	AdmQnaDto qnaData = admQnaService.data(admQnaDto);
        	
        	ModelAndView mv = new ModelAndView(suffix+"/admQnaInsert");
            mv.addObject("qnaData", qnaData);
            
            return mv;
        }
        
        @PostMapping(suffix+"/insert")
        public String qnaInsert(@AuthenticationPrincipal User userInfo, AdmQnaDto admQnaDto) throws Exception {
        	
        	//?????????????????????ID
        	admQnaDto.setUpUserId(userInfo.getUsername());
        	
        	admQnaService.insert(admQnaDto);
        	
            return "redirect:/adm/qna/view?seq="+admQnaDto.getSeq();
        }
        
        @PostMapping(suffix+"/updateToStatus")
        @ResponseBody
        public Map<String,Object> updateToStatus(@AuthenticationPrincipal User userInfo, @RequestParam String seq, @RequestParam String status) throws Exception {
        	
        	Map<String,Object> resultMap = new HashMap<String, Object>();
        	
        	int result = 1;
        	
        	AdmQnaDto qnaData = admQnaService.data(seq);
        	if(qnaData.getStatus().equals("100")) {
        		//?????????????????????ID
            	qnaData.setUpUserId(userInfo.getUsername());
            	qnaData.setStatus(status);
        		resultMap.put("data", admQnaService.insert(qnaData));
        	}else {
        		result = 0;
        	}
        	
        	resultMap.put("result", result);
    		
    		return resultMap;
        }
}