package com.expeditors.training.course3demo.routing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.expeditors.training.course3demo.enums.Status;
import com.expeditors.training.course3demo.model.Container;

public class PortTimeGraphTest {

	@Test
	public void testTwoPortBuildTimeGraph() {
		List<Container> containers = new ArrayList<>();
		
		containers.add( new Container( "1", 10.0, "A", "A", Status.READY, 1.0, 1, 2 ));
		containers.add( new Container( "2", 10.0, "A", "B", Status.READY, 3.0, 1, 2 ));
		containers.add( new Container( "3", 10.0, "A", "B", Status.READY, 2.0, 1, 3 ));
		containers.add( new Container( "4", 10.0, "A", "B", Status.READY, 1.0, 2, 3 ));
		containers.add( new Container( "5", 10.0, "B", "B", Status.READY, 1.0, 2, 3 ));

		List<Port> result = Utilities.buildPortTimeGraph(containers, 10);
		
		System.out.println( result );
		
		assertEquals( 4, result.size() );
	}
	
	@Test
	public void testTwoPortBuildTimeGraphTimeGap() {
		List<Container> containers = new ArrayList<>();
		
		containers.add( new Container( "1", 10.0, "A", "A", Status.READY, 1.0, 1, 3 ));
		containers.add( new Container( "2", 10.0, "A", "B", Status.READY, 3.0, 1, 2 ));
		containers.add( new Container( "3", 10.0, "A", "B", Status.READY, 2.0, 3, 4 ));
		containers.add( new Container( "4", 10.0, "B", "B", Status.READY, 1.0, 2, 3 ));
		containers.add( new Container( "5", 10.0, "B", "B", Status.READY, 1.0, 3, 4 ));

		List<Port> result = Utilities.buildPortTimeGraph(containers, 10);
		
		System.out.println( result );
		
		assertEquals( 5, result.size() );
	}
	
}
