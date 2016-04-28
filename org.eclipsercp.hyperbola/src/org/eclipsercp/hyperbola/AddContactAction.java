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

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.hyperbola.model.ContactsEntry;
import org.eclipsercp.hyperbola.model.ContactsGroup;

public class AddContactAction extends Action implements ISelectionListener,
		IWorkbenchAction {

	private IWorkbenchWindow window;
	private IStructuredSelection sSelection;

	public AddContactAction(IWorkbenchWindow window) {
		setId(AddContactAction.class.getName());
		setText("&Add Contact...");
		setToolTipText("Add a contact to your contacts list.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				"org.eclipsercp.hyperbola", "icons/add_contact.gif"));
		this.window = window;
		window.getSelectionService().addSelectionListener(this);
	}

	public void dispose() {
		window.getSelectionService().removeSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			sSelection = (IStructuredSelection) selection;
			setEnabled(sSelection.size() == 1
					&& sSelection.getFirstElement() instanceof ContactsGroup);
		} else {
			setEnabled(false);
		}
	}

	@Override
	public void run() {
		AddContactDialog dialog = new AddContactDialog(window.getShell());
		int code = dialog.open();
		if (code == Window.OK) {
			ContactsGroup group = (ContactsGroup) sSelection.getFirstElement();
			ContactsEntry entry = new ContactsEntry(group, dialog.getName(),
					dialog.getNickname(), dialog.getServer());
			group.addEntry(entry);
		}
	}
}
