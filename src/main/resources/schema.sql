create table IF NOT EXISTS user (
  id int auto_increment,
  name varchar(255),
  registrationid varchar(255),
  registration_date	datetime,
  primary key(id,registrationid)
);