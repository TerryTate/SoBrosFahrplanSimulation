package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import de.hohenheim.controller.main.Main;

/**
 * The class AboutUsDialog displays a message box including all informations
 * about the application and the constructors.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class AboutUsDialog extends Dialog {

	Shell parent;

	/**
	 * A Constructor for a new AboutUs dialog.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public AboutUsDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}

	/**
	 * The method open contains informations about the application and also
	 * about the constructors.
	 */
	public void open() {

		// Message Box of About Us

		MessageBox messageBox = new MessageBox(Main.getMainShell(),
				SWT.ICON_INFORMATION | SWT.YES);
		messageBox
				.setMessage("Fahrplan Animation"
						+ "\r\n"
						+ "\r\n"
						+ "Version: 1.0"
						+ "\r\n"
						+ "(c) Copyright SoBros contributors, 2013."
						+ "\r\n"
						+ "\r\n"
						+ "All rights reserved. "
						+ "\r\n"
						+ "\r\n"
						+ "This product includes software developed by:"
						+ "\r\n"
						+ "Besim Gashi, Arthur Kaul, Daniel Intili, Matthias Zwiesele and Bernd Hofs‰ﬂ"
						+ "\r\n" + "\r\n" + "MatrikelNr: " + "\r\n"
						+ "2739010, 2725383, 2716596, 2716389, 2738846");

		messageBox.setText("About Us");
		int response = messageBox.open();
		if (response == SWT.YES)
			System.exit(0);

	}

}
