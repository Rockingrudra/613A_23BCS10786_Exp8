import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String studentId = request.getParameter("studentId");
        String date = request.getParameter("date");
        String status = request.getParameter("status");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://bytexldb.com:5051/db_43zytdf8j",
                "user_43zytdf8j",
                "p43zytdf8j"
            );

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO Attendance (StudentID, Date, Status) VALUES (?, ?, ?)");
            ps.setInt(1, Integer.parseInt(studentId));
            ps.setString(2, date);
            ps.setString(3, status);

            int result = ps.executeUpdate();
            if (result > 0) {
                out.println("<h3>Attendance recorded successfully!</h3>");
            } else {
                out.println("<h3>Error saving data!</h3>");
            }

            con.close();
        } catch (Exception e) {
            out.println("<h4 style='color:red;'>Error: " + e.getMessage() + "</h4>");
        }
    }
}
