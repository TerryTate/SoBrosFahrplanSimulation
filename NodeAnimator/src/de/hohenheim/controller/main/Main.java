package de.hohenheim.controller.main;

import java.awt.Toolkit;
import java.util.ArrayList;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;

import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

import de.hohenheim.controller.XmlReader;
import de.hohenheim.controller.XmlWriter;
import de.hohenheim.controller.events.ProjectEvents;
import de.hohenheim.controller.events.TimeTableEvents;
import de.hohenheim.controller.events.TrainEvents;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.menu.MenuBar;
import de.hohenheim.view.mobile.ImageHelper;
import de.hohenheim.view.tab.TabFolder;

/**
 * Main Method witch starts the application with an Splash screen and set then a
 * mainShell with his Components
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class Main {

	private static Composite tabComposite;
	private static Composite bottomComposite;
	private static TabFolder tabFolder;
	private static ArrayList<TrainData> trainListAll = new ArrayList<TrainData>();
	private static ArrayList<Timetable> timetableListAll = new ArrayList<Timetable>();
	private static ArrayList<Project> projectListAll = new ArrayList<Project>();
	private static Shell mainShell;
	private static Display display;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		display = (Display.getDefault());

		mainShell = new Shell();
		mainShell.setBackgroundImage(ImageHelper.animBack);
		getMainShell().setText("Fahrplan Animation");
		getMainShell().setImage(ImageHelper.trainTab);
		int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		getMainShell().setSize(960, 700);
		getMainShell().setLocation(screenWidth / 2 - 410,
				screenHeight / 2 - 360);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		getMainShell().setLayout(gridLayout);

		getMainShell().setMenuBar(MenuBar.createMenu(getMainShell()));

		tabComposite = new Composite(getMainShell(), SWT.NONE);
        tabComposite.setBackgroundImage(ImageHelper.animBack);
        
		// ///////// innerer Rand
		tabComposite.setBackground(ColorConstants.lightGray);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		tabComposite.setLayoutData(gridData);
		tabComposite.setLayout(gridLayout);

		bottomComposite = new Composite(getMainShell(), SWT.BORDER);
		bottomComposite.setBackgroundImage(ImageHelper.leiste);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.heightHint = 39;
		bottomComposite.setLayoutData(gridData);

		tabFolder = new TabFolder(tabComposite, SWT.BORDER, getDisplay());
		tabFolder.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {

				if (AnimationControllerCanvas.isRun()) {

					tabFolder.setSelection(0);
					MessageBox messageBox = new MessageBox(Main.getMainShell(),
							SWT.ERROR | SWT.OK);
					messageBox
							.setMessage("Die Ansicht kann nicht gewechselt werden \n"
									+ "solange eine Animation l‰uft!");
					messageBox.open();
				}

			}
		});

		// Animation SoBros mit ProgressBar

		Shell animation = new Shell(display, SWT.NO_TRIM | SWT.ON_TOP
				| SWT.CENTER);
		animation.setSize(600, 400);

		animation.setBackgroundImage(ImageHelper.logo);
		animation.setLocation(screenWidth / 2 - 300, screenHeight / 2 - 200);
        Label loadSetting = new Label(animation, SWT.NONE);
        animation.setBackgroundMode(SWT.INHERIT_DEFAULT);
		loadSetting.setText("");
		
		loadSetting.setBounds(10, 350, 400, 20);
		ProgressBar bar = new ProgressBar(animation, SWT.SMOOTH);
		bar.setBounds(0, 370, 600, 15);

		animation.open();
		
		
		XmlReader.loadAllData();
		
		ProjectEvents.importAllProjects();
		
		TrainEvents.importAllTrain();
		
		TimeTableEvents.importAllTimetable();
		
		for (int i = 0; i <= bar.getMaximum(); i++){
			try {
			if(i < (bar.getMaximum()/3)){
            	
            	loadSetting.setText("/Settings/loadProjects");
            	
            }else if(i < (2*(bar.getMaximum()/3))){
           	    loadSetting.setText("/Settings/loadTrains");
            	
            }else{
                loadSetting.setText("/Settings/loadTimetables");
            	
		    }
		
			
				Thread.sleep(50);
			} catch (Throwable th) {
			}
			bar.setSelection(i);
		}

		animation.close();
		animation.dispose();
		mainShell.addShellListener(new ShellAdapter() {
            public void shellClosed(ShellEvent e) {
                XmlWriter.writeAllToXML();
            }
        });
 
		getMainShell().open();

		
		while (!getMainShell().isDisposed()) {
			if (!getDisplay().readAndDispatch()) {

				getDisplay().sleep();
			}

		}

	}

	/**
	 * Getter for the tabFolder
	 * 
	 * @return TabFolder - tabFolder
	 */
	public static TabFolder getcTabFolder() {
		return tabFolder;
	}

	/**
	 * Getter for the mainShell
	 * 
	 * @return Shell - mainShell
	 */
	public static Shell getMainShell() {
		return mainShell;
	}

	/**
	 * Getter for the Display
	 * 
	 * @return Display
	 */
	public static Display getDisplay() {
		return display;
	}

	/**
	 * Getter for timetableListAll
	 * 
	 * @return ArrayList - with all existing Timetables
	 */
	public static ArrayList<Timetable> getTimetableListAll() {
		return timetableListAll;
	}

	/**
	 * Getter for trainListAll
	 * 
	 * @return ArrayList - with all existing TrainDatas
	 */
	public static ArrayList<TrainData> getTrainListAll() {
		return trainListAll;
	}

	/**
	 * Getter for projectListAll
	 * 
	 * @return ArrayList - with all existing projects
	 */
	public static ArrayList<Project> getProjectListAll() {
		return projectListAll;
	}

}
