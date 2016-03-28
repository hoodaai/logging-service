


/*account schema*/

CREATE TABLE account (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  app_name varchar(50) NOT NULL,
  app_id varchar(50) NOT NULL,
  app_secret  varchar(150) NOT NULL,
  password_hash varchar(60) DEFAULT NULL,
  activated  bit(1) NOT NULL,
  CREATED_DATE  timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY ( id ),
  UNIQUE KEY app_name  ( app_name ),
  UNIQUE KEY idx_user_app_name  ( app_name )
);
