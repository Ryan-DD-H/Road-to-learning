package com.nm.model;



/**
 * �߲���
 *
 * @author nmxy
 */
public class Vegetables {

    private Integer vegetablesID;// ���
    private String name;// �߲���
    private String indate;// �������
    private int QGPDay;// ������(��)
    private String category;// ���
    private String address;// ����
    private Integer stock;//���

    public Vegetables(Integer vegetablesID, String name, String indate, int QGPDay, String category, String address, Integer stock) {
        this.vegetablesID = vegetablesID;
        this.name = name;
        this.indate = indate;
        this.QGPDay = QGPDay;
        this.category = category;
        this.address = address;
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getVegetablesID() {
        return vegetablesID;
    }

    public void setVegetablesID(Integer vegetablesID) {
        this.vegetablesID = vegetablesID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public String getIndate() {
        return indate;
    }

    public void setIndate(String indate) {
        this.indate = indate;
    }

    public int getQGPDay() {
        return QGPDay;
    }

    public void setQGPDay(int QGPDay) {
        this.QGPDay = QGPDay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
    	this.address = address;
    }
}
