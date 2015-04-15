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
	public static final int WIDTH = 1280;
	/** The height of the game screen in pixels. */
	public static final int HEIGHT=720;
	
	public static final float PIXELS_PER_METER=80f;
	
	public static final Vector2 GRAVITY = new Vector2(0, -10);
	public static final float TIME_STEP = 1/30f;
	
	public static final float VIEWPORT_H = HEIGHT/PIXELS_PER_METER; //9 meters
	
	public static final float VIEWPORT_W = WIDTH/PIXELS_PER_METER; //16 meters
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
	 
	 public static void instantiate(Entity entity, World world)
	 {
	     entity.body = world.createBody(entity.getBdef());
	     for(FixtureDef fd : entity.getFixtures())
		 entity.body.createFixture(fd);
	 }
}
