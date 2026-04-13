/*******************************************************************************
 * Copyright (c) 2026 Patrick Ziegler and others.
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

package swt.bugs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.layout.BorderData;
import org.eclipse.swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;

public class Test {

	public static void main(String[] args) {
		Shell shell = new Shell();
		shell.setLayout(new BorderLayout());
		shell.setSize(200, 100);

		Display display = shell.getDisplay();
		Image image = createTestImage(display);

		Slider slider = new Slider(shell, SWT.NONE);
		slider.setMinimum(1);
		slider.setMaximum(200);
		slider.setSelection(100);
		slider.setIncrement(5);
		slider.setLayoutData(new BorderData(SWT.TOP));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new BorderData(SWT.CENTER));
		composite.addPaintListener(event -> {
			float scale = slider.getSelection() / 100.0f;

			Transform t = new Transform(display);
			t.scale(scale, scale);

			GC gc = event.gc;
			gc.setTransform(t);
			t.dispose();

			gc.drawImage(image, 0, 0);
		});

		slider.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				composite.redraw();
			}
		});

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		image.dispose();
	}

	private static Image createTestImage(Display display) {
		Image image = new Image(display, 16, 16);
		GC gc = new GC(image);
		gc.setBackground(display.getSystemColor(SWT.COLOR_RED));
		gc.fillRectangle(0, 0, 16, 16);
		gc.dispose();
		return image;
	}
}
