package de.hohenheim.controller.events;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.dialouge.TimetableAddDialog;


public class TimeTableEvents {

	public static void addMiddleStation() {
		
		TableItem item = new TableItem(TimetableAddDialog.midlestationTable, SWT.NONE);
		
		item.setText(TimetableAddDialog.comboMiddlestation.getText());
		
		
	}

	public static void removeMiddleStation() {
		
		
		TimetableAddDialog.midlestationTable.remove(TimetableAddDialog.midlestationTable.getSelectionIndex());
		
	}

	public static void addTimeTable() {
		
		int id = Integer.parseInt(TimetableAddDialog.idText.getText());
		
		String name = TimetableAddDialog.fahrplannameText.getText();
		ArrayList<String> drivingdays = new ArrayList<String>();
		
		int startstation = Integer.parseInt(TimetableAddDialog.comboStartstation.getText());
		int endstation = Integer.parseInt(TimetableAddDialog.comboEndstation.getText());	
		ArrayList<Integer> middlestation = new ArrayList<Integer>();
		
		
		
	    for(int i = 0; i < TimetableAddDialog.midlestationTable.getItemCount(); i++){
	    	middlestation.add(Integer.parseInt(TimetableAddDialog.midlestationTable.getItem(i).getText(0)));
	    }
		
		
		Timetable timetable = new Timetable(id, drivingdays, name, startstation, endstation, middlestation);
		
		Main.timetableListAll.add(timetable);
		
		
		TableItem item = new TableItem(CompositeTimeTable.getTimeTableTable(), SWT.NONE);
		
		String drivingdaysItem = "";
		
		for( int i = 0; i < drivingdays.size(); i++){
			
			drivingdaysItem = drivingdaysItem + drivingdays.get(0) + "\n" ;
		}
		
		String middleStationItems = "";
		
        for( int i = 0; i < middlestation.size(); i++){
			
			middleStationItems = middleStationItems + middlestation.get(0) + "\n" ;
	
		}
		
		System.out.println(middleStationItems);
		
		item.setText(new String[]{String.valueOf(timetable.getId()),
				timetable.getName(),drivingdaysItem
				, String.valueOf(timetable.getStartstation()), String.valueOf(timetable.getEndstation()), middleStationItems});
		
		TimetableAddDialog.dialog.close();
		
	}

	public static void editTimeTable() {
		// TODO Auto-generated method stub
		
	}

	public static void deleteTimeTable() {
		// TODO Auto-generated method stub
		
	}

}
