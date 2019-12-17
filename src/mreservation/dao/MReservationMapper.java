package mreservation.dao;

import java.util.ArrayList;
import java.util.HashMap;
import mreservation.vo.*;

public interface MReservationMapper {
	
	ArrayList<Movie> movieList();
	ArrayList<Screening> screeningList();
	AuditoriumSize getAuditoriumSize(HashMap<String, String> map);
}
