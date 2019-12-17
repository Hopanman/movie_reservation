package mreservation.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mreservation.vo.Movie;
import mreservation.vo.Screening;

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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return screeningList;
	}
}
