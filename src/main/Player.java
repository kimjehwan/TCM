package main;

public class Player {
	private static String id;
	private static int pScore;
	private static int sScore;
	private static int qScore;
	
	public Player(String dbId, int dbPscore, int dbSscore, int dbQscore) {
		this.id = dbId;
		this.pScore = dbPscore;
		this.sScore  = dbSscore;
		this.qScore = dbQscore;
	}

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
