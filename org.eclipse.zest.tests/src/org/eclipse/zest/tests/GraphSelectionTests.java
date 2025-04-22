/*******************************************************************************
 * Copyright (c) 2010, 2025 Fabian Steeg and others.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Fabian Steeg - initial tests
 *******************************************************************************/
package org.eclipse.zest.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.zest.core.widgets.Graph;
import org.eclipse.zest.core.widgets.GraphConnection;
import org.eclipse.zest.core.widgets.GraphNode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Selection-related tests for the {@link Graph} class.
 *
 * @author Fabian Steeg (fsteeg)
 *
 */
public class GraphSelectionTests {

	private GraphNode[] nodes;

	private Graph graph;

	@BeforeEach
	public void setUp() throws Exception {
		graph = new Graph(new Shell(), SWT.NONE);
		nodes = new GraphNode[] { new GraphNode(graph, SWT.NONE), new GraphNode(graph, SWT.NONE) };
		new GraphConnection(graph, SWT.NONE, nodes[0], nodes[1]);
	}

	@Test
	public void testSetSelectionGetSelection() {
		graph.setSelection(new GraphNode[] {});
		assertEquals(0, graph.getSelection().size());
		graph.setSelection(nodes);
		assertEquals(2, graph.getSelection().size());
	}

	@Test
	public void testSelectAllGetSelection() {
		graph.selectAll();
		assertEquals(2, graph.getSelection().size());
	}

	@Test
	public void testAddSelectionListenerEventIdentity() {
		final List<SelectionEvent> selectionEvents = new ArrayList<>();
		graph.addSelectionListener(setupListener(selectionEvents));
		graph.addSelectionListener(setupListener(selectionEvents));
		Event event = new Event();
		event.widget = nodes[0];
		graph.notifyListeners(SWT.Selection, event);
		assertEquals(2, selectionEvents.size(), "Two listeners should receive one event each"); //$NON-NLS-1$
		assertEquals(selectionEvents.get(0), selectionEvents.get(1), "Two listeners should receive the same event"); //$NON-NLS-1$
	}

	/**
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=314710
	 */
	@Test
	public void testSelectedNodeDisposal() {
		graph.setSelection(nodes);
		nodes[0].dispose();
		graph.layout();
		assertEquals(1, graph.getSelection().size(), "Disposing a selected node should remove it from the selection"); //$NON-NLS-1$
	}

	/**
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=320281
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=218148#c6
	 */
	@Test
	public void testAddSelectionListenerSetSelection() {
		final List<SelectionEvent> selectionEvents = new ArrayList<>();
		graph.addSelectionListener(setupListener(selectionEvents));
		graph.setSelection(nodes);
		assertEquals(0, selectionEvents.size(), "Programmatic selection should not trigger events"); //$NON-NLS-1$
		for (GraphNode node : nodes) {
			assertTrue(node.isSelected(), "Programmatic selection should select nodes"); //$NON-NLS-1$
		}
		graph.setSelection(new GraphNode[] { nodes[0] });
		for (int i = 1; i < nodes.length; i++) {
			GraphNode node = nodes[i];
			assertFalse(node.isSelected(), "Changing the selection should deselect the nodes selected before"); //$NON-NLS-1$
		}
	}

	/**
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=320281
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=322054
	 */
	@Test
	public void testAddSelectionListenerSelectAll() {
		final List<SelectionEvent> selectionEvents = new ArrayList<>();
		graph.addSelectionListener(setupListener(selectionEvents));
		graph.selectAll();
		assertEquals(0, selectionEvents.size(), "Programmatic selection should not trigger events"); //$NON-NLS-1$
		for (GraphNode node : graph.getNodes()) {
			assertTrue(node.isSelected(), "Programmatic selection should set nodes selected"); //$NON-NLS-1$
		}
	}

	/**
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=320281
	 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=218148#c6
	 */
	@Test
	public void testAddSelectionListenerNotifyListeners() {
		final List<SelectionEvent> selectionEvents = new ArrayList<>();
		graph.addSelectionListener(setupListener(selectionEvents));
		graph.setSelection(nodes);
		Event event = new Event();
		event.widget = graph;
		graph.notifyListeners(SWT.Dispose, event);
		assertEquals(0, selectionEvents.size(), "Non-selection events should not be received"); //$NON-NLS-1$
		graph.notifyListeners(SWT.Selection, event);
		assertEquals(1, selectionEvents.size(), "Selection events should be received"); //$NON-NLS-1$
	}

	@Test
	public void testClearGraphCheckSelection() throws Exception {
		graph.setSelection(nodes);
		assertEquals(2, graph.getSelection().size());
		graph.clear();
		assertEquals(0, graph.getNodes().size());
		assertEquals(0, graph.getConnections().size());
		assertEquals(0, graph.getSelection().size());
		setUp();
		assertEquals(2, graph.getNodes().size());
		assertEquals(1, graph.getConnections().size());
	}

	private static SelectionListener setupListener(final List<SelectionEvent> events) {
		return new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				events.add(e);
			}
		};
	}
}
