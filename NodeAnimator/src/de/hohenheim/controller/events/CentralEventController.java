package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.controller.XmlReader;
import de.hohenheim.controller.XmlWriter;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.composite.CompositeTimeTable;
import de.hohenheim.view.composite.CompositeTrain;
import de.hohenheim.view.dialouge.AboutUsDialog;
import de.hohenheim.view.dialouge.HelpDialog;
import de.hohenheim.view.dialouge.ProjectAddDialog;
import de.hohenheim.view.dialouge.ProjectEditDialog;
import de.hohenheim.view.dialouge.TimetableAddDialog;
import de.hohenheim.view.dialouge.TimetableDeletDialog;
import de.hohenheim.view.dialouge.TimetableEditDialog;
import de.hohenheim.view.dialouge.TimetableExportDialog;
import de.hohenheim.view.dialouge.TrainAddDialog;
import de.hohenheim.view.dialouge.TrainDeletDialog;
import de.hohenheim.view.dialouge.TrainEditDialog;
import de.hohenheim.view.dialouge.TrainExportDialog;

public class CentralEventController {

	public static void closeProgramm() {
		
		 MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ICON_QUESTION
		            | SWT.YES | SWT.NO);
		 messageBox.setMessage("Wollen Sie das Programm wirklich beenden ?");
		 messageBox.setText("Programm Beenden");
		 int response = messageBox.open();
		 if (response == SWT.YES)
		 System.exit(0);
		
	}

	public static void changeLook(int tab) {
		if (tab == 0){
		    Main.getcTabFolder().setSelection(0);
		}else if (tab == 1){
			Main.getcTabFolder().setSelection(1);	
		}else if (tab == 2){
			Main.getcTabFolder().setSelection(2);
		}else if (tab == 3){
			Main.getcTabFolder().setSelection(3);
		}
	}

	public static void openAddDialog(int addDialog) {
		
		if (addDialog == 0){
		
			new TrainAddDialog(Main.getShell(), SWT.NONE).open();
		
		}else if (addDialog == 1){
		
			new TimetableAddDialog(Main.getShell(), SWT.NONE).open();
		
		}else if (addDialog == 2){
			
			if((Main.timetableListAll.size() > 0) && (Main.trainListAll.size() > 0)){
				
			    new ProjectAddDialog(Main.getShell(), SWT.NONE).open();
			
			}else{
				String message = "";
			    if(Main.timetableListAll.size() < 1){
			    	
			    	message = message + "Es gibt keine Fahrpläne daher kann kein Project erstellt werden. \n";
			        
			    }
			    if (Main.trainListAll.size() < 1){
			    	
			    	message = message + "Es gibt keine Züge daher kann kein Project ertstellt werden.";
			    	
			    }
			    MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
		        messageBox.setMessage(message);    
		        messageBox.open();
			}
		}
		
	}

	public static void openEditDialog(boolean menu, int editDialog) {
		
		if (editDialog == 0){
		    
			if (Main.trainListAll.size() > 0){
			
			    new TrainEditDialog(Main.getShell(), SWT.NONE).open(menu);
			
		    }else{
			
			    MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
	            messageBox.setMessage("Es gibt keine Züge die Bearbeitet werden können.");    
	            messageBox.open();
			
		    }
	
		}else if (editDialog == 1){
			
			if (Main.timetableListAll.size() > 0){
			    
	        	new TimetableEditDialog(Main.getShell(), SWT.NONE).open(menu);
			
	        }else{
				
				MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
		        messageBox.setMessage("Es gibt keine Fahrpläne die Bearbeitet werden können.");    
		        messageBox.open();
				
			}
			
		}else if (editDialog == 2){
			
			new ProjectEditDialog(Main.getShell(), SWT.NONE).open();
		}
	}
	
    public static void openDeleteDialog(boolean menu, int deleteDialog) {
    	
    	if (deleteDialog == 0){
    		
    	    if (Main.trainListAll.size() > 0){
		    
    		    new TrainDeletDialog(Main.getShell(), SWT.NONE).open(menu);
		
            }else{
			
		    	MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
	            messageBox.setMessage("Es gibt keine Züge die Gelöscht werden können.");    
	            messageBox.open();
			
		    }
    	}else if(deleteDialog == 1){
    		
    		if (Main.timetableListAll.size() > 0){
			    
    			new TimetableDeletDialog(Main.getShell(), SWT.NONE).open(menu);
    			
    	    }else{
    				
    			MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
    			messageBox.setMessage("Es gibt keine Fahrpläne die Gelöscht werden können.");    
    		    messageBox.open();
    				
        	}	
    		
    	}
	}

	public static void showHelp() {
		
		new HelpDialog(Main.getShell(), SWT.NONE).open();
	}

	public static void save(boolean menu, int saveDialog) {
        String title = "";
        
        if (saveDialog == 0){
        
        	title = "Zug Exportieren";
        	
        }else if(saveDialog == 1){
        	
        	title = "Fahrplan Exportieren";
        	
        }else if(saveDialog == 2){
        	
        	title = "Projekt Exportieren";
        	
        }
        
		FileDialog fd = new FileDialog(Main.getShell(), SWT.SAVE);
        fd.setText(title);
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.xml" };
        fd.setFilterExtensions(filterExt);
        String selected = fd.open(); 
        
        if(saveDialog == 0){
        	
            if (menu == false){
        	
        	   TableItem [] rowData = CompositeTrain.getTrainTable().getSelection();
        	   TrainData td = Main.trainListAll.get(0);
        	   int i = 0; 
        	
        	    while(Integer.parseInt(rowData[0].getText(0)) != td.getID()){
        		    i++;
        		    td = Main.trainListAll.get(i);
        	    }
        		
        	    XmlWriter.saveSingleTrain(selected,  td);
        
            }else if (menu == true){
        	
        	    String idCheck = TrainExportDialog.comboTrains.getText();
        	    TrainData td = Main.trainListAll.get(0);
        	    int i = 0; 
        	
        	    while(Integer.parseInt(idCheck) != td.getID()){
        		   i++;
        		   td = Main.trainListAll.get(i);
        	    }
        	
        	    XmlWriter.saveSingleTrain(selected,  td);
        	    TrainExportDialog.dialog.close();
            }
        }else if(saveDialog == 1){
        
        	if (menu == false){
            	
            	TableItem [] rowData = CompositeTimeTable.getTimeTableTable().getSelection();
            	Timetable tt = Main.timetableListAll.get(0);
            	int i = 0; 
            	
            	while(Integer.parseInt(rowData[0].getText(0)) != tt.getId()){
            		i++;
            		tt = Main.timetableListAll.get(i);
            	}
            		
            	XmlWriter.saveSingleTimeTable(selected, tt);
            
            }else if (menu == true){
            	
            	String idCheck = TimetableExportDialog.comboTimetables.getText();
            	Timetable tt = Main.timetableListAll.get(0);
            	int i = 0; 
            	
            	while(Integer.parseInt(idCheck) != tt.getId()){
            		i++;
            		tt = Main.timetableListAll.get(i);
            	}
            	
            	XmlWriter.saveSingleTimeTable(selected,  tt);
            	TimetableExportDialog.dialog.close();
            }
        	
        }else if(saveDialog == 2){
        	
        }
	}

	public static void open(int openDialog) {
		 String title = "";
	        
	     if (openDialog == 0){
	        
	        title = "Zug Importieren";
	        	
	     }else if(openDialog == 1){
	        	
	        title = "Fahrplan Importieren";
	        	
	     }else if(openDialog == 2){
	        	
	        title = "Projekt Importieren";
	        	
	     }
	     
		 FileDialog fd = new FileDialog(Main.getShell(), SWT.OPEN);
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
	        	
	     }
		
	}

	public static void openExportDialog(int exportDialog) {
		
		if(exportDialog == 0){
			
			if (Main.trainListAll.size() > 0){
	        	
	        	new TrainExportDialog(Main.getShell(),SWT.NONE).open(true);
	    				
	        }else{
				
				MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
		        messageBox.setMessage("Es gibt keine Züge die Exportiert werden können.");    
		        messageBox.open();
				
			}
			
		}else if(exportDialog == 1){
			
			if (Main.timetableListAll.size() > 0){
		        	
		       	new TimetableExportDialog(Main.getShell(),SWT.NONE).open(true);
		    				
	        }else{
					
				MessageBox messageBox = new MessageBox(Main.getShell(), SWT.ERROR | SWT.OK);
			    messageBox.setMessage("Es gibt keine Fahrpläne die Exportiert werden können.");    
		        messageBox.open();
				
			}	
			
		}else if(exportDialog == 2){
			
		}
       		
	}

	public static void showInfo() {
		
		new AboutUsDialog(Main.getShell(), SWT.NONE).open();
		
	}

	
}
