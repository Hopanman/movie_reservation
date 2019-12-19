--�󿵰� ���̺� ����
drop table auditorium cascade constraints;
CREATE TABLE auditorium(
auditorium_id NUMBER NOT NULL, 
auditorium_name VARCHAR2(20) NOT NULL, 
seats_no NUMBER NOT NULL, 
CONSTRAINT AUDITORIUM_PK PRIMARY KEY(auditorium_id)
);

--�󿵰� ������ ����
drop sequence auditorium_SEQ;
CREATE SEQUENCE auditorium_SEQ
NOCACHE;

--��ȭ ���̺� ����
drop table movie cascade constraints;
CREATE TABLE movie(
movie_id NUMBER NOT NULL, 
movie_title VARCHAR2(100) NOT NULL, 
movie_title_en VARCHAR2(50), 
movie_director VARCHAR2(100), 
movie_actor VARCHAR2(200), 
movie_duration NUMBER NOT NULL, 
movie_rating VARCHAR2(30) NOT NULL CHECK(movie_rating in('��ü������','12���̻������','15���̻������','û�ҳ�����Ұ�')), 
CONSTRAINT MOVIE_PK PRIMARY KEY (movie_id)
);

--��ȭ ������ ����
drop sequence movie_SEQ;
CREATE SEQUENCE movie_SEQ
NOCACHE;

--�� ���̺� ����
drop table screening cascade constraints;
CREATE TABLE screening(
screening_id NUMBER NOT NULL, 
movie_id NUMBER NOT NULL, 
auditorium_id NUMBER NOT NULL, 
screening_start DATE NOT NULL, 
screening_end DATE NOT NULL, 
screening_cnt VARCHAR2(5) NOT NULL,
CONSTRAINT SCREENING_PK PRIMARY KEY(screening_id),
CONSTRAINT SCREENING_UK_1 UNIQUE(auditorium_id, screening_start),
CONSTRAINT SCREENING_UK_2 UNIQUE(auditorium_id, screening_end),
CONSTRAINT SCREENING_FK_1 FOREIGN KEY(movie_id) REFERENCES movie(movie_id),
CONSTRAINT SCREENING_FK_2 FOREIGN KEY(auditorium_id) REFERENCES auditorium(auditorium_id)
);

--�� ������ ����
drop sequence screening_SEQ;
CREATE SEQUENCE screening_SEQ
NOCACHE;


--ȸ�� ���̺� ����
drop table theater_user cascade constraints;
CREATE TABLE theater_user(
user_id NUMBER NOT NULL, 
user_name VARCHAR2(20) NOT NULL, 
user_password VARCHAR2(20) NOT NULL, 
user_phone VARCHAR2(14) NOT NULL, 
user_email VARCHAR2(30), 
user_point NUMBER DEFAULT 0 NOT NULL, 
CONSTRAINT USER_PK PRIMARY KEY (user_id),
constraint user_uk unique(user_name)
);

--ȸ�� ������ ����
drop sequence user_SEQ;
CREATE SEQUENCE user_SEQ
NOCACHE;

--�¼� ���̺� ����
drop table seat cascade constraints;
CREATE TABLE seat(
seat_id NUMBER NOT NULL, 
seat_row NUMBER NOT NULL, 
seat_number NUMBER NOT NULL, 
auditorium_id NUMBER NOT NULL, 
CONSTRAINT SEAT_PK PRIMARY KEY(seat_id),
CONSTRAINT SEAT_FK FOREIGN KEY(auditorium_id) REFERENCES auditorium(auditorium_id)
);

--�¼� ������ ����
drop sequence seat_SEQ;
CREATE SEQUENCE seat_SEQ
NOCACHE;

--���� ���̺� ����
drop table reservation cascade constraints;
CREATE TABLE reservation(
reservation_id NUMBER NOT NULL, 
user_id NUMBER, 
screening_id NUMBER NOT NULL,
reservation_time date default sysdate,
CONSTRAINT RESERVATION_PK PRIMARY KEY(reservation_id),
CONSTRAINT RESERVATION_FK_1 FOREIGN KEY (screening_id) REFERENCES screening (screening_id),
CONSTRAINT RESERVATION_FK_2 FOREIGN KEY(user_id) REFERENCES theater_user(user_id) on delete set null
);

