/**
 * 
 */
package com.mygdx.chroma.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.FixtureData;

/**
 * @author Arun
 *
 */
public class DummyPlatform extends Entity
{

    /**
     * Constructor
     */
    public DummyPlatform(float x, float y, float w, float h, TextureRegion img)
    {
	super();
	
	this.bDef.type=BodyType.StaticBody;
	this.bDef.position.set(x,y);
	
	FixtureDef fixture=new FixtureDef();
	PolygonShape shape=new PolygonShape();
	shape.setAsBox(w/2, h/2);
	fixture.friction=0;
	fixture.filter.categoryBits = Constants.TERRAIN;
	fixture.filter.maskBits = Constants.PLAYER|Constants.ENEMY|Constants.OBJECT;
	fixture.shape=shape;

	FixtureData data = new FixtureData(Constants.MAIN, img, w, h);
	data.setFd(fixture);
	this.data.put(Constants.MAIN, data);
    }

}
