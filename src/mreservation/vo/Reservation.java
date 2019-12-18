package mreservation.vo;

public class Reservation {
	private int reservation_id;
	private int user_id;
	private int screening_id;
	private String reservation_time;
	
	public Reservation() {
		
	}

	public Reservation(int reservation_id, int user_id, int screening_id, String reservation_time) {
		super();
		this.reservation_id = reservation_id;
		this.user_id = user_id;
		this.screening_id = screening_id;
		this.reservation_time = reservation_time;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getScreening_id() {
		return screening_id;
	}

	public void setScreening_id(int screening_id) {
		this.screening_id = screening_id;
	}
	
	public String getReservation_time() {
		return reservation_time;
	}

	public void setReservation_time(String reservation_time) {
		this.reservation_time = reservation_time;
	}

	@Override
	public String toString() {
		return "Reservation [reservation_id=" + reservation_id + ", user_id=" + user_id + ", screening_id="
				+ screening_id + ", reservation_time=" + reservation_time + "]";
	}

	
}
