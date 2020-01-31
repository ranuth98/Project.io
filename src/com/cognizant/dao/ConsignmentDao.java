package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Consignment;

public interface ConsignmentDao {

    public boolean createConsignment(Consignment consignment);

    public List<Consignment> getAllConsignments();

    public Consignment getConsignment(long consignmentId);

    public boolean updateConsignment(Consignment consignment);
}
