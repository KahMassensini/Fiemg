package br.com.fiemg.view;

import br.com.fiemg.conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class CadUsuario extends javax.swing.JInternalFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public CadUsuario() {
        initComponents();
        // iniciar a conexão com a classe Conexão
        con = Conexao.conectar();
        desabilitarCampos();
    }

    //método de pesquisa no banco
    private void consultar() {
        //comando para consultar no banco        
        String sql = "Select * from usuario where id = ? ";
        try {
            //envia a consulta para o banco
            pst = con.prepareStatement(sql);
            //seta o valor de Id para o primeiro índice do comando
            pst.setString(1, txtUsuId.getText());            
            //recebe a consulta com executeQuery que será usado somente para o select
            rs = pst.executeQuery();
            if (rs.next()) {
                txtUsuNome.setText(rs.getString(2));
                txtUsuLogin.setText(rs.getString(3));
                txtUsuSenha.setText(rs.getString(4));
                cbUsuPerfil.setSelectedItem(rs.getString(5));
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado");
                desabilitarCampos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID não encontrado:" + e);
        }
    }

    private void inserir() {
        String sql = "Insert into usuario(nome, login, senha, perfil) values(?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cbUsuPerfil.getSelectedItem().toString());
            int op = pst.executeUpdate();
            if (op > 0) {
                JOptionPane.showMessageDialog(null, "Inserido com sucesso!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void editar() {
        String sql = "Update usuario set nome =?, login =?, senha =?, perfil =? where id =? ";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuNome.getText());
            pst.setString(2, txtUsuLogin.getText());
            pst.setString(3, txtUsuSenha.getText());
            pst.setString(4, cbUsuPerfil.getSelectedItem().toString());
            pst.setString(5, txtUsuId.getText());
            int op = pst.executeUpdate();
            if (op > 0) {
                JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void excluir() {
        String sql = "Delete from usuario where id = ?";
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar?", "Atenção", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            int op = pst.executeUpdate();
            if (op > 0) {
                JOptionPane.showMessageDialog(null, "Deletado com sucesso!");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        btnUsuEditar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnUsuNovo = new javax.swing.JButton();
        btnUsuPesquisar = new javax.swing.JButton();
        txtUsuId = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        txtUsuSenha = new javax.swing.JTextField();
        cbUsuPerfil = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnUsuExcluir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Cadatro de Usuário");

        jLabel4.setText("Senha: ");

        btnUsuEditar.setText("Editar");
        btnUsuEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuEditarActionPerformed(evt);
            }
        });

        jLabel5.setText("Perfil:");

        btnUsuNovo.setText("Inserir");
        btnUsuNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuNovoActionPerformed(evt);
            }
        });

        btnUsuPesquisar.setText("Pesquisar");
        btnUsuPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuPesquisarActionPerformed(evt);
            }
        });

        txtUsuNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuNomeActionPerformed(evt);
            }
        });

        cbUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "administrador ", "usuário" }));

        jLabel1.setText("Id:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Login: ");

        btnUsuExcluir.setText("Excluir");
        btnUsuExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnUsuNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUsuPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                        .addComponent(btnUsuEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUsuExcluir)
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUsuNome)
                                .addGap(44, 44, 44))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtUsuSenha))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUsuExcluir)
                    .addComponent(btnUsuEditar)
                    .addComponent(btnUsuNovo)
                    .addComponent(btnUsuPesquisar))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuNomeActionPerformed

    }//GEN-LAST:event_txtUsuNomeActionPerformed

    private void btnUsuPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuPesquisarActionPerformed
        consultar();
    }//GEN-LAST:event_btnUsuPesquisarActionPerformed

    private void btnUsuNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuNovoActionPerformed
        inserir();
    }//GEN-LAST:event_btnUsuNovoActionPerformed

    private void btnUsuEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuEditarActionPerformed
        editar();
    }//GEN-LAST:event_btnUsuEditarActionPerformed

    private void btnUsuExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnUsuExcluirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUsuEditar;
    private javax.swing.JButton btnUsuExcluir;
    private javax.swing.JButton btnUsuNovo;
    private javax.swing.JButton btnUsuPesquisar;
    private javax.swing.JComboBox<String> cbUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtUsuId;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables

    private void desabilitarCampos() {
        txtUsuNome.setText(null);
        txtUsuLogin.setText(null);
        txtUsuSenha.setText(null);
        cbUsuPerfil.setSelectedItem(null);

    }
}
