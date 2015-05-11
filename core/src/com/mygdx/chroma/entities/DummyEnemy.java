/**
 * 
 */
package com.mygdx.chroma.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.FixtureData;

/** @author Arun */
public class DummyEnemy extends Enemy
{
	public DummyEnemy(float x, float y, float w, float h)
	{
		super();

		// Body work
		this.bDef.type = BodyType.DynamicBody;
		this.bDef.position.set(x, y);

		// main body fixture work
		PolygonShape shape = new PolygonShape();
		FixtureData data = new FixtureData(Constants.ENEMY_MAIN, new TextureRegion(new Texture(Gdx.files.internal("dummy-blue.jpg"))), w, h);
		FixtureDef fixture = new FixtureDef();
		shape.setAsBox(data.getWidth()/2, data.getHeight()/2);
		fixture.shape = shape;
		fixture.density = 0.5f;
		fixture.friction = 1f;
		fixture.restitution = .1f;
		fixture.isSensor = false;
		fixture.filter.categoryBits = Constants.ENEMY;
		fixture.filter.maskBits = Constants.TERRAIN | Constants.ENEMY;
		data.setFd(fixture);
		this.data.put(Constants.ENEMY_MAIN, data);

		state = 0b000;
	}

	@Override
	public void updateMovement()
	{
		if(Math.abs(pX-this.body.getPosition().x)>1+(Math.random()*this.data.get(Constants.ENEMY_MAIN).getWidth()))
			if(pX>this.body.getPosition().x)
				this.body.setLinearVelocity(1.5f, this.body.getLinearVelocity().y);
			else
				this.body.setLinearVelocity(-1.5f, this.body.getLinearVelocity().y);
		else
			this.body.setLinearVelocity(-1.5f, this.body.getLinearVelocity().y);
	}
}
