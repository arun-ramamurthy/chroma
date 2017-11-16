/**
 * Arun Ramamurthy, Eric Cheung
 * Chroma (Demo)
 * 2015
 */
package com.mygdx.chroma.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.chroma.Constants;
import com.mygdx.chroma.Sounds;

/**
 * Represents the screen that appears when HP reaches 0.
 */
public class TitleScreen extends Screen
{

    Texture image;
    /* (non-Javadoc)
     * @see com.mygdx.chroma.demo.screen.Screen#create()
     */
    @Override
    public void create()
    {
	image=new Texture(Gdx.files.internal("title.png"));
	Sounds.TITLE_BGM.play();
	
    }

    /* (non-Javadoc)
     * @see com.mygdx.chroma.demo.screen.Screen#update()
     */
    @Override
    public void update()
    {
	if(Gdx.input.isKeyPressed(Keys.ENTER))
	{
	    WorldState.restart();
		ScreenManager.setScreen(WorldState.map);
	    Sounds.TITLE_BGM.stop();
	}
	
    }

    /* (non-Javadoc)
     * @see com.mygdx.chroma.demo.screen.Screen#render(com.badlogic.gdx.graphics.g2d.SpriteBatch)
     */
    @Override
    public void render(SpriteBatch sb)
    {
	sb.begin();
	sb.draw(image, 0, 0);
	sb.end();
	
    }

    /* (non-Javadoc)
     * @see com.mygdx.chroma.demo.screen.Screen#resize(int, int)
     */
    @Override
    public void resize(int width, int height)
    {
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see com.mygdx.chroma.demo.screen.Screen#dispose()
     */
    @Override
    public void dispose()
    {
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see com.mygdx.chroma.demo.screen.Screen#pause()
     */
    @Override
    public void pause()
    {
	// TODO Auto-generated method stub
	
    }

    /* (non-Javadoc)
     * @see com.mygdx.chroma.demo.screen.Screen#resume()
     */
    @Override
    public void resume()
    {
	// TODO Auto-generated method stub
	
    }
	
}