--���� ������ ����
drop sequence reservation_SEQ;
CREATE SEQUENCE reservation_SEQ
NOCACHE;

--�����¼� ���̺� ����
drop table seat_reserved cascade constraints;
CREATE TABLE seat_reserved(
seat_reserved_id NUMBER NOT NULL, 
seat_id NUMBER NOT NULL, 
reservation_id NUMBER NOT NULL, 
screening_id NUMBER NOT NULL, 
CONSTRAINT SEAT_RESERVED_PK PRIMARY KEY(seat_reserved_id),
CONSTRAINT SEAT_RESERVED_FK_1 FOREIGN KEY(seat_id) REFERENCES seat(seat_id),
CONSTRAINT SEAT_RESERVED_FK_2 FOREIGN KEY(screening_id) REFERENCES screening(screening_id),
CONSTRAINT SEAT_RESERVED_FK_3 FOREIGN KEY(reservation_id) REFERENCES reservation(reservation_id) on delete cascade,
constraint seat_reserved_UK_1 unique(seat_id, reservation_id),
constraint seat_reserved_UK_2 unique(seat_id, screening_id)
);

--�����¼� ������ ����
drop sequence seat_reserved_SEQ;
CREATE SEQUENCE seat_reserved_SEQ
NOCACHE;

--�󿵰� ������ ����
insert into auditorium values(auditorium_seq.nextval, '1��', 30);
insert into auditorium values(auditorium_seq.nextval, '2��', 42);
insert into auditorium values(auditorium_seq.nextval, '3��', 56);
commit;

--��ȭ ������ ����
insert into movie values(movie_seq.nextval, '�ܿ�ձ� 2', 'Frozen 2','ũ���� ��, ������ ��','ũ����ƾ ��, �̵� ����',103,'��ü������');
insert into movie values(movie_seq.nextval, '�길��: �ؽ�Ʈ ����', 'Jumanji : The Next Level','����ũ ĳ����','����� ����, �� ��',123,'12���̻������');
insert into movie values(movie_seq.nextval, '���� V ���', 'FORD v FERRARI','���ӽ� �ǰ��','�� ���̸�, ũ������ ����',152,'12���̻������');
insert into movie values(movie_seq.nextval, '���̺꽺 �ƿ�', 'KNIVES OUT','���̾� ����','�ٴϿ� ũ���̱�, ũ���� ���ݽ�',130,'12���̻������');
commit;

--�¼� ������ ����
begin
    for i in 5..7 loop
        for j in 1..i loop
            for k in 1..i+1 loop
                insert into seat values(seat_seq.nextval, j, k, i-4);
                commit;
            end loop;
        end loop;
    end loop;
end;
/

--�� ������ ����
alter session set nls_date_format='yy/mm/dd hh24:mi';
insert into screening values(screening_seq.nextval, 1, 3, '19/12/17 08:20', '19/12/17 10:13','1ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/17 08:50', '19/12/17 11:03','1ȸ');
insert into screening values(screening_seq.nextval, 4, 2, '19/12/17 09:00', '19/12/17 11:20','1ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/17 10:35', '19/12/17 12:28','2ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/17 11:25', '19/12/17 13:45','2ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/17 11:42', '19/12/17 13:55','2ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/17 12:50', '19/12/17 14:43','3ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/17 14:07', '19/12/17 16:20','3ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/17 14:17', '19/12/17 16:59','3ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/17 15:05', '19/12/17 16:58','4ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/17 16:42', '19/12/17 19:02','4ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/17 17:20', '19/12/17 19:13','5ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/17 17:21', '19/12/17 19:34','4ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/17 19:24', '19/12/17 21:37','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/17 19:35', '19/12/17 21:28','6ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/17 19:56', '19/12/17 22:38','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/17 21:50', '19/12/17 23:43','7ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/17 21:59', '19/12/18 00:19','6ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/17 23:00', '19/12/18 01:13','6ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/18 00:05', '19/12/18 01:58','8ȸ');

