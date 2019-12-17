package mreservation.vo;

public class Movie {
	private int movie_id;
	private String movie_title;
	private String movie_title_en;
	private String movie_director;
	private String movie_actor;
	private int movie_duration;
	private String movie_rating;
	
	public Movie() {
		
	}

	public Movie(int movie_id, String movie_title, String movie_title_en, String movie_director, String movie_actor,
			int movie_duration, String movie_rating) {
		super();
		this.movie_id = movie_id;
		this.movie_title = movie_title;
		this.movie_title_en = movie_title_en;
		this.movie_director = movie_director;
		this.movie_actor = movie_actor;
		this.movie_duration = movie_duration;
		this.movie_rating = movie_rating;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
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

	public String getMovie_director() {
		return movie_director;
	}

	public void setMovie_director(String movie_director) {
		this.movie_director = movie_director;
	}

	public String getMovie_actor() {
		return movie_actor;
	}

	public void setMovie_actor(String movie_actor) {
		this.movie_actor = movie_actor;
	}

	public int getMovie_duration() {
		return movie_duration;
	}

	public void setMovie_duration(int movie_duration) {
		this.movie_duration = movie_duration;
	}

	public String getMovie_rating() {
		return movie_rating;
	}

	public void setMovie_rating(String movie_rating) {
		this.movie_rating = movie_rating;
	}

	@Override
	public String toString() {
		return "Movie [movie_id=" + movie_id + ", movie_title=" + movie_title + ", movie_title_en=" + movie_title_en
				+ ", movie_director=" + movie_director + ", movie_actor=" + movie_actor + ", movie_duration="
				+ movie_duration + ", movie_rating=" + movie_rating + "]";
	}
	
	
}
