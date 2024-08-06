delete from publisher;
delete from alternative_publisher_name;
ALTER TABLE `publisher` AUTO_INCREMENT = 1;
ALTER TABLE `alternative_publisher_name` AUTO_INCREMENT = 1;
select * from publisher;
select * from alternative_publisher_name;
select * from publisher p
	join alternative_publisher_name alt_p
		on p.publisher_id = original_publisher_id;
        
        
desc publication;
desc publisher;
desc alternative_title;
desc anime_adaptation;
desc publication_publisher;
desc publication_genre;
desc related_series;



use anidb;
select * from publication;
desc publication;

delete from publication;
ALTER TABLE `publication` AUTO_INCREMENT = 1;

select * from publication;

