package view;

import static service.LocatarioService.validaCamposLocatario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import controller.LocatarioController;
import model.Endereco;
import model.Locatario;
import service.UtilsService;

public class TelaEdicaoLocatario extends JInternalFrame {

	private static final long serialVersionUID = 2163927849948746L;
	private JPanel contentPane;
	private JFormattedTextField txtNome;
	private JFormattedTextField txtCPF;
	private JFormattedTextField txtCelular;
	private JFormattedTextField txtLogin;
	private JPasswordField txtSenha;
	private JFormattedTextField txtLogradouro;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JFormattedTextField txtBairro;
	private JFormattedTextField txtCidade;
	private JFormattedTextField txtEstado;
	private JFormattedTextField txtCEP;
	private static Properties prop = UtilsService.getProp();

	private static final String VALIDCHARS = prop.getProperty("StringUtils.CaracteresValidos");

	public static void main(String[] args, Locatario locatario) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEdicaoLocatario frame = new TelaEdicaoLocatario(args, locatario);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEdicaoLocatario(String[] args, Locatario locatario) throws Exception {
		setTitle(prop.getProperty("EditLocatarioView.Title"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 602);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaEdicaoLocatario.class.getResource("/images/icons/logoEditProfile.png")));
		lblLogo.setBounds(460, 0, 147, 168);
		contentPane.add(lblLogo);

