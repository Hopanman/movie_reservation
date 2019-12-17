package mreservation.ui;

import java.util.*;
import mreservation.dao.MReservationDAO;
import mreservation.vo.Movie;
import mreservation.vo.Screening;

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
		System.out.println();
		ArrayList<Screening> screeningList = dao.screeningList();
		
		if(screeningList == null || screeningList.size() == 0) {
			System.out.println("���� ���Ÿ� �� ���� �����ϴ�.");
			System.out.println();
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
		System.out.print("�����Ͻ� ��ȭ�� �Է����ּ���>");
		
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
			System.out.println();
			return;
		}
		
		for(Movie movie : movieList) {
			System.out.println("SCIT��ȭ������ ������ ��ȭ�Դϴ�");
			System.out.println("================================");
			System.out.println("����:"+movie.getMovie_title());
			System.out.println("����:"+movie.getMovie_director());
			System.out.println("�ֿ����:"+movie.getMovie_actor());
			System.out.println("�󿵽ð�:"+movie.getMovie_duration());
			System.out.println("�������:"+movie.getMovie_rating());
		}
	}

	void printMainMenu() {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
