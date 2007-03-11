<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ include file="/WEB-INF/jsp/header.jsp" %>

<f:view>
<html>
<body>
<h:form>

<h:commandButton value="Create" action="#{empListPage.doCreate}"/>

<h:dataTable value="#{empListPage.empItems}" var="emp" border="1" headerClass="header2" columnClasses="right, right, left, right, left, right, right, right " rowClasses="row_odd, row_even">
  <h:column>
    <f:facet name="header">
      <h:outputText value="id" />
    </f:facet>
    <h:outputText value="#{emp.id}"/>
  </h:column>
  <h:column>
  	<f:facet name="header">
      <h:outputText value="empNo" />
    </f:facet>
    <h:outputText value="#{emp.empNo}" />
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="empName" />
    </f:facet>
    <h:outputText value="#{emp.empName}" />
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="mgrId" />
    </f:facet>
    <h:outputText value="#{emp.mgrId}" />
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="hiredate"/>
    </f:facet>
    <h:outputText value="#{emp.hiredate}" >
      <f:convertDateTime pattern="yyyy/MM/dd"/>
    </h:outputText>	
  </h:column>      
  <h:column>
    <f:facet name="header">
      <h:outputText value="sal" />
    </f:facet>
    <h:outputText value="#{emp.sal}" />
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="deptId" />
    </f:facet>
    <h:outputText value="#{emp.deptId}"/>
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="versionNo" />
    </f:facet>
    <h:outputText value="#{emp.versionNo}" />
  </h:column>    
  <h:column>
    <f:facet name="header">
      <h:outputText value="" />
    </f:facet>
	<h:commandLink value="Edit" action="#{empListPage.doDetail}">
	    <f:param id="editId" name="id" value="#{emp.id}"/>
	    <f:param id="editVersionNo" name="versionNo" value="#{emp.versionNo}"/>
	    <f:param id="editCrudType" name="crudType" value="2"/>
	</h:commandLink>
	<h:outputText value=" " />
	<h:commandLink value="Delete" action="#{empListPage.doDetail}" >
	    <f:param id="deleteId" name="id" value="#{emp.id}"/>
	    <f:param id="deleteVersionNo" name="versionNo" value="#{emp.versionNo}"/>
	    <f:param id="deleteCrudType" name="crudType" value="3"/>
	</h:commandLink>
	<h:outputText value=" " />
	<h:commandLink value="Inquire" action="#{empListPage.doDetail}">
	    <f:param id="inquireId" name="id" value="#{emp.id}"/>
	    <f:param id="inquireVersionNo" name="versionNo" value="#{emp.versionNo}"/>
		<f:param id="inquireCrudType" name="crudType" value="1"/>	
	</h:commandLink>
  </h:column>   
</h:dataTable>
</h:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
</f:view>

