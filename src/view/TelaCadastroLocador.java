package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import static controller.AppController.inicializa;

@SuppressWarnings("serial")
public class TelaCadastroLocador extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtCelular;
	private JTextField txtLogin;
	private JPasswordField txtSenha;
	private JTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroLocador frame = new TelaCadastroLocador(args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroLocador(String[] args) {
		setTitle("Cadastro de Locador");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 602);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("/home/alan/eclipse-workspace/VaDeBike/icons/locador.png"));
		lblLogo.setBounds(426, 0, 216, 168);
		contentPane.add(lblLogo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(40, 180, 468, 297);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome *");
		lblNome.setBounds(12, 38, 66, 15);
		panel.add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF *");
		lblCpf.setBounds(12, 79, 66, 15);
		panel.add(lblCpf);
		
		JLabel lblCelular = new JLabel("Celular *");
		lblCelular.setBounds(12, 120, 66, 15);
		panel.add(lblCelular);
		
		JLabel lblLogin = new JLabel("Login *");
		lblLogin.setBounds(12, 161, 66, 15);
		panel.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha  *");
		lblSenha.setBounds(12, 202, 66, 15);
		panel.add(lblSenha);
		
		txtNome = new JTextField();
		txtNome.setBounds(83, 36, 373, 19);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		javax.swing.text.MaskFormatter maskCPF = null;
		try {
			maskCPF = new javax.swing.text.MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtCPF = new JFormattedTextField(maskCPF);
		txtCPF.setBounds(83, 77, 373, 19);
		panel.add(txtCPF);
		txtCPF.setColumns(10);
		
		javax.swing.text.MaskFormatter maskPhone = null;
		try {
			maskPhone = new javax.swing.text.MaskFormatter("(##) #####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		txtCelular = new JFormattedTextField(maskPhone);
		txtCelular.setColumns(10);
		txtCelular.setBounds(83, 118, 373, 19);
		panel.add(txtCelular);
		
		txtLogin = new JTextField();
		txtLogin.setColumns(10);
		txtLogin.setBounds(83, 159, 373, 19);
		panel.add(txtLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(83, 200, 373, 19);
		panel.add(txtSenha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_1.setBounds(520, 180, 468, 297);
		contentPane.add(panel_1);
		
		JLabel lblLogradouro = new JLabel("Logradouro *");
		lblLogradouro.setBounds(12, 38, 117, 15);
		panel_1.add(lblLogradouro);
		
		JLabel lblNmero = new JLabel("Número *");
		lblNmero.setBounds(12, 79, 66, 15);
		panel_1.add(lblNmero);
		
		JLabel lblBairro = new JLabel("Complemento");
		lblBairro.setBounds(12, 120, 117, 15);
		panel_1.add(lblBairro);
		
		JLabel lblBairro_1 = new JLabel("Bairro *");
		lblBairro_1.setBounds(12, 161, 66, 15);
		panel_1.add(lblBairro_1);
		
		JLabel lblCidade = new JLabel("Cidade *");
		lblCidade.setBounds(12, 202, 66, 15);
		panel_1.add(lblCidade);
		
		txtLogradouro = new JTextField();
		txtLogradouro.setColumns(10);
		txtLogradouro.setBounds(134, 36, 322, 19);
		panel_1.add(txtLogradouro);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setBounds(134, 77, 322, 19);
		panel_1.add(txtNumero);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(134, 118, 322, 19);
		panel_1.add(txtComplemento);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(134, 159, 322, 19);
		panel_1.add(txtBairro);
		
		txtCidade = new JTextField();
		txtCidade.setBounds(134, 200, 322, 19);
		panel_1.add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado *");
		lblEstado.setBounds(12, 243, 66, 15);
		panel_1.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setBounds(134, 241, 322, 19);
		panel_1.add(txtEstado);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(874, 508, 114, 25);
		contentPane.add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setText("");
				txtCPF.setText("");
				txtCelular.setText("");
				txtLogin.setText("");
				txtSenha.setText("");
				txtLogradouro.setText("");
				txtNumero.setText("");
				txtComplemento.setText("");
				txtBairro.setText("");
				txtCidade.setText("");
				txtEstado.setText("");
			}
		});
		btnLimpar.setBounds(752, 508, 114, 25);
		contentPane.add(btnLimpar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				inicializa(args);
			}
		});
		btnCancelar.setBounds(633, 508, 114, 25);
		contentPane.add(btnCancelar);
		
		JLabel lblCamposObrigatrios = new JLabel("* Campos obrigatórios");
		lblCamposObrigatrios.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblCamposObrigatrios.setBounds(40, 483, 216, 15);
		contentPane.add(lblCamposObrigatrios);
	}
}
