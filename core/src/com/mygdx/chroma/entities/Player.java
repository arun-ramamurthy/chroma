/**
 * 
 */
package com.mygdx.chroma.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.FixtureData;
import com.mygdx.chroma.Spriting;
import com.mygdx.chroma.screens.WorldState;

/** The player of the game in the battle scenes. */
public class Player extends ActiveEntity
{

	public int	wepType;
	public int	attackLength;

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

		this.setBaseHp(1000);
		this.setHP(WorldState.playerHP);

		// Body work
		this.bDef.type = BodyType.DynamicBody;
		this.bDef.position.set(x, y);

		// main body fixture work
		PolygonShape shape = new PolygonShape();
		FixtureData data = new FixtureData(Constants.PLAYER_MAIN, Spriting.PLAYER, Constants.ID_PLAYER.x, Constants.ID_PLAYER.y);
		FixtureDef fixture = new FixtureDef();
		shape.setAsBox(data.getWidth()/2, data.getHeight()/2);
		fixture.shape = shape;
		fixture.density = 0.5f;
		fixture.friction = 1f;
		fixture.restitution = 0f;
		fixture.isSensor = false;
		fixture.filter.categoryBits = Constants.PLAYER;
		fixture.filter.maskBits = Constants.TERRAIN;
		data.setFd(fixture);
		this.data.put(Constants.PLAYER_MAIN, data);

		// main hitbox fixture work
		PolygonShape hbShape = new PolygonShape();
		FixtureData hbData = new FixtureData(Constants.PLAYER_HITBOX, Spriting.BLANK, Constants.ID_PLAYER.x, Constants.ID_PLAYER.y);
		FixtureDef hbFixture = new FixtureDef();
		hbShape.setAsBox(hbData.getWidth()/2, hbData.getHeight()/2);
		hbFixture.shape = hbShape;
		hbFixture.isSensor = true;
		hbData.setFd(hbFixture);
		this.data.put(Constants.PLAYER_HITBOX, hbData);

		// weapon fixture work
		PolygonShape wepShape = new PolygonShape();
		FixtureData wepData = new FixtureData();
		switch(wepType)
		{
			case Constants.SPEAR:
				wepData = new FixtureData(Constants.PLAYER_WEAPON, Spriting.SPEAR, 1.0f, 0.25f, Constants.IP_SPEAR.x, Constants.IP_SPEAR.y, 0);
				wepShape.setAsBox(
						wepData.getWidth()/2,
						wepData.getHeight()/2,
						new Vector2(wepData.getxOffset(), wepData.getyOffset()),
						wepData.getAngle(Constants.RADIANS));
				break;
			case Constants.CHAKRAM:
				wepData = new FixtureData(Constants.PLAYER_WEAPON, Spriting.CHAKRAM, 0.25f, 0.25f, Constants.IP_CHAKRAM.x, Constants.IP_CHAKRAM.y, 0);
				wepShape.setAsBox(
						wepData.getWidth()/2,
						wepData.getHeight()/2,
						new Vector2(wepData.getxOffset(), wepData.getyOffset()),
						wepData.getAngle(Constants.RADIANS));
				break;
		}
		FixtureDef weapon = new FixtureDef();
		weapon.shape = wepShape;
		weapon.isSensor = true;
		wepData.setFd(weapon);
		this.data.put(Constants.PLAYER_WEAPON, wepData);

