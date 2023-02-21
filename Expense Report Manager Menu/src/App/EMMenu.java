package App;

import java.io.*;
import java.util.*;

/*this class has most of the methods From Case 1 to 4
and the rest of the methods on Report class
 */


public class EMMenu {
    /* Creating a Repository object and calling 'Repository.getRepository(); '
    to use the database we created
    */
    Repository repo = Repository.getRepository();

    //this is for calling ReportService which contains cases from 4 to 7 to use them and display them in menu
    Report reportService = new Report();
    private Scanner in = new Scanner(System.in); //Scanner object
    private int choice; // getting the user (choice) to use it in switch
    public EMMenu(){
        restoreRepository();

    }

    /* this method to display menu and use while loop to enter an infinite
    and can only exit if you entered 8
    * */
    public void showMenu() {
        while (true) {
            printMenu();
            switch (choice) {
                case 1:
                    toAddCategory();
                    pressAnyKeyToContinue();
                    break;
                case 2:
                    toCategoryList();
                    pressAnyKeyToContinue();
                    break;
                case 3:
                    toExpenseEntry();
                    pressAnyKeyToContinue();
                    break;
                case 4:
                    toExpenseList();
                    pressAnyKeyToContinue();
                    break;
                case 5:
                    toMonthlyExpenses();
                    pressAnyKeyToContinue();
                    break;
                case 6:
                    toYearlyExpenses();
                    pressAnyKeyToContinue();
                    break;
                case 7:
                    exit();
            }

        }

    }
    //Printing the menu (interface)
    public void printMenu() {
        System.out.println("---------Expense Report Manager Menu---------");
        System.out.println("1/ Add Category");
        System.out.println("2/ Categories List");
        System.out.println("3/ Expense Entry");
        System.out.println("4/ Expenses List");
        System.out.println("5/ Monthly Expenses");
        System.out.println("6/ Yearly Expenses");
        System.out.println("7/ Exit");
        System.out.println("---------------------------------");
        System.out.println("Enter Your Choice: ");
        choice = in.nextInt();
    }

    //this method to catName and add a category
public void toAddCategory(){
        in.nextLine();
    System.out.println("Enter Category Name: ");
    String catName = in.nextLine();
    Category cat = new Category(catName);
    repo.catList.add(cat);
    System.out.println("New Category Has Been Added Successfully ");

}

// this method to show all the categories you have catList(Category List)
    public void toCategoryList() {
        System.out.println("Categories List ");
        List<Category> cList = repo.catList;
        for (int i = 0; i < repo.catList.size();i++) { //this for loop is to get all elements in catList
            Category c= cList.get(i);
            System.out.println((i+1)+" - " + c.getName() + " - " + c.getCategoryId()  );
        }
    }
    /* this method to choose a category and add all the description you need
     and amount and date and then will be added in the Repository
    * */
    public void toExpenseEntry(){
        System.out.println("Enter Details For Expense Entry.... ");
        toCategoryList(); // to show all categories
        System.out.println("Choose Category: ");
        int catChoice = in.nextInt();
        Category selectedCategory = repo.catList.get(catChoice-1); // -1 Because of the index
        System.out.println("Your Category Choice : "+selectedCategory.getName());
        System.out.println("Enter Amount : ");
        float amount = in.nextFloat(); // Float Scanner
        System.out.println("Enter Description For Your Expense Entry : ");
        in.nextLine(); // next line so the user can add whatever description he wants
        String description = in.nextLine();

        System.out.println("Enter Date (DD/MM/YYYY) : ");// this is the Format i use
        String dateAsString = in.nextLine(); // this method will get date as string

        Date date = DateFormat.stringToDate(dateAsString); // Creating object From Date Format

        Expense exp = new Expense(); // here i create an object from Expense to save user inputs in the category and send it to Repository on expList
        exp.setCategoryId(selectedCategory.getCategoryId());
        exp.setAmount(amount);
        exp.setDescription(description);
        exp.setDate(date);

        repo.expList.add(exp);
        System.out.println("Expense Has Been Added Successfully ");
    }

    // this method is same to category list, because it will display all the Expenses that are in Repository using for loop
    public void toExpenseList(){
        System.out.println("Listing Expenses....");
       List<Expense> expenseList = repo.expList;
       for (int i =0;i<expenseList.size();i++){
           Expense exp = expenseList.get(i);
           String catName = reportService.getCategoryNameById(exp.getCategoryId());
           String dateString = DateFormat.dateToString(exp.getDate());
           System.out.println((i+1)+"-"+ catName+" Amount : "+ exp.getAmount()+ ", "+" Description : "+exp.getRemark()+ ", "+" Date : " + dateString +", ");
       }

    }

    // this method for display the monthly expenses from Report Class
    public void toMonthlyExpenses(){
        System.out.println("Monthly Expenses Total....");
        Map<String, Float > resultMap = reportService.calculateMonthlyTotal();
        Set<String> keys = resultMap.keySet();// I use Set here because if the user entered two different Expenses in the same Month, it will be considered as one
        for (String yearMonth : keys){
            System.out.println(yearMonth+" : " + resultMap.get(yearMonth));
        }
    }

    // this method for display the yearly expenses from Report Class
    public void toYearlyExpenses(){
        System.out.println("Yearly Expense Total....");
        Map<Integer, Float> resultMap = reportService.calculateYearlyTotal();
        Set<Integer> years = resultMap.keySet(); //I use Set here because if the user entered different Expenses in the same Year, it will be considered as one
        Float total = 0.0F;
        for (Integer year: years){
            Float exp = resultMap.get(year);
            total = total + exp;
            System.out.println(year+" : "+ resultMap.get(year));
        }
        System.out.println("-------------------------");
        System.out.println("Total Expense : "+ total);
    }

    //this method for exiting the program, storing the Repository data
    public void exit(){
        storeRepository();
        System.exit(7);
    }

    private void storeRepository() {
      serialize("Expense.ser", repo.expList);//to make file to store expenses in
      serialize("Category.ser", repo.catList);//to make file to store category in
    }
    public void serialize(String file, Object obj) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj); // store expense list in file
            oos.close();
            fos.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    // reusable method to use for expense and category restore from the file
    public Object deser(String file){
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object obj = ois.readObject();
            return obj;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private void restoreRepository() {
        List<Expense> expList = (List<Expense>) deser("Expense.ser");
        List<Category> catList = (List<Category>) deser("Category.ser");
        if (expList != null){
            repo.expList = expList;
        }
        if (catList != null){
            repo.catList = catList;
        }
    }

// this method as a loading and let you know that your inputs are entered

    public void pressAnyKeyToContinue() {
        try {
            System.out.println("Press Any Key To Continue....");
            System.in.read();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

