package dao;

import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/empl?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "Abhiram@123";

    // INSERT
    public int insertEmployee(Employee emp) {
        String sql = "INSERT INTO empl (id, name, age, gender, state, district, city, address, email, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1,    emp.getId());
                ps.setString(2, emp.getName());
                ps.setString(3, emp.getAge());
                ps.setString(4, emp.getGender());
                ps.setString(5, emp.getState());
                ps.setString(6, emp.getDistrict());
                ps.setString(7, emp.getCity());
                ps.setString(8, emp.getAddress());
                ps.setString(9, emp.getEmail());
                ps.setString(10,emp.getImage()); // file name only
               

                result = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // SELECT ALL
    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM empl";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
                 Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    Employee emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("email"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("district"),
                        rs.getString("state"),
                        rs.getString("image")
                        
                    );
                    list.add(emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // SELECT ONE BY ID
    public Employee getEmployeeById(int id) {
        Employee emp = null;
        String sql = "SELECT * FROM empl WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    emp = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("email"),
                        rs.getString("gender"),
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("district"),
                        rs.getString("state"),
                        rs.getString("image")
                        
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emp;
    }

    // UPDATE
    public int updateEmployee(Employee emp) {
        String sql = "UPDATE empl SET name=?, age=?, gender=?, state=?, district=?, city=?, address=?, email=?, image=? WHERE id=?";
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, emp.getName());
                ps.setString(2, emp.getAge());
                ps.setString(3, emp.getGender());
                ps.setString(4, emp.getState());
                ps.setString(5, emp.getDistrict());
                ps.setString(6, emp.getCity());
                ps.setString(7, emp.getAddress());
                ps.setString(8, emp.getEmail());
                ps.setString(9, emp.getImage());
                ps.setInt(10, emp.getId());
                // file name
                
                
              
                 
                 ps.setInt(10, emp.getId());

                result = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // DELETE
    public int deleteEmployee(int id) {
        String sql = "DELETE FROM empl WHERE id = ?";
        int result = 0;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(JDBC_URL, USER, PASS);
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setInt(1, id);
                result = ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}