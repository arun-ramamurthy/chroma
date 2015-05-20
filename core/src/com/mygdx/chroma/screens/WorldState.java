/**
 * 
 */
package com.mygdx.chroma.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.Spriting;

/** @author Arun */
public class WorldState
{

	public static boolean[][]	on			= new boolean[Constants.GRID_SIZE][Constants.GRID_SIZE];
	public static boolean[][]	found		= new boolean[Constants.GRID_SIZE][Constants.GRID_SIZE];
	public static boolean[][]	hidden		= new boolean[Constants.GRID_SIZE][Constants.GRID_SIZE];

	public static MapScreen		map			= new MapScreen();

	public static int			playerHP	= 1000;
	
	public static int currRow;
	public static int currCol;

	public static Texture[][] fullmap;
	public static Texture[][] lightMap;
	public static Texture[][] darkMap;

	public static Sprite player;
	public static float numSlimes=2;
	public static float numBeasts=0;
	public static float numSkeletons=0;
	
	public static int steps=0;
	
	public static void restart()
	{
		on = new boolean[Constants.GRID_SIZE][Constants.GRID_SIZE];
		found = new boolean[Constants.GRID_SIZE][Constants.GRID_SIZE];
		hidden = new boolean[Constants.GRID_SIZE][Constants.GRID_SIZE];

		fullmap=new Texture[Constants.GRID_SIZE][Constants.GRID_SIZE];
		darkMap=new Texture[Constants.GRID_SIZE][Constants.GRID_SIZE];
		lightMap=new Texture[Constants.GRID_SIZE][Constants.GRID_SIZE];
		
		player=new Sprite(new Texture("player-standing.png"));
		for(int row=0; row<Constants.GRID_SIZE; row++)
			for(int col=0; col<Constants.GRID_SIZE; col++)
			{
				fullmap[row][col]=Spriting.TILE.getTexture();
				darkMap[row][col]=Spriting.HIDDEN_TILE.getTexture();
				lightMap[row][col]=Spriting.FOUND_TILE.getTexture();

				WorldState.hidden[row][col]=true;
				WorldState.on[row][col]=false;
				WorldState.found[row][col]=false;

			}
		currRow=0;
		currCol=0;
		map.playerMoved(0, 0);
		playerHP = 1000;
	}

	
}
