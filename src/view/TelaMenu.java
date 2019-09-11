package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaMenu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaMenu frame = new TelaMenu(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaMenu(String[] args) {
		setResizable(false);
		setTitle("Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 148);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnListarUsurios = new JButton("Editar usuários");
		btnListarUsurios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			TelaEditarUsuario.main(args);
			dispose();
				
			}
		});
		btnListarUsurios.setBounds(32, 30, 149, 55);
		contentPane.add(btnListarUsurios);
		
		JButton btnListarUsurios_1 = new JButton("Listar usuários");
		btnListarUsurios_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaLista.main(args);
			}
		});
		btnListarUsurios_1.setBounds(231, 30, 149, 55);
		contentPane.add(btnListarUsurios_1);
	}
}
