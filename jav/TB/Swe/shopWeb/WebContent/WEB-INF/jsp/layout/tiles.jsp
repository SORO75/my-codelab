<?xml version="1.0" encoding="iso-8859-1"?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html:html>

<head>       
    <title><tiles:get name="browserTitle"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <link rel="stylesheet" href="css/screen.css" type="text/css" 
          media="screen, projection" title="Normal" />
    <link rel="stylesheet" href="css/text-only.css" type="text/css" 
          media="braille, embossed, aural, tty, print" title="Text only" />
</head>

<body id="all">
    <div id="top">
      <a name="top" class="invis" id="top"></a>
      <a href="#content" class="invis"><fmt:message key="header.gotocontent"/></a>
    </div>
    <div id="wrapper">
      <div id="header">
		<tiles:get name="header"/>
      </div>
      <hr />
      <div id="menue1">
		<tiles:get name="menue1"/>
      </div>
      <div id="menue2">
     	<tiles:get name="menue2"/>
      </div>
      <hr />
      <div id="content">
        <span class="invis"><a name="content" id="content"></a></span>
       	<tiles:get name="content"/>
      </div>
      <div id="footer">
       	<tiles:get name="footer"/>      
      </div>
    </div>
  </body>      
</html:html>