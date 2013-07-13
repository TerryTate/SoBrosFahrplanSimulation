package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.project.Project;
import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.map.NodeMap;

/**
 * AnimationPlay class implements Runnable so that the calculation of the Timer
 * and trains can refresh
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */

public class AnimationPlay implements Runnable {

	private boolean stop;
	private boolean start;
	private boolean pause = false;

	int houre;
	int min;
	String drivingday;
	private static NodeMap map;
	private static Project p;

	/**
	 * Constructor for a new AnimationPlay
	 * 
	 */
	public AnimationPlay() {

		setHoure(0);
		setMin(0);
		setStop(true);

	}

	/**
	 * 
	 * Setter for the Minutes of the Time
	 * 
	 */
	private void setMin(int min) {
		this.min = min;

	}

	/**
	 * Setter for the hour of the Time
	 * 
	 * @param houre
	 */
	private void setHoure(int houre) {
		this.houre = houre;
	}

	/**
	 * Method run check if the animation is played, paused or stopped and call
	 * if play is true the Method to update the timer and update the Animation
	 * of the trains
	 * 
	 */
	public void run() {

		if (!isStart()) {
			if (getMin() < 59) {
				setMin(getMin() + 1);
			} else {
				setHoure(getHoure() + 1);
				setMin(0);

			}
		}
		updateTime(houre, min, drivingday);
		if (!isStop() && !isPause()) {
			setStart(false);
			AnimationProcess.calculateSimulation(getP(), getMap(), houre, min);
			getMap().getDisplay().timerExec(
					AnimationControllerCanvas.getSimulationSpeed(), this);
		}
	}

	/**
	 * 
	 * Method updateTime refresh the Timer of the GUI in the
	 * AnimationeControllerCanvas
	 * 
	 * @param houre
	 * @param min
	 */
	private void updateTime(int houre, int min, String drivingday) {

		if ((min == 59) && (houre == 23)) {
			min = 0;
			houre = 0;
			AnimationEvents.stop(map, p, drivingday);
			AnimationControllerCanvas.setRun(false);
			MessageBox messageBox = new MessageBox(Main.getMainShell(),
					SWT.ERROR | SWT.OK);
			messageBox.setMessage("Fahrtag komplett Abgespielt !");
			messageBox.open();
		}

		if ((houre < 10) && (min < 10)) {

			AnimationControllerCanvas.timer.setText("Timer : " + "0" + houre
					+ " : " + "0" + min);
		} else if ((houre >= 10) && (min < 10)) {

			AnimationControllerCanvas.timer.setText("Timer : " + houre + " : "
					+ "0" + min);
		} else if ((houre >= 10) && (min >= 10)) {

			AnimationControllerCanvas.timer.setText("Timer : " + houre + " : "
					+ min);
		} else if ((houre < 10) && (min >= 10)) {

			AnimationControllerCanvas.timer.setText("Timer : " + "0" + houre
					+ " : " + min);
		}

	}

	/**
	 * Getter for hour
	 * 
	 * @return
	 */
	private int getHoure() {
		return houre;
	}

	/**
	 * Getter for the minutes
	 * 
	 * @return
	 */
	private int getMin() {
		return min;
	}

	/**
	 * Getter for start
	 * 
	 * @return boolean - start
	 */
	private boolean isStart() {

		return start;
	}

	/**
	 * Getter for Stop
	 * 
	 * @return boolean - stop
	 */
	public boolean isStop() {
		return stop;
	}

	/**
	 * Method start sets the StartTime the Project the map and stop and pause to
	 * false and call then the run Method
	 * 
	 * @param houre
	 *            - start time hour
	 * @param min
	 *            - start time minutes
	 * @param map
	 *            - NodeMap with all Nodes and Paths
	 * @param p
	 *            - project witch should be play
	 */
	public void start(int houre, int min, NodeMap map, Project p) {
		setHoure(houre);
		setMin(min);
		setMap(map);
		setProject(p);
		setStop(false);
		setPause(false);
		setStart(true);

		run();

	}

	/**
	 * Setter for Project
	 * 
	 * @param p2
	 */
	private void setProject(Project p2) {
		this.setP(p2);

	}

	/**
	 * Setter for map
	 * 
	 * @param map2
	 */
	private void setMap(NodeMap map2) {
		AnimationPlay.map = map2;

	}

	/**
	 * Setter for start
	 * 
	 * @param start
	 */
	private void setStart(boolean start) {
		this.start = start;

	}

	/**
	 * Setter for pause
	 * 
	 * @param pause
	 */
	private void setPause(boolean pause) {
		this.pause = pause;

	}

	/**
	 * Setter for stop
	 * 
	 * @param stop
	 */
	private void setStop(boolean stop) {
		this.stop = stop;
	}

	/**
	 * Getter for pause
	 * 
	 * @return
	 */
	public boolean isPause() {
		return pause;
	}

	/**
	 * Method unpause starts the Animation after a pause
	 * 
	 */
	public void unpause() {
		setPause(false);
		setStart(true);
		run();
	}

	/**
	 * Method pause stops the Animation but the animation can be play on when
	 * the start button is pushed
	 * 
	 */
	public void pause() {

		setPause(true);

	}

	/**
	 * Method stop stops the Animation the animation can¥t be play on
	 * 
	 */
	public void stop() {

		setStop(true);
		setPause(false);
		getMap().getDisplay().timerExec(-1, this);

	}

	/**
	 * Getter for Project
	 * 
	 * @return
	 */
	public static Project getP() {
		return p;
	}

	/**
	 * Setter for Project
	 * 
	 * @param p
	 */
	public void setP(Project p) {
		AnimationPlay.p = p;
	}

	/**
	 * Setter for map
	 * 
	 * @return
	 */
	public static NodeMap getMap() {
		return map;
	}

}
