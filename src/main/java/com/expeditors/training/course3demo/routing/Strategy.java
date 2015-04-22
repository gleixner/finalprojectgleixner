package com.expeditors.training.course3demo.routing;

import java.util.List;

import com.expeditors.training.course3demo.model.Container;

public interface Strategy {
	
	/**
	 * Returns a route object containing the containers and capacity assignments that
	 * give the lowest cost.
	 * Returns a route with an empty container list and a score of Double.POSITIVE_INIFITY
	 * if there is no legal route.
	 * @return
	 */
	Route getBestRoute( List<Container> containers, double shipmentSize);
	
	String getName();
}
