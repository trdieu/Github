/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.control;

import java.util.List;
import manager.entity.ModuleEntity;
import manager.util.HibernateUtil;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;

/**
 *
 * @author Dell
 */
public class ModuleListControl {
    public List<String> getFacultyList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
         
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT ten_khoa FROM KhoaVien ORDER BY ten_khoa ASC;")
                    .addScalar("ten_khoa", StringType.INSTANCE)
                    .setCacheMode(CacheMode.GET);
                    

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
    
    public List<ModuleEntity> getModuleList(int firstResult) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
         
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM DsHP")
                    .addEntity(ModuleEntity.class);
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
    
    public List<ModuleEntity> getModuleByCode(String code) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
         
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM DsHP WHERE ma_hp=:maHp")
                    .addEntity(ModuleEntity.class)
                    .setString("maHp", code);

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
    
    public List<ModuleEntity> getModuleByName(String name, int firstResult) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
         
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM DsHP WHERE "
                    + "CHARINDEX(:tenHp,ten_hp) > 0")
                    .addEntity(ModuleEntity.class)
                    .setString("tenHp", name);
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
    
    public List<ModuleEntity> getModuleByFaculty(String faculty, int firstResult) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
         
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM DsHP WHERE ten_khoa=:tenKhoa")
                    .addEntity(ModuleEntity.class)
                    .setString("tenKhoa", faculty);
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

    public ModuleListControl() {
    }
    
    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setTotalModule() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT COUNT(*) FROM DsHP");
            
            tx.commit();
            totalModule = (int)query.list().get(0);
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
    }

    public int getTotalModule() {
        return totalModule;
    }

    public int getTotalModuleByName() {
        return totalModuleByName;
    }

    public int getTotalModuleByFaculty() {
        return totalModuleByFaculty;
    }

    public void setTotalModuleByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT COUNT(*) FROM DsHP WHERE "
                    + "CHARINDEX(:tenHp,ten_hp) > 0")
                    .setString("tenHp", name);
            
            tx.commit();
            totalModuleByName = (int)query.list().get(0);
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void setTotalModuleByFaculty(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT COUNT(*) FROM DsHP WHERE ten_khoa=:tenKhoa")
                    .setString("tenKhoa", name);
            
            tx.commit();
            totalModuleByFaculty = (int)query.list().get(0);
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    private int totalModuleByName;
    private int totalModuleByFaculty;
    private int totalModule;
    private int maxResult = 0;  
}
