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

/**
 * Java - Class to find Blocks between Trains so that the can move on and finished his Timetable
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofsäß
 * 
 * @version 1.0
 */
public class BlockFinder implements Runnable {

	@Override
	public void run() {
		List<TrainData> waitingTrains = new Vector<TrainData>();
		List<TrainData> finishedTrains = new Vector<TrainData>();
		List<List<TrainData>> blocks = new Vector<List<TrainData>>();
		List<List<TrainData>> finishedTrainBlocks = new Vector<List<TrainData>>();
		int k = 0;

		// Check if a Train is waiting or finished 

		for (TrainData train : AnimationPlay.getP().getTraindataProjectList()) {

			Timetable tt = AnimationPlay.getP().getTimeTableProjectList()
					.get(k);
			AnimationFigure af = AnimationPlay.getMap().getMobileObjects()
					.get(String.valueOf(train.getID()));
			if (af != null) {
				NodeFigure nextStation = null;
				
				if ((tt.getVisits())  == tt.getMiddlestations().size()) {
                    
					nextStation = AnimationPlay.getMap().getNodes()
							.get(String.valueOf(tt.getEndstation()));
					train.setFinish(true);
					
				} else if (tt.getVisits() < tt.getMiddlestations().size()) {
					nextStation = AnimationPlay
							.getMap()
							.getNodes()
							.get(String.valueOf(tt.getMiddlestations().get(
									tt.getVisits())));
				}
				
				
				
				if (af.getCurrentAnimation() instanceof BusyAnimator
						&& nextStation == null) {
					
					train.setFinish(true); 
					finishedTrains.add(train);

				
				}/*else if(train.isFinish()){
					finishedTrains.add(train);
				}*/
				else if (nextStation == null) {
					finishedTrains.add(train);
				
				
				

				} else if (af.getCurrentAnimation() instanceof BusyWaitAnimator){
                   
					waitingTrains.add(train);
				} else {
					waitingTrains.add(train);
				}
				
				
			}
			k++;
		}
    
		System.out.println("grösser  der wartenden züge " +waitingTrains.size());
		System.out.println("grösse der Fertigen Züge" + finishedTrains.size());
		
		// Check whether a finished Train block a Waiting Train
		for (TrainData train : waitingTrains) {
			
			for (TrainData finishedTrain : finishedTrains) {
				
				if (getWaitingForNodes(AnimationPlay.getMap().getMobileObjects().get(String.valueOf(train.getID()))
								.getAnimationList()).contains(
						AnimationPlay.getMap().getMobileObjects()
								.get(String.valueOf(finishedTrain.getID()))
								.getNodeFigure())) {
					
					List<TrainData> block = new Vector<TrainData>();
					block.add(train);
					block.add(finishedTrain);
					finishedTrainBlocks.add(block);
				}
			}
		}

		// Check whether two waiting trains are blocking each other
		
		for (TrainData train : waitingTrains) {

			for (TrainData waitingTrain : waitingTrains) {
				
				if (train.getID() != waitingTrain.getID()) {
                    
					// Check whether a deadlock between these trains has already
					// been detected.

					boolean blockWasFind = false;
					for (List<TrainData> deadlock : blocks) {
						if (deadlock.contains(train)
								&& deadlock.contains(waitingTrain)) {
							blockWasFind = true;
							break;
						}
					}

					// check for deadlock between these two trains
					if (!blockWasFind
							&& getWaitingForNodes(
									AnimationPlay.getMap().getMobileObjects()
											.get(String.valueOf(train.getID()))
											.getAnimationList()).contains(
									AnimationPlay
											.getMap()
											.getMobileObjects()
											.get(String.valueOf(waitingTrain
													.getID())).getNodeFigure())
							&& getWaitingForNodes(
									AnimationPlay
											.getMap()
											.getMobileObjects()
											.get(String.valueOf(waitingTrain
													.getID()))
											.getAnimationList()).contains(
									AnimationPlay.getMap().getMobileObjects()
											.get(String.valueOf(train.getID()))
											.getNodeFigure())) {

						// Add the detected deadlock to the deadlocks list

						List<TrainData> block = new Vector<TrainData>();
						block.add(train);
						block.add(waitingTrain);
						blocks.add(block);
					}
				}
			}
		}

		// resolve the Block how are was find
		
		for (List<TrainData> deadlock : finishedTrainBlocks) {
			resolveBlockFinishedTrain(deadlock.get(0), deadlock.get(1));
		}
		for (List<TrainData> deadlock : blocks) {
			resolveBlock(deadlock.get(0), deadlock.get(1));
		}
	}

