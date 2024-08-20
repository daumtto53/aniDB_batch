use anidb;
ALTER DATABASE anidb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


create table series_type (
    type_id     INT AUTO_INCREMENT PRIMARY KEY ,
    type_name   VARCHAR(16) NOT NULL,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at  DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3)
);

-- confirmed.
create table genre(
	genre_id	INT PRIMARY KEY	auto_increment,
    genre_name	VARCHAR(256) UNIQUE,
    genre_description	VARCHAR(256),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at  DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3)
);


-- make series_type before publication
CREATE TABLE publication (
    publication_id INT PRIMARY KEY  AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(8192),
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
    relation VARCHAR(256),
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




-- publisher
CREATE TABLE publisher (
    publisher_id INT PRIMARY KEY AUTO_INCREMENT,
    publisher_name VARCHAR(256) UNIQUE NOT NULL,
    website_url VARCHAR(2048),
    parent_publisher_id INT,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
    FOREIGN KEY (parent_publisher_id) REFERENCES publisher(publisher_id)
);
delete from publisher;

CREATE TABLE alternative_publisher_name (
    alternative_publisher_id    INT NOT NULL AUTO_INCREMENT,
    original_publisher_id       INT NOT NULL,
    alternative_name            VARCHAR(256) NOT NULL,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
    PRIMARY KEY (alternative_publisher_id, original_publisher_id),
    FOREIGN KEY (original_publisher_id) REFERENCES publisher(publisher_id)
);


CREATE TABLE artist_type (
  artist_type_id INT PRIMARY KEY AUTO_INCREMENT,
  artist_type_name VARCHAR(256) UNIQUE,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3)
);

-- create aritst_type before artist
CREATE TABLE artist (
  artist_id INT PRIMARY KEY AUTO_INCREMENT,
  artist_type INT NOT NULL,
  native_name VARCHAR(256),
  artist_description VARCHAR(2048),
  birth_place VARCHAR(256),
  birth_date DATE,
  artist_status VARCHAR(256),
  gender VARCHAR(5),
  official_website_url VARCHAR(2048),
  twitter_url VARCHAR(2048),
  cover_image_url VARCHAR(2048),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  FOREIGN KEY (artist_type) REFERENCES artist_type(artist_type_id)
);

-- create artist before ""
CREATE TABLE artist_associated_name (
  associated_name_id INT PRIMARY KEY AUTO_INCREMENT,
  artist_id INT NOT NULL,
  associated_name VARCHAR(256),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  FOREIGN KEY (artist_id) REFERENCES artist(artist_id)
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

create TABLE anime (
    anime_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(256),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3)
);

-- CONFIRMED
CREATE TABLE anime_adaptation (
  anime_adaptation_id INT auto_increment,
  publication_id INT,
  anime_id INT,
  anime_name VARCHAR(255),
  anime_type INT NULL,
  publication_start INT,
  publication_end INT,
  anime_start INT,
  anime_end INT,
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



-- MEMBER / ARTICLE RELATED
CREATE TABLE anidb_role (
  role_id INT PRIMARY KEY AUTO_INCREMENT,
  role_name VARCHAR(32) NOT NULL UNIQUE,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3)
);

CREATE TABLE anidb_member (
  member_id INT PRIMARY KEY AUTO_INCREMENT,

  loginid VARCHAR(256) UNIQUE,
  username VARCHAR(256) UNIQUE NOT NULL,
  member_password VARCHAR(120) NOT NULL,
  email VARCHAR(32),
  isFromSocial BOOLEAN DEFAULT TRUE,
  isDisabled BOOLEAN DEFAULT FALSE,
  member_name VARCHAR(128),
  nickname VARCHAR(120),
  birthday DATE,
--   M or F
  gender VARCHAR(1),
  member_description VARCHAR(256),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3)
);

CREATE TABLE series_comment (
	publication_id INT,
    member_id	INT,
    anidb_comment	VARCHAR(2048),
    created_at	DATETIME(3) NOT NULL DEFAULT now(3),
    updated_at DATETIME(3) NOT NULL DEFAULT now(3) on update now(3),
    PRIMARY KEY (publication_id, member_id),
    FOREIGN KEY (publication_id) references publication(publication_id),
    FOREIGN KEY (member_id) references anidb_member(member_id)
);


CREATE TABLE member_anidb_role (
  member_id INT ,
  role_id INT ,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (member_id, role_id),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
  FOREIGN KEY (role_id) REFERENCES anidb_role(role_id)
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

CREATE TABLE anidb_comment (
  comment_id INT PRIMARY KEY AUTO_INCREMENT,
  member_id INT NOT NULL,  -- Foreign key to member table
  article_id INT NOT NULL,  -- Foreign key to article table
  content VARCHAR(2048),  -- Assuming content can be long
  upvotes INT DEFAULT 0,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
  FOREIGN KEY (article_id) REFERENCES anidb_article(article_id)
);

CREATE TABLE recommend_anime (
  member_id INT,
  anime_id INT,
  discussion VARCHAR(1045),
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (member_id, anime_id),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
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

-- Upvoted
CREATE TABLE upvoted_article (
  member_id INT NOT NULL,
  article_id INT NOT NULL,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (member_id, article_id),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
  FOREIGN KEY (article_id) REFERENCES anidb_article(article_id)
);

CREATE TABLE upvoted_comment(
  member_id INT NOT NULL,
  comment_id INT NOT NULL,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (member_id, comment_id),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
  FOREIGN KEY (comment_id) REFERENCES anidb_comment(comment_id)
);

CREATE TABLE upvoted_publication(
  member_id INT NOT NULL,
  publication_id INT NOT NULL,
    created_at  DATETIME(3) NOT NULL DEFAULT NOW(3),
    updated_at DATETIME(3) NOT NULL DEFAULT NOW(3) ON UPDATE NOW(3),
  PRIMARY KEY (member_id, publication_id),
  FOREIGN KEY (member_id) REFERENCES anidb_member(member_id),
  FOREIGN KEY (publication_id) REFERENCES publication(publication_id)
);

CREATE TABLE article_file (
	article_id INT,
    member_id INT, 
    original_filename VARCHAR(255) NOT NULL UNIQUE,
    saved_filename VARCHAR(255) NOT NULL,
    size float DEFAULT 0.0,
    created_at DATETIME(3) DEFAULT now(),
    updated_at DATETIME(3) default now() on update now(),
    primary key (article_id, member_id),
    foreign key (article_id) references anidb_article(article_id),
    foreign key (member_id) references anidb_member(member_id)
);


show tables;

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