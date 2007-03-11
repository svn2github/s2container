<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page import="org.seasar.employee.spring2.web.CrudType" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<f:view>
<html>
<body>
<h:form>

	<h:inputHidden value="#{empCreateConfirmPage.crudType}"/>

	<table class="tablebg">
		<tr>
			<td><label id="empNoLabel">empNo</label></td>
			<td><h:outputText value="#{empCreateConfirmPage.empNo}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="empNameLabel">empName</label></td>
			<td><h:outputText value="#{empCreateConfirmPage.empName}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="mgrIdLabel">mgrId</label></td>
			<td><h:outputText value="#{empCreateConfirmPage.mgrId}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="hiredateLabel">hiredate</label></td>
			<td><h:outputText value="#{empCreateConfirmPage.hiredate}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="salLabel">sal</label></td>
			<td><h:outputText value="#{empCreateConfirmPage.sal}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="deptIdLabel">deptId</label></td>
			<td><h:outputText value="#{empCreateConfirmPage.deptId}"/></td>
			<td></td>
		</tr>
	</table>	
	
	<h:commandButton value="Previous" action="#{empCreateConfirmPage.doPrevious}"/>	
	<br/>
	<h:commandButton value="Create" action="#{empCreateConfirmPage.doCreate}"/>


</h:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
</f:view>