package mreservation.vo;

public class Screening {
	private String screening_start;
	private String screening_end;
	private String movie_title;
	private String auditorium_name;
	
	public Screening() {
		
	}

	public Screening(String screening_start, String screening_end, String movie_title, String auditorium_name) {
		super();
		this.screening_start = screening_start;
		this.screening_end = screening_end;
		this.movie_title = movie_title;
		this.auditorium_name = auditorium_name;
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

	public String getAuditorium_name() {
		return auditorium_name;
	}

	public void setAuditorium_name(String auditorium_name) {
		this.auditorium_name = auditorium_name;
	}

	@Override
	public String toString() {
		return "Screening [screening_start=" + screening_start + ", screening_end=" + screening_end + ", movie_title="
				+ movie_title + ", auditorium_name=" + auditorium_name + "]";
	}
	
	
}
