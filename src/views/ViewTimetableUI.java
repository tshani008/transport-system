/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Vector;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import transportsystem.FareManagment;
import transportsystem.Network;
import transportsystem.Planning;
import transportsystem.Services;
import transportsystem.SetOfFares;
import transportsystem.SetOfNetworks;
import transportsystem.SetOfTimetables;

/**
 *
 * @author theja
 */
public class ViewTimetableUI extends javax.swing.JFrame {

    private static final String FILE_NAME_ROUTES = "Routes.ser";
    private static final String FILE_NAME_Vehicles = "Vehicles.ser";
    private static final String FILE_NAME_Fare = "Fare.ser";
    private static final String FILE_NAME_Fare2 = "Fare2.ser";
    private static final String FILE_NAME_Timetable = "TimeTable.ser";

    private Vector data;
    private TableModel model;
    private Vector header_Table;
    private Vector header_Fare;

    private Services service;
    private SetOfNetworks theRoutes = new SetOfNetworks();
    private SetOfNetworks theVehicle = new SetOfNetworks();
    private SetOfFares theFare = new SetOfFares();
    private SetOfTimetables theTimetables = new SetOfTimetables();
    private Planning timPlanning;
    TableRowSorter<TableModel> sorter_Tables;
    TableRowSorter<TableModel> sorter_Fare;
      TableRowSorter<TableModel> sorter_Tables2;

    /**
     * Creates new form iewTimetableUI
     *
     *
     * @param args
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     * main method
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {

        ViewTimetableUI theGUI = new ViewTimetableUI();
        theGUI.setVisible(true);
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public ViewTimetableUI() throws IOException, ClassNotFoundException {

        initComponents();

        service = new Services();

        try {

            theRoutes.addAll(service.deserialize_routes(FILE_NAME_ROUTES));

        } catch (IOException e) {
            serialize_all();

        }
        try {
            theTimetables.addAll(service.deserialize_timetable(FILE_NAME_Timetable));
        } catch (Exception e) {
            serialize_timetable();
        }
        try {
            theFare.addAll(service.deserialize_fare(FILE_NAME_Fare));
        } catch (Exception e) {
            serialize_timetable();
        }
        header_Table = new Vector();
        header_Table.add("Route Name");
        header_Table.add("Vehicle Number");
         header_Table.add("Transport Type");
        header_Table.add("Depature Time");
        header_Table.add("Arrival Time");
        header_Table.add("Available");

        loadTimeTable(theTimetables) ;

        displayDeserialization();
        displayDeserialization2();

        header_Table = new Vector();
        header_Table.add("Route Name");
         header_Table.add("Vehicle Number");
         header_Table.add("Transport Type");
        header_Table.add("Depature Time");
        header_Table.add("Arrival Time");
        header_Table.add("Available");
        loadTimeTableAvailability(theTimetables);

        header_Fare = new Vector();
        header_Fare.add("Route Name");
        header_Fare.add("Transport Type");
        header_Fare.add("Start Point");
        header_Fare.add("End Point");
        header_Fare.add("Fare");
        loadFareTable(theFare);
    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ConcurrentModificationException
     * Display Deserializing route list
     */
    public final void displayDeserialization() throws IOException, ClassNotFoundException, ConcurrentModificationException {

        Network net = new Network();
        net.getRouteName();

        theRoutes = (SetOfNetworks) service.deserialize_routes(FILE_NAME_ROUTES);

        chooseroutecmb.removeAllItems();
        for (Network holding : theRoutes) {
            chooseroutecmb.addItem(holding.getRouteName());
        }

    }
//Load Timetables
    private void loadTimeTable(SetOfTimetables tbs) throws IOException, ClassNotFoundException {
        data = new Vector<Vector<String>>();
        for (Planning table : tbs) {
            Vector v = new Vector();
            v.add(table.getRoute());
            v.add(table.getType());
            v.add(table.getVehicleno());
            v.add(table.getStarttime());
            v.add(table.getEndtime());
            v.add(table.getStatus());

            data.add(v);

        }
        model = new DefaultTableModel(data, header_Table);

        routetimetable.setModel(model);
        sorter_Tables2 = new TableRowSorter<TableModel>(model);
        routetimetable.setRowSorter(sorter_Tables2);
        jScrollPane2.setViewportView(routetimetable);
        routetimetable.setAutoscrolls(true);
        routetimetable.getTableHeader().setReorderingAllowed(false);

    }
//Load fare Table
    private void loadFareTable(SetOfFares fare) throws IOException, ClassNotFoundException {
        data = new Vector<Vector<String>>();
        for (FareManagment faremanage : fare) {
            Vector v = new Vector();
            v.add(faremanage.getRouteName());
            v.add(faremanage.getNetworkType());
            v.add(faremanage.getStartPoint());
            v.add(faremanage.getEndPoint());
            v.add(faremanage.getFare());

            data.add(v);

        }
        model = new DefaultTableModel(data, header_Fare);

        faretable2.setModel(model);
        sorter_Fare = new TableRowSorter<TableModel>(model);
        faretable2.setRowSorter(sorter_Fare);
        jScrollPane4.setViewportView(faretable2);
        faretable2.setAutoscrolls(true);
        faretable2.getTableHeader().setReorderingAllowed(false);

    }

