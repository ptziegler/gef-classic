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
package org.eclipse.zest.examples.cloudio.application.actions;

import org.eclipse.jface.action.IAction;

/**
 *
 * @author sschwieb
 *
 */
public class ZoomFitAction extends AbstractTagCloudAction {

	@Override
	public void run(IAction action) {
		getViewer().zoomFit();
	}

}