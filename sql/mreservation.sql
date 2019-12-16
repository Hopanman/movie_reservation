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
movie_rating VARCHAR2(30) NOT NULL CHECK(movie_rating in('전체관람가','12세이상관람가','15세이상관람가','청소년관람불가')), 
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

--상영관 데이터 삽입
insert into auditorium values(auditorium_seq.nextval, '1관', 30);
insert into auditorium values(auditorium_seq.nextval, '2관', 42);
insert into auditorium values(auditorium_seq.nextval, '3관', 56);
commit;

--영화 데이터 삽입
insert into movie values(movie_seq.nextval, '겨울왕국 2', 'Frozen 2','크리스 벅, 제니퍼 리','크리스틴 벨, 이디나 멘젤',103,'전체관람가');
insert into movie values(movie_seq.nextval, '쥬만지: 넥스트 레벨', 'Jumanji : The Next Level','제이크 캐스단','드웨인 존슨, 잭 블랙',123,'12세이상관람가');
insert into movie values(movie_seq.nextval, '포드 V 페라리', 'FORD v FERRARI','제임스 맨골드','맷 데이먼, 크리스찬 베일',152,'12세이상관람가');
insert into movie values(movie_seq.nextval, '나이브스 아웃', 'KNIVES OUT','라이언 존슨','다니엘 크레이그, 크리스 에반스',130,'12세이상관람가');
commit;

--좌석 데이터 삽입
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