	/**
	 * Resolve a block between two trains. One of them blocks the node
	 * because he has no Stations more and stand on a Node.
	 * 
	 * @param train
	 * @param finishedTrain
	 */
	private void resolveBlockFinishedTrain(TrainData train,
			TrainData finishedTrain) {
        
		// search a rout for the waiting Train to move him away
		ArrayList<NodeFigure> unblockedNodes = new ArrayList<NodeFigure>();
		List<NodeFigure> routeOfTrain = getWaitingForNodes(AnimationPlay
				.getMap().getMobileObjects().get(String.valueOf(train.getID()))
				.getAnimationList());

		for (NodeFigure n : AnimationPlay.getMap().getNodes().values()) {
			if (State.statemap.get(n.getName()).geState() == State.UNBLOCKED
					&& !routeOfTrain.contains(n)) {
				unblockedNodes.add(n);
			}
		}

		// Get a random node of the unblocked nodes

		double zufallszahl = Math.random();
		zufallszahl = zufallszahl * (unblockedNodes.size() - 1);
		
		int index = (int) zufallszahl;

		AnimationPlay.getP().getTimetable(finishedTrain.getID())
				.setBlocked(true);
//		AnimationPlay
//				.getP()
//				.getTimetable(finishedTrain.getID())
//				.setVisits(
//						AnimationPlay.getP()
//								.getTimetable(finishedTrain.getID())
//								.getMiddlestations().size());

		// Start walkTo random node

		System.out.println("BLOCK AUFlösung gestarte ");
		AnimationEvents.walkTo(
				((TrainFigure) AnimationPlay.getMap().getMobileObjects()
						.get(String.valueOf(finishedTrain.getID()))),
				unblockedNodes.get(index), AnimationPlay.getMap());
		

	}

	/**
	 * Resolve a block between to waiting Trains. the Train with lower Priority 
	 * have to move away 
	 * 
	 * @param train1
	 * @param train2
	 */
	private void resolveBlock(TrainData train1, TrainData train2) {

		// Train with lower priority has to move away from its Node
		int value1 = getValue(train1.getPriority());
		int value2 = getValue(train2.getPriority());

		if (value1 < value2) {
			// train2 has to move away to next free node or something
			ArrayList<NodeFigure> unblockedNodes = new ArrayList<NodeFigure>();
			List<NodeFigure> routeOfTrain = getWaitingForNodes(AnimationPlay
					.getMap().getMobileObjects()
					.get(String.valueOf(train1.getID())).getAnimationList());

			for (NodeFigure n : AnimationPlay.getMap().getNodes().values()) {
				if (State.statemap.get(n.getName()).geState() == State.UNBLOCKED
						&& !routeOfTrain.contains(n)) {
					unblockedNodes.add(n);
				}
			}

			// get a random node of the unblocked nodes
			double zufallszahl = Math.random();
			zufallszahl = zufallszahl * (unblockedNodes.size() - 1);
			int index = (int) zufallszahl;

			AnimationPlay.getP().getTimetable(train2.getID()).setBlocked(true);

			// start walkTo random node
			AnimationEvents.walkTo(((TrainFigure) AnimationPlay.getMap()
					.getMobileObjects().get(String.valueOf(train2.getID()))),
					unblockedNodes.get(index), AnimationPlay.getMap());

		} else {
			// priority of train2  is higher Train1 has to move away to next
			// free node or something
			ArrayList<NodeFigure> unblockedNodes = new ArrayList<NodeFigure>();
			List<NodeFigure> routeOfTrain = getWaitingForNodes(AnimationPlay
					.getMap().getMobileObjects()
					.get(String.valueOf(train2.getID())).getAnimationList());

			for (NodeFigure n : AnimationPlay.getMap().getNodes().values()) {
				if (State.statemap.get(n.getName()).geState() == State.UNBLOCKED
						&& !routeOfTrain.contains(n)) {
					unblockedNodes.add(n);
				}
			}

			// get a random node of the unblocked nodes
			double zufallszahl = Math.random();
			zufallszahl = zufallszahl * (unblockedNodes.size() - 1);
			int index = (int) zufallszahl;

			AnimationPlay.getP().getTimetable(train2.getID()).setBlocked(true);

			// start walkTo random node
		
			AnimationEvents.walkTo(((TrainFigure) AnimationPlay.getMap()
					.getMobileObjects().get(String.valueOf(train1.getID()))),
					unblockedNodes.get(index), AnimationPlay.getMap());
		}

	}

	/**
	 * Convert the Priority to a Value
	 * 
	 * @param priority
	 * @return
	 */
	private int getValue(String priority) {

		if (priority.equalsIgnoreCase("Sehr Wichtig")) {
			return 1;
		} else if (priority.equalsIgnoreCase("Wichtig")) {
			return 2;
		} else if (priority.equalsIgnoreCase("Normal")) {
			return 3;
		} else {
			return 4;
		}

	}

	/**
	 * Check if a finished or waiting Train stay on a Node witch is need by a 
	 * other Train.
	 * 
	 * @param animationList
	 * @return
	 */
	private List<NodeFigure> getWaitingForNodes(
			ArrayList<Animator> animationList) {
		List<NodeFigure> waitingForNodes = new Vector<NodeFigure>();
		for (Animator a : animationList) {
			if (a instanceof BusyWaitAnimator) {
				BusyWaitAnimator b = (BusyWaitAnimator) a;
				Node n = (Node) b.getState().getObject();
				waitingForNodes.add(n.getNodeFigure());
			}
		}
		return waitingForNodes;

	}
}
