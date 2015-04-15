package com.mygdx.chroma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class FightScreen extends Screen {

	World world;
	OrthographicCamera camera;
	Box2DDebugRenderer dbr;
	
	Dummy ball;
	@Override
	public void create() {
		Box2D.init();
		world=new World(Constants.GRAVITY, true);
		camera=new OrthographicCamera(Constants.VIEWPORT_W, Constants.VIEWPORT_H);
		dbr=new Box2DDebugRenderer();
		
		BodyDef groundDef=new BodyDef();
		groundDef.type=BodyType.StaticBody;
		groundDef.position.set(new Vector2(0, -camera.viewportHeight/2+1f));
		
		PolygonShape groundShape=new PolygonShape();
		groundShape.setAsBox(camera.viewportWidth/2, 0.5f);
		
		Body groundBody=world.createBody(groundDef);
		groundBody.createFixture(groundShape, 0f);
		
		
		ball = new Dummy(4, 5, 1, 1, new Texture(Gdx.files.internal("badlogic.jpg")));
		Constants.instantiate(ball, world);
	}

	@Override
	public void update() {
		
		if(Gdx.input.isKeyPressed(Keys.UP))
			ball.body.applyLinearImpulse(0f, 0.25f, ball.body.getPosition().x, ball.body.getPosition().y, true);
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			ball.body.applyForce(-0.2f, 0f, ball.body.getPosition().x, ball.body.getPosition().y+1, true);
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			ball.body.applyForce(0.2f, 0f, ball.body.getPosition().x, ball.body.getPosition().y+1, true);
		world.step(Constants.TIME_STEP, 6, 2);
	}

	@Override
	public void render(SpriteBatch batch) {
		
		batch.begin();
		ball.draw(batch);
		batch.end(); 
		
		
		
		
		dbr.render(world, camera.combined);
	}


	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
