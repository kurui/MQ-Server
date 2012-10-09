package com.kurui.kums.message.jms.webspere.receive;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.Vector;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * Created by IntelliJ IDEA. User: ���� Date: 2009-3-6 Time: 8:59:22 ��˰�ⱨ�Ľ���
 */
public class SskkRcvServerFrame extends JFrame {

	private JPanel contentPane; // ������

	private JPanel panMain;// ������

	private JPanel panTable;// �������

	private JPanel panButton;// ��ť����

	private JLabel lblHostIP; // ���IP
	private JTextField txtHostIP;

	private JLabel lblQueueManager; // ���й�����
	private JTextField txtQueueManager;

	private JLabel lblIn; // ���Ͷ���
	private JTextField txtIn;

	private JLabel lblOut; // ���ܶ���
	private JTextField txtOut;

	private JLabel lblServerConnect; // ����l��ͨ��
	private JTextField txtServerConnect;

	private JLabel lblPort; // l�Ӷ˿�
	private JTextField txtPort;

	private JLabel lblCoding; // ���ձ���
	private JTextField txtCoding;

	private JTextField tcOutTime; // ����ʱ��
	private JTextField tcMessage; // ����
	private JTextField tcIsSucceed;// �Ƿ�����

	JTable jTable1;
	JScrollPane jScrollPane1;
	private String[] columnHeader = new String[] { "����ʱ��", "����", "�Ƿ�����" };

	public static Vector getRowData() {
		return rowData;
	}

	public static NdTableModel getTm() {
		return tm;
	}

	private static Vector rowData;
	private static NdTableModel tm;

	private JButton btnSaveFile; // �����ļ�
	private JButton btnReset; // ���
	private JButton btnEnter; // ȷ��
	MQTest mqRcv;
	Thread t;

