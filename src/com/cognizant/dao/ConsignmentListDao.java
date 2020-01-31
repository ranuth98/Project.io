package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.ConsignmentList;

public interface ConsignmentListDao {

    public boolean createConsignmentList(ConsignmentList consignmentList);

    public List<ConsignmentList> getListByPackage(long packageId);

    public List<ConsignmentList> getListByConsignment(long consignmentId);

    public boolean updateConsignmentList(ConsignmentList consignmentList);

    public ConsignmentList getConsignmentList(long consignmentListId);
}
