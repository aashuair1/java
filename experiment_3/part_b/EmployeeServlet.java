import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // ⚠️ TODO: Update these with your actual database details
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; 
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "mypassword";

    // Utility method to get a database connection
    private Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Employee Records</title></head><body>");
        out.println("<h2>Employee Management Portal</h2>");

        // --- Search Form ---
        out.println("<h3>Search Employee by ID</h3>");
        out.println("<form action='EmployeeServlet' method='GET'>");
        out.println("<label for='empId'>Employee ID:</label>");
        out.println("<input type='text' id='empId' name='empId' placeholder='e.g., 101'>");
        out.println("<input type='submit' value='Search'>");
        out.println("</form><hr>");

        // Get the search parameter
        String searchId = request.getParameter("empId");
        
        if (searchId != null && !searchId.trim().isEmpty()) {
            displayFilteredRecord(out, searchId);
        } else {
            displayAllRecords(out);
        }

        out.println("</body></html>");
    }

    // Displays a single employee record based on ID
    private void displayFilteredRecord(PrintWriter out, String empId) {
        String sql = "SELECT EmpID, Name, Salary FROM Employee WHERE EmpID = ?";
        out.println("<h3>Search Results for Employee ID: " + empId + "</h3>");
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
                    out.println("<tr>");
                    out.println("<td>" + rs.getInt("EmpID") + "</td>");
                    out.println("<td>" + rs.getString("Name") + "</td>");
                    out.println("<td>$" + rs.getDouble("Salary") + "</td>");
                    out.println("</tr></table>");
                } else {
                    out.println("<p style='color:red;'>Employee with ID " + empId + " not found.</p>");
                }
            }
        } catch (SQLException e) {
            out.println("<p style='color:red;'>Database Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }

    // Displays all employee records
    private void displayAllRecords(PrintWriter out) {
        String sql = "SELECT EmpID, Name, Salary FROM Employee";
        out.println("<h3>All Employee Records</h3>");
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
            
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getInt("EmpID") + "</td>");
                out.println("<td>" + rs.getString("Name") + "</td>");
                out.println("<td>$" + rs.getDouble("Salary") + "</td>");
                out.println("</tr>");
            }
            out.println("</table>");

        } catch (SQLException e) {
            out.println("<p style='color:red;'>Database Error: " + e.getMessage() + "</p>");
            e.printStackTrace();
        }
    }
}
