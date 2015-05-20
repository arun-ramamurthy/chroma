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
public class Beast extends Enemy
{
	public Beast(float x, float y)
	{
		super();

		this.setBaseHp(50);

		// Body work
		this.bDef.type = BodyType.DynamicBody;
		this.bDef.position.set(x, y);

		// main body fixture work
		PolygonShape shape = new PolygonShape();
		FixtureData data = new FixtureData(Constants.MAIN, Spriting.ANIM_BEAST_WALK.getKeyFrame(0), 1f, 0.5f);
		FixtureDef fixture = new FixtureDef();
		shape.setAsBox(data.getWidth()/2, data.getHeight()/2);
		fixture.shape = shape;
		fixture.density = 1f;
		fixture.friction = 1f;
		fixture.restitution = 0f;
		fixture.isSensor = false;
		fixture.filter.categoryBits = Constants.ENEMY;
		fixture.filter.maskBits = Constants.TERRAIN;
		data.setFd(fixture);
		this.data.put(Constants.MAIN, data);

		state = 0b000;
	}
	@Override
	public void update(float pX, float pY)
	{
		super.update();
		this.pX = pX;
		this.pY = pY;
		if(this.state == (this.state | Constants.MOVING))
		{
			if(pX<this.body.getPosition().x) 
				this.dir=Constants.LEFT;
			else
				this.dir=Constants.RIGHT;
		}

	}
	@Override
	public void updateMovement()
	{
		float dir = 1;
		if(this.isDir(Constants.LEFT)) dir = -1;
		System.out.println(Math.abs(this.body.getPosition().x-pX));
		if(this.state != (this.state | Constants.ATTACKING))
		{
			this.body.setLinearVelocity(dir*1f, this.body.getLinearVelocity().y);
			if(Math.abs(this.body.getPosition().x-pX)<=2)
			{
				this.state = this.state | Constants.ATTACKING;
			}
		}
		else if(this.body.getLinearVelocity().x == 0 && this.body.getPosition().x <= 7.45)
		{
			this.body.setLinearVelocity(dir*1f, 2f*Constants.JUMP_FORCE);
		}
		else
		{
			this.body.setLinearVelocity(dir*2f, this.body.getLinearVelocity().y);
			if(Math.abs(this.body.getPosition().x-pX)>=3)
			{
				this.state = Constants.MOVING;
			}
		}


	}

	@Override
	public void updateTextures()
	{
		if(state==0) atime = 0;
		this.data.get(Constants.MAIN).setTexture(
				Spriting.ANIM_BEAST_WALK.getKeyFrame(atime += Gdx.graphics.getDeltaTime(), true));
		
		if((state&Constants.MOVING)==Constants.MOVING) {
			this.data.get(Constants.MAIN).setTexture(
					Spriting.ANIM_BEAST_WALK.getKeyFrame(atime += Gdx.graphics.getDeltaTime(), true));
		}
		if((state&Constants.ATTACKING)==Constants.ATTACKING) {
			this.data.get(Constants.MAIN).setTexture(
					Spriting.ANIM_BEAST_RUN.getKeyFrame(atime += Gdx.graphics.getDeltaTime(), true));
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
		p.loseHealth(10);

	}
}
