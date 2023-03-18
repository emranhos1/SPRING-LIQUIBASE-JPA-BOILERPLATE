package com.eh.gc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eh.gc.model.Test;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 17 MAR, 2023
 * @version 1.1
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

}
