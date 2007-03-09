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
			<td><h:inputText value="#{empConfirmPage.id}" rendered="#{empConfirmPage.crudType != 0}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="empNoLabel">empNo</label></td>
			<td><h:inputText value="#{empConfirmPage.empNo}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="empNameLabel">empName</label></td>
			<td><h:inputText value="#{empConfirmPage.empName}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="mgrIdLabel">mgrId</label></td>
			<td><h:inputText value="#{empConfirmPage.mgrId}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="hiredateLabel">hiredate</label></td>
			<td><h:inputText value="#{empConfirmPage.hiredate}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="salLabel">sal</label></td>
			<td><h:inputText value="#{empConfirmPage.sal}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="deptIdLabel">deptId</label></td>
			<td><h:inputText value="#{empConfirmPage.deptId}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="versionNoLabel">versionNo</label></td>
			<td><h:inputText value="#{empConfirmPage.versionNo}" rendered="#{empConfirmPage.crudType != 0}"/></td>
			<td></td>
		</tr>
	</table>	
	
	<h:commandButton value="Previous" action="#{empConfirmPage.doPrevious}"/>	
	<h:commandButton value="Update" action="#{empConfirmPage.doUpdate}">
		<f:param id="confirmCrudType" name="crudType" value="#{empConfirmPage.crudType}"/>	
	</h:commandButton>	


</h:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
</f:view>