# Brick Breaker Game - JavaFX Application

A classic arcade-style Brick Breaker game built with JavaFX, demonstrating GUI development, collision detection, and game mechanics in Java.

## 📋 Overview

Players control a paddle to bounce a ball and break colorful bricks. The game features:
- **Keyboard & Mouse Controls**: Use arrow keys/WASD or move your mouse to control the paddle
- **Collision Detection**: Advanced hit detection for ball-paddle and ball-brick interactions
- **Score Tracking**: Earn 10 points for each brick destroyed
- **Win/Lose Conditions**: Complete the level by breaking all bricks or lose if the ball falls

## 🎮 Game Features

### Core Gameplay
- **Paddle Movement**: Arrow keys (LEFT/RIGHT) or WASD keys, or move mouse to control
- **Ball Physics**: Realistic bouncing with angle-based spin when hitting the paddle
- **Brick Grid**: 4 rows × 10 columns of colorful bricks in different colors
- **Score System**: +10 points per brick destroyed
- **Game States**: Active, Won (all bricks destroyed), and Over (ball missed)

### Visual Elements
- **Neon Color Scheme**: Dark background with vibrant neon colors
- **Real-time Score Display**: Top-left corner shows current score
- **Game Over Screen**: Clear win/loss messages with restart instructions
- **Dynamic Ball**: Green ball with smooth movement
- **Color-Coded Bricks**: 4 different colors for visual appeal

## 🛠️ Prerequisites

Before running the game, ensure you have:

1. **Java Development Kit (JDK) 11 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **JavaFX SDK** (separate download from JDK 11+)
   - Download from: https://gluonhq.com/products/javafx/
   - Version: 21 or compatible with your JDK version
   - Note the installation path (e.g., `/path/to/javafx-sdk-21`)

3. **Maven** (for project management)
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn --version`

## 📦 Project Structure

```
JavaTestAI/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/brickbreaker/
│   │   │       └── BrickBreakerGame.java
│   │   └── resources/
│   └── test/
│       └── java/
│           └── com/brickbreaker/
│               └── BrickBreakerGameTest.java
├── pom.xml
├── README.md
└── SETUP_GUIDE.md
```

## 🚀 Setup & Installation

### Option 1: Using Maven (Recommended)

1. **Clone or download the repository**
   ```bash
   cd JavaTestAI
   ```

2. **Update `pom.xml` with your JavaFX path**
   - Open `pom.xml`
   - Replace `${javafx.version}` with your JavaFX version (e.g., 21)
   - Set `${javafx.modules.path}` to your JavaFX SDK path

3. **Install dependencies**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn javafx:run
   ```

### Option 2: Manual Compilation & Execution

1. **Navigate to project directory**
   ```bash
   cd JavaTestAI
   ```

2. **Compile the code**
   ```bash
   javac --module-path /path/to/javafx-sdk-21/lib \
         --add-modules javafx.controls \
         -d out \
         src/main/java/com/brickbreaker/BrickBreakerGame.java
   ```
   Replace `/path/to/javafx-sdk-21` with your actual JavaFX SDK path.

3. **Run the application**
   ```bash
   java --module-path /path/to/javafx-sdk-21/lib \
        --add-modules javafx.controls \
        -cp out \
        com.brickbreaker.BrickBreakerGame
   ```

### Option 3: Using an IDE (IntelliJ IDEA or Eclipse)

#### IntelliJ IDEA
1. Open the project in IntelliJ
2. Go to **File > Project Structure > Libraries**
3. Click **+** and add JavaFX SDK as a library
4. Go to **Run > Edit Configurations**
5. Add VM options: `--module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls`
6. Click **Run**

#### Eclipse
1. Open the project in Eclipse
2. Right-click project > **Build Path > Configure Build Path**
3. Add JavaFX SDK to **Libraries** tab
4. Right-click project > **Run As > Java Application**

## 🎮 How to Play

### Controls
| Key/Input | Action |
|-----------|--------|
| **← Arrow / A** | Move paddle left |
| **→ Arrow / D** | Move paddle right |
| **Mouse Movement** | Move paddle to mouse X-position |
| **R** | Restart game (after winning or losing) |

### Gameplay Flow
1. **Start**: Ball appears at center, paddle at bottom
2. **Play**: Move the paddle to keep the ball bouncing
   - Hit bricks to destroy them and gain score (+10 points each)
   - Each brick hit changes ball direction
3. **Win**: Destroy all bricks → "YOU WIN!" message appears
4. **Lose**: Miss the ball (falls below screen) → "GAME OVER!" message appears
5. **Restart**: Press **R** to play again

### Tips
- The ball bounces differently depending on where you hit it with the paddle
- Hitting the ball on the paddle's edges adds spin for better control
- Use mouse movement for smoother, more intuitive paddle control
- Break bricks strategically to maintain gameplay flow

## 🧪 Testing

### Running Unit Tests

```bash
mvn test
```

### Manual Testing Checklist

- [ ] **Startup**: Game launches without errors
- [ ] **Paddle Control**: 
  - [ ] Arrow keys move paddle left/right
  - [ ] WASD keys work as alternative controls
  - [ ] Mouse movement controls paddle smoothly
- [ ] **Ball Physics**:
  - [ ] Ball bounces off paddle
  - [ ] Ball bounces off left/right walls
  - [ ] Ball bounces off top wall
  - [ ] Ball bounces off bricks
- [ ] **Collision Detection**:
  - [ ] All bricks register collision
  - [ ] Paddle collision works from all angles
  - [ ] Score increases on brick collision
