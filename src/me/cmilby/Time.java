package me.cmilby;

public class Time {

	private static final long SECOND = 1000000000L;
	
	private Time() {
		// Prevents Initialization
	}
	
	public static double getCurrentTime() {
		return (double) System.nanoTime() / (double) SECOND;
	}
}
