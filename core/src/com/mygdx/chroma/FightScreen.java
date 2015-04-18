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
    DummyPlatform	ground, wall1, wall2, ceiling;

    @Override
    public void create() {
	Box2D.init();
	world=new World(Constants.GRAVITY, true);
	camera=new OrthographicCamera(Constants.PHYSICS_WIDTH, Constants.PHYSICS_HEIGHT);
	dbr=new Box2DDebugRenderer();
	EntityManager em=new EntityManager(world);
	player = new Player(3, 0);
	player.instantiate(world);
	
	ball=new Dummy(4, 0, 1, 1.5f, 1f, true, new Texture(Gdx.files.internal("dummy-red.jpg")));
	ball.instantiate(world);
	
	ground=new DummyPlatform(0, -Constants.PHYSICS_HEIGHT/2, 15, 0.2f, new Texture(Gdx.files.internal("dummy-blue.jpg")));
	ground.instantiate(world);
	
	wall1=new DummyPlatform(-Constants.PHYSICS_WIDTH/2, 0, 1f, 15, new Texture(Gdx.files.internal("dummy-blue.jpg")));
	wall1.instantiate(world);
	
	wall2=new DummyPlatform(Constants.PHYSICS_WIDTH/2, 0, 1f, 15, new Texture(Gdx.files.internal("dummy-blue.jpg")));
	wall2.instantiate(world);
	
	ceiling=new DummyPlatform(0, Constants.PHYSICS_HEIGHT/2, 15, 0.2f, new Texture(Gdx.files.internal("dummy-blue.jpg")));
	ceiling.instantiate(world);
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
	wall1.draw(batch);
	wall2.draw(batch);
	ceiling.draw(batch);
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
