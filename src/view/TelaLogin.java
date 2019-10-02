package view;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import service.UtilsService;

@SuppressWarnings("serial")
public class TelaLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private Properties prop = UtilsService.getProp();

	public TelaLogin(String[] args) {
		setResizable(false);
		setTitle(prop.getProperty("LoginView.Title"));
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

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/images/icons/logobike.png")));
		lblLogo.setBounds(141, 23, 175, 92);
		contentPane.add(lblLogo);

		JLabel lbLogin = new JLabel(prop.getProperty("LoginView.Label.Login"));
		lbLogin.setBounds(34, 139, 52, 14);
		contentPane.add(lbLogin);

		JLabel lbSenha = new JLabel(prop.getProperty("LoginView.Label.Senha"));
		lbSenha.setBounds(34, 181, 52, 14);
		contentPane.add(lbSenha);

		txtLogin = new JTextField();
		txtLogin.setBounds(90, 136, 317, 20);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);

		txtSenha = new JPasswordField();
		txtSenha.setColumns(10);
		txtSenha.setBounds(90, 178, 317, 20);
		contentPane.add(txtSenha);

		JButton btnLogar = new JButton(prop.getProperty("LoginView.Button.Logar"));
		btnLogar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				LoginController controller = new LoginController();

				String login = txtLogin.getText();
				String senha = txtSenha.getText();

				try {
					if (controller.enviaParaService(login, senha)) {
						dispose();
					} else {
						txtLogin.setText("");
						txtSenha.setText("");
						JOptionPane.showMessageDialog(null, prop.getProperty("LoginView.Message.LoginIncorreto"));
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnLogar.setBounds(163, 209, 129, 23);
		contentPane.add(btnLogar);

		JButton btnCadastrarLocador = new JButton(prop.getProperty("LoginView.Button.NovoLocador"));
		btnCadastrarLocador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroLocador.main(args);
				dispose();
			}
		});
		btnCadastrarLocador.setBounds(34, 251, 156, 23);
		contentPane.add(btnCadastrarLocador);

		JButton btnNovoLocatrio = new JButton(prop.getProperty("LoginView.Button.NovoLocatario"));
		btnNovoLocatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCadastroLocatario.main(args);
				dispose();
			}
		});
		btnNovoLocatrio.setBounds(251, 251, 156, 23);
		contentPane.add(btnNovoLocatrio);
	}
}
