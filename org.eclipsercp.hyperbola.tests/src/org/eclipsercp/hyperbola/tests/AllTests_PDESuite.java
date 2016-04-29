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
package org.eclipsercp.hyperbola.tests;

import org.eclipsercp.hyperbola.ContactsView_PDETest;
import org.eclipsercp.hyperbola.model.ContactGroup_Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/*
 * If the tests are designed to only run in the workspace, a test
 * suite is not necessary. Test suites usually are run from a 
 * continuous integration server. 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ContactsView_PDETest.class, ContactGroup_Test.class })
public class AllTests_PDESuite {

}
