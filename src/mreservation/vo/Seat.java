package mreservation.vo;

public class Seat {
	private int seat_id;
	private int seat_row;
	private int seat_number;
	private int auditorium_id;
	
	public Seat() {
		
	}

	public Seat(int seat_id, int seat_row, int seat_number, int auditorium_id) {
		super();
		this.seat_id = seat_id;
		this.seat_row = seat_row;
		this.seat_number = seat_number;
		this.auditorium_id = auditorium_id;
	}

	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}

	public int getSeat_row() {
		return seat_row;
	}

	public void setSeat_row(int seat_row) {
		this.seat_row = seat_row;
	}

	public int getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}

	public int getAuditorium_id() {
		return auditorium_id;
	}

	public void setAuditorium_id(int auditorium_id) {
		this.auditorium_id = auditorium_id;
	}

	@Override
	public String toString() {
		return "Seat [seat_id=" + seat_id + ", seat_row=" + seat_row + ", seat_number=" + seat_number
				+ ", auditorium_id=" + auditorium_id + "]";
	}
	
	
}
