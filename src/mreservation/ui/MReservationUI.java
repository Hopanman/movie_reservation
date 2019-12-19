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
		
		System.out.println("�ȳ��ϼ��� SCIT��ȭ�� �����Դϴ�");
		System.out.println("�� ������ ȸ������ �Ǿ��ֽ��ϴ�");
		
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
				System.out.println("�̿����ּż� �����մϴ�");
				System.exit(0);
			default:
				System.out.println("1���� 7������ ������ �Է����ּ���");
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
		System.out.println("============ȸ������===============");
		System.out.println("���̵�:"+user.getUser_name());
		System.out.println("��й�ȣ:"+user.getUser_password().replaceAll("[A-Za-z0-9]", "*"));
		System.out.println("�ڵ�����ȣ:"+user.getUser_phone());
		System.out.println("�̸����ּ�:"+user.getUser_email());
		System.out.println("�ܿ�����Ʈ:"+user.getUser_point()+"��");
		System.out.println("============ȸ������===============");
		System.out.println();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ȸ������������ ���� ������ ��й�ȣ/�ڵ�����ȣ/�̸����ּҸ� �Է����ּ���(����ȭ��:0)");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("��й�ȣ�� 8~20�ڸ��� ���� ��ҹ���,���ڸ� ����ϼ���");
		System.out.println("�������� �ʴ� �׸��� ���͸� �����ּ���");
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
			System.out.print("��й�ȣ>");
			user_password = sc.nextLine().trim();
			
			if(user_password.equals("0")) return;
			if(user_password.equals("")) break;
			
			Matcher matcher = pattern.matcher(user_password);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("��й�ȣ�� 8~20�ڸ��� ���� ��ҹ���,���ڷ� �����Ǿ�� �մϴ�.");
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
			System.out.print("��й�ȣ Ȯ��>");
			user_password_confirm = sc.nextLine().trim();
			
			if(user_password_confirm.equals("0")) return;
			
			if(!user_password_confirm.equals(user_password)) {
				System.out.println();
				System.out.println("�Է��Ͻ� �� ��й�ȣ�� ��ġ���� �ʽ��ϴ�");
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
			System.out.print("�ڵ�����ȣ>");
			user_phone = sc.nextLine().trim();
			
			if(user_phone.equals("0")) return;
			if(user_phone.equals("")) break;
			
			Matcher matcher = pattern.matcher(user_phone);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("�ùٸ� �������� �Է����ּ���");
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
			System.out.print("�̸����ּ�>");
			user_email = sc.nextLine().trim();
			
			if(user_email.equals("0")) return;
			if(user_email.equals("")) break;
			
			Matcher matcher = pattern.matcher(user_email);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("�ùٸ� �������� �Է����ּ���");
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
			System.out.println("ȸ������ ���濡 �����߽��ϴ�");
		} else {
			System.out.println();
			System.out.println("�ý��� ������ �߻��߽��ϴ�");
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
			System.out.println("�α����� ���� ���̵�� ��й�ȣ�� �Է¹ٶ��ϴ�(ȸ������:1)(����:0)");
			System.out.print("���̵�>");
			String user_name = sc.nextLine();
			if(user_name.equals("0")) {
				System.out.println();
				System.out.println("�̿����ּż� �����մϴ�");
				System.exit(0);
			} else if(user_name.equals("1")) {
				enrollUser();
				continue;
			}
			
			System.out.print("��й�ȣ>");
			String user_password = sc.nextLine();
			if(user_password.equals("0")) {
				System.out.println();
				System.out.println("�̿����ּż� �����մϴ�");
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
				System.out.println("�ý��� ������ �߻��߽��ϴ�.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(userList.size() == 0) {
				System.out.println();
				System.out.println("�������� ���� ���̵��̰ų�, �߸��� ��й�ȣ�Դϴ�.");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				user = userList.get(0);
				System.out.println();
				System.out.println("�α��εǾ����ϴ�.");
				break;
			}
		}

	}

	void enrollUser() {
		System.out.println();
		System.out.println("ȸ�������� ���� ���̵�/��й�ȣ/�ڵ�����ȣ/�̸����ּҸ� �Է����ּ���");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���̵�� 5~20�ڸ��� ���� �ҹ���,����/��й�ȣ�� 8~20�ڸ��� ���� ��ҹ���,���ڸ� ����ϼ���(�α���ȭ��:0)");
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
			System.out.print("���̵�>");
			user_name = sc.nextLine().trim();
			
			if(user_name.equals("0")) return;
			
			Matcher matcher = pattern.matcher(user_name);
			
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("���̵�� 5~20�ڸ��� ���� �ҹ���,���ڷ� �����Ǿ�� �մϴ�.");
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
					System.out.println("�ý��� ������ �߻��߽��ϴ�.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println();
				} else if(user.size() == 1) {
					System.out.println();
					System.out.println("����� �� ���� ID�Դϴ�");
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
			System.out.print("��й�ȣ>");
			user_password = sc.nextLine().trim();
			
			if(user_password.equals("0")) return;
			
			Matcher matcher = pattern.matcher(user_password);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("��й�ȣ�� 8~20�ڸ��� ���� ��ҹ���,���ڷ� �����Ǿ�� �մϴ�.");
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
			System.out.print("�ڵ�����ȣ>");
			user_phone = sc.nextLine().trim();
			
			if(user_phone.equals("0")) return;
			
			Matcher matcher = pattern.matcher(user_phone);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("�ùٸ� �������� �Է����ּ���");
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
			System.out.print("�̸����ּ�>");
			user_email = sc.nextLine().trim();
			
			if(user_email.equals("0")) return;
			
			Matcher matcher = pattern.matcher(user_email);
			if(!matcher.matches()) {
				System.out.println();
				System.out.println("�ùٸ� �������� �Է����ּ���");
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
		
		if(res) System.out.println("�����ϵǾ����ϴ�");
		else System.out.println("�ý��� ������ �߻��߽��ϴ�");
	}

	void refundReservation() {
		sc.nextLine();
		System.out.println();
		while(true) {
			System.out.println("ȯ���Ͻ� Ƽ���� ���Ź�ȣ�� �Է����ּ���(����ȭ��:0)");
			System.out.println("**ȯ���� �󿵽��۽ð� 20�� ������ �����մϴ�**");
			System.out.print(">");
			int reservation_id;
			try {
				reservation_id = sc.nextInt();
				if(reservation_id < 0) throw new Exception();
			} catch (Exception e) {
				System.out.println();
				sc.nextLine();
				System.out.println("���Ź�ȣ�� �ùٸ��� �Է����ֽʽÿ�");
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
				System.out.println("ȯ�ҵǼ̽��ϴ�");
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
		HashMap<String, Object> movie_data;
		int user_id = user.getUser_id();
		while(true) {
			System.out.println("�����Ͻ� ��ȭ����� �ð��� �Է����ּ���(����ȭ��:0)");
			System.out.print("��ȭ����>");
			String movie_title = sc.nextLine().trim();
			if(movie_title.equals("0")) return;
			System.out.print("�󿵽��۽ð�(��-�� ��:��)>");
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
			
			if(!st2.hasMoreTokens()) break;
		}
		
		int pre_user_point = user.getUser_point();
		user.setUser_point(pre_user_point+90*numberList.size());
		boolean pointRes = dao.updateUserInfo(user);
		
		if(!pointRes) user.setUser_point(pre_user_point);
		
		ArrayList<Ticket> ticket = dao.getTicket(reservation_id);
		if(ticket == null || ticket.size() == 0) {
			System.out.println("Ƽ�� ��¿� ������ ������ϴ�");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
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
				System.out.println("���Ź�ȣ "+ticket.get(i).getReservation_id()+"��");
				System.out.println(ticket.get(i).getMovie_title());
				System.out.println(ticket.get(i).getMovie_title_en());
				System.out.println(ticket.get(i).getMovie_rating());
				System.out.println("======================================");
				System.out.print(ticket.get(i).getScreening_start().substring(0, 10)+"\t");
				System.out.print(ticket.get(i).getScreening_cnt()+"\t");
				System.out.print(ticket.get(i).getScreening_start().substring(11, 16)+"~");
				System.out.println(ticket.get(i).getScreening_end().substring(11, 16));
				System.out.print(ticket.get(i).getAuditorium_id()+"��"+"\t");
				System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"��");
				System.out.print(ticket.get(i).getSeat_number()+"��");
				System.out.println("("+ticket.get(i).getAudience_cnt()+"��)");
				System.out.println("======================================");
				System.out.print(user.getUser_name()+"��"+"\t");
				System.out.println("�ܿ�����Ʈ: "+(pointRes ? user.getUser_point()+"��": "����Ʈ ������ ������ �߻��߽��ϴ� �����ڿ��� �������ּ���"));
				System.out.println("======================================");
				System.out.println("-ȯ���� ǥ��ð� 20�� ������ �����մϴ�");
				System.out.println("-�������忡 ���� ���������� �ּ�ȭ�ϰ��� ��ȭ�� �� 10�� �Ŀ� ���۵˴ϴ�");
				System.out.println("              SCIT��ȭ��                               ");
				System.out.println("==============��ȭ �����===============");
			} else {
				if(i==0) {
					System.out.println("���Ź�ȣ "+ticket.get(i).getReservation_id()+"��");
					System.out.println(ticket.get(i).getMovie_title());
					System.out.println(ticket.get(i).getMovie_title_en());
					System.out.println(ticket.get(i).getMovie_rating());
					System.out.println("======================================");
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
					System.out.println("======================================");
					System.out.print(user.getUser_name()+"��"+"\t");
					System.out.println("�ܿ�����Ʈ: "+(pointRes ? user.getUser_point()+"��": "����Ʈ ������ ������ �߻��߽��ϴ� �����ڿ��� �������ּ���"));
					System.out.println("======================================");
					System.out.println("-ȯ���� ǥ��ð� 20�� ������ �����մϴ�");
					System.out.println("-�������忡 ���� ���������� �ּ�ȭ�ϰ��� ��ȭ�� �� 10�� �Ŀ� ���۵˴ϴ�");
					System.out.println("              SCIT��ȭ��                               ");
					System.out.println("==============��ȭ �����===============");
				} else {
					System.out.print(row_name[ticket.get(i).getSeat_row()-1]+"��");
					System.out.print(ticket.get(i).getSeat_number()+"��"+" ");
				}
			}
			
		}
		
		System.out.println("��ſ� ���� �ǽʽÿ�");
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
		System.out.println();
		System.out.println("����ȭ������ ���ư��÷��� �ƹ� Ű�� �Է����ּ���");
		sc.nextLine();
	}

	void printMainMenu() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println("ȯ���մϴ� "+user.getUser_name()+"��");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("���Ͻô� �޴��� �������ּ���");
		System.out.println("1.�󿵿�ȭ����");
		System.out.println("2.��ȭ����");
		System.out.println("3.��ȭȯ��");
		System.out.println("4.������ȸ");
		System.out.println("5.ȸ����������");
		System.out.println("6.�α׾ƿ�");
		System.out.println("7.����");
		System.out.print(">");
	}
}
