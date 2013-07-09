package de.hohenheim.view.mobile.animation.listeners;

import java.util.EventObject;

/**
 * The class AnimationEventMulticaster contains the different ActionListener to
 * finish the ongoing animation event.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class AnimationFinishedEvent extends EventObject {

	private static final long serialVersionUID = 2L;
    
	public final static int MOVE_FINISHED      = 0;
    public final static int LOADING_FINISHED   = 1;
    public final static int UNLOADING_FINISHED = 2;
    public final static int BUSY_FINISHED=3;
	
	private int animationtype;
	
	/**
	 * The ActionListener to finish an event
	 * 
	 * @param source
	 * @param animationtype
	 */
    public AnimationFinishedEvent(Object source, int animationtype) {
		super(source);
		this.animationtype=animationtype;
	}
	
	/**
	 * Getter for AnimationType
	 * 
	 * @return animationType
	 */
    public int getAnimationtype() {
    	return this.animationtype;
    }
    
	/**
	 * Getter for Source
	 */
	public Object getSource() {
		return super.getSource();
	}

	/**
	 * Getter for toString
	 */
	public String toString() {
		return super.toString();
	}

	
}
