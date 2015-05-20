/**
 * 
 */
package com.mygdx.chroma.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.Spriting;

/** The superclass for all enemies in the game. */
public abstract class Enemy extends ActiveEntity
{
	/** The position of the player. Used for tracking. */
	public float	pX, pY;

	/** Overrides the generic entity update method.
	 * 
	 * @param pX
	 *            the x-coordinate of the player's position
	 * @param pY
	 *            the y-coordinate of the player's position */
	public void update(float pX, float pY)
	{
		super.update();
		this.pX = pX;
		this.pY = pY;
		if(this.state == (this.state|Constants.MOVING))
		if(pX<this.body.getPosition().x) 
			this.dir=Constants.LEFT;
		else
			this.dir=Constants.RIGHT;

	}

	public void drawHP(SpriteBatch batch)
	{
		batch.end();
		Spriting.brush.begin(ShapeType.Filled);
		if(this.getHP()>0.5*this.getBaseHp())
			Spriting.brush.setColor(Color.GREEN);
		else if(this.getHP()>0.25*this.getBaseHp())
			Spriting.brush.setColor(Color.YELLOW);
		else 
			Spriting.brush.setColor(Color.RED);
		float renderX = (Constants.PHYSICS_WIDTH/2+body.getPosition().x + data.get(
				Constants.MAIN).getxOffset())
						*Constants.PIXELS_PER_METER;
		float renderY = (Constants.PHYSICS_HEIGHT/2+body.getPosition().y-data.get(Constants.MAIN).getHeight()/2+data.get(
				Constants.MAIN).getyOffset())
						*Constants.PIXELS_PER_METER;
		float renderH = data.get(Constants.MAIN).getHeight()*Constants.PIXELS_PER_METER;
		Spriting.brush.rect(renderX - 50 , renderY+renderH+20, 100*((float)this.getHP()/this.getBaseHp()), 10);
		Spriting.brush.end();
		batch.begin();

	}
}
