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

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipsercp.hyperbola.model.ContactsEntry;
import org.eclipsercp.hyperbola.model.ContactsGroup;
import org.eclipsercp.hyperbola.model.IContactsListener;
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

		getSite().setSelectionProvider(treeViewer);
		session.getRoot().addContactsListener(new IContactsListener() {
			public void contactsChanged(ContactsGroup contacts,
					ContactsEntry entry) {
				treeViewer.refresh(contacts);
			}
		});

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStatusLineManager statusLine = getViewSite().getActionBars()
						.getStatusLineManager();
				IStructuredSelection sSelection = (IStructuredSelection) event
						.getSelection();
				if (sSelection.isEmpty()) {
					statusLine.setMessage("");
				} else {
					statusLine.setMessage(sSelection.size()
							+ " item(s) selected");
				}
			}
		});

		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(treeViewer.getControl(),
						"org.eclipsercp.hyperbola.help.contactsView");

		createContextMenu(treeViewer);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	private void createContextMenu(Viewer viewer) {
		MenuManager menuMgr = new MenuManager();
		menuMgr.add(new GroupMarker("additions"));
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

}
