package com.example.subway.service;

import com.example.subway.pojo.SubwayInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface SubwayInfoService extends JpaRepository<SubwayInfo,Integer> {
    @Transactional
    @Modifying
    @Query("update subway_info s set s.siteSize = ?1, s.startSiteId = ?2, s.endSiteId = ?3, s.name = ?4 where s.id = ?5")
    int updateSiteSizeAndStartSiteIdAndEndSiteIdAndNameById(int siteSize, int startSiteId, int endSiteId, String name, int id);
    SubwayInfo findByName(String name);

}
