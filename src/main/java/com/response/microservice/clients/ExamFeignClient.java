package com.response.microservice.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.exam.microservice.entity.Exam;

@FeignClient(name = "exam-microservice")
public interface ExamFeignClient {
	
	@GetMapping("api/exams/{id}")
	public Exam getExamById(@PathVariable("id") Long id);
	
	@GetMapping("api/exams/response-values-for-questions")
	public List<Long> getExamIdsByQuestionIdsResponseValues(@RequestParam List<Long> questionIds);

}
