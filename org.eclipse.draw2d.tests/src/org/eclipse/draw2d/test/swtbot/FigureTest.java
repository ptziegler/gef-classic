/*******************************************************************************
 * Copyright (c) 2024 Patrick Ziegler and others.
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

package org.eclipse.draw2d.test.swtbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.Platform;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.test.utils.Snippet;

import gef.bugs.BugWithLocalCoordinateSystem;
import gef.bugs.BugWithMouseWheelScrolling;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FigureTest extends AbstractSWTBotTests {
	private static List<String> events = new ArrayList<>();
	private static ILogListener listener = (status, plugin) -> {
		if (status.getPlugin().startsWith("org.eclipse.draw2d")) { //$NON-NLS-1$
			events.add(status.getMessage());
		}
	};

	@BeforeAll
	public static void setUpAll() {
		Platform.addLogListener(listener);
	}

	@AfterAll
	public static void tearDownAll() {
		Platform.removeLogListener(listener);
	}

	@BeforeEach
	@SuppressWarnings("static-method")
	public void setUp() {
		events.clear();
	}

	@Test
	@Snippet(type = BugWithLocalCoordinateSystem.class)
	public void testMouseEventTargetWithLocalCoordinates() {
		IFigure label = root.findFigureAt(20, 125);
		IFigure rectangle = root.findFigureAt(20, 25);

		assertNotNull(label);
		assertNotNull(rectangle);
		assertNull(rectangle.getBorder());

		bot.click(20, 125);
		assertNull(rectangle.getBorder());
	}

	@Test
	@Snippet(type = BugWithMouseWheelScrolling.class)
	public void testMouseWheelScrolling() {
		// Set figure Red as mouse target
		bot.click(100, 55);

		assertEquals(events, List.of("Entered figure Red")); //$NON-NLS-1$
		events.clear();

		// Scroll to figure Blue
		scrollMouseWheelVertical(100, 55, -20);
		assertEquals(events, List.of("Exited figure Red", "Entered figure Blue")); //$NON-NLS-1$ //$NON-NLS-2$
		events.clear();

		// Scroll beyond figure Blue
		scrollMouseWheelVertical(100, 55, -40);
		assertEquals(events, List.of("Exited figure Blue")); //$NON-NLS-1$
		events.clear();

		// Scroll back to figure Red
		scrollMouseWheelVertical(100, 55, 40);
		assertEquals(events, List.of("Entered figure Red")); //$NON-NLS-1$
		events.clear();
	}
}
