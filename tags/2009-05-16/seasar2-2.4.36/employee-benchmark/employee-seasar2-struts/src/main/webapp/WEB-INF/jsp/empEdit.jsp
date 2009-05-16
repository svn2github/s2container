<%@ include file="/WEB-INF/jsp/includes.jsp"
%><%@ include file="/WEB-INF/jsp/header.jsp" 
%><html:form action="/empConfirm">
	<html:hidden property="crudType" />
	<table class="tablebg">
		<logic:notEqual name="EmpForm" property="crudType" value="0">
			<tr>
				<td><label id="idLabel">id</label></td>
				<td><bean:write name="EmpForm" property="id" /><html:hidden
					property="id" /></td>
				<td><html:errors property="id" /></td>
			</tr>
		</logic:notEqual>
		<tr>
			<td><label id="empNoLabel">empNo</label></td>
			<td><html:text property="empNo" /></td>
			<td><html:errors property="empNo" /></td>
		</tr>
		<tr>
			<td><label id="empNameLabel">empName</label></td>
			<td><html:text property="empName" /></td>
			<td><html:errors property="empName" /></td>
		</tr>
		<tr>
			<td><label id="mgrIdLabel">mgrId</label></td>
			<td><html:text property="mgrId" /></td>
			<td><html:errors property="mgrId" /></td>
		</tr>
		<tr>
			<td><label id="hiredateLabel">hiredate</label></td>
			<td><layout:date property="hiredate" layout="false"
				patternKey="yyyy/MM/dd" /></td>
			<td><html:errors property="hiredate" /></td>
		</tr>
		<tr>
			<td><label id="salLabel">sal</label></td>
			<td><html:text property="sal" /></td>
			<td><html:errors property="sal" /></td>
		</tr>
		<tr>
			<td><label id="deptIdLabel">deptId</label></td>
			<td><html:text property="deptId" /></td>
			<td><html:errors property="deptId" /></td>
		</tr>
		<logic:notEqual name="EmpForm" property="crudType" value="0">
			<tr>
				<td><label id="versionNoLabel">versionNo</label></td>
				<td><bean:write name="EmpForm" property="versionNo" /><html:hidden
					property="versionNo" /></td>
				<td><html:errors property="versionNo" /></td>
			</tr>
		</logic:notEqual>
	</table>
	<input type="button" value="Previous"
		onclick="forms['previus'].submit()" />
	<logic:notEqual name="EmpForm" property="crudType" value="1">
		<html:submit>Confirm</html:submit>
	</logic:notEqual>
</html:form>
<html:form action="/empList" styleId="previus" />
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
