/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import BUS.CauHoiBUS;
import BUS.ChuDeBUS;
import BUS.DapAnBUS;
import Customs.XuLyFileExcel;
import DAO.ChuDeDAO;
import DAO.DapAnDAO;
import DTO.CauHoiDTO;
import DTO.ChuDeDTO;
import DTO.DapAnDTO;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author nhu
 */
public class PnQuanLyCauHoiGUI extends javax.swing.JPanel {

    DefaultTableModel model1, model2;
    CauHoiBUS quesBUS = new CauHoiBUS();
    ChuDeBUS tpBUS = new ChuDeBUS();
    DapAnBUS anBUS = new DapAnBUS();
    DapAnDAO anDAL = new DapAnDAO();
    ChuDeDAO tpDAO = new ChuDeDAO();
    private int selectedQuestionID = -1;
    private int selectedAnswerID = -1;
    Font font = new Font("Tahoma", Font.BOLD, 13);

    public PnQuanLyCauHoiGUI() {
        initComponents();
        model1 = new DefaultTableModel();
        model1.addColumn("Mã câu hỏi");
        model1.addColumn("Nội dung");
        model1.addColumn("Chủ đề");
        model1.addColumn("Bài học");
        model1.addColumn("Mức độ");
        model1.addColumn("Trạng thái");
        model1.addColumn("Hình ảnh");
        tbQuestions.setModel(model1);
        jTextFieldSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                searchTable(jTextFieldSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                searchTable(jTextFieldSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                searchTable(jTextFieldSearch.getText());
            }
        });
        tbQuestions.setFocusable(false);
        tbQuestions.setIntercellSpacing(new Dimension(0, 0));
        tbQuestions.getTableHeader().setFont(font);
        tbQuestions.setRowHeight(50);
        tbQuestions.setShowVerticalLines(false);
        tbQuestions.getTableHeader().setOpaque(false);
        tbQuestions.setFillsViewportHeight(true);
        tbQuestions.getTableHeader().setBackground(new Color(0, 153, 153));
        tbQuestions.getTableHeader().setForeground(Color.WHITE);
        tbQuestions.setSelectionBackground(new Color(232, 232, 232));
        tbQuestions.setCursor(new Cursor(Cursor.HAND_CURSOR));

        model2 = new DefaultTableModel();
        model2.addColumn("Mã câu hỏi");
        model2.addColumn("Mã câu trả lời");
        model2.addColumn("Nội dung");
        model2.addColumn("Hình ảnh");
        model2.addColumn("Đúng/sai");
        model2.addColumn("Trang thái");
        TableAnswers.setModel(model2);

        TableAnswers.setFocusable(false);
        TableAnswers.setIntercellSpacing(new Dimension(0, 0));
        TableAnswers.getTableHeader().setFont(font);
        TableAnswers.setRowHeight(50);
        TableAnswers.setShowVerticalLines(false);
        TableAnswers.getTableHeader().setOpaque(false);
        TableAnswers.setFillsViewportHeight(true);
        TableAnswers.getTableHeader().setBackground(new Color(0, 153, 153));
        TableAnswers.getTableHeader().setForeground(Color.WHITE);
        TableAnswers.setSelectionBackground(new Color(232, 232, 232));
        TableAnswers.setCursor(new Cursor(Cursor.HAND_CURSOR));

