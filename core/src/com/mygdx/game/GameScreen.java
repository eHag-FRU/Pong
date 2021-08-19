package  com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen extends ScreenAdapter{
	
	MyGdxGame game;
	
	ShapeRenderer render;
	
	BitmapFont font;
	
	SpriteBatch batch;
	
	Sound ping;
	
	int score;
	
	String scoreString;
	
	boolean scoreUpToDate;
	
	boolean readyForSpace;
	
	int lives;
	
	int liveBoxPosition;
	
	int[] positionOffSets;
	
	float ballWidth;
	float ballHeigth;
	float ballX;
	float ballY;
	
	float ballXSpeed;
	float ballYSpeed;
	
	float orignalBallXSpeed;
	float orginalBallYSpeed;
	
	float paddleHeigth;
	float paddleWidth;
	float paddleX;
	float paddleY;
	
	float paddleSpeed;
	
	Rectangle paddleBox;
	Rectangle ballBox;
	
	Rectangle deadZone;
	
	static float ZERO; 
	
	
	public GameScreen(MyGdxGame game) {
		
		this.game = game;
		
		Gdx.input.setInputProcessor(new paddleControl());
		
		ping = Gdx.audio.newSound(Gdx.files.internal("paddleHit.wav"));
		
		render = game.render;
		
		font = game.font;
		
		batch = game.batch;
		
		score = 0;
		
		scoreUpToDate = false;
		
		readyForSpace = false;
		
		scoreString = "" + score;
		
		lives = 3;
		
		liveBoxPosition = 200;
		
		ZERO = 0;
		
		positionOffSets = new int[lives];

		if (lives == 1) {
			positionOffSets[0] = liveBoxPosition;
		}
		else if (lives == 2) {
			positionOffSets[0] = liveBoxPosition;
			positionOffSets[1] = liveBoxPosition + 15;
		}
		else {
			positionOffSets[0] = liveBoxPosition;
			positionOffSets[1] = positionOffSets[0] + 15;
			positionOffSets[2] = positionOffSets[0] + 30;
		}
		
		ballWidth = 15;
		ballHeigth = 15;
		ballX = Gdx.graphics.getWidth() / 2;
		ballY = Gdx.graphics.getHeight() / 2;
		ballXSpeed = 2;
		ballYSpeed = 4;
		
		orignalBallXSpeed = ballXSpeed;
		orginalBallYSpeed = ballYSpeed;
		
		paddleHeigth = 80;
		paddleWidth = 10;
		paddleX = 80;
		paddleY = Gdx.graphics.getHeight() / 2;
		paddleSpeed = 50;
		
		paddleBox = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeigth);
		
		ballBox = new Rectangle(ballX, ballY, ballWidth, ballHeigth);
		
		deadZone = new Rectangle (ZERO, ZERO, paddleX, Gdx.graphics.getHeight());

	}
	
	@Override
	public void render(float delta) {
		scoreString = "" + score;
		
		ballX += ballXSpeed;
		ballY += ballYSpeed;
		
		ballBox.setX(ballBox.getX() + ballXSpeed);
		ballBox.setY(ballBox.getY() + ballYSpeed);
		
		if (ballX < 0 || ballX + 15 > Gdx.graphics.getWidth()) {
			ballXSpeed *= -1;
			ping.play(1);
		}
		else if (ballY < 0 || ballY + 15 > Gdx.graphics.getHeight()) {
			ballYSpeed *= -1;
			ping.play(1);
		}
		
		if (ballBox.overlaps(paddleBox)) {
			ballXSpeed *= -1;
			score++;
			ping.play();
		}
		
		if (!ballBox.overlaps(deadZone)) {
			scoreUpToDate = false;
		}
		
		if (deadZone.overlaps(ballBox) && !scoreUpToDate) {
			score --;
			lives--;
			scoreUpToDate = true;
		}
		
		ScreenUtils.clear(0, 0, 0, 0);
		
		if (ballBox.getX() == 0) {
			ballXSpeed = 0;
			ballYSpeed = 0;
			
			if (lives == 0) {
				game.score = score;
				game.setScreen(new GameOverScreen(game));
			}
			
			batch.begin();
			font.draw(batch, "Press Space to Begin", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
			batch.end();

			readyForSpace = true;
		}
		
		
		render.begin(ShapeRenderer.ShapeType.Filled);
		render.rect((float) ballX, ballY, ballWidth, ballHeigth);
		render.rect(paddleX, paddleY, paddleWidth, paddleHeigth);
		
		
		for (int i = 0; i < lives; i++) {
			render.rect(positionOffSets[i], Gdx.graphics.getHeight() - 30, 10, 10);
		}
		
		render.end();
		
		liveBoxPosition = 200;
		
		batch.begin();
		font.draw(batch, scoreString, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() - 10);
		batch.end();
	}
	
	@Override
	public void dispose () {
		render.dispose();
		font.dispose();
		batch.dispose();
		ping.dispose();
	}

	private void setScore(int newScore) {
		score = newScore;
		scoreString = " " + score;
	}

	private void setLives(int newLives) {
		lives = newLives;
	}
	
	private class paddleControl extends InputAdapter {
		
		public paddleControl() {
			//Left blank
		}
		
		@Override
		public boolean keyDown(int keycode) {
			if (keycode == Keys.UP && paddleY + 95 < Gdx.graphics.getHeight()) {
				paddleY += paddleSpeed;
				paddleBox.setY(paddleBox.getY() + paddleSpeed);
			}
			else if (keycode == Keys.DOWN && paddleY
					> 0) {
				paddleY -= paddleSpeed;
				paddleBox.setY(paddleBox.getY() - paddleSpeed);
			}
			else if (keycode == Keys.SPACE && readyForSpace && lives > 0) {

				int numberOfLives = lives;
				int currentScore = score;

				GameScreen temp = new GameScreen(game);
				temp.setScore(currentScore);
				temp.setLives(numberOfLives);

				game.setScreen(temp);
			}
			
			return true;
		}
	}
	
	private static class ScreenChange extends ScreenAdapter {
		
		public ScreenChange(MyGdxGame game) {
			game.setScreen(new GameOverScreen(game));
		}
	}
}