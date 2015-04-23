/**
 * 
 */
package com.mygdx.chroma.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.chroma.Constants;

/** 
 * The superclass for all enemies in the game.
 */
public class Enemy extends Entity
{
	/** A collection of bits to decide the state of the enemy; i.e. is it moving, jumping,
	 * attacking, etc. */
	public int	state;
	
	/** The position of the player. Used for tracking. */
	public float pX, pY;
	
	/**
	 * Overrides the generic entity update method.
	 * @param pX the x-coordinate of the player's position
	 * @param pY the y-coordinate of the player's position
	 */
	public void update(float pX, float pY)
	{
		this.pX=pX;
		this.pY=pY;
		
		if((state&Constants.STUNNED)==Constants.STUNNED)
			if(Constants.getElapsed(Constants.STUNNED)>Constants.STUN_TIME) state &= ~Constants.STUNNED;
		if((state&Constants.STUNNED)!=Constants.STUNNED)
			updateMovement();
	}

	public void knockback(Body player)
	{

	}

	public void damage(Player p)
	{
		//p.state |= Constants.STUNNED;

	}
}
