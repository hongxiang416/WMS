/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2022-11-21 09:18:35 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.webpage.com.zzjee.wm;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class wmPlatIo_002dadd_jsp extends org.apache.jasper.runtime.HttpJspBase
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
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005ftiptype_005flayout_005fformid_005fdialog_005faction;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005ftiptype_005flayout_005fformid_005fdialog_005faction = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fset_0026_005fvar_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005ftiptype_005flayout_005fformid_005fdialog_005faction.release();
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
      out.write("<html lang=\"zh-CN\">\n");
      out.write("<head>\n");
      out.write("  <meta charset=\"utf-8\">\n");
      out.write("  <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("  <title>月台进出</title>\n");
      out.write("  <meta name=\"description\" content=\"\">\n");
      out.write("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"online/template/ledefault/css/vendor.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"online/template/ledefault/css/bootstrap-theme.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"online/template/ledefault/css/bootstrap.css\">\n");
      out.write("  <link rel=\"stylesheet\" href=\"online/template/ledefault/css/app.css\">\n");
      out.write("  \n");
      out.write("  <link rel=\"stylesheet\" href=\"plug-in/Validform/css/metrole/style.css\" type=\"text/css\"/>\n");
      out.write("  <link rel=\"stylesheet\" href=\"plug-in/Validform/css/metrole/tablefrom.css\" type=\"text/css\"/>\n");
      out.write("  \n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/jquery/jquery-1.8.3.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/tools/dataformat.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/easyui/jquery.easyui.min.1.3.2.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/easyui/locale/zh-cn.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/tools/syUtil.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/My97DatePicker/WdatePicker.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/lhgDialog/lhgdialog.min.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/tools/curdtools_zh-cn.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/tools/easyuiextend.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_v5.3.1_min_zh-cn.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/Validform/js/Validform_Datatype_zh-cn.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/Validform/js/datatype_zh-cn.js\"></script>\n");
      out.write("  <script type=\"text/javascript\" src=\"plug-in/Validform/plugin/passwordStrength/passwordStrength-min.js\"></script>\n");
      out.write("  <script type=\"text/javascript\"  charset=\"utf-8\" src=\"plug-in/ueditor/ueditor.config.js\"></script>\n");
      out.write("  <script type=\"text/javascript\"  charset=\"utf-8\" src=\"plug-in/ueditor/ueditor.all.min.js\"></script>\n");
      out.write("\t\t\t\t\t\t\t\t\n");
      out.write("   <script type=\"text/javascript\">\n");
      out.write("  //编写自定义JS代码\n");
      out.write("  </script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write(" <body>\n");
      out.write("\n");
      out.write("\t");
      if (_jspx_meth_t_005fformvalid_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("   $(function(){\n");
      out.write("    //查看模式情况下,删除和上传附件功能禁止使用\n");
      out.write("\tif(location.href.indexOf(\"load=detail\")!=-1){\n");
      out.write("\t\t$(\".jeecgDetail\").hide();\n");
      out.write("\t}\n");
      out.write("\t\n");
      out.write("\tif(location.href.indexOf(\"mode=read\")!=-1){\n");
      out.write("\t\t//查看模式控件禁用\n");
      out.write("\t\t$(\"#formobj\").find(\":input\").attr(\"disabled\",\"disabled\");\n");
      out.write("\t}\n");
      out.write("\tif(location.href.indexOf(\"mode=onbutton\")!=-1){\n");
      out.write("\t\t//其他模式显示提交按钮\n");
      out.write("\t\t$(\"#sub_tr\").show();\n");
      out.write("\t}\n");
      out.write("   });\n");
      out.write("\n");
      out.write("  var neibuClickFlag = false;\n");
      out.write("  function neibuClick() {\n");
      out.write("\t  neibuClickFlag = true; \n");
      out.write("\t  $('#btn_sub').trigger('click');\n");
      out.write("  }\n");
      out.write("\n");
      out.write("</script>\n");
      out.write(" </body>\n");
      out.write("<script src = \"webpage/com/zzjee/wm/wmPlatIo.js\"></script>\t\t\n");
      out.write("</html>");
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

  private boolean _jspx_meth_t_005fformvalid_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  t:formvalid
    org.jeecgframework.tag.core.easyui.FormValidationTag _jspx_th_t_005fformvalid_005f0 = (org.jeecgframework.tag.core.easyui.FormValidationTag) _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005ftiptype_005flayout_005fformid_005fdialog_005faction.get(org.jeecgframework.tag.core.easyui.FormValidationTag.class);
    _jspx_th_t_005fformvalid_005f0.setPageContext(_jspx_page_context);
    _jspx_th_t_005fformvalid_005f0.setParent(null);
    // /webpage/com/zzjee/wm/wmPlatIo-add.jsp(42,1) name = formid type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setFormid("formobj");
    // /webpage/com/zzjee/wm/wmPlatIo-add.jsp(42,1) name = dialog type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setDialog(true);
    // /webpage/com/zzjee/wm/wmPlatIo-add.jsp(42,1) name = usePlugin type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setUsePlugin("password");
    // /webpage/com/zzjee/wm/wmPlatIo-add.jsp(42,1) name = layout type = java.lang.String reqTime = false required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setLayout("table");
    // /webpage/com/zzjee/wm/wmPlatIo-add.jsp(42,1) name = action type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setAction("wmPlatIoController.do?doAdd");
    // /webpage/com/zzjee/wm/wmPlatIo-add.jsp(42,1) name = tiptype type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_t_005fformvalid_005f0.setTiptype("1");
    int _jspx_eval_t_005fformvalid_005f0 = _jspx_th_t_005fformvalid_005f0.doStartTag();
    if (_jspx_eval_t_005fformvalid_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t<input type=\"hidden\" id=\"btn_sub\" class=\"btn_sub\"/>\n");
        out.write("\t\t\t<input type=\"hidden\" id=\"id\" name=\"id\"/>\n");
        out.write("\t\t\t<input type=\"hidden\" id=\"platOper\" name=\"platOper\"/>\n");
        out.write("\t\t\t\n");
        out.write("\t\t\t<div class=\"tab-wrapper\">\n");
        out.write("\t\t\t    <!-- tab -->\n");
        out.write("\t\t\t    <ul class=\"nav nav-tabs\">\n");
        out.write("\t\t\t      <li role=\"presentation\" class=\"active\"><a href=\"javascript:void(0);\">月台进出</a></li>\n");
        out.write("\t\t\t    </ul>\n");
        out.write("\t\t\t    <!-- tab内容 -->\n");
        out.write("\t\t\t    <div class=\"con-wrapper\" id=\"con-wrapper1\" style=\"display: block;\">\n");
        out.write("\t\t\t      <div class=\"row form-wrapper\">\n");
        out.write("\t\t\t\t\t\t\t<div class=\"row show-grid\">\n");
        out.write("\t\t\t          <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>车号：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"carno\" name=\"carno\" type=\"text\" class=\"form-control\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\" datatype=\"*\"\n");
        out.write("\t\t\t\t\t\t\t\t />\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">车号</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          \t          <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>月台编号：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"platId\" name=\"platId\" type=\"text\" class=\"form-control\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\" datatype=\"*\"\n");
        out.write("\t\t\t\t\t\t\t\t />\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">月台编号</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          \t\t\t          <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>单据编号：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"docId\" name=\"docId\" type=\"text\" class=\"form-control\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\" datatype=\"*\"\n");
        out.write("\t\t\t\t\t\t\t\t />\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">单据编号</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t\t\t\t</div>\n");
        out.write("\t\t\t          \n");
        out.write("\t\t          \n");
        out.write("\t\t\t        \n");
        out.write("\t\t\t\t\t\t\t<div class=\"row show-grid\">\n");
        out.write("\t\t\t          <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>进入时间：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"inData\" name=\"inData\" type=\"text\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\"\n");
        out.write("\t\t\t\t\t\t\t\tstyle=\"background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;\" class=\"form-control\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"  type=\"date\" pattern=\"yyyy-MM-dd hh:mm:ss\"/>\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">进入时间</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          \t\t          <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>驶出时间：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"outData\" name=\"outData\" type=\"text\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\"\n");
        out.write("\t\t\t\t\t\t\t\tstyle=\"background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;\" class=\"form-control\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"  type=\"date\" pattern=\"yyyy-MM-dd hh:mm:ss\"/>\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">驶出时间</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t\t\t\t</div>\n");
        out.write("\t\t\t          \n");
        out.write("\t\t          \t\t\t\t\t<div class=\"row show-grid\">\n");
        out.write("\t\t\t          <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>计划进入时间：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"planIndata\" name=\"planIndata\" type=\"text\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\" datatype=\"*\"\n");
        out.write("\t\t\t\t\t\t\t\tstyle=\"background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;\" class=\"form-control\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"  type=\"date\" pattern=\"yyyy-MM-dd hh:mm:ss\"/>\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">计划进入时间</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          \t\t\t          <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>计划驶出时间：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"planOutdata\" name=\"planOutdata\" type=\"text\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\"\n");
        out.write("\t\t\t\t\t\t\t\tstyle=\"background: url('plug-in/ace/images/datetime.png') no-repeat scroll right center transparent;\" class=\"form-control\" onClick=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})\"  type=\"date\" pattern=\"yyyy-MM-dd hh:mm:ss\"/>\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">计划驶出时间</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t\t\t\t</div>\n");
        out.write("\t\t\t        \n");
        out.write("\t\t\t\t\t\t\t<div class=\"row show-grid\">\n");
        out.write("\t\t\t          <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>月台状态：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"platSta\" name=\"platSta\" type=\"text\" class=\"form-control\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\"\n");
        out.write("\t\t\t\t\t\t\t\t />\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">月台状态</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t              <div class=\"col-xs-3 text-center\">\n");
        out.write("\t\t\t          \t<b>备注：</b>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t          <div class=\"col-xs-3\">\n");
        out.write("\t\t\t\t\t\t\t\t<input id=\"platBeizhu\" name=\"platBeizhu\" type=\"text\" class=\"form-control\" \n");
        out.write("\t\t\t\t\t\t\t\t\tignore=\"ignore\"\n");
        out.write("\t\t\t\t\t\t\t\t />\n");
        out.write("\t\t\t\t\t\t<span class=\"Validform_checktip\" style=\"float:left;height:0px;\"></span>\n");
        out.write("\t\t\t\t\t\t<label class=\"Validform_label\" style=\"display: none\">备注</label>\n");
        out.write("\t\t\t          </div>\n");
        out.write("\t\t\t\t\t\t</div>\n");
        out.write("\t\n");
        out.write("\t\t\t          \n");
        out.write("\t\t\t        \n");
        out.write("\t\t\n");
        out.write("\t\t       \n");
        out.write("\t\t\t          <div class=\"row\" id = \"sub_tr\" style=\"display: none;\">\n");
        out.write("\t\t\t\t        <div class=\"col-xs-12 layout-header\">\n");
        out.write("\t\t\t\t          <div class=\"col-xs-6\"></div>\n");
        out.write("\t\t\t\t          <div class=\"col-xs-6\"><button type=\"button\" onclick=\"neibuClick();\" class=\"btn btn-default\">提交</button></div>\n");
        out.write("\t\t\t\t        </div>\n");
        out.write("\t\t\t\t      </div>\n");
        out.write("\t\t\t     </div>\n");
        out.write("\t\t\t   </div>\n");
        out.write("\t\t\t   \n");
        out.write("\t\t\t   <div class=\"con-wrapper\" id=\"con-wrapper2\" style=\"display: block;\"></div>\n");
        out.write("\t\t\t </div>\n");
        out.write("  ");
        int evalDoAfterBody = _jspx_th_t_005fformvalid_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_t_005fformvalid_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005ftiptype_005flayout_005fformid_005fdialog_005faction.reuse(_jspx_th_t_005fformvalid_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005ft_005fformvalid_0026_005fusePlugin_005ftiptype_005flayout_005fformid_005fdialog_005faction.reuse(_jspx_th_t_005fformvalid_005f0);
    return false;
  }
}
