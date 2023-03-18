package com.eh.gc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eh.gc.repository.TestRepository;
import com.eh.gc.service.IService.ITestService;
/**
 * @author MD. EMRAN HOSSAIN
 * @since 17 MAR, 2023
 * @version 1.1
 */
@Service
public class TestService implements ITestService {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(TestService.class);
    
    @Autowired
    TestRepository repository;

    @Override
    public Object getTest() throws Exception {
        return repository.findAll();
    }
}
