package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.AluguelController;
import dao.AluguelDAO;
import model.Aluguel;
import model.Locador;
import model.Locatario;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class TelaAluguelPendenteLocador extends JInternalFrame {

	private static final long serialVersionUID = 8724324911819865660L;
	private JTextField txtBike;
	private JTextField txtInicio;
	private JTextField txtFim;
	private JTextField txtValor;
	private JTextField txtLocatario;
	private static Locador loc;
	private AluguelDAO aluguelDAO = new AluguelDAO();
	private AluguelController controller = new AluguelController();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAluguelPendenteLocador frame = new TelaAluguelPendenteLocador(loc, args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAluguelPendenteLocador(Locador loc, String[] args) {
		TelaAluguelPendenteLocador.loc = loc;
		setTitle("Alugueis agendados");
		setBounds(100, 100, 450, 452);
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaAluguelPendenteLocador.class.getResource("/images/icons/calendar.png")));
		lblLogo.setBounds(155, 12, 135, 158);
		getContentPane().add(lblLogo);
		
		JLabel lblSelecioneOAluguel = new JLabel("Selecione o aluguel");
		lblSelecioneOAluguel.setBounds(12, 177, 135, 15);
		getContentPane().add(lblSelecioneOAluguel);
		
		List<Aluguel> alugueisPendentes = aluguelDAO.buscarAlugueisPendentesLocador(loc);
		JComboBox<Aluguel> cbAlugueisAgendados = new JComboBox();
		if (alugueisPendentes != null) {
			cbAlugueisAgendados.setModel(new DefaultComboBoxModel(alugueisPendentes.toArray()));
		} else {
			cbAlugueisAgendados.setModel(new DefaultComboBoxModel());
		}
		cbAlugueisAgendados.setBounds(155, 172, 273, 24);
		getContentPane().add(cbAlugueisAgendados);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados do aluguel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 203, 416, 175);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBicicleta = new JLabel("Bicicleta");
		lblBicicleta.setBounds(12, 37, 66, 15);
		panel.add(lblBicicleta);
		
		JLabel lblDataIncio = new JLabel("Data início");
		lblDataIncio.setBounds(12, 64, 88, 15);
		panel.add(lblDataIncio);
		
		JLabel lblDataFim = new JLabel("Data fim");
		lblDataFim.setBounds(12, 94, 66, 15);
		panel.add(lblDataFim);
		
		JLabel lblValorPrevisto = new JLabel("Valor previsto");
		lblValorPrevisto.setBounds(12, 121, 118, 15);
		panel.add(lblValorPrevisto);
		
		JLabel lblLocatrio = new JLabel("Locatário");
		lblLocatrio.setBounds(12, 148, 66, 15);
		panel.add(lblLocatrio);
		
		txtBike = new JTextField();
		txtBike.setEditable(false);
		txtBike.setBounds(120, 35, 284, 19);
		panel.add(txtBike);
		txtBike.setColumns(10);
		
		txtInicio = new JTextField();
		txtInicio.setEditable(false);
		txtInicio.setBounds(120, 62, 284, 19);
		panel.add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFim = new JTextField();
		txtFim.setEditable(false);
		txtFim.setBounds(120, 92, 284, 19);
		panel.add(txtFim);
		txtFim.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.setEditable(false);
		txtValor.setBounds(120, 119, 284, 19);
		panel.add(txtValor);
		txtValor.setColumns(10);
		
		txtLocatario = new JTextField();
		txtLocatario.setEditable(false);
		txtLocatario.setBounds(120, 148, 284, 19);
		panel.add(txtLocatario);
		txtLocatario.setColumns(10);
		
		JButton btnInicio = new JButton("Iniciar");
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtBike.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um item antes de prosseguir.");
					return;
				}
				
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,"Deseja confirmar o início do aluguel?",
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if(resp > 0) {
					Aluguel aluguel = (Aluguel) cbAlugueisAgendados.getSelectedItem();
					controller.confirmarInicioDeAluguel(aluguel);
				}

				dispose();
			}
		});
		btnInicio.setForeground(new Color(255, 255, 255));
		btnInicio.setBackground(new Color(60, 179, 113));
		btnInicio.setBounds(320, 385, 108, 25);
		getContentPane().add(btnInicio);
		
		JButton btnCancelarAluguel = new JButton("Cancelar aluguel");
		btnCancelarAluguel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtBike.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um item antes de prosseguir.");
					return;
				}
				
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,"Deseja cancelar o aluguel? Obs: 50% do valor previsto será cobrado e enviado para o locatário.",
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if(resp > 0) {
					Aluguel aluguel = (Aluguel) cbAlugueisAgendados.getSelectedItem();
					controller.enviarSolicitacaoDeCancelamentoDeAluguelPeloLocador(aluguel);
				}

				dispose();
			}
		});
		btnCancelarAluguel.setBackground(new Color(220, 20, 60));
		btnCancelarAluguel.setForeground(new Color(255, 255, 255));
		btnCancelarAluguel.setBounds(132, 385, 176, 25);
		getContentPane().add(btnCancelarAluguel);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(12, 385, 108, 25);
		
		cbAlugueisAgendados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Aluguel alug = (Aluguel) cbAlugueisAgendados.getSelectedItem();
				txtBike.setText(alug.getBicicleta().getModelo());
				txtInicio.setText(alug.getDtInicio().toString());
				txtFim.setText(alug.getDtFimPrevisto().toString());
				txtLocatario.setText(alug.getLocador().getNome());
				txtValor.setText(String.valueOf(alug.getValorPrevisto()));
			}
		});
		
		getContentPane().add(btnNewButton);

	}
	
	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
