package com.expeditors.training.course3demo.routing;

import java.util.Comparator;

import com.expeditors.training.course3demo.model.Container;

public interface RouteStrategy extends Comparator<Container> {
	public String getName();
}
