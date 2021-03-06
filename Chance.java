import java.util.Random;

public class Chance extends Square{
	
	private static final String[] Cards = {
			"Advance to Go.",
			"Go to jail. Move directly to jail. Do not pass Go. Do not collect �200.",
			"Advance to Pall Mall. If you pass Go collect �200.",
			"Take a trip to Marylebone Station and if you pass Go collect �200",
			"Advance to Trafalgar Square. If you pass Go collect �200.",
			"Advance to Mayfair.",
			"Go back three spaces",
			"Make general repairs on all of your houses. For each house pay �25. For each hotel pay �100.",
			"You are assessed for street repairs: �40 per house, �115 per hotel.",
			"Pay school fees of �150",
			"Drunk in charge fine �20.",
			"Speeding fine �15.",
			"Your building loan matures. Receive �150.",
			"You have won a crossword competition. Collect �100.",
			"Bank pays you dividend of �50.",
			"Get out of jail free. This card may be kept until needed or sold."};
	
	

	
	public Chance(String name, int type) {
		super(name, type);
		// TODO Auto-generated constructor stub
	}
	
	
	public String drawCard(){
		int number;
		Random Generate = new Random();
		number = Generate.nextInt((Cards.length-1) - 0 + 0) + 0;
		return Cards[number];
	}
	
	public void advanceBySpaces(Player person, Monopoly m, int spaces){
		int count=0;
		while(!(count==spaces))
		{
			person.moveBackward();
			if(person.getPosition() == 0){
				m.getUI().displayString("You passed GO! Collect 200!");
				person.getRent(200);
			}
			count++;
		}
		m.moveTokenBackwardByThree(person);
		m.getUI().displayString(m.getBoard().squareInfo(person.getPosition()));
	}
	
	public int buildingsCardWithLowCharge(Player person){
		return person.payRent((25*person.numberOfHouses()) + (100*person.numberOfHotels()));
	}
	
	public int buildingsCardWithHighCharge(Player person){
		return person.payRent((40*person.numberOfHouses()) + (115*person.numberOfHotels()));
	}
	
	public void processCard(Player person, Monopoly m, String cardInstruction){
		m.getUI().displayString(cardInstruction);
		
		switch(cardInstruction){
		
		case "Advance to Go.":
			advanceToPosition(person,m,0);
			break;
		
		case "Go to jail. Move directly to jail. Do not pass Go. Do not collect �200.":
			break;
			
		case "Advance to Pall Mall. If you pass Go collect �200.":
			advanceToPosition(person,m,11);
			break;
			
		case "Take a trip to Marylebone Station and if you pass Go collect �200":
			advanceToPosition(person,m,15);
			break;
			
		case "Advance to Trafalgar Square. If you pass Go collect �200.":
			advanceToPosition(person,m,24);
			break;
			
		case "Advance to Mayfair.":
			advanceToPosition(person,m,49);
			break;
		
		case "Go back three spaces":
			advanceBySpaces(person,m,3);
			break;
			
		case "Make general repairs on all of your houses. For each house pay �25. For each hotel pay �100.":
			m.getUI().displayString("You paid: " + buildingsCardWithLowCharge(person));
			break;
			
		case "You are assessed for street repairs: �40 per house, �115 per hotel.":
			m.getUI().displayString("You paid: " + buildingsCardWithHighCharge(person));
			break;
			
		case "Pay school fees of �150":
			m.getUI().displayString("You paid: " + person.payRent(150));
			break;
			
		case "Drunk in charge fine �20.":
			m.getUI().displayString("You paid: " + person.payRent(20));
			break;
			
		case "Speeding fine �15.":
			m.getUI().displayString("You paid: " + person.payRent(15));
			break;
			
		case "Your building loan matures. Receive �150.":
			m.getUI().displayString("You got: " + person.getRent(150));
			break;
			
		case "You have won a crossword competition. Collect �100.":
			m.getUI().displayString("You got: " + person.getRent(100));
			break;
			
		case "Bank pays you dividend of �50.":
			m.getUI().displayString("You got: " + person.getRent(50));
			break;
			
		case "Get out of jail free. This card may be kept until needed or sold.":
			break;
			
		default:
			break;
		}
		
	}
	
	public void advanceToPosition(Player person, Monopoly m, int position){
		int count=0;
		while(!(person.getPosition()==position))
		{
			person.moveForward();
			if(person.getPosition() == 0){
				m.getUI().displayString("You passed GO! Collect 200!");
				person.getRent(200);
			}
			count++;
		}
		try {
			m.moveToken(count, person);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		m.getUI().displayString(m.getBoard().squareInfo(person.getPosition()));
		
	}
	
}
