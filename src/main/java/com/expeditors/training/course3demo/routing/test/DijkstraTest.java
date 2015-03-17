package com.expeditors.training.course3demo.routing.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.expeditors.training.course3demo.routing.Dijkstra;

public class DijkstraTest {
	
	public static void main(String... args ) {
		test2();
	}
	
	public static void test2() {
		Dijkstra<Port> dij = new Dijkstra<Port>();
		
		List<Port> scenario = ContainerCases.buildPortGraph( ContainerCases.caseB());
		
		List<Port> resultA = dij.findPath(scenario, find(scenario, "A"), end(scenario, "Z"));
		
		for( Port port : resultA ) {
			System.out.println(port.getName());
		}
	}
	
	private static Collection<Port> end(List<Port> caseA, String string) {
		Collection<Port> result = new ArrayList<>();
		result.add( find(caseA, "Z"));
		return result;
	}

	private static Port find(List<Port> caseA, String arg) {
		int i = 0;
		while(!caseA.get(i).getName().equals(arg) )
			i += 1;
		return caseA.get(i);
	}

	public static void test1() {
		Dijkstra<Port> dij = new Dijkstra<Port>();
		
		List<Port> caseA = (List<Port>) Cases.caseA();
		Collection<Port> goalA = new ArrayList<>();
		goalA.add( caseA.get( caseA.size() - 1 ) );
		
		List<Port> resultA = dij.findPath(caseA, caseA.get(0), goalA);
		
		for( Port port : resultA ) {
			System.out.println(port.getName());
		}
		
		List<Port> caseB = (List<Port>) Cases.caseB();
		Collection<Port> goalB = new ArrayList<>();
		goalB.add( caseB.get( caseB.size() - 1 ) );
		
		List<Port> resultB = dij.findPath(caseB, caseB.get(0), goalB);
		
		for( Port port : resultB ) {
			System.out.println(port.getName());
		}
	}

}
