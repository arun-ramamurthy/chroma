package com.mygdx.chroma.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.LogicManager;
import com.mygdx.chroma.Sounds;
import com.mygdx.chroma.Spriting;
import com.mygdx.chroma.entities.Beast;
import com.mygdx.chroma.entities.DummyPlatform;
import com.mygdx.chroma.entities.Enemy;
import com.mygdx.chroma.entities.Player;
import com.mygdx.chroma.entities.Skeleton;
import com.mygdx.chroma.entities.Slime;
import com.mygdx.chroma.entities.Terrain;

public class FightScreen extends Screen
{

	World				world;
	OrthographicCamera	camera;
	Box2DDebugRenderer	dbr;
	Player				player;
	Terrain[]			terrain;
	DummyPlatform		ground, wall1, wall2, ceiling;
	ArrayList<Enemy>	enemies;
	Texture				bg;
	LogicManager		lm;

	// TODO: Spear attack, sprite animations, terrain

	@Override
	public void create()
	{
		Sounds.FIGHT_BGM.play();
		Box2D.init();
		world = new World(Constants.GRAVITY, true);
		camera = new OrthographicCamera(Constants.PHYSICS_WIDTH, Constants.PHYSICS_HEIGHT);
		dbr = new Box2DDebugRenderer();

		player = new Player(-3, -1);
		player.instantiate(world);
		
		if(WorldState.numSlimes>Constants.MAX_SLIMES) WorldState.numSlimes=Constants.MAX_SLIMES;
		if(WorldState.numSlimes>Constants.MAX_SKELETONS) WorldState.numSlimes=Constants.MAX_SKELETONS;
		if(WorldState.numSlimes>Constants.MAX_BEASTS) WorldState.numSlimes=Constants.MAX_BEASTS;
		
		enemies = new ArrayList<Enemy>();
		for(int i = 1; i<WorldState.numSlimes; i++)
			//for(int i = 1; i<0; i++)
			enemies.add(new Slime((float)Math.random()*.6f*Constants.PHYSICS_WIDTH-Constants.PHYSICS_WIDTH/4f, (float).25f
																						*Constants.PHYSICS_HEIGHT));
		for(int i = 1; i<WorldState.numBeasts; i++)
			//for(int i = 1; i<2; i++)
			enemies.add(new Beast((float)Math.random()*.6f*Constants.PHYSICS_WIDTH-Constants.PHYSICS_WIDTH/4f, (float).25f
																						*Constants.PHYSICS_HEIGHT));
		for(int i = 1; i<WorldState.numSkeletons; i++)
			//for(int i = 1; i<0; i++)
			enemies.add(new Skeleton((float)Math.random()*.6f*Constants.PHYSICS_WIDTH-Constants.PHYSICS_WIDTH/4f, (float).25f
																						*Constants.PHYSICS_HEIGHT));

		for(Enemy enemy : enemies)
			enemy.instantiate(world);

		terrain = Terrain.generate(8, Constants.PHYSICS_HEIGHT/6, 0.2f, Spriting.TERR_TOP, Spriting.TERR_SIDE);
		
		//terrain = Terrain.generate(8, Constants.PHYSICS_HEIGHT/6, 0f, Spriting.TERR_TOP, Spriting.TERR_SIDE);
		for(Terrain stack : terrain)
			stack.instantiate(world);

		wall1 = new DummyPlatform(-Constants.PHYSICS_WIDTH/2, 0, 0.05f, Constants.PHYSICS_HEIGHT, Spriting.DUMMY_BLUE);
		wall1.instantiate(world);

		wall2 = new DummyPlatform(Constants.PHYSICS_WIDTH/2, 0, 0.05f, Constants.PHYSICS_HEIGHT, Spriting.DUMMY_BLUE);
		wall2.instantiate(world);

		ceiling = new DummyPlatform(0, Constants.PHYSICS_HEIGHT/2+1, Constants.PHYSICS_WIDTH, 0.05f, Spriting.DUMMY_BLUE);
		ceiling.instantiate(world);

		lm = new LogicManager(player, enemies, world);
	}

	@Override
	public void update()
	{
		if(Gdx.input.isKeyPressed(Keys.TAB))
		{
				enemies.add(new Skeleton((float)Math.random()*.6f*Constants.PHYSICS_WIDTH-Constants.PHYSICS_WIDTH/4f, (float).25f
						*Constants.PHYSICS_HEIGHT));
				enemies.get(enemies.size()-1).instantiate(world);
		}
		if(enemies.isEmpty() && Gdx.input.isKeyPressed(Keys.SPACE))
		{
			WorldState.steps++;
			WorldState.numSlimes = 2 + (float)(Math.random()*(2*WorldState.steps));
			WorldState.numBeasts = (float)(Math.random()*(0.5*WorldState.steps));
			WorldState.numSkeletons = (float)(Math.random()*(0.85*WorldState.steps));
			Sounds.FIGHT_BGM.stop();
			ScreenManager.setScreen(WorldState.map);
		}
		lm.update();
		world.step(Constants.TIME_STEP, 6, 2);
	}

	@Override
	public void render(SpriteBatch batch)
	{

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		batch.begin();
		batch.draw(Spriting.TEMP_BG.getTexture(), 0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		for(Enemy e : enemies)
			e.draw(batch);
		player.draw(batch);
		for(Terrain stack : terrain)
			stack.draw(batch);
		batch.end();
		// dbr.render(world, camera.combined);
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
