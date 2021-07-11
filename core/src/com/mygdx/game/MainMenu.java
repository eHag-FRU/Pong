package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;



public class MainMenu extends ScreenAdapter{

	//Makes a local variable for the games instance
	protected Pong Ponggame;
	
	//Local variables from the Pong class
	private SpriteBatch batch;
	private ShapeRenderer render;
	private BitmapFont font;
	
	//Local variables specifically for this menu ONLY!
	private Buttons test;
	
	public MainMenu(Pong game) {
		
			//Assigns the argument to the local variable
			this.Ponggame = game;
		
			//Assigns the arguments public variables to private, local ones
			this.batch = game.batch;
			this.font = game.font;
			this.render = game.render;
		
			//Creates a button for the menu
			test = new Buttons();
	}
	
	@Override
	public void show() {
		//TODO: Implement input handling
		Gdx.input.setInputProcessor(new MainMenuInput());
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLUE);
	
		batch.begin();
		font.draw(batch, "Title", 50, 50 );
		font.draw(batch, "Press space to start", 100, 100);
		batch.end();
	}
	
	private class MainMenuInput extends InputAdapter{
		
		public MainMenuInput() {
		
		}
		
		@Override
		public boolean keyDown (int keycode) {
				if (keycode == Keys.SPACE) {
					Ponggame.setScreen(new GameScreen(Ponggame));
				}
			return true;
		}
	}
}
	