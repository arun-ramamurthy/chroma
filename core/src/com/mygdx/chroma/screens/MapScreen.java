/**
 * 
 */
package com.mygdx.chroma.screens;
/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.Sounds;
import com.mygdx.chroma.Spriting;

/**
 * Represents the game map screen.
 */
public class MapScreen extends Screen
{
	private int mapStartWidth=Constants.SCREEN_WIDTH/4;
	private int mapStartHeight=Constants.SCREEN_HEIGHT/4;
	private float tileWidth=Constants.SCREEN_HEIGHT/5;
	private float tileHeight=Constants.SCREEN_HEIGHT/5;

	private int currRow;
	private int currCol;

	private boolean encounter;
	private int cont;

	public Texture[][] map;
	public Texture[][] lightMap;
	public Texture[][] darkMap;


	private Sprite player;

	@Override
	public void create()
	{
		Sounds.MAP_BGM.play();
	}

	public void playerMoved(int row, int col)
	{
		WorldState.hidden[row][col]=false;
		//traveledMap[row][col] = true;

		if(row-1>=0) WorldState.found[row-1][col]=true;
		if(row+1<Constants.GRID_SIZE) WorldState.found[row+1][col]=true;
		if(col-1>=0) WorldState.found[row][col-1]=true;
		if(col+1<Constants.GRID_SIZE) WorldState.found[row][col+1]=true;

	}

	public void movePlayer()
	{
		if(Gdx.input.isKeyJustPressed(Keys.UP)&&!encounter)
		{
			if(WorldState.currCol+1<Constants.GRID_SIZE)

				WorldState.currCol+=1;
		}
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)&&!encounter)
		{
			if(WorldState.currCol-1>=0)

				WorldState.currCol-=1;
		}
		if(Gdx.input.isKeyJustPressed(Keys.LEFT)&&!encounter)
		{
			if(WorldState.currRow-1>=0)

				WorldState.currRow-=1;
		}
		if(Gdx.input.isKeyJustPressed(Keys.RIGHT)&&!encounter)
		{
			if(WorldState.currRow+1<Constants.GRID_SIZE)

				WorldState.currRow+=1;
		}
		if(WorldState.on[WorldState.currRow][WorldState.currCol]==false)
			encounter=true;
		else
			encounter=false;
		playerMoved(WorldState.currRow, WorldState.currCol);
		cont=0;
		if(Gdx.input.isKeyPressed(Keys.SPACE) && !WorldState.on[WorldState.currRow][WorldState.currCol])
		{
			WorldState.on[WorldState.currRow][WorldState.currCol]=true;
			Sounds.MAP_BGM.stop();
			ScreenManager.setScreen(new FightScreen());
			//ScreenManager.setScreen(Constants.FIGHT_ARRAY[currRow][currCol]);
		}
	}

	@Override
	public void update()
	{
		boolean win = true;
		for(int row=0; row<Constants.GRID_SIZE; row++)
			for(int col=0; col<Constants.GRID_SIZE; col++)
			{
				
				if(!WorldState.on[row][col])
					win = false;
			}
		if(win)
			ScreenManager.setScreen(new WinScreen());
	}


	@Override
	public void render(SpriteBatch batch)
	{
		//Color c = new Color(batch.getColor());
		batch.begin();
		movePlayer();
		for(int row=0; row<Constants.GRID_SIZE; row++)
			for(int col=0; col<Constants.GRID_SIZE; col++)
			{
				if(WorldState.on[row][col]==true)
					batch.draw(WorldState.fullmap[row][col], mapStartWidth+row*tileWidth, mapStartHeight+col*tileHeight, tileWidth, tileHeight); //sprite, xloc,yloc,xsize,ysize
				else if(WorldState.found[row][col]==true)
					batch.draw(WorldState.lightMap[row][col], mapStartWidth+row*tileWidth, mapStartHeight+col*tileHeight, tileWidth, tileHeight); //sprite, xloc,yloc,xsize,ysize
				else
					batch.draw(WorldState.darkMap[row][col], mapStartWidth+row*tileWidth, mapStartHeight+col*tileHeight, tileWidth, tileHeight); //sprite, xloc,yloc,xsize,ysize
			}
		batch.draw(WorldState.player, WorldState.currRow*tileWidth+mapStartWidth+tileWidth/4, WorldState.currCol*tileHeight+mapStartHeight+tileWidth/4);
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
	}
}