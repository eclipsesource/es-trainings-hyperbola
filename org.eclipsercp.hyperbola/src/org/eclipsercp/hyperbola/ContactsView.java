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

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
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
import org.eclipsercp.hyperbola.model.ISession;
import org.eclipsercp.hyperbola.model.Session;

public class ContactsView extends ViewPart {

	public static final String ID = "org.eclipsercp.hyperbola.views.contacts"; //$NON-NLS-1$

	private Session session;
	private TreeViewer treeViewer;

	private void initializeSession() {
		session = new Session();
		ContactsGroup root = session.getRoot();

		ContactsGroup group1 = new ContactsGroup(root, Messages.ContactsView_groupFriends);
		group1.addEntry(new ContactsEntry(group1, "Alize", "aliz", "localhost")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		group1.addEntry(new ContactsEntry(group1, "Sydney", "syd", "localhost")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		root.addEntry(group1);

		ContactsGroup group2 = new ContactsGroup(root, Messages.ContactsView_groupOther);
		group2.addEntry(new ContactsEntry(group2, "Nadine", "nad", "localhost")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		root.addEntry(group2);

		addProtocols(root);
	}

	private void addProtocols(ContactsGroup root) {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		IConfigurationElement[] elements = reg
				.getConfigurationElementsFor("org.eclipsercp.hyperbola.protocols"); //$NON-NLS-1$
		for (IConfigurationElement element : elements) {
			try {
				Object obj = element.createExecutableExtension("class"); //$NON-NLS-1$
				if (obj instanceof ISession) {
					ContactsGroup group = ((ISession) obj).getRoot();
					root.addEntry(group);
					group.setParent(root);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
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
					statusLine.setMessage(""); //$NON-NLS-1$
				} else {
					statusLine.setMessage(sSelection.size()
							+ Messages.ContactsView_itemsSelected);
				}
			}
		});

		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(treeViewer.getControl(),
						"org.eclipsercp.hyperbola.help.contactsView"); //$NON-NLS-1$

		createContextMenu(treeViewer);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	private void createContextMenu(Viewer viewer) {
		MenuManager menuMgr = new MenuManager();
		menuMgr.add(new GroupMarker("additions")); //$NON-NLS-1$
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

}
