package com.example.subway.controller;


import com.example.subway.pojo.SiteInfo;
import com.example.subway.pojo.SiteRelationShip;
import com.example.subway.pojo.SiteSubwayRoute;
import com.example.subway.pojo.SubwayInfo;
import com.example.subway.service.SiteInfoService;
import com.example.subway.service.SiteRelationShipService;
import com.example.subway.service.SiteSubwayRouteService;
import com.example.subway.service.SubwayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/subwayInfo")
public class SubwayController {

    @Autowired
    private SubwayInfoService subwayInfoService;
    @Autowired
    private SiteSubwayRouteService siteSubwayRouteService;
    @Autowired
    private SiteRelationShipService siteRelationShipService;
    @Autowired
    private SiteInfoService siteInfoService;

    @GetMapping("/{name}")
    List<SiteInfo> querySubway(@PathVariable String name){
        return getSubway(name);
    }

    public  List<SiteInfo> getSubway(String subwayName) {
        List<SiteInfo> siteInfos = new ArrayList<>();

        SubwayInfo subwayInfo = subwayInfoService.findByName(subwayName);

        int subwayId = subwayInfo.getId();

        List<SiteSubwayRoute> siteSubwayRoutes = new ArrayList<>();
        siteSubwayRoutes = siteSubwayRouteService.findBySubwayId(subwayId);

//        获得这条路线的所有站点id
        List<Integer> siteId = new ArrayList<>();
        for (SiteSubwayRoute r : siteSubwayRoutes) {
            siteId.add(r.getSiteId());
        }

//        获得起始站点
        SiteInfo startSite = siteInfoService.findById(subwayInfo.getStartSiteId()).get();

        siteInfos.add(startSite);

        for (int i = 0; i < subwayInfo.getSiteSize()-1; i++) {
            SiteRelationShip siteRelationShip = siteRelationShipService.findByFromIdAndSubwayId(startSite.getId(),subwayId);
            startSite = siteInfoService.findById(siteRelationShip.getToId()).get();
            siteInfos.add(startSite);
        }
        return siteInfos;
    }

    /**
     * 在这条地铁站的中间增加一个站点，所以需要前后站点的id
     * <br>如下addEnd方法</br>
     * @param subwayId 地铁线的Id
     * @param fromId 前一个站点的id
     * @param currName 新建站点的名称
     * @param Abstract 新建站点的摘要
     * @param toId 后一个站点的id
     */
    @PostMapping("/middle")
    void addMiddle(Integer subwayId,Integer fromId,String currName,String Abstract,Integer toId){
        SiteInfo siteInfo = new SiteInfo();
        siteInfo.setName(currName);
        siteInfo.setAbstract(Abstract);
        SiteInfo siteInfo1 = siteInfoService.save(siteInfo);


        siteRelationShipService.save(new SiteRelationShip(fromId,siteInfo1.getId(),subwayId));
        siteRelationShipService.save(new SiteRelationShip(siteInfo1.getId(),toId,subwayId));

        siteRelationShipService.deleteByFromIdAndToIdAndSubwayId(fromId,toId,subwayId);

        SubwayInfo subwayInfo = subwayInfoService.findById(subwayId).get();
        subwayInfo.setSiteSize(subwayInfo.getSiteSize()+1);
        subwayInfoService.updateSiteSizeAndStartSiteIdAndEndSiteIdAndNameById(subwayInfo.getSiteSize(),subwayInfo.getStartSiteId(),subwayInfo.getEndSiteId(), subwayInfo.getName(), subwayId);

        siteSubwayRouteService.save(new SiteSubwayRoute(siteInfo1.getId(),subwayId));
    }

