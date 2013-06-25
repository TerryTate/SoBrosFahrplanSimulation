package de.hohenheim.view.path;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.LineAttributes;
import de.hohenheim.view.mobile.Utility;

/**
 * This class is used to draw paths between rooms in a RoomMap.
 * @author Marc Fernandes
 *
 */
public class PathFigure extends PolylineConnection {
	 
	  /**
	   * The thickness of the polyline which is drawn.
	   */
	  public static int LINE_WIDTH = 3; 
	  
	  /*
	   * 
	   */
	  
	  final Color red = new Color(null,200,0,0);
	  final Color blue = new Color(null,0,200,0);
	  final Color green = new Color(null,0,0,200);
	  final Color orange = new Color(null, 255, 127, 0);
	  final Color lime = new Color(null, 127, 255, 127);
	  
	  
	  /**
	   * A Object that is connected to this {@link PathFigure} as the modell.
	   */
	  private Object modellObject;

	private int pathSpeed;

	  
	  /**
	   * The constructor sets a {@link BendpointConnectionRouter} to draw the path
	   * as a line.  
	 * @param pathSpeed 
	   */
	  public PathFigure(Object modellObject, int pathSpeed) {
		BendpointConnectionRouter router = new BendpointConnectionRouter();
	    
		/*
		 * 
		 */
		
		if(pathSpeed == 100){
			this.setBackgroundColor(red);
			this.setForegroundColor(red);
		}
		else if(pathSpeed == 150){
			this.setBackgroundColor(blue);
			this.setForegroundColor(blue);
		}
		else if(pathSpeed == 200){
			this.setBackgroundColor(green);
			this.setForegroundColor(green);
		}
		else if(pathSpeed == 250){
			this.setBackgroundColor(orange);
			this.setForegroundColor(orange);
		}
		else if(pathSpeed == 300){
			this.setBackgroundColor(lime);
			this.setForegroundColor(lime);
		}
		
		
	    setConnectionRouter(router);
	    LineAttributes attr = new LineAttributes(LINE_WIDTH);
	    this.setLineAttributes(attr);
	    this.modellObject=modellObject;
	    this.pathSpeed = pathSpeed;
	    
	  }
	  
	  

	/**
	   * Sets the modellObject for this {@link PathFigure}.
	   * @param modellObject - The modell object that belongs to this PathFigure.
	   */
	  public void setModellObject(Object modellObject) {
		  this.modellObject = modellObject;
	  }
	  
	  /**
	   * Returns the modellObject of this PathFigure.
	   * @return {@link Object}
	   */
	  public Object getModellObject() {
		  return this.modellObject;
	  }	  
	  
	  /**
	   * Calculates and returns the distance of this path.
	   * @return double
	   */
	  public double getDistance() {
		  return Utility.getDistance(this);
	  }
	  
	  public int getPathSpeed(){
		  return this.pathSpeed;
	  }
	}