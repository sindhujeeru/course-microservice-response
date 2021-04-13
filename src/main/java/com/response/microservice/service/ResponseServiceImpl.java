package com.response.microservice.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.response.microservice.entity.Response;
import com.response.microservice.repository.ResponseRepository;

@Service
public class ResponseServiceImpl implements ResponseService {
	
	@Autowired
	private ResponseRepository responseRepository;
		
	@Override
	public Iterable<Response> saveAll(Iterable<Response> responses) {
		return responseRepository.saveAll(responses);
	}

	@Override
	@Transactional
	public Iterable<Response> findResponseByStudentByExam(Long studentId, Long examId) {
		
		/*
		  Exam exam = exClient.getExamById(examId); 
		  List<Question> questions = exam.getQuestions(); 
		  List<Long> questionIds = questions
				  .stream() .map(q ->q.getId())
				  .collect(Collectors.toList()); 
		  List<Response> responses =(List<Response>)responseRepository.findResponseByStudentByQuestionIds(studentId,questionIds);
		  
		  responses = responses
				  	.stream()
				  	.map(r -> { questions.forEach(q -> {
					  if(q.getId() == r.getQuestionId()){
						  r.setQuestion(q); 
						  } 
					  }); 
				  return r;
				 }).collect(Collectors.toList()); 
		 */
		
		List<Response> responses2 =(List<Response>)responseRepository.findResponsesByStudentByExam(studentId, examId);
		return responses2;
	}

	@Override
	@Transactional
	public Iterable<Long> findExamIdsWithResponsesByStudent(Long studentId) {
		
		/*
		  List<Response> responsesOfStudent = (List<Response>)responseRepository.findByStudentId(studentId); 
		  List<Long> examids = Collections.emptyList(); 
		  
		  if(responsesOfStudent.size()>0) { 
			  List<Long> questionIds = responsesOfStudent .stream() .map(r ->
			  r.getQuestionId()).collect(Collectors.toList()); examids =
			  exClient.getExamIdsByQuestionIdsResponseValues(questionIds) ; 
		  } 
		  */
			
		List<Response> responseStudent = (List<Response>)responseRepository.findExamIdsWithResponsesByStudent(studentId); 
		 
		List<Long> examids = responseStudent.stream().map(rs ->
			 rs.getQuestion()
			 .getExam()
			 .getId())
			 .distinct()
			 .collect(Collectors.toList()); 
			 
		return examids;
	}

	@Override
	@Transactional
	public Iterable<Response> findByStudentId(Long studentId) {
		return responseRepository.findByStudentId(studentId);
	}

}
