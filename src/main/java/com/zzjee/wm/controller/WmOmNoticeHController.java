package com.zzjee.wm.controller;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.zzjee.ba.entity.BaStoreEntity;
import com.zzjee.md.entity.MdGoodsEntity;
import com.zzjee.tms.entity.TmsMdCheliangEntity;
import com.zzjee.tms.entity.TmsYwDingdanEntity;
import com.zzjee.wave.entity.WaveToDownEntity;
import com.zzjee.wm.entity.*;
import com.zzjee.wm.page.*;
import com.zzjee.wmutil.dsc.dscUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.exception.BusinessException;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BarcodeUtil;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.QRcodeUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.sms.util.Constants;
import org.jeecgframework.web.system.sms.util.TuiSongMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import com.zzjee.md.entity.MdCusEntity;
import com.zzjee.md.entity.MdCusOtherEntity;
import com.zzjee.md.entity.MvGoodsEntity;
import com.zzjee.wm.service.WmOmNoticeHServiceI;
import com.zzjee.wmutil.resResult;
import com.zzjee.wmutil.sdresult;
import com.zzjee.wmutil.wmIntUtil;
import com.zzjee.wmutil.wmUtil;
import com.zzjee.wmutil.yyUtil;

import net.sf.json.JSONArray;

import static com.xiaoleilu.hutool.date.DateTime.now;

