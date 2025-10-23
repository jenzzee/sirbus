package main;
import java.io.Serializable;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String categoryId;
    private String categoryName;
    private String description;

    public Category(String categoryId, String categoryName, String description) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public String getDescription() { return description; }

    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Category ID: " + categoryId + ", Name: " + categoryName + ", Description: " + description;
    }
}