    /**
     * 在这条地铁线的头部增加一个站点
     * <br>如下addEnd方法</br>
     * @param subwayId 地铁线的Id
     * @param currName 新建站点的名称
     * @param Abstract 新建站点的摘要
     */
    @PostMapping("/head")
    void addHead(Integer subwayId,String currName,String Abstract){
        SiteInfo save = siteInfoService.save(new SiteInfo(currName, Abstract));

        SubwayInfo subwayInfo = subwayInfoService.findById(subwayId).get();
        subwayInfoService.updateSiteSizeAndStartSiteIdAndEndSiteIdAndNameById(subwayInfo.getSiteSize()+1,save.getId(),subwayInfo.getEndSiteId(), subwayInfo.getName(), subwayId);

        siteRelationShipService.save(new SiteRelationShip(save.getId(),subwayInfo.getStartSiteId(),subwayId));

        siteSubwayRouteService.save(new SiteSubwayRoute(save.getId(),subwayId));
    }

    /**
     * 在这条地铁线的末尾增加一个站点
     * <br> http://localhost:8080/subwayInfo/end?subwayId=8809&currName=EndSite&Abstract=EndSite摘要</br>
     * @param subwayId 在哪条地铁站新增站点
     * @param currName 新增站点的站点名
     * @param Abstract 新增站点的站点摘要
     */
    @PostMapping("/end")
    void addEnd(Integer subwayId,String currName,String Abstract){
        SiteInfo save = siteInfoService.save(new SiteInfo(currName, Abstract));

        SubwayInfo subwayInfo = subwayInfoService.findById(subwayId).get();
        subwayInfoService.updateSiteSizeAndStartSiteIdAndEndSiteIdAndNameById(subwayInfo.getSiteSize()+1,subwayInfo.getStartSiteId(),save.getId(), subwayInfo.getName(), subwayId);

        siteRelationShipService.save(new SiteRelationShip(subwayInfo.getEndSiteId(),save.getId(),subwayId));

        siteSubwayRouteService.save(new SiteSubwayRoute(save.getId(),subwayId));
    }

    /**
     * http://localhost:8080/subwayInfo/querySubway/8816
     * @param siteId 需要查询的站点Id                  8816为站点的id
     * @return 经过这个站点的地铁路线信息
     */
    @GetMapping("/querySubway/{siteId}")
    List<SubwayInfo> querySiteOfSubway(@PathVariable String siteId){
        List<SiteSubwayRoute> siteSubwayRouteList = siteSubwayRouteService.findBySiteId(Integer.valueOf(siteId));

        List<SubwayInfo> subwayInfoList = new ArrayList<>();
        for (SiteSubwayRoute s :
                siteSubwayRouteList) {
            subwayInfoList.add(subwayInfoService.findById(s.getSubwayId()).get());
        }
        return subwayInfoList;
    }

    /**
     *  http://localhost:8080/subwayInfo/queryRoute?startSiteId=8810&endSiteId=8813
     * @param startSiteId 起始站Id
     * @param endSiteId 终止站Id
     * @return 路经的站点
     */
    @GetMapping("/queryRoute")
    List<SiteInfo> querySiteToSite(Integer startSiteId,Integer endSiteId) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(startSiteId);
        Set<Integer> isExist = new HashSet<>();
        Map<Integer, Integer> route = new HashMap<>();
        isExist.add(startSiteId);
        while (!queue.isEmpty()) {
            Integer from = queue.peek();
            queue.poll();
            List<SiteRelationShip> siteRelationShipList = new ArrayList<>();
            siteRelationShipList = siteRelationShipService.findByFromId(from);
            for (SiteRelationShip s : siteRelationShipList) {
                if (!isExist.contains(s.getToId())) {
                    route.put(s.getToId(), from);
                    isExist.add(s.getToId());
                    queue.add(s.getToId());
                    if (s.getToId() == endSiteId) {
                        break;
                    }
                }
            }
        }
        Stack<SiteInfo> siteInfos = new Stack<>();
        Integer temp = endSiteId;
        while (!Objects.equals(temp, startSiteId)) {
            siteInfos.push(siteInfoService.findById(temp).get());
            temp = route.get(temp);
        }
        List<SiteInfo> ans = new ArrayList<>();
        ans.add(siteInfoService.findById(startSiteId).get());
        while (!siteInfos.isEmpty()){
            ans.add(siteInfos.peek());
            siteInfos.pop();
        }
        return ans;
    }
}
