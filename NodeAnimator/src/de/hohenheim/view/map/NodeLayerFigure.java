package de.hohenheim.view.map;

import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.MarginBorder;

/**
 * This class is a container to draw the rooms in a RoomMap.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
class NodeLayerFigure extends FreeformLayeredPane {
	public NodeLayerFigure() {
		setLayoutManager(new FreeformLayout());
		setBorder(new MarginBorder(5));
	}
}