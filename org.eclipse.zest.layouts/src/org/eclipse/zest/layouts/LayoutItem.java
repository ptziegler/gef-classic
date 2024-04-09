/*******************************************************************************
 * Copyright 2005, 2024 CHISEL Group, University of Victoria, Victoria, BC,
 *                      Canada.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: The Chisel Group, University of Victoria
 *******************************************************************************/
package org.eclipse.zest.layouts;

import org.eclipse.zest.layouts.interfaces.EntityLayout;

/**
 * Super interface for both Layout Entities and Layout Relationships
 *
 * @author Ian Bull
 * @deprecated Use {@link EntityLayout} instead.
 */
@Deprecated(since = "2.0", forRemoval = true)
public interface LayoutItem {

	public void setGraphData(Object o);

	public Object getGraphData();

}
