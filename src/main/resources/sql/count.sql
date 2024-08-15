show tables;

SELECT * FROM alternative_publisher_name;
SELECT COUNT(*) FROM alternative_publisher_name;

SELECT * FROM alternative_title;
SELECT COUNT(*) FROM alternative_title;

SELECT * FROM anime_adaptation;
SELECT COUNT(*) FROM anime_adaptation;

SELECT * FROM genre;
SELECT COUNT(*) FROM genre;

SELECT * FROM publication;
SELECT COUNT(*) FROM publication;
desc publication;

SELECT * FROM publication_genre;
SELECT COUNT(*) FROM publication_genre;

SELECT * FROM publication_publisher;
SELECT COUNT(*) FROM publication_publisher;

SELECT * FROM publisher;
SELECT COUNT(*) FROM publisher;

SELECT * FROM related_series;
SELECT COUNT(*) FROM related_series;

SELECT * FROM series_type;
SELECT COUNT(*) FROM series_type;

DESC alternative_publisher_name;
DESC alternative_title;
DESC anime_adaptation;
DESC genre;
DESC publication;
DESC publication_genre;
DESC publication_publisher;
DESC publisher;
DESC related_series;
DESC series_type;

-- BELOW IS TEST
select * from publication
order by ranked desc; 

show tables;
desc upvoted_publication;
