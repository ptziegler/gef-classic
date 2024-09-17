/*******************************************************************************
 * Copyright 2005-2007, 2024, CHISEL Group, University of Victoria, Victoria,
 *                            BC, Canada and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     The Chisel Group, University of Victoria
 *******************************************************************************/
package org.eclipse.zest.examples.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;

/**
 * This snippet creates a graph with 80*3 nodes (240 nodes). Only the icons are
 * shown for the nodes, but if you mouse over the node you get the entire text.
 *
 * @author Ian Bull
 *
 */
public class GraphSnippet6 {
	private static Graph g;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Shell shell = new Shell();
		Display d = shell.getDisplay();
		shell.setText("GraphSnippet6");
		Image image1 = Display.getDefault().getSystemImage(SWT.ICON_INFORMATION);
		Image image2 = Display.getDefault().getSystemImage(SWT.ICON_WARNING);
		Image image3 = Display.getDefault().getSystemImage(SWT.ICON_ERROR);
		shell.setLayout(new FillLayout());
		shell.setSize(800, 800);

		g = new Graph(shell, SWT.NONE);
		g.setConnectionStyle(ZestStyles.CONNECTIONS_DIRECTED);
		for (int i = 0; i < 80; i++) {
			GraphNode n1 = new GraphNode(g, ZestStyles.NODES_HIDE_TEXT | ZestStyles.NODES_FISHEYE, "Information",
					image1);
			GraphNode n2 = new GraphNode(g, ZestStyles.NODES_HIDE_TEXT | ZestStyles.NODES_FISHEYE, "Warning", image2);
			GraphNode n3 = new GraphNode(g, ZestStyles.NODES_HIDE_TEXT | ZestStyles.NODES_FISHEYE, "Error", image3);
			new GraphConnection(g, SWT.NONE, n1, n2);
			new GraphConnection(g, SWT.NONE, n2, n3);
			new GraphConnection(g, SWT.NONE, n3, n3);
		}
		g.setLayoutAlgorithm(new GridLayoutAlgorithm.Zest1(LayoutStyles.NO_LAYOUT_NODE_RESIZING), true);

		shell.open();
		while (!shell.isDisposed()) {
			while (!d.readAndDispatch()) {
				d.sleep();
			}
		}

	}

}
