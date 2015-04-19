/**
 * 
 */
package com.mygdx.chroma;

import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * A generic object in the battle screen world, consisting of a body, a body definition, and a set of fixtures.
 * Can be accessed direction from the body through the userData method.
 */
public abstract class Entity
{
    /** The body of the entity. */
    public Body body;
    /** The definition of the body of the entity. */
    protected BodyDef bDef;
    /** A Hashtable of FixtureData. 
     * Each FixtureData (and hence each Fixture and FixtureDef) in the body can be accessed by giving an Integer key from the Constants class .*/
    protected Hashtable<Integer, FixtureData> data;
    
    /** Default construct. */
    public Entity()
    {
	body = null;
	bDef=new BodyDef();
	data = new Hashtable<Integer, FixtureData>();
    }

    /**
     * Draws every fixture contained by this entity, given the screen's SpriteBatch. 
     * Draws in reverse order of the integer tags of the fixtures. 
     * @param batch
     */
    public void draw(SpriteBatch batch)
    {
	
	float renderX, renderY, renderW, renderH, theta;
	for(Integer tag : this.getData().keySet())
	{
	  
	    renderX=(Constants.PHYSICS_WIDTH/2+body.getPosition().x-data.get(tag).getWidth()/2+data.get(tag).getxOffset())*Constants.PIXELS_PER_METER;
	    renderY=(Constants.PHYSICS_HEIGHT/2+body.getPosition().y-data.get(tag).getHeight()/2+data.get(tag).getyOffset())*Constants.PIXELS_PER_METER;
	    renderW=data.get(tag).getWidth()*Constants.PIXELS_PER_METER;
	    renderH=data.get(tag).getHeight()*Constants.PIXELS_PER_METER;
	    theta=(float)((body.getAngle()+data.get(tag).getAngle(Constants.RADIANS))*Constants.DEGREES_PER_RADIAN);
	    batch.setColor(data.get(tag).getTint());
	    batch.draw(new TextureRegion(data.get(tag).getTexture()), renderX, renderY, data.get(tag).getWidth()/2*Constants.PIXELS_PER_METER, data.get(tag).getHeight()/2*Constants.PIXELS_PER_METER, renderW, renderH, 1f, 1f, theta);  
	    batch.setColor(Color.WHITE);
	}
    }
    
    /**
     * Generates the body with the body definition, and then attaches each fixture to the body. 
     * Then it adds each Fixture to the FixtureData corresponding with it for easy access.
     * @param world the world to generate the body in
     */
    public void instantiate(World world)
	 {
	     this.body = world.createBody(this.getBdef());
	     Fixture fixture;
	     for(Integer tag : this.getData().keySet())
	     {
		 this.body.createFixture(this.getData().get(tag).getFd());
		 fixture=this.body.getFixtureList().get(this.body.getFixtureList().size-1);
		 this.getData().get(tag).setFixture(fixture);
	     }
	     this.body.setUserData(this);
	 }
    
    /**
     * What the entity must do during each interval.
     */
    public void update()
    {
	
    }
    
    /**
     * Returns the private variable, this.bdef.
     * @return this.bdef
     */
    public BodyDef getBdef() {
        return this.bDef;
    }

    /**
     * Sets the private variable, this.body, to the passed parameter, body.
     * @param body the new value of this.body
     */
    public void setBdef(Body body) {
        this.body=body;
    }

    /**
     * Returns the private variable, this.body.
     * @return this.body
     */
    public Body getBody() {
        return this.body;
    }

    /**
     * Returns the private variable, this.data.
     * @return this.data
     */
    public Hashtable<Integer, FixtureData> getData()
    {
        return this.data;
    }
    
    /**
     * Returns the fixture associated with a particular tag, if present.
     * @param tag the tag of the fixture to be accessed
     * @return the Fixture if the Entity contains it. otherwise, null
     */
    public Fixture getFixture(Integer tag)
    {
	if(!this.data.keySet().contains(tag))
	    return null;
	else
	    return this.data.get(tag).getFixture();
    }

}
