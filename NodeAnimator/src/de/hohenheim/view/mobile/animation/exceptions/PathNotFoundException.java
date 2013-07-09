package de.hohenheim.view.mobile.animation.exceptions;

/**
 * The class PathNotFoundException contains the errors if the searching paths
 * are not found.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class PathNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public PathNotFoundException() {
		super("No path could be found between the two Nodes ...");
	}

	public PathNotFoundException(String pathname1, String pathname2) {
		super("No path could be found between the two Nodes " + pathname1
				+ " and " + pathname2);
	}
}