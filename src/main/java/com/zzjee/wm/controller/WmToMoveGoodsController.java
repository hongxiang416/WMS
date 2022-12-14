package com.zzjee.wm.controller;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.zzjee.md.entity.MvCusEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.Now;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.*;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.zzjee.api.ResultDO;
import com.zzjee.wm.entity.WmToMoveGoodsEntity;
import com.zzjee.wm.page.wmtomovepage;
import com.zzjee.wm.service.WmToMoveGoodsServiceI;
import com.zzjee.wmutil.wmUtil;

import static com.xiaoleilu.hutool.date.DateTime.now;

/**
 * @Title: Controller
 * @Description: ????????????
 * @author erzhongxmu
 * @date 2017-09-08 21:03:22
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/wmToMoveGoodsController")
public class WmToMoveGoodsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WmToMoveGoodsController.class);

	@Autowired
	private WmToMoveGoodsServiceI wmToMoveGoodsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;



	/**
	 * ?????????????????? ????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zzjee/wm/wmToMoveGoodsList");
	}

	/**
	 * easyui AJAX????????????
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(WmToMoveGoodsEntity wmToMoveGoods,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WmToMoveGoodsEntity.class, dataGrid);
		//?????????????????????
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wmToMoveGoods, request.getParameterMap());
		try{
		//???????????????????????????
		}catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("createDate", "desc");
		cq.setOrder(map1);
		cq.add();
		this.wmToMoveGoodsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WmToMoveGoodsEntity wmToMoveGoods, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		wmToMoveGoods = systemService.getEntity(WmToMoveGoodsEntity.class, wmToMoveGoods.getId());
		message = "????????????????????????";
		try{
			wmToMoveGoods.setMoveSta("?????????");
			wmToMoveGoodsService.saveOrUpdate(wmToMoveGoods);
			systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "????????????????????????";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * ????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "doBatchUpdate")
	@ResponseBody
	public AjaxJson doBatchUpdate(String ids,String moveSta,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????????????????";
		try{
			for(String id:ids.split(",")){
				WmToMoveGoodsEntity wmToMoveGoods = systemService.getEntity(WmToMoveGoodsEntity.class,
						id
				);
				message = "??????????????????";
				String movesta = "?????????";
				try{
					movesta = ResourceUtil.getConfigByName("wm.movesta");

				}catch (Exception e){

				}

				wmToMoveGoods.setMoveSta(movesta);
				wmToMoveGoodsService.updateEntitie(wmToMoveGoods);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "????????????????????????";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * ????????????????????????
	 *
	 * @return
	 */
	 @RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????????????????";
		try{
			for(String id:ids.split(",")){
				WmToMoveGoodsEntity wmToMoveGoods = systemService.getEntity(WmToMoveGoodsEntity.class,
				id
				);
				wmToMoveGoods.setMoveSta("?????????");
				wmToMoveGoodsService.updateEntitie(wmToMoveGoods);
				systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
			}
		}catch(Exception e){
			e.printStackTrace();
			message = "????????????????????????";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}




		@RequestMapping(params = "doGetstock", method = { RequestMethod.GET,
				RequestMethod.POST })
		@ResponseBody
		public AjaxJson doGetstock(HttpServletRequest request) {
			AjaxJson j = new AjaxJson();
			WmToMoveGoodsEntity  wmToMoveGoods = new WmToMoveGoodsEntity();



			j.setObj(wmToMoveGoods);
			return j;
		}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WmToMoveGoodsEntity wmToMoveGoods, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????????????????";
		try{
			wmToMoveGoods.setMoveSta("?????????");
			wmToMoveGoodsService.save(wmToMoveGoods);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "????????????????????????";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WmToMoveGoodsEntity wmToMoveGoods, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????????????????";
		WmToMoveGoodsEntity t = wmToMoveGoodsService.get(WmToMoveGoodsEntity.class, wmToMoveGoods.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(wmToMoveGoods, t);
			try{
				MvCusEntity mvcus = systemService.findUniqueByProperty(MvCusEntity.class, "cusCode",wmToMoveGoods.getToCusCode() ) ;
				t.setToCusName(mvcus.getCusName());
			}catch (Exception e){

			}

			wmToMoveGoodsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "????????????????????????";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}



	/**
	 *
	 * @param page
	 * @return
	 */
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(wmtomovepage page){
		String message = null;
		List<WmToMoveGoodsEntity> demos=page.getWmtomoverows();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(demos)){
			for(WmToMoveGoodsEntity jeecgDemo:demos){
				if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
					WmToMoveGoodsEntity t =systemService.get(WmToMoveGoodsEntity.class, jeecgDemo.getId());
					try {
						if(!wmUtil.checkstcok( jeecgDemo.getBinFrom(),jeecgDemo.getTinFrom(),jeecgDemo.getGoodsId(),jeecgDemo.getGoodsProData(),jeecgDemo.getBaseGoodscount())) {
						}else{
							message = "??????????????????";
							String movesta = "?????????";
							try{
								movesta = ResourceUtil.getConfigByName("wm.movesta");

							}catch (Exception e){

							}
							t.setMoveSta(movesta);
							MyBeanUtils.copyBeanNotNull2Bean(jeecgDemo, t);
							systemService.saveOrUpdate(t);
							systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return j;
	}




	/**
	 * ??????????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(WmToMoveGoodsEntity wmToMoveGoods, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wmToMoveGoods.getId())) {
			wmToMoveGoods = wmToMoveGoodsService.getEntity(WmToMoveGoodsEntity.class, wmToMoveGoods.getId());
			req.setAttribute("wmToMoveGoodsPage", wmToMoveGoods);
		}
		return new ModelAndView("com/zzjee/wm/wmToMoveGoods-add");
	}
	/**
	 * ??????????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WmToMoveGoodsEntity wmToMoveGoods, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wmToMoveGoods.getId())) {
			wmToMoveGoods = wmToMoveGoodsService.getEntity(WmToMoveGoodsEntity.class, wmToMoveGoods.getId());
			req.setAttribute("wmToMoveGoodsPage", wmToMoveGoods);
		}
		return new ModelAndView("com/zzjee/wm/wmToMoveGoods-update");
	}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name","wmToMoveGoodsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * ??????excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(WmToMoveGoodsEntity wmToMoveGoods,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(WmToMoveGoodsEntity.class, dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wmToMoveGoods, request.getParameterMap());
		List<WmToMoveGoodsEntity> wmToMoveGoodss = this.wmToMoveGoodsService.getListByCriteriaQuery(cq,false);
		modelMap.put(NormalExcelConstants.FILE_NAME,"????????????");
		modelMap.put(NormalExcelConstants.CLASS,WmToMoveGoodsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("??????????????????", "?????????:"+ResourceUtil.getSessionUserName().getRealName(),
			"????????????"));
		modelMap.put(NormalExcelConstants.DATA_LIST,wmToMoveGoodss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * ??????excel ?????????
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(WmToMoveGoodsEntity wmToMoveGoods,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid,ModelMap modelMap) {
    	modelMap.put(NormalExcelConstants.FILE_NAME,"????????????");
    	modelMap.put(NormalExcelConstants.CLASS,WmToMoveGoodsEntity.class);
    	modelMap.put(NormalExcelConstants.PARAMS,new ExportParams("??????????????????", "?????????:"+ResourceUtil.getSessionUserName().getRealName(),
    	"????????????"));
    	modelMap.put(NormalExcelConstants.DATA_LIST,new ArrayList());
    	return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// ????????????????????????
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setHeadRows(1);
			params.setNeedSave(true);
			try {
				List<WmToMoveGoodsEntity> listWmToMoveGoodsEntitys = ExcelImportUtil.importExcel(file.getInputStream(),WmToMoveGoodsEntity.class,params);
				for (WmToMoveGoodsEntity wmToMoveGoods : listWmToMoveGoodsEntitys) {
					if(StringUtil.isNotEmpty(wmToMoveGoods.getOrderIdI())){
						try{
							WmToMoveGoodsEntity t = systemService.findByProperty(WmToMoveGoodsEntity.class,"orderIdI",wmToMoveGoods.getOrderIdI()).get(0);
							if(t!=null){
								continue;
							}
						}catch (Exception e){

						}
					}
					wmToMoveGoods.setRunSta("?????????");
					wmToMoveGoodsService.save(wmToMoveGoods);
				}
				j.setMsg("?????????????????????");
			} catch (Exception e) {
				j.setMsg("?????????????????????");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?>  list( @RequestParam(value="username", required=false) String username,
									@RequestParam(value="searchstr", required=false)String searchstr,
									@RequestParam(value="searchstr2", required=false)String searchstr2) {
//		return listWvGis;


		ResultDO D0 = new  ResultDO();
		String hql = " from WmToMoveGoodsEntity where 1 = 1 and moveSta = '?????????' ";
		D0.setOK(true);
		if(!StringUtil.isEmpty(searchstr)) {
			hql=hql+"  and binFrom like '%" + searchstr + "%'";
		}
		if(!StringUtil.isEmpty(searchstr2)) {
			try{
				String shpbianma = wmUtil.getmdgoodsbytiaoma(searchstr2);
				if(StringUtil.isNotEmpty(shpbianma)){
					searchstr2=shpbianma;
				}
			}catch (Exception e){

			}
			hql=hql+"  and goodsId like '%" + searchstr2 + "%'";
		}

		List<WmToMoveGoodsEntity> listWmToMoveGoodss=wmToMoveGoodsService.findHql(hql);


		List<WmToMoveGoodsEntity> result = new ArrayList<WmToMoveGoodsEntity>();
		int i = 0;
		for (WmToMoveGoodsEntity t :listWmToMoveGoodss){

			i++;
			if(i>100){
				break;
			}
			result.add(t);
		}
		if(result.size()<=0){
			D0.setOK(false);
		}
		D0.setObj(result);
		return new ResponseEntity(D0, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		WmToMoveGoodsEntity task = wmToMoveGoodsService.get(WmToMoveGoodsEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody WmToMoveGoodsEntity wmToMoveGoods, UriComponentsBuilder uriBuilder) {
		//??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		Set<ConstraintViolation<WmToMoveGoodsEntity>> failures = validator.validate(wmToMoveGoods);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//??????
		try{
			wmToMoveGoodsService.save(wmToMoveGoods);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		//??????Restful???????????????????????????????????????url, ?????????????????????id?????????.
		String id = wmToMoveGoods.getId();
		URI uri = uriBuilder.path("/rest/wmToMoveGoodsController/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/change", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> update(@RequestParam String wmToMoveGoodsstr,
									UriComponentsBuilder uriBuilder) {
		//??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		ResultDO D0 = new  ResultDO();
		WmToMoveGoodsEntity wmToMoveGoods  = (WmToMoveGoodsEntity)JSONHelper.json2Object(wmToMoveGoodsstr,WmToMoveGoodsEntity.class);
		//??????
		try{
			WmToMoveGoodsEntity t = systemService.get(WmToMoveGoodsEntity.class,wmToMoveGoods.getId());
			MyBeanUtils.copyBeanNotNull2Bean(wmToMoveGoods,t);
			try{
				if(StringUtil.isNotEmpty(wmToMoveGoods.getUpdateBy())){
					TSUser tsuser = systemService.findUniqueByProperty(TSUser.class,"",wmToMoveGoods.getUpdateBy());
					t.setUpdateName(tsuser.getRealName());
				}
			}catch (Exception e){

			}
			t.setUpdateDate(now());
//			if(!wmUtil.checkstcok( t.getBinFrom(),t.getTinFrom(),t.getGoodsId(),t.getGoodsProData(),t.getBaseGoodscount())) {
//				D0.setOK(false);
//				D0.setErrorMsg("????????????");
//			}else{

				String movesta = "?????????";
				try{
					movesta = ResourceUtil.getConfigByName("wm.movesta");

				}catch (Exception e){

				}
				t.setMoveSta(movesta);
				wmToMoveGoodsService.saveOrUpdate(t);
				D0.setOK(true);
//			}


		} catch (Exception e) {
			e.printStackTrace();
			D0.setOK(false);

		}

		//???Restful???????????????204?????????, ?????????. ???????????????200?????????.
		return new ResponseEntity(D0, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		wmToMoveGoodsService.deleteEntityById(WmToMoveGoodsEntity.class, id);
	}
}
