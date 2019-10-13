package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Dimension;

import com.toedter.calendar.JDateChooser;

import controller.AluguelController;
import dao.BicicletaDAO;
import dao.LocadorDAO;
import model.Bicicleta;
import model.Locador;
import model.Locatario;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SolicitarAluguelView extends JInternalFrame {

	private static final long serialVersionUID = 8272040968029096300L;
	private static Locatario locatario;
	private JTextField txtValorDiario;
	private JTextField txtValorFinal;
	private long diasTotaisSolicitacao = 1;
	private AluguelController controller = new AluguelController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SolicitarAluguelView frame = new SolicitarAluguelView(locatario, args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SolicitarAluguelView(Locatario locatario, String[] args) {
		SolicitarAluguelView.locatario = locatario;
		setTitle("Solicitar aluguel");
		setBounds(100, 100, 450, 525);
		getContentPane().setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/images/icons/bikeRent.png")));
		lblLogo.setBounds(116, 11, 194, 137);
		getContentPane().add(lblLogo);

		JPanel panelPeriodo = new JPanel();
		panelPeriodo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Selecione o per\u00EDodo que deseja alugar", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelPeriodo.setBounds(10, 212, 414, 109);
		getContentPane().add(panelPeriodo);
		panelPeriodo.setLayout(null);

		JLabel lblIncio = new JLabel("In\u00EDcio");
		lblIncio.setBounds(10, 35, 46, 14);
		panelPeriodo.add(lblIncio);

		JDateChooser jcDataInicio = new JDateChooser();
		jcDataInicio.setBounds(66, 29, 205, 20);
		panelPeriodo.add(jcDataInicio);

		JLabel lblFim = new JLabel("Fim");
		lblFim.setBounds(10, 68, 46, 14);
		panelPeriodo.add(lblFim);

		JPanel panelBikes = new JPanel();
		panelBikes.setBorder(new TitledBorder(null, "Selecione uma bicicleta dispon\u00EDvel", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panelBikes.setBounds(10, 332, 414, 103);
		getContentPane().add(panelBikes);
		panelBikes.setLayout(null);

		JComboBox<Bicicleta> cbBicicletasDisponiveis = new JComboBox();
		cbBicicletasDisponiveis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Bicicleta bic = (Bicicleta) cbBicicletasDisponiveis.getSelectedItem();
				txtValorDiario.setText(String.valueOf(bic.getValorDeAluguel()));
				txtValorFinal.setText(String.valueOf(diasTotaisSolicitacao * bic.getValorDeAluguel()));
			}
		});
		cbBicicletasDisponiveis.setBounds(10, 31, 394, 20);
		panelBikes.add(cbBicicletasDisponiveis);

		JDateChooser jcDataFim = new JDateChooser();
		jcDataFim.setBounds(66, 62, 205, 20);
		panelPeriodo.add(jcDataFim);

		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!controller.validaDatas(jcDataInicio.getDate(), jcDataInicio.getDate())) {
					return;
				} 
				
				BicicletaDAO bicDAO = new BicicletaDAO();
				LocalDate dtInicio = jcDataInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate dtFim = jcDataFim.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if(dtInicio.isAfter(dtFim)) {
					JOptionPane.showMessageDialog(null, "A data do início do aluguel deve ser anterior a data final.");
					return;
				}

				List<Bicicleta> bikesDisponiveis = bicDAO.listarBikesDisponiveisParaLocacao(dtInicio, dtFim);
				if (dtInicio.isBefore(dtFim)) {
					cbBicicletasDisponiveis.setModel(new DefaultComboBoxModel(bikesDisponiveis.toArray()));
				} else {
					cbBicicletasDisponiveis.setModel(new DefaultComboBoxModel());
					txtValorDiario.setText("");
					txtValorFinal.setText("");
				}
				diasTotaisSolicitacao = ChronoUnit.DAYS.between(dtInicio, dtFim);

				if (diasTotaisSolicitacao <= 0) {
					diasTotaisSolicitacao = 1;
				} else {
					diasTotaisSolicitacao++;
				}
			}
		});
		btnNewButton.setBounds(303, 31, 89, 51);
		panelPeriodo.add(btnNewButton);

		JLabel lblValorDirio = new JLabel("Valor di\u00E1rio");
		lblValorDirio.setBounds(10, 62, 63, 14);
		panelBikes.add(lblValorDirio);

		txtValorDiario = new JTextField();
		txtValorDiario.setEnabled(false);
		txtValorDiario.setBounds(83, 59, 86, 20);
		panelBikes.add(txtValorDiario);
		txtValorDiario.setColumns(10);

		JLabel lblValorTotal = new JLabel("Valor total");
		lblValorTotal.setBounds(250, 62, 63, 14);
		panelBikes.add(lblValorTotal);

		txtValorFinal = new JTextField();
		txtValorFinal.setEnabled(false);
		txtValorFinal.setColumns(10);
		txtValorFinal.setBounds(318, 59, 86, 20);
		panelBikes.add(txtValorFinal);

		JButton btnSolicitar = new JButton("Solicitar");
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if(!controller.validaDatas(jcDataInicio.getDate(), jcDataInicio.getDate())) {
					return;
				} 
				
				LocalDate dtInicio = jcDataInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate dtFim = jcDataFim.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if(controller.validaDataInicioPosteriorDataFim(dtInicio, dtFim)) {
					return;
				}
				
				if(txtValorDiario.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Selecione uma bicicleta antes de prosseguir!");
					return;
				}
				
				double valorPrevisto = Double.parseDouble(txtValorFinal.getText());
				
				// Verificar se o Locatario não possui multa
				Bicicleta bic = (Bicicleta) cbBicicletasDisponiveis.getSelectedItem();
				Locador locador = bic.getLocador();
				if(controller.enviarSolicitacao(locatario, locador, bic, dtInicio, dtFim, valorPrevisto)) {
					dispose();
				}
				
				dispose();
			}
		});
		btnSolicitar.setBounds(335, 447, 89, 23);
		getContentPane().add(btnSolicitar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(10, 447, 89, 23);
		getContentPane().add(btnCancelar);
	}

	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
