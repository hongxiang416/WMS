package com.zzjee.api;

import com.alibaba.fastjson.support.odps.udf.CodecCheck;
import com.zzjee.BI.BiBinController;
import com.zzjee.BI.biEntity;
import com.zzjee.md.controller.MdGoodsController;
import com.zzjee.md.entity.MdCusEntity;
import com.zzjee.md.entity.MdGoodsEntity;
import com.zzjee.md.entity.MvGoodsEntity;
import com.zzjee.rfid.entity.RfidBuseEntity;
import com.zzjee.uniapp.entity.WmsAppFunctionEntity;
import com.zzjee.uniapp.entity.WmsAppRoleEntity;
import com.zzjee.uniapp.entity.WmsAppUserEntity;
import com.zzjee.uniapp.service.WmsAppFunctionServiceI;
import com.zzjee.uniapp.service.WmsAppRoleServiceI;
import com.zzjee.uniapp.service.WmsAppUserServiceI;
import com.zzjee.wave.controller.WaveToDownController;
import com.zzjee.wave.controller.WaveToFjController;
import com.zzjee.wm.controller.*;
import com.zzjee.wm.entity.WmImNoticeHEntity;
import com.zzjee.wm.entity.WmImNoticeIEntity;
import com.zzjee.wm.entity.WmInQmIEntity;
import com.zzjee.wmapi.controller.WvGiController;
import com.zzjee.wmapi.controller.WvGiNoticeController;
import com.zzjee.wmapi.controller.WvNoticeController;
import com.zzjee.wmapi.entity.WvNoticeEntity;
import com.zzjee.wmutil.wmUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.HttpMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.util.DateUtils;
import org.jeecgframework.core.util.JSONHelper;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.jwt.util.ResponseMessage;
import org.jeecgframework.jwt.util.Result;
import org.jeecgframework.p3.core.author.LoginUser;
import org.jeecgframework.web.system.pojo.base.TSBaseUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.util.*;

/**
 * @Package com.zzjee.api
 * @date 2021/7/22 10:47
 * @description
 */
@RestController
@RequestMapping("/pdaapi")
public class ApiController {
    @Autowired
    private SystemService systemService;
    @Autowired
    private UserService userService;
    @Autowired
    private WvNoticeController wvNoticeController;
    @Autowired
    private WmInQmIController wmInQmIController;
    @Autowired
    private WmToUpGoodsController wmToUpGoodsController;
    @Autowired
    private WvGiNoticeController wvGiNoticeController;
    @Autowired
    private WmToDownGoodsController wmToDownGoodsController;
    @Autowired
    private WvGiController wvGiController;
    @Autowired
    private WaveToDownController waveToDownController;
    @Autowired
    private WaveToFjController waveToFjController;
    @Autowired
    private WvStockController wvStockController;
    @Autowired
    private WmToMoveGoodsController wmToMoveGoodsController;
    @Autowired
    private WmSttInGoodsController wmSttInGoodsController;
    @Autowired
    private MdGoodsController mdGoodsController;
    @Autowired
    private wmomController wmomController;
    private static final Logger logger = Logger.getLogger(ApiController.class);
    @Autowired
    private WmsAppUserServiceI wmsAppUserService;

    @Autowired
    private WmsAppRoleServiceI wmsAppRoleService;
    @Autowired
    private WmsAppFunctionServiceI wmsAppFunctionService;

    @Autowired
    private BiBinController biBinController;

    //??????????????????begin
    //????????????
    @RequestMapping(value = "/wvNoticeController/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list1(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2) {
        return wvNoticeController.list(username, searchstr, searchstr2);
    }

    //????????????
    @RequestMapping(value = "/wmInQmIController/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create1(@RequestParam String wmInQmIstr,
                                     UriComponentsBuilder uriBuilder) {
        return wmInQmIController.create(wmInQmIstr, uriBuilder);
    }


    //??????????????????begin
    //????????????
    @RequestMapping(value = "/update/qty", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> update1(@RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "id", required = false) String id,
                                     @RequestParam(value = "searchstr", required = false) String searchstr,
                                     @RequestParam(value = "searchstr2", required = false) String searchstr2) {
        return wmInQmIController.updateqty(username, id, searchstr, searchstr2);
    }

