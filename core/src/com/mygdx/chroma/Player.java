/**
 * 
 */
package com.mygdx.chroma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * @author Arun
 *
 */
public class Player extends Entity
{

    public Integer state;

    /**
     * 
     */
    public Player(float x, float y)
    {
	super();

	//Body work
	this.bdef.type=BodyType.DynamicBody;
	this.bdef.position.set(x,y);


	PolygonShape shape=new PolygonShape();
	
	//Fixture 1 work
	FixtureData data = new FixtureData(new Texture(Gdx.files.internal("dummy-red.jpg")), .75f, 1.5f);
	FixtureDef fixture=new FixtureDef();
	shape.setAsBox(data.getWidth()/2, data.getHeight()/2);
	fixture.shape=shape;
	fixture.density=0.5f;
	fixture.friction=1f;
	fixture.restitution=0f;
	fixture.isSensor=false;
	this.fixtures.add(fixture);
	this.data.add(data);
	
	//Fixture 2 work
	PolygonShape shape2=new PolygonShape();
	FixtureData wepData=new FixtureData(new Texture(Gdx.files.internal("dummy-blue.jpg")), 0.1f, 1f, this.data.get(0).getWidth()/2 , 0, 20);
	FixtureDef weapon = new FixtureDef();
	shape2.setAsBox(wepData.getWidth()/2, wepData.getHeight()/2, new Vector2(wepData.getxOffset(), wepData.getyOffset()), wepData.getAngle(Constants.RADIANS));
	weapon.shape=shape;
	this.fixtures.add(weapon);
	this.data.add(wepData);

	state=0b000;
    }

    public void update()
    {
	this.body.setFixedRotation(true);
	if(!((state&Constants.JUMPING)==Constants.JUMPING) && Gdx.input.isKeyPressed(Keys.UP))
	{
	    state|=Constants.JUMPING;
	    this.body.applyLinearImpulse(0f, 3f, this.body.getPosition().x, this.body.getPosition().y, true);
	}
	if(Gdx.input.isKeyPressed(Keys.LEFT))
	{
	    state|=Constants.MOVING_LEFT;
	    this.body.setLinearVelocity(-3f, this.body.getLinearVelocity().y);
	}
	if(Gdx.input.isKeyPressed(Keys.RIGHT))
	{
	    state|=Constants.MOVING_RIGHT;
	    this.body.setLinearVelocity(3f, this.body.getLinearVelocity().y);
	}
	if(this.body.getLinearVelocity().y==0)
	    state=state&~Constants.JUMPING;
	if(!(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.LEFT)))
	{
	    this.state=state&~Constants.MOVING_LEFT&~Constants.MOVING_RIGHT;
	    this.body.setLinearVelocity(0f, this.body.getLinearVelocity().y);
	}
	if(Gdx.input.isKeyPressed(Keys.Z))
	{
	    this.body.destroyFixture(this.body.getFixtureList().get(1));
	    this.getData().get(1).setAngle(this.getData().get(1).getAngle(Constants.DEGREES)-30);
	    PolygonShape temp=new PolygonShape();
	    temp.setAsBox(this.getData().get(1).getWidth()/2, this.getData().get(1).getHeight()/2, new Vector2(this.getData().get(1).getxOffset(), this.getData().get(1).getyOffset()), this.getData().get(1).getAngle(Constants.RADIANS));
	    this.fixtures.get(1).shape=temp;
	    this.body.createFixture(this.fixtures.get(1));
	}

    }

}
