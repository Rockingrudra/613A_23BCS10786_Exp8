import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;


public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empID = request.getParameter("empid");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://bytexldb.com:5051/db_43zytdf8j",
                "user_43zytdf8j",
                "p43zytdf8j"
            );

            Statement st = con.createStatement();
            ResultSet rs;

            if (empID != null && !empID.isEmpty()) {
                rs = st.executeQuery("SELECT * FROM Employee WHERE EmpID=" + empID);
                out.println("<h3>Employee Details</h3>");
            } else {
                rs = st.executeQuery("SELECT * FROM Employee");
                out.println("<h3>All Employees</h3>");
            }

            out.println("<table border='1'><tr><th>EmpID</th><th>Name</th><th>Salary</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                            rs.getString("Name") + "</td><td>" +
                            rs.getDouble("Salary") + "</td></tr>");
            }
            out.println("</table>");

            out.println("<br><form method='get' action='EmployeeServlet'>");
            out.println("Search by EmpID: <input type='text' name='empid'>");
            out.println("<input type='submit' value='Search'></form>");

            con.close();
        } catch (Exception e) {
            out.println("<h4 style='color:red;'>Error: " + e.getMessage() + "</h4>");
        }
    }
}
