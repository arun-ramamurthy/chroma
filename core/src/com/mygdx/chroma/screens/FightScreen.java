package com.mygdx.chroma.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
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
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.LogicManager;
import com.mygdx.chroma.Spriting;
import com.mygdx.chroma.entities.DummyEnemy;
import com.mygdx.chroma.entities.DummyEntity;
import com.mygdx.chroma.entities.DummyPlatform;
import com.mygdx.chroma.entities.Enemy;
import com.mygdx.chroma.entities.Player;
import com.mygdx.chroma.entities.Terrain;

public class FightScreen extends Screen
{

	World				world;
	OrthographicCamera	camera;
	Box2DDebugRenderer	dbr;
	Player				player;
	DummyEntity			ball;
	Terrain[]			terrain;
	DummyPlatform		ground, wall1, wall2, ceiling;
	ArrayList<Enemy>	enemies;
	Texture				bg;
	LogicManager		lm;

	// TODO: Spear attack, sprite animations, terrain

	@Override
	public void create()
	{
		Box2D.init();
		world = new World(Constants.GRAVITY, true);
		camera = new OrthographicCamera(Constants.PHYSICS_WIDTH, Constants.PHYSICS_HEIGHT);
		dbr = new Box2DDebugRenderer();

		player = new Player(3, 0);
		player.instantiate(world);

		enemies = new ArrayList<Enemy>();
		for(int i = 0; i<1; i++)
			enemies.add(new DummyEnemy((float)Math.random()*Constants.PHYSICS_WIDTH-Constants.PHYSICS_WIDTH/2, (float)Math.random()
																												*Constants.PHYSICS_HEIGHT
																												-Constants.PHYSICS_HEIGHT
																												/2, .5f, .5f));
		// enemies.add(new DummyEnemy(0, 0, 3, 6));

		for(Enemy enemy : enemies)
			enemy.instantiate(world);

		terrain = Terrain.generate(10, Constants.PHYSICS_HEIGHT/4, 2, Spriting.TERR_TOP, Spriting.TERR_SIDE);
		for(Terrain stack : terrain)
			stack.instantiate(world);

		wall1 = new DummyPlatform(-Constants.PHYSICS_WIDTH/2, 0, 1f, 15, Spriting.DUMMY_BLUE);
		wall1.instantiate(world);

		wall2 = new DummyPlatform(Constants.PHYSICS_WIDTH/2, 0, 1f, 15, Spriting.DUMMY_BLUE);
		wall2.instantiate(world);

		ceiling = new DummyPlatform(0, Constants.PHYSICS_HEIGHT/2, 15, 0.2f, Spriting.DUMMY_BLUE);
		ceiling.instantiate(world);

		lm = new LogicManager(player, enemies, world);
	}

	@Override
	public void update()
	{
		lm.update();
		world.step(Constants.TIME_STEP, 6, 2);
	}

	@Override
	public void render(SpriteBatch batch)
	{

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		batch.begin();
		for(Enemy e : enemies)
			e.draw(batch);
		player.draw(batch);
		for(Terrain stack : terrain)
			stack.draw(batch);
		wall1.draw(batch);
		wall2.draw(batch);
		ceiling.draw(batch);
		batch.end();

		 dbr.render(world, camera.combined);
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

}
