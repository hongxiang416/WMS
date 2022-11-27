<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="wmsAppRoleList" checkbox="true" pagination="true" fitColumns="false" title="APP角色" actionUrl="wmsAppRoleController.do?datagrid" idField="id" fit="true" queryMode="group">
    <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="角色编号"  field="approleCode"   query="true" queryMode="single"  width="120"></t:dgCol>
    <t:dgCol title="角色名称"  field="approleName"   query="true" queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="app模块id"  field="appmodelId"    queryMode="group" dictionary="pop_app_fun,id,appmodel_code,appmodel_name,appmodel_id,appmodel_code,appmodel_name" popup="true" width="120"></t:dgCol>--%>
    <t:dgCol title="app模块编号"  field="appmodelCode"   query="true" queryMode="single"  width="120"></t:dgCol>
<%--    <t:dgCol title="app模块名称"  field="appmodelName"   query="true" queryMode="single"  width="120"></t:dgCol>--%>
    <t:dgCol title="备用1"  field="query1"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="备用2"  field="query2"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="备用3"  field="query3"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="备用4"  field="query4"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="备用5"  field="query5"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
    <t:dgCol title="备用6"  field="query6"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="wmsAppRoleController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="wmsAppRoleController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="wmsAppRoleController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="wmsAppRoleController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="wmsAppRoleController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zzjee/uniapp/wmsAppRoleList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
 });



//导入
function ImportXls() {
	openwindow('Excel导入', 'wmsAppRoleController.do?upload', "wmsAppRoleList");
}

//导出
function ExportXls() {
	JeecgExcelExport("wmsAppRoleController.do?exportXls","wmsAppRoleList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("wmsAppRoleController.do?exportXlsByT","wmsAppRoleList");
}

 </script>
