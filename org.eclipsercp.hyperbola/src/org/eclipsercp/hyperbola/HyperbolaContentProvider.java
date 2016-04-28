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

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipsercp.hyperbola.model.Contact;
import org.eclipsercp.hyperbola.model.ContactsGroup;

public class HyperbolaContentProvider implements ITreeContentProvider {

	public void dispose() {
		// unused
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// unused
	}

	public Object[] getElements(Object inputElement) {
		ContactsGroup root = (ContactsGroup) inputElement;
		return root.getEntries();
	}

	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ContactsGroup) {
			ContactsGroup group = (ContactsGroup) parentElement;
			return group.getEntries();
		}
		return new Object[0];
	}

	public Object getParent(Object element) {
		Contact contact = (Contact) element;
		return contact.getParent();
	}

	public boolean hasChildren(Object element) {
		if (element instanceof ContactsGroup) {
			ContactsGroup group = (ContactsGroup) element;
			return group.getEntries().length > 0;
		}
		return false;
	}

}
