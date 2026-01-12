/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.control;

import java.util.List;
import manager.entity.*;
import manager.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StringType;

/**
 *
 * @author Dell
 */
public final class ClassListControl {
    public ClassListControl() {
    }


    public List<String> getFacultyList() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT ten_khoa FROM KhoaVien ORDER BY ten_khoa ASC;")
                    .addScalar("ten_khoa", StringType.INSTANCE);
            
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
    
    public List<String> getTermList() {
        Session session = manager.util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT DISTINCT REVERSE("
                    + "SUBSTRING(REVERSE(ma_lop), 1, CHARINDEX('K', REVERSE(ma_lop)) - 1)) "
                    + "AS khoa_hoc FROM LopSV ORDER BY khoa_hoc DESC;");
            
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
    
    public List<String> getClassNameList(String khoaHoc, String tenKhoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC [dbo].getDsTenLop :khoaHoc,:tenKhoa")
                    .addScalar("ten_lop", StringType.INSTANCE)
                    .setString("khoaHoc", khoaHoc)
                    .setString("tenKhoa", tenKhoa);
            
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
    
    public List<ClassListEntity> getClassList(String className, int firstResult) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM [dbo].DsLopSV WHERE ten_lop=:className")
                    .addEntity(ClassListEntity.class)
                    .setString("className", className);
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
    
    public List<ClassListEntity> getListByStudentName(String studentName, int firstResult) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM [dbo].DsLopSV WHERE "
                    + "CHARINDEX(:studentName,CONCAT_WS(' ', DsLopSV.ho, DsLopSV.dem, DsLopSV.ten)) > 0")
                    .addEntity(ClassListEntity.class)
                    .setString("studentName", studentName);
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
    
    public  List<ClassListEntity> getListByStudentCode(String studentCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM [dbo].DsLopSV WHERE mssv=:studentCode")
                    .addEntity(ClassListEntity.class)
                    .setString("studentCode", studentCode);
            
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

    public int getMaxResult() {
        return maxResult;
    }
    
    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public int getTotalByStudentName() {
        return totalByStudentName;
    }

    public int getTotalByClass() {
        return totalByClass;
    }

    public void setTotalByStudentName(String studentName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC [dbo].get_countByStudentName :studentName")
                    .setString("studentName", studentName);
            
            tx.commit();
            totalByStudentName = (int)query.list().get(0);
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void setTotalByClass(String className) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC [dbo].get_countByClass :className")
                    .setString("className", className);
            
            tx.commit();
            totalByClass = (int)query.list().get(0);
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
            he.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    private int totalByStudentName = 0;
    private int totalByClass = 0;
    private int maxResult = 0;   
}