/**
 * @Title: Controller
 * @Description: ????????????
 * @author erzhongxmu
 * @date 2017-08-15 23:18:59
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/wmOmNoticeHController")
public class WmOmNoticeHController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WmOmNoticeHController.class);

	@Autowired
	private WmOmNoticeHServiceI wmOmNoticeHService;
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
		return new ModelAndView("com/zzjee/wm/wmOmNoticeHList");
	}
	/**
	 * ?????????????????? ????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "listitem")
	public ModelAndView listitem(HttpServletRequest request) {
		return new ModelAndView("com/zzjee/wm/wmOmNoticeitemList");
	}



	@RequestMapping(params = "datagriditem")
	public void datagriditem(WmOmNoticeIEntity wmOmNoticeitem,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WmOmNoticeIEntity.class, dataGrid);
		//?????????????????????
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wmOmNoticeitem);

		try {
			// ???????????????????????????

		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
			cq.eq("cusCode", wmUtil.getCusCode());

		}

		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("createDate", "desc");
		cq.setOrder(map1);
		cq.add();
		this.wmOmNoticeHService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	@RequestMapping(params = "saveOmnotice")
    @ResponseBody
	public AjaxJson saveOmnotice(wmomnoticeipage page){
		String message = null;
		List<WmOmNoticeIEntity> demos=page.getWmomnoticeirows();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(demos)){
			for(WmOmNoticeIEntity jeecgDemo:demos){
				if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
					WmOmNoticeIEntity t =systemService.get(WmOmNoticeIEntity.class, jeecgDemo.getId());
					try {
						message = "????????????";
						t.setBinId(jeecgDemo.getBinId());
						t.setPlanSta(jeecgDemo.getPlanSta());
						t.setGoodsProData(jeecgDemo.getGoodsProData());
						t.setBaseGoodscount(jeecgDemo.getBaseGoodscount());
						t.setGoodsQua(jeecgDemo.getGoodsQua());
						systemService.updateEntitie(t);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return j;
	}


	@RequestMapping(params = "listqt")
	public ModelAndView listqt(HttpServletRequest request) {
		return new ModelAndView("com/zzjee/wm/wmOmqtNoticeHList");
	}

	@RequestMapping(params = "cuslist")
	public ModelAndView cuslist(HttpServletRequest request) {
		return new ModelAndView("com/zzjee/wm/cuswmOmNoticeHList");
	}

	@RequestMapping(params = "batchconflist")
	public ModelAndView batchconf_rowedtior(HttpServletRequest request) {
		return new ModelAndView("com/zzjee/wm/batchconf_rowedtior");
	}


	@RequestMapping(params = "doPrintpage")
	public ModelAndView doPrint(String id,HttpServletRequest request) {
		WmOmNoticeHEntity wmOmNoticeHEntity = wmOmNoticeHService.getEntity(WmOmNoticeHEntity.class, id);
		wmOmNoticeHEntity.setPrintStatus("?????????");
		systemService.updateEntitie(wmOmNoticeHEntity);
		request.setAttribute("wmOmNoticeHPage", wmOmNoticeHEntity);
		request.setAttribute("kprq",DateUtils.date2Str(wmOmNoticeHEntity.getCreateDate(),DateUtils.date_sdf));
		request.setAttribute("comname", ResourceUtil.getConfigByName("comname"));
		request.setAttribute("showlisturl", ResourceUtil.getConfigByName("show.noticeurl")+id);

		if(StringUtil.isNotEmpty(wmOmNoticeHEntity.getImCusCode())){
			request.setAttribute("noticeid", wmOmNoticeHEntity.getImCusCode());
		}else{
			request.setAttribute("noticeid", wmOmNoticeHEntity.getOmNoticeId());
		}

		BaStoreEntity baStoreEntity = systemService.findUniqueByProperty(BaStoreEntity.class,"storeCode",wmOmNoticeHEntity.getStoreCode());
		if (baStoreEntity != null && StringUtils.isNotEmpty(baStoreEntity.getStoreName())) {
			request.setAttribute("storeName", baStoreEntity.getStoreName());
		}else {
			request.setAttribute("storeName", "");
		}

		try{
			MdCusEntity mdcus = systemService.findUniqueByProperty(MdCusEntity.class,"keHuBianMa",wmOmNoticeHEntity.getCusCode());
			MdCusOtherEntity mdcusother = systemService.findUniqueByProperty(MdCusOtherEntity.class,"keHuBianMa",wmOmNoticeHEntity.getOcusCode());
			request.setAttribute("cusname",wmOmNoticeHEntity.getCusCode()+"-"+ mdcus.getZhongWenQch());
			if(mdcusother!=null){
				request.setAttribute("ocusname",wmOmNoticeHEntity.getOcusCode()+"-"+ mdcusother.getZhongWenQch());
			}else{
				request.setAttribute("ocusname",wmOmNoticeHEntity.getOcusCode());
			}

		}catch (Exception e){

		}
		//????????????
		Object id0 = wmOmNoticeHEntity.getOmNoticeId();
		//===================================================================================
		//??????-??????
		String hql0 = "from WmOmQmIEntity where 1 = 1 AND omNoticeId = ? order by binId";
		Double tomsum = 0.00;
		Double  noticesum = 0.00;
		Double  tijisum = 0.00;
		Double  zhlsum = 0.00;
		try{
			List<WmOmQmIEntity> wmOmQmIEntityList = systemService.findHql(hql0, id0);//???????????????
            //List<WmOmQmIEntity> wmOmQmIEntityListnew = new ArrayList<>();
            DecimalFormat dfsum=new DecimalFormat(".##");
            try{
				for(WmOmQmIEntity tom:wmOmQmIEntityList){
					tomsum = tomsum + Double.parseDouble(tom.getBaseGoodscount());
					try{
						tijisum = tijisum + Double.parseDouble(tom.getTinTj());

					}catch ( Exception e){
					}
					try{
						zhlsum = zhlsum + Double.parseDouble(tom.getTinZhl());
					}catch ( Exception e){
					}
                    try{
                        tom.setTinZhl(dfsum.format(Double.parseDouble(tom.getTinZhl())));
                    }catch ( Exception e){
                    }
                    try{
                        tom.setTinTj(dfsum.format(Double.parseDouble(tom.getTinTj())));
                    }catch ( Exception e){
                    }
                    tom.setBaseGoodscount(StringUtil.getdouble(tom.getBaseGoodscount()));
					try{
						MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
								MvGoodsEntity.class, "goodsCode", tom.getGoodsId());
						if (mvgoods != null) {
							tom.setShpGuiGe(mvgoods.getShpGuiGe());
						}
						int shpguige = 0;
						try{
							shpguige = Integer.parseInt(mvgoods.getShpGuiGe());
						}catch (Exception e){
						}
						if(shpguige!=0){
							Double xianhshu = Math.floor(Double.parseDouble(tom.getBaseGoodscount())/shpguige);
							Double jianshu = Double.parseDouble(tom.getBaseGoodscount())%shpguige;
							long xiangshuint = Math.round(xianhshu);
							if(xianhshu > 0){
								tom.setPickNotice(xiangshuint+"???"+jianshu+tom.getBaseUnit());
							}else{
								tom.setPickNotice(tom.getBaseGoodscount()+tom.getBaseUnit());
							}
						}
					}catch (Exception e){

					}
                    //wmOmQmIEntityListnew.add(tom);
				}
			}catch ( Exception e){
			}
			String hqlnotice = "from WmOmNoticeIEntity where 1 = 1 AND oM_NOTICE_ID = ? ";
			List<WmOmNoticeIEntity> wmOmNoticeIEntityList = systemService.findHql(hqlnotice,id0);
			for(WmOmNoticeIEntity tnotice:wmOmNoticeIEntityList){
				noticesum = noticesum + Double.parseDouble(tnotice.getBaseGoodscount());
			}
			if(Double.doubleToLongBits(noticesum) != Double.doubleToLongBits(tomsum)){
				request.setAttribute("jianhuoremark", "?????????"+dfsum.format(noticesum)+" ?????????"+dfsum.format(tomsum));
			}else{
				request.setAttribute("jianhuoremark", "??????????????????"+dfsum.format(noticesum));
			}
			String tijiunit="????????????";
			String zhongliangunit="??????";
			try{
				tijiunit= ResourceUtil.getConfigByName("tijiunit");
				 zhongliangunit=ResourceUtil.getConfigByName("zhongliangunit");
			}catch (Exception e){
			}
			request.setAttribute("tijisum", dfsum.format(tijisum)+tijiunit);
			request.setAttribute("zhlsum", dfsum.format(zhlsum)+zhongliangunit);
			request.setAttribute("wmOmQmIList", wmOmQmIEntityList);
		}catch (Exception e){
		}
		return new ModelAndView("com/zzjee/wm/print/jianhuorenwu-print");
	}

	@RequestMapping(params = "doPrintOutStorage")
	public ModelAndView doPrintOutStorage(String id,HttpServletRequest request) {
		WmOmNoticeHEntity wmOmNoticeHEntity = wmOmNoticeHService.getEntity(WmOmNoticeHEntity.class, id);
		List<Map<String,Object>> list = new ArrayList<>();
		list = wmOmNoticeHService.findForJdbc("select id,goods_id ,goods_name ,base_goodscount,goods_unit from wm_to_down_goods where order_id = ? ",wmOmNoticeHEntity.getOmNoticeId());
		if (list.size() == 0 ) {
			list = systemService.findForJdbc("select id,goods_id ,goods_name ,base_goodscount,goods_unit from wm_om_qm_i where om_notice_id = ? ",wmOmNoticeHEntity.getOmNoticeId());
		}

		if (list != null && list.size() > 0) {
			List<WmToDownGoodsEntity> resultList = new ArrayList<>();
			for (Map<String, Object> map : list) {
				WmToDownGoodsEntity wmToDownGoodsEntity = new WmToDownGoodsEntity();
				wmToDownGoodsEntity.setId(map.get("id")== null ?null :map.get("id").toString() );
				wmToDownGoodsEntity.setGoodsId(map.get("goods_id") == null ?null :map.get("goods_id").toString() );
				wmToDownGoodsEntity.setGoodsName(map.get("goods_name")== null ?null :map.get("goods_name").toString() );
				wmToDownGoodsEntity.setBaseGoodscount(map.get("base_goodscount")== null ?null :map.get("base_goodscount").toString() );
				wmToDownGoodsEntity.setGoodsUnit(map.get("goods_unit")== null ?null :map.get("goods_unit").toString() );
				resultList.add(wmToDownGoodsEntity);
			}
			request.setAttribute("listitem",resultList);
		}
		request.setAttribute("id",id);

		return new ModelAndView("com/zzjee/wm/print/selectGoodsOutStorage");
	}


	@RequestMapping(params = "doPrintzhuisu")
	public ModelAndView doPrintpzhuisu(String id,HttpServletRequest request) {
		WmOmNoticeHEntity wmOmNoticeHEntity = wmOmNoticeHService.getEntity(WmOmNoticeHEntity.class, id);
		wmOmNoticeHEntity.setPrintStatus("?????????");
		systemService.updateEntitie(wmOmNoticeHEntity);
		request.setAttribute("wmOmNoticeHPage", wmOmNoticeHEntity);
		request.setAttribute("kprq",DateUtils.date2Str(wmOmNoticeHEntity.getCreateDate(),DateUtils.date_sdf));
		request.setAttribute("comname", ResourceUtil.getConfigByName("comname"));
		request.setAttribute("showlisturl", ResourceUtil.getConfigByName("show.noticeurl")+id);

		if(StringUtil.isNotEmpty(wmOmNoticeHEntity.getImCusCode())){
			request.setAttribute("noticeid", wmOmNoticeHEntity.getImCusCode());
		}else{
			request.setAttribute("noticeid", wmOmNoticeHEntity.getOmNoticeId());
		}

		BaStoreEntity baStoreEntity = systemService.findUniqueByProperty(BaStoreEntity.class,"storeCode",wmOmNoticeHEntity.getStoreCode());
		if (baStoreEntity != null && StringUtils.isNotEmpty(baStoreEntity.getStoreName())) {
			request.setAttribute("storeName", baStoreEntity.getStoreName());
		}else {
			request.setAttribute("storeName", "");
		}

		try{
			MdCusEntity mdcus = systemService.findUniqueByProperty(MdCusEntity.class,"keHuBianMa",wmOmNoticeHEntity.getCusCode());
			MdCusOtherEntity mdcusother = systemService.findUniqueByProperty(MdCusOtherEntity.class,"keHuBianMa",wmOmNoticeHEntity.getOcusCode());
			request.setAttribute("cusname",wmOmNoticeHEntity.getCusCode()+"-"+ mdcus.getZhongWenQch());
			if(mdcusother!=null){
				request.setAttribute("ocusname",wmOmNoticeHEntity.getOcusCode()+"-"+ mdcusother.getZhongWenQch());
			}else{
				request.setAttribute("ocusname",wmOmNoticeHEntity.getOcusCode());
			}

		}catch (Exception e){

		}
		//????????????
		Object id0 = wmOmNoticeHEntity.getOmNoticeId();
		//===================================================================================
		//??????-??????
		String hql0 = "from WmOmQmIEntity where 1 = 1 AND omNoticeId = ? order by binId";
		Double tomsum = 0.00;
		Double  noticesum = 0.00;
		Double  tijisum = 0.00;
		Double  zhlsum = 0.00;
		try{
			List<WmOmQmIEntity> wmOmQmIEntityList = systemService.findHql(hql0, id0);//???????????????
			//List<WmOmQmIEntity> wmOmQmIEntityListnew = new ArrayList<>();
			DecimalFormat dfsum=new DecimalFormat(".##");
			String filepath = ResourceUtil.getConfigByName("webUploadpath");
            String goodsurl = ResourceUtil.getConfigByName("show.goodsurl");

			try{
				for(WmOmQmIEntity tom:wmOmQmIEntityList){
                    try{
                        QRcodeUtil.encode(goodsurl+tom.getId(),filepath+ File.separator + tom.getId()+".png");
  						System.out.println(goodsurl+tom.getId());
                    }catch (Exception e){

                    }
					tomsum = tomsum + Double.parseDouble(tom.getBaseGoodscount());
					try{
						tijisum = tijisum + Double.parseDouble(tom.getTinTj());

					}catch ( Exception e){
					}
					try{
						zhlsum = zhlsum + Double.parseDouble(tom.getTinZhl());
					}catch ( Exception e){
					}
					try{
						tom.setTinZhl(dfsum.format(Double.parseDouble(tom.getTinZhl())));
					}catch ( Exception e){
					}
					try{
						tom.setTinTj(dfsum.format(Double.parseDouble(tom.getTinTj())));
					}catch ( Exception e){
					}
					tom.setBaseGoodscount(StringUtil.getdouble(tom.getBaseGoodscount()));
					try{
						MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
								MvGoodsEntity.class, "goodsCode", tom.getGoodsId());
						if (mvgoods != null) {
							tom.setShpGuiGe(mvgoods.getShpGuiGe());
						}
						int shpguige = 0;
						try{
							shpguige = Integer.parseInt(mvgoods.getShpGuiGe());
						}catch (Exception e){
						}
						if(shpguige!=0){
							Double xianhshu = Math.floor(Double.parseDouble(tom.getBaseGoodscount())/shpguige);
							Double jianshu = Double.parseDouble(tom.getBaseGoodscount())%shpguige;
							long xiangshuint = Math.round(xianhshu);
							if(xianhshu > 0){
								tom.setPickNotice(xiangshuint+"???"+jianshu+tom.getBaseUnit());
							}else{
								tom.setPickNotice(tom.getBaseGoodscount()+tom.getBaseUnit());
							}
						}
					}catch (Exception e){
					}
					//wmOmQmIEntityListnew.add(tom);
				}
			}catch ( Exception e){
			}
			String hqlnotice = "from WmOmNoticeIEntity where 1 = 1 AND oM_NOTICE_ID = ? ";
			List<WmOmNoticeIEntity> wmOmNoticeIEntityList = systemService.findHql(hqlnotice,id0);
			for(WmOmNoticeIEntity tnotice:wmOmNoticeIEntityList){
				noticesum = noticesum + Double.parseDouble(tnotice.getBaseGoodscount());
			}
			if(Double.doubleToLongBits(noticesum) != Double.doubleToLongBits(tomsum)){
				request.setAttribute("jianhuoremark", "?????????"+dfsum.format(noticesum)+" ?????????"+dfsum.format(tomsum));
			}else{
				request.setAttribute("jianhuoremark", "??????????????????"+dfsum.format(noticesum));
			}
			String tijiunit="????????????";
			String zhongliangunit="??????";
			try{
				tijiunit= ResourceUtil.getConfigByName("tijiunit");
				zhongliangunit=ResourceUtil.getConfigByName("zhongliangunit");
			}catch (Exception e){
			}
			request.setAttribute("tijisum", dfsum.format(tijisum)+tijiunit);
			request.setAttribute("zhlsum", dfsum.format(zhlsum)+zhongliangunit);
			request.setAttribute("wmOmQmIList", wmOmQmIEntityList);
		}catch (Exception e){
		}
		return new ModelAndView("com/zzjee/wm/print/jianhuorenwuzhuisu");
	}
	@RequestMapping(params = "showlist")
	public ModelAndView showlist(String id,HttpServletRequest request) {
		WmOmNoticeHEntity wmOmNoticeHEntity = wmOmNoticeHService.getEntity(WmOmNoticeHEntity.class, id);
        request.setAttribute("wmOmNoticeHPage", wmOmNoticeHEntity);
		request.setAttribute("comname", ResourceUtil.getConfigByName("comname"));


		try{
			MdCusEntity mdcus = systemService.findUniqueByProperty(MdCusEntity.class,"keHuBianMa",wmOmNoticeHEntity.getCusCode());
			MdCusOtherEntity mdcusother = systemService.findUniqueByProperty(MdCusOtherEntity.class,"keHuBianMa",wmOmNoticeHEntity.getOcusCode());
			request.setAttribute("cusname",wmOmNoticeHEntity.getCusCode()+"-"+ mdcus.getZhongWenQch());
			if(mdcusother!=null){
				mdcusother.setZuZhiJiGou(mdcusother.getZuZhiJiGou().replace(",",""));
				request.setAttribute("mdcusother",mdcusother);

				request.setAttribute("ocusname",wmOmNoticeHEntity.getOcusCode()+"-"+ mdcusother.getZhongWenQch());
			}else{
				request.setAttribute("ocusname",wmOmNoticeHEntity.getOcusCode());
			}

		}catch (Exception e){

		}
		//????????????
		Object id0 = wmOmNoticeHEntity.getOmNoticeId();
		//===================================================================================
		//??????-??????
		String hql0 = "from WmOmQmIEntity where 1 = 1 AND omNoticeId = ? order by binId";
		Double tomsum = 0.00;
		Double  noticesum = 0.00;
		Double  tijisum = 0.00;
		Double  zhlsum = 0.00;
		try{
			List<WmOmQmIEntity> wmOmQmIEntityList = systemService.findHql(hql0, id0);//???????????????
			//List<WmOmQmIEntity> wmOmQmIEntityListnew = new ArrayList<>();
			DecimalFormat dfsum=new DecimalFormat(".##");
			try{
				for(WmOmQmIEntity tom:wmOmQmIEntityList){
					tomsum = tomsum + Double.parseDouble(tom.getBaseGoodscount());
					try{
						tijisum = tijisum + Double.parseDouble(tom.getTinTj());

					}catch ( Exception e){
					}
					try{
						zhlsum = zhlsum + Double.parseDouble(tom.getTinZhl());
					}catch ( Exception e){
					}
					try{
						tom.setTinZhl(dfsum.format(Double.parseDouble(tom.getTinZhl())));
					}catch ( Exception e){
					}
					try{
						tom.setTinTj(dfsum.format(Double.parseDouble(tom.getTinTj())));
					}catch ( Exception e){
					}
					tom.setBaseGoodscount(StringUtil.getdouble(tom.getBaseGoodscount()));
					try{
						MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
								MvGoodsEntity.class, "goodsCode", tom.getGoodsId());
						if (mvgoods != null) {
							tom.setShpGuiGe(mvgoods.getShpGuiGe());
						}
						int shpguige = 0;
						try{
							shpguige = Integer.parseInt(mvgoods.getShpGuiGe());
						}catch (Exception e){
						}
						if(shpguige!=0){
							Double xianhshu = Math.floor(Double.parseDouble(tom.getBaseGoodscount())/shpguige);
							Double jianshu = Double.parseDouble(tom.getBaseGoodscount())%shpguige;
							long xiangshuint = Math.round(xianhshu);
							if(xianhshu > 0){
								tom.setPickNotice(xiangshuint+"???"+jianshu+tom.getBaseUnit());
							}else{
								tom.setPickNotice(tom.getBaseGoodscount()+tom.getBaseUnit());
							}
						}
					}catch (Exception e){
					}
					//wmOmQmIEntityListnew.add(tom);
				}
			}catch ( Exception e){
			}
			String hqlnotice = "from WmOmNoticeIEntity where 1 = 1 AND oM_NOTICE_ID = ? ";
			List<WmOmNoticeIEntity> wmOmNoticeIEntityList = systemService.findHql(hqlnotice,id0);
			for(WmOmNoticeIEntity tnotice:wmOmNoticeIEntityList){
				noticesum = noticesum + Double.parseDouble(tnotice.getBaseGoodscount());
			}
			if(Double.doubleToLongBits(noticesum) != Double.doubleToLongBits(tomsum)){
				request.setAttribute("jianhuoremark", "?????????"+dfsum.format(noticesum)+" ?????????"+dfsum.format(tomsum));
			}else{
				request.setAttribute("jianhuoremark", "??????????????????"+dfsum.format(noticesum));
			}
			String tijiunit="????????????";
			String zhongliangunit="??????";
			try{
				tijiunit= ResourceUtil.getConfigByName("tijiunit");
				zhongliangunit=ResourceUtil.getConfigByName("zhongliangunit");
			}catch (Exception e){
			}
			request.setAttribute("tijisum", dfsum.format(tijisum)+tijiunit);
			request.setAttribute("zhlsum", dfsum.format(zhlsum)+zhongliangunit);
			request.setAttribute("wmOmQmIList", wmOmQmIEntityList);
		}catch (Exception e){
		}
		return new ModelAndView("com/zzjee/wm/print/zhuisu-print");
	}


	@RequestMapping(params = "showgoods")
	public ModelAndView showgoods(String id,HttpServletRequest request) {
		WmOmQmIEntity wmOmQmIEntity = wmOmNoticeHService.getEntity(WmOmQmIEntity.class, id);
		request.setAttribute("wmOmQmIEntity", wmOmQmIEntity);
		request.setAttribute("comname", ResourceUtil.getConfigByName("comname"));

		System.out.println(id);

		try{
			MdCusEntity mdcus = systemService.findUniqueByProperty(MdCusEntity.class,"keHuBianMa",wmOmQmIEntity.getCusCode());
			request.setAttribute("cusname",wmOmQmIEntity.getCusCode()+"-"+ mdcus.getZhongWenQch());
			mdcus.setZuZhiJiGou(mdcus.getZuZhiJiGou().replace(",",""));

			request.setAttribute("MdCusEntity",mdcus);


		}catch (Exception e){

		}
		//????????????
		Object goodsid = wmOmQmIEntity.getGoodsId();
		Object goodspro = wmOmQmIEntity.getProData();

		//===================================================================================
		//??????-??????
		String hql0 = "from WmInQmIEntity where proData = ? AND goodsId = ? order by createDate  ";
		String hql1 = "from WmImNoticeHEntity where noticeId = ?  order by createDate  ";
		try{
			List<WmInQmIEntity> WmInQmIEntityList = systemService.findHql(hql0, goodspro,goodsid);//???????????????
			if(WmInQmIEntityList!=null&&WmInQmIEntityList.size()>0){
				String imnoticeid = WmInQmIEntityList.get(0).getImNoticeId();
				List<WmImNoticeHEntity> WmImNoticeHEntityList = systemService.findHql(hql1, imnoticeid);//???????????????
				if(WmInQmIEntityList!=null&&WmInQmIEntityList.size()>0) {
					WmImNoticeHEntity wmImNoticeHEntity = WmImNoticeHEntityList.get(0);
//					wmImNoticeHEntity.setFuJian(wmImNoticeHEntity.getFuJian().replace(",",""));
					try{
						String arr[] = wmImNoticeHEntity.getFuJian().split(",");
						for(int si=0;si<arr.length;si++){
							request.setAttribute("fujian"+si,arr[si]);
						}
					}catch (Exception e){

					}

					request.setAttribute("wmimnoticeh",wmImNoticeHEntity);
				}
				}
			}catch ( Exception e){
			}

		return new ModelAndView("com/zzjee/wm/print/zhuisuitem-print");
	}

//
	/**
	 * easyui AJAX????????????
	 *
	 * @param request
	 * @param response
	 * @param dataGrid
	 */


	@RequestMapping(params = "datagridbatchconf")
	public void datagridbatchconf(WmOmNoticeHEntity wmOmNoticeH,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WmOmNoticeHEntity.class, dataGrid);
		//?????????????????????
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wmOmNoticeH);

		try {
			// ???????????????????????????
			String query_imData_begin = request.getParameter("delvData_begin1");
			String query_imData_end = request.getParameter("delvData_end2");

			if (StringUtil.isNotEmpty(query_imData_begin)) {
				cq.ge("delvData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(query_imData_begin));
			}
			if (StringUtil.isNotEmpty(query_imData_end)) {
				cq.le("delvData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(query_imData_end));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
		if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
			cq.eq("cusCode", wmUtil.getCusCode());
		}
		cq.eq("omSta", Constants.wm_sta6);//"????????????"
		cq.add();
		this.wmOmNoticeHService.getDataGridReturn(cq, true);
		List<WmOmNoticeHEntity> resultnew = new ArrayList<WmOmNoticeHEntity>();
		List<WmOmNoticeHEntity> resultold = dataGrid.getResults();
		for (WmOmNoticeHEntity WmOmNoticeH : resultold) {
			WmOmNoticeH.setDelvData(null);
			resultnew.add(WmOmNoticeH);
		}
		dataGrid.setResults(resultnew);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "saveRows")
	@ResponseBody
	public AjaxJson saveRows(confrowpage page){
		String message = null;
		List<WmOmNoticeHEntity> demos=page.getDownrows();
		AjaxJson j = new AjaxJson();
		if(CollectionUtils.isNotEmpty(demos)){
			for(WmOmNoticeHEntity jeecgDemo:demos){
				if (StringUtil.isNotEmpty(jeecgDemo.getId())) {
//					WmOmNoticeHEntity t =systemService.get(WmOmNoticeHEntity.class, jeecgDemo.getId());
					try {
						message = "????????????";
						WmNoticeConfEntity confe = new WmNoticeConfEntity();
						confe.setBeizhu(jeecgDemo.getOmBeizhu());
						confe.setHdData(jeecgDemo.getDelvData());
						confe.setCusCode(jeecgDemo.getCusCode());
						confe.setWmNoticeId(jeecgDemo.getOmNoticeId());
						systemService.save(confe);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return j;
	}
	@RequestMapping(params = "datagridqt")
	public void datagridqt(WmOmNoticeHEntity wmOmNoticeH,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WmOmNoticeHEntity.class, dataGrid);
		//?????????????????????
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wmOmNoticeH);

		try {
			// ???????????????????????????
			String query_imData_begin = request.getParameter("delvData_begin1");
			String query_imData_end = request.getParameter("delvData_end2");

			if (StringUtil.isNotEmpty(query_imData_begin)) {
				cq.ge("delvData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(query_imData_begin));
			}
			if (StringUtil.isNotEmpty(query_imData_end)) {
				cq.le("delvData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(query_imData_end));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
			cq.eq("cusCode", wmUtil.getCusCode());

		}
		if (wmOmNoticeH.getOmSta() == null) {
			cq.notEq("omSta", Constants.wm_sta4);
		}
		cq.like("omNoticeId", "QT%");
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("omSta", "desc");
//		cq.setOrder(map);
//		Map<String,Object> map2 = new HashMap<String,Object>();
//		map2.put("omSta", "asc");
//		cq.setOrder(map2);
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("createDate", "desc");
		cq.setOrder(map1);

		cq.add();
		this.wmOmNoticeHService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	@RequestMapping(params = "datagrid")
	public void datagrid(WmOmNoticeHEntity wmOmNoticeH,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(WmOmNoticeHEntity.class, dataGrid);
		//?????????????????????
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wmOmNoticeH);

		try {
			// ???????????????????????????
			String query_imData_begin = request.getParameter("delvData_begin1");
			String query_imData_end = request.getParameter("delvData_end2");

			if (StringUtil.isNotEmpty(query_imData_begin)) {
				cq.ge("delvData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(query_imData_begin));
			}
			if (StringUtil.isNotEmpty(query_imData_end)) {
				cq.le("delvData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
						.parse(query_imData_end));
			}
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

		if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
			cq.eq("cusCode", wmUtil.getCusCode());

		}
		if (wmOmNoticeH.getOmSta() == null) {
			cq.notEq("omSta", Constants.wm_sta4);
		}
		Map<String,Object> map1 = new HashMap<String,Object>();
		map1.put("createDate", "desc");
		cq.setOrder(map1);
		cq.add();
		this.wmOmNoticeHService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}


	@RequestMapping(params = "docheck")
	@ResponseBody
	public AjaxJson docheck(HttpServletRequest request,String goodscode,String goodsqua) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "??????";
		try {

			String goods = null;
			String goodsid = request.getParameter("goodscode");
			if(!StringUtil.isEmpty(goodsid)){
				if(goodsid.endsWith("l")){
					goods = goodsid.substring(0,goodsid.length() - 1);
				}else{
					goods = goodsid;
				}

			}
			String sql = "select sum(base_goodscount) as qua from wv_stock t where  t.goods_id LIKE  '%"
					+ goods + "%'";
			Map<String, Object> binMap	 = systemService.findOneForJdbc(sql);
			if(binMap!=null){
				if(Double.parseDouble(binMap.get("qua").toString())< Double.parseDouble(request.getParameter("goodsqua").toString())){
					j.setSuccess(false);
					message = request.getParameter("goodscode").toString() +"?????????"+binMap.get("qua").toString();
					j.setMsg(message);
					return j;
				}
			}
		} catch (Exception e) {
			j.setSuccess(false);
			message = request.getParameter("goodscode").toString() +"?????????0";
			j.setMsg(message);
			return j;
		}
		j.setMsg(message);
		return j;
	}


	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "doDel")
	@ResponseBody
	public AjaxJson doDel(WmOmNoticeHEntity wmOmNoticeH, HttpServletRequest request) {
		String deltype = ResourceUtil.getConfigByName("del.type");

		AjaxJson j = new AjaxJson();
		wmOmNoticeH = systemService.getEntity(WmOmNoticeHEntity.class, wmOmNoticeH.getId());
		String message = "????????????????????????";
		try{
			wmOmNoticeH.setOmSta("?????????");
			Object id0 = wmOmNoticeH.getOmNoticeId();
			//===================================================================================
			//1.?????????????????????????????????-??????????????????
			String hql0 = "from WmOmNoticeIEntity where 1 = 1 AND oM_NOTICE_ID = ? ";
			List<WmOmNoticeIEntity> wmOmNoticeIOldList = systemService.findHql(hql0,id0);
			for (WmOmNoticeIEntity wmOmNoticeIEntity : wmOmNoticeIOldList) {
				wmOmNoticeIEntity.setOmSta("?????????");
				wmOmNoticeIEntity.setPlanSta("Y");
				if("database".equals(deltype)){
					systemService.delete(wmOmNoticeIEntity);

				}else{
					systemService.saveOrUpdate(wmOmNoticeIEntity);

				}
			}
			if("database".equals(deltype)){
				wmOmNoticeHService.delete(wmOmNoticeH);
			}else{
				wmOmNoticeHService.saveOrUpdate(wmOmNoticeH);

			}
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
	@RequestMapping(params = "doBatchDel")
	@ResponseBody
	public AjaxJson doBatchDel(String ids,HttpServletRequest request){
		AjaxJson j = new AjaxJson();
		String message = "????????????????????????";
		try{
			for(String id:ids.split(",")){
				WmOmNoticeHEntity wmOmNoticeH = systemService.getEntity(WmOmNoticeHEntity.class,
						id
				);
				wmOmNoticeHService.delMain(wmOmNoticeH);
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

	/**
	 * ??????????????????
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "doAdd")
	@ResponseBody
	public AjaxJson doAdd(WmOmNoticeHEntity wmOmNoticeH,WmOmNoticeHPage wmOmNoticeHPage, HttpServletRequest request) {
		List<WmOmNoticeIEntity> wmOmNoticeIList =  wmOmNoticeHPage.getWmOmNoticeIList();
		AjaxJson j = new AjaxJson();
		String message = "????????????";
		if(org.springframework.util.CollectionUtils.isEmpty(wmOmNoticeIList)){
			message = "??????????????????????????????";
			throw new BusinessException(message);
		}
		try{
			String noticeid = wmUtil.getNextomNoticeId(wmOmNoticeH.getOrderTypeCode());
			WmPlatIoEntity wmPlatIo = new WmPlatIoEntity();
			wmPlatIo.setCarno(wmOmNoticeH.getReCarno());
			wmPlatIo.setDocId(noticeid);
			wmPlatIo.setPlanIndata(wmOmNoticeH.getDelvData());
			wmPlatIo.setPlatId(wmOmNoticeH.getOmPlatNo());
			wmPlatIo.setPlatSta(Constants.wm_sta1);
			wmPlatIo.setPlatBeizhu("??????:" + wmOmNoticeH.getReMember() + "??????:"
					+ wmOmNoticeH.getReMobile());
			systemService.save(wmPlatIo);
			wmOmNoticeH.setOmNoticeId(noticeid);
			wmOmNoticeH.setOmSta(Constants.wm_sta1);
			if(wmOmNoticeH.getCusCode()==null){
				if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
					wmOmNoticeH.setCusCode(wmUtil.getCusCode());
				}
			}


			List<WmOmNoticeIEntity> wmomNoticeIListnew = new ArrayList<WmOmNoticeIEntity>();
			for (WmOmNoticeIEntity wmomNoticeIEntity : wmOmNoticeIList) {
				if(!StringUtil.isEmpty(wmomNoticeIEntity.getGoodsId())){
					try {

						String goodsId = wmomNoticeIEntity.getGoodsId().split("-")[0];
						if(goodsId.endsWith("l")){
							goodsId = goodsId.substring(0,goodsId.lastIndexOf("l"));
						}

						MvGoodsEntity mvgoods = systemService.findUniqueByProperty(MvGoodsEntity.class,"goodsId",goodsId);

//					String date[]=wmImNoticeIEntity.getGoodsCode().split("-");
//						wmImNoticeIEntity.setGoodsCode(mvgoods.getGoodsCode());
//						wmImNoticeIEntity.setGoodsName(mvgoods.getShpMingCheng());
//						String date[]=wmomNoticeIEntity.getGoodsId().split("-");
						wmomNoticeIEntity.setGoodsId(mvgoods.getGoodsCode());
						wmomNoticeIEntity.setGoodsName(mvgoods.getShpMingCheng());
					} catch (Exception e) {
						// TODO: handle exception
						logger.error(ExceptionUtil.getExceptionMessage(e));
					}
					wmomNoticeIListnew.add(wmomNoticeIEntity);
				}
			}
			if(StringUtil.isNotEmpty( wmOmNoticeH.getOcusCode())){
//				String datecuso[]= wmOmNoticeH.getOcusCode().split("-");
				MdCusOtherEntity mdcusother = systemService.findUniqueByProperty(MdCusOtherEntity.class, "keHuBianMa",wmOmNoticeH.getOcusCode());
				if (mdcusother != null) {
					wmOmNoticeH.setOcusCode(wmOmNoticeH.getOcusCode());
					wmOmNoticeH.setOcusName(mdcusother.getZhongWenQch());
				}
			}
			wmOmNoticeHService.addMain(wmOmNoticeH, wmomNoticeIListnew);



			Map<String ,Object> map = new HashMap<String ,Object>();
			map.put("id", wmOmNoticeH.getOmNoticeId());
			try {
				TuiSongMsgUtil.sendMessage("????????????", Constants.SMS_SEND_TYPE_3, "CKYYTZ", map, "admin", ResourceUtil.getSessionUserName().getUserName());
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}catch(Exception e){
			e.printStackTrace();
			message = "????????????????????????";
			throw new BusinessException(e.getMessage());
		}
		j.setMsg(message);
		return j;
	}

	@RequestMapping(params = "doGet")
	@ResponseBody
	public AjaxJson dogetfromother(String formDate, HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????";
		if ("U8".equals(ResourceUtil.getConfigByName("interfacetype"))){
			if(StringUtil.isEmpty(formDate)){
				formDate = "2011-01-01";
			}
			yyUtil.getqtck(formDate);//????????????
			yyUtil.getSdvl(formDate);//????????????
			yyUtil.getclck(formDate);//????????????

		}
		if ("DSC".equals(ResourceUtil.getConfigByName("interfacetype"))){

			dscUtil.updateorderFromDsc();

		}
		if ("UAS".equals(ResourceUtil.getConfigByName("interfacetype"))) {
			String masterbill[] = {"XKN_TEST", "XKN_TEST"};
			for (int m = 0; m < masterbill.length; m++) {

				try {
					if (StringUtil.isEmpty(formDate)) {
						formDate = "2011-01-01";
					}
					String master = masterbill[m];
					String billclass[] = {"?????????", "???????????????", "?????????", "???????????????", "?????????????????????"};
					for (int i = 0; i < billclass.length; i++) {
						Map<String, Object> paramMap = new HashMap<String, Object>();
						paramMap.put("lastUpdateTime", formDate);
						paramMap.put("pi_class", billclass[i]);
						paramMap.put("master", master);

						sdresult billResult = wmIntUtil.getsdBillin(paramMap);
						for (int s = 0; s < billResult.getData().size(); s++) {
							String imcuscode = billResult.getData().get(s).getPiInoutno();
							if (StringUtil.isNotEmpty(imcuscode)) {
								WmOmNoticeHEntity wmimh = systemService.findUniqueByProperty(WmOmNoticeHEntity.class, "imCusCode", imcuscode);
								if (wmimh == null) {
									WmOmNoticeHEntity wmOmNoticeH = new WmOmNoticeHEntity();
									List<WmOmNoticeIEntity> wmomNoticeIListnew = new ArrayList<WmOmNoticeIEntity>();

									wmOmNoticeH.setOmPlatNo(Integer.toString(billResult.getData().get(s).getPiId()));
									wmOmNoticeH.setOrderTypeCode("11");
									wmOmNoticeH.setCusCode(ResourceUtil.getConfigByName("uas.cuscode"));
									String noticeid = wmUtil.getNextomNoticeId(wmOmNoticeH.getOrderTypeCode());
									wmOmNoticeH.setOmNoticeId(noticeid);
									wmOmNoticeH.setPiClass(billResult.getData().get(s).getPiClass());
									wmOmNoticeH.setPiMaster(master);
									wmOmNoticeH.setOcusCode(billResult.getData().get(s).getPiCardcode());
									MdCusOtherEntity mdcusother = systemService.findUniqueByProperty(MdCusOtherEntity.class, "keHuBianMa", wmOmNoticeH.getOcusCode());
									if (mdcusother != null) {
										wmOmNoticeH.setOcusName(mdcusother.getZhongWenQch());
									}
									wmOmNoticeH.setImCusCode(imcuscode);
									for (int k = 0; k < billResult.getData().get(s).getDetail().size(); k++) {
										WmOmNoticeIEntity wmi = new WmOmNoticeIEntity();
										wmi.setGoodsId(billResult.getData().get(s).getDetail().get(k).getPdProdcode());
										MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
												MvGoodsEntity.class, "goodsCode", wmi.getGoodsId());
										if (mvgoods != null) {
											wmi.setGoodsName(mvgoods.getGoodsName());
											wmi.setGoodsUnit(mvgoods.getShlDanWei());
										}
										wmi.setGoodsProData(DateUtils.str2Date(billResult.getData().get(s).getDetail().get(k).getPdProdmadedate(), DateUtils.date_sdf));
										wmi.setGoodsQua(Integer.toString(billResult.getData().get(s).getDetail().get(k).getPdPurcoutqty()));
//                               wmi.setGoodsPrdData(billResult.getData().get(s).getDetail().get(k).getPdProdmadedate2User());
										wmi.setOtherId(Integer.toString(billResult.getData().get(s).getDetail().get(k).getPdPdno()));

										wmomNoticeIListnew.add(wmi);
									}
									wmOmNoticeHService.addMain(wmOmNoticeH, wmomNoticeIListnew);
								}
							} else {
								continue;
							}
						}
					}
					systemService.addLog(message, Globals.Log_Type_UPDATE,
							Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
					message = "????????????";
					throw new BusinessException(e.getMessage());
				}
			}
		}
		j.setMsg(message);
		return j;
	}





	@RequestMapping(params = "doPost")
	@ResponseBody
	public AjaxJson dopost(String id,HttpServletRequest request) {
		String message = null;
		AjaxJson j = new AjaxJson();
		message = "????????????";
		WmOmNoticeHEntity wmOmNoticeHEntity = wmOmNoticeHService.getEntity(WmOmNoticeHEntity.class, id);
		//????????????
		Object id0 = wmOmNoticeHEntity.getOmNoticeId();
		//===================================================================================
		//??????-??????
		String hql0 = "from WmOmNoticeIEntity where 1 = 1 AND omNoticeId = ? ";
		try{
			List<WmOmNoticeIEntity> wmOmNoticeIEntityList = systemService
					.findHql(hql0, id0);//???????????????
			List<Map<String,String>> list = new ArrayList<Map<String,String>>();
			for(WmOmNoticeIEntity t:wmOmNoticeIEntityList){
				Map<String,String> map = new HashMap<String,String>();
//				[{"pd_pdno":1,"pd_outqty":"100","pi_class":"?????????","pi_id":50765226,"pi_inoutno":"JRS180800008"}]
				map.put("pd_pdno",t.getOtherId());
				map.put("pd_outqty",t.getGoodsQua());
				map.put("pi_class",wmOmNoticeHEntity.getPiClass());
				map.put("pi_id",wmOmNoticeHEntity.getOmPlatNo());
				map.put("pi_inoutno",wmOmNoticeHEntity.getImCusCode());
				list.add(map);
			}
			String jsonStr = JSONArray.fromObject(list).toString();
			JSONArray ja = JSONArray.fromObject(jsonStr);
			resResult resResult = wmIntUtil.postBill(ja.toString(),wmOmNoticeHEntity.getPiMaster());
			j.setMsg(resResult.getDetailedMessage());
		}catch (Exception e){
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}

		j.setMsg(message);
		return j;
	}




	/**
	 * ??????????????????
	 *
	 * @return
	 */


	@RequestMapping(params = "doPrint")
	@ResponseBody
	public void downReceiveExcel(WmOmNoticeHEntity wmOmNoticeH,HttpServletRequest request,HttpServletResponse response){
		OutputStream fileOut = null;
		BufferedImage bufferImg = null;
//		String codedFileName = null;
		wmOmNoticeH = systemService.getEntity(WmOmNoticeHEntity.class,
				wmOmNoticeH.getId());//????????????
		String hql0 = "from WmOmNoticeIEntity where 1 = 1 AND omNoticeId = ? ";
		List<WmOmNoticeIEntity> wmOmNoticeIEntityList = systemService
				.findHql(hql0, wmOmNoticeH.getOmNoticeId());//???????????????
		//????????????????????????????????????ByteArrayOutputStream??????????????????ByteArray
		try {
//			StringBuffer sber=new StringBuffer();
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			bufferImg = ImageIO.read(BarcodeUtil.generateToStream(wmOmNoticeH.getOmNoticeId()));
			// ??????????????????????????????????????????
//			codedFileName = java.net.URLEncoder.encode("??????", "UTF-8");
			response.setHeader("content-disposition", "attachment;filename="+wmOmNoticeH.getOmNoticeId()+".xls");
			ImageIO.write(bufferImg, "jpg", byteArrayOut);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("????????????");
			sheet.setColumnWidth(0, 5 * 256);
			sheet.setColumnWidth(1, 10 * 256);
			sheet.setColumnWidth(2, 10 * 200);
			sheet.setColumnWidth(3, 8 * 256);
			sheet.setColumnWidth(4, 8 * 256);
			sheet.setColumnWidth(5, 8 * 256);
			sheet.setColumnWidth(6, 8 * 256);
			sheet.setColumnWidth(7, 8 * 256);
			sheet.setColumnWidth(8, 25 * 256);
			//?????????????????????????????????sheet?????????????????????????????????????????????
			HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
			//anchor?????????????????????????????????
			HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short)7, 1, (short)9, 3);
			anchor.setAnchorType(3);
			//????????????
			patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));



			// ???????????????
			Row row = sheet.createRow((short) 0);   //???????????????


			// ???????????????????????????
			CellStyle cs = wb.createCellStyle();
			CellStyle cs2 = wb.createCellStyle();
			CellStyle cs3 = wb.createCellStyle();
			CellStyle cs4 = wb.createCellStyle();
			// ??????????????????
			Font f = wb.createFont();
			Font f2 = wb.createFont();

			// ?????????????????????????????????????????????
			f.setFontHeightInPoints((short) 16);
			f.setColor(IndexedColors.BLACK.getIndex());
			f.setBoldweight(Font.BOLDWEIGHT_BOLD);

			// ??????????????????????????????????????????
			f2.setFontHeightInPoints((short) 10);
			f2.setColor(IndexedColors.BLACK.getIndex());

//	        Font f3=wb.createFont();
//	        f3.setFontHeightInPoints((short) 10);
//	        f3.setColor(IndexedColors.RED.getIndex());

			// ???????????????????????????????????????????????????
			cs.setFont(f);
			cs.setBorderLeft(CellStyle.BORDER_NONE);
			cs.setBorderRight(CellStyle.BORDER_NONE);
			cs.setBorderTop(CellStyle.BORDER_NONE);
			cs.setBorderBottom(CellStyle.BORDER_NONE);
			cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			// ????????????????????????????????????????????????
			cs2.setFont(f2);
			cs2.setBorderLeft(CellStyle.BORDER_NONE);
			cs2.setBorderRight(CellStyle.BORDER_NONE);
			cs2.setBorderTop(CellStyle.BORDER_NONE);
			cs2.setBorderBottom(CellStyle.BORDER_NONE);
			cs2.setWrapText(true);

//	        cs2.setAlignment(CellStyle.BORDER_NONE);


			cs3.setFont(f2);
			cs3.setBorderLeft(CellStyle.BORDER_MEDIUM);
			cs3.setBorderRight(CellStyle.BORDER_MEDIUM);
			cs3.setBorderTop(CellStyle.BORDER_MEDIUM);
			cs3.setBorderBottom(CellStyle.BORDER_MEDIUM);
//	        cs3.setAlignment(CellStyle.BORDER_HAIR);
			cs4.setFont(f2);
			cs4.setBorderTop(CellStyle.BORDER_MEDIUM);
			cs4.setBorderBottom(CellStyle.BORDER_MEDIUM);
			Row row1 = sheet.createRow((short) 1);   //???????????????
			row1.setHeight((short)700);
			Cell cellTitle = row1.createCell(0);
			cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"????????????");
			cellTitle.setCellStyle(cs);


			Row rowHead1=sheet.createRow((short) 2);  //???????????????
			Cell cellHead11 = rowHead1.createCell(0);
			cellHead11.setCellValue("???????????????"+wmOmNoticeH.getOmNoticeId());
			cellHead11.setCellStyle(cs2);


			Row rowHead2=sheet.createRow((short) 3);  //???????????????
			Cell cellHead21 = rowHead2.createCell(0);
			try{
				MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmOmNoticeH.getCusCode());
				if(md!=null){
					cellHead21.setCellValue("?????????"+wmOmNoticeH.getCusCode()+"/"+md.getZhongWenQch());
				}else{
					cellHead21.setCellValue("?????????"+wmOmNoticeH.getCusCode());
				}
			}finally{

			}

			cellHead21.setCellStyle(cs2);

			Cell cellHead23 = rowHead2.createCell(5);
			cellHead23.setCellValue(" ?????????????????????"+wmOmNoticeH.getDelvData());
			cellHead23.setCellStyle(cs2);


			Row rowHead3=sheet.createRow((short) 4);  //???????????????
			Cell cellHead31 = rowHead3.createCell(0);
			cellHead31.setCellValue("?????????"+wmOmNoticeH.getReMember()+"  ???????????????"+wmOmNoticeH.getReMobile());
			cellHead31.setCellStyle(cs2);


			Cell cellHead35 = rowHead3.createCell(5);
			cellHead35.setCellValue("?????????"+wmOmNoticeH.getReCarno()+"  ?????????"+wmOmNoticeH.getOmBeizhu());
			cellHead35.setCellStyle(cs2);

			Row rowHead4=sheet.createRow((short) 5);  //???????????????
			Cell cellHead41 = rowHead4.createCell(0);
			cellHead41.setCellValue("????????????"+wmOmNoticeH.getDelvMember()+"  ?????????"+wmOmNoticeH.getDelvMobile());
			cellHead31.setCellStyle(cs2);


			Cell cellHead45 = rowHead4.createCell(5);
			cellHead45.setCellValue("???????????????"+wmOmNoticeH.getDelvAddr());
			cellHead45.setCellStyle(cs2);

			//???????????????
			CellRangeAddress   c = new CellRangeAddress(0, 0, 0, 8); //???????????????
			CellRangeAddress   c0 = new CellRangeAddress(1, 1, 0, 8);//???????????????
			CellRangeAddress   c1 = new CellRangeAddress(2, 2, 0, 8);//?????????????????????
			CellRangeAddress   c2 = new CellRangeAddress(3, 3, 0, 4);//???????????????
			CellRangeAddress   c3 = new CellRangeAddress(3, 3, 5, 8);//???????????????????????????
			CellRangeAddress   c4 = new CellRangeAddress(4, 4, 0, 4);//???????????????
			CellRangeAddress   c5 = new CellRangeAddress(4, 4, 5, 8);//???????????????????????????
			CellRangeAddress   c6 = new CellRangeAddress(5, 5, 0, 4);//???????????????
			CellRangeAddress   c7 = new CellRangeAddress(5, 5, 5, 8);//???????????????????????????
//	        CellRangeAddress   c4 = new CellRangeAddress(4, 4, 0, 1);
//	        CellRangeAddress   c5 = new CellRangeAddress(4, 4, 2, 3);
//	        CellRangeAddress   c6 = new CellRangeAddress(4, 4, 4, 5);
//	        CellRangeAddress   c7 = new CellRangeAddress(4, 4, 6, 6);

			sheet.addMergedRegion(c);
			sheet.addMergedRegion(c0);
			sheet.addMergedRegion(c1);
			sheet.addMergedRegion(c2);
			sheet.addMergedRegion(c3);
			sheet.addMergedRegion(c4);
			sheet.addMergedRegion(c5);
			sheet.addMergedRegion(c6);
			sheet.addMergedRegion(c7);


			Row rowColumnName=sheet.createRow((short) 6);  //??????
			String [] columnNames={"??????","????????????","????????????","??????","??????","????????????","????????????"," ","??????"};

			for(int i=0;i<columnNames.length;i++){
				Cell cell = rowColumnName.createCell(i);
				cell.setCellValue(columnNames[i]);
				cell.setCellStyle(cs3);
			}
			int cellsNum=6;
			int cerconNo=1;
			for(int i=0;i<wmOmNoticeIEntityList.size() ;i++){
				WmOmNoticeIEntity entity = wmOmNoticeIEntityList.get(i);
				cellsNum++;
				Row rowColumnValue=sheet.createRow((short) cellsNum);  //??????
				rowColumnValue.setHeight((short)1000);
				Cell cell1 = rowColumnValue.createCell(0);
				cell1.setCellValue(cerconNo);
				cell1.setCellStyle(cs3);
				Cell cell2 = rowColumnValue.createCell(1);
				cell2.setCellValue(entity.getGoodsId());
				cell2.setCellStyle(cs3);
				Cell cell3 = rowColumnValue.createCell(2);
				cell3.setCellStyle(cs3);
				Cell cell8 = rowColumnValue.createCell(7);
				cell8.setCellValue(entity.getBinOm());
				cell8.setCellStyle(cs4);
				Cell cell5 = rowColumnValue.createCell(4);

				cell5.setCellStyle(cs3);
				try{
					MvGoodsEntity goods = systemService.findUniqueByProperty(MvGoodsEntity.class, "goodsCode", entity.getGoodsId());
					if(goods!=null){
						cell3.setCellValue(goods.getGoodsName());
						cell5.setCellValue(goods.getShlDanWei());
					}
				}finally{

				}
				Cell cell4 = rowColumnValue.createCell(3);
				cell4.setCellValue(entity.getGoodsQua());
				cell4.setCellStyle(cs3);

				Cell cell6 = rowColumnValue.createCell(5);
				cell6.setCellValue(DateUtils.date2Str(entity.getGoodsProData(),DateUtils.date_sdf));
				cell6.setCellStyle(cs3);
				Cell cell7 = rowColumnValue.createCell(6);
//				           cell7.setCellValue(entity.getGoodsWeight());
				cell7.setCellStyle(cs3);
				Cell cell9 = rowColumnValue.createCell(8);
				cell9.setCellStyle(cs4);
				//????????????

				byteArrayOut = new ByteArrayOutputStream();
				bufferImg = ImageIO.read(BarcodeUtil.generateToStream(entity.getGoodsId()));
				ImageIO.write(bufferImg, "jpg", byteArrayOut);
//TODO ???????????????????????????????????????????????????
				anchor = new HSSFClientAnchor(0, 0, 0, 0,(short)8, cellsNum, (short)9, cellsNum+1);

				patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));


				cerconNo++;
			}
			Row rowColumnInfo=sheet.createRow((short) 2+cellsNum);  //??????
			rowColumnInfo.createCell(0).setCellValue("???:???????????????"+ResourceUtil.getConfigByName("comaddr")+" ?????????");
			CellRangeAddress   c15 = new CellRangeAddress(10+cellsNum, 10+cellsNum, 0, 15);
			sheet.addMergedRegion(c15);
			fileOut = response.getOutputStream();
			wb.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getExceptionMessage(e));
		}finally{
			if(fileOut != null){
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(ExceptionUtil.getExceptionMessage(e));
				}
			}
		}
	}

	@RequestMapping(params = "doPrintckd")
	@ResponseBody
	public void doPrintckd(WmOmNoticeHEntity wmOmNoticeH,
						   HttpServletRequest request, HttpServletResponse response) {
		OutputStream fileOut = null;
		BufferedImage bufferImg = null;
//		String codedFileName = null;
		wmOmNoticeH = systemService.getEntity(WmOmNoticeHEntity.class,
				wmOmNoticeH.getId());//????????????


		// ????????????????????????????????????ByteArrayOutputStream??????????????????ByteArray
		try {
//			StringBuffer sber = new StringBuffer();

			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//			bufferImg = ImageIO.read(BarcodeUtil.generateToStream(wmImNoticeH
//					.getNoticeId()));
			bufferImg = QRcodeUtil.createImage(wmOmNoticeH
					.getOmNoticeId());

			// ??????????????????????????????????????????
//			codedFileName = java.net.URLEncoder.encode("??????", "UTF-8");
			response.setHeader("content-disposition", "attachment;filename="
					+ wmOmNoticeH.getOmNoticeId() + ".xls");
			ImageIO.write(bufferImg, "jpg", byteArrayOut);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("?????????");
			sheet.setMargin(HSSFSheet.TopMargin,0.1);// ??????????????????
			sheet.setMargin(HSSFSheet.BottomMargin,1.5);// ??????????????????
			sheet.setMargin(HSSFSheet.LeftMargin,0.3);// ??????????????????
			sheet.setMargin(HSSFSheet.RightMargin,0.0);// ???????????????
//			sheet.setDisplayGridlines(true);
			//set print grid lines or not
//			sheet.setPrintGridlines(true);
			sheet.setColumnWidth(0, 5 * 256);
			sheet.setColumnWidth(1, 15 * 256);
			sheet.setColumnWidth(2, 25 * 256);
			sheet.setColumnWidth(3, 11 * 256);
			sheet.setColumnWidth(4, 5 * 256);
			sheet.setColumnWidth(5, 5 * 256);
			sheet.setColumnWidth(6, 7 * 256);
			sheet.setColumnWidth(7, 7 * 256);
			sheet.setColumnWidth(8, 9 * 256);
			sheet.setColumnWidth(9, 7 * 256);
			sheet.setColumnWidth(10, 3 * 256);
			// sheet.setColumnWidth(6, 8 * 256);
			// sheet.setColumnWidth(7, 8 * 256);
			// sheet.setColumnWidth(8, 8 * 256);

			// ???????????????????????????
			CellStyle cs = wb.createCellStyle();
			CellStyle cs1 = wb.createCellStyle();
			CellStyle cs2 = wb.createCellStyle();
			CellStyle cs3 = wb.createCellStyle();
			CellStyle cs4 = wb.createCellStyle();
			CellStyle cs5 = wb.createCellStyle();
			CellStyle cs5r = wb.createCellStyle();

			CellStyle cs51 = wb.createCellStyle();
			CellStyle cs52 = wb.createCellStyle();
			// ??????????????????
			Font f = wb.createFont();
			Font f2 = wb.createFont();
			Font f5 = wb.createFont();
			// ?????????????????????????????????????????????
			f.setFontHeightInPoints((short) 16);
			f.setColor(IndexedColors.BLACK.getIndex());
			f.setBoldweight(Font.BOLDWEIGHT_BOLD);

			// ??????????????????????????????????????????
			f2.setFontHeightInPoints((short) 10);
			f2.setColor(IndexedColors.BLACK.getIndex());


			f5.setFontHeightInPoints((short) 8);
			f5.setColor(IndexedColors.BLACK.getIndex());

			// ???????????????????????????????????????????????????
			cs.setFont(f);
			cs.setBorderLeft(CellStyle.BORDER_NONE);
			cs.setBorderRight(CellStyle.BORDER_NONE);
			cs.setBorderTop(CellStyle.BORDER_NONE);
			cs.setBorderBottom(CellStyle.BORDER_NONE);
			cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			cs1.setFont(f2);
			cs1.setBorderLeft(CellStyle.BORDER_NONE);
			cs1.setBorderRight(CellStyle.BORDER_NONE);
			cs1.setBorderTop(CellStyle.BORDER_NONE);
			cs1.setBorderBottom(CellStyle.BORDER_NONE);
			cs1.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			cs1.setWrapText(true);
			// ????????????????????????????????????????????????
			cs2.setFont(f2);
			cs2.setBorderLeft(CellStyle.BORDER_NONE);
			cs2.setBorderRight(CellStyle.BORDER_NONE);
			cs2.setBorderTop(CellStyle.BORDER_NONE);
			cs2.setBorderBottom(CellStyle.BORDER_NONE);
			cs2.setWrapText(true);

			// cs2.setAlignment(CellStyle.BORDER_NONE);

			cs3.setFont(f2);
			cs3.setBorderLeft(CellStyle.BORDER_MEDIUM);
			cs3.setBorderRight(CellStyle.BORDER_MEDIUM);
			cs3.setBorderTop(CellStyle.BORDER_MEDIUM);
			cs3.setBorderBottom(CellStyle.BORDER_MEDIUM);
			// cs3.setAlignment(CellStyle.BORDER_HAIR);
			cs4.setFont(f2);
			cs4.setBorderTop(CellStyle.BORDER_MEDIUM);
			cs4.setBorderBottom(CellStyle.BORDER_MEDIUM);

			cs5.setFont(f2);
			cs5.setBorderLeft(CellStyle.BORDER_THIN);
			cs5.setBorderRight(CellStyle.BORDER_THIN);
			cs5.setBorderTop(CellStyle.BORDER_THIN);
			cs5.setBorderBottom(CellStyle.BORDER_THIN);
			cs5.setWrapText(true);


			cs5r.setFont(f2);
			cs5r.setBorderLeft(CellStyle.BORDER_THIN);
			cs5r.setBorderRight(CellStyle.BORDER_THIN);
			cs5r.setBorderTop(CellStyle.BORDER_THIN);
			cs5r.setBorderBottom(CellStyle.BORDER_THIN);
			cs5r.setWrapText(true);
			cs5r.setAlignment(CellStyle.ALIGN_RIGHT);
			cs51.setFont(f2);
			cs51.setBorderLeft(CellStyle.BORDER_THIN);
			cs51.setBorderRight(CellStyle.BORDER_THIN);
			cs51.setBorderTop(CellStyle.BORDER_THIN);
			cs51.setBorderBottom(CellStyle.BORDER_THIN);
			cs51.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			cs51.setWrapText(true);

			cs52.setFont(f5);
			cs52.setBorderLeft(CellStyle.BORDER_NONE);
			cs52.setBorderRight(CellStyle.BORDER_NONE);
			cs52.setBorderTop(CellStyle.BORDER_NONE);
			cs52.setBorderBottom(CellStyle.BORDER_NONE);
			cs52.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			cs52.setWrapText(true);
			cs52.setRotation((short)255);

			int page = 0;
			int cerconNo = 1;
//			String tsql = "SELECT wq.pro_data,wq.base_unit,wq.rec_deg, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,cast(sum(wq.tin_tj) as signed) tin_tj ,cast(sum(wq.tin_zhl)  as signed) tin_zhl "
//					+" FROM wm_om_qm_i wq,mv_goods mg where wq.om_notice_id = ? "
//					+" and  wq.goods_id = mg.goods_code group by wq.om_notice_id, mg.goods_code,wq.pro_data";
			String tsql = "SELECT wq.goods_pro_data as pro_data,wq.base_unit, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,mg.chl_shl,cast(mg.ti_ji_cm/mg.chl_shl as signed) tin_tj ,(mg.zhl_kg/mg.chl_shl ) as tin_zhl  "
					+" FROM wm_to_down_goods wq,mv_goods mg where wq.order_id =  ? "
					+" and  wq.goods_id = mg.goods_code group by wq.order_id, mg.goods_code,wq.goods_pro_data";

			List<Map<String, Object>> result = systemService
					.findForJdbc(tsql, wmOmNoticeH.getOmNoticeId());


			int size = result.size();
			if(size<1){
				tsql = "SELECT wq.pro_data,wq.base_unit, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,mg.chl_shl,cast(mg.ti_ji_cm/mg.chl_shl as signed) tin_tj , (mg.zhl_kg/mg.chl_shl)  as   tin_zhl "
						+" FROM wm_om_qm_i wq,mv_goods mg where wq.om_notice_id = ? "
						+" and  wq.goods_id = mg.goods_code group by wq.om_notice_id, mg.goods_code,wq.pro_data";
				result = systemService
						.findForJdbc(tsql, wmOmNoticeH.getOmNoticeId());
				size = result.size();
			}
			int pagesize = 10;
			int pagecount = size%pagesize==0?size/pagesize:size/pagesize+1;
			double sum = 0;
			double sumxs = 0;
			double sumzl = 0;
			do {

				// ?????????????????????????????????sheet?????????????????????????????????????????????
				HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
				// anchor?????????????????????????????????
				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
						(short) 8, page*20+1, (short) 10, page*20+5);
				anchor.setAnchorType(2);
				// ????????????
				patriarch
						.createPicture(anchor, wb.addPicture(
								byteArrayOut.toByteArray(),
								HSSFWorkbook.PICTURE_TYPE_JPEG));

				// ???????????????
				Row row = sheet.createRow((short) page*20+0); // ???????????????


				Row row1 = sheet.createRow((short) page*20+1); // ???????????????
				row1.setHeight((short) 700);
				Cell cellTitle = row1.createCell(0);
				cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"?????????");
				cellTitle.setCellStyle(cs);

				Row rowHead1 = sheet.createRow((short) page*20+2); // ???????????????
				Cell cellHead1 = rowHead1.createCell(0);
				cellHead1.setCellValue("???????????????"+ResourceUtil.getConfigByName("comaddr") );
				cellHead1.setCellStyle(cs1);

				Row rowHead2 = sheet.createRow((short) page*20+3); // ???????????????
				Cell cellHead2 = rowHead2.createCell(0);
				cellHead2.setCellValue("?????????"+ ResourceUtil.getConfigByName("comtel"));
				cellHead2.setCellStyle(cs1);


				Row rowHead4 = sheet.createRow((short) page*20+4); // ???????????????
				Cell cellHead4 = rowHead4.createCell(0);
				cellHead4.setCellValue("??????????????? " +DateUtils.date2Str(wmOmNoticeH.getDelvData(), DateUtils.date_sdf) );
				cellHead4.setCellStyle(cs2);

				Cell cellHead42 = rowHead4.createCell(3);
				cellHead42.setCellValue("??????????????? " +wmOmNoticeH.getOmNoticeId());
				cellHead42.setCellStyle(cs2);

				Row rowHead5 = sheet.createRow((short) page*20+5); // ???????????????
				Cell cellHead5 = rowHead5.createCell(0);
				cellHead5.setCellValue("??????????????? " );
				cellHead5.setCellStyle(cs2);

				Cell cellHead52 = rowHead5.createCell(3);
				cellHead52.setCellValue("????????? " +wmOmNoticeH.getReCarno());
				cellHead52.setCellStyle(cs2);

				Row rowHead6 = sheet.createRow((short) page*20+6); // ???????????????
				Cell cellHead6 = rowHead6.createCell(0);
				MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmOmNoticeH.getCusCode());

				cellHead6.setCellValue("??????????????? " +wmOmNoticeH.getCusCode()+md.getZhongWenQch());
				cellHead6.setCellStyle(cs2);

				Cell cellHead62 = rowHead6.createCell(3);
				cellHead62.setCellValue("???????????? "+wmOmNoticeH.getDelvMember()+"   ??????:"+wmOmNoticeH.getDelvMobile() );
				cellHead62.setCellStyle(cs2);

				Row rowHead7 = sheet.createRow((short) page*20+7); // ???????????????
				Cell cellHead7 = rowHead7.createCell(0);
				cellHead7.setCellValue("??????????????? " +wmOmNoticeH.getDelvAddr());
				cellHead7.setCellStyle(cs2);

				Cell cellHead72 = rowHead7.createCell(5);
				cellHead72.setCellValue("??????????????? "+DateUtils.date2Str(DateUtils.getDate(), DateUtils.datetimeFormat) +"   ???"+(page+1)+"???");
				cellHead72.setCellStyle(cs2);


				// ???????????????
				CellRangeAddress c = new CellRangeAddress(page*20+0, page*20+0, 0, 9); // ???????????????
				CellRangeAddress c1 = new CellRangeAddress(page*20+1, page*20+1, 0, 8);// ???????????????
				CellRangeAddress c2 = new CellRangeAddress(page*20+2, page*20+2, 0, 9);// ???????????????
				CellRangeAddress c3 = new CellRangeAddress(page*20+3, page*20+3, 0, 9);// ???????????????

				CellRangeAddress c4 = new CellRangeAddress(page*20+4, page*20+4, 0, 2);// ???5??? ????????????
				CellRangeAddress c42 = new CellRangeAddress(page*20+4, page*20+4, 3, 9);// ???5???????????????
				CellRangeAddress c5 = new CellRangeAddress(page*20+5, page*20+5, 0, 2);// ???6?????????????????????
				CellRangeAddress c52 = new CellRangeAddress(page*20+5, page*20+5, 3, 9);// ???6?????????
				CellRangeAddress c6 = new CellRangeAddress(page*20+6, page*20+6, 0, 2);// ???7???????????????
				CellRangeAddress c62 = new CellRangeAddress(page*20+6, page*20+6, 3, 9);// ???7?????????
				CellRangeAddress c7 = new CellRangeAddress(page*20+7, page*20+7, 0, 4);//???7???????????????
				CellRangeAddress c72 = new CellRangeAddress(page*20+7, page*20+7, 5, 9);//???7???????????????
				sheet.addMergedRegion(c);
				sheet.addMergedRegion(c1);
				sheet.addMergedRegion(c2);
				sheet.addMergedRegion(c3);
				sheet.addMergedRegion(c4);
				sheet.addMergedRegion(c5);
				sheet.addMergedRegion(c6);
				sheet.addMergedRegion(c7);
				sheet.addMergedRegion(c42);
				sheet.addMergedRegion(c52);
				sheet.addMergedRegion(c62);
				sheet.addMergedRegion(c72);

				Cell cell73 = row.createCell(10);
				cell73.setCellValue("??? ????????? ??? ????????? ???????????? ????????????                                   ");
				cell73.setCellStyle(cs52);


				CellRangeAddress c73 = new CellRangeAddress(page*20, page*20+19, 10, 10);//???7???????????????
				sheet.addMergedRegion(c73);

				Row rowColumnName = sheet.createRow((short) page*20+8); // ??????
				String[] columnNames = { "??????", "????????????", "????????????", "????????????", "??????","??????", "?????????", "??????/KG","??????/cm??","??????" };
				try{
					if("hr".equals(ResourceUtil.getConfigByName("wm.ckd"))){
//						String[]  columnNames1 = { "??????", "????????????", "????????????", "????????????", "??????","??????", "?????????", "??????/KG","??????","??????" };
						String[]  columnNames1 = { "??????", "????????????", "????????????", "????????????", "??????","??????", "?????????", "??????/KG","??????","??????" };

						columnNames = columnNames1;
					}
				}catch ( Exception e){
					logger.error(ExceptionUtil.getExceptionMessage(e));
				}


				for (int i = 0; i < columnNames.length; i++) {
					Cell cell = rowColumnName.createCell(i);
					cell.setCellValue(columnNames[i]);
					cell.setCellStyle(cs3);
				}


				int cellsNum = page*20+8;
				int oversize = 0;
				if(size==pagesize&&page==pagecount-1){
					oversize = 1;
				}
				for (int i = page*pagesize; i < (page+1)*pagesize+oversize; i++) {
					if(i< size){

						cellsNum++;
						Row rowColumnValue = sheet.createRow((short) cellsNum); // ??????
						rowColumnValue.setHeight((short) 250);

						Cell cell1 = rowColumnValue.createCell(0);
						cell1.setCellValue(cerconNo);
						cell1.setCellStyle(cs51);
						Cell cell2 = rowColumnValue.createCell(1);
						cell2.setCellValue(result.get(i).get("goods_id")
								.toString());
						cell2.setCellStyle(cs5);

						Cell cell3 = rowColumnValue.createCell(2);
						cell3.setCellValue(result.get(i).get("shp_ming_cheng")
								.toString());
						cell3.setCellStyle(cs5);
						try {
							Cell cell4 = rowColumnValue.createCell(3);// ????????????



							cell4.setCellValue(result.get(i).get("pro_data")
									.toString());


							cell4.setCellStyle(cs5r);
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}

						try {
							Cell cell5 = rowColumnValue.createCell(4);// ??????
							cell5.setCellValue("");
							cell5.setCellStyle(cs5);
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}

						try {

							long  xs = (long) Math.floor(Double.parseDouble(result.get(i).get("goods_count")
									.toString()) / Double.parseDouble(result.get(i).get("chl_shl")
									.toString()));
							sumxs = sumxs  + xs;
							Cell cell6 = rowColumnValue.createCell(5);// ??????
							cell6.setCellValue(xs);
							cell6.setCellStyle(cs5);
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}

						try {
							double bs =						  Double.parseDouble(result.get(i).get("goods_count")
									.toString()) % Double.parseDouble(result.get(i).get("chl_shl")
									.toString());
							sum = sum + bs;
							Cell cell7 = rowColumnValue.createCell(6);// ??????
							cell7.setCellValue(bs);
							cell7.setCellStyle(cs5);
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}
						Cell cell8 = rowColumnValue.createCell(7);// ??????
						try {

							double zhl = Double.parseDouble(result.get(i).get("tin_zhl")
									.toString()) * Double.parseDouble(result.get(i).get("goods_count").toString());
							sumzl = sumzl + zhl;
							cell8.setCellValue(zhl);

						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}
						cell8.setCellStyle(cs5);
						Cell cell9 = rowColumnValue.createCell(8);// ??????
//						try {
//							double tij = Double.parseDouble(result.get(i).get("tin_tj")
//									.toString()) * Double.parseDouble(result.get(i).get("goods_count").toString());
//
//
//							cell9.setCellValue(tij);
//
//						} catch (Exception e) {
//							// TODO: handle exception
//						}

						try{
							if("hr".equals(ResourceUtil.getConfigByName("wm.ckd"))) {
								cell9.setCellValue(wmUtil.getstock(result.get(i).get("goods_id").toString()));
							}
						}catch (Exception e){
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}


						cell9.setCellStyle(cs5);

						Cell cell10 = rowColumnValue.createCell(9);// ??????


						cell10.setCellStyle(cs5);

						cerconNo++;
					}
					if(i== size){

						cellsNum++;
						Row rowColumnValue = sheet.createRow((short) cellsNum); // ??????
						rowColumnValue.setHeight((short) 250);
						Cell cell5 = rowColumnValue.createCell(5);// ??????
						cell5.setCellValue(Double.toString(sumxs));
						Cell cell6 = rowColumnValue.createCell(6);// ??????
						cell6.setCellValue(Double.toString(sum));
						Cell cell7 = rowColumnValue.createCell(7);// ????????????
						cell7.setCellValue(Double.toString(sumzl));
//				cell6.setCellStyle(cs5);
						Cell cell0 = rowColumnValue.createCell(0);// ??????
						cell0.setCellValue("?????????");
//				cell0.setCellStyle(cs5);
						CellRangeAddress c15 = new CellRangeAddress( cellsNum,
								cellsNum, 0, 4);
						sheet.addMergedRegion(c15);
						cerconNo++;

					}


				}
				Row rowColumnInfo = sheet.createRow((short) 1 + cellsNum); // ??????
				rowColumnInfo.setHeight((short) 250);
				rowColumnInfo.createCell(0).setCellValue(
						"  ???????????????                               ???????????????                               ???????????????	");
				CellRangeAddress c15 = new CellRangeAddress(1 + cellsNum,
						1 + cellsNum, 0, 9);
				sheet.addMergedRegion(c15);

				Row rowColumnInfo2 = sheet.createRow((short) 2 + cellsNum); // ??????
				rowColumnInfo2.setHeight((short) 250);
				rowColumnInfo2.createCell(0).setCellValue(
						"  ???????????????                               ???????????????                               ?????????????????????	");
				CellRangeAddress c152 = new CellRangeAddress(2 + cellsNum,
						2 + cellsNum, 0, 9);
				sheet.addMergedRegion(c152);
				page++;
			} while (page<pagecount);
			fileOut = response.getOutputStream();
			HSSFPrintSetup printSetup = sheet.getPrintSetup();
			printSetup.setPaperSize(HSSFPrintSetup.A5_PAPERSIZE);

			wb.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getExceptionMessage(e));
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(ExceptionUtil.getExceptionMessage(e));
				}
			}
		}
	}

	@RequestMapping(params = "doPrintckdsxlq")
	@ResponseBody
	public void doPrintckdsxlq(WmOmNoticeHEntity wmOmNoticeH,
						   HttpServletRequest request, HttpServletResponse response) {
		OutputStream fileOut = null;
		BufferedImage bufferImg = null;
//		String codedFileName = null;
		wmOmNoticeH = systemService.getEntity(WmOmNoticeHEntity.class,
				wmOmNoticeH.getId());//????????????


		// ????????????????????????????????????ByteArrayOutputStream??????????????????ByteArray
		try {
//			StringBuffer sber = new StringBuffer();

			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//			bufferImg = ImageIO.read(BarcodeUtil.generateToStream(wmImNoticeH
//					.getNoticeId()));
			ClassPathResource classPathResource = new ClassPathResource("icon_sx.jpeg");
			bufferImg = ImageIO.read(classPathResource.getInputStream());

			// ??????????????????????????????????????????
//			codedFileName = java.net.URLEncoder.encode("??????", "UTF-8");
			response.setHeader("content-disposition", "attachment;filename="
					+ wmOmNoticeH.getOmNoticeId() + ".xls");
			ImageIO.write(bufferImg, "jpg", byteArrayOut);

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet("?????????");
			sheet.setMargin(HSSFSheet.TopMargin,0.1);// ??????????????????
			sheet.setMargin(HSSFSheet.BottomMargin,1.5);// ??????????????????
			sheet.setMargin(HSSFSheet.LeftMargin,0.3);// ??????????????????
			sheet.setMargin(HSSFSheet.RightMargin,0.0);// ???????????????
//			sheet.setDisplayGridlines(true);
			//set print grid lines or not
//			sheet.setPrintGridlines(true);
			sheet.setColumnWidth(0, 9 * 256);
			sheet.setColumnWidth(1, 20 * 256);
			sheet.setColumnWidth(2, 9 * 256);
			sheet.setColumnWidth(3, 5 * 256);
			sheet.setColumnWidth(4, 5 * 256);
			sheet.setColumnWidth(5, 22 * 256);
			sheet.setColumnWidth(6, 11 * 256);
			sheet.setColumnWidth(7, 12 * 256);
			sheet.setColumnWidth(8, 5 * 256);
			sheet.setColumnWidth(9, 5 * 256);
			sheet.setColumnWidth(10, 3 * 256);
			// sheet.setColumnWidth(6, 8 * 256);
			// sheet.setColumnWidth(7, 8 * 256);
			// sheet.setColumnWidth(8, 8 * 256);

			// ???????????????????????????
			CellStyle cs = wb.createCellStyle();
			CellStyle cs1 = wb.createCellStyle();
			CellStyle cs2 = wb.createCellStyle();
			CellStyle cs3 = wb.createCellStyle();
			CellStyle cs4 = wb.createCellStyle();
			CellStyle cs5 = wb.createCellStyle();
			CellStyle cs5r = wb.createCellStyle();

			CellStyle cs51 = wb.createCellStyle();
			CellStyle cs52 = wb.createCellStyle();
			// ??????????????????
			Font f = wb.createFont();
			Font f2 = wb.createFont();
			Font f5 = wb.createFont();
			// ?????????????????????????????????????????????
			f.setFontHeightInPoints((short) 16);
			f.setColor(IndexedColors.BLACK.getIndex());
			f.setBoldweight(Font.BOLDWEIGHT_BOLD);

			// ??????????????????????????????????????????
			f2.setFontHeightInPoints((short) 10);
			f2.setColor(IndexedColors.BLACK.getIndex());


			f5.setFontHeightInPoints((short) 8);
			f5.setColor(IndexedColors.BLACK.getIndex());

			// ???????????????????????????????????????????????????
			cs.setFont(f);
			cs.setBorderLeft(CellStyle.BORDER_NONE);
			cs.setBorderRight(CellStyle.BORDER_NONE);
			cs.setBorderTop(CellStyle.BORDER_NONE);
			cs.setBorderBottom(CellStyle.BORDER_NONE);
			cs.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			cs1.setFont(f2);
			cs1.setBorderLeft(CellStyle.BORDER_NONE);
			cs1.setBorderRight(CellStyle.BORDER_NONE);
			cs1.setBorderTop(CellStyle.BORDER_NONE);
			cs1.setBorderBottom(CellStyle.BORDER_NONE);
			cs1.setAlignment(HSSFCellStyle.ALIGN_LEFT);

			cs1.setWrapText(true);
			// ????????????????????????????????????????????????
			cs2.setFont(f2);
			cs2.setBorderLeft(CellStyle.BORDER_NONE);
			cs2.setBorderRight(CellStyle.BORDER_NONE);
			cs2.setBorderTop(CellStyle.BORDER_NONE);
			cs2.setBorderBottom(CellStyle.BORDER_NONE);
			cs2.setWrapText(true);

			// cs2.setAlignment(CellStyle.BORDER_NONE);

			cs3.setFont(f2);
			cs3.setBorderLeft(CellStyle.BORDER_MEDIUM);
			cs3.setBorderRight(CellStyle.BORDER_MEDIUM);
			cs3.setBorderTop(CellStyle.BORDER_MEDIUM);
			cs3.setBorderBottom(CellStyle.BORDER_MEDIUM);
			// cs3.setAlignment(CellStyle.BORDER_HAIR);
			cs4.setFont(f2);
			cs4.setBorderTop(CellStyle.BORDER_MEDIUM);
			cs4.setBorderBottom(CellStyle.BORDER_MEDIUM);

			cs5.setFont(f2);
			cs5.setBorderLeft(CellStyle.BORDER_THIN);
			cs5.setBorderRight(CellStyle.BORDER_THIN);
			cs5.setBorderTop(CellStyle.BORDER_THIN);
			cs5.setBorderBottom(CellStyle.BORDER_THIN);
			cs5.setWrapText(true);


			cs5r.setFont(f2);
			cs5r.setBorderLeft(CellStyle.BORDER_THIN);
			cs5r.setBorderRight(CellStyle.BORDER_THIN);
			cs5r.setBorderTop(CellStyle.BORDER_THIN);
			cs5r.setBorderBottom(CellStyle.BORDER_THIN);
			cs5r.setWrapText(true);
			cs5r.setAlignment(CellStyle.ALIGN_RIGHT);
			cs51.setFont(f2);
			cs51.setBorderLeft(CellStyle.BORDER_THIN);
			cs51.setBorderRight(CellStyle.BORDER_THIN);
			cs51.setBorderTop(CellStyle.BORDER_THIN);
			cs51.setBorderBottom(CellStyle.BORDER_THIN);
			cs51.setAlignment(HSSFCellStyle.ALIGN_CENTER);

			cs51.setWrapText(true);

			cs52.setFont(f5);
			cs52.setBorderLeft(CellStyle.BORDER_NONE);
			cs52.setBorderRight(CellStyle.BORDER_NONE);
			cs52.setBorderTop(CellStyle.BORDER_NONE);
			cs52.setBorderBottom(CellStyle.BORDER_NONE);
			cs52.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);


			cs52.setWrapText(true);
			cs52.setRotation((short)255);

			int page = 0;
			int cerconNo = 1;
//			String tsql = "SELECT wq.pro_data,wq.base_unit,wq.rec_deg, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,cast(sum(wq.tin_tj) as signed) tin_tj ,cast(sum(wq.tin_zhl)  as signed) tin_zhl "
//					+" FROM wm_om_qm_i wq,mv_goods mg where wq.om_notice_id = ? "
//					+" and  wq.goods_id = mg.goods_code group by wq.om_notice_id, mg.goods_code,wq.pro_data";
			String tsql = "SELECT wq.goods_pro_data as pro_data,wq.base_goodscount,wq.base_unit,mg.gao_dan_pin, mg.goods_code,mg.shp_gui_ge, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,mg.chl_shl,cast(mg.ti_ji_cm/mg.chl_shl as signed) tin_tj ,(mg.zhl_kg/mg.chl_shl ) as tin_zhl  "
					+" FROM wm_to_down_goods wq,mv_goods mg where wq.order_id =  ? "
					+" and  wq.goods_id = mg.goods_id group by wq.order_id, mg.goods_code,wq.goods_pro_data";

			List<Map<String, Object>> result = systemService
					.findForJdbc(tsql, wmOmNoticeH.getOmNoticeId());


			int size = result.size();
			if(size<1){
				tsql = "SELECT wq.pro_data,wq.base_unit,wq.base_goodscount, mg.goods_code,mg.shp_gui_ge,mg.gao_dan_pin, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,mg.chl_shl,cast(mg.ti_ji_cm/mg.chl_shl as signed) tin_tj , (mg.zhl_kg/mg.chl_shl)  as   tin_zhl "
						+" FROM wm_om_qm_i wq,mv_goods mg where wq.om_notice_id = ? "
						+" and  wq.goods_id = mg.goods_id group by wq.om_notice_id, mg.goods_code,wq.pro_data";
				result = systemService
						.findForJdbc(tsql, wmOmNoticeH.getOmNoticeId());
				size = result.size();
			}
			int pagesize = 10;
			int pagecount = size%pagesize==0?size/pagesize:size/pagesize+1;
			double sum = 0;
			double sumxs = 0;
			double sumzl = 0;
			do {

				// ?????????????????????????????????sheet?????????????????????????????????????????????
				HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
				// anchor?????????????????????????????????
				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
						(short) 8, page*20+1, (short) 10, page*20+5);
				anchor.setAnchorType(2);
				// ????????????
				patriarch
						.createPicture(anchor, wb.addPicture(
								byteArrayOut.toByteArray(),
								HSSFWorkbook.PICTURE_TYPE_JPEG));

				// ???????????????
				Row row = sheet.createRow((short) page*20+0); // ???????????????
				row.setHeight((short) 700);
				Cell cellTitle = row.createCell(0);
				cellTitle.setCellValue("?????????");
				cellTitle.setCellStyle(cs);

				BaStoreEntity baStoreEntity = systemService.findUniqueByProperty(BaStoreEntity.class,"storeCode",wmOmNoticeH.getStoreCode());

				Row row2 = sheet.createRow((short) page*20+1); // ???????????????
				//row2.setHeight((short) 700);
				Cell cellTitle20 = row2.createCell(0);
				if (baStoreEntity != null) {
					cellTitle20.setCellValue("?????????"+(StringUtils.isEmpty(baStoreEntity.getStoreName())?"":baStoreEntity.getStoreName()));
				}else {
					cellTitle20.setCellValue("?????????");
				}
				cellTitle20.setCellStyle(cs1);
				Cell cellTitle2 = row2.createCell(3);
				cellTitle2.setCellValue("???????????????");
				cellTitle2.setCellStyle(cs1);
				Cell cellTitle21 = row2.createCell(6);
				cellTitle21.setCellValue("???????????????"+ (StringUtils.isEmpty(wmOmNoticeH.getDelvMethod())?"":wmOmNoticeH.getDelvMethod()));
				cellTitle21.setCellStyle(cs1);

				Row row3 = sheet.createRow((short) page*20+2); // ???????????????
				//row3.setHeight((short) 700);
				Cell cellTitle31 = row3.createCell(0);
				cellTitle31.setCellValue("????????????");
				cellTitle31.setCellStyle(cs1);
				Cell cellTitle32 = row3.createCell(3);
				cellTitle32.setCellValue("??????????????????");
				cellTitle32.setCellStyle(cs1);
				Cell cellTitle33 = row3.createCell(6);
				cellTitle33.setCellValue("???????????????");
				cellTitle33.setCellStyle(cs1);


				MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmOmNoticeH.getCusCode());
				Row row4 = sheet.createRow((short) page*20+3); // ???????????????
				//row4.setHeight((short) 700);
				Cell cellTitle41 = row4.createCell(0);
				cellTitle41.setCellValue("???????????????"+wmOmNoticeH.getImCusCode());
				cellTitle41.setCellStyle(cs1);
				Cell cellTitle42 = row4.createCell(3);
				cellTitle42.setCellValue("???????????????"+wmOmNoticeH.getOcusName() == null ?"":wmOmNoticeH.getOcusName());
				cellTitle42.setCellStyle(cs1);
				Cell cellTitle43 = row4.createCell(6);
				cellTitle43.setCellValue("???????????????"+wmOmNoticeH.getDelvData() == null?"":DateUtils.date2Str(wmOmNoticeH.getDelvData(), DateUtils.date_sdf));
				cellTitle43.setCellStyle(cs1);
				//MdCusOtherEntity cusOther = systemService.findUniqueByProperty(MdCusOtherEntity.class,"keHuBianMa",wmOmNoticeH.getOcusCode());


				Row row5 = sheet.createRow((short) page*20+4); // ???????????????
				//row5.setHeight((short) 700);
				Cell cellTitle51 = row5.createCell(0);
				cellTitle51.setCellValue("???????????????"+wmOmNoticeH.getDelvMobile());
				cellTitle51.setCellStyle(cs1);
				Cell cellTitle52 = row5.createCell(3);
				cellTitle52.setCellValue("???????????????"+wmOmNoticeH.getOmNoticeId());
				cellTitle52.setCellStyle(cs1);
				Cell cellTitle53 = row5.createCell(6);
				cellTitle53.setCellValue("????????????"+wmOmNoticeH.getDelvMember());
				cellTitle53.setCellStyle(cs1);

				Row row6 = sheet.createRow((short) page*20+5); // ???????????????
				//row6.setHeight((short) 700);
				Cell cellTitle61 = row6.createCell(0);
				cellTitle61.setCellValue("???????????????"+wmOmNoticeH.getDelvAddr() == null ?"":wmOmNoticeH.getDelvAddr());
				cellTitle61.setCellStyle(cs1);

				Row row7 = sheet.createRow((short) page*20+6); // ???????????????
				//row6.setHeight((short) 700);
				Cell cellTitle71 = row7.createCell(0);
				cellTitle71.setCellValue("?????????"+wmOmNoticeH.getOmBeizhu());
				cellTitle71.setCellStyle(cs1);
