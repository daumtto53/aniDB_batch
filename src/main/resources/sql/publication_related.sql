DROP TABLE publication;
DROP TABLE alternative_title;
DROP TABLE related_series;
DROP TABLE published_status;
DROP TABLE publication_publisher;
DROP TABLE publication_artist;
DROP TABLE publication_genre;
DROP TABLE anime_adaptation;
DROP TABLE publication_adaptation;
DROP TABLE anidb_article;
-- anidb_article FOREIGN KEY -> 
DROP TABLE recommend_publication;
DROP TABLE upvoted_publication;


-- make series_type before publication
CREATE TABLE publication (
    publication_id INT PRIMARY KEY  AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(2048),
    series_type INT NOT NULL,
    volumes_in_origin_country INT,
    status_in_origin_country VARCHAR(50),
    published_date DATETIME,
    licensed BOOLEAN DEFAULT FALSE,
    ranked INT,
    scores FLOAT DEFAULT 0.0,
    cover_image_url VARCHAR(2048),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
    FOREIGN KEY (series_type) REFERENCES series_type(type_id)
);



-- create publication before ""
CREATE TABLE alternative_title (
    alternative_title_id INT AUTO_INCREMENT,
    publication_id INT NOT NULL,
    alternative_title VARCHAR(255) NOT NULL,
    title_language varchar(255),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
    PRIMARY KEY (alternative_title_id, publication_id),
    FOREIGN KEY (publication_id) REFERENCES publication(publication_id)
);


-- create publication before ""
CREATE TABLE related_series(
    related_publication_id INT AUTO_INCREMENT,
    publication_id INT NOT NULL,
    relation VARCHAR(32),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
    PRIMARY KEY (related_publication_id, publication_id),
    FOREIGN KEY (publication_id) REFERENCES publication(publication_id)
);



-- confirmed.
-- create publication before published_status
CREATE TABLE published_status (
    publication_id INT,
    volume_number INT ,
    publication_date DATETIME,
    published_country VARCHAR(256),
    cover_image_url VARCHAR(256),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
    PRIMARY KEY (publication_id, volume_number),
    FOREIGN KEY (publication_id) REFERENCES publication(publication_id)
);



-- CONFIRMED
CREATE TABLE publication_publisher (
  publication_id INT NOT NULL, 
  publisher_id INT NOT NULL,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (publication_id, publisher_id),
  FOREIGN KEY (publication_id) REFERENCES publication(publication_id),
  FOREIGN KEY (publisher_id) REFERENCES publisher(publisher_id)
);

-- CONFIRMED
CREATE TABLE publication_artist (
  publication_id INT NOT NULL,
  artist_id INT NOT NULL,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (publication_id, artist_id),
  FOREIGN KEY (publication_id) REFERENCES publication(publication_id),
  FOREIGN KEY (artist_id) REFERENCES artist(artist_id)
);

-- CONFIRMED
CREATE TABLE publication_genre (
  publication_id INT NOT NULL,
  genre_id INT NOT NULL,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (publication_id, genre_id),
  FOREIGN KEY (publication_id) REFERENCES publication(publication_id),
  FOREIGN KEY (genre_id) REFERENCES genre(genre_id)
);


-- CONFIRMED
CREATE TABLE anime_adaptation (
  anime_adaptation_id INT auto_increment,
  publication_id INT,
  anime_id INT,
  publication_start INT UNIQUE,
  publication_end INT UNIQUE,
  anime_start INT UNIQUE,
  anime_end INT UNIQUE,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (anime_adaptation_id),
  FOREIGN KEY (publication_id) REFERENCES publication(publication_id),
  FOREIGN KEY (anime_id) REFERENCES anime(anime_id)
);

-- CONFIRMED
CREATE TABLE publication_adaptation (
  src_publication_id INT ,
  dst_publication_id INT ,
  src_publication_start INT ,
  src_publication_end INT ,
  dst_publication_start INT ,
  dst_publication_end INT ,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (src_publication_id, dst_publication_id),
  FOREIGN KEY (src_publication_id) REFERENCES publication(publication_id),
  FOREIGN KEY (dst_publication_id) REFERENCES publication(publication_id)
);


CREATE TABLE anidb_article (
  article_id INT AUTO_INCREMENT,
  member_id INT NOT NULL,  -- Foreign key to member table
  publication_id INT,
  anime_id INT, 
  title VARCHAR(256),
  content TEXT,  -- Assuming content can be long
  views INT     NOT NULL DEFAULT 0,
  upvotes INT NOT NULL DEFAULT 0,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (article_id, member_id),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
  FOREIGN KEY (publication_id) REFERENCES publication(publication_id),
  FOREIGN KEY (anime_id) REFERENCES anime(anime_id)
);

CREATE TABLE recommend_publication (
  member_id INT,
  publication_id INT,
  discussion VARCHAR(1045),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (member_id, publication_id),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
  FOREIGN KEY (publication_id) REFERENCES publication(publication_id)
);


CREATE TABLE upvoted_publication(
  member_id INT NOT NULL,
  publication_id INT NOT NULL,
  upvoted_number INT,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (member_id, publication_id),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
  FOREIGN KEY (publication_id) REFERENCES publication(publication_id)
);

-- CHANGING UNIQUE CONTRAINT IN PUBLICATION -- NEED TO DROP FOREIGN KEY CONSTRAINT AND UNIQUE KEY CONSTRAINT -- AND CREATE FOREIGN KEY CONSTRAINT AND INDEX
SHOW INDEX FROM publication;

DROP INDEX series_type ON publication;

ALTER TABLE publication DROP CONSTRAINT publication_ibfk_1;

ALTER TABLE publication ADD CONSTRAINT foreign key (series_type) references series_type(type_id);

SELECT 
  TABLE_NAME,COLUMN_NAME,CONSTRAINT_NAME, REFERENCED_TABLE_NAME,REFERENCED_COLUMN_NAME
FROM
  INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
  REFERENCED_TABLE_SCHEMA = (SELECT DATABASE()) AND
  REFERENCED_TABLE_NAME = 'series_type';

desc publication;


-- ###############
ALTER TABLE publication MODIFY COLUMN description varchar(8192);
-- ##############
SHOW FULL COLUMNS FROM publication LIKE 'description';

-- CHARACTER SET 변경
--  ALTER DATABASE anidb DEFAULT CHARACTER SET utf8mb4;

ALTER TABLE publication MODIFY description TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE publication CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER DATABASE anidb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


