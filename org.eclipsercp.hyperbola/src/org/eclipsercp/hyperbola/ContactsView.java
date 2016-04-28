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

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipsercp.hyperbola.model.ContactsEntry;
import org.eclipsercp.hyperbola.model.ContactsGroup;
import org.eclipsercp.hyperbola.model.Session;

public class ContactsView extends ViewPart {

	public static final String ID = "org.eclipsercp.hyperbola.views.contacts";

	private Session session;
	private TreeViewer treeViewer;

	private void initializeSession() {
		session = new Session();
		ContactsGroup root = session.getRoot();

		ContactsGroup group1 = new ContactsGroup(root, "Friends");
		group1.addEntry(new ContactsEntry(group1, "Alize", "aliz", "localhost"));
		group1.addEntry(new ContactsEntry(group1, "Sydney", "syd", "localhost"));
		root.addEntry(group1);

		ContactsGroup group2 = new ContactsGroup(root, "Other");
		group2.addEntry(new ContactsEntry(group2, "Nadine", "nad", "localhost"));
		root.addEntry(group2);
	}

	@Override
	public void createPartControl(Composite parent) {
		initializeSession();
		treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI
				| SWT.V_SCROLL);
		treeViewer.setContentProvider(new HyperbolaContentProvider());
		treeViewer.setLabelProvider(new HyperbolaLabelProvider());
		treeViewer.setUseHashlookup(true);
		treeViewer.setInput(session.getRoot());
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

}
