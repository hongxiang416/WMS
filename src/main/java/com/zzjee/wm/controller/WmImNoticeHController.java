package com.zzjee.wm.controller;

import com.zzjee.api.ResultDO;
import com.zzjee.ba.entity.BaStoreEntity;
import com.zzjee.md.entity.MdCusEntity;
import com.zzjee.md.entity.MdGoodsEntity;
import com.zzjee.md.entity.MdSupEntity;
import com.zzjee.md.entity.MvGoodsEntity;
import com.zzjee.wm.entity.*;
import com.zzjee.wm.page.WmImNoticeHPage;
import com.zzjee.wm.page.WmNoticeImpPage;
import com.zzjee.wm.service.WmImNoticeHServiceI;
import com.zzjee.wmutil.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.sms.util.Constants;
import org.jeecgframework.web.system.sms.util.TuiSongMsgUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Title: Controller
 * @Description: ??????????????????
 * @author erzhongxmu
 * @date 2017-08-15 23:17:33
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/wmImNoticeHController")
public class WmImNoticeHController extends BaseController {
    /**
     * Logger for this class
     */
    private static final Logger logger = Logger
            .getLogger(WmImNoticeHController.class);

    @Autowired
    private WmImNoticeHServiceI wmImNoticeHService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private Validator validator;

