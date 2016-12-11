package projetobd;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

public class Relatorio extends javax.swing.JDialog{

    Connection connection;
    ResultSet rsPDF;
    /**
     * Creates new form Relatorio
     */
    public Relatorio(java.awt.Frame parent, boolean modal, Connection connection){
        super(parent, modal);
        this.connection = connection;
        initComponents();
        buscaDados();
        this.setLocationRelativeTo(null);        
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        botaoPDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatório atletas");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tabela.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Nº de jogos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabela);
        if (tabela.getColumnModel().getColumnCount() > 0) {
            tabela.getColumnModel().getColumn(0).setResizable(false);
            tabela.getColumnModel().getColumn(1).setResizable(false);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(350);
            tabela.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Atletas que participaram de 3 ou mais jogos:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projetobd/Tokyo-2020-menor.jpg"))); // NOI18N

        botaoPDF.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        botaoPDF.setText("Salvar como PDF");
        botaoPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(197, 197, 197)
                        .addComponent(jLabel2)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoPDF)
                .addGap(261, 261, 261))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(botaoPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPDFActionPerformed
        Document relatorioPDF = new Document();
        
        try{ 
            JFileChooser selCaminho = new JFileChooser();
            int returnVal = selCaminho.showOpenDialog(this);
            
            if(returnVal != JFileChooser.APPROVE_OPTION) {
                return;
            }else{
                 File arq = selCaminho.getSelectedFile().getAbsoluteFile();  
                String caminho = arq.getPath() + ".pdf"; 
                //PdfWriter.getInstance(relatorioPDF, new FileOutputStream("C:\\Users\\Guilherme\\Desktop\\Relatório.pdf"));
                PdfWriter.getInstance(relatorioPDF, new FileOutputStream(caminho));

                relatorioPDF.open();

                relatorioPDF.setPageSize(PageSize.A4);

                Font fontbold = FontFactory.getFont("Times-Roman", 24, Font.BOLD);
                Font fontnormal = FontFactory.getFont("Times-Roman", 18, Font.UNDERLINE);
                Font fontnormalp = FontFactory.getFont("Times-Roman", 14, Font.NORMAL);

                relatorioPDF.add(new Paragraph("Relatório Tóquio 2020", fontbold));
                relatorioPDF.add( Chunk.NEWLINE );
                relatorioPDF.add(new Paragraph("Atletas que participaram de 3 ou mais jogos:", fontnormal));
                relatorioPDF.add( Chunk.NEWLINE );

                ResultSet rs = consulta();

                while (rs.next()) {
                    relatorioPDF.add(new Paragraph(rs.getString("ID_") + " - " + rs.getString("NOME") + 
                            " - " + rs.getString("COUNT(*)") + " jogos.", fontnormalp));
                    relatorioPDF.add( Chunk.NEWLINE );
                }
            }
            
            
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            relatorioPDF.close();
        }
    }//GEN-LAST:event_botaoPDFActionPerformed

    private void buscaDados(){
        try{
            ResultSet rs = consulta();
            DefaultTableModel model = (DefaultTableModel) tabela.getModel();
            while (rs.next()) {
                model.addRow(new String[]{rs.getString("ID_"), rs.getString("NOME"),
                    rs.getString("COUNT(*)")});
            }
        }catch(SQLException ex){
            Logger.getLogger(Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ResultSet consulta(){       
        try{
            Statement stmt;
            ResultSet rs;
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT A.ID_, A.NOME, COUNT(*)\n" +
                "  FROM COMPETEEQUIPE C JOIN PARTICIPAEQUIPE P\n" +
                "    ON C.EQUIPE = P.EQUIPE\n" +
                "  JOIN ATLETA A\n" +
                "    ON P.ATLETA = A.ID_\n" +
                "  WHERE ((C.PONTUACAO IS NOT NULL) AND (C.COLOCACAO IS NOT NULL))\n" +
                "  GROUP BY A.ID_, A.NOME\n" +
                "    HAVING COUNT(*)>=3");
            return rs;
        }catch(SQLException ex){
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoPDF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
