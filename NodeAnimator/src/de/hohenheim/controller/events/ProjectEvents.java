package de.hohenheim.controller.events;

import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableItem;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeProject;
import de.hohenheim.view.dialouge.ProjectAddDialog;
import de.hohenheim.view.dialouge.ProjectEditDialog;

/**
 * The class ProjectEvents contain Methods to add, edit, delete, import and export 
 * a Project.
 * 
 * 
 * @author Arthur Kaul
 *
 */
public class ProjectEvents {
	
    /**
     * The Methode addLink add a new Item (TrainID and TimetaleID)
     *  to a Table in the ProjetAddDialog/ProjectEditDialog
     * 
     * @param add - Give the information that a Link was add in the add Dialog or in the edit Dialog
     */
	public static void addLink(boolean add) {
		
		if(add == true){
			
		    TableItem item = new TableItem(ProjectAddDialog.linkTable, SWT.NONE);
		    item.setText(0, ProjectAddDialog.comboChooseTrain.getText());
		    item.setText(1, ProjectAddDialog.comboChooseTimeTable.getText());
		    
		}else{
			
			 TableItem item = new TableItem(ProjectEditDialog.linkTable, SWT.NONE);
			    item.setText(0, ProjectEditDialog.comboChooseTrain.getText());
			    item.setText(1, ProjectEditDialog.comboChooseTimeTable.getText());
		}
	}
    
	/**
	 *  The Methode addLink remove a selected Item (TrainID and TimetaleID)
     *  from a Table in the ProjetAddDialog/ProjectEditDialog
	 * 
	 * @param add - Give the information that a Link was remove in the add Dialog or in the edit Dialog
	 */
	
	public static void removeLink(boolean add){
		
        if(add == true){
			
        	ProjectAddDialog.linkTable.remove(ProjectAddDialog.linkTable.getSelectionIndex());
		    
		}else{
			
			ProjectEditDialog.linkTable.remove(ProjectEditDialog.linkTable.getSelectionIndex());
		}
		
	}
	
	/**
	 * The Methode addProject add a new project to the MainProjectlist (Data) and to the Table in 
	 * the CompositeProject(View) 
	 *  
	 */
	public static void addProject() {
		
		 
		// Iniziales all needed Datas for a Project
		
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
		
	    // Add Project to the Mainlist 
	    
		Main.projectListAll.add(project);
		
		String trainsItem = "";
		
		for( int i = 0; i < trainList.size(); i++){
			
			trainsItem = trainsItem + trainList.get(i).getID() + "; " ;
		}
		
		String timetableItem = "";
		
        for( int i = 0; i < timetableList.size(); i++){
			
			timetableItem = timetableItem + timetableList.get(i).getId() + "; " ;
	
		}
		
        // Add Project to the Table
        
		TableItem item = new TableItem(CompositeProject.getProjectTable(), SWT.NONE);
		
		item.setText(new String[]{String.valueOf(id), name, trainsItem, timetableItem});
		
		ProjectAddDialog.dialog.close();
		
		
	}

	public static void editProject(){
		
		ArrayList<TrainData> trainsID = new ArrayList<TrainData>();
		ArrayList<Timetable> timetableID = new ArrayList<Timetable>();
		
		TableItem [] rowData = CompositeProject.getProjectTable().getSelection();
		
    	String idText = ProjectEditDialog.idText.getText();
	    int id = Integer.parseInt(idText);
	
    	int idCheck = Integer.parseInt(rowData[0].getText(0));
	
	    for (Project p : Main.projectListAll){
		
		    if (idCheck == p.getId()){
			    p.setId(id);
			    p.setName(ProjectEditDialog.nameText.getText());
			   
			    for(int i = 0; i < ProjectEditDialog.linkTable.getItemCount(); i++){
			    	
			    	for(int j = 0; j < Main.trainListAll.size(); j++){
			    		TrainData td = Main.trainListAll.get(j);
			    		if(Integer.parseInt(ProjectEditDialog.linkTable.getItem(i).getText(0)) == td.getID()){
			    			trainsID.add(td);
			    		}
			    	}
			    	
			    	for(int j = 0; j < Main.timetableListAll.size(); j++){
			    		Timetable tt = Main.timetableListAll.get(j);
			    		if(Integer.parseInt(ProjectEditDialog.linkTable.getItem(i).getText(1)) == tt.getId()){
			    			timetableID.add(tt);
			    		}
			    	}
					
			    }
			    p.setTimeTableProjectList(timetableID);
			    p.setTraindataProjectList(trainsID);
	    	}
	    }
    
        String trainsItem = "";
		
		for( int i = 0; i < trainsID.size(); i++){
			
			trainsItem = trainsItem + trainsID.get(i).getID() + "; " ;
		}
		
		String timetableItem = "";
		
        for( int i = 0; i < timetableID.size(); i++){
			
			timetableItem = timetableItem + timetableID.get(i).getId() + "; " ;
	
		}
	    
	    rowData[0].setText(0, idText);
	    rowData[0].setText(1, ProjectEditDialog.nameText.getText());
 	    rowData[0].setText(2, trainsItem);
 	    rowData[0].setText(3, timetableItem);
 	  
 	    	    
 	   ProjectEditDialog.dialog.close();
		
		
	}
	
	/**
	 *  Methode deletProject delete an exist Project from the Mainlist and from the 
	 *  Table in the CompositeProject
	 * 
	 */
	public static void deletProject() {
		
		int i = 0;
    	
		TableItem [] rowData = CompositeProject.getProjectTable().getSelection();
		
    	while(Integer.parseInt((rowData[0].getText(0))) != ((Main.projectListAll.get(i).getId()))){
	    	i++;
    	}
	
	    Main.projectListAll.remove(i);
	
    	CompositeProject.getProjectTable().remove(CompositeProject.getProjectTable().getSelectionIndices()); 
		
	}

}
