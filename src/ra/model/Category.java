package ra.model;

import java.io.Serializable;

public class Category implements Serializable {
    private int idCategory;
    private String nameCategory;
    private boolean status = true;

    public Category() {
    }


    public Category(int idCategory, String nameCategory, boolean status) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.status = status;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Danh mục{" +
                "ID=" + idCategory +
                ", Tên danh mục='" + nameCategory + '\'' +
                ", Trạng thái: " + status + '}';
    }
}
