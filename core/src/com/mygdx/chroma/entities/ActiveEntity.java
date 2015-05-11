/**
 * 
 */
package com.mygdx.chroma.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.chroma.Constants;

/** @author Arun */
public abstract class ActiveEntity extends Entity
{

	/** A collection of bits to decide the state of the player; i.e. is he moving, jumping,
	 * attacking, etc. */
	public int	state;
	/** The direction the entity is facing. See Constants.LEFT and Constants.RIGHT */
	protected boolean	dir;

	@Override
	public void update()
	{
		super.update();
		updateMovement();
	}

	public void draw(SpriteBatch batch)
	{

		float renderX, renderY, renderW, renderH, theta;
		for(Integer tag : this.getData().keySet()) {

			renderX = (Constants.PHYSICS_WIDTH/2+body.getPosition().x-data.get(tag).getWidth()/2+data.get(tag).getxOffset())
						*Constants.PIXELS_PER_METER;
			renderY = (Constants.PHYSICS_HEIGHT/2+body.getPosition().y-data.get(tag).getHeight()/2+data.get(tag).getyOffset())
						*Constants.PIXELS_PER_METER;
			renderW = data.get(tag).getWidth()*Constants.PIXELS_PER_METER;
			renderH = data.get(tag).getHeight()*Constants.PIXELS_PER_METER;
			theta = (float)((body.getAngle()+data.get(tag).getAngle(Constants.RADIANS))*Constants.DEGREES_PER_RADIAN);
			batch.setColor(data.get(tag).getTint());

			if(this.isDir(Constants.LEFT))
				batch.draw(
						data.get(tag).getTexture(),
						renderX,
						renderY,
						data.get(tag).getWidth()/2*Constants.PIXELS_PER_METER,
						data.get(tag).getHeight()/2*Constants.PIXELS_PER_METER,
						renderW,
						renderH,
						-1f,
						1f,
						theta);

			else if(this.isDir(Constants.RIGHT))
				batch.draw(
						data.get(tag).getTexture(),
						renderX,
						renderY,
						data.get(tag).getWidth()/2*Constants.PIXELS_PER_METER,
						data.get(tag).getHeight()/2*Constants.PIXELS_PER_METER,
						renderW,
						renderH,
						1f,
						1f,
						theta);

			batch.setColor(Color.WHITE);
		}
	}

	/** How the entity must move during each interval. Left non-overridden for non-moving entities. */
	public void updateMovement()
	{

	}

	/** Destroys and reconstructs the fixtures of the entity during each interval. */
	public void updateFixtures()
	{
		FixtureDef fd;
		PolygonShape shape = new PolygonShape();
		for(Integer tag : this.getData().keySet()) {
			shape.setAsBox(
					data.get(tag).getWidth()/2,
					data.get(tag).getHeight()/2,
					new Vector2(data.get(tag).getxOffset(), data.get(tag).getyOffset()),
					data.get(tag).getAngle(Constants.RADIANS));
			fd = this.getData().get(tag).getFd();
			fd.shape = shape;
			data.get(tag).setFd(fd);
			this.body.destroyFixture(this.data.get(tag).getFixture());
			this.body.createFixture(this.data.get(tag).getFd());
		}
	}

	/** Returns the private variable, this.dir.
	 * 
	 * @return this.dir */
	public boolean isDir(boolean dir)
	{
		return this.dir==dir;
	}

	/** Sets the private variable, this.dir, to the passed parameter, dir.
	 * 
	 * @param dir
	 *            the new value of this.dir */
	public void setDir(boolean dir)
	{
		this.dir = dir;
	}
}
