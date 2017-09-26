package com.base.engine;

import org.lwjgl.glfw.*;
import org.lwjgl.system.*;
import java.nio.*;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Window {

	// The window handle
	private static long window;
	private static long height;
	private static long width;
	private static long title;

	public static long getWindow() {
		return window;
	}

	public static void setWindow(long window) {
		Window.window = window;
	}

	public static long getHeight() {
		return height;
	}

	public static void setHeight(long height) {
		Window.height = height;
	}

	public static long getWidth() {
		return width;
	}

	public static void setWidth(long width) {
		Window.width = width;
	}

	public static long getTitle() {
		return title;
	}

	public static void setTitle(long title) {
		Window.title = title;
	}

	public static void createWindow(int width, int height, String title, boolean fullScreen) {

		// Setup an error callback.
		GLFWErrorCallback.createPrint(System.err).set();

		// Initialize GLFW. Most GLFW functions will not work before doing this.
		if (!glfwInit()) {

			throw new IllegalStateException("Unable to initialize GLFW");
		}

		// Configure GLFW
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

		// Get the resolution of the primary monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		if (fullScreen == true) {

			// Create the window
			window = glfwCreateWindow(vidmode.width(), vidmode.height(), title, NULL, NULL);
			if (window == NULL) {

				throw new RuntimeException("Failed to create the FullScreen OpenGL window");
			}

			Window.setWidth(vidmode.width());
			Window.setHeight(vidmode.height());

		} else {

			// Create the window
			window = glfwCreateWindow(width, height, title, NULL, NULL);
			if (window == NULL) {

				throw new RuntimeException("Failed to create the Regular OpenGL window");
			}

			Window.setWidth(width);
			Window.setHeight(height);
		}

		// Setup a key callback
		glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {

			Input.update(key, action == GLFW_PRESS, action == GLFW_RELEASE, true);

		});

		// Setup a Mouse Cursor callback
		glfwSetCursorPosCallback(window, (window, x, y) -> {

			Input.updateMousePosition(x, y);

		});

		// Setup a Mouse Wheel callback
		glfwSetScrollCallback(window, (window, x, y) -> {

			Input.updateMouseScrollPosition(x, y);

		});

		// Setup a Mouse Button callback
		glfwSetMouseButtonCallback(window, (window, button, action, mods) -> {

			Input.updateMouseButtons(button == GLFW_MOUSE_BUTTON_LEFT, button == GLFW_MOUSE_BUTTON_RIGHT,
					button == GLFW_MOUSE_BUTTON_MIDDLE);

		});

		// Get the thread stack and push a new frame
		try (MemoryStack stack = stackPush()) {

			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			// Get the window size passed to glfwCreateWindow
			glfwGetWindowSize(window, pWidth, pHeight);

			// Position the window
			glfwSetWindowPos(window, 0, 0);

		} // the stack frame is popped automatically

		// Make the OpenGL context current
		glfwMakeContextCurrent(window);

		// Enable v-sync
		glfwSwapInterval(0);

		// Make the window visible
		glfwShowWindow(window);
	}

	public static void destroyWindow() {

		// Free the window call backs and destroy the window
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
	}

}
