/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager.ui;

import java.awt.Color;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import manager.control.AcademicTranscriptControl;
import manager.entity.AcademicTranscriptEntity;
import manager.entity.ClassListEntity;
import manager.entity.StudyResultEntity;

/**
 *
 * @author Dell
 */
public class AcademicTranscriptPanel extends javax.swing.JPanel {
    private String mssv;
    /**
     * Creates new form AcademyTranscriptPanel
     */
    
    public AcademicTranscriptPanel() {
        initComponents();
    }

    public AcademicTranscriptPanel(String mssv) {
        this.mssv = mssv;
        initComponents();
        initStudyResultTable();
        initTranscriptTable();
        
        AcademicTranscriptControl control = new AcademicTranscriptControl();
        List<AcademicTranscriptEntity> transcriptResult = control.getTranscriptByStudentCode(mssv);
        List<StudyResultEntity> studyResult = control.getStudyResult(mssv);
        displayTrancript(transcriptResult);
        displayStudyResult(studyResult);
    }

    public void initTranscriptTable() {
        JTableHeader header = transcriptTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(72, 3, 111));
        header.setForeground(Color.white);
        header.setFont(new java.awt.Font("SansSerif", 1, 14));
        transcriptScroll.getViewport().setBackground(Color.WHITE);
        Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );
        transcriptScroll.setViewportBorder(border);
        transcriptScroll.setBorder(border);
    }
    
    public void initStudyResultTable() {
        JTableHeader header = studyResultTable.getTableHeader();
        header.setOpaque(false);
        header.setBackground(new Color(72, 3, 111));
        header.setForeground(Color.white);
        header.setFont(new java.awt.Font("SansSerif", 1, 14));
        studyResultScroll.getViewport().setBackground(Color.WHITE);
        Border border = BorderFactory.createEmptyBorder( 0, 0, 0, 0 );
        studyResultScroll.setViewportBorder(border);
        studyResultScroll.setBorder(border);
    }
    
    private void displayTrancript(List resultList) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        tableHeaders.add("Học kỳ");
        tableHeaders.add("Mã HP");
        tableHeaders.add("Tên HP");
        tableHeaders.add("TC");
        tableHeaders.add("Lớp học");
        tableHeaders.add("Điểm QT");
        tableHeaders.add("Điểm thi");
        tableHeaders.add("Điểm chữ");
        
        for (int i = 0; i < resultList.size(); i++) {
            AcademicTranscriptEntity student = (AcademicTranscriptEntity) resultList.get(i);
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(student.getHocKy());
            oneRow.add(student.getMaHp());
            oneRow.add(student.getTenHp());
            oneRow.add(student.getTc());
            oneRow.add(student.getMaLopDK());
            oneRow.add(student.getDiemQt());
            oneRow.add(student.getDiemCk());
            oneRow.add(student.getDiemChu());
            tableData.add(oneRow);
        }
        transcriptTable.setModel(new DefaultTableModel(tableData, tableHeaders));
    }
    
    private void displayStudyResult(List resultList) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();
        tableHeaders.add("Học kỳ");
        tableHeaders.add("GPA");
        tableHeaders.add("CPA");
        tableHeaders.add("TC qua");
        tableHeaders.add("TC tích lũy");
        tableHeaders.add("TC nợ ĐK");
        tableHeaders.add("TC ĐK");
        tableHeaders.add("Trình độ");
        tableHeaders.add("Thiểu điểm");
        
        for (int i = 0; i < resultList.size(); i++) {
            StudyResultEntity student = (StudyResultEntity) resultList.get(i);
            Vector<Object> oneRow = new Vector<Object>();
            oneRow.add(student.getHocKy());
            oneRow.add(student.getGpa());
            oneRow.add(student.getCpa());
            oneRow.add(student.getTcQua());
            oneRow.add(student.getTcTichLuy());
            oneRow.add(student.getTcNoDk());
            oneRow.add(student.getTcDk());
            oneRow.add(student.getTrinhDo());
            oneRow.add(student.getThieuDiem());
            tableData.add(oneRow);
        }
        studyResultTable.setModel(new DefaultTableModel(tableData, tableHeaders));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        transcriptScroll = new javax.swing.JScrollPane();
        transcriptTable = new javax.swing.JTable();
        studyResultScroll = new javax.swing.JScrollPane();
        studyResultTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1020, 565));

        jPanel1.setBackground(new java.awt.Color(110, 89, 222));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 13);
        flowLayout1.setAlignOnBaseline(true);
        jPanel1.setLayout(flowLayout1);

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bảng điểm sinh viên");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1);

        jPanel3.setBackground(new java.awt.Color(110, 89, 222));

        jLabel3.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kết quả học tập sinh viên");
        jPanel3.add(jLabel3);

        transcriptScroll.setBackground(new java.awt.Color(255, 255, 255));
        transcriptScroll.setBorder(null);

        transcriptTable.setAutoCreateRowSorter(true);
        transcriptTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        transcriptTable.setForeground(new java.awt.Color(54, 33, 89));
        transcriptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Học kỳ", "Mã HP", "Tên HP", "TC", "Lớp học", "Điểm QT", "Điểm thi", "Điểm chữ"
            }
        ));
        transcriptTable.setGridColor(new java.awt.Color(204, 204, 255));
        transcriptScroll.setViewportView(transcriptTable);

        studyResultScroll.setBorder(null);

        studyResultTable.setAutoCreateRowSorter(true);
        studyResultTable.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        studyResultTable.setForeground(new java.awt.Color(54, 33, 89));
        studyResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Học kỳ", "GPA", "CPA", "TC qua", "TC tích lũy", "TC nợ ĐK", "Trình độ", "Thiếu điểm", "TC ĐK"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        studyResultTable.setGridColor(new java.awt.Color(204, 204, 255));
        studyResultScroll.setViewportView(studyResultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(studyResultScroll)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(transcriptScroll)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(transcriptScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(studyResultScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane studyResultScroll;
    private javax.swing.JTable studyResultTable;
    private javax.swing.JScrollPane transcriptScroll;
    private javax.swing.JTable transcriptTable;
    // End of variables declaration//GEN-END:variables
}
