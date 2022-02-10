package kakao.adm.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kakao.adm.qna.service.AdmQnaService;
import kakao.qna.dto.QnaDto;
import kakao.util.StringUtil;

@Controller
public class AdmQnaController {

        @Autowired
        private AdmQnaService admQnaService;
        
        final String suffix = "adm/qna";

        @GetMapping(suffix+"/list")
        public ModelAndView qnalistPage(QnaDto qnaDto) throws Exception {
        	
        	List<QnaDto> qnaList = admQnaService.list(qnaDto);
        	
            ModelAndView mv = new ModelAndView(suffix+"/qnaList");
            mv.addObject("qnaList", qnaList);

            return mv;
        }
        
        @GetMapping(suffix+"/view")
        public ModelAndView qnaViewPage(QnaDto qnaDto) throws Exception {
        	
        	QnaDto qnaData = admQnaService.data(qnaDto);
        	
        	ModelAndView mv = new ModelAndView(suffix+"/qnaView");
        	mv.addObject("qnaData", qnaData);
            
            return mv;
        }
        
        @GetMapping(suffix+"/insert")
        public ModelAndView qnainsertPage(QnaDto qnaDto) throws Exception {
        	
        	QnaDto qnaData = admQnaService.data(qnaDto);
        	
        	ModelAndView mv = new ModelAndView(suffix+"/qnaUpdate");
            mv.addObject("qnaData", qnaData);
            
            return mv;
        }
        
        @PostMapping(suffix+"/insert")
        public String qnaInsert(QnaDto qnaDto) throws Exception {
        	System.out.println(qnaDto.getQueContent());
        	int result = 0;
        	
        	if(!StringUtil.isEmpty(qnaDto.getSeq())) {
        		result = admQnaService.update(qnaDto);
        	}
        	
        	System.out.println("result : " + result);
            return "redirect:/qna/list";
        }
}