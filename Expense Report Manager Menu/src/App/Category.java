package App;

import java.io.Serializable;

// this Class is For category
public class Category implements Serializable {
    private Long categoryId = System.currentTimeMillis();// this to generate the Id number for every category
    private String name;
    public Category(String name){
        this.name = name;
    }
    public Category(Long categoryId, String name){
        this.categoryId = categoryId;
        this.name = name;
    }
    public Category(){
    }
    public Long getCategoryId(){
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;

    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;

    }
}
