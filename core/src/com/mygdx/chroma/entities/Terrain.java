/**
 * 
 */
package com.mygdx.chroma.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.FixtureData;

/**
 * Randomized terrain for the fight screen. Terrain is composed of n stacks, each with 1m long blocks in a column.
 *
 */
public class Terrain extends Entity
{
	/**
	 * Generates an array of n Terrain stacks to fill the screen. 
	 * @param n the number of stacks 
	 * @param midH the midpoint line for the stacks
	 * @param amp the amount of random variation to use. Results vary from midH +/- amp.
	 * @param top the texture of the first block of the stack
	 * @param side the texture of the remaining blocks of the stack
	 * @return an array of Terrain stacks
	 */
	public static Terrain[] generate(int n, float midH, float amp, TextureRegion top, TextureRegion side)
	{
		Terrain[] terrain=new Terrain[n];
		for(int s=0; s<n; s++)
		{
			float w = Constants.PHYSICS_WIDTH/n;
			float x = s*w - Constants.PHYSICS_WIDTH/2 +w/2;
			float y = midH + (float) Math.random()*amp*2-1 - Constants.PHYSICS_HEIGHT/2;
			terrain[s]=new Terrain(x, y, w, top, side);
		}
		return terrain;
	}
	
	/**
	 * Represents a single terrain stack, of y+3 meter height and w width.
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param texture
	 */
	public Terrain(float x, float y, float w, TextureRegion top, TextureRegion side)
	{

		this.bDef.type=BodyType.StaticBody;
		this.bDef.position.set(x,y);
		
		FixtureDef fixture=new FixtureDef();
		PolygonShape shape=new PolygonShape();
		shape.setAsBox(w/2, 0.5f);
		fixture.friction=1;
		fixture.filter.categoryBits = Constants.TERRAIN;
		fixture.filter.maskBits = Constants.PLAYER|Constants.ENEMY|Constants.OBJECT;
		fixture.shape=shape;
		FixtureData data = new FixtureData(0, top, w, 1);
		data.setFd(fixture);
		this.data.put(Constants.MAIN, data);
		for(float tempY=-1; tempY>-5f; tempY-=1f)
		{
			FixtureData tData = new FixtureData((int)tempY, side, w, 1);
			tData.setyOffset(tempY);
			tData.setFd(fixture);
			this.data.put((int)tempY, tData);
			
		}
		
	}
	
}
