package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * The class TrainDeleteDialog contains the GUI of deleting a train
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */

public class TrainDeletDialog extends Dialog {

	Shell parent;
	public static Combo comboTrains;
	public static Shell dialog;

	/**
	 * A Constructor for a new TrainDelete dialog.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public TrainDeletDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;

	}

	/**
	 * The method open is responsible for the view elements of deleting a train.
	 */
	public void open(final boolean menu) {

		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		dialog.setSize(215, 130);

		// Set the window in the middle

		Toolkit myToolkit = Toolkit.getDefaultToolkit();
		Dimension myDimension = myToolkit.getScreenSize();
		dialog.setLocation(
				(int) ((myDimension.getWidth() - dialog.getSize().x) / 2),
				(int) ((myDimension.getHeight() - dialog.getSize().y) / 2));

		dialog.setText("Zug löschen");
		dialog.setImage(ImageHelper.delete);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		dialog.setLayout(gridLayout);

		Label chooseTrain = new Label(dialog, SWT.NONE);
		chooseTrain.setText("Wähle Zug ID : ");

		comboTrains = new Combo(dialog, SWT.READ_ONLY);
		String[] trainsID = new String[Main.getTrainListAll().size()];
		comboTrains.setItems(loadTrainList(trainsID));
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		comboTrains.setLayoutData(gridData);
		comboTrains.select(0);

		// OK Button

		Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.CENTER;
		okButton.setLayoutData(gridData);

		okButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				TrainEvents.deleteTrain(menu);

			}
		});

		// Cancel Button

		Button cancelButton = new Button(dialog, SWT.NONE);
		cancelButton.setText("Cancel");
		cancelButton.setImage(ImageHelper.cancel);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.CENTER;
		cancelButton.setLayoutData(gridData);

		cancelButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				dialog.close();

			}
		});

		dialog.open();
	}

	/**
	 * Load the trainId's into a String[]
	 * 
	 * @param trainsID
	 * @return trainsID - the id's of the trains
	 */
	private String[] loadTrainList(String[] trainsID) {

		for (int i = 0; i < Main.getTrainListAll().size(); i++) {
			Integer id = Main.getTrainListAll().get(i).getID();
			trainsID[i] = id.toString();
		}

		return trainsID;
	}
}
