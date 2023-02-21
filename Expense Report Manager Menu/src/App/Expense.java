package App;

import java.io.Serializable;
import java.util.Date;

// this Class is For expense
public class Expense implements Serializable {
    private Long expenseId= System.currentTimeMillis();// this to generate the Id number for every expense
    private Long categoryId;//this is the category which this expense in
    private Float amount;
    private Date date;
    private String remark;

    public Expense(){

    }
    public Expense(Long categoryId, Float amount, Date date, String remark){
        this.categoryId = categoryId;
        this.amount = amount;
        this.date = date;
        this.remark = remark;
    }
    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void setDescription(String remark){
        this.remark = remark;
    }

        public Long getExpenseId(){
            return expenseId;
        }
        public Float getAmount(){
        return amount;
        }
        public String getRemark(){
        return remark;
        }
        public Date getDate(){
        return date;
        }
        public void setExpenseId(Long expenseId) {
            this.expenseId = expenseId;
        }
        public Long getCategoryId(){
        return categoryId;

        }

}
