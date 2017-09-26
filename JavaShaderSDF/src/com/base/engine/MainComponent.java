package com.base.engine;

import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_1;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_2;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_3;
import static org.lwjgl.glfw.GLFW.GLFW_JOYSTICK_4;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwGetJoystickName;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;

public class MainComponent {

	// Members
	public long windowInstance;
	private boolean isOkToRender;
	private boolean isRunning;

	private Mesh mesh;
	private Shader shader;
	
	// Constructor
	public MainComponent() {

		isOkToRender = false;
		isRunning = false;
		
	}

	// Entry Point
	public static void main(String[] args) {

		// Create the application instance
		MainComponent renderSDF = new MainComponent();

		// Run the application component
		renderSDF.run();

	}

	// Application Interface
	public void run() {

		long nTime = 0;

		if (isRunning)
			return;

		isRunning = true;

		// Set FullScreen true = desktop resolution override
		try {

			Window.createWindow(1024, 768, "Jello_O", true);

		} catch (Exception e) {

			// TODO Auto-generated catch block
			isOkToRender = false;
			isRunning = false;

			e.printStackTrace();

			return;
		}

		// Save the instance
		windowInstance = Window.getWindow();

		System.out.println("Display: " + Window.getWidth() + "x" + Window.getHeight());
				
		initOpenGL();
		
		System.out.println("");
		System.out.println("OpenGL Version: " + glGetString(GL_VERSION));
		System.out.println("");

		// FullScreen Triangle Mesh ? May use full-screen triangle with no mesh
		mesh = new Mesh();
		//Vertex[] data = new Vertex[] {	new Vertex(new Vector3f(-1.0f,  3.0f, 0.0f)),
		//								new Vertex(new Vector3f( 3.0f, -1.0f, 0.0f)),
		//								new Vertex(new Vector3f(-1.0f, -1.0f, 0.0f))};
		//mesh.addVertices(data);
		
		// Load the Shaders .vert, .frag
		shader = new Shader();
		
		
		shader.setVertexShader(ResourceLoader.loadShader("basic.vert"));
		shader.setFragmentShader(ResourceLoader.loadShader("basic.frag"));
		shader.compileShader();
		
		shader.addUniform("iTime");
		shader.addUniform("iResolution");
		
		shader.bind();
		
		// Game Pad Init
		System.out.println("");
		String Controller1Name = glfwGetJoystickName(GLFW_JOYSTICK_1);
		String Controller2Name = glfwGetJoystickName(GLFW_JOYSTICK_2);
		String Controller3Name = glfwGetJoystickName(GLFW_JOYSTICK_3);
		String Controller4Name = glfwGetJoystickName(GLFW_JOYSTICK_4);
		System.out.println("GamePad1: " + Controller1Name);
		System.out.println("GamePad2: " + Controller2Name);
		System.out.println("GamePad3: " + Controller3Name);
		System.out.println("GamePad4: " + Controller4Name);
		// glfwGetJoystickAxes(GLFW_JOYSTICK_1) [5]
		// glfwGetJoystickButtons(GLFW_JOYSTICK_1) [13]
		
		// Get Start Clock
		nTime = Timer.startTime();

		// Update UniformBuffer
		 shader.updateResolution(Window.getWidth(), Window.getHeight());

		// Made it this far set true
		isOkToRender = true;

		// Loop till exit
		renderLoop();

		// Tidy Up
		cleanUp();
	}

	public void initOpenGL() {
		
		// This line is critical for LWJGL's interaction with GLFW
		GL.createCapabilities();

		// Set the clear color
		glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
		//glEnable(GL_FRAMEBUFFER_SRGB);
		glFrontFace(GL_CW);
		glDisable(GL_CULL_FACE);
		glDisable(GL_DEPTH_TEST);
	}

	// Main Application Loop
	private void renderLoop() {

		double fps = 0;
		double avgFps = 0;
		int frameCount = 0;
		double globalTime = 0;
		long nTime = 0;

		// Run until close window or has pressed the ESCAPE key.
		while (!glfwWindowShouldClose(windowInstance)) {

			if (isOkToRender) {

				// Get EndClock
				nTime = Timer.endTime();

				// Update Shader - Must be called after EndTime
				globalTime += Timer.getSeconds();
				shader.updateTime((float)globalTime);

				// Calculate FPS
				fps = Timer.getFps();
				avgFps += fps;
				frameCount++;

				// Get StartClock
				nTime = Timer.startTime();

				// Clear Display
				//glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				shader.bind();
				
				// Render SDF - VertexShader FullScreen Triangle
				mesh.drawFST();
								
				// Swap the screen buffers
				glfwSwapBuffers(windowInstance);
			}

			// Poll for window events.
			glfwPollEvents();

			if (Keyboard.keyUp(GLFW_KEY_ESCAPE)) {

				glfwSetWindowShouldClose(windowInstance, true);

			}

		}

		// In case of MT
		isOkToRender = false;

		// Print Status
		System.out.println("");
		System.out.println("Last FPS: " + (int) fps);
		System.out.println("Average FPS: " + (int) avgFps / frameCount);
		System.out.println("Frame Count: " + frameCount);

	}

	// Clean up the resources
	public void cleanUp() {

		// Close Window
		Window.destroyWindow();

		// Terminate GLFW and free the error callback
		glfwTerminate();
		glfwSetErrorCallback(null).free();
	}

}
