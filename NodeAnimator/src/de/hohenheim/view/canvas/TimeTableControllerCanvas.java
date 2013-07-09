package de.hohenheim.view.canvas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import de.hohenheim.controller.events.CentralEventController;
import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.main.Main;

import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * This class is responsible for the GUI of the TimeTableControllerCanvas.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */
public class TimeTableControllerCanvas extends Canvas {

	private static Group groupControlSmall;

	/**
	 * A Constructor for a new TimeTableControllerCanvas.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public TimeTableControllerCanvas(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	/**
	 * Create the content of the new TimeTableControllerCanvas
	 */
	private void createContent() {

		groupControlSmall = (new Group(this, SWT.SHADOW_ETCHED_IN));
		groupControlSmall.setText("Fahrplan Verwaltung");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		groupControlSmall.setLayout(gridLayout);

		Button newTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		newTimeTable2.setText("Fahrplan hinzufügen");
		newTimeTable2.setImage(ImageHelper.add);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		newTimeTable2.setLayoutData(gridData);
		newTimeTable2.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				CentralEventController.openAddDialog(1);

			}
		});

		Button editTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		editTimeTable2.setText("Fahrplan bearbeiten");
		editTimeTable2.setImage(ImageHelper.editF);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		editTimeTable2.setLayoutData(gridData);
		editTimeTable2.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				if (Main.getTimetableListAll().size() > 0) {
					boolean showText = false;

					for (int i = 0; i < Main.getTimetableListAll().size(); i++) {

						if (CompositeTimeTable.getTimeTableTable()
								.isSelected(i)) {
							CentralEventController.openEditDialog(false, 1);
							showText = true;
						}
					}

					if (showText == false) {
						MessageBox messageBox = new MessageBox(Main
								.getMainShell(), SWT.ERROR | SWT.OK);
						messageBox
								.setMessage("Sie haben keinen Fahrplan gewählt!"
										+ "\r\n"
										+ "\r\n"
										+ "Wählen Sie einen Fahrplan aus und drücken Sie auf Ok.");
						messageBox.open();
					}
				} else {
					MessageBox messageBox = new MessageBox(Main.getMainShell(),
							SWT.ERROR | SWT.OK);
					messageBox
							.setMessage("Es sind keine Fahrpläne vorhanden, die bearbeitet werden können!");
					messageBox.open();
				}

			}
		});

		Button deleteTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		deleteTimeTable2.setText("Fahrplan löschen");
		deleteTimeTable2.setImage(ImageHelper.delete);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		deleteTimeTable2.setLayoutData(gridData);
		deleteTimeTable2.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				if (Main.getTimetableListAll().size() > 0) {
					boolean showText = false;

					for (int i = 0; i < Main.getTimetableListAll().size(); i++) {

						if (CompositeTimeTable.getTimeTableTable()
								.isSelected(i)) {
							TimeTableEvents.deleteTimeTable(false);
							showText = true;
						}
					}

					if (showText == false) {
						MessageBox messageBox = new MessageBox(Main
								.getMainShell(), SWT.ERROR | SWT.OK);
						messageBox
								.setMessage("Sie haben keinen Fahrplan gewählt!"
										+ "\r\n"
										+ "\r\n"
										+ "Wählen Sie einen Fahrplan aus und drücken Sie auf Ok.");
						messageBox.open();
					}
				} else {
					MessageBox messageBox = new MessageBox(Main.getMainShell(),
							SWT.ERROR | SWT.OK);
					messageBox
							.setMessage("Es sind keine Fahrpläne vorhanden, die gelöscht werden können !");
					messageBox.open();
				}

			}
		});

		Button importTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		importTimeTable2.setText("Fahrplan Importieren");
		importTimeTable2.setImage(ImageHelper.importPic);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		importTimeTable2.setLayoutData(gridData);
		importTimeTable2.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				CentralEventController.open(1);

			}
		});

		Button exportTimeTable2 = new Button(groupControlSmall, SWT.NONE);
		exportTimeTable2.setText("Fahrplan Exportieren");
		exportTimeTable2.setImage(ImageHelper.export);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		exportTimeTable2.setLayoutData(gridData);
		exportTimeTable2.addListener(SWT.Selection, new Listener() {

			public void handleEvent(Event arg0) {

				if (Main.getTimetableListAll().size() > 0) {
					boolean showText = false;

					for (int i = 0; i < Main.getTimetableListAll().size(); i++) {

						if (CompositeTimeTable.getTimeTableTable()
								.isSelected(i)) {
							CentralEventController.save(false, 1);
							showText = true;
						}
					}

					if (showText == false) {
						MessageBox messageBox = new MessageBox(Main
								.getMainShell(), SWT.ERROR | SWT.OK);
						messageBox
								.setMessage("Sie haben keinen Fahrplan gewählt!"
										+ "\r\n"
										+ "\r\n"
										+ "Wählen Sie einen Fahrplan aus und drücken Sie auf Ok.");
						messageBox.open();
					}
				} else {
					MessageBox messageBox = new MessageBox(Main.getMainShell(),
							SWT.ERROR | SWT.OK);
					messageBox
							.setMessage("Es sind keine Fahrpläne vorhanden, die exportiert werden können!");
					messageBox.open();
				}

			}
		});

		groupControlSmall.pack();

	}

}