    //??????????????????end

    //??????????????????begin
    //????????????
    @RequestMapping(value = "/wmInQmIController/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list2(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2) {
        return wmInQmIController.list(username, searchstr, searchstr2);
    }

    //????????????
    @RequestMapping(value = "/wmToUpGoodsController/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create2(@RequestParam String wmToUpGoodsstr, UriComponentsBuilder uriBuilder) {
        return wmInQmIController.save(wmToUpGoodsstr, uriBuilder);
    }
    //??????????????????end

    //??????????????????begin
    //??????????????????
    //????????????  PDA??????
    @RequestMapping(value = "/wvGiNoticeController/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list3(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2,
                                   @RequestParam(value = "searchstr3", required = false) String searchstr3) {
        return wvGiNoticeController.lists(username, searchstr, searchstr2, searchstr3);
    }

    //????????????
    //??????
    @RequestMapping(value = "/wmToDownGoodsController/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public synchronized ResponseEntity<?> create3(@RequestParam String wmToDownGoodsstr,
                                                  UriComponentsBuilder uriBuilder) {
        return wmToDownGoodsController.create(wmToDownGoodsstr, uriBuilder);
    }
    //??????????????????end

    //??????????????????
    @RequestMapping(value = "/wvGiController/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list4(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2) {
        return wvGiController.list(username, searchstr, searchstr2);
    }

