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
		
		System.out.println("�ȳ��ϼ��� SCIT��ȭ���Դϴ�");
		
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
				System.out.println("�̿����ּż� �����մϴ�");
				System.exit(0);
			default:
				System.out.println("1���� 8������ ������ �Է����ּ���");
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
			System.out.println("���� ���Ÿ� �� ���� �����ϴ�.");
			return;
		}
		
		System.out.println("���� ������ �� �ִ� ��ȭ ����Դϴ�");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("=======================================================");
		System.out.println("   ���۽ð�\t  ����ð�\t  ��ȭ����\t�󿵰�");
		System.out.println("=======================================================");
		for(Screening screening : screeningList) {
			System.out.printf("%s\t%s\t%s\t%d��\n",screening.getScreening_start().substring(5, 16), screening.getScreening_end().substring(5, 16), screening.getMovie_title(), screening.getAuditorium_id());
		}
		
		AuditoriumSize aud_size = null;
		HashMap<String, String> movie_data;
		while(true) {
			System.out.println("�����Ͻ� ��ȭ����� �ð��� �Է����ּ���(����ȭ��:0)");
			System.out.print("��ȭ����>");
			String movie_title = sc.nextLine().trim();
			if(movie_title.equals("0")) return;
			System.out.print("�󿵽��۽ð�(��-�� ��:��)>");
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
			System.out.println("�ý��� ������ �߻��߽��ϴ�");
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
			System.out.print("�¼��� �������ּ���(2���̻� ���ð���)(��:G��3�� G��4��)(����ȭ��:0)>");
			String selected_seat = sc.nextLine().trim();
			if(selected_seat.equals("0")) return;
			
			StringTokenizer st1 = new StringTokenizer(selected_seat, " ");
			Pattern pattern = Pattern.compile("([A-"+row_name[max_row-1]+(("a-"+row_name[max_row-1]).toLowerCase())+"]��)([1-"+max_number+"]��)");
			
			while(st1.hasMoreTokens()) {
				Matcher matcher = pattern.matcher(st1.nextToken());
				if(!matcher.matches()) {
					System.out.println("�ùٸ� �������� �Է����ּ���");
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
				System.out.println("�ý��� ������ �߻��߽��ϴ�");
				return;
			}
			
			Reservation cur_reserve = dao.currentReservation();
			if(cur_reserve == null) {
				System.out.println("�ý��� ������ �߻��߽��ϴ�.");
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
			System.out.println("Ƽ�� ��¿� ������ ������ϴ�");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("���� ��ȸ���� Ȯ�����ּ���");
			return;
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("==============��ȭ �����===============");
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
				System.out.print(ticket.get(i).getAuditorium_id()+"��"+"\t");
				System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"��");
				System.out.print(ticket.get(i).getSeat_number()+"��");
				System.out.println("("+ticket.get(i).getAudience_cnt()+"��)");
				System.out.println("==================================");
				System.out.println("-��ȯ �� ȯ���� ǥ��ð� 20�� ������ �����մϴ�");
				System.out.println("-�������忡 ���� ���������� �ּ�ȭ�ϰ��� ��ȭ�� �� 10���Ŀ� ���۵˴ϴ�");
				System.out.println("                       SCIT��ȭ��                               ");
				System.out.println("==============��ȭ �����===============");
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
					System.out.print(ticket.get(i).getAuditorium_id()+"��"+"\t");
					System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"��");
					System.out.print(ticket.get(i).getSeat_number()+"��"+" ");
				} else if(i==ticket.size()-1) {
					System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"��");
					System.out.print(ticket.get(i).getSeat_number()+"��");
					System.out.println("("+ticket.get(i).getAudience_cnt()+"��)");
					System.out.println("==================================");
					System.out.println("-��ȯ �� ȯ���� ǥ��ð� 20�� ������ �����մϴ�");
					System.out.println("-�������忡 ���� ���������� �ּ�ȭ�ϰ��� ��ȭ�� �� 10���Ŀ� ���۵˴ϴ�");
					System.out.println("                       SCIT��ȭ��                               ");
					System.out.println("==============��ȭ �����===============");
				} else {
					System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"��");
					System.out.print(ticket.get(i).getSeat_number()+"��"+" ");
				}
			}
			
		}
		
		System.out.println("��ȭ���� �Ϸ�");
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
			System.out.println("��ȸ������ �����ϴ�");
			return;
		}
		System.out.println("SCIT��ȭ������ ������ ��ȭ�Դϴ�");
		System.out.println("================================");
		for(Movie movie : movieList) {
			System.out.println("����:"+movie.getMovie_title());
			System.out.println("����:"+movie.getMovie_director());
			System.out.println("�ֿ����:"+movie.getMovie_actor());
			System.out.println("�󿵽ð�:"+movie.getMovie_duration());
			System.out.println("�������:"+movie.getMovie_rating());
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
		System.out.println("���Ͻô� �޴��� �������ּ���");
		System.out.println("1.�󿵿�ȭ����");
		System.out.println("2.��ȭ����");
		System.out.println("3.��ȭ��ȯ");
		System.out.println("4.��ȭȯ��");
		System.out.println("5.������ȸ");
		System.out.println("6.�α���");
		System.out.println("7.ȸ������");
		System.out.println("8.����");
		System.out.print(">");
	}
}