insert into screening values(screening_seq.nextval, 1, 3, '19/12/18 08:20', '19/12/18 10:13','1ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/18 08:50', '19/12/18 11:03','1ȸ');
insert into screening values(screening_seq.nextval, 4, 2, '19/12/18 09:00', '19/12/18 11:20','1ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/18 10:35', '19/12/18 12:28','2ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/18 11:25', '19/12/18 13:45','2ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/18 11:42', '19/12/18 13:55','2ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/18 12:50', '19/12/18 14:43','3ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/18 14:07', '19/12/18 16:20','3ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/18 14:17', '19/12/18 16:59','3ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/18 15:05', '19/12/18 16:58','4ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/18 16:42', '19/12/18 19:02','4ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/18 17:20', '19/12/18 19:13','5ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/18 17:21', '19/12/18 19:34','4ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/18 19:24', '19/12/18 21:37','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/18 19:35', '19/12/18 21:28','6ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/18 19:56', '19/12/18 22:38','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/18 21:50', '19/12/18 23:43','7ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/18 21:59', '19/12/19 00:19','6ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/18 23:00', '19/12/19 01:13','6ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/19 00:05', '19/12/19 01:58','8ȸ');

insert into screening values(screening_seq.nextval, 1, 3, '19/12/19 08:20', '19/12/19 10:13','1ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/19 08:50', '19/12/19 11:03','1ȸ');
insert into screening values(screening_seq.nextval, 4, 2, '19/12/19 09:00', '19/12/19 11:20','1ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/19 10:35', '19/12/19 12:28','2ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/19 11:25', '19/12/19 13:45','2ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/19 11:42', '19/12/19 13:55','2ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/19 12:50', '19/12/19 14:43','3ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/19 14:07', '19/12/19 16:20','3ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/19 14:17', '19/12/19 16:59','3ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/19 15:05', '19/12/19 16:58','4ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/19 16:42', '19/12/19 19:02','4ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/19 17:20', '19/12/19 19:13','5ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/19 17:21', '19/12/19 19:34','4ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/19 19:24', '19/12/19 21:37','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/19 19:35', '19/12/19 21:28','6ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/19 19:56', '19/12/19 22:38','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/19 21:50', '19/12/19 23:43','7ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/19 21:59', '19/12/20 00:19','6ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/19 23:00', '19/12/20 01:13','6ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/20 00:05', '19/12/20 01:58','8ȸ');

insert into screening values(screening_seq.nextval, 1, 3, '19/12/20 08:20', '19/12/20 10:13','1ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/20 08:50', '19/12/20 11:03','1ȸ');
insert into screening values(screening_seq.nextval, 4, 2, '19/12/20 09:00', '19/12/20 11:20','1ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/20 10:35', '19/12/20 12:28','2ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/20 11:25', '19/12/20 13:45','2ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/20 11:42', '19/12/20 13:55','2ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/20 12:50', '19/12/20 14:43','3ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/20 14:07', '19/12/20 16:20','3ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/20 14:17', '19/12/20 16:59','3ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/20 15:05', '19/12/20 16:58','4ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/20 16:42', '19/12/20 19:02','4ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/20 17:20', '19/12/20 19:13','5ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/20 17:21', '19/12/20 19:34','4ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/20 19:24', '19/12/20 21:37','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/20 19:35', '19/12/20 21:28','6ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/20 19:56', '19/12/20 22:38','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/20 21:50', '19/12/20 23:43','7ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/20 21:59', '19/12/21 00:19','6ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/20 23:00', '19/12/21 01:13','6ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/21 00:05', '19/12/21 01:58','8ȸ');

insert into screening values(screening_seq.nextval, 1, 3, '19/12/21 08:20', '19/12/21 10:13','1ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/21 08:50', '19/12/21 11:03','1ȸ');
insert into screening values(screening_seq.nextval, 4, 2, '19/12/21 09:00', '19/12/21 11:20','1ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/21 10:35', '19/12/21 12:28','2ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/21 11:25', '19/12/21 13:45','2ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/21 11:42', '19/12/21 13:55','2ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/21 12:50', '19/12/21 14:43','3ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/21 14:07', '19/12/21 16:20','3ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/21 14:17', '19/12/21 16:59','3ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/21 15:05', '19/12/21 16:58','4ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/21 16:42', '19/12/21 19:02','4ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/21 17:20', '19/12/21 19:13','5ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/21 17:21', '19/12/21 19:34','4ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/21 19:24', '19/12/21 21:37','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/21 19:35', '19/12/21 21:28','6ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/21 19:56', '19/12/21 22:38','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/21 21:50', '19/12/21 23:43','7ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/21 21:59', '19/12/22 00:19','6ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/21 23:00', '19/12/22 01:13','6ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/22 00:05', '19/12/22 01:58','8ȸ');

