package org.apache.jsp.WEB_002dINF.jsp.angebotsverwaltung;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class angebotDetail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(1);
    _jspx_dependants.add("/WEB-INF/jsp/layout/layout.jspf");
  }

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_definition_scope_page_id;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_put_value_name_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_choose;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_when_test;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_otherwise;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_insert_flush_beanName;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_tiles_put_type_name;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_message_key_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_img_page_altKey_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_fmt_formatNumber_value_type_currencyCode_nobody;
  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_html_link_action;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_tiles_definition_scope_page_id = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_tiles_put_value_name_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_choose = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_when_test = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_otherwise = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_tiles_insert_flush_beanName = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_tiles_put_type_name = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_fmt_message_key_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_html_img_page_altKey_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_fmt_formatNumber_value_type_currencyCode_nobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _jspx_tagPool_html_link_action = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_tiles_definition_scope_page_id.release();
    _jspx_tagPool_tiles_put_value_name_nobody.release();
    _jspx_tagPool_c_choose.release();
    _jspx_tagPool_c_when_test.release();
    _jspx_tagPool_c_otherwise.release();
    _jspx_tagPool_tiles_insert_flush_beanName.release();
    _jspx_tagPool_tiles_put_type_name.release();
    _jspx_tagPool_fmt_message_key_nobody.release();
    _jspx_tagPool_html_img_page_altKey_nobody.release();
    _jspx_tagPool_c_forEach_var_items.release();
    _jspx_tagPool_fmt_formatNumber_value_type_currencyCode_nobody.release();
    _jspx_tagPool_html_link_action.release();
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
      out.write("\r\n\r\n");
      if (_jspx_meth_tiles_insert_0(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
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

  private boolean _jspx_meth_tiles_insert_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:insert
    org.apache.struts.tiles.taglib.InsertTag _jspx_th_tiles_insert_0 = (org.apache.struts.tiles.taglib.InsertTag) _jspx_tagPool_tiles_insert_flush_beanName.get(org.apache.struts.tiles.taglib.InsertTag.class);
    _jspx_th_tiles_insert_0.setPageContext(_jspx_page_context);
    _jspx_th_tiles_insert_0.setParent(null);
    _jspx_th_tiles_insert_0.setBeanName("shop.layout");
    _jspx_th_tiles_insert_0.setFlush(true);
    int _jspx_eval_tiles_insert_0 = _jspx_th_tiles_insert_0.doStartTag();
    if (_jspx_eval_tiles_insert_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('\r');
        out.write('\n');
        out.write('	');
        if (_jspx_meth_tiles_put_5(_jspx_th_tiles_insert_0, _jspx_page_context))
          return true;
        out.write("\r\n\r\n\t");
        if (_jspx_meth_tiles_put_6(_jspx_th_tiles_insert_0, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        int evalDoAfterBody = _jspx_th_tiles_insert_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_tiles_insert_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_insert_flush_beanName.reuse(_jspx_th_tiles_insert_0);
      return true;
    }
    _jspx_tagPool_tiles_insert_flush_beanName.reuse(_jspx_th_tiles_insert_0);
    return false;
  }

  private boolean _jspx_meth_tiles_put_5(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_insert_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:put
    org.apache.struts.tiles.taglib.PutTag _jspx_th_tiles_put_5 = (org.apache.struts.tiles.taglib.PutTag) _jspx_tagPool_tiles_put_type_name.get(org.apache.struts.tiles.taglib.PutTag.class);
    _jspx_th_tiles_put_5.setPageContext(_jspx_page_context);
    _jspx_th_tiles_put_5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_insert_0);
    _jspx_th_tiles_put_5.setName("browserTitle");
    _jspx_th_tiles_put_5.setType("string");
    int _jspx_eval_tiles_put_5 = _jspx_th_tiles_put_5.doStartTag();
    if (_jspx_eval_tiles_put_5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_tiles_put_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_tiles_put_5.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_tiles_put_5.doInitBody();
      }
      do {
        out.write("\r\n\t\t");
        if (_jspx_meth_fmt_message_0(_jspx_th_tiles_put_5, _jspx_page_context))
          return true;
        out.write("\r\n\t\t");
        if (_jspx_meth_fmt_message_1(_jspx_th_tiles_put_5, _jspx_page_context))
          return true;
        out.write('\r');
        out.write('\n');
        out.write('	');
        int evalDoAfterBody = _jspx_th_tiles_put_5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_tiles_put_5 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_tiles_put_5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_put_type_name.reuse(_jspx_th_tiles_put_5);
      return true;
    }
    _jspx_tagPool_tiles_put_type_name.reuse(_jspx_th_tiles_put_5);
    return false;
  }

  private boolean _jspx_meth_fmt_message_0(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_put_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_message_0 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _jspx_tagPool_fmt_message_key_nobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_message_0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_message_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_put_5);
    _jspx_th_fmt_message_0.setKey("header.title.constant");
    int _jspx_eval_fmt_message_0 = _jspx_th_fmt_message_0.doStartTag();
    if (_jspx_th_fmt_message_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_0);
      return true;
    }
    _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_1(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_put_5, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_message_1 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _jspx_tagPool_fmt_message_key_nobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_message_1.setPageContext(_jspx_page_context);
    _jspx_th_fmt_message_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_put_5);
    _jspx_th_fmt_message_1.setKey("angebotdetail.header.title");
    int _jspx_eval_fmt_message_1 = _jspx_th_fmt_message_1.doStartTag();
    if (_jspx_th_fmt_message_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_1);
      return true;
    }
    _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_1);
    return false;
  }

  private boolean _jspx_meth_tiles_put_6(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_insert_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:put
    org.apache.struts.tiles.taglib.PutTag _jspx_th_tiles_put_6 = (org.apache.struts.tiles.taglib.PutTag) _jspx_tagPool_tiles_put_type_name.get(org.apache.struts.tiles.taglib.PutTag.class);
    _jspx_th_tiles_put_6.setPageContext(_jspx_page_context);
    _jspx_th_tiles_put_6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_insert_0);
    _jspx_th_tiles_put_6.setName("content");
    _jspx_th_tiles_put_6.setType("string");
    int _jspx_eval_tiles_put_6 = _jspx_th_tiles_put_6.doStartTag();
    if (_jspx_eval_tiles_put_6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_tiles_put_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_tiles_put_6.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_tiles_put_6.doInitBody();
      }
      do {
        out.write("\r\n\t<div id=\"table\">\r\n\t\t<table>\r\n\t\t\t<tr>\r\n\t\t\t\t<th colspan=\"2\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${angebotDetailForm.beschreibung}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</th>\r\n\t\t\t</tr>\t\t\t\r\n\t\t\t<tr>\r\n\t\t\t\t<td>");
        if (_jspx_meth_html_img_0(_jspx_th_tiles_put_6, _jspx_page_context))
          return true;
        out.write("</td>\r\n\t\t\t\t<td>\r\n\t\t\t\t\t");
        if (_jspx_meth_fmt_message_2(_jspx_th_tiles_put_6, _jspx_page_context))
          return true;
        out.write("\t\t\t\t\r\n\t\t\t\t<br/>\r\n\t\t\t\t<br/>\r\n\t\t\t\t");
        if (_jspx_meth_c_forEach_0(_jspx_th_tiles_put_6, _jspx_page_context))
          return true;
        out.write("\r\n\t\t\t\t<br/>\r\n\t\t\t\t\t");
        if (_jspx_meth_fmt_message_3(_jspx_th_tiles_put_6, _jspx_page_context))
          return true;
        out.write("\r\n\t\t\t\t<br/>\r\n\t\t\t\t\t");
        if (_jspx_meth_fmt_formatNumber_0(_jspx_th_tiles_put_6, _jspx_page_context))
          return true;
        out.write("\t\t\t\t\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td align=\"right\" colspan=\"2\">\r\n\t\t\t\t\t");
        if (_jspx_meth_html_link_0(_jspx_th_tiles_put_6, _jspx_page_context))
          return true;
        out.write("\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t</table>\r\n\t</div>\r\n\t");
        int evalDoAfterBody = _jspx_th_tiles_put_6.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_tiles_put_6 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_tiles_put_6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_tiles_put_type_name.reuse(_jspx_th_tiles_put_6);
      return true;
    }
    _jspx_tagPool_tiles_put_type_name.reuse(_jspx_th_tiles_put_6);
    return false;
  }

  private boolean _jspx_meth_html_img_0(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_put_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:img
    org.apache.struts.taglib.html.ImgTag _jspx_th_html_img_0 = (org.apache.struts.taglib.html.ImgTag) _jspx_tagPool_html_img_page_altKey_nobody.get(org.apache.struts.taglib.html.ImgTag.class);
    _jspx_th_html_img_0.setPageContext(_jspx_page_context);
    _jspx_th_html_img_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_put_6);
    _jspx_th_html_img_0.setPage((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("/images/${angebotDetailForm.bildUrl}.jpg", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    _jspx_th_html_img_0.setAltKey("angebotdetail.picture.bike.alter");
    int _jspx_eval_html_img_0 = _jspx_th_html_img_0.doStartTag();
    if (_jspx_th_html_img_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_img_page_altKey_nobody.reuse(_jspx_th_html_img_0);
      return true;
    }
    _jspx_tagPool_html_img_page_altKey_nobody.reuse(_jspx_th_html_img_0);
    return false;
  }

  private boolean _jspx_meth_fmt_message_2(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_put_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_message_2 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _jspx_tagPool_fmt_message_key_nobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_message_2.setPageContext(_jspx_page_context);
    _jspx_th_fmt_message_2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_put_6);
    _jspx_th_fmt_message_2.setKey("angebotdetail.parts");
    int _jspx_eval_fmt_message_2 = _jspx_th_fmt_message_2.doStartTag();
    if (_jspx_th_fmt_message_2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_2);
      return true;
    }
    _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_2);
    return false;
  }

  private boolean _jspx_meth_c_forEach_0(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_put_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_put_6);
    _jspx_th_c_forEach_0.setVar("teil");
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${angebotDetailForm.teil}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n\t\t\t\t\t");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${teil.beschreibung}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\r\n\t\t\t\t\t<br/>\r\n\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }

  private boolean _jspx_meth_fmt_message_3(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_put_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:message
    org.apache.taglibs.standard.tag.rt.fmt.MessageTag _jspx_th_fmt_message_3 = (org.apache.taglibs.standard.tag.rt.fmt.MessageTag) _jspx_tagPool_fmt_message_key_nobody.get(org.apache.taglibs.standard.tag.rt.fmt.MessageTag.class);
    _jspx_th_fmt_message_3.setPageContext(_jspx_page_context);
    _jspx_th_fmt_message_3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_put_6);
    _jspx_th_fmt_message_3.setKey("angebotdetail.price");
    int _jspx_eval_fmt_message_3 = _jspx_th_fmt_message_3.doStartTag();
    if (_jspx_th_fmt_message_3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_3);
      return true;
    }
    _jspx_tagPool_fmt_message_key_nobody.reuse(_jspx_th_fmt_message_3);
    return false;
  }

  private boolean _jspx_meth_fmt_formatNumber_0(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_put_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  fmt:formatNumber
    org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag _jspx_th_fmt_formatNumber_0 = (org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag) _jspx_tagPool_fmt_formatNumber_value_type_currencyCode_nobody.get(org.apache.taglibs.standard.tag.rt.fmt.FormatNumberTag.class);
    _jspx_th_fmt_formatNumber_0.setPageContext(_jspx_page_context);
    _jspx_th_fmt_formatNumber_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_put_6);
    _jspx_th_fmt_formatNumber_0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${angebotDetailForm.preis}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    _jspx_th_fmt_formatNumber_0.setType("currency");
    _jspx_th_fmt_formatNumber_0.setCurrencyCode("EUR");
    int _jspx_eval_fmt_formatNumber_0 = _jspx_th_fmt_formatNumber_0.doStartTag();
    if (_jspx_th_fmt_formatNumber_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_fmt_formatNumber_value_type_currencyCode_nobody.reuse(_jspx_th_fmt_formatNumber_0);
      return true;
    }
    _jspx_tagPool_fmt_formatNumber_value_type_currencyCode_nobody.reuse(_jspx_th_fmt_formatNumber_0);
    return false;
  }

  private boolean _jspx_meth_html_link_0(javax.servlet.jsp.tagext.JspTag _jspx_th_tiles_put_6, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:link
    org.apache.struts.taglib.html.LinkTag _jspx_th_html_link_0 = (org.apache.struts.taglib.html.LinkTag) _jspx_tagPool_html_link_action.get(org.apache.struts.taglib.html.LinkTag.class);
    _jspx_th_html_link_0.setPageContext(_jspx_page_context);
    _jspx_th_html_link_0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_tiles_put_6);
    _jspx_th_html_link_0.setAction((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("/AddItemToWarenkorb?mid=${angebotDetailForm.id}&anzahl=1", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_html_link_0 = _jspx_th_html_link_0.doStartTag();
    if (_jspx_eval_html_link_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      if (_jspx_eval_html_link_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.pushBody();
        _jspx_th_html_link_0.setBodyContent((javax.servlet.jsp.tagext.BodyContent) out);
        _jspx_th_html_link_0.doInitBody();
      }
      do {
        out.write("\r\n\t\t\t\t\t");
        if (_jspx_meth_html_img_1(_jspx_th_html_link_0, _jspx_page_context))
          return true;
        out.write("\r\n\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_html_link_0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
      if (_jspx_eval_html_link_0 != javax.servlet.jsp.tagext.Tag.EVAL_BODY_INCLUDE) {
        out = _jspx_page_context.popBody();
      }
    }
    if (_jspx_th_html_link_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_link_action.reuse(_jspx_th_html_link_0);
      return true;
    }
    _jspx_tagPool_html_link_action.reuse(_jspx_th_html_link_0);
    return false;
  }

  private boolean _jspx_meth_html_img_1(javax.servlet.jsp.tagext.JspTag _jspx_th_html_link_0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  html:img
    org.apache.struts.taglib.html.ImgTag _jspx_th_html_img_1 = (org.apache.struts.taglib.html.ImgTag) _jspx_tagPool_html_img_page_altKey_nobody.get(org.apache.struts.taglib.html.ImgTag.class);
    _jspx_th_html_img_1.setPageContext(_jspx_page_context);
    _jspx_th_html_img_1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_link_0);
    _jspx_th_html_img_1.setPage("/images/buy.gif");
    _jspx_th_html_img_1.setAltKey("angebotdetail.picture.buybutton.alter");
    int _jspx_eval_html_img_1 = _jspx_th_html_img_1.doStartTag();
    if (_jspx_th_html_img_1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _jspx_tagPool_html_img_page_altKey_nobody.reuse(_jspx_th_html_img_1);
      return true;
    }
    _jspx_tagPool_html_img_page_altKey_nobody.reuse(_jspx_th_html_img_1);
    return false;
  }
}
