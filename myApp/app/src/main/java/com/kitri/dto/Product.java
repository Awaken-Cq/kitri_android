package com.kitri.dto;

import java.io.Serializable;

public class Product implements Serializable {
    private String product_no;
    private String product_name;
    //transient private int product_price;//직렬화에서 제외 : transient 즉 가격은 기본값 0값을 갖는다.
    private int product_price;
    private String product_detail;
    private ProductCategory productCategory;

    public Product() {
        super();
    }

    public Product(String product_no, String product_name, int product_price, String product_detail,
                   ProductCategory productCategory) {
        super();
        this.product_no = product_no;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_detail = product_detail;
        this.productCategory = productCategory;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_detail() {
        return product_detail;
    }

    public void setProduct_detail(String product_detail) {
        this.product_detail = product_detail;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product_no == null) ? 0 : product_no.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (product_no == null) {
            if (other.product_no != null)
                return false;
        } else if (!product_no.equals(other.product_no))
            return false;
        return true;
    }


}
