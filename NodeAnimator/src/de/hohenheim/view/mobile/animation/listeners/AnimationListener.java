package de.hohenheim.view.mobile.animation.listeners;

import java.util.EventListener;

/**
 * The class AnimationListener contains the interface to start or to finish an
 * animation.
 * 
 * @author Arthur Kaul, Besim Gashi, Mathias Zwiesele, Daniel Intili, Bernd
 *         Hofs‰ﬂ
 * 
 * @version 1.0
 */
public interface AnimationListener extends EventListener {

	public void animationStarted(AnimationStartedEvent e);

	public void animationFinished(AnimationFinishedEvent e);

}
