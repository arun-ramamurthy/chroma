/**
 * 
 */
package com.mygdx.chroma;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * @author Arun
 *
 */
public class DummyPlatform extends Entity
{

    /**
     * 
     */
    public DummyPlatform(float x, float y, float w, float h, Texture img)
    {
	super();
	
	this.bdef.type=BodyType.StaticBody;
	this.bdef.position.set(x,y);
	
	FixtureDef fixture=new FixtureDef();
	PolygonShape shape=new PolygonShape();
	shape.setAsBox(w/2, h/2);
	fixture.shape=shape;
	this.fixtures.add(fixture);
	
	FixtureData data = new FixtureData(img, w, h);
	this.data.add(data);
    }

}