	public void init() {
		// ���ý���
		this.setResizable(false);
		this.getContentPane().setLayout(null);
		this.setTitle("��˰�ⱨ�Ľ���");
		this.setSize(new Dimension(790, 520));
		this.getContentPane().setBackground(Color.lightGray);
		this.getContentPane().setEnabled(false);

		// ���õ�����
		contentPane = new JPanel();
		contentPane.setBounds(new Rectangle(0, 0, 790, 520));
		contentPane.setBackground(Color.lightGray);
		contentPane.setLayout(null);

		// ����������
		panMain = new JPanel();
		panMain.setFont(new java.awt.Font("Dialog", 0, 12));
		panMain.setBorder(BorderFactory.createEtchedBorder());
		panMain.setBounds(new Rectangle(5, 2, 770, 82));
		panMain.setLayout(null);

		// ����Table����
		panTable = new JPanel();
		panTable.setBounds(new Rectangle(5, 84, 770, 374));
		panTable.setLayout(new BorderLayout());

		// ���ð�ť����
		panButton = new JPanel();
		panButton.setBackground(Color.lightGray);
		panButton.setBounds(new Rectangle(5, 459, 770, 24));
		panButton.setLayout(null);

		// �������IP�ı�ǩ���������ı������������������
		lblHostIP = new JLabel();
		lblHostIP.setFont(new java.awt.Font("Dialog", 0, 12));
		lblHostIP.setText("���IP");
		lblHostIP.setBounds(new Rectangle(2, 2, 79, 22));
		txtHostIP = new JTextField();
		txtHostIP.setFont(new java.awt.Font("Dialog", 0, 12));
		txtHostIP.setText("127.0.0.1");
		txtHostIP.setBounds(new Rectangle(79, 2, 304, 22));
		panMain.add(lblHostIP, null);
		panMain.add(txtHostIP, null);

		// ���ö��й�����ı�ǩ���������ı������������������
		lblQueueManager = new JLabel();
		lblQueueManager.setFont(new java.awt.Font("Dialog", 0, 12));
		lblQueueManager.setText("���й�����");
		lblQueueManager.setBounds(new Rectangle(387, 2, 79, 22));
		txtQueueManager = new JTextField();
		txtQueueManager.setFont(new java.awt.Font("Dialog", 0, 12));
		txtQueueManager.setText("QM_TIPS_2600_01");
		txtQueueManager.setBounds(new Rectangle(466, 2, 302, 22));
		panMain.add(lblQueueManager, null);
		panMain.add(txtQueueManager, null);

		// ���÷��Ͷ��еı�ǩ���������ı������������������
		lblIn = new JLabel();
		lblIn.setFont(new java.awt.Font("Dialog", 0, 12));
		lblIn.setText("���Ͷ���");
		lblIn.setBounds(new Rectangle(2, 30, 79, 22));
		txtIn = new JTextField();
		txtIn.setFont(new java.awt.Font("Dialog", 0, 12));
		txtIn.setText("PBC.EXT.ONLINE.IN");
		txtIn.setBounds(new Rectangle(79, 30, 304, 22));
		panMain.add(lblIn, null);
		panMain.add(txtIn, null);

		// ���ý��ն��еı�ǩ���������ı������������������
		lblOut = new JLabel();
		lblOut.setFont(new java.awt.Font("Dialog", 0, 12));
		lblOut.setText("���ն���");
		lblOut.setBounds(new Rectangle(387, 30, 79, 22));
		txtOut = new JTextField();
		txtOut.setFont(new java.awt.Font("Dialog", 0, 12));
		txtOut.setText("PBC.2600.ONLINE.OUT");
		txtOut.setBounds(new Rectangle(466, 30, 302, 22));
		panMain.add(lblOut, null);
		panMain.add(txtOut, null);

		// ���÷���l��ͨ�5ı�ǩ���������ı������������������
		lblServerConnect = new JLabel();
		lblServerConnect.setFont(new java.awt.Font("Dialog", 0, 12));
		lblServerConnect.setText("����l��ͨ��");
		lblServerConnect.setBounds(new Rectangle(2, 58, 79, 22));
		txtServerConnect = new JTextField();
		txtServerConnect.setFont(new java.awt.Font("Dialog", 0, 12));
		txtServerConnect.setText("SVRCONN1");
		txtServerConnect.setBounds(new Rectangle(79, 58, 154, 22));
		panMain.add(lblServerConnect, null);
		panMain.add(txtServerConnect, null);

		// ����l�Ӷ˿ڵı�ǩ���������ı������������������
		lblPort = new JLabel();
		lblPort.setFont(new java.awt.Font("Dialog", 0, 12));
		lblPort.setText("l�Ӷ˿�");
		lblPort.setBounds(new Rectangle(237, 58, 70, 22));
		txtPort = new JTextField();
		txtPort.setFont(new java.awt.Font("Dialog", 0, 12));
		txtPort.setText("1415");
		txtPort.setBounds(new Rectangle(307, 58, 76, 22));
		panMain.add(lblPort, null);
		panMain.add(txtPort, null);

		// ���ý��ձ���ı�ǩ���������ı������������������
		lblCoding = new JLabel();
		lblCoding.setFont(new java.awt.Font("Dialog", 0, 12));
		lblCoding.setText("���ձ���");
		lblCoding.setBounds(new Rectangle(387, 58, 79, 22));
		txtCoding = new JTextField();
		txtCoding.setFont(new java.awt.Font("Dialog", 0, 12));
		txtCoding.setText("819");
		txtCoding.setBounds(new Rectangle(466, 58, 110, 22));
		panMain.add(lblCoding, null);
		panMain.add(txtCoding, null);

		btnEnter = new JButton();
		btnEnter.setBounds(new Rectangle(630, 58, 97, 22));
		btnEnter.setText("l��(E)");
		btnEnter.setMnemonic('E');
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEnter_actionPerformed(e);
			}
		});
		panMain.add(btnEnter, null);

		// ���ñ?Ԫ��
		tcOutTime = new JTextField();
		tcOutTime.setEditable(false);

		tcMessage = new JTextField();
		tcMessage.setEditable(false);

		tcIsSucceed = new JTextField();
		tcIsSucceed.setEditable(false);

		// ���ñ��
		rowData = new Vector();
		rowData.add(new String[] { "", "", "" });
		tm = new NdTableModel(rowData, columnHeader);
		jTable1 = new JTable(tm);

		jTable1.getTableHeader().setReorderingAllowed(false); // �����б��ⲻ���ƶ�
		jTable1.setRowHeight(20); // �����и�
		jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
		jTable1.getColumnModel().getColumn(0).setCellEditor(
				new DefaultCellEditor(tcOutTime));
		jTable1.getColumnModel().getColumn(1).setPreferredWidth(500);
		jTable1.getColumnModel().getColumn(1).setCellEditor(
				new DefaultCellEditor(tcMessage));
		jTable1.getColumnModel().getColumn(2).setPreferredWidth(35);
		jTable1.getColumnModel().getColumn(2).setCellEditor(
				new DefaultCellEditor(tcIsSucceed));

		jScrollPane1 = new JScrollPane();
		panTable.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jTable1, null);

		btnSaveFile = new JButton();
		btnSaveFile.setBounds(new Rectangle(291, 0, 97, 22));
		btnSaveFile.setText("����(S)");
		btnSaveFile.setMnemonic('S');
		btnSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSave_actionPerformed(e);
			}
		});
		panButton.add(btnSaveFile, null);

		btnReset = new JButton();
		btnReset.setBounds(new Rectangle(387, 0, 97, 22));
		btnReset.setText("���(R)");
		btnReset.setMnemonic('R');
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnReset_actionPerformed(e);
			}
		});
		panButton.add(btnReset, null);

		contentPane.add(panMain, null);
		contentPane.add(panTable, null);
		contentPane.add(panButton, null);

		this.getContentPane().add(contentPane, null);

		mqRcv = new MQTest();
		t = new Thread(mqRcv);
	}

	public SskkRcvServerFrame() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			init();
			// loadTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * �ύ��ť�¼�
	 * 
	 * @param e
	 *            �¼�
	 */
	private void btnEnter_actionPerformed(ActionEvent e) {
		if (tm.getValueAt(1, 0).equals("") && tm.getValueAt(1, 1).equals("")
				&& tm.getValueAt(1, 2).equals("")) {
			rowData.clear();
		}
		mqRcv.setCCSID(Integer.parseInt(txtCoding.getText()));
		mqRcv.setCHANNEL(txtServerConnect.getText());
		mqRcv.setHOST_NAME(txtHostIP.getText());
		mqRcv.setQManager(txtQueueManager.getText());
		mqRcv.setPORT(Integer.parseInt(txtPort.getText()));
		mqRcv.setR_NAME(txtOut.getText());
		mqRcv.setS_NAME(txtIn.getText());
		t.start();
		btnEnter.setEnabled(false);
		tm.fireTableDataChanged();
	}

	/**
	 * �����ļ��¼�
	 * 
	 * @param e
	 *            �¼�
	 */
	private void btnSave_actionPerformed(ActionEvent e) {
		if (JOptionPane.showConfirmDialog(null, "ȷ�ϱ���?", "��ʾ",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

		}
		StringBuffer sb = new StringBuffer();
		for (int x = 0; x < jTable1.getRowCount(); x++) {
			sb.append(jTable1.getValueAt(x, 0));
			sb.append(":");
			sb.append(jTable1.getValueAt(x, 1));
			sb.append(":");
			sb.append(jTable1.getValueAt(x, 2));
			sb.append("\r\n");
		}
		File f = new File("E://a.txt");
		try {
			FileWriter fw = new FileWriter(f, true);
			BufferedWriter bfw = new BufferedWriter(fw);
			bfw.write(sb.toString(), 0, sb.toString().length());
			bfw.flush();
			bfw.close();
			fw.close();
		} catch (Exception ce) {
			System.out.println(ce);
		}
		btnEnter.setFocusable(true);
	}

	private void btnReset_actionPerformed(ActionEvent e) {
		if (jTable1.getCellEditor() != null) {
			jTable1.getCellEditor().stopCellEditing();
		}
		rowData.clear();
		rowData.add(new String[] { "", "", "" });
		tm.fireTableDataChanged();
	}

	public class NdTableModel extends AbstractTableModel {
		/**
		 * ��ͷ
		 */
		private String[] header;

		/**
		 * ������
		 */
		private Vector data;

		/**
		 * ע��Vector����Ϊnull
		 * 
		 * @param data
		 *            Vector
		 * @param header
		 *            String[]
		 */
		public NdTableModel(Vector data, String[] header) {
			this.data = data;
			this.header = header;
		}

		public int getColumnCount() {
			return header.length;
		}

		public String getColumnName(int column) {
			if (column < header.length) {
				return header[column];
			} else {
				return "";
			}
		}

		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		public int getRowCount() {
			return data.size();
		}

		public Object getValueAt(int row, int col) {
			if (row < data.size()
					&& col < ((Object[]) data.elementAt(0)).length) {
				return ((Object[]) data.elementAt(row))[col];
			} else {
				return "";
			}
		}

		public void setValueAt(Object aValue, int row, int column) {
			if (row < data.size()
					&& column < ((Object[]) data.elementAt(0)).length) {
				((Object[]) data.elementAt(row))[column] = String
						.valueOf(aValue);
			}
		}

		public boolean isCellEditable(int row, int col) {
			return true;
		}
	}

	public static void main(String[] args) {
		SskkRcvServerFrame frame = new SskkRcvServerFrame();
		frame.show();
	}
}
