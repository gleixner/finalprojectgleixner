<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add a Card</title>
</head>
<body>
<h2>Add a Card</h2>
<form:form method="post" commandName="card">
    <form:hidden path="id"/>
    <table>
    <tr>
        <td>Number:</td>
        <td><form:input path="number" /></td>
        <td><form:errors path="number" cssclass="error" /></td>
    </tr>
    <tr>
        <td>Owner:</td>
        <td><form:input path="owner" /></td>
           <td><form:errors path="owner" cssclass="error" /></td>
     </tr>
    <tr>
        <td>Amount:</td>
        <td><form:input path="amount" /></td>
        <td><form:errors path="amount" cssclass="error" /></td>
    </tr>
    <tr>
    	<td>Street:</td>
    	<td><form:input path="address.street" /></td>
    	<td><form:errors path="address.street" cssclass="error" /> </td>
    </tr>
    <tr>
    	<td>City:</td>
    	<td><form:input path="address.city" /></td>
    	<td><form:errors path="address.city" cssclass="error" /> </td>
    </tr>
    <tr>
    	<td>State:</td>
    	<td><form:input path="address.state" /></td>
    	<td><form:errors path="address.state" cssclass="error" /> </td>
    </tr>
    <tr>
    	<td>Phone:</td>
    	<td><form:input path="phoneNumbers[0].number" /></td>
    	<td><form:errors path="phoneNumbers[0].number" cssclass="error" /> </td>
    </tr>
    <tr>
    	<td>Alternate Phone:</td>
    	<td><form:input path="phoneNumbers[1].number" /></td>
    	<td><form:errors path="phoneNumbers[1].number" cssclass="error" /> </td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Register Card"/>
        </td>
    </tr>
</table>
 
</form:form>
</body>
</html>