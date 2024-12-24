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
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.gef.test;

import static org.junit.Assert.assertThrows;

import org.eclipse.gef.ui.palette.PaletteColorProvider;

import org.junit.Test;

public class PaletteColorProviderTest {
	private final PaletteColorProvider provider = PaletteColorProvider.INSTANCE;

	@Test
	public void testInvalidMixedButtonDarker1() {
		assertThrows(IllegalArgumentException.class, () -> provider.getButtonDarker(-0.1));
	}

	@Test
	public void testInvalidMixedButtonDarker2() {
		assertThrows(IllegalArgumentException.class, () -> provider.getButtonDarker(1.1));
	}

	@Test
	public void testInvalidMixedListBackground1() {
		assertThrows(IllegalArgumentException.class, () -> provider.getListBackground(-0.1));
	}

	@Test
	public void testInvalidMixedListBackground2() {
		assertThrows(IllegalArgumentException.class, () -> provider.getListBackground(1.1));
	}

	@Test
	public void testValidMixedListBackground() {
		provider.getListBackground(0.0);
		provider.getListBackground(0.75);
		provider.getListBackground(1.0);
	}

	@Test
	public void testValidMixedButtonDarker() {
		provider.getButtonDarker(0.0);
		provider.getButtonDarker(0.75);
		provider.getButtonDarker(1.0);
	}
}
