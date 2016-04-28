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

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipsercp.hyperbola.model.ContactsEntry;
import org.osgi.framework.Bundle;

public class ChatAction extends Action implements ISelectionListener,
		IWorkbenchAction {

	private IWorkbenchWindow window;
	private IStructuredSelection sSelection;

	public ChatAction(IWorkbenchWindow window) {
		setId(ChatAction.class.getName());
		setText("&Chat...");
		setToolTipText("Chat with a contact.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(
				"org.eclipsercp.hyperbola", "icons/chat.gif"));
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
					&& sSelection.getFirstElement() instanceof ContactsEntry);
		} else {
			setEnabled(false);
		}
	}

	@Override
	public void run() {
		ContactsEntry entry = (ContactsEntry) sSelection.getFirstElement();
		IWorkbenchPage page = window.getActivePage();
		ChatEditorInput input = new ChatEditorInput(entry);
		try {
			page.openEditor(input, ChatEditor.ID);
		} catch (PartInitException exc) {
			Bundle bundle = Platform.getBundle("org.eclipsercp.hyperbola");
			Platform.getLog(bundle).log(exc.getStatus());
		}
	}
}
