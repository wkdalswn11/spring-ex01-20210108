package org.zerock.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/model/*")
@Log4j
public class ModelController {
	
	@RequestMapping("/ex1")
	public void method1(Model model) {
		log.info("method1");
		
//		request.setAttribute("abc", abc);	// 이렇게 request 영역에 담을 필요없이
		model.addAttribute("name", "java"); // name에 java를 담아 view 로 보냄 
											// view에서는 ${name} 이렇게 받아 쓰면 됨.
	}
	
	@RequestMapping("/ex2")
	public void method2(@ModelAttribute("name") String name) { // @ModelAttribute("name") 이 어노테이션을 붙이면
															   //  아래 주석처리한 부분을 대신해줌 뒤에
															   //  Model model 를 써줄필요가없어짐.
		log.info("method2");
		
//		model.addAttribute("name", name);
	}
	@RequestMapping("/ex3")
	public void method3(@ModelAttribute("name") String name,@ModelAttribute("age") int age) {
		log.info("method3");
		log.info(name);
		log.info(age);
	}
	
	@RequestMapping("/ex03")
	public void method03(@ModelAttribute("member") Member m ) {
		log.info("method03");
		m.setAge(26);
		m.setName("장민주");
		log.info(m.getAge());
		log.info(m.getName());
		
	}
	@RequestMapping("/ex4")
	public void method4(@ModelAttribute("member") Member member) {
		log.info("method4");
		log.info(member);
		
//		model.addAttribute("member", member);
	}
	
	@RequestMapping("/ex5")
	// model attribute의 이름은 소문자로 바꾼 type명으로 결정됨 (선생님 주석)
	public void method5(@ModelAttribute Member member) { // ("member") 이걸 생략하면 Member라는 타입명을 따라감
		log.info("method5");
		log.info(member);
		
	}
	
	@RequestMapping("/ex6")
	// model attribute의 이름은 소문자로 바꾼 type명으로 결정됨 (선생님 주석)
	public String method6(Member member) { // @ModelAttribute 를 생략해도 성립됨.
//		member.setAge(26);				   // 보충설명 : Member는 Bean 이기때문에 생략가능한것임.
//		member.setName("장민주");
		log.info("method6");
		log.info(member);
		
		
		return "model/ex4";
	}
	
	@RequestMapping("/ex7")
	public String method7(Model model, HttpSession session, RedirectAttributes rattr) {
		log.info("method7");
		model.addAttribute("myAttr1", "myValue1");
		session.setAttribute("myAttr2", "myValue2");
		rattr.addFlashAttribute("myAttr3", "myValue3");
		//    -----------------
		//    이걸 쓰면 redirect 보내고 한번쓰고 사라짐.
		return "redirect:ex8";
	}
	
	@RequestMapping("/ex8")
	public String method8(Model model) {
		log.info("method8");
		Map<String, Object> map = model.asMap();
		log.info(map.get("myAttr1")); // request 영역이므로 redirect로 보낼수가 없음 그래서 안보임.
		log.info(map.get("myAttr2")); // session영역에 있으므로 model에선 보이지않음.
		log.info(map.get("myAttr3")); // session 이나 request영역이아니라 model객체에 존재함.
		
		
		return "redirectex1";
	}
	
	@RequestMapping("/ex9")
	public String method9(RedirectAttributes rattr) {
		log.info("method9");
		
		rattr.addFlashAttribute("name", "jangminju");
		rattr.addFlashAttribute("age", "26");
		return "redirect:ex10";
	}
	
	@RequestMapping("/ex10")
	public String method10(Model model) {
		log.info("method10");
		
		model.addAttribute("name");
		model.addAttribute("age");
		
		return "redirectex1";
	}
}
















