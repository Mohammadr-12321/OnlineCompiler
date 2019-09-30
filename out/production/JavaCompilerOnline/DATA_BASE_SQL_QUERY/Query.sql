Step 1 : Login To Data Base
in linux Distribution Ubuntu Terminal 
Type : mysql -u root -p 
Then Enter Data Base Password :
123456 

Step 2 : Create Data Base
Type -->> CREATE DATABASE CompilerDB;

Step 3 : Type Following Command 
Use CompilerDB;

Step 4 : For Creat Student Table Type Following Query
CREATE TABLE Student(
		user_id INT NOT NULL AUTO_INCREMENT,
		user_name VARCHAR(200) NOT NULL,
		user_password VARCHAR(200) NOT NULL,
		user_email VARCHAR(200) NOT NULL,
		user_fullName VARCHAR(200) NOT NULL,
		user_college VARCHAR(200) NOT NULL,
		user_gender VARCHAR(200) NOT NULL,
		user_linkedin VARCHAR(200) NOT NULL,
		user_github VARCHAR(200) NOT NULL,
		user_contact VARCHAR(100) NOT NULL,
		PRIMARY KEY(user_id)
		);

Step 4.5: for Create Professor Table Type Following Query
CREATE TABLE Professor(
    user_id INT NOT NULL AUTO_INCREMENT,
		user_name VARCHAR(200) NOT NULL,
		user_password VARCHAR(200) NOT NULL,
		user_email VARCHAR(200) NOT NULL,
		user_fullName VARCHAR(200) NOT NULL,
		user_college VARCHAR(200) NOT NULL,
		user_gender VARCHAR(200) NOT NULL,
		user_linkedin VARCHAR(200) NOT NULL,
		user_github VARCHAR(200) NOT NULL,
		user_contact VARCHAR(100) NOT NULL,
		PRIMARY KEY(user_id)
);


Step 4.6: for Create Lecture Table Type Following Query
CREATE TABLE Lecture(
       lecture_id INT NOT NULL,
       lecture_name VARCHAR(100) NOT NULL,
)

Step 4.7 : for Create Student Professor Lecture Table Type Following Query
CREATE TABLE Student_Professor_Lecture(
       student_professor_lecture_id INT NOT NULL AUTO_INCREMENT,
       student_id INT NOT NULL,
       professor_id INT NOT NULL,
       lecture_id INT NOT NULL,
       PRIMARY KEY(student_professor_lecture_id),
       CONSTRAINT FK_Student FOREIGN KEY(student_id) REFERENCES Student(user_id),
       CONSTRAINT FK_Professor FOREIGN KEY(professor_id) REFERENCES Professor(professor_id),
       CONSTRAINT FK_Lecture FOREIGN KEY(lecture_id) REFERENCES Lecture(lecture_id)
);

Step 4.8: for create Degree Table type Following Query
CREATE TABLE Degree(
       degree_id INT NOT NULL,
       degreee_name VARCHAR NOT NULL,
       PRIMARY KEY(degree_id)
);




Step 4.91: for Create Professor Lecture Table Type Following Query
CREATE TABLE Professor_Lecture(
       professor_lecture_id INT NOT NULL AUTO_INCREMENT,
       professor_id INT NOT NULL,
       lecture_id INT NOT NULL,
       PRIMARY KEY(professor_lecture_id),
       CONSTRAINT FK_Professor2 FOREIGN KEY(professor_id) REFERENCES Professor(user_id),
       CONSTRAINT FK_Lecture1 FOREIGN KEY(lecture_id) REFERENCES Lecture(lecture_id)
);



Step 4.92 : For Create File Table Type following Query
CREATE TABLE File(
		file_id INT NOT NULL AUTO_INCREMENT,
		file_name VARCHAR(100) NOT NULL,
		file_data MEDIUMBLOB NOT NULL,
		file_type VARCHAR(50) NOT NULL,
		PRIMARY KEY(file_id),
);


in the end of project we decide that drop this table and instead of create Practise and Solution Tables and too create Professor_Practise_Lecture and Student_Solution_Practise_Lecture Tables

Step 4.93: for Create Student File Lecture Type Following Query
CREATE TABLE Student_File_Lecture(
       student_file_lecture_id INT NOT NULL AUTO_INCREMENT ,
       student_id INT NOT NULL,
       file_id INT NOT NULL,
       lecture_id INT NOT NULL,
       PRIMARY KEY(student_file_lecture_id),
       CONSTRAINT FK_student1 FOREIGN KEY(student_id) REFERENCES Student(user_id),
       CONSTRAINT FK_File FOREIGN KEY(file_id) REFERENCES File(file_id),
       CONSTRAINT FK_Lecture2 FOREIGN KEY(lecture_id) REFERENCES Lecture(lecture_id)
);



Step 4.94: for Create Professor File Lecture Type Following Query
CREATE TABLE Professor_File_Lecture(
       professor_file_lecture_id INT NOT NULL AUTO_INCREMENT,
       professor_id INT NOT NULL,
       file_id INT NOT NULL,
       lecture_id INT NOT NULL,
       PRIMARY KEY(professor_file_lecture_id),
       CONSTRAINT FK_professor3 FOREIGN KEY(professor_id) REFERENCES Professor(user_id),
       CONSTRAINT FK_File1 FOREIGN KEY(file_id) REFERENCES File(file_id),
       CONSTRAINT FK_lecture3 FOREIGN KEY(lecture_id) REFERENCES Lecture(lecture_id)
);

Step 4.95: for Create Professor File Table
CREATE Table Professor_File(
       professor_file_id INT NOT NULL AUTO_INCREMENT,
       professor_id INT NOT NULL,
       file_id INT NOT NULL,
       PRIMARY KEY(professor_file_id),
       CONSTRAINT FK_Professor4 FOREIGN KEY(professor_id) REFERENCES Professor(user_id),
       CONSTRAINT FK_File2 FOREIGN KEY(file_id) REFERENCES File(file_id)
);

Step 4.96: for Create Student File Table
CREATE Table Student_File(
      student_file_id INT NOT NULL AUTO_INCREMENT,
      student_id INT NOT NULL,
      file_id INT NOT NULL,
      PRIMARY KEY(student_file_id),
      CONSTRAINT FK_Student2 FOREIGN KEY(student_id) REFERENCES Student(user_id),
      CONSTRAINT FK_File3 FOREIGN KEY(file_id) REFERENCES File(file_id)
);


Step 4.97: for Create Field Study Table Type Following Query :
CREATE TABLE FieldStudy(
       field_study_id INT NOT NULL AUTO_INCREMENT,
       field_study_title VARCHAR(80) NOT NULL,
       PRIMARY KEY(field_study_id),
);

Step 4.98: for Create Professor Degree Field Study Table Type Following Query
CREATE TABLE Professor_Degree_FieldStudy(
       professor_degree_field_study_id INT NOT NULL AUTO_INCREMENT,
       professor_id INT NOT NULL,
       degree_id INT NOT NULL,
       field_study_id INT NOT NULL,
       PRIMARY KEY(professor_degree_field_study_id),
       CONSTRAINT FK_professor5 FOREIGN KEY(professor_id) REFERENCES Professor(user_id),
       CONSTRAINT FK_degree FOREIGN KEY(degree_id) REFERENCES Degree(degree_id),
       CONSTRAINT FK_field_study FOREIGN KEY(field_study_id) REFERENCES FieldStudy(field_study_id)
);

Step 4.99: for create Practise Table Type Following Query
CREATE TABLE Practise(
       practise_id INT NOT NULL AUTO_INCREMENT,
       file_id INT NOT ,
       practise_title VARCHAR(50) NOT NULL,
       programming_languages VARCHAR(40) NOT NULL,
       PRIMARY KEY(practise_id),
       CONSTRAINT FK_Practise_File FOREIGN KEY(file_id) REFERENCES File(file_id)
);


 Step 5.0: for create Professor_Practise_Lecture Table Type Following Query
 CREATE TABLE Professor_Practise_Lecture(
        professor_practise_lecture_id INT NOT NULL AUTO_INCREMENT,
        professor_id INT NOT NULL ,
        practise_id INT NOT NULL,
        lecture_id INT NOT NULL,
        PRIMARY KEY(professor_practise_lecture_id),
        CONSTRAINT FK_professor6 FOREIGN KEY(professor_id) REFERENCES Professor(user_id),
        CONSTRAINT FK_practise1 FOREIGN KEY(practise_id) REFERENCES Practise(practise_id),
        CONSTRAINT FK_Lecture4 FOREIGN KEY(lecture_id) REFERENCES Lecture(lecture_id)
 );

 Step 5.1: for CREATE Student_Solution_Practise_Lecture_Professor Table Type Following Query
 CRAETE TABLE Student_Solution_Practise_Lecture_Professor(
              student_solution_practise_lecture_professor_id INT NOT NULL AUTO_INCREMENT,
              student_id INT NOT NULL,
              solution_id INT NOT NULL,
              practise_id INT NOT NULL,
              lecture_id INT NOT NULL,
              professor_id INT NOT NULL,
              PRIMARY KEY(student_solution_practise_lecture_professor_id),
              CONSTRAINT FK_student3 FOREIGN KEY(student_id) REFERENCES Student(user_id),
              CONSTRAINT FK_File4 FOREIGN KEY(solution_id) REFERENCES File(file_id),
              CONSTRAINT FK_Practise FOREIGN KEY(practise_id) REFERENCES Practise(practise_id),
              CONSTRAINT FK_lecture5 FOREIGN KEY(lecture_id) REFERENCES Lecture(lecture_id),
              CONSTRAINT FK_professor7 FOREIGN KEY(professor_id) REFERENCES Professor(user_id)
 );



