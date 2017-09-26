package com.base.engine;

public class Mouse extends Keyboard {

	public static boolean[] mouseButtons = new boolean[3];
	public static double[] mouseCoords = new double[2];
	public static double[] mouseScroll = new double[2];

	public static double getMouseX() {

		return mouseCoords[0];
	}

	public static double getMouseY() {

		return mouseCoords[1];
	}

	public static boolean getLeftMouseButton() {

		return mouseButtons[0];
	}

	public static boolean getRightMouseButton() {

		return mouseButtons[1];
	}

	public static boolean getMiddleMouseButton() {

		return mouseButtons[2];
	}

	public static double getMouseScrollX() {

		return mouseScroll[0];
	}

	public static double getMouseScrollY() {

		return mouseScroll[1];
	}

	public Mouse() {
		super();
	}

}