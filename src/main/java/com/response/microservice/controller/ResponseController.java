package com.response.microservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.response.microservice.entity.Response;
import com.response.microservice.service.ResponseService;

@RestController
@RequestMapping("/api/responses")
public class ResponseController {
	
	@Autowired
	private ResponseService responseService;
	
	@PostMapping
	public ResponseEntity<?> createResponse(@RequestBody Iterable<Response> responses){
		responses = ((List<Response>)responses).stream().map(r ->{
			r.setStudentId(r.getStudent().getId());
			r.setQuestionId(r.getQuestion().getId());
			return r;
		}).collect(Collectors.toList());
		
		Iterable<Response> responseDb = responseService.saveAll(responses);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(responseService.saveAll(responseDb));
	}
	
	@GetMapping("/student/{studId}/exam/{examId}")
	public ResponseEntity<?> getRespByStudAndExam(@PathVariable("studId") Long studId, @PathVariable("examId") Long examId){
		Iterable<Response> responssIterable = responseService.findResponseByStudentByExam(studId, examId);
		return ResponseEntity.ok(responssIterable);
	}
	
	@GetMapping("/student/{id}/exam-responses")
	@Transactional
	public ResponseEntity<?> getExamIdsWithResponseOfStudents(@PathVariable("id") Long studentId){
		Iterable<Long> examIdsIterable = responseService.findExamIdsWithResponsesByStudent(studentId);
		
		return ResponseEntity.ok(examIdsIterable);
		
	}

}
