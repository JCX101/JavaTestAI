package com.brickbreaker;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

/**
 * Brick Breaker Game - A classic arcade game implementation using JavaFX
 * Players control a paddle to bounce a ball and break bricks.
 */
public class BrickBreakerGame extends Application {

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 15;
    private static final int BALL_RADIUS = 8;
    private static final int BRICK_WIDTH = 75;
    private static final int BRICK_HEIGHT = 20;
    private static final double BALL_SPEED = 5.0;

    private Pane gamePane;
    private Circle ball;
    private Rectangle paddle;
    private Text scoreText;
    private Text gameOverText;
    private Rectangle[][] bricks;
    private int score = 0;
    private boolean gameOver = false;
    private boolean gameWon = false;

    // Ball velocity
    private double ballVelocityX = BALL_SPEED;
    private double ballVelocityY = -BALL_SPEED;

    // Paddle control
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private double paddleSpeed = 8.0;

    @Override
    public void start(Stage primaryStage) {
        gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: #1a1a2e;");

        // Initialize game objects
        initializeBall();
        initializePaddle();
        initializeBricks();
        initializeUI();

        // Setup key listeners
        setupKeyListeners();

        Scene scene = new Scene(gamePane, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brick Breaker Game");
        primaryStage.show();

        // Start game loop
        startGameLoop();
    }

    /**
     * Initialize the ball at the center of the game window
     */
    private void initializeBall() {
        ball = new Circle(WINDOW_WIDTH / 2.0, WINDOW_HEIGHT / 2.0, BALL_RADIUS);
        ball.setFill(Color.web("#00ff00"));
        gamePane.getChildren().add(ball);
    }

    /**
     * Initialize the paddle at the bottom of the game window
     */
    private void initializePaddle() {
        paddle = new Rectangle(
                (WINDOW_WIDTH - PADDLE_WIDTH) / 2.0,
                WINDOW_HEIGHT - 40,
                PADDLE_WIDTH,
                PADDLE_HEIGHT
        );
        paddle.setFill(Color.web("#00d4ff"));
        gamePane.getChildren().add(paddle);

        // Mouse control for paddle
        gamePane.setOnMouseMoved(this::handleMouseMovement);
    }

    /**
     * Initialize bricks in a grid pattern
     */
    private void initializeBricks() {
        int rows = 4;
        int cols = 10;
        bricks = new Rectangle[rows][cols];

        Color[] brickColors = {
                Color.web("#ff006e"),
                Color.web("#fb5607"),
                Color.web("#ffbe0b"),
                Color.web("#8338ec")
        };

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Rectangle brick = new Rectangle(
                        col * (BRICK_WIDTH + 5) + 10,
                        row * (BRICK_HEIGHT + 5) + 50,
                        BRICK_WIDTH,
                        BRICK_HEIGHT
                );
                brick.setFill(brickColors[row]);
                bricks[row][col] = brick;
                gamePane.getChildren().add(brick);
            }
        }
    }

    /**
     * Initialize UI elements (score, game over message)
     */
    private void initializeUI() {
        scoreText = new Text(20, 30, "Score: 0");
        scoreText.setFont(Font.font("Arial", 20));
        scoreText.setFill(Color.web("#00ff00"));
        gamePane.getChildren().add(scoreText);

        gameOverText = new Text(WINDOW_WIDTH / 2.0 - 100, WINDOW_HEIGHT / 2.0, "");
        gameOverText.setFont(Font.font("Arial", 40));
        gameOverText.setFill(Color.web("#ff006e"));
        gamePane.getChildren().add(gameOverText);
    }

    /**
     * Setup keyboard event listeners for paddle movement
     */
    private void setupKeyListeners() {
        gamePane.setOnKeyPressed(this::handleKeyPress);
        gamePane.setOnKeyReleased(this::handleKeyRelease);
        gamePane.setFocusTraversable(true);
        gamePane.requestFocus();
    }

    /**
     * Handle keyboard press events
     */
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
            moveLeft = true;
        } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
            moveRight = true;
        } else if (event.getCode() == KeyCode.R) {
            resetGame();
        }
    }

    /**
     * Handle keyboard release events
     */
    private void handleKeyRelease(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
            moveLeft = false;
        } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
            moveRight = false;
        }
    }

    /**
     * Handle mouse movement for paddle control
     */
    private void handleMouseMovement(MouseEvent event) {
        double mouseX = event.getX();
        double paddleX = paddle.getX();
        double paddleCenter = paddleX + PADDLE_WIDTH / 2.0;

        if (mouseX < paddleCenter - 5) {
            moveLeft = true;
            moveRight = false;
        } else if (mouseX > paddleCenter + 5) {
            moveRight = true;
            moveLeft = false;
        } else {
            moveLeft = false;
            moveRight = false;
        }
    }

    /**
     * Start the game loop using AnimationTimer
     */
    private void startGameLoop() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameOver && !gameWon) {
                    updatePaddlePosition();
                    updateBallPosition();
                    checkCollisions();
                    checkGameConditions();
                }
            }
        };
        timer.start();
    }

    /**
     * Update paddle position based on keyboard/mouse input
     */
    private void updatePaddlePosition() {
        double paddleX = paddle.getX();

        if (moveLeft && paddleX > 0) {
            paddle.setX(paddleX - paddleSpeed);
        }
        if (moveRight && paddleX < WINDOW_WIDTH - PADDLE_WIDTH) {
            paddle.setX(paddleX + paddleSpeed);
        }
    }

    /**
     * Update ball position and handle boundary collisions
     */
    private void updateBallPosition() {
        double ballX = ball.getCenterX();
        double ballY = ball.getCenterY();

        // Update position
        ball.setCenterX(ballX + ballVelocityX);
        ball.setCenterY(ballY + ballVelocityY);

        // Left and right boundary collision
        if (ball.getCenterX() - BALL_RADIUS <= 0 || ball.getCenterX() + BALL_RADIUS >= WINDOW_WIDTH) {
            ballVelocityX = -ballVelocityX;
            ball.setCenterX(Math.max(BALL_RADIUS, Math.min(WINDOW_WIDTH - BALL_RADIUS, ball.getCenterX())));
        }

        // Top boundary collision
        if (ball.getCenterY() - BALL_RADIUS <= 0) {
            ballVelocityY = -ballVelocityY;
            ball.setCenterY(BALL_RADIUS);
        }

        // Bottom boundary - game over condition
        if (ball.getCenterY() - BALL_RADIUS > WINDOW_HEIGHT) {
            gameOver = true;
            gameOverText.setText("GAME OVER!\nPress R to Restart");
            gameOverText.setX(WINDOW_WIDTH / 2.0 - 150);
            gameOverText.setY(WINDOW_HEIGHT / 2.0);
        }
    }

    /**
     * Check all collision conditions (ball with paddle and bricks)
     */
    private void checkCollisions() {
        // Ball-Paddle collision
        checkPaddleCollision();

        // Ball-Brick collision
        checkBrickCollisions();
    }

    /**
     * Check collision between ball and paddle
     */
    private void checkPaddleCollision() {
        double ballCenterX = ball.getCenterX();
        double ballCenterY = ball.getCenterY();
        double paddleX = paddle.getX();
        double paddleY = paddle.getY();

        // Simple AABB collision detection
        if (ballCenterX >= paddleX && ballCenterX <= paddleX + PADDLE_WIDTH &&
                ballCenterY + BALL_RADIUS >= paddleY && ballCenterY + BALL_RADIUS <= paddleY + PADDLE_HEIGHT) {

            ballVelocityY = -ballVelocityY;
            ball.setCenterY(paddleY - BALL_RADIUS);

            // Add spin based on where the ball hit the paddle
            double paddleCenter = paddleX + PADDLE_WIDTH / 2.0;
            double hitOffset = (ballCenterX - paddleCenter) / (PADDLE_WIDTH / 2.0);
            ballVelocityX += hitOffset * 3;
        }
    }

    /**
     * Check collision between ball and bricks
     */
    private void checkBrickCollisions() {
        for (int row = 0; row < bricks.length; row++) {
            for (int col = 0; col < bricks[row].length; col++) {
                Rectangle brick = bricks[row][col];

                if (brick != null && gamePane.getChildren().contains(brick)) {
                    if (checkRectangleCollision(brick)) {
                        // Remove brick and update score
                        gamePane.getChildren().remove(brick);
                        bricks[row][col] = null;
                        score += 10;
                        updateScoreDisplay();

                        // Bounce ball
                        ballVelocityY = -ballVelocityY;
                    }
                }
            }
        }
    }

    /**
     * Check collision between ball and a rectangle (brick or paddle)
     */
    private boolean checkRectangleCollision(Rectangle rect) {
        double ballCenterX = ball.getCenterX();
        double ballCenterY = ball.getCenterY();
        double rectX = rect.getX();
        double rectY = rect.getY();
        double rectWidth = rect.getWidth();
        double rectHeight = rect.getHeight();

        // Find closest point on rectangle to ball center
        double closestX = Math.max(rectX, Math.min(ballCenterX, rectX + rectWidth));
        double closestY = Math.max(rectY, Math.min(ballCenterY, rectY + rectHeight));

        // Calculate distance
        double distanceX = ballCenterX - closestX;
        double distanceY = ballCenterY - closestY;
        double distance = Math.sqrt(distanceX * distanceX + distanceY * distanceY);

        return distance < BALL_RADIUS;
    }

    /**
     * Update score display on UI
     */
    private void updateScoreDisplay() {
        scoreText.setText("Score: " + score);
    }

    /**
     * Check win/lose conditions
     */
    private void checkGameConditions() {
        // Check if all bricks are destroyed (win condition)
        boolean allBricksDestroyed = true;
        for (Rectangle[] row : bricks) {
            for (Rectangle brick : row) {
                if (brick != null && gamePane.getChildren().contains(brick)) {
                    allBricksDestroyed = false;
                    break;
                }
            }
        }

        if (allBricksDestroyed) {
            gameWon = true;
            gameOverText.setText("YOU WIN!\nPress R to Restart");
            gameOverText.setX(WINDOW_WIDTH / 2.0 - 120);
            gameOverText.setY(WINDOW_HEIGHT / 2.0);
            gameOverText.setFill(Color.web("#00ff00"));
        }
    }

    /**
     * Reset game to initial state
     */
    private void resetGame() {
        score = 0;
        gameOver = false;
        gameWon = false;
        ballVelocityX = BALL_SPEED;
        ballVelocityY = -BALL_SPEED;
        gameOverText.setText("");
        gameOverText.setFill(Color.web("#ff006e"));

        // Reset ball position
        ball.setCenterX(WINDOW_WIDTH / 2.0);
        ball.setCenterY(WINDOW_HEIGHT / 2.0);

        // Reset paddle position
        paddle.setX((WINDOW_WIDTH - PADDLE_WIDTH) / 2.0);

        // Reset bricks
        gamePane.getChildren().removeIf(node ->
                node instanceof Rectangle && !node.equals(paddle) && !(node instanceof Rectangle && node.getStyle().contains("fx-fill")));
        for (int row = 0; row < bricks.length; row++) {
            for (int col = 0; col < bricks[row].length; col++) {
                if (bricks[row][col] != null) {
                    gamePane.getChildren().add(bricks[row][col]);
                } else {
                    Rectangle brick = new Rectangle(
                            col * (BRICK_WIDTH + 5) + 10,
                            row * (BRICK_HEIGHT + 5) + 50,
                            BRICK_WIDTH,
                            BRICK_HEIGHT
                    );
                    Color[] brickColors = {
                            Color.web("#ff006e"),
                            Color.web("#fb5607"),
                            Color.web("#ffbe0b"),
                            Color.web("#8338ec")
                    };
                    brick.setFill(brickColors[row]);
                    bricks[row][col] = brick;
                    gamePane.getChildren().add(brick);
                }
            }
        }

        updateScoreDisplay();
    }

    /**
     * Main method to launch the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
