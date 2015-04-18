/**
 * 
 */
package com.mygdx.chroma;

import java.util.Hashtable;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Arun
 *
 */
public class EntityManager implements ContactListener
{
    
    
    /**
     * 
     */
    public EntityManager(World world)
    {
	world.setContactListener(this);
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.physics.box2d.ContactListener#beginContact(com.badlogic.gdx.physics.box2d.Contact)
     */
    @Override
    public void beginContact(Contact contact)
    {

	Fixture f1=contact.getFixtureA();
	Fixture f2=contact.getFixtureB();
	Body b1 = f1.getBody();
	Body b2 = f2.getBody();
	Entity e1 = (Entity) b1.getUserData();
	Entity e2 = (Entity) b2.getUserData();
	Player p;
	if(!e1.getClass().isInstance(new Player()))
	    return;
	else
	    p = (Player) e1;
	System.out.println(Constants.ATTACKING==(p.state&Constants.ATTACKING));
	if(Constants.ATTACKING==(p.state&Constants.ATTACKING) && f1==p.getFixture(Constants.PLAYER_WEAPON) && f2==e2.getFixture(Constants.DUMMY_MAIN))
	    b2.applyLinearImpulse(-1f, 6f, b2.getPosition().x, b2.getPosition().y, true);
    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.physics.box2d.ContactListener#endContact(com.badlogic.gdx.physics.box2d.Contact)
     */
    @Override
    public void endContact(Contact contact)
    {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.physics.box2d.ContactListener#preSolve(com.badlogic.gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.Manifold)
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {
	// TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.badlogic.gdx.physics.box2d.ContactListener#postSolve(com.badlogic.gdx.physics.box2d.Contact, com.badlogic.gdx.physics.box2d.ContactImpulse)
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {
	// TODO Auto-generated method stub

    }
    
    public void update()
    {
	
    }
}
