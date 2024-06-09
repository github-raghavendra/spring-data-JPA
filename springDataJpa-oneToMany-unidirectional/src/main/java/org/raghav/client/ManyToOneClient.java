package org.raghav.client;

import org.raghav.service.CollegeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ManyToOneClient implements ApplicationRunner {
	
	@Autowired
	private CollegeManagementService service;

	@Override
	public void run(ApplicationArguments args) throws Exception {	
		
		//persisting
		service.createType();
		
		//updating
		service.updateType();
		
		//finding
		service.fetchType();
		
	}	

}