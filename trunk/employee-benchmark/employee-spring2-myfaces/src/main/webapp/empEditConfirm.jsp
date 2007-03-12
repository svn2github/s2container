<%@ page contentType="text/html; charset=UTF-8" 
%><%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" 
%><%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" 
%><%@ page import="org.seasar.employee.spring2.web.CrudType" 
%><%@ include file="/WEB-INF/jsp/header.jsp" %>
<f:view><html><body>
<h:form>
	<h:inputHidden value="#{empEditConfirmPage.crudType}"/>
	<table class="tablebg">
		<tr>
			<td><label id="idLabel">id</label></td>
			<td><h:outputText value="#{empEditConfirmPage.id}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="empNoLabel">empNo</label></td>
			<td><h:outputText value="#{empEditConfirmPage.empNo}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="empNameLabel">empName</label></td>
			<td><h:outputText value="#{empEditConfirmPage.empName}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="mgrIdLabel">mgrId</label></td>
			<td><h:outputText value="#{empEditConfirmPage.mgrId}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="hiredateLabel">hiredate</label></td>
			<td><h:outputText value="#{empEditConfirmPage.hiredate}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="salLabel">sal</label></td>
			<td><h:outputText value="#{empEditConfirmPage.sal}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="deptIdLabel">deptId</label></td>
			<td><h:outputText value="#{empEditConfirmPage.deptId}"/></td>
			<td></td>
		</tr>
		<tr>
			<td><label id="versionNoLabel">versionNo</label></td>
			<td><h:outputText value="#{empEditConfirmPage.versionNo}"/></td>
			<td></td>
		</tr>
	</table>
	<h:commandButton value="Previous" action="#{empEditConfirmPage.doPrevious}"/><br/>
	<h:commandButton value="Update" action="#{empEditConfirmPage.doUpdate}" rendered="#{empEditConfirmPage.crudType == 2}"/>
	<h:commandButton value="Delete" action="#{empEditConfirmPage.doDelete}" rendered="#{empEditConfirmPage.crudType == 3}"/>
</h:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" 
%></body></html></f:view>