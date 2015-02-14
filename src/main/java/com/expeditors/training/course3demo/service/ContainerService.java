package com.expeditors.training.course3demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;


@Service
public class ContainerService {
	
	public List<Container> getAllContainers() {
		List<Container> result = new ArrayList<Container>();
		
		result.add( new Container( "Bob", 1.0, "jfk", "ams", Status.ARRIVED) );
		result.add( new Container( "Sue", 2.0, "jfk", "ams", Status.ARRIVED) );
		result.add( new Container( "Jim", 3.0, "jfk", "ams", Status.ARRIVED) );

		return result;
	}

}
