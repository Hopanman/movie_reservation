package mreservation.ui;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mreservation.dao.MReservationDAO;
import mreservation.vo.*;

public class MReservationUI {
	
	private Scanner sc = new Scanner(System.in);
	private MReservationDAO dao = new MReservationDAO();
	private User user = null;
	
	public MReservationUI() {
		
		System.out.println("안녕하세요 SCIT영화관 어플입니다");
		System.out.println("본 어플은 회원제로 되어있습니다");
		
		while(true) {
			if(user == null) logInMenu();
			
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
				refundReservation();
				break;
			case 4:
				break;
			case 5:
				updateUserInfo();
				break;
			case 6:
				user = null;
				sc.nextLine();
				break;
			case 7:
				System.out.println("이용해주셔서 감사합니다");
				System.exit(0);
			default:
				System.out.println("1에서 7까지의 정수를 입력해주세요");
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	void updateUserInfo() {
		sc.nextLine();
		System.out.println();
		System.out.println("============회원정보===============");
		System.out.println("아이디:"+user.getUser_name());
		System.out.println("비밀번호:"+user.getUser_password().replaceAll("[A-Za-z0-9]", "*"));
		System.out.println("핸드폰번호:"+user.getUser_phone());
		System.out.println("이메일주소:"+user.getUser_email());
		System.out.println("잔여포인트:"+user.getUser_point()+"점");
		System.out.println("============회원정보===============");
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("회원정보수정을 위해 변경할 비밀번호/핸드폰번호/이메일주소를 입력해주세요(메인화면:0)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("비밀번호는 8~20자리의 영문 대소문자,숫자를 사용하세요");
		System.out.println("변경하지 않는 항목은 엔터를 눌러주세요");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String user_password;
		String user_password_confirm;
		String user_phone;
		String user_email;
		
		while(true) {
			Pattern pattern = Pattern.compile("[A-Za-z0-9]{8,20}");
			System.out.print("비밀번호>");
			user_password = sc.nextLine().trim();
			
			if(user_password.equals("0")) return;
			if(user_password.equals("")) break;
			
			Matcher matcher = pattern.matcher(user_password);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("비밀번호는 8~20자리의 영문 대소문자,숫자로 구성되어야 합니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
			} else {
				break;
			}
		}
		
		while(true) {
			if(user_password.equals("")) break;
			System.out.print("비밀번호 확인>");
			user_password_confirm = sc.nextLine().trim();
			
			if(user_password_confirm.equals("0")) return;
			
			if(!user_password_confirm.equals(user_password)) {
				System.out.println();
				System.out.println("입력하신 새 비밀번호와 일치하지 않습니다");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
			}
			else break;
		}
		
		while(true) {
			Pattern pattern = Pattern.compile("01[01789]-[0-9]{3,4}-[0-9]{4}");
			System.out.print("핸드폰번호>");
			user_phone = sc.nextLine().trim();
			
			if(user_phone.equals("0")) return;
			if(user_phone.equals("")) break;
			
			Matcher matcher = pattern.matcher(user_phone);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("올바른 형식으로 입력해주세요");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
			} else {
				break;
			}
		}
		
		while(true) {
			Pattern pattern = Pattern.compile("[a-z0-9]+@[a-z]+\\.[a-z]+");
			System.out.print("이메일주소>");
			user_email = sc.nextLine().trim();
			
			if(user_email.equals("0")) return;
			if(user_email.equals("")) break;
			
			Matcher matcher = pattern.matcher(user_email);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("올바른 형식으로 입력해주세요");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println();
			} else {
				break;
			}
		}
		
		if(user_password.equals("") && user_phone.equals("") && user_email.equals("")) return;
		
		String pre_user_password = user.getUser_password();
		String pre_user_phone = user.getUser_phone();
		String pre_user_email = user.getUser_email();
		
		if(!user_password.equals("")) user.setUser_password(user_password);
		if(!user_phone.equals("")) user.setUser_phone(user_phone);
		if(!user_email.equals("")) user.setUser_email(user_email);
		
		
		boolean res = dao.updateUserInfo(user);
		
		if(res) {
			System.out.println();
			System.out.println("회원정보 변경에 성공했습니다");
		} else {
			System.out.println();
			System.out.println("시스템 문제가 발생했습니다");
			user.setUser_password(pre_user_password);
			user.setUser_phone(pre_user_phone);
			user.setUser_email(pre_user_email);
		}
	}

	void logInMenu() {
		while(true) {
			System.out.println();
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("로그인을 위해 아이디와 비밀번호를 입력바랍니다(회원가입:1)(종료:0)");
			System.out.print("아이디>");
			String user_name = sc.nextLine();
			if(user_name.equals("0")) {
				System.out.println();
				System.out.println("이용해주셔서 감사합니다");
				System.exit(0);
			} else if(user_name.equals("1")) {
				enrollUser();
				continue;
			}
			
			System.out.print("비밀번호>");
			String user_password = sc.nextLine();
			if(user_password.equals("0")) {
				System.out.println();
				System.out.println("이용해주셔서 감사합니다");
				System.exit(0);
			} else if(user_password.equals("1")) {
				enrollUser();
				continue;
			}
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("user_name", user_name);
			map.put("password_in", true);
			map.put("user_password",user_password);
			
			ArrayList<User> userList = dao.searchUser(map);
			
			if(userList == null) {
				System.out.println();
				System.out.println("시스템 문제가 발생했습니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(userList.size() == 0) {
				System.out.println();
				System.out.println("가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				user = userList.get(0);
				System.out.println();
				System.out.println("로그인되었습니다.");
				break;
			}
		}

	}

	void enrollUser() {
		System.out.println();
		System.out.println("회원가입을 위해 아이디/비밀번호/핸드폰번호/이메일주소를 입력해주세요");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("아이디는 5~20자리의 영문 소문자,숫자/비밀번호는 8~20자리의 영문 대소문자,숫자를 사용하세요(로그인화면:0)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String user_name;
		String user_password;
		String user_phone;
		String user_email;
		
		while(true) {
			Pattern pattern = Pattern.compile("[a-z0-9]{5,20}");
			System.out.print("아이디>");
			user_name = sc.nextLine().trim();
			
			if(user_name.equals("0")) return;
			
			Matcher matcher = pattern.matcher(user_name);
			
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("아이디는 5~20자리의 영문 소문자,숫자로 구성되어야 합니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
			} else {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("user_name", user_name);
				map.put("password_in", false);
				
				ArrayList<User> user = dao.searchUser(map);
				
				if(user == null) {
					System.out.println();
					System.out.println("시스템 문제가 발생했습니다.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println();
				} else if(user.size() == 1) {
					System.out.println();
					System.out.println("사용할 수 없는 ID입니다");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println();
				} else {
					break;
				}
				
			}
		}
		
		while(true) {
			Pattern pattern = Pattern.compile("[A-Za-z0-9]{8,20}");
			System.out.print("비밀번호>");
			user_password = sc.nextLine().trim();
			
			if(user_password.equals("0")) return;
			
			Matcher matcher = pattern.matcher(user_password);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("비밀번호는 8~20자리의 영문 대소문자,숫자로 구성되어야 합니다.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
			} else {
				break;
			}
		}
		
		while(true) {
			Pattern pattern = Pattern.compile("01[01789]-[0-9]{3,4}-[0-9]{4}");
			System.out.print("핸드폰번호>");
			user_phone = sc.nextLine().trim();
			
			if(user_phone.equals("0")) return;
			
			Matcher matcher = pattern.matcher(user_phone);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("올바른 형식으로 입력해주세요");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
			} else {
				break;
			}
		}
		
		while(true) {
			Pattern pattern = Pattern.compile("[a-z0-9]+@[a-z]+\\.[a-z]+");
			System.out.print("이메일주소>");
			user_email = sc.nextLine().trim();
			
			if(user_email.equals("0")) return;
			
			Matcher matcher = pattern.matcher(user_email);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("올바른 형식으로 입력해주세요");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println();
			} else {
				break;
			}
		}
		
		User user = new User(user_name, user_password, user_phone, user_email);
		boolean res = dao.insertUser(user);
		
		if(res) System.out.println("정상등록되었습니다");
		else System.out.println("시스템 문제가 발생했습니다");
	}

	void refundReservation() {
		sc.nextLine();
		System.out.println();
		while(true) {
			System.out.println("환불하실 티켓의 예매번호를 입력해주세요(메인화면:0)");
			System.out.println("**환불은 상영시작시간 20분 전까지 가능합니다**");
			System.out.print(">");
			int reservation_id;
			try {
				reservation_id = sc.nextInt();
				if(reservation_id < 0) throw new Exception();
			} catch (Exception e) {
				System.out.println();
				sc.nextLine();
				System.out.println("예매번호를 올바르게 입력해주십시오");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.out.println();
				continue;
			}
			
			if(reservation_id == 0) return;
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("reservation_id", reservation_id);
			map.put("refund", true);
			boolean res = dao.deleteReservation(map);
			
			if(res) {
				System.out.println("환불되셨습니다");
				return;
			} else {
				return;
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
		HashMap<String, Object> movie_data;
		int user_id = user.getUser_id();
		while(true) {
			System.out.println("예매하실 영화제목과 시간을 입력해주세요(메인화면:0)");
			System.out.print("영화제목>");
			String movie_title = sc.nextLine().trim();
			if(movie_title.equals("0")) return;
			System.out.print("상영시작시간(월-일 시:분)>");
			String screening_start = sc.nextLine().trim();
			if(screening_start.equals("0")) return;
			
			movie_data = new HashMap<String, Object>();
			movie_data.put("movie_title", movie_title);
			movie_data.put("screening_start", screening_start);
			movie_data.put("user_id",user_id);
			
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
			
			if(!st2.hasMoreTokens()) break;
		}
		
		int pre_user_point = user.getUser_point();
		user.setUser_point(pre_user_point+90*numberList.size());
		boolean pointRes = dao.updateUserInfo(user);
		
		if(!pointRes) user.setUser_point(pre_user_point);
		
		ArrayList<Ticket> ticket = dao.getTicket(reservation_id);
		if(ticket == null || ticket.size() == 0) {
			System.out.println("티켓 출력에 문제가 생겼습니다");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
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
				System.out.println("예매번호 "+ticket.get(i).getReservation_id()+"번");
				System.out.println(ticket.get(i).getMovie_title());
				System.out.println(ticket.get(i).getMovie_title_en());
				System.out.println(ticket.get(i).getMovie_rating());
				System.out.println("======================================");
				System.out.print(ticket.get(i).getScreening_start().substring(0, 10)+"\t");
				System.out.print(ticket.get(i).getScreening_cnt()+"\t");
				System.out.print(ticket.get(i).getScreening_start().substring(11, 16)+"~");
				System.out.println(ticket.get(i).getScreening_end().substring(11, 16));
				System.out.print(ticket.get(i).getAuditorium_id()+"관"+"\t");
				System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"열");
				System.out.print(ticket.get(i).getSeat_number()+"번");
				System.out.println("("+ticket.get(i).getAudience_cnt()+"명)");
				System.out.println("======================================");
				System.out.print(user.getUser_name()+"님"+"\t");
				System.out.println("잔여포인트: "+(pointRes ? user.getUser_point()+"점": "포인트 적립에 문제가 발생했습니다 관리자에게 문의해주세요"));
				System.out.println("======================================");
				System.out.println("-환불은 표기시간 20분 전까지 가능합니다");
				System.out.println("-지연입장에 의한 관람불편을 최소화하고자 영화는 약 10분 후에 시작됩니다");
				System.out.println("              SCIT영화관                               ");
				System.out.println("==============영화 입장권===============");
			} else {
				if(i==0) {
					System.out.println("예매번호 "+ticket.get(i).getReservation_id()+"번");
					System.out.println(ticket.get(i).getMovie_title());
					System.out.println(ticket.get(i).getMovie_title_en());
					System.out.println(ticket.get(i).getMovie_rating());
					System.out.println("======================================");
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
					System.out.println("======================================");
					System.out.print(user.getUser_name()+"님"+"\t");
					System.out.println("잔여포인트: "+(pointRes ? user.getUser_point()+"점": "포인트 적립에 문제가 발생했습니다 관리자에게 문의해주세요"));
					System.out.println("======================================");
					System.out.println("-환불은 표기시간 20분 전까지 가능합니다");
					System.out.println("-지연입장에 의한 관람불편을 최소화하고자 영화는 약 10분 후에 시작됩니다");
					System.out.println("              SCIT영화관                               ");
					System.out.println("==============영화 입장권===============");
				} else {
					System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"열");
					System.out.print(ticket.get(i).getSeat_number()+"번"+" ");
				}
			}
			
		}
		
		System.out.println("즐거운 관람 되십시오");
	}

	void searchMovieInfo() {
		sc.nextLine();
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
		System.out.println();
		System.out.println("메인화면으로 돌아가시려면 아무 키나 입력해주세요");
		sc.nextLine();
	}

	void printMainMenu() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("환영합니다 "+user.getUser_name()+"님");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("원하시는 메뉴를 선택해주세요");
		System.out.println("1.상영영화정보");
		System.out.println("2.영화예매");
		System.out.println("3.영화환불");
		System.out.println("4.예매조회");
		System.out.println("5.회원정보수정");
		System.out.println("6.로그아웃");
		System.out.println("7.종료");
		System.out.print(">");
	}
}
