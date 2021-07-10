package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;



public class MainMenu extends ScreenAdapter{
	
	private Pong Ponggame;
	
	public MainMenu(Pong game) {
		this.Ponggame = game;
		    //Test
	}
	
	
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLUE);
	
		Ponggame.batch.begin();
		Ponggame.font.draw(Ponggame.batch, "Title", 50, 50 );
		Ponggame.batch.end();
		}
}
	