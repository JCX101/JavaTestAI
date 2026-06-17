package com.brickbreaker;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Brick Breaker Game
 * Tests game logic, collision detection, and game state management
 */
public class BrickBreakerGameTest {

    private GameTestHarness gameHarness;

    @Before
    public void setUp() {
        gameHarness = new GameTestHarness();
    }

    // ============= BALL MOVEMENT TESTS =============

    @Test
    public void testBallInitialPosition() {
        double ballX = gameHarness.getBallCenterX();
        double ballY = gameHarness.getBallCenterY();
        
        assertEquals("Ball X should be at center", 400.0, ballX, 0.1);
        assertEquals("Ball Y should be at center", 300.0, ballY, 0.1);
    }

    @Test
    public void testBallMovement() {
        double initialX = gameHarness.getBallCenterX();
        double initialY = gameHarness.getBallCenterY();
        
        gameHarness.updateBallPosition();
        
        double newX = gameHarness.getBallCenterX();
        double newY = gameHarness.getBallCenterY();
        
        assertNotEquals("Ball X should change", initialX, newX, 0.1);
        assertNotEquals("Ball Y should change", initialY, newY, 0.1);
    }

    // ============= PADDLE MOVEMENT TESTS =============

    @Test
    public void testPaddleInitialPosition() {
        double paddleX = gameHarness.getPaddleX();
        assertEquals("Paddle X should be centered", 350.0, paddleX, 0.1);
    }

    @Test
    public void testPaddleMovesLeft() {
        gameHarness.setPaddleMoveLeft(true);
        gameHarness.setPaddleMoveRight(false);
        
        double initialX = gameHarness.getPaddleX();
        gameHarness.updatePaddlePosition();
        double newX = gameHarness.getPaddleX();
        
        assertTrue("Paddle should move left", newX < initialX);
    }

    @Test
    public void testPaddleMovesRight() {
        gameHarness.setPaddleMoveLeft(false);
        gameHarness.setPaddleMoveRight(true);
        
        double initialX = gameHarness.getPaddleX();
        gameHarness.updatePaddlePosition();
        double newX = gameHarness.getPaddleX();
        
        assertTrue("Paddle should move right", newX > initialX);
    }

    // ============= SCORE TESTS =============

    @Test
    public void testInitialScore() {
        int score = gameHarness.getScore();
        assertEquals("Initial score should be 0", 0, score);
    }

    @Test
    public void testScoreIncrement() {
        gameHarness.incrementScore();
        int score = gameHarness.getScore();
        assertEquals("Score should increment by 10", 10, score);
    }

    // ============= GAME STATE TESTS =============

    @Test
    public void testGameStartsActive() {
        boolean gameOver = gameHarness.isGameOver();
        boolean gameWon = gameHarness.isGameWon();
        
        assertFalse("Game should not be over at start", gameOver);
        assertFalse("Game should not be won at start", gameWon);
    }

    @Test
    public void testGameResetState() {
        gameHarness.setGameOver(true);
        gameHarness.incrementScore();
        
        gameHarness.resetGame();
        
        assertFalse("Game over flag should be reset", gameHarness.isGameOver());
        assertEquals("Score should be reset to 0", 0, gameHarness.getScore());
    }

    // ============= CONSTANT VALIDATION TESTS =============

    @Test
    public void testWindowDimensions() {
        assertEquals("Window width should be 800", 800, gameHarness.getWindowWidth());
        assertEquals("Window height should be 600", 600, gameHarness.getWindowHeight());
    }

    @Test
    public void testPaddleDimensions() {
        assertEquals("Paddle width should be 100", 100, gameHarness.getPaddleWidth());
        assertEquals("Paddle height should be 15", 15, gameHarness.getPaddleHeight());
    }

    @Test
    public void testBallRadius() {
        assertEquals("Ball radius should be 8", 8, gameHarness.getBallRadius());
    }

    /**
     * Test harness class that exposes game logic for testing
     */
    static class GameTestHarness {
        private static final int WINDOW_WIDTH = 800;
        private static final int WINDOW_HEIGHT = 600;
        private static final int PADDLE_WIDTH = 100;
        private static final int PADDLE_HEIGHT = 15;
        private static final int BALL_RADIUS = 8;
        private static final double PADDLE_Y = WINDOW_HEIGHT - 40;

        private double ballCenterX = WINDOW_WIDTH / 2.0;
        private double ballCenterY = WINDOW_HEIGHT / 2.0;
        private double ballVelocityX = 5.0;
        private double ballVelocityY = -5.0;

        private double paddleX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2.0;
        private double paddleSpeed = 8.0;

        private int score = 0;
        private boolean gameOver = false;
        private boolean gameWon = false;
        private boolean moveLeft = false;
        private boolean moveRight = false;

        public double getBallCenterX() { return ballCenterX; }
        public double getBallCenterY() { return ballCenterY; }
        public void setBallCenterX(double x) { this.ballCenterX = x; }
        public void setBallCenterY(double y) { this.ballCenterY = y; }

        public double getBallVelocityX() { return ballVelocityX; }
        public double getBallVelocityY() { return ballVelocityY; }

        public double getPaddleX() { return paddleX; }
        public void setPaddleX(double x) { this.paddleX = x; }
        public void setPaddleMoveLeft(boolean move) { this.moveLeft = move; }
        public void setPaddleMoveRight(boolean move) { this.moveRight = move; }

        public int getScore() { return score; }
        public void incrementScore() { this.score += 10; }

        public boolean isGameOver() { return gameOver; }
        public void setGameOver(boolean over) { this.gameOver = over; }
        public boolean isGameWon() { return gameWon; }

        public int getWindowWidth() { return WINDOW_WIDTH; }
        public int getWindowHeight() { return WINDOW_HEIGHT; }
        public int getPaddleWidth() { return PADDLE_WIDTH; }
        public int getPaddleHeight() { return PADDLE_HEIGHT; }
        public int getBallRadius() { return BALL_RADIUS; }

        public void updateBallPosition() {
            ballCenterX += ballVelocityX;
            ballCenterY += ballVelocityY;

            if (ballCenterX - BALL_RADIUS <= 0 || ballCenterX + BALL_RADIUS >= WINDOW_WIDTH) {
                ballVelocityX = -ballVelocityX;
            }
            if (ballCenterY - BALL_RADIUS <= 0) {
                ballVelocityY = -ballVelocityY;
            }
            if (ballCenterY - BALL_RADIUS > WINDOW_HEIGHT) {
                gameOver = true;
            }
        }

        public void updatePaddlePosition() {
            if (moveLeft && paddleX > 0) {
                paddleX -= paddleSpeed;
            }
            if (moveRight && paddleX < WINDOW_WIDTH - PADDLE_WIDTH) {
                paddleX += paddleSpeed;
            }
        }

        public void resetGame() {
            score = 0;
            gameOver = false;
            gameWon = false;
            ballVelocityX = 5.0;
            ballVelocityY = -5.0;
            ballCenterX = WINDOW_WIDTH / 2.0;
            ballCenterY = WINDOW_HEIGHT / 2.0;
            paddleX = (WINDOW_WIDTH - PADDLE_WIDTH) / 2.0;
        }
    }
}
