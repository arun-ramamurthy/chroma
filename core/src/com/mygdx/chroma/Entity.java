/**
 * 
 */
package com.mygdx.chroma;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Arun
 *
 */
public abstract class Entity
{
    public Body body;
    protected BodyDef bdef;
    protected ArrayList<FixtureDef> fixtures;
    protected ArrayList<FixtureData> data;
    
    public Entity()
    {
	body = null;
	bdef=new BodyDef();
	fixtures = new ArrayList<FixtureDef>();
	data = new ArrayList<FixtureData>();
    }

    public void draw(SpriteBatch batch)
    {
	
	float renderX, renderY, renderW, renderH, theta;
	for(int i=0; i<fixtures.size(); i++)
	{
	    renderX=(Constants.VIEWPORT_W/2+body.getPosition().x-data.get(i).getWidth()/2+data.get(i).getxOffset())*Constants.PIXELS_PER_METER;
	    renderY=(Constants.VIEWPORT_H/2+body.getPosition().y-data.get(i).getHeight()/2+data.get(i).getyOffset())*Constants.PIXELS_PER_METER;
	    renderW=data.get(i).getWidth()*Constants.PIXELS_PER_METER;
	    renderH=data.get(i).getHeight()*Constants.PIXELS_PER_METER;
	    theta=(float)((body.getAngle()+data.get(i).getAngle(Constants.RADIANS))*Constants.DEGREES_PER_RADIAN);
	    batch.draw(new TextureRegion(data.get(i).getTexture()), renderX, renderY, data.get(i).getWidth()/2*Constants.PIXELS_PER_METER, data.get(i).getHeight()/2*Constants.PIXELS_PER_METER, renderW, renderH, 1f, 1f, theta);
	    
	}
    }
    
    public void instantiate(World world)
	 {
	     this.body = world.createBody(this.getBdef());
	     for(FixtureDef fd : this.getFixtures())
		 this.body.createFixture(fd);
	 }
    
    public void update()
    {
	
    }
    /**
     * Returns the private variable, this.bdef.
     * @return this.bdef
     */
    public BodyDef getBdef() {
        return this.bdef;
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
     * Returns the private variable, this.fixtures.
     * @return this.fixtures
     */
    public ArrayList<FixtureDef> getFixtures() {
        return this.fixtures;
    }

    /**
     * Returns the private variable, this.data.
     * @return this.data
     */
    public ArrayList<FixtureData> getData() {
        return this.data;
    }

}
