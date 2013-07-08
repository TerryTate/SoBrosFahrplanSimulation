package de.hohenheim.controller.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import de.hohenheim.modell.Node;
import de.hohenheim.modell.State;
import de.hohenheim.modell.timetable.Timetable;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.mobile.AnimationFigure;
import de.hohenheim.view.mobile.TrainFigure;
import de.hohenheim.view.mobile.animation.Animator;
import de.hohenheim.view.mobile.animation.BusyAnimator;
import de.hohenheim.view.mobile.animation.BusyWaitAnimator;
import de.hohenheim.view.node.NodeFigure;

public class BlockFinder implements Runnable{
	/**
	 * Führt die Erkennung von Deadlocks aus.
	 */
	@Override
	public void run() {
		List<TrainData> waitingTrains = new Vector<TrainData>();
		List<TrainData> finishedTrains = new Vector<TrainData>();
		List<List<TrainData>> deadlocks = new Vector<List<TrainData>>();
		List<List<TrainData>> finishedTrainDeadlocks = new Vector<List<TrainData>>();
		int k = 0;
		
		// Get waiting and finished Trains
		for (TrainData train : AnimationPlay.getP().getTraindataProjectList()) {
			    
			    Timetable tt = AnimationPlay.getP().getTimeTableProjectList().get(k);
				AnimationFigure af = AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train.getID()));
				if (af != null) {
					NodeFigure nextStation = null;
					if(tt.getVisits() == tt.getMiddlestations().size()){
						
					    nextStation = AnimationPlay.getMap().getNodes().get(String.valueOf(tt.getEndstation()));
					}else if(tt.getVisits() < tt.getMiddlestations().size()){
					    nextStation = AnimationPlay.getMap().getNodes().get(String.valueOf(tt.getMiddlestations().get(tt.getVisits())));
					}

					if (af.getCurrentAnimation() instanceof BusyAnimator
							&& nextStation == null) {
						
						finishedTrains.add(train);
						
						// train is not waiting and has no further stations
					} else if (nextStation == null) {
						
						finishedTrains.add(train);
						
					
					} else if (af.getCurrentAnimation() instanceof BusyWaitAnimator) {
						
						waitingTrains.add(train);
					}
				}
				k++;
		}

		// Check whether a finished train blocks a waiting train from going to
		// next node.
		// If so, the finished train has to move to another free node
		for (TrainData train : waitingTrains) {
			for (TrainData finishedTrain : finishedTrains) {
				if (getWaitingForNodes(AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train.getID())).getAnimationList()).contains(
						AnimationPlay.getMap().getMobileObjects().get(String.valueOf(finishedTrain.getID())).getNodeFigure())) {
					
					List<TrainData> deadlock = new Vector<TrainData>();
					deadlock.add(train);
					deadlock.add(finishedTrain);
					finishedTrainDeadlocks.add(deadlock);
				}
			}
		}

		// Check whether two waiting trains are blocking each other
		for (TrainData train : waitingTrains) {
			
			for (TrainData waitingTrain : waitingTrains) {
				if (train.getID() != waitingTrain.getID()) {
					
					//Check whether a deadlock between these trains has already been detected.
					boolean deadlockAlreadyDetected = false;
					for(List<TrainData> deadlock : deadlocks){
						if(deadlock.contains(train) && deadlock.contains(waitingTrain)){
							deadlockAlreadyDetected = true;
							break;
						}
					}
					
					//check for deadlock between these two trains
					if (!deadlockAlreadyDetected && getWaitingForNodes(AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train.getID())).getAnimationList()).contains(
							AnimationPlay.getMap().getMobileObjects().get(String.valueOf(waitingTrain.getID())).getNodeFigure())
							&& getWaitingForNodes(AnimationPlay.getMap().getMobileObjects().get(String.valueOf(waitingTrain.getID())).getAnimationList()).contains(
									AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train.getID())).getNodeFigure())) {
						
						//add the detected deadlock to the deadlocks list
						List<TrainData> deadlock = new Vector<TrainData>();
						deadlock.add(train);
						deadlock.add(waitingTrain);
						deadlocks.add(deadlock);
					}
				}
			}
		}
		
		//Start resolving deadlocks
		//
		//Not a smart way to do this... resolving one deadlock could resolve multiple deadlocks and
		//the resolving actions won't make any sense. But since there are no resolving actions
		//this doesn't matter.
		for(List<TrainData> deadlock : finishedTrainDeadlocks){
			resolveBlockFinishedTrain(deadlock.get(0), deadlock.get(1));
		}
		for(List<TrainData> deadlock : deadlocks){
			resolveBlock(deadlock.get(0), deadlock.get(1));
		}
	}

	/**
	 * Löst ein Deadlock zwischen zwei Zügen auf, von denen einer einen Knoten blockiert, da er
	 * keine weiteren Fahrplan Stationen mehr hat (der finishedTrain).
	 * 
	 * @param train
	 * @param finishedTrain
	 */
	private void resolveBlockFinishedTrain(TrainData train,
			TrainData finishedTrain) {
		//get actual route of "train" and search for the next UNBLOCKED node that is not contained in this actual path near the "finishedTrain".
		//let the finishedTrain go to the UNBLOCKED node, so the "train" can move on. Afterwards reset the timetable of the finishedTrain to the last
		//station, so the finishedTrain is on the right node at the end of the simulation.
		//
		//if no free node walkable is found, show an error message.
		//
		ArrayList<NodeFigure> unblockedNodes = new ArrayList<NodeFigure>();
		List<NodeFigure> routeOfTrain = getWaitingForNodes(AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train.getID())).getAnimationList());
		
		for(NodeFigure n : AnimationPlay.getMap().getNodes().values()){
			if(State.statemap.get(n.getName()).geState() == State.UNBLOCKED &&
					!routeOfTrain.contains(n)){
				unblockedNodes.add(n);
			}
		}
		
		//get a random node of the unblocked nodes
		double zufallszahl = Math.random();
		zufallszahl = zufallszahl * (unblockedNodes.size()-1);
		System.out.println("KnotenArray-Länge: "+unblockedNodes.size()+" - Zufallszahl: "+zufallszahl);
		int index = (int)zufallszahl;
		
		AnimationPlay.getP().getTimetable(finishedTrain.getID()).setBlocked(true);
		AnimationPlay.getP().getTimetable(finishedTrain.getID()).setVisits(AnimationPlay.getP().getTimetable(finishedTrain.getID()).getMiddlestations().size());
		
		//start walkTo random node
		AnimationEvents.walkTo(((TrainFigure)AnimationPlay.getMap().getMobileObjects().get(String.valueOf(finishedTrain.getID()))), unblockedNodes.get(index), AnimationPlay.getMap());
		
	}

	/**
	 * Löst ein Deadlock zwischen zwei Zügen auf. Hierbei wird die Priorität der Züge 
	 * beachtet und der niedriger priorisierte Zug muss ausweichen.
	 *  
	 * @param train1
	 * @param train2
	 */
	private void resolveBlock(TrainData train1, TrainData train2) {
		
		//Train with lower priority has to move away from its path
		int value1 = getValue(train1.getPriority());
		int value2 = getValue(train2.getPriority());
		
		if(value1 < value2){
			//train2 has to move away to next free node or something
			ArrayList<NodeFigure> unblockedNodes = new ArrayList<NodeFigure>();
			List<NodeFigure> routeOfTrain = getWaitingForNodes(AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train1.getID())).getAnimationList());
			
			for(NodeFigure n : AnimationPlay.getMap().getNodes().values()){
				if(State.statemap.get(n.getName()).geState() == State.UNBLOCKED &&
						!routeOfTrain.contains(n)){
					unblockedNodes.add(n);
				}
			}
			
			//get a random node of the unblocked nodes
			double zufallszahl = Math.random();
			zufallszahl = zufallszahl * (unblockedNodes.size()-1);
			int index = (int)zufallszahl;
			
			AnimationPlay.getP().getTimetable(train2.getID()).setBlocked(true);
			
			//start walkTo random node
			AnimationEvents.walkTo(((TrainFigure) AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train2.getID()))), unblockedNodes.get(index), AnimationPlay.getMap());
			
		}else{
			//priority is higher or the same - train1 has to move away to next free node or something
			ArrayList<NodeFigure> unblockedNodes = new ArrayList<NodeFigure>();
			List<NodeFigure> routeOfTrain = getWaitingForNodes(AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train2.getID())).getAnimationList());
			
			for(NodeFigure n : AnimationPlay.getMap().getNodes().values()){
				if(State.statemap.get(n.getName()).geState() == State.UNBLOCKED &&
						!routeOfTrain.contains(n)){
					unblockedNodes.add(n);
				}
			}
			
			//get a random node of the unblocked nodes
			double zufallszahl = Math.random();
			zufallszahl = zufallszahl * (unblockedNodes.size()-1);
			System.out.println("KnotenArray-Länge: "+unblockedNodes.size()+" - Zufallszahl: "+zufallszahl);
			int index = (int)zufallszahl;

			AnimationPlay.getP().getTimetable(train2.getID()).setBlocked(true);
			
			//start walkTo random node
			AnimationEvents.walkTo(((TrainFigure) AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train1.getID()))), unblockedNodes.get(index), AnimationPlay.getMap());
		}
		
	}

	private int getValue(String priority) {
		
		if(priority.equalsIgnoreCase("Sehr Wichtig")){
			return 1;
		}else if(priority.equalsIgnoreCase("Wichtig")){
			return 2;
		}else if(priority.equalsIgnoreCase("Normal")){
			return 3;
		}else{
			return 4;
		}
		
	}

	private List<NodeFigure> getWaitingForNodes(
			ArrayList<Animator> animationList) {
		List<NodeFigure> waitingForNodes = new Vector<NodeFigure>();
		for(Animator a : animationList){
			if(a instanceof BusyWaitAnimator){
				BusyWaitAnimator b = (BusyWaitAnimator)a;
				Node n = (Node)b.getState().getObject();
				waitingForNodes.add(n.getNodeFigure());
			}
		}
		return waitingForNodes;
		
	}
}
