package com.expeditors.training.course3demo.routing.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.expeditors.training.course3demo.routing.Node;

public class Port implements Node<Port> {
	
	String name;
	
	Map<Port,Double> routes = new HashMap<>();
	
	@Override
	public double pathCostEstimate(Port goal) {
		// This method is not called in Dijkstra
		return 0;
	}

	@Override
	public double traverseCost(Port dest) {
		Double result = routes.get(dest);
		return result;
	}

	@Override
	public Iterable<Port> neighbors() {
		return routes.keySet();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<Port, Double> getRoutes() {
		return routes;
	}

	public void setRoutes(Map<Port, Double> cost) {
		this.routes = cost;
	}

	public Port(String name) {
		this.name = name;
	}
	
}
