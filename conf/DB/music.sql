CREATE TABLE Member (

  _id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  member_name VARCHAR(256) NOT NULL,

  PRIMARY KEY (_id),
  UNIQUE KEY member_name (member_name)

) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE GroupMembers (

  group_id SMALLINT NOT NULL,
  member_id INT NOT NULL,

  PRIMARY KEY (group_id, member_id)

) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `Group` (

  _id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  group_name VARCHAR(256) NOT NULL,
  year_formed SMALLINT NOT NULL,
  year_disbanded SMALLINT,
  current_members SMALLINT NOT NULL,
  past_members SMALLINT NOT NULL,

  PRIMARY KEY (_id),
  FOREIGN KEY (current_members) REFERENCES GroupMembers(group_id),
  FOREIGN KEY (past_members) REFERENCES GroupMembers(group_id),

  UNIQUE KEY group_name (group_name)

) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE Album (

  _id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  album_name VARCHAR(256) NOT NULL,
  release_year SMALLINT NOT NULL,
  group_name VARCHAR(256) NOT NULL,

  PRIMARY KEY (_id),
  FOREIGN KEY (group_name) REFERENCES `Group`(group_name),

  UNIQUE KEY album_name (album_name)

) ENGINE=INNODB DEFAULT CHARSET=latin1;