<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<%@ include file="/WEB-INF/jsp/header.jsp"%>
<html:form action="/empFinish">
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
			<td><bean:write name="EmpForm" property="empNo" /><html:hidden
				property="empNo" /></td>
			<td><html:errors property="empNo" /></td>
		</tr>
		<tr>
			<td><label id="empNameLabel">empName</label></td>
			<td><bean:write name="EmpForm" property="empName" /><html:hidden
				property="empName" /></td>
			<td><html:errors property="empName" /></td>
		</tr>
		<tr>
			<td><label id="mgrIdLabel">mgrId</label></td>
			<td><bean:write name="EmpForm" property="mgrId" /><html:hidden
				property="mgrId" /></td>
			<td><html:errors property="mgrId" /></td>
		</tr>
		<tr>
			<td><label id="hiredateLabel">hiredate</label></td>
			<td><bean:write name="EmpForm" property="hiredate" /><html:hidden
				property="hiredate" /></td>
			<td><html:errors property="hiredate" /></td>
		</tr>
		<tr>
			<td><label id="salLabel">sal</label></td>
			<td><bean:write name="EmpForm" property="sal" /><html:hidden
				property="sal" /></td>
			<td><html:errors property="sal" /></td>
		</tr>
		<tr>
			<td><label id="deptIdLabel">deptId</label></td>
			<td><bean:write name="EmpForm" property="deptId" /><html:hidden
				property="deptId" /></td>
			<td><span id="deptIdMessage"></span></td>
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
		onclick="forms['EmpForm'].action=forms['previus'].action;forms['EmpForm'].submit()" />
	<logic:notEqual name="EmpForm" property="crudType" value="1">
		<html:submit>
			<logic:equal name="EmpForm" property="crudType" value="0">Finish</logic:equal>
			<logic:equal name="EmpForm" property="crudType" value="2">Update</logic:equal>
			<logic:equal name="EmpForm" property="crudType" value="3">Delete</logic:equal>
		</html:submit>
	</logic:notEqual>
</html:form>
<logic:equal name="EmpForm" property="crudType" value="0">
	<html:form action="/empEditBack" styleId="previus" />
</logic:equal><logic:equal name="EmpForm" property="crudType" value="1">
	<html:form action="/empList" styleId="previus" />
</logic:equal><logic:equal name="EmpForm" property="crudType" value="2">
	<html:form action="/empEditBack" styleId="previus" />
</logic:equal><logic:equal name="EmpForm" property="crudType" value="3">
	<html:form action="/empList" styleId="previus" />
</logic:equal><%@ include file="/WEB-INF/jsp/footer.jsp"%>
