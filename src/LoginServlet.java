import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if ("admin".equals(username) && "1234".equals(password)) {
            out.println("<h2>Welcome, " + username + "!</h2>");
        } else {
            out.println("<h3 style='color:red;'>Invalid username or password.</h3>");
            out.println("<a href='login.html'>Try again</a>");
        }
    }
}
