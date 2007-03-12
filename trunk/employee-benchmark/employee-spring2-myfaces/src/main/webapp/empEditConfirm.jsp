<%@ page contentType="text/html; charset=UTF-8" 
%><%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" 
%><%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" 
%><%@ include file="/WEB-INF/jsp/header.jsp" 
%><f:view><html><head>
<script type="text/javascript">
<!--
function displayButton() {
	var crudType =document.all && document.all('form:crudType') || document.getElementById && document.getElementById('form:crudType');
	var updateButton=document.all && document.all('form:updateButton') || document.getElementById && document.getElementById('form:updateButton');
	var deleteButton=document.all && document.all('form:deleteButton') || document.getElementById && document.getElementById('form:deleteButton');
	 if (crudType.value == 2) {
		display(updateButton);
	 } else if (crudType.value == 3) {
		display(deleteButton);
	 }
}
function display(obj) {
	if(obj && obj.style) obj.style.display='';
}
//-->
</script>
</head>
<body onload="displayButton();">
<h:form id="form">
	<h:inputHidden id="crudType" value="#{empEditConfirmPage.crudType}"/>
	<h:inputHidden value="#{empEditConfirmPage.id}"/>
	<h:inputHidden value="#{empEditConfirmPage.empNo}"/>
	<h:inputHidden value="#{empEditConfirmPage.empName}"/>
	<h:inputHidden value="#{empEditConfirmPage.mgrId}"/>
	<h:inputHidden value="#{empEditConfirmPage.hiredate}"/>
	<h:inputHidden value="#{empEditConfirmPage.sal}"/>
	<h:inputHidden value="#{empEditConfirmPage.deptId}"/>
	<h:inputHidden value="#{empEditConfirmPage.versionNo}"/>
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
	<h:commandButton value="Previous" action="#{empEditConfirmPage.doPrevious}"/>
	<h:commandButton id="updateButton" value="Update" action="#{empEditConfirmPage.doUpdate}" style="display:none"/>
	<h:commandButton id="deleteButton" value="Delete" action="#{empEditConfirmPage.doDelete}" style="display:none"/>
</h:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" 
%></body></html></f:view>