    /**
     * ???????????????????????? ????????????
     *
     * @return
     */
    @RequestMapping(params = "list")
    public ModelAndView list(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wm/wmImNoticeHList");
    }
    @RequestMapping(params = "listqt")
    public ModelAndView listqt(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wm/wmImqtNoticeHList");
    }
    @RequestMapping(params = "tlist")
    public ModelAndView tlist(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wm/wmImtNoticeHList");
    }
    @RequestMapping(params = "yklist")
    public ModelAndView yklist(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wm/wmImykNoticeHList");
    }
    @RequestMapping(params = "cuslist")
    public ModelAndView cuslist(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wm/cuswmImNoticeHList");
    }
    @RequestMapping(params = "custlist")
    public ModelAndView custlist(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wm/cuswmImtNoticeHList");
    }
    @RequestMapping(params = "batchlist")//????????????
    public ModelAndView batchlist(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wm/wminqmbatchList");
    }
    @RequestMapping(params = "tbatchlist")//????????????
    public ModelAndView tbatchlist(HttpServletRequest request) {
        return new ModelAndView("com/zzjee/wm/wmintqmbatchList");
    }
    @RequestMapping(params = "doPrintgoods")
    public ModelAndView doPrintgoods(String id,HttpServletRequest request) {
        WmImNoticeHEntity wmImNoticeHEntity = wmImNoticeHService.getEntity(WmImNoticeHEntity.class, id);

        Object id0 = wmImNoticeHEntity.getNoticeId();


        List<WmImNoticeIEntity> wmImNoticeIEntitynewList = new ArrayList<>();
        String hql0 = "from WmImNoticeIEntity where  iM_NOTICE_ID = ? ";
        try {
            List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService
                    .findHql(hql0, id0);
            for (WmImNoticeIEntity wmImNoticeIEntity : wmImNoticeIEntityList) {
                try{
                    MdGoodsEntity mvgoods = systemService.findUniqueByProperty(
                            MdGoodsEntity.class, "shpBianMa", wmImNoticeIEntity.getGoodsCode());
                    if (mvgoods != null) {
                        wmImNoticeIEntity.setBzhiQi(mvgoods.getBzhiQi());
                        wmImNoticeIEntity.setShpGuiGe(mvgoods.getShpGuiGe());
                        wmImNoticeIEntity.setGoodsName(mvgoods.getShpMingCheng());
                        wmImNoticeIEntity.setBarCode(mvgoods.getShpTiaoMa());
                    }
                }catch (Exception e){

                }
                wmImNoticeIEntitynewList.add(wmImNoticeIEntity);
            }
            request.setAttribute("wmImNoticeIList", wmImNoticeIEntitynewList);

        }catch (Exception e){

        }
        return new ModelAndView("com/zzjee/wm/print/imnotice-printgoods");
    }
    @RequestMapping(params = "doPrintpage")
    public ModelAndView doPrint(String id,HttpServletRequest request) {
        WmImNoticeHEntity wmImNoticeHEntity = wmImNoticeHService.getEntity(WmImNoticeHEntity.class, id);
        request.setAttribute("wmImNoticeHPage", wmImNoticeHEntity);
        request.setAttribute("kprq", DateUtils.date2Str(wmImNoticeHEntity.getCreateDate(), DateUtils.date_sdf));
        request.setAttribute("comname", ResourceUtil.getConfigByName("comname"));

        if(StringUtil.isNotEmpty(wmImNoticeHEntity.getImCusCode())){
            request.setAttribute("noticeid", wmImNoticeHEntity.getImCusCode());
        }else{
            request.setAttribute("noticeid", wmImNoticeHEntity.getNoticeId());
        }

        BaStoreEntity baStoreEntity = systemService.findUniqueByProperty(BaStoreEntity.class,"storeCode",wmImNoticeHEntity.getStoreCode());
        if (baStoreEntity != null && StringUtils.isNotEmpty(baStoreEntity.getStoreCode())) {
            request.setAttribute("storeName",baStoreEntity.getStoreName());
        }else {
            request.setAttribute("storeName","");
        }


        try{
            MdSupEntity mdSupEntity = systemService.findUniqueByProperty(MdSupEntity.class,"gysBianMa",wmImNoticeHEntity.getSupCode());
            MdCusEntity mdcus = systemService.findUniqueByProperty(MdCusEntity.class,"keHuBianMa",wmImNoticeHEntity.getCusCode());

            request.setAttribute("cusname",wmImNoticeHEntity.getCusCode()+"-"+mdcus.getZhongWenQch());

            request.setAttribute("supname",mdSupEntity.getGysBianMa()+"-"+ mdSupEntity.getZhongWenQch());

        }catch (Exception e){

        }
        //????????????
        Object id0 = wmImNoticeHEntity.getNoticeId();
        //===================================================================================
        //??????-??????
//		Double tomsum = 0.00;
//		Double  noticesum = 0.00;
        DecimalFormat dfsum=new DecimalFormat(".##");

        List<WmImNoticeIEntity> wmImNoticeIEntitynewList = new ArrayList<>();
        String hql0 = "from WmImNoticeIEntity where 1 = 1 AND iM_NOTICE_ID = ? ";
        try {
            List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService
                    .findHql(hql0, id0);
            BigDecimal totalCount = BigDecimal.ZERO;
            for (WmImNoticeIEntity wmImNoticeIEntity : wmImNoticeIEntityList) {
                if (StringUtil.isEmpty(wmImNoticeIEntity.getBinPlan())){
                    String hqlup = "from WmToUpGoodsEntity where 1 = 1 AND goodsId = ?  order by createDate desc";
                    try {
                        WmToUpGoodsEntity wmToUpGoodsEntity = 	(WmToUpGoodsEntity)systemService.findHql(hqlup,wmImNoticeIEntity.getGoodsCode()).get(0);
                        wmImNoticeIEntity.setBinPlan(wmToUpGoodsEntity.getKuWeiBianMa());
                    }catch (Exception e){

                    }
                }
                try{
                    MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
                            MvGoodsEntity.class, "goodsCode", wmImNoticeIEntity.getGoodsCode());
                    if (mvgoods != null) {
                        wmImNoticeIEntity.setBzhiQi(mvgoods.getBzhiQi());
                        wmImNoticeIEntity.setShpGuiGe(mvgoods.getShpGuiGe());
                        wmImNoticeIEntity.setGoodsName(mvgoods.getShpMingCheng());
                    }
                }catch (Exception e){

                }

                try{

                    wmImNoticeIEntity.setGoodsFvol(dfsum.format(Double.parseDouble(wmImNoticeIEntity.getGoodsFvol())));
                }catch (Exception e){

                }

                try{
                    wmImNoticeIEntity.setGoodsWeight(dfsum.format(Double.parseDouble(wmImNoticeIEntity.getGoodsWeight())));

                }catch (Exception e){

                }
                totalCount = totalCount.add(new BigDecimal(wmImNoticeIEntity.getGoodsCount()));


                wmImNoticeIEntitynewList.add(wmImNoticeIEntity);
            }
            request.setAttribute("wmImNoticeIList", wmImNoticeIEntitynewList);
            request.setAttribute("totalCount",totalCount);
        }catch (Exception e){

        }
        return new ModelAndView("com/zzjee/wm/print/imnotice-print");
    }

    @RequestMapping(params = "datagridbatch")
    public void datagridbatch(WmImNoticeIEntity wmImNoticeI,
                              HttpServletRequest request, HttpServletResponse response,
                              DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WmImNoticeIEntity.class, dataGrid);
        // ?????????????????????
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
                wmImNoticeI);
        if ("on".equals(ResourceUtil.getConfigByName("comgroup"))){
            cq.like("sysOrgCode", wmUtil.getCurrentDepartCode()+"%");
        }
        cq.eq("binPre", "N");
        cq.add();
        this.wmImNoticeHService.getDataGridReturn(cq, true);
        List<WmImNoticeIEntity> resultnew = new ArrayList<WmImNoticeIEntity>();
        List<WmImNoticeIEntity> resultold = dataGrid.getResults();
        for (WmImNoticeIEntity wmImNoticeIEntity : resultold) {
            try {
                if (Double.parseDouble(wmImNoticeIEntity.getGoodsCount()) > Double.parseDouble(wmImNoticeIEntity.getGoodsQmCount())) {
                    wmImNoticeIEntity.setGoodsWqmCount(Double.toString(Double.parseDouble(wmImNoticeIEntity.getGoodsCount()) - Double.parseDouble(wmImNoticeIEntity.getGoodsQmCount())));
                    resultnew.add(wmImNoticeIEntity);
                }
            } catch (Exception e) {
                wmImNoticeIEntity.setGoodsQmCount("0");
                resultnew.add(wmImNoticeIEntity);
            }


        }
        dataGrid.setResults(resultnew);
        dataGrid.setTotal(resultnew.size());
        TagUtil.datagrid(response, dataGrid);

    }
    @RequestMapping(params = "datagridtbatch")
    public void datagridtbatch(WmImNoticeIEntity wmImNoticeI,
                               HttpServletRequest request, HttpServletResponse response,
                               DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WmImNoticeIEntity.class, dataGrid);
        // ?????????????????????
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
                wmImNoticeI);
        cq.eq("binPre", "N");
        cq.like("imNoticeId", "TH%");
        cq.add();
        this.wmImNoticeHService.getDataGridReturn(cq, true);
        List<WmImNoticeIEntity> resultnew = new ArrayList<WmImNoticeIEntity>();
        List<WmImNoticeIEntity> resultold = dataGrid.getResults();
        for (WmImNoticeIEntity wmImNoticeIEntity : resultold) {
            if (Double.parseDouble(wmImNoticeIEntity.getGoodsCount()) > Double.parseDouble(wmImNoticeIEntity.getGoodsQmCount())) {
                wmImNoticeIEntity.setGoodsWqmCount(Double.toString(Double.parseDouble(wmImNoticeIEntity.getGoodsCount()) - Double.parseDouble(wmImNoticeIEntity.getGoodsQmCount())));

                resultnew.add(wmImNoticeIEntity);
            }
        }
        dataGrid.setResults(resultnew);
        dataGrid.setTotal(resultnew.size());
        TagUtil.datagrid(response, dataGrid);

    }
    /**
     * easyui AJAX????????????
     *
     * @param request
     * @param response
     * @param dataGrid
     * @param
     */

    @RequestMapping(params = "datagrid")
    public void datagrid(WmImNoticeHEntity wmImNoticeH,
                         HttpServletRequest request, HttpServletResponse response,
                         DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WmImNoticeHEntity.class, dataGrid);
        // ?????????????????????
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
                wmImNoticeH);
        try {
            // ???????????????????????????
            String query_imData_begin = request.getParameter("imData_begin1");
            String query_imData_end = request.getParameter("imData_end2");

            if (StringUtil.isNotEmpty(query_imData_begin)) {
                cq.ge("imData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .parse(query_imData_begin));
            }
            if (StringUtil.isNotEmpty(query_imData_end)) {
                cq.le("imData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .parse(query_imData_end));
            }
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

        this.wmImNoticeHService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * easyui AJAX????????????
     *
     * @param request
     * @param response
     * @param dataGrid
     * @param
     */

    @RequestMapping(params = "datagridqt")
    public void datagridqt(WmImNoticeHEntity wmImNoticeH,
                           HttpServletRequest request, HttpServletResponse response,
                           DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WmImNoticeHEntity.class, dataGrid);
        // ?????????????????????
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
                wmImNoticeH);
        try {
            // ???????????????????????????
            String query_imData_begin = request.getParameter("imData_begin1");
            String query_imData_end = request.getParameter("imData_end2");

            if (StringUtil.isNotEmpty(query_imData_begin)) {
                cq.ge("imData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .parse(query_imData_begin));
            }
            if (StringUtil.isNotEmpty(query_imData_end)) {
                cq.le("imData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .parse(query_imData_end));
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
//		if (wmImNoticeH.getImSta() == null) {
//			cq.eq("imSta", Constants.wm_sta1);
//		}



        if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
            cq.eq("cusCode", wmUtil.getCusCode());
        }

//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("imSta", "desc");
//		cq.setOrder(map);

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("createDate", "desc");
        cq.setOrder(map1);
        cq.eq("orderTypeCode", "09");
        cq.add();

        this.wmImNoticeHService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    //??????
    @RequestMapping(params = "datagridt")
    public void datagridt(WmImNoticeHEntity wmImNoticeH,
                          HttpServletRequest request, HttpServletResponse response,
                          DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WmImNoticeHEntity.class, dataGrid);
        // ?????????????????????
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
                wmImNoticeH);
        try {
            // ???????????????????????????
            String query_imData_begin = request.getParameter("imData_begin1");
            String query_imData_end = request.getParameter("imData_end2");

            if (StringUtil.isNotEmpty(query_imData_begin)) {
                cq.ge("imData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .parse(query_imData_begin));
            }
            if (StringUtil.isNotEmpty(query_imData_end)) {
                cq.le("imData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .parse(query_imData_end));
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (wmImNoticeH.getImSta() == null) {
            cq.eq("imSta", Constants.wm_sta1);
        }



        if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
            cq.eq("cusCode", wmUtil.getCusCode());
        }

//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("imSta", "desc");
//		cq.setOrder(map);

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("createDate", "desc");
        cq.setOrder(map1);
        cq.eq("orderTypeCode", "03");
        cq.add();

        this.wmImNoticeHService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }


    //??????
    @RequestMapping(params = "datagridyk")
    public void datagridyk(WmImNoticeHEntity wmImNoticeH,
                           HttpServletRequest request, HttpServletResponse response,
                           DataGrid dataGrid) {
        CriteriaQuery cq = new CriteriaQuery(WmImNoticeHEntity.class, dataGrid);
        // ?????????????????????
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
                wmImNoticeH);
        try {
            // ???????????????????????????
            String query_imData_begin = request.getParameter("imData_begin1");
            String query_imData_end = request.getParameter("imData_end2");

            if (StringUtil.isNotEmpty(query_imData_begin)) {
                cq.ge("imData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .parse(query_imData_begin));
            }
            if (StringUtil.isNotEmpty(query_imData_end)) {
                cq.le("imData", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .parse(query_imData_end));
            }
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (wmImNoticeH.getImSta() == null) {
            cq.eq("imSta", Constants.wm_sta1);
        }
        if(StringUtil.isNotEmpty(wmUtil.getCusCode())){
            cq.eq("cusCode", wmUtil.getCusCode());
        }

        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("createDate", "desc");
        cq.setOrder(map1);
        cq.eq("orderTypeCode", "04");
        cq.add();

        this.wmImNoticeHService.getDataGridReturn(cq, true);
        TagUtil.datagrid(response, dataGrid);
    }

    /**
     * ????????????????????????
     *
     * @return
     */
    @RequestMapping(params = "appor")
    @ResponseBody
    public AjaxJson doappor(WmImNoticeHEntity wmImNoticeH,
                            HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        String message = "????????????";
        try {
            wmImNoticeH = systemService.getEntity(WmImNoticeHEntity.class,
                    request.getParameter("id"));
            wmImNoticeH.setImSta(Constants.wm_sta1);
            wmImNoticeHService.saveOrUpdate(wmImNoticeH);
            systemService.addLog(message, Globals.Log_Type_DEL,
                    Globals.Log_Leavel_INFO);
            Object id0 = wmImNoticeH.getNoticeId();
            // ===================================================================================
            // ??????-??????????????????
            String hql0 = "from WmImNoticeIEntity where 1 = 1 AND iM_NOTICE_ID = ? ";
            try {
                List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService
                        .findHql(hql0, id0);
                for (WmImNoticeIEntity wmImNoticeIEntity : wmImNoticeIEntityList) {
                    wmImNoticeIEntity.setBinPre("N");
                    systemService.updateEntitie(wmImNoticeIEntity);
                }
            }catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "????????????";
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
    @RequestMapping(params = "close")
    @ResponseBody
    public AjaxJson doclose(WmImNoticeHEntity wmImNoticeH,
                            HttpServletRequest request) {
        AjaxJson j = new AjaxJson();

        String message = "????????????";
        try {
            wmImNoticeH = systemService.getEntity(WmImNoticeHEntity.class,
                    request.getParameter("id"));
            wmImNoticeH.setImSta(Constants.wm_sta4);
            wmImNoticeHService.saveOrUpdate(wmImNoticeH);
            systemService.addLog(message, Globals.Log_Type_DEL,
                    Globals.Log_Leavel_INFO);
            Object id0 = wmImNoticeH.getNoticeId();
            // ===================================================================================
            // ??????-??????????????????
            String hql0 = "from WmImNoticeIEntity where 1 = 1 AND iM_NOTICE_ID = ? ";
            try {
                List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService
                        .findHql(hql0, id0);
                for (WmImNoticeIEntity wmImNoticeIEntity : wmImNoticeIEntityList) {
                    wmImNoticeIEntity.setBinPre("Y");
                    systemService.updateEntitie(wmImNoticeIEntity);
                }
            }catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "????????????";
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
    @RequestMapping(params = "doDel")
    @ResponseBody
    public AjaxJson doDel(WmImNoticeHEntity wmImNoticeH,
                          HttpServletRequest request) {
        boolean  deltrue = true;
        if(!"database".equals(ResourceUtil.getConfigByName("sys.del"))){
            deltrue=false;
        }
        AjaxJson j = new AjaxJson();
        wmImNoticeH = systemService.getEntity(WmImNoticeHEntity.class,
                wmImNoticeH.getId());
        String message = "????????????????????????";
        try {
            WmPlatIoEntity wmPlatIo = systemService.findUniqueByProperty(
                    WmPlatIoEntity.class, "docId", wmImNoticeH.getNoticeId());// ??????????????????
            if (wmPlatIo != null) {
                systemService.delete(wmPlatIo);
            }
            wmImNoticeH.setImSta(Constants.wm_sta3);
            if(deltrue){
                wmImNoticeHService.delete(wmImNoticeH);

            }else{
                wmImNoticeHService.saveOrUpdate(wmImNoticeH);

            }
            systemService.addLog(message, Globals.Log_Type_DEL,
                    Globals.Log_Leavel_INFO);
            Object id0 = wmImNoticeH.getNoticeId();
            // ===================================================================================
            // ??????-??????????????????

            if(wmImNoticeH.getOrderTypeCode().equals("04")){
                String 	tsql = "delete  from wm_in_qm_i where im_notice_id = ?";
                systemService.executeSql(tsql, wmImNoticeH.getNoticeId());
            }
            String hql0 = "from WmImNoticeIEntity where 1 = 1 AND iM_NOTICE_ID = ? ";
            try {
                List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService
                        .findHql(hql0, id0);
                for (WmImNoticeIEntity wmImNoticeIEntity : wmImNoticeIEntityList) {
                    wmImNoticeIEntity.setBinPre("Y");
                    if(deltrue){
                        systemService.delete(wmImNoticeIEntity);

                    }else{
                        systemService.updateEntitie(wmImNoticeIEntity);

                    }
                }
            }catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "????????????????????????";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }
    @RequestMapping(params = "doPrinthpid")
    @ResponseBody
    public void downReceiveExcelhpid(WmImNoticeHEntity wmImNoticeH,
                                     HttpServletRequest request, HttpServletResponse response) {
        OutputStream fileOut = null;
        BufferedImage bufferImg = null;
//		String codedFileName = null;
        wmImNoticeH = systemService.getEntity(WmImNoticeHEntity.class,
                wmImNoticeH.getId());// ????????????
        String hql0 = "from WmInQmIEntity where 1 = 1 AND imNoticeId = ? ";
        List<WmInQmIEntity> wmImQmIEntityList = systemService.findHql(
                hql0, wmImNoticeH.getNoticeId());// ???????????????
        // ????????????????????????????????????ByteArrayOutputStream??????????????????ByteArray
        try {
//			StringBuffer sber = new StringBuffer();

            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//			bufferImg = ImageIO.read(BarcodeUtil.generateToStream(wmImNoticeH
//					.getNoticeId()));
            // ??????????????????????????????????????????
//			codedFileName = java.net.URLEncoder.encode("??????", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename="
                    + wmImNoticeH.getNoticeId() + "hpid.xls");
//			ImageIO.write(bufferImg, "jpg", byteArrayOut);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("??????ID");

            sheet.setMargin(HSSFSheet.TopMargin,0.1);// ??????????????????
            sheet.setMargin(HSSFSheet.BottomMargin,0.1);// ??????????????????
            sheet.setMargin(HSSFSheet.LeftMargin,0.1);// ??????????????????
            sheet.setMargin(HSSFSheet.RightMargin,0.1);// ???????????????
            sheet.setColumnWidth(0, 28 * 256);
            sheet.setColumnWidth(1, 29 * 256);
            sheet.setColumnWidth(2, 6 * 200);

            sheet.setColumnWidth(3, 14 * 256);
            sheet.setColumnWidth(4, 14 * 256);
            sheet.setColumnWidth(5, 6 * 256);
            // sheet.setColumnWidth(6, 8 * 256);
            // sheet.setColumnWidth(7, 8 * 256);
            // sheet.setColumnWidth(8, 8 * 256);
            // ?????????????????????????????????sheet?????????????????????????????????????????????
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            // anchor?????????????????????????????????
            HSSFClientAnchor anchor = null;



            // ???????????????????????????
            CellStyle csh = wb.createCellStyle();
            CellStyle csh1 = wb.createCellStyle();
            CellStyle cs = wb.createCellStyle();
            CellStyle cs2 = wb.createCellStyle();
            CellStyle cs3 = wb.createCellStyle();
            CellStyle cs4 = wb.createCellStyle();
            CellStyle cs5 = wb.createCellStyle();
            // ??????????????????
            Font f = wb.createFont();
            Font f2 = wb.createFont();
            Font fh = wb.createFont();
            fh.setFontHeightInPoints((short) 42);
            fh.setColor(IndexedColors.BLACK.getIndex());
            fh.setBoldweight(Font.BOLDWEIGHT_BOLD);
            Font fh1 = wb.createFont();
            fh1.setFontHeightInPoints((short) 32);
            fh1.setColor(IndexedColors.BLACK.getIndex());
            fh1.setBoldweight(Font.BOLDWEIGHT_BOLD);
            // ?????????????????????????????????????????????
            f.setFontHeightInPoints((short) 22);
            f.setColor(IndexedColors.BLACK.getIndex());
            f.setBoldweight(Font.BOLDWEIGHT_BOLD);

            // ??????????????????????????????????????????
            f2.setFontHeightInPoints((short) 10);
            f2.setColor(IndexedColors.BLACK.getIndex());

            // Font f3=wb.createFont();
            // f3.setFontHeightInPoints((short) 10);
            // f3.setColor(IndexedColors.RED.getIndex());
            csh.setFont(fh);
            csh.setBorderLeft(CellStyle.BORDER_NONE);
            csh.setBorderRight(CellStyle.BORDER_NONE);
            csh.setBorderTop(CellStyle.BORDER_NONE);
            csh.setBorderBottom(CellStyle.BORDER_NONE);
            csh.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            csh1.setFont(fh1);
            csh1.setBorderLeft(CellStyle.BORDER_NONE);
            csh1.setBorderRight(CellStyle.BORDER_NONE);
            csh1.setBorderTop(CellStyle.BORDER_NONE);
            csh1.setBorderBottom(CellStyle.BORDER_NONE);
            csh1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            // ???????????????????????????????????????????????????
            cs.setFont(f);
            cs.setBorderLeft(CellStyle.BORDER_NONE);
            cs.setBorderRight(CellStyle.BORDER_NONE);
            cs.setBorderTop(CellStyle.BORDER_NONE);
            cs.setBorderBottom(CellStyle.BORDER_NONE);
            cs.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
            cs5.setBorderLeft(CellStyle.BORDER_NONE);
            cs5.setBorderRight(CellStyle.BORDER_NONE);
            cs5.setBorderTop(CellStyle.BORDER_NONE);
            cs5.setBorderBottom(CellStyle.BORDER_NONE);
            cs5.setWrapText(true);

            int cellsNum = 0;
            int pagesize = 1;
            int page = 0;
            short high =  600;
            for (WmInQmIEntity wmInQmIEntity : wmImQmIEntityList) {
                cellsNum++;
                MvGoodsEntity goods = systemService.findUniqueByProperty(
                        MvGoodsEntity.class, "goodsCode",
                        wmInQmIEntity.getGoodsId());
                Row rowColumnValue = sheet.createRow((short) page*pagesize+cellsNum); // ??????
                rowColumnValue.setHeight((short) 1000);
                Cell cell1 = rowColumnValue.createCell(0);
                try {
                    cell1.setCellValue("?????????"+wmInQmIEntity.getBinId().substring(0,2)+"  "+wmInQmIEntity.getQmOkQuat()+goods.getShlDanWei());

                } catch (Exception e) {
                    // TODO: handle exception
                }
                cell1.setCellStyle(csh);
                CellRangeAddress c0 = new CellRangeAddress( page*pagesize+cellsNum,  page*pagesize+cellsNum, 0, 5);
                sheet.addMergedRegion(c0);

                cellsNum++;
                Row rowColumnValue1 = sheet.createRow((short) page*pagesize+cellsNum); // ??????
                rowColumnValue1.setHeight( (short) 1000);
                Cell cell2 = rowColumnValue1.createCell(0);

                cell2.setCellValue( goods.getShpMingCheng());
                cell2.setCellStyle(csh1);
                CellRangeAddress c2 = new CellRangeAddress( page*pagesize+cellsNum,  page*pagesize+cellsNum, 0, 5);
                sheet.addMergedRegion(c2);
                cellsNum++;
                //????????????
                try {
                    byteArrayOut = new ByteArrayOutputStream();
                    try {
                        bufferImg =
                                ImageIO.read(BarcodeUtil.generateToStream(wmInQmIEntity.getBinId().substring(0,2)));
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                    ImageIO.write(bufferImg, "jpg", byteArrayOut);
                    anchor = new HSSFClientAnchor(0, 0, 0, 0,(short)2, page*pagesize+cellsNum,
                            (short)5, page*pagesize+cellsNum+4);
                    patriarch.createPicture(anchor,
                            wb.addPicture(byteArrayOut.toByteArray(),
                                    HSSFWorkbook.PICTURE_TYPE_JPEG));
                } catch (Exception e) {
                    // TODO: handle exception
                }
                Row rowColumnValue12 = sheet.createRow((short) page*pagesize+cellsNum); // ??????
                rowColumnValue12.setHeight((short) 1000);
                Cell cell13 = rowColumnValue12.createCell(0);
                cell13.setCellValue(goods.getGoodsCode() );
                cell13.setCellStyle(cs);
                CellRangeAddress c13 = new CellRangeAddress( page*pagesize+cellsNum,  page*pagesize+cellsNum, 0, 5);
                sheet.addMergedRegion(c13);
                cellsNum++;

                Row rowColumnValue2 = sheet.createRow((short) page*pagesize+cellsNum); // ??????
                rowColumnValue2.setHeight(high);
                Cell cell3 = rowColumnValue2.createCell(0);
                cell3.setCellValue("????????????:"+wmInQmIEntity.getProData() );
                cell3.setCellStyle(cs);
                CellRangeAddress c3 = new CellRangeAddress( page*pagesize+cellsNum,  page*pagesize+cellsNum, 0, 5);
                sheet.addMergedRegion(c3);
                cellsNum++;
                Row rowColumnValue3 = sheet.createRow((short) page*pagesize+cellsNum); // ??????
                rowColumnValue3.setHeight(high);
                Cell cell4 = rowColumnValue3.createCell(0);
                try {
                    Calendar cal = Calendar.getInstance();//??????????????????????????????????????????????????????
                    cal.setTime(DateUtils.str2Date(wmInQmIEntity.getProData(), DateUtils.date_sdf));
                    cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(goods.getBzhiQi()));//???????????????????????????.
                    cell4.setCellValue("????????????:"+ DateUtils.date2Str(cal.getTime(), DateUtils.date_sdf));
                } catch (Exception e) {
                    // TODO: handle exception
                }
                cell4.setCellStyle(cs);
                CellRangeAddress c4 = new CellRangeAddress( page*pagesize+cellsNum,  page*pagesize+cellsNum, 0, 5);
                sheet.addMergedRegion(c4);
                cellsNum++;
                Row rowColumnValue4 = sheet.createRow((short) page*pagesize+cellsNum); // ??????
                rowColumnValue4.setHeight(high);
                Cell cell5 = rowColumnValue4.createCell(0);
                cell5.setCellValue("????????????:"+ DateUtils.date2Str(wmInQmIEntity.getCreateDate() , DateUtils.date_sdf) );
                cell5.setCellStyle(cs);
                CellRangeAddress c5 = new CellRangeAddress( page*pagesize+cellsNum,  page*pagesize+cellsNum, 0, 5);
                sheet.addMergedRegion(c5);

                page++;
            }
            fileOut = response.getOutputStream();
            HSSFPrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setLandscape(true);
            printSetup.setPaperSize(HSSFPrintSetup.ENVELOPE_DL_PAPERSIZE);
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * ??????????????????
     *
     * @return
     */

    @RequestMapping(params = "doPrint")
    @ResponseBody
    public void downReceiveExcel(WmImNoticeHEntity wmImNoticeH,
                                 HttpServletRequest request, HttpServletResponse response) {
        OutputStream fileOut = null;
        BufferedImage bufferImg = null;
//		String codedFileName = null;
        wmImNoticeH = systemService.getEntity(WmImNoticeHEntity.class,
                wmImNoticeH.getId());// ????????????
        String hql0 = "from WmImNoticeIEntity where 1 = 1 AND imNoticeId = ? ";
        List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService.findHql(
                hql0, wmImNoticeH.getNoticeId());// ???????????????
        // ????????????????????????????????????ByteArrayOutputStream??????????????????ByteArray
        try {
//			StringBuffer sber = new StringBuffer();

            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
            bufferImg = ImageIO.read(BarcodeUtil.generateToStream(wmImNoticeH
                    .getNoticeId()));
            // ??????????????????????????????????????????
//			codedFileName = java.net.URLEncoder.encode("??????", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename="
                    + wmImNoticeH.getNoticeId() + ".xls");
            ImageIO.write(bufferImg, "jpg", byteArrayOut);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet("????????????");
            sheet.setColumnWidth(0, 5 * 256);
            sheet.setColumnWidth(1, 42 * 256);
            sheet.setColumnWidth(2, 10 * 200);
            sheet.setColumnWidth(3, 8 * 256);
            sheet.setColumnWidth(4, 8 * 256);
            sheet.setColumnWidth(5, 8 * 256);
            // sheet.setColumnWidth(6, 8 * 256);
            // sheet.setColumnWidth(7, 8 * 256);
            // sheet.setColumnWidth(8, 8 * 256);
            // ?????????????????????????????????sheet?????????????????????????????????????????????
            HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
            // anchor?????????????????????????????????
            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,
                    (short) 3, 1, (short) 6, 3);
            anchor.setAnchorType(3);
            // ????????????
            patriarch
                    .createPicture(anchor, wb.addPicture(
                            byteArrayOut.toByteArray(),
                            HSSFWorkbook.PICTURE_TYPE_JPEG));

            // ???????????????
            Row row = sheet.createRow((short) 0); // ???????????????

            // ???????????????????????????
            CellStyle cs = wb.createCellStyle();
            CellStyle cs2 = wb.createCellStyle();
            CellStyle cs3 = wb.createCellStyle();
            CellStyle cs4 = wb.createCellStyle();
            CellStyle cs5 = wb.createCellStyle();
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

            // Font f3=wb.createFont();
            // f3.setFontHeightInPoints((short) 10);
            // f3.setColor(IndexedColors.RED.getIndex());

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
            cs5.setBorderLeft(CellStyle.BORDER_NONE);
            cs5.setBorderRight(CellStyle.BORDER_NONE);
            cs5.setBorderTop(CellStyle.BORDER_NONE);
            cs5.setBorderBottom(CellStyle.BORDER_NONE);
            cs5.setWrapText(true);

            Row row1 = sheet.createRow((short) 1); // ???????????????
            row1.setHeight((short) 700);
            Cell cellTitle = row1.createCell(0);
            cellTitle.setCellValue("????????????");
            cellTitle.setCellStyle(cs);

            Row rowHead1 = sheet.createRow((short) 2); // ???????????????
            Cell cellHead11 = rowHead1.createCell(0);
            cellHead11.setCellValue("???????????????" + wmImNoticeH.getNoticeId());
            cellHead11.setCellStyle(cs2);

            Row rowHead2 = sheet.createRow((short) 3); // ???????????????
            Cell cellHead21 = rowHead2.createCell(0);
            try {
                MdCusEntity md = systemService.findUniqueByProperty(
                        MdCusEntity.class, "keHuBianMa",
                        wmImNoticeH.getCusCode());
                if (md != null) {
                    cellHead21.setCellValue("?????????" + wmImNoticeH.getCusCode()
                            + "-" + md.getZhongWenQch());
                } else {
                    cellHead21.setCellValue("?????????" + wmImNoticeH.getCusCode());
                }
            } finally {

            }

            cellHead21.setCellStyle(cs2);

            Cell cellHead23 = rowHead2.createCell(2);
            cellHead23.setCellValue(" ?????????????????????" + wmImNoticeH.getImData());
            cellHead23.setCellStyle(cs2);

            Row rowHead3 = sheet.createRow((short) 4); // ???????????????
            Cell cellHead31 = rowHead3.createCell(0);
            cellHead31.setCellValue("?????????" + wmImNoticeH.getImCarDri()
                    + "  ???????????????" + wmImNoticeH.getImCarMobile());
            cellHead31.setCellStyle(cs2);

            Cell cellHead35 = rowHead3.createCell(2);
            cellHead35.setCellValue("?????????" + wmImNoticeH.getImCarNo() + "  ?????????"
                    + wmImNoticeH.getImBeizhu());
            cellHead35.setCellStyle(cs2);

            // ???????????????
            CellRangeAddress c = new CellRangeAddress(0, 0, 0, 8); // ???????????????
            CellRangeAddress c0 = new CellRangeAddress(1, 1, 0, 5);// ???????????????
            CellRangeAddress c1 = new CellRangeAddress(2, 2, 0, 8);// ?????????????????????
            CellRangeAddress c2 = new CellRangeAddress(3, 3, 0, 1);// ???????????????
            CellRangeAddress c3 = new CellRangeAddress(3, 3, 2, 5);// ???????????????????????????
            CellRangeAddress c4 = new CellRangeAddress(4, 4, 0, 1);// ???????????????
            CellRangeAddress c5 = new CellRangeAddress(4, 4, 2, 5);// ???????????????????????????
            // CellRangeAddress c4 = new CellRangeAddress(4, 4, 0, 1);
            // CellRangeAddress c5 = new CellRangeAddress(4, 4, 2, 3);
            // CellRangeAddress c6 = new CellRangeAddress(4, 4, 4, 5);
            // CellRangeAddress c7 = new CellRangeAddress(4, 4, 6, 6);

            sheet.addMergedRegion(c);
            sheet.addMergedRegion(c0);
            sheet.addMergedRegion(c1);
            sheet.addMergedRegion(c2);
            sheet.addMergedRegion(c3);
            sheet.addMergedRegion(c4);
            sheet.addMergedRegion(c5);
            // sheet.addMergedRegion(c6);
            // sheet.addMergedRegion(c7);

            Row rowColumnName = sheet.createRow((short) 5); // ??????
            String[] columnNames = { "??????", "????????????", "??????", "??????cm??", "??????KG",
                    "TiHi" };

            for (int i = 0; i < columnNames.length; i++) {
                Cell cell = rowColumnName.createCell(i);
                cell.setCellValue(columnNames[i]);
                cell.setCellStyle(cs3);
            }
            int cellsNum = 5;
            int cerconNo = 1;
            for (int i = 0; i < wmImNoticeIEntityList.size(); i++) {
                WmImNoticeIEntity entity = wmImNoticeIEntityList.get(i);
                cellsNum++;
                Row rowColumnValue = sheet.createRow((short) cellsNum); // ??????
                rowColumnValue.setHeight((short) 1000);
                Cell cell1 = rowColumnValue.createCell(0);
                cell1.setCellValue(cerconNo);
                cell1.setCellStyle(cs4);
                Cell cell2 = rowColumnValue.createCell(1);

                Cell cell6 = rowColumnValue.createCell(5);
                cell6.setCellStyle(cs3);
                try {
                    MvGoodsEntity goods = systemService.findUniqueByProperty(
                            MvGoodsEntity.class, "goodsCode",
                            entity.getGoodsCode());
                    if (goods != null) {
                        cell2.setCellValue(new HSSFRichTextString(goods
                                .getGoodsName()));
                        cell2.setCellStyle(cs3);

                        cell6.setCellValue(goods.getMpDanCeng() + "*"
                                + goods.getMpCengGao());
                    }
                } finally {

                }
                Cell cell3 = rowColumnValue.createCell(2);
                cell3.setCellValue(entity.getGoodsCount());
                cell3.setCellStyle(cs4);

                Cell cell4 = rowColumnValue.createCell(3);
                cell4.setCellValue(entity.getGoodsFvol());
                cell4.setCellStyle(cs4);
                Cell cell5 = rowColumnValue.createCell(4);
                cell5.setCellValue(entity.getGoodsWeight());
                cell5.setCellStyle(cs4);


                cerconNo++;
            }
            Row rowColumnInfo = sheet.createRow((short) 2 + cellsNum); // ??????
            rowColumnInfo.createCell(0).setCellValue(
                    "???:??????????????????"+ ResourceUtil.getConfigByName("comaddr")+" ?????????");
            CellRangeAddress c15 = new CellRangeAddress(10 + cellsNum,
                    10 + cellsNum, 0, 15);
            sheet.addMergedRegion(c15);
            fileOut = response.getOutputStream();
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(params = "doPrintysd")
    @ResponseBody
    public void downReceiveExcelysd(WmImNoticeHEntity wmImNoticeH,
                                    HttpServletRequest request, HttpServletResponse response) {
        OutputStream fileOut = null;
        BufferedImage bufferImg = null;
//		String codedFileName = null;
        wmImNoticeH = systemService.getEntity(WmImNoticeHEntity.class,
                wmImNoticeH.getId());// ????????????

        // ????????????????????????????????????ByteArrayOutputStream??????????????????ByteArray
        try {
//			StringBuffer sber = new StringBuffer();

            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//			bufferImg = ImageIO.read(BarcodeUtil.generateToStream(wmImNoticeH
//					.getNoticeId()));
            bufferImg = QRcodeUtil.createImage(wmImNoticeH
                    .getNoticeId());

            // ??????????????????????????????????????????
//			codedFileName = java.net.URLEncoder.encode("??????", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename="
                    + wmImNoticeH.getNoticeId() + "?????????.xls");
            ImageIO.write(bufferImg, "jpg", byteArrayOut);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(wmImNoticeH.getNoticeId());
            sheet.setMargin(HSSFSheet.TopMargin,0.1);// ??????????????????
            sheet.setMargin(HSSFSheet.BottomMargin,0.1);// ??????????????????
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
            cs52.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);

            cs52.setWrapText(true);
            cs52.setRotation((short)255);

            int page = 0;
            int cerconNo = 1;
            String tsql = "SELECT wq.pro_data,wq.goods_unit,wq.rec_deg, mg.shp_gui_ge ,mg.goods_code, mg.goods_id,mg.shp_ming_cheng,"
                    + "cast(sum(wq.qm_ok_quat) as signed) as goods_count,truncate(sum(wq.tin_tj),2) tin_tj ,truncate(sum(wq.tin_zhl),2) as tin_zhl,count(*) as tuopan   "
                    + "FROM wm_in_qm_i wq,mv_goods mg where wq.im_notice_id = ? and  wq.goods_id = mg.goods_code group by wq.im_notice_id, mg.goods_code,wq.pro_data";
            List<Map<String, Object>> result = systemService
                    .findForJdbc(tsql, wmImNoticeH.getNoticeId());


            int size = result.size();
            int pagesize = 10;
            int pagecount = size%pagesize==0?size/pagesize:size/pagesize+1;
            double sum = 0;
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
                if(wmImNoticeH.getOrderTypeCode().equals("03")){
                    cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"???????????????");
                }else if(wmImNoticeH.getOrderTypeCode().equals("01")){
                    cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"???????????????");
                }else if(wmImNoticeH.getOrderTypeCode().equals("04")){
                    cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"?????????");
                }else if(wmImNoticeH.getOrderTypeCode().equals("09")){
                    cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"???????????????");
                }


                cellTitle.setCellStyle(cs);

                Row rowHead1 = sheet.createRow((short) page*20+2); // ???????????????
                Cell cellHead1 = rowHead1.createCell(0);
                cellHead1.setCellValue("???????????????"+ ResourceUtil.getConfigByName("comaddr") );
                cellHead1.setCellStyle(cs1);

                Row rowHead2 = sheet.createRow((short) page*20+3); // ???????????????
                Cell cellHead2 = rowHead2.createCell(0);
                cellHead2.setCellValue("?????????"+ ResourceUtil.getConfigByName("comtel") );
                cellHead2.setCellStyle(cs1);


                Row rowHead4 = sheet.createRow((short) page*20+4); // ???????????????
                Cell cellHead4 = rowHead4.createCell(0);
                cellHead4.setCellValue("??????????????? " + wmImNoticeH.getImData() !=null ?DateUtils.date2Str(wmImNoticeH.getImData(), DateUtils.date_sdf) :"" );
                cellHead4.setCellStyle(cs2);

                Cell cellHead42 = rowHead4.createCell(3);
                cellHead42.setCellValue("??????????????? " +wmImNoticeH.getNoticeId());
                cellHead42.setCellStyle(cs2);

                Row rowHead5 = sheet.createRow((short) page*20+5); // ???????????????
                Cell cellHead5 = rowHead5.createCell(0);
                cellHead5.setCellValue("????????????????????? "+wmImNoticeH.getImCusCode() );
                cellHead5.setCellStyle(cs2);

                Cell cellHead52 = rowHead5.createCell(3);
                BaStoreEntity baStoreEntity = systemService.findUniqueByProperty(BaStoreEntity.class,"storeCode",wmImNoticeH.getStoreCode());
                if (baStoreEntity != null && StringUtils.isNotEmpty(baStoreEntity.getStoreCode())) {
                    cellHead52.setCellValue("????????? " +baStoreEntity.getStoreName());
                }else {
                    cellHead52.setCellValue("????????? ");
                }
                //cellHead52.setCellValue("????????? " +(StringUtils.isEmpty(wmImNoticeH.getPlatformCode())?"":wmImNoticeH.getPlatformCode()));
                cellHead52.setCellStyle(cs2);

                Row rowHead6 = sheet.createRow((short) page*20+6); // ???????????????
                Cell cellHead6 = rowHead6.createCell(0);
                MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmImNoticeH.getCusCode());

                cellHead6.setCellValue("??????????????? " +wmImNoticeH.getCusCode()+md.getZhongWenQch());
                cellHead6.setCellStyle(cs2);

                Cell cellHead62 = rowHead6.createCell(3);
                String gys="";
                if(!StringUtil.isEmpty(wmImNoticeH.getSupCode())){
                    gys = wmImNoticeH.getSupCode()+wmImNoticeH.getSupName();
                }
                if(!StringUtil.isEmpty(wmImNoticeH.getImCarNo())){
                    if(!StringUtil.isEmpty(gys)){
                        gys = wmImNoticeH.getSupCode();
                    }else{
                        gys =gys+"/"+ wmImNoticeH.getSupCode();
                    }

                }
                cellHead62.setCellValue("?????????/????????? "+  gys );
                cellHead62.setCellStyle(cs2);

                Row rowHead7 = sheet.createRow((short) page*20+7); // ???????????????
                Cell cellHead7 = rowHead7.createCell(0);
                cellHead7.setCellValue("??????????????? " +md.getDianHua());
                cellHead7.setCellStyle(cs2);

                Cell cellHead72 = rowHead7.createCell(3);
                cellHead72.setCellValue("??????????????? "+ DateUtils.date2Str(DateUtils.getDate(), DateUtils.datetimeFormat) +"                        ???"+(page+1)+"???");
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
                CellRangeAddress c7 = new CellRangeAddress(page*20+7, page*20+7, 0, 2);//???7???????????????
                CellRangeAddress c72 = new CellRangeAddress(page*20+7, page*20+7, 3, 9);//???7???????????????
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
                cell73.setCellValue("??? ?????????  ??? ?????????  ????????????  ????????????");
                cell73.setCellStyle(cs52);


                CellRangeAddress c73 = new CellRangeAddress(page*20+0, page*20+19, 10, 10);//???7???????????????
                sheet.addMergedRegion(c73);

                Row rowColumnName = sheet.createRow((short) page*20+8); // ??????
                String[] columnNames = { "??????", "????????????", "????????????", "????????????", "????????????","??????", "??????", "??????KG","??????","??????" };
                if(ResourceUtil.getConfigByName("systuopan").equals("yes")){
                    String[]   columnNamest = { "??????", "????????????", "????????????", "????????????", "????????????","??????", "??????", "??????KG","??????","??????" };
                    columnNames = columnNamest;
                }
                try{
                    if("hr".equals(ResourceUtil.getConfigByName("wm.ckd"))){
                        String[] columnNames1 = { "??????", "????????????", "????????????", "????????????", "????????????","??????", "??????", "??????KG","??????","??????" };
                        if(ResourceUtil.getConfigByName("systuopan").equals("yes")){
                            String[]   columnNamest1 = { "??????", "????????????", "????????????", "????????????", "????????????","??????", "??????", "??????KG","??????","??????" };
                            columnNames1 = columnNamest1;
                        }
                        columnNames = columnNames1;
                    }
                }catch ( Exception e){
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
                for (int i = page*pagesize; i < (page+1)*pagesize + oversize; i++) {
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

                            cell4.setCellStyle(cs5r);
                            cell4.setCellValue(result.get(i).get("pro_data")
                                    .toString());

                        } catch (Exception e) {
                            // TODO: handle exception

                        }

                        try {
                            Cell cell5 = rowColumnValue.createCell(4);// ??????

                            cell5.setCellStyle(cs5);
                            cell5.setCellValue("");
//							cell5.setCellValue(result.get(i)
//									.get("rec_deg").toString());
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        try {
                            Cell cell6 = rowColumnValue.createCell(5);// ??????

                            cell6.setCellStyle(cs5);
                            cell6.setCellValue(result.get(i).get("goods_unit")
                                    .toString());
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        try {
                            sum = sum + Double.parseDouble(result.get(i).get("goods_count")
                                    .toString());
                            Cell cell7 = rowColumnValue.createCell(6);// ??????

                            cell7.setCellStyle(cs5);
                            cell7.setCellValue(result.get(i).get("goods_count")
                                    .toString());
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        try {
                            Cell cell8 = rowColumnValue.createCell(7);// ??????
                            sumzl = sumzl + Double.parseDouble(result.get(i).get("tin_zhl")
                                    .toString());
                            cell8.setCellStyle(cs5);
                            cell8.setCellValue(result.get(i).get("tin_zhl")
                                    .toString());
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        try {

                            Cell cell9 = rowColumnValue.createCell(8);// ??????

                            cell9.setCellValue(result.get(i).get("shp_gui_ge").toString());
                            cell9.setCellStyle(cs5);
//							if(ResourceUtil.getConfigByName("systuopan").equals("yes")){
//								cell9.setCellValue(result.get(i).get("tuopan")
//										.toString());
//							}else{
//								cell9.setCellValue(result.get(i).get("tin_tj")
//										.toString());
//							}


                        } catch (Exception e) {
                            // TODO: handle exception
                        }


                        Cell cell10 = rowColumnValue.createCell(9);// ??????
                        try{
                            if("hr".equals(ResourceUtil.getConfigByName("wm.rkd"))) {
                                try{
//									 cell10.setCellValue(wmUtil.getstock(result.get(i).get("goods_id").toString()));
                                }catch (Exception e){

                                }
                            }
                        }catch (Exception e){

                        }
                        cell10.setCellStyle(cs5);

                        cerconNo++;
                    }
                    if(i== size){

                        cellsNum++;
                        Row rowColumnValue = sheet.createRow((short) cellsNum); // ??????
                        rowColumnValue.setHeight((short) 250);
                        Cell cell6 = rowColumnValue.createCell(6);// ??????
                        cell6.setCellValue(Double.toString(sum));
                        Cell cell7 = rowColumnValue.createCell(7);// ??????
                        cell7.setCellValue(Double.toString(sumzl));
//				cell6.setCellStyle(cs5);
                        Cell cell0 = rowColumnValue.createCell(0);// ??????
                        cell0.setCellValue("?????????");
//				cell0.setCellStyle(cs5);
                        CellRangeAddress c15 = new CellRangeAddress( cellsNum,
                                cellsNum, 0, 5);
                        sheet.addMergedRegion(c15);
                        cerconNo++;

                    }


                }
                Row rowColumnInfo = sheet.createRow((short) 1 + cellsNum); // ??????
                rowColumnInfo.setHeight((short) 250);
                rowColumnInfo.createCell(0).setCellValue(
                        "?????????                               ?????????                                ?????????                                ?????????");
                CellRangeAddress c15 = new CellRangeAddress(1 + cellsNum,
                        1 + cellsNum, 0, 9);
                sheet.addMergedRegion(c15);
                page++;
            } while (page<pagecount);
            fileOut = response.getOutputStream();
            HSSFPrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setPaperSize(HSSFPrintSetup.QUARTO_PAPERSIZE);
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @RequestMapping(params = "doPrintrkd")
    @ResponseBody
    public void downReceiveExcelrkd(WmImNoticeHEntity wmImNoticeH,
                                    HttpServletRequest request, HttpServletResponse response) {
        OutputStream fileOut = null;
        BufferedImage bufferImg = null;
//		String codedFileName = null;
        wmImNoticeH = systemService.getEntity(WmImNoticeHEntity.class,
                wmImNoticeH.getId());// ????????????

        // ????????????????????????????????????ByteArrayOutputStream??????????????????ByteArray
        try {
//			StringBuffer sber = new StringBuffer();

            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
//			bufferImg = ImageIO.read(BarcodeUtil.generateToStream(wmImNoticeH
//					.getNoticeId()));
            bufferImg = QRcodeUtil.createImage(wmImNoticeH
                    .getNoticeId());

            // ??????????????????????????????????????????
//			codedFileName = java.net.URLEncoder.encode("??????", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename="
                    + wmImNoticeH.getNoticeId() + "?????????.xls");
            ImageIO.write(bufferImg, "jpg", byteArrayOut);

            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(wmImNoticeH.getNoticeId());
            sheet.setMargin(HSSFSheet.TopMargin,0.1);// ??????????????????
            sheet.setMargin(HSSFSheet.BottomMargin,0.1);// ??????????????????
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
            String tsql = "SELECT wq.goods_pro_data as pro_data,wq.goods_unit, (select wmi.rec_deg from wm_in_qm_i wmi where wmi.im_notice_id = wq.order_id and wmi.goods_id =  wq.goods_id limit 1) as rec_deg, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,"
                    + " cast(sum(wq.goods_qua) as signed) as goods_count,truncate(sum(wq.goods_qua*mg.ti_ji_cm),2) tin_tj ,truncate(sum(wq.goods_qua*mg.zhl_kg),2) as tin_zhl,count(*) as tuopan     "
                    + "FROM wm_to_up_goods wq,mv_goods mg where wq.order_id = ? and  wq.goods_id = mg.goods_code group by wq.order_id, mg.goods_code,wq.goods_pro_data";
            List<Map<String, Object>> result = systemService
                    .findForJdbc(tsql, wmImNoticeH.getNoticeId());


            int size = result.size();
            int pagesize = 10;
            int pagecount = size%pagesize==0?size/pagesize:size/pagesize+1;
            double sum = 0;
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
                if(wmImNoticeH.getOrderTypeCode().equals("03")){
                    cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"???????????????");
                }else if(wmImNoticeH.getOrderTypeCode().equals("01")){
                    cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"???????????????");
                }else if(wmImNoticeH.getOrderTypeCode().equals("04")){
                    cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"?????????");
                }else if(wmImNoticeH.getOrderTypeCode().equals("09")){
                    cellTitle.setCellValue(ResourceUtil.getConfigByName("comname")+"???????????????");
                }


                cellTitle.setCellStyle(cs);

                Row rowHead1 = sheet.createRow((short) page*20+2); // ???????????????
                Cell cellHead1 = rowHead1.createCell(0);
                cellHead1.setCellValue("???????????????"+ ResourceUtil.getConfigByName("comaddr") );
                cellHead1.setCellStyle(cs1);

                Row rowHead2 = sheet.createRow((short) page*20+3); // ???????????????
                Cell cellHead2 = rowHead2.createCell(0);
                cellHead2.setCellValue("?????????"+ ResourceUtil.getConfigByName("comtel") );
                cellHead2.setCellStyle(cs1);


                Row rowHead4 = sheet.createRow((short) page*20+4); // ???????????????
                Cell cellHead4 = rowHead4.createCell(0);
                cellHead4.setCellValue("??????????????? " + wmImNoticeH.getImData() != null ? DateUtils.date2Str(wmImNoticeH.getImData(), DateUtils.date_sdf):"" );
                cellHead4.setCellStyle(cs2);

                Cell cellHead42 = rowHead4.createCell(3);
                cellHead42.setCellValue("??????????????? " +wmImNoticeH.getNoticeId());
                cellHead42.setCellStyle(cs2);

                Row rowHead5 = sheet.createRow((short) page*20+5); // ???????????????
                Cell cellHead5 = rowHead5.createCell(0);
                cellHead5.setCellValue("????????????????????? "+wmImNoticeH.getImCusCode() );
                cellHead5.setCellStyle(cs2);

                Cell cellHead52 = rowHead5.createCell(3);
                BaStoreEntity baStoreEntity = systemService.findUniqueByProperty(BaStoreEntity.class,"storeCode",wmImNoticeH.getStoreCode());
                if (baStoreEntity != null && StringUtils.isNotEmpty(baStoreEntity.getStoreCode())) {
                    cellHead52.setCellValue("????????? " +baStoreEntity.getStoreName());
                }else {
                    cellHead52.setCellValue("????????? ");
                }
               // cellHead52.setCellValue("????????? " +(StringUtils.isEmpty(wmImNoticeH.getPlatformCode())?"":wmImNoticeH.getPlatformCode()));
                cellHead52.setCellStyle(cs2);

                Row rowHead6 = sheet.createRow((short) page*20+6); // ???????????????
                Cell cellHead6 = rowHead6.createCell(0);
                MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmImNoticeH.getCusCode());

                cellHead6.setCellValue("????????? " +wmImNoticeH.getCusCode()+md.getZhongWenQch());
                cellHead6.setCellStyle(cs2);

                Cell cellHead62 = rowHead6.createCell(3);
                cellHead62.setCellValue("?????????/????????? "+wmImNoticeH.getImCarNo() );
                cellHead62.setCellStyle(cs2);

                Row rowHead7 = sheet.createRow((short) page*20+7); // ???????????????
                Cell cellHead7 = rowHead7.createCell(0);
                cellHead7.setCellValue("??????????????? " +md.getDianHua());
                cellHead7.setCellStyle(cs2);

                Cell cellHead72 = rowHead7.createCell(3);
                cellHead72.setCellValue("??????????????? "+ DateUtils.date2Str(DateUtils.getDate(), DateUtils.datetimeFormat) +"                        ???"+(page+1)+"???");
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
                CellRangeAddress c7 = new CellRangeAddress(page*20+7, page*20+7, 0, 2);//???7???????????????
                CellRangeAddress c72 = new CellRangeAddress(page*20+7, page*20+7, 3, 9);//???7???????????????
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
                cell73.setCellValue("??? ????????? ??? ????????? ???????????? ????????????  ");
                cell73.setCellStyle(cs52);


                CellRangeAddress c73 = new CellRangeAddress(page*20+0, page*20+19, 10, 10);//???7???????????????
                sheet.addMergedRegion(c73);

                Row rowColumnName = sheet.createRow((short) page*20+8); // ??????
                String[] columnNames = { "??????", "????????????", "????????????", "????????????", "????????????","??????", "??????", "??????KG","??????cm??","??????" };
                if(ResourceUtil.getConfigByName("systuopan").equals("yes")){
                    String[]   columnNamest = { "??????", "????????????", "????????????", "????????????", "????????????","??????", "??????", "??????KG","??????cm??","??????" };
                    columnNames = columnNamest;
                }
                try{
                    if("hr".equals(ResourceUtil.getConfigByName("wm.ckd"))){
                        String[] columnNames1 = { "??????", "????????????", "????????????", "????????????", "????????????","??????", "??????", "??????KG","??????cm??","??????" };
                        if(ResourceUtil.getConfigByName("systuopan").equals("yes")){
                            String[]   columnNamest1 = { "??????", "????????????", "????????????", "????????????", "????????????","??????", "??????", "??????KG","??????","??????" };
                            columnNames1 = columnNamest1;
                        }
                        columnNames = columnNames1;
                    }
                }catch ( Exception e){
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
                for (int i = page*pagesize; i < (page+1)*pagesize + oversize; i++) {
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

                            cell4.setCellStyle(cs5r);
                            cell4.setCellValue(result.get(i).get("pro_data")
                                    .toString());

                        } catch (Exception e) {
                            // TODO: handle exception

                        }

                        try {
                            Cell cell5 = rowColumnValue.createCell(4);// ??????

                            cell5.setCellStyle(cs5);
//							cell5.setCellValue(result.get(i)
//									.get("rec_deg").toString());
                            cell5.setCellValue("");
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        try {
                            Cell cell6 = rowColumnValue.createCell(5);// ??????

                            cell6.setCellStyle(cs5);
                            cell6.setCellValue(result.get(i).get("goods_unit")
                                    .toString());
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        try {
                            sum = sum + Double.parseDouble(result.get(i).get("goods_count")
                                    .toString());
                            Cell cell7 = rowColumnValue.createCell(6);// ??????

                            cell7.setCellStyle(cs5);
                            cell7.setCellValue(result.get(i).get("goods_count")
                                    .toString());
                        } catch (Exception e) {
                            // TODO: handle exception
                        }

                        try {
                            Cell cell8 = rowColumnValue.createCell(7);// ??????
                            sumzl = sumzl + Double.parseDouble(result.get(i).get("tin_zhl")
                                    .toString());
                            cell8.setCellStyle(cs5);
                            cell8.setCellValue(result.get(i).get("tin_zhl")
                                    .toString());
                        } catch (Exception e) {
                            // TODO: handle exception
                        }
                        try {

                            Cell cell9 = rowColumnValue.createCell(8);// ??????

                            cell9.setCellStyle(cs5);
                            cell9.setCellValue(result.get(i).get("tin_tj")
                                    .toString());
//							if(ResourceUtil.getConfigByName("systuopan").equals("yes")){
//								cell9.setCellValue(result.get(i).get("tuopan")
//										.toString());
//							}else{
//								cell9.setCellValue(result.get(i).get("tin_tj")
//										.toString());
//							}


                        } catch (Exception e) {
                            // TODO: handle exception
                        }


                        Cell cell10 = rowColumnValue.createCell(9);// ??????
                        try{
                            if("hr".equals(ResourceUtil.getConfigByName("wm.rkd"))) {
                                try{
//									 cell10.setCellValue(wmUtil.getstock(result.get(i).get("goods_id").toString()));
                                }catch (Exception e){

                                }
                            }
                        }catch (Exception e){

                        }
                        cell10.setCellStyle(cs5);

                        cerconNo++;
                    }
                    if(i== size){

                        cellsNum++;
                        Row rowColumnValue = sheet.createRow((short) cellsNum); // ??????
                        rowColumnValue.setHeight((short) 250);
                        Cell cell6 = rowColumnValue.createCell(6);// ??????
                        cell6.setCellValue(Double.toString(sum));
                        Cell cell7 = rowColumnValue.createCell(7);// ??????
                        cell7.setCellValue(Double.toString(sumzl));
//				cell6.setCellStyle(cs5);
                        Cell cell0 = rowColumnValue.createCell(0);// ??????
                        cell0.setCellValue("?????????");
//				cell0.setCellStyle(cs5);
                        CellRangeAddress c15 = new CellRangeAddress( cellsNum,
                                cellsNum, 0, 5);
                        sheet.addMergedRegion(c15);
                        cerconNo++;

                    }


                }
                Row rowColumnInfo = sheet.createRow((short) 1 + cellsNum); // ??????
                rowColumnInfo.setHeight((short) 250);
                rowColumnInfo.createCell(0).setCellValue(
                        "?????????                               ?????????                                ???????????????                              ????????????");
                CellRangeAddress c15 = new CellRangeAddress(1 + cellsNum,
                        1 + cellsNum, 0, 9);
                sheet.addMergedRegion(c15);
                page++;
            } while (page<pagecount);
            fileOut = response.getOutputStream();
            HSSFPrintSetup printSetup = sheet.getPrintSetup();
            printSetup.setPaperSize(HSSFPrintSetup.QUARTO_PAPERSIZE);
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
//??????????????????????????? ???????????????EXCEL

    @RequestMapping(params = "doPrintpagerkd")
    public ModelAndView doPrintpagerkd(String id,HttpServletRequest request) {
        PrintHeader printHeader  = new PrintHeader();
        WmImNoticeHEntity wmImNoticeH = systemService.getEntity(WmImNoticeHEntity.class,
                id);// ????????????
        if(wmImNoticeH.getOrderTypeCode().equals("03")){
            printHeader.setHeader01(ResourceUtil.getConfigByName("comname")+"???????????????");
        }else if(wmImNoticeH.getOrderTypeCode().equals("01")){
            printHeader.setHeader01(ResourceUtil.getConfigByName("comname")+"???????????????");
        }else if(wmImNoticeH.getOrderTypeCode().equals("04")){
            printHeader.setHeader01(ResourceUtil.getConfigByName("comname")+"?????????");
        }else if(wmImNoticeH.getOrderTypeCode().equals("09")){
            printHeader.setHeader01(ResourceUtil.getConfigByName("comname")+"???????????????");
        }



        printHeader.setHeader02("???????????????" );

        printHeader.setHeader03("?????????"+ ResourceUtil.getConfigByName("comtel") );

        printHeader.setHeader04("??????????????? " + wmImNoticeH.getImData() != null ?DateUtils.date2Str(wmImNoticeH.getImData(), DateUtils.date_sdf) :"" );

        printHeader.setHeader05("??????????????? " +wmImNoticeH.getNoticeId());

        printHeader.setHeader06("????????????????????? "+wmImNoticeH.getImCusCode() );

        printHeader.setHeader07("????????? " +wmImNoticeH.getPlatformCode());

        MdCusEntity md = systemService.findUniqueByProperty(MdCusEntity.class, "keHuBianMa", wmImNoticeH.getCusCode());

        printHeader.setHeader08("????????? " +wmImNoticeH.getCusCode()+md.getZhongWenQch());

        printHeader.setHeader09("???????????? "+(StringUtils.isEmpty(wmImNoticeH.getSupCode())||StringUtils.isEmpty(wmImNoticeH.getSupName())?"":wmImNoticeH.getSupCode()+ wmImNoticeH.getSupName()));


        printHeader.setHeader10("??????????????? " +md.getDianHua());

        printHeader.setHeader11("??????????????? "+ DateUtils.date2Str(DateUtils.getDate(), DateUtils.datetimeFormat)  );
        printHeader.setHeader14("????????? "+ wmImNoticeH.getImBeizhu()  );
        printHeader.setHeader15(wmImNoticeH.getCreateName());

        BaStoreEntity baStoreEntity = systemService.findUniqueByProperty(BaStoreEntity.class,"storeCode",wmImNoticeH.getStoreCode());
        if (baStoreEntity != null && StringUtils.isNotEmpty(baStoreEntity.getStoreCode())) {
            printHeader.setHeader16("????????? " +baStoreEntity.getStoreName());
        }else {
            printHeader.setHeader16("????????? ");
        }

        List<PrintItem> listitem = new ArrayList<>();

        String tsql = "SELECT wq.goods_pro_data as pro_data,wq.goods_unit, (select wmi.rec_deg from wm_in_qm_i wmi where wmi.im_notice_id = wq.order_id and wmi.goods_id =  wq.goods_id limit 1) as rec_deg, mg.goods_code, mg.goods_id,mg.shp_ming_cheng,"
                + " cast(sum(wq.goods_qua) as signed) as goods_count,truncate(sum(wq.goods_qua*mg.ti_ji_cm),2) tin_tj ,truncate(sum(wq.goods_qua*mg.zhl_kg),2) as tin_zhl,count(*) as tuopan     "
                + "FROM wm_to_up_goods wq,mv_goods mg where wq.order_id = ? and  wq.goods_id = mg.goods_code group by wq.order_id, mg.goods_code,wq.goods_pro_data";
        List<Map<String, Object>> result = systemService
                .findForJdbc(tsql, wmImNoticeH.getNoticeId());

        Double sum =0.00;
        Double	sumzl = 0.00;
        int cerconNo = 0;

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
            }
            try {
                printItem.setItem04(result.get(i)
                        .get("rec_deg").toString());
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {

                printItem.setItem05(result.get(i).get("goods_unit")
                        .toString());
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                sum = sum + Double.parseDouble(result.get(i).get("goods_count")
                        .toString());

                printItem.setItem06(result.get(i).get("goods_count")
                        .toString());
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                sumzl = sumzl + Double.parseDouble(result.get(i).get("tin_zhl")
                        .toString());
                printItem.setItem07(result.get(i).get("tin_zhl")
                        .toString());
            } catch (Exception e) {
                // TODO: handle exception
            }
            try {
                if(ResourceUtil.getConfigByName("systuopan").equals("yes")){
                    printItem.setItem08(result.get(i).get("tuopan")
                            .toString());
                }else{
                    printItem.setItem08(result.get(i).get("tin_tj")
                            .toString());
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            listitem.add(printItem);
        }

        printHeader.setHeader12(sum.toString());
        printHeader.setHeader13(sumzl.toString());
        request.setAttribute("printHeader", printHeader);

        request.setAttribute("listitem", listitem);

        return new ModelAndView("com/zzjee/wm/print/imnoticerkd-print");
    }


//??????????????????????????????????????????EXCEL
    /**
     * ??????????????????????????????
     *
     * @return
     */
    @RequestMapping(params = "doBatchDel")
    @ResponseBody
    public AjaxJson doBatchDel(String ids, HttpServletRequest request) {
        AjaxJson j = new AjaxJson();
        String message = "????????????????????????";
        try {
            for (String id : ids.split(",")) {
                WmImNoticeHEntity wmImNoticeH = systemService.getEntity(
                        WmImNoticeHEntity.class, id);
                WmPlatIoEntity wmPlatIo = systemService.findUniqueByProperty(
                        // ??????????????????
                        WmPlatIoEntity.class, "docId",
                        wmImNoticeH.getNoticeId());
                if (wmPlatIo != null) {
                    systemService.delete(wmPlatIo);
                }
                wmImNoticeH.setImSta(Constants.wm_sta3);
                wmImNoticeHService.saveOrUpdate(wmImNoticeH);
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
     * ????????????????????????
     *
     * @param
     * @return
     */
    @RequestMapping(params = "doAdd")
    @ResponseBody
    public AjaxJson doAdd(WmImNoticeHEntity wmImNoticeH,
                          WmImNoticeHPage wmImNoticeHPage, HttpServletRequest request) {
        List<WmImNoticeIEntity> wmImNoticeIList = wmImNoticeHPage
                .getWmImNoticeIList();
        AjaxJson j = new AjaxJson();
        String message = "????????????????????????";
        if(CollectionUtils.isEmpty(wmImNoticeIList)){
             message = "??????????????????????????????";
            throw new BusinessException(message);
        }
        try {

            String noticeid =  wmUtil.getNextNoticeid(wmImNoticeH.getOrderTypeCode()) ;
            wmImNoticeH.setNoticeId(noticeid);
            WmPlatIoEntity wmPlatIo = new WmPlatIoEntity();
            wmPlatIo.setCarno(wmImNoticeH.getImCarNo());
            wmPlatIo.setDocId(wmImNoticeH.getNoticeId());
            wmPlatIo.setPlanIndata(wmImNoticeH.getImData());
            wmPlatIo.setPlatId(wmImNoticeH.getPlatformCode());
            wmPlatIo.setPlatSta(Constants.wm_sta1);
            wmPlatIo.setPlatBeizhu("??????:" + wmImNoticeH.getImCarDri() + "??????:"
                    + wmImNoticeH.getImCarMobile());
            systemService.save(wmPlatIo);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id", wmImNoticeH.getNoticeId());
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
                    wmImNoticeH.setCusCode(user.getUserName());

                }
            }
            if(roles.equals("CUS")){
                wmImNoticeH.setImSta(Constants.wm_sta0);
            }else{
                wmImNoticeH.setImSta(Constants.wm_sta1);
            }
            if(wmImNoticeH.getCusCode()==null){
                if(roles.equals("CUS")){
                    wmImNoticeH.setCusCode(user.getUserName());
                }
            }
            //???????????????
            if(StringUtil.isNotEmpty(wmImNoticeH.getSupCode())){
                try{
                    MdSupEntity mdSupEntity = systemService.findUniqueByProperty(MdSupEntity.class,"gysBianMa",wmImNoticeH.getSupCode());
                    wmImNoticeH.setSupName(mdSupEntity.getZhongWenQch());
                }catch (Exception e){
                }
            }
            //
            //???????????????
            if(StringUtil.isNotEmpty(wmImNoticeH.getSupName())){
                try{
                    MdSupEntity mdSupEntity = systemService.findUniqueByProperty(MdSupEntity.class,"zhongWenQch",wmImNoticeH.getSupName());
                    wmImNoticeH.setSupCode(mdSupEntity.getGysBianMa());
                }catch (Exception e){
                }
            }
            //
            List<WmImNoticeIEntity> wmImNoticeIListnew = new ArrayList<WmImNoticeIEntity>();
            for (WmImNoticeIEntity wmImNoticeIEntity : wmImNoticeIList) {
                if(!StringUtil.isEmpty(wmImNoticeIEntity.getGoodsCode())){
                    try {
                        String goodsId = wmImNoticeIEntity.getGoodsCode().split("-")[0];
                        if(goodsId.endsWith("l")){
                            goodsId = goodsId.substring(0,goodsId.lastIndexOf("l"));
                        }
                        MvGoodsEntity mvgoods = systemService.findUniqueByProperty(MvGoodsEntity.class,"goodsId",goodsId);
//					String date[]=wmImNoticeIEntity.getGoodsCode().split("-");
                        long hiti = 0;
                        try {
                            if(StringUtil.isEmpty(wmImNoticeIEntity.getBinPlan())){
                                hiti = Long.parseLong(wmImNoticeIEntity.getGoodsCount())/ ( Long.parseLong(mvgoods.getMpCengGao()) * Long.parseLong(mvgoods.getMpDanCeng()) * Long.parseLong(mvgoods.getChlShl()));
                                wmImNoticeIEntity.setBinPlan(Long.toString(hiti));
                            }
                        } catch (Exception e) {
                        }
                        wmImNoticeIEntity.setGoodsCode(mvgoods.getGoodsCode());
                        wmImNoticeIEntity.setGoodsName(mvgoods.getShpMingCheng());
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    wmImNoticeIListnew.add(wmImNoticeIEntity);
                }
            }
            wmImNoticeHService.addMain(wmImNoticeH, wmImNoticeIListnew);
            try {
                TuiSongMsgUtil.sendMessage("????????????", Constants.SMS_SEND_TYPE_3,
                        "RKYYTZ", map, "admin", ResourceUtil.getSessionUserName()
                                .getUserName());
            } catch (Exception e) {
                // TODO: handle exception
            }

            systemService.addLog(message, Globals.Log_Type_INSERT,
                    Globals.Log_Leavel_INFO);
        } catch (Exception e) {
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
            yyUtil.getPord(formDate);
            yyUtil.getcprd(formDate);
            yyUtil.getqtrd(formDate);
        }
        if ("UAS".equals(ResourceUtil.getConfigByName("interfacetype"))){
            String masterbill[] = {"XKN_TEST","XKN_TEST"};
            for(int m =0;m<masterbill.length;m++) {
                try {
                    if (StringUtil.isEmpty(formDate)) {
                        formDate = "2011-01-01";
                    }
                    String master = masterbill[m];
                    String billclass[] = {"???????????????", "???????????????", "???????????????", "?????????????????????"};
                    for (int i = 0; i < billclass.length; i++) {
                        Map<String, Object> paramMap = new HashMap<String, Object>();
                        paramMap.put("lastUpdateTime", formDate);
                        paramMap.put("pi_class", billclass[i]);
                        paramMap.put("master", master);

                        billResult billResult = wmIntUtil.getBillin(paramMap);
                        for (int s = 0; s < billResult.getData().size(); s++) {
                            String imcuscode = billResult.getData().get(s).getPiInoutno();
                            if (StringUtil.isNotEmpty(imcuscode)) {
                                WmImNoticeHEntity wmimh = systemService.findUniqueByProperty(WmImNoticeHEntity.class, "imCusCode", imcuscode);
                                if (wmimh == null) {
                                    WmImNoticeHEntity wmImNoticeH = new WmImNoticeHEntity();
                                    List<WmImNoticeIEntity> wmImNoticeIListnew = new ArrayList<WmImNoticeIEntity>();

                                    wmImNoticeH.setOrderTypeCode("01");
                                    String noticeid = wmUtil.getNextNoticeid(wmImNoticeH.getOrderTypeCode());

                                    wmImNoticeH.setCusCode(ResourceUtil.getConfigByName("uas.cuscode"));
                                    wmImNoticeH.setNoticeId(noticeid);
                                    wmImNoticeH.setPlatformCode(Integer.toString(billResult.getData().get(s).getPiId()));
                                    wmImNoticeH.setPiClass(billResult.getData().get(s).getPiClass());
                                    wmImNoticeH.setPiMaster(master);
                                    wmImNoticeH.setSupCode(billResult.getData().get(s).getPiCardcode());
                                    MdSupEntity mdsup = systemService.findUniqueByProperty(MdSupEntity.class, "gysBianMa", wmImNoticeH.getSupCode());
                                    if (mdsup != null) {
                                        wmImNoticeH.setSupName(mdsup.getZhongWenQch());
                                    }
                                    wmImNoticeH.setImCusCode(imcuscode);
                                    wmImNoticeH.setSupName(billResult.getData().get(s).getPiReceivename());
                                    for (int k = 0; k < billResult.getData().get(s).getDetail().size(); k++) {
                                        WmImNoticeIEntity wmi = new WmImNoticeIEntity();
                                        wmi.setGoodsCode(billResult.getData().get(s).getDetail().get(k).getPdProdcode());
                                        MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
                                                MvGoodsEntity.class, "goodsCode", wmi.getGoodsCode());
                                        if (mvgoods != null) {
                                            wmi.setGoodsName(mvgoods.getGoodsName());
                                            wmi.setGoodsUnit(mvgoods.getShlDanWei());
                                        }
                                        wmi.setGoodsCount(Integer.toString(billResult.getData().get(s).getDetail().get(k).getPdInqty()));
//                               wmi.setGoodsPrdData(billResult.getData().get(s).getDetail().get(k).getPdProdmadedate2User());
                                        wmi.setOtherId(Integer.toString(billResult.getData().get(s).getDetail().get(k).getPdPdno()));
                                        wmImNoticeIListnew.add(wmi);
                                    }
                                    wmImNoticeHService.addMain(wmImNoticeH, wmImNoticeIListnew);
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
    public AjaxJson dopost(String id, HttpServletRequest request) {
        String message = null;
        AjaxJson j = new AjaxJson();
        message = "????????????";
        WmImNoticeHEntity wmImNoticeH = wmImNoticeHService.getEntity(WmImNoticeHEntity.class, id);

        //????????????
        Object id0 = wmImNoticeH.getNoticeId();
        //===================================================================================
        //??????-??????
        String hql0 = "from WmImNoticeIEntity where 1 = 1 AND iM_NOTICE_ID = ? ";
        try{
            List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService
                    .findHql(hql0, id0);
            List<Map<String,String>> list = new ArrayList<Map<String,String>>();
            for(WmImNoticeIEntity t:wmImNoticeIEntityList){

//                List<WmInQmIEntity> WmInQmlist = new ArrayList<WmInQmIEntity>();
                String  hql = null;
                hql = "from WmInQmIEntity t where t.imNoticeItem = ? ";

                List<WmInQmIEntity>  WmInQmlist = systemService.findHql(hql, new Object[] { t.getId() });
                for(WmInQmIEntity qm:WmInQmlist){

                    Map<String,String> map = new HashMap<String,String>();
                    //				[{"pd_pdno":1,"pd_outqty":"100","pi_class":"?????????","pi_id":50765226,"pi_inoutno":"JRS180800008"}]
                    map.put("pd_pdno",t.getOtherId());
                    map.put("pd_outqty",qm.getBaseGoodscount());
                    map.put("pi_class",wmImNoticeH.getPiClass());
                    map.put("pi_id",wmImNoticeH.getPlatformCode());
                    map.put("pi_inoutno",wmImNoticeH.getImCusCode());
                    map.put("pd_prodmadedate",qm.getProData());

                    MdGoodsEntity mvgoods  = systemService.findUniqueByProperty(
                            MdGoodsEntity.class, "shpBianMa",
                            qm.getGoodsId());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // ????????????
                    Date date = dateFormat.parse(qm.getProData()); // ????????????
                    if(mvgoods!=null){

                        Date newDate = addDate(date, Long.parseLong(mvgoods.getBzhiQi())); // ??????????????????10???
                        map.put("pd_replydate",dateFormat.format(newDate));
                    }else{
                        Date newDate = addDate(date, 1); // ??????????????????10???
                        map.put("pd_replydate ",qm.getProData());
                    }

                    list.add(map);

                }

            }
            String jsonStr = JSONArray.fromObject(list).toString();
            JSONArray ja = JSONArray.fromObject(jsonStr);
            resResult resResult = wmIntUtil.postBill(ja.toString(),wmImNoticeH.getPiMaster());
            j.setMsg(resResult.getDetailedMessage());
        }catch (Exception e){
            e.printStackTrace();
            message = "????????????";
            throw new BusinessException(e.getMessage());
        }

        j.setMsg(message);
        return j;
    }



    public static Date addDate(Date date, long day){
        long time = date.getTime(); // ??????????????????????????????
        day = day * 24 * 60 * 60 * 1000; // ????????????????????????????????????
        time += day; // ???????????????????????????
        return new Date(time); // ???????????????????????????
    }


    /**
     * ????????????????????????
     *
     * @param
     * @return
     */
    @RequestMapping(params = "doUpdate")
    @ResponseBody
    public AjaxJson doUpdate(WmImNoticeHEntity wmImNoticeH,
                             WmImNoticeHPage wmImNoticeHPage, HttpServletRequest request) {
        List<WmImNoticeIEntity> wmImNoticeIList = wmImNoticeHPage
                .getWmImNoticeIList();
        AjaxJson j = new AjaxJson();
        String message = "????????????";
        try {
            try {
                WmPlatIoEntity wmPlatIo = systemService.findUniqueByProperty(
                        WmPlatIoEntity.class, "docId", wmImNoticeH.getNoticeId());
                if (wmPlatIo != null) {
                    wmPlatIo.setCarno(wmImNoticeH.getImCarNo());
                    wmPlatIo.setDocId(wmImNoticeH.getNoticeId());
                    wmPlatIo.setPlanIndata(wmImNoticeH.getImData());
                    wmPlatIo.setPlatId(wmImNoticeH.getPlatformCode());
                    wmPlatIo.setPlatSta(Constants.wm_sta1);
                    wmPlatIo.setPlatBeizhu("??????" + wmImNoticeH.getImCarDri() + "??????"
                            + wmImNoticeH.getImCarMobile());
                    systemService.saveOrUpdate(wmPlatIo);
                }
            } catch (Exception e) {
                // TODO: handle exception
            }

            if(wmImNoticeH.getCusCode()==null){
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
                        wmImNoticeH.setCusCode(user.getUserName());

                    }
                }
            }


            wmImNoticeHService.updateMain(wmImNoticeH, wmImNoticeIList);
            systemService.addLog(message, Globals.Log_Type_UPDATE,
                    Globals.Log_Leavel_INFO);
        } catch (Exception e) {
            e.printStackTrace();
            message = "??????????????????????????????";
            throw new BusinessException(e.getMessage());
        }
        j.setMsg(message);
        return j;
    }

	/**
	 * ????????????????????????????????????
	 *
	 * @return
	 */
	@RequestMapping(params = "goAdd")
	public ModelAndView goAdd(ModelMap modelMap, WmImNoticeHEntity wmImNoticeH,
                              HttpServletRequest req) {
		if (StringUtil.isNotEmpty(wmImNoticeH.getId())) {
			wmImNoticeH = wmImNoticeHService.getEntity(WmImNoticeHEntity.class,
					wmImNoticeH.getId());
			req.setAttribute("wmImNoticeHPage", wmImNoticeH);

        }else{
            wmImNoticeH.setOrderTypeCode(req.getParameter("orderTypeCode").toString());
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
                    wmImNoticeH.setCusCode(user.getUserName());
                    wmImNoticeH.setReadonly("readonly");
                    wmImNoticeH.setWherecon("where cus_code = '"+user.getUserName()+"'");
                    modelMap.put("roleName", roles);
                    req.setAttribute("wmImNoticeHPage", wmImNoticeH);

                }else{
                    if(!StringUtil.isEmpty( wmImNoticeH.getCusCode())){
                        wmImNoticeH.setWherecon("where cus_code = '"+wmImNoticeH.getCusCode()+"'");
                    }else{
                        wmImNoticeH.setWherecon("where 1 = 1");
                    }


                    req.setAttribute("wmImNoticeHPage", wmImNoticeH);
                }
            }
        }




		return new ModelAndView("com/zzjee/wm/wmImNoticeH-add");
	}

    /**
     * ????????????????????????????????????
     *
     * @return
     */
    @RequestMapping(params = "goUpdate")
    public ModelAndView goUpdate(WmImNoticeHEntity wmImNoticeH,
                                 HttpServletRequest req) {
        if (StringUtil.isNotEmpty(wmImNoticeH.getId())) {
            wmImNoticeH = wmImNoticeHService.getEntity(WmImNoticeHEntity.class,
                    wmImNoticeH.getId());


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
                    wmImNoticeH.setCusCode(user.getUserName());
                    wmImNoticeH.setReadonly("readonly");
                    wmImNoticeH.setWherecon("where cus_code = '"+user.getUserName()+"'");
                }else{

                    wmImNoticeH.setWherecon("where 1 = 1");
                }
            }

            req.setAttribute("wmImNoticeHPage", wmImNoticeH);
        }
        return new ModelAndView("com/zzjee/wm/wmImNoticeH-update");
    }

    /**
     * ??????????????????[??????????????????]
     *
     * @return
     */
    @RequestMapping(params = "wmImNoticeIList")
    public ModelAndView wmImNoticeIList(WmImNoticeHEntity wmImNoticeH,
                                        HttpServletRequest req) {

        // ===================================================================================
        // ????????????
        Object id0 = wmImNoticeH.getNoticeId();
        // ===================================================================================
        // ??????-??????????????????
        String hql0 = "from WmImNoticeIEntity where 1 = 1 AND iM_NOTICE_ID = ? ";
        try {
            if(StringUtil.isNotEmpty(id0)){
                List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService
                        .findHql(hql0, id0);
                req.setAttribute("wmImNoticeIList", wmImNoticeIEntityList);
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return new ModelAndView("com/zzjee/wm/wmImNoticeIList");
    }

    /**
     * ??????excel
     *
     * @param request
     * @param response
     */
    @RequestMapping(params = "exportXls")
    public String exportXls(WmImNoticeHEntity wmImNoticeH,
                            HttpServletRequest request, HttpServletResponse response,
                            DataGrid dataGrid, ModelMap map) {
        CriteriaQuery cq = new CriteriaQuery(WmImNoticeHEntity.class, dataGrid);
        // ?????????????????????
        org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
                wmImNoticeH);
        try {
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
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        cq.add();
        List<WmImNoticeHEntity> list = this.wmImNoticeHService
                .getListByCriteriaQuery(cq, false);
        List<WmImNoticeHPage> pageList = new ArrayList<WmImNoticeHPage>();
        if (list != null && list.size() > 0) {
            for (WmImNoticeHEntity entity : list) {
                try {
                    WmImNoticeHPage page = new WmImNoticeHPage();
                    MyBeanUtils.copyBeanNotNull2Bean(entity, page);
                    Object id0 = entity.getNoticeId();
                    String hql0 = "from WmImNoticeIEntity where 1 = 1 AND iM_NOTICE_ID = ? ";
                    List<WmImNoticeIEntity> wmImNoticeIEntityList = systemService
                            .findHql(hql0, id0);
                    page.setWmImNoticeIList(wmImNoticeIEntityList);
                    pageList.add(page);
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            }
        }
        map.put(NormalExcelConstants.FILE_NAME, "??????????????????");
        map.put(NormalExcelConstants.CLASS, WmImNoticeHPage.class);
        map.put(NormalExcelConstants.PARAMS, new ExportParams("????????????????????????",
                "?????????:admin", "????????????"));
        map.put(NormalExcelConstants.DATA_LIST, pageList);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * ??????excel????????????
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping(params = "importExcel", method = RequestMethod.POST)
    @ResponseBody
    public  AjaxJson importExcel(HttpServletRequest request,
                                HttpServletResponse response) {
        AjaxJson j = new AjaxJson();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();

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

        }


        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();// ????????????????????????
            ImportParams params = new ImportParams();
            params.setTitleRows(2);
            params.setHeadRows(1);
            params.setNeedSave(true);
            try {
                List<WmNoticeImpPage> list = ExcelImportUtil.importExcel(
                        file.getInputStream(), WmNoticeImpPage.class, params);

                String flag = "Y";
                String message="";
                for(WmNoticeImpPage wmt:list){
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

                List<WmNoticeImpPage> listheader =  ExcelImportUtil.importExcel(
                        file.getInputStream(), WmNoticeImpPage.class, params);
                for(int i=0; i<listheader.size()-1;i++){
                    for(int  k=listheader.size()-1;k>i;k--){
                        if  (listheader.get(k).getImCusCode().equals(listheader.get(i).getImCusCode()))  {
                            listheader.remove(k);
                        }
                    }
                }

                for(WmNoticeImpPage pageheader: listheader){
//                    List<WmImNoticeHEntity>  wmimh = systemService.findByProperty(WmImNoticeHEntity.class, "imCusCode", pageheader.getImCusCode());
//                    if(wmimh!=null&&wmimh.size()>0){
//                        continue;
//                    }
                    List<WmImNoticeIEntity> wmImNoticeIListnew = new ArrayList<WmImNoticeIEntity>();
                    for (WmNoticeImpPage page : list) {
                        if(pageheader.getImCusCode().equals(page.getImCusCode())){
                            WmImNoticeIEntity wmi = new WmImNoticeIEntity();
                            wmi.setGoodsCode(page.getGoodsId());
                            MvGoodsEntity mvgoods = systemService.findUniqueByProperty(
                                    MvGoodsEntity.class, "goodsCode", wmi.getGoodsCode());
                            if (mvgoods != null) {
                                wmi.setGoodsName(mvgoods.getGoodsName());
                                wmi.setGoodsUnit(mvgoods.getShlDanWei());
                            }
                            try{
                                wmi.setGoodsCount(page.getGoodsQua());
                                String[] args=page.getGoodsQua().split("\\.");
                                wmi.setGoodsCount(args[0]);
                            }catch (Exception e){

                            }
                            wmi.setOtherId(page.getOtherId());
                            wmImNoticeIListnew.add(wmi);
                        }
                    }

                    WmImNoticeHEntity wmImNoticeH  = new WmImNoticeHEntity();

                    if(roles.equals("CUS")){
                        wmImNoticeH.setImSta(Constants.wm_sta0);
                    }else{
                        wmImNoticeH.setImSta(Constants.wm_sta1);
                    }
                    wmImNoticeH.setOrderTypeCode(pageheader.getOrderTypeCode());
                    String noticeid = wmUtil.getNextNoticeid(wmImNoticeH.getOrderTypeCode());
                    wmImNoticeH.setCusCode(pageheader.getCusCode());
                    wmImNoticeH.setNoticeId(noticeid);
                    wmImNoticeH.setImData(pageheader.getImData());
                    wmImNoticeH.setPlatformCode(pageheader.getCusCode());
                    wmImNoticeH.setImBeizhu(pageheader.getImBeizhu() );
                    wmImNoticeH.setSupCode(pageheader.getOcusCode() );
                    MdSupEntity mdsup  = systemService.findUniqueByProperty(MdSupEntity.class,"gysBianMa",wmImNoticeH.getSupCode());
                    if(mdsup!=null){
                        wmImNoticeH.setSupName(mdsup.getZhongWenQch());
                    }
                    wmImNoticeH.setImCusCode(pageheader.getImCusCode());

                    wmImNoticeHService.addMain(wmImNoticeH, wmImNoticeIListnew);
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

    /**
     * ??????excel ?????????
     */
    @RequestMapping(params = "exportXlsByT")
    public String exportXlsByT(ModelMap map) {
        map.put(NormalExcelConstants.FILE_NAME, "????????????");
        map.put(NormalExcelConstants.CLASS, WmNoticeImpPage.class);
        map.put(NormalExcelConstants.PARAMS, new ExportParams("????????????",
                "?????????:" + ResourceUtil.getSessionUserName().getRealName(),
                "????????????"));
        map.put(NormalExcelConstants.DATA_LIST, new ArrayList());
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * ??????????????????
     *
     * @return
     */
    @RequestMapping(params = "upload")
    public ModelAndView upload(HttpServletRequest req) {
        req.setAttribute("controller_name", "wmImNoticeHController");
        return new ModelAndView("common/upload/pub_excel_upload");
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<WmImNoticeHEntity> list() {
        List<WmImNoticeHEntity> listWmImNoticeHs = wmImNoticeHService
                .getList(WmImNoticeHEntity.class);
        return listWmImNoticeHs;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    @ResponseBody
    public ResponseEntity<?> get(@PathVariable("id") String id) {
        WmImNoticeHEntity task = wmImNoticeHService.get(
                WmImNoticeHEntity.class, id);
        if (task == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/apicreate")
    @ResponseBody
    public ResponseEntity<?> create(
            @RequestBody WmImNoticeHPage wmImNoticeHPage) {
        // ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
        ResultDO D0 = new ResultDO();
        try{//?????????????????????????????????
            String hql = "from WmImNoticeHEntity where imCusCode = ? ";
            String imCusCode = wmImNoticeHPage.getImCusCode();
            if(StringUtil.isNotEmpty(imCusCode)){
                List<WmImNoticeHEntity> listim = systemService.findHql(hql,imCusCode);
                if(listim!=null&&listim.size()>0){
                    D0.setOK(true);
                    return new ResponseEntity(D0, HttpStatus.OK);
                }
            }
        }catch (Exception e){
        }
        // ??????
        List<WmImNoticeIEntity> wmImNoticeIList = wmImNoticeHPage
                .getWmImNoticeIList();

        WmImNoticeHEntity wmImNoticeH = new WmImNoticeHEntity();
        try {
            String noticeid =  wmUtil.getNextNoticeid(wmImNoticeH.getOrderTypeCode()) ;
            wmImNoticeHPage.setNoticeId(noticeid);
            wmImNoticeHPage.setImSta("?????????");
            MyBeanUtils.copyBeanNotNull2Bean(wmImNoticeHPage,wmImNoticeH);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        List<WmImNoticeIEntity> wmOmNoticeIListnew = new ArrayList<>();
        for(WmImNoticeIEntity t: wmImNoticeIList){
            try{
                MdGoodsEntity md =systemService.findUniqueByProperty(MdGoodsEntity.class,"shpBianMa",t.getGoodsCode());
                wmImNoticeH.setCusCode(md.getSuoShuKeHu());
            }catch ( Exception e){

            }
            t.setBinPre("I");
            wmOmNoticeIListnew.add(t);
        }
        wmImNoticeHService.addMain(wmImNoticeH, wmOmNoticeIListnew);
        D0.setOK(true);
        return new ResponseEntity(D0, HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody WmImNoticeHPage wmImNoticeHPage) {
        // ??????JSR303 Bean Validator????????????????????????????????????400????????????json?????????????????????.
        Set<ConstraintViolation<WmImNoticeHPage>> failures = validator
                .validate(wmImNoticeHPage);
        if (!failures.isEmpty()) {
            return new ResponseEntity(
                    BeanValidators.extractPropertyAndMessage(failures),
                    HttpStatus.BAD_REQUEST);
        }

        // ??????
        List<WmImNoticeIEntity> wmImNoticeIList = wmImNoticeHPage
                .getWmImNoticeIList();

        WmImNoticeHEntity wmImNoticeH = new WmImNoticeHEntity();
        try {
            MyBeanUtils.copyBeanNotNull2Bean(wmImNoticeH, wmImNoticeHPage);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        wmImNoticeHService.updateMain(wmImNoticeH, wmImNoticeIList);

        // ???Restful???????????????204?????????, ?????????. ???????????????200?????????.
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        WmImNoticeHEntity wmImNoticeH = wmImNoticeHService.get(
                WmImNoticeHEntity.class, id);
        wmImNoticeHService.delMain(wmImNoticeH);
    }
}
