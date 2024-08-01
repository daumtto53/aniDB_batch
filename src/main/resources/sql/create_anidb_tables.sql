use anidb;

show tables;

create table genre(
	genre_id	INT 	auto_increment,
    genre_name	VARCHAR(256) UNIQUE,
    genre_description	VARCHAR(256), 
    PRIMARY KEY(genre_id)
);

select * from genre;