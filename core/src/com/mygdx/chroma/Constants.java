package com.mygdx.chroma;

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

/**
 * Stores all constant information for the game. *
 */
public class Constants 
{

	public static final int GRID_SIZE=3;
	/** The width of the game screen in pixels. */
	public static final int SCREEN_WIDTH = 1280;
	/** The height of the game screen in pixels. */
	public static final int SCREEN_HEIGHT=720;
	
	public static final float PIXELS_PER_METER=80f;
	
	public static final Vector2 GRAVITY = new Vector2(0, -10);
	public static final float TIME_STEP = 1/30f;
	
	public static final float PHYSICS_HEIGHT = SCREEN_HEIGHT/PIXELS_PER_METER; //9 meters
	
	public static final float PHYSICS_WIDTH = SCREEN_WIDTH/PIXELS_PER_METER; //16 meters
	
	public static final Integer MOVING_LEFT=0b0001;
	public static final Integer MOVING_RIGHT=0b0010;
	public static final Integer JUMPING=0b0100;
	public static final Integer ATTACKING=0b1000;
	
	public static final boolean DEGREES=false;
	public static final boolean RADIANS=true;
	public static final float RADIANS_PER_DEGREES=(float)(Math.PI/180);
	public static final float DEGREES_PER_RADIAN=(float)(180/Math.PI);
	
	public static final int PLAYER_BODY=0x0002;
	public static final int PLAYER_WEAPON=0x0001;
	public static final int DUMMY_MAIN=0x0000;
	public static final int DUMMY_PLATFORM=0x1000;
	
	//TOOLS
	 public static Animation generateAnimation(String spriteSheetName, int rows, int cols, float animationSpeed)
	    {
		Texture tempTexture = new Texture(Gdx.files.internal(spriteSheetName));
		TextureRegion[][] tempArr2D = TextureRegion.split(tempTexture, tempTexture.getWidth()/cols, tempTexture.getHeight()/rows);
		TextureRegion[] tempArr1D = new TextureRegion[rows*cols];
		int index=0;
		for(int r=0; r<rows; r++)
		    for(int c=0; c<cols; c++)
			tempArr1D[index++] = tempArr2D[r][c];
		return new Animation(animationSpeed, tempArr1D);
	    }
	 
	 
}