//				Cell cellTitle62 = row5.createCell(3);
//				cellTitle62.setCellValue("cs1");
//				cellTitle62.setCellStyle(cs);
//
//				Row row1 = sheet.createRow((short) page*20+1); // ???????????????
//				row1.setHeight((short) 700);
//				Cell cellTitle = row1.createCell(0);
//				cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"?????????");
//				cellTitle.setCellStyle(cs);
//
//				Row rowHead1 = sheet.createRow((short) page*20+2); // ???????????????
//				Cell cellHead1 = rowHead1.createCell(0);
//				cellHead1.setCellValue("???????????????"+ResourceUtil.getConfigByName("comaddr") );
//				cellHead1.setCellStyle(cs1);
//
//				Row rowHead2 = sheet.createRow((short) page*20+3); // ???????????????
//				Cell cellHead2 = rowHead2.createCell(0);
//				cellHead2.setCellValue("?????????"+ ResourceUtil.getConfigByName("comtel"));
//				cellHead2.setCellStyle(cs1);
//
//
//				Row rowHead4 = sheet.createRow((short) page*20+4); // ???????????????
//				Cell cellHead4 = rowHead4.createCell(0);
//				cellHead4.setCellValue("??????????????? " +DateUtils.date2Str(wmOmNoticeH.getDelvData(), DateUtils.date_sdf) );
//				cellHead4.setCellStyle(cs2);
//
//				Cell cellHead42 = rowHead4.createCell(3);
//				cellHead42.setCellValue("??????????????? " +wmOmNoticeH.getOmNoticeId());
//				cellHead42.setCellStyle(cs2);
//
//				Row rowHead5 = sheet.createRow((short) page*20+5); // ???????????????
//				Cell cellHead5 = rowHead5.createCell(0);
//				cellHead5.setCellValue("??????????????? " );
//				cellHead5.setCellStyle(cs2);
//
//				Cell cellHead52 = rowHead5.createCell(3);
//				cellHead52.setCellValue("????????? " +wmOmNoticeH.getReCarno());
//				cellHead52.setCellStyle(cs2);
//
//				Row rowHead6 = sheet.createRow((short) page*20+6); // ???????????????
//				Cell cellHead6 = rowHead6.createCell(0);
//				MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmOmNoticeH.getCusCode());
//
//				cellHead6.setCellValue("??????????????? " +wmOmNoticeH.getCusCode()+md.getZhongWenQch());
//				cellHead6.setCellStyle(cs2);
//
//				Cell cellHead62 = rowHead6.createCell(3);
//				cellHead62.setCellValue("???????????? "+wmOmNoticeH.getDelvMember()+"   ??????:"+wmOmNoticeH.getDelvMobile() );
//				cellHead62.setCellStyle(cs2);
//
//				Row rowHead7 = sheet.createRow((short) page*20+7); // ???????????????
//				Cell cellHead7 = rowHead7.createCell(0);
//				cellHead7.setCellValue("??????????????? " +wmOmNoticeH.getDelvAddr());
//				cellHead7.setCellStyle(cs2);
//
//				Cell cellHead72 = rowHead7.createCell(5);
//				cellHead72.setCellValue("??????????????? "+DateUtils.date2Str(DateUtils.getDate(), DateUtils.datetimeFormat) +"   ???"+(page+1)+"???");
//				cellHead72.setCellStyle(cs2);


				// ???????????????
				CellRangeAddress c = new CellRangeAddress(page*20+0, page*20+0, 0, 9); // ???????????????
				CellRangeAddress c1 = new CellRangeAddress(page*20+1, page*20+1, 0, 2);// ???????????????
				CellRangeAddress c11 = new CellRangeAddress(page*20+1, page*20+1, 3, 5);// ???????????????
				CellRangeAddress c12 = new CellRangeAddress(page*20+1, page*20+1, 6, 7);// ???????????????
				CellRangeAddress c2 = new CellRangeAddress(page*20+2, page*20+2, 0, 2);// ???????????????
				CellRangeAddress c21 = new CellRangeAddress(page*20+2, page*20+2, 3, 5);// ???????????????
				CellRangeAddress c22 = new CellRangeAddress(page*20+2, page*20+2, 6, 7);// ???????????????
				CellRangeAddress c3 = new CellRangeAddress(page*20+3, page*20+3, 0, 2);// ???????????????
				CellRangeAddress c31 = new CellRangeAddress(page*20+3, page*20+3, 3, 5);// ???????????????
				CellRangeAddress c32 = new CellRangeAddress(page*20+3, page*20+3, 6, 7);// ???????????????

				CellRangeAddress c4 = new CellRangeAddress(page*20+4, page*20+4, 0, 2);// ???5??? ????????????
				CellRangeAddress c42 = new CellRangeAddress(page*20+4, page*20+4, 3, 5);// ???5???????????????
				CellRangeAddress c43 = new CellRangeAddress(page*20+4, page*20+4, 6, 9);// ???5???????????????
				CellRangeAddress c5 = new CellRangeAddress(page*20+5, page*20+5, 0, 2);// ???6?????????????????????
				CellRangeAddress c52 = new CellRangeAddress(page*20+5, page*20+5, 3, 5);// ???6?????????
				CellRangeAddress c53 = new CellRangeAddress(page*20+5, page*20+5, 6, 9);// ???6?????????
				CellRangeAddress c6 = new CellRangeAddress(page*20+6, page*20+6, 0, 9);// ???7???????????????
