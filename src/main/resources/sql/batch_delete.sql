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

delete from series_type;
ALTER TABLE `series_type` AUTO_INCREMENT = 1;

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

SELECT
            (COUNT(distinct p.publication_id))
        FROM publication p
        LEFT JOIN series_type as st ON st.type_id = p.series_type
        LEFT JOIN publication_genre pg on pg.publication_id=p.publication_id
        LEFT JOIN genre g on g.genre_id = pg.genre_id
        LEFT JOIN publication_publisher pp on pp.publication_id = p.publication_id
        LEFT JOIN publisher pu on pp.publisher_id = pu.publisher_id
         WHERE 1 = 1
                AND st.type_name LIKE CONCAT ('%', 'Manga', '%') ;