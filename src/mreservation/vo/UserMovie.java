package mreservation.vo;

public class UserMovie {
	private int reservation_id;
	private String movie_title;
	private String screening_start;
	private String screening_end;
	private int auditorium_id;
	private String screening_cnt;
	
	public UserMovie() {
		
	}

	public UserMovie(int reservation_id, String movie_title, String screening_start, String screening_end,
			int auditorium_id, String screening_snt) {
		super();
		this.reservation_id = reservation_id;
		this.movie_title = movie_title;
		this.screening_start = screening_start;
		this.screening_end = screening_end;
		this.auditorium_id = auditorium_id;
		this.screening_cnt = screening_snt;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public String getScreening_start() {
		return screening_start;
	}

	public void setScreening_start(String screening_start) {
		this.screening_start = screening_start;
	}

	public String getScreening_end() {
		return screening_end;
	}

	public void setScreening_end(String screening_end) {
		this.screening_end = screening_end;
	}

	public int getAuditorium_id() {
		return auditorium_id;
	}

	public void setAuditorium_id(int auditorium_id) {
		this.auditorium_id = auditorium_id;
	}

	public String getScreening_cnt() {
		return screening_cnt;
	}

	public void setScreening_cnt(String screening_snt) {
		this.screening_cnt = screening_snt;
	}

	@Override
	public String toString() {
		return "UserMovie [reservation_id=" + reservation_id + ", movie_title=" + movie_title + ", screening_start="
				+ screening_start + ", screening_end=" + screening_end + ", auditorium_id=" + auditorium_id
				+ ", screening_cnt=" + screening_cnt + "]";
	}
	
	
}
