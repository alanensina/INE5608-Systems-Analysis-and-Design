package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import static service.UtilsService.getProp;

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
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
	private Properties prop = getProp();

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
		setTitle(prop.getProperty("SolicitarAluguelView.Title"));
		setBounds(100, 100, 450, 525);
		getContentPane().setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(TelaLogin.class.getResource("/images/icons/bikeRent.png")));
		lblLogo.setBounds(116, 11, 194, 137);
		getContentPane().add(lblLogo);

		JPanel panelPeriodo = new JPanel();
		panelPeriodo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				prop.getProperty("SolicitarAluguelView.Panel.Periodo"), TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelPeriodo.setBounds(10, 212, 414, 109);
		getContentPane().add(panelPeriodo);
		panelPeriodo.setLayout(null);

		JLabel lblIncio = new JLabel(prop.getProperty("SolicitarAluguelView.Label.Inicio"));
		lblIncio.setBounds(10, 35, 98, 14);
		panelPeriodo.add(lblIncio);

		JDateChooser jcDataInicio = new JDateChooser();
		jcDataInicio.setBounds(126, 29, 145, 20);
		panelPeriodo.add(jcDataInicio);

		JLabel lblFim = new JLabel(prop.getProperty("SolicitarAluguelView.Label.Fim"));
		lblFim.setBounds(10, 68, 98, 14);
		panelPeriodo.add(lblFim);

		JPanel panelBikes = new JPanel();
		panelBikes.setBorder(new TitledBorder(null, prop.getProperty("SolicitarAluguelView.Panel.Bikes"), TitledBorder.LEADING,
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
		jcDataFim.setBounds(126, 62, 145, 20);
		panelPeriodo.add(jcDataFim);

		JButton btnConsultar = new JButton(prop.getProperty("SolicitarAluguelView.Button.Consultar"));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!controller.validaDatas(jcDataInicio.getDate(), jcDataFim.getDate())) {
					return;
				} 
				
				BicicletaDAO bicDAO = new BicicletaDAO();
				LocalDate dtInicio = jcDataInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate dtFim = jcDataFim.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if(dtInicio.isAfter(dtFim)) {
					JOptionPane.showMessageDialog(null, prop.getProperty("SolicitarAluguelView.Message.InicioAnteriorAoFim"));
					return;
				}
				
				if(dtInicio.equals(LocalDate.now()) && dtFim.equals(LocalDate.now())) {
					
				}
				else if(dtInicio.isBefore(LocalDate.now()) || dtFim.isBefore(LocalDate.now())) {
					JOptionPane.showMessageDialog(null, prop.getProperty("SolicitarAluguelView.Message.InicioOuFimAnteriorADataAtual"));
					return;
				}

				List<Bicicleta> bikesDisponiveis = bicDAO.listarBikesDisponiveisParaLocacao(dtInicio, dtFim);
				
				if (dtInicio.isBefore(dtFim) || dtInicio.isEqual(dtFim)) {
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
		btnConsultar.setBounds(283, 31, 119, 51);
		panelPeriodo.add(btnConsultar);

		JLabel lblValorDirio = new JLabel(prop.getProperty("SolicitarAluguelView.Label.ValorDiario"));
		lblValorDirio.setBounds(10, 62, 91, 14);
		panelBikes.add(lblValorDirio);

		txtValorDiario = new JTextField();
		txtValorDiario.setEnabled(false);
		txtValorDiario.setBounds(107, 60, 86, 20);
		panelBikes.add(txtValorDiario);
		txtValorDiario.setColumns(10);

		JLabel lblValorTotal = new JLabel(prop.getProperty("SolicitarAluguelView.Label.ValorTotal"));
		lblValorTotal.setBounds(222, 62, 91, 14);
		panelBikes.add(lblValorTotal);

		txtValorFinal = new JTextField();
		txtValorFinal.setEnabled(false);
		txtValorFinal.setColumns(10);
		txtValorFinal.setBounds(318, 59, 86, 20);
		panelBikes.add(txtValorFinal);

		JButton btnSolicitar = new JButton(prop.getProperty("SolicitarAluguelView.Button.Solicitar"));
		btnSolicitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if(!controller.validaDatas(jcDataInicio.getDate(), jcDataFim.getDate())) {
					return;
				} 
				
				LocalDate dtInicio = jcDataInicio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate dtFim = jcDataFim.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				if(controller.validaDataInicioPosteriorDataFim(dtInicio, dtFim)) {
					return;
				}
				
				if(txtValorDiario.getText().equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, prop.getProperty("SolicitarAluguelView.Message.SelecioneUmaBike"));
					return;
				}
				
				if(controller.verificaSeHaMultasPendentes(locatario)) {
					return ;
				}
				
				double valorPrevisto = Double.parseDouble(txtValorFinal.getText());
				
				Bicicleta bic = (Bicicleta) cbBicicletasDisponiveis.getSelectedItem();
				Locador locador = bic.getLocador();

				if(controller.enviarSolicitacao(locatario, locador, bic, dtInicio, dtFim, valorPrevisto)) {
					dispose();
					return ;
				}
				
				JOptionPane.showMessageDialog(null, prop.getProperty("SolicitarAluguelView.Message.ErroAoSolicitar"));
				
				dispose();
			}
		});
		btnSolicitar.setBounds(277, 447, 147, 23);
		getContentPane().add(btnSolicitar);

		JButton btnCancelar = new JButton(prop.getProperty("SolicitarAluguelView.Button.Cancelar"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(10, 447, 147, 23);
		getContentPane().add(btnCancelar);
	}

	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
