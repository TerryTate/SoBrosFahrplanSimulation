package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;

public class TimetableEditDialog extends Dialog{
	
	Shell parent;

	public TimetableEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
	
	 public void open() {
			
		final Shell editTimeTable = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    editTimeTable.setSize(220, 310);
	    editTimeTable.setLocation(500, 200);
	    editTimeTable.setText("Fahrplan bearbeiten");
			
		    
		editTimeTable.open();
	}
}
