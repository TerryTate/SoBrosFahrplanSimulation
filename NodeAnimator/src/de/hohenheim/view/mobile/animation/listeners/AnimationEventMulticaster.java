package de.hohenheim.view.mobile.animation.listeners;

import java.util.Vector;

/**
 * The class AnimationEventMulticaster contains the different ActionListener for
 * the animation.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public class AnimationEventMulticaster implements AnimationListener {

	protected Vector<AnimationListener> listeners = new Vector<AnimationListener>();

	/**
	 * The ActionListener add.
	 * 
	 * @param listener
	 */
	public void add(AnimationListener listener) {
		if (!listeners.contains(listener)) {
			this.listeners.add(listener);
		}
	}

	/**
	 * The ActionListener remove.
	 * 
	 * @param listener
	 */
	public void remove(AnimationListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * The ActionListener to finish the animation.
	 */
	@Override
	public void animationFinished(AnimationFinishedEvent e) {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.elementAt(i).animationFinished(e);
		}
	}

	/**
	 * The ActionListener to start the animation.
	 */
	@Override
	public void animationStarted(AnimationStartedEvent e) {
		for (int i = 0; i < listeners.size(); i++) {
			listeners.elementAt(i).animationStarted(e);
		}
	}
}
