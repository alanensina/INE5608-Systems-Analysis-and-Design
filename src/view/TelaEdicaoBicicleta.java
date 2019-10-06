package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JYearChooser;

import controller.BicicletaController;
import dao.BicicletaDAO;
import enumeration.UF;
import model.Bicicleta;
import model.Locador;

import static service.UtilsService.getProp;

import java.awt.Color;

public class TelaEdicaoBicicleta extends JInternalFrame {

	private static final long serialVersionUID = -7890262835619403532L;
	private static Locador loc;
	private JTextField txtModelo;
	private JTextField txtValorDoAluguel;
	private BicicletaDAO bicDAO = new BicicletaDAO();
	private BicicletaController controller = new BicicletaController();
	private Properties prop = getProp();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEdicaoBicicleta frame = new TelaEdicaoBicicleta(loc, args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEdicaoBicicleta(Locador loc, String[] args) {
		TelaEdicaoBicicleta.loc = loc;
		setTitle(prop.getProperty("EditBikeView.Label.Title"));
		setBounds(100, 100, 450, 560);
		getContentPane().setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaEdicaoBicicleta.class.getResource("/images/icons/iconeEditarBike.png")));
		lblLogo.setBounds(158, 12, 126, 145);
		getContentPane().add(lblLogo);

		JLabel lblBicicletas = new JLabel(prop.getProperty("EditBikeView.Label.Bicicletas"));
		lblBicicletas.setBounds(12, 174, 84, 15);
		getContentPane().add(lblBicicletas);

		JLabel lbModelo = new JLabel(prop.getProperty("EditBikeView.Label.Modelo"));
		lbModelo.setBounds(12, 214, 84, 15);
		getContentPane().add(lbModelo);

		txtModelo = new JTextField();
		txtModelo.setBounds(113, 212, 315, 19);
		getContentPane().add(txtModelo);
		txtModelo.setColumns(10);

		JLabel lblAno = new JLabel(prop.getProperty("EditBikeView.Label.Ano"));
		lblAno.setBounds(12, 255, 66, 15);
		getContentPane().add(lblAno);

		JYearChooser jcAno = new JYearChooser();
		jcAno.setBounds(114, 251, 53, 19);
		getContentPane().add(jcAno);

		JLabel lblValorDoAluguel = new JLabel(prop.getProperty("EditBikeView.Label.Valor"));
		lblValorDoAluguel.setBounds(201, 255, 140, 15);
		getContentPane().add(lblValorDoAluguel);

		txtValorDoAluguel = new JTextField();
		txtValorDoAluguel.setBounds(362, 253, 66, 19);
		getContentPane().add(txtValorDoAluguel);
		txtValorDoAluguel.setColumns(10);

		JLabel lbAcessorios = new JLabel(prop.getProperty("EditBikeView.Label.Acessorios"));
		lbAcessorios.setBounds(12, 293, 155, 15);
		getContentPane().add(lbAcessorios);

		JTextArea txtAcessorios = new JTextArea();
		txtAcessorios.setBounds(12, 320, 416, 77);
		getContentPane().add(txtAcessorios);

		JCheckBox cbDisbonibilidade = new JCheckBox(prop.getProperty("EditBikeView.Checkbox.Disponivel"));
		cbDisbonibilidade.setBounds(8, 413, 126, 23);
		getContentPane().add(cbDisbonibilidade);

		JLabel lblCamposObrigatrios = new JLabel(prop.getProperty("EditBikeView.Label.CamposObrigatorios"));
		lblCamposObrigatrios.setFont(new Font("Dialog", Font.ITALIC, 10));
		lblCamposObrigatrios.setBounds(173, 417, 255, 15);
		getContentPane().add(lblCamposObrigatrios);

		JComboBox<Bicicleta> cbBikes = new JComboBox<Bicicleta>();
		cbBikes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Bicicleta bic = (Bicicleta) cbBikes.getSelectedItem();
				txtModelo.setText(bic.getModelo());
				txtValorDoAluguel.setText(String.valueOf(bic.getValorDeAluguel()));
				jcAno.setYear(Integer.valueOf(bic.getAno()));
				cbDisbonibilidade.setSelected(bic.isDisponivel());
				txtAcessorios.setText(bic.getAcessorios());
			}
		});
		
		//TODO: Listar as bicicletas apenas que estão disponíveis
		List<Bicicleta> bikes = bicDAO.listarBicicletasPeloIdLocador(loc);
		cbBikes.setModel(new DefaultComboBoxModel(bikes.toArray()));
		cbBikes.setBounds(114, 169, 314, 24);
		getContentPane().add(cbBikes);

		JButton btnCancelar = new JButton(prop.getProperty("EditBikeView.Button.Cancelar"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(12, 457, 93, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnAtualizar = new JButton(prop.getProperty("EditBikeView.Button.Atualizar"));
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Bicicleta bic = new Bicicleta();
				bic.setModelo(txtModelo.getText());
				bic.setAno(String.valueOf(jcAno.getYear()));
				bic.setAcessorios(txtAcessorios.getText());
				bic.setId(((Bicicleta) cbBikes.getSelectedItem()).getId());
				
				if(txtValorDoAluguel.getText().isEmpty()) {
					txtValorDoAluguel.setText("0");
				}
				
				bic.setValorDeAluguel(Double.parseDouble(txtValorDoAluguel.getText()));
				bic.setDisponivel(cbDisbonibilidade.isSelected());
				
				if(controller.atualizar(bic)) {
					dispose();
				}

				dispose();
			}
		});
		btnAtualizar.setBounds(307, 457, 101, 25);
		getContentPane().add(btnAtualizar);

		JButton btnDeletar = new JButton(prop.getProperty("EditBikeView.Button.Deletar"));
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Object[] options = { "Confirmar", "Cancelar" };
				int resp = JOptionPane.showOptionDialog(null,
						prop.getProperty("EditBikeView.Message.ConfirmarDelete"),
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);

				if(resp == 0) {
					Bicicleta bic = (Bicicleta) cbBikes.getSelectedItem();
					
					//TODO: validar se a bicicleta não está ativa em algum aluguel antes de deletá-la.
					
					if(controller.deletar(bic)) {
						return ;
					}
					dispose();
				}

			}
		});
		btnDeletar.setForeground(new Color(250, 235, 215));
		btnDeletar.setBackground(new Color(178, 34, 34));
		btnDeletar.setBounds(149, 457, 114, 25);
		getContentPane().add(btnDeletar);

	}

	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
