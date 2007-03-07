# The Grinder 3.0-beta32
# HTTP script recorded by TCPProxy at 2007/02/27 19:19:47

from net.grinder.script import Test
from net.grinder.script.Grinder import grinder
from net.grinder.plugin.http import HTTPPluginControl, HTTPRequest
from HTTPClient import NVPair
connectionDefaults = HTTPPluginControl.getConnectionDefaults()
httpUtilities = HTTPPluginControl.getHTTPUtilities()

# To use a proxy server, uncomment the next line and set the host and port.
# connectionDefaults.setProxyServer("localhost", 8001)

# These definitions at the top level of the file are evaluated once,
# when the worker process is started.

connectionDefaults.defaultHeaders = \
  ( NVPair('User-Agent', 'Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)'),
    NVPair('Accept-Language', 'ja'), )

headers0= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'), )

headers1= \
  ( NVPair('Accept', '*/*'),
    NVPair('Referer', 'http://localhost:8080/employee-kuina/view/emp/empList.html'), )

headers2= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-kuina/view/emp/empList.html'), )

headers3= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-kuina/view/emp/empEdit.html'), )

headers4= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-kuina/view/emp/empConfirm.html'), )

headers5= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-kuina/view/emp/empEdit.html?crudType=2&id=17&versionNo=0'), )

headers6= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-kuina/view/emp/empConfirm.html?crudType=1&id=17&versionNo=1'), )

headers7= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-kuina/view/emp/empConfirm.html?crudType=3&id=17&versionNo=1'), )

url0 = 'http://localhost:8080'

# Create an HTTPRequest for each request, then replace the
# reference to the HTTPRequest with an instrumented version.
# You can access the unadorned instance using request101.__target__.
request101 = HTTPRequest(url=url0, headers=headers0)
request101 = Test(101, 'GET empList.html').wrap(request101)

request102 = HTTPRequest(url=url0, headers=headers1)
request102 = Test(102, 'GET global.css').wrap(request102)

request201 = HTTPRequest(url=url0, headers=headers2)
request201 = Test(201, 'POST empList.html').wrap(request201)

request202 = HTTPRequest(url=url0, headers=headers2)
request202 = Test(202, 'GET empEdit.html').wrap(request202)

request301 = HTTPRequest(url=url0, headers=headers3)
request301 = Test(301, 'POST empEdit.html').wrap(request301)

request302 = HTTPRequest(url=url0, headers=headers3)
request302 = Test(302, 'GET empConfirm.html').wrap(request302)

request401 = HTTPRequest(url=url0, headers=headers4)
request401 = Test(401, 'POST empConfirm.html').wrap(request401)

request402 = HTTPRequest(url=url0, headers=headers4)
request402 = Test(402, 'GET empList.html').wrap(request402)

request501 = HTTPRequest(url=url0, headers=headers2)
request501 = Test(501, 'GET empEdit.html').wrap(request501)

request601 = HTTPRequest(url=url0, headers=headers5)
request601 = Test(601, 'POST empEdit.html').wrap(request601)

request602 = HTTPRequest(url=url0, headers=headers5)
request602 = Test(602, 'GET empConfirm.html').wrap(request602)

request701 = HTTPRequest(url=url0, headers=headers4)
request701 = Test(701, 'POST empConfirm.html').wrap(request701)

request702 = HTTPRequest(url=url0, headers=headers4)
request702 = Test(702, 'GET empList.html').wrap(request702)

request801 = HTTPRequest(url=url0, headers=headers2)
request801 = Test(801, 'GET empConfirm.html').wrap(request801)

request901 = HTTPRequest(url=url0, headers=headers6)
request901 = Test(901, 'POST empConfirm.html').wrap(request901)

request902 = HTTPRequest(url=url0, headers=headers6)
request902 = Test(902, 'GET empList.html').wrap(request902)

request1001 = HTTPRequest(url=url0, headers=headers2)
request1001 = Test(1001, 'GET empConfirm.html').wrap(request1001)

request1101 = HTTPRequest(url=url0, headers=headers7)
request1101 = Test(1101, 'POST empConfirm.html').wrap(request1101)

request1102 = HTTPRequest(url=url0, headers=headers7)
request1102 = Test(1102, 'GET empList.html').wrap(request1102)

