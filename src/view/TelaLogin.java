package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JTextField txtSenha;

	public TelaLogin(String[] args) {
		setResizable(false);
		setTitle("V\u00E1 de Bike!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 444, 344);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) (screenSize.getWidth() - 363) / 2;
		int y = (int) (screenSize.getHeight() - 308) / 2;
		setLocation(x, y);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogo = new JLabel("LogoV\u00E1DeBike");
		lblLogo.setBounds(179, 65, 145, 14);
		contentPane.add(lblLogo);

		JLabel lbLogin = new JLabel("Login");
		lbLogin.setBounds(34, 139, 52, 14);
		contentPane.add(lbLogin);

		JLabel lbSenha = new JLabel("Senha");
		lbSenha.setBounds(34, 181, 52, 14);
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
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "LOGOU!");
			}
		});
		btnLogar.setBounds(163, 209, 129, 23);
		contentPane.add(btnLogar);

		JButton btnCadastrarLocador = new JButton("Novo locador?");
		btnCadastrarLocador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroLocador.main(args);
				dispose();
			}
		});
		btnCadastrarLocador.setBounds(34, 251, 129, 23);
		contentPane.add(btnCadastrarLocador);

		JButton btnNovoLocatrio = new JButton("Novo locat\u00E1rio?");
		btnNovoLocatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroLocatario.main(args);
				dispose();
			}
		});
		btnNovoLocatrio.setBounds(278, 251, 129, 23);
		contentPane.add(btnNovoLocatrio);
	}
}
