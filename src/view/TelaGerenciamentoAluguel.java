package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import controller.AluguelController;
import dao.AluguelDAO;
import model.Aluguel;
import model.Locatario;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaGerenciamentoAluguel extends JInternalFrame {

	private static final long serialVersionUID = -9000025784179448163L;
	private JTextField txtBike;
	private JTextField txtInicio;
	private JTextField txtFimPrevisto;
	private JTextField txtFimRealizado;
	private JTextField txtValorPrevisto;
	private JTextField txtMulta;
	private JTextField txtValorFinal;
	private JTextField txtLocador;
	private AluguelDAO aluguelDAO = new AluguelDAO();
	private static Locatario loc;
	private AluguelController controller = new AluguelController();
	private double valorFinal = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaGerenciamentoAluguel frame = new TelaGerenciamentoAluguel(args, loc);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaGerenciamentoAluguel(String[] args, Locatario loc) {
		TelaGerenciamentoAluguel.loc = loc;
		setTitle("Aluguel em andamento");
		setBounds(100, 100, 431, 509);
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaGerenciamentoAluguel.class.getResource("/images/icons/bikered.png")));
		lblLogo.setBounds(146, 12, 136, 140);
		getContentPane().add(lblLogo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dados do aluguel", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(22, 167, 385, 256);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBicicleta = new JLabel("Bicicleta");
		lblBicicleta.setBounds(12, 28, 66, 15);
		panel.add(lblBicicleta);
		
		JLabel lblDataInicio = new JLabel("Data inicio");
		lblDataInicio.setBounds(12, 55, 100, 15);
		panel.add(lblDataInicio);
		
		JLabel lblFimPrevisto = new JLabel("Fim previsto");
		lblFimPrevisto.setBounds(12, 82, 107, 15);
		panel.add(lblFimPrevisto);
		
		JLabel lblFimRealizado = new JLabel("Fim realizado");
		lblFimRealizado.setBounds(12, 109, 109, 15);
		panel.add(lblFimRealizado);
		
		JLabel lblValorPrevisto = new JLabel("Valor previsto");
		lblValorPrevisto.setBounds(12, 136, 100, 15);
		panel.add(lblValorPrevisto);
		
		JLabel lblMulta = new JLabel("Multa");
		lblMulta.setBounds(12, 163, 66, 15);
		panel.add(lblMulta);
		
		JLabel lblLocador = new JLabel("Locador");
		lblLocador.setBounds(12, 216, 66, 15);
		panel.add(lblLocador);
		
		txtBike = new JTextField();
		txtBike.setEditable(false);
		txtBike.setBounds(119, 26, 254, 19);
		panel.add(txtBike);
		txtBike.setColumns(10);
		
		txtInicio = new JTextField();
		txtInicio.setEditable(false);
		txtInicio.setBounds(119, 53, 254, 19);
		panel.add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFimPrevisto = new JTextField();
		txtFimPrevisto.setEditable(false);
		txtFimPrevisto.setBounds(119, 80, 254, 19);
		panel.add(txtFimPrevisto);
		txtFimPrevisto.setColumns(10);
		
		txtFimRealizado = new JTextField();
		txtFimRealizado.setEditable(false);
		txtFimRealizado.setBounds(119, 107, 254, 19);
		panel.add(txtFimRealizado);
		txtFimRealizado.setColumns(10);
		
		txtValorPrevisto = new JTextField();
		txtValorPrevisto.setEditable(false);
		txtValorPrevisto.setBounds(119, 134, 254, 19);
		panel.add(txtValorPrevisto);
		txtValorPrevisto.setColumns(10);
		
		txtMulta = new JTextField();
		txtMulta.setEditable(false);
		txtMulta.setBounds(119, 161, 254, 19);
		panel.add(txtMulta);
		txtMulta.setColumns(10);
		
		txtLocador = new JTextField();
		txtLocador.setEditable(false);
		txtLocador.setBounds(119, 214, 254, 19);
		panel.add(txtLocador);
		txtLocador.setColumns(10);
		
		JLabel lblValorFinal = new JLabel("Valor final");
		lblValorFinal.setBounds(12, 189, 100, 15);
		panel.add(lblValorFinal);
		
		txtValorFinal = new JTextField();
		txtValorFinal.setEditable(false);
		txtValorFinal.setBounds(119, 187, 254, 19);
		panel.add(txtValorFinal);
		txtValorFinal.setColumns(10);
		
		JButton btnFinalizarAluguel = new JButton("Finalizar aluguel");
		btnFinalizarAluguel.setBounds(260, 435, 147, 25);
		getContentPane().add(btnFinalizarAluguel);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(22, 435, 114, 25);
		
		List<Aluguel> alugueisAtivos = aluguelDAO.buscarAluguelAtivo(loc);
		
		if(!alugueisAtivos.isEmpty()) {
			Aluguel alug = alugueisAtivos.get(0);
			txtBike.setText(alug.getBicicleta().getModelo());
			txtLocador.setText(alug.getLocador().getNome());
			txtInicio.setText(alug.getDtInicio().toString());
			txtFimPrevisto.setText(alug.getDtFimPrevisto().toString());
			txtFimRealizado.setText(LocalDate.now().toString());
			txtValorPrevisto.setText(String.valueOf(alug.getValorPrevisto()));			
			long diasExcedentes = contarDiasDeAluguel(alug.getDtFimPrevisto());
			calcularValores(alug, diasExcedentes);			
		}
		
		btnFinalizarAluguel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,"Deseja finalizar o aluguel? \nObs: Caso confirme, uma confirmação de devolução da bicicleta será enviada ao locador. \nCaso o locador não confirme o recebimento, uma multa de R$ 3000,00 será debitada de sua conta.",
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if(resp > 0) {
					if(!alugueisAtivos.isEmpty()) {
						Aluguel alug = alugueisAtivos.get(0);
						controller.enviarSolicitacaoDeFinalizacao(alug, valorFinal);			
					}
					
				}
				dispose();
			}
		});
		
		getContentPane().add(btnCancelar);

	}
	
	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
	
	public long contarDiasDeAluguel(LocalDate inicio) {
		LocalDate fim = LocalDate.now();
		long periodo =  ChronoUnit.DAYS.between(inicio, fim);
		return periodo;
	}
	
	public void calcularValores(Aluguel aluguel, long diasExcedentes) {
		double multa = 0;
		if(diasExcedentes > 0) {
			multa = diasExcedentes * (3 * aluguel.getBicicleta().getValorDeAluguel());
		}
		txtMulta.setText(String.valueOf(multa));
		this.valorFinal = aluguel.getValorPrevisto() + multa;
		txtValorFinal.setText(String.valueOf(valorFinal));		
	}
	
}
