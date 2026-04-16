package abstraction;

public class Livestream extends Youtube {



	public Livestream(String title, String description) {
		super(title, description, "Livestream", 0, 0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void skipForward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void skipBackward() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showInfo() {
		super.showInfo();
		
	}

	@Override
	public void play() {
		super.play();
		System.out.println("\n");
		System.out.print("Choose (0: back): ");
	}

}
