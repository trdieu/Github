/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.control.admin;

import java.util.ArrayList;
import java.util.List;
import manager.entity.admin.Account;
import manager.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

/**
 *
 * @author Dell
 */
public class AccountControl {
    public Account findAccount(String userName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM Users WHERE user_name=:userName")
                    .addEntity(Account.class)
                    .setString("userName", userName);
            
            tx.commit();
            if(query.list().size() == 0) {
                return null;
            }
            
            return (Account) query.list().get(0);
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
    public void updatePassWord(Account acc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("UPDATE dbo.Users SET pass_word=:passWord WHERE user_name=:userName")
                    .setString("userName", acc.getUserName())
                    .setString("passWord", acc.getPassWord());
            
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
    
    public int insertAccount(Account acc) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC dbo.insertAccount :userName,:passWord,:type")
                    .addScalar("errorCode", IntegerType.INSTANCE)
                    .setString("userName", acc.getUserName())
                    .setString("passWord", acc.getPassWord())
                    .setString("type", acc.getAccountType());
            
            tx.commit();
            return (int)query.list().get(0);  
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return 0;
    }
    
    public int insertGroupAccount(String from, String to) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC dbo.insertGroupStudentAccount :from,:to")
                    .addScalar("errorCode", IntegerType.INSTANCE)
                    .setString("from", from)
                    .setString("to", to);
            
            tx.commit();
            return (int)query.list().get(0);  
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return 0;
    }
    
    public void removeGroupAccount(ArrayList<String> removeList) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            for(String userName: removeList) {
                query = (SQLQuery) session.createSQLQuery("DELETE FROM Users WHERE user_name=:userName")
                    .setString("userName", userName);
                query.executeUpdate();
            }

            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    
    public List<Account> getAccount(int firstResult) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM Users ORDER BY permission, user_name ASC;")
                    .addEntity(Account.class);
            query.setFirstResult(firstResult);
            query.setMaxResults(maxResult);
            tx.commit();
            return query.list();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public int getTotalAccount() {
        return totalAccount;
    }

    public void setTotalAccount() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT COUNT(*) FROM Users");
            
            tx.commit();
            totalAccount = (int)query.list().get(0);
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }
    
    private int totalAccount = 0;
    private int maxResult = 0;   
}
