package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.events.ProjectEvents;
import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.view.composite.CompositeProject;
import de.hohenheim.view.composite.CompositeTrain;

public class ProjectEditDialog extends Dialog{
	

	Shell parent;
	private Text idText;
	private Text nameText;
	private Combo comboChooseTrain;
	private Combo comboChooseTimeTable;
	
	private Shell dialog;
	private Combo comboProject;
	private Table linkTable; 

	public ProjectEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open() {
			
		    dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		    dialog.setSize(320, 310);
		    dialog.setText("Zug bearbeiten");
		    dialog.setImage(new Image(null, "img/Edit2.png"));
		    GridLayout gridLayout = new GridLayout();
		    gridLayout.numColumns = 3; 
		    dialog.setLayout(gridLayout);
		    
		    TableItem [] rowData = CompositeProject.getProjectTable().getSelection();
		    
//		    if (menu == true){
//		    	
//				System.out.println("Test");
//		    	Label chooseTrain = new Label(dialog, SWT.NONE);
//		    	chooseTrain.setText("Wähle Zug ID : ");
//		    	
//		    	comboProject = new Combo(dialog, SWT.READ_ONLY);
//		    	String[] trainsID = new String [Main.trainListAll.size()];
//			    comboProject.setItems(loadProjectList(trainsID));
//			    
//			    comboProject.addSelectionListener(new SelectionListener() {
//			       
//			    	public void widgetSelected(SelectionEvent e) {
//			          
//			    	    setText();
//			    		
//			        }
//
//			        public void widgetDefaultSelected(SelectionEvent e) {
//			         
//			        }
//			      });
//			    
//			    GridData gridData = new GridData();
//			    gridData.horizontalSpan = 2;
//			    gridData.horizontalAlignment = SWT.FILL;
//			    comboProject.setLayoutData(gridData);
//			    
//		    	
//			}
		
	    //Project ID
	    
	    Label id = new Label(dialog, SWT.NONE);
	    id.setText("ID : ");
	    
	    idText = new Text(dialog, SWT.NONE);
	    GridData gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    idText.setLayoutData(gridData);
	    
	    //Project Name
	    
	    Label name = new Label(dialog, SWT.NONE);
	    name.setText("Name : ");
	    
	    nameText = new Text(dialog, SWT.NONE);
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    nameText.setLayoutData(gridData);
	    
	    //Choose Train
	    
	    Label chooseTrain = new Label(dialog, SWT.NONE);
	    chooseTrain.setText("Zug wählen : ");
	    
	    comboChooseTrain = new Combo(dialog, SWT.READ_ONLY);
	    //comboChooseTrain.setItems(typs);
	    gridData = new GridData();
	    
	    gridData.horizontalAlignment = SWT.FILL;
	    comboChooseTrain.setLayoutData(gridData);
	    
        //Add Button 
	    
	    Button addButton = new Button(dialog, SWT.NONE);
		addButton.setText("Add");
		addButton.setImage(new Image(null,"img/add24.png"));
		addButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    ProjectEvents.addLink();   
				
			}
		});
	    
	    //Choose TimeTable (Fahrplan)	
	    
	    Label chooseTimeTable = new Label(dialog, SWT.NONE);
	    chooseTimeTable.setText("Fahrplan wählen : ");
	    
	    comboChooseTimeTable = new Combo(dialog, SWT.READ_ONLY);
	    //comboChooseTimeTable.setItems(typs);
	    gridData = new GridData();
	
	    gridData.horizontalAlignment = SWT.FILL;
	    comboChooseTimeTable.setLayoutData(gridData);
	    
 // Remove Button
		
		Button removeButton = new Button(dialog, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setImage(new Image(null,"img/Clear.png"));
		removeButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    TimeTableEvents.removeMiddleStation(true);  
				
			}
		});
		    
	    
	    //Linked List
        Label room6 = new Label(dialog, SWT.NONE);
	    
		Composite tableComposite = new Composite(dialog, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalSpan = 2; 
		gridData.horizontalAlignment = SWT.FILL;
		tableComposite.setLayoutData(gridData);
		tableComposite.setLayout(new FillLayout());
		
		linkTable = new Table(tableComposite, SWT.NONE);
		
		linkTable.setLinesVisible(true);

		TableColumn trains = new TableColumn(linkTable, SWT.None);
		TableColumn timetable = new TableColumn(linkTable, SWT.NONE);
		trains.setWidth(100);  
		timetable.setWidth(100);
		
 // Buttonn Composite
	    
	    Composite buttonComposite = new Composite(dialog, SWT.NONE);
	    GridLayout gridLayout2 = new GridLayout();
	    gridLayout2.numColumns = 2;
	    buttonComposite.setLayout(gridLayout2);
	    
	    // OK Button 
	    
	    Button okButton = new Button(dialog, SWT.NONE);
		okButton.setText("OK");
		okButton.setImage(new Image(null, "img/Ok.png"));
		okButton.setImage(new Image(null, "img/add.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    okButton.setLayoutData(gridData);
		
		okButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			    ProjectEvents.addProject();
				
			}
		});
	    
	    // Cancel Button
		
		Button cancelButton = new Button(dialog, SWT.NONE);
		cancelButton.setText("Cancel");
		cancelButton.setImage(new Image(null, "img/Cancel.png"));
	    gridData = new GridData();
	    gridData.horizontalAlignment = SWT.CENTER;
	    cancelButton.setLayoutData(gridData); 
		
		cancelButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event arg0) {
				
			   dialog.close();
				
			}
		});
		    
		dialog.open();
	}

	protected void setText() {
		// TODO Auto-generated method stub
		
	}

	private String[] loadProjectList(String[] trainsID) {
		// TODO Auto-generated method stub
		return null;
	}

}
