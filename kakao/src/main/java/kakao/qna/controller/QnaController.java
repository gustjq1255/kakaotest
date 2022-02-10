package kakao.qna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kakao.qna.dto.QnaDto;
import kakao.qna.entity.Qna;
import kakao.qna.service.QnaService;
import kakao.util.StringUtil;

@Controller
public class QnaController {

        @Autowired
        private QnaService qnaService;
        
        final String suffix = "qna";

        @GetMapping(suffix+"/list")
        public ModelAndView qnalistPage(QnaDto qnaDto) throws Exception {
        	
//        	int i = 10/0;
        	qnaDto.setQueEmail("aa");
        	List<QnaDto> qnaList = qnaService.list(qnaDto);
        	
            ModelAndView mv = new ModelAndView(suffix+"/qnaList");
            mv.addObject("qnaList", qnaList);

            return mv;
        }
        
        @GetMapping(suffix+"/view")
        public ModelAndView qnaViewPage(QnaDto qnaDto) throws Exception {
        	
        	QnaDto qnaData = qnaService.data(qnaDto);
        	
        	ModelAndView mv = new ModelAndView(suffix+"/qnaView");
        	mv.addObject("qnaData", qnaData);
            
            return mv;
        }
        
        @GetMapping(suffix+"/insert")
        public ModelAndView qnainsertPage(QnaDto qnaDto) throws Exception {
        	
        	QnaDto qnaData = qnaService.data(qnaDto);
        	
        	ModelAndView mv = new ModelAndView(suffix+"/qnaUpdate");
            mv.addObject("qnaData", qnaData);
            
            return mv;
        }
        
        @PostMapping(suffix+"/insert")
        public String qnaInsert(QnaDto qnaDto) throws Exception {
        	System.out.println(qnaDto.getQueContent());
        	int result = 0;
        	
        	qnaService.insert(qnaDto);
        	
        	System.out.println("result : " + result);
            return "redirect:/qna/list";
        }
}