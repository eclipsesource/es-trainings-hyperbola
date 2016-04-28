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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	private IAction exitAction;
	private IAction helpAction;
	private IAction dynamicHelpAction;
	private IAction aboutAction;
	private IAction addContactAction;
	private IAction chatAction;
	private IAction saveAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	protected void makeActions(IWorkbenchWindow window) {
		exitAction = ActionFactory.QUIT.create(window);
		register(exitAction);
		helpAction = ActionFactory.HELP_CONTENTS.create(window);
		register(helpAction);
		dynamicHelpAction = ActionFactory.DYNAMIC_HELP.create(window);
		register(dynamicHelpAction);
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
		addContactAction = new AddContactAction(window);
		register(addContactAction);
		chatAction = new ChatAction(window);
		register(chatAction);
		saveAction = ActionFactory.SAVE.create(window);
		register(saveAction);
	}

	protected void fillMenuBar(IMenuManager menuBar) {
		MenuManager fileMenu = new MenuManager("&File", "file");
		fileMenu.add(addContactAction);
		fileMenu.add(chatAction);
		fileMenu.add(saveAction);
		fileMenu.add(new GroupMarker("additions"));
		fileMenu.add(new Separator());
		fileMenu.add(exitAction);
		menuBar.add(fileMenu);

		MenuManager helpMenu = new MenuManager("&Help", "help");
		helpMenu.add(helpAction);
		helpMenu.add(dynamicHelpAction);
		helpMenu.add(new GroupMarker("additions"));
		helpMenu.add(new Separator());
		helpMenu.add(aboutAction);
		menuBar.add(helpMenu);
	}

	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		ToolBarManager toolbar = new ToolBarManager();
		toolbar.add(addContactAction);
		toolbar.add(chatAction);
		toolbar.add(saveAction);
		coolBar.add(new ToolBarContributionItem(toolbar, "hyperbola"));
	}

}
