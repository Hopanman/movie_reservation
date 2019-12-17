package mreservation.vo;

public class AuditoriumSize {
	private int max_seat_row;
	private int max_seat_number;
	
	public AuditoriumSize() {
		
	}

	public AuditoriumSize(int max_seat_row, int max_seat_number) {
		super();
		this.max_seat_row = max_seat_row;
		this.max_seat_number = max_seat_number;
	}

	public int getMax_seat_row() {
		return max_seat_row;
	}

	public void setMax_seat_row(int max_seat_row) {
		this.max_seat_row = max_seat_row;
	}

	public int getMax_seat_number() {
		return max_seat_number;
	}

	public void setMax_seat_number(int max_seat_number) {
		this.max_seat_number = max_seat_number;
	}

	@Override
	public String toString() {
		return "AuditoriumSize [max_seat_row=" + max_seat_row + ", max_seat_number=" + max_seat_number + "]";
	}
	
	
}