insert into screening values(screening_seq.nextval, 1, 3, '19/12/22 08:20', '19/12/22 10:13','1ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/22 08:50', '19/12/22 11:03','1ȸ');
insert into screening values(screening_seq.nextval, 4, 2, '19/12/22 09:00', '19/12/22 11:20','1ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/22 10:35', '19/12/22 12:28','2ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/22 11:25', '19/12/22 13:45','2ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/22 11:42', '19/12/22 13:55','2ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/22 12:50', '19/12/22 14:43','3ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/22 14:07', '19/12/22 16:20','3ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/22 14:17', '19/12/22 16:59','3ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/22 15:05', '19/12/22 16:58','4ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/22 16:42', '19/12/22 19:02','4ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/22 17:20', '19/12/22 19:13','5ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/22 17:21', '19/12/22 19:34','4ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/22 19:24', '19/12/22 21:37','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/22 19:35', '19/12/22 21:28','6ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/22 19:56', '19/12/22 22:38','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/22 21:50', '19/12/22 23:43','7ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/22 21:59', '19/12/23 00:19','6ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/22 23:00', '19/12/23 01:13','6ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/23 00:05', '19/12/23 01:58','8ȸ');

insert into screening values(screening_seq.nextval, 1, 3, '19/12/23 08:20', '19/12/23 10:13','1ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/23 08:50', '19/12/23 11:03','1ȸ');
insert into screening values(screening_seq.nextval, 4, 2, '19/12/23 09:00', '19/12/23 11:20','1ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/23 10:35', '19/12/23 12:28','2ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/23 11:25', '19/12/23 13:45','2ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/23 11:42', '19/12/23 13:55','2ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/23 12:50', '19/12/23 14:43','3ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/23 14:07', '19/12/23 16:20','3ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/23 14:17', '19/12/23 16:59','3ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/23 15:05', '19/12/23 16:58','4ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/23 16:42', '19/12/23 19:02','4ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/23 17:20', '19/12/23 19:13','5ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/23 17:21', '19/12/23 19:34','4ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/23 19:24', '19/12/23 21:37','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/23 19:35', '19/12/23 21:28','6ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/23 19:56', '19/12/23 22:38','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/23 21:50', '19/12/23 23:43','7ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/23 21:59', '19/12/24 00:19','6ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/23 23:00', '19/12/24 01:13','6ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/24 00:05', '19/12/24 01:58','8ȸ');

insert into screening values(screening_seq.nextval, 1, 3, '19/12/24 08:20', '19/12/24 10:13','1ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/24 08:50', '19/12/24 11:03','1ȸ');
insert into screening values(screening_seq.nextval, 4, 2, '19/12/24 09:00', '19/12/24 11:20','1ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/24 10:35', '19/12/24 12:28','2ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/24 11:25', '19/12/24 13:45','2ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/24 11:42', '19/12/24 13:55','2ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/24 12:50', '19/12/24 14:43','3ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/24 14:07', '19/12/24 16:20','3ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/24 14:17', '19/12/24 16:59','3ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/24 15:05', '19/12/24 16:58','4ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/24 16:42', '19/12/24 19:02','4ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/24 17:20', '19/12/24 19:13','5ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/24 17:21', '19/12/24 19:34','4ȸ');
insert into screening values(screening_seq.nextval, 2, 1, '19/12/24 19:24', '19/12/24 21:37','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/24 19:35', '19/12/24 21:28','6ȸ');
insert into screening values(screening_seq.nextval, 3, 2, '19/12/24 19:56', '19/12/24 22:38','5ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/24 21:50', '19/12/24 23:43','7ȸ');
insert into screening values(screening_seq.nextval, 4, 1, '19/12/24 21:59', '19/12/25 00:19','6ȸ');
insert into screening values(screening_seq.nextval, 2, 2, '19/12/24 23:00', '19/12/25 01:13','6ȸ');
insert into screening values(screening_seq.nextval, 1, 3, '19/12/25 00:05', '19/12/25 01:58','8ȸ');
commit;