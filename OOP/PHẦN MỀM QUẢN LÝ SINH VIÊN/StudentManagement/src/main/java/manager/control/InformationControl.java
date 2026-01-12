/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.control;

import manager.entity.InformationEntity;
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
public class InformationControl {
    private final InformationEntity informationEntity;
    private String mssv;

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public InformationControl(String mssv) {
        this.mssv = mssv;
        informationEntity = new InformationEntity();
    }

    public InformationEntity getInformation() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC [dbo].getThongTinSv :mssv")
                    .addEntity(InformationEntity.class)
                    .setParameter("mssv", mssv);
         
            tx.commit();

            return (InformationEntity)query.list().get(0);
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
    
    public void setInfo(String email, String address, String phoneNumber) {
        if(email.equals("")) {
            informationEntity.setEmail(null);
        } else {
            informationEntity.setEmail(email);
        }
        if(address.equals("")) {
            informationEntity.setDiaChi(null);
        } else {
            informationEntity.setDiaChi(address);
        }
        if(phoneNumber.equals("")) {
            informationEntity.setSoDT(null);
        } else {
            informationEntity.setSoDT(phoneNumber);
        }
    }
    
    public void update() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            
            query = (SQLQuery) session.createSQLQuery("UPDATE [dbo].SinhVien "
                    + "SET dia_chi=:dia_chi,email=:email,so_dt=:so_dt WHERE mssv=:mssv")
                    .setString("dia_chi", informationEntity.getDiaChi())
                    .setString("email", informationEntity.getEmail())
                    .setString("so_dt", informationEntity.getSoDT())
                    .setString("mssv", mssv);
            
            int executeUpdate = query.executeUpdate();
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
    
    public boolean changePassword(String oldPass, String newPass) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
         
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC [dbo].changePassword"
                    + " :oldPass, :newPass, :userName")
                    .setString("userName", mssv)
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
