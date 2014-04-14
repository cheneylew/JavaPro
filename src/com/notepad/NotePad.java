package com.notepad;
import java.io.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
public class NotePad extends JFrame implements Action {
	JMenuBar jmb = null;
	JMenu jm = null;
	JMenuItem jmi1,jmi2;
	JTextArea jta = null;
	public NotePad() {
		// TODO Auto-generated constructor stub
		jmb = new JMenuBar();
			jm = new JMenu("文件");
				jmi1 = new JMenuItem("打开");
				jmi1.addActionListener(this);
				jmi1.setActionCommand("open");
				
				jmi2 = new JMenuItem("保存");
				jmi2.addActionListener(this);
				jmi2.setActionCommand("save");
			jm.add(jmi1);
			jm.add(jmi2);
		jmb.add(jm);
		this.setJMenuBar(jmb);
		
		jta = new JTextArea();
		this.add(jta);
		
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocation(300, 0);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new NotePad();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if (command.equals("open")) {
			 JFileChooser jfc = new JFileChooser();
			 jfc.setDialogTitle("请选择文件...");
			 jfc.showOpenDialog(null);
			 jfc.setVisible(true);
			 
			 String fileName = jfc.getSelectedFile().getAbsolutePath();
			 FileReader fr = null;
			 BufferedReader br =null;
			 try {
				 fr = new FileReader(fileName);
				 br = new BufferedReader(fr);
				 String s = "";
				 String allCon = "";
				 while ((s=br.readLine())!= null) {
					allCon += s+"\r\n";
				 }
				 jta.setText(allCon);
				
			} catch (Exception e2) {
				// TODO: handle exception
			}finally{
				try {
					fr.close();
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if (command.equals("save")) {
			 JFileChooser jfc = new JFileChooser();
			 jfc.setDialogTitle("另存为...");
			 jfc.showSaveDialog(null);
			 jfc.setVisible(true);
			 
			 String fileName = jfc.getSelectedFile().getAbsolutePath();
			 
			 FileWriter fw = null;
			 BufferedWriter bw = null;
			try {
				fw = new FileWriter(fileName);
				bw = new BufferedWriter(fw);
				bw.write(this.jta.getText());
			} catch (Exception e2) {
				e2.printStackTrace();
			}finally{
				try {
					bw.close();
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Override
	public Object getValue(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putValue(String key, Object value) {
		// TODO Auto-generated method stub
		
	}

}
