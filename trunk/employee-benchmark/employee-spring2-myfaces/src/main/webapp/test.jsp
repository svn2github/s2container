<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

<f:view>
<html>
<body>
<h:form>

<input type="hidden" name="crudType" value="0"/>
<h:commandButton value="Create"/>
<h:dataTable value="#{empListPage.empItems}" var="emp">
  <h:column>
    <f:facet name="header">
      <h:outputText value="id" />
    </f:facet>
    <h:outputText value="#{emp.id}" />
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="empNo" />
    </f:facet>
    <h:outputText value="#{emp.empNo}" />
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="mgrId" />
    </f:facet>
    <h:outputText value="#{emp.mgrId}" />
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="hiredate" />
    </f:facet>
    <h:outputText value="#{emp.hiredate}" />
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
    <h:outputText value="#{emp.deptId}" />
  </h:column>
  <h:column>
    <f:facet name="header">
      <h:outputText value="versionNo" />
    </f:facet>
    <h:outputText value="#{emp.versionNo}" />
  </h:column>    
</h:dataTable>
</h:form>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
</f:view>

