package com.vg.hbase.manager.ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableExistsException;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.util.Bytes;

import com.vg.hbase.comm.manager.HBaseTableData;
import com.vg.hbase.comm.manager.HBaseTableManager;
import com.vg.hbase.comm.manager.HBaseTableStructure;
import com.vg.hbase.comm.manager.HbaseTableObject;
import com.vg.hbase.operations.base.HBaseConfigurationManager;
import com.vg.hbase.operations.base.HbaseManagerTableGetter;

/**
 * 
 * @author skrishnanunni
 */
public class HbaseDataBackupRestoreDialog extends javax.swing.JDialog {
    
    /**
	 * 
	 */
    private static final long serialVersionUID = 5959525532772616338L;
    /**
     * Creates new form HbaseDataBackupRestoreDialog
     */
    DefaultListModel listBackupSelectedModel, listBackupAllTablesModel;
    DefaultListModel listRestoreSelectedModel, listRestoreupAllTablesModel;
    
    HbaseTableObject hbTable = new HbaseTableObject();
    HBaseTableData tableData = new HBaseTableData();
    HBaseTableStructure tableStructure = new HBaseTableStructure();
    HashMap<String, HbaseTableObject> tableObjects = new HashMap<String, HbaseTableObject>();
    
    private HBaseTableManager tblMngr = HbaseManagerTableGetter.getTableManagerObject();
    
