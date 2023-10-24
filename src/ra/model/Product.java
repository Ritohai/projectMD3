package ra.model;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String nameProduct;
    private Category category;
    private double importPrice;
    private double price;

    private String description; // miêu tả
    private String manufacturer; // hãng
    private int stock;
    private boolean status = true;

    public Product() {
    }

    public Product(int id, String nameProduct, Category category, double importPrice, double price, String description, String manufacturer, int stock, boolean status) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.category = category;
        this.importPrice = importPrice;
        this.price = price;
        this.description = description;
        this.manufacturer = manufacturer;
        this.stock = stock;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }


    public double getPrice() {
        return importPrice * 2;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Id: " + id + " | Tên sản phẩm: " + nameProduct + " | Giá bán: " + getPrice() + "$" + " | Danh mục: " + category.getNameCategory() + " | Mô tả: " + description + " | Số lượng kho: " + stock + " | Hãng: " + manufacturer + " | Trạng thái: " + (status && stock > 0 ? "Còn hàng" : "Hết hàng");
    }
}

