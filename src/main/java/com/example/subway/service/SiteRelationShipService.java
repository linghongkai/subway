package com.example.subway.service;

import com.example.subway.pojo.SiteRelationShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface SiteRelationShipService extends JpaRepository<SiteRelationShip,Integer> {
    SiteRelationShip findByFromIdAndSubwayId(int from,int subwayId);
    @Modifying
    @Transactional
    void deleteByFromIdAndToIdAndSubwayId(int fromId, int toId, int subwayId);

    List<SiteRelationShip> findByFromId(int from);
}
