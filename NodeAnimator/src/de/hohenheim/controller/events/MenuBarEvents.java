package de.hohenheim.controller.events;

import de.hohenheim.controller.main.Main;

public class MenuBarEvents {

	public static void closeProgramm() {
		
		Main.getShell().close();
		Main.getShell().getDisplay().dispose();
		
	}

	public static void changeLookToAnimation() {
		
		Main.getcTabFolder().setSelection(0);
		
	}

	public static void changeLookToProject() {
		
		Main.getcTabFolder().setSelection(1);
		
	}

	public static void changeLookToTrain() {
		
		Main.getcTabFolder().setSelection(2);
		
	}

	public static void changeLookToTimetable() {
		
		Main.getcTabFolder().setSelection(3);
		
	}

}
