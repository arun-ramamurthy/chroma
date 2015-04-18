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
 * The player of the game in the battle scenes.
 */
public class Player extends Entity
{

    /** A collection of bits to decide the state of the player; i.e. is he moving, jumping, attacking, etc. */
    public Integer state;

    /** Default constructor. Used just to check the class of entities. */
    public Player()
    {

    }
    
    /**
     * Creates a new player at a specified location.
     * @param x the x-coordinate of the player's initial placement, in meters from the origin.
     * @param y the y-coordinate of the player's initial placement, in meters from the origin.
     */
    public Player(float x, float y)
    {
	super();

	//Body work
	this.bDef.type=BodyType.DynamicBody;
	this.bDef.position.set(x,y);

	

	//main body fixture work
	PolygonShape shape=new PolygonShape();
	FixtureData data = new FixtureData(Constants.PLAYER_BODY, new Texture(Gdx.files.internal("dummy-red.jpg")), .75f, 1.5f);
	FixtureDef fixture=new FixtureDef();
	shape.setAsBox(data.getWidth()/2, data.getHeight()/2);
	fixture.shape=shape;
	fixture.density=0.5f;
	fixture.friction=1f;
	fixture.restitution=0f;
	fixture.isSensor=false;
	data.setFd(fixture);
	this.data.put(Constants.PLAYER_BODY, data);

	//weapon fixture work
	PolygonShape shape2=new PolygonShape();
	FixtureData wepData=new FixtureData(Constants.PLAYER_WEAPON, new Texture(Gdx.files.internal("dummy-blue.jpg")), 0.1f, 1.8f, 0 , 0, 20);
	FixtureDef weapon = new FixtureDef();
	shape2.setAsBox(wepData.getWidth()/2, wepData.getHeight()/2, new Vector2(wepData.getxOffset(), wepData.getyOffset()), wepData.getAngle(Constants.RADIANS));
	weapon.shape=shape2;
	weapon.isSensor=true;
	wepData.setFd(weapon);
	this.data.put(Constants.PLAYER_WEAPON, wepData);

	//sets the initial state to not doing anything
	state=0b000;
    }

    public void update()
    {
	this.body.setFixedRotation(true);
	if(!((state&Constants.JUMPING)==Constants.JUMPING) && Gdx.input.isKeyPressed(Keys.UP))
	{
	    state|=Constants.JUMPING;
	    state=state&~Constants.ATTACKING;
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
	    this.state|=Constants.ATTACKING;
	    this.body.destroyFixture(this.data.get(Constants.PLAYER_WEAPON).getFixture());
	    FixtureDef newWeapon = this.data.get(Constants.PLAYER_WEAPON).getFd();
	    this.data.get(Constants.PLAYER_WEAPON).setAngle(this.data.get(Constants.PLAYER_WEAPON).getAngle(Constants.DEGREES)-30);
	    //this.data.get(Constants.PLAYER_WEAPON).setxOffset((float)(this.data.get(Constants.PLAYER_WEAPON).getxOffset()+0.1));
	    PolygonShape temp=new PolygonShape();
	    temp.setAsBox(this.data.get(Constants.PLAYER_WEAPON).getWidth()/2, this.data.get(Constants.PLAYER_WEAPON).getHeight()/2, new Vector2(this.data.get(Constants.PLAYER_WEAPON).getxOffset(), this.data.get(Constants.PLAYER_WEAPON).getyOffset()), this.data.get(Constants.PLAYER_WEAPON).getAngle(Constants.RADIANS));
	    newWeapon.shape=temp;
	    this.data.get(Constants.PLAYER_WEAPON).setFd(newWeapon);
	    this.body.createFixture(this.data.get(Constants.PLAYER_WEAPON).getFd());
	}

	if(Gdx.input.isKeyPressed(Keys.X))
	{
	    this.state|=Constants.ATTACKING;
	    this.body.destroyFixture(this.data.get(Constants.PLAYER_WEAPON).getFixture());
	    FixtureDef newWeapon = this.data.get(Constants.PLAYER_WEAPON).getFd();
	    this.data.get(Constants.PLAYER_WEAPON).setAngle(this.data.get(Constants.PLAYER_WEAPON).getAngle(Constants.DEGREES)+30);
	    //this.data.get(Constants.PLAYER_WEAPON).setxOffset((float)(this.data.get(Constants.PLAYER_WEAPON).getxOffset()-0.1));
	    PolygonShape temp=new PolygonShape();
	    temp.setAsBox(this.data.get(Constants.PLAYER_WEAPON).getWidth()/2, this.data.get(Constants.PLAYER_WEAPON).getHeight()/2, new Vector2(this.data.get(Constants.PLAYER_WEAPON).getxOffset(), this.data.get(Constants.PLAYER_WEAPON).getyOffset()), this.data.get(Constants.PLAYER_WEAPON).getAngle(Constants.RADIANS));
	    newWeapon.shape=temp;
	    this.data.get(Constants.PLAYER_WEAPON).setFd(newWeapon);
	    this.body.createFixture(this.data.get(Constants.PLAYER_WEAPON).getFd());
	    
	}
	
	if(!(Gdx.input.isKeyPressed(Keys.Z) || Gdx.input.isKeyPressed(Keys.X)))
		this.state=state&~Constants.ATTACKING;
    }

}
