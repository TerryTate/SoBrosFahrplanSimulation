package de.hohenheim.modell;

import de.hohenheim.view.FigureFactory;
import de.hohenheim.view.map.NodeMap;
import de.hohenheim.view.path.PathFigure;

public class Path {
  
	private State state;
	private PathFigure path;
	private int speed;
	
	
	public Path(NodeMap map, Node start, Node end, int pathSpeed) {
		path = FigureFactory.createPath(map, start.getNodeFigure(), end.getNodeFigure(), this, pathSpeed);
		state = new State(this);
		this.speed = pathSpeed;
		
	}
	
	public State getState() {
		return this.state;
	}
	
	public PathFigure getPathFigure() {
		return this.path;
	}
	
	public int getSpeed(){
		return this.speed;
	}
}
