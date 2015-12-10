BEGIN TRANSACTION; 
CREATE TABLE IF NOT EXISTS users (
   id INTEGER PRIMARY KEY AUTOINCREMENT, 
   login_name VARCHAR2(15), 
   first_name VARCHAR2(25), 
   last_name VARCHAR2(25), 
   password VARCHAR2(40));

CREATE TABLE IF NOT EXISTS courses (
   id INTEGER PRIMARY KEY AUTOINCREMENT,
   owner_id VARCHAR2(25) NOT NULL, 
   title VARCHAR2(40), 
   year INTEGER, 
   section INTEGER, 
   term VARCHAR2(15), 
   UNIQUE (title, section, year, term));

CREATE TABLE IF NOT EXISTS user_associations (
   user_id VARCHAR2(25) NOT NULL,
   course_id INTEGER NOT NULL, 
   type VARCHAR2(16), 
   FOREIGN KEY (course_id) REFERENCES courses (id));   

CREATE TABLE IF NOT EXISTS tests (
   id INTEGER PRIMARY KEY AUTOINCREMENT, 
   course_id INTEGER, 
   title VARCHAR2(60), 
   start_date DATETIME, 
   end_date DATETIME, 
   time_limit INTEGER,
   published INTEGER,
   FOREIGN KEY (course_id) REFERENCES courses (id));  

CREATE TABLE IF NOT EXISTS submissions(
   id INTEGER PRIMARY KEY AUTOINCREMENT, 
   test_id INTEGER NOT NULL, 
   user_id VARCHAR2(25) NOT NULL, 
   complete INTEGER NOT NULL,
   is_graded INTEGER NOT NULL,
   FOREIGN KEY (test_id) REFERENCES tests (id));

CREATE TABLE IF NOT EXISTS questions (
   id INTEGER PRIMARY KEY AUTOINCREMENT, 
   points INTEGER, 
   difficulty INTEGER, 
   prompt VARCHAR2(400), 
   correct_response_id INTEGER, 
   type VARCHAR2(20),
   cq_script_path VARCHAR2(100));  

CREATE TABLE IF NOT EXISTS mc_options (
   id INTEGER PRIMARY KEY AUTOINCREMENT, 
   response_id INTEGER, 
   rank INTEGER, 
   option VARCHAR2(50), 
   FOREIGN KEY (response_id) REFERENCES question_responses (id));

CREATE TABLE IF NOT EXISTS test_questions (
   id INTEGER PRIMARY KEY AUTOINCREMENT, 
   question_id INTEGER NOT NULL,
   rank INTEGER,
   test_id INTEGER NOT NULL, 
   FOREIGN KEY (question_id) REFERENCES questions (id), 
   FOREIGN KEY (test_id) REFERENCES tests (id));  

CREATE TABLE IF NOT EXISTS question_responses (
   id INTEGER PRIMARY KEY AUTOINCREMENT, 
   question_id INTEGER NOT NULL,
   submission_id INTEGER,
   points_received INTEGER, 
   mc_response INTEGER, 
   sa_response VARCHAR2(600),
   sa_mtype VARCHAR2(20),
   c_response VARCHAR2(600),
   c_given VARCHAR2(600),
   type VARCHAR2(40), 
   FOREIGN KEY (question_id) REFERENCES questions (id)); 

CREATE TABLE IF NOT EXISTS matching_pairs (
   id INTEGER PRIMARY KEY AUTOINCREMENT, 
   response_id INTEGER NOT NULL, 
   prompt VARCHAR2(35), 
   response VARCHAR2(35), 
   FOREIGN KEY (response_id) REFERENCES question_responses (id));  

COMMIT TRANSACTION;












