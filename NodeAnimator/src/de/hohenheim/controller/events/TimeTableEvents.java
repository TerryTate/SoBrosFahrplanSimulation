package de.hohenheim.controller.events;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.dialouge.TimetableAddDialog;
import de.hohenheim.view.dialouge.TimetableDeletDialog;
import de.hohenheim.view.dialouge.TimetableEditDialog;

/**
 * The class TimetableEvents contain Methods to add, edit, delete, import and
 * export a Timetable.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class TimeTableEvents {

	/**
	 * The method addMiddleStation add a new Middlestation to a Table in the
	 * TimetableAddDialog if the boolean add is true else to the
	 * TimetableEditDialog
	 * 
	 * @param add
	 *            - boolean true if TimetableAddDialog false if
	 *            TimetableEditDialog
	 */
	public static void addMiddleStation(boolean add) {

		if (add == true) {

			TableItem item = new TableItem(
					TimetableAddDialog.midlestationTable, SWT.NONE);

			item.setText(TimetableAddDialog.comboMiddlestation.getText());

		} else if (add == false) {

			TableItem item = new TableItem(
					TimetableEditDialog.midlestationTable, SWT.NONE);
			item.setText(TimetableEditDialog.comboMiddlestation.getText());

		}

	}

	/**
	 * The method removeMiddleStation remove a selected Middlestation from the
	 * MiddlestationTable in the TimetableAddDialog if add is true else in the
	 * TimetableEditDialog
	 * 
	 * @param add
	 *            - boolean true if TimetableAddDialog false if
	 *            TimetableEditDialog
	 */
	public static void removeMiddleStation(boolean add) {

		if (add == true) {
			TimetableAddDialog.midlestationTable
					.remove(TimetableAddDialog.midlestationTable
							.getSelectionIndex());
		} else if (add == false) {
			TimetableEditDialog.midlestationTable
					.remove(TimetableEditDialog.midlestationTable
							.getSelectionIndex());
		}

	}

	/**
	 * The method addTimetable add a new timetable to the MainTimetablelist
	 * (Data) and to the Table in the CompositeTimetable(View)
	 * 
	 */
	public static void addTimeTable() {

		int id = Integer.parseInt(TimetableAddDialog.idText.getText());

		String name = TimetableAddDialog.fahrplannameText.getText();

		int h = Integer.parseInt(TimetableAddDialog.houre.getText());

		int m = Integer.parseInt(TimetableAddDialog.minutes.getText());

		ArrayList<String> drivingdays = new ArrayList<String>();

		if (TimetableAddDialog.alle.getSelection() == true) {
			drivingdays.add("Mo");
			drivingdays.add("Di");
			drivingdays.add("Mi");
			drivingdays.add("Do");
			drivingdays.add("Fr");
			drivingdays.add("Sa");
			drivingdays.add("So");
		} else {
			if (TimetableAddDialog.montag.getSelection() == true) {
				drivingdays.add("Mo");
			}
			if (TimetableAddDialog.dienstag.getSelection() == true) {
				drivingdays.add("Di");
			}
			if (TimetableAddDialog.mittwoch.getSelection() == true) {
				drivingdays.add("Mi");
			}
			if (TimetableAddDialog.donerstag.getSelection() == true) {
				drivingdays.add("Do");
			}
			if (TimetableAddDialog.freitag.getSelection() == true) {
				drivingdays.add("Fr");
			}
			if (TimetableAddDialog.samstag.getSelection() == true) {
				drivingdays.add("Sa");
			}
			if (TimetableAddDialog.sontag.getSelection() == true) {
				drivingdays.add("So");
			}
		}

		int startstation = Integer
				.parseInt(TimetableAddDialog.comboStartstation.getText());
		int endstation = Integer.parseInt(TimetableAddDialog.comboEndstation
				.getText());

		ArrayList<Integer> middlestation = new ArrayList<Integer>();
		for (int i = 0; i < TimetableAddDialog.midlestationTable.getItemCount(); i++) {
			middlestation.add(Integer
					.parseInt(TimetableAddDialog.midlestationTable.getItem(i)
							.getText(0)));
		}

		Timetable timetable = new Timetable(id, drivingdays, name,
				startstation, endstation, middlestation, h, m, 0);
		Main.getTimetableListAll().add(timetable);

		String drivingdaysItem = "";

		for (int i = 0; i < drivingdays.size(); i++) {

			drivingdaysItem = drivingdaysItem + drivingdays.get(i) + "; ";
		}

		String middleStationItems = "";

		for (int i = 0; i < middlestation.size(); i++) {

			middleStationItems = middleStationItems + middlestation.get(i)
					+ " --> ";

		}

		String time = "";

		if ((h < 10) && (m < 10)) {

			time = ("0" + h + " : " + "0" + m);

		} else if ((h >= 10) && (m < 10)) {

			time = (h + " : " + "0" + m);
		} else if ((h >= 10) && (m >= 10)) {

			time = (h + " : " + m);
		} else if ((h < 10) && (m >= 10)) {

			time = ("0" + h + " : " + m);
		}

		TableItem item = new TableItem(CompositeTimeTable.getTimeTableTable(),
				SWT.NONE);

		item.setText(new String[] { String.valueOf(timetable.getId()),
				timetable.getName(), time, drivingdaysItem,
				String.valueOf(timetable.getStartstation()),
				String.valueOf(timetable.getEndstation()), middleStationItems });

		TimetableAddDialog.dialog.close();

	}

	/**
	 * The Method editTimetable edit a existing Timetable in the Mainlist and in
	 * the Table.
	 * 
	 * @param menu
	 *            - boolean if true it is a chosen one from a comboBox if false
	 *            it is an selected one
	 */
	public static void editTimeTable(boolean menu) {

		if (menu == false) {

			ArrayList<String> drivingdays = new ArrayList<String>();
			ArrayList<Integer> middlestation = new ArrayList<Integer>();

			TableItem[] rowData = CompositeTimeTable.getTimeTableTable()
					.getSelection();

			String idText = TimetableEditDialog.idText.getText();
			int id = Integer.parseInt(idText);

			int idCheck = Integer.parseInt(rowData[0].getText(0));

			for (Timetable tt : Main.getTimetableListAll()) {

				if (idCheck == tt.getId()) {
					tt.setId(id);
					tt.setName(TimetableEditDialog.fahrplannameText.getText());
					tt.setStartHouer(Integer.parseInt(TimetableEditDialog.houre
							.getText()));
					tt.setStartMinutes(Integer
							.parseInt(TimetableEditDialog.minutes.getText()));

					if (TimetableEditDialog.alle.getSelection() == true) {
						drivingdays.add("Mo");
						drivingdays.add("Di");
						drivingdays.add("Mi");
						drivingdays.add("Do");
						drivingdays.add("Fr");
						drivingdays.add("Sa");
						drivingdays.add("So");
					} else {

						if (TimetableEditDialog.montag.getSelection() == true) {
							drivingdays.add("Mo");
						}

						if (TimetableEditDialog.dienstag.getSelection() == true) {
							drivingdays.add("Di");
						}

						if (TimetableEditDialog.mittwoch.getSelection() == true) {
							drivingdays.add("Mi");
						}

						if (TimetableEditDialog.donerstag.getSelection() == true) {
							drivingdays.add("Do");
						}

						if (TimetableEditDialog.freitag.getSelection() == true) {
							drivingdays.add("Fr");
						}

						if (TimetableEditDialog.samstag.getSelection() == true) {
							drivingdays.add("Sa");
						}

						if (TimetableEditDialog.sontag.getSelection() == true) {
							drivingdays.add("So");
						}

					}
					tt.setDrivingdays(drivingdays);
					tt.setStartstation(Integer
							.parseInt(TimetableEditDialog.comboStartstation
									.getText()));
					tt.setEndstation(Integer
							.parseInt(TimetableEditDialog.comboEndstation
									.getText()));
					for (int i = 0; i < TimetableEditDialog.midlestationTable
							.getItemCount(); i++) {

						middlestation.add(Integer
								.parseInt(TimetableEditDialog.midlestationTable
										.getItem(i).getText(0)));
					}
					tt.setMiddlestations(middlestation);
				}
			}

			int h = Integer.parseInt(TimetableEditDialog.houre.getText());
			int m = Integer.parseInt(TimetableEditDialog.minutes.getText());

			String time = "";

			if ((h < 10) && (m < 10)) {

				time = ("0" + h + " : " + "0" + m);

			} else if ((h >= 10) && (m < 10)) {

				time = (h + " : " + "0" + m);
			} else if ((h >= 10) && (m >= 10)) {

				time = (h + " : " + m);
			} else if ((h < 10) && (m >= 10)) {

				time = ("0" + h + " : " + m);
			}

			rowData[0].setText(0, idText);
			rowData[0].setText(1,
					TimetableEditDialog.fahrplannameText.getText());
			rowData[0].setText(2, time);
			String drivingdaysItem = "";
			for (int j = 0; j < drivingdays.size(); j++) {

				drivingdaysItem = drivingdaysItem + drivingdays.get(j) + "; ";
			}
			rowData[0].setText(3, drivingdaysItem);
			rowData[0].setText(4,
					TimetableEditDialog.comboStartstation.getText());
			rowData[0]
					.setText(5, TimetableEditDialog.comboEndstation.getText());

			String middleStationItems = "";

			for (int k = 0; k < middlestation.size(); k++) {

				middleStationItems = middleStationItems + middlestation.get(k)
						+ " --> ";

			}
			rowData[0].setText(6, middleStationItems);

			TimetableEditDialog.dialog.close();

		} else if (menu == true) {

			ArrayList<String> drivingdays = new ArrayList<String>();
			ArrayList<Integer> middlestation = new ArrayList<Integer>();

			for (Timetable tt : Main.getTimetableListAll()) {

				if (Integer.parseInt(TimetableEditDialog.comboTimetable
						.getText()) == tt.getId()) {

					tt.setId(Integer.parseInt(TimetableEditDialog.idText
							.getText()));
					tt.setName(TimetableEditDialog.fahrplannameText.getText());

					if (TimetableEditDialog.alle.getSelection() == true) {
						drivingdays.add("Mo");
						drivingdays.add("Di");
						drivingdays.add("Mi");
						drivingdays.add("Do");
						drivingdays.add("Fr");
						drivingdays.add("Sa");
						drivingdays.add("So");
					} else {

						if (TimetableEditDialog.montag.getSelection() == true) {
							drivingdays.add("Mo");
						}

						if (TimetableEditDialog.dienstag.getSelection() == true) {
							drivingdays.add("Di");
						}

						if (TimetableEditDialog.mittwoch.getSelection() == true) {
							drivingdays.add("Mi");
						}

						if (TimetableEditDialog.donerstag.getSelection() == true) {
							drivingdays.add("Do");
						}

						if (TimetableEditDialog.freitag.getSelection() == true) {
							drivingdays.add("Fr");
						}

						if (TimetableEditDialog.samstag.getSelection() == true) {
							drivingdays.add("Sa");
						}

						if (TimetableEditDialog.sontag.getSelection() == true) {
							drivingdays.add("So");
						}

					}
					tt.setDrivingdays(drivingdays);
					tt.setStartstation(Integer
							.parseInt(TimetableEditDialog.comboStartstation
									.getText()));
					tt.setEndstation(Integer
							.parseInt(TimetableEditDialog.comboEndstation
									.getText()));

					for (int i = 0; i < TimetableEditDialog.midlestationTable
							.getItemCount(); i++) {

						middlestation.add(Integer
								.parseInt(TimetableEditDialog.midlestationTable
										.getItem(i).getText(0)));
					}

					tt.setMiddlestations(middlestation);
					tt.setStartHouer(Integer.parseInt(TimetableEditDialog.houre
							.getText()));
					tt.setStartMinutes(Integer
							.parseInt(TimetableEditDialog.minutes.getText()));

				}
			}

			TableItem[] items = CompositeTimeTable.getTimeTableTable()
					.getItems();
			TableItem item = items[0];
			int i = 0;

			while (Integer.parseInt(TimetableEditDialog.comboTimetable
					.getText()) != Integer.parseInt(item.getText(0))) {
				i++;
				item = items[i];

			}

			int h = Integer.parseInt(TimetableEditDialog.houre.getText());
			int m = Integer.parseInt(TimetableEditDialog.minutes.getText());

			String time = "";

			if ((h < 10) && (m < 10)) {

				time = ("0" + h + " : " + "0" + m);

			} else if ((h >= 10) && (m < 10)) {

				time = (h + " : " + "0" + m);
			} else if ((h >= 10) && (m >= 10)) {

				time = (h + " : " + m);
			} else if ((h < 10) && (m >= 10)) {

				time = ("0" + h + " : " + m);
			}

			item.setText(0, TimetableEditDialog.idText.getText());
			item.setText(1, TimetableEditDialog.fahrplannameText.getText());
			item.setText(2, time);

			String drivingdaysItem = "";
			for (int j = 0; j < drivingdays.size(); j++) {

				drivingdaysItem = drivingdaysItem + drivingdays.get(j) + "; ";
			}

			String middleStationItems = "";

			for (int k = 0; k < middlestation.size(); k++) {

				middleStationItems = middleStationItems + middlestation.get(k)
						+ " --> ";

			}

			item.setText(3, drivingdaysItem);
			item.setText(4, TimetableEditDialog.comboStartstation.getText());
			item.setText(5, TimetableEditDialog.comboEndstation.getText());
			item.setText(6, middleStationItems);

			TimetableEditDialog.dialog.close();

		}

	}

	/**
	 * 
	 * Method deletTimetable delete an existing Timetable from the Mainlist and
	 * from the Table in the CompositeTimetable
	 * 
	 * @param menu
	 *            - boolean if true it is a chosen one from a comboBox if false
	 *            it is an selected one
	 */
	public static void deleteTimeTable(boolean menu) {

		if (menu == false) {

			int i = 0;

			TableItem[] rowData = CompositeTimeTable.getTimeTableTable()
					.getSelection();

			while (Integer.parseInt((rowData[0].getText(0))) != ((Main
					.getTimetableListAll().get(i).getId()))) {
				i++;
			}

			Main.getTimetableListAll().remove(i);

			CompositeTimeTable.getTimeTableTable().remove(
					CompositeTimeTable.getTimeTableTable()
							.getSelectionIndices());

		} else if (menu == true) {

			int i = 0;

			TableItem[] items = CompositeTimeTable.getTimeTableTable()
					.getItems();
			TableItem item = items[0];

			while (Integer.parseInt(TimetableDeletDialog.comboTimetables
					.getText()) != ((Main.getTimetableListAll().get(i).getId()))) {
				i++;
			}

			Main.getTimetableListAll().remove(i);

			int j = 0;

			while (Integer.parseInt(TimetableDeletDialog.comboTimetables
					.getText()) != Integer.parseInt(item.getText(0))) {

				j++;
				item = items[j];
			}

			CompositeTimeTable.getTimeTableTable().remove(j);

			TimetableDeletDialog.dialog.close();

		}

	}

	/**
	 * Load a Timetable to the Tableview and to the TimetableMainList
	 * 
	 * @param tt
	 *            - Timetable that is imported from an XMl File
	 */
	public static void importTimetable(Timetable tt) {

		if (idCheck(tt.getId())) {

			Main.getTimetableListAll().add(tt);

			String drivingdaysItem = "";

			for (int j = 0; j < tt.getDrivingdays().size(); j++) {

				drivingdaysItem = drivingdaysItem + tt.getDrivingdays().get(j)
						+ "; ";
			}

			String middleStationItems = "";

			for (int i = 0; i < tt.getMiddlestations().size(); i++) {

				middleStationItems = middleStationItems
						+ tt.getMiddlestations().get(i) + " --> ";

			}

			int h = tt.getStartHouer();
			int m = tt.getStartMinutes();

			String time = "";

			if ((h < 10) && (m < 10)) {

				time = ("0" + h + " : " + "0" + m);

			} else if ((h >= 10) && (m < 10)) {

				time = (h + " : " + "0" + m);
			} else if ((h >= 10) && (m >= 10)) {

				time = (h + " : " + m);
			} else if ((h < 10) && (m >= 10)) {

				time = ("0" + h + " : " + m);
			}

			TableItem item = new TableItem(
					CompositeTimeTable.getTimeTableTable(), SWT.NONE);
			item.setText(new String[] { String.valueOf((tt.getId())),
					tt.getName(), time, drivingdaysItem,
					String.valueOf(tt.getStartstation()),
					String.valueOf(tt.getEndstation()), middleStationItems });
		} else {
			MessageBox messageBox = new MessageBox(Main.getMainShell(),
					SWT.ERROR | SWT.OK);
			messageBox
					.setMessage("Der zu ladene Fahrplan konnte nicht geladen werden !\n"
							+ "Da der zu ladene Fahrplan die selbe ID wie ein bereits \n"
							+ "existierender Fahrplan hat!");
			messageBox.open();
		}
	}

	/**
	 * The Methode idCheck control that the id which is given as parameter not
	 * exist in the MainTimetable list
	 * 
	 * @param id
	 *            - Timetable id from the loading xml File
	 * @return boolean - return true if the id not exist else false
	 */
	private static boolean idCheck(int id) {

		boolean check = true;

		for (int j = 0; j < Main.getTimetableListAll().size(); j++) {

			if (Main.getTimetableListAll().get(j).getId() == id) {
				check = false;
			}

		}

		return check;
	}
	
	/**
	 * 
	 * Methode importAllTimetable load one or more then one Timetable into the Program when it starts
	 * 
	 */
	public static void importAllTimetable(){
		
		for(Timetable tt: Main.getTimetableListAll()){
			String drivingdaysItem = "";

			System.out.println("Fahrtaggrˆsse" + tt.getDrivingdays().size());
			for (int j = 0; j < tt.getDrivingdays().size(); j++) {

				drivingdaysItem = drivingdaysItem + tt.getDrivingdays().get(j)
						+ "; ";
			}
            System.out.println(drivingdaysItem);
			String middleStationItems = "";

			for (int i = 0; i < tt.getMiddlestations().size(); i++) {

				middleStationItems = middleStationItems
						+ tt.getMiddlestations().get(i) + " --> ";

			}

			int h = tt.getStartHouer();
			int m = tt.getStartMinutes();

			String time = "";

			if ((h < 10) && (m < 10)) {

				time = ("0" + h + " : " + "0" + m);

			} else if ((h >= 10) && (m < 10)) {

				time = (h + " : " + "0" + m);
			} else if ((h >= 10) && (m >= 10)) {

				time = (h + " : " + m);
			} else if ((h < 10) && (m >= 10)) {

				time = ("0" + h + " : " + m);
			}

			TableItem item = new TableItem(
					CompositeTimeTable.getTimeTableTable(), SWT.NONE);
			item.setText(new String[] { String.valueOf((tt.getId())),
					tt.getName(), time, drivingdaysItem,
					String.valueOf(tt.getStartstation()),
					String.valueOf(tt.getEndstation()), middleStationItems });	
		}
		
	}
}
