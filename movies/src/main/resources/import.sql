-- SQL Server specifics: Note remove 'set identity_insert' before running locally using H2

set identity_insert directors on;
-- directors
insert into directors( director, firstname, lastname ) values (1, 'Jon' ,'Favreau');
insert into directors( director, firstname, lastname ) values (2, 'Louis' ,'Leterrier');
insert into directors( director, firstname, lastname ) values (3, 'Kenneth' ,'Branagh');
insert into directors( director, firstname, lastname ) values (4, 'Joe' ,'Johnston');
set identity_insert directors off;

set identity_insert ratings on;
-- ratings
insert into ratings( rating, ratingcode, ratingvalue, description ) values (1, 'Uc', 'Universal Children','Titles rated bbfc-uc have been classified suitable for all.');
insert into ratings( rating, ratingcode, ratingvalue, description ) values (2, 'U', 'Universal','Titles rated bbfc-u have been classified suitable for all.');
insert into ratings( rating, ratingcode, ratingvalue, description ) values (3, 'PG', 'Parental Guidance','Titles rated bbfc-pg are for general viewing, but some scenes may be unsuitable for young children.');
insert into ratings( rating, ratingcode, ratingvalue, description ) values (4, '12A', '12 Accompanied/Advisory','Titles rated bbfc-12a are suitable for 12 years and over. No-one younger than 12 may see a 12A film in a cinema unless accompanied by an adult.');
insert into ratings( rating, ratingcode, ratingvalue, description ) values (5, '12', '12','Titles rated bbfc-12 are for 12 and over. No-one younger than 12 may rent or buy a 12 rated video or DVD. Responsibility for allowing under-12s to view lies with the accompanying or supervising adult.');
insert into ratings( rating, ratingcode, ratingvalue, description ) values (6, '15', '15','Titles rated bbfc-15 are suitable only for 15 years and over. No-one younger than 15 may see a 15 film in a cinema. No-one younger than 15 may rent or buy a 15 rated video or DVD.');
insert into ratings( rating, ratingcode, ratingvalue, description ) values (7, '18', '18','TTitles rated bbfc-18 are suitable only for adults. No-one younger than 18 may see an 18 film in a cinema. No-one younger than 18 may rent or buy an 18 rated video.');
insert into ratings( rating, ratingcode, ratingvalue, description ) values (8, 'R18', 'Restricted 18','Titles rated bbfc-r18 are to be shown only in specially licensed cinemas, or supplied only in licensed sex shops, and to adults of not less than 18 years.');
insert into ratings( rating, ratingcode, ratingvalue, description ) values (9, 'TBC', 'To Be Confirmed','Titles carrying the bbfc-tbc marker have been submitted to the BBFC and are awaiting final rating.');
set identity_insert ratings off;

set identity_insert movies on;
-- movies
insert into movies(movie, rating, director, name, description) values (1, 5, 1, 'Iron Man', 'Chap in an iron suit');
insert into movies(movie, rating, director, name, description) values (2, 5, 2, 'The Incredible Hulk', 'Angry green chap');
insert into movies(movie, rating, director, name, description) values (3, 5, 1, 'Iron Man 2', 'Same chap, better suit');
insert into movies(movie, rating, director, name, description) values (4, 5, 3, 'Thor', 'Demigod');
insert into movies(movie, rating, director, name, description) values (5, 5, 4, 'Captain America: The First Avenger', 'Chemically enhanced soldier chap');
set identity_insert movies off;

