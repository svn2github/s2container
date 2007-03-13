<%@page contentType="text/html; charset=UTF-8" 
%><%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" 
%><%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" 
%><f:view><html><f:subview id="headerView"
><%@ include file="/WEB-INF/jsp/header.jsp" 
%></f:subview><body>
<h:form>
	<h:inputHidden value="#{empCreatePage.crudType}"/>
	<table class="tablebg">
		<tr>
			<td><label id="empNoLabel">empNo</label></td>
			<td><h:inputText id="empNo" value="#{empCreatePage.empNo}" required="true"/></td>
			<td><h:message for="empNo"/></td>
		</tr>
		<tr>
			<td><label id="empNameLabel">empName</label></td>
			<td><h:inputText id="empName" value="#{empCreatePage.empName}"/></td>
			<td><h:message for="empName"/></td>
		</tr>
		<tr>
			<td><label id="mgrIdLabel">mgrId</label></td>
			<td><h:inputText id="mgrId" value="#{empCreatePage.mgrId}"/></td>
			<td><h:message for="id"/></td>
		</tr>
		<tr>
			<td><label id="hiredateLabel">hiredate</label></td>
			<td>
				<h:inputText id="hiredate" value="#{empCreatePage.hiredate}">
					<f:convertDateTime pattern="yyyy/MM/dd"/>
				</h:inputText>
			</td>
			<td><h:message for="hiredate"/></td>
		</tr>
		<tr>
			<td><label id="salLabel">sal</label></td>
			<td><h:inputText id="sal" value="#{empCreatePage.sal}"/></td>
			<td><h:message for="sal"/></td>
		</tr>
		<tr>
			<td><label id="deptIdLabel">deptId</label></td>
			<td><h:inputText id="deptId" value="#{empCreatePage.deptId}"/></td>
			<td><h:message for="deptId"/></td>
		</tr>
	</table>
	<h:commandButton value="Previous" action="#{empCreatePage.doPrevious}" immediate="true"/><br/>
	<h:commandButton value="Confirm" action="#{empCreatePage.doConfirm}"/>	
</h:form>
</body></html></f:view>