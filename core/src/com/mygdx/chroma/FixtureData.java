package com.mygdx.chroma;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class FixtureData
{
    /** The texture applied over this fixture.*/
    private Texture texture;
    /** The tint applied to the texture. */
    private Color tint;
    /** The x-coordinate offset of the fixture from the center of the body, in meters.*/
    private float xOffset;
    /** The y-coordinate offset of the fixture from the center of the body, in meters.*/
    private float yOffset;
    /** The width of the fixture, in meters.*/
    private float width;
    /** The height of the fixture, in meters.*/
    private float height;
    
    public FixtureData(Texture texture, float width, float height)
    {
	this.texture=texture;
	this.tint=new Color(1,1,1,1);
	this.xOffset=0;
	this.yOffset=0;
	this.width=width;
	this.height=height;
    }

    /**
     * Returns the private variable, this.tint.
     * @return this.tint
     */
    public Color getTint() {
        return this.tint;
    }

    /**
     * Sets the private variable, this.tint, to the passed parameter, tint.
     * @param tint the new value of this.tint
     */
    public void setTint(Color tint) {
        this.tint=tint;
    }

    /**
     * Returns the private variable, this.xOffset.
     * @return this.xOffset
     */
    public float getxOffset() {
        return this.xOffset;
    }

    /**
     * Sets the private variable, this.xOffset, to the passed parameter, xOffset.
     * @param xOffset the new value of this.xOffset
     */
    public void setxOffset(float xOffset) {
        this.xOffset=xOffset;
    }

    /**
     * Returns the private variable, this.yOffset.
     * @return this.yOffset
     */
    public float getyOffset() {
        return this.yOffset;
    }

    /**
     * Sets the private variable, this.yOffset, to the passed parameter, yOffset.
     * @param yOffset the new value of this.yOffset
     */
    public void setyOffset(float yOffset) {
        this.yOffset=yOffset;
    }

    /**
     * Returns the private variable, this.texture.
     * @return this.texture
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * Returns the private variable, this.width.
     * @return this.width
     */
    public float getWidth() {
        return this.width;
    }

    /**
     * Returns the private variable, this.height.
     * @return this.height
     */
    public float getHeight() {
        return this.height;
    }

   
    
    
}
