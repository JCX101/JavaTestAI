# Brick Breaker Game - Detailed Setup Guide

This guide provides step-by-step instructions for setting up and running the Brick Breaker Game.

## Table of Contents
1. [System Requirements](#system-requirements)
2. [JavaFX Installation](#javafx-installation)
3. [Project Setup](#project-setup)
4. [Running the Game](#running-the-game)
5. [IDE Setup](#ide-setup)
6. [Troubleshooting](#troubleshooting)
7. [Quick Start Commands](#quick-start-commands)

---

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 10+, macOS 10.13+, Ubuntu 18.04+
- **Java Version**: JDK 11 or higher
- **RAM**: 512 MB minimum (1 GB recommended)
- **Disk Space**: 500 MB for JDK + 300 MB for JavaFX SDK

### Supported Platforms
- ✅ Windows (10, 11)
- ✅ macOS (Intel and Apple Silicon)
- ✅ Linux (Ubuntu, Debian, CentOS, Fedora)

---

## JavaFX Installation

### Step 1: Download JDK 11 or Higher

1. Visit: https://www.oracle.com/java/technologies/downloads/
2. Download **JDK 21** (or your preferred version ≥ 11)
3. Run the installer and follow on-screen instructions
4. **Note the installation path** (you'll need it later)

**Verify installation:**
```bash
java -version
javac -version
```

You should see output like:
```
java version "21.0.1" 2023-10-17 LTS
```

### Step 2: Download JavaFX SDK

1. Visit: https://gluonhq.com/products/javafx/
2. Download **JavaFX SDK 21** (match your JDK version)
3. **Windows/macOS**: Download the `.zip` file
4. **Linux**: Download the `.tar.gz` file
5. Extract to a convenient location (e.g., `C:\javafx-sdk-21` or `~/javafx-sdk-21`)

**Note the JavaFX path** - you'll use this as `JAVAFX_HOME` or in your IDE configuration.

### Step 3: Set Environment Variables (Optional but Recommended)

#### Windows
1. Right-click **This PC** → **Properties**
2. Click **Advanced system settings** → **Environment Variables**
3. Click **New** under "System variables"
4. Variable name: `JAVAFX_HOME`
5. Variable value: `C:\path\to\javafx-sdk-21` (your extracted path)
6. Click **OK** and restart your terminal

#### macOS/Linux
1. Open terminal
2. Edit your shell profile:
   ```bash
   nano ~/.bash_profile    # For bash
   # or
   nano ~/.zshrc           # For zsh (default on newer macOS)
   ```
3. Add these lines:
   ```bash
   export JAVAFX_HOME=/path/to/javafx-sdk-21
   export PATH=$JAVAFX_HOME/lib:$PATH
   ```
4. Save and reload:
   ```bash
   source ~/.bash_profile  # or ~/.zshrc
   ```

---

## Project Setup

### Option A: Using Git (Recommended)

```bash
# Clone the repository
git clone https://github.com/JCX101/JavaTestAI.git
cd JavaTestAI

# Verify structure
ls -la
# You should see: pom.xml, README.md, src/, etc.
```

### Option B: Manual Download

1. Download the project files from GitHub
2. Extract to a folder
3. Navigate to that folder in terminal/command prompt

---

## Running the Game

### Method 1: Maven (Easiest)

**Prerequisites**: Maven installed on your system

```bash
# Navigate to project directory
cd JavaTestAI

# Clean and build
mvn clean install

# Run the game
mvn javafx:run
```

**First run may take 2-3 minutes** as Maven downloads dependencies.

### Method 2: Command Line (Manual Compilation)

**Replace `/path/to/javafx-sdk-21` with your actual JavaFX path.**

```bash
cd JavaTestAI

# Compile
javac --module-path /path/to/javafx-sdk-21/lib \
      --add-modules javafx.controls \
      -d out \
      src/main/java/com/brickbreaker/BrickBreakerGame.java

# Run
java --module-path /path/to/javafx-sdk-21/lib \
     --add-modules javafx.controls \
     -cp out \
     com.brickbreaker.BrickBreakerGame
```

### Method 3: Using IDE

See **IDE Setup** section below.

---

## IDE Setup

### IntelliJ IDEA

#### Step 1: Open Project
1. File → Open
2. Select the `JavaTestAI` folder
3. Click "Trust Project"

#### Step 2: Configure JavaFX
1. File → Project Structure (Ctrl+Alt+Shift+S on Windows, ⌘+; on macOS)
2. Go to **Libraries** (left sidebar)
3. Click **+** → **Java**
4. Navigate to your JavaFX SDK folder → `lib` folder
5. Select and click **OK**

#### Step 3: Create Run Configuration
1. Run → Edit Configurations
2. Click **+** → **Application**
3. Set **Main class**: `com.brickbreaker.BrickBreakerGame`
4. Go to **VM options** and paste:
   ```
   --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls
   ```
   Replace path accordingly
5. Click **OK**
6. Click the Run button or press Shift+F10

### Eclipse

#### Step 1: Import Project
1. File → Import → Maven → Existing Maven Projects
2. Select the `JavaTestAI` folder
3. Click Finish

#### Step 2: Configure JavaFX
1. Right-click project → Properties
2. Go to **Java Build Path** → **Libraries** tab
3. Click **Add External JARs**
4. Navigate to JavaFX SDK → select all JARs in `lib` folder
5. Click **OK**

#### Step 3: Run Configuration
1. Right-click project → Run As → Run Configurations
2. Create new **Java Application**
3. Main class: `com.brickbreaker.BrickBreakerGame`
4. Arguments tab → VM arguments:
   ```
   --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls
   ```
5. Click **Apply** → **Run**

### Visual Studio Code

#### Step 1: Install Extension Pack for Java
1. Open VS Code
2. Go to Extensions (Ctrl+Shift+X)
3. Search for "Extension Pack for Java"
4. Click Install

#### Step 2: Open Project
1. File → Open Folder
2. Select the `JavaTestAI` folder

#### Step 3: Run Game
1. Open `BrickBreakerGame.java`
2. Click "Run" above the main method
3. If it fails, create `.vscode/launch.json` with JavaFX VM args

---

## Troubleshooting

### Error: "module javafx.controls not found"

**Solution:**
- Verify JavaFX SDK is properly downloaded
- Check that `--module-path` points to the correct `lib` directory
- Ensure the path has no spaces (use quotes if it does): `"C:\Program Files\javafx-sdk-21\lib"`

**Windows Example:**
```bash
java --module-path "C:\javafx-sdk-21\lib" ^
     --add-modules javafx.controls ^
     -cp out com.brickbreaker.BrickBreakerGame
```

### Error: "javac: command not found"

**Solution:**
- Java isn't in your PATH
- Reinstall JDK and ensure "Add Java to PATH" is checked
- Or use full path:
  ```bash
  /path/to/jdk/bin/javac --version
  ```

### Error: "Maven command not found"

**Solution:**
- Download Maven: https://maven.apache.org/download.cgi
- Extract it
- Add to PATH or use full path:
  ```bash
  /path/to/maven/bin/mvn --version
  ```

### Game Window Opens but Has Errors

**Check console output** for specific errors. Common issues:

1. **ClassNotFoundException**: 
   - Ensure you're in the correct directory
   - Check that compilation output directory (`out/`) has the compiled class

2. **Window doesn't appear on Linux**:
   ```bash
   sudo apt-get install libgtk-3-0
   ```

3. **Game is very slow**:
   - Close other applications
   - Increase available RAM: `java -Xmx1024m ...`

---

## Quick Start Commands

### Quick Start with Maven (Easiest)
```bash
cd JavaTestAI
mvn clean javafx:run
```

### Quick Start with Manual Compilation

**Windows:**
```batch
cd JavaTestAI
javac --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls -d out src\main\java\com\brickbreaker\BrickBreakerGame.java
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls -cp out com.brickbreaker.BrickBreakerGame
```

**macOS/Linux:**
```bash
cd JavaTestAI
javac --module-path ~/javafx-sdk-21/lib \
      --add-modules javafx.controls \
      -d out \
      src/main/java/com/brickbreaker/BrickBreakerGame.java
java --module-path ~/javafx-sdk-21/lib \
     --add-modules javafx.controls \
     -cp out \
     com.brickbreaker.BrickBreakerGame
```

---

## Getting Help

If you're still having issues:

1. **Check README.md** for general information
2. **Review console error messages** carefully
3. **Verify all paths** in your configuration
4. **Try with Maven first** - it handles dependencies automatically
5. **Check Java version**: `java -version` should be 11+
6. **Check JavaFX location**: Verify the lib folder exists and contains .jar files

---

Good luck! 🎮