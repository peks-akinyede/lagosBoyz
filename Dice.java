import java.util.Random;

public class Dice {

	private int face;
	
	public Dice(){
		this.face = 1;
	}
	
	public int roll(){
		Random Generate = new Random();
		this.face = Generate.nextInt(6 - 1 + 1) + 1;
		return this.face;
	}
	
}