        showTableQuestions();
        ShowTableAnwers();
        tbQuestions.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // ActionEvent evt = null;
                // BtnDetailActionPerformed(evt);
                int selectedRow = tbQuestions.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) tbQuestions.getValueAt(selectedRow, 0);
                    selectedQuestionID = (int) tbQuestions.getValueAt(selectedRow, 0);
                    selectedAnswerID = -1;
                    model2.setRowCount(0);
                    ArrayList<DapAnDTO> arr = anDAL.getID(id);
                    for (DapAnDTO answersDTO : arr) {
                        Object[] row = {answersDTO.getqID(), answersDTO.getAwID(), answersDTO.getAwContent(), answersDTO.getAwPictures(), answersDTO.getIsRight(), answersDTO.getAwStatus()};
                        model2.addRow(row);
                    }
                }

            }
        });
        TableAnswers.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int selectedRow = TableAnswers.getSelectedRow();
                if (selectedRow != -1) {
                    selectedAnswerID = (int) TableAnswers.getValueAt(selectedRow, 1); // Lấy ID câu trả lời
                    selectedQuestionID = -1;
                }
            }
        });

    }

    private void showTableQuestions() {
        model1.setRowCount(0);
        ArrayList<CauHoiDTO> arr = quesBUS.getQuestion();
        //ArrayList<ChuDeDTO> arrTp = tpBUS.getListChuDe();
        for (CauHoiDTO quesDTO : arr) {
            ChuDeDTO tp = tpDAO.getIdTp(quesDTO.getqTopicID());
            int idParent = tp.getTpParentID();
            String nameLesson = tp.getTpTitle();
            ChuDeDTO tp1 = tpDAO.getIdTp(idParent);
            String nameTp = tp1.getTpTitle();
            Object[] row = {quesDTO.getqID(), quesDTO.getqContent(), nameTp, nameLesson, quesDTO.getqLevel(), quesDTO.getqStatus() == 1 ? "Hoạt đông" : "Ngừng hoạt động", quesDTO.getqPictures()};
            model1.addRow(row);
        }

    }

    private void ShowTableAnwers() {
        model2.setRowCount(0);
        ArrayList<DapAnDTO> arr = anDAL.getAnswers();
        for (DapAnDTO answersDTO : arr) {
            Object[] row = {answersDTO.getqID(), answersDTO.getAwID(), answersDTO.getAwContent(), answersDTO.getAwPictures(), answersDTO.getIsRight() == 1 ? "Đúng" : "Sai", answersDTO.getAwStatus() == 1 ? "Hoạt đông" : "Ngừng hoạt động"};
            model2.addRow(row);
        }

    }

    private void showCustomDialog(JPanel panel) {
        JDialog dialog = new JDialog();
        dialog.setSize(570, 740);
        dialog.setLocationRelativeTo(null);
        dialog.setContentPane(panel);
        dialog.setModal(true);
        dialog.setVisible(true);
    }

    private void searchTable(String keyword) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model1);
        tbQuestions.setRowSorter(sorter);

        if (keyword.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + keyword));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbQuestions = new javax.swing.JTable();
        BtnAdd = new javax.swing.JButton();
        BtnUpdate = new javax.swing.JButton();
        BtnDelete = new javax.swing.JButton();
        BtnDetail = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TableAnswers = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnExcel1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        tbQuestions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbQuestions);

        BtnAdd.setBackground(new java.awt.Color(0, 153, 153));
        BtnAdd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnAdd.setForeground(java.awt.Color.white);
        BtnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add-icon.png"))); // NOI18N
        BtnAdd.setText("Thêm");
        BtnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddActionPerformed(evt);
            }
        });

        BtnUpdate.setBackground(new java.awt.Color(0, 153, 153));
        BtnUpdate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnUpdate.setForeground(java.awt.Color.white);
        BtnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit-icon.png"))); // NOI18N
        BtnUpdate.setText("Sửa");
        BtnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnUpdateActionPerformed(evt);
            }
        });

        BtnDelete.setBackground(new java.awt.Color(0, 153, 153));
        BtnDelete.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnDelete.setForeground(java.awt.Color.white);
        BtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete-icon.png"))); // NOI18N
        BtnDelete.setText("Xóa");
        BtnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDeleteActionPerformed(evt);
            }
        });

        BtnDetail.setBackground(new java.awt.Color(0, 153, 153));
        BtnDetail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BtnDetail.setForeground(java.awt.Color.white);
        BtnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/details-icon.png"))); // NOI18N
        BtnDetail.setText("Chi tiết");
        BtnDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDetailActionPerformed(evt);
            }
        });

        btnExcel.setBackground(new java.awt.Color(0, 153, 153));
        btnExcel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcel.setForeground(java.awt.Color.white);
        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excel-icon.png"))); // NOI18N
        btnExcel.setText("Xuất Excel");
        btnExcel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(0, 153, 153));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset-icon.png"))); // NOI18N
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        TableAnswers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(TableAnswers);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("BẢNG QUẢN LÝ CÂU HỎI");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("BẢNG QUẢN LÝ CÂU TRẢ LỜI");

        btnExcel1.setBackground(new java.awt.Color(0, 153, 153));
        btnExcel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnExcel1.setForeground(java.awt.Color.white);
        btnExcel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/excel-icon.png"))); // NOI18N
        btnExcel1.setText("Nhập excel");
        btnExcel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExcel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(BtnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnUpdate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnDetail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReset)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 950, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(382, 382, 382))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(401, 401, 401))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnAdd)
                        .addComponent(BtnUpdate)
                        .addComponent(BtnDelete)
                        .addComponent(btnExcel)
                        .addComponent(btnExcel1)
                        .addComponent(BtnDetail)
                        .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnUpdateActionPerformed
        // TODO add your handling code here:
        if (selectedQuestionID != -1) {
            showCustomDialog(new SuaCauHoiGUI(selectedQuestionID));

        } else if (selectedAnswerID != -1) {
            showCustomDialog(new SuaDapAnGUI(selectedAnswerID));
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một câu hỏi hoặc câu trả lời trước!");
        }
        //showCustomDialog(new UpdateQuestionsGUI());

    }//GEN-LAST:event_BtnUpdateActionPerformed

    private void BtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddActionPerformed
        // TODO add your handling code here:
        showCustomDialog(new TaoCauHoiGUI());

    }//GEN-LAST:event_BtnAddActionPerformed

    private void BtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDeleteActionPerformed
        // TODO add your handling code here:
        if (selectedQuestionID != -1) {
            try {
                showCustomDialog(new XoaCauHoiGUI(selectedQuestionID));
            } catch (SQLException ex) {
                Logger.getLogger(PnQuanLyCauHoiGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (selectedAnswerID != -1) {
            showCustomDialog(new XoaDapAnGUI(selectedAnswerID));
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một câu hỏi hoặc câu trả lời trước!");
        }

    }//GEN-LAST:event_BtnDeleteActionPerformed

    private void BtnDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDetailActionPerformed
        // TODO add your handling code here:
        if (selectedQuestionID != -1) {

            showCustomDialog(new ChiTietCauHoiGUI(selectedQuestionID));

        } else if (selectedAnswerID != -1) {
            showCustomDialog(new ChiTietDapAnGUI(selectedAnswerID));
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một câu hỏi hoặc câu trả lời trước!");
        }
    }//GEN-LAST:event_BtnDetailActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        showTableQuestions();
        ShowTableAnwers();
        jTextFieldSearch.setText("");
    }//GEN-LAST:event_btnResetActionPerformed
    public void exportTablesToExcel(String filePath) {
        Workbook workbook = new XSSFWorkbook();

        // Tạo sheet cho câu hỏi
        Sheet questionSheet = workbook.createSheet("Danh sách câu hỏi");
        writeTableToSheet(tbQuestions, questionSheet);

        // Tạo sheet cho câu trả lời
        Sheet answerSheet = workbook.createSheet("Danh sách câu trả lời");
        writeTableToSheet(TableAnswers, answerSheet);

        // Ghi ra file Excel
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            workbook.close();
            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel!");
        }
    }

    private void writeTableToSheet(JTable table, Sheet sheet) {
        TableModel model = table.getModel();

        // Ghi tiêu đề cột
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < model.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(model.getColumnName(i));
        }

        // Ghi dữ liệu từng hàng
        for (int row = 0; row < model.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 1);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = excelRow.createCell(col);
                Object value = model.getValueAt(row, col);
                cell.setCellValue(value != null ? value.toString() : "");
            }
        }
    }

    public void importQuestionsAndAnswers(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            Workbook workbook = WorkbookFactory.create(fis);

            // Read questions
            Sheet questionSheet = workbook.getSheetAt(0);
            for (Row row : questionSheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header
                }
                int qID = (int) row.getCell(0).getNumericCellValue();
                String qContent = row.getCell(1).getStringCellValue();
                String qPictures = row.getCell(2).getStringCellValue();
                int qTopicID = (int) row.getCell(3).getNumericCellValue();
                String qLevel = row.getCell(4).getStringCellValue();
                int qStatus = (int) row.getCell(5).getNumericCellValue();

                quesBUS.saveQuestion(qID, qContent, qPictures, qTopicID, qLevel, qStatus);
            }

            // Read answers
            Sheet answerSheet = workbook.getSheetAt(1);
            for (Row row : answerSheet) {
                if (row.getRowNum() == 0) {
                    continue; // Skip header
                }
                // Read each cell, checking for null
                int awID = (int) row.getCell(0).getNumericCellValue(); // Answer ID
                int qID = (int) row.getCell(1).getNumericCellValue(); // Question ID
                String awContent = row.getCell(2).getStringCellValue();     // Answer content
                String awPictures = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "";
                // Answer pictures
                int isRight = (int) row.getCell(4).getNumericCellValue(); // Is correct answer (1 or 0)
                int awStatus = (int) row.getCell(5).getNumericCellValue(); // Answer status (1 or 0)

                // Save the answer with ID
                anBUS.saveAnswer(awID, qID, awContent, awPictures, isRight, awStatus);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        showTableQuestions();
        ShowTableAnwers();
    }
    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file");
        fileChooser.setSelectedFile(new File("DanhSachCauHoi_DapAn.xlsx"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            exportTablesToExcel(fileToSave.getAbsolutePath());
        }

//        XuLyFileExcel xuatFile = new XuLyFileExcel();
//        xuatFile.xuatExcel(tbQuestions);
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnExcel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcel1ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Excel Files", "xlsx", "xls"));

        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            importQuestionsAndAnswers(filePath); // Call your import function

            JOptionPane.showMessageDialog(this, "Import completed successfully!");
        }

