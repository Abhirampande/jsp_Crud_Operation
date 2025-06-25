<%-- 
    Document   : EmployeeBoard.jsp
    Created on : Jun 24, 2025, 1:16:31?PM
    Author     : 91821
--%>

<%@ page import="java.util.List" %>
<%@ page import="model.Employee" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employee Board</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
    <h2 class="text-center mb-4">Employee Panel</h2>

    

    <table class="table table-bordered table-striped">
        <thead class="thead-dark">
        <tr>
            <th>ID</th><th>Name</th><th>Age</th><th>Email</th><th>Gender</th>
            <th>Address</th><th>City</th><th>District</th><th>State</th><th>Image</th><th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Employee> list = (List<Employee>) request.getAttribute("list");
            if (list != null && !list.isEmpty()) {
                for (Employee emp : list) {
        %>
        <tr>
          
            <td><%= emp.getId() %></td>
            <td><%= emp.getName() %></td>
            <td><%= emp.getAge() %></td>
            <td><%= emp.getEmail() %></td>
            <td><%= emp.getGender() %></td>
            <td><%= emp.getAddress() %></td>
            <td><%= emp.getCity() %></td>
            <td><%= emp.getDistrict() %></td>
            <td><%= emp.getState() %></td>
              <td>
                <img src="images/<%= emp.getImage() %>"  width="100" height="100" style="object-fit:cover; border-radius:50%;"> 
            </td>
            
            <td>
                <a href="EmployeeServlet?action=edit&id=<%= emp.getId() %>" class="btn btn-sm btn-info">Edit</a>
                <a href="EmployeeServlet?action=delete&id=<%= emp.getId() %>" class="btn btn-sm btn-danger"
                   onclick="return confirm('Are you sure?')">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="10" class="text-center">No employees found.</td></tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
>
