package com.zzjee.md.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import com.zzjee.md.entity.MdCusEntity;
import com.zzjee.wm.entity.WmOmQmIEntity;
import com.zzjee.wm.page.WmOmNoticeImpnewPage;
import com.zzjee.wm.page.WmTmsNoticeHPage;
import com.zzjee.wmutil.dsc.dscUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.zzjee.api.ResultDO;
import com.zzjee.md.entity.MdCusOtherEntity;
import com.zzjee.md.entity.MdGoodsEntity;
import com.zzjee.md.entity.MvGoodsEntity;
import com.zzjee.md.service.MdGoodsServiceI;
import com.zzjee.wm.entity.WmOmNoticeHEntity;
import com.zzjee.wm.entity.WmOmNoticeIEntity;
import com.zzjee.wm.service.WmOmNoticeHServiceI;
import com.zzjee.wmutil.wmIntUtil;
import com.zzjee.wmutil.wmUtil;
import com.zzjee.wmutil.yyUtil;

/**
 * @Title: Controller
 * @Description: ????????????
 * @author erzhongxmu
 * @date 2017-08-15 23:16:53
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/mdGoodsController")
public class MdGoodsController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(MdGoodsController.class);

	@Autowired
	private MdGoodsServiceI mdGoodsService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private Validator validator;

	@Autowired
	private WmOmNoticeHServiceI wmOmNoticeHService;
	/**
	 * ?????????????????? ????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "list")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("com/zzjee/md/mdGoodsList");
	}

	@RequestMapping(params = "listall")
	public ModelAndView listall(HttpServletRequest request) {
		return new ModelAndView("com/zzjee/md/mdGoodsallList");
	}
	/**
	 * easyui AJAX????????????
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(MdGoodsEntity mdGoods, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(MdGoodsEntity.class, dataGrid);
		// ?????????????????????
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				mdGoods, request.getParameterMap());

		if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
			cq.eq("suoShuKeHu", wmUtil.getCusCode());
		}


//		if(mdGoods.getZhuangTai()==null){
//			cq.notEq("zhuangTai", "Y");
//		}
		cq.add();
		this.mdGoodsService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(MdGoodsEntity mdGoods, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		mdGoods = systemService.getEntity(MdGoodsEntity.class, mdGoods.getId());
		message = "??????????????????";
		try {
//			mdGoods.setZhuangTai("Y");
			if(wmUtil.checkishavestock("goods",mdGoods.getShpBianMa())){
				message = "??????????????????";
				j.setSuccess(false);
				j.setMsg(message);
				return j;
			}
			mdGoodsService.delete(mdGoods);;
			systemService.addLog(message, Globals.Log_Type_DEL,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "??????????????????";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}


	@RequestMapping(params = "doPrintmdgoods")
	public ModelAndView doPrintpzhuisu(String id,HttpServletRequest request) {
		MdGoodsEntity mdGoodsEntity = mdGoodsService.getEntity(MdGoodsEntity.class, id);
		request.setAttribute("mdGoodsPage", mdGoodsEntity);
		return new ModelAndView("com/zzjee/wm/print/mdGoods-print");
	}



	/**
	 * ????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????????????????";
		try {
			for (String id : ids.split(",")) {
				MdGoodsEntity mdGoods = systemService.getEntity(
						MdGoodsEntity.class, id);
				if(wmUtil.checkishavestock("goods",mdGoods.getShpBianMa())){
					message = "??????????????????";
					j.setSuccess(false);
					j.setMsg(message);
					return j;
				}
				mdGoodsService.delete(mdGoods);
				systemService.addLog(message, Globals.Log_Type_DEL,
						Globals.Log_Leavel_INFO);
			}
		} catch (Exception e) {
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
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(MdGoodsEntity mdGoods, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????????????????";
		try {
			if(StringUtil.isEmpty(mdGoods.getChlKongZhi()) ){
				mdGoods.setChlKongZhi("N");
				mdGoods.setChlShl("1");
				mdGoods.setJshDanWei(mdGoods.getShlDanWei());
			}
			try {
				if(StringUtil.isEmpty(mdGoods.getZhlKgm())){
					if(!StringUtil.isEmpty(mdGoods.getBzhiQi())){
						int bzhiq = Integer.parseInt(mdGoods.getBzhiQi());
						mdGoods.setZhlKgm(Integer.toString(bzhiq));
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(StringUtil.isEmpty(mdGoods.getShpBianMa())){
				//???????????????????????????????????????
				Map<String, Object> countMap = systemService.findOneForJdbc("select right(shp_bian_ma,7) shp_bian_ma  from md_goods where category_code =? and suo_shu_ke_hu  = ? and shp_bian_ma like ? ORDER BY shp_bian_ma desc LIMIT 1",mdGoods.getCategoryCode(),mdGoods.getSuoShuKeHu(),mdGoods.getSuoShuKeHu()+mdGoods.getCategoryCode()+"%");
				if (countMap == null) {
					mdGoods.setShpBianMa(mdGoods.getSuoShuKeHu()+mdGoods.getCategoryCode()+String.format("%07d", 1));
				}else {
					Object goodsCode = countMap.get("shp_bian_ma");
					if (goodsCode != null) {
						mdGoods.setShpBianMa(mdGoods.getSuoShuKeHu()+mdGoods.getCategoryCode()+String.format("%07d",Integer.parseInt(((String) goodsCode))+1));
					}else {
						mdGoods.setShpBianMa(mdGoods.getSuoShuKeHu()+mdGoods.getCategoryCode()+String.format("%07d", 1));
					}
				}
				mdGoodsService.save(mdGoods);
				systemService.addLog(message, Globals.Log_Type_INSERT,
						Globals.Log_Leavel_INFO);
			}else{
				MdGoodsEntity mdGoods1 = systemService.findUniqueByProperty(
						MdGoodsEntity.class, "shpBianMa", mdGoods.getShpBianMa());
				if(mdGoods1 != null){
					message = "????????????????????????";
					j.setSuccess(false);
				}else{
					mdGoodsService.save(mdGoods);
					systemService.addLog(message, Globals.Log_Type_INSERT,
							Globals.Log_Leavel_INFO);
				}

			}
		} catch (Exception e) {
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
	@RequestMapping(params = "doGet")
	@ResponseBody
	public AjaxJson dogetfromother(String formDate,String othercode, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????????????????";

		try {

			if ("U8".equals(ResourceUtil.getConfigByName("interfacetype"))){
				yyUtil.getProduct(othercode);

			}
			if ("UAS".equals(ResourceUtil.getConfigByName("interfacetype"))){
				if(StringUtil.isEmpty(formDate)){
					formDate = "2011-01-01";
				}
				wmIntUtil.getproduct(formDate);

			}
			if ("DSC".equals(ResourceUtil.getConfigByName("interfacetype"))){

				dscUtil.updateGoodsFromDsc();

			}
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
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
	public AjaxJson doUpdate(MdGoodsEntity mdGoods, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????????????????";
		MdGoodsEntity t = mdGoodsService.get(MdGoodsEntity.class,
				mdGoods.getId());
		try {
			MyBeanUtils.copyBeanNotNull2Bean(mdGoods, t);
			mdGoodsService.saveOrUpdate(t);
			systemService.addLog(message, Globals.Log_Type_UPDATE,
					Globals.Log_Leavel_INFO);
		} catch (Exception e) {
			e.printStackTrace();
			message = "????????????????????????";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * ??????????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(MdGoodsEntity mdGoods, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mdGoods.getId())) {
			mdGoods = mdGoodsService.getEntity(MdGoodsEntity.class,mdGoods.getId());
			req.setAttribute("mdGoodsPage", mdGoods);
		}
		return new ModelAndView("com/zzjee/md/mdGoods-add");
	}

	/**
	 * ??????????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(MdGoodsEntity mdGoods, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(mdGoods.getId())) {
			mdGoods = mdGoodsService.getEntity(MdGoodsEntity.class,
					mdGoods.getId());
			req.setAttribute("mdGoodsPage", mdGoods);
		}
		return new ModelAndView("com/zzjee/md/mdGoods-update");
	}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "mdGoodsController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * ??????excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(MdGoodsEntity mdGoods, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid, ModelMap modelMap) {
		CriteriaQuery cq = new CriteriaQuery(MdGoodsEntity.class, dataGrid);
		if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
			cq.eq("suoShuKeHu", wmUtil.getCusCode());
		}
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				mdGoods, request.getParameterMap());
		List<MdGoodsEntity> mdGoodss = this.mdGoodsService
				.getListByCriteriaQuery(cq, false);
		modelMap.put(NormalExcelConstants.FILE_NAME, "????????????");
		modelMap.put(NormalExcelConstants.CLASS, MdGoodsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("??????????????????",
				"?????????:" + ResourceUtil.getSessionUserName().getRealName(),
				"????????????"));
		modelMap.put(NormalExcelConstants.DATA_LIST, mdGoodss);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * ??????excel ?????????
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(MdGoodsEntity mdGoods,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid, ModelMap modelMap) {
		modelMap.put(NormalExcelConstants.FILE_NAME, "????????????");
		modelMap.put(NormalExcelConstants.CLASS, MdGoodsEntity.class);
		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams("??????????????????",
				"?????????:" + ResourceUtil.getSessionUserName().getRealName(),
				"????????????"));
		modelMap.put(NormalExcelConstants.DATA_LIST, new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request,
			HttpServletResponse response) {
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
				List<MdGoodsEntity> listMdGoodsEntitys = ExcelImportUtil
						.importExcel(file.getInputStream(),
								MdGoodsEntity.class, params);
				for (MdGoodsEntity mdGoods : listMdGoodsEntitys) {
					if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
						mdGoods.setSuoShuKeHu(wmUtil.getCusCode());
					}
					MdGoodsEntity mdGoods1 = systemService.findUniqueByProperty(
							MdGoodsEntity.class, "shpBianMa", mdGoods.getShpBianMa());
					if(mdGoods1 ==null ){
						try {
							if(StringUtil.isEmpty(mdGoods.getShpBianMa())){//?????????????????????????????????
								if (StringUtils.isEmpty(mdGoods.getCategoryCode())) {
									j.setSuccess(false);
									j.setMsg("?????????????????????");
									return j;
								}
								Map<String, Object> countMap = systemService.findOneForJdbc("select right(shp_bian_ma,7) shp_bian_ma  from md_goods where category_code =? and suo_shu_ke_hu  = ? and shp_bian_ma like ? ORDER BY shp_bian_ma desc LIMIT 1",mdGoods.getCategoryCode(),mdGoods.getSuoShuKeHu(),mdGoods.getSuoShuKeHu()+mdGoods.getCategoryCode()+"%");
								if (countMap == null) {
									mdGoods.setShpBianMa(mdGoods.getSuoShuKeHu()+mdGoods.getCategoryCode()+String.format("%07d", 1));
								}else {
									Object goodsCode = countMap.get("shp_bian_ma");
									if (goodsCode != null) {
										mdGoods.setShpBianMa(mdGoods.getSuoShuKeHu()+mdGoods.getCategoryCode()+String.format("%07d",Integer.parseInt(((String) goodsCode))+1));
									}else {
										mdGoods.setShpBianMa(mdGoods.getSuoShuKeHu()+mdGoods.getCategoryCode()+String.format("%07d", 1));
									}
								}
							}


							if(StringUtil.isEmpty(mdGoods.getZhlKgm())){
								if(!StringUtil.isEmpty(mdGoods.getBzhiQi())){
									int bzhiq = Integer.parseInt(mdGoods.getBzhiQi());
									mdGoods.setZhlKgm(Integer.toString(bzhiq));
								}

							}
							if(StringUtil.isEmpty(mdGoods.getChlKongZhi()) ){
								mdGoods.setChlKongZhi("N");

							}
							if("N".equals(mdGoods.getChlKongZhi())){
								mdGoods.setChlShl("1");
								mdGoods.setJshDanWei(mdGoods.getShlDanWei());

							}

						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						mdGoodsService.save(mdGoods);
					}else{
						try {
							if(StringUtil.isEmpty(mdGoods.getZhlKgm())){
								if(!StringUtil.isEmpty(mdGoods.getBzhiQi())){
									int bzhiq = Integer.parseInt(mdGoods.getBzhiQi());
									mdGoods.setZhlKgm(Integer.toString(bzhiq));
								}

							}
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}
				  		MyBeanUtils.copyBeanNotNull2Bean(mdGoods, mdGoods1);
				  		mdGoodsService.updateEntitie(mdGoods1);
					}
				}
				j.setMsg("?????????????????????");
			} catch (Exception e) {
				j.setMsg("?????????????????????");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			} finally {
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
									@RequestParam(value="all", required=false)String all,

									@RequestParam(value="searchstr", required=false)String searchstr,
									@RequestParam(value="searchstr2", required=false)String searchstr2,
									@RequestParam(value="searchstrin1", required=false)String searchstrin1,
									@RequestParam(value="searchstrin2", required=false)String searchstrin2,
									@RequestParam(value="searchstrin3", required=false)String searchstrin3) {


		ResultDO D0 = new  ResultDO();

		String hql = " from MdGoodsEntity where 1 = 1    ";
		D0.setOK(true);
		if(!StringUtil.isEmpty(searchstr)) {
			hql=hql+"  and  (shpBianMa = '" + searchstr + "'";
            hql=hql+"  or   shpTiaoMa = '" + searchstr + "')";

        }
//		if(!StringUtil.isEmpty(searchstr2)) {
//			hql=hql+"  and (shpTiaoMa = '" + searchstr2 + "'";
//            hql=hql+"  or shpBianMa = '" + searchstr2 + "')";
//
//        }

		List<MdGoodsEntity> listMdGoodss = mdGoodsService.findHql(hql);
		D0.setOK(true);
		List<MdGoodsEntity> result = new ArrayList<MdGoodsEntity>();
		int i = 0;
		for (MdGoodsEntity t :listMdGoodss){
					i++;
					if(!"all".equals(all)){
						if(i>100){
							break;
						}
					}
//					t.setShpYanSe(searchstrin1);
//					t.setPpTuPian(searchstrin2);
//					t.setJjZhongBi(searchstrin3);
			result.add(t);
		}

		D0.setObj(result);
		return new ResponseEntity(D0, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		MdGoodsEntity task = mdGoodsService.get(MdGoodsEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestParam String mdGoodsstr,
			UriComponentsBuilder uriBuilder) {
		// ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
//		Set<ConstraintViolation<MdGoodsEntity>> failures = validator
//				.validate(mdGoods);
//		if (!failures.isEmpty()) {
//			return new ResponseEntity(
//					BeanValidators.extractPropertyAndMessage(failures),
//					HttpStatus.BAD_REQUEST);
//		}
		ResultDO D0 = new  ResultDO();
		MdGoodsEntity mdGoods  = (MdGoodsEntity)JSONHelper.json2Object(mdGoodsstr,MdGoodsEntity.class);

		// ??????
		try {
			mdGoodsService.save(mdGoods);
			D0.setOK(true);
		} catch (Exception e) {
			e.printStackTrace();
			D0.setOK(false);
		}
		// ??????Restful???????????????????????????????????????url, ?????????????????????id?????????.
		String id = mdGoods.getId();
		URI uri = uriBuilder.path("/rest/mdGoodsController/" + id).build()
				.toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);

		return new ResponseEntity(D0, HttpStatus.OK);
	}


	@RequestMapping(value = "/apicreategoods")
	@ResponseBody
	public ResponseEntity<?> creategoods(@RequestBody MdGoodsEntity mdGoodsEntity ) {
		//??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		ResultDO D0 = new ResultDO();
		try {
			MdGoodsEntity t = systemService.findUniqueByProperty(MdGoodsEntity.class,"shpBianMa",mdGoodsEntity.getShpBianMa());
			if(t!=null){
				MyBeanUtils.copyBeanNotNull2Bean(mdGoodsEntity,t);
				mdGoodsService.saveOrUpdate(t);
			}else{
				mdGoodsService.save(mdGoodsEntity);

			}


			try{
				MdCusEntity mdcus1 = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", mdGoodsEntity.getSuoShuKeHu());
				if(mdcus1==null){
					mdcus1 = new  MdCusEntity();
					mdcus1.setKeHuBianMa(mdGoodsEntity.getSuoShuKeHu());
					mdcus1.setZhongWenQch(mdGoodsEntity.getCusName());
					systemService.save(mdcus1);
 				}

			}catch(Exception e){

			}

			D0.setOK(true);
		} catch (Exception e) {
			e.printStackTrace();
			D0.setOK(false);
		}
		return new ResponseEntity(D0, HttpStatus.OK);
	}

	@RequestMapping(value = "/addgoods", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addgoods(@RequestParam String mdGoodsstr,
									UriComponentsBuilder uriBuilder) {		// ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		ResultDO D0 = new  ResultDO();
		MdGoodsEntity mdGoods  = (MdGoodsEntity)JSONHelper.json2Object(mdGoodsstr,MdGoodsEntity.class);
		// ??????
		try {
			MdGoodsEntity t = systemService.findUniqueByProperty(MdGoodsEntity.class,"shpBianMa",mdGoods.getShpBianMa());
			if(t!=null){
				MyBeanUtils.copyBeanNotNull2Bean(mdGoods,t);
				mdGoodsService.saveOrUpdate(t);
			}else{
				mdGoodsService.save(mdGoods);

			}

			D0.setOK(true);
		} catch (Exception e) {
			e.printStackTrace();
			D0.setOK(false);
		}

		// ???Restful???????????????204?????????, ?????????. ???????????????200?????????.
		return new ResponseEntity(D0, HttpStatus.OK);
	}

	@RequestMapping(value = "/change", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> update(@RequestParam String mdGoodsstr,
									UriComponentsBuilder uriBuilder) {		// ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		ResultDO D0 = new  ResultDO();
		MdGoodsEntity mdGoods  = (MdGoodsEntity)JSONHelper.json2Object(mdGoodsstr,MdGoodsEntity.class);
		// ??????
		try {
			MdGoodsEntity t = systemService.get(MdGoodsEntity.class,mdGoods.getId());
			MyBeanUtils.copyBeanNotNull2Bean(mdGoods,t);
			mdGoodsService.saveOrUpdate(t);
			D0.setOK(true);
		} catch (Exception e) {
			e.printStackTrace();
			D0.setOK(false);
		}

		// ???Restful???????????????204?????????, ?????????. ???????????????200?????????.
		return new ResponseEntity(D0, HttpStatus.OK);
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> xiadan(@RequestParam String mdGoodsstr,
									UriComponentsBuilder uriBuilder) {		// ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		ResultDO D0 = new  ResultDO();
		WmOmNoticeImpnewPage pageheader  = (WmOmNoticeImpnewPage)JSONHelper.json2Object(mdGoodsstr,WmOmNoticeImpnewPage.class);

		WmOmNoticeHEntity wmOmNoticeH = null;
		MdGoodsEntity t = systemService.get(MdGoodsEntity.class,pageheader.getId());

		List<WmOmNoticeHEntity>  wmomh = systemService.findByProperty(WmOmNoticeHEntity.class, "imCusCode", pageheader.getImCusCode());
		if(wmomh!=null&&wmomh.size()>0){
			wmOmNoticeH = wmomh.get(0);
		}else{
			wmOmNoticeH = new WmOmNoticeHEntity();
			wmOmNoticeH.setCreateBy(pageheader.getCreateBy());
			wmOmNoticeH.setOrderTypeCode("11");
			wmOmNoticeH.setCusCode(t.getSuoShuKeHu());
			String noticeid = wmUtil.getNextomNoticeId(wmOmNoticeH.getOrderTypeCode());
			wmOmNoticeH.setOmNoticeId(noticeid);
			wmOmNoticeH.setImCusCode(pageheader.getImCusCode());
			wmOmNoticeH.setReCarno(pageheader.getReCarno());
			wmOmNoticeH.setOmBeizhu(pageheader.getImBeizhu());

			systemService.save(wmOmNoticeH);
		}
		WmOmNoticeIEntity wmi = new WmOmNoticeIEntity();
		wmi.setGoodsId(t.getShpBianMa());
		wmi.setOmNoticeId(wmOmNoticeH.getOmNoticeId());
		MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
		MvGoodsEntity.class, "goodsCode", wmi.getGoodsId());
		if (mvgoods != null) {
			wmi.setGoodsName(t.getShpMingCheng());
			wmi.setGoodsUnit(t.getShlDanWei());
		}
		wmi.setCusCode(wmOmNoticeH.getCusCode());
		try{
			wmi.setGoodsQua(pageheader.getGoodsQua());//
		}catch (Exception e){

		}
		systemService.save(wmi);
			D0.setErrorMsg("??????????????????");
			D0.setOK(true);
		// ???Restful???????????????204?????????, ?????????. ???????????????200?????????.
		return new ResponseEntity(D0, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		mdGoodsService.deleteEntityById(MdGoodsEntity.class, id);
	}
}
