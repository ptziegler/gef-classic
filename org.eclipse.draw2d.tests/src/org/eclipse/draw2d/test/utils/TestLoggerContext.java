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

package org.eclipse.draw2d.test.utils;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.Platform;

import org.eclipse.draw2d.internal.Logger;
import org.eclipse.draw2d.internal.LoggerContext;

public class TestLoggerContext implements LoggerContext {
	@Override
	public Logger getLogger(Class<?> clazz) {
		return new TestLogger(clazz);
	}

	private static class TestLogger extends Logger {
		private final ILog referee;

		public TestLogger(Class<?> clazz) {
			referee = Platform.getLog(clazz);
		}

		@Override
		public void info(String message, Throwable throwable) {
			referee.info(message, throwable);
		}

		@Override
		public void warn(String message, Throwable throwable) {
			referee.warn(message, throwable);
		}

		@Override
		public void error(String message, Throwable throwable) {
			referee.error(message, throwable);
		}

	}
}
