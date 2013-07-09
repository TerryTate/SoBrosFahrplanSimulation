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
public class AnimationStartedEvent extends EventObject {

	private static final long serialVersionUID = 2L;

	public final static int MOVE_STARTED = 0;
	public final static int LOADING_STARTED = 1;
	public final static int UNLOADING_STARTED = 2;
	public final static int BUSY_STARTED = 3;

	private int animationtype;

	/**
	 * A Constructor.
	 * 
	 * @param source
	 * @param animationtype
	 */
	public AnimationStartedEvent(Object source, int animationtype) {
		super(source);
		this.animationtype = animationtype;
	}

	/**
	 * Getter of AnimationType
	 * 
	 * @return animationtype
	 */
	public int getAnimationtype() {
		return this.animationtype;
	}

	/**
	 * Getter of Source
	 */
	public Object getSource() {
		return super.getSource();
	}

	public String toString() {
		return super.toString();
	}

}
