package org.zerock.controller;



import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/paramex/*") // ("paramex") 이렇게 /를 빼도됨 (상대경로).
@Log4j
public class ParameterController {
	
	@RequestMapping("/ex1")
	public void method1(HttpServletRequest request) { // 메소드에 파라미터로 이렇게 주고
		log.info(request);
		log.info(request.getParameter("name")); // 이런식으로 값을 성공적으로 받아올 수 있음
		log.info(request.getParameter("age"));  // 주소창에 get방식으로 줘서 실험함.
		log.info("method1");				    
	}
	
	@RequestMapping("/ex2")
	public void method2(@RequestParam("name") String n) { // name이라는 파라미터를 String타입의 n에 담을수있음.
		log.info("method2");
		log.info(n);									  // 그리고 그걸 log에 찍어봄.
	}
	
	@RequestMapping("/ex3")
	public void method3(@RequestParam String name) {  // 받아오는 파라미터의 이름과 담아쓰는 변수의 이름이같으면 
		log.info("method3");						  // 생략이 가능함.
		log.info(name);
	}
	
	@RequestMapping("/ex4")
	public void method4(String name) {  // @RequestParm까지 생략해도 request.Parameter에 맞는 이름이 있다면 
		log.info("method4");            // 가져올수있음.
		log.info(name);
	}
	@RequestMapping("/ex5")
	public void method5(HttpServletRequest request) {
		log.info("method5");
		log.info(request.getParameterValues("check"));
		
		String[] list = request.getParameterValues("check");
		for (String s : list) {
			log.info(s);
		}
	}
	
	@RequestMapping("/ex6")
	public void method6(String[] check) {	// 여러개의 값을 가져올경우 method5처럼 가져오는 방법도있지만
		log.info("method6");				// 애초에 메소드의 파라미터에 배열타입을 넣어서 가져오는 방법도 있다.
		for (String s : check) {			// 향상된 for문을 사용하여 값을 가져옴.
			log.info(s);
		}
	}
	// 출처 : https://docs.spring.io/spring-framework/docs/5.0.20.RELEASE/javadoc-api/org/springframework/beans/BeanUtils.html#isSimpleProperty-java.lang.Class-
	// 심플타입은  a primitive, a String or other CharSequence, a Number, a Date, a URI, a URL, a Locale, a Class, or a corresponding array.
	// 같은 타입을 말함. 
	@RequestMapping("/ex7")			
	public void method7(@RequestParam("check") ArrayList<String> check) { // Map list 와같은 심플타입이 아닌경우는 
		log.info("method7");											  // @RequestParam("check") 를 생략할수없음
		for (String c : check) {					
			log.info(c);
		}
	}
	
	@RequestMapping("/ex8")
	public void method8(HttpServletRequest request) {
		String name = request.getParameter("name");
		String ageStr = request.getParameter("age");
		int age = Integer.parseInt(ageStr);
		
		Member member = new Member();
		member.setName(name);
		member.setAge(age);
		
		log.info("method8");
		log.info(member);
	}
	// method8처럼 복잡하게 할필요없이  method9처럼 하면 간단하게 값을 불러올수있음.
	@RequestMapping("/ex9")
	public void method9(Member member) {
		log.info("method9");
		log.info(member);  // lobmok에서 toString 메소드를 자동생성해주기 때문에 
						   // get 메소드없이 값을 받아올수있음.
	}
	
	@InitBinder
	public void initBinder1() {
		log.info("initbinder1");
	}
}








