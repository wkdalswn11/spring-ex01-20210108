package org.zerock.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;

import lombok.extern.log4j.Log4j;

@Controller // Controller 어노테이션 안에 Component 어노테이션이 포함돼있음
@RequestMapping("/sample/*") // 경로를 지정해주면 () 안에있는경로로 요청이올때 일을함.
@Log4j
public class SampleController {
	
		@RequestMapping("") // 실제로 일을하는건 메소드이므로 메소드에도 붙여준다 ""처럼 빈것을 줄경우 /sample/모든경로에서 일함
		public void basic() {
			log.info("basic......");
		}
		
		@RequestMapping(value = "/basic", method = {RequestMethod.GET, RequestMethod.POST})
		public void basicGet() {
			log.info("basic get....................");
		}
		
		@GetMapping("/basicOnlyGet")
		public void basicGet2() {
			log.info("basic get only get................");
		}
		
		@PostMapping("/basicOnlyPost")
		public void basicPost() {
			log.info("basic post only post...............");
		}
		
		@RequestMapping("/ex02")
		public String ex02(String name,int age) {
			log.info("name: " + name);
			log.info("age: " + age);
			
			return "ex02";
		}
		
		// 133 page
		@GetMapping("/ex02")
		public String ex02Array(@RequestParam("ids") String[] ids) {
			log.info("array ids: " + Arrays.toString(ids));
			
			return "ex02Array";
		}
		
		// 궁금해서 해봄 @RequestParam("ids") 생략해도 되는지
		@GetMapping("/ex03")
		public String ex03Array(String[] ids) {
			log.info("array ids: " + Arrays.toString(ids));
			
			return "ex03Array";
		}
		
		// 132 page 아래
		@GetMapping("/ex02List")
		public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
			log.info("ids: " + ids);
			
			return "ex02List";
		}
		
		// 130쪽
		@GetMapping("/ex01")
		public String ex01(SampleDTO dto) {
			log.info("" + dto);
			
			return "ex01";
		}
		
		// 134쪽
		// http://localhost:8080/sample/ex02Bean?list[0].name=aaa&list[1].name=bbb
		// http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb [ = %5B / ] = %5D
		@GetMapping("/ex02Bean")
		public String ex02Bean(SampleDTOList list) {
			log.info("list dtos: " + list);
			
			return "ex02Bean";
		}
}
