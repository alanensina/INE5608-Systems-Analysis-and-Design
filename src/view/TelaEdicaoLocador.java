package view;

import static service.LocadorService.validaCamposLocador;

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
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import controller.LocadorController;
import model.Endereco;
import model.Locador;
import service.UtilsService;

public class TelaEdicaoLocador extends JInternalFrame {

	private static final long serialVersionUID = 2370540030184652866L;
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

	public static void main(String[] args, Locador locador) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEdicaoLocador frame = new TelaEdicaoLocador(args, locador);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEdicaoLocador(String[] args, Locador locador) throws Exception {
		setTitle(prop.getProperty("EditLocadorView.Title"));
		setResizable(false);
		setBounds(100, 100, 1027, 602);
		getContentPane().setLayout(null);

		ImageIcon icon = new ImageIcon(TelaEdicaoLocador.class.getResource("/images/icons/locador.png"));

		JPanel panelDadosPessoais = new JPanel();
		panelDadosPessoais
				.setBorder(new TitledBorder(null, prop.getProperty("EditLocadorView.BorderTitle.DadosPessoais"),
						TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDadosPessoais.setBounds(40, 180, 468, 316);
		getContentPane().add(panelDadosPessoais);
		panelDadosPessoais.setLayout(null);

		MaskFormatter mascaraTexto = new MaskFormatter("******************************");
		mascaraTexto.setValidCharacters(VALIDCHARS);

		JLabel lblNome = new JLabel(prop.getProperty("EditLocadorView.Label.Nome"));
		lblNome.setBounds(12, 38, 66, 15);
		panelDadosPessoais.add(lblNome);

		JLabel lblCpf = new JLabel(prop.getProperty("EditLocadorView.Label.CPF"));
		lblCpf.setBounds(12, 79, 66, 15);
		panelDadosPessoais.add(lblCpf);

		JLabel lblCelular = new JLabel(prop.getProperty("EditLocadorView.Label.Celular"));
		lblCelular.setBounds(12, 120, 66, 15);
		panelDadosPessoais.add(lblCelular);

		JLabel lblLogin = new JLabel(prop.getProperty("EditLocadorView.Label.Login"));
		lblLogin.setBounds(12, 161, 66, 15);
		panelDadosPessoais.add(lblLogin);

		JLabel lblSenha = new JLabel(prop.getProperty("EditLocadorView.Label.Senha"));
		lblSenha.setBounds(12, 202, 66, 15);
		panelDadosPessoais.add(lblSenha);

		txtNome = new JFormattedTextField(mascaraTexto);
		txtNome.setBounds(83, 36, 373, 19);
		txtNome.setText(locador.getNome());
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
		txtCPF.setText(locador.getCpf());
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
		txtCelular.setText(locador.getCelular());
		txtCelular.setBounds(83, 118, 373, 19);
		panelDadosPessoais.add(txtCelular);

		txtLogin = new JFormattedTextField(mascaraTexto);
		txtLogin.setEditable(false);
		txtLogin.setColumns(10);
		txtLogin.setText(locador.getLogin());
		txtLogin.setBounds(83, 159, 373, 19);
		panelDadosPessoais.add(txtLogin);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(83, 200, 373, 19);
		panelDadosPessoais.add(txtSenha);

		JPanel panelEndereco = new JPanel();
		panelEndereco.setLayout(null);
		panelEndereco.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)),
				prop.getProperty("EditLocadorView.BorderTitle.Endereco"), TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(51, 51, 51)));
		panelEndereco.setBounds(520, 180, 468, 316);
		getContentPane().add(panelEndereco);

		JLabel lblLogradouro = new JLabel(prop.getProperty("EditLocadorView.Label.Logradouro"));
		lblLogradouro.setBounds(12, 38, 117, 15);
		panelEndereco.add(lblLogradouro);

		JLabel lblNmero = new JLabel(prop.getProperty("EditLocadorView.Label.Numero"));
		lblNmero.setBounds(12, 79, 66, 15);
		panelEndereco.add(lblNmero);

		JLabel lblComplemento = new JLabel(prop.getProperty("EditLocadorView.Label.Complemento"));
		lblComplemento.setBounds(12, 120, 117, 15);
		panelEndereco.add(lblComplemento);

		JLabel lblBairro = new JLabel(prop.getProperty("EditLocadorView.Label.Bairro"));
		lblBairro.setBounds(12, 161, 66, 15);
		panelEndereco.add(lblBairro);

		JLabel lblCidade = new JLabel(prop.getProperty("EditLocadorView.Label.Cidade"));
		lblCidade.setBounds(12, 202, 66, 15);
		panelEndereco.add(lblCidade);

		txtLogradouro = new JFormattedTextField(mascaraTexto);
		txtLogradouro.setColumns(10);
		txtLogradouro.setText(locador.getEndereco().getLogradouro());
		txtLogradouro.setBounds(134, 36, 322, 19);
		panelEndereco.add(txtLogradouro);

		txtNumero = new JTextField();
		txtNumero.setColumns(10);
		txtNumero.setText(locador.getEndereco().getNumero());
		txtNumero.setBounds(134, 77, 322, 19);
		panelEndereco.add(txtNumero);

		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setText(locador.getEndereco().getComplemento());
		txtComplemento.setBounds(134, 118, 322, 19);
		panelEndereco.add(txtComplemento);

		txtBairro = new JFormattedTextField(mascaraTexto);
		txtBairro.setColumns(10);
		txtBairro.setText(locador.getEndereco().getBairro());
		txtBairro.setBounds(134, 159, 322, 19);
		panelEndereco.add(txtBairro);

		txtCidade = new JFormattedTextField(mascaraTexto);
		txtCidade.setBounds(134, 200, 322, 19);
		txtCidade.setText(locador.getEndereco().getCidade());
		panelEndereco.add(txtCidade);

		JLabel lblEstado = new JLabel(prop.getProperty("EditLocadorView.Label.Estado"));
		lblEstado.setBounds(12, 243, 66, 15);
		panelEndereco.add(lblEstado);

		txtEstado = new JFormattedTextField(mascaraTexto);
		txtEstado.setBounds(134, 241, 322, 19);
		txtEstado.setText(locador.getEndereco().getEstado());
		panelEndereco.add(txtEstado);

		JLabel lblCep = new JLabel(prop.getProperty("EditLocadorView.Label.CEP"));
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
		txtCEP.setText(locador.getEndereco().getCep());
		panelEndereco.add(txtCEP);

		JButton btnAtualizar = new JButton(prop.getProperty("EditLocadorView.Button.Atualizar"));
		btnAtualizar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {

				Endereco end = new Endereco();
				Locador loc = new Locador();
				LocadorController controller = new LocadorController();

				end.setId(locador.getEndereco().getId());
				end.setLogradouro(txtLogradouro.getText());
				end.setBairro(txtBairro.getText());
				end.setNumero(txtNumero.getText());
				end.setCep(txtCEP.getText());
				end.setComplemento(txtComplemento.getText());
				end.setCidade(txtCidade.getText());
				end.setEstado(txtEstado.getText());

				loc.setId(locador.getId());
				loc.setNome(txtNome.getText());
				loc.setCpf(txtCPF.getText());
				loc.setCelular(txtCelular.getText());
				loc.setLogin(txtLogin.getText());
				loc.setSenha(txtSenha.getText());
				
				if (validaCamposLocador(loc, end)) {
					JOptionPane.showMessageDialog(null, prop.getProperty("CadLocadorView.Message.CamposVazios"));
					return;
				}

				try {
					if (controller.enviaParaServiceAtualizar(loc, end)) {
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								prop.getProperty("EditLocadorView.Message.AtualizacaoFalha"));
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAtualizar.setBounds(874, 508, 114, 25);
		getContentPane().add(btnAtualizar);

		JButton btnLimpar = new JButton(prop.getProperty("EditLocadorView.Button.Limpar"));
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
		getContentPane().add(btnLimpar);

		JButton btnCancelar = new JButton(prop.getProperty("EditLocadorView.Button.Cancelar"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(633, 508, 114, 25);
		getContentPane().add(btnCancelar);

		JLabel lblCamposObrigatrios = new JLabel(prop.getProperty("EditLocadorView.Label.CamposObrigatorios"));
		lblCamposObrigatrios.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblCamposObrigatrios.setBounds(40, 514, 216, 15);
		getContentPane().add(lblCamposObrigatrios);

		JButton btnDeletarUsurio = new JButton(prop.getProperty("EditLocadorView.Button.Deletar"));
		btnDeletarUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,
						prop.getProperty("EditLocadorView.Message.ConfirmarDelete"),
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if(resp == 1) {

					LocadorController controller = new LocadorController();
					// Verificar se há aluguel ativado
					
					// Deletar as bicicletas
					if(controller.deletaBicicletas(locador)) {
						// Deletar locador
						if(controller.deletarLocador(locador)) {
						dispose();
						System.exit(0);
						}
					}
				}
			}
		});
		btnDeletarUsurio.setBackground(new Color(255, 51, 51));
		btnDeletarUsurio.setForeground(Color.WHITE);
		btnDeletarUsurio.setBounds(167, 508, 159, 25);
		getContentPane().add(btnDeletarUsurio);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaEdicaoLocador.class.getResource("/images/icons/logoEditProfile.png")));
		lblLogo.setBounds(467, 12, 146, 156);
		getContentPane().add(lblLogo);
	}

	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
