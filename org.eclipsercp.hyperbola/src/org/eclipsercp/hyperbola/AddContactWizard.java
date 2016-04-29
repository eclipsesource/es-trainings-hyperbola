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

	private final WizardData data;
	private final ContactsGroup group;

	public AddContactWizard(ContactsGroup group) {
		this.group = group;
		this.data = new WizardData();
	}

	@Override
	public void addPages() {
		addPage(new ContactPage(data));
		addPage(new NicknamePage(data));
		setWindowTitle(Messages.AddContactWizard_windowTitle);
	}

	@Override
	public boolean performFinish() {
		ContactsEntry entry = new ContactsEntry(group, data.getUsername(),
				data.getNickname(), data.getServer());
		group.addEntry(entry);
		return true;
	}
}
