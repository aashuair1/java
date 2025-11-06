CREATE TABLE Employee (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(100),
    Salary DECIMAL(10, 2)
);

-- Insert sample data
INSERT INTO Employee (EmpID, Name, Salary) VALUES (101, 'Alice Smith', 75000.00);
INSERT INTO Employee (EmpID, Name, Salary) VALUES (102, 'Bob Johnson', 62000.50);
INSERT INTO Employee (EmpID, Name, Salary) VALUES (103, 'Charlie Brown', 90000.00);