/**
 * 
 */
package com.mygdx.chroma.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.FixtureData;
import com.mygdx.chroma.Spriting;

/** @author Arun */
public class Skeleton extends Enemy
{
	public Skeleton(float x, float y)
	{
		super();

		this.setBaseHp(40);
		
		// Body work
		this.bDef.type = BodyType.DynamicBody;
		this.bDef.position.set(x, y);

		// main body fixture work
		PolygonShape shape = new PolygonShape();
		FixtureData data = new FixtureData(Constants.MAIN, Spriting.ANIM_SKELETON_WALK.getKeyFrame(0), 1f, 1f);
		FixtureDef fixture = new FixtureDef();
		shape.setAsBox(data.getWidth()/2, data.getHeight()/2);
		fixture.shape = shape;
		fixture.density = 0.75f;
		fixture.friction = 1f;
		fixture.restitution = 0f;
		fixture.isSensor = false;
		fixture.filter.categoryBits = Constants.ENEMY;
		fixture.filter.maskBits = Constants.TERRAIN;
		data.setFd(fixture);
		this.data.put(Constants.MAIN, data);

		state = Constants.MOVING;
	}

	@Override
	public void updateMovement()
	{
		float dir = 1;
		if(this.isDir(Constants.LEFT)) dir = -1;
		
		if((this.state != (this.state | Constants.ATTACKING)) && this.body.getLinearVelocity().x == 0 && this.body.getLinearVelocity().y == 0)
			this.body.applyLinearImpulse(0f, 2f, this.body.getPosition().x,this.body.getPosition().y , true);

		else if(this.state != (this.state | Constants.ATTACKING))
		{
			
			this.setSpeed(1.5f);
			if(Math.abs(this.body.getPosition().x-pX)<1)
			{
				this.state|= Constants.ATTACKING;
				this.state = this.state & ~Constants.MOVING;
			}
		}
	
		else
		{
			this.setSpeed(0.5f);
			if(Math.abs(this.body.getPosition().x-pX)>=1)
			{
				this.state |= Constants.MOVING;
				this.state = this.state & ~Constants.ATTACKING;
			}
		}
		
		
	}

	@Override
	public void updateTextures()
	{
		if(state==0) atime = 0;
		this.data.get(Constants.MAIN).setTexture(
				Spriting.ANIM_SKELETON_WALK.getKeyFrame(0));
		
		if((state&Constants.MOVING)==Constants.MOVING) {
			this.data.get(Constants.MAIN).setTexture(
					Spriting.ANIM_SKELETON_WALK.getKeyFrame(atime += Gdx.graphics.getDeltaTime(), true));
		}
		else if((state&Constants.ATTACKING)==Constants.ATTACKING) {
			this.data.get(Constants.MAIN).setTexture(
					Spriting.ANIM_SKELETON_ATTACK.getKeyFrame(atime += Gdx.graphics.getDeltaTime(), true));
		}

	}


	public void knockback(Body p)
	{
		float dir = 1;
		if(this.isDir(Constants.LEFT)) dir = -1;
			p.applyLinearImpulse(dir*1.1f, 0.1f, p.getPosition().x, p.getPosition().y, true);
	}

	public void damage(ActiveEntity p)
	{
		super.damage(p);
		p.loseHealth(5);

	}
}
