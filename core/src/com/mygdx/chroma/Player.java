/**
 * 
 */
package com.mygdx.chroma;

import java.util.Timer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.TimeUtils;

/** The player of the game in the battle scenes. */
public class Player extends Entity
{

	/** A collection of bits to decide the state of the player; i.e. is he moving, jumping,
	 * attacking, etc. */
	public int	state;
	public int	wepType;
	public int	attackLength;

	/** Default constructor. Used just to check the class of entities. */
	public Player()
	{

	}

	/** Creates a new player at a specified location.
	 * 
	 * @param x
	 *            the x-coordinate of the player's initial placement, in meters from the origin.
	 * @param y
	 *            the y-coordinate of the player's initial placement, in meters from the origin. */
	public Player(float x, float y)
	{
		super();
		wepType = Constants.SPEAR; // TEMP
		// Body work
		this.bDef.type = BodyType.DynamicBody;
		this.bDef.position.set(x, y);

		// main body fixture work
		PolygonShape shape = new PolygonShape();
		FixtureData data = new FixtureData(Constants.PLAYER_BODY, new Texture(Gdx.files.internal("dummy-red.jpg")), .75f, 1.5f);
		FixtureDef fixture = new FixtureDef();
		shape.setAsBox(data.getWidth()/2, data.getHeight()/2);
		fixture.shape = shape;
		fixture.density = 0.5f;
		fixture.friction = 1f;
		fixture.restitution = 0f;
		fixture.isSensor = false;
		data.setFd(fixture);
		this.data.put(Constants.PLAYER_BODY, data);

		// weapon fixture work
		PolygonShape shape2 = new PolygonShape();
		FixtureData wepData = new FixtureData(Constants.PLAYER_WEAPON, new Texture(Gdx.files.internal("dummy-blue.jpg")), 0.1f, 1.8f, 0, 0, 20);
		FixtureDef weapon = new FixtureDef();
		shape2.setAsBox(wepData.getWidth()/2, wepData.getHeight()/2, new Vector2(wepData.getxOffset(), wepData.getyOffset()), wepData.getAngle(Constants.RADIANS));
		weapon.shape = shape2;
		weapon.isSensor = true;
		wepData.setFd(weapon);
		this.data.put(Constants.PLAYER_WEAPON, wepData);

		// sets the initial state to not doing anything
		state = 0b00000;
	}

	public void update()
	{
		this.body.setFixedRotation(true);
		this.move();
		if(Constants.CHARGING==(this.state&Constants.CHARGING)) this.charge();
		if(Constants.ATTACKING==(this.state&Constants.ATTACKING)) this.attack();

	}

	public void move()
	{
		if(!((state&Constants.JUMPING)==Constants.JUMPING)&&Gdx.input.isKeyPressed(Keys.UP)) {
			state |= Constants.JUMPING;
			state = state&~Constants.ATTACKING;
			this.body.applyLinearImpulse(0f, 3f, this.body.getPosition().x, this.body.getPosition().y, true);
		}

		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			state |= Constants.MOVING_LEFT;
			this.body.setLinearVelocity(-3f, this.body.getLinearVelocity().y);
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			state |= Constants.MOVING_RIGHT;
			this.body.setLinearVelocity(3f, this.body.getLinearVelocity().y);
		}

		if(this.body.getLinearVelocity().y==0) // Turns JUMPING to false if y velocity is 0.
			state = state&~Constants.JUMPING;

		if(!(Gdx.input.isKeyPressed(Keys.RIGHT)||Gdx.input.isKeyPressed(Keys.LEFT))) // Stops horizontal motion if keys
																						// aren't pressed anymore
		{
			this.state = state&~Constants.MOVING_LEFT&~Constants.MOVING_RIGHT;
			this.body.setLinearVelocity(0f, this.body.getLinearVelocity().y);
		}

	}

	public void charge()
	{
		if(Constants.getElapsed()%500<250) {
			if(Constants.getElapsed()>Constants.LONG_TIME)
				this.data.get(Constants.PLAYER_BODY).setTint(Color.PURPLE);
			else if(Constants.getElapsed()>Constants.MED_TIME)
				this.data.get(Constants.PLAYER_BODY).setTint(Color.BLUE);
			else
				this.data.get(Constants.PLAYER_BODY).setTint(Color.WHITE);

		}
		else
			this.data.get(Constants.PLAYER_BODY).setTint(Color.WHITE);

	}

	public void attack()
	{
		long t = Constants.getElapsed()/100l-1;
		System.out.println(t);
		this.body.destroyFixture(this.data.get(Constants.PLAYER_WEAPON).getFixture());
		FixtureDef newWeapon = this.data.get(Constants.PLAYER_WEAPON).getFd();
		switch(wepType)
		{
			case Constants.SPEAR:
				switch(attackLength)
				{
					case Constants.SHORT:
						this.data.get(Constants.PLAYER_WEAPON).setxOffset((t-1)*(t+1));
						this.data.get(Constants.PLAYER_WEAPON).setyOffset((t-1)*(t+1));
						if(t>1) state = state&~Constants.ATTACKING;
						break;
					case Constants.MEDIUM:

						break;
					case Constants.LONG:

						break;

				}
				break;
			case Constants.BOOMERANG:

				break;
			default:
				break;
		}
		PolygonShape temp = new PolygonShape();
		temp.setAsBox(this.data.get(Constants.PLAYER_WEAPON).getWidth()/2, this.data.get(Constants.PLAYER_WEAPON).getHeight()/2, new Vector2(this.data.get(Constants.PLAYER_WEAPON).getxOffset(), this.data.get(Constants.PLAYER_WEAPON).getyOffset()), this.data.get(Constants.PLAYER_WEAPON).getAngle(Constants.RADIANS));
		newWeapon.shape = temp;
		this.data.get(Constants.PLAYER_WEAPON).setFd(newWeapon);
		this.body.createFixture(this.data.get(Constants.PLAYER_WEAPON).getFd());
	}

	/** @param body */
	public void knockback(Body enemy)
	{
		// TODO Auto-generated method stub

	}

}
