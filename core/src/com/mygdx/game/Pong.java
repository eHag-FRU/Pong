package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Pong extends Game{
	public SpriteBatch batch;
	public BitmapFont font;
	public ShapeRenderer render;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		render = new ShapeRenderer();
		setScreen(new MainMenu(this));
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
		render.dispose();

	}
}
