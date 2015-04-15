/**
 * 
 */
package com.mygdx.chroma;

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

    public Dummy(float x, float y, float w, float h, Texture img)
    {
	super();
	
	this.bdef.type=BodyType.DynamicBody;
	this.bdef.position.set(x,y);
	
	FixtureDef fixture=new FixtureDef();
	PolygonShape shape=new PolygonShape();
	shape.setAsBox(w/2, h/2);
	fixture.shape=shape;
	fixture.density=0.5f;
	fixture.friction=0.3f;
	fixture.restitution=0.3f;
	this.fixtures.add(fixture);
	
	FixtureData data = new FixtureData(img, w, h);
	this.data.add(data);
    }
}

