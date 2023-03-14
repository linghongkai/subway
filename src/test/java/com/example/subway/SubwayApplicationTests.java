package com.example.subway;

import com.example.subway.pojo.SiteInfo;
import com.example.subway.pojo.SiteRelationShip;
import com.example.subway.service.SiteInfoService;
import com.example.subway.service.SiteRelationShipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SubwayApplicationTests {
    @Autowired
    SiteInfoService siteInfoService;

    @Autowired
    SiteRelationShipService siteRelationShipService;

    @Test
    void contextLoads() {
//        System.out.println(siteInfoService.save(new SiteInfo("哈哈哈", "edfg")));
//        System.out.println(siteInfoService.findAll());
//        siteRelationShipService.save(new SiteRelationShip(1,3,4));
        siteRelationShipService.deleteByFromIdAndToIdAndSubwayId(8809,8810,8809);
    }

}
