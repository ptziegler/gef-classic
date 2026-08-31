/*******************************************************************************
 * Copyright (c) 2000, 2026 IBM Corporation and others.
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
package org.eclipse.gef.internal;

import org.eclipse.swt.graphics.Image;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;

import org.eclipse.draw2d.internal.ImageUtils;

public class InternalImages {

	public static final ImageDescriptor DESC_ZOOM_IN;
	public static final ImageDescriptor DESC_ZOOM_OUT;

	public static final ImageDescriptor DESC_MATCH_SIZE;
	public static final ImageDescriptor DESC_MATCH_WIDTH;
	public static final ImageDescriptor DESC_MATCH_HEIGHT;

	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_MATCH_SIZE_DIS;
	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_MATCH_WIDTH_DIS;
	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_MATCH_HEIGHT_DIS;

	public static final ImageDescriptor DESC_HORZ_ALIGN_CENTER;
	public static final ImageDescriptor DESC_HORZ_ALIGN_LEFT;
	public static final ImageDescriptor DESC_HORZ_ALIGN_RIGHT;

	public static final ImageDescriptor DESC_VERT_ALIGN_MIDDLE;
	public static final ImageDescriptor DESC_VERT_ALIGN_TOP;
	public static final ImageDescriptor DESC_VERT_ALIGN_BOTTOM;

	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_HORZ_ALIGN_CENTER_DIS;
	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_HORZ_ALIGN_LEFT_DIS;
	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_HORZ_ALIGN_RIGHT_DIS;

	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_VERT_ALIGN_MIDDLE_DIS;
	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_VERT_ALIGN_TOP_DIS;
	/**
	 * @deprecated No longer needed with SWT 3.130.0. Can be removed once this is
	 *             the minimum supported version.
	 */
	@Deprecated
	public static final ImageDescriptor DESC_VERT_ALIGN_BOTTOM_DIS;

	public static final ImageDescriptor DESC_SEPARATOR;
	public static final ImageDescriptor DESC_FOLDER_OPEN;
	public static final ImageDescriptor DESC_FOLDER_CLOSED;

	public static final ImageDescriptor DESC_BOLD;
	public static final ImageDescriptor DESC_ITALIC;
	public static final ImageDescriptor DESC_UNDERLINE;

	public static final ImageDescriptor DESC_BLOCK_LTR;
	public static final ImageDescriptor DESC_BLOCK_RTL;

	public static final ImageDescriptor DESC_BLOCK_ALIGN_LEFT;
	public static final ImageDescriptor DESC_BLOCK_ALIGN_CENTER;
	public static final ImageDescriptor DESC_BLOCK_ALIGN_RIGHT;

	public static final ImageDescriptor DESC_PINNED;
	public static final ImageDescriptor DESC_UNPINNED;

	public static final ImageDescriptor DESC_PALETTE;

	private static ResourceManager resourceManager;

	static {
		DESC_BOLD = createDescriptor("icons/style_bold.svg"); //$NON-NLS-1$
		DESC_ITALIC = createDescriptor("icons/style_italic.svg"); //$NON-NLS-1$
		DESC_UNDERLINE = createDescriptor("icons/style_underline.svg"); //$NON-NLS-1$

		DESC_BLOCK_LTR = createDescriptor("icons/style_paragraph_ltr.svg"); //$NON-NLS-1$
		DESC_BLOCK_RTL = createDescriptor("icons/style_paragraph_rtl.svg"); //$NON-NLS-1$

		DESC_BLOCK_ALIGN_LEFT = createDescriptor("icons/style_paragraph_left.svg"); //$NON-NLS-1$
		DESC_BLOCK_ALIGN_CENTER = createDescriptor("icons/style_paragraph_center.svg"); //$NON-NLS-1$
		DESC_BLOCK_ALIGN_RIGHT = createDescriptor("icons/style_paragraph_right.svg"); //$NON-NLS-1$

		DESC_ZOOM_IN = createDescriptor("icons/zoom_in.svg"); //$NON-NLS-1$
		DESC_ZOOM_OUT = createDescriptor("icons/zoom_out.svg"); //$NON-NLS-1$

		DESC_MATCH_SIZE = createDescriptor("icons/matchsize.svg"); //$NON-NLS-1$
		DESC_MATCH_WIDTH = createDescriptor("icons/matchwidth.svg"); //$NON-NLS-1$
		DESC_MATCH_HEIGHT = createDescriptor("icons/matchheight.svg"); //$NON-NLS-1$

		DESC_MATCH_SIZE_DIS = createDescriptor("icons/matchsize_d.png"); //$NON-NLS-1$
		DESC_MATCH_WIDTH_DIS = createDescriptor("icons/matchwidth_d.png"); //$NON-NLS-1$
		DESC_MATCH_HEIGHT_DIS = createDescriptor("icons/matchheight_d.png"); //$NON-NLS-1$

		DESC_VERT_ALIGN_BOTTOM = createDescriptor("icons/alignbottom.svg"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_CENTER = createDescriptor("icons/aligncenter.svg"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_LEFT = createDescriptor("icons/alignleft.svg"); //$NON-NLS-1$
		DESC_VERT_ALIGN_MIDDLE = createDescriptor("icons/alignmid.svg"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_RIGHT = createDescriptor("icons/alignright.svg"); //$NON-NLS-1$
		DESC_VERT_ALIGN_TOP = createDescriptor("icons/aligntop.svg"); //$NON-NLS-1$

		DESC_VERT_ALIGN_BOTTOM_DIS = createDescriptor("icons/alignbottom_d.png"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_CENTER_DIS = createDescriptor("icons/aligncenter_d.png"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_LEFT_DIS = createDescriptor("icons/alignleft_d.png"); //$NON-NLS-1$
		DESC_VERT_ALIGN_MIDDLE_DIS = createDescriptor("icons/alignmid_d.png"); //$NON-NLS-1$
		DESC_HORZ_ALIGN_RIGHT_DIS = createDescriptor("icons/alignright_d.png"); //$NON-NLS-1$
		DESC_VERT_ALIGN_TOP_DIS = createDescriptor("icons/aligntop_d.png"); //$NON-NLS-1$

		DESC_SEPARATOR = createDescriptor("icons/separator.svg"); //$NON-NLS-1$
		DESC_FOLDER_OPEN = createDescriptor("icons/folder_open.svg"); //$NON-NLS-1$
		DESC_FOLDER_CLOSED = createDescriptor("icons/folder_closed.svg"); //$NON-NLS-1$

		DESC_PINNED = createDescriptor("icons/pinned.svg"); //$NON-NLS-1$
		DESC_UNPINNED = createDescriptor("icons/unpinned.svg"); //$NON-NLS-1$

		DESC_PALETTE = createDescriptor("icons/palette_view.svg"); //$NON-NLS-1$

	}

	/**
	 * Creates and returns an image descriptor from the given file. If the file is
	 * an SVG, it will be automatically swapped out with a PNG if not yet supported
	 * by SWT.
	 */
	public static ImageDescriptor createDescriptor(String filename) {
		return ImageDescriptor.createFromFile(InternalImages.class, ImageUtils.getEffectiveFileName(filename));
	}

	/**
	 * Creates and returns a shared image for the given descriptor. This image is
	 * disposed automatically when this bundle is stopped and must not be disposed
	 * by the caller.
	 *
	 * @param descriptor The image descriptor to create an image from.
	 * @return the created image.
	 */
	public static Image getImage(ImageDescriptor descriptor) {
		return getResourceManager().create(descriptor);
	}

	/**
	 * Disposes the underlying resource manager and all of its allocated images.
	 * This method must be called before this bundle is stopped.
	 */
	public static void dispose() {
		if (resourceManager != null) {
			resourceManager.dispose();
			resourceManager = null;
		}
	}

	/**
	 * Lazily creates and returns the resource manager used to allocate the shared
	 * images.
	 */
	private static ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return resourceManager;
	}
}
