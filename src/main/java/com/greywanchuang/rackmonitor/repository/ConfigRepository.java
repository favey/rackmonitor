package com.greywanchuang.rackmonitor.repository;

import com.greywanchuang.rackmonitor.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigRepository extends JpaRepository<Config,Integer> {

    List<Config> findAll();

    Config save(Config config);

    void deleteById(int id);
}
