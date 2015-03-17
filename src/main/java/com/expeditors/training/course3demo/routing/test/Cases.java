package com.expeditors.training.course3demo.routing.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cases {

	public static Collection<Port> caseA() {
		List<Port> ports = new ArrayList<>();
		
		ports.add( new Port("A") );//0
		ports.add( new Port("B") );//1
		ports.add( new Port("C") );//2
		ports.add( new Port("D") );//3
		ports.add( new Port("Z") );//4

		//Port A
		Map<Port,Double> aCost = new HashMap<>();
		aCost.put( ports.get(1), new Double(2) );//B
		aCost.put( ports.get(3), new Double(5) );//D
		
		ports.get(0).setRoutes( aCost );

		//PortB
		Map<Port,Double> bCost = new HashMap<>();
		bCost.put( ports.get(0), new Double(2) );//A
		bCost.put( ports.get(2), new Double(2) );//C
		
		ports.get(1).setRoutes( bCost );
		
		//PortC
		Map<Port,Double> cCost = new HashMap<>();
		cCost.put( ports.get(1), new Double(2) );//B
		cCost.put( ports.get(4), new Double(2) );//Z
		
		ports.get(2).setRoutes( cCost );
		
		//PortD
		Map<Port,Double> dCost = new HashMap<>();
		dCost.put( ports.get(0), new Double(5) );//A
		dCost.put( ports.get(4), new Double(5) );//Z
		
		ports.get(3).setRoutes( dCost );
		
		//PortZ
		Map<Port,Double> zCost = new HashMap<>();
		zCost.put( ports.get(3), new Double(5) );//D
		zCost.put( ports.get(2), new Double(2) );//C
		
		ports.get(4).setRoutes(zCost);
		
		return ports;
	}
	
	public static Collection<Port> caseB() {
		List<Port> ports = new ArrayList<>();
		
		ports.add( new Port("A") );//0
		ports.add( new Port("B") );//1
		ports.add( new Port("C") );//2
		ports.add( new Port("D") );//3
		ports.add( new Port("Z") );//4

		//Port A
		Map<Port,Double> aCost = new HashMap<>();
		aCost.put( ports.get(1), new Double(5) );//B
		aCost.put( ports.get(3), new Double(2) );//D
		
		ports.get(0).setRoutes( aCost );

		//PortB
		Map<Port,Double> bCost = new HashMap<>();
		bCost.put( ports.get(0), new Double(5) );//A
		bCost.put( ports.get(2), new Double(5) );//C
		
		ports.get(1).setRoutes( bCost );
		
		//PortC
		Map<Port,Double> cCost = new HashMap<>();
		cCost.put( ports.get(1), new Double(5) );//B
		cCost.put( ports.get(4), new Double(5) );//Z
		
		ports.get(2).setRoutes( cCost );
		
		//PortD
		Map<Port,Double> dCost = new HashMap<>();
		dCost.put( ports.get(0), new Double(2) );//A
		dCost.put( ports.get(4), new Double(2) );//Z
		
		ports.get(3).setRoutes( dCost );
		
		//PortZ
		Map<Port,Double> zCost = new HashMap<>();
		zCost.put( ports.get(3), new Double(2) );//D
		zCost.put( ports.get(2), new Double(5) );//C
		
		ports.get(4).setRoutes(zCost);
		
		return ports;
	}
}
