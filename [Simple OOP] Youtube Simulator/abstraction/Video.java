package abstraction;

public class Video extends Youtube {

	
	public Video(String title, String description, int duration) {
		super(title, description, "Video", 0, duration);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void showInfo() {
		super.showInfo();
		System.out.printf("(%s)", this.toTime(this.duration));
	}

	public void showInfo(int currDur) {
		super.showInfo();
		System.out.printf("(played at %s)", this.toTime(currDur));
	}

	@Override
	public void play() {
		super.play();
		System.out.printf("Current play at %s/%s\n\n", this.toTime(this.currDur), this.toTime(this.duration));
		System.out.print("Choose (0: back|1: skip forward|2: skip backward): ");
		
	}

}
