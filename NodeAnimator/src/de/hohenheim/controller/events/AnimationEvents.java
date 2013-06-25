package de.hohenheim.controller.events;

import java.util.Iterator;
import de.hohenheim.modell.State;
import de.hohenheim.modell.Train;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.view.map.NodeMap;
import de.hohenheim.view.mobile.AnimationFigure;
import de.hohenheim.view.mobile.TrainFigure;
import de.hohenheim.view.mobile.Utility;
import de.hohenheim.view.node.NodeFigure;

public class AnimationEvents {

	public static void drawTrains(NodeMap map, Project p) {

		map.getAnimationLayer().removeAll();
		
		//Delete all animations, so that unfinished ones can't cause any errors 
		//(in case of simulation stop)
		for(AnimationFigure af : map.getMobileObjects().values()){
			af.clearAnimations();
		}
		
		//Delete all mobile objects, so that no duplicates are created
		map.getMobileObjects().clear();
		
	    for(int j = 0; j < p.getTraindataProjectList().size(); j++){
	    
    		new Train(map, map.getNodes().get(String.valueOf(p.getTimeTableProjectList().get(j).getStartstation())),
			p.getTraindataProjectList().get(j).getID());
					    							    	 	
		}
	    
	}	

    public static void updateNodeState(NodeMap map) {
		   	
   		//Alle Knoten auf unblocked setzen
   		for(String s : State.statemap.keySet()){
   			State.statemap.get(s).setState(State.UNBLOCKED);
   		}
    		
   		//Alle Knoten auf denen ein Zug steht auf BLOCKED setzen
   	    for(AnimationFigure af : map.getMobileObjects().values()){
   	    	TrainFigure tf = (TrainFigure)af;
   	    	NodeFigure n = tf.getNodeFigure();
   	    	State.statemap.get(n.getName()).setState(State.BLOCKED);
       }
    			
	}

	public static void start(NodeMap map, Project p, int houre, int min) {
	    if(AnimationProcess.player.isStop()){
	    	
		    AnimationEvents.drawTrains(map, p);
			AnimationProcess.player.start(houre, min, map, p);
	    }else if(!AnimationProcess.player.isStop() && AnimationProcess.player.isPause()){ 
		    AnimationProcess.startAnimations(p, map);
		    AnimationProcess.player.unpause();
	    }    	
	
	}

	public static void walkTo(TrainFigure trainFigure, NodeFigure nodeFigure, NodeMap map) {
		
		trainFigure.stopAnimation();
		trainFigure.clearAnimations();
			
		//Auf alle Knoten warten.
		Iterator<NodeFigure> walkingpath = Utility.getOptimizedRoute(
					map, 
					trainFigure.getNodeFigure(),
					nodeFigure
					);
			while(walkingpath.hasNext()){
				trainFigure.waitFor(State.statemap.get(walkingpath.next().getName()));
			}
			
			trainFigure.walkTo(nodeFigure);
			trainFigure.busy(2);
			trainFigure.startAnimation();
		}

	public static void pause(NodeMap map, Project p) {
		if(!AnimationProcess.player.isPause() && !AnimationProcess.player.isStop()){
			AnimationProcess.player.pause();
			AnimationProcess.stopAnimations(map, p);
		}
		
	}

	public static void stop(NodeMap map, Project p) {
		if (!AnimationProcess.player.isStop()) {
			AnimationProcess.player.stop();
			drawTrains(map, p);
			AnimationProcess.stopAnimations(map, p);
			for (Timetable tt : p.getTimeTableProjectList()) {
				tt.setVisits(0);
			}
		}
		
	}
		
}
