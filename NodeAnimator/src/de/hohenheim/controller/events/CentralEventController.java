package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.controller.XmlReader;
import de.hohenheim.controller.XmlWriter;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.composite.CompositeProject;
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.dialouge.AboutUsDialog;
import de.hohenheim.view.dialouge.HelpDialog;
import de.hohenheim.view.dialouge.ProjectAddDialog;
import de.hohenheim.view.dialouge.ProjectEditDialog;
import de.hohenheim.view.dialouge.ProjectExportDialog;
import de.hohenheim.view.dialouge.TimetableAddDialog;
import de.hohenheim.view.dialouge.TimetableDeletDialog;
import de.hohenheim.view.dialouge.TimetableEditDialog;
import de.hohenheim.view.dialouge.TimetableExportDialog;
import de.hohenheim.view.dialouge.TrainAddDialog;
import de.hohenheim.view.dialouge.TrainDeletDialog;
import de.hohenheim.view.dialouge.TrainEditDialog;
import de.hohenheim.view.dialouge.TrainExportDialog;

/**
 * Class that contain different Methods to open different Dialogs
 * for edit, add, delete, import export Train/Timetable/Projects, close the Programm, 
 * show help and info Dialog. 
 * 
 * 
 * @author Arthur Kaul
 *
 */
public class CentralEventController {
    
	/**
	 *  This methode open a Dialog with a request at the User 
	 *  and close the program if the Yse Button is pushed and 
	 *  close the dialog if the no Button is pushed. 
	 * 
	 */
	public static void closeProgramm() {
		
		 MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
		 messageBox.setMessage("Wollen Sie das Programm wirklich beenden ?");
		 messageBox.setText("Programm Beenden");
		 int response = messageBox.open();
		 if (response == SWT.YES)
		 System.exit(0);
		
	}
	
    /**
     * Change the tabFolder Item to Animation, Project, Train or Timetable View
     * 
     * @param tab - index for the different TabFolders.
     */
	public static void changeLook(int tab) {
		
		if(!AnimationControllerCanvas.isRun()){
		    Main.getcTabFolder().setSelection(tab);
		}else{
			 MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
	         messageBox.setMessage("Die Ansicht kann nicht gewechselt werden \n" +
	         		               "solange eine Animation läuft!");    
	         messageBox.open();
		}
		
	}
    
	/**
	 * Open a AddDialog to add a Train or a Timetable or a Project 
	 *   open a ProjectAddDialog if addDialog = 2
	 *   open a TimetableAddDialog if addDialog = 1 
	 *   open a TrainAddDialog id addDialog = 0
	 * 
	 * @param addDialog - int witch Dialog should be open
	 */
	public static void openAddDialog(int addDialog) {
		
		if (addDialog == 0){
		
			new TrainAddDialog(Main.getMainShell(), SWT.NONE).open();
		
		}else if (addDialog == 1){
		
			new TimetableAddDialog(Main.getMainShell(), SWT.NONE).open();
		
		}else if (addDialog == 2){
			
			if((Main.getTimetableListAll().size() > 0) && (Main.getTrainListAll().size() > 0)){
				
			    new ProjectAddDialog(Main.getMainShell(), SWT.NONE).open();
			
			}else{
				String message = "";
			    if(Main.getTimetableListAll().size() < 1){
			    	
			    	message = message + "Es gibt keine Fahrpläne daher kann kein Project erstellt werden. \n";
			        
			    }
			    if (Main.getTrainListAll().size() < 1){
			    	
			    	message = message + "Es gibt keine Züge daher kann kein Project ertstellt werden.";
			    	
			    }
			    MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
		        messageBox.setMessage(message);    
		        messageBox.open();
			}
		}
		
	}
	
    /**
     * Open a EditDialog to edit a Train or a Timetable or a Project 
	 *   open a ProjectEditDialog if addDialog = 2
	 *   open a TimetableEditDialog if addDialog = 1 
	 *   open a TrainEditDialog id addDialog = 0
     * 
     * @param menu
     * @param editDialog - int witch Dialog should be open
     */
	public static void openEditDialog(boolean menu, int editDialog) {
		
		if (editDialog == 0){
		    
			if (Main.getTrainListAll().size() > 0){
			
			    new TrainEditDialog(Main.getMainShell(), SWT.NONE).open(menu);
			
		    }else{
			
			    MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
	            messageBox.setMessage("Es gibt keine Züge die Bearbeitet werden können.");    
	            messageBox.open();
			
		    }
	
		}else if (editDialog == 1){
			
			if (Main.getTimetableListAll().size() > 0){
			    
	        	new TimetableEditDialog(Main.getMainShell(), SWT.NONE).open(menu);
			
	        }else{
				
				MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
		        messageBox.setMessage("Es gibt keine Fahrpläne die Bearbeitet werden können.");    
		        messageBox.open();
				
			}
			
		}else if (editDialog == 2){
			
			new ProjectEditDialog(Main.getMainShell(), SWT.NONE).open();
		}
	}
	
