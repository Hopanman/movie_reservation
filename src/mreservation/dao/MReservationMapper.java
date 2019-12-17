package mreservation.dao;

import java.util.ArrayList;
import mreservation.vo.*;

public interface MReservationMapper {
	
	ArrayList<Movie> movieList();
	ArrayList<Screening> screeningList();
}
