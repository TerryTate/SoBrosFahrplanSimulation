package de.hohenheim.view.composite;

import java.text.Collator;
import java.util.Locale;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import de.hohenheim.view.canvas.ProjektControllerCanvas;

/**
 * This class create a new CompositeProject with a Table 
 * and call the Constructor for a new ProjectControllerCanvas 
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs��
 * 
 * @version 1.0
 */
public class CompositeProject extends Composite {
	
	  
	private ScrolledComposite scrollComposite;
	private GridLayout gridLayout;
	private static Table projectTable;

	/**
	 * A Constructor for a new CompositeProject
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 */
	public CompositeProject(Composite parent, int style) {
		super(parent, style);
		createContent();
	}

	/**
	 * Create the content of the new Train Composite (the ProjectTable, 
	 * the ProjectControllersCanvas
	 */
	private void createContent() {
		
		gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		this.setLayout(gridLayout);
		
		
        scrollComposite = new ScrolledComposite(this, SWT.H_SCROLL  | SWT.V_SCROLL);
		
		scrollComposite.setVisible(true);
		scrollComposite.setExpandHorizontal(true);
	    scrollComposite.setExpandVertical(true);
		scrollComposite.setBackground(ColorConstants.blue);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		scrollComposite.setLayoutData(gridData);
		
		setProjectTable(new Table(scrollComposite, SWT.FULL_SELECTION));
	    TableColumn idProject = new TableColumn(getProjectTable(),SWT.CENTER);
	    TableColumn projectName = new TableColumn(getProjectTable(), SWT.CENTER);
	    TableColumn idtrains = new TableColumn(getProjectTable(), SWT.CENTER);
	    TableColumn idtimeTable = new TableColumn(getProjectTable(), SWT.CENTER);
	    
	    idProject.setText("ID");
	    projectName.setText("Projekt Name");
	    idtrains.setText("ID Z�ge");
	    idtimeTable.setText("ID Fahrplan");
	    
	    idProject.setWidth(70);
	    projectName.setWidth(70);
	    idtrains.setWidth(80);
	    idtimeTable.setWidth(80);
	    
	    idProject.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	           
	            TableItem[] items = projectTable.getItems();
	           
	            for (int i = 1; i < items.length; i++) {
	              int value1 = Integer.parseInt(items[i].getText(0));
	              for (int j = 0; j < i; j++) {
	                int value2 = Integer.parseInt(items[j].getText(0));
	                if (value1 <= value2) {
	                  String[] values = { items[i].getText(0),
	                      items[i].getText(1), items[i].getText(2),
	                      items[i].getText(3),items[i].getText(4)};
	                  items[i].dispose();
	                  TableItem item = new TableItem(projectTable, SWT.NONE, j);
	                  item.setText(values);
	                  items = projectTable.getItems();
	                  break;
	                }
	              }
	            }
	          }
	    });
	    
	    projectName.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
		         
	            TableItem[] items = projectTable.getItems();
	            Collator collator = Collator.getInstance(Locale.getDefault());
	            for (int i = 1; i < items.length; i++) {
	              String value1 = items[i].getText(1);
	              for (int j = 0; j < i; j++) {
	                String value2 = items[j].getText(1);
	                if (collator.compare(value1, value2) < 0) {
	                  String[] values = { items[i].getText(0),
	                      items[i].getText(1), items[i].getText(2),
	                      items[i].getText(3),items[i].getText(4)};
	                  items[i].dispose();
	                  TableItem item = new TableItem(projectTable, SWT.NONE, j);
	                  item.setText(values);
	                  items = projectTable.getItems();
	                  break;
	                }
	              }
	            }
	          }
	    });
	    
	    getProjectTable().setHeaderVisible(true);
	    getProjectTable().setLinesVisible(true);
	    
	    scrollComposite.setContent(getProjectTable());
		
		ProjektControllerCanvas canvasControl = new ProjektControllerCanvas(this, SWT.NONE );	
		
		gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		canvasControl.setLayoutData(gridData);
		
	}

	/**
	 * Getter for the ProjectTable
	 * 
	 * @return projectTable - return the table from the Project-Composite with the project
	 *         data
	 */
	public static Table getProjectTable() {
		return projectTable;
	}

	/**
	 * Setter for the ProjectTable
	 * 
	 * @param projectTable
	 */
	public static void setProjectTable(Table projectTable) {
		CompositeProject.projectTable = projectTable;
	}

}
