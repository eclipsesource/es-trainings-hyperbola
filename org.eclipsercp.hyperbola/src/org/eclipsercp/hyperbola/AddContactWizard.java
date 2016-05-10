package org.eclipsercp.hyperbola;

import org.eclipse.jface.wizard.Wizard;
import org.eclipsercp.hyperbola.model.ContactsEntry;
import org.eclipsercp.hyperbola.model.ContactsGroup;

/** 
 * A wizard class that adds contacts to a contact group
 */
public class AddContactWizard extends Wizard {

  private NicknamePage nicknamePage;
  private ContactPage contactPage;
  private final ContactsGroup group;

  public AddContactWizard( ContactsGroup group ) {
    this.group = group;
  }

  public void addPages() {
    contactPage = new ContactPage();
    nicknamePage = new NicknamePage();
    addPage( contactPage );
    addPage( nicknamePage );
//    setHelpAvailable( true );
  }

  public boolean performFinish() {
    ContactsEntry entry = new ContactsEntry( group,
                                       contactPage.getUserId(),
                                       nicknamePage.getNickname(),
                                       contactPage.getServer() );
    group.addEntry( entry );
    return true;
  }
}
