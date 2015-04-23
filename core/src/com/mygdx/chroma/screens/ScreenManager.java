/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.screens;

/**
 * Manages which screens appear when.
 */
public abstract class ScreenManager {

	private static Screen currentScreen;

	public static void setScreen(Screen screen) {
		if (currentScreen != null)
			currentScreen.dispose();
		currentScreen = screen;
		currentScreen.create();
	}

	public static Screen getCurrentScreen() {
		return currentScreen;
	}

}