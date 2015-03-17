<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register Account</title>
</head>
<body>
<h2>Register Account</h2>
<form:form method="post" commandName="user_account">
<%--     <form:hidden path="id"/> --%>
    <table>
    <tr>
        <td>Username:</td>
        <td><form:input path="username" /></td>
        <td><form:errors path="username" cssclass="error" /></td>
    </tr>
    <tr>
        <td>Password:</td>
        <td><form:input path="password" /></td>
           <td><form:errors path="password" cssclass="error" /></td>
     </tr>
    <tr>
        <td>Enabled:</td>
        <td><form:input path="enabled" /></td>
        <td><form:errors path="enabled" cssclass="error" /></td>
    </tr>
    <tr>
    	<td>Permission 1:</td>
    	<td><form:input path="userRoles[0].role" value="None" /></td>
    	<td><form:errors path="userRoles[0].role" cssclass="error" /> </td>
    </tr>
    <tr>
    	<td>Permission 2:</td>
    	<td><form:input path="userRoles[1].role" value="None" /></td>
    	<td><form:errors path="userRoles[1].role" cssclass="error" /> </td>
    </tr>
    <tr>
    	<td>Permission 3:</td>
    	<td><form:input path="userRoles[2].role" value="None" /></td>
    	<td><form:errors path="userRoles[2].role" cssclass="error" /> </td>
    </tr>
    <tr>
    	<td>Permission 4:</td>
    	<td><form:input path="userRoles[3].role" value="None" /></td>
    	<td><form:errors path="userRoles[3].role" cssclass="error" /> </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Register account"/>
        </td>
    </tr>
</table>
 
</form:form>
</body>
</html>