	/**
	 * 
     * Open a DeleteDialog to delete a Train or a Timetable 
	 *
	 *   open a TimetableDeleteDialog if addDialog = 1 
	 *   open a TrainDeleteDialog id addDialog = 0
	 *   
	 * @param menu
	 * @param deleteDialog - int witch Dialog should be open
	 */
    public static void openDeleteDialog(boolean menu, int deleteDialog) {
    	
    	if (deleteDialog == 0){
    		
    	    if (Main.getTrainListAll().size() > 0){
		    
    		    new TrainDeletDialog(Main.getMainShell(), SWT.NONE).open(menu);
		
            }else{
			
		    	MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
	            messageBox.setMessage("Es gibt keine Züge die Gelöscht werden können.");    
	            messageBox.open();
			
		    }
    	}else if(deleteDialog == 1){
    		
    		if (Main.getTimetableListAll().size() > 0){
			    
    			new TimetableDeletDialog(Main.getMainShell(), SWT.NONE).open(menu);
    			
    	    }else{
    				
    			MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
    			messageBox.setMessage("Es gibt keine Fahrpläne die Gelöscht werden können.");    
    		    messageBox.open();
    				
        	}	
    		
    	}
	}

    /**
     * Open a show new HelpDialog
     * 
     */
	public static void showHelp() {
		
		new HelpDialog(Main.getMainShell(), SWT.NONE).open();
	}

	/**
	 * Open a SaveDialog to delete a Train or a Timetable 
	 *   
	 *   open a ProjectSaveDialog if addDialog = 2
	 *   open a TimetableSaveDialog if addDialog = 1 
	 *   open a TrainSaveDialog id addDialog = 0
	 *   
	 * @param menu
	 * @param saveDialog
	 */
	public static void save(boolean menu, int saveDialog) {
        String title = "";
        
        if (saveDialog == 0){
        
        	title = "Zug Exportieren";
        	
        }else if(saveDialog == 1){
        	
        	title = "Fahrplan Exportieren";
        	
        }else if(saveDialog == 2){
        	
        	title = "Projekt Exportieren";
        	
        }
        
		FileDialog fd = new FileDialog(Main.getMainShell(), SWT.SAVE);
        fd.setText(title);
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.xml" };
        fd.setFilterExtensions(filterExt);
        String selected = fd.open(); 
        
        if(saveDialog == 0){
        	
            if (menu == false){
        	
        	   TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
        	   TrainData td = Main.getTrainListAll().get(0);
        	   int i = 0; 
        	
        	    while(Integer.parseInt(rowData[0].getText(0)) != td.getID()){
        		    i++;
        		    td = Main.getTrainListAll().get(i);
        	    }
        		
        	    XmlWriter.saveSingleTrain(selected,  td);
        
            }else if (menu == true){
        	
        	    String idCheck = TrainExportDialog.comboTrains.getText();
        	    TrainData td = Main.getTrainListAll().get(0);
        	    int i = 0; 
        	
        	    while(Integer.parseInt(idCheck) != td.getID()){
        		   i++;
        		   td = Main.getTrainListAll().get(i);
        	    }
        	
        	    XmlWriter.saveSingleTrain(selected,  td);
        	    TrainExportDialog.dialog.close();
            }
        }else if(saveDialog == 1){
        
        	if (menu == false){
            	
            	TableItem [] rowData = CompositeTimeTable.getTimeTableTable().getSelection();
            	Timetable tt = Main.getTimetableListAll().get(0);
            	int i = 0; 
            	
            	while(Integer.parseInt(rowData[0].getText(0)) != tt.getId()){
            		i++;
            		tt = Main.getTimetableListAll().get(i);
            	}
            		
            	XmlWriter.saveSingleTimeTable(selected, tt);
            
            }else if (menu == true){
            	
            	String idCheck = TimetableExportDialog.comboTimetables.getText();
            	Timetable tt = Main.getTimetableListAll().get(0);
            	int i = 0; 
            	
            	while(Integer.parseInt(idCheck) != tt.getId()){
            		i++;
            		tt = Main.getTimetableListAll().get(i);
            	}
            	
            	XmlWriter.saveSingleTimeTable(selected,  tt);
            	TimetableExportDialog.dialog.close();
            }
        	
        }else if(saveDialog == 2){
        	
        	if(menu == false){
        		
        		TableItem [] rowData = CompositeProject.getProjectTable().getSelection();
            	Project p = Main.getProjectListAll().get(0);
            	int i = 0; 
            	
            	while(Integer.parseInt(rowData[0].getText(0)) != p.getId()){
            		i++;
            		p = Main.getProjectListAll().get(i);
            	}
            		
            	XmlWriter.saveSingleProject(selected, p);
        		
        	}else if(menu == true){
        		
        		String idCheck = ProjectExportDialog.comboProjects.getText();
            	Project p = Main.getProjectListAll().get(0);
            	int i = 0; 
            	
            	while(Integer.parseInt(idCheck) != p.getId()){
            		i++;
            		p = Main.getProjectListAll().get(i);
            	}
            	
            	XmlWriter.saveSingleProject(selected, p);
            	ProjectExportDialog.dialog.close();
        		
        	}
        	
        }
	}

