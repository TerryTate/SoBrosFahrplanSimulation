package de.hohenheim.view.path;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * A class named CenterAnchor contains the Rectangle-Box and getReferencePoint.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class CenterAnchor extends ChopboxAnchor {

	/**
	 * A Constructor
	 * 
	 * @param fig
	 */
	public CenterAnchor(IFigure fig) {
		super(fig);
	}

	/**
	 * Getter of the Box.
	 * 
	 * @return r - the size of the rectangle box
	 */
	protected Rectangle getBox() {
		Rectangle tmp = getOwner().getBounds();
		Rectangle r = new Rectangle(tmp.x + tmp.width / 2, tmp.y + tmp.height
				/ 2, 0, 0);
		return r;
	}

	/**
	 * Getter of the ReferencePoint.
	 */
	public Point getReferencePoint() {
		Rectangle r = this.getOwner().getBounds();
		return new Point(r.x + r.width / 2, r.y + r.height / 2);
	}
}
