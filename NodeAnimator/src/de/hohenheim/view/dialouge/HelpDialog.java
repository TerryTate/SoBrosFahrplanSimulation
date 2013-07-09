package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.main.Main;

/**
 * The class AboutUsDialog displays the help option for user if he has problems.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class HelpDialog extends Dialog {

	Shell parent;

	/**
	 * A Constructor for a new Help dialog.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public HelpDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}

	/**
	 * The method open contains a message box with the necessary help
	 * information.
	 */
	public void open() {

		// Message Box of HelpDialog

		MessageBox messageBox = new MessageBox(Main.getMainShell(),
				SWT.ICON_QUESTION);
		messageBox.setMessage("Bitte wenden Sie sich an Ihren Hersteller"
				+ "\r\n" + "\r\n" + "(c) Copyright SoBros");
		messageBox.setText("Help");
		messageBox.open();

	}

}