//        XuLyFileExcel nhapFile = new XuLyFileExcel();
//        nhapFile.nhapExcel(tbQuestions);
//
//        int row = tbQuestions.getRowCount();
//        for (int i = 0; i < row; i++) {
//            int idcauhoi = Integer.parseInt(tbQuestions.getValueAt(i, 1).toString());
//            String noidung = tbQuestions.getValueAt(i, 2) + "";
//            int chude = Integer.parseInt(tbQuestions.getValueAt(i, 3).toString());
////            String baihoc = tbQuestions.getValueAt(i, 4) + "";
//            String mucdo = tbQuestions.getValueAt(i, 5) + "";
//            int trangthai = Integer.parseInt(tbQuestions.getValueAt(i, 6).toString());
//            String hinhanh = tbQuestions.getValueAt(i, 7) + "";
//
//            quesBUS.nhapCauHoiTuExcel(idcauhoi, noidung, chude, mucdo, trangthai, hinhanh);
//        }
    }//GEN-LAST:event_btnExcel1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdd;
    private javax.swing.JButton BtnDelete;
    private javax.swing.JButton BtnDetail;
    private javax.swing.JButton BtnUpdate;
    private javax.swing.JTable TableAnswers;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnExcel1;
    private javax.swing.JButton btnReset;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTable tbQuestions;
    // End of variables declaration//GEN-END:variables
}
