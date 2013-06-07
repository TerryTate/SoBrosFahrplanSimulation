package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;

public class TimetableDeletDialog extends Dialog {
	
	Shell parent; 

	public TimetableDeletDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}

	public void open() {
			
		final Shell deleteTimeTable = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    deleteTimeTable.setSize(220, 310);
	    deleteTimeTable.setText("Fahrplan löschen");
			
		    
		deleteTimeTable.open();
	}

}
