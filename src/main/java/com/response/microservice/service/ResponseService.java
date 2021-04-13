package com.response.microservice.service;

import com.response.microservice.entity.Response;

public interface ResponseService {
	
	public Iterable<Response> saveAll(Iterable<Response> responses);
	
	public Iterable<Response> findResponseByStudentByExam(Long studentId,Long examId);

	public Iterable<Long> findExamIdsWithResponsesByStudent(Long studentId);
	
	public Iterable<Response> findByStudentId(Long studentId);
}
