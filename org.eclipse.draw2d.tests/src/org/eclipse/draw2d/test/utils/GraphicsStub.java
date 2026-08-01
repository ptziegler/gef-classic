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

package org.eclipse.draw2d.test.utils;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontMetrics;
import org.eclipse.swt.graphics.Image;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * Dummy implementation of the Draw2D Graphics class where every method throws
 * an {@link UnsupportedOperationException}.
 */
public class GraphicsStub extends Graphics {

	@Override
	public void clipRect(Rectangle r) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void dispose() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawArc(int x, int y, int w, int h, int offset, int length) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawFocus(int x, int y, int w, int h) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawImage(Image srcImage, int x, int y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawImage(Image srcImage, int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawLine(int x1, int y1, int x2, int y2) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawOval(int x, int y, int w, int h) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawPolygon(PointList points) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawPolyline(PointList points) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawRectangle(int x, int y, int width, int height) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawString(String s, int x, int y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void drawText(String s, int x, int y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fillArc(int x, int y, int w, int h, int offset, int length) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fillGradient(int x, int y, int w, int h, boolean vertical) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fillOval(int x, int y, int w, int h) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fillPolygon(PointList points) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fillRectangle(int x, int y, int width, int height) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fillRoundRectangle(Rectangle r, int arcWidth, int arcHeight) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fillString(String s, int x, int y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void fillText(String s, int x, int y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Color getBackgroundColor() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Rectangle getClip(Rectangle rect) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Font getFont() {
		throw new UnsupportedOperationException();
	}

	@Override
	public FontMetrics getFontMetrics() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Color getForegroundColor() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLineStyle() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getLineWidth() {
		throw new UnsupportedOperationException();
	}

	@Override
	public float getLineWidthFloat() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean getXORMode() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void popState() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void pushState() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void restoreState() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void scale(double amount) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setBackgroundColor(Color rgb) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setClip(Rectangle r) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setFont(Font f) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setForegroundColor(Color rgb) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLineStyle(int style) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLineWidth(int width) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLineWidthFloat(float width) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLineMiterLimit(float miterLimit) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setXORMode(boolean b) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void translate(int dx, int dy) {
		throw new UnsupportedOperationException();
	}
}
