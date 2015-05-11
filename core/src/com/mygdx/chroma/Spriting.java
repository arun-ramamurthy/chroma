/**
 * 
 */
package com.mygdx.chroma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/** @author Arun */
public class Spriting
{
	//TEXTURES
	public static final TextureRegion DUMMY_BLUE = new TextureRegion(new Texture(Gdx.files.internal("dummy-blue.jpg")));
	public static final TextureRegion DUMMY_RED = new TextureRegion(new Texture(Gdx.files.internal("dummy-red.jpg")));
	public static final TextureRegion PLAYER = new TextureRegion(new Texture(Gdx.files.internal("player-standing.png")));
	public static final TextureRegion SPEAR = new TextureRegion(new Texture(Gdx.files.internal("spear.png")));
	public static final TextureRegion SWORD = new TextureRegion(new Texture(Gdx.files.internal("sword.png")));
	public static final TextureRegion TEMP_BG = new TextureRegion(new Texture(Gdx.files.internal("temp-background.jpg")));
	public static final TextureRegion TERR_TOP = new TextureRegion(new Texture(Gdx.files.internal("grass-block-top.png")));
	public static final TextureRegion TERR_SIDE = new TextureRegion(new Texture(Gdx.files.internal("grass-block-side.png")));
	
	
	// ANIMATIONS
	/** The animation for player movement. */
	public static final Animation	ANIM_PLAYER_RUNNING	= generateAnimation("player-running.png", 1, 4, 0.15f);

	// TOOLS
	/** Generates an Animation using Libgdx methods.
	 * 
	 * @param spriteSheetName
	 *            the full path name of the sprite sheet to use
	 * @param rows
	 *            the number of rows in the sprite sheet
	 * @param cols
	 *            the number of columns in the sprite sheet
	 * @param animationSpeed
	 *            the number of seconds between frames
	 * @return the Animation */
	public static Animation generateAnimation(String spriteSheetName, int rows, int cols, float animationSpeed)
	{
		Texture tempTexture = new Texture(Gdx.files.internal(spriteSheetName));
		TextureRegion[][] tempArr2D = TextureRegion.split(
				tempTexture,
				tempTexture.getWidth()/cols,
				tempTexture.getHeight()/rows);
		TextureRegion[] tempArr1D = new TextureRegion[rows*cols];
		int index = 0;
		for(int r = 0; r<rows; r++)
			for(int c = 0; c<cols; c++)
				tempArr1D[index++] = tempArr2D[r][c];
		return new Animation(animationSpeed, tempArr1D);
	}
}
