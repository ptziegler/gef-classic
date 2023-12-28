/*******************************************************************************
 * Copyright (c) 2023 Patrick Ziegler and others.
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

package org.eclipse.draw2d.text;

import java.text.BreakIterator;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 *
 * @since 3.15
 */
public interface BreakIteratorFactory {
	BreakIterator newInstance();

	static BreakIterator getInstance() {
		Bundle bundle = FrameworkUtil.getBundle(BreakIteratorFactory.class);
		BundleContext bundleContext = bundle.getBundleContext();

		ServiceReference<BreakIteratorFactory> serviceReference = bundleContext
				.getServiceReference(BreakIteratorFactory.class);

		if (serviceReference == null) {
			return BreakIterator.getLineInstance();
		}

		try {
			BreakIteratorFactory factory = bundleContext.getService(serviceReference);
			return factory.newInstance();
		} finally {
			bundleContext.ungetService(serviceReference);
		}
	}
}