    //??????????????????
    @RequestMapping(value = "/wmToDownGoodsController/change", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update4(@RequestParam String wmToDownGoodsstr,
                                     UriComponentsBuilder uriBuilder) {
        return wmToDownGoodsController.update(wmToDownGoodsstr, uriBuilder);
    }

    // TODO: 2022/6/25 ????????????
    //??????????????????
    @RequestMapping(value = "/waveToDownController/list/todown", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list5(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2,
                                   @RequestParam(value = "searchstr3", required = false) String searchstr3) {
        return waveToDownController.list(username, searchstr, searchstr2, searchstr3);
    }

    //??????????????????
    @RequestMapping(value = "/waveToDownController/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create5(@RequestParam String waveToDownstr, UriComponentsBuilder uriBuilder) {
//        waveToDown.getWaveId(),waveToDown.getGoodsId(),waveToDown.getProData(),waveToDown.getTinId(),waveToDown.getBinId()
//         ??????????????????????????????????????????????????????????????? ?????????
        return waveToDownController.create(waveToDownstr, uriBuilder);
    }

    //??????????????????

    @RequestMapping(value = "/waveToFjController/list/tofj", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list6(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2,
                                   @RequestParam(value = "searchstr3", required = false) String searchstr3,
                                   @RequestParam(value = "searchstr4", required = false) String searchstr4,//????????????
                                   @RequestParam(value = "searchstr5", required = false) String searchstr5) {
        return waveToFjController.list(username, searchstr, searchstr2, searchstr3, searchstr4, searchstr5);
    }

    //??????????????????
    @RequestMapping(value = "/waveToFjController/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> create6(@RequestParam String waveToFjstr, UriComponentsBuilder uriBuilder) {
        //?????????ID??????????????????
        return waveToFjController.create(waveToFjstr, uriBuilder);
    }

    //????????????
    @RequestMapping(value = "/wvStockController/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list7(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2,
                                   @RequestParam(value = "searchstr3", required = false) String searchstr3) {
        return wvStockController.list(username, searchstr, searchstr2,searchstr3);
    }

    //????????????
    public static final String ToMoveGoodsController = "/rest/wmToMoveGoodsController";

    @RequestMapping(value = "/wmToMoveGoodsController/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list8(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2) {
        return wmToMoveGoodsController.list(username, searchstr, searchstr2);
    }
    //????????????

    @RequestMapping(value = "/wmToMoveGoodsController/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update8(@RequestParam String wmToMoveGoodsstr,
                                     UriComponentsBuilder uriBuilder) {
        return wmToMoveGoodsController.update(wmToMoveGoodsstr, uriBuilder);
    }

    //????????????
    //PDA??????
    @RequestMapping(value = "/wmSttInGoodsController/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list9(@RequestParam(value = "username", required = false) String username,
                                   @RequestParam(value = "searchstr", required = false) String searchstr,
                                   @RequestParam(value = "searchstr2", required = false) String searchstr2) {
        return wmSttInGoodsController.list(username, searchstr, searchstr2);
    }

    //????????????
    @RequestMapping(value = "/wmSttInGoodsController/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update9(@RequestParam String wmSttInGoodsstr,
                                     UriComponentsBuilder uriBuilder) {
        return wmSttInGoodsController.update(wmSttInGoodsstr, uriBuilder);
    }

    //????????????
    public static final String GoodsController = "/rest/mdGoodsController";

    @RequestMapping(value = "/mdGoodsController/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> list10(@RequestParam(value = "username", required = false) String username,
                                    @RequestParam(value = "all", required = false) String all,
                                    @RequestParam(value = "searchstr", required = false) String searchstr,
                                    @RequestParam(value = "searchstr2", required = false) String searchstr2,
                                    @RequestParam(value = "searchstrin1", required = false) String searchstrin1,
                                    @RequestParam(value = "searchstrin2", required = false) String searchstrin2,
                                    @RequestParam(value = "searchstrin3", required = false) String searchstrin3) {
        return mdGoodsController.list(username, all, searchstr, searchstr2, searchstrin1, searchstrin2, searchstrin3);
    }

    //??????????????????

    @RequestMapping(value = "/mdGoodsController/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update10(@RequestParam String mdGoodsstr,
                                      UriComponentsBuilder uriBuilder) {
        return mdGoodsController.update(mdGoodsstr, uriBuilder);
    }

    //PDA?????????????????? -????????????????????????
    @RequestMapping(value = "/wvStockController/pdazysave", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> update11(@RequestParam(value = "id", required = false) String id ,

                                      @RequestParam (value = "username", required = false) String username ,
                                      @RequestParam(value = "binto", required = false) String binto ,
                                      @RequestParam(value = "tinto", required = false) String tinto ,
                                      @RequestParam(value = "goodsqua", required = false) String goodsqua ,
                                     UriComponentsBuilder uriBuilder) {
        return wvStockController.doSttpda(id,username,binto,tinto, goodsqua,uriBuilder);
    }
    //????????????
    @RequestMapping(value = "/mdGoodsController/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> xiadan(@RequestParam String mdGoodsstr,
                                    UriComponentsBuilder uriBuilder) {
        return mdGoodsController.xiadan(mdGoodsstr, uriBuilder);
    }

    @RequestMapping(value = "/weightsave/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<?> list10(@PathVariable("username") String username,
                                     @RequestParam(value = "searchstr", required = false) String searchstr) {
        return wmomController.weight_save(username, searchstr);
    }

    @RequestMapping(value = "/rfidsave/{username}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "RFID??????", produces = "application/json", httpMethod = "POST")
    public ResponseMessage<?> rfid_save(@PathVariable("username") String username,
                                        @RequestBody wmientity wmientityin,
                                        HttpServletRequest request) {

        return wmomController.rfid_save(username, wmientityin, request);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultApi<?> login(@RequestBody TSBaseUser tsBaseUser, HttpServletRequest request) {
        logger.info("??????TOKEN[{}]" + tsBaseUser.getUserName());
        ResultDO D0 = new ResultDO();
        // ??????
        if (org.apache.commons.lang3.StringUtils.isEmpty(tsBaseUser.getUserName())) {
            return ResultApi.error("????????????????????????!");
        }
        // ??????
        if (StringUtils.isEmpty(tsBaseUser.getUserName())) {
            return ResultApi.error("????????????????????????!");
        }
        TSUser user = userService.checkUserExits(tsBaseUser.getUserName(), tsBaseUser.getPassword());
        if (user == null) {
            D0.setErrorMsg("????????????????????????!");
            D0.setOK(false);
            return ResultApi.error("??????TOKEN,??????????????????[{}]!");
        }
        return ResultApi.OK(user);
    }

    /**
     * ??????id??????
     *
     * @return
     */

    @RequestMapping(value = "/appfunctionList/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<?> appfunctionList(@PathVariable("username") String username,
                                              HttpServletRequest request) {
        //????????????????????????APP??????
        CriteriaQuery queryuser = new CriteriaQuery(WmsAppUserEntity.class);
        queryuser.eq("appuserCode", username);
        List<WmsAppUserEntity> listByCriteriaQuery = wmsAppUserService.getListByCriteriaQuery(queryuser, false);
        if (CollectionUtils.isEmpty(listByCriteriaQuery)) {
            return Result.error("?????????APP???????????????");
        }
        //??????????????????Id
        String roleId = listByCriteriaQuery.get(0).getApproleCode();
        System.err.println(roleId);
        //??????????????????Id?????????APP????????????
        CriteriaQuery queryrole = new CriteriaQuery(WmsAppRoleEntity.class);
        queryrole.in("approleCode", roleId.split(","));
        List<WmsAppRoleEntity> mesAppRole = wmsAppRoleService.getListByCriteriaQuery(queryrole, false);
        if (mesAppRole == null) {
            return Result.error("?????????APP???????????????");
        }
        String funidstr = "";
        for (WmsAppRoleEntity role : mesAppRole) {
            //???????????????APP??????id
            funidstr = funidstr + "," + role.getAppmodelCode();
            System.err.println(funidstr);
        }
        //??????APP??????id?????????APP??????????????????
        CriteriaQuery funcwrapper = new CriteriaQuery(WmsAppFunctionEntity.class);
        funcwrapper.in("appmodelCode", funidstr.split(","));
        List<WmsAppFunctionEntity> mesAppFunctions = wmsAppFunctionService.getListByCriteriaQuery(funcwrapper, false);
        if (mesAppFunctions == null) {
            return Result.error("?????????APP??????????????????");
        }
        return Result.success(mesAppFunctions);
    }
    //????????????

    @RequestMapping(value = "/bi/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> bilist(@RequestParam(value = "username", required = false) String username) {
        biEntity biEntity = biBinController.getBi(username);
        ResultDO D0 = new ResultDO();
        D0.setOK(false);
        D0.setObj(biEntity);
        return new ResponseEntity(D0, HttpStatus.OK);
    }

    @RequestMapping(value = "/annountCement/listByUser/{username}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMessage<?> messagelist(@PathVariable("username") String username,
                                          HttpServletRequest request) {
        List<TSUser> roles = systemService.findByProperty(TSUser.class, "userName", username);
        TSUser user = null;
        String userid = "no";
        try {
            user = roles.get(0);
            userid = user.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "SELECT notice.*,noticeRead.is_read as is_read FROM t_s_notice notice "
                + " LEFT JOIN t_s_notice_read_user noticeRead ON  notice.id = noticeRead.notice_id "
                + " WHERE noticeRead.del_flag = 0 and noticeRead.user_id = '" + userid + "' "
                + " ORDER BY noticeRead.is_read asc,noticeRead.create_time DESC ";
        List<Map<String, Object>> resultList = systemService.findForJdbc(sql);
        List<AnnouncementSendModel> noticeList = new ArrayList<AnnouncementSendModel>();
        if (resultList != null && resultList.size() > 0) {
            for (int i = 0; i < resultList.size(); i++) {
                AnnouncementSendModel announcementSendModel = new AnnouncementSendModel();
                Map<String, Object> obj = resultList.get(i);
                announcementSendModel.setTitile(String.valueOf(obj.get("notice_title")));
                announcementSendModel.setStartTime(String.valueOf(obj.get("create_time")));
                announcementSendModel.setMsgAbstract(String.valueOf(obj.get("notice_title")));
                announcementSendModel.setMsgContent(String.valueOf(obj.get("notice_content")));
                noticeList.add(announcementSendModel);
            }
        }
        return Result.success(noticeList);
    }
}
