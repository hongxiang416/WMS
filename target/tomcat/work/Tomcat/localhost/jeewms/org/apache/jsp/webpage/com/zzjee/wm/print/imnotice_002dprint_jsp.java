/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-11-14 03:53:03 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.webpage.com.zzjee.wm.print;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class imnotice_002dprint_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

private static org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("fn:length", org.apache.taglibs.standard.functions.Functions.class, "length", new Class[] {java.lang.Object.class});
}

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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005flayout_005fformid_005fdialog = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005ft_005fbase_0026_005ftype_005fnobody.release();
    _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005flayout_005fformid_005fdialog.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
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
      out.write("\t<title>入库通知打印</title>\n");
      out.write("\t");
      if (_jspx_meth_t_005fbase_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t<script type=\"text/javascript\" charset=\"utf-8\" src=\"webpage/com/zzjee/wmjs/jquery.jqprint.js\"></script>\n");
      out.write("\t<script language=\"javascript\">\n");
      out.write("\t\tfunction printall(){\n");
      out.write("\n");
      out.write("\t\t\t$(\".printdiv\").jqprint();\n");
      out.write("\n");
      out.write("\t\t}\n");
      out.write("\t\tfunction printview(){\n");
      out.write("\t\t\tdocument.all.WebBrowser1.ExecWB(7,1);\n");
      out.write("\t\t}\n");
      out.write("\n");
      out.write("\n");
      out.write("\t</script>\n");
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
      out.write("<a class=\"easyui-linkbutton\" style=\"margin-top:3px\" icon=\"icon-print\" href=\"javascript:printall()\">打印</a>\n");
      out.write("\n");
      out.write("<div class=\"printdiv\">");
      if (_jspx_meth_t_005fformvalid_005f0(_jspx_page_context))
        return;
      out.write("</div>\n");
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
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(7,1) name = type type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(103,22) name = formid type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setFormid("formobj");
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(103,22) name = dialog type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setDialog(true);
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(103,22) name = usePlugin type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setUsePlugin("password");
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(103,22) name = layout type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setLayout("table");
    int _jspx_eval_t_005fformvalid_005f0 = _jspx_th_t_005fformvalid_005f0.doStartTag();
    if (_jspx_eval_t_005fformvalid_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t<input id=\"content\" type=\"hidden\" value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wmOmNoticeHPage.omNoticeId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("\">\n");
        out.write("\t<table border=0 cellpadding=0 cellspacing=0 width=680 style='border-collapse:\n");
        out.write(" collapse;table-layout:fixed;width:438pt;margin-left: 50px;margin-top: -30px'>\n");
        out.write("\t\t<col width=102 style='mso-width-source:userset;mso-width-alt:3612;width:76pt'>\n");
        out.write("\n");
        out.write("\t\t\t");
        out.write("\n");
        out.write("\t\t<col width=253 style='mso-width-source:userset;mso-width-alt:4010;width:252pt'>\n");
        out.write("\t\t<col width=45 style='mso-width-source:userset;mso-width-alt:1592;width:34pt'>\n");
        out.write("\t\t<col width=94  style='mso-width-source:userset;mso-width-alt:2986;\n");
        out.write(" width:34pt'>\n");
        out.write("\t\t<col width=67 style='mso-width-source:userset;mso-width-alt:2389;width:50pt'>\n");
        out.write("\t\t<col width=102 style='mso-width-source:userset;mso-width-alt:2389;width:60pt'>\n");
        out.write("\t\t<col width=67 style='mso-width-source:userset;mso-width-alt:2389;width:50pt'>\n");
        out.write("\t\t<col width=67 style='mso-width-source:userset;mso-width-alt:2389;width:50pt'>\n");
        out.write("\t\t<col width=67 style='mso-width-source:userset;mso-width-alt:2389;width:50pt'>\n");
        out.write("\n");
        out.write("\t\t<col width=67 style='mso-width-source:userset;mso-width-alt:2389;width:50pt'>\n");
        out.write("\t\t<col width=67 style='mso-width-source:userset;mso-width-alt:2389;width:50pt'>\n");
        out.write("\n");
        out.write("\t\t<tr height=18 style='height:13.2pt'>\n");
        out.write("\t\t\t<td colspan=9 height=18 width=585 style='height:13.2pt;width:438pt'></td>\n");
        out.write("\t\t</tr>\n");
        out.write("\t\t<tr height=40 style='mso-height-source:userset;height:30.0pt'>\n");
        out.write("\t\t\t<td colspan=11 height=40 class=xl67 style='height:30.0pt;text-align: center;'><span style=\"font-size: 18pt\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${comname}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("入库通知单</span></td>\n");
        out.write("\n");
        out.write("\t\t</tr>\n");
        out.write("\t\t<tr height=25 style='mso-height-source:userset;height:25.0pt'>\n");
        out.write("\t\t\t<td colspan=4 height=25 class=xl68 width=242 style='height:25.0pt;width:182pt'>日期：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${kprq}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</td>\n");
        out.write("\t\t\t<td colspan=4 class=xl68 width=168 style='width:126pt'>采购单号：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noticeid}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</td>\n");
        out.write("\t\t\t<td  rowspan=\"3\" class=xl69>\n");
        out.write("\t\t\t\t<img src=\"rest/wmBaseController/showOrDownqrcodeByurl?qrvalue=");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wmImNoticeHPage.noticeId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("\" style=\"width:80px;height:80px;vertical-align:right\">\n");
        out.write("\t\t\t</td>\n");
        out.write("\t\t\t<td></td>\n");
        out.write("\t\t\t<td rowspan=\"7\" style=\"font-size: 10px;\">①<br/>白<br/>存<br/>根<br/><br/>②<br/>红<br/>货<br/>主<br/><br/>③<br/>绿<br/>回<br/>单<br/><br/>④<br/>黄<br/>财<br/>务<br/></td>\n");
        out.write("\t\t</tr>\n");
        out.write("\t\t<tr height=25 style='mso-height-source:userset;height:25.0pt'>\n");
        out.write("\t\t\t<td colspan=4 height=25 class=xl68 width=242 style='height:25.0pt;width:182pt'>货主：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cusname}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</td>\n");
        out.write("\t\t\t<td colspan=4 height=25 class=xl68 width=242 style='height:25pt;width:182pt'>生产厂商：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${supname}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</td>\n");
        out.write("\t\t</tr>\n");
        out.write("\t\t<tr height=25 style='mso-height-source:userset;height:25.0pt'>\n");
        out.write("\t\t\t<td colspan=4 class=xl68 width=337 style='width:252pt'>备注：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wmImNoticeHPage.imBeizhu}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</td>\n");
        out.write("\t\t\t<td colspan=4 >进货单号：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wmImNoticeHPage.noticeId}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</td>\n");
        out.write("\t\t</tr>\n");
        out.write("\t\t<tr height=25 style='mso-height-source:userset;height:25.0pt'>\n");
        out.write("\t\t\t<td colspan=8 class=xl68 width=337 style='width:252pt'>仓库：");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${storeName}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</td>\n");
        out.write("\t\t</tr>\n");
        out.write("\n");
        out.write("\n");
        out.write("\n");
        out.write("\t\t<tr height=33 style='mso-height-source:userset;height:25.05pt'>\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>商品编码</td>\n");
        out.write("\t\t\t\t");
        out.write("\n");
        out.write("\t\t\t<td height=33 class=xl65 style='height:25.05pt;border:1.0pt solid black;text-align: center'>商品</td>\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>规格</td>\n");
        out.write("\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>单位</td>\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>生产日期</td>\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>生产批号</td>\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>保质期</td>\n");
        out.write("\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>数量</td>\n");
        out.write("\t\t\t\t");
        out.write("\n");
        out.write("\t\t\t\t");
        out.write("\n");
        out.write("\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>储位</td>\n");
        out.write("\n");
        out.write("\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>二维码</td>\n");
        out.write("\n");
        out.write("\t\t</tr>\n");
        out.write("\n");
        out.write("\t\t");
        if (_jspx_meth_c_005fif_005f0(_jspx_th_t_005fformvalid_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("\t\t<tr height=20 style='height:25.0pt'>\n");
        out.write("\t\t\t<td height=20 class=xl66 colspan=8 style='height:15.0pt;mso-ignore:colspan;text-align: justify'>制单：<span\n");
        out.write("\t\t\t\t\tstyle='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
        out.write("  </span>仓管： ");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${wmOmNoticeHPage.createBy}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("<span\n");
        out.write("\t\t\t\t\tstyle='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
        out.write("  </span>主管：<span\n");
        out.write("\t\t\t\t\tstyle='mso-spacerun:yes'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
        out.write("  </span>叉车：<span\n");
        out.write("\t\t\t\t\tstyle='mso-spacerun:yes'></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n");
        out.write("\t\t\t</td>\n");
        out.write("\t\t\t<td></td>\n");
        out.write("\t\t</tr>\n");
        out.write("\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_t_005fformvalid_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_t_005fformvalid_005f0);
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(174,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${fn:length(wmImNoticeIList)  > 0 }", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, _jspx_fnmap_0, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t");
        if (_jspx_meth_c_005fforEach_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("\n");
        out.write("\t\t\t<tr height=33 style='mso-height-source:userset;height:50px'>\n");
        out.write("\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>合计</td>\n");
        out.write("\t\t\t\t\t");
        out.write("\n");
        out.write("\t\t\t\t<td class=xl65 colspan=\"6\" style='border:1.0pt solid black;text-align: center;word-break:break-all;'><span style='word-break:break-all;width: auto;font-size: 14pt'></span></td>\n");
        out.write("\t\t\t\t\t");
        out.write("\n");
        out.write("\n");
        out.write("\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${totalCount}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
        out.write("</td>\n");
        out.write("\t\t\t\t\t");
        out.write("\n");
        out.write("\n");
        out.write("\t\t\t\t\t");
        out.write("\n");
        out.write("\n");
        out.write("\t\t\t\t<td class=xl65 colspan=\"2\"  style='border:1.0pt solid black;text-align: center'></td>\n");
        out.write("\n");
        out.write("\t\t\t\t\t");
        out.write("\n");
        out.write("\t\t\t</tr>\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(175,3) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/webpage/com/zzjee/wm/print/imnotice-print.jsp(175,3) '${wmImNoticeIList}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${wmImNoticeIList}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(175,3) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("poVal");
    // /webpage/com/zzjee/wm/print/imnotice-print.jsp(175,3) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("stuts");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\n");
          out.write("\t\t\t\t<tr height=33 style='mso-height-source:userset;height:50px'>\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.goodsCode }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("　</td>\n");
          out.write("\t\t\t\t\t\t");
          out.write("\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center;word-break:break-all;'><span style='word-break:break-all;width: auto;font-size: 14pt'>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.goodsName }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</span></td>\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.shpGuiGe }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.goodsUnit }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.goodsPrdData }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'></td>\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.bzhiQi }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.goodsCount }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t\t\t\t");
          out.write("\n");
          out.write("\n");
          out.write("\t\t\t\t\t\t");
          out.write("\n");
          out.write("\n");
          out.write("\t\t\t\t\t<td class=xl65 style='border:1.0pt solid black;text-align: center'></td>\n");
          out.write("\n");
          out.write("\t\t\t\t\t<td class=xl65 align=\"center\" valign=\"middle\" style='border:1.0pt solid black'><img src=\"rest/wmBaseController/showOrDownqrcodeByurl?qrvalue=");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.goodsCode }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" alt=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${poVal.goodsCode }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("\" style=\"width:40px;height:40px;vertical-align:middle;\">　</td>\n");
          out.write("\t\t\t\t\t<td  ></td>\n");
          out.write("\t\t\t\t</tr>\n");
          out.write("\n");
          out.write("\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}