package org.raghav.service;

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
	public void createType() {
		Guide guide = new Guide("2000MO10789", "Mike Lawson", 1000);
		Student student = new Student("2014JT50123", "John Smith");
		student.setGuide(guide);
		//guideRepository.save( guide );
		studentRepository.save( student );
	}
	
	@Transactional
	public void updateType() {
		Student student = studentRepository.findById(1L).get();
		System.out.println(student);
		
		student.setName("raghavendra rai");
		
		student.getGuide().setSalary(45000);
		
		// load guide only when needed improve performance of the app
		//Guide guide = student.getGuide();
		
		//System.out.println("The Guide staffId is= " + guide.getStaffId());
		//guide.setSalary(3300); // also update happen into database after method ends
		
		//System.out.println("The Guide salary  is= " + guide.getSalary());
	}
	
	@Transactional
	public void fetchType() {
		Student student = studentRepository.findById(1L).get();
		System.out.println(student);
		
		// load guide only when needed improve performance of the app
		Guide guide = student.getGuide(); // still no guide object loaded
		//String staffId = guide.getStaffId(); // loaded now
		System.out.println("The Guide Id is= " + guide.getId());
	}

}
