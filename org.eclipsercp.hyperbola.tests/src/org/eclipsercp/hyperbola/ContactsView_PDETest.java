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

import junit.framework.Assert;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.PlatformUI;
import org.eclipsercp.hyperbola.model.ContactsGroup;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * This is a test that requires a running workbench. It is identified by the _PDETest suffix. 
 */
public class ContactsView_PDETest {

	private ContactsView contactsView;

	@Before
	public void openView() throws Exception {
		contactsView = (ContactsView) PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage()
				.showView(ContactsView.ID);
	}

	@After
	public void closeView() {
		PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.hideView(contactsView);
	}

	@Test
	public void testFriendsGroupAvailable() {
		TreeViewer viewer = contactsView.getViewer();
		ContactsGroup root = (ContactsGroup) viewer.getInput();
		ContactsGroup actual = (ContactsGroup) root.getEntries()[0];
		Assert.assertEquals("Friends", actual.getName());
	}
}
