<%@ include file="/WEB-INF/jsp/includes.jsp" 
%><%@ include file="/WEB-INF/jsp/header.jsp" %>
<html:form action="/empEdit"><html:submit>Create</html:submit></html:form><br/>
<html:form action="/empList">
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
	<tbody><c:forEach var="empItem" items="${EmpListForm.empItems}">
		<tr class="row_even">
			<td class="right"><html:text name="empItem" property="id" indexed="true"/></td>
			<td class="right"><html:text name="empItem" property="empNo" indexed="true"/></td>
			<td><html:text name="empItem" property="empName" indexed="true"/></td>
			<td class="right"><html:text name="empItem" property="mgrId" indexed="true"/></td>
			<td><html:text name="empItem" property="hiredate" indexed="true"/></td>
			<td class="right"><html:text name="empItem" property="sal" indexed="true"/></td>
			<td class="right"><html:text name="empItem" property="deptId" indexed="true"/></td>
			<td class="right"><html:text name="empItem" property="versionNo" indexed="true"/></td>
			<td><a href="/empEdit?crudType=2&id=id&versionNo=versionNo">Edit</a>
			<a href="/empConfirm?crudType=3&id=id&versionNo=versionNo">Delete</a>
			<a href="/empConfirm?crudType=1&id=id&versionNo=versionNo">Inquire</a>
			</td>
		</tr>
	</c:forEach></tbody>
</table>
</html:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>