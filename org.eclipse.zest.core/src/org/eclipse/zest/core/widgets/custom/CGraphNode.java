/*******************************************************************************
 * Copyright (c) 2005, 2024 IBM Corporation and others.
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
package org.eclipse.zest.core.widgets.custom;

import org.eclipse.zest.core.widgets.IContainer;

import org.eclipse.draw2d.IFigure;

/**
 * A Custom Graph Node
 *
 * @since 1.12
 */
@SuppressWarnings("removal")
public class CGraphNode extends org.eclipse.zest.core.widgets.CGraphNode {
	public CGraphNode(IContainer graphModel, int style, IFigure figure) {
		super(graphModel, style, figure);
	}
}
