package com.base.engine;

public class Keyboard {

	public static boolean[] downkeys = new boolean[65536];
	public static boolean[] upkeys = new boolean[65536];
	public static boolean[] currentkeys = new boolean[65536];

	public static boolean keyDown(int keyCode) {

		return downkeys[keyCode];

	}

	public static boolean keyUp(int keyCode) {

		return upkeys[keyCode];

	}

	public static boolean keyPressed(int keyCode) {

		return currentkeys[keyCode];

	}

	public Keyboard() {
		super();
	}

}