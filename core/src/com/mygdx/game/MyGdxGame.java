package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends Game {
	
	SpriteBatch batch;
	ShapeRenderer render;
	BitmapFont font;
	int score;
	
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		render = new ShapeRenderer();
		font = new BitmapFont();
		
		setScreen(new mainMenu(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		render.dispose();
		font.dispose();
	}
	
}
