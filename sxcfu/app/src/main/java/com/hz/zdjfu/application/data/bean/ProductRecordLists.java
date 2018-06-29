package com.hz.zdjfu.application.data.bean;

import java.util.List;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 2.1.0
 * @time 2017/8/21 0021
 */
public class ProductRecordLists {

   private List<ProductRecordsBean> productRecords;



    public List<ProductRecordsBean> getProductRecords() {
        return productRecords;
    }

    public void setProductRecords(List<ProductRecordsBean> productRecords) {
        this.productRecords = productRecords;
    }

    @Override
    public String toString() {
        return "ProductRecordLists{" +
                "productRecords=" + productRecords +
                '}';
    }
}
