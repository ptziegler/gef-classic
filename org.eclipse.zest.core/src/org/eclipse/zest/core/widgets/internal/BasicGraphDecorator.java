/*******************************************************************************
 * Copyright (c) 2026 Patrick Ziegler and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Patrick Ziegler - initial API and implementation
 *******************************************************************************/

package org.eclipse.zest.core.widgets.internal;

import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.decorators.IGraphDecorator;

import org.eclipse.draw2d.ColorConstants;

/**
 * Default implementation used for decorating a {@link Graph} and its nodes if
 * no other decorator is specified.
 */
public class BasicGraphDecorator implements IGraphDecorator {
	private static final IGraphDecorator INSTANCE = new BasicGraphDecorator();

	private BasicGraphDecorator() {
		// should never be called from outside in a singleton
	}

	public static IGraphDecorator getInstance() {
		return INSTANCE;
	}

	@Override
	@SuppressWarnings("removal")
	public void decorateConnection(GraphConnection connection) {
		connection.setLineColor(ColorConstants.lightGray);
		connection.setHighlightColor(connection.getGraphModel().DARK_BLUE);
	}

	@Override
	@SuppressWarnings("removal")
	public void decorateNode(GraphNode node) {
		node.setForegroundColor(node.getGraphModel().DARK_BLUE);
		node.setForegroundHighlightColor(null);
		node.setBackgroundColor(node.getGraphModel().LIGHT_BLUE);
		node.setBackgroundHighlightColor(node.getGraphModel().HIGHLIGHT_COLOR);
		node.setBorderColor(ColorConstants.lightGray);
		node.setBorderHighlightColor(ColorConstants.blue);
	}

	@Override
	public void decorateGraph(Graph graph) {
		graph.setBackground(ColorConstants.white);
	}
}