- [ ] **Game States**:
  - [ ] Game properly detects win condition (all bricks destroyed)
  - [ ] Game properly detects loss condition (ball falls)
  - [ ] "YOU WIN!" or "GAME OVER!" displays correctly
- [ ] **Restart**: 
  - [ ] Pressing R resets game completely
  - [ ] All bricks regenerate
  - [ ] Ball returns to center
  - [ ] Score resets to 0
- [ ] **Score Tracking**: Score increments by 10 for each brick

## 📊 Game Constants & Configuration

You can customize the game by modifying constants in `BrickBreakerGame.java`:

```java
private static final int WINDOW_WIDTH = 800;           // Game window width
private static final int WINDOW_HEIGHT = 600;          // Game window height
private static final int PADDLE_WIDTH = 100;           // Paddle width
private static final int PADDLE_HEIGHT = 15;           // Paddle height
private static final int BALL_RADIUS = 8;              // Ball radius
private static final int BRICK_WIDTH = 75;             // Brick width
private static final int BRICK_HEIGHT = 20;            // Brick height
private static final double BALL_SPEED = 5.0;          // Initial ball speed
```

Adjust these values to modify:
- **Difficulty**: Increase `BALL_SPEED` for faster gameplay
- **Game Size**: Modify `WINDOW_WIDTH` and `WINDOW_HEIGHT`
- **Paddle Control**: Adjust `paddleSpeed` variable
- **Brick Layout**: Change grid dimensions in `initializeBricks()`

## 🎓 Learning Outcomes

By studying this code, you'll learn:

1. **JavaFX Fundamentals**
   - `Application` and `Stage` lifecycle
   - `Scene` and `Pane` for layout management
   - Shape objects: `Circle`, `Rectangle`, `Text`
   - Event handling: keyboard and mouse input
   - Color management and styling

2. **Game Development Concepts**
   - **Game Loop**: AnimationTimer for continuous updates
   - **Collision Detection**: AABB (Axis-Aligned Bounding Box) algorithm
   - **Physics**: Velocity-based movement and bouncing
   - **Game States**: Managing game over, win, and active states

3. **Advanced Java Concepts**
   - Event-driven programming
   - Real-time animation and updates
   - Object-oriented design patterns
   - Memory management in continuous loops

4. **Software Design Principles**
   - Separation of concerns (UI, logic, game loop)
   - Helper methods for clarity
   - Configuration constants for easy modification
   - Comprehensive documentation

## 🐛 Troubleshooting

### "JavaFX not found" Error
- Ensure JavaFX SDK is properly installed
- Verify `--module-path` points to correct JavaFX lib directory
- Check that `--add-modules javafx.controls` is included in VM options

### Game Window Doesn't Open
- Ensure Java version is 11 or higher: `java -version`
- Try running with explicit memory: `java -Xmx512m ...`
- On Linux, you may need additional libraries: `sudo apt-get install libgtk-3-0`

### Paddle Not Responding
- Click on the game window to ensure it has focus
- Verify keyboard/mouse input is not being captured by another application
- Try restarting the application

### Ball Movement is Choppy
- Close other resource-intensive applications
- Increase RAM available to JVM: `java -Xmx1024m ...`
- Check system performance while game is running

## 📝 Code Structure Overview

### Main Components

- **BrickBreakerGame**: Main application class extending JavaFX `Application`
- **Game Loop**: AnimationTimer that updates game state ~60 FPS
- **Collision Detection**: Rectangle-circle collision using closest-point algorithm
- **Input Handling**: Keyboard and mouse event listeners
- **Game State Management**: Tracks score, game over, win conditions

### Key Methods

| Method | Purpose |
|--------|---------|
| `start()` | Initialize game and UI |
| `initializeBall()` | Create ball object |
| `initializePaddle()` | Create paddle object |
| `initializeBricks()` | Generate brick grid |
| `startGameLoop()` | Start AnimationTimer for game updates |
| `updateBallPosition()` | Handle ball movement and boundary collisions |
| `updatePaddlePosition()` | Handle paddle movement |
| `checkCollisions()` | Detect all collisions |
| `checkPaddleCollision()` | Ball-paddle collision with spin |
| `checkBrickCollisions()` | Ball-brick collision detection |
| `checkGameConditions()` | Win/lose condition checks |
| `resetGame()` | Reset all game objects to initial state |

## 🚀 Performance Optimization

The game is optimized for smooth 60 FPS gameplay:

- **AnimationTimer**: Native JavaFX rendering loop (~60 FPS)
- **Collision Detection**: O(n) brick checks (40 total bricks)
- **Memory Management**: Minimal object creation in game loop
- **Rendering**: Double-buffering handled by JavaFX internally

## 📚 Further Learning

After mastering this game, consider adding:

1. **Sound Effects**: BGM and collision sounds using JavaFX Media API
2. **Power-ups**: Temporary paddle expansion, multi-ball, slow-motion
3. **Difficulty Levels**: Progressive ball speed increases
4. **Level Progression**: Multiple brick patterns and increasingly difficult layouts
5. **High Score Persistence**: Save/load high scores from file
6. **Animations**: Particle effects, brick destruction animations
7. **Networking**: Multiplayer support with JavaFX networking

## 📄 License

This project is provided as an educational resource for learning JavaFX and game development.

## 🤝 Contributing

Feel free to extend this game with additional features and improvements!

---

**Happy Gaming! 🎮**
