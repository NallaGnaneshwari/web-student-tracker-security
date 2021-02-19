package com.nalla.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nalla.springdemo.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	//need to inject the Session Factory, since DAO uses session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Student> getStudents() {
		
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//create a query
		//get the data, also order by lastname
		Query<Student> theQuery = currentSession.createQuery("from Student order by lastName", Student.class);
		
		//execute query and get result list
		List<Student> students= theQuery.getResultList();
		
		//return the results
		return students;
		
	}


	@Override
	public void saveStudent(Student theStudent) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//save/Update the student 
		currentSession.saveOrUpdate(theStudent);
		
	}


	@Override
	public Student getStudent(int theId) {
		//get the current Hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//read the  student from the database using primary key
		Student theStudent= currentSession.get(Student.class, theId);
		
		return theStudent;
	}


	@Override
	public void deleteStudent(int theId) {
	//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
	//delete the student with primary key
		Query theQuery=currentSession.createQuery("delete from Student where id=:studentId");
		theQuery.setParameter("studentId", theId);
		theQuery.executeUpdate();
	}


	@Override
	public List<Student> searchStudents(String theSearchName) {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
			
		Query theQuery = null;
        
		// only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) 
        {
        	 theQuery =currentSession.createQuery("from Student where lower(firstName) like :theName or lower(lastName) like :theName", Student.class);
             theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else
        {
            // theSearchName is empty ... so just get all students
            theQuery =currentSession.createQuery("from Student", Student.class);            
        }
        
        // execute query and get result list
        List<Student> students = theQuery.getResultList();
                
        // return the results        
        return students;
	
	}

}
