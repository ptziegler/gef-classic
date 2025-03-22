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

package gef.bugs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.ToolTipHelper;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.internal.Logger;

/**
 * When the mouse wheel is scrolled, a {@code mouseExited} and
 * {@code mouseEntered} event should be fired once the figure is no longer under
 * the cursor and vice versa. In the same way, the tool-tip should disappear if
 * the figure is no longer under the cursor.
 */
public class BugWithMouseWheelScrolling {
	private static final Logger LOGGER = Logger.getLogger(BugWithMouseWheelScrolling.class);
	private static Shell shell;

	public static void main(String[] args) {
		ToolTipHelper.setDefaultHideDelay(Integer.MAX_VALUE);

		shell = new Shell();
		shell.setLayout(new FillLayout());
		shell.setSize(400, 400);

		IFigure f1 = new Figure() {
			@Override
			public String toString() {
				return "Red"; //$NON-NLS-1$
			}
		};
		f1.setBounds(Rectangle.SINGLETON.setBounds(50, 50, 100, 10));
		f1.setToolTip(new Label("This is a tooltip")); //$NON-NLS-1$
		f1.setBackgroundColor(ColorConstants.red);
		f1.setOpaque(true);
		f1.addMouseMotionListener(new MouseMotionListener.Stub() {
			@Override
			public void mouseEntered(MouseEvent me) {
				LOGGER.info("Entered figure " + f1); //$NON-NLS-1$
			}

			@Override
			public void mouseExited(MouseEvent me) {
				LOGGER.info("Exited figure " + f1); //$NON-NLS-1$
			}
		});

		IFigure f2 = new Figure() {
			@Override
			public String toString() {
				return "Blue"; //$NON-NLS-1$
			}
		};
		f2.setBounds(Rectangle.SINGLETON.setBounds(50, 70, 100, 10));
		f2.setBackgroundColor(ColorConstants.blue);
		f2.setOpaque(true);
		f2.addMouseMotionListener(new MouseMotionListener.Stub() {
			@Override
			public void mouseEntered(MouseEvent me) {
				LOGGER.info("Entered figure " + f2); //$NON-NLS-1$
			}

			@Override
			public void mouseExited(MouseEvent me) {
				LOGGER.info("Exited figure " + f2); //$NON-NLS-1$
			}
		});

		FigureCanvas canvas = new FigureCanvas(shell, SWT.NONE);
		canvas.getViewport().add(f1);
		canvas.getViewport().add(f2);

		shell.setVisible(true);
		while (!shell.isDisposed()) {
			shell.getDisplay().readAndDispatch();
		}
	}
}
