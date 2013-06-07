package de.hohenheim.view.dialouge;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import de.hohenheim.controller.main.Main;
import de.hohenheim.view.composite.CompositeProject;


public class ProjectEditDialog extends Dialog{
	

	Shell parent;
	private Text idText;
	private Text nameText;
	private Combo comboChooseTrain;
	private Combo comboChooseTimeTable;
	
	private Shell dialog;
	private Combo comboProject; 

	public ProjectEditDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open(final boolean menu) {
			
		    dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		    dialog.setSize(220, 310);
		    dialog.setLocation(500, 200);
		    dialog.setText("Zug bearbeiten");
		    dialog.setImage(new Image(null, "img/Edit2.png"));
		    GridLayout gridLayout = new GridLayout();
		    gridLayout.numColumns = 3; 
		    dialog.setLayout(gridLayout);
		    
		    TableItem [] rowData = CompositeProject.getProjectTable().getSelection();
		    
		    if (menu == true){
		    	
				System.out.println("Test");
		    	Label chooseTrain = new Label(dialog, SWT.NONE);
		    	chooseTrain.setText("Wähle Zug ID : ");
		    	
		    	comboProject = new Combo(dialog, SWT.READ_ONLY);
		    	String[] trainsID = new String [Main.trainListAll.size()];
			    comboProject.setItems(loadProjectList(trainsID));
			    
			    comboProject.addSelectionListener(new SelectionListener() {
			       
			    	public void widgetSelected(SelectionEvent e) {
			          
			    	    setText();
			    		
			        }

			        public void widgetDefaultSelected(SelectionEvent e) {
			         
			        }
			      });
			    
			    GridData gridData = new GridData();
			    gridData.horizontalSpan = 2;
			    gridData.horizontalAlignment = SWT.FILL;
			    comboProject.setLayoutData(gridData);
			    
		    	
			}
		
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
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboChooseTrain.setLayoutData(gridData);
	    
	    //Choose TimeTable (Fahrplan)	
	    
	    Label chooseTimeTable = new Label(dialog, SWT.NONE);
	    chooseTimeTable.setText("Fahrplan wählen : ");
	    
	    comboChooseTimeTable = new Combo(dialog, SWT.READ_ONLY);
	    //comboChooseTimeTable.setItems(typs);
	    gridData = new GridData();
	    gridData.horizontalSpan = 2;
	    gridData.horizontalAlignment = SWT.FILL;
	    comboChooseTimeTable.setLayoutData(gridData);
	    
	    //Linked List
		    
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
