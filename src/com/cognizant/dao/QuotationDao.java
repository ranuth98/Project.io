package com.cognizant.dao;

import java.util.List;

import com.cognizant.model.Quotation;

public interface QuotationDao {

    public boolean updateQuotation(Quotation quotation);

    public List<Quotation> getAllQuotation();

    public Quotation getQuotation(long quotationId);

    public Quotation getQuotation(int distance);

    public boolean createQuotation(Quotation quotation);
}
