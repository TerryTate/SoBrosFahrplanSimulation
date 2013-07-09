package de.hohenheim.view.canvas;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Spinner;
import de.hohenheim.controller.events.AnimationEvents;
import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.project.Project;
import de.hohenheim.view.map.NodeMap;
import de.hohenheim.view.mobile.ImageHelper;

/**
 * This class is responsible for the GUI of the AnimationControllerCanvas.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */
public class AnimationControllerCanvas extends Canvas {

	public static NodeMap map;
	public static Combo comboProjects;
	public static Spinner houre;
	public static Spinner minutes;
	public static Combo comboDrivingday;
	String[] drivingdayItems = { "Mo", "Di", "Mi", "Do", "Fr", "Sa", "So" };
	public static Scale scaleAnimationSpeed;
	public static Label timer;
	private static boolean run = false;
	public static int hour;
	public static int min;
	String message = "";

	/**
	 * A Constructor for a new AnimationControllerCanvas.
	 * 
	 * @param parent
	 *            - component in which it's inside
	 * @param style
	 *            - how the style looks like
	 * @param map
	 */
	public AnimationControllerCanvas(Composite parent, int style, NodeMap map) {
		super(parent, style);
		AnimationControllerCanvas.map = map;
		createContents();
	}

	/**
	 * Create the content of the new AnimationControllerCanvas
	 */
	private void createContents() {
		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 1;

		this.setLayout(gridLayout1);
		Group group = new Group(this, SWT.SHADOW_ETCHED_IN);
		group.setText("Animations Einstellungen");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		group.setLayout(gridLayout);

		// Project

		Label choseProject = new Label(group, SWT.NONE);
		choseProject.setText("Project ID wählen:");

		comboProjects = new Combo(group, SWT.READ_ONLY);
		String[] projectIDs = new String[Main.getProjectListAll().size()];
		comboProjects.setItems(loadProjectIDs(projectIDs));
		comboProjects.select(0);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		comboProjects.setLayoutData(gridData);
		comboProjects.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		// Time

		Label starttime = new Label(group, SWT.NONE);
		starttime.setText("Start Uhrzeit : ");

		Composite timeComposite = new Composite(group, SWT.NONE);
		timeComposite.setLayout(new FillLayout());
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		timeComposite.setLayoutData(gridData);

		houre = new Spinner(timeComposite, SWT.NONE);
		houre.setMaximum(23);

		Label h = new Label(timeComposite, SWT.NONE);
		h.setText("  h");

		minutes = new Spinner(timeComposite, SWT.NONE);
		minutes.setMaximum(59);

		Label m = new Label(timeComposite, SWT.NONE);
		m.setText("  m");

		// DrivingDay

		Label drivingday = new Label(group, SWT.NONE);
		drivingday.setText("Fahrtag wählen:");

		comboDrivingday = new Combo(group, SWT.READ_ONLY);

		comboDrivingday.setItems(drivingdayItems);
		comboDrivingday.select(0);
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		comboDrivingday.setLayoutData(gridData);

		// Animation Speed

		Label animationSpeed = new Label(group, SWT.NONE);
		animationSpeed.setText("Geschwindigkeit : ");

		scaleAnimationSpeed = new Scale(group, SWT.HORIZONTAL);
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		scaleAnimationSpeed.setLayoutData(gridData);
		scaleAnimationSpeed.setMinimum(1);
		scaleAnimationSpeed.setMaximum(1000);
		scaleAnimationSpeed.setIncrement(250);
		scaleAnimationSpeed.setPageIncrement(250);

		group.pack();

		Group group2 = new Group(this, SWT.SHADOW_ETCHED_IN | SWT.FILL);
		group2.setText("Animation");
		GridLayout gridLayout2 = new GridLayout();
		gridLayout2.numColumns = 3;

		group2.setLayout(gridLayout2);

		timer = new Label(group2, SWT.NONE);
		setTimerLabel();
		gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = SWT.CENTER;

		timer.setLayoutData(gridData);

		Button play = new Button(group2, SWT.NONE);
		play.setImage(ImageHelper.play48);
		play.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {

				if (projectCheck()) {

					Project p = null;
					for (Project project : Main.getProjectListAll()) {

						if (Integer.parseInt(comboProjects.getText()) == project
								.getId()) {

							p = project;
						}

					}
					setRun(true);
					AnimationEvents.start(map, p,
							Integer.parseInt(houre.getText()),
							Integer.parseInt(minutes.getText()),
							comboDrivingday.getText());
				} else {
					MessageBox messageBox = new MessageBox(Main.getMainShell(),
							SWT.ERROR | SWT.OK);
					messageBox.setMessage(message);
					messageBox.open();
				}
			}

		});

		Button pause = new Button(group2, SWT.NONE);
		pause.setImage(ImageHelper.pause48);
		pause.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {

				Project p = null;
				for (Project project : Main.getProjectListAll()) {

					if (Integer.parseInt(comboProjects.getText()) == project
							.getId()) {

						p = project;
					}

				}
				setRun(false);
				AnimationEvents.pause(map, p);

			}
		});

		Button stop = new Button(group2, SWT.NONE);
		stop.setImage(ImageHelper.stop48);
		stop.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {

				Project p = null;
				for (Project project : Main.getProjectListAll()) {

					if (Integer.parseInt(comboProjects.getText()) == project
							.getId()) {

						p = project;
					}

				}
				setRun(false);
				AnimationEvents.stop(map, p, comboDrivingday.getText());
			}
		});

		Group legend = new Group(this, SWT.SHADOW_ETCHED_IN);
		legend.setText("Legende : ");
		GridLayout gridLayout3 = new GridLayout();
		gridLayout3.numColumns = 4;
		legend.setLayout(gridLayout3);

		Label train = new Label(legend, SWT.NONE);
		train.setText("Züge :");
		gridData = new GridData();
		gridData.horizontalSpan = 4;
		gridData.horizontalAlignment = SWT.BEGINNING;
		train.setLayoutData(gridData);

		Label iceText = new Label(legend, SWT.NONE);
		iceText.setText("ICE");

		Label ice = new Label(legend, SWT.NONE);
		ice.setImage(ImageHelper.ice);

		Label icText = new Label(legend, SWT.NONE);
		icText.setText("    IC");

		Label ic = new Label(legend, SWT.NONE);
		ic.setImage(ImageHelper.ic);

		Label sBahnText = new Label(legend, SWT.NONE);
		sBahnText.setText("S-Bahn");

		Label sBahn = new Label(legend, SWT.NONE);
		sBahn.setImage(ImageHelper.sBahn);

		Label dampflockText = new Label(legend, SWT.NONE);
		dampflockText.setText("    Dampflock");

		Label dampflock = new Label(legend, SWT.NONE);
		dampflock.setImage(ImageHelper.dampflock);

		Label gueterText = new Label(legend, SWT.NONE);
		gueterText.setText("Güterzug");

		Label gueterzug = new Label(legend, SWT.NONE);
		gueterzug.setImage(ImageHelper.gueterZug);

		Label regioText = new Label(legend, SWT.NONE);
		regioText.setText("    RegioBahn");

		Label regio = new Label(legend, SWT.NONE);
		regio.setImage(ImageHelper.regioBahn);

		Label pathSpeed = new Label(legend, SWT.NONE);
		pathSpeed.setText("Streckengeschwindigkeiten :");
		gridData = new GridData();
		gridData.horizontalSpan = 4;
		gridData.horizontalAlignment = SWT.BEGINNING;
		pathSpeed.setLayoutData(gridData);

		Label redText = new Label(legend, SWT.NONE);
		redText.setText("100 km/h");

		Label red = new Label(legend, SWT.NONE);
		red.setImage(ImageHelper.redPath);

		Label blueText = new Label(legend, SWT.NONE);
		blueText.setText("    150 km/h");

		Label blue = new Label(legend, SWT.NONE);
		blue.setImage(ImageHelper.bluePath);

		Label greenText = new Label(legend, SWT.NONE);
		greenText.setText("200 km/h");

		Label green = new Label(legend, SWT.NONE);
		green.setImage(ImageHelper.greenPath);

		Label orangeText = new Label(legend, SWT.NONE);
		orangeText.setText("   250 km/h");

		Label orange = new Label(legend, SWT.NONE);
		orange.setImage(ImageHelper.orangePath);

		Label limeText = new Label(legend, SWT.NONE);
		limeText.setText("300 km/h");

		Label lime = new Label(legend, SWT.NONE);
		lime.setImage(ImageHelper.limePath);

	}

	/**
	 * Setter for TimerLable
	 */
	private void setTimerLabel() {

		if ((Integer.parseInt(houre.getText()) < 10)
				&& Integer.parseInt(minutes.getText()) < 10) {
			timer.setText("Timer : " + "0" + houre.getText() + " : " + "0"
					+ minutes.getText());
		} else if ((Integer.parseInt(houre.getText()) >= 10)
				&& Integer.parseInt(minutes.getText()) < 10) {
			timer.setText("Timer : " + houre.getText() + " : " + "0"
					+ minutes.getText());
		} else if ((Integer.parseInt(houre.getText()) >= 10)
				&& Integer.parseInt(minutes.getText()) >= 10) {
			timer.setText("Timer : " + houre.getText() + " : "
					+ minutes.getText());
		} else if ((Integer.parseInt(houre.getText()) < 10)
				&& Integer.parseInt(minutes.getText()) >= 10) {
			timer.setText("Timer : " + "0" + houre.getText() + " : "
					+ minutes.getText());
		}

	}

	/**
	 * The method loadProjectIDs loads the trainID's into a String[]
	 * 
	 * @param projectIDs
	 * @return projectIDs - - the id's of the project
	 */
	public static String[] loadProjectIDs(String[] projectIDs) {

		for (int i = 0; i < Main.getProjectListAll().size(); i++) {
			Integer id = Main.getProjectListAll().get(i).getId();
			projectIDs[i] = id.toString();
		}

		return projectIDs;
	}

	/**
	 * The method getNodeNames loads the names into a String[] and sort them
	 * with the bubble-sort.
	 * 
	 * @return n - the name entries
	 */
	public static String[] getNodeNames() {
		Object[] names = map.getNodes().keySet().toArray();
		String[] n = new String[names.length];
		for (int i = 0; i < names.length; i++) {
			n[i] = names[i].toString();
		}
		bubblesort(n);
		// Arrays.sort(n);
		return n;
	}

	/**
	 * This method contains the bubble-sort to sort the entries ascending.
	 * 
	 * @param n
	 *            - the entries
	 */
	private static void bubblesort(String[] n) {

		boolean unsortiert = true;
		String temp;

		while (unsortiert) {
			unsortiert = false;
			for (int i = 0; i < n.length - 1; i++) {
				if (Integer.parseInt(n[i]) >= Integer.parseInt(n[i + 1])) {
					temp = n[i];
					n[i] = n[i + 1];
					n[i + 1] = temp;
					unsortiert = true;

				}
			}
		}
	}

	/**
	 * Getter for the SimulationSpeed
	 * 
	 * @return scaleSimulationSpeed - scaling the speed of the simulation
	 */
	public static int getSimulationSpeed() {

		return scaleAnimationSpeed.getMaximum() + 1
				- scaleAnimationSpeed.getSelection();
	}

	/**
	 * Getter for Animation Speed
	 * 
	 * @return scaleAnimationSpeed - scaling the speed of the animation
	 */
	public static int getAnimationSpeed() {

		return scaleAnimationSpeed.getSelection() / 100;
	}

	/**
	 * The method projectCheck proves the animation and throws exception
	 * messages if the animation is already running or if no project was
	 * selected.
	 * 
	 * @return check - if the statements are false there will be displayed
	 *         errors
	 */
	private boolean projectCheck() {

		boolean check = true;
		message = "";

		if (isRun() == true) {
			message = "Die animmation läuft bereits ! \n";
			check = false;
		}
		if (comboProjects.getText().equalsIgnoreCase("")) {

			message = "Sie haben kein Projekt ausgewählt ! \n";
			check = false;
		}

		return check;
	}

	public static boolean isRun() {
		return run;
	}

	public static void setRun(boolean run) {
		AnimationControllerCanvas.run = run;
	}

}
