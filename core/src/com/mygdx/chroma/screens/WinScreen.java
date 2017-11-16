/** Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015 */
package com.mygdx.chroma.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.chroma.Sounds;
import com.mygdx.chroma.Spriting;

/** Represents the screen that appears when HP reaches 0. */
public class WinScreen extends Screen
{

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#create()
	 */
	@Override
	public void create()
	{
		Sounds.GAMEOVER_WIN_BGM.play();
	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#update()
	 */
	@Override
	public void update()
	{
		if(Gdx.input.isKeyPressed(Keys.ENTER)) {
			Sounds.GAMEOVER_WIN_BGM.stop();
			ScreenManager.setScreen(new TitleScreen());
		}
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) System.exit(0);

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
	 */
	@Override
	public void render(SpriteBatch sb)
	{
		sb.begin();
		sb.draw(Spriting.WIN_BG, 0, 0);
		sb.end();

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#dispose()
	 */
	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#pause()
	 */
	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see com.mygdx.chroma.demo.screen.Screen#resume()
	 */
	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

}