		JPanel panelDadosPessoais = new JPanel();
		panelDadosPessoais
				.setBorder(new TitledBorder(null, prop.getProperty("EditLocatarioView.BorderTitle.DadosPessoais"),
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDadosPessoais.setBounds(40, 180, 468, 316);
		contentPane.add(panelDadosPessoais);
		panelDadosPessoais.setLayout(null);

		MaskFormatter mascaraTexto = new MaskFormatter("******************************");
		mascaraTexto.setValidCharacters(VALIDCHARS);

		JLabel lblNome = new JLabel(prop.getProperty("EditLocatarioView.Label.Nome"));
		lblNome.setBounds(12, 38, 66, 15);
		panelDadosPessoais.add(lblNome);

		JLabel lblCpf = new JLabel(prop.getProperty("EditLocatarioView.Label.CPF"));
		lblCpf.setBounds(12, 79, 66, 15);
		panelDadosPessoais.add(lblCpf);

		JLabel lblCelular = new JLabel(prop.getProperty("EditLocatarioView.Label.Celular"));
		lblCelular.setBounds(12, 120, 66, 15);
		panelDadosPessoais.add(lblCelular);

		JLabel lblLogin = new JLabel(prop.getProperty("EditLocatarioView.Label.Login"));
		lblLogin.setBounds(12, 161, 66, 15);
		panelDadosPessoais.add(lblLogin);

		JLabel lblSenha = new JLabel(prop.getProperty("EditLocatarioView.Label.Senha"));
		lblSenha.setBounds(12, 202, 66, 15);
		panelDadosPessoais.add(lblSenha);

		txtNome = new JFormattedTextField(mascaraTexto);
		txtNome.setBounds(83, 36, 373, 19);
		txtNome.setText(locatario.getNome());
		panelDadosPessoais.add(txtNome);
		txtNome.setColumns(10);

		javax.swing.text.MaskFormatter maskCPF = null;
		try {
			maskCPF = new javax.swing.text.MaskFormatter("###.###.###-##");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		txtCPF = new JFormattedTextField(maskCPF);
		txtCPF.setBounds(83, 77, 373, 19);
		txtCPF.setText(locatario.getCpf());
		panelDadosPessoais.add(txtCPF);
		txtCPF.setColumns(10);

		javax.swing.text.MaskFormatter maskPhone = null;
		try {
			maskPhone = new javax.swing.text.MaskFormatter("(##) #####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		txtCelular = new JFormattedTextField(maskPhone);
		txtCelular.setColumns(10);
		txtCelular.setText(locatario.getCelular());
		txtCelular.setBounds(83, 118, 373, 19);
		panelDadosPessoais.add(txtCelular);

		txtLogin = new JFormattedTextField(mascaraTexto);
		txtLogin.setEditable(false);
		txtLogin.setColumns(10);
		txtLogin.setText(locatario.getLogin());
		txtLogin.setBounds(83, 159, 373, 19);
		panelDadosPessoais.add(txtLogin);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(83, 200, 373, 19);
		panelDadosPessoais.add(txtSenha);

		JPanel panelEndereco = new JPanel();
		panelEndereco.setLayout(null);
		panelEndereco.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				prop.getProperty("EditLocatarioView.BorderTitle.Endereco"), TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(51, 51, 51)));
		panelEndereco.setBounds(520, 180, 468, 316);
		contentPane.add(panelEndereco);

		JLabel lblLogradouro = new JLabel(prop.getProperty("EditLocatarioView.Label.Logradouro"));
		lblLogradouro.setBounds(12, 38, 117, 15);
		panelEndereco.add(lblLogradouro);

		JLabel lblNmero = new JLabel(prop.getProperty("EditLocatarioView.Label.Numero"));
		lblNmero.setBounds(12, 79, 66, 15);
		panelEndereco.add(lblNmero);

		JLabel lblComplemento = new JLabel(prop.getProperty("EditLocatarioView.Label.Complemento"));
		lblComplemento.setBounds(12, 120, 117, 15);
		panelEndereco.add(lblComplemento);

		JLabel lblBairro = new JLabel(prop.getProperty("EditLocatarioView.Label.Bairro"));
		lblBairro.setBounds(12, 161, 66, 15);
		panelEndereco.add(lblBairro);

		JLabel lblCidade = new JLabel(prop.getProperty("EditLocatarioView.Label.Cidade"));
		lblCidade.setBounds(12, 202, 66, 15);
		panelEndereco.add(lblCidade);

		txtLogradouro = new JFormattedTextField(mascaraTexto);
		txtLogradouro.setColumns(10);
		txtLogradouro.setText(locatario.getEndereco().getLogradouro());
		txtLogradouro.setBounds(134, 36, 322, 19);
		panelEndereco.add(txtLogradouro);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setText(locatario.getEndereco().getNumero());
		txtNumero.setBounds(134, 77, 322, 19);
		panelEndereco.add(txtNumero);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setText(locatario.getEndereco().getComplemento());
		txtComplemento.setBounds(134, 118, 322, 19);
		panelEndereco.add(txtComplemento);

		txtBairro = new JFormattedTextField(mascaraTexto);
		txtBairro.setColumns(10);
		txtBairro.setText(locatario.getEndereco().getBairro());
		txtBairro.setBounds(134, 159, 322, 19);
		panelEndereco.add(txtBairro);

		txtCidade = new JFormattedTextField(mascaraTexto);
		txtCidade.setBounds(134, 200, 322, 19);
		txtCidade.setText(locatario.getEndereco().getCidade());
		panelEndereco.add(txtCidade);

		JLabel lblEstado = new JLabel(prop.getProperty("EditLocatarioView.Label.Estado"));
		lblEstado.setBounds(12, 243, 66, 15);
		panelEndereco.add(lblEstado);

		txtEstado = new JFormattedTextField(mascaraTexto);
		txtEstado.setBounds(134, 241, 322, 19);
		txtEstado.setText(locatario.getEndereco().getEstado());
		panelEndereco.add(txtEstado);

		JLabel lblCep = new JLabel(prop.getProperty("EditLocatarioView.Label.CEP"));
		lblCep.setBounds(12, 284, 66, 15);
		panelEndereco.add(lblCep);

		javax.swing.text.MaskFormatter maskCep = null;
		try {
			maskCep = new javax.swing.text.MaskFormatter("##.###-###");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		txtCEP = new JFormattedTextField(maskCep);
		txtCEP.setBounds(134, 284, 322, 19);
		txtCEP.setText(locatario.getEndereco().getCep());
		panelEndereco.add(txtCEP);

		JButton btnAtualizar = new JButton(prop.getProperty("EditLocatarioView.Button.Atualizar"));
		btnAtualizar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				Endereco end = new Endereco();
				Locatario loc = new Locatario();
				LocatarioController controller = new LocatarioController();

				end.setId(locatario.getEndereco().getId());
				end.setLogradouro(txtLogradouro.getText());
				end.setBairro(txtBairro.getText());
				end.setNumero(txtNumero.getText());
				end.setCep(txtCEP.getText());
				end.setComplemento(txtComplemento.getText());
				end.setCidade(txtCidade.getText());
				end.setEstado(txtEstado.getText());

				loc.setId(locatario.getId());
				loc.setNome(txtNome.getText());
				loc.setCpf(txtCPF.getText());
				loc.setCelular(txtCelular.getText());
				loc.setLogin(txtLogin.getText());
				loc.setSenha(txtSenha.getText());

				if (validaCamposLocatario(loc, end)) {
					JOptionPane.showMessageDialog(null, prop.getProperty("CadLocadorView.Message.CamposVazios"));
					return;
				}

				try {
					if (controller.enviaParaServiceAtualizar(loc, end)) {
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								prop.getProperty("EditLocatarioView.Message.AtualizacaoFalha"));
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(874, 508, 114, 25);
		contentPane.add(btnAtualizar);

		JButton btnLimpar = new JButton(prop.getProperty("EditLocatarioView.Button.Limpar"));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNome.setText("");
				txtCPF.setText("");
				txtCelular.setText("");
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

		JButton btnCancelar = new JButton(prop.getProperty("EditLocatarioView.Button.Cancelar"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(633, 508, 114, 25);
		contentPane.add(btnCancelar);

		JLabel lblCamposObrigatrios = new JLabel(prop.getProperty("EditLocatarioView.Label.CamposObrigatorios"));
		lblCamposObrigatrios.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblCamposObrigatrios.setBounds(40, 514, 216, 15);
		contentPane.add(lblCamposObrigatrios);

		JButton btnDeletarLocatrio = new JButton(prop.getProperty("EditLocatarioView.Button.Deletar"));
		btnDeletarLocatrio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocatarioController controller = new LocatarioController();

				Object[] options = { "Confirmar", "Cancelar" };
				int resp = JOptionPane.showOptionDialog(null,
						prop.getProperty("EditLocatarioView.Message.ConfirmarDelete"), "ATENÇÃO",
						JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if (resp == 0) {

					// Verifica multas
					// verifica alugueis em andamento
					// Deleta
					if (controller.enviarParaDeletar(locatario)) {
						dispose();
						System.exit(0);
					}
				}
			}
		});
		btnDeletarLocatrio.setForeground(Color.WHITE);
		btnDeletarLocatrio.setBackground(new Color(255, 51, 51));
		btnDeletarLocatrio.setBounds(179, 508, 159, 25);
		contentPane.add(btnDeletarLocatrio);
	}

	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}

}
