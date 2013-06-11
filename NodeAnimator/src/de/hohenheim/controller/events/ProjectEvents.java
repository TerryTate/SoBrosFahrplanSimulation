package de.hohenheim.controller.events;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeProject;
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.dialouge.ProjectAddDialog;
import de.hohenheim.view.dialouge.TimetableAddDialog;


public class ProjectEvents {

	public static void addLink() {
		
		TableItem item = new TableItem(ProjectAddDialog.linkTable, SWT.NONE);
		
		item.setText(0, ProjectAddDialog.comboChooseTrain.getText());
		item.setText(1, ProjectAddDialog.comboChooseTimeTable.getText());
		
	}

	public static void addProject() {
		
		int id = Integer.parseInt(ProjectAddDialog.idText.getText());
        String name = ProjectAddDialog.nameText.getText();
		
		ArrayList<TrainData> trainList = new ArrayList<TrainData>();
		ArrayList<Timetable> timetableList = new ArrayList<Timetable>();
		
	    for(int i = 0; i < ProjectAddDialog.linkTable.getItemCount(); i++){
	    	
	    	for(int j = 0; j < Main.trainListAll.size(); j++){
	    		TrainData td = Main.trainListAll.get(j);
	    		if(Integer.parseInt(ProjectAddDialog.linkTable.getItem(i).getText(0)) == td.getID()){
	    			trainList.add(td);
	    		}
	    	}
	    	
	    	for(int j = 0; j < Main.timetableListAll.size(); j++){
	    		Timetable tt = Main.timetableListAll.get(j);
	    		if(Integer.parseInt(ProjectAddDialog.linkTable.getItem(i).getText(1)) == tt.getId()){
	    			timetableList.add(tt);
	    		}
	    	}
	    	
	    }
		
	    Project project = new Project(id, name, trainList, timetableList);
		
		Main.projectListAll.add(project);
		
		String trainsItem = "";
		
		for( int i = 0; i < trainList.size(); i++){
			
			trainsItem = trainsItem + trainList.get(i).getID() + "; " ;
		}
		
		String timetableItem = "";
		
        for( int i = 0; i < timetableList.size(); i++){
			
			timetableItem = timetableItem + timetableList.get(0).getId() + "; " ;
	
		}
		
		TableItem item = new TableItem(CompositeProject.getProjectTable(), SWT.NONE);
		
		item.setText(new String[]{String.valueOf(id), name, trainsItem, timetableItem});
		
		ProjectAddDialog.dialog.close();
		
		
	}

}
