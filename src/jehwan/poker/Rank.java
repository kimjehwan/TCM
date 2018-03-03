package jehwan.poker;

public class Rank {
	private int grade, number;
	private String result;

	Rank(int grade, int number,String result) {
		this.grade = grade;
		this.number = number;
		this.result = result;
	}

	public String getResult() {
		return result;
	}
	
	public int getGrade() {
		return grade;
	}

	public int getNumber() {
		return number;
	}
}
