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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.ColorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;

public class BackgroundColorAction implements IEditorActionDelegate {

	private ChatEditor editor;

	public void run(IAction action) {
		Shell shell = editor.getSite().getShell();
		ColorDialog dialog = new ColorDialog(shell);
		RGB rgb = dialog.open();
		if (rgb != null) {
			editor.setBackgroundColor(rgb);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// unused
	}

	public void setActiveEditor(IAction action, IEditorPart targetEditor) {
		editor = (ChatEditor) targetEditor;
	}

}
