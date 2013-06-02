package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Shell;

public class TrainDeletDialog extends Dialog {
	
	Shell parent;

	public TrainDeletDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}

    public void open() {
		
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(220, 310);
	    dialog.setText("Zug löschen");
		
	    
	    dialog.open();
	}
}
