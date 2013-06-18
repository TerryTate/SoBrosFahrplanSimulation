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

public class AnimationEvents {

	public static void drawTrains(NodeMap map, int idProject) {
	 
		for (Project project : Main.projectListAll) {
			
			if (idProject == project.getId()){
				
				for(int j = 0; j < project.getTraindataProjectList().size(); j++){
					
					Timetable tt = project.getTimeTableProjectList().get(j);
					
					for(int i = 0; i < tt.getDrivingdays().size(); i++){
						
					    if(tt.getDrivingdays().get(i).equalsIgnoreCase(AnimationControllerCanvas.comboDrivingday.getText())){
					    	
					    	if((tt.getStartHouer() == Integer.parseInt(AnimationControllerCanvas.houre.getText())) && (tt.getStartMinutes() == Integer.parseInt(AnimationControllerCanvas.minutes.getText()))){
					    		
					    		new Train(map, map.getNodes().get(String.valueOf(project.getTimeTableProjectList().get(j).getStartstation())),
								           project.getTraindataProjectList().get(j).getID());
					    		
					    	}				    	
					    }
					}
				}
			}	
		}
		
		
	}
	
	public static void setAnimations(NodeMap map, Project p){
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
								System.out.println(tt.getVisits());
								System.out.println(tt.getMiddlestations().size());
								tf.walkTo(map,  map.getNodes().get(String.valueOf(tt.getMiddlestations().get(tt.getVisits()))));
								tt.setVisits(tt.getVisits() + 1);
							}
								
						}
							
					}
		    		
					
				
	    	
	   
	    }
	}
    
    public static void updateNodeState(NodeMap map) {
		
		for(AnimationFigure af : map.getMobileObjects().values()){
		    	TrainFigure tf = (TrainFigure)af;
		    	NodeFigure n = tf.getNodeFigure();
		        State.statemap.get(n.getName()).setState(State.BLOCKED);
		   
		    }
		
	}

	public static boolean isBlocked(NodeFigure nodeFigure) {
	
		if(State.statemap.get(nodeFigure).geState() == State.BLOCKED ){
			return true;
		}else{
			return false;
		}
		
	}


}
