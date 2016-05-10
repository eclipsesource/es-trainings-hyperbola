package org.eclipsercp.hyperbola;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * A wizard page that allows to enter a server and a user name
 */
public class ContactPage extends WizardPage implements IWizardPage {

  private Text serverTxt;
  private Text userTxt;

  public ContactPage() {
    super( "Contact data",
           "Enter Contact information",
           AbstractUIPlugin.imageDescriptorFromPlugin( "org.eclipsercp.hyperbola",
                                                       IImageKeys.NEW_CONTACT ) );
    setDescription( "Enter the server of the user and her name." );
  }

  public void createControl( Composite parent ) {
    Composite control = new Composite( parent, SWT.NONE );
    Label serverLbl = new Label( control, SWT.NONE );
    serverLbl.setText( "Server: " );
    serverTxt = new Text( control, SWT.BORDER );
    Label userLbl = new Label( control, SWT.NONE );
    userLbl.setText( "User: " );
    userTxt = new Text( control, SWT.BORDER );
    control.setLayout( new GridLayout( 2, false ) );
    serverTxt.setLayoutData( new GridData( SWT.FILL, SWT.CENTER, true, false ) );
    userTxt.setLayoutData( new GridData( SWT.FILL, SWT.CENTER, true, false ) );
    setControl( control );
    setPageComplete( false );
    PageCompleteSetter listener = new PageCompleteSetter();
    serverTxt.addModifyListener( listener );
    userTxt.addModifyListener( listener );
  }

  public String getUserId() {
    return userTxt.getText();
  }

  public String getServer() {
    return serverTxt.getText();
  }
  private final class PageCompleteSetter implements ModifyListener {

    public void modifyText( ModifyEvent e ) {
      if( serverTxt.getText().length() > 0 && userTxt.getText().length() > 0 ) {
        setMessage( null );
        setPageComplete( true );
      } else {
        giveMessage();
        setPageComplete( false );
      }
    }

    private void giveMessage() {
      if( serverTxt.getText().length() == 0 ) {
        setMessage( "Enter a server name", ERROR );
      } else if( userTxt.getText().length() == 0 ) {
        setMessage( "Enter the user name", ERROR );
      }
    }
  }
}
