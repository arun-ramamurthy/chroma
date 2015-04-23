package com.mygdx.chroma;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

/**
 * Represents all data accosciated with a given Fixture. 
 * Accessed with a tag from Constants, it contains the Fixture and its FixtureDefinition, the Texture associated with that Fixture, and additional information as well. 
 */
public class FixtureData
{
    /** The tag used to access this FixtureData. Found in Constants. */
    private Integer tag;
    /** The Fixture that this FixtureData represents. Used purely for existence checking; DOES NOT UPDATE AFTER INSTANTIATION!*/
    private Fixture fixture;
    /** The FixtureDef associated with the Fixture this FixtureData represents.*/
    private FixtureDef fd;
    /** The texture applied over this fixture.*/
    private TextureRegion texture;
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
    /** The angle of rotation from the body of the fixture, in degrees.*/
    private float angle;
    
    /** Default constructor. */
    public FixtureData()
    {
    	
    }
    
    /** 
     * Simple constructor.
     * @param tag the tag used this access this FixtureData
     * @param texture the texture meant to be drawn over the associated fixture
     * @param width the width of the fixture, in meters
     * @param height the height of the fixture, in meters
     */
    public FixtureData(Integer tag, TextureRegion texture, float width, float height)
    {
	this.tag=tag;
	this.fixture=null;
	this.fd=null;
	
	this.texture=texture;
	this.tint=new Color(Color.WHITE);
	this.xOffset=0;
	this.yOffset=0;
	this.width=width;
	this.height=height;
	this.angle=0;
    }

    /**
     * Constructor with more placement options.
     * @param tag the tag used this access this FixtureData
     * @param texture the texture meant to be drawn over the associated fixture
     * @param width the width of the fixture, in meters
     * @param height the height of the fixture, in meters
     * @param xOffset the offset from the body in the x direction, in meters
     * @param yOffset the offset from the body in the y direction, in meters
     * @param angle the angle rotated from the origin of this Fixture, in degrees
     */
    public FixtureData(Integer tag, TextureRegion texture, float width, float height, float xOffset, float yOffset, float angle)
    {
	this.tag=tag;
	this.fixture=null;
	this.fd=null;
	this.texture=texture;
	this.tint=new Color(Color.WHITE);
	this.xOffset=xOffset;
	this.yOffset=yOffset;
	this.angle=angle;
	this.width=width;
	this.height=height;
    }

    /**
	 * Returns the private variable, this.fixture.
	 * @return this.fixture
	 */
	public Fixture getFixture()
	{
	    return this.fixture;
	}

	/**
	 * Sets the private variable, this.fixture, to the passed parameter, fixture.
	 * @param fixture the new value of this.fixture
	 */
	public void setFixture(Fixture fixture)
	{
	    this.fixture=fixture;
	}

	/**
	 * Returns the private variable, this.texture.
	 * @return this.texture
	 */
	public TextureRegion getTexture() {
	    return this.texture;
	}

	
	/**
	 * Sets the private variable, this.texture, to the passed parameter, texture.
	 * @param texture the new value of this.texture
	 */
	public void setTexture(TextureRegion texture)
	{
		this.texture = texture;
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

    /**
     * Returns the angle of rotation about the Fixture's origin, in the specified unit
     * @param units the units of the angle measurement (Constants.RADIANS or Constants.DEGREES)
     * @return the angle of rotation in the specified unit
     */
    public float getAngle(boolean units)
    {
        if(units==Constants.DEGREES)
            return this.angle;
        else
            return this.angle*Constants.RADIANS_PER_DEGREES;
    }

    /**
     * Sets the private variable, this.angle, to the passed parameter, angle.
     * @param angle the new value of this.angle in degrees
     */
    public void setAngle(float angle)
    {
        this.angle=angle;
    }

    /**
     * Returns the private variable, this.fd.
     * @return this.fd
     */
    public FixtureDef getFd()
    {
        return this.fd;
    }
    
    /**
     * Sets the private variable, this.fd, to the passed parameter, fd.
     * @param fd the new value of this.fd
     */
    public void setFd(FixtureDef fd)
    {
        this.fd=fd;
    }


	/**
     * Returns the private variable, this.tag.
     * @return this.tag
     */
    public Integer getTag()
    {
        return this.tag;
    }

   
    
    
}
