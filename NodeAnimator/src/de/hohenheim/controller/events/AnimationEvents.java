package de.hohenheim.controller.events;

import java.util.HashMap;
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

/**
 * All Logical Methods for the Animation to start the animation stop the animation 
 * pause the animation, drawTrains on the map, refresh Stats of the Nodes.
 * 
 * @author Arthur Kaul
 *
 */
public class AnimationEvents {


	/**
	 * Draw all trains on the startstations from the Project into a given map 
	 *  
	 * @param map - NodeMap with Nodes and Paths 
	 * @param p -Project witch should be play
	 */
	public static void drawTrains(NodeMap map, Project p) {

		map.getAnimationLayer().removeAll();
		
		for(AnimationFigure af : map.getMobileObjects().values()){
			af.clearAnimations();
		}
		
		map.getMobileObjects().clear();
		
	    for(int j = 0; j < p.getTraindataProjectList().size(); j++){
	    
    		new Train(map, map.getNodes().get(String.valueOf(p.getTimeTableProjectList().get(j).getStartstation())),
			p.getTraindataProjectList().get(j).getID());
    		updateNodeState(map);
					    							    	 	
		}
	    
	}	

	/**
	 * 
	 * Refresh all Stats of the Nodes in the Map to Blocked if a 
	 * Train on the Node and to Unblocked if no train on the Node
	 * 
	 * @param map - NodeMap with the Nodes
	 * 
	 */
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

    /**
     * Method start the Animation if it is Stopped or Paused 
     *  
     * @param map - NodeMap with all Nodes and Paths
     * @param p - Project witch is be played
     * @param houre - int Starttime houre
     * @param min -int Starttime minutes
     */
	public static void start(NodeMap map, Project p, int houre, int min) {
	    if(AnimationProcess.player.isStop()){
	    	
		    AnimationEvents.drawTrains(map, p);
			AnimationProcess.player.start(houre, min, map, p);
	    }else if(!AnimationProcess.player.isStop() && AnimationProcess.player.isPause()){ 
		    AnimationProcess.startAnimations(p, map);
		    AnimationProcess.player.unpause();
	    }    	
	
	}

	  /**
     * Method waltTo wait for all Nodes witch are need for 
     * a Train Move and then starts the MoveAnimation of a Train
     * 
     * @param trainFigure - TrainFigure with should be move
     * @param nodeFigure - NodeFigure where the train have to move
     * @param map - NodeMap with all Nodes and Paths of the map
     */
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

	/**
	 * 
	 * Method pause stops the animation but it can be 
	 * play on at the stopped Time with the Play Button.
	 * 
	 * @param map - NodeMap with all Nodes and Paths of the Map
	 * @param p - Project witch is played at the moment
	 */
	public static void pause(NodeMap map, Project p) {
		if(!AnimationProcess.player.isPause() && !AnimationProcess.player.isStop()){
			AnimationProcess.player.pause();
			AnimationProcess.stopAnimations(map, p);
		}
		
	}

	 /**
     * Method stop break up the animation so that a new animation can be play
     * 
     * @param map - NodeMap with all Nodes and Paths of the Map 
     * @param p - Project witch is played and should be stopped
     */
	public static void stop(NodeMap map, Project p) {
		if (!AnimationProcess.player.isStop()) {
			AnimationProcess.player.stop();
			drawTrains(map, p);
			for (Timetable tt : p.getTimeTableProjectList()) {
				tt.setVisits(0);
				tt.setDeadlockHandling(false);
				tt.setHandled(false);
			}
			setNodesUnblocked(map);
		}
		
	}

	/**
	 * Methode setNodesUnblocked set all Nodes of the map to Unblocked 
	 * 
	 * @param map - NodeMap with all Nodes and Paths
	 */
	private static void setNodesUnblocked(NodeMap map) {
		HashMap<String, NodeFigure> nodesToUnblocked = map.getNodes();
		for(int j = 1; j <= nodesToUnblocked.size(); j++){
			State.statemap.get(String.valueOf(j)).setState(State.UNBLOCKED);
		}
		
	}
		
}
