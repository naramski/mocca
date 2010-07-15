<!--
  Copyright 2008 Federal Chancellery Austria and
  Graz University of Technology

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--

  This JSP page loads the MOCCA applet into the browser. It is not intended
  to by directly called by the browser, but the UIServlet should be called.
  The UIServlet sets some request attributes and forwards to this page.
  
  The look and behavior of this page and the embedded applet may be 
  customized by a number of parameters handed over in the request. If this
  does not provide enough flexibility you may choose to modify this page
  or provide an alternative customized page. The name of the customized page
  may be specified as parameter in the original request. The UIServlet will
  forward to the given page.
  
 --%>
<%-- Applet --%>
<c:set var="defaultWidth" value="190"/>
<c:set var="defaultHeight" value="130"/>
<c:set var="minJavaVersion" value="1.6.0_04"/>
<%-- Images --%>
<c:set var="defaultButtonWidth" value="16"/>
<c:set var="defaultButtonHeight" value="22"/>
<%-- URLs --%>
<c:set value="./applet" var="codebase"/>
<c:url value="/help/" var="helpUrl"/>
<c:url value="/stal?wsdl" var="wsdlUrl"/>
<c:url value="/result" var="resultUrl"/>
<%-- Messages --%>
<fmt:bundle basename="web">
<%-- Applet archive and class --%>
<%-- 
  Uncomment the following line if you would like to prevent applet caching! 
--%>
<%-- jsp:useBean id="now" class="java.util.Date" scope="request"/ --%>
<c:choose>
  <c:when test="${requestScope.moccaParam.extension == 'activation'}">
    <c:url var="appletArchive" value="BKUAppletExt-single.jar">
      <c:if test="${! empty now}">
        <c:param name="no-cache" value="${now.time}"/>
      </c:if>
    </c:url>
    <c:set var="appletClass" value="at.gv.egiz.bku.online.applet.ActivationApplet.class"/>
  </c:when>
  <c:when test="${requestScope.moccaParam.extension == 'pin'}">
    <c:url var="appletArchive" value="BKUAppletExt-single.jar">
      <c:if test="${! empty now}">
        <c:param name="no-cache" value="${now.time}"/>
      </c:if>
    </c:url>
    <c:set var="appletClass" value="at.gv.egiz.bku.online.applet.PINManagementApplet.class"/>
  </c:when>
  <c:otherwise>
    <c:url var="appletArchive" value="BKUApplet-single.jar">
      <c:if test="${! empty now}">
        <c:param name="no-cache" value="${now.time}"/>
      </c:if>
    </c:url>
    <c:set var="appletClass" value="at.gv.egiz.bku.online.applet.BKUApplet.class"/>
  </c:otherwise>
