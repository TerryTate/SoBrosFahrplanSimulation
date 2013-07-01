package de.hohenheim.controller.events;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.project.Project;
import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.map.NodeMap;

/**
 * 
 * @author Arthur Kaul
 *
 */

public class AnimationPlay implements Runnable{

	private boolean stop;
	private boolean start;
	private boolean pause = false;
		
	int houre;
	int min;
	private static NodeMap map;
	private static Project p;
	
	
	public AnimationPlay(){
		
	    setHoure(0);
	    setMin(0);
	    setStop(true);
	   
	}
	
	private void setMin(int min) {
		this.min = min;
		
	}

	private void setHoure(int houre) {
		this.houre = houre;	
	}

	/**
	 * 
	 *  
	 *  
	 */
    public void run(){
    	
    	if(!isStart()){
			if(getMin() < 59){
				setMin(getMin()+1);
			}else{
				setHoure(getHoure()+1);
				setMin(0);
				
			}
		}
		updateTime(houre, min);
		if(!isStop() && !isPause()){
			setStart(false);
			AnimationProcess.calculateSimulation(getP(), getMap());
			getMap().getDisplay().timerExec(AnimationControllerCanvas.getSimulationSpeed(), this);
		}
    }
    
	private void updateTime(int houre, int min) {
		
		   if((min == 59) && (houre == 23)){
			   min = 0; 
			   houre = 0;
			   AnimationEvents.stop(map, p);
			   AnimationControllerCanvas.setRun(false);
			   MessageBox messageBox = new MessageBox(Main.getMainShell(), SWT.ERROR | SWT.OK);
		        messageBox.setMessage("Fahrtag komplett Abgespielt !");    
		        messageBox.open();
		   }
		  
		   if ((houre < 10) && (min < 10)){
			
			  AnimationControllerCanvas.timer.setText("Timer : " + "0" + houre + " : " + "0" + min);
		   }else if ((houre >= 10) && (min < 10)){
			
			   AnimationControllerCanvas.timer.setText("Timer : " + houre + " : " + "0" + min);
		   }else if ((houre >= 10) && (min >= 10)){
			 
			   AnimationControllerCanvas.timer.setText("Timer : "  + houre + " : "  + min);
		   }else if ((houre < 10) && (min >= 10)){
		
			   AnimationControllerCanvas.timer.setText("Timer : " + "0" + houre + " : " + min);
		   }
			
		}

    private int getHoure(){
		return houre;
	}

	private int getMin(){ 
		return min;
	}

	private boolean isStart() {
		
		return start;
	}

	public boolean isStop() {
		return stop;
	}

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

	private void setProject(Project p2) {
		this.setP(p2);
		
	}

	private void setMap(NodeMap map2) {
		this.map = map2;
		
	}

	private void setStart(boolean start) {
		this.start = start;
		
	}

	private void setPause(boolean pause) {
	    this.pause = pause;
		
	}

	private void setStop(boolean stop) {
		this.stop = stop;	
	}

	public boolean isPause() {
		return pause;
	}

	public void unpause() {
		setPause(false);
		setStart(true);
		run();	
	}

	public void pause() {
	
		setPause(true);
		
	}
    
	public void stop(){
	
		setStop(true);
		setPause(false);
		getMap().getDisplay().timerExec(-1, this);

	}

	public static Project getP() {
		return p;
	}

	public void setP(Project p) {
		this.p = p;
	}

	public static NodeMap getMap() {
		return map;
	}

}
