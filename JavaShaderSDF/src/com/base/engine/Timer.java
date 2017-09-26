
package com.base.engine;

public class Timer {

	private static long NanoFreq = 1000000000L;
	
	private static double seconds;
	private static double delta;
	private static double fps;
	
	private static long startT;
	private static long endT;
	
	public static long startTime()
	{
		startT = System.nanoTime(); 
		return startT;
	}
	
	public static long endTime()
	{
		endT = System.nanoTime(); 
		return endT;
	}
	
	public static double getDelta()
	{
		delta = endT - startT;
		
		return delta;
	}
	
	public static double getFps()
	{
		fps = (double)NanoFreq / (double)(endT - startT);
		
		return fps;
	}
	
	public static double getSeconds()
	{
		seconds = (double)(endT - startT) / (double)NanoFreq;
		
		return seconds;
	}
	
}
