/*******************************************************************************
* Copyright (c) 2011, 2023 Stephan Schwiebert.
*
* This program and the accompanying materials are made available under the
* terms of the Eclipse Public License 2.0 which is available at
* http://www.eclipse.org/legal/epl-2.0.
*
* SPDX-License-Identifier: EPL-2.0
*
* Contributors: Stephan Schwiebert - initial API and implementation
*******************************************************************************/
package org.eclipse.zest.cloudio.layout;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.eclipse.zest.cloudio.Word;
import org.eclipse.zest.cloudio.util.CloudMatrix;

/**
 *
 * @author sschwieb
 *
 */
public interface ILayouter {

	/**
	 * Places the given word within the defined rectangle, starting at the initial
	 * position.
	 *
	 * @param offset
	 * @param word
	 * @param cloudArea
	 * @param cloudMatrix
	 * @return
	 */
	public boolean layout(Point initial, final Word word, final Rectangle cloudArea, CloudMatrix cloudMatrix);

	/**
	 * Calculates the initial offset of the given word, within the bounds of the
	 * specified rectangle. The layout algorithm will try to find a matching
	 * position around the initial offset.
	 *
	 * @param word
	 * @param cloudArea
	 * @return
	 */
	public Point getInitialOffset(Word word, Rectangle cloudArea);

	/**
	 * Set Layouter-specific options. See {@link DefaultLayouter} as an example.
	 *
	 * @param optionName
	 * @param object
	 */
	public void setOption(String optionName, Object object);
}