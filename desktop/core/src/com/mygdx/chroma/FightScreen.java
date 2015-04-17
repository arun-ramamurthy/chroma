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

public class FightScreen extends Screen
{

    World	      world;
    OrthographicCamera camera;
    Box2DDebugRenderer dbr;
    Player player;
    Dummy	      ball;
    DummyPlatform	ground;

    @Override
    public void create() {
	Box2D.init();
	world=new World(Constants.GRAVITY, true);
	camera=new OrthographicCamera(Constants.VIEWPORT_W, Constants.VIEWPORT_H);
	dbr=new Box2DDebugRenderer();
	
	player = new Player(3, 4);
	player.instantiate(world);
	
	ball=new Dummy(4, 5, 1, 1.5f, 5f, true, new Texture(Gdx.files.internal("dummy-red.jpg")));
	ball.instantiate(world);
	
	ground=new DummyPlatform(0, 0, 15, 0.2f, new Texture(Gdx.files.internal("dummy-blue.jpg")));
	ground.instantiate(world);
    }

    @Override
    public void update() {
	ball.update();
	player.update();
	world.step(Constants.TIME_STEP, 6, 2);
    }

    @Override
    public void render(SpriteBatch batch) {

	batch.begin();
	player.draw(batch);
	ball.draw(batch);
	ground.draw(batch);
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
