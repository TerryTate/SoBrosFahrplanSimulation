package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * The class TrainEditDialog contains the GUI of editing a train.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */

public class TrainEditDialog extends Dialog {

	Shell parent;

	String[] typs = { "S-Bahn", "ICE", "IC", "RegioBahn", "Güterzug",
			"Dampflock" };
	String[] speeds = { "100", "150", "200", "250", "300" };
	String[] priorities = { "Sehr wichtig", "Wichtig", "Normal", "Irrelevant" };
	String[] ladung = { "keine", "Rohstoffe", "Müll", "Fertige Produkte",
			"Brennstoffe" };
	String[] trainsID;
	int row;

	public static Text idText;
	public static Combo comboTypOfTrain;
	public static Combo comboSpeed;
	public static Combo comboPriority;
	public static Combo comboLadungen;

	public static Combo comboTrains;

	public static Shell dialog;
	String message = "";

	/**
	 * A Constructor for a new TrainEdit dialog.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public TrainEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;

	}

	/**
	 * The method open is responsible for the view elements of editing an train.
	 */
	public void open(final boolean menu) {

		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		dialog.setSize(300, 260);

		// Fenster mittig setzen
		Toolkit myToolkit = Toolkit.getDefaultToolkit();
		Dimension myDimension = myToolkit.getScreenSize();
		dialog.setLocation(
				(int) ((myDimension.getWidth() - dialog.getSize().x) / 2),
				(int) ((myDimension.getHeight() - dialog.getSize().y) / 2));

		dialog.setText("Zug bearbeiten");
		dialog.setImage(ImageHelper.edit);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		dialog.setLayout(gridLayout);

		TableItem[] rowData = CompositeTrain.getTrainTable().getSelection();

		if (menu == true) {

			System.out.println("Test");
			Label chooseTrain = new Label(dialog, SWT.NONE);
			chooseTrain.setText("Wähle Zug ID : ");

			comboTrains = new Combo(dialog, SWT.READ_ONLY);
			String[] trainsID = new String[Main.getTrainListAll().size()];
			comboTrains.setItems(loadTrainList(trainsID));
			comboTrains.select(0);
			comboTrains.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {

					setText();

				}

				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});

			GridData gridData = new GridData();
			gridData.horizontalSpan = 2;
			gridData.horizontalAlignment = SWT.FILL;
			comboTrains.setLayoutData(gridData);

		}

		// Train ID

		Label id = new Label(dialog, SWT.NONE);
		id.setText("ID : ");

		idText = new Text(dialog, SWT.NONE);

		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		idText.setLayoutData(gridData);

		// Train Type

		Label typOfTrain = new Label(dialog, SWT.NONE);
		typOfTrain.setText("ZugTyp : ");

		comboTypOfTrain = new Combo(dialog, SWT.READ_ONLY);
		comboTypOfTrain.setItems(typs);
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		comboTypOfTrain.setLayoutData(gridData);

		// Train Speed

		Label trainSpeed = new Label(dialog, SWT.NONE);
		trainSpeed.setText("Geschwindigkeit : ");

		comboSpeed = new Combo(dialog, SWT.READ_ONLY);
		comboSpeed.setItems(speeds);

		Label kmH = new Label(dialog, SWT.NONE);
		kmH.setText("km/h");

		// Train Priority

		Label priority = new Label(dialog, SWT.NONE);
		priority.setText("Priorität : ");

		comboPriority = new Combo(dialog, SWT.READ_ONLY);
		comboPriority.setItems(priorities);
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		comboPriority.setLayoutData(gridData);

		// Train Ladung

		Label ladungen = new Label(dialog, SWT.NONE);
		ladungen.setText("Ladung : ");

		comboLadungen = new Combo(dialog, SWT.READ_ONLY);
		comboLadungen.setItems(ladung);

		if (menu == false) {

			idText.setText(rowData[0].getText(0));
			comboTypOfTrain.setText(rowData[0].getText(1));
			comboSpeed.setText(rowData[0].getText(2));
			comboPriority.setText(rowData[0].getText(3));
			comboLadungen.setText(rowData[0].getText(4));

		}

		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		comboLadungen.setLayoutData(gridData);

		// Buttonn Composite

		Composite buttonComposite = new Composite(dialog, SWT.NONE);
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 2;
		buttonComposite.setLayout(gridLayout2);

		// OK Button

		Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.CENTER;
		okButton.setLayoutData(gridData);

		okButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {
				if (idCheckOk()) {

					TrainEvents.editTrain(menu);
				} else {

					MessageBox messageBox = new MessageBox(dialog, SWT.ERROR
							| SWT.OK);
					messageBox.setMessage(message);
					messageBox.open();
				}
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
		if (menu == true) {

			setText();
		}

		dialog.open();
	}

	/**
	 * The method idCheckOK prove the entries. If there is a wrong input or a
	 * blank box, exceptions will be called in terms of error messages.
	 * 
	 * @return check - if the statements are wrong, an error message will be
	 *         display
	 */
	protected boolean idCheckOk() {
		message = "";
		boolean idCheck = true;
		try {
			int id = Integer.parseInt(idText.getText());
			if (id < 0) {
				message = message
						+ "Die Zug-ID muss eine positive Zahl sein!\n" + "\r\n";
				idCheck = false;
			}

		} catch (NumberFormatException e) {
			message = message
					+ "Die Zug-ID darf nur aus Zahlen bestehen und muss mindestens \n"
					+ "eine Ziffer enthalten!" + "\r\n";

			idCheck = false;
		}

		return idCheck;
	}

	/**
	 * Set the informations in terms of text of trains in the overview
	 */
	protected void setText() {

		for (int i = 0; i < Main.getTrainListAll().size(); i++) {

			TrainData td = Main.getTrainListAll().get(i);

			if (Integer.valueOf(comboTrains.getText()) == td.getID()) {

				Integer id = Integer.valueOf(td.getID());
				Integer speed = Integer.valueOf(td.getSpeed());

				idText.setText(id.toString());
				comboTypOfTrain.setText(td.getTypOfTrain());
				comboSpeed.setText(speed.toString());
				comboPriority.setText(td.getPriority());
				comboLadungen.setText(td.getLadung());

			}
		}

	}

	/**
	 * Load the trainId's into a String[]
	 * 
	 * @param trainsID2
	 * @return trainsID2 - the id's of the trains
	 */
	private String[] loadTrainList(String[] trainsID2) {

		for (int i = 0; i < Main.getTrainListAll().size(); i++) {
			Integer id = Main.getTrainListAll().get(i).getID();
			trainsID2[i] = id.toString();
		}

		return trainsID2;
	}

}
