package org.zerock.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import com.fasterxml.jackson.databind.util.BeanUtil;

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
		/*
		 * @GetMapping("/ex03") public String ex03Array(String[] ids) {
		 * log.info("array ids: " + Arrays.toString(ids));
		 * 
		 * return "ex03Array"; }
		 */
		
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
		
		// 137쪽
		/*
		 * @InitBinder public void initBinder(WebDataBinder binder) { SimpleDateFormat
		 * dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 * binder.registerCustomEditor(java.util.Date.class, new
		 * CustomDateEditor(dateFormat, false)); }
		 */
		
		// 137 page
		// /sample/ex03?title=test&dueDate=2018-01-01 주석처리한것처럼 처리할때
		// /sample/ex03?title=test&dueDate=2018/01/01 TodoDTO에 패턴을 정해줬을때
		@GetMapping("/ex03")
		public String ex03(TodoDTO todo) {
			log.info("todo: " + todo);
			return "ex03";
		}
		
//		@GetMapping("/ex04")
//		public String ex04(SampleDTO dto, int page) { // int 타입인 page는 전달되지않음
//			log.info("dto: " + dto);
//			log.info("page: " + page);
//			
//			return "/sample/ex04";
//		}
		
		@GetMapping("/ex04")
		public String ex04(SampleDTO dto, @ModelAttribute("page") int page) { // 그러므로
			log.info("dto: " + dto);							// @ModelAttribute("page") int page 이런식으로
			log.info("page: " + page);							// 써주어야 값이 넘어감
			
			return "/sample/ex04";
		}
		
		@GetMapping("/ex06")
		public @ResponseBody SampleDTO ex06() {
			log.info("/ex06..........");
			
			SampleDTO dto = new SampleDTO();
			dto.setName("장민주");
			dto.setAge(26);
			
			return dto;
		}
		
		@GetMapping("/ex07") 
		public ResponseEntity<String> ex07() {
			log.info("/ex07.............");
			
			// {"name" : "홍길동"}
			String msg = "{\"name\" : \"홍길동\"}";
			
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "application/json;charset=UTF-8"); // 한글이 안깨지게 해줌.
			
			return new ResponseEntity<String>(msg, header, HttpStatus.OK);
		}
		
		@GetMapping("exUpload")
		public void exUpload() {
			log.info("/exUpload..............");
		}
		
		@PostMapping("/exUploadPost")
		public void exUploadPost(ArrayList<MultipartFile> files, Model model) {
			log.info("exUploadPost");
			
//			files.forEach(file -> {
//				log.info("------------------------");
//				log.info("name:" +file.getOriginalFilename());
//				log.info("size:" + file.getSize());
//			});
			
			String uploadFolder = "C:\\upload";
			for (MultipartFile multipartFile : files) {
				
				log.info("----------------------");
				log.info("Upload File Name: " + multipartFile.getOriginalFilename());
				log.info("Upload File size: " + multipartFile.getSize());
				
				File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
				
				try {
					
					multipartFile.transferTo(saveFile);
				} catch (Exception e) {
					log.error(e.getMessage());
				} //end catch
				
			} //end for
		}
		
}









