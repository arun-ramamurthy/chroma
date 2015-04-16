/**
 * 
 */
package com.mygdx.chroma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * @author Arun
 *
 */
public class Dummy extends Entity
{

    public Dummy(float x, float y, float w, float h, float density, boolean dynamic, Texture img)
    {
	super();
	
	if(dynamic)
	    this.bdef.type=BodyType.DynamicBody;
	else
	    this.bdef.type=BodyType.StaticBody;
	this.bdef.position.set(x,y);
	
	FixtureDef fixture=new FixtureDef();
	PolygonShape shape=new PolygonShape();
	shape.setAsBox(w/2, h/2);
	fixture.shape=shape;
	fixture.density=density;
	fixture.friction=0.3f;
	fixture.restitution=0.3f;
	this.fixtures.add(fixture);
	
	FixtureData data = new FixtureData(img, w, h);
	this.data.add(data);
    }
    
    public void update()
    {
	this.body.setFixedRotation(true);
	if(Gdx.input.isKeyPressed(Keys.W))
		this.body.applyLinearImpulse(0f, 0.5f, this.body.getPosition().x, this.body.getPosition().y, true);
	if(Gdx.input.isKeyPressed(Keys.A))
		this.body.applyLinearImpulse(-0.1f, 0f, this.body.getPosition().x, this.body.getPosition().y+this.data.get(0).getHeight()/2, true);
	if(Gdx.input.isKeyPressed(Keys.D))
		this.body.applyLinearImpulse(0.1f, 0f, this.body.getPosition().x, this.body.getPosition().y+this.data.get(0).getHeight()/2, true);
	if(Gdx.input.isKeyPressed(Keys.S))
	    	this.body.applyLinearImpulse(0f, -0.5f, this.body.getPosition().x, this.body.getPosition().y, true);
	
    }
}

