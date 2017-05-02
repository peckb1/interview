CREATE TABLE CurrentMember (

  _id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  member_name VARCHAR(256) NOT NULL,

  PRIMARY KEY (_id),
  UNIQUE KEY member_name (member_name)

) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE PastMember (

  _id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  member_name VARCHAR(256) NOT NULL,

  PRIMARY KEY (_id),
  UNIQUE KEY member_name (member_name)

) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE `Group` (

  _id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  group_name VARCHAR(256) NOT NULL,
  year_formed SMALLINT NOT NULL,
  year_disbanded SMALLINT,

  PRIMARY KEY (_id),

  UNIQUE KEY group_name (group_name)

) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE CurrentGroupMembers (

  group_id INT UNSIGNED NOT NULL,
  member_id INT UNSIGNED NOT NULL,

  PRIMARY KEY (group_id, member_id),
  FOREIGN KEY (group_id) REFERENCES `Group`(_id),
  FOREIGN KEY (member_id) REFERENCES `CurrentMember`(_id)

) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE PastGroupMembers (

  group_id INT UNSIGNED NOT NULL,
  member_id INT UNSIGNED NOT NULL,

  PRIMARY KEY (group_id, member_id),
  FOREIGN KEY (group_id) REFERENCES `Group`(_id),
  FOREIGN KEY (member_id) REFERENCES `PastMember`(_id)

) ENGINE=INNODB DEFAULT CHARSET=latin1;

CREATE TABLE Album (

  _id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  album_name VARCHAR(256) NOT NULL,
  release_year SMALLINT NOT NULL,
  group_id INT UNSIGNED NOT NULL,

  PRIMARY KEY (_id),
  FOREIGN KEY (group_id) REFERENCES `Group`(_id),

  UNIQUE KEY album_name (album_name)

) ENGINE=INNODB DEFAULT CHARSET=latin1;