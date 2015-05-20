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
public class Slime extends Enemy
{
	public Slime(float x, float y)
	{
		super();

		this.setBaseHp(30);
		
		// Body work
		this.bDef.type = BodyType.DynamicBody;
		this.bDef.position.set(x, y);

		// main body fixture work
		PolygonShape shape = new PolygonShape();
		FixtureData data = new FixtureData(Constants.MAIN, Spriting.ANIM_SLIME.getKeyFrame(0), 0.5f, 0.5f);
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

		state = 0b000;
	}

	@Override
	public void updateMovement()
	{
		double r = Math.random()*1000;
		float dir = 1;
		if(this.isDir(Constants.LEFT)) dir = -1;
		if(r>990&&(state&Constants.JUMPING)!=Constants.JUMPING)
		{
				state |= Constants.JUMPING | Constants.ATTACKING;
				this.body.applyLinearImpulse(
						dir*((float)Math.random()*.15f+.3f),
						1.25f,
						this.body.getPosition().x,
						this.body.getPosition().y,
						true);
		}
		if(this.body.getLinearVelocity().y==0) state = state&~Constants.JUMPING&~Constants.ATTACKING;
	}

	@Override
	public void updateTextures()
	{
		if(state==0) atime = 0;
		this.data.get(Constants.MAIN).setTexture(Spriting.ANIM_SLIME.getKeyFrame(0));
		if((state&Constants.JUMPING)==Constants.JUMPING) {
			this.data.get(Constants.MAIN).setTexture(
					Spriting.ANIM_SLIME.getKeyFrame(atime += Gdx.graphics.getDeltaTime(), true));
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
