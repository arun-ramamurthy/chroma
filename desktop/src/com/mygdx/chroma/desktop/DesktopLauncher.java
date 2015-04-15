package com.mygdx.chroma.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.chroma.ChromaMain;
import com.mygdx.chroma.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new ChromaMain(), config);
		config.title = "Chroma";
        config.width = Constants.WIDTH;
        config.height = Constants.HEIGHT;
	}
}
