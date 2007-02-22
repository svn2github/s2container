<%@ include file="/WEB-INF/jsp/includes.jsp" 
%><%@ include file="/WEB-INF/jsp/header.jsp" %>
<html:form action="/empEdit"><input type="hidden" name="crudType" value="0"/><html:submit>Create</html:submit></html:form>
<table id="empGridXY" height="200px" border="1">
	<colgroup>
		<col span="1" width="60px" class="T_leftFixed" />
	</colgroup>
	<thead>
		<tr height="50px">
			<th class="right"><label id="idLabel">id</label></th>
			<th class="right"><label id="empNoLabel">empNo</label></th>
			<th><label id="empNameLabel">empName</label></th>
			<th class="right"><label id="mgrIdLabel">mgrId</label></th>
			<th><label id="hiredateLabel">hiredate</label></th>
			<th class="right"><label id="salLabel">sal</label></th>
			<th class="right"><label id="deptIdLabel">deptId</label></th>
			<th class="right"><label id="versionNoLabel">versionNo</label></th>
			<th><br/></th>
		</tr>
	</thead>
	<tbody><c:forEach var="empItem" items="${EmpListForm.empItems}" varStatus="status">
		<c:choose><c:when test="${status.index % 2 == 1 }"><tr class="row_odd"></c:when><c:otherwise><tr class="row_even"></c:otherwise></c:choose>
			<td class="right"><bean:write name="empItem" property="id" /></td>
			<td class="right"><bean:write name="empItem" property="empNo" /></td>
			<td><bean:write name="empItem" property="empName" /></td>
			<td class="right"><bean:write name="empItem" property="mgrId" /></td>
			<td><bean:write name="empItem" property="hiredate" /></td>
			<td class="right"><bean:write name="empItem" property="sal" /></td>
			<td class="right"><bean:write name="empItem" property="deptId" /></td>
			<td class="right"><bean:write name="empItem" property="versionNo" /></td>
			<td><a href="empEdit.do?crudType=2&id=<bean:write name="empItem" property="id"/>&versionNo=<bean:write name="empItem" property="versionNo"/>">Edit</a>
			<a href="empConfirm.do?crudType=3&id=<bean:write name="empItem" property="id"/>&versionNo=<bean:write name="empItem" property="versionNo"/>">Delete</a>
			<a href="empConfirm.do?crudType=1&id=<bean:write name="empItem" property="id"/>&versionNo=<bean:write name="empItem" property="versionNo"/>">Inquire</a>
			</td>
		</tr>
	</c:forEach></tbody>
</table><%@ include file="/WEB-INF/jsp/footer.jsp" %>