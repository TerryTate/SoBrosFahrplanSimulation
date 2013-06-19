package de.hohenheim.controller.events;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.State;
import de.hohenheim.modell.Train;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.canvas.AnimationControllerCanvas;
import de.hohenheim.view.map.NodeMap;
import de.hohenheim.view.mobile.AnimationFigure;
import de.hohenheim.view.mobile.TrainFigure;
import de.hohenheim.view.node.NodeFigure;

/**
 * 
 * @author Arthur Kaul
 *
 */

public class AnimationPlay extends Thread{

	int idProject;
	String drivingday;
	int hour;
	int min;
	NodeMap map; 
	Project p;
	
	/**
	 * 
	 * @param idProject
	 * @param drivingday
	 * @param map
	 * @param hour
	 * @param min
	 * @param p
	 */
	public AnimationPlay(int idProject, String drivingday, NodeMap map, int hour, int min, Project p){
		
		this.idProject = idProject;
		this.drivingday = drivingday;
		this.map = map;
		this.hour = hour;
		this.min = min;
		this.p = p;
		
	}
	
	/**
	 * 
	 *  
	 *  
	 */
    public void run(){
        	
        	while (AnimationControllerCanvas.run == true){ 
                          
               try {
                  sleep(1000);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
               Main.getDisplay().asyncExec(new Runnable(){
                  public void run() {
                	  
                      draw();
                      update();
                  }
               });
        	
             }
         }
	
    /**
	 *     
	 */
	protected void draw() {
		drawTrains();
		
	}
    /**
     * 
     */
	private void drawTrains() {
				
				
					for(int j = 0; j < p.getTraindataProjectList().size(); j++){
						
						Timetable tt = p.getTimeTableProjectList().get(j);
						
						for(int i = 0; i < tt.getDrivingdays().size(); i++){
							
						    if(tt.getDrivingdays().get(i).equalsIgnoreCase(drivingday)){
						    	
						    	if((tt.getStartHouer() == AnimationControllerCanvas.hour) && (tt.getStartMinutes() == AnimationControllerCanvas.min)){
						    	  
//						    		if(AnimationEvents.isBlocked(map.getNodes().get(String.valueOf(project.getTimeTableProjectList().get(j).getStartstation())))){
//						    	    	System.out.println("Blocked");
//						    	    }else{
						    		 new Train(map, map.getNodes().get(String.valueOf(p.getTimeTableProjectList().get(j).getStartstation())),
									           p.getTraindataProjectList().get(j).getID());
						    		       //    AnimationEvents.updateNodeState(map);
						    		 updateAnimations();
						    		 updateStartAnimations();
						    	
						        }
						    	    
//						    	}				    	
						    }
						}
					}
					
				
			
		
	}
    
	/**
	 * 
	 */
	private void update() {
		//updateAnimations();
		updateTime();
		//updateStartAnimations();
		
	}

    /**
     * 
     */
	private void updateStartAnimations() {
		
		boolean animcheck = true; 
		
		if( animcheck == true) {
		
		    for(AnimationFigure af : map.getMobileObjects().values()){
			
			    TrainFigure tf = (TrainFigure) af;
			
		    	for(TrainData td : p.getTraindataProjectList()){
			   
			    	if(tf.getFigureId() == td.getID()){
					
				    	if(!td.getAnim()){
				 	     	 tf.startAnimation();	
				 	     	 td.setAnim(true);
				     	}
					
			     	}
				   
			     }     
		     }
		    
		}
		
     	}
	
    /***
     * 
     */
	private void updateAnimations() {
		
		for(AnimationFigure af : map.getMobileObjects().values()){
	    	TrainFigure tf = (TrainFigure)af;
	    	NodeFigure n = tf.getNodeFigure();
	    	
					for(int j = 0; j < p.getTraindataProjectList().size(); j++){
						
						TrainData td = p.getTraindataProjectList().get(j);
						Timetable tt = p.getTimeTableProjectList().get(j);
						
						if (tf.getFigureId() == td.getID()){
						
							if (tt.getVisits() == tt.getMiddlestations().size()){
								
								tf.walkTo(map, map.getNodes().get(String.valueOf(tt.getEndstation())));
								tt.setVisits(tt.getVisits() + 1);
							
							}else if(tt.getVisits()  < tt.getMiddlestations().size()){
								
								tf.walkTo(map,  map.getNodes().get(String.valueOf(tt.getMiddlestations().get(tt.getVisits()))));
								tt.setVisits(tt.getVisits() + 1);
							}
								
						}
							
					}
		    		
					
				
	    	
	   
	    }
			
		
		
	}

	/**
	 * 
	 * 
	 */
	private void updateTime() {
	
	   if((AnimationControllerCanvas.min == 59) && (AnimationControllerCanvas.hour == 23)){
		   AnimationControllerCanvas.run = false;
		   AnimationControllerCanvas.min = 0;
		   AnimationControllerCanvas.hour = 0;
	   }else if (AnimationControllerCanvas.min < 59){
		   AnimationControllerCanvas.min++;
	   }else{
		   AnimationControllerCanvas.min = 0;
		   AnimationControllerCanvas.hour++;
	   }
	  
	   if ((AnimationControllerCanvas.hour < 10) && (AnimationControllerCanvas.min < 10)){
		
		  AnimationControllerCanvas.timer.setText("Timer : " + "0" + AnimationControllerCanvas.hour + " : " + "0" + AnimationControllerCanvas.min);
	   }else if ((AnimationControllerCanvas.hour >= 10) && (AnimationControllerCanvas.min < 10)){
		
		   AnimationControllerCanvas.timer.setText("Timer : " + AnimationControllerCanvas.hour + " : " + "0" + AnimationControllerCanvas.min);
	   }else if ((AnimationControllerCanvas.hour >= 10) && (AnimationControllerCanvas.min >= 10)){
		 
		   AnimationControllerCanvas.timer.setText("Timer : "  + AnimationControllerCanvas.hour + " : "  + AnimationControllerCanvas.min);
	   }else if ((AnimationControllerCanvas.hour < 10) && (AnimationControllerCanvas.min >= 10)){
	
		   AnimationControllerCanvas.timer.setText("Timer : " + "0" + AnimationControllerCanvas.hour + " : " + AnimationControllerCanvas.min);
	   }
		
	}

}
