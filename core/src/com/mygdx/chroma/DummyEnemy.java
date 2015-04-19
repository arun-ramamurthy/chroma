/**
 * 
 */
package com.mygdx.chroma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * @author Arun
 *
 */
public class DummyEnemy extends Enemy
{
	public DummyEnemy(float x, float y)
	{
		super();

		// Body work
		this.bDef.type = BodyType.DynamicBody;
		this.bDef.position.set(x, y);

		// main body fixture work
		PolygonShape shape = new PolygonShape();
		FixtureData data = new FixtureData(Constants.ENEMY_MAIN, new Texture(Gdx.files.internal("dummy-blue.jpg")), .75f, 1.5f);
		FixtureDef fixture = new FixtureDef();
		shape.setAsBox(data.getWidth()/2, data.getHeight()/2);
		fixture.shape = shape;
		fixture.density = 0.5f;
		fixture.friction = 1f;
		fixture.restitution = 0f;
		fixture.isSensor = false;
		data.setFd(fixture);
		this.data.put(Constants.ENEMY_MAIN, data);

		state = 0b000;
	}
}
