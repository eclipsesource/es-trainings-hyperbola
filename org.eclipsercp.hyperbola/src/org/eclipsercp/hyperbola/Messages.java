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

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipsercp.hyperbola.messages"; //$NON-NLS-1$

	public static String AddContactAction_text;
	public static String AddContactAction_tooltip;
	public static String AddContactWizard_windowTitle;
	public static String ApplicationActionBarAdvisor_fileMenu;
	public static String ApplicationActionBarAdvisor_helpMenu;
	public static String ApplicationWorkbenchWindowAdvisor_appTitle;

	public static String ChatAction_text;
	public static String ChatAction_tooltip;
	public static String ChatEditor_btnSend;
	public static String ChatEditorInput_tooltip;

	public static String ContactPage_errorServer;
	public static String ContactPage_errorUsername;
	public static String ContactPage_labelServer;
	public static String ContactPage_labelUser;
	public static String ContactPage_pageDescription;
	public static String ContactPage_pageTitle;

	public static String ContactsView_groupFriends;
	public static String ContactsView_groupOther;
	public static String ContactsView_itemsSelected;

	public static String NicknamePage_errorMsg;
	public static String NicknamePage_label;
	public static String NicknamePage_pageDescription;
	public static String NicknamePage_pageTitle;

	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
