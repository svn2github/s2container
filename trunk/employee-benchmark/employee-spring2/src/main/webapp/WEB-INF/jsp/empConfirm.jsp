<%@ include file="/WEB-INF/jsp/includes.jsp" 
%><%@ include file="/WEB-INF/jsp/header.jsp" 
%><form id="EmpConfirmForm"><input type="hidden" name="crudType" value="<c:out value="${emp.crudType}"/>" />
<spring:bind path="emp"><div>
<span id="messages"><c:out value="${status.errorMessage}"/></span>
</div></spring:bind><spring:nestedPath path="emp">
<table class="tablebg">
<spring:bind path="id"><tr>
    <td><label id="idLabel">id</label></td>
	<td><c:out value="${status.value}"/><input type="hidden" name="id" value="<c:out value="${status.value}"/>" /></td>
	<td><span id="errorMessages"><c:out value="${status.errorMessage}"/></span></td>
</tr></spring:bind>
<spring:bind path="empNo"><tr>
    <td><label id="empNoLabel">empNo</label></td>
	<td><c:out value="${status.value}"/><input type="hidden" name="empNo" value="<c:out value="${status.value}"/>" /></td>
	<td><span id="errorMessages"><c:out value="${status.errorMessage}"/></span></td>
</tr></spring:bind>
<spring:bind path="empName"><tr>
    <td><label id="empNameLabel">empName</label></td>
	<td><c:out value="${status.value}"/><input type="hidden" name="empName" value="<c:out value="${status.value}"/>" /></td>
	<td><span id="errorMessages"><c:out value="${status.errorMessage}"/></span></td>
</tr></spring:bind>
<spring:bind path="mgrId"><tr>
    <td><label id="mgrIdLabel">mgrId</label></td>
	<td><c:out value="${status.value}"/><input type="hidden" name="mgrId" value="<c:out value="${status.value}"/>" /></td>
	<td><span id="errorMessages"><c:out value="${status.errorMessage}"/></span></td>
</tr></spring:bind>
<spring:bind path="hiredate"><tr>
    <td><label id="hiredateLabel">hiredate</label></td>
	<td><fmt:formatDate value="${status.value}" pattern="yyyy-MM-dd"/><input type="hidden" name="hiredate" value="<c:out value="${status.value}"/>" /></td>
	<td><span id="errorMessages"><c:out value="${status.errorMessage}"/></span></td>
</tr></spring:bind>
<spring:bind path="sal"><tr>
    <td><label id="salLabel">sal</label></td>
	<td><c:out value="${status.value}"/><input type="hidden" name="sal" value="<c:out value="${status.value}"/>" /></td>
	<td><span id="errorMessages"><c:out value="${status.errorMessage}"/></span></td>
</tr></spring:bind>
<spring:bind path="deptId"><tr>
    <td><label id="deptIdLabel">deptId</label></td>
	<td><c:out value="${status.value}"/><input type="hidden" name="deptId" value="<c:out value="${status.value}"/>" /></td>
	<td><span id="errorMessages"><c:out value="${status.errorMessage}"/></span></td>
</tr></spring:bind>
<spring:bind path="versionNo"><tr>
    <td><label id="versionNoLabel">versionNo</label></td>
	<td><c:out value="${status.value}"/><input type="hidden" name="versionNo" value="<c:out value="${status.value}"/>" /></td>
	<td><span id="errorMessages"><c:out value="${status.errorMessage}"/></span></td>
</tr></spring:bind>
</table></spring:nestedPath>
<input type="submit" value="Previous" />
<c:if test="${emp.isNotRead}"><input type="submit" value="Finish" /></c:if>
</form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>