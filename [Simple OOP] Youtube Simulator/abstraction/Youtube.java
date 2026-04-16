package abstraction;

public abstract class Youtube implements Skippable, Playable {
	protected String title, description, type;
	protected int duration, currDur;
	
	public Youtube(String title, String description, String type, int currDur, int duration) {
		super();
		this.title = title;
		this.description = description;
		this.type = type;
		this.currDur = currDur;
		this.duration = duration;
	}
	
	public void showInfo() {
		System.out.printf("%s: %s", this.type, this.title);
	}
	
	public void play() {
		System.out.println("Now playing: " + this.title);
		System.out.println(this.description);
	}
	
	@Override
	public void skipForward() {
		this.currDur += 5;
		if(this.currDur > this.duration) {
			this.currDur = this.duration;
		}
		
	}

	@Override
	public void skipBackward() {
		this.currDur -= 5;
		if(this.currDur < 0) {
			this.currDur = 0;
		}
		
	}
	
//	Convert dur to string
	public String toTime(int dur) {
		int s, m;
		m = dur/60;
		s = dur % 60;
		
		return String.format("%02d:%02d", m, s);
	}
	
	
//	Getter setter
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCurrDur() {
		return currDur;
	}

	public void setCurrDur(int currDur) {
		this.currDur = currDur;
	}
	
	
}
