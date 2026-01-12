/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.control;

import manager.util.HibernateUtil;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dell
 */
public class ChangePassWordControl {
    private String userName;

    public String getMssv() {
        return userName;
    }

    public void setMssv(String userName) {
        this.userName = userName;
    }

    public ChangePassWordControl(String userName) {
        this.userName = userName;
    }

    
    public boolean changePassword(String oldPass, String newPass) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
         
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC [dbo].changePassword"
                    + " :oldPass, :newPass, :userName")
                    .setString("userName", userName)
                    .setString("newPass", newPass)
                    .setString("oldPass", oldPass)
                    .setCacheMode(CacheMode.GET);
                    

            tx.commit();
            int retVal = (int)query.list().get(0);
            return retVal == 0;    
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
