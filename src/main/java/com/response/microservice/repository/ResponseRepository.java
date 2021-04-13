package com.response.microservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.response.microservice.entity.Response;

public interface ResponseRepository extends MongoRepository<Response, String> {
	
	/*
	 * @Query("select r from Response r join fetch r.question q join fetch q.exam e where r.studentId=?1 and e.id=?2"
	 * ) public Iterable<Response> findResponseByStudentByExam(Long studentId,Long
	 * examId);
	 * 
	 * @Query("select e.id from Response r join r.question q join q.exam e where r.studentId=?1 group by e.id"
	 * ) public Iterable<Long> findExamIdsWithResponsesByStudent(Long studentId);
	 */
	
	@Query("{'studentId':?0, 'questionId':{$in: ?1}}")
	public Iterable<Response> findResponseByStudentByQuestionIds(Long studentId, Iterable<Long> questionId);
	
	@Query("{'studentId':?0}")
	public Iterable<Response> findByStudentId(Long studentId);
	
	@Query("{'studentId': ?0, 'question.exam.id': ?1}")
	public Iterable<Response> findResponsesByStudentByExam(Long studentId, Long examId);
	
	@Query(value = "{'studentId': ?0}",fields = "{'question.exam.id':1}")
	public Iterable<Response> findExamIdsWithResponsesByStudent(Long studentId);

}
