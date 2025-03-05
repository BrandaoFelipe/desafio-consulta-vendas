package com.devsuperior.dsmeta.dto;

public class TotalSaleDTO {

    private String sellerName;
    private double total;
    
    
    public TotalSaleDTO() {
    }


    public TotalSaleDTO(String sellerName, double total) {
        this.sellerName = sellerName;
        this.total = total;
    } 


    public String getSellerName() {
        return sellerName;
    }


    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }


    public double getTotal() {
        return total;
    }


    public void setTotal(double total) {
        this.total = total;
    }

    

    

}
