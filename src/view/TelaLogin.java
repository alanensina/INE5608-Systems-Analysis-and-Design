package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtSenha;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaLogin() {
		setResizable(false);
		setTitle("V\u00E1 de Bike!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("LogoV\u00E1DeBike");
		lblLogo.setBounds(200, 71, 145, 14);
		contentPane.add(lblLogo);
		
		JLabel lbLogin = new JLabel("Login");
		lbLogin.setBounds(34, 139, 46, 14);
		contentPane.add(lbLogin);
		
		JLabel lbSenha = new JLabel("Senha");
		lbSenha.setBounds(34, 181, 46, 14);
		contentPane.add(lbSenha);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(90, 136, 317, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		txtSenha = new JTextField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(90, 178, 317, 20);
		contentPane.add(txtSenha);
		
		JButton btnLogar = new JButton("Logar");
		btnLogar.setBounds(180, 219, 89, 23);
		contentPane.add(btnLogar);
		
		JButton btnCadastroLocador = new JButton("Cadastrar como Locador");
		btnCadastroLocador.setBounds(49, 265, 167, 23);
		contentPane.add(btnCadastroLocador);
		
		JButton btnCadastroLocatario = new JButton("Cadastrar como Locat\u00E1rio");
		btnCadastroLocatario.setBounds(240, 265, 167, 23);
		contentPane.add(btnCadastroLocatario);
	}
}
