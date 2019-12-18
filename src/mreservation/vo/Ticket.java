package mreservation.vo;

public class Ticket {
	private String movie_title;
	private String movie_title_en;
	private String movie_rating;
	private String screening_cnt;
	private String screening_start;
	private String screening_end;
	private int auditorium_id;
	private int reservation_id;
	private int user_id;
	private String reservation_time;
	private int seat_row;
	private int seat_number;
	private int audience_cnt;
	
	public Ticket() {
		
	}
	

	public Ticket(String movie_title, String movie_title_en, String movie_rating, String screening_cnt,
			String screening_start, String screening_end, int auditorium_id, int reservation_id, int user_id,
			String reservation_time, int seat_row, int seat_number, int audience_cnt) {
		super();
		this.movie_title = movie_title;
		this.movie_title_en = movie_title_en;
		this.movie_rating = movie_rating;
		this.screening_cnt = screening_cnt;
		this.screening_start = screening_start;
		this.screening_end = screening_end;
		this.auditorium_id = auditorium_id;
		this.reservation_id = reservation_id;
		this.user_id = user_id;
		this.reservation_time = reservation_time;
		this.seat_row = seat_row;
		this.seat_number = seat_number;
		this.audience_cnt = audience_cnt;
	}


	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public String getMovie_title_en() {
		return movie_title_en;
	}

	public void setMovie_title_en(String movie_title_en) {
		this.movie_title_en = movie_title_en;
	}

	public String getMovie_rating() {
		return movie_rating;
	}

	public void setMovie_rating(String movie_rating) {
		this.movie_rating = movie_rating;
	}

	public String getScreening_cnt() {
		return screening_cnt;
	}

	public void setScreening_cnt(String screening_cnt) {
		this.screening_cnt = screening_cnt;
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

	public String getReservation_time() {
		return reservation_time;
	}

	public void setReservation_time(String reservation_time) {
		this.reservation_time = reservation_time;
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


	public int getAudience_cnt() {
		return audience_cnt;
	}


	public void setAudience_cnt(int audience_cnt) {
		this.audience_cnt = audience_cnt;
	}


	@Override
	public String toString() {
		return "Ticket [movie_title=" + movie_title + ", movie_title_en=" + movie_title_en + ", movie_rating="
				+ movie_rating + ", screening_cnt=" + screening_cnt + ", screening_start=" + screening_start
				+ ", screening_end=" + screening_end + ", auditorium_id=" + auditorium_id + ", reservation_id="
				+ reservation_id + ", user_id=" + user_id + ", reservation_time=" + reservation_time + ", seat_row="
				+ seat_row + ", seat_number=" + seat_number + ", audience_cnt=" + audience_cnt + "]";
	}

	
}
