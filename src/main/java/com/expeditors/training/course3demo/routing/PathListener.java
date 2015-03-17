package com.expeditors.training.course3demo.routing;

//Copyright (C) 2002-2010 StackFrame, LLC http://www.stackframe.com/
//This software is provided under the GNU General Public License, version 2.

import java.util.EventListener;

/**
* The listener interface for receiving events related to paths generated by a {@link PathFinder}.
*
* @author Gene McCulley
*/
public interface PathListener<T extends Node> extends EventListener {

 /**
  * Called when a new path is being considered.
  *
  * @param e the PathEvent that belongs to the PathFinder.
  */
 void considered(PathEvent<T> e);

}
