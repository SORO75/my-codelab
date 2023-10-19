package org.apache.jsp.WEB_002dINF.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class tiles_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_html;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_get_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_nobody;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_html_html = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_tiles_get_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_fmt_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_html_html.release();
    _jspx_tagPool_tiles_get_name_nobody.release();
    _jspx_tagPool_fmt_message_key_nobody.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    JspFactory _jspxFactory = null;
    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      _jspxFactory = JspFactory.getDefaultFactory();
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\r\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\r\n    \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
      if (_jspx_meth_html_html_0(_jspx_page_context))
        return;
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      if (_jspxFactory != null) _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_html_html_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:html
    org.apache.struts.taglib.html.HtmlTag _jspx_th_html_html_0 = (org.apache.struts.taglib.html.HtmlTag) _jspx_tagPool_html_html.get(org.apache.struts.taglib.html.HtmlTag.class);
    _jspx_th_html_html_0.setPageContext(_jspx_page_context);
    _jspx_th_html_html_0.setParent(null);
    int _jspx_eval_html_html_0 = _jspx_th_html_html_0.doStartTag();
    if (_jspx_eval_html_html_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n\r\n<head>       \r\n    <title>");
        if (_jspx_meth_tiles_get_0(_jspx_th_html_html_0, _jspx_page_context))
          return true;
        out.write("</title>\r\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\" />\r\n    <link rel=\"stylesheet\" href=\"css/screen.css\" type=\"text/css\" \r\n          media=\"screen, projection\" title=\"Normal\" />\r\n    <link rel=\"stylesheet\" href=\"css/text-only.css\" type=\"text/css\" \r\n          media=\"braille, embossed, aural, tty, print\" title=\"Text only\" />\r\n</head>\r\n\r\n<body id=\"all\">\r\n    <div id=\"top\">\r\n      <a name=\"top\" class=\"invis\" id=\"top\"></a>\r\n      <a href=\"#content\" class=\"invis\">");
        if (_jspx_meth_fmt_message_0(_jspx_th_html_html_0, _jspx_page_context))
          return true;
        out.write("</a>\r\n    </div>\r\n    <div id=\"wrapper\">\r\n      <div id=\"header\">\r\n\t\t");
        if (_jspx_meth_tiles_get_1(_jspx_th_html_html_0, _jspx_page_context))
          return true;
        out.write("\r\n      </div>\r\n      <hr />\r\n      <div id=\"menue1\">\r\n\t\t");
        if (_jspx_meth_tiles_get_2(_jspx_th_html_html_0, _jspx_page_context))
          return true;
        out.write("\r\n      </div>\r\n      <div id=\"menue2\">\r\n     \t");
        if (_jspx_meth_tiles_get_3(_jspx_th_html_html_0, _jspx_page_context))
          return true;
        out.write("\r\n      </div>\r\n      <hr />\r\n      <div id=\"content\">\r\n        <span class=\"invis\"><a name=\"content\" id=\"content\"></a></span>\r\n       \t");
        if (_jspx_meth_tiles_get_4(_jspx_th_html_html_0, _jspx_page_context))
          return true;
        out.write("\r\n      </div>\r\n      <div id=\"footer\">\r\n       \t");
        if (_jspx_meth_tiles_get_5(_jspx_th_html_html_0, _jspx_page_context))
          return true;
        out.write("      \r\n      </div>\r\n    </div>\r\n  </body>      \r\n");
        int evalDoAfterBody = _jspx_th_html_html_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_html_html_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_html.reuse(_jspx_th_html_html_0);
      return true;
    }
    _jspx_tagPool_html_html.reuse(_jspx_th_html_html_0);
    return false;
  }

  private boolean _jspx_meth_tiles_get_0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_html_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:get
    org.apache.struts.tiles.taglib.GetTag _jspx_th_tiles_get_0 = (org.apache.struts.tiles.taglib.GetTag) _jspx_tagPool_tiles_get_name_nobody.get(org.apache.struts.tiles.taglib.GetTag.class);
    _jspx_th_tiles_get_0.setPageContext(_jspx_page_context);
    _jspx_th_tiles_get_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_html_0);
    _jspx_th_tiles_get_0.setName("browserTitle");
    int _jspx_eval_tiles_get_0 = _jspx_th_tiles_get_0.doStartTag();
    if (_jspx_th_tiles_get_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_0);
      return true;
    }
    _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_html_html_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _jspx_tagPool_fmt_message_key_nobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_html_0);
    _jspx_th_fmt_message_0.setKey("header.gotocontent");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_0);
      return true;
    }
    _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_tiles_get_1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_html_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:get
    org.apache.struts.tiles.taglib.GetTag _jspx_th_tiles_get_1 = (org.apache.struts.tiles.taglib.GetTag) _jspx_tagPool_tiles_get_name_nobody.get(org.apache.struts.tiles.taglib.GetTag.class);
    _jspx_th_tiles_get_1.setPageContext(_jspx_page_context);
    _jspx_th_tiles_get_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_html_0);
    _jspx_th_tiles_get_1.setName("header");
    int _jspx_eval_tiles_get_1 = _jspx_th_tiles_get_1.doStartTag();
    if (_jspx_th_tiles_get_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_1);
      return true;
    }
    _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_1);
    return false;
  }

  private boolean _jspx_meth_tiles_get_2(javax.servlet.jsp.tagext.JspTag _jspx_th_html_html_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:get
    org.apache.struts.tiles.taglib.GetTag _jspx_th_tiles_get_2 = (org.apache.struts.tiles.taglib.GetTag) _jspx_tagPool_tiles_get_name_nobody.get(org.apache.struts.tiles.taglib.GetTag.class);
    _jspx_th_tiles_get_2.setPageContext(_jspx_page_context);
    _jspx_th_tiles_get_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_html_0);
    _jspx_th_tiles_get_2.setName("menue1");
    int _jspx_eval_tiles_get_2 = _jspx_th_tiles_get_2.doStartTag();
    if (_jspx_th_tiles_get_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_2);
      return true;
    }
    _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_2);
    return false;
  }

  private boolean _jspx_meth_tiles_get_3(javax.servlet.jsp.tagext.JspTag _jspx_th_html_html_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:get
    org.apache.struts.tiles.taglib.GetTag _jspx_th_tiles_get_3 = (org.apache.struts.tiles.taglib.GetTag) _jspx_tagPool_tiles_get_name_nobody.get(org.apache.struts.tiles.taglib.GetTag.class);
    _jspx_th_tiles_get_3.setPageContext(_jspx_page_context);
    _jspx_th_tiles_get_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_html_0);
    _jspx_th_tiles_get_3.setName("menue2");
    int _jspx_eval_tiles_get_3 = _jspx_th_tiles_get_3.doStartTag();
    if (_jspx_th_tiles_get_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_3);
      return true;
    }
    _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_3);
    return false;
  }

  private boolean _jspx_meth_tiles_get_4(javax.servlet.jsp.tagext.JspTag _jspx_th_html_html_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:get
    org.apache.struts.tiles.taglib.GetTag _jspx_th_tiles_get_4 = (org.apache.struts.tiles.taglib.GetTag) _jspx_tagPool_tiles_get_name_nobody.get(org.apache.struts.tiles.taglib.GetTag.class);
    _jspx_th_tiles_get_4.setPageContext(_jspx_page_context);
    _jspx_th_tiles_get_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_html_0);
    _jspx_th_tiles_get_4.setName("content");
    int _jspx_eval_tiles_get_4 = _jspx_th_tiles_get_4.doStartTag();
    if (_jspx_th_tiles_get_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_4);
      return true;
    }
    _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_4);
    return false;
  }

  private boolean _jspx_meth_tiles_get_5(javax.servlet.jsp.tagext.JspTag _jspx_th_html_html_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:get
    org.apache.struts.tiles.taglib.GetTag _jspx_th_tiles_get_5 = (org.apache.struts.tiles.taglib.GetTag) _jspx_tagPool_tiles_get_name_nobody.get(org.apache.struts.tiles.taglib.GetTag.class);
    _jspx_th_tiles_get_5.setPageContext(_jspx_page_context);
    _jspx_th_tiles_get_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_html_0);
    _jspx_th_tiles_get_5.setName("footer");
    int _jspx_eval_tiles_get_5 = _jspx_th_tiles_get_5.doStartTag();
    if (_jspx_th_tiles_get_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_5);
      return true;
    }
    _jspx_tagPool_tiles_get_name_nobody.reuse(_jspx_th_tiles_get_5);
    return false;
  }
}
