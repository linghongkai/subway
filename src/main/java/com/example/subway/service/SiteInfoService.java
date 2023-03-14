package com.example.subway.service;

import com.example.subway.pojo.SiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface SiteInfoService extends JpaRepository<SiteInfo, Integer> {
}
