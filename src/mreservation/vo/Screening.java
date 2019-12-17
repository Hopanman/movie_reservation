package mreservation.vo;

public class Screening {
	private String screening_start;
	private String screening_end;
	private String movie_title;
	private int auditorium_id;
	
	public Screening() {
		
	}

	public Screening(String screening_start, String screening_end, String movie_title, int auditorium_id) {
		super();
		this.screening_start = screening_start;
		this.screening_end = screening_end;
		this.movie_title = movie_title;
		this.auditorium_id = auditorium_id;
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

	public String getMovie_title() {
		return movie_title;
	}

	public void setMovie_title(String movie_title) {
		this.movie_title = movie_title;
	}

	public int getAuditorium_id() {
		return auditorium_id;
	}

	public void setAuditorium_id(int auditorium_id) {
		this.auditorium_id = auditorium_id;
	}

	@Override
	public String toString() {
		return "Screening [screening_start=" + screening_start + ", screening_end=" + screening_end + ", movie_title="
				+ movie_title + ", auditorium_id=" + auditorium_id + "]";
	}
	
	
}
