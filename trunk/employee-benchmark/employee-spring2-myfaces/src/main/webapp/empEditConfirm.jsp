<%@ page contentType="text/html; charset=UTF-8" 
%><%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" 
%><%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" 
%><%@ include file="/WEB-INF/jsp/header.jsp" 
%><f:view><html><head>
<script type="text/javascript">
<!--
function hideButton() {
	var crudType =document.all && document.all('crudType') || document.getElementById && document.getElementById('crudType');
	var crudTypeValue = crudType.childNodes[0].value;
	var updateButton=document.all && document.all('updateButton') || document.getElementById && document.getElementById('updateButton');
	var deleteButton=document.all && document.all('deleteButton') || document.getElementById && document.getElementById('deleteButton');
	if (crudTypeValue == 1) { 
		hide(updateButton);
		hide(deleteButton);
	 } else if (crudTypeValue == 2) {
		hide(deleteButton);
	 } else if (crudTypeValue == 3) {
		hide(updateButton);
	 }
}

function hide(obj) {
	if(obj && obj.style) obj.style.display='none';
}
//-->
</script>
</head>
<body onload="hideButton();">
<h:form>
	<span id="crudType"><h:inputHidden value="#{empEditConfirmPage.crudType}"/></span>
	<h:inputHidden value="#{empEditConfirmPage.empNo}"/>
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
	<div id="updateButton">
		<h:commandButton value="Update" action="#{empEditConfirmPage.doUpdate}"/>
	</div>
	<div id="deleteButton">
		<h:commandButton value="Delete" action="#{empEditConfirmPage.doDelete}" />
	<div>
</h:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" 
%></body></html></f:view>