//				CellRangeAddress c62 = new CellRangeAddress(page*20+6, page*20+6, 3, 5);// ???7?????????
//				CellRangeAddress c63 = new CellRangeAddress(page*20+6, page*20+6, 6, 9);// ???7?????????
//				CellRangeAddress c7 = new CellRangeAddress(page*20+7, page*20+7, 0, 4);//???7???????????????
//				CellRangeAddress c72 = new CellRangeAddress(page*20+7, page*20+7, 5, 9);//???7???????????????
				sheet.addMergedRegion(c);
				sheet.addMergedRegion(c1);
				sheet.addMergedRegion(c11);
				sheet.addMergedRegion(c12);
				sheet.addMergedRegion(c2);
				sheet.addMergedRegion(c21);
				sheet.addMergedRegion(c22);
				sheet.addMergedRegion(c3);
				sheet.addMergedRegion(c31);
				sheet.addMergedRegion(c32);
				sheet.addMergedRegion(c4);
				sheet.addMergedRegion(c43);
				sheet.addMergedRegion(c5);
				sheet.addMergedRegion(c53);
				sheet.addMergedRegion(c6);
//				sheet.addMergedRegion(c63);
//				sheet.addMergedRegion(c7);
				sheet.addMergedRegion(c42);
				sheet.addMergedRegion(c52);
