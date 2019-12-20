package mreservation.vo;

public class Reservations {
	private int reservations_id;
	private String reservations_movie_title;
	private int auditorium_id;
	private int seat_id;
	private String reservations_screening_start;
	
	public Reservations() {
		super();
	}

	public Reservations(int reservations_id, String reservations_movie_title, int auditorium_id, int seat_id,
			String reservations_screening_start) {
		super();
		this.reservations_id = reservations_id;
		this.reservations_movie_title = reservations_movie_title;
		this.auditorium_id = auditorium_id;
		this.seat_id = seat_id;
		this.reservations_screening_start = reservations_screening_start;
	}

	public int getReservations_id() {
		return reservations_id;
	}

	public void setReservations_id(int reservations_id) {
		this.reservations_id = reservations_id;
	}

	public String getReservations_movie_title() {
		return reservations_movie_title;
	}

	public void setReservations_movie_title(String reservations_movie_title) {
		this.reservations_movie_title = reservations_movie_title;
	}

	public int getAuditorium_id() {
		return auditorium_id;
	}

	public void setAuditorium_id(int auditorium_id) {
		this.auditorium_id = auditorium_id;
	}

	public int getSeat_id() {
		return seat_id;
	}

	public void setSeat_id(int seat_id) {
		this.seat_id = seat_id;
	}

	public String getReservations_screening_start() {
		return reservations_screening_start;
	}

	public void setReservations_screening_start(String reservations_screening_start) {
		this.reservations_screening_start = reservations_screening_start;
	}

	@Override
	public String toString() {
		return "Reservations [reservations_id=" + reservations_id + ", reservations_movie_title="
				+ reservations_movie_title + ", auditorium_id=" + auditorium_id + ", seat_id=" + seat_id
				+ ", reservations_screening_start=" + reservations_screening_start + "]";
	}
	
	
}
