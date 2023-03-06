package com.tao.dao;

import com.tao.po.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

    Test findByName(String name);

    Test findById(Long id);

}
