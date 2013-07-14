package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
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
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * The class TimeTableEditDialog contains the GUI of editing a timetable
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */
public class TimetableEditDialog extends Dialog {

	Shell parent;
	public static Shell dialog;
	public static Text idText;
	public static Text fahrplannameText;
	public static Combo comboStartstation;
	public static Combo comboEndstation;
	public static Combo comboMiddlestation;
	public static Table midlestationTable;
	public static Combo comboTimetable;
	public static Spinner houre;
	public static Spinner minutes;
	public static Button montag;
	public static Button dienstag;
	public static Button mittwoch;
	public static Button donerstag;
	public static Button freitag;
	public static Button samstag;
	public static Button sontag;
	public static Button alle;
	String message = "";

	/**
	 * A Constructor for a new TimetableEdit dialog.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public TimetableEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}

	/**
	 * The method open is responsible for the view elements of editing a
	 * timetable.
	 */
	public void open(final boolean menu) {

		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		dialog.setSize(330, 460);

		// Set the window in the middle

		Toolkit myToolkit = Toolkit.getDefaultToolkit();
		Dimension myDimension = myToolkit.getScreenSize();
		dialog.setLocation(
				(int) ((myDimension.getWidth() - dialog.getSize().x) / 2),
				(int) ((myDimension.getHeight() - dialog.getSize().y) / 2));

		dialog.setText("Fahrplan bearbeiten");
		dialog.setImage(ImageHelper.edit);

		TableItem[] rowData = CompositeTimeTable.getTimeTableTable()
				.getSelection();

		if (menu == true) {

			Label chooseTimetable = new Label(dialog, SWT.NONE);
			chooseTimetable.setText("Wähle Fahrplan ID : ");
			chooseTimetable.setBounds(5, 10, 105, 25);

			comboTimetable = new Combo(dialog, SWT.READ_ONLY);
			String[] timetableID = new String[Main.getTimetableListAll().size()];
			comboTimetable.setItems(loadTimetableList(timetableID));
			comboTimetable.select(0);
			comboTimetable.setBounds(115, 5, 100, 30);

			comboTimetable.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {

					setText();

				}

				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});


		}

		// Train ID

		Label id = new Label(dialog, SWT.NONE);
		id.setText("ID : ");
		id.setBounds(5, 35, 100, 20);

		idText = new Text(dialog, SWT.NONE);
		idText.setBounds(115, 35, 100, 15);
		idText.setTextLimit(6);


		// TimetableName

		Label timeTableName = new Label(dialog, SWT.NONE);
		timeTableName.setText("Fahrplanname : ");
        timeTableName.setBounds(5, 60, 100, 15);
        
		fahrplannameText = new Text(dialog, SWT.NONE);
		fahrplannameText.setBounds(115, 60, 100, 15);

		// Set the Label and a spinner for the Starttime
		Label starttime = new Label(dialog, SWT.NONE);
		starttime.setText("Start Uhrzeit : ");
		starttime.setBounds(5, 85, 100, 15);

		houre = new Spinner(dialog, SWT.NONE);
		houre.setMaximum(23);
		houre.setBounds(115, 85, 35, 15);

		Label h = new Label(dialog, SWT.NONE);
		h.setText("  h");
		h.setBounds(150, 85, 20, 15);

		minutes = new Spinner(dialog, SWT.NONE);
		minutes.setMaximum(59);
		minutes.setBounds(170, 85, 35, 15);

		Label m = new Label(dialog, SWT.NONE);
		m.setText("m");
		m.setBounds(210, 85, 10, 15);

		// Timetable drivingDays

		Label drivingDays = new Label(dialog, SWT.NONE);
		drivingDays.setText("Fahrtage : ");
		drivingDays.setBounds(5, 110, 100, 15);

		montag = new Button(dialog, SWT.CHECK);
		montag.setText("Montag");
		montag.setBounds(115, 110, 60, 15);
		
		dienstag = new Button(dialog, SWT.CHECK);
		dienstag.setText("Dienstag");	
		dienstag.setBounds(195, 110, 70, 15);
		
		mittwoch = new Button(dialog, SWT.CHECK);
		mittwoch.setText("Mittwoch");
		mittwoch.setBounds(115, 130, 60, 15);
		
		donerstag = new Button(dialog, SWT.CHECK);
		donerstag.setText("Donerstag");
		donerstag.setBounds(195, 130, 70, 15);
		
		freitag = new Button(dialog, SWT.CHECK);
		freitag.setText("Freitag");
		freitag.setBounds(115, 150, 60, 15);
		
		samstag = new Button(dialog, SWT.CHECK);
		samstag.setText("Samstag");
		samstag.setBounds(195, 150, 70, 15);
		
		sontag = new Button(dialog, SWT.CHECK);
		sontag.setText("Sontag");
		sontag.setBounds(115, 170, 60, 15);
		
		alle = new Button(dialog, SWT.CHECK);
		alle.setText("Alle");
		alle.setBounds(195, 170, 70, 15);
		// TitmeTable StartStation

		Label startstation = new Label(dialog, SWT.NONE);
		startstation.setText("Startstation : ");
		startstation.setBounds(5, 190, 100, 15);

		comboStartstation = new Combo(dialog, SWT.READ_ONLY);
		comboStartstation.setBounds(115, 190, 100, 15);

		String[] items = AnimationControllerCanvas.getNodeNames();
		comboStartstation.setItems(items);

		// Timetable EndStation

		Label endstation = new Label(dialog, SWT.NONE);
		endstation.setText("Endstation : ");
		endstation.setBounds(5, 223, 100, 15);

		comboEndstation = new Combo(dialog, SWT.READ_ONLY);
		comboEndstation.setItems(items);
		comboEndstation.setBounds(115, 218, 100, 15);


		// Timetable MiddleStation

		Label middlestation = new Label(dialog, SWT.NONE);
		middlestation.setText("Zwischenstationen : ");
		middlestation.setBounds(5, 250, 100, 15);

		comboMiddlestation = new Combo(dialog, SWT.READ_ONLY);
		comboMiddlestation.setItems(items);
		comboMiddlestation.select(0);
		comboMiddlestation.setBounds(115, 245, 100, 15);

		// Add Button

		Button addButton = new Button(dialog, SWT.NONE);
		addButton.setText("Add");
		addButton.setImage(ImageHelper.add);
		addButton.setBounds(220, 245, 95, 40);
		;
		addButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				if (middleStationCheck()) {
					TimeTableEvents.addMiddleStation(false);
				} else {
					MessageBox messageBox = new MessageBox(dialog, SWT.ERROR
							| SWT.OK);
					messageBox.setMessage(message);
					messageBox.open();
				}

			}
		});

		// Remove Button

		Button removeButton = new Button(dialog, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setImage(ImageHelper.remove);
		removeButton.setBounds(220, 290, 95, 40);
		removeButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				if (midlestationTable.getItemCount() > 0) {
					boolean showText = false;

					for (int i = 0; i < midlestationTable.getItemCount(); i++) {

						if (midlestationTable.isSelected(i)) {
							TimeTableEvents.removeMiddleStation(false);
							showText = true;
						}
					}

					if (showText == false) {
						MessageBox messageBox = new MessageBox(dialog,
								SWT.ERROR | SWT.OK);
						messageBox
								.setMessage("Sie haben keine Zwischenstation gewählt!"
										+ "\r\n"
										+ "\r\n"
										+ "Wählen Sie einen Zwischenstation !");
						messageBox.open();
					}
				} else {
					MessageBox messageBox = new MessageBox(dialog, SWT.ERROR
							| SWT.OK);
					messageBox
							.setMessage("Es sind keine Zwischenstationen vorhanden, die gelöscht werden können!");
					messageBox.open();
				}

			}
		});

		midlestationTable = new Table(dialog, SWT.NONE);
		
		midlestationTable.setLinesVisible(true);
        midlestationTable.setBounds(115, 280, 100, 100);
		
		TableColumn midleStations = new TableColumn(midlestationTable, SWT.None);

		midleStations.setWidth(80);

		if (menu == false) {

			for (int i = 0; i < Main.getTimetableListAll().size(); i++) {

				Timetable tt = Main.getTimetableListAll().get(i);

				if (Integer.parseInt(rowData[0].getText()) == tt.getId()) {

					Integer idTimetable = Integer.valueOf(tt.getId());
					Integer startstationTimetable = Integer.valueOf(tt
							.getStartstation());
					Integer endstationTimetable = Integer.valueOf(tt
							.getEndstation());

					idText.setText(idTimetable.toString());
					fahrplannameText.setText(tt.getName());
					houre.setSelection(tt.getStartHouer());
					minutes.setSelection(tt.getStartMinutes());
					for (int j = 0; j < tt.getDrivingdays().size(); j++) {

						if (tt.getDrivingdays().get(j).equalsIgnoreCase("Mo")) {

							montag.setSelection(true);

						} else if (tt.getDrivingdays().get(j)
								.equalsIgnoreCase("Di")) {

							dienstag.setSelection(true);

						} else if (tt.getDrivingdays().get(j)
								.equalsIgnoreCase("Mi")) {

							mittwoch.setSelection(true);

						} else if (tt.getDrivingdays().get(j)
								.equalsIgnoreCase("Do")) {

							donerstag.setSelection(true);

						} else if (tt.getDrivingdays().get(j)
								.equalsIgnoreCase("Fr")) {

							freitag.setSelection(true);

						} else if (tt.getDrivingdays().get(j)
								.equalsIgnoreCase("Sa")) {

							samstag.setSelection(true);

						} else if (tt.getDrivingdays().get(j)
								.equalsIgnoreCase("So")) {

							sontag.setSelection(true);

						}
					}
					comboStartstation.setText(startstationTimetable.toString());
					comboEndstation.setText(endstationTimetable.toString());

					Integer middle;

					for (int j = 0; j < tt.getMiddlestations().size(); j++) {

						middle = Integer.valueOf(tt.getMiddlestations().get(j));

						TableItem item = new TableItem(
								TimetableEditDialog.midlestationTable, SWT.NONE);
						item.setText(middle.toString());
					}

				}
			}

		}

		
		// OK Button

		Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
		okButton.setBounds(80, 385, 100, 40);

		okButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				if (checkOk()) {
					TimeTableEvents.editTimeTable(menu);
				} else {
					MessageBox messageBox = new MessageBox(Main.getMainShell(),
							SWT.ERROR | SWT.OK);
					messageBox.setMessage(message);
					messageBox.open();
				}
			}
		});

		// Cancel Button

		Button cancelButton = new Button(dialog, SWT.NONE);
		cancelButton.setText("Cancel");
		cancelButton.setImage(ImageHelper.cancel);
		cancelButton.setBounds(185, 385, 100, 40);

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
	 * The method projectCheckOK prove the entries. If there are identical
	 * middleStations or the same as the start- or endStation, exceptions will
	 * be called in terms of error messages.
	 * 
	 * @return check - if the statements are wrong, an error message will be
	 *         display
	 */
	protected boolean middleStationCheck() {
		message = "";
		if (midlestationTable.getItemCount() > 0) {
			if (comboMiddlestation.getText().equalsIgnoreCase(
					midlestationTable.getItem(
							midlestationTable.getItemCount() - 1).getText())) {
				message = message
						+ "Es sind zwei identische Zwischenstationen vorhanden. "
						+ "Dies ist leider nicht möglich!\n" + "\r\n";
				return false;
			}
		} else {
			if (comboMiddlestation.getText().equalsIgnoreCase(
					comboStartstation.getText())) {
				message = message
						+ "Die Zwischenstation ist identisch mit der Startstation. \n"
						+ "Bitte geben Sie eine andere, nicht identische Zwischenstation ein!\n"
						+ "\r\n";
				return false;
			}
		}
		return true;

	}

	/**
	 * The method checkOK prove the entries. If there is a wrong input or a
	 * blank box in the drivingDays, exceptions will be called in terms of error
	 * messages.
	 * 
	 * @return check - if the statements are wrong, an error message will be
	 *         display
	 */
	protected boolean checkOk() {

		boolean check = true;

		message = "";

		if ((montag.getSelection() == false)
				&& (dienstag.getSelection() == false)
				&& (mittwoch.getSelection() == false)
				&& (donerstag.getSelection() == false)
				&& (freitag.getSelection() == false)
				&& (samstag.getSelection() == false)
				&& (sontag.getSelection() == false)
				&& (alle.getSelection() == false)) {
			message = message
					+ "Bitte wählen Sie mindestens einen Fahrtag aus!\n"
					+ "\r\n";
			check = false;
		}

		if (fahrplannameText.getText().equalsIgnoreCase("")) {
			message = message + "Bitte geben Sie einen Fahrplan-Name ein!\n"
					+ "\r\n";
			check = false;
		}

		if (comboStartstation.getText().equalsIgnoreCase(
				comboEndstation.getText())
				&& (midlestationTable.getItemCount() == 0)) {
			message = message
					+ "Die Startstation und Endstation dürfen nur die \n"
					+ "selben sein wenn die Zwischenstationen nicht leer sind!\n"
					+ "\r\n";
			check = false;
		}

		for (int j = 0; j < (midlestationTable.getItemCount()); j++) {
			if (j == 0) {
				if (comboStartstation.getText().equalsIgnoreCase(
						midlestationTable.getItem(j).getText())) {

					message = message
							+ "Die erste Zwischenstation ist identisch mit der Startstation. \n"
							+ "Bitte geben Sie eine andere, nicht identische Zwischenstation ein!\n"
							+ "\r\n";
					check = false;
				}

			} else {
				if (midlestationTable
						.getItem(j - 1)
						.getText()
						.equalsIgnoreCase(
								midlestationTable.getItem(j).getText())) {

					message = message
							+ "Es sind zwei identische Zwischenstationen vorhanden. \n"
							+ "Bitte löschen Sie einen dieser beiden Stationen!\n"
							+ "\r\n";
					check = false;
				}
			}

		}
		try {
			if (comboEndstation.getText().equalsIgnoreCase(
					midlestationTable.getItem(
							midlestationTable.getItemCount() - 1).getText())) {
				message = message
						+ "Die Zwischenstation darf nicht identisch mit der \n"
						+ "Endstation sein!\n" + "\r\n";

				check = false;
			}
		} catch (IllegalArgumentException e) {

		}
		try {
			int id = Integer.parseInt(idText.getText());

			if (id < 0) {
				message = message
						+ "Die Fahrplan-ID muss eine positive Zahl sein!\n"
						+ "\r\n";
				check = false;
			}

		} catch (NumberFormatException e) {

			message = message + "Die Zug ID darf nur aus Zahlen bestehen \n"
					+ "und muss mindestens eine Ziffer haben! \n" + "\r\n";
			check = false;

		}

		return check;

	}

	/**
	 * Set the entries in the timetable overview
	 */
	protected void setText() {

		for (int i = 0; i < Main.getTimetableListAll().size(); i++) {

			Timetable tt = Main.getTimetableListAll().get(i);

			if (Integer.valueOf(comboTimetable.getText()) == tt.getId()) {

				Integer id = Integer.valueOf(tt.getId());
				Integer startstation = Integer.valueOf(tt.getStartstation());
				Integer endstation = Integer.valueOf(tt.getEndstation());

				idText.setText(id.toString());
				fahrplannameText.setText(tt.getName());
				houre.setSelection(tt.getStartHouer());
				minutes.setSelection(tt.getStartMinutes());
				for (int j = 0; j < tt.getDrivingdays().size(); j++) {

					if (tt.getDrivingdays().get(j).equalsIgnoreCase("Mo")) {

						montag.setSelection(true);

					} else if (tt.getDrivingdays().get(j)
							.equalsIgnoreCase("Di")) {

						dienstag.setSelection(true);

					} else if (tt.getDrivingdays().get(j)
							.equalsIgnoreCase("Mi")) {

						mittwoch.setSelection(true);

					} else if (tt.getDrivingdays().get(j)
							.equalsIgnoreCase("Do")) {

						donerstag.setSelection(true);

					} else if (tt.getDrivingdays().get(j)
							.equalsIgnoreCase("Fr")) {

						freitag.setSelection(true);

					} else if (tt.getDrivingdays().get(j)
							.equalsIgnoreCase("Sa")) {

						samstag.setSelection(true);

					} else if (tt.getDrivingdays().get(j)
							.equalsIgnoreCase("So")) {

						sontag.setSelection(true);

					}
				}
				comboStartstation.setText(startstation.toString());
				comboEndstation.setText(endstation.toString());

				Integer middlestation;
				midlestationTable.removeAll();

				for (int j = 0; j < tt.getMiddlestations().size(); j++) {

					middlestation = Integer.valueOf(tt.getMiddlestations().get(
							j));

					TableItem item = new TableItem(midlestationTable, SWT.NONE);
					item.setText(middlestation.toString());
				}

			}

		}

	}

	/**
	 * Load the timetableID's into a String[]
	 * 
	 * @param timetableID
	 * @return timetableID - the id's of the timetables
	 */
	private String[] loadTimetableList(String[] timetableID) {

		for (int i = 0; i < Main.getTimetableListAll().size(); i++) {
			Integer id = Main.getTimetableListAll().get(i).getId();
			timetableID[i] = id.toString();
		}

		return timetableID;

	}
}
