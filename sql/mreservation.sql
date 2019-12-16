CREATE TABLE auditorium(
auditorium_id NUMBER NOT NULL, 
auditorium_name VARCHAR2(20) NOT NULL, 
seats_no NUMBER NOT NULL, 
CONSTRAINT AUDITORIUM_PK PRIMARY KEY(auditorium_id)
);

CREATE SEQUENCE auditorium_SEQ
NOCACHE;

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

CREATE SEQUENCE movie_SEQ
NOCACHE;

CREATE TABLE screening(
screening_id NUMBER NOT NULL, 
movie_id NUMBER NOT NULL, 
auditorium_id NUMBER NOT NULL, 
screening_start DATE NOT NULL, 
screening_end DATE NOT NULL, 
CONSTRAINT SCREENING_PK PRIMARY KEY(screening_id),
CONSTRAINT SCREENING_UK_1 UNIQUE(auditorium_id, screening_start),
CONSTRAINT SCREENING_UK_2 UNIQUE(auditorium_id, screening_end),
CONSTRAINT SCREENING_FK_1 FOREIGN KEY(movie_id) REFERENCES movie(movie_id),
CONSTRAINT SCREENING_FK_2 FOREIGN KEY(auditorium_id) REFERENCES auditorium(auditorium_id)
);


CREATE SEQUENCE screening_SEQ
NOCACHE;


CREATE TABLE theater_user(
user_id NUMBER NOT NULL, 
user_name VARCHAR2(20) NOT NULL, 
user_password VARCHAR2(20) NOT NULL, 
user_phone VARCHAR2(14) NOT NULL, 
user_email VARCHAR2(30), 
user_point NUMBER DEFAULT 0 NOT NULL, 
CONSTRAINT USER_PK PRIMARY KEY (user_id)
);


CREATE SEQUENCE user_SEQ
NOCACHE;


CREATE TABLE seat(
seat_id NUMBER NOT NULL, 
seat_row NUMBER NOT NULL, 
seat_number NUMBER NOT NULL, 
auditorium_id NUMBER NOT NULL, 
CONSTRAINT SEAT_PK PRIMARY KEY(seat_id),
CONSTRAINT SEAT_FK FOREIGN KEY(auditorium_id) REFERENCES auditorium(auditorium_id)
);

CREATE SEQUENCE seat_SEQ
NOCACHE;


CREATE TABLE reservation(
reservation_id NUMBER NOT NULL, 
user_id NUMBER, 
screening_id NUMBER NOT NULL, 
CONSTRAINT RESERVATION_PK PRIMARY KEY(reservation_id),
CONSTRAINT RESERVATION_FK_1 FOREIGN KEY (screening_id) REFERENCES screening (screening_id),
CONSTRAINT RESERVATION_FK_2 FOREIGN KEY(user_id) REFERENCES theater_user(user_id)
);

CREATE SEQUENCE reservation_SEQ
NOCACHE;


CREATE TABLE seat_reserved(
seat_reserved_id NUMBER NOT NULL, 
seat_id NUMBER NOT NULL, 
reservation_id NUMBER NOT NULL, 
screening_id NUMBER NOT NULL, 
CONSTRAINT SEAT_RESERVED_PK PRIMARY KEY(seat_reserved_id),
CONSTRAINT SEAT_RESERVED_FK_1 FOREIGN KEY(seat_id) REFERENCES seat(seat_id),
CONSTRAINT SEAT_RESERVED_FK_2 FOREIGN KEY(screening_id) REFERENCES screening(screening_id),
CONSTRAINT SEAT_RESERVED_FK_3 FOREIGN KEY(reservation_id) REFERENCES reservation(reservation_id)
);


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
