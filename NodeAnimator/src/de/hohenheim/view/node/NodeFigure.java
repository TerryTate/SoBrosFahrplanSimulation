package de.hohenheim.view.node;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * This class is for drawing of a room.
 * @author Marc Fernandes
 *
 */
public class NodeFigure extends Figure {
	private String name = new String();
	
	private Object modellObject;
	
	private Image backgroundImage=null;
	
	/**
	 * Constructor. Creates a room with a name.
	 * The Name will be displayed in the center of the figure.
	 * @param String roomName 
	 */
	
	public NodeFigure(Object modellObject) {
		setModelObject(modellObject);
	}
	
	public NodeFigure(Object modellObject, Image backgroundImg) {
		this(modellObject);
		this.backgroundImage=backgroundImg;
	}
	
	/**
	 * Returns the modell object of this RoomFigure.
	 * @return {@link Object}
	 */
	public Object getModellObject() {
		return this.modellObject;
	}
	/**
	 * Sets the Object which is the modell part of this view object .
	 * @param modellObject - The modell object which belongs to this RoomFigure.
	 */
	public void setModelObject(Object modellObject) {
		this.modellObject=modellObject;
	}
	
	/**
	 * Sets the name of the room, which then is beeing displayed.
	 * @param roomName - the name of the room.
	 */
	public void setName(String roomName) {
	    name = roomName;
	    repaint();
	}	
	/**
	 * Returns the name of the room.
	 * 
	 * @return String name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Draws a rectangle which represents a room in a RoomMap.
	 * The name of the room will be placed in the middle of the rectangle.
	 */
	public void paintFigure(Graphics g) {
      Rectangle r = bounds;
      
      Rectangle r_tmp = r.getCopy();
      g.setBackgroundColor(ColorConstants.lightGray);
      g.fillRectangle(r_tmp);
      
      if(backgroundImage!=null) {
        g.drawImage(backgroundImage, r_tmp.x,r_tmp.y);
      }
      g.setForegroundColor(ColorConstants.darkGray);
      g.drawRectangle(r.x, r.y, r.width-1, r.height-1);
      
      g.setForegroundColor(ColorConstants.white);
      
      Font f = g.getFont();
      Dimension dim = FigureUtilities.getStringExtents(name, f);
      g.drawText(name, r.x+r.width/2-dim.width/2 , r.y+r.height/2-dim.height/2);
  }  
}