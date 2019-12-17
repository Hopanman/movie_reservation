package mreservation.ui;

import java.util.*;
import mreservation.dao.MReservationDAO;
import mreservation.vo.Movie;
import mreservation.vo.Screening;

public class MReservationUI {
	
	private Scanner sc = new Scanner(System.in);
	private MReservationDAO dao = new MReservationDAO();
	
	public MReservationUI() {
		
		System.out.println("안녕하세요 SCIT영화관입니다");
		
		while(true) {
			printMainMenu();
			
			int su = 0;
			
			try {
				su = sc.nextInt();
			} catch(Exception e) {
				sc.nextLine();
			}
			
			switch(su) {
			case 1:
				searchMovieInfo();
				break;
			case 2:
				movieReservation();
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				System.out.println("이용해주셔서 감사합니다");
				System.exit(0);
			default:
				System.out.println("1에서 8까지의 정수를 입력해주세요");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	void movieReservation() {
		System.out.println();
		ArrayList<Screening> screeningList = dao.screeningList();
		
		if(screeningList == null || screeningList.size() == 0) {
			System.out.println("현재 예매를 할 수가 없습니다.");
			System.out.println();
			return;
		}
		
		System.out.println("현재 예매할 수 있는 영화 목록입니다");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=======================================================");
		System.out.println("   시작시간\t  종료시간\t  영화제목\t상영관");
		System.out.println("=======================================================");
		for(Screening screening : screeningList) {
			System.out.printf("%s\t%s\t%s\t%d관\n",screening.getScreening_start().substring(5, 16), screening.getScreening_end().substring(5, 16), screening.getMovie_title(), screening.getAuditorium_id());
		}
		System.out.print("예매하실 영화를 입력해주세요>");
		
	}

	void searchMovieInfo() {
		System.out.println();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ArrayList<Movie> movieList = dao.movieList();
		
		if(movieList == null || movieList.size()==0) {
			System.out.println("조회내역이 없습니다");
			System.out.println();
			return;
		}
		
		for(Movie movie : movieList) {
			System.out.println("SCIT영화관에서 상영중인 영화입니다");
			System.out.println("================================");
			System.out.println("제목:"+movie.getMovie_title());
			System.out.println("감독:"+movie.getMovie_director());
			System.out.println("주연배우:"+movie.getMovie_actor());
			System.out.println("상영시간:"+movie.getMovie_duration());
			System.out.println("관람등급:"+movie.getMovie_rating());
		}
	}

	void printMainMenu() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("원하시는 메뉴를 선택해주세요");
		System.out.println("1.상영영화정보");
		System.out.println("2.영화예매");
		System.out.println("3.영화교환");
		System.out.println("4.영화환불");
		System.out.println("5.예매조회");
		System.out.println("6.로그인");
		System.out.println("7.회원가입");
		System.out.println("8.종료");
		System.out.print(">");
	}
}
