package com.base.engine;

public class Input extends Mouse {

	public static void update(int key, boolean down, boolean up, boolean keyPressed) {

		if (key == -1) {
			
			// Happens when Xbox Controller XButton/PowerButtom is pressed
		}

		if (key >= 0 || key <= 65535) {

			downkeys[key] = down;
			upkeys[key] = up;
			currentkeys[key] = keyPressed;

		}

	}

	public static void updateMousePosition(double x, double y) {

		mouseCoords[0] = x;
		mouseCoords[1] = y;

	}

	public static void updateMouseButtons(boolean x, boolean y, boolean z) {

		mouseButtons[0] = x;
		mouseButtons[1] = y;
		mouseButtons[2] = z;

	}

	public static void updateMouseScrollPosition(double x, double y) {

		mouseScroll[0] = x;
		mouseScroll[1] = y;

	}

}
