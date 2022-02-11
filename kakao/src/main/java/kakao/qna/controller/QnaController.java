package kakao.qna.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kakao.qna.dto.QnaDto;
import kakao.qna.service.QnaService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QnaController {

        private final QnaService qnaService;
        
        final String suffix = "qna";
        
        @GetMapping(suffix+"/index")
        public ModelAndView qnaIndexPage(QnaDto qnaDto) throws Exception {
        	
        	qnaDto.setQueEmail("gustjq1255@naver.com");
        	List<QnaDto> qnaList = qnaService.list(qnaDto);
        	
            ModelAndView mv = new ModelAndView(suffix+"/qnaIndex");
            mv.addObject("qnaList", qnaList);

            return mv;
        }
        
        @GetMapping(suffix+"/list")
        public ModelAndView qnalistPage(QnaDto qnaDto) throws Exception {
        	
        	qnaDto.setQueEmail("gustjq1255@naver.com");
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
        	
        	ModelAndView mv = new ModelAndView(suffix+"/qnaInsert");
        	
        	System.out.println("aaa seq : " + qnaData.getSeq());
        	QnaDto bb = new QnaDto();
        	System.out.println("bbbb seq : " + bb.getSeq());
            mv.addObject("qnaData", qnaData);
            
            return mv;
        }
        
        @PostMapping(suffix+"/insert")
        public String qnaInsert(QnaDto qnaDto) throws Exception {
        	System.out.println("aaaaaa");
        	System.out.println(qnaDto.getQueContent());
        	int result = 0;
        	
        	qnaService.insert(qnaDto);
        	
        	System.out.println("result : " + result);
            return "redirect:/qna/list";
        }
}