package com.cu.partc;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
    @PersistenceContext
    private EntityManager em;
    public Account find(int id) { return em.find(Account.class, id); }
    public void update(Account a) { em.merge(a); }
    public void save(Account a) { em.persist(a); }
}