    private void loadTimeTableAvailability(SetOfTimetables tb) throws IOException, ClassNotFoundException {
        data = new Vector<Vector<String>>();
        for (Planning table : tb) {
            Vector v = new Vector();
            v.add(table.getRoute());
            v.add(table.getType());
            v.add(table.getVehicleno());
            v.add(table.getStarttime());
            v.add(table.getEndtime());
            v.add(table.getStatus());

            data.add(v);

        }
        model = new DefaultTableModel(data, header_Table);

        availabletable.setModel(model);
        sorter_Tables = new TableRowSorter<TableModel>(model);
        availabletable.setRowSorter(sorter_Tables);
        jScrollPane3.setViewportView(availabletable);
        availabletable.setAutoscrolls(true);
        availabletable.getTableHeader().setReorderingAllowed(false);

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ConcurrentModificationException
     */
    public final void displayDeserialization2() throws IOException, ClassNotFoundException, ConcurrentModificationException {

        Network net = new Network();
        net.getRouteName();

        theRoutes = (SetOfNetworks) service.deserialize_routes(FILE_NAME_ROUTES);

        routenamecmb.removeAllItems();
        for (Network holding : theRoutes) {
            routenamecmb.addItem(holding.getRouteName());
        }

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void serialize_all() throws IOException, ClassNotFoundException {

        service.Serialize(theRoutes, FILE_NAME_ROUTES);

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * Serializing Timetable
     */
    public void serialize_timetable() throws IOException, ClassNotFoundException {

        service.Serialize(theTimetables, FILE_NAME_Timetable);

    }

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void serialize_fare() throws IOException, ClassNotFoundException {

        service.Serialize(theFare, FILE_NAME_Fare);

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
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        routenamecmb = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        routetimetable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        Availablecmb = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        availabletable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        chooseroutecmb = new javax.swing.JComboBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        faretable2 = new javax.swing.JTable();

        jLabel1.setText("View Time Tables");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Choose Route   :");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Choose Route  :");

        routenamecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        routenamecmb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                routenamecmbItemStateChanged(evt);
            }
        });
        routenamecmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                routenamecmbActionPerformed(evt);
            }
        });

        routetimetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(routetimetable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(36, 36, 36)
                        .addComponent(routenamecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 341, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(routenamecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        jTabbedPane1.addTab(" View TimeTables By Route  ", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Choose Availability  :");

        Availablecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Daily", "Weekend" }));
        Availablecmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvailablecmbActionPerformed(evt);
            }
        });

        availabletable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(availabletable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(28, 28, 28)
                        .addComponent(Availablecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Availablecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("View TimeTables By Availability ", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Choose Route  :");

        chooseroutecmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        chooseroutecmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseroutecmbActionPerformed(evt);
            }
        });

        faretable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(faretable2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(35, 35, 35)
                        .addComponent(chooseroutecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 371, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(chooseroutecmb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("   View Transport Fares  ", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 520));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void AvailablecmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvailablecmbActionPerformed
        // TODO add your handling code here:
        RowFilter<TableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(Availablecmb.getSelectedItem().toString(), 5);
        } catch (Exception e) {
        }
        sorter_Tables.setRowFilter(rf);


    }//GEN-LAST:event_AvailablecmbActionPerformed

    private void routenamecmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_routenamecmbActionPerformed
        // TODO add your handling code here:

        RowFilter<TableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(routenamecmb.getSelectedItem().toString(),0);
        } catch (Exception e) {
        }
        sorter_Tables2.setRowFilter(rf);

    }//GEN-LAST:event_routenamecmbActionPerformed

    private void routenamecmbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_routenamecmbItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_routenamecmbItemStateChanged

    private void chooseroutecmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseroutecmbActionPerformed
        // TODO add your handling code here:
        RowFilter<TableModel, Object> rf = null;
        try {
            rf = RowFilter.regexFilter(chooseroutecmb.getSelectedItem().toString(), 0);
        } catch (Exception e) {
        }
        try {
            sorter_Fare.setRowFilter(rf);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_chooseroutecmbActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ViewTimetableUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ViewTimetableUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ViewTimetableUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ViewTimetableUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ViewTimetableUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox Availablecmb;
    private javax.swing.JTable availabletable;
    private javax.swing.JComboBox chooseroutecmb;
    private javax.swing.JTable faretable2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox routenamecmb;
    private javax.swing.JTable routetimetable;
    // End of variables declaration//GEN-END:variables
}
