<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page import="org.seasar.employee.spring2.web.CrudType" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<f:view>
<html>
<body>
<h:form>

	<table class="tablebg">
		<tr>
			<td><label id="idLabel">id</label></td>
			<td><h:inputText value="#{empEditPage.id}" rendered="#{empEditPage.crudType != 0}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="empNoLabel">empNo</label></td>
			<td><h:inputText value="#{empEditPage.empNo}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="empNameLabel">empName</label></td>
			<td><h:inputText value="#{empEditPage.empName}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="mgrIdLabel">mgrId</label></td>
			<td><h:inputText value="#{empEditPage.mgrId}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="hiredateLabel">hiredate</label></td>
			<td><h:inputText value="#{empEditPage.hiredate}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="salLabel">sal</label></td>
			<td><h:inputText value="#{empEditPage.sal}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="deptIdLabel">deptId</label></td>
			<td><h:inputText value="#{empEditPage.deptId}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="versionNoLabel">versionNo</label></td>
			<td><h:inputText value="#{empEditPage.versionNo}" rendered="#{empEditPage.crudType != 0}"/></td>
			<td></td>
		</tr>
	</table>	
	
	<h:commandButton value="Previous" action="#{empEditPage.doPrevious}"/>	
	<h:commandButton value="Confirm" action="#{empEditPage.doConfirm}"/>	

</h:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
</f:view>