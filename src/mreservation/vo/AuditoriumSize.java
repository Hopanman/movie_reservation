package mreservation.vo;

public class AuditoriumSize {
	private int auditorium_id;
	private int max_seat_row;
	private int max_seat_number;
	
	public AuditoriumSize() {
		
	}

	public AuditoriumSize(int auditorium_id, int max_seat_row, int max_seat_number) {
		super();
		this.auditorium_id = auditorium_id;
		this.max_seat_row = max_seat_row;
		this.max_seat_number = max_seat_number;
	}
	
	public int getAuditorium_id() {
		return auditorium_id;
	}

	public void setAuditorium_id(int auditorium_id) {
		this.auditorium_id = auditorium_id;
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
		return "AuditoriumSize [auditorium_id=" + auditorium_id + ", max_seat_row=" + max_seat_row
				+ ", max_seat_number=" + max_seat_number + "]";
	}

	
}
