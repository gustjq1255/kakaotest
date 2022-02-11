package kakao.adm.qna.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        public ModelAndView qnalistPage(AdmQnaDto admQnaDto) throws Exception {
        	
        	List<AdmQnaDto> qnaList = admQnaService.list(admQnaDto);
        	
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
        public ModelAndView qnainsertPage(AdmQnaDto admQnaDto) throws Exception {
        	
        	AdmQnaDto qnaData = admQnaService.data(admQnaDto);
        	
        	ModelAndView mv = new ModelAndView(suffix+"/admQnaInsert");
            mv.addObject("qnaData", qnaData);
            
            return mv;
        }
        
        @PostMapping(suffix+"/insert")
        public String qnaInsert(AdmQnaDto admQnaDto) throws Exception {
        	
        	admQnaService.insert(admQnaDto);
        	
            return "redirect:/adm/qna/list";
        }
}