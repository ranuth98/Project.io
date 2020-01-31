package com.cognizant.dao;

import com.cognizant.model.Policy;

public interface PolicyDao {

    public boolean updatePolicy(Policy policy);

    public Policy getPolicy();
}
