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

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.Wizard;
import org.eclipsercp.hyperbola.model.ContactsEntry;
import org.eclipsercp.hyperbola.model.ContactsGroup;

public class AddContactWizard extends Wizard implements IWizard {

	private ContactsGroup group;
	private ContactPage contactPage;
	private NicknamePage nicknamePage;

	public AddContactWizard(ContactsGroup group) {
		this.group = group;
	}

	@Override
	public void addPages() {
		contactPage = new ContactPage();
		addPage(contactPage);
		nicknamePage = new NicknamePage();
		addPage(nicknamePage);
		setWindowTitle("Add Contact");
	}

	@Override
	public boolean performFinish() {
		ContactsEntry entry = new ContactsEntry(group,
				contactPage.getUsername(), nicknamePage.getNickname(),
				contactPage.getServer());
		group.addEntry(entry);
		return true;
	}
}