		// sets the initial state to not doing anything
		state = 0b00000;
	}

	@Override
	public void update()
	{

		super.update();
		if(this.isDir(Constants.LEFT))
			this.getData().get(Constants.PLAYER_WEAPON).setxOffset(
					-Math.abs(this.getData().get(Constants.PLAYER_WEAPON).getxOffset()));
		else
			this.getData().get(Constants.PLAYER_WEAPON).setxOffset(
					Math.abs(this.getData().get(Constants.PLAYER_WEAPON).getxOffset()));

		if(Constants.CHARGING==(this.state&Constants.CHARGING)) this.charge();
		if(Constants.ATTACKING==(this.state&Constants.ATTACKING)) this.attack();

	}

	@Override
	public void updateTextures()
	{
		if(state==0) atime = 0;
		this.data.get(Constants.PLAYER_MAIN).setTexture(Spriting.PLAYER);
		if((state&Constants.MOVING)==Constants.MOVING) {
			this.data.get(Constants.PLAYER_MAIN).setTexture(
					Spriting.ANIM_PLAYER_RUNNING.getKeyFrame(atime += Gdx.graphics.getDeltaTime(), true));
		}
	}

	@Override
	public void drawHP(SpriteBatch batch)
	{
		batch.end();

		Spriting.brush.begin(ShapeType.Filled);
		Spriting.brush.setColor(Color.RED);
		Spriting.brush.rect(
				.05f*Constants.SCREEN_WIDTH,
				0.9f*Constants.SCREEN_HEIGHT,
				(Constants.SCREEN_WIDTH*0.9f)*((float)this.getHP()/this.getBaseHp()),
				Constants.SCREEN_HEIGHT*0.05f);
		Spriting.brush.end();

		Spriting.brush.begin(ShapeType.Line);
		Spriting.brush.setColor(Color.WHITE);
		Spriting.brush.rect(
				.05f*Constants.SCREEN_WIDTH,
				0.9f*Constants.SCREEN_HEIGHT,
				(Constants.SCREEN_WIDTH*0.9f),
				Constants.SCREEN_HEIGHT*0.05f);
		Spriting.brush.end();

		batch.begin();
		Spriting.pen.setColor(Color.BLACK);
		Spriting.pen.draw(
				batch,
				"HP: "+this.getHP()+" / "+this.getBaseHp(),
				.05f*Constants.SCREEN_WIDTH,
				0.93f*Constants.SCREEN_HEIGHT);

	}

	@Override
	public void updateMovement()
	{
		super.updateMovement();
		if(((state&Constants.JUMPING)!=Constants.JUMPING)&&Gdx.input.isKeyPressed(Keys.UP)) {
			state |= Constants.JUMPING;
			this.body.applyLinearImpulse(
					0f,
					Constants.JUMP_FORCE,
					this.body.getPosition().x,
					this.body.getPosition().y,
					true);
		}

		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			this.setDir(Constants.LEFT);
			state |= Constants.MOVING;
			this.body.setLinearVelocity(-3f, this.body.getLinearVelocity().y);
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			this.setDir(Constants.RIGHT);
			state |= Constants.MOVING;
			this.body.setLinearVelocity(3f, this.body.getLinearVelocity().y);
		}

		if(this.body.getLinearVelocity().y==0) // Turns JUMPING to false if y velocity is 0.
			state = state&~Constants.JUMPING;

		if(!(Gdx.input.isKeyPressed(Keys.RIGHT)||Gdx.input.isKeyPressed(Keys.LEFT))) // Stops horizontal motion if keys
																						// aren't pressed anymore
		{
			this.state = state&~Constants.MOVING;
			this.body.setLinearVelocity(0f, this.body.getLinearVelocity().y);
		}

	}

	/** Charges an attack for as long as the key is pressed. Flashes different colors for different amounts of charge. */
	public void charge()
	{
		if(Constants.getElapsed(Constants.CHARGING)%250<120) {
			if(Constants.getElapsed(Constants.CHARGING)>Constants.LONG_TIME)
				this.data.get(Constants.PLAYER_MAIN).setTint(Color.BLUE);
			else if(Constants.getElapsed(Constants.CHARGING)>Constants.MED_TIME)
				this.data.get(Constants.PLAYER_MAIN).setTint(Color.YELLOW);
			else
				this.data.get(Constants.PLAYER_MAIN).setTint(Color.WHITE);
		}
		else
			this.data.get(Constants.PLAYER_MAIN).setTint(Color.WHITE);
	}

	/** Attacks depending on weapon and amount of charge. */
	public void attack()
	{
		float t = Constants.getElapsed(Constants.ATTACKING)/1000f; // t is the number of seconds the attack has been
																	// running for
		float dir = 1;

		if(this.isDir(Constants.LEFT)) dir = -1;

		switch(wepType)
		{
			case Constants.SPEAR:
				switch(attackLength)
				{
					case Constants.WEAK:
						this.data.get(Constants.PLAYER_WEAPON).setxOffset(
								(float)(Constants.IP_SPEAR.x+(1-Math.abs(1-40*t)))*dir);
						if(t>0.05) state = state&~Constants.ATTACKING;
						break;
					case Constants.MEDIUM:
						if(t<.05)
							this.body.applyLinearImpulse(
									dir*.1f,
									Constants.JUMP_FORCE/3f,
									this.body.getPosition().x,
									this.body.getPosition().y,
									true);
						this.data.get(Constants.PLAYER_WEAPON).setxOffset(Constants.IP_SPEAR.x+(1-Math.abs(1-8*t))*dir);
						this.data.get(Constants.PLAYER_WEAPON).setyOffset(
								(float)(Constants.IP_SPEAR.y+((Constants.u(t, 0, 0.1)*Math.pow(15*t, 10)+Constants.u(
										t,
										0.1,
										0.25)*(1-4*t)))));
						this.data.get(Constants.PLAYER_WEAPON).setAngle(0+dir*(90-Math.abs(90-720*t)));
						if(t>.25) state = state&~Constants.ATTACKING;
						break;
					case Constants.STRONG:
						this.body.applyLinearImpulse(
								3*dir,
								0,
								this.body.getPosition().x,
								this.body.getPosition().y,
								true);
						if(t>0.25) state = state&~Constants.ATTACKING;
						break;
				}
				// Resets the weapon after each attack
				if(((state&Constants.ATTACKING)!=Constants.ATTACKING)) {
					this.data.get(Constants.PLAYER_WEAPON).setxOffset(Constants.IP_SPEAR.x);
					this.data.get(Constants.PLAYER_WEAPON).setyOffset(Constants.IP_SPEAR.y);
					this.data.get(Constants.PLAYER_WEAPON).setAngle(0);
				}
				break;
			case Constants.CHAKRAM:
				switch(attackLength)
				{
					case Constants.WEAK:
						this.data.get(Constants.PLAYER_WEAPON).setxOffset((float)(Constants.IP_CHAKRAM.x+(5*t/(3f/4f+Math.pow(t, 3))*2)*dir));
						this.data.get(Constants.PLAYER_WEAPON).setxOffset(
								(float)(4*Math.pow(t, 2)/(1+Math.pow(t, 3))));
						if(t>0.05) state = state&~Constants.ATTACKING;
						break;
					case Constants.MEDIUM:
						if(t<.05)
							this.body.applyLinearImpulse(
									dir*.1f,
									Constants.JUMP_FORCE/3f,
									this.body.getPosition().x,
									this.body.getPosition().y,
									true);
						this.data.get(Constants.PLAYER_WEAPON).setxOffset(Constants.IP_SPEAR.x+(1-Math.abs(1-8*t))*dir);
						this.data.get(Constants.PLAYER_WEAPON).setyOffset(
								(float)(Constants.IP_SPEAR.y+((Constants.u(t, 0, 0.1)*Math.pow(15*t, 10)+Constants.u(
										t,
										0.1,
										0.25)*(1-4*t)))));
						this.data.get(Constants.PLAYER_WEAPON).setAngle(0+dir*(90-Math.abs(90-720*t)));
						if(t>.25) state = state&~Constants.ATTACKING;
						break;
					case Constants.STRONG:
						this.body.applyLinearImpulse(
								3*dir,
								0,
								this.body.getPosition().x,
								this.body.getPosition().y,
								true);
						if(t>0.25) state = state&~Constants.ATTACKING;
						break;
				}
				// Resets the weapon after each attack
				if(((state&Constants.ATTACKING)!=Constants.ATTACKING)) {
					this.data.get(Constants.PLAYER_WEAPON).setxOffset(Constants.IP_CHAKRAM.x);
					this.data.get(Constants.PLAYER_WEAPON).setyOffset(Constants.IP_CHAKRAM.y);
					this.data.get(Constants.PLAYER_WEAPON).setAngle(0);
				}
				break;
		}
		updateFixtures();
	}

	/** Sends a linear impulse to the affected enemy depending on the attack.
	 * 
	 * @param enemy
	 *            the body of the enemy attacked */
	public void knockback(Body enemy)
	{
		float dir = 1;
		if(this.isDir(Constants.LEFT)) dir = -1;
		switch(wepType)
		{
			case Constants.SPEAR:
				switch(attackLength)
				{
					case Constants.WEAK:
						enemy.applyLinearImpulse(dir*0.1f, 0, enemy.getPosition().x, enemy.getPosition().y, true);
						break;
					case Constants.MEDIUM:
						enemy.applyLinearImpulse(dir*0.2f, 0.5f, enemy.getPosition().x, enemy.getPosition().y, true);
						break;
					case Constants.STRONG:
						enemy.applyLinearImpulse(dir*1f, 0, enemy.getPosition().x, enemy.getPosition().y, true);
						break;
				}
				break;

			case Constants.CHAKRAM:
				switch(attackLength)
				{
					case Constants.WEAK:

						break;
					case Constants.MEDIUM:

						break;
					case Constants.STRONG:

						break;
				}
				break;
		}

	}

	/** Damages and stuns the enemy depending on the attack.
	 * 
	 * @param enemy
	 *            the enemy attacked */
	public void damage(Enemy enemy)
	{
		switch(wepType)
		{
			case Constants.SPEAR:
				switch(attackLength)
				{
					case Constants.WEAK:
						enemy.loseHealth(2);
						break;
					case Constants.MEDIUM:
						enemy.loseHealth(5);
						break;
					case Constants.STRONG:
						enemy.loseHealth(5);
						break;
				}
				break;

			case Constants.CHAKRAM:
				switch(attackLength)
				{
					case Constants.WEAK:

						break;
					case Constants.MEDIUM:

						break;
					case Constants.STRONG:

						break;
				}
				break;
		}

		if(attackLength!=Constants.WEAK) {
			enemy.state |= Constants.STUNNED;
			Constants.setTimestamp(Constants.STUNNED);
		}

	}

}
