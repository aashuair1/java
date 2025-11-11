package com.cu.partc;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        AccountDAO dao = ctx.getBean(AccountDAO.class);
        // create two accounts if not present
        if (dao.find(101) == null) dao.save(new Account(101, "Alice", 10000));
        if (dao.find(102) == null) dao.save(new Account(102, "Bob", 2000));
        BankService svc = ctx.getBean(BankService.class);
        try {
            svc.transfer(101, 102, 3000);
            System.out.println("Transfer success");
        } catch (Exception e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
        ctx.close();
    }
}
