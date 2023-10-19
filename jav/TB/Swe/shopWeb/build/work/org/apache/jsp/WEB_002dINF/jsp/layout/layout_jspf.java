package org.apache.jsp.WEB_002dINF.jsp.layout;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class layout_jspf extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_definition_scope_page_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_put_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_tiles_definition_scope_page_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_tiles_put_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_tiles_definition_scope_page_id.release();
    _jspx_tagPool_tiles_put_value_name_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_c_otherwise.release();
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

      out.write("\r\n\r\n\r\n\r\n\r\n");
      if (_jspx_meth_tiles_definition_0(_jspx_page_context))
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

  private boolean _jspx_meth_tiles_definition_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:definition
    org.apache.struts.tiles.taglib.DefinitionTag _jspx_th_tiles_definition_0 = (org.apache.struts.tiles.taglib.DefinitionTag) _jspx_tagPool_tiles_definition_scope_page_id.get(org.apache.struts.tiles.taglib.DefinitionTag.class);
    _jspx_th_tiles_definition_0.setPageContext(_jspx_page_context);
    _jspx_th_tiles_definition_0.setParent(null);
    _jspx_th_tiles_definition_0.setId("shop.layout");
    _jspx_th_tiles_definition_0.setPage("/WEB-INF/jsp/layout/tiles.jsp");
    _jspx_th_tiles_definition_0.setScope("request");
    int _jspx_eval_tiles_definition_0 = _jspx_th_tiles_definition_0.doStartTag();
    if (_jspx_eval_tiles_definition_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_tiles_put_0(_jspx_th_tiles_definition_0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_tiles_put_1(_jspx_th_tiles_definition_0, _jspx_page_context))
          return true;
        out.write("\r\n\r\n\t");
        if (_jspx_meth_c_choose_0(_jspx_th_tiles_definition_0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_tiles_put_4(_jspx_th_tiles_definition_0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_tiles_definition_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_tiles_definition_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_definition_scope_page_id.reuse(_jspx_th_tiles_definition_0);
      return true;
    }
    _jspx_tagPool_tiles_definition_scope_page_id.reuse(_jspx_th_tiles_definition_0);
    return false;
  }

  private boolean _jspx_meth_tiles_put_0(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_definition_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:put
    org.apache.struts.tiles.taglib.PutTag _jspx_th_tiles_put_0 = (org.apache.struts.tiles.taglib.PutTag) _jspx_tagPool_tiles_put_value_name_nobody.get(org.apache.struts.tiles.taglib.PutTag.class);
    _jspx_th_tiles_put_0.setPageContext(_jspx_page_context);
    _jspx_th_tiles_put_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_definition_0);
    _jspx_th_tiles_put_0.setName("header");
    _jspx_th_tiles_put_0.setValue("/WEB-INF/jsp/layout/header.jspf");
    int _jspx_eval_tiles_put_0 = _jspx_th_tiles_put_0.doStartTag();
    if (_jspx_th_tiles_put_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_0);
      return true;
    }
    _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_0);
    return false;
  }

  private boolean _jspx_meth_tiles_put_1(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_definition_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:put
    org.apache.struts.tiles.taglib.PutTag _jspx_th_tiles_put_1 = (org.apache.struts.tiles.taglib.PutTag) _jspx_tagPool_tiles_put_value_name_nobody.get(org.apache.struts.tiles.taglib.PutTag.class);
    _jspx_th_tiles_put_1.setPageContext(_jspx_page_context);
    _jspx_th_tiles_put_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_definition_0);
    _jspx_th_tiles_put_1.setName("menue1");
    _jspx_th_tiles_put_1.setValue("/WEB-INF/jsp/layout/menue1.jspf");
    int _jspx_eval_tiles_put_1 = _jspx_th_tiles_put_1.doStartTag();
    if (_jspx_th_tiles_put_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_1);
      return true;
    }
    _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_1);
    return false;
  }

  private boolean _jspx_meth_c_choose_0(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_definition_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:choose
    org.apache.taglibs.standard.tag.common.core.ChooseTag _jspx_th_c_choose_0 = (org.apache.taglibs.standard.tag.common.core.ChooseTag) _jspx_tagPool_c_choose.get(org.apache.taglibs.standard.tag.common.core.ChooseTag.class);
    _jspx_th_c_choose_0.setPageContext(_jspx_page_context);
    _jspx_th_c_choose_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_definition_0);
    int _jspx_eval_c_choose_0 = _jspx_th_c_choose_0.doStartTag();
    if (_jspx_eval_c_choose_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n\t\t");
        if (_jspx_meth_c_when_0(_jspx_th_c_choose_0, _jspx_page_context))
          return true;
        out.write("\r\n\t\t");
        if (_jspx_meth_c_otherwise_0(_jspx_th_c_choose_0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write('	');
        int evalDoAfterBody = _jspx_th_c_choose_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_choose_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
      return true;
    }
    _jspx_tagPool_c_choose.reuse(_jspx_th_c_choose_0);
    return false;
  }

  private boolean _jspx_meth_c_when_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:when
    org.apache.taglibs.standard.tag.rt.core.WhenTag _jspx_th_c_when_0 = (org.apache.taglibs.standard.tag.rt.core.WhenTag) _jspx_tagPool_c_when_test.get(org.apache.taglibs.standard.tag.rt.core.WhenTag.class);
    _jspx_th_c_when_0.setPageContext(_jspx_page_context);
    _jspx_th_c_when_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
    _jspx_th_c_when_0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${Administrator eq true}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_when_0 = _jspx_th_c_when_0.doStartTag();
    if (_jspx_eval_c_when_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n\t\t\t");
        if (_jspx_meth_tiles_put_2(_jspx_th_c_when_0, _jspx_page_context))
          return true;
        out.write("\r\n\t\t");
        int evalDoAfterBody = _jspx_th_c_when_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_when_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
      return true;
    }
    _jspx_tagPool_c_when_test.reuse(_jspx_th_c_when_0);
    return false;
  }

  private boolean _jspx_meth_tiles_put_2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_when_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:put
    org.apache.struts.tiles.taglib.PutTag _jspx_th_tiles_put_2 = (org.apache.struts.tiles.taglib.PutTag) _jspx_tagPool_tiles_put_value_name_nobody.get(org.apache.struts.tiles.taglib.PutTag.class);
    _jspx_th_tiles_put_2.setPageContext(_jspx_page_context);
    _jspx_th_tiles_put_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_when_0);
    _jspx_th_tiles_put_2.setName("menue2");
    _jspx_th_tiles_put_2.setValue("/WEB-INF/jsp/layout/menue2_admin.jspf");
    int _jspx_eval_tiles_put_2 = _jspx_th_tiles_put_2.doStartTag();
    if (_jspx_th_tiles_put_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_2);
      return true;
    }
    _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_2);
    return false;
  }

  private boolean _jspx_meth_c_otherwise_0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_choose_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:otherwise
    org.apache.taglibs.standard.tag.common.core.OtherwiseTag _jspx_th_c_otherwise_0 = (org.apache.taglibs.standard.tag.common.core.OtherwiseTag) _jspx_tagPool_c_otherwise.get(org.apache.taglibs.standard.tag.common.core.OtherwiseTag.class);
    _jspx_th_c_otherwise_0.setPageContext(_jspx_page_context);
    _jspx_th_c_otherwise_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_choose_0);
    int _jspx_eval_c_otherwise_0 = _jspx_th_c_otherwise_0.doStartTag();
    if (_jspx_eval_c_otherwise_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n\t\t\t");
        if (_jspx_meth_tiles_put_3(_jspx_th_c_otherwise_0, _jspx_page_context))
          return true;
        out.write("\r\n\t\t");
        int evalDoAfterBody = _jspx_th_c_otherwise_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_otherwise_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
      return true;
    }
    _jspx_tagPool_c_otherwise.reuse(_jspx_th_c_otherwise_0);
    return false;
  }

  private boolean _jspx_meth_tiles_put_3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_otherwise_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:put
    org.apache.struts.tiles.taglib.PutTag _jspx_th_tiles_put_3 = (org.apache.struts.tiles.taglib.PutTag) _jspx_tagPool_tiles_put_value_name_nobody.get(org.apache.struts.tiles.taglib.PutTag.class);
    _jspx_th_tiles_put_3.setPageContext(_jspx_page_context);
    _jspx_th_tiles_put_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_otherwise_0);
    _jspx_th_tiles_put_3.setName("menue2");
    _jspx_th_tiles_put_3.setValue("/WEB-INF/jsp/layout/menue2.jspf");
    int _jspx_eval_tiles_put_3 = _jspx_th_tiles_put_3.doStartTag();
    if (_jspx_th_tiles_put_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_3);
      return true;
    }
    _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_3);
    return false;
  }

  private boolean _jspx_meth_tiles_put_4(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_definition_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:put
    org.apache.struts.tiles.taglib.PutTag _jspx_th_tiles_put_4 = (org.apache.struts.tiles.taglib.PutTag) _jspx_tagPool_tiles_put_value_name_nobody.get(org.apache.struts.tiles.taglib.PutTag.class);
    _jspx_th_tiles_put_4.setPageContext(_jspx_page_context);
    _jspx_th_tiles_put_4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_definition_0);
    _jspx_th_tiles_put_4.setName("footer");
    _jspx_th_tiles_put_4.setValue("/WEB-INF/jsp/layout/footer.jspf");
    int _jspx_eval_tiles_put_4 = _jspx_th_tiles_put_4.doStartTag();
    if (_jspx_th_tiles_put_4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_4);
      return true;
    }
    _jspx_tagPool_tiles_put_value_name_nobody.reuse(_jspx_th_tiles_put_4);
    return false;
  }
}
