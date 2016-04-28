/*******************************************************************************
 * Copyright (c) 2005 Jean-Michel Lemieux, Jeff McAffer and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Hyperbola is an RCP application developed for the book
 *     Eclipse Rich Client Platform - 
 *         Designing, Coding, and Packaging Java Applications
 * See http://eclipsercp.org
 *
 * Contributors:
 *     Jean-Michel Lemieux and Jeff McAffer - initial API and implementation
 *******************************************************************************/
package org.eclipsercp.hyperbola;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipsercp.hyperbola.model.Contact;
import org.eclipsercp.hyperbola.model.ContactsGroup;

public class HyperbolaLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		Contact contact = (Contact) element;
		return contact.getName();
	}

	@Override
	public Image getImage(Object element) {
		boolean isGroup = element instanceof ContactsGroup;
		String key = isGroup ? IImageKeys.GROUP : IImageKeys.ONLINE;
		return Activator.getDefault().getImageRegistry().get(key);
	}
}
