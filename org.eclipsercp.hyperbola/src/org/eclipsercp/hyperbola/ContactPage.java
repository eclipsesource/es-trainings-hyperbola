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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class ContactPage extends WizardPage {

	private final WizardData data;
	private Text txtUser;
	private Text txtServer;

	public ContactPage(WizardData data) {
		super(ContactPage.class.getName(), Messages.ContactPage_pageTitle,
				Activator.getImageDescriptor("icons/wizard_banner.png")); //$NON-NLS-1$
		setDescription(Messages.ContactPage_pageDescription);
		this.data = data;
	}

	public void createControl(Composite parent) {
		Composite control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout(2, false));

		new Label(control, SWT.NONE).setText(Messages.ContactPage_labelUser);
		txtUser = new Text(control, SWT.BORDER);
		txtUser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		new Label(control, SWT.NONE).setText(Messages.ContactPage_labelServer);
		txtServer = new Text(control, SWT.BORDER);
		txtServer.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		setControl(control);
		setPageComplete(false);

		DataBindingContext dbc = new DataBindingContext();
		dbc.bindValue(SWTObservables.observeText(txtUser, SWT.Modify),
				PojoObservables.observeValue(data, "username"), //$NON-NLS-1$
				new UpdateValueStrategy()
						.setBeforeSetValidator(new NotEmptyValidator(
								Messages.ContactPage_errorUsername)), null);
		dbc.bindValue(SWTObservables.observeText(txtServer, SWT.Modify),
				PojoObservables.observeValue(data, "server"), //$NON-NLS-1$
				new UpdateValueStrategy()
						.setBeforeSetValidator(new NotEmptyValidator(
								Messages.ContactPage_errorServer)), null);
		WizardPageSupport.create(this, dbc);
	}
}
