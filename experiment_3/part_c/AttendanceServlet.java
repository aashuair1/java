import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AttendanceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // ⚠️ TODO: Update these with your actual database details
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; 
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "mypassword";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Read the attendance data from the request
        String studentIdStr = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        String message;
        
        if (studentIdStr == null || date == null || status == null || studentIdStr.isEmpty()) {
            message = "Error: All fields are required.";
        } else {
            // 2. Insert data into the database
            String sql = "INSERT INTO Attendance (StudentID, AttendanceDate, Status) VALUES (?, ?, ?)";
            
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                
                int studentId = Integer.parseInt(studentIdStr);
                
                pstmt.setInt(1, studentId);
                pstmt.setString(2, date); // Date can be inserted as String in SQL DATE format (YYYY-MM-DD)
                pstmt.setString(3, status);
                
                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    message = "✅ Attendance recorded successfully for Student ID: " + studentId;
                } else {
                    message = "❌ Failed to record attendance.";
                }

            } catch (SQLException e) {
                message = "❌ Database Error: " + e.getMessage();
                e.printStackTrace();
            } catch (NumberFormatException e) {
                message = "❌ Invalid Student ID format.";
            }
        }
        
        // 3. Send a confirmation response (using RequestDispatcher to return to the JSP)
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher("attendanceForm.jsp");
        dispatcher.forward(request, response);
    }
}