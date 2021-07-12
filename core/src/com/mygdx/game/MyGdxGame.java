package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	ShapeRenderer render;
	
	float ballWidth;
	float ballHeigth;
	float ballX;
	float ballY;
	
	float ballXSpeed;
	float ballYSpeed;
	
	float paddleHeigth;
	float paddleWidth;
	float paddleX;
	float paddleY;
	
	float paddleSpeed;
	
	Rectangle paddleBox;
	Rectangle ballBox;
	
	@Override
	public void create () {
		
		Gdx.input.setInputProcessor(new paddleControl());
		
		render = new ShapeRenderer();
		
		ballWidth = 15;
		ballHeigth = 15;
		ballX = Gdx.graphics.getWidth() / 2;
		ballY = Gdx.graphics.getHeight() / 2;
		ballXSpeed = 2;
		ballYSpeed = 4;
		
		paddleHeigth = 80;
		paddleWidth = 10;
		paddleX = 80;
		paddleY = Gdx.graphics.getHeight() / 2;
		paddleSpeed = 100;
		
		paddleBox = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeigth);
		
		ballBox = new Rectangle(ballX, ballY, ballWidth, ballHeigth);
	}

	@Override
	public void render () {
		ballX += ballXSpeed;
		ballY += ballYSpeed;
		
		ballBox.setX(ballBox.getX() + ballXSpeed);
		ballBox.setY(ballBox.getY() + ballYSpeed);
		
		if (ballX < 0 || ballX + 15 > Gdx.graphics.getWidth()) {
			ballXSpeed *= -1;
		}
		else if (ballY < 0 || ballY + 15 > Gdx.graphics.getHeight()) {
			ballYSpeed *= -1;
		}
		
		if (ballBox.overlaps(paddleBox)) {
			ballXSpeed *= -1;
		}
		
		ScreenUtils.clear(0, 0, 0, 0);
		
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.rect((float) ballX, ballY, ballWidth, ballHeigth);
		render.rect(paddleX, paddleY, paddleWidth, paddleHeigth);
		render.end();
	}
	
	@Override
	public void dispose () {
		render.dispose();
	}
	
	private class paddleControl extends InputAdapter {
		
		public paddleControl() {
			//Left blank
		}
		
		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Keys.UP) {
				paddleY += paddleSpeed;
				paddleBox.setY(paddleBox.getY() + paddleSpeed);
			}
			else if (keycode == Keys.DOWN) {
				paddleY -= paddleSpeed;
				paddleBox.setY(paddleBox.getY() - paddleSpeed);
			}
			return true;
		}
	}
}
