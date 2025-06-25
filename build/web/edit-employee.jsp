<%-- 
    Document   : edit-employee.jsp
    Created on : Jun 24, 2025, 1:14:29 PM
    Author     : Abhiram
--%>

<%@ page import="model.Employee" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Employee emp = (Employee) request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Employee</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2 style="color: red" align="center">Edit Employee</h2>
            <form action="EmployeeServlet" method="post" enctype="multipart/form-data">
                
                <input type="hidden" name="action" value="update" />
                <input type="hidden" name="id" value="<%= emp.getId() %>">
                
                <!-- ID is read-only -->
                <div class="form-group">
                    <label>ID:</label>
                    <input type="number" name="id" class="form-control" value="<%= emp.getId()%>" readonly>
                </div>
                <!-- Other fields Pre-filled -->
                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" name="name" class="form-control" value="<%= emp.getName()%>" required>
                </div>
                <div class="form-group">
                    <label>Age:</label>
                    <input type="text" name="age" class="form-control" value="<%= emp.getAge()%>">
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" class="form-control" value="<%= emp.getEmail()%>">
                </div>
                <div class="form-group">
                    <label>Gender:</label>
                    <select name="gender" class="form-control" required>
                         <option value="" disabled selected>-- Select Gender --</option>
                        <option value="Male"   <%= "Male".equals(emp.getGender()) ? "selected" : ""%>>Male</option>
                        <option value="Female" <%= "Female".equals(emp.getGender()) ? "selected" : ""%>>Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Address:</label>
                    <input type="text" name="address" class="form-control" value="<%= emp.getAddress()%>">
                </div>
                <div class="form-group">
                    <label>City:</label>
                    <input type="text" name="city" class="form-control" value="<%= emp.getCity()%>">
                </div>
                <div class="form-group">
                    <label>District:</label>
                    <input type="text" name="district" class="form-control" value="<%= emp.getDistrict()%>">
                </div>
                <div class="form-group">
                    <label>State:</label>
                    <input type="text" name="state" class="form-control" value="<%= emp.getState()%>">
                </div>
                <div><!-- add image -->
                    <label>Image</label>
                    <input type="file" name="image" class="form-control" accept="image/*">
                    <p>Current: <%= emp.getImage() %></p>
                    <button type="submit" value="Add Image">
                        
                        <a href="EmployeeServlet?action=list" class="btn btn-secondary">Back</a>
                        
                    </button>
                    
                
                </div>
                <!-- Submit and Cancel -->
                <button type="submit" class="btn btn-primary">Update</button>
                <!-- Return to list via servlet with action=list -->
                <a href="EmployeeServlet?action=list" class="btn btn-secondary">Cancel</a>
            </form>
        </div>
    </body>
</html>

