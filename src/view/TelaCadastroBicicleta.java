package view;

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

import service.UtilsService;

public class TelaCadastroBicicleta extends JInternalFrame {
	private JTextField txtModelo;
	private JTextField txtValorAluguel;
	private JTextField txtAno;
	private Properties prop = UtilsService.getProp();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastroBicicleta frame = new TelaCadastroBicicleta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCadastroBicicleta() {
		setClosable(true);
		setResizable(false);
		setTitle(prop.getProperty("CadBikeView.Title"));
		setBounds(100, 100, 450, 496);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, prop.getProperty("CadBikeView.BorderTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 161, 414, 260);
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblModelo = new JLabel(prop.getProperty("CadBikeView.Label.Modelo"));
		lblModelo.setBounds(10, 35, 46, 14);
		panel.add(lblModelo);

		txtModelo = new JTextField();
		txtModelo.setBounds(66, 32, 338, 20);
		panel.add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblAno = new JLabel(prop.getProperty("CadBikeView.Label.Ano"));
		lblAno.setBounds(10, 71, 46, 14);
		panel.add(lblAno);

		JLabel lblValorDeAluguel = new JLabel(prop.getProperty("CadBikeView.Label.Valor"));
		lblValorDeAluguel.setBounds(172, 71, 86, 14);
		panel.add(lblValorDeAluguel);

		txtValorAluguel = new JTextField();
		txtValorAluguel.setBounds(268, 68, 136, 20);
		panel.add(txtValorAluguel);
		txtValorAluguel.setColumns(10);

		JLabel lblAcessrios = new JLabel(prop.getProperty("CadBikeView.Label.Acessorios"));
		lblAcessrios.setBounds(10, 106, 75, 14);
		panel.add(lblAcessrios);

		JTextArea txtAcessorios = new JTextArea();
		txtAcessorios.setBounds(10, 131, 394, 90);
		panel.add(txtAcessorios);

		JCheckBox checkboxDisponibilidade = new JCheckBox(prop.getProperty("CadBikeView.Checkbox.DisponibilidadeLocacao"));
		checkboxDisponibilidade.setSelected(true);
		checkboxDisponibilidade.setBounds(6, 228, 231, 23);
		panel.add(checkboxDisponibilidade);

		txtAno = new JTextField();
		txtAno.setBounds(66, 68, 86, 20);
		panel.add(txtAno);
		txtAno.setColumns(10);

		JButton btnCadastrar = new JButton(prop.getProperty("CadBikeView.Button.Cadastrar"));
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
