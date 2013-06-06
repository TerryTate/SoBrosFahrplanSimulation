package de.hohenheim.view.dialouge;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class ProjectAddDialog extends Dialog {
	

	Shell parent;
	

	
	private Text idText;
	private Text nameText;
	private Combo comboChooseTrain;
	private Combo comboChooseTimeTable; 

	public ProjectAddDialog(Shell parent, int style) {
		super(parent, style);
		parent = this.parent;
	}
    
	 public void open() {
			
		final Shell dialog = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
	    dialog.setSize(220, 310);
	    dialog.setText("Projekt hinzufügen");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3; 
	    dialog.setLayout(gridLayout);
	    
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

}
