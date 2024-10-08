/*******************************************************************************
 * Copyright (c) 2003, 2024 IBM Corporation and others.
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
package org.eclipse.gef.internal.ui.rulers;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.rulers.RulerProvider;

/**
 * @author Pratik Shah
 */
public class RulerEditPartFactory implements EditPartFactory {

	protected GraphicalViewer diagramViewer;

	public RulerEditPartFactory(GraphicalViewer primaryViewer) {
		diagramViewer = primaryViewer;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	@Override
	public EditPart createEditPart(EditPart parentEditPart, Object model) {
		// the model can be null when the contents of the root edit part are set
		// to null
		EditPart part = null;
		if (isRuler(model)) {
			part = new RulerEditPart(model);
		} else if (model != null) {
			part = new GuideEditPart(model);
		}
		return part;
	}

	protected Object getHorizontalRuler() {
		Object ruler = null;
		RulerProvider provider = (RulerProvider) diagramViewer.getProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER);
		if (provider != null) {
			ruler = provider.getRuler();
		}
		return ruler;
	}

	protected Object getVerticalRuler() {
		Object ruler = null;
		RulerProvider provider = (RulerProvider) diagramViewer.getProperty(RulerProvider.PROPERTY_VERTICAL_RULER);
		if (provider != null) {
			ruler = provider.getRuler();
		}
		return ruler;
	}

	protected boolean isRuler(Object model) {
		boolean result = false;
		if (model != null) {
			result = model == getHorizontalRuler() || model == getVerticalRuler();
		}
		return result;
	}

}
