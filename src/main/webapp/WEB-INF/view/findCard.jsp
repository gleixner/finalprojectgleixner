<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Find a Card</title>
</head>
<body>
<h2>Find a Card</h2>
<form:form method="post" commandName="criteria">
    <table>
    <tr>
        <td>Owner:</td>
        <td><form:input path="owner" /></td>
    <tr>
    	<td>Amount:</td>
    	<td><form:input path="amount" /></td>
    </tr>
     </tr>
        <tr>
        <td colspan="2">
            <input type="submit" value="Find Card"/>
        </td>
    </tr>
</table>
 
</form:form>
</body>
</html>