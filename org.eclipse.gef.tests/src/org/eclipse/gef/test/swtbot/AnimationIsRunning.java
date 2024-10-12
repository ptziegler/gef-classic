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

package org.eclipse.gef.test.swtbot;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

import org.eclipse.draw2d.Animation;

import org.eclipse.gef.examples.flow.parts.GraphAnimation;

/**
 * This condition is used in combination with {@code waitWhile} to ensure that
 * the test waits for the completion of the current animation. The GEF editor
 * can't be modified while the animation is running, so any operation that is
 * done by the test might not be executed and may therefore lead to sporadic
 * failures.
 *
 * @see SWTBot#waitWhile(ICondition)
 * @see SWTBot#waitWhile(ICondition, long)
 * @see SWTBot#waitWhile(ICondition, long, long)
 */
public class AnimationIsRunning implements ICondition {
	private static AnimationIsRunning INSTANCE = new AnimationIsRunning();

	private AnimationIsRunning() {
		// This is a singleton class and should never be instantiated directly.
	}

	@Override
	public boolean test() throws Exception {
		// The flow example is using its own Animation class
		return Animation.isAnimating() || GraphAnimation.isAnimating();
	}

	@Override
	public void init(SWTBot bot) {
		// nothing to do
	}

	@Override
	public String getFailureMessage() {
		return "The figure animations didn't finish in time."; //$NON-NLS-1$
	}

	public static AnimationIsRunning animationIsRunning() {
		return INSTANCE;
	}
}
