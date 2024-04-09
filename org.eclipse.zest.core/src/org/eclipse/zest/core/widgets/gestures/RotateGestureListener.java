/*******************************************************************************
 * Copyright 2012, 2024 Zoltan Ujhelyi.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Zoltan Ujhelyi
 ******************************************************************************/
package org.eclipse.zest.core.widgets.gestures;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.GestureListener;

import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphNode;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Transform;

/**
 * A gesture listener for rotate gestures in Graph widgets.
 *
 * @author Zoltan Ujhelyi
 * @since 2.0
 */
public class RotateGestureListener implements GestureListener {

	Graph graph;
	double rotate;
	List<? extends GraphNode> nodes;
	List<Point> originalLocations;
	double xCenter, yCenter;

	void storePosition(List<? extends GraphNode> nodes) {
		originalLocations = new ArrayList<>();
		Transform t = new Transform();
		t.setTranslation(-xCenter, -yCenter);
		for (GraphNode node : nodes) {
			originalLocations.add(t.getTransformed(node.getLocation()));
		}
	}

	void updatePositions(double rotation) {
		Transform t = new Transform();
		t.setRotation(rotation);
		t.setTranslation(xCenter, yCenter);
		for (int i = 0; i < nodes.size(); i++) {
			GraphNode node = nodes.get(i);
			Point p = originalLocations.get(i);
			Point rot = t.getTransformed(p);
			node.setLocation(rot.preciseX(), rot.preciseY());
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.swt.events.GestureListener#gesture(org.eclipse.swt.events
	 * .GestureEvent)
	 */
	@Override
	public void gesture(GestureEvent e) {
		if (!(e.widget instanceof Graph)) {
			return;
		}
		switch (e.detail) {
		case SWT.GESTURE_BEGIN:
			graph = (Graph) e.widget;
			rotate = 0.0;
			nodes = (List<GraphNode>) graph.getSelection();
			if (nodes.isEmpty()) {
				nodes = graph.getNodes();
			}
			xCenter = 0;// e.x;
			yCenter = 0;// e.y;
			for (GraphNode node : nodes) {
				Point location = node.getLocation();
				xCenter += location.preciseX();
				yCenter += location.preciseY();
			}
			xCenter = xCenter / nodes.size();
			yCenter = yCenter / nodes.size();
			storePosition(nodes);
			break;
		case SWT.GESTURE_END:
			break;
		case SWT.GESTURE_ROTATE:
			updatePositions(e.rotation / 2 / Math.PI);
			break;
		default:
			// Do nothing
		}
	}

}
