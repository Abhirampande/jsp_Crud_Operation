<%-- 
    Document   : add-employee.jsp
    Created on : Jun 24, 2025, 1:26:55 PM
    Author     : Abhiram
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Add Employee</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-4">
            <h2>Add New Employee</h2>
            <form action="EmployeeServlet" method="post" enctype="multipart/form-data">
                
                <input type="hidden" name="action" value="add" />

                <div class="form-group">
                    <label>ID:</label>
                    <input type="number" name="id" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Name:</label>
                    <input type="text" name="name" class="form-control" required>
                </div>
                <div class="form-group">
                    <label>Age:</label>
                    <input type="text" name="age" class="form-control">
                </div>
                <div class="form-group">
                    <label>Email:</label>
                    <input type="email" name="email" class="form-control">
                </div>
                <div class="form-group">
                    <label>Gender:</label>
                    <select name="gender" class="form-control" required>
                        <option value="" disabled selected>-- Select Gender --</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Address:</label>
                    <input type="text" name="address" class="form-control">
                </div>
                <div class="form-group">
                    <label>City:</label>
                    <input type="text" name="city" class="form-control">
                </div>
                <div class="form-group">
                    <label>District:</label>
                    <input type="text" name="district" class="form-control">
                </div>
                <div class="form-group">
                    <label>State:</label>
                    <input type="text" name="state" class="form-control">
                </div>
                
                <!-- add image -->
                <div class="form-group">
                    <label>Image</label>
                    <input type="file" name="image" class="form-control" accept="image/*" required>              
                </div>

                <button type="submit" class="btn btn-success">Save</button>
                <!-- ensure the back link returns to the list by passing action=list -->
                <a href="EmployeeServlet?action=list" class="btn btn-secondary">Back</a>
            </form>
        </div>
    </body>
</html>