request1201 = HTTPRequest(url=url0, headers=headers2)
request1201 = Test(1201, 'GET empConfirm.html').wrap(request1201)

request1301 = HTTPRequest(url=url0, headers=headers7)
request1301 = Test(1301, 'POST empConfirm.html').wrap(request1301)

request1302 = HTTPRequest(url=url0, headers=headers7)
request1302 = Test(1302, 'GET empList.html').wrap(request1302)


class TestRunner:
  """A TestRunner instance is created for each worker thread."""

  # A method for each recorded page.
  def page1(self):
    """GET empList.html (requests 101-102)."""
    result = request101.GET('/employee-kuina/view/emp/empList.html')
    self.token_jsessionid = \
      httpUtilities.valueFromBodyURI('jsessionid') # 'A9C20F4758B8BAC13CFC7AFB6A9DC822'
    # 29 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 40 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    self.token_versionNo = \
      httpUtilities.valueFromBodyURI('versionNo') # '0'
    self.token_EmpListFormviewempempListhtml = \
      httpUtilities.valueFromHiddenInput('EmpListForm/view/emp/empList.html') # 'EmpListForm'

    grinder.sleep(438)
    request102.GET('/employee-kuina/css/global.css', None,
      ( NVPair('If-Modified-Since', 'Wed, 21 Feb 2007 08:12:51 GMT'),
        NVPair('If-None-Match', 'W/\"1363-1172045571168\"'), ))

    return result

  def page2(self):
    """POST empList.html (requests 201-202)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request201.POST('/employee-kuina/view/emp/empList.html;jsessionid=' +
      self.token_jsessionid,
      ( NVPair('EmpListForm:goEmpEdit', 'Create'),
        NVPair('EmpListForm/view/emp/empList.html', self.token_EmpListFormviewempempListhtml), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    grinder.sleep(31)
    request202.GET('/employee-kuina/view/emp/empEdit.html')
    self.token_EmpEditFormcrudType = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:crudType') # '0'
    self.token_EmpEditFormisNotReadTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:isNotReadTeedaHidden') # 'true'
    self.token_EmpEditFormviewempempEdithtml = \
      httpUtilities.valueFromHiddenInput('EmpEditForm/view/emp/empEdit.html') # 'EmpEditForm'

    return result

  def page3(self):
    """POST empEdit.html (requests 301-302)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request301.POST('/employee-kuina/view/emp/empEdit.html',
      ( NVPair('EmpEditForm:crudType', self.token_EmpEditFormcrudType),
        NVPair('EmpEditForm:empNo', '90'),
        NVPair('EmpEditForm:empName', 'EMP_NAME'),
        NVPair('EmpEditForm:mgrId', '80'),
        NVPair('EmpEditForm:hiredate', '2007/12/21'),
        NVPair('EmpEditForm:sal', '70'),
        NVPair('EmpEditForm:deptId', '60'),
        NVPair('EmpEditForm:isNotReadTeedaHidden', self.token_EmpEditFormisNotReadTeedaHidden),
        NVPair('EmpEditForm:goEmpConfirm', 'Confirm'),
        NVPair('EmpEditForm/view/emp/empEdit.html', self.token_EmpEditFormviewempempEdithtml), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    grinder.sleep(15)
    request302.GET('/employee-kuina/view/emp/empConfirm.html')
    self.token_EmpConfirmFormcrudType = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:crudType') # '0'
    self.token_EmpConfirmFormempNohidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:empNo-hidden') # '90'
    self.token_EmpConfirmFormempNamehidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:empName-hidden') # 'EMP_NAME'
    self.token_EmpConfirmFormmgrIdhidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:mgrId-hidden') # '80'
    self.token_EmpConfirmFormhiredatehidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:hiredate-hidden') # '2007/12/21'
    self.token_EmpConfirmFormsalhidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:sal-hidden') # '70'
    self.token_EmpConfirmFormdeptIdhidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:deptId-hidden') # '60'
    self.token_EmpConfirmFormisNotComeFromListTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isNotComeFromListTeedaHidden') # 'true'
    self.token_EmpConfirmFormisNotReadTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isNotReadTeedaHidden') # 'true'
    self.token_EmpConfirmFormisCreateTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isCreateTeedaHidden') # 'true'
    self.token_EmpConfirmFormviewempempConfirmhtml = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm/view/emp/empConfirm.html') # 'EmpConfirmForm'

    return result

  def page4(self):
    """POST empConfirm.html (requests 401-402)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request401.POST('/employee-kuina/view/emp/empConfirm.html',
      ( NVPair('EmpConfirmForm:crudType', self.token_EmpConfirmFormcrudType),
        NVPair('EmpConfirmForm:empNo-hidden', self.token_EmpConfirmFormempNohidden),
        NVPair('EmpConfirmForm:empName-hidden', self.token_EmpConfirmFormempNamehidden),
        NVPair('EmpConfirmForm:mgrId-hidden', self.token_EmpConfirmFormmgrIdhidden),
        NVPair('EmpConfirmForm:hiredate-hidden', self.token_EmpConfirmFormhiredatehidden),
        NVPair('EmpConfirmForm:sal-hidden', self.token_EmpConfirmFormsalhidden),
        NVPair('EmpConfirmForm:deptId-hidden', self.token_EmpConfirmFormdeptIdhidden),
        NVPair('EmpConfirmForm:isNotComeFromListTeedaHidden', self.token_EmpConfirmFormisNotComeFromListTeedaHidden),
        NVPair('EmpConfirmForm:isNotReadTeedaHidden', self.token_EmpConfirmFormisNotReadTeedaHidden),
        NVPair('EmpConfirmForm:isCreateTeedaHidden', self.token_EmpConfirmFormisCreateTeedaHidden),
        NVPair('EmpConfirmForm:doCreate', 'Finish'),
        NVPair('EmpConfirmForm/view/emp/empConfirm.html', self.token_EmpConfirmFormviewempempConfirmhtml), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    grinder.sleep(16)
    request402.GET('/employee-kuina/view/emp/empList.html')
    # 30 different values for token_crudType found in response; the first matched
    # the last known value of token_crudType - don't update the variable.
    # 42 different values for token_id found in response; the first matched
    # the last known value of token_id - don't update the variable.

    return result

  def page5(self):
    """GET empEdit.html (request 501)."""
    self.token_id = \
      '17'
    result = request501.GET('/employee-kuina/view/emp/empEdit.html' +
      '?crudType=' +
      self.token_crudType +
      '&id=' +
      self.token_id +
      '&versionNo=' +
      self.token_versionNo)
    self.token_EmpEditFormcrudType = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:crudType') # '2'
    self.token_EmpEditFormisNotCreateidLabelTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:isNotCreate-idLabelTeedaHidden') # 'true'
    self.token_EmpEditFormisNotCreateidHiddenTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:isNotCreate-idHiddenTeedaHidden') # 'true'
    self.token_EmpEditFormidhidden = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:id-hidden') # '17'
    self.token_EmpEditFormisNotCreateversionNoLabelTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:isNotCreate-versionNoLabelTeedaHidden') # 'true'
    self.token_EmpEditFormisNotCreateversionNoHiddenTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:isNotCreate-versionNoHiddenTeedaHidden') # 'true'
    self.token_EmpEditFormversionNohidden = \
      httpUtilities.valueFromHiddenInput('EmpEditForm:versionNo-hidden') # '0'

    return result

  def page6(self):
    """POST empEdit.html (requests 601-602)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request601.POST('/employee-kuina/view/emp/empEdit.html',
      ( NVPair('EmpEditForm:crudType', self.token_EmpEditFormcrudType),
        NVPair('EmpEditForm:isNotCreate-idLabelTeedaHidden', self.token_EmpEditFormisNotCreateidLabelTeedaHidden),
        NVPair('EmpEditForm:isNotCreate-idHiddenTeedaHidden', self.token_EmpEditFormisNotCreateidHiddenTeedaHidden),
        NVPair('EmpEditForm:id-hidden', self.token_EmpEditFormidhidden),
        NVPair('EmpEditForm:empNo', '91'),
        NVPair('EmpEditForm:empName', 'EMP_NAME'),
        NVPair('EmpEditForm:mgrId', '81'),
        NVPair('EmpEditForm:hiredate', '2007/12/21'),
        NVPair('EmpEditForm:sal', '71.00'),
        NVPair('EmpEditForm:deptId', '61'),
        NVPair('EmpEditForm:isNotCreate-versionNoLabelTeedaHidden', self.token_EmpEditFormisNotCreateversionNoLabelTeedaHidden),
        NVPair('EmpEditForm:isNotCreate-versionNoHiddenTeedaHidden', self.token_EmpEditFormisNotCreateversionNoHiddenTeedaHidden),
        NVPair('EmpEditForm:versionNo-hidden', self.token_EmpEditFormversionNohidden),
        NVPair('EmpEditForm:isNotReadTeedaHidden', self.token_EmpEditFormisNotReadTeedaHidden),
        NVPair('EmpEditForm:goEmpConfirm', 'Confirm'),
        NVPair('EmpEditForm/view/emp/empEdit.html', self.token_EmpEditFormviewempempEdithtml), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    grinder.sleep(16)
    request602.GET('/employee-kuina/view/emp/empConfirm.html')
    self.token_EmpConfirmFormcrudType = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:crudType') # '2'
    self.token_EmpConfirmFormisNotCreateidLabelTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isNotCreate-idLabelTeedaHidden') # 'true'
    self.token_EmpConfirmFormisNotCreateidHiddenTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isNotCreate-idHiddenTeedaHidden') # 'true'
    self.token_EmpConfirmFormidhidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:id-hidden') # '17'
    self.token_EmpConfirmFormempNohidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:empNo-hidden') # '91'
    self.token_EmpConfirmFormmgrIdhidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:mgrId-hidden') # '81'
    self.token_EmpConfirmFormsalhidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:sal-hidden') # '71.00'
    self.token_EmpConfirmFormdeptIdhidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:deptId-hidden') # '61'
    self.token_EmpConfirmFormisNotCreateversionNoLabelTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isNotCreate-versionNoLabelTeedaHidden') # 'true'
    self.token_EmpConfirmFormisNotCreateversionNoHiddenTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isNotCreate-versionNoHiddenTeedaHidden') # 'true'
    self.token_EmpConfirmFormversionNohidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:versionNo-hidden') # '0'
    self.token_EmpConfirmFormisNotCreateTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isNotCreateTeedaHidden') # 'true'

    return result

  def page7(self):
    """POST empConfirm.html (requests 701-702)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request701.POST('/employee-kuina/view/emp/empConfirm.html',
      ( NVPair('EmpConfirmForm:crudType', self.token_EmpConfirmFormcrudType),
        NVPair('EmpConfirmForm:isNotCreate-idLabelTeedaHidden', self.token_EmpConfirmFormisNotCreateidLabelTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreate-idHiddenTeedaHidden', self.token_EmpConfirmFormisNotCreateidHiddenTeedaHidden),
        NVPair('EmpConfirmForm:id-hidden', self.token_EmpConfirmFormidhidden),
        NVPair('EmpConfirmForm:empNo-hidden', self.token_EmpConfirmFormempNohidden),
        NVPair('EmpConfirmForm:empName-hidden', self.token_EmpConfirmFormempNamehidden),
        NVPair('EmpConfirmForm:mgrId-hidden', self.token_EmpConfirmFormmgrIdhidden),
        NVPair('EmpConfirmForm:hiredate-hidden', self.token_EmpConfirmFormhiredatehidden),
        NVPair('EmpConfirmForm:sal-hidden', self.token_EmpConfirmFormsalhidden),
        NVPair('EmpConfirmForm:deptId-hidden', self.token_EmpConfirmFormdeptIdhidden),
        NVPair('EmpConfirmForm:isNotCreate-versionNoLabelTeedaHidden', self.token_EmpConfirmFormisNotCreateversionNoLabelTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreate-versionNoHiddenTeedaHidden', self.token_EmpConfirmFormisNotCreateversionNoHiddenTeedaHidden),
        NVPair('EmpConfirmForm:versionNo-hidden', self.token_EmpConfirmFormversionNohidden),
        NVPair('EmpConfirmForm:isNotComeFromListTeedaHidden', self.token_EmpConfirmFormisNotComeFromListTeedaHidden),
        NVPair('EmpConfirmForm:isNotReadTeedaHidden', self.token_EmpConfirmFormisNotReadTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreateTeedaHidden', self.token_EmpConfirmFormisNotCreateTeedaHidden),
        NVPair('EmpConfirmForm:doUpdate', 'Finish'),
        NVPair('EmpConfirmForm/view/emp/empConfirm.html', self.token_EmpConfirmFormviewempempConfirmhtml), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    grinder.sleep(16)
    request702.GET('/employee-kuina/view/emp/empList.html')
    # 30 different values for token_crudType found in response; the first matched
    # the last known value of token_crudType - don't update the variable.
    # 43 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    # 3 different values for token_versionNo found in response; the first matched
    # the last known value of token_versionNo - don't update the variable.

    return result

  def page8(self):
    """GET empConfirm.html (request 801)."""
    self.token_crudType = \
      '1'
    self.token_id = \
      '17'
    self.token_versionNo = \
      '1'
    result = request801.GET('/employee-kuina/view/emp/empConfirm.html' +
      '?crudType=' +
      self.token_crudType +
      '&id=' +
      self.token_id +
      '&versionNo=' +
      self.token_versionNo)
    self.token_EmpConfirmFormcrudType = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:crudType') # '1'
    self.token_EmpConfirmFormversionNohidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:versionNo-hidden') # '1'
    self.token_EmpConfirmFormisComeFromListTeedaHidden = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:isComeFromListTeedaHidden') # 'true'

    return result

  def page9(self):
    """POST empConfirm.html (requests 901-902)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request901.POST('/employee-kuina/view/emp/empConfirm.html',
      ( NVPair('EmpConfirmForm:crudType', self.token_EmpConfirmFormcrudType),
        NVPair('EmpConfirmForm:isNotCreate-idLabelTeedaHidden', self.token_EmpConfirmFormisNotCreateidLabelTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreate-idHiddenTeedaHidden', self.token_EmpConfirmFormisNotCreateidHiddenTeedaHidden),
        NVPair('EmpConfirmForm:id-hidden', self.token_EmpConfirmFormidhidden),
        NVPair('EmpConfirmForm:empNo-hidden', self.token_EmpConfirmFormempNohidden),
        NVPair('EmpConfirmForm:empName-hidden', self.token_EmpConfirmFormempNamehidden),
        NVPair('EmpConfirmForm:mgrId-hidden', self.token_EmpConfirmFormmgrIdhidden),
        NVPair('EmpConfirmForm:hiredate-hidden', self.token_EmpConfirmFormhiredatehidden),
        NVPair('EmpConfirmForm:sal-hidden', self.token_EmpConfirmFormsalhidden),
        NVPair('EmpConfirmForm:deptId-hidden', self.token_EmpConfirmFormdeptIdhidden),
        NVPair('EmpConfirmForm:isNotCreate-versionNoLabelTeedaHidden', self.token_EmpConfirmFormisNotCreateversionNoLabelTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreate-versionNoHiddenTeedaHidden', self.token_EmpConfirmFormisNotCreateversionNoHiddenTeedaHidden),
        NVPair('EmpConfirmForm:versionNo-hidden', self.token_EmpConfirmFormversionNohidden),
        NVPair('EmpConfirmForm:isComeFromListTeedaHidden', self.token_EmpConfirmFormisComeFromListTeedaHidden),
        NVPair('EmpConfirmForm:jumpEmpList', 'Previous'),
        NVPair('EmpConfirmForm/view/emp/empConfirm.html', self.token_EmpConfirmFormviewempempConfirmhtml), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    grinder.sleep(16)
    request902.GET('/employee-kuina/view/emp/empList.html')
    # 31 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 43 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    # 4 different values for token_versionNo found in response, using the first one.
    self.token_versionNo = \
      httpUtilities.valueFromBodyURI('versionNo') # '0'

    return result

  def page10(self):
    """GET empConfirm.html (request 1001)."""
    self.token_crudType = \
      '3'
    self.token_id = \
      '17'
    self.token_versionNo = \
      '1'
    result = request1001.GET('/employee-kuina/view/emp/empConfirm.html' +
      '?crudType=' +
      self.token_crudType +
      '&id=' +
      self.token_id +
      '&versionNo=' +
      self.token_versionNo)
    self.token_EmpConfirmFormcrudType = \
      httpUtilities.valueFromHiddenInput('EmpConfirmForm:crudType') # '3'

    return result

  def page11(self):
    """POST empConfirm.html (requests 1101-1102)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request1101.POST('/employee-kuina/view/emp/empConfirm.html',
      ( NVPair('EmpConfirmForm:crudType', self.token_EmpConfirmFormcrudType),
        NVPair('EmpConfirmForm:isNotCreate-idLabelTeedaHidden', self.token_EmpConfirmFormisNotCreateidLabelTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreate-idHiddenTeedaHidden', self.token_EmpConfirmFormisNotCreateidHiddenTeedaHidden),
        NVPair('EmpConfirmForm:id-hidden', self.token_EmpConfirmFormidhidden),
        NVPair('EmpConfirmForm:empNo-hidden', self.token_EmpConfirmFormempNohidden),
        NVPair('EmpConfirmForm:empName-hidden', self.token_EmpConfirmFormempNamehidden),
        NVPair('EmpConfirmForm:mgrId-hidden', self.token_EmpConfirmFormmgrIdhidden),
        NVPair('EmpConfirmForm:hiredate-hidden', self.token_EmpConfirmFormhiredatehidden),
        NVPair('EmpConfirmForm:sal-hidden', self.token_EmpConfirmFormsalhidden),
        NVPair('EmpConfirmForm:deptId-hidden', self.token_EmpConfirmFormdeptIdhidden),
        NVPair('EmpConfirmForm:isNotCreate-versionNoLabelTeedaHidden', self.token_EmpConfirmFormisNotCreateversionNoLabelTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreate-versionNoHiddenTeedaHidden', self.token_EmpConfirmFormisNotCreateversionNoHiddenTeedaHidden),
        NVPair('EmpConfirmForm:versionNo-hidden', self.token_EmpConfirmFormversionNohidden),
        NVPair('EmpConfirmForm:isComeFromListTeedaHidden', self.token_EmpConfirmFormisComeFromListTeedaHidden),
        NVPair('EmpConfirmForm:jumpEmpList', 'Previous'),
        NVPair('EmpConfirmForm:isNotReadTeedaHidden', self.token_EmpConfirmFormisNotReadTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreateTeedaHidden', self.token_EmpConfirmFormisNotCreateTeedaHidden),
        NVPair('EmpConfirmForm/view/emp/empConfirm.html', self.token_EmpConfirmFormviewempempConfirmhtml), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    grinder.sleep(15)
    request1102.GET('/employee-kuina/view/emp/empList.html')
    # 31 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 43 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    # 4 different values for token_versionNo found in response, using the first one.
    self.token_versionNo = \
      httpUtilities.valueFromBodyURI('versionNo') # '0'

    return result

  def page12(self):
    """GET empConfirm.html (request 1201)."""
    self.token_crudType = \
      '3'
    self.token_id = \
      '17'
    self.token_versionNo = \
      '1'
    result = request1201.GET('/employee-kuina/view/emp/empConfirm.html' +
      '?crudType=' +
      self.token_crudType +
      '&id=' +
      self.token_id +
      '&versionNo=' +
      self.token_versionNo)

    return result

  def page13(self):
    """POST empConfirm.html (requests 1301-1302)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request1301.POST('/employee-kuina/view/emp/empConfirm.html',
      ( NVPair('EmpConfirmForm:crudType', self.token_EmpConfirmFormcrudType),
        NVPair('EmpConfirmForm:isNotCreate-idLabelTeedaHidden', self.token_EmpConfirmFormisNotCreateidLabelTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreate-idHiddenTeedaHidden', self.token_EmpConfirmFormisNotCreateidHiddenTeedaHidden),
        NVPair('EmpConfirmForm:id-hidden', self.token_EmpConfirmFormidhidden),
        NVPair('EmpConfirmForm:empNo-hidden', self.token_EmpConfirmFormempNohidden),
        NVPair('EmpConfirmForm:empName-hidden', self.token_EmpConfirmFormempNamehidden),
        NVPair('EmpConfirmForm:mgrId-hidden', self.token_EmpConfirmFormmgrIdhidden),
        NVPair('EmpConfirmForm:hiredate-hidden', self.token_EmpConfirmFormhiredatehidden),
        NVPair('EmpConfirmForm:sal-hidden', self.token_EmpConfirmFormsalhidden),
        NVPair('EmpConfirmForm:deptId-hidden', self.token_EmpConfirmFormdeptIdhidden),
        NVPair('EmpConfirmForm:isNotCreate-versionNoLabelTeedaHidden', self.token_EmpConfirmFormisNotCreateversionNoLabelTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreate-versionNoHiddenTeedaHidden', self.token_EmpConfirmFormisNotCreateversionNoHiddenTeedaHidden),
        NVPair('EmpConfirmForm:versionNo-hidden', self.token_EmpConfirmFormversionNohidden),
        NVPair('EmpConfirmForm:isComeFromListTeedaHidden', self.token_EmpConfirmFormisComeFromListTeedaHidden),
        NVPair('EmpConfirmForm:isNotReadTeedaHidden', self.token_EmpConfirmFormisNotReadTeedaHidden),
        NVPair('EmpConfirmForm:isNotCreateTeedaHidden', self.token_EmpConfirmFormisNotCreateTeedaHidden),
        NVPair('EmpConfirmForm:doUpdate', 'Finish'),
        NVPair('EmpConfirmForm/view/emp/empConfirm.html', self.token_EmpConfirmFormviewempempConfirmhtml), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    grinder.sleep(15)
    request1302.GET('/employee-kuina/view/emp/empList.html')
    # 29 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 40 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    self.token_versionNo = \
      httpUtilities.valueFromBodyURI('versionNo') # '0'

    return result

  def __call__(self):
    """This method is called for every run performed by the worker thread."""
    self.page1()      # GET empList.html (requests 101-102)

    grinder.sleep(939)
    self.page2()      # POST empList.html (requests 201-202)

    grinder.sleep(17471)
    self.page3()      # POST empEdit.html (requests 301-302)

    grinder.sleep(1126)
    self.page4()      # POST empConfirm.html (requests 401-402)

    grinder.sleep(3675)
    self.page5()      # GET empEdit.html (request 501)

    grinder.sleep(7742)
    self.page6()      # POST empEdit.html (requests 601-602)

    grinder.sleep(1094)
    self.page7()      # POST empConfirm.html (requests 701-702)

    grinder.sleep(2690)
    self.page8()      # GET empConfirm.html (request 801)

    grinder.sleep(1048)
    self.page9()      # POST empConfirm.html (requests 901-902)

    grinder.sleep(2487)
    self.page10()     # GET empConfirm.html (request 1001)

    grinder.sleep(1205)
    self.page11()     # POST empConfirm.html (requests 1101-1102)

    grinder.sleep(2158)
    self.page12()     # GET empConfirm.html (request 1201)

    grinder.sleep(844)
    self.page13()     # POST empConfirm.html (requests 1301-1302)


def instrumentMethod(test, method_name, c=TestRunner):
  """Instrument a method with the given Test."""
  unadorned = getattr(c, method_name)
  import new
  method = new.instancemethod(test.wrap(unadorned), None, c)
  setattr(c, method_name, method)

# Replace each method with an instrumented version.
# You can call the unadorned method using self.page1.__target__().
instrumentMethod(Test(100, 'Page 1'), 'page1')
instrumentMethod(Test(200, 'Page 2'), 'page2')
instrumentMethod(Test(300, 'Page 3'), 'page3')
instrumentMethod(Test(400, 'Page 4'), 'page4')
instrumentMethod(Test(500, 'Page 5'), 'page5')
instrumentMethod(Test(600, 'Page 6'), 'page6')
instrumentMethod(Test(700, 'Page 7'), 'page7')
instrumentMethod(Test(800, 'Page 8'), 'page8')
instrumentMethod(Test(900, 'Page 9'), 'page9')
instrumentMethod(Test(1000, 'Page 10'), 'page10')
instrumentMethod(Test(1100, 'Page 11'), 'page11')
instrumentMethod(Test(1200, 'Page 12'), 'page12')
instrumentMethod(Test(1300, 'Page 13'), 'page13')
