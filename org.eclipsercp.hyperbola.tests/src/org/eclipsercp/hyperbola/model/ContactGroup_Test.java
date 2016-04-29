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
package org.eclipsercp.hyperbola.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * This is a normal JUnit test. It is identified by the _Test suffix. It will
 * also run as a PDE Test, but it's faster to just run it as JUnit tests.
 */
public class ContactGroup_Test {

	@Test
	public void testAddEntry() {
		ContactsGroup group = new ContactsGroup(null, "test group");

		assertEquals(0, group.getEntries().length);

		ContactsEntry entry = new ContactsEntry(group, "name", "nick", "server");
		group.addEntry(entry);

		assertEquals(1, group.getEntries().length);
		assertEquals(entry, group.getEntries()[0]);
	}
}
