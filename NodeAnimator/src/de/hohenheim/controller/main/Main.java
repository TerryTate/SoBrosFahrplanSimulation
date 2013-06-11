package de.hohenheim.controller.main;
	
import java.awt.Toolkit;
import java.util.ArrayList;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.menu.MenuBar;
import de.hohenheim.view.tab.TabFolder;

public class Main {
	
	private static Composite tabComposite;
	private static Composite bottomComposite;
	private static TabFolder tabFolder;
	public static ArrayList<TrainData> trainListAll = new ArrayList();
	public static ArrayList<Timetable> timetableListAll = new ArrayList();
	public static ArrayList<Project>  projectListAll = new ArrayList();
	private static Shell shell;
	
	
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
        
		setShell(new Shell());
		getShell().setText("Fahrplan Animation");
		int screenWidth  = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		getShell().setSize(820, 720);
		getShell().setLocation( screenWidth/2 - 410, screenHeight/2 - 360);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
	    getShell().setLayout(gridLayout);
		
		getShell().setMenuBar(MenuBar.createMenu(getShell()));
		
		tabComposite = new Composite(getShell(), SWT.BORDER );
		tabComposite.setBackground(ColorConstants.lightGray);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		tabComposite.setLayoutData(gridData);
		tabComposite.setLayout(gridLayout);
		
		
		bottomComposite = new Composite(getShell(), SWT.BORDER );
		bottomComposite.setBackground(ColorConstants.lightGray);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 20;
    	bottomComposite.setLayoutData(gridData);
    	
	    tabFolder = new TabFolder(tabComposite, SWT.BORDER, display);   
        
	    Shell animation = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP| SWT.CENTER);
        animation.setSize(600, 400);
        
        animation.setBackgroundImage(new Image(null, "img/Logo.png"));
        animation.setLocation( screenWidth/2 - 300, screenHeight/2 - 200 );
        
        ProgressBar bar = new ProgressBar (animation, SWT.SMOOTH);
        bar.setBounds (0, 370, 600, 15);
        
        animation.open();
        for (int i=0; i<=bar.getMaximum (); i++) {
            try {Thread.sleep (100);} catch (Throwable th) {}
            bar.setSelection (i);
        }
        
        animation.close();
        animation.dispose();
        
	    getShell().open();
    
	
		while (!getShell().isDisposed()) {			
			if (!display.readAndDispatch()) {
	    
			    display.sleep();
		   }
	
		}
	
	}

	public static TabFolder getcTabFolder() {
		return tabFolder;
	}

	public static void setTabFolder(TabFolder tabFolder) {
		Main.tabFolder = tabFolder;
	}

	public static Shell getShell() {
		return shell;
	}

	public static void setShell(Shell shell) {
		Main.shell = shell;
	}

}
