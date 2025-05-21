/*******************************************************************************
 * Copyright (c) 2006, 2025 IBM Corporation and others.
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
import org.eclipse.swt.graphics.ImageData;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.draw2d.BasicColorProvider;
import org.eclipse.draw2d.ColorProvider;

import org.eclipse.gef.GEFColorProvider;

import org.osgi.framework.BundleContext;

public class InternalGEFPlugin extends AbstractUIPlugin {

	private static BundleContext context;
	private static AbstractUIPlugin singleton;

	public InternalGEFPlugin() {
		singleton = this;
	}

	@Override
	public void start(BundleContext bc) throws Exception {
		super.start(bc);
		context = bc;
		// Overloads the basic color provider with customizable one
		if (ColorProvider.SystemColorFactory.getColorProvider() instanceof BasicColorProvider
				&& PlatformUI.isWorkbenchRunning() && !PlatformUI.getWorkbench().isClosing()) {
			ColorProvider.SystemColorFactory.setColorProvider(new GEFColorProvider());
		}
		Logger.setContext(new LoggerContext());
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		super.initializeImageRegistry(reg);
	}

	public static BundleContext getContext() {
		return context;
	}

	public static AbstractUIPlugin getDefault() {
		return singleton;
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		savePluginPreferences();
		super.stop(context);
	}

	/**
	 * Convenience method for getting the current zoom level of the active device.If
	 * on MacOS or Linux (x11 window system) or if the device zoom couldn't
	 * otherwise be determined, this method returns {@code 100} as default value.
	 */
	public static int getOrDefaultDeviceZoom() {
		// On Mac and Linux X11 ImageData for cursors should always be created with 100%
		// device zoom
		if (Platform.getOS().equals(Platform.OS_MACOSX) || (Platform.getOS().equals(Platform.OS_LINUX)
				&& "x11".equalsIgnoreCase(System.getenv("XDG_SESSION_TYPE")))) { //$NON-NLS-1$//$NON-NLS-2$
			return 100;
		}
		String deviceZoom = System.getProperty("org.eclipse.swt.internal.deviceZoom", "100"); //$NON-NLS-1$ //$NON-NLS-2$
		try {
			return Integer.parseInt(deviceZoom);
		} catch (NumberFormatException e) {
			return 100;
		}
	}

	/**
	 * Convenience method to get the image data for a given zoom level. If no image
	 * for the requested zoom level exists, the image data may be an auto-scaled
	 * version of the native image and may look blurred or mangled.
	 */
	public static ImageData scaledImageData(ImageDescriptor descriptor, int zoom) {
		// Default case: Image in matching resolution has been found
		ImageData data = descriptor.getImageData(zoom);
		if (data != null) {
			return data;
		}
		// Otherwise artifically scale the image
		Image image = descriptor.createImage();
		try {
			return image.getImageData(zoom);
		} finally {
			image.dispose();
		}
	}
}
