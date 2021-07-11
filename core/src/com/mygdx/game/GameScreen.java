package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GameScreen extends ScreenAdapter {
	private Pong game;
	
	private float ballX;
	private float ballY;
	private float ballRad;
	
	public GameScreen(Pong game) {
		this.game = game;
		
		ballX = 10;
		ballY = 30;
		ballRad = 60;
	}
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(new GameScreenInput());
	}
	
	@Override
	public void render(float delta) {
		ScreenUtils.clear(Color.BLACK);
		game.render.begin(ShapeRenderer.ShapeType.Filled);
		game.render.circle(ballX, ballY, ballRad);
		game.render.end();
	}
	
	public class GameScreenInput extends InputAdapter {
		
		public GameScreenInput() {
			
		}
		
		@Override
		public boolean keyDown(int keycode) {
				if (keycode == Keys.UP) {
					ballX += 10;
				}
				
				else if (keycode == Keys.DOWN) {
					ballX -= 10;
				}
				else if (keycode == Keys.ESCAPE) {
					game.setScreen(new MainMenu(game));
				}
			return true;
		}
	
	}
	
}