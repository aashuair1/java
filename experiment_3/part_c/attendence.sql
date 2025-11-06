CREATE TABLE Attendance (
    AttendanceID INT PRIMARY KEY AUTO_INCREMENT,
    StudentID INT NOT NULL,
    AttendanceDate DATE NOT NULL,
    Status VARCHAR(10) NOT NULL  -- e.g., 'Present', 'Absent'
);