    public HbaseDataBackupRestoreDialog(java.awt.Frame parent, boolean modal) {
	super(parent, modal);
	initComponents();
	this.setTitle("Backup/ Restore your Data");
	listBackupAllTablesModel = (DefaultListModel) listAllTables.getModel();
	listBackupSelectedModel = (DefaultListModel) listSelectedTables.getModel();
	listRestoreupAllTablesModel = (DefaultListModel) listRestoreAllTables.getModel();
	listRestoreSelectedModel = (DefaultListModel) listRestoreSelectedTables.getModel();
	populateAvailableTables();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void initComponents() {
	
	jPanel1 = new javax.swing.JPanel();
	jScrollPane1 = new javax.swing.JScrollPane();
	listAllTables = new javax.swing.JList(new DefaultListModel());
	jScrollPane2 = new javax.swing.JScrollPane();
	listSelectedTables = new javax.swing.JList(new DefaultListModel());
	jButton1 = new javax.swing.JButton();
	jButton2 = new javax.swing.JButton();
	jButton3 = new javax.swing.JButton();
	jButton4 = new javax.swing.JButton();
	jButton5 = new javax.swing.JButton();
	jPanel2 = new javax.swing.JPanel();
	jLabel1 = new javax.swing.JLabel();
	jButton6 = new javax.swing.JButton();
	jScrollPane3 = new javax.swing.JScrollPane();
	listRestoreAllTables = new javax.swing.JList(new DefaultListModel());
	jScrollPane4 = new javax.swing.JScrollPane();
	listRestoreSelectedTables = new javax.swing.JList(new DefaultListModel());
	jButton7 = new javax.swing.JButton();
	jButton8 = new javax.swing.JButton();
	jButton9 = new javax.swing.JButton();
	jButton10 = new javax.swing.JButton();
	
	setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
	
	jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
	listAllTables.setModel(new DefaultListModel()
	
	);
	jScrollPane1.setViewportView(listAllTables);
	
	jScrollPane2.setViewportView(listSelectedTables);
	
	jButton1.setText(">>");
	jButton1.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton1ActionPerformed(evt);
	    }
	});
	
	jButton2.setText("<<");
	jButton2.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton2ActionPerformed(evt);
	    }
	});
	
	jButton3.setText("Backup to File");
	jButton3.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton3ActionPerformed(evt);
	    }
	});
	
	jButton4.setText("Migrate");
	
	jButton5.setText("Cancel");
	jButton5.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton5ActionPerformed(evt);
	    }
	});
	
	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	jPanel1.setLayout(jPanel1Layout);
	jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			jPanel1Layout
				.createSequentialGroup()
				.addGap(74, 74, 74)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(18, 18, 18)
				.addGroup(
					jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
							javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76,
							javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(18, 18, 18)
				.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(53, 53, 53)
				.addGroup(
					jPanel1Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99,
							javax.swing.GroupLayout.PREFERRED_SIZE))
				.addContainerGap(57, Short.MAX_VALUE)));
	jPanel1Layout
		.setVerticalGroup(jPanel1Layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel1Layout
					.createSequentialGroup()
					.addGap(37, 37, 37)
					.addComponent(jButton1)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jButton2).addGap(96, 96, 96))
			.addGroup(
				jPanel1Layout
					.createSequentialGroup()
					.addGroup(
						jPanel1Layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
								jPanel1Layout
									.createSequentialGroup()
									.addGap(19, 19, 19)
									.addGroup(
										jPanel1Layout
											.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
											.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												230,
												javax.swing.GroupLayout.PREFERRED_SIZE)
											.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												230,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
							.addGroup(
								jPanel1Layout.createSequentialGroup()
									.addGap(57, 57, 57).addComponent(jButton3)
									.addGap(26, 26, 26).addComponent(jButton4)
									.addGap(30, 30, 30).addComponent(jButton5)))
					.addContainerGap(39, Short.MAX_VALUE)));
	
	jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	
	jLabel1.setText("Select Backup File :");
	
	jButton6.setText("...");
	jButton6.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton6ActionPerformed(evt);
	    }
	});
	
	listRestoreAllTables.setModel(new DefaultListModel());
	jScrollPane3.setViewportView(listRestoreAllTables);
	
	jScrollPane4.setViewportView(listRestoreSelectedTables);
	
	jButton7.setText(">>");
	jButton7.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton7ActionPerformed(evt);
	    }
	});
	
	jButton8.setText("<<");
	jButton8.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton8ActionPerformed(evt);
	    }
	});
	
	jButton9.setText("Restore");
	jButton9.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton9ActionPerformed(evt);
	    }
	});
	
	jButton10.setText("Exit");
	jButton10.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton10ActionPerformed(evt);
	    }
	});
	
	javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	jPanel2.setLayout(jPanel2Layout);
	jPanel2Layout
		.setHorizontalGroup(jPanel2Layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel2Layout
					.createSequentialGroup()
					.addGroup(
						jPanel2Layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addGroup(
								jPanel2Layout
									.createSequentialGroup()
									.addGap(27, 27, 27)
									.addComponent(jLabel1)
									.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(jButton6))
							.addGroup(
								jPanel2Layout
									.createSequentialGroup()
									.addGap(102, 102, 102)
									.addComponent(jScrollPane3,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										138,
										javax.swing.GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addGroup(
										jPanel2Layout
											.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
											.addComponent(
												jButton7,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												65,
												javax.swing.GroupLayout.PREFERRED_SIZE)
											.addComponent(
												jButton8,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												65,
												javax.swing.GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
									.addComponent(jScrollPane4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										129,
										javax.swing.GroupLayout.PREFERRED_SIZE)
									.addGap(51, 51, 51)
									.addGroup(
										jPanel2Layout
											.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
											.addComponent(
												jButton9,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
											.addComponent(
												jButton10,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))))
					.addContainerGap(80, Short.MAX_VALUE)));
	jPanel2Layout
		.setVerticalGroup(jPanel2Layout
			.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				jPanel2Layout
					.createSequentialGroup()
					.addGap(24, 24, 24)
					.addGroup(
						jPanel2Layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
							.addComponent(jLabel1).addComponent(jButton6))
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
					.addGroup(
						jPanel2Layout
							.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
							.addComponent(jScrollPane3).addComponent(jScrollPane4))
					.addContainerGap())
			.addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel2Layout
					.createSequentialGroup()
					.addGap(73, 73, 73)
					.addComponent(jButton7)
					.addGap(18, 18, 18)
					.addComponent(jButton9)
					.addGap(18, 18, 18)
					.addComponent(jButton10)
					.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9,
						Short.MAX_VALUE).addComponent(jButton8).addGap(32, 32, 32)));
	
	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	getContentPane().setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		layout.createSequentialGroup()
			.addContainerGap()
			.addGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
					.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
		layout.createSequentialGroup()
			.addContainerGap()
			.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
			.addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
			.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	
	pack();
    }// </editor-fold>
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
	// HBaseTableManager tblmngr = HbaseManagerTableGetter.getTblManager();
	byte[][] colFamilys;
	HashMap<String, String[][]> tableDataList;
	List<byte[]> rowKeyList = new ArrayList<byte[]>();
	
	final JFileChooser fc = new JFileChooser();
	
	fc.showSaveDialog(this);
	ObjectOutputStream writeObjectFile = null;
	try {
	    // JOptionPane.
	    fc.getSelectedFile().getName();
	    writeObjectFile = new ObjectOutputStream(new FileOutputStream(fc.getSelectedFile()));
	    
	}
	catch (IOException ex) {
	    Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(Level.SEVERE, null, ex);
	}
	try {
	    for (int i = 0; i < listSelectedTables.getModel().getSize(); i++) {
		String tblName = (String) listSelectedTables.getModel().getElementAt(i);// (String)listSelectedTables.
		listSelectedTables.setSelectedValue(tblName, true);
		Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName())
			.log(Level.INFO, "Table name: " + tblName);
		HBaseTableStructure tableStructure = new HBaseTableStructure();
		tableStructure.createWriteTableStructure(tblName);
		
		colFamilys = tableStructure.getAllColoumnFamilies();
		
		ResultScanner resultScan = HbaseManagerTableGetter.getAllFamilyList("0", "zz", colFamilys, tblName);
		
		tableDataList = getObjectList(resultScan, colFamilys, rowKeyList);
		
		// HbaseTbleObject ob;
		
		HbaseTableObject hbTable = new HbaseTableObject();
		HBaseTableData tableData = new HBaseTableData();
		
		tableData.setDbDataList(tableDataList);
		tableData.setRowKeylist(rowKeyList);
		
		hbTable.setTableData(tableData);
		hbTable.setTableStructure(tableStructure);
		
		writeObjectFile.writeObject(hbTable);
		listSelectedTables.setSelectedValue(tblName, false);
		
	    } // write hbTable to file, object stream;
	}
	catch (IOException ex) {
	    Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(Level.SEVERE, null, ex);
	    try {
		writeObjectFile.close();
	    }
	    catch (IOException ex1) {
		Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(Level.SEVERE, null, ex1);
	    }
	}
	
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	
	Object[] sellist = listAllTables.getSelectedValues();
	for (int i = 0; i < sellist.length; i++)
	    listBackupSelectedModel.addElement((String) sellist[i]);
	
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	int[] sellist = listSelectedTables.getSelectedIndices();
	for (int i = 0; i < sellist.length; i++)
	    listBackupSelectedModel.remove(listSelectedTables.getSelectedIndex());
    }
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
	
	// HBaseTableManager tblmngr = HbaseManagerTableGetter.getTblManager();
	// byte[][] colFamilys;
	// List<Put> putList = null;
	String tableName = null;
	final JFileChooser fc = new JFileChooser();
	
	fc.showOpenDialog(new JLabel("Select the Backup File"));
	
	ObjectInputStream readObjectFile = null;
	try {
	    // JOptionPane.
	    readObjectFile = new ObjectInputStream(new FileInputStream(fc.getSelectedFile()));
	    
	}
	catch (IOException ex) {
	    Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	while (true) {
	    try {
		hbTable = (HbaseTableObject) readObjectFile.readObject();
		
		tableName = hbTable.getTableStructure().getHTableName();
		
		listRestoreupAllTablesModel.addElement(tableName);
		
		tableObjects.put(tableName, hbTable);
		
	    }
	    catch (Exception e) {
		try {
		    readObjectFile.close();
		}
		catch (IOException ex1) {
		    Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(Level.SEVERE, null, ex1);
		}
		System.out.print("Restoring Failed at " + tableName);
		break;
		
	    }
	    
	}
	
    }
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
	
	String tableName = null;
	
	List<Put> putList = null;
	
	for (int i = 0; i < listRestoreSelectedTables.getModel().getSize(); i++) {
	    try {
		tableName = (String) listRestoreSelectedTables.getModel().getElementAt(i);
		listRestoreSelectedTables.setSelectedValue(tableName, true);
		hbTable = tableObjects.get(tableName);
		
		tableData = hbTable.getTableData();
		
		tableStructure = hbTable.getTableStructure();
		
		HTableDescriptor descriptor = tableStructure.createReadTableStructure();
		byte[][] family = createTable(descriptor);
		
		putList = createPutList(tableData, family);
		
		HbaseManagerTableGetter.setTable(tableStructure.getHTableName());
		HbaseManagerTableGetter._insert(putList);
		listRestoreSelectedTables.setSelectedValue(tableName, false);
		
	    }
	    catch (Exception e) {
		System.out.print("Restore of " + tableName + " Failed");
		break;
	    }
	    
	}
	
    }
    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
	
	Object[] sellist = listRestoreAllTables.getSelectedValues();
	for (int i = 0; i < sellist.length; i++)
	    listRestoreSelectedModel.addElement((String) sellist[i]);
	
    }
    
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
	
	int[] sellist = listRestoreSelectedTables.getSelectedIndices();
	for (int i = 0; i < sellist.length; i++)
	    listRestoreSelectedModel.remove(listRestoreSelectedTables.getSelectedIndex());
	
    }
    
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
	this.dispose();
    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
	this.dispose();
    }
    
    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {
	/*
	 * Set the Nimbus look and feel
	 */
	// <editor-fold defaultstate="collapsed"
	// desc=" Look and feel setting code (optional) ">
	/*
	 * If Nimbus (introduced in Java SE 6) is not available, stay with the
	 * default look and feel. For details see
	 * http://download.oracle.com/javase
	 * /tutorial/uiswing/lookandfeel/plaf.html
	 */
	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    javax.swing.UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	}
	catch (ClassNotFoundException ex) {
	    java.util.logging.Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	catch (InstantiationException ex) {
	    java.util.logging.Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	catch (IllegalAccessException ex) {
	    java.util.logging.Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(HbaseDataBackupRestoreDialog.class.getName()).log(
		    java.util.logging.Level.SEVERE, null, ex);
	}
	// </editor-fold>
	
	/*
	 * Create and display the dialog
	 */
	java.awt.EventQueue.invokeLater(new Runnable() {
	    
	    public void run() {
		HbaseDataBackupRestoreDialog dialog = new HbaseDataBackupRestoreDialog(new javax.swing.JFrame(), true);
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
		    
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent e) {
			System.exit(0);
		    }
		});
		dialog.setVisible(true);
	    }
	});
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList listAllTables;
    private javax.swing.JList listRestoreAllTables;
    private javax.swing.JList listRestoreSelectedTables;
    private javax.swing.JList listSelectedTables;
    
    // End of variables declaration
    
    private byte[][] createTable(HTableDescriptor tableDesc) {
	
	HBaseAdmin admin = HBaseConfigurationManager.getHbaseAdmin();
	
	try {
	    
	    admin.createTable(tableDesc);
	    
	}
	catch (TableExistsException e) {
	    int sel = JOptionPane.showConfirmDialog(this, "Table Already Exist, Add Data ???", "Warning!",
		    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	    
	    if (sel == JOptionPane.NO_OPTION) {
		this.dispose();
	    }
	    
	}
	catch (IOException ex) {
	    JOptionPane.showMessageDialog(this, "Table Creation Failed", "Error", JOptionPane.ERROR_MESSAGE);
	    Logger.getLogger(HBaseManagerTableDesign.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	byte[][] families = null;
	
	Set<byte[]> fams = tableDesc.getFamiliesKeys();
	families = new byte[fams.size()][];
	fams.toArray(families);
	
	return families;
	
    }
    
    private List<Put> createPutList(HBaseTableData tableData, byte[][] family) {
	
	HashMap<String, String[][]> dataList = tableData.getDbDataList();
	
	List<Put> putList = new ArrayList<Put>();
	
	Put rowPut;
	String Data[][];
	
	Set<String> keys = dataList.keySet();
	String[] rowlist = new String[keys.size()];
	keys.toArray(rowlist);
	
	String[] rowfamPair = new String[2];
	
	for (int i = 0; i < rowlist.length; i++) {
	    
	    rowfamPair = rowlist[i].split(",");
	    rowPut = new Put(Bytes.toBytes(rowfamPair[1]));
	    Data = dataList.get(rowlist[i]);
	    
	    rowPut = preparePut(Data, rowfamPair[0], rowPut);
	    System.out.println("Adding: " + rowfamPair[0]);
	    
	    putList.add(rowPut);
	    
	}
	return putList;
	
    }
    
    private Put preparePut(String[][] Data, String family, Put rowPut) {
	
	for (int i = 1; i < Data.length; i++) {
	    System.out.println("Adding put Object:" + family + Data[i][1] + Data[i][0]);
	    rowPut.add(Bytes.toBytes(family), Bytes.toBytes(Data[i][1]), Bytes.toBytes(Data[i][0]));
	    
	}
	
	return rowPut;
	
    }
    
    private void populateAvailableTables() {
	
	String[] tables = HBaseTableManager.getAllTableNames();
	for (int i = 0; i < tables.length; i++)
	    listBackupAllTablesModel.addElement(tables[i]);
	
    }
    
    private HashMap<String, String[][]> getObjectList(ResultScanner resultScan, byte[][] family, List<byte[]> rowKeyList) {
	String tableFamilies;// = new table;
	String data[][];
	String rowKey;
	HashMap<String, String[][]> tableDataList = new HashMap<String, String[][]>();
	
	long k = 0;
	for (Result rows : resultScan) {
	    System.out.println("Rows passed : " + (++k));
	    for (int i = 0; i < family.length; i++) {
		
		tableFamilies = Bytes.toString(family[i]);
		rowKey = Bytes.toString(rows.getRow());
		
		rowKeyList.add(rows.getRow());
		
		if (tblMngr.getDataFiller(rows, tableFamilies)) {
		    data = tblMngr.getResultMap();
		    tableFamilies = tableFamilies + "," + rowKey;
		    tableDataList.put(tableFamilies, data);
		}
		
	    }
	}
	return tableDataList;
    }
}
