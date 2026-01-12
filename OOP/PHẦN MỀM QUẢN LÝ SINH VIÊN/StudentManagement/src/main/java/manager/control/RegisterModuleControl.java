/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.control;

import java.util.Date;
import java.util.List;
import manager.entity.RegisterModuleEntity;
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
public class RegisterModuleControl {

    public List<String> getSemesterList() {
        Session session = manager.util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT hoc_ky FROM HocKyMoDK");

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

    public List<RegisterModuleEntity> getRegisteredModule(String studentCode, String semester) {
        Session session = manager.util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT DangKyHP.*, ten_hp, tinchi FROM "
                    + "DangKyHP JOIN HocPhan ON DangKyHP.ma_hp=HocPhan.ma_hp\n"
                    + "WHERE mssv=:mssv AND hoc_ky=:hocKy")
                    .addEntity(RegisterModuleEntity.class)
                    .setString("hocKy", semester)
                    .setString("mssv", studentCode);

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

    public RegisterModuleEntity getModule(String moduleCode) {
        Session session = manager.util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT ten_hp, tinchi  FROM HocPhan WHERE ma_hp=:maHp")
                    .addScalar("ten_hp", StringType.INSTANCE)
                    .addScalar("tinchi", IntegerType.INSTANCE)
                    .setString("maHp", moduleCode);

            tx.commit();
            if (query.list().size() > 0) {
                RegisterModuleEntity module = new RegisterModuleEntity();
                module.setMaHp(moduleCode);
                module.setTenHp((String) ((Object[]) query.list().get(0))[0]);
                module.setTinChi((int) ((Object[]) query.list().get(0))[1]);
                module.setNgayDk(new Date());

                return module;
            }

            return null;
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

    public String checkModuleConstraint(String moduleCode, String studentCode) {
        Session session = manager.util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC [dbo].checkDkhp :moduleCode,:studentCode")
                    .addScalar("retVal", StringType.INSTANCE)
                    .setString("moduleCode", moduleCode)
                    .setString("studentCode", studentCode);

            tx.commit();
            return (String) query.list().get(0);
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
    
    public void removeOldRegister(String studentCode, String semester) {
        Session session = manager.util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("DELETE FROM dbo.DangKyHP WHERE mssv=:mssv AND hoc_ky=:hocKy")
                    .setString("mssv", studentCode)
                    .setString("hocKy", semester);
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public boolean registerModule(RegisterModuleEntity module) {
        Session session = manager.util.HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("INSERT INTO dbo.DangKyHP(mssv,ma_hp,hoc_ky,ngay_dk) VALUES(:mssv,:maHp,:hocKy,:ngayDk)")
                    .setString("mssv", module.getMssv())
                    .setString("maHp", module.getMaHp())
                    .setString("hocKy", module.getHocKy())
                    .setDate("ngayDk", module.getNgayDk());
            query.executeUpdate();
            tx.commit();
        } catch (HibernateException he) {
            return false;
        } finally {
            session.close();
        }
        return true;
    }
}
