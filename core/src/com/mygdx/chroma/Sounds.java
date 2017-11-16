/**
 * 
 */
package com.mygdx.chroma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * @author Arun
 *
 */
public class Sounds
{
	public static final Music TITLE_BGM = Gdx.audio.newMusic(Gdx.files.internal("Duskofgod.mp3"));
	public static final Music FIGHT_BGM = Gdx.audio.newMusic(Gdx.files.internal("FightBattle.mp3"));
	public static final Music GAMEOVER_LOSE_BGM = Gdx.audio.newMusic(Gdx.files.internal("DefeatScreen.mp3"));
	public static final Music GAMEOVER_WIN_BGM = Gdx.audio.newMusic(Gdx.files.internal("WinScreen.mp3"));
	public static final Music MAP_BGM = Gdx.audio.newMusic(Gdx.files.internal("LazyAfternoon.mp3"));
	
	public static void stopAll()
	{
		TITLE_BGM.stop();
		FIGHT_BGM.stop();
		GAMEOVER_LOSE_BGM.stop();
		GAMEOVER_WIN_BGM.stop();
		MAP_BGM.stop();
		
	}
}
