/*******************************************************************************
 * Copyright (c) 2005, 2026 IBM Corporation and others.
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
package org.eclipse.gef.examples.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;

import org.eclipse.gef.EditPart;

import org.eclipse.gef.examples.text.edit.TextEditPart;

/**
 * SelectionModel is immutable.
 *
 * @author Pratik Shah
 * @since 3.2
 */
public record SelectionModel(SelectionRange selectionRange, List<EditPart> selectedEditParts) {

	@SuppressWarnings("unchecked")
	public SelectionModel(IStructuredSelection selection) {
		this(null, selection.toList());
	}

	public SelectionModel(SelectionRange selectionRange, List<EditPart> selectedEditParts) {
		this.selectionRange = selectionRange;
		this.selectedEditParts = nullSafeCollection(selectedEditParts);
	}

	private static List<EditPart> nullSafeCollection(List<EditPart> list) {
		return list == null ? Collections.emptyList() : List.copyOf(list);

	}

	protected void applySelectedParts() {
		if (!selectedEditParts.isEmpty()) {
			Iterator<EditPart> itr = selectedEditParts.iterator();
			while (true) {
				EditPart part = itr.next();
				if (!itr.hasNext()) {
					part.setSelected(EditPart.SELECTED_PRIMARY);
					break;
				}
				part.setSelected(EditPart.SELECTED);
			}
		}
	}

	protected void applySelectionRange() {
		SelectionRange range = selectionRange();
		if (range != null) {
			List<EditPart> currentSelection = range.getSelectedParts();
			for (EditPart element : currentSelection) {
				TextEditPart textpart = (TextEditPart) element;
				textpart.setSelection(0, textpart.getLength());
			}

			if (range.begin.part == range.end.part) {
				range.begin.part.setSelection(range.begin.offset, range.end.offset);
			} else {
				range.begin.part.setSelection(range.begin.offset, range.begin.part.getLength());
				range.end.part.setSelection(0, range.end.offset);
			}
		}
	}

	public void deselect() {
		deselectSelectedParts();
		deselectSelectionRange();
	}

	protected void deselectSelectedParts() {
		selectedEditParts.forEach(ep -> ep.setSelected(EditPart.SELECTED_NONE));
	}

	protected void deselectSelectionRange() {
		SelectionRange range = selectionRange();
		if (range != null) {
			range.getSelectedParts().forEach(ep -> ((TextEditPart) ep).setSelection(-1, -1));
		}
	}

	public SelectionModel getAppendedSelection(EditPart newPart) {
		ArrayList<EditPart> list = new ArrayList<>(selectedEditParts);
		list.remove(newPart);
		list.add(newPart);
		return new SelectionModel(selectionRange, list);
	}

	public SelectionModel getExcludedSelection(EditPart exclude) {
		ArrayList<EditPart> list = new ArrayList<>(selectedEditParts);
		list.remove(exclude);
		return new SelectionModel(selectionRange, list);
	}

	public EditPart getFocusPart() {
		if (selectedEditParts.isEmpty()) {
			return null;
		}
		return selectedEditParts.get(selectedEditParts.size() - 1);
	}

	public void applySelection(SelectionModel old) {
		if (old == null) {
			applySelectedParts();
			applySelectionRange();
			return;
		}

		// Convert to HashSet to optimize performance.
		if (!old.selectedEditParts().isEmpty()) {
			Collection<EditPart> editparts = new HashSet<>(selectedEditParts);
			old.selectedEditParts().stream().filter(part -> !editparts.contains(part))
					.forEach(part -> part.setSelected(EditPart.SELECTED_NONE));
		}
		applySelectedParts();

		old.deselectSelectionRange();
		applySelectionRange();
	}

	public boolean isTextSelected() {
		return selectionRange != null;
	}

}