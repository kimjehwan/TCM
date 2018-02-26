package jehwan.poker;

public class Rank {
	private int grade, number;

	Rank(int grade, int number) {
		this.grade = grade;
		this.number = number;
	}

	public int getGrade() {
		return grade;
	}

	public int getNumber() {
		return number;
	}
}
