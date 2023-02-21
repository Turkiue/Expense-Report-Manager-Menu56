package App;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Report {
    private Repository repo = Repository.getRepository();

    //this method will calculate the total year expense and display it in order by using TreeMap
    public Map<String, Float> calculateMonthlyTotal(){
        Map<String, Float> m = new TreeMap();//using Tree Map because it will order the month number
        for (Expense exp : repo.expList){
       Date expDate = exp.getDate();
        String yearMonth = DateFormat.getYearAndMonth(expDate);
        if (m.containsKey(yearMonth)){
        Float total = m.get(yearMonth);
        total = total + exp.getAmount();
        m.put(yearMonth, total);
        } else {
            m.put(yearMonth, exp.getAmount());
        }
        }
        return m;
    }
    //this method will calculate the total year expense and display it in order by using TreeMap
    public Map<Integer, Float> calculateYearlyTotal(){
        Map<Integer, Float> m = new TreeMap(); // using Tree Map because it will order the year number
        for (Expense exp : repo.expList){
            Date expDate = exp.getDate();
            Integer year = DateFormat.getYear(expDate);
            if (m.containsKey(year)){
                Float total = m.get(year);
                total = total + exp.getAmount();
                m.put(year, total);
            } else {
                m.put(year, exp.getAmount());
            }
        }
        return m;
    }
    public String getCategoryNameById(Long categoryId){
        for (Category c : repo.catList){
            if (c.getCategoryId().equals(categoryId)){
                return c.getName();
            }

        }
        return null;
    }
}

