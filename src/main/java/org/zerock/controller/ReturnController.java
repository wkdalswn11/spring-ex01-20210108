package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/return/*")
@Log4j
public class ReturnController {
	
	@RequestMapping("/ex1")
	public String method1() {
		log.info("method1");
		return "returnView1"; // String 으로 리턴할때 우리가 보여주고싶은 view 즉 jsp 화면을 리턴함.	
							  // servlet-context.xml 에  정의 되어있음 prefix , suffix 때문에 경로가
							  // /WEB-INF/views/returnView1.jsp 이런식으로 찾아짐
	}
	
	@RequestMapping("/ex2")
	public String method2() {
		log.info("method2");
		
		// 일을 마치면
		// forward or redirect를 함.   forward 시키는 예는 method1() 임.
		return "redirect:/sample/";  // redirect 시키는 방법. 여기선 /sample/ 로 redirect 시킴.
									 // redirect:/보낼경로 
	}
	
	@RequestMapping("/ex3") 
		public @ResponseBody String method3() { // @ResponseBody 를 쓸경우 return의 "" 내용이 text 그대로 출력
			log.info("method3");
			
			return "returnValue3 hello world~!!!";
		}
	
	@RequestMapping("/ex4")
	public void method4() {		// method의 타입을 void로 할경우 @RequestMapping("/ex4") 즉 요청 경로 자체가
		log.info("method4");	// jsp가 됨.
		
	}
	
	@RequestMapping("/ex5")
	public @ResponseBody Member method5() {
		log.info("method5");
		
		Member member = new Member();
		member.setName("jangminju");
		member.setAge(26);
		
		// {"name":"jangminju", "age":26} jason방식
		
		return member;
		
	}
	
}
