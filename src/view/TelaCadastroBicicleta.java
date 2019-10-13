package view;

import static service.UtilsService.getProp;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JYearChooser;

import controller.BicicletaController;
import model.Bicicleta;
import model.Locador;
import java.awt.Font;

public class TelaCadastroBicicleta extends JInternalFrame {

	private static final long serialVersionUID = 8228872097129522654L;
	private JTextField txtModelo;
	private JTextField txtValorAluguel;
	private static Locador loc;
	private Properties prop = getProp();
	private BicicletaController controller = new BicicletaController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroBicicleta frame = new TelaCadastroBicicleta(loc, args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroBicicleta(Locador loc, String[] args) {
		TelaCadastroBicicleta.loc = loc;
		setClosable(true);
		setResizable(false);
		setTitle(prop.getProperty("CadBikeView.Title"));
		setBounds(100, 100, 450, 496);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, prop.getProperty("CadBikeView.BorderTitle"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBounds(10, 161, 414, 260);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblModelo = new JLabel(prop.getProperty("CadBikeView.Label.Modelo"));
		lblModelo.setBounds(10, 35, 106, 14);
		panel.add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setBounds(78, 32, 326, 20);
		panel.add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblAno = new JLabel(prop.getProperty("CadBikeView.Label.Ano"));
		lblAno.setBounds(10, 71, 46, 14);
		panel.add(lblAno);

		JLabel lblValorDeAluguel = new JLabel(prop.getProperty("CadBikeView.Label.Valor"));
		lblValorDeAluguel.setBounds(172, 71, 144, 14);
		panel.add(lblValorDeAluguel);

		txtValorAluguel = new JTextField();
		txtValorAluguel.setBounds(334, 68, 70, 20);
		panel.add(txtValorAluguel);
		txtValorAluguel.setColumns(10);

		JLabel lblAcessrios = new JLabel(prop.getProperty("CadBikeView.Label.Acessorios"));
		lblAcessrios.setBounds(10, 106, 75, 14);
		panel.add(lblAcessrios);

		JTextArea txtAcessorios = new JTextArea();
		txtAcessorios.setBounds(10, 131, 394, 90);
		panel.add(txtAcessorios);

		JCheckBox checkboxDisponibilidade = new JCheckBox(
				prop.getProperty("CadBikeView.Checkbox.DisponibilidadeLocacao"));
		checkboxDisponibilidade.setSelected(true);
		checkboxDisponibilidade.setBounds(6, 228, 231, 23);
		panel.add(checkboxDisponibilidade);

		JYearChooser jcAno = new JYearChooser();
		jcAno.setBounds(88, 77, 53, 20);
		panel.add(jcAno);

		JLabel lblValoresObrigatrios = new JLabel(prop.getProperty("CadBikeView.Label.CamposObrigatorios"));
		lblValoresObrigatrios.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblValoresObrigatrios.setBounds(239, 233, 144, 15);
		panel.add(lblValoresObrigatrios);

		JButton btnCadastrar = new JButton(prop.getProperty("CadBikeView.Button.Cadastrar"));
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Bicicleta bic = new Bicicleta();
				bic.setModelo(txtModelo.getText());
				bic.setAno(String.valueOf(jcAno.getYear()));
				bic.setAcessorios(txtAcessorios.getText());

				if (txtValorAluguel.getText().isEmpty()) {
					txtValorAluguel.setText("0");
				}

				bic.setValorDeAluguel(Double.parseDouble(txtValorAluguel.getText()));
				bic.setDisponivel(checkboxDisponibilidade.isSelected());

				if (controller.enviaParaValidacoesObrigatorias(bic)) {
					controller.salvar(bic, loc);
					txtModelo.setText("");
					txtAcessorios.setText("");
					txtValorAluguel.setText("");
					jcAno.setYear(2019);
				} else {
					return;
				}
			}
		});
		btnCadastrar.setBounds(309, 432, 115, 23);
		getContentPane().add(btnCadastrar);

		JButton btnCancelar = new JButton(prop.getProperty("CadBikeView.Button.Cancelar"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(20, 432, 115, 23);
		getContentPane().add(btnCancelar);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/images/icons/logobike.png")));
		lblLogo.setBounds(141, 23, 175, 92);
		getContentPane().add(lblLogo);

	}

	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