	/**
	 * Open a LoadDialog to delete a Train or a Timetable 
	 *   
	 *   open a ProjectLoadDialog if addDialog = 2
	 *   open a TimetableLoadDialog if addDialog = 1 
	 *   open a TrainLoadDialog id addDialog = 0
	 *
	 * @param openDialog
	 */
	public static void open(int openDialog) {
		 String title = "";
	        
	     if (openDialog == 0){
	        
	        title = "Zug Importieren";
	        	
	     }else if(openDialog == 1){
	        	
	        title = "Fahrplan Importieren";
	        	
	     }else if(openDialog == 2){
	        	
	        title = "Projekt Importieren";
	        	
	     }
	     
		 FileDialog fd = new FileDialog(Main.getMainShell(), SWT.OPEN);
	     fd.setText(title);
	     fd.setFilterPath("C:/");
	     String[] filterExt = { "*.xml"};
	     fd.setFilterExtensions(filterExt);
	     String selected = fd.open();
	     
	     if(openDialog == 0){
	    	 
	    	 TrainData td = XmlReader.loadSingleTrain(selected);
		     
	    	 if (td.getLadung() != null){
		    
	    		 TrainEvents.importTrain(td); 
		     }
	           
	     }else if(openDialog == 1){
	         
	    	 Timetable tt = XmlReader.loadSingleTimeTable(selected);
	    	 
	         if (tt.getDrivingdays() != null){
	        	 
	        	 TimeTableEvents.importTimetable(tt);
	        	 
	         }
	    	 
	     }else if(openDialog == 2){
	        
	    	  
	    	 Project p = XmlReader.loadSingelProject(selected);
	    	 
	         
	        	 
	        	 ProjectEvents.importProject(p);
	        	 
	         
	    	 
	     }
		
	}

	/**
	 * This methode create and open a new export Dialog for
	 * Train/Timetable or Project.
	 *
	 * 
	 * @param exportDialog
	 */
	public static void openExportDialog(int exportDialog) {
		
		if(exportDialog == 0){
			
			if (Main.getTrainListAll().size() > 0){
	        	
	        	new TrainExportDialog(Main.getMainShell(),SWT.NONE).open(true);
	    				
	        }else{
				
				MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
		        messageBox.setMessage("Es gibt keine Züge die Exportiert werden können.");    
		        messageBox.open();
				
			}
			
		}else if(exportDialog == 1){
			
			if (Main.getTimetableListAll().size() > 0){
		        	
		       	new TimetableExportDialog(Main.getMainShell(),SWT.NONE).open(true);
		    				
	        }else{
					
				MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
			    messageBox.setMessage("Es gibt keine Fahrpläne die Exportiert werden können.");    
		        messageBox.open();
				
			}	
			
		}else if(exportDialog == 2){
			
			if (Main.getProjectListAll().size() > 0){
	        	
		       	new TimetableExportDialog(Main.getMainShell(),SWT.NONE).open(true);
		    				
	        }else{
					
				MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
			    messageBox.setMessage("Es gibt keine Projecte die Exportiert werden können.");    
		        messageBox.open();
				
			}	
			
		}
       		
	}

	/**
	 * This methode create and open a new Info Dialog.
	 * 
	 */
	public static void showInfo() {
		
		new AboutUsDialog(Main.getMainShell(), SWT.NONE).open();
		
	}

	
}
