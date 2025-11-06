import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// The @WebServlet annotation is often used instead of web.xml configuration
// Assuming it's mapped to "/LoginServlet"
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Hardcoded credentials for validation
    private static final String VALID_USER = "user";
    private static final String VALID_PASS = "pass";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // 1. Retrieve parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        out.println("<html><head><title>Login Result</title></head><body>");
        
        // 2. Validate credentials
        if (VALID_USER.equals(username) && VALID_PASS.equals(password)) {
            // Valid: Display personalized welcome
            out.println("<h3>✅ Login Successful!</h3>");
            out.println("<h1>Welcome, " + username + "!</h1>");
            out.println("<p>This is your personalized dashboard.</p>");
        } else {
            // Invalid: Show error message
            out.println("<h3>❌ Login Failed</h3>");
            out.println("<p style='color:red;'>Invalid Username or Password. Please try again.</p>");
            // Optional: Add a link back to the login page
            out.println("<p><a href='login.html'>Go Back to Login</a></p>");
        }
        
        out.println("</body></html>");
    }
}