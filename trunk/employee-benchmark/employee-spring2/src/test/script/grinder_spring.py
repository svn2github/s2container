# The Grinder 3.0-beta32
# HTTP script recorded by TCPProxy at 2007/02/27 19:13:04

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
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empList.do'), )

headers2= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empList.do'), )

headers3= \
  ( NVPair('Accept', '*/*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empEdit.do'), )

headers4= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empEdit.do'), )

headers5= \
  ( NVPair('Accept', '*/*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empConfirm.do'), )

headers6= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empConfirm.do'), )

headers7= \
  ( NVPair('Accept', '*/*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empFinish.do'), )

headers8= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empFinish.do'), )

headers9= \
  ( NVPair('Accept', '*/*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empInquire.do?crudType=1&id=15&versionNo=0'), )

headers10= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empInquire.do?crudType=1&id=15&versionNo=0'), )

headers11= \
  ( NVPair('Accept', '*/*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empEdit.do?crudType=2&id=15&versionNo=0'), )

headers12= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empEdit.do?crudType=2&id=15&versionNo=0'), )

headers13= \
  ( NVPair('Accept', '*/*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empInquire.do?crudType=3&id=15&versionNo=1'), )

headers14= \
  ( NVPair('Accept', 'image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*'),
    NVPair('Referer', 'http://localhost:8080/employee-spring2/empInquire.do?crudType=3&id=15&versionNo=1'), )

url0 = 'http://localhost:8080'

# Create an HTTPRequest for each request, then replace the
# reference to the HTTPRequest with an instrumented version.
# You can access the unadorned instance using request101.__target__.
request101 = HTTPRequest(url=url0, headers=headers0)
request101 = Test(101, 'GET /').wrap(request101)

request102 = HTTPRequest(url=url0, headers=headers0)
request102 = Test(102, 'GET empList.do').wrap(request102)

request201 = HTTPRequest(url=url0, headers=headers1)
request201 = Test(201, 'GET skin1').wrap(request201)

request301 = HTTPRequest(url=url0, headers=headers2)
request301 = Test(301, 'POST empEdit.do').wrap(request301)

request401 = HTTPRequest(url=url0, headers=headers3)
request401 = Test(401, 'GET skin1').wrap(request401)

request501 = HTTPRequest(url=url0, headers=headers4)
request501 = Test(501, 'POST empConfirm.do').wrap(request501)

request601 = HTTPRequest(url=url0, headers=headers5)
request601 = Test(601, 'GET skin1').wrap(request601)

request701 = HTTPRequest(url=url0, headers=headers6)
request701 = Test(701, 'POST empFinish.do').wrap(request701)

request801 = HTTPRequest(url=url0, headers=headers7)
request801 = Test(801, 'GET skin1').wrap(request801)

request901 = HTTPRequest(url=url0, headers=headers8)
request901 = Test(901, 'GET empInquire.do').wrap(request901)

request1001 = HTTPRequest(url=url0, headers=headers9)
request1001 = Test(1001, 'GET skin1').wrap(request1001)

request1101 = HTTPRequest(url=url0, headers=headers10)
request1101 = Test(1101, 'POST empList.do').wrap(request1101)

request1201 = HTTPRequest(url=url0, headers=headers1)
request1201 = Test(1201, 'GET skin1').wrap(request1201)

request1301 = HTTPRequest(url=url0, headers=headers2)
request1301 = Test(1301, 'GET empEdit.do').wrap(request1301)

request1401 = HTTPRequest(url=url0, headers=headers11)
request1401 = Test(1401, 'GET skin1').wrap(request1401)

request1501 = HTTPRequest(url=url0, headers=headers12)
request1501 = Test(1501, 'POST empConfirm.do').wrap(request1501)

request1601 = HTTPRequest(url=url0, headers=headers5)
request1601 = Test(1601, 'GET skin1').wrap(request1601)

request1701 = HTTPRequest(url=url0, headers=headers6)
request1701 = Test(1701, 'POST empFinish.do').wrap(request1701)

request1801 = HTTPRequest(url=url0, headers=headers7)
request1801 = Test(1801, 'GET skin1').wrap(request1801)

request1901 = HTTPRequest(url=url0, headers=headers8)
request1901 = Test(1901, 'GET empInquire.do').wrap(request1901)

request2001 = HTTPRequest(url=url0, headers=headers13)
request2001 = Test(2001, 'GET skin1').wrap(request2001)

request2101 = HTTPRequest(url=url0, headers=headers14)
request2101 = Test(2101, 'POST empList.do').wrap(request2101)

request2201 = HTTPRequest(url=url0, headers=headers1)
request2201 = Test(2201, 'GET skin1').wrap(request2201)

