/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.pojo;


public class ProductsPojo {

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ProductsPojo(String productId, String productName, String productCompany, double productPrice, double productOur, int tax, int quantity,double total) {
        this.productId = productId;
        this.productName = productName;
        this.productCompany = productCompany;
        this.productPrice = productPrice;
        this.productOur = productOur;
        this.tax = tax;
        this.quantity = quantity;
        this.total=total;
    }

    public ProductsPojo() {
    }



    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCompany() {
        return productCompany;
    }

    public void setProductCompany(String productCompany) {
        this.productCompany = productCompany;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductOur() {
        return productOur;
    }

    public void setProductOur(double productOur) {
        this.productOur = productOur;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    private String productId;
    private String productName;
    private String productCompany;
    private double productPrice;
    private double productOur;
    private int tax;
    private int quantity;
    private double total;
         
}
