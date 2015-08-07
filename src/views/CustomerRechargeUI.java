/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import transportsystem.Account;
import transportsystem.OtherServices;
import transportsystem.SetOfAccounts;
import transportsystem.SetOfTokens;
import transportsystem.Token;

/**
 *
 * @author Manjula
 */
public class CustomerRechargeUI extends javax.swing.JFrame {
    
    private Token token;
    private OtherServices services;
    private SetOfTokens allTokens = new SetOfTokens();
    private Account account;
    private Account selectedAccount;
    private SetOfAccounts allAccounts = new SetOfAccounts();
    private SetOfAccounts modifiedList = new SetOfAccounts();
    private Vector data;
    private float selectedBalance;
    
    private static final String FILE_NAME_TOKENS = "CustomerTokens.ser";
    private static final String FILE_NAME_ACCOUNTS = "CustomerAccounts.ser";
    
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        CustomerRechargeUI customerRechargeUI = new CustomerRechargeUI();
        customerRechargeUI.setVisible(true);
        
    }
    
    public CustomerRechargeUI() throws IOException, ClassNotFoundException {
        initComponents();
        
        services = new OtherServices();
        
        try {
            allTokens.addAll(services.deserialize_Tokens(FILE_NAME_TOKENS));
            allAccounts.addAll(services.deserialize_Accounts(FILE_NAME_ACCOUNTS));
        } catch (IOException e) {
//            serialize_all();
        }
        
    }
    
    private void loadBalance(SetOfAccounts ac) throws IOException, ClassNotFoundException {
        
        int accNo = Integer.parseInt(txtaccNo.getText());
        float balance = 0;
        String bal1 = null;
        
        for (Account account : ac) {
            
            if (accNo == (account.getAccountNo())) {
                balance = (account.getBalance());
                bal1 = String.valueOf(balance);
                
            }
            
            lblBalance.setText(bal1);
        }
        
    }
    
    private void loadBalanceDetails(SetOfAccounts ax) throws IOException, ClassNotFoundException {
        
        int accNo = Integer.parseInt(txtaccNo.getText());
        //float balance = 0;
        //String name = null;

        for (Account account : ax) {
            if (accNo == (account.getAccountNo())) {
                
                account.setBalance(account.getBalance() + Float.parseFloat(lblBalance.getText()));
                int i = ax.indexOf(account);
                allAccounts.set(i, account);
            }
        }
        // if(!isExist) allAccounts.add(new Account)

        try {
            
            services.Serialize(allAccounts, FILE_NAME_ACCOUNTS);
        } catch (Exception e) {
        }
        
    }

    private void loanupdate(SetOfAccounts ax) throws IOException, ClassNotFoundException {
        
        int accNo = Integer.parseInt(txtaccNo.getText());
        //float balance = 0;
        //String name = null;
        float loan = Float.parseFloat(hideloantxt.getText());
        
        for (Account account : ax) {
            if (accNo == (account.getAccountNo())) {
                
                account.setBalance((account.getBalance() + Float.parseFloat(lblBalance.getText())) - loan);
                int i = ax.indexOf(account);
                allAccounts.set(i, account);
            }
        }
        // if(!isExist) allAccounts.add(new Account)

        try {
            
            services.Serialize(allAccounts, FILE_NAME_ACCOUNTS);
        } catch (Exception e) {
        }
        
    }

    /* private void updateBalance(SetOfAccounts ac) throws IOException, ClassNotFoundException
     {
     
     int accNo = Integer.parseInt(txtaccNo.getText());
     float balance;
     String ubalance1 = null;
     float ubalance;
     float amount=Float.parseFloat(txttknAmount.getText());
    
     for (Account account: ac )
     {
        
     if (accNo == (account.getAccountNo()))
     {
     balance=(account.getBalance());
     allAccounts.removeBalance(allAccounts.findBalance(balance));
     ubalance=amount+balance;

     ubalance1= Float.toString(balance);
                   
     }
                 
     lblBalance.setText(ubalance1);    
     }
        
     }*/
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
        jLabel2 = new javax.swing.JLabel();
        txtaccNo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txttknAmount = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        lblBalance = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        hideloantxt = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 204, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 51), 2));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Recharge Card");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Account No  :");

        txtaccNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtaccNoActionPerformed(evt);
            }
        });
        txtaccNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtaccNoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaccNoKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Amount   :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Balance   :");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Recharge ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        lblBalance.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lblBalance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBalanceMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jLabel6.setText("Back");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtaccNo, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                                    .addComponent(txttknAmount)
                                    .addComponent(lblBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(hideloantxt, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 58, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtaccNo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txttknAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(lblBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(hideloantxt, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(75, 75, 75)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        txttknAmount.setText("");
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtaccNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaccNoKeyReleased

    }//GEN-LAST:event_txtaccNoKeyReleased

    private void lblBalanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBalanceMouseClicked

    }//GEN-LAST:event_lblBalanceMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        float amount = Float.parseFloat(txttknAmount.getText());
        float kBalance = Float.parseFloat(lblBalance.getText());
        float fbalance = amount + kBalance;
        int accNo = Integer.parseInt(txtaccNo.getText());
        String gbalance = String.valueOf(fbalance);
        float loanblance = allAccounts.getloanFromNumber(accNo);
        String loanval = String.valueOf(loanblance);
        hideloantxt.setText(loanval);
        if (loanblance == 0) {            
            try {
                loadBalanceDetails(OtherServices.deserialize_Accounts(FILE_NAME_ACCOUNTS));

                //allAccounts.add(new Account(Integer.parseInt(lblAccountNo.getText()),txtPassengerName.getText(),txtNIC.getText(),txtAddress.getText(),txtUserName.getText(),txtPassword.getText(),balance,loanAmount));
            } catch (IOException ex) {
                Logger.getLogger(CustomerRechargeUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CustomerRechargeUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (txtaccNo.getText().equalsIgnoreCase("") || txttknAmount.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(rootPane, "Please fill all the fields !!!!!!");
            } else {
                lblBalance.setText(gbalance);
                
                try {
                    serialize_all();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(CustomerRechargeUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JOptionPane.showMessageDialog(rootPane, "Recharged successfully !!!!!!");
                txtaccNo.setText("");
                txttknAmount.setText("");
                lblBalance.setText("");
                
            }
            
        } else if(loanblance>0){
            try {
                loanupdate(OtherServices.deserialize_Accounts(FILE_NAME_ACCOUNTS));
                loadBalanceDetails(OtherServices.deserialize_Accounts(FILE_NAME_ACCOUNTS));
                
                
                   try {
                    serialize_all();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(CustomerRechargeUI.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                JOptionPane.showMessageDialog(rootPane, "Recharged successfully and Deducted the Loan ");
                txtaccNo.setText("");
                txttknAmount.setText("");
                lblBalance.setText("");
            } catch (Exception e) {
            }
            
        }        
        

    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtaccNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtaccNoActionPerformed
        
        try {
            loadBalance(allAccounts);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(CustomerRechargeUI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_txtaccNoActionPerformed

    private void txtaccNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaccNoKeyTyped
        // TODO add your handling code here:
        numbersOnlyValidation(evt);
    }//GEN-LAST:event_txtaccNoKeyTyped

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        new CustomerHomeUI().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new EmployeeHomeUI().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    public void serialize_all() throws IOException, ClassNotFoundException {
        
        services.Serialize(allTokens, FILE_NAME_TOKENS);
        services.Serialize(allAccounts, FILE_NAME_ACCOUNTS);
        
    }
    
    public void selectAccountBalance() {
        selectedAccount = allAccounts.findBalance(selectedBalance);
        System.out.println("Account selected : " + selectedBalance);
    }

    /**
     *
     *
     * Numbers are only Accepted
     */
    private void numbersOnlyValidation(java.awt.event.KeyEvent evt) {
        
        try {
            char ch = evt.getKeyChar();
            if (!Character.isDigit(ch)) {
                
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *
     * Letters are only Accepted
     */
    private void lettersOnlyValidation(java.awt.event.KeyEvent evt) {
        try {
            char ch = evt.getKeyChar();
            int no = evt.getKeyCode();
            
            if (Character.isLetter(ch)) {
                
            } else if (Character.isLetter(ch) || Character.isDigit(ch)) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(rootPane, "Characters Only !");
                
            }
            
        } catch (HeadlessException e) {
        }
        
    }

    /*public void balanceUpdate(float balance,int accountNo) 
     {
     balance=selectedBalance;
     float amount = Float.parseFloat(txttknAmount.getText());
     float ubalance= balance+amount;
     allAccounts.removeBalance(accountNo,balance);
     allAccounts.updateBalance(ubalance, accountNo);
     }
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField hideloantxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBalance;
    private javax.swing.JTextField txtaccNo;
    private javax.swing.JTextField txttknAmount;
    // End of variables declaration//GEN-END:variables
}
