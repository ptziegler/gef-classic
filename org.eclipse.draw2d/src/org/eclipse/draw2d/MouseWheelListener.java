/*******************************************************************************
 * Copyright (c) 2025 Patrick Ziegler and others.
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

package org.eclipse.draw2d;

/**
 * A listener interface for receiving mouse wheel events.
 *
 * @since 3.20
 */
public interface MouseWheelListener {
	/**
	 * Called when the mouse wheel is scrolled over the listened to object.
	 *
	 * @param me The MouseEvent object
	 */
	void mouseWheelMoved(MouseEvent me);
}
