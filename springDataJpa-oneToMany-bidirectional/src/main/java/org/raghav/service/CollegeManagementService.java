package org.raghav.service;

import java.util.Set;

import org.raghav.entity.Guide;
import org.raghav.entity.Student;
import org.raghav.repository.GuideRepository;
import org.raghav.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CollegeManagementService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private GuideRepository guideRepository;

	@Transactional
	public void createType1() {
		Guide guide1 = new Guide("2000MO10789", "Mike Lawson", 1000);
		Guide guide2 = new Guide("2000IM10901", "Ian Lamb", 2000);

		Student student1 = new Student("2014JT50123", "John Smith");
		Student student2 = new Student("2014AL50456", "Amy Gill");

		student1.setGuide(guide1);
		student2.setGuide(guide1);

		guide1.getStudents().add(student1);
		guide1.getStudents().add(student2);

		guideRepository.save(guide1);
		guideRepository.save(guide2);
	}

	@Transactional
	public void findType1() {
		Guide guide = guideRepository.findById(1L).get();
		System.out.println("guide= " + guide);
		Set<Student> students = guide.getStudents();
		int numberOfStudents = students.size();// contains // isEmpty() all loaded data from database
		System.out.println("numberOfStudents= " + numberOfStudents);
	}
	
	//Failed to update database
	// Never do this
	@Transactional
	public void updatingAGuide1() {
        Guide guide2 = guideRepository.findById( 2L ).get();  
        
        Student student2 = studentRepository.findById( 2L ).get();          
        
        // fail to update in database
        guide2.getStudents().add(student2);
        
		System.out.println("\n === guide2 still having no students inverse-end fails in update ==>>>>\n");
        guide2.getStudents().forEach(student -> System.out.println("student= " + student));
        
        // update happen successfully 
        guide2.setSalary(4700);
	}
	
	@Transactional
	public void updatingAGuide2() {
        Guide guide2 = guideRepository.findById( 2L ).get();  
        
        Student student2 = studentRepository.findById( 2L ).get();          
      
        // working fine 
        // because owner of the relationship
        // student2 guide_id updated from 1 to 2
        student2.setGuide(guide2);
	}
	
	@Transactional
	public void updatingAGuide3() {
        Guide guide2 = guideRepository.findById( 2L ).get();  
        
        Student student2 = studentRepository.findById( 2L ).get();    
        // Helper method Define in guide class
        // Fires update query
        // now student2 will map guide 2
        // guide_id is update from 1 to 2
        guide2.addStudent(student2);
        
        guide2.getStudents().forEach(student -> System.out.println("student= " + student));
        
	}
	
	@Transactional
	public void clearAllStudents() {
        Guide guide1 = guideRepository.findById( 2l ).get();  
        // Helper method Define in guide class
        // Fires delete query automatically
      
        guide1.removeAllStudents();
	}
	
}
