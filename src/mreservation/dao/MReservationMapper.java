package mreservation.dao;

import java.util.ArrayList;
import java.util.HashMap;
import mreservation.vo.*;

public interface MReservationMapper {
	
	ArrayList<Movie> movieList();
	ArrayList<Screening> screeningList();
	AuditoriumSize getAuditoriumSize(HashMap<String, Object> map);
	ArrayList<Seat> getReservedSeat(HashMap<String, Object> map);
	int insertReservation(HashMap<String, Object> map);
	int reserveSeat(HashMap<String, Integer> map);
	Reservation currentReservation();
	int deleteReservation(HashMap<String, Object> map);
	ArrayList<Ticket> getTicket(HashMap<String, Integer> ticketMap);
	int insertUser(User user);
	ArrayList<User> searchUser(HashMap<String, Object> map);
	int updateUserInfo(User user);
	ArrayList<UserMovie> getReservedMovie(int user_id);
}
