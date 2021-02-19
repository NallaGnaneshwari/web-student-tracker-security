package com.nalla.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nalla.springdemo.entity.Student;
import com.nalla.springdemo.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	//inject the DAO into the controller
	//	@Autowired
	//	private StudentDAO studentDAO;
	
	
	//inject the Service into the controller
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/list")
	public String listStudents(Model theModel) {
		
		//get the studentss from the service
		List<Student> theStudents = studentService.getStudents();
		
		//add the students to the model
		theModel.addAttribute("students",theStudents);
		
		return "list-students";
	}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		
		//create Model attribute to bind form data
		Student  theStudent = new Student();
		theModel.addAttribute("student",theStudent);
		
		return "student-form";
	}
	
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student theStudent){
		
		//save the student using Service 
		studentService.saveStudent(theStudent);
		
		return "redirect:/student/list";
	}
	
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId")int theId, Model theModel) {
		
		//get the student from the service (DAo, DB)
		Student theStudent = studentService.getStudent(theId);
		
		//set student as a model attribute to pre-populate the form
		theModel.addAttribute("student",theStudent);
		
		//send over to the form
		return "student-form";
		
	}
	
	
	@GetMapping("/delete")
	public String deleteStudent(@RequestParam("studentId")int theId) {
		
		//delete the student
		studentService.deleteStudent(theId);
		
		return "redirect:/student/list";
	}
	
	
	@GetMapping("/search")
	public String searchStudents(@RequestParam("theSearchName")String theSearchName, Model theModel)
	{
		//search students from the studentService
		List<Student> theStudents = studentService.searchStudents(theSearchName);
		
		//add the students to the model attribute
		theModel.addAttribute("students", theStudents);
		
		return "list-students";
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