//				sheet.addMergedRegion(c62);
//				sheet.addMergedRegion(c72);

				Cell cell73 = row.createCell(10);
				cell73.setCellValue("????????????  ????????????  ????????????  ????????????");
				cell73.setCellStyle(cs52);


				CellRangeAddress c73 = new CellRangeAddress(page*20, page*20+16, 10, 10);//???7???????????????
				sheet.addMergedRegion(c73);

				Row rowColumnName = sheet.createRow((short) page*20+7); // ??????
				String[] columnNames = { "??????", "????????????", "????????????", "????????????", "??????","??????", "?????????", "??????/KG","??????/cm??","??????" };
				String[] columnNames2 = { "??????", "????????????", "??????", "??????", "??????","??????", "??????", "??????","??????","??????" };
				try{
					if("hr".equals(ResourceUtil.getConfigByName("wm.ckd"))){
//						String[]  columnNames1 = { "??????", "????????????", "????????????", "????????????", "??????","??????", "?????????", "??????/KG","??????","??????" };
//						String[]  columnNames1 = { "??????", "????????????", "????????????", "????????????", "??????","??????", "?????????", "??????/KG","??????","??????" };
//
//						columnNames = columnNames1;
						columnNames = columnNames2;
					}
				}catch ( Exception e){
					logger.error(ExceptionUtil.getExceptionMessage(e));
				}


				for (int i = 0; i < columnNames.length; i++) {
					Cell cell = rowColumnName.createCell(i);
					cell.setCellValue(columnNames[i]);
					cell.setCellStyle(cs3);
				}


				int cellsNum = page*20+7;
				int oversize = 0;
				if(size==pagesize&&page==pagecount-1){
					oversize = 1;
				}
				for (int i = page*pagesize; i < (page+1)*pagesize+oversize; i++) {
					if(i< size){

						cellsNum++;
						Row rowColumnValue = sheet.createRow((short) cellsNum); // ??????
						rowColumnValue.setHeight((short) 250);

						Cell cell1 = rowColumnValue.createCell(0);
						//cell1.setCellValue(cerconNo);
						cell1.setCellValue(result.get(i).get("goods_id").toString());
						cell1.setCellStyle(cs51);
						Cell cell2 = rowColumnValue.createCell(1);
//						cell2.setCellValue(result.get(i).get("goods_id")
//								.toString());
						cell2.setCellValue(result.get(i).get("shp_ming_cheng").toString());
						cell2.setCellStyle(cs5);

						Cell cell3 = rowColumnValue.createCell(2);
						cell3.setCellValue(result.get(i).get("gao_dan_pin").toString());
//						cell3.setCellValue("0");
						cell3.setCellStyle(cs5);
						try {
							Cell cell4 = rowColumnValue.createCell(3);// ????????????



//								cell4.setCellValue(result.get(i).get("pro_data")
//									.toString());
							cell4.setCellValue("");
							cell4.setCellStyle(cs5r);
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}

						try {
							Cell cell5 = rowColumnValue.createCell(4);// ??????
//							cell5.setCellValue("");

							cell5.setCellValue(result.get(i).get("shp_gui_ge").toString());
							cell5.setCellStyle(cs5);
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}

						try {

							long  xs = (long) Math.floor(Double.parseDouble(result.get(i).get("base_goodscount")
									.toString()));
							sumxs = sumxs  + xs;
							Cell cell6 = rowColumnValue.createCell(5);// ??????
//							cell6.setCellValue(xs);
							cell6.setCellValue(xs);
							cell6.setCellStyle(cs5);

						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}

						try {
//							double bs =						  Double.parseDouble(result.get(i).get("goods_count")
//									.toString()) % Double.parseDouble(result.get(i).get("chl_shl")
//									.toString());
//							sum = sum + bs;
							double zhl = Double.parseDouble(result.get(i).get("tin_zhl")
									.toString());
							sumzl = sumzl + zhl;
							Cell cell7 = rowColumnValue.createCell(6);// ??????
//							cell7.setCellValue(bs);
							cell7.setCellValue(zhl);
							cell7.setCellStyle(cs5);
						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}
						Cell cell8 = rowColumnValue.createCell(7);// ??????
						try {

//							double zhl = Double.parseDouble(result.get(i).get("tin_zhl")
//									.toString()) * Double.parseDouble(result.get(i).get("goods_count").toString());
//							sumzl = sumzl + zhl;
//							cell8.setCellValue(zhl);
							cell8.setCellValue("");

						} catch (Exception e) {
							// TODO: handle exception
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}
						cell8.setCellStyle(cs5);
						Cell cell9 = rowColumnValue.createCell(8);// ??????
//						try {
//							double tij = Double.parseDouble(result.get(i).get("tin_tj")
//									.toString()) * Double.parseDouble(result.get(i).get("goods_count").toString());
//
//
//							cell9.setCellValue(tij);
//
//						} catch (Exception e) {
//							// TODO: handle exception
//						}

						try{
							cell9.setCellValue("?????????");
							if("hr".equals(ResourceUtil.getConfigByName("wm.ckd"))) {
//									cell9.setCellValue(wmUtil.getstock(result.get(i).get("goods_id").toString()));
							}
						}catch (Exception e){
							logger.error(ExceptionUtil.getExceptionMessage(e));
						}


						cell9.setCellStyle(cs5);

						Cell cell10 = rowColumnValue.createCell(9);// ??????


						cell10.setCellStyle(cs5);

						cerconNo++;
					}
					if(i== size){

						cellsNum++;
						Row rowColumnValue = sheet.createRow((short) cellsNum); // ??????
						rowColumnValue.setHeight((short) 250);
						Cell cell5 = rowColumnValue.createCell(5);// ??????
						cell5.setCellValue(Double.toString(sumxs));
						cell5.setCellStyle(cs5);
						Cell cell6 = rowColumnValue.createCell(6);// ??????
						cell6.setCellValue(Double.toString(sumzl));
//						cell6.setCellValue("");
						cell5.setCellStyle(cs5);
						Cell cell7 = rowColumnValue.createCell(7);//
						cell7.setCellValue("");
						cell7.setCellStyle(cs5);
						Cell cell8 = rowColumnValue.createCell(8);//
						cell8.setCellValue("");
						cell8.setCellStyle(cs5);
						Cell cell9 = rowColumnValue.createCell(9);//
						cell9.setCellValue("");
						cell9.setCellStyle(cs5);
//				cell6.setCellStyle(cs5);
						Cell cell0 = rowColumnValue.createCell(0);// ??????
						cell0.setCellValue("?????????");
						cell0.setCellStyle(cs5);
//				cell0.setCellStyle(cs5);
						CellRangeAddress c15 = new CellRangeAddress( cellsNum,
								cellsNum, 0, 4);
						sheet.addMergedRegion(c15);
						setBorderStyle(HSSFCellStyle.BORDER_THIN, c15, sheet, wb);
						cerconNo++;

					}


				}

				Row rowInfo1=sheet.createRow((short) 1+cellsNum);  //???????????????
				Cell cellInfo1 = rowInfo1.createCell(0);
				cellInfo1.setCellValue("?????????");
				cellInfo1.setCellStyle(cs5);
				Cell cellInfo2 = rowInfo1.createCell(1);
				cellInfo2.setCellValue("??????????????????");
				cellInfo2.setCellStyle(cs5);
				Cell cellInfo3 = rowInfo1.createCell(2);
				cellInfo3.setCellValue("???        ???");
				cellInfo3.setCellStyle(cs5);
				Cell cellInfo4 = rowInfo1.createCell(3);
				cellInfo4.setCellValue("??????????????????");
				cellInfo4.setCellStyle(cs5);
				Cell cellInfo5 = rowInfo1.createCell(6);
				cellInfo5.setCellValue("????????????");
				cellInfo5.setCellStyle(cs5);

				Row rowInfo2 =sheet.createRow((short) 2+cellsNum);  //???????????????
				Cell cellInfo21 = rowInfo2.createCell(0);
				cellInfo21.setCellValue("????????????");
				cellInfo21.setCellStyle(cs5);
				Cell cellInfo22 = rowInfo2.createCell(1);
				cellInfo22.setCellValue("");
				cellInfo22.setCellStyle(cs5);

				CellRangeAddress cInfo1 = new CellRangeAddress(1 + cellsNum,
						2 + cellsNum, 3, 5);
				sheet.addMergedRegion(cInfo1);
				setBorderStyle(HSSFCellStyle.BORDER_THIN, cInfo1, sheet, wb);
				CellRangeAddress cInfo2 = new CellRangeAddress(1 + cellsNum,
						2 + cellsNum, 6, 9);
				sheet.addMergedRegion(cInfo2);
				setBorderStyle(HSSFCellStyle.BORDER_THIN, cInfo2, sheet, wb);
				CellRangeAddress cInfo3 = new CellRangeAddress(2 + cellsNum,
						2 + cellsNum, 1, 2);
				sheet.addMergedRegion(cInfo3);
				setBorderStyle(HSSFCellStyle.BORDER_THIN, cInfo3, sheet, wb);


				Row rowInfo3 =sheet.createRow((short) 3+cellsNum);  //???????????????
				rowInfo3.setHeight((short) 1000);
				Cell cellInfo31 = rowInfo3.createCell(0);
				cellInfo31.setCellValue("??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????"+
						"??????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????");
				cellInfo31.setCellStyle(cs2);
				CellRangeAddress cInfo4 = new CellRangeAddress(3 + cellsNum,
						3 + cellsNum, 0, 9);
				sheet.addMergedRegion(cInfo4);



				Row rowColumnInfo = sheet.createRow((short) 4 + cellsNum); // ??????
				rowColumnInfo.setHeight((short) 250);
				rowColumnInfo.createCell(0).setCellValue(
						"  ????????????                                      ????????????                                       ???????????????	");
				CellRangeAddress c15 = new CellRangeAddress(4 + cellsNum,
						4 + cellsNum, 0, 9);
				sheet.addMergedRegion(c15);

				Row rowColumnInfo2 = sheet.createRow((short) 4 + cellsNum); // ??????
				rowColumnInfo2.setHeight((short) 250);
				rowColumnInfo2.createCell(0).setCellValue(
						"  ????????????                       ????????????                        ??????:      	");
				CellRangeAddress c152 = new CellRangeAddress(4 + cellsNum,
						4 + cellsNum, 0, 9);
				sheet.addMergedRegion(c152);

				Row rowColumnInfo3 = sheet.createRow((short) 5 + cellsNum); // ??????
				rowColumnInfo3.setHeight((short) 250);
				rowColumnInfo3.createCell(0).setCellValue(
						"  ???????????????"+ResourceUtil.getConfigByName("comaddr")+"                                           ???????????????                               	");
				CellRangeAddress c153 = new CellRangeAddress(5 + cellsNum,
						5 + cellsNum, 0, 9);
				sheet.addMergedRegion(c153);

				Row rowColumnInfo4 = sheet.createRow((short) 6 + cellsNum); // ??????
				rowColumnInfo4.setHeight((short) 250);
				rowColumnInfo4.createCell(0).setCellValue(
						"  ???????????????                ???????????????                  ?????????              ???????????????              ??????????????? ");
				CellRangeAddress c154 = new CellRangeAddress(6 + cellsNum,
						6 + cellsNum, 0, 9);
				sheet.addMergedRegion(c154);
				page++;
			} while (page<pagecount);
			fileOut = response.getOutputStream();
			HSSFPrintSetup printSetup = sheet.getPrintSetup();
			printSetup.setPaperSize(HSSFPrintSetup.A5_PAPERSIZE);

			wb.write(fileOut);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(ExceptionUtil.getExceptionMessage(e));
		} finally {
			if (fileOut != null) {
				try {
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error(ExceptionUtil.getExceptionMessage(e));
				}
			}
		}
	}

	@RequestMapping(params = "doSelectPrintckd")
	public ModelAndView doSelectPrintckd(String itemId,String id ,HttpServletRequest request) {
		PrintHeader printHeader  = new PrintHeader();
		WmOmNoticeHEntity wmOmNoticeH = systemService.getEntity(WmOmNoticeHEntity.class,
				id);//????????????


		printHeader.setHeader01("?????????????????????");

		printHeader.setHeader02("???????????????");

		printHeader.setHeader03("????????????");

		printHeader.setHeader04("????????? " );


		printHeader.setHeader05("??????????????? " +wmOmNoticeH.getOmNoticeId());

		printHeader.setHeader06("???????????? " +wmOmNoticeH.getImCusCode());


		printHeader.setHeader07("??????????????? " );

		MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmOmNoticeH.getCusCode());

		printHeader.setHeader08("??????????????? " +wmOmNoticeH.getCusCode()+md.getZhongWenQch());

		printHeader.setHeader09("???????????? "+wmOmNoticeH.getDelvMember());

		printHeader.setHeader10("??????????????? " );

		printHeader.setHeader11("??????????????? " +(wmOmNoticeH.getDelvData() == null ?"":DateUtils.date2Str(wmOmNoticeH.getDelvData(), DateUtils.date_sdf)));
		printHeader.setHeader14("??????????????? " +wmOmNoticeH.getDelvAddr());
		printHeader.setHeader15("??????????????? "+(StringUtils.isEmpty(wmOmNoticeH.getDelvMethod())?"":wmOmNoticeH.getDelvMethod()) );
		printHeader.setHeader16("????????? " +wmOmNoticeH.getOmBeizhu());
		printHeader.setHeader17("??????????????? ");
		printHeader.setHeader18("??????????????? "+(StringUtils.isEmpty(wmOmNoticeH.getDelvMethod())?"":wmOmNoticeH.getDelvMethod()) );
		printHeader.setHeader19("?????????????????? ");
		printHeader.setHeader20("?????????????????? "+wmOmNoticeH.getDelvMobile());

		BaStoreEntity baStoreEntity = systemService.findUniqueByProperty(BaStoreEntity.class,"storeCode",wmOmNoticeH.getStoreCode());
		if (baStoreEntity != null){
			printHeader.setHeader21("????????? " +(StringUtils.isEmpty(baStoreEntity.getStoreName())?"":baStoreEntity.getStoreName()));
		}else {
			printHeader.setHeader21("????????? ");
		}


		List<PrintItem> listitem = new ArrayList<>();

		String tsql = "SELECT wq.goods_pro_data as pro_data,mg.sku,wq.base_goodscount,wq.base_unit,mg.gao_dan_pin, mg.goods_code,mg.shp_gui_ge, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,mg.chl_shl,cast(mg.ti_ji_cm/mg.chl_shl as signed) tin_tj ,(mg.zhl_kg/mg.chl_shl ) as tin_zhl  "
				+" FROM wm_to_down_goods wq,mv_goods mg where wq.id in  (?) "
				+" and  wq.goods_id = mg.goods_id group by wq.order_id, mg.goods_code,wq.goods_pro_data";

		List<Map<String, Object>> result = systemService
				.findForJdbc(tsql, itemId);


		int size = result.size();
		if(size<1){
			tsql = "SELECT wq.pro_data,mg.sku,wq.base_unit,wq.base_goodscount,mg.shp_gui_ge,mg.gao_dan_pin, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,mg.chl_shl,cast(mg.ti_ji_cm/mg.chl_shl as signed) tin_tj , (mg.zhl_kg/mg.chl_shl)  as   tin_zhl "
					+" FROM wm_om_qm_i wq,mv_goods mg where wq.om_notice_id = ? "
					+" and  wq.goods_id = mg.goods_id group by wq.om_notice_id, mg.goods_code,wq.pro_data";
			result = systemService
					.findForJdbc(tsql, wmOmNoticeH.getOmNoticeId());
			size = result.size();
		}
		long sumxs=0;
		Double sum =0.00;
		Double	sumzl = 0.00;
		int cerconNo=0;
		for (int i = 0; i < result.size(); i++) {
			PrintItem printItem = new PrintItem();

			cerconNo++;
			printItem.setItem20(Integer.toString(cerconNo));
			printItem.setItem01(result.get(i).get("goods_id")
					.toString());
			printItem.setItem02(result.get(i).get("shp_ming_cheng")
					.toString());
			try {
				printItem.setItem03(result.get(i).get("pro_data")
						.toString());
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}



			try {

				long  xs = (long) Math.floor(Double.parseDouble(result.get(i).get("base_goodscount")
						.toString()));
				sumxs = sumxs  + xs;
				printItem.setItem05(Long.toString(xs));
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}

			try {
				sum = sum + Double.parseDouble(result.get(i).get("goods_count")
						.toString());
				printItem.setItem06(result.get(i).get("goods_count")
						.toString());
			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
			try {

				double zhl = Double.parseDouble(result.get(i).get("tin_zhl")
						.toString()) * Double.parseDouble(result.get(i).get("goods_count").toString());
				sumzl = sumzl + zhl;
				printItem.setItem07("");

			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
			try {
				double tij = Double.parseDouble(result.get(i).get("tin_tj")
						.toString()) * Double.parseDouble(result.get(i).get("goods_count").toString());
				printItem.setItem08(Double.toString(tij));
			} catch (Exception e) {
				// TODO: handle exception
			}
			try{
				if("hr".equals(ResourceUtil.getConfigByName("wm.ckd"))) {
					printItem.setItem09(wmUtil.getstock(result.get(i).get("goods_id").toString()));
				}
			}catch (Exception e){
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
			printItem.setItem10(result.get(i).get("shp_gui_ge")
					.toString());
			try{
				printItem.setItem11(result.get(i).get("gao_dan_pin") == null ?"":result.get(i).get("gao_dan_pin").toString());
			}catch (Exception e){
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
			printItem.setItem12(result.get(i).get("sku") == null ?"":result.get(i).get("sku").toString());
			printItem.setItem13(result.get(i).get("base_unit") == null ?"":result.get(i).get("base_unit").toString());
			listitem.add(printItem);
		}

		printHeader.setHeader12(sum.toString());
		printHeader.setHeader13(sumzl.toString());
		request.setAttribute("printHeader", printHeader);
		request.setAttribute("listitem", listitem);

		return new ModelAndView("com/zzjee/wm/print/imnoticeckd-print");
	}


//??????????????????????????? ???????????????EXCEL

	@RequestMapping(params = "doPrintpageckd")
	public ModelAndView doPrintpageckd(String id,HttpServletRequest request) {
		PrintHeader printHeader  = new PrintHeader();
		WmOmNoticeHEntity wmOmNoticeH = systemService.getEntity(WmOmNoticeHEntity.class,
				id);//????????????


		printHeader.setHeader01(ResourceUtil.getConfigByName("comname")+"?????????");

		printHeader.setHeader02("???????????????"+ResourceUtil.getConfigByName("comaddr") );

		printHeader.setHeader03("?????????"+ ResourceUtil.getConfigByName("comtel"));

		printHeader.setHeader04("??????????????? " +DateUtils.date2Str(wmOmNoticeH.getDelvData(), DateUtils.date_sdf) );


		printHeader.setHeader05("??????????????? " +wmOmNoticeH.getOmNoticeId());

		printHeader.setHeader06("??????????????? " +wmOmNoticeH.getImCusCode());


		printHeader.setHeader07("????????? " +wmOmNoticeH.getReCarno());

		MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmOmNoticeH.getCusCode());

		printHeader.setHeader08("??????????????? " +wmOmNoticeH.getCusCode()+md.getZhongWenQch());

		printHeader.setHeader09("???????????? "+wmOmNoticeH.getDelvMember()+"   ??????:"+wmOmNoticeH.getDelvMobile() );

		printHeader.setHeader10("??????????????? " +wmOmNoticeH.getDelvAddr());

		printHeader.setHeader11("??????????????? "+DateUtils.date2Str(DateUtils.getDate(), DateUtils.datetimeFormat)  );
		printHeader.setHeader14("????????? " +wmOmNoticeH.getOmBeizhu());

		List<PrintItem> listitem = new ArrayList<>();

		String tsql = "SELECT wq.goods_pro_data as pro_data,wq.base_unit, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,mg.chl_shl,cast(mg.ti_ji_cm/mg.chl_shl as signed) tin_tj ,(mg.zhl_kg/mg.chl_shl ) as tin_zhl  "
				+" FROM wm_to_down_goods wq,mv_goods mg where wq.order_id =  ? "
				+" and  wq.goods_id = mg.goods_id group by wq.order_id, mg.goods_code,wq.goods_pro_data";

		List<Map<String, Object>> result = systemService
				.findForJdbc(tsql, wmOmNoticeH.getOmNoticeId());


		int size = result.size();
		if(size<1){
			tsql = "SELECT wq.pro_data,wq.base_unit, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,cast(sum(wq.base_goodscount) as signed) as goods_count,mg.chl_shl,cast(mg.ti_ji_cm/mg.chl_shl as signed) tin_tj , (mg.zhl_kg/mg.chl_shl)  as   tin_zhl "
					+" FROM wm_om_qm_i wq,mv_goods mg where wq.om_notice_id = ? "
					+" and  wq.goods_id = mg.goods_id group by wq.om_notice_id, mg.goods_code,wq.pro_data";
			result = systemService
					.findForJdbc(tsql, wmOmNoticeH.getOmNoticeId());
			size = result.size();
		}
       long sumxs=0;
		Double sum =0.00;
		Double	sumzl = 0.00;
		int cerconNo=0;
		for (int i = 0; i < result.size(); i++) {
			PrintItem printItem = new PrintItem();

			cerconNo++;
			printItem.setItem20(Integer.toString(cerconNo));
			printItem.setItem01(result.get(i).get("goods_id")
					.toString());
			printItem.setItem02(result.get(i).get("shp_ming_cheng")
					.toString());
 			try {
				printItem.setItem03(result.get(i).get("pro_data")
						.toString());
 			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}



			try {

				long  xs = (long) Math.floor(Double.parseDouble(result.get(i).get("goods_count")
						.toString()) / Double.parseDouble(result.get(i).get("chl_shl")
						.toString()));
				sumxs = sumxs  + xs;
 				printItem.setItem05(Long.toString(xs));
 			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}

			try {
				sum = sum + Double.parseDouble(result.get(i).get("goods_count")
						.toString());
				printItem.setItem06(result.get(i).get("goods_count")
						.toString());
 			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
 			try {

				double zhl = Double.parseDouble(result.get(i).get("tin_zhl")
						.toString()) * Double.parseDouble(result.get(i).get("goods_count").toString());
				sumzl = sumzl + zhl;
				printItem.setItem07(Double.toString(zhl));

			} catch (Exception e) {
				// TODO: handle exception
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}
  						try {
							double tij = Double.parseDouble(result.get(i).get("tin_tj")
									.toString()) * Double.parseDouble(result.get(i).get("goods_count").toString());
							printItem.setItem08(Double.toString(tij));
						} catch (Exception e) {
							// TODO: handle exception
						}
			try{
				if("hr".equals(ResourceUtil.getConfigByName("wm.ckd"))) {
					printItem.setItem09(wmUtil.getstock(result.get(i).get("goods_id").toString()));
				}
			}catch (Exception e){
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}


			listitem.add(printItem);
		}

		printHeader.setHeader12(sum.toString());
		printHeader.setHeader13(sumzl.toString());
		request.setAttribute("printHeader", printHeader);
		request.setAttribute("listitem", listitem);

		return new ModelAndView("com/zzjee/wm/print/imnoticeckd-print");
	}


//??????????????????????????????????????????EXCEL

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "doUpdate")
	@ResponseBody
	public AjaxJson doUpdate(WmOmNoticeHEntity wmOmNoticeH,WmOmNoticeHPage wmOmNoticeHPage, HttpServletRequest request) {
		List<WmOmNoticeIEntity> wmOmNoticeIList =  wmOmNoticeHPage.getWmOmNoticeIList();
		List<TmsYwDingdanEntity> wmOmtmsIList =   wmOmNoticeHPage.getWmOmtmsIList();
		AjaxJson j = new AjaxJson();
		String message = "????????????";
		try{

			if(wmOmNoticeH.getCusCode()==null){
				TSUser user = ResourceUtil.getSessionUserName();
				String roles = "";
				if (user != null) {
					List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
					for (TSRoleUser ru : rUsers) {
						TSRole role = ru.getTSRole();
						roles += role.getRoleCode() + ",";
					}
					if (roles.length() > 0) {
						roles = roles.substring(0, roles.length() - 1);
					}
					if(roles.equals("CUS")){
						wmOmNoticeH.setCusCode(user.getUserName());

					}
				}
			}


			wmOmNoticeHService.updateMain(wmOmNoticeH, wmOmNoticeIList,wmOmtmsIList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		}catch(Exception e){
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
	public ModelAndView goAdd(WmOmNoticeHEntity wmOmNoticeH, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wmOmNoticeH.getId())) {
			wmOmNoticeH = wmOmNoticeHService.getEntity(WmOmNoticeHEntity.class, wmOmNoticeH.getId());
			req.setAttribute("wmOmNoticeHPage", wmOmNoticeH);
		}else{
			wmOmNoticeH.setOrderTypeCode(req.getParameter("orderTypeCode").toString());

			TSUser user = ResourceUtil.getSessionUserName();
			String roles = "";
			if (user != null) {
				List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
				for (TSRoleUser ru : rUsers) {
					TSRole role = ru.getTSRole();
					roles += role.getRoleCode() + ",";
				}
				if (roles.length() > 0) {
					roles = roles.substring(0, roles.length() - 1);
				}
				if(roles.equals("CUS")){
					wmOmNoticeH.setCusCode(user.getUserName());
					wmOmNoticeH.setReadonly("readonly");
					wmOmNoticeH.setWherecon("where cus_code = '"+user.getUserName()+"'");
					req.setAttribute("wmOmNoticeHPage", wmOmNoticeH);

				}else{
					if(!StringUtil.isEmpty( wmOmNoticeH.getCusCode())){
						wmOmNoticeH.setWherecon("where cus_code = '"+wmOmNoticeH.getCusCode()+"'");
					}else{
						wmOmNoticeH.setWherecon("where 1 = 1");
					}
					req.setAttribute("wmOmNoticeHPage", wmOmNoticeH);
				}
			}

		}
		return new ModelAndView("com/zzjee/wm/wmOmNoticeH-add");
	}

	/**
	 * ??????????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "goUpdate")
	public ModelAndView goUpdate(WmOmNoticeHEntity wmOmNoticeH, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wmOmNoticeH.getId())) {
			wmOmNoticeH = wmOmNoticeHService.getEntity(WmOmNoticeHEntity.class, wmOmNoticeH.getId());
			TSUser user = ResourceUtil.getSessionUserName();
			String roles = "";
			if (user != null) {
				List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
				for (TSRoleUser ru : rUsers) {
					TSRole role = ru.getTSRole();
					roles += role.getRoleCode() + ",";
				}
				if (roles.length() > 0) {
					roles = roles.substring(0, roles.length() - 1);
				}
				if(roles.equals("CUS")){
					wmOmNoticeH.setCusCode(user.getUserName());
					wmOmNoticeH.setReadonly("readonly");
					wmOmNoticeH.setWherecon("where cus_code = '"+user.getUserName()+"'");
				}
			}
			req.setAttribute("wmOmNoticeHPage", wmOmNoticeH);
		}
		return new ModelAndView("com/zzjee/wm/wmOmNoticeH-update");
	}


	/**
	 * ??????????????????[??????????????????]
	 *
	 * @return
	 */
	@RequestMapping(params = "wmOmNoticeIList")
	public ModelAndView wmOmNoticeIList(WmOmNoticeHEntity wmOmNoticeH, HttpServletRequest req) {

		//===================================================================================
		//????????????
		Object id0 = wmOmNoticeH.getOmNoticeId();
		//===================================================================================
		//??????-??????????????????
		String hql0 = "from WmOmNoticeIEntity where 1 = 1 AND oM_NOTICE_ID = ? ";
		try{
			if(StringUtil.isNotEmpty(id0)){
				List<WmOmNoticeIEntity> wmOmNoticeIEntityList = systemService.findHql(hql0,id0);
				req.setAttribute("wmOmNoticeIList", wmOmNoticeIEntityList);
			}
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zzjee/wm/wmOmNoticeIList");
	}

	/**
	 * ??????????????????[??????????????????]
	 *
	 * @return
	 */
	@RequestMapping(params = "wmOmtmsIList")
	public ModelAndView wmOmtmsIList(WmOmNoticeHEntity wmOmNoticeH, HttpServletRequest req) {

		//===================================================================================
		//????????????
		Object id0 = wmOmNoticeH.getOmNoticeId();
		//===================================================================================
		//??????-??????????????????
		String hql0 = "from TmsYwDingdanEntity where 1 = 1 AND ywkhdh = ? ";
		try{
			if(StringUtil.isNotEmpty(id0)){
				List<TmsYwDingdanEntity> wmOmtmsIEntityList = systemService.findHql(hql0,id0);
				req.setAttribute("wmOmtmsIList", wmOmtmsIEntityList);
			}
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/zzjee/wm/wmOmtmsIList");
	}




	/**
	 * ??????excel
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public String exportXls(WmOmNoticeHEntity wmOmNoticeH,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid,ModelMap map) {
		CriteriaQuery cq = new CriteriaQuery(WmOmNoticeHEntity.class, dataGrid);
		//?????????????????????
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, wmOmNoticeH);
		try{

			TSUser user = ResourceUtil.getSessionUserName();
			String roles = "";
			if (user != null) {
				List<TSRoleUser> rUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
				for (TSRoleUser ru : rUsers) {
					TSRole role = ru.getTSRole();
					roles += role.getRoleCode() + ",";
				}
				if (roles.length() > 0) {
					roles = roles.substring(0, roles.length() - 1);
				}
				if(roles.equals("CUS")){
					cq.eq("cusCode", user.getUserName());
				}
			}

			//???????????????????????????
		}catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
		cq.add();
		List<WmOmNoticeHEntity> list=this.wmOmNoticeHService.getListByCriteriaQuery(cq, false);
		List<WmOmNoticeHPage> pageList=new ArrayList<WmOmNoticeHPage>();
		if(list!=null&&list.size()>0){
			for(WmOmNoticeHEntity entity:list){
				try{
					WmOmNoticeHPage page=new WmOmNoticeHPage();
					MyBeanUtils.copyBeanNotNull2Bean(entity,page);
					Object id0 = entity.getOmNoticeId();
					String hql0 = "from WmOmNoticeIEntity where 1 = 1 AND oM_NOTICE_ID = ? ";
					List<WmOmNoticeIEntity> wmOmNoticeIEntityList = systemService.findHql(hql0,id0);
					page.setWmOmNoticeIList(wmOmNoticeIEntityList);
					pageList.add(page);
				}catch(Exception e){
					logger.info(e.getMessage());
				}
			}
		}
		map.put(NormalExcelConstants.FILE_NAME,"????????????");
		map.put(NormalExcelConstants.CLASS,WmOmNoticeHPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("??????????????????", "?????????:admin",
				"????????????"));
		map.put(NormalExcelConstants.DATA_LIST,pageList);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * ??????excel????????????
	 * @param request
	 * @param
	 * @return
	 */
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
				List<WmOmNoticeImpnewPage> list =  ExcelImportUtil.importExcel(file.getInputStream(), WmOmNoticeImpnewPage.class, params);

				String flag = "Y";
				String message="";
				for(WmOmNoticeImpnewPage wmt:list){
					MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
							MvGoodsEntity.class, "goodsCode", wmt.getGoodsId());
					if(mvgoods==null){
						flag = "N";
						message=message+wmt.getGoodsId();
					}
				}
				if("N".equals(flag)){
					j.setMsg(message+"?????????");
					return j;
				}
				List<WmOmNoticeImpnewPage> listheader =  ExcelImportUtil.importExcel(
						file.getInputStream(), WmOmNoticeImpnewPage.class, params);
				for(int i=0;i<listheader.size()-1;i++){
					for(int  k=listheader.size()-1;k>i;k--){
						if(listheader.get(k).getImCusCode().equals(listheader.get(i).getImCusCode()))  {
							listheader.remove(k);
						}
					}
				}
				for(WmOmNoticeImpnewPage pageheader: listheader) {
//					List<WmOmNoticeHEntity>  wmomh = systemService.findByProperty(WmOmNoticeHEntity.class, "imCusCode", pageheader.getImCusCode());
//					if(wmomh!=null&&wmomh.size()>0){
//						continue;
//					}

					List<WmOmNoticeIEntity> wmomNoticeIListnew = new ArrayList<WmOmNoticeIEntity>();
					for (WmOmNoticeImpnewPage page : list) {
						if(pageheader.getImCusCode().equals(page.getImCusCode())) {
							WmOmNoticeIEntity wmi = new WmOmNoticeIEntity();
							wmi.setGoodsId(page.getGoodsId());
							MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
									MvGoodsEntity.class, "goodsCode", wmi.getGoodsId());
							if (mvgoods != null) {
								wmi.setGoodsName(mvgoods.getGoodsName());
								wmi.setGoodsUnit(mvgoods.getShlDanWei());
							}
							try{
								wmi.setGoodsQua(page.getGoodsQua());
								String[] args=page.getGoodsQua().split("\\.");
								wmi.setGoodsQua(args[0]);
							}catch (Exception e){

							}

//                               wmi.setGoodsPrdData(billResult.getData().get(s).getDetail().get(k).getPdProdmadedate2User());
							wmi.setOtherId(page.getOtherId());
							wmi.setBinId(page.getBinId());
							wmi.setBinOm(page.getBinOm());
							if(StringUtil.isNotEmpty(page.getGoodsProData())){
								wmi.setGoodsProData(DateUtils.str2Date(page.getGoodsProData(),DateUtils.date_sdf));
							}
							wmomNoticeIListnew.add(wmi);
						}
					}
					WmOmNoticeHEntity wmOmNoticeH = new WmOmNoticeHEntity();
					wmOmNoticeH.setReMember(pageheader.getReMember());
					wmOmNoticeH.setReCarno(pageheader.getReCarno());
					wmOmNoticeH.setDelvMember(pageheader.getDelvMember());
					wmOmNoticeH.setDelvMobile(pageheader.getDelvMobile());
					wmOmNoticeH.setDelvData(pageheader.getImData());
					wmOmNoticeH.setOrderTypeCode(pageheader.getOrderTypeCode());
					wmOmNoticeH.setCusCode(pageheader.getCusCode());
					String noticeid = wmUtil.getNextomNoticeId(wmOmNoticeH.getOrderTypeCode());
					wmOmNoticeH.setOmNoticeId(noticeid);
					wmOmNoticeH.setOmBeizhu(pageheader.getImBeizhu());
					wmOmNoticeH.setOcusCode(pageheader.getOcusCode());
					wmOmNoticeH.setDelvAddr(pageheader.getDelvAddr());
					MdCusOtherEntity mdcusother = systemService.findUniqueByProperty(MdCusOtherEntity.class, "keHuBianMa", wmOmNoticeH.getOcusCode());
					if (mdcusother != null) {
						wmOmNoticeH.setOcusName(mdcusother.getZhongWenQch());
					}
					wmOmNoticeH.setImCusCode(pageheader.getImCusCode());
					wmOmNoticeHService.addMain(wmOmNoticeH, wmomNoticeIListnew);
				}
				//
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


	/**
	 * ??????excel????????????
	 * @param request
	 * @param
	 * @return
	 */
	@RequestMapping(params = "importExcel2", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel2(HttpServletRequest request, HttpServletResponse response) {
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
				List<WmOmNoticeImpPage> listfirst =  ExcelImportUtil.importExcel(file.getInputStream(), WmOmNoticeImpPage.class, params);
				List<WmNoticeImpPage> list =  new ArrayList<WmNoticeImpPage>();
				List<WmNoticeImpPage> listheader = new ArrayList<WmNoticeImpPage>();
				for(WmOmNoticeImpPage t:listfirst){
					WmNoticeImpPage wmNoticeImpPage = new WmNoticeImpPage();
					wmNoticeImpPage.setOrderTypeCode("11");
					wmNoticeImpPage.setImCusCode(t.getImCusCode());//????????????
					wmNoticeImpPage.setImBeizhu(t.getImBeizhu());//??????
					try{
						wmNoticeImpPage.setGoodsId(wmUtil.getGoodsId(t.getCusCode(),t.getGoodsId(),t.getGoodsUnit()).get("goodsCode"));
//						wmNoticeImpPage.setCusCode(wmUtil.getGoodsId(t.getGoodsId(),t.getGoodsUnit()).get("cusCode"));
					}catch (Exception e){

					}
					wmNoticeImpPage.setCusCode(t.getCusCode());//??????
					wmNoticeImpPage.setGoodsName(t.getGoodsName());//????????????
					wmNoticeImpPage.setGoodsQua(t.getGoodsQua());//??????
					wmNoticeImpPage.setGoodsUnit(t.getGoodsUnit());//??????
					wmNoticeImpPage.setOcusCode(t.getOcusCode());//??????????????????
					wmNoticeImpPage.setOcusName(t.getOcusName());//??????????????????
					list.add(wmNoticeImpPage);
					listheader.add(wmNoticeImpPage);
				}
//?????????????????????

				String flag = "Y";
				String message="";
				if(!"yes".equals(ResourceUtil.getConfigByName("nocheck"))){
					for(WmNoticeImpPage wmt:list){
						MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
								MvGoodsEntity.class, "goodsCode", wmt.getGoodsId());
						if(mvgoods==null){
							flag = "N";
							message=message+wmt.getGoodsName();
						}
					}
					if("N".equals(flag)){
						j.setMsg(message+"?????????");
						return j;
					}

				}

				for(int i=0;i<listheader.size()-1;i++){
					for(int  k=listheader.size()-1;k>i;k--){
						if(listheader.get(k).getImCusCode().equals(listheader.get(i).getImCusCode()))  {
							listheader.remove(k);
						}
					}
				}
				for(WmNoticeImpPage pageheader: listheader) {
//					List<WmOmNoticeHEntity>  wmomh = systemService.findByProperty(WmOmNoticeHEntity.class, "imCusCode", pageheader.getImCusCode());
//					if(wmomh!=null&&wmomh.size()>0){
//						continue;
//					}

					List<WmOmNoticeIEntity> wmomNoticeIListnew = new ArrayList<WmOmNoticeIEntity>();
					for (WmNoticeImpPage page : list) {
						if(pageheader.getImCusCode().equals(page.getImCusCode())) {
							WmOmNoticeIEntity wmi = new WmOmNoticeIEntity();
							wmi.setGoodsId(page.getGoodsId());
							MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
									MvGoodsEntity.class, "goodsCode", wmi.getGoodsId());
							if (mvgoods != null) {
								wmi.setGoodsName(mvgoods.getGoodsName());
								wmi.setGoodsUnit(mvgoods.getShlDanWei());
							}
							try{
								wmi.setGoodsQua(page.getGoodsQua());
								String[] args=page.getGoodsQua().split("\\.");
								wmi.setGoodsQua(args[0]);
							}catch (Exception e){

							}

//                               wmi.setGoodsPrdData(billResult.getData().get(s).getDetail().get(k).getPdProdmadedate2User());
							wmi.setOtherId(page.getOtherId());
							wmi.setBinOm(page.getBinOm());
							wmi.setBinId(page.getBinId());
							if(StringUtil.isNotEmpty(page.getGoodsProData())){
								wmi.setGoodsProData(DateUtils.str2Date(page.getGoodsProData(),DateUtils.date_sdf));
							}
							wmomNoticeIListnew.add(wmi);
						}
					}
					WmOmNoticeHEntity wmOmNoticeH = new WmOmNoticeHEntity();

					wmOmNoticeH.setDelvData(pageheader.getImData());
					wmOmNoticeH.setOrderTypeCode(pageheader.getOrderTypeCode());
					wmOmNoticeH.setCusCode(pageheader.getCusCode());
					String noticeid = wmUtil.getNextomNoticeId(wmOmNoticeH.getOrderTypeCode());
					wmOmNoticeH.setOmNoticeId(noticeid);
					wmOmNoticeH.setOmBeizhu(pageheader.getImBeizhu());
					wmOmNoticeH.setOcusCode(pageheader.getOcusCode());
					wmOmNoticeH.setDelvAddr(pageheader.getDelvAddr());
					MdCusOtherEntity mdcusother = systemService.findUniqueByProperty(MdCusOtherEntity.class, "keHuBianMa", wmOmNoticeH.getOcusCode());
					if (mdcusother != null) {
						wmOmNoticeH.setOcusName(mdcusother.getZhongWenQch());
					}
					wmOmNoticeH.setImCusCode(pageheader.getImCusCode());
					wmOmNoticeHService.addMain(wmOmNoticeH, wmomNoticeIListnew);
				}
				//
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
	/**
	 * ??????excel ?????????
	 */
	@RequestMapping(params = "exportXlsByT")
	public String exportXlsByT(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"????????????");
		map.put(NormalExcelConstants.CLASS,WmOmNoticeImpnewPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("????????????", "?????????:"+ ResourceUtil.getSessionUserName().getRealName(),
				"????????????"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * ??????excel ?????????
	 */
	@RequestMapping(params = "exportXlsByT2")
	public String exportXlsByT2(ModelMap map) {
		map.put(NormalExcelConstants.FILE_NAME,"????????????");
		map.put(NormalExcelConstants.CLASS,WmOmNoticeImpPage.class);
		map.put(NormalExcelConstants.PARAMS,new ExportParams("????????????", "?????????:"+ ResourceUtil.getSessionUserName().getRealName(),
				"????????????"));
		map.put(NormalExcelConstants.DATA_LIST,new ArrayList());
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		req.setAttribute("controller_name", "wmOmNoticeHController");
		return new ModelAndView("common/upload/pub_excel_upload");
	}

	/**
	 * ??????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "upload2")
	public ModelAndView upload2(HttpServletRequest req) {
		req.setAttribute("controller_name", "wmOmNoticeHController");
		return new ModelAndView("common/upload/pub_excel_upload2");
	}






	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<WmOmNoticeHEntity> list() {
		List<WmOmNoticeHEntity> listWmOmNoticeHs=wmOmNoticeHService.getList(WmOmNoticeHEntity.class);
		return listWmOmNoticeHs;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		WmOmNoticeHEntity task = wmOmNoticeHService.get(WmOmNoticeHEntity.class, id);
		if (task == null) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(task, HttpStatus.OK);
	}

	@RequestMapping(value = "/apicreate")
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody WmOmNoticeHPage wmOmNoticeHPage ) {
		//??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		ResultDO D0 = new  ResultDO();
		//??????
		List<WmOmNoticeIEntity> wmOmNoticeIList =  wmOmNoticeHPage.getWmOmNoticeIList();
		String noticeid = wmUtil.getNextomNoticeId(wmOmNoticeHPage.getOrderTypeCode());
		wmOmNoticeHPage.setOmNoticeId(noticeid);
		WmOmNoticeHEntity wmOmNoticeH = new WmOmNoticeHEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(wmOmNoticeHPage,wmOmNoticeH);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		List<WmOmNoticeIEntity> wmOmNoticeIListnew = new ArrayList<>();
		for(WmOmNoticeIEntity t: wmOmNoticeIList){
			try{
				MdGoodsEntity md =systemService.findUniqueByProperty(MdGoodsEntity.class,"shpBianMa",t.getGoodsId());
				t.setCusCode(md.getSuoShuKeHu());
			}catch ( Exception e){

			}

			wmOmNoticeIListnew.add(t);
	   }
		wmOmNoticeHService.addMain(wmOmNoticeH, wmOmNoticeIListnew);
		D0.setOK(true);
		//??????Restful???????????????????????????????????????url, ?????????????????????id?????????.
		return new ResponseEntity(D0, HttpStatus.OK);

	}


	@RequestMapping(value = "/apicreatetms")
	@ResponseBody
	public ResponseEntity<?> createtms(@RequestBody WmTmsNoticeHPage wmOmNoticeHPage ) {
		//??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		ResultDO D0 = new  ResultDO();
		//??????
		List<WmTmsNoticeIEntity> wmOmNoticeIList =  wmOmNoticeHPage.getWmOmNoticeIList();
		String noticeid = wmUtil.getNextomNoticeIdtms(wmOmNoticeHPage.getOrderTypeCode());
		wmOmNoticeHPage.setOmNoticeId(noticeid);
		WmTmsNoticeHEntity wmOmNoticeH = new WmTmsNoticeHEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(wmOmNoticeHPage,wmOmNoticeH);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		List<WmTmsNoticeIEntity> wmOmNoticeIListnew = new ArrayList<>();
		for(WmTmsNoticeIEntity t: wmOmNoticeIList){
			try{
				MdGoodsEntity md =systemService.findUniqueByProperty(MdGoodsEntity.class,"shpBianMa",t.getGoodsId());
				t.setCusCode(md.getSuoShuKeHu());
				wmOmNoticeH.setOmSta("?????????");
				wmOmNoticeH.setCreateDate(now());
				wmOmNoticeH.setCusCode(md.getSuoShuKeHu());
			}catch ( Exception e){

			}

			wmOmNoticeIListnew.add(t);
		}
		try{
			Thread.sleep(1000);

		}catch (Exception e){

		}
		wmOmNoticeHService.addMaintms(wmOmNoticeH, wmOmNoticeIListnew);

		D0.setOK(true);
		//??????Restful???????????????????????????????????????url, ?????????????????????id?????????.
		return new ResponseEntity(D0, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody WmOmNoticeHPage wmOmNoticeHPage) {
		//??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
		Set<ConstraintViolation<WmOmNoticeHPage>> failures = validator.validate(wmOmNoticeHPage);
		if (!failures.isEmpty()) {
			return new ResponseEntity(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}

		//??????
		List<WmOmNoticeIEntity> wmOmNoticeIList =  wmOmNoticeHPage.getWmOmNoticeIList();
		List<TmsYwDingdanEntity> wmOmtmsIList =  wmOmNoticeHPage.getWmOmtmsIList();

		WmOmNoticeHEntity wmOmNoticeH = new WmOmNoticeHEntity();
		try{
			MyBeanUtils.copyBeanNotNull2Bean(wmOmNoticeH,wmOmNoticeHPage);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		wmOmNoticeHService.updateMain(wmOmNoticeH, wmOmNoticeIList,wmOmtmsIList);

		//???Restful???????????????204?????????, ?????????. ???????????????200?????????.
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") String id) {
		WmOmNoticeHEntity wmOmNoticeH = wmOmNoticeHService.get(WmOmNoticeHEntity.class, id);
		wmOmNoticeHService.delMain(wmOmNoticeH);
	}



	@RequestMapping(value = "/list/hehuo",  method = RequestMethod.GET)   //?????????
	@ResponseBody
	public ResponseEntity<?> list(@RequestParam(value="username", required=false) String username,
								  @RequestParam(value="searchstr", required=false)String searchstr,
								  @RequestParam(value="searchstr2", required=false)String searchstr2) {
		ResultDO D0 = new  ResultDO();
		D0.setOK(true);

		String hql="from WmOmNoticeHEntity  ";

		List<WmOmNoticeHEntity> listWaveToDowns =new ArrayList<>();
 		List<WmOmNoticeHEntity> listWaveToDownsnew =new ArrayList<>();
		if(StringUtil.isNotEmpty(searchstr)&&!"null".equals(searchstr)){
			hql="from WmOmNoticeHEntity where  omSta <> ? and  reMember = ? and  omNoticeId = ?";
			listWaveToDowns = wmOmNoticeHService.findHql(hql,"????????????",username,searchstr);
		}else{
			hql="from WmOmNoticeHEntity where omSta <> ? and   reMember = ? ";
			listWaveToDowns = wmOmNoticeHService.findHql(hql,"????????????",username);

		}
//		listWaveToDowns = wmOmNoticeHService.findHql(hql);
		System.out.println("/list/hehuolistWaveToDowns==="+listWaveToDowns.toString()+listWaveToDowns.size());

		for(WmOmNoticeHEntity t: listWaveToDowns){
			List<WmOmNoticeIEntity> listWaveToDowndetial =new ArrayList<>();
			hql="from WmOmNoticeIEntity where   omNoticeId = ?";
			listWaveToDowndetial = wmOmNoticeHService.findHql(hql, t.getOmNoticeId());
			try{
				double sumcount =0.00;
				for(WmOmNoticeIEntity dt: listWaveToDowndetial){
					sumcount = sumcount + Double.parseDouble(dt.getBaseGoodscount());
				}

				t.setOmBeizhu(Integer.toString((int) Math.round(sumcount)) );
			}catch (Exception e){

			}
            //
			String hqlrongqi = "from WmOmQmIEntity where omNoticeId = ?";
			List<WmOmQmIEntity> listom =new ArrayList<>();
			listom = wmOmNoticeHService.findHql(hqlrongqi, t.getOmNoticeId());
			boolean isshow = true;
			for(WmOmQmIEntity tom: listom ){
				if("H".equals(tom.getBinSta())){
					isshow = false;
				}

			}
			try{
				if(isshow){
					t.setOmPlatNo(listom.get(0).getSecondRq());
				}else{
					t.setOmPlatNo("???????????????");
				continue;//??????????????????????????????
				}
			}catch (Exception e){

			}
			for(WmOmNoticeIEntity dt2: listWaveToDowndetial){
				if("????????????".equals(dt2.getOmSta())){
					continue;
				}
				WmOmNoticeHEntity tout = new WmOmNoticeHEntity();
				try{
					MyBeanUtils.copyBean2Bean(tout,t);

				}catch (Exception e){

				}
				tout.setId(dt2.getId());//??????ID
				System.out.println("searchstr2===="+searchstr2);
				String  keyword = "";
				try {
					  keyword = new String(searchstr2.getBytes("iso-8859-1"), "utf-8");
					System.out.println("keyword===="+keyword);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				if(StringUtil.isNotEmpty(keyword)){
					try{
						if (!(StringUtil.strPos(dt2.getChpShuXing(), keyword))) {
							System.out.println("dt2.getChpShuXing()===="+dt2.getChpShuXing());

							continue;
						}
					}catch (Exception e){

					}
				}
				tout.setPiMaster( dt2.getGoodsName());
//				tout.setPiClass(dt2.getBaseGoodscount()+ "dt2.getBaseUnit()");
				tout.setPiClass( (int) Math.round(Double.parseDouble(dt2.getBaseGoodscount()))  + "???");
				listWaveToDownsnew.add(tout);
 			}
 		}
		D0.setObj(listWaveToDownsnew);


		return new ResponseEntity(D0, HttpStatus.OK);
	}
	@RequestMapping(value = "/listdetail/hehuo",  method = RequestMethod.GET)//
	@ResponseBody
	public ResponseEntity<?> listdetail(
								  @RequestParam(value="omnoticeid", required=false)String omnoticeid) {
		ResultDO D0 = new  ResultDO();
		D0.setOK(true);
		System.out.println("/list/hehuo/omNoticeId"+omnoticeid );

		String hql="from WmOmNoticeIEntity where omNoticeId = ? ";
		List<WmOmNoticeIEntity> listWaveToDowns =new ArrayList<>();
//			hql="from WmImNoticeIEntity where  noticeiSta <> ? and  omNoticeId = ?";
//			listWaveToDowns = wmOmNoticeHService.findHql(hql,"?????????",searchstr);
//			hql="from WmOmNoticeIEntity ";
			hql="from WmOmNoticeIEntity where   id = ?  order by  chpShuXing";
//			listWaveToDowns = wmOmNoticeHService.findHql(hql);
			listWaveToDowns = wmOmNoticeHService.findHql(hql, omnoticeid);
		D0.setObj(listWaveToDowns);
		System.out.println("/listdetail/hehuolistWaveToDowns==="+listWaveToDowns.toString()+listWaveToDowns.size());

		return new ResponseEntity(D0, HttpStatus.OK);
	}
	@RequestMapping(value = "/update/hehuo",  method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> doupdate(
			@RequestParam(value="id", required=false)String id) {
		ResultDO D0 = new  ResultDO();
		D0.setOK(true);
		String hql="from WmImNoticeIEntity where id = ? ";
		List<WmOmNoticeIEntity> listWaveToDowns =new ArrayList<>();
		hql=" from WmOmNoticeIEntity where   id = ?";
		listWaveToDowns = wmOmNoticeHService.findHql(hql,id);
		if(listWaveToDowns!=null&&listWaveToDowns.size()>0){
			for(WmOmNoticeIEntity tom: listWaveToDowns){
				tom.setOmSta("????????????");
				systemService.updateEntitie(tom);

			}
			List<WmOmNoticeIEntity> listWaveToDownsafter =new ArrayList<>();
			hql="from WmOmNoticeIEntity where   omNoticeId = ? and omSta <> ? order by  chpShuXing";
//			listWaveToDowns = wmOmNoticeHService.findHql(hql);
			listWaveToDownsafter = wmOmNoticeHService.findHql(hql, listWaveToDowns.get(0).getOmNoticeId(),"????????????");
			if(listWaveToDownsafter==null||(listWaveToDownsafter!=null&&listWaveToDownsafter.size()==0)){
				String noticeid = listWaveToDowns.get(0).getOmNoticeId();
				String hqlh=" from WmOmNoticeHEntity where omNoticeId = ? ";
				List<WmOmNoticeHEntity> listWaveToDowns11 = wmOmNoticeHService.findHql(hqlh,noticeid);

				for(WmOmNoticeHEntity t: listWaveToDowns11){
					t.setOmSta("????????????");
					wmOmNoticeHService.updateEntitie(t);
					updatetms(t.getPiClass());

				}
			}

		}else{
			String hql1=" from WmOmNoticeHEntity where omNoticeId = ? ";
			List<WmOmNoticeHEntity> listWaveToDowns11 = wmOmNoticeHService.findHql(hql1,id);
			for(WmOmNoticeHEntity t: listWaveToDowns11){
				t.setOmSta("????????????");
				wmOmNoticeHService.updateEntitie(t);
				updatetms(t.getPiClass());

			}

			String hql2=" from WmOmNoticeHEntity where id = ? ";
			List<WmOmNoticeHEntity> listWaveToDowns12 = wmOmNoticeHService.findHql(hql2,id);
			for(WmOmNoticeHEntity t: listWaveToDowns12){
				t.setOmSta("????????????");
				wmOmNoticeHService.updateEntitie(t);
				updatetms(t.getPiClass());
			}

		}

		D0.setObj(listWaveToDowns);
		return new ResponseEntity(D0, HttpStatus.OK);
	}


	private  void updatetms(String sfordeid){
		for(String ombeizhu:sfordeid.split(";")){
			try{
				WmTmsNoticeHEntity wmsom = systemService.findUniqueByProperty(WmTmsNoticeHEntity.class,"omBeizhu",ombeizhu);
				if(wmsom!=null){
					wmsom.setOmSta("????????????");
					systemService.updateEntitie(wmsom);
				}

			}catch (Exception e){

			}

		}

	}

	public void setBorderStyle(int border,CellRangeAddress region,HSSFSheet sheet,HSSFWorkbook wb){
		RegionUtil.setBorderBottom(border,region, sheet, wb);
		RegionUtil.setBorderLeft(border,region, sheet, wb);
		RegionUtil.setBorderRight(border,region, sheet, wb);
		RegionUtil.setBorderTop(border,region, sheet, wb);
	}
}
