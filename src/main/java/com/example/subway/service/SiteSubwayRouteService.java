package com.example.subway.service;

import com.example.subway.pojo.SiteSubwayRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SiteSubwayRouteService extends JpaRepository<SiteSubwayRoute,Integer> {
    List<SiteSubwayRoute> findBySubwayId(Integer subwayId);

    List<SiteSubwayRoute> findBySiteId(Integer siteId);
}
