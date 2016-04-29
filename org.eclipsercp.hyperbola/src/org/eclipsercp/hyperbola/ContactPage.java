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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ContactPage extends WizardPage {

	private Text txtUser;
	private Text txtServer;

	public ContactPage() {
		super(ContactPage.class.getName(), "Enter Contact information",
				Activator.getImageDescriptor("icons/wizard_banner.png"));
		setDescription("Enter the user's name and server information.");
	}

	public void createControl(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout(2, false));

		new Label(control, SWT.NONE).setText("User name:");
		txtUser = new Text(control, SWT.BORDER);
		txtUser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		new Label(control, SWT.NONE).setText("Server:");
		txtServer = new Text(control, SWT.BORDER);
		txtServer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		setControl(control);
		setPageComplete(false);
		ModifyListener listener = new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				checkPage();
			}
		};
		txtUser.addModifyListener(listener);
		txtServer.addModifyListener(listener);
	}

	public String getUsername() {
		return txtUser.getText();
	}

	public String getServer() {
		return txtServer.getText();
	}

	private void checkPage() {
		String msg = null;
		if (txtUser.getText().trim().length() == 0) {
			msg = "Enter a user name.";
		} else if (txtServer.getText().trim().length() == 0) {
			msg = "Enter a server.";
		}
		setPageComplete(msg == null);
		setErrorMessage(msg);
	}
}
