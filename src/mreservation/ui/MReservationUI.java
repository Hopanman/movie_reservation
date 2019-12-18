package mreservation.ui;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mreservation.dao.MReservationDAO;
import mreservation.vo.*;

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
		sc.nextLine();
		System.out.println();
		ArrayList<Screening> screeningList = dao.screeningList();
		
		if(screeningList == null || screeningList.size() == 0) {
			System.out.println("현재 예매를 할 수가 없습니다.");
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
		
		AuditoriumSize aud_size = null;
		HashMap<String, String> movie_data;
		while(true) {
			System.out.println("예매하실 영화제목과 시간을 입력해주세요(메인화면:0)");
			System.out.print("영화제목>");
			String movie_title = sc.nextLine().trim();
			if(movie_title.equals("0")) return;
			System.out.print("상영시작시간(월-일 시:분)>");
			String screening_start = sc.nextLine().trim();
			if(screening_start.equals("0")) return;
			
			movie_data = new HashMap<String, String>();
			movie_data.put("movie_title", movie_title);
			movie_data.put("screening_start", screening_start);
			
			aud_size = dao.getAuditoriumSize(movie_data);
			
			if(aud_size != null) break;
		}
		
		int auditorium_id = aud_size.getAuditorium_id();
		int max_row = aud_size.getMax_seat_row();
		int max_number = aud_size.getMax_seat_number();
		
		char[][] seats = new char[max_row][max_number];
		
		for(int i=0; i<max_row; i++) {
			for(int j=0; j<max_number; j++) {
				seats[i][j] = '_';
			}
		}
		
		ArrayList<Seat> seatList =  dao.getReservedSeat(movie_data);
		if(seatList == null) {
			System.out.println("시스템 문제가 발생했습니다");
			return;
		}
		
		for(Seat seat : seatList) {
			seats[seat.getSeat_row()-1][seat.getSeat_number()-1] = 'X';
		}
		
		char[] row_name = {'A','B','C','D','E','F','G'};
		
		System.out.print("   ");
		for(int i=0; i<max_number; i++) {
			System.out.print(i+1+"  ");
		}
		System.out.println();
		for(int i=0; i<max_row; i++) {
			System.out.print(row_name[i]+"  ");
			for(int j=0; j<max_number; j++) {
				System.out.print(seats[i][j]+"  ");
			}
			System.out.println();
		}
		int seat_row;
		int seat_number;
		int cnt_audience;
		int reservation_id;
		ArrayList<Integer> rowList = new ArrayList<>();
		ArrayList<Integer> numberList = new ArrayList<>();
		Outer: while(true) {
			System.out.print("좌석을 선택해주세요(2인이상 선택가능)(예:G열3번 G열4번)(메인화면:0)>");
			String selected_seat = sc.nextLine().trim();
			if(selected_seat.equals("0")) return;
			
			StringTokenizer st1 = new StringTokenizer(selected_seat, " ");
			Pattern pattern = Pattern.compile("([A-"+row_name[max_row-1]+(("a-"+row_name[max_row-1]).toLowerCase())+"]열)([1-"+max_number+"]번)");
			
			while(st1.hasMoreTokens()) {
				Matcher matcher = pattern.matcher(st1.nextToken());
				if(!matcher.matches()) {
					System.out.println("올바른 형식으로 입력해주세요");
					System.out.println();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue Outer;
				}
			}
			boolean insert_res1 = dao.insertReservation(movie_data);
			if(!insert_res1) {
				System.out.println("시스템 문제가 발생했습니다");
				return;
			}
			
			Reservation cur_reserve = dao.currentReservation();
			if(cur_reserve == null) {
				System.out.println("시스템 문제가 발생했습니다.");
				return;
			}
			reservation_id = cur_reserve.getReservation_id();
			StringTokenizer st2 = new StringTokenizer(selected_seat, " ");
			
			while(st2.hasMoreTokens()) {
				Matcher matcher = pattern.matcher(st2.nextToken());
				if(matcher.find()) {
					seat_row = matcher.group(1).toUpperCase().charAt(0)-'A'+1;
					seat_number = Integer.parseInt(matcher.group(2).substring(0,1));
					boolean insert_res2 = dao.reserveSeat(seat_row, seat_number, auditorium_id, reservation_id);
					
					if(!insert_res2) {
						rowList.clear();
						numberList.clear();
						continue Outer;
					}
					rowList.add(seat_row);
					numberList.add(seat_number);
				}
			}
			cnt_audience = st2.countTokens();
			if(!st2.hasMoreTokens()) break;
		}
		
		ArrayList<Ticket> ticket = dao.getTicket(reservation_id);
		if(ticket == null || ticket.size() == 0) {
			System.out.println("티켓 출력에 문제가 생겼습니다");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("예매 조회에서 확인해주세요");
			return;
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("==============영화 입장권===============");
		for(int i=0; i<ticket.size();i++) {
			if(i==0 && i == ticket.size()-1) {
				System.out.println(ticket.get(i).getReservation_id());
				System.out.println(ticket.get(i).getMovie_title());
				System.out.println(ticket.get(i).getMovie_title_en());
				System.out.println(ticket.get(i).getMovie_rating());
				System.out.println("=================================");
				System.out.print(ticket.get(i).getScreening_start().substring(0, 10)+"\t");
				System.out.print(ticket.get(i).getScreening_cnt()+"\t");
				System.out.print(ticket.get(i).getScreening_start().substring(11, 16)+"~");
				System.out.println(ticket.get(i).getScreening_end().substring(11, 16));
				System.out.print(ticket.get(i).getAuditorium_id()+"관"+"\t");
				System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"열");
				System.out.print(ticket.get(i).getSeat_number()+"번");
				System.out.println("("+ticket.get(i).getAudience_cnt()+"명)");
				System.out.println("==================================");
				System.out.println("-교환 및 환불은 표기시간 20분 전까지 가능합니다");
				System.out.println("-지연입장에 의한 관람불편을 최소화하고자 영화는 약 10분후에 시작됩니다");
				System.out.println("                       SCIT영화관                               ");
				System.out.println("==============영화 입장권===============");
			} else {
				if(i==0) {
					System.out.println(ticket.get(i).getReservation_id());
					System.out.println(ticket.get(i).getMovie_title());
					System.out.println(ticket.get(i).getMovie_title_en());
					System.out.println(ticket.get(i).getMovie_rating());
					System.out.println("=================================");
					System.out.print(ticket.get(i).getScreening_start().substring(0, 10)+"\t");
					System.out.print(ticket.get(i).getScreening_cnt()+"\t");
					System.out.print(ticket.get(i).getScreening_start().substring(11, 16)+"~");
					System.out.println(ticket.get(i).getScreening_end().substring(11, 16));
					System.out.print(ticket.get(i).getAuditorium_id()+"관"+"\t");
					System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"열");
					System.out.print(ticket.get(i).getSeat_number()+"번"+" ");
				} else if(i==ticket.size()-1) {
					System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"열");
					System.out.print(ticket.get(i).getSeat_number()+"번");
					System.out.println("("+ticket.get(i).getAudience_cnt()+"명)");
					System.out.println("==================================");
					System.out.println("-교환 및 환불은 표기시간 20분 전까지 가능합니다");
					System.out.println("-지연입장에 의한 관람불편을 최소화하고자 영화는 약 10분후에 시작됩니다");
					System.out.println("                       SCIT영화관                               ");
					System.out.println("==============영화 입장권===============");
				} else {
					System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"열");
					System.out.print(ticket.get(i).getSeat_number()+"번"+" ");
				}
			}
			
		}
		
		System.out.println("영화예매 완료");
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
			return;
		}
		System.out.println("SCIT영화관에서 상영중인 영화입니다");
		System.out.println("================================");
		for(Movie movie : movieList) {
			System.out.println("제목:"+movie.getMovie_title());
			System.out.println("감독:"+movie.getMovie_director());
			System.out.println("주연배우:"+movie.getMovie_actor());
			System.out.println("상영시간:"+movie.getMovie_duration());
			System.out.println("관람등급:"+movie.getMovie_rating());
			System.out.println("================================");
		}
	}

	void printMainMenu() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
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
