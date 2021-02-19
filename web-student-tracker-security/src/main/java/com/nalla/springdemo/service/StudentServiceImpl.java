package com.nalla.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nalla.springdemo.dao.StudentDAO;
import com.nalla.springdemo.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {


	//inject Student DAO, since service depends on DAO
	@Autowired
	private StudentDAO studentDAO;
	
	@Override
	@Transactional
	public List<Student> getStudents() {
	return studentDAO.getStudents();
	}

	@Override
	@Transactional
	public void saveStudent(Student theStudent) {
		studentDAO.saveStudent(theStudent);
	}

	@Override
	@Transactional
	public Student getStudent(int theId) {
		return studentDAO.getStudent(theId);
	}

	@Override
	@Transactional
	public void deleteStudent(int theId) {
		studentDAO.deleteStudent(theId);
		
	}

	@Override
	@Transactional
	public List<Student> searchStudents(String theSearchName) {
		return studentDAO.searchStudents(theSearchName);
		
	}

}


//Remove @Transactional from DAO, it is efficient if we run a trasaction at Service level