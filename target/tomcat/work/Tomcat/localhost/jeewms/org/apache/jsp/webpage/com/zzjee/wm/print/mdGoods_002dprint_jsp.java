/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-11-19 07:43:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.webpage.com.zzjee.wm.print;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class mdGoods_002dprint_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/tld/easyui.tld", Long.valueOf(1666432920000L));
    _jspx_dependants.put("/context/mytags.jsp", Long.valueOf(1666432920000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005flayout_005fformid_005fdialog;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005flayout_005fformid_005fdialog = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.release();
    _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005flayout_005fformid_005fdialog.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
 
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;

      out.write('\n');
      //  c:set
      org.apache.taglibs.standard.tag.rt.core.SetTag _jspx_th_c_005fset_005f0 = (org.apache.taglibs.standard.tag.rt.core.SetTag) _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.SetTag.class);
      _jspx_th_c_005fset_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fset_005f0.setParent(null);
      // /context/mytags.jsp(9,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setVar("webRoot");
      // /context/mytags.jsp(9,0) name = value type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
      _jspx_th_c_005fset_005f0.setValue(basePath);
      int _jspx_eval_c_005fset_005f0 = _jspx_th_c_005fset_005f0.doStartTag();
      if (_jspx_th_c_005fset_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
        return;
      }
      _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.reuse(_jspx_th_c_005fset_005f0);
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>商品打印</title>\n");
      if (_jspx_meth_t_005fbase_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"webpage/com/zzjee/wmjs/jquery.jqprint.js\"></script>\n");
      out.write("<script language=\"javascript\">\n");
      out.write("function printall(){\n");
      out.write("\n");
      out.write("    $(\".printdiv\").jqprint();\n");
      out.write("\n");
      out.write("}\n");
      out.write("function printview(){\n");
      out.write("\tdocument.all.WebBrowser1.ExecWB(7,1);\n");
      out.write("}\n");
      out.write("\n");
      out.write("\n");
      out.write("</script>\n");
      out.write("\t<style>\n");
      out.write("\t\ttr\n");
      out.write("\t\t{mso-height-source:auto;\n");
      out.write("\t\t\tmso-ruby-visibility:none;}\n");
      out.write("\t\tcol\n");
      out.write("\t\t{mso-width-source:auto;\n");
      out.write("\t\t\tmso-ruby-visibility:none;}\n");
      out.write("\t\tbr\n");
      out.write("\t\t{mso-data-placement:same-cell;}\n");
      out.write("\t\truby\n");
      out.write("\t\t{ruby-align:left;}\n");
      out.write("\t\t.style0\n");
      out.write("\t\t{mso-number-format:General;\n");
      out.write("\t\t\ttext-align:general;\n");
      out.write("\t\t\tvertical-align:bottom;\n");
      out.write("\t\t\twhite-space:nowrap;\n");
      out.write("\t\t\tmso-rotate:0;\n");
      out.write("\t\t\tmso-background-source:auto;\n");
      out.write("\t\t\tmso-pattern:auto;\n");
      out.write("\t\t\tcolor:windowtext;\n");
      out.write("\t\t\tfont-size:14pt;\n");
      out.write("\t\t\tfont-weight:400;\n");
      out.write("\t\t\tfont-style:normal;\n");
      out.write("\t\t\ttext-decoration:none;\n");
      out.write("\t\t\tfont-family: 黑体;\n");
      out.write("\t\t\tmso-font-charset:0;\n");
      out.write("\t\t\tborder:none;\n");
      out.write("\t\t\tmso-protection:locked visible;\n");
      out.write("\t\t\tmso-style-name:常规;\n");
      out.write("\t\t\tmso-style-id:0;}\n");
      out.write("\t\ttd\n");
      out.write("\t\t{mso-style-parent:style0;\n");
      out.write("\t\t\tpadding-top:1px;\n");
      out.write("\t\t\tpadding-right:1px;\n");
      out.write("\t\t\tpadding-left:1px;\n");
      out.write("\t\t\tmso-ignore:padding;\n");
      out.write("\t\t\tcolor:windowtext;\n");
      out.write("\t\t\tfont-size:14pt;\n");
      out.write("\t\t\tfont-weight:400;\n");
      out.write("\t\t\tfont-style:normal;\n");
      out.write("\t\t\ttext-decoration:none;\n");
      out.write("\t\t\tfont-family:黑体;\n");
      out.write("\t\t\tmso-font-charset:0;\n");
      out.write("\t\t\tmso-number-format:General;\n");
      out.write("\t\t\ttext-align:general;\n");
      out.write("\t\t\tvertical-align:bottom;\n");
      out.write("\t\t\tborder:none;\n");
      out.write("\t\t\tmso-background-source:auto;\n");
      out.write("\t\t\tmso-pattern:auto;\n");
      out.write("\t\t\tmso-protection:locked visible;\n");
      out.write("\t\t\twhite-space:nowrap;\n");
      out.write("\t\t\tmso-rotate:0;}\n");
      out.write("\t\t.xl65\n");
      out.write("\t\t{mso-style-parent:style0;\n");
      out.write("\t\t\tcolor:black;\n");
      out.write("\t\t\tfont-size:16pt;\n");
      out.write("\t\t\ttext-align:center;\n");
      out.write("\t\t\tborder:1.0pt solid black;}\n");
      out.write("\t\t.xl66\n");
      out.write("\t\t{mso-style-parent:style0;\n");
      out.write("\t\t\tcolor:black;\n");
      out.write("\t\t\tfont-size:16pt;}\n");
      out.write("\t\t.xl67\n");
      out.write("\t\t{mso-style-parent:style0;\n");
      out.write("\t\t\tcolor:black;\n");
      out.write("\t\t\tfont-size:18pt;\n");
      out.write("\t\t\ttext-align:center;}\n");
      out.write("\t\t.xl68\n");
      out.write("\t\t{mso-style-parent:style0;\n");
      out.write("\t\t\tcolor:black;\n");
      out.write("\t\t\tfont-size:16pt;\n");
      out.write("\t\t\twhite-space:normal;}\n");
      out.write("\t\t.xl69\n");
      out.write("\t\t{mso-style-parent:style0;\n");
      out.write("\t\t\ttext-align:center;}\n");
      out.write("\n");
      out.write("\t</style>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body style=\"overflow-y:auto\" scroll=\"no\">\n");
      out.write("\n");
      out.write("<div class=\"printdiv\">");
      if (_jspx_meth_t_005fformvalid_005f0(_jspx_page_context))
        return;
      out.write("</div>\n");
      out.write("\n");
      out.write("<a class=\"easyui-linkbutton\" style=\"margin-top:3px\" icon=\"icon-print\" href=\"javascript:printall()\">打印</a>\n");
      out.write("\n");
      out.write("</body>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_t_005fbase_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  t:base
    org.jeecgframework.tag.core.easyui.BaseTag _jspx_th_t_005fbase_005f0 = (org.jeecgframework.tag.core.easyui.BaseTag) _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.get(org.jeecgframework.tag.core.easyui.BaseTag.class);
    _jspx_th_t_005fbase_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fbase_005f0.setParent(null);
    // /webpage/com/zzjee/wm/print/mdGoods-print.jsp(7,0) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fbase_005f0.setType("jquery,easyui,tools");
    int _jspx_eval_t_005fbase_005f0 = _jspx_th_t_005fbase_005f0.doStartTag();
    if (_jspx_th_t_005fbase_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.reuse(_jspx_th_t_005fbase_005f0);
    return false;
  }

  private boolean _jspx_meth_t_005fformvalid_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  t:formvalid
    org.jeecgframework.tag.core.easyui.FormValidationTag _jspx_th_t_005fformvalid_005f0 = (org.jeecgframework.tag.core.easyui.FormValidationTag) _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005flayout_005fformid_005fdialog.get(org.jeecgframework.tag.core.easyui.FormValidationTag.class);
    _jspx_th_t_005fformvalid_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fformvalid_005f0.setParent(null);
    // /webpage/com/zzjee/wm/print/mdGoods-print.jsp(102,22) name = formid type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setFormid("formobj");
    // /webpage/com/zzjee/wm/print/mdGoods-print.jsp(102,22) name = dialog type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setDialog(true);
    // /webpage/com/zzjee/wm/print/mdGoods-print.jsp(102,22) name = usePlugin type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setUsePlugin("password");
    // /webpage/com/zzjee/wm/print/mdGoods-print.jsp(102,22) name = layout type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setLayout("table");
    int _jspx_eval_t_005fformvalid_005f0 = _jspx_th_t_005fformvalid_005f0.doStartTag();
    if (_jspx_eval_t_005fformvalid_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t<table border=0 cellpadding=0 cellspacing=0 width=1000 style='border-collapse:\n");
        out.write(" collapse;table-layout:fixed;width:200pt;margin-left: 30px;margin-top: -30px'>\n");
        out.write("\t\t<col width=20 style='mso-width-source:userset;mso-width-alt:3612;width:17pt'>\n");
        out.write("\t\t<col width=20 style='mso-width-source:userset;mso-width-alt:1560;width:17pt'>\n");
        out.write("\t\t<col width=20 style='mso-width-source:userset;mso-width-alt:3612;width:17pt'>\n");
        out.write("\t\t<col width=20 style='mso-width-source:userset;mso-width-alt:1560;width:17pt'>\n");
        out.write("\n");
        out.write("\t\t<tr height=40 style='mso-height-source:userset;height:50.0pt'>\n");
        out.write("\t\t\t<th colspan=4 height=40 class=xl67 style='height:50.0pt' ><span style=\"font-size: 18pt\"></span></th>\n");
        out.write("\t\t</tr>\n");
        out.write("\n");
        out.write("\t\t<tr height=20 style='mso-height-source:userset;height:20.05pt'>\n");
        out.write("\n");
        out.write("\t\t\t<td    height=20 class=xl65 style='height:20.05pt;border:1.0pt solid black;text-align: center'>品名</td>\n");
        out.write("\n");
        out.write("\t\t\t<td  colspan=3  class=xl65 style='border:1.0pt solid black;text-align: center'> ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mdGoodsPage.shpMingCheng}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write(" </td>\n");
        out.write("\n");
        out.write("        </tr>\n");
        out.write("\t\t");
        out.write("\n");
        out.write("\n");
        out.write("\t\t<tr height=20 style='mso-height-source:userset;height:20.05pt'>\n");
        out.write("\t\t\t<td    height=20 class=xl65 style='height:20.05pt;border:1.0pt solid black;text-align: center'>规格</td>\n");
        out.write("\t\t\t<td    class=xl65 style='border:1.0pt solid black;text-align: center'> ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mdGoodsPage.shpGuiGe}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write(" </td>\n");
        out.write("\t\t\t<td    height=20 class=xl65 style='height:20.05pt;border:1.0pt solid black;text-align: center'>单位</td>\n");
        out.write("\t\t\t<td    class=xl65 style='border:1.0pt solid black;text-align: center'> ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mdGoodsPage.shlDanWei}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write(" </td>\n");
        out.write("\t\t</tr>\n");
        out.write('\n');
        out.write('\n');
        out.write('\n');
        out.write('\n');
        out.write('\n');
        out.write('\n');
        out.write('\n');
        out.write('\n');
        out.write('	');
        out.write('	');
        out.write("\n");
        out.write("\n");
        out.write("\t\t\t");
        out.write("\n");
        out.write("\n");
        out.write("\t\t\t");
        out.write("\n");
        out.write("\n");
        out.write("\t\t");
        out.write("\n");
        out.write("\n");
        out.write("\n");
        out.write("\n");
        out.write("\t\t<tr height=40 style='mso-height-source:userset '>\n");
        out.write("\t\t\t<td height=20 class=xl65 style='height:20.05pt;border:1.0pt solid black;text-align: center' >条码</td>\n");
        out.write("\t\t\t<th colspan=3 height=80 class=xl65  style='border:1.0pt solid black;text-align: center' ><span style=\"font-size: 15pt\"><img width=\"90%\" src=\"rest/wmBaseController/showOrDownbarcodeByurl.do?qrvalue=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${mdGoodsPage.shpBianMa }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("\" /></span></th>\n");
        out.write("\t\t</tr>\n");
        out.write("\t</table>\n");
        int evalDoAfterBody = _jspx_th_t_005fformvalid_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_t_005fformvalid_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005flayout_005fformid_005fdialog.reuse(_jspx_th_t_005fformvalid_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005flayout_005fformid_005fdialog.reuse(_jspx_th_t_005fformvalid_005f0);
    return false;
  }
}
