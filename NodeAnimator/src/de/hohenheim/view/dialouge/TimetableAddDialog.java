package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;

public class TimetableAddDialog extends Dialog{
	
	Shell parent; 

	public TimetableAddDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open() {
			
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(220, 310);
	    dialog.setText("Fahrplan hinzuf�gen");
			
		    
		dialog.open();
	}
}