</c:choose>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
    <META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
    <META HTTP-EQUIV="EXPIRES" CONTENT="Mon, 22 Jul 2002 11:12:01 GMT">
    <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
    <title><fmt:message key="title"/></title>
    <link rel="shortcut icon" href="img/chip16.ico" type="image/x-icon">
    <style type="text/css" media="all">
      root {
        display: block;
      }
      body {
        background: #ffffff;
        padding: 0;
        margin: 0;
        border-style: none;
        width: <c:out value="${requestScope.moccaParam.appletWidth}" default="${defaultWidth}"/>px;
      }
      #container {
        padding: 0;
        margin: 0;
        width: <c:out value="${requestScope.moccaParam.appletWidth}" default="${defaultWidth}"/>px;
        background: <c:out value="${requestScope.moccaParam.appletBackgroundColor}" default="#eeeeee"/>;
      }
      #container applet{
        padding: 0;
        margin: 0;
      }
      .button {
        width: <c:out value="${defaultButtonWidth}px"/>;
        height: <c:out value="${defaultButtonHeight}px"/>;
        text-decoration: none;
        border-style: none;
      }
      #buttons {
        padding: 0;
        margin: 0;
      }
      #message {
        margin: 0;
        padding: 0.25em 0 0.25em 0;
        clear: both;
      }
      #message p{
        margin: 0;
        padding: 0.25em 0.5em 0.25em 0.5em;
      }
    </style>
    <script type="text/javascript" src="https://www.java.com/js/deployJava.js"></script>
    <script type="text/javascript">
      var iframe = false;
      try {
    	  iframe = top.location.href != window.location.href;
      } catch (e) {
        // if window.location can not be accessed we are most likely in an iframe
        iframe = true;
      }
      var fontSize = 100;
      var width = <c:out value="${requestScope.moccaParam.appletWidth}" default="${defaultWidth}"/>;
      var height = <c:out value="${requestScope.moccaParam.appletHeight}" default="${defaultHeight}"/>;
      var buttonWidth = <c:out value="${defaultButtonWidth}"/>;
      var buttonHeight = <c:out value="${defaultButtonHeight}"/>;
      var buttons = ["incButton", "decButton", "helpButton"];
      function focusToBrowser() {
        document.getElementById("focus").focus();
      }
      function focusToApplet() {
        var applet = document.getElementById("moccaapplet");
        if (applet != null) {
          applet.getFocusFromBrowser();
        }
      }
      function resize(factor) {
        width = Math.ceil(width * factor);
        height = Math.ceil(height * factor);
        var container = document.getElementById("container");
        if (container != null) {
          container.style.width = width + "px";
        }
        var applet = document.getElementById("moccaapplet");
        if (applet != null) {
          applet.width = width;
          applet.height = height;
        }
        buttonWidth = Math.ceil(buttonWidth * factor);
        buttonHeight = Math.ceil(buttonHeight * factor);
        for (var b in buttons) {
          var button = document.getElementById(buttons[b]);
          if (button != null) {
            button.style.width = buttonWidth + "px";
            button.style.height = buttonHeight + "px";
          }
        }
        fontSize *= factor;
        document.body.style.fontSize = fontSize + "%";
      }
    </script>
    </head>
    <body>
      <div id="container">
        <div id="buttons">
	        <a href="#" id="focus" onclick="resize(1.2); return false;" style="float: left;"><img alt="<fmt:message key="incTextSize"/>" src="img/inc.png" id="incButton" class="button"></a>
	        <a href="#" onclick="resize(1/1.2); return false;" style="float: left;"><img alt="<fmt:message key="decTextSize"/>" src="img/dec.png" id="decButton" class="button"></a>
        </div>
        <a href="<c:out value="${helpUrl}"/>" onclick="this.href = document.moccaapplet.getHelpURL(); return true;" target="_new" style="float: right;"><img alt="<fmt:message key="help"/>" src="img/help.png" id="helpButton" class="button"></a>
        <div id="message" style="display: none;">
          <p><fmt:message key="javaPluginRequired"/></p>
          <p style="text-align: right;">
            <a style="width: 90%; font-size: 100%" href="http://www.java.com" onclick="deployJava.installLatestJRE(); return false;"><fmt:message key="installJava"/></a>
          </p>
        </div><script type="text/javascript">
        if (iframe) {
          document.getElementById("buttons").style.visibility = "hidden";
        }
        if (!deployJava.versionCheck('<c:out value="${minJavaVersion}+"/>')) {
          document.getElementById("message").style.display = "block";
        } else {
          var attributes = {
            codebase :'<c:out value="${codebase}"/>',
            code : '<c:out value="${appletClass}"/>',
            archive : '<c:out value="${appletArchive}"/>',
            width : <c:out value="${requestScope.moccaParam.appletWidth}" default="${defaultWidth}"/>,
            height : <c:out value="${requestScope.moccaParam.appletHeight}" default="${defaultHeight}"/>,
            name : 'moccaapplet',
            id : 'moccaapplet'
          };
          var parameters = {
            GuiStyle : '<c:out value="${requestScope.moccaParam.guiStyle}" default="simple"/>',
            Locale : '<c:out value="${requestScope.moccaParam.locale}" default=""/>',
            Background : '<c:out value="${requestScope.moccaParam.appletBackground}" default=""/>',
            BackgroundColor : '<c:out value="${requestScope.moccaParam.appletBackgroundColor}" default="#eeeeee"/>',
            WSDL_URL : '<c:out value="${wsdlUrl}"/>',
            HelpURL : '<c:out value="${helpUrl}"/>',
            SessionID : '<c:out value="${requestScope.id}"/>',
            RedirectURL : '<c:out value="${resultUrl}"/>',
            RedirectTarget: '_parent',
            EnforceRecommendedPINLength: 'true'
          };
          deployJava.runApplet(attributes, parameters, '<c:out value="${minJavaVersion}"/>');
        }
      </script></div>
  </body>
</html>
</fmt:bundle>