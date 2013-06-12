package de.hohenheim.controller.events;

import de.hohenheim.controller.main.Main;
import de.hohenheim.modell.Train;
import de.hohenheim.modell.project.Project;
import de.hohenheim.modell.train.TrainData;
import de.hohenheim.view.map.NodeMap;

public class AnimationEvents {

	public static void drawTrains(NodeMap map, int idProject) {
	
		for (Project project : Main.projectListAll) {
			
			if (idProject == project.getId()){
				
				for(int j = 0; j < project.getTraindataProjectList().size(); j++){
					
					
					new Train(map, map.getNodes().get(String.valueOf(project.getTimeTableProjectList().get(j).getStartstation())),
							project.getTraindataProjectList().get(j).getID());
					
				}
			}
	
			
		}
		
	}

}
