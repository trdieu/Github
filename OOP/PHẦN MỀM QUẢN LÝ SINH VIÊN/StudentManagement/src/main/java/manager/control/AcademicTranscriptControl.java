/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.control;

import java.util.ArrayList;
import java.util.List;
import manager.entity.AcademicTranscriptEntity;
import manager.entity.ClassListEntity;
import manager.entity.StudyResultEntity;
import manager.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dell
 */
public class AcademicTranscriptControl {

    public AcademicTranscriptControl() {
    }

    public List<AcademicTranscriptEntity> getTranscriptByStudentCode(String mssv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("SELECT * FROM BangdiemSV WHERE mssv=:mssv ORDER BY hoc_ky ASC")
                    .addEntity(AcademicTranscriptEntity.class)
                    .setString("mssv", mssv);
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

    public List<AcademicTranscriptEntity> getTranscriptForTeacherSc(String mssv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC dbo.get_diemForTeacherSc :mssv")
                    .addEntity(AcademicTranscriptEntity.class)
                    .setString("mssv", mssv);
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

    public List<AcademicTranscriptEntity> getTranscriptForTeacherCc(String registerClassCode) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC dbo.get_diemForTeacherCc :maLopDk")
                    .addEntity(AcademicTranscriptEntity.class)
                    .setString("maLopDk", registerClassCode);
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

    public List<StudyResultEntity> getStudyResult(String mssv) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            query = (SQLQuery) session.createSQLQuery("EXEC dbo.getKetQuaHocTap :mssv")
                    .addEntity(StudyResultEntity.class)
                    .setString("mssv", mssv);
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

    public void updateTranscript(ArrayList<AcademicTranscriptEntity> list) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            SQLQuery query;
            for (AcademicTranscriptEntity entity : list) {
                query = (SQLQuery) session.createSQLQuery("UPDATE DangKyLop SET diem_qt=:diemQt,diem_ck=:diemCk "
                        + "WHERE mssv=:mssv AND ma_lop_dk=:maLopDk")
                        .setString("mssv", entity.getMssv())
                        .setString("maLopDk", entity.getMaLopDK())
                        .setFloat("diemQt", entity.getDiemQt())
                        .setFloat("diemCk", entity.getDiemCk());
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
}
