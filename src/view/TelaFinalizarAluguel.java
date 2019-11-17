package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.AluguelController;
import dao.AluguelDAO;
import model.Aluguel;
import model.Locador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

public class TelaFinalizarAluguel extends JInternalFrame {

	private static final long serialVersionUID = 8620985420870622530L;
	private JTextField txtBike;
	private JTextField txtInicio;
	private JTextField txtFim;
	private JTextField txtValorPrevisto;
	private JTextField txtMulta;
	private JTextField txtValorFinal;
	private JTextField txtLocatario;
	private static Locador loc;
	private AluguelDAO aluguelDAO = new AluguelDAO();
	private AluguelController controller = new AluguelController();
	private JTextField txtFimPrevisto;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaFinalizarAluguel frame = new TelaFinalizarAluguel(loc, args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaFinalizarAluguel(Locador loc, String[] args) {
		TelaFinalizarAluguel.loc = loc;
		setTitle("Aluguéis finalizados");
		setBounds(100, 100, 450, 552);
		getContentPane().setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaFinalizarAluguel.class.getResource("/images/icons/racing-flag.png")));
		lblLogo.setBounds(168, 12, 136, 142);
		getContentPane().add(lblLogo);
		
		JLabel lblAlugueisFinalizados = new JLabel("Aluguéis finalizados");
		lblAlugueisFinalizados.setBounds(12, 166, 145, 15);
		getContentPane().add(lblAlugueisFinalizados);
		
		List<Aluguel> alugueisPendentes = aluguelDAO.buscarAlugueisFinalizados(loc);
		
		JComboBox<Aluguel> cbAlugueisFinalizados = new JComboBox();
		if (alugueisPendentes != null) {
			cbAlugueisFinalizados.setModel(new DefaultComboBoxModel(alugueisPendentes.toArray()));
		} else {
			cbAlugueisFinalizados.setModel(new DefaultComboBoxModel());
		}
		
		cbAlugueisFinalizados.setBounds(164, 161, 264, 24);
		getContentPane().add(cbAlugueisFinalizados);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 193, 416, 242);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBicicleta = new JLabel("Bicicleta");
		lblBicicleta.setBounds(12, 12, 66, 15);
		panel.add(lblBicicleta);
		
		JLabel lblDataIncio = new JLabel("Data início");
		lblDataIncio.setBounds(12, 39, 92, 15);
		panel.add(lblDataIncio);
		
		JLabel lblDataFim = new JLabel("Data fim realizada");
		lblDataFim.setBounds(12, 94, 144, 15);
		panel.add(lblDataFim);
		
		JLabel lblValorPrevisto = new JLabel("Valor previsto");
		lblValorPrevisto.setBounds(12, 121, 118, 15);
		panel.add(lblValorPrevisto);
		
		JLabel lblMulta = new JLabel("Multa");
		lblMulta.setBounds(12, 148, 66, 15);
		panel.add(lblMulta);
		
		JLabel lblValorFinal = new JLabel("Valor final");
		lblValorFinal.setBounds(12, 174, 92, 15);
		panel.add(lblValorFinal);
		
		JLabel lblLocatrio = new JLabel("Locatário");
		lblLocatrio.setBounds(12, 201, 66, 15);
		panel.add(lblLocatrio);
		
		txtBike = new JTextField();
		txtBike.setEditable(false);
		txtBike.setBounds(156, 10, 248, 19);
		panel.add(txtBike);
		txtBike.setColumns(10);
		
		txtInicio = new JTextField();
		txtInicio.setEditable(false);
		txtInicio.setBounds(156, 37, 248, 19);
		panel.add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFim = new JTextField();
		txtFim.setEditable(false);
		txtFim.setBounds(156, 92, 248, 19);
		panel.add(txtFim);
		txtFim.setColumns(10);
		
		txtValorPrevisto = new JTextField();
		txtValorPrevisto.setEditable(false);
		txtValorPrevisto.setBounds(156, 119, 248, 19);
		panel.add(txtValorPrevisto);
		txtValorPrevisto.setColumns(10);
		
		txtMulta = new JTextField();
		txtMulta.setEditable(false);
		txtMulta.setBounds(156, 148, 248, 19);
		panel.add(txtMulta);
		txtMulta.setColumns(10);
		
		txtValorFinal = new JTextField();
		txtValorFinal.setEditable(false);
		txtValorFinal.setBounds(156, 172, 248, 19);
		panel.add(txtValorFinal);
		txtValorFinal.setColumns(10);
		
		txtLocatario = new JTextField();
		txtLocatario.setEditable(false);
		txtLocatario.setBounds(156, 199, 248, 19);
		panel.add(txtLocatario);
		txtLocatario.setColumns(10);
		
		JLabel lblDataFimPrevista = new JLabel("Data fim prevista");
		lblDataFimPrevista.setBounds(12, 66, 144, 15);
		panel.add(lblDataFimPrevista);
		
		txtFimPrevisto = new JTextField();
		txtFimPrevisto.setEditable(false);
		txtFimPrevisto.setBounds(156, 64, 248, 19);
		panel.add(txtFimPrevisto);
		txtFimPrevisto.setColumns(10);
		
		JButton btnConfirmarDevoluo = new JButton("Confirmar devolução");
		btnConfirmarDevoluo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtBike.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um item antes de prosseguir.");
					return;
				}
				
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,"Confirma a devolução da bicicleta?",
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if(resp > 0) {
					Aluguel aluguel = (Aluguel) cbAlugueisFinalizados.getSelectedItem();
					controller.finalizarAluguel(aluguel);
				}

				dispose();
			}
		});
		btnConfirmarDevoluo.setBackground(new Color(60, 179, 113));
		btnConfirmarDevoluo.setForeground(Color.WHITE);
		btnConfirmarDevoluo.setBounds(241, 447, 187, 25);
		getContentPane().add(btnConfirmarDevoluo);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(12, 447, 114, 58);
		getContentPane().add(btnCancelar);
		
		JButton btnNaoDevolveu = new JButton("Não houve devolução");
		btnNaoDevolveu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtBike.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um item antes de prosseguir.");
					return;
				}
				
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,"O locatário não devolveu a bicicleta?",
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if(resp > 0) {
					Aluguel aluguel = (Aluguel) cbAlugueisFinalizados.getSelectedItem();
					controller.finalizarAluguelSemDevolucao(aluguel);
				}

				dispose();
				
			}
		});
		btnNaoDevolveu.setBackground(new Color(220, 20, 60));
		btnNaoDevolveu.setForeground(new Color(255, 255, 255));
		btnNaoDevolveu.setBounds(241, 480, 187, 25);
		
		cbAlugueisFinalizados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Aluguel alug = (Aluguel) cbAlugueisFinalizados.getSelectedItem();
				txtBike.setText(alug.getBicicleta().getModelo());
				txtInicio.setText(alug.getDtInicio().toString());
				txtFim.setText(alug.getDtFimRealizado().toString());
				txtLocatario.setText(alug.getLocatario().getNome());
				txtMulta.setText(String.valueOf(alug.getValorMulta()));
				txtValorFinal.setText(String.valueOf(alug.getValorFinal()));
				txtValorPrevisto.setText(String.valueOf(alug.getValorPrevisto()));
				txtFimPrevisto.setText(alug.getDtFimPrevisto().toString());
			}
		});
		
		getContentPane().add(btnNaoDevolveu);
	}
	
	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
