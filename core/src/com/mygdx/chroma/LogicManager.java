/**
 * 
 */
package com.mygdx.chroma;

import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.chroma.entities.Enemy;
import com.mygdx.chroma.entities.Entity;
import com.mygdx.chroma.entities.Player;

/** Conducts all the game logic for the fight screens. Contains a ContactListener and InputProcessor. */
public class LogicManager implements ContactListener, InputProcessor
{

	Player				player;
	ArrayList<Enemy>	enemies;
	Enemy				arbE;

	/** Constructs itself in the given world, and sets the world's ContactListener and InputProcessor
	 * accordingly. */
	public LogicManager(Player player, ArrayList<Enemy> enemies, World world)
	{
		world.setContactListener(this);
		Gdx.input.setInputProcessor(this);
		this.player = player;
		this.enemies = enemies;
	}

	public void update()
	{
		player.update();
		for(Enemy e : enemies)
			e.update(player.getBody().getPosition().x, player.getBody().getPosition().y);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#beginContact(com.badlogic.gdx.physics.box2d
	 * .Contact)
	 */
	@Override
	public void beginContact(Contact contact)
	{

		Fixture f1 = contact.getFixtureA();
		Fixture f2 = contact.getFixtureB();
		Body b1 = f1.getBody();
		Body b2 = f2.getBody();
		Entity e1 = (Entity)b1.getUserData();
		Entity e2 = (Entity)b2.getUserData();
		if(e1.getClass().isInstance(player)&&enemies.contains(e2)) {
			arbE = (Enemy)e2;
			if(Constants.ATTACKING==(player.state&Constants.ATTACKING)&&f1==player.getFixture(Constants.PLAYER_WEAPON)) {
				player.knockback(arbE.getBody());
				player.damage(arbE);
			}
			if(Constants.ATTACKING==(arbE.state&Constants.ATTACKING)&&f1==player.getFixture(Constants.PLAYER_MAIN)) {
				arbE.knockback(player.getBody());
				arbE.damage(player);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#endContact(com.badlogic.gdx.physics.box2d.
	 * Contact)
	 */
	@Override
	public void endContact(Contact contact)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#preSolve(com.badlogic.gdx.physics.box2d.Contact
	 * , com.badlogic.gdx.physics.box2d.Manifold)
	 */
	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * com.badlogic.gdx.physics.box2d.ContactListener#postSolve(com.badlogic.gdx.physics.box2d.Contact
	 * , com.badlogic.gdx.physics.box2d.ContactImpulse)
	 */
	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */
	@Override
	public boolean keyDown(int keycode)
	{
		if(!(Constants.ATTACKING==(Constants.ATTACKING&player.state))&&keycode==Keys.Z) {
			player.state |= Constants.CHARGING;
			Constants.setTimestamp(Constants.CHARGING);
			return true;
		}
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyUp(int)
	 */
	@Override
	public boolean keyUp(int keycode)
	{
		if(Constants.CHARGING==(Constants.CHARGING&player.state)&&keycode==Keys.Z) {
			player.state = player.state&~Constants.CHARGING;
			player.getData().get(Constants.PLAYER_MAIN).setTint(Color.WHITE);
			int len;
			if(Constants.getElapsed(Constants.CHARGING)>Constants.LONG_TIME)
				len = Constants.STRONG;
			else if(Constants.getElapsed(Constants.CHARGING)>Constants.MED_TIME)
				len = Constants.MEDIUM;
			else
				len = Constants.WEAK;
			Constants.setTimestamp(Constants.ATTACKING);
			player.attackLength = len;
			player.state |= Constants.ATTACKING;
			return true;
		}
		else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
	 */
	@Override
	public boolean keyTyped(char character)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
	@Override
	public boolean scrolled(int amount)
	{
		// TODO Auto-generated method stub
		return false;
	}
}
