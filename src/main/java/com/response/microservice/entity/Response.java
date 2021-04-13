package com.response.microservice.entity;


import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.common.exam.microservice.entity.Question;
import com.common.microservice.users.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "responses")
public class Response {
	
	@Id
	private String id;
	
	private String answer;
	
	//@Transient
	private Student student;
	
	private Long studentId;
	
	//@Transient
	private Question question;
	
	private Long questionId;
	

}
