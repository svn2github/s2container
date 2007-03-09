<html>





<html>
<body>
<form>

<input type="submit" actionListener="#{empListPage.fireCreate}" action="#{empListPage.doCreate}" value="Create"></input>

<table var="emp" value="#{empListPage.empItems}" rowClasses="row_odd, row_even" border="1"><tr><th>
      <span>id</span>
    </th><th>
      <span>empNo</span>
    </th><th>
      <span>mgrId</span>
    </th><th>
      <span>hiredate</span>
    </th><th>
      <span>sal</span>
    </th><th>
      <span>deptId</span>
    </th><th>
      <span>versionNo</span>
    </th><th>
      <span></span>
    </th></tr><tr><td><span>#{emp.id}</span></td><td><span>#{emp.empNo}</span></td><td><span>#{emp.mgrId}</span></td><td><span>#{emp.hiredate}</span></td><td><span>#{emp.sal}</span></td><td><span>#{emp.deptId}</span></td><td><span>#{emp.versionNo}</span></td><td><h:outputLink value="empEdit">
	    <span>Edit</span>
	    <f:param id="editId" name="id" value="#{emp.id}"/>
	    <f:param id="editVersionNo" name="versionNo" value="#{emp.versionNo}"/>
	    <f:param id="editCrudType" name="crudType" value="2"/>
	</h:outputLink><h:outputLink value="empEdit">
	    <span>Delete</span>
	    <f:param id="deleteId" name="id" value="#{emp.id}"/>
	    <f:param id="deleteVersionNo" name="versionNo" value="#{emp.versionNo}"/>
	    <f:param id="deleteCrudType" name="crudType" value="3"/>
	</h:outputLink><h:outputLink value="empEdit">
	    <span>Inquire</span>
	    <f:param id="inquireId" name="id" value="#{emp.id}"/>
	    <f:param id="inquireVersionNo" name="versionNo" value="#{emp.versionNo}"/>
		<f:param id="inquireCrudType" name="crudType" value="1"/>	
	</h:outputLink></td></tr></table>
</form>
&lt;%@ include file=&quot;/WEB-INF/jsp/footer.jsp&quot; %&gt;
</body></html>