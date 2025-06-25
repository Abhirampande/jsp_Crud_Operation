/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import javax.servlet.annotation.WebServlet;      
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhiram
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/empl", "root", "Abhiram@123")) {
                String sql = "SELECT image FROM empl WHERE id=?";
                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, id);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            byte[] imgData = rs.getBytes("image");
                            response.setContentType("image/jpeg");
                            OutputStream out = response.getOutputStream();
                            out.write(imgData);
                            out.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // You may log or show a default image
        }
    }
}

