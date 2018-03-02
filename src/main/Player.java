package main;

public class Player {
	private static String id;
	private static int pScore;
	private static int sScore;
	private static int qScore;
	
	public static String getId() {
		return id;
	}
	public static void setId(String id) {
		Player.id = id;
	}
	public static int getpScore() {
		return pScore;
	}
	public static void setpScore(int pScore) {
		Player.pScore = pScore;
	}
	public static int getsScore() {
		return sScore;
	}
	public static void setsScore(int sScore) {
		Player.sScore = sScore;
	}
	public static int getqScore() {
		return qScore;
	}
	public static void setqScore(int qScore) {
		Player.qScore = qScore;
	}

	
}