request2301 = HTTPRequest(url=url0, headers=headers2)
request2301 = Test(2301, 'GET empInquire.do').wrap(request2301)

request2401 = HTTPRequest(url=url0, headers=headers13)
request2401 = Test(2401, 'GET skin1').wrap(request2401)

request2501 = HTTPRequest(url=url0, headers=headers14)
request2501 = Test(2501, 'POST empFinish.do').wrap(request2501)

request2601 = HTTPRequest(url=url0, headers=headers7)
request2601 = Test(2601, 'GET skin1').wrap(request2601)


class TestRunner:
  """A TestRunner instance is created for each worker thread."""

  # A method for each recorded page.
  def page1(self):
    """GET / (requests 101-102)."""
    
    # Expecting 302 'Moved Temporarily'
    result = request101.GET('/employee-spring2/')

    grinder.sleep(31)
    request102.GET('/employee-spring2/empList.do')
    # 30 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 40 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    self.token_versionNo = \
      httpUtilities.valueFromBodyURI('versionNo') # '0'

    return result

  def page2(self):
    """GET skin1 (request 201)."""
    result = request201.GET('/employee-spring2/config/skin1')

    return result

  def page3(self):
    """POST empEdit.do (request 301)."""
    result = request301.POST('/employee-spring2/empEdit.do',
      ( NVPair('crudType', '0'), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))
    self.token_crudType = \
      httpUtilities.valueFromHiddenInput('crudType') # '0'

    return result

  def page4(self):
    """GET skin1 (request 401)."""
    result = request401.GET('/employee-spring2/config/skin1')

    return result

  def page5(self):
    """POST empConfirm.do (request 501)."""
    result = request501.POST('/employee-spring2/empConfirm.do',
      ( NVPair('crudType', self.token_crudType),
        NVPair('empNo', '90'),
        NVPair('empName', 'EMP_NAME'),
        NVPair('mgrId', '80'),
        NVPair('hiredate', '2007/02/05'),
        NVPair('calmois', '2'),
        NVPair('calyear', '2007'),
        NVPair('sal', '70'),
        NVPair('deptId', '60'), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))
    self.token_empNo = \
      httpUtilities.valueFromHiddenInput('empNo') # '90'
    self.token_empName = \
      httpUtilities.valueFromHiddenInput('empName') # 'EMP_NAME'
    self.token_mgrId = \
      httpUtilities.valueFromHiddenInput('mgrId') # '80'
    self.token_hiredate = \
      httpUtilities.valueFromHiddenInput('hiredate') # '2007/02/05'
    self.token_sal = \
      httpUtilities.valueFromHiddenInput('sal') # '70'
    self.token_deptId = \
      httpUtilities.valueFromHiddenInput('deptId') # '60'

    return result

  def page6(self):
    """GET skin1 (request 601)."""
    result = request601.GET('/employee-spring2/config/skin1')

    return result

  def page7(self):
    """POST empFinish.do (request 701)."""
    result = request701.POST('/employee-spring2/empFinish.do',
      ( NVPair('crudType', self.token_crudType),
        NVPair('empNo', self.token_empNo),
        NVPair('empNo', self.token_empNo),
        NVPair('empName', self.token_empName),
        NVPair('mgrId', self.token_mgrId),
        NVPair('hiredate', self.token_hiredate),
        NVPair('sal', self.token_sal),
        NVPair('deptId', self.token_deptId), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))
    # 32 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 42 different values for token_id found in response; the first matched
    # the last known value of token_id - don't update the variable.

    return result

  def page8(self):
    """GET skin1 (request 801)."""
    result = request801.GET('/employee-spring2/config/skin1')

    return result

  def page9(self):
    """GET empInquire.do (request 901)."""
    self.token_crudType = \
      '1'
    self.token_id = \
      '15'
    result = request901.GET('/employee-spring2/empInquire.do' +
      '?crudType=' +
      self.token_crudType +
      '&id=' +
      self.token_id +
      '&versionNo=' +
      self.token_versionNo)
    self.token_sal = \
      httpUtilities.valueFromHiddenInput('sal') # '70.00'

    return result

  def page10(self):
    """GET skin1 (request 1001)."""
    result = request1001.GET('/employee-spring2/config/skin1')

    return result

  def page11(self):
    """POST empList.do (request 1101)."""
    result = request1101.POST('/employee-spring2/empList.do',
      ( NVPair('crudType', self.token_crudType),
        NVPair('id', self.token_id),
        NVPair('id', self.token_id),
        NVPair('empNo', self.token_empNo),
        NVPair('empNo', self.token_empNo),
        NVPair('empName', self.token_empName),
        NVPair('mgrId', self.token_mgrId),
        NVPair('hiredate', self.token_hiredate),
        NVPair('sal', self.token_sal),
        NVPair('deptId', self.token_deptId),
        NVPair('versionNo', self.token_versionNo), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))
    # 32 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 43 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'

    return result

  def page12(self):
    """GET skin1 (request 1201)."""
    result = request1201.GET('/employee-spring2/config/skin1')

    return result

  def page13(self):
    """GET empEdit.do (request 1301)."""
    self.token_id = \
      '15'
    result = request1301.GET('/employee-spring2/empEdit.do' +
      '?crudType=' +
      self.token_crudType +
      '&id=' +
      self.token_id +
      '&versionNo=' +
      self.token_versionNo)

    return result

  def page14(self):
    """GET skin1 (request 1401)."""
    result = request1401.GET('/employee-spring2/config/skin1')

    return result

  def page15(self):
    """POST empConfirm.do (request 1501)."""
    self.token_empNo = \
      '91'
    self.token_empName = \
      'EMP_NAME1'
    self.token_mgrId = \
      '81'
    self.token_sal = \
      '71.00'
    self.token_deptId = \
      '61'
    result = request1501.POST('/employee-spring2/empConfirm.do',
      ( NVPair('crudType', self.token_crudType),
        NVPair('id', self.token_id),
        NVPair('empNo', self.token_empNo),
        NVPair('empName', self.token_empName),
        NVPair('mgrId', self.token_mgrId),
        NVPair('hiredate', self.token_hiredate),
        NVPair('calmois', '0'),
        NVPair('sal', self.token_sal),
        NVPair('deptId', self.token_deptId),
        NVPair('versionNo', self.token_versionNo), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))

    return result

  def page16(self):
    """GET skin1 (request 1601)."""
    result = request1601.GET('/employee-spring2/config/skin1')

    return result

  def page17(self):
    """POST empFinish.do (request 1701)."""
    result = request1701.POST('/employee-spring2/empFinish.do',
      ( NVPair('crudType', self.token_crudType),
        NVPair('id', self.token_id),
        NVPair('id', self.token_id),
        NVPair('empNo', self.token_empNo),
        NVPair('empNo', self.token_empNo),
        NVPair('empName', self.token_empName),
        NVPair('mgrId', self.token_mgrId),
        NVPair('hiredate', self.token_hiredate),
        NVPair('sal', self.token_sal),
        NVPair('deptId', self.token_deptId),
        NVPair('versionNo', self.token_versionNo), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))
    # 31 different values for token_crudType found in response; the first matched
    # the last known value of token_crudType - don't update the variable.
    # 43 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    # 3 different values for token_versionNo found in response; the first matched
    # the last known value of token_versionNo - don't update the variable.

    return result

  def page18(self):
    """GET skin1 (request 1801)."""
    result = request1801.GET('/employee-spring2/config/skin1')

    return result

  def page19(self):
    """GET empInquire.do (request 1901)."""
    self.token_crudType = \
      '3'
    self.token_id = \
      '15'
    self.token_versionNo = \
      '1'
    result = request1901.GET('/employee-spring2/empInquire.do' +
      '?crudType=' +
      self.token_crudType +
      '&id=' +
      self.token_id +
      '&versionNo=' +
      self.token_versionNo)

    return result

  def page20(self):
    """GET skin1 (request 2001)."""
    result = request2001.GET('/employee-spring2/config/skin1')

    return result

  def page21(self):
    """POST empList.do (request 2101)."""
    result = request2101.POST('/employee-spring2/empList.do',
      ( NVPair('crudType', self.token_crudType),
        NVPair('id', self.token_id),
        NVPair('id', self.token_id),
        NVPair('empNo', self.token_empNo),
        NVPair('empNo', self.token_empNo),
        NVPair('empName', self.token_empName),
        NVPair('mgrId', self.token_mgrId),
        NVPair('hiredate', self.token_hiredate),
        NVPair('sal', self.token_sal),
        NVPair('deptId', self.token_deptId),
        NVPair('versionNo', self.token_versionNo), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))
    # 32 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 43 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    # 4 different values for token_versionNo found in response, using the first one.
    self.token_versionNo = \
      httpUtilities.valueFromBodyURI('versionNo') # '0'

    return result

  def page22(self):
    """GET skin1 (request 2201)."""
    result = request2201.GET('/employee-spring2/config/skin1')

    return result

  def page23(self):
    """GET empInquire.do (request 2301)."""
    self.token_crudType = \
      '3'
    self.token_id = \
      '15'
    self.token_versionNo = \
      '1'
    result = request2301.GET('/employee-spring2/empInquire.do' +
      '?crudType=' +
      self.token_crudType +
      '&id=' +
      self.token_id +
      '&versionNo=' +
      self.token_versionNo)

    return result

  def page24(self):
    """GET skin1 (request 2401)."""
    result = request2401.GET('/employee-spring2/config/skin1')

    return result

  def page25(self):
    """POST empFinish.do (request 2501)."""
    result = request2501.POST('/employee-spring2/empFinish.do',
      ( NVPair('crudType', self.token_crudType),
        NVPair('id', self.token_id),
        NVPair('id', self.token_id),
        NVPair('empNo', self.token_empNo),
        NVPair('empNo', self.token_empNo),
        NVPair('empName', self.token_empName),
        NVPair('mgrId', self.token_mgrId),
        NVPair('hiredate', self.token_hiredate),
        NVPair('sal', self.token_sal),
        NVPair('deptId', self.token_deptId),
        NVPair('versionNo', self.token_versionNo), ),
      ( NVPair('Content-Type', 'application/x-www-form-urlencoded'), ))
    # 30 different values for token_crudType found in response, using the first one.
    self.token_crudType = \
      httpUtilities.valueFromBodyURI('crudType') # '2'
    # 40 different values for token_id found in response, using the first one.
    self.token_id = \
      httpUtilities.valueFromBodyURI('id') # '1'
    self.token_versionNo = \
      httpUtilities.valueFromBodyURI('versionNo') # '0'

    return result

  def page26(self):
    """GET skin1 (request 2601)."""
    result = request2601.GET('/employee-spring2/config/skin1')

    return result

  def __call__(self):
    """This method is called for every run performed by the worker thread."""
    self.page1()      # GET / (requests 101-102)

    grinder.sleep(31)
    self.page2()      # GET skin1 (request 201)

    grinder.sleep(4317)
    self.page3()      # POST empEdit.do (request 301)

    grinder.sleep(47)
    self.page4()      # GET skin1 (request 401)

    grinder.sleep(26542)
    self.page5()      # POST empConfirm.do (request 501)

    grinder.sleep(31)
    self.page6()      # GET skin1 (request 601)

    grinder.sleep(1361)
    self.page7()      # POST empFinish.do (request 701)

    grinder.sleep(47)
    self.page8()      # GET skin1 (request 801)

    grinder.sleep(3362)
    self.page9()      # GET empInquire.do (request 901)

    grinder.sleep(31)
    self.page10()     # GET skin1 (request 1001)

    grinder.sleep(1079)
    self.page11()     # POST empList.do (request 1101)

    grinder.sleep(31)
    self.page12()     # GET skin1 (request 1201)

    grinder.sleep(1626)
    self.page13()     # GET empEdit.do (request 1301)

    grinder.sleep(16)
    self.page14()     # GET skin1 (request 1401)

    grinder.sleep(9650)
    self.page15()     # POST empConfirm.do (request 1501)

    grinder.sleep(15)
    self.page16()     # GET skin1 (request 1601)

    grinder.sleep(1767)
    self.page17()     # POST empFinish.do (request 1701)

    grinder.sleep(47)
    self.page18()     # GET skin1 (request 1801)

    grinder.sleep(4254)
    self.page19()     # GET empInquire.do (request 1901)

    grinder.sleep(32)
    self.page20()     # GET skin1 (request 2001)

    grinder.sleep(1658)
    self.page21()     # POST empList.do (request 2101)

    grinder.sleep(31)
    self.page22()     # GET skin1 (request 2201)

    grinder.sleep(1236)
    self.page23()     # GET empInquire.do (request 2301)

    grinder.sleep(32)
    self.page24()     # GET skin1 (request 2401)

    grinder.sleep(1063)
    self.page25()     # POST empFinish.do (request 2501)

    grinder.sleep(31)
    self.page26()     # GET skin1 (request 2601)


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
instrumentMethod(Test(1400, 'Page 14'), 'page14')
instrumentMethod(Test(1500, 'Page 15'), 'page15')
instrumentMethod(Test(1600, 'Page 16'), 'page16')
instrumentMethod(Test(1700, 'Page 17'), 'page17')
instrumentMethod(Test(1800, 'Page 18'), 'page18')
instrumentMethod(Test(1900, 'Page 19'), 'page19')
instrumentMethod(Test(2000, 'Page 20'), 'page20')
instrumentMethod(Test(2100, 'Page 21'), 'page21')
instrumentMethod(Test(2200, 'Page 22'), 'page22')
instrumentMethod(Test(2300, 'Page 23'), 'page23')
instrumentMethod(Test(2400, 'Page 24'), 'page24')
instrumentMethod(Test(2500, 'Page 25'), 'page25')
instrumentMethod(Test(2600, 'Page 26'), 'page26')
