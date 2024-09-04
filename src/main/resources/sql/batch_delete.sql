select * from genre;
select * from series_type;
select * from publication;
select * from publisher;
show tables;

select * from related_series;

select * from publication;
delete from publication;

-- Delete from child tables first
DELETE FROM publication_genre WHERE publication_id IN (SELECT publication_id FROM publication);
DELETE FROM publication_publisher WHERE publication_id IN (SELECT publication_id FROM publication);
DELETE FROM related_series WHERE publication_id IN (SELECT publication_id FROM publication);
DELETE FROM alternative_title WHERE publication_id IN (SELECT publication_id FROM publication);
DELETE FROM anime_adaptation WHERE publication_id IN (SELECT publication_id FROM publication);
delete from publication_genre;
-- Delete from the parent table last
DELETE FROM publication; 

delete from series_type;
delete from genre;

select * from series_type;
delete from series_type;
ALTER TABLE `series_type` AUTO_INCREMENT = 1;

select * from alternative_publisher_name;
delete from alternative_publisher_name;
delete from publication_publisher;
update publisher set parent_publisher_id = null;
delete from publisher;

ALTER TABLE `publication_genre` AUTO_INCREMENT = 1;
ALTER TABLE `publication_publisher` AUTO_INCREMENT = 1;
ALTER TABLE `related_series` AUTO_INCREMENT = 1;
ALTER TABLE `alternative_title` AUTO_INCREMENT = 1;
ALTER TABLE `anime_adaptation` AUTO_INCREMENT = 1;
ALTER TABLE `publication` AUTO_INCREMENT = 1;
ALTER TABLE `series_type` AUTO_INCREMENT = 1;
ALTER TABLE `genre` AUTO_INCREMENT = 1;
ALTER TABLE `alternative_publisher_name` AUTO_INCREMENT = 1;
ALTER TABLE `publisher` AUTO_INCREMENT = 1;


desc alternative_publisher_name;