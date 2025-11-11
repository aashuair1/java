package com.cu.partc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankService {
    @Autowired
    private AccountDAO dao;

    @Transactional
    public void transfer(int from, int to, double amount) {
        Account a1 = dao.find(from);
        Account a2 = dao.find(to);
        if (a1 == null || a2 == null) throw new RuntimeException("Account not found");
        if (a1.getBalance() < amount) throw new RuntimeException("Insufficient funds");
        a1.setBalance(a1.getBalance() - amount);
        a2.setBalance(a2.getBalance() + amount);
        dao.update(a1);
        dao.update(a2);
    }
}
