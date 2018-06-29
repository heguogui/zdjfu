package com.hz.zdjfu.application.data.even;

import com.hz.zdjfu.application.data.bean.ProductInformBean;

/**
 * [类功能说明]
 *
 * @author HeGuoGui
 * @version 3.0.0
 * @time 2017/11/27 0027.
 */

public class ProductEven {


   private  ProductInformBean mProductInformBean;

    public ProductEven(ProductInformBean mProductInformBean) {
        this.mProductInformBean = mProductInformBean;
    }

    public ProductInformBean getmProductInformBean() {
        return mProductInformBean;
    }

    public void setmProductInformBean(ProductInformBean mProductInformBean) {
        this.mProductInformBean = mProductInformBean;
    }

    @Override
    public String toString() {
        return "ProductEven{" +
                "mProductInformBean=" + mProductInformBean +
                '}';
    }
}
