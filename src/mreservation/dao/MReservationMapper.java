package mreservation.dao;

import java.util.ArrayList;
import java.util.HashMap;
import mreservation.vo.*;

public interface MReservationMapper {
	
	ArrayList<Movie> movieList();
	ArrayList<Screening> screeningList();
	AuditoriumSize getAuditoriumSize(HashMap<String, String> map);
	ArrayList<Seat> getReservedSeat(HashMap<String, String> map);
	int insertReservation(HashMap<String, String> map);
	int reserveSeat(HashMap<String, Integer> map);
	Reservation currentReservation();
	int deleteReservation(HashMap<String, Object> map);
	ArrayList<Ticket> getTicket(int reservation_id);
	int insertUser(User user);
	ArrayList<User> searchUser(HashMap<String, Object> map);
}
