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
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * A wizard page that allows to enter a nickname
 */
public class NicknamePage extends WizardPage {

  private Text text;

  public NicknamePage() {
    super( "Nickname",
           "Enter Nickname",
           AbstractUIPlugin.imageDescriptorFromPlugin( "org.eclipsercp.hyperbola",
                                                       IImageKeys.NEW_CONTACT ) );
    setDescription( "The nick name is displayed in the buddy list." );
  }

  public void createControl( Composite parent ) {
    Composite control = new Composite( parent, SWT.NONE );
    Label lbl = new Label( control, SWT.NONE );
    lbl.setText( "Nickname: " );
    text = new Text( control, SWT.BORDER );
    text.addModifyListener( new ModifyListener() {

      public void modifyText( ModifyEvent e ) {
        if( text.getText().length() > 0 ) {
          setMessage( null );
          setPageComplete( true );
        } else {
          setMessage( "Enter a nickname for your new buddy.", ERROR );
          setPageComplete( false );
        }
      }
    } );
    control.setLayout( new GridLayout( 2, false ) );
    text.setLayoutData( new GridData( SWT.FILL, SWT.CENTER, true, false ) );
    setControl( control );
    setPageComplete( false );
  }

  public String getNickname() {
    return text.getText();
  }
}
