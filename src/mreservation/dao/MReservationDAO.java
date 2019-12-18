package mreservation.dao;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mreservation.vo.AuditoriumSize;
import mreservation.vo.Movie;
import mreservation.vo.Reservation;
import mreservation.vo.Screening;
import mreservation.vo.Seat;
import mreservation.vo.Ticket;
import mreservation.vo.User;

public class MReservationDAO {
	
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public ArrayList<Movie> movieList() {
		SqlSession session = null;
		ArrayList<Movie> movieList = null;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			movieList = mapper.movieList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return movieList;
	}

	public ArrayList<Screening> screeningList() {
		SqlSession session = null;
		ArrayList<Screening> screeningList = null;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			screeningList = mapper.screeningList();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return screeningList;
	}

	public AuditoriumSize getAuditoriumSize(HashMap<String, String> movie_data) {
		SqlSession session = null;
		AuditoriumSize aud_size = null;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			aud_size = mapper.getAuditoriumSize(movie_data);
		}catch(PersistenceException e) {
			System.out.println("올바른 형식으로 입력해주세요");
			System.out.println();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null; 
		}catch (Exception e) {
			System.out.println("알 수 없는 오류가 발생했습니다");
			System.out.println();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return null;
		} finally {
			if(session != null) session.close();
		}
		
		if(aud_size == null) {
			System.out.println("예매할 수 있는 영화가 아닙니다");
			System.out.println();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return aud_size;
	}

	public ArrayList<Seat> getReservedSeat(HashMap<String, String> movie_data) {
		SqlSession session = null;
		ArrayList<Seat> reserved_seat = null;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			reserved_seat = mapper.getReservedSeat(movie_data);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		
		return reserved_seat;
	}

	public boolean insertReservation(HashMap<String, String> movie_data) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			res = mapper.insertReservation(movie_data);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		
		return res > 0;
	}

	public boolean reserveSeat(int seat_row, int seat_number, int auditorium_id, int reservation_id) {
		SqlSession session = null;
		int res = 0;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("seat_row", seat_row);
		map.put("seat_number", seat_number);
		map.put("auditorium_id", auditorium_id);
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			res = mapper.reserveSeat(map);
			session.commit();
		}catch (PersistenceException e) {
			HashMap<String, Object> delete_map = new HashMap<String, Object>();
			delete_map.put("reservation_id", reservation_id);
			delete_map.put("refund", false);
			deleteReservation(delete_map);
			System.out.println("올바른 좌석을 선택해주세요");
			System.out.println();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
		return res > 0;
	}

	

	public boolean deleteReservation(HashMap<String, Object> map) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			res = mapper.deleteReservation(map);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("시스템 문제가 발생했습니다");
			return false;
		}finally {
			if(session != null) session.close();
		}
		
		if(res == 0) System.out.println("환불할 수 없는 예매번호입니다");
		return res > 0;
	}

	public Reservation currentReservation() {
		SqlSession session = null;
		Reservation curr_res = null;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			curr_res = mapper.currentReservation();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return curr_res;
	}

	public ArrayList<Ticket> getTicket(int reservation_id) {
		SqlSession session = null;
		ArrayList<Ticket> ticket = null;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			ticket = mapper.getTicket(reservation_id);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return ticket;
	}

	public boolean insertUser(User user) {
		SqlSession session = null;
		int res = 0;
		
		try {
			session = factory.openSession();
			MReservationMapper mapper = session.getMapper(MReservationMapper.class);
			res = mapper.insertUser(user);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session != null) session.close();
		}
		
		return res > 0;
	}
}
