package de.hohenheim.view.dialouge;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.eclipse.swt.SWT;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.ProjectEvents;
import de.hohenheim.controller.main.Main;

import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;

import de.hohenheim.view.composite.CompositeProject;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * The class ProjectEditDialog contains the GUI of editing a project.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */
public class ProjectEditDialog extends Dialog {

	Shell parent;
	public static Text idText;
	public static Text nameText;
	public static Combo comboChooseTrain;
	public static Combo comboChooseTimeTable;
	String message = "";
	public static Shell dialog;

	public static Table linkTable;

	/**
	 * A Constructor for a new ProjectEdit dialog.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public ProjectEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}

	/**
	 * The method open contains view elements of editing a project
	 */
	public void open() {

		dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		dialog.setSize(325, 295);

		// Set the window in the middle of the window

		Toolkit myToolkit = Toolkit.getDefaultToolkit();
		Dimension myDimension = myToolkit.getScreenSize();
		dialog.setLocation(
				(int) ((myDimension.getWidth() - dialog.getSize().x) / 2),
				(int) ((myDimension.getHeight() - dialog.getSize().y) / 2));

		dialog.setText("Projekt bearbeiten");
		dialog.setImage(ImageHelper.edit);

		TableItem[] rowData = CompositeProject.getProjectTable().getSelection();

		// Project ID

		Label id = new Label(dialog, SWT.NONE);
		id.setText("ID : ");
		id.setBounds(10, 10, 100, 15);

		idText = new Text(dialog, SWT.NONE);
		idText.setBounds(110, 10, 100, 15);
		// Project Name

		Label name = new Label(dialog, SWT.NONE);
		name.setText("Name : ");
		name.setBounds(10, 30, 100, 15);

		nameText = new Text(dialog, SWT.NONE);
		nameText.setBounds(110, 30, 100, 15);

		// Choose Train

		Label chooseTrain = new Label(dialog, SWT.NONE);
		chooseTrain.setText("Zug wählen : ");
		chooseTrain.setBounds(10, 55, 100, 15);

		comboChooseTrain = new Combo(dialog, SWT.READ_ONLY);
		String[] trainsID = new String[Main.getTrainListAll().size()];
		comboChooseTrain.setItems(loadTrainList(trainsID));
		comboChooseTrain.select(0);
		comboChooseTrain.setBounds(110, 50, 100, 15);

		// Add Button

		Button addButton = new Button(dialog, SWT.NONE);
		addButton.setText("Add");
		addButton.setImage(ImageHelper.addPlus);
		addButton.setBounds(215, 50, 100, 40);
		addButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				if (linkTableCheckOk()) {

					ProjectEvents.addLink(false);

				} else {

					MessageBox messageBox = new MessageBox(dialog, SWT.ERROR
							| SWT.OK);
					messageBox.setMessage(message);
					messageBox.open();

				}

			}
		});

		// Choose TimeTable

		Label chooseTimeTable = new Label(dialog, SWT.NONE);
		chooseTimeTable.setText("Fahrplan wählen : ");
		chooseTimeTable.setBounds(10, 85, 100, 15);

		comboChooseTimeTable = new Combo(dialog, SWT.READ_ONLY);
		String[] timetableID2 = new String[Main.getTimetableListAll().size()];
		comboChooseTimeTable.setItems(loadTimetableList(timetableID2));
		comboChooseTimeTable.select(0);
		comboChooseTimeTable.setBounds(110, 80, 100, 15);

		// Remove Button

		Button removeButton = new Button(dialog, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setImage(ImageHelper.remove);
		removeButton.setBounds(215, 95, 100, 40);
		removeButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				if (linkTable.getItemCount() > 0) {
					boolean showText = false;

					for (int i = 0; i < linkTable.getItemCount(); i++) {

						if (linkTable.isSelected(i)) {
							ProjectEvents.removeLink(false);
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

		// Linked List

		linkTable = new Table(dialog, SWT.NONE);
 
		linkTable.setLinesVisible(true);
        linkTable.setBounds(55, 110, 150, 100); 
 		TableColumn trains = new TableColumn(linkTable, SWT.None);
		TableColumn timetable = new TableColumn(linkTable, SWT.NONE);
		trains.setWidth(65);
		timetable.setWidth(65);

		// OK Button

		Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(ImageHelper.ok);
		okButton.setBounds(60, 220, 100, 40);

		okButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {
				if (projectCheckOk()) {
					ProjectEvents.editProject();
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
		cancelButton.setBounds(165, 220, 100, 40);

		cancelButton.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				dialog.close();

			}
		});

		for (int i = 0; i < Main.getProjectListAll().size(); i++) {

			Project p = Main.getProjectListAll().get(i);

			if (Integer.parseInt(rowData[0].getText()) == p.getId()) {

				Integer idProject = Integer.valueOf(p.getId());

				idText.setText(idProject.toString());
				nameText.setText(p.getName());

				Integer trainID;
				Integer timetableID;

				for (int j = 0; j < p.getTraindataProjectList().size(); j++) {

					TrainData td = p.getTraindataProjectList().get(j);
					trainID = Integer.valueOf(td.getID());

					TableItem item = new TableItem(linkTable, SWT.NONE);
					item.setText(0, trainID.toString());

					Timetable tt = p.getTimeTableProjectList().get(j);
					timetableID = Integer.valueOf(tt.getId());
					item.setText(1, timetableID.toString());
				}

			}
		}

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

	/**
	 * Load the timetableID's into a String[]
	 * 
	 * @param timetableID2
	 * @return timetableID2 - the id's of the timetables
	 */
	private String[] loadTimetableList(String[] timetableID2) {

		for (int i = 0; i < Main.getTimetableListAll().size(); i++) {
			Integer id = Main.getTimetableListAll().get(i).getId();
			timetableID2[i] = id.toString();
		}

		return timetableID2;
	}

	/**
	 * The method linkTableCheckOK proves the items. If there is an identical
	 * train or timetable, errors will be displays
	 * 
	 * @return check - if the statements are wrong, errors will be displays
	 */
	protected boolean linkTableCheckOk() {
		message = "";
		boolean check = true;

		Timetable tt = null;
		for(int k = 0; k < Main.getTimetableListAll().size(); k++){
			if (Integer.parseInt(comboChooseTimeTable.getText()) == Main.getTimetableListAll().get(k).getId()){ 
				tt = Main.getTimetableListAll().get(k);
			}
			
		}
		
		for (int j = 0; j < linkTable.getItemCount(); j++) {

			if (Integer.parseInt(comboChooseTrain.getText()) == Integer
					.parseInt(linkTable.getItem(j).getText(0))) {

				message = message
						+ "Der ausgewählte Zug ist schon vorhanden !\n";
				check = false;
			}

			if (Integer.parseInt(comboChooseTimeTable.getText()) == Integer
					.parseInt(linkTable.getItem(j).getText(1))) {

				message = message
						+ "Der ausgewählte Fahrplan ist schon vorhanden !\n";
				check = false;
			}

			Timetable tt2 = null;
			for(int i = 0; i < Main.getTimetableListAll().size(); i++){
				
				if (Integer.parseInt(linkTable.getItem(j).getText(1)) == Main.getTimetableListAll().get(i).getId()){ 
					tt2 = Main.getTimetableListAll().get(i);
				}
			}
			
			if(tt.getStartstation() == tt2.getStartstation()){
				
				message = message + "Es können keine zwei Fahrpläne mit der selben \n"+
				                    "Startstation in einem Projekt existieren !\n";
				check = false;
			}
		}

		return check;
	}

	/**
	 * The method projectCheckOK prove the entries. If there is a wrong input or
	 * a blank box, exceptions will be called in terms of error messages.
	 * 
	 * @return check - if the statements are wrong, an error message will be
	 *         display
	 */
	protected boolean projectCheckOk() {

		message = "";
		boolean check = true;

		try {
			if (linkTable.getItemCount() < 1) {
				message = message
						+ "Es muss mindestens ein Zug und mindestens ein Fahrplan"
						+ "\n" + "eingetragen sein!\n" + "\r\n";
				check = false;
			}

			if (nameText.getText().equalsIgnoreCase("")) {
				message = message
						+ "Bitte geben Sie einen Projekt-Namen ein!\n" + "\r\n";
				check = false;
			}

			int id = Integer.parseInt(idText.getText());
			if (id < 0) {
				message = message
						+ "Die Projekt-ID muss eine positive Zahl enthalten!\n"
						+ "\r\n";
				check = false;
			}

		} catch (NumberFormatException e) {
			message = message
					+ "Die Zug ID darf nur aus Zahlen bestehen und muss mindestens \n"
					+ "eine Ziffer enthalten!" + "\r\n";

			check = false;
		}

		return check;
	}

}
