/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EmployeeDao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Employee;

/**
 *
 * @author Abhiram  *
 * Tip: If the failing expression is known to legally refer to something that's
 * sometimes null or missing, either specify a default value like
 * myOptionalVar!myDefault, or use
 * <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover
 * the last step of the expression; to cover the whole expression, use
 * parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
 *
 *
 */
@WebServlet("/EmployeeServlet")
@MultipartConfig(maxFileSize = 35177215)// 35MB
public class EmployeeServlet extends HttpServlet {

    private static final String UPLOAD_DIR = "images";  //Folder Name to save images
    private EmployeeDao employeeDao;

    @Override
    public void init() {
        employeeDao = new EmployeeDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID is missing or empty.");
        }

        int id = Integer.parseInt(idStr);
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");
        String state = request.getParameter("state");
        String district = request.getParameter("district");
        String city = request.getParameter("city");
        String address = request.getParameter("address");
        String email = request.getParameter("email");

        // Handle file upload
        String fileName = null;
        Part filePart = request.getPart("image");
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Save file in /images folder
            String uploadPath = getServletContext().getRealPath("") + File.separator + "images";

            // Ensure the directory exists
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();  // Create "images" directory if missing
            }

            // Save the file to the images folder
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, Paths.get(uploadPath, fileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } else {
            if ("update".equalsIgnoreCase(action)) {
                Employee existingEmp = employeeDao.getEmployeeById(id);
                fileName = existingEmp.getImage();

            }
        }

        // Build Employee object
        Employee emp = new Employee(id, name, age, email, gender, address, city, district, state, fileName);
        emp.setImage(fileName); // store filename in DB

        if ("add".equalsIgnoreCase(action)) {
            employeeDao.insertEmployee(emp);
        } else if ("update".equalsIgnoreCase(action)) {
            employeeDao.updateEmployee(emp);
        }

        response.sendRedirect("EmployeeServlet?action=list");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("list".equals(action)) {
            List<Employee> list = employeeDao.getAllEmployees();
            request.setAttribute("list", list);
            request.getRequestDispatcher("EmployeeBoard.jsp").forward(request, response);
        } else if ("edit".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Employee emp = employeeDao.getEmployeeById(id);
            request.setAttribute("emp", emp);
            request.getRequestDispatcher("edit-employee.jsp").forward(request, response);
        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            employeeDao.deleteEmployee(id);
            response.sendRedirect("EmployeeServlet?action=list");
        } else {
            response.sendRedirect("EmployeeServlet?action=list");
        }
    }

}
