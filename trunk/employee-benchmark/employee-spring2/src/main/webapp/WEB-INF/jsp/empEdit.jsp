<%@ include file="/WEB-INF/jsp/includes.jsp" 
%><%@ include file="/WEB-INF/jsp/header.jsp" 
%><form id="EmpEditForm"><input type="hidden" id="crudType" />
<div>
<span id="messages"></span>
</div>
<table class="tablebg">
<tr>
    <td><label id="idLabel">id</label></td>
	<td><div id="isCreate">
			<input type="text" id="id"/>
		</div>
		<div id="isNotCreate" style="display: none;">
			<span id="id-out">id</span><input type="hidden" id="id-hidden" />
		</div></td>
	<td><span id="idMessage"></span></td>
</tr>
<tr>
    <td><label id="empNoLabel">empNo</label></td>
	<td><input type="text" id="empNo"/></td>
	<td><span id="empNoMessage"></span></td>
</tr>
<tr>
    <td><label id="empNameLabel">empName</label></td>
	<td><input type="text" id="empName"/></td>
	<td><span id="empNameMessage"></span></td>
</tr>
<tr>
    <td><label id="mgrIdLabel">mgrId</label></td>
	<td><input type="text" id="mgrId"/></td>
	<td><span id="mgrIdMessage"></span></td>
</tr>
<tr>
    <td><label id="hiredateLabel">hiredate</label></td>
	<td><input type="text" id="hiredate" class="T_date"/></td>
	<td><span id="hiredateMessage"></span></td>
</tr>
<tr>
    <td><label id="salLabel">sal</label></td>
	<td><input type="text" id="sal"/></td>
	<td><span id="salMessage"></span></td>
</tr>
<tr>
    <td><label id="deptIdLabel">deptId</label></td>
	<td><input type="text" id="deptId"/></td>
	<td><span id="deptIdMessage"></span></td>
</tr>
<tr>
    <td><label id="versionNoLabel">versionNo</label></td>
	<td><input type="text" id="versionNo"/></td>
	<td><span id="versionNoMessage"></span></td>
</tr>
</table>
<input type="button" id="jumpEmpList" value="Previous"
	onclick="location.href='EmpList.html'"/>
<div id="isNotRead">
<input type="button" id="goEmpConfirm" value="Confirm"
	onclick="location.href='EmpConfirm.html'"/></div>
</form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>