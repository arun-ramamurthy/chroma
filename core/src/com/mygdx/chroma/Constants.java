package com.mygdx.chroma;

import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.chroma.screens.FightScreen;
import com.mygdx.chroma.screens.MapScreen;
import com.mygdx.chroma.screens.Screen;

/** Stores all constant information for the game. * */
public class Constants
{

	// DIMENSIONING
	public static final int					GRID_SIZE			= 3;
	/** The conversion factor from meters to pixels. */
	public static final float				PIXELS_PER_METER	= 80f;
	/** The width of the visible game screen in pixels. */
	public static final int					SCREEN_WIDTH		= 1280;
	/** The height of the visible game screen in pixels. */
	public static final int					SCREEN_HEIGHT		= 720;
	/** The width of the visible game screen in meters. */
	public static final float				PHYSICS_WIDTH		= SCREEN_WIDTH/PIXELS_PER_METER;						// 16
	/** The height of the visible game screen in meters. */
	public static final float				PHYSICS_HEIGHT		= SCREEN_HEIGHT/PIXELS_PER_METER;						// 9
	/** The gravity vector bearing down upon all entities. */
	public static final Vector2				GRAVITY				= new Vector2(0, -10);
	/** The screen refresh rate. */
	public static final float				TIME_STEP			= 1/30f;

	public static final boolean				WIN					= true;
	public static final boolean				LOSE				= false;

	public static final int					MAX_SLIMES			= 10;
	public static final int					MAX_SKELETONS		= 5;
	public static final int					MAX_BEASTS			= 3;

	// DIRECTON AND ANGLING
	/** Designates the direction as left. */
	public static final boolean				LEFT				= true;
	/** Designates the direction as right. */
	public static final boolean				RIGHT				= false;
	/** Designates the units as degrees. */
	public static final boolean				DEGREES				= false;
	/** Designates the units as radians. */
	public static final boolean				RADIANS				= true;

	/** Convertion factor from degrees to radians. */
	public static final float				RADIANS_PER_DEGREES	= (float)(Math.PI/180);
	/** Convertion factor from radians to degrees. */
	public static final float				DEGREES_PER_RADIAN	= (float)(180/Math.PI);

	// TIMINGS
	/** The length of time to charge to unleash a 'weak' attack. */
	public static final long				SHORT_TIME			= 0;
	/** The length of time to charge to unleash a 'medium' attack. */
	public static final long				MED_TIME			= 500;
	/** The length of time to charge to unleash a 'strong' attack. */
	public static final long				LONG_TIME			= 1500;
	/** The length of time non-player entities are stunned for after being hit. */
	public static final long				STUN_TIME			= 1000;
	/** The length of time the player is stunned for after being hit. */
	public static final float				PLAYER_STUN_TIME	= 100;

	/** A hash table of timers, for use in a variety of actions. Each timer can be set and accessed via an integer key. */
	public static Hashtable<Integer, Long>	timers				= new Hashtable<Integer, Long>();

	// ATTACK TYPES
	/** Designates a weak attack. */
	public static final int					WEAK				= 0x1;
	/** Designates a medium attack. */
	public static final int					MEDIUM				= 0x2;
	/** Designates a strong attack. */
	public static final int					STRONG				= 0x3;

	// COLLISION MASKS
	/** Designates an entity as of the terrain type. */
	public static final int					TERRAIN				= 0b00001;
	/** Designates an entity as of the player type. */
	public static final int					PLAYER				= 0b00010;
	/** Designates an entity as of the enemy type. */
	public static final int					ENEMY				= 0b00100;
	/** Designates an entity as of the object type. */
	public static final int					OBJECT				= 0b01000;

	// PLAYER STATES
	/** Designates that the player is currently stunned. */
	public static final int					STUNNED				= 0b00001;
	/** Designates that the player is currently walking. */
	public static final int					MOVING				= 0b00010;
	/** Designates that the player is currently jumping. */
	public static final int					JUMPING				= 0b00100;
	/** Designates that the player is currently charging an attack. */
	public static final int					CHARGING			= 0b01000;
	/** Designates that the player is currently unleashing an attack. */
	public static final int					ATTACKING			= 0b10000;

	// FIXTURE TAGS

	public static final int					MAIN				= 0;
	/** Designates a fixture as the player's main hitbox. */
	public static final int					PLAYER_MAIN			= 0x2;
	/** Designates a fixture as the player's weapon. */
	public static final int					PLAYER_WEAPON		= 0x1;

	public static final int					PLAYER_HITBOX		= 0x3;

	// WEAPON TYPES
	/** Designates a weapon as the spear. */
	public static final int					SPEAR				= 0x2;
	/** Designates a weapon as the chakram. */
	public static final int					CHAKRAM			= 0x3;
	/** Designates a weapon as the sword. */
	public static final int					SWORD				= 0x4;

	// INITIAL VALUES
	// D = DIMENSION
	// P = POSITION
	/** The initial dimensions of the player. */
	public static final Vector2				ID_PLAYER			= new Vector2(.6f, 1.1f);
	/** The initial position of the spear relative to the player's main hitbox. */
	public static final Vector2				IP_SPEAR			= new Vector2(ID_PLAYER.x*(1.5f/4f), 0
																										-ID_PLAYER.y
																										*(0.4f/3f));
	/** The initial position of the spear relative to the player's main hitbox. */
	public static final Vector2				IP_CHAKRAM			= new Vector2(ID_PLAYER.x*(1.5f/4f), 0
																										-ID_PLAYER.y
																										*(0.4f/3f));

	public static final float				JUMP_FORCE			= 2f;

	/** The Heavyside function.
	 * 
	 * @param t
	 *            the value to test
	 * @param a
	 *            the lower bound
	 * @param b
	 *            the upper bound
	 * @return 1 if t is within the bounds, 0 if t is outside the bounds */
	public static int u(float t, double a, double b)
	{
		if(t<a||t>b)
			return 0;
		else
			return 1;
	}

	/** Sets a new timestamp for a tag to the current time if it has not been set before, or resets it to the current
	 * time if it has.
	 * 
	 * @param tag
	 *            the tag to refer to this timestamp with */
	public static void setTimestamp(int tag)
	{
		timers.put(tag, TimeUtils.millis());
	}

	/** Returns the number of milliseconds since the timestamp has been set.
	 * 
	 * @param tag
	 *            the tag to refer to this timestamp with
	 * @return the number of milliseconds since the timestamp has been set. */
	public static long getElapsed(int tag)
	{
		return TimeUtils.timeSinceMillis(timers.get(tag));
	}

}
