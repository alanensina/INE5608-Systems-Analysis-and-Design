package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.AluguelDAO;
import model.Aluguel;
import model.Bicicleta;
import model.Locatario;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class TelaAluguelPendenteLocatario extends JInternalFrame {

	private static final long serialVersionUID = -848446941036866364L;
	private JTextField txtBike;
	private JTextField txtInicio;
	private JTextField txtFim;
	private JTextField txtValor;
	private JTextField txtLocador;
	private AluguelDAO aluguelDAO = new AluguelDAO();
	private static Locatario loc;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAluguelPendenteLocatario frame = new TelaAluguelPendenteLocatario(loc, args);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaAluguelPendenteLocatario(Locatario loc, String[] args) {
		TelaAluguelPendenteLocatario.loc = loc;
		setTitle("Alugueis agendados");
		setBounds(100, 100, 450, 429);
		getContentPane().setLayout(null);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(TelaAluguelPendenteLocatario.class.getResource("/images/icons/calendar.png")));
		lblLogo.setBounds(159, 12, 144, 118);
		getContentPane().add(lblLogo);

		JLabel lbSelecioneOAluguel = new JLabel("Selecione o aluguel");
		lbSelecioneOAluguel.setBounds(12, 146, 139, 15);
		getContentPane().add(lbSelecioneOAluguel);

		List<Aluguel> alugueisPendentes = aluguelDAO.buscarAlugueisPendentesLocatario(loc);
		JComboBox<Aluguel> cbAlugueisAgendados = new JComboBox();
		if (alugueisPendentes != null) {
			cbAlugueisAgendados.setModel(new DefaultComboBoxModel(alugueisPendentes.toArray()));
		} else {
			cbAlugueisAgendados.setModel(new DefaultComboBoxModel());
		}
		cbAlugueisAgendados.setBounds(169, 141, 259, 24);
		getContentPane().add(cbAlugueisAgendados);

		JPanel panelDadosAluguel = new JPanel();
		panelDadosAluguel.setBounds(12, 186, 416, 153);
		getContentPane().add(panelDadosAluguel);
		panelDadosAluguel.setLayout(null);

		JLabel lblBicicleta = new JLabel("Bicicleta");
		lblBicicleta.setBounds(12, 12, 66, 15);
		panelDadosAluguel.add(lblBicicleta);

		JLabel lblIncioPrevisto = new JLabel("Início previsto");
		lblIncioPrevisto.setBounds(12, 39, 113, 15);
		panelDadosAluguel.add(lblIncioPrevisto);

		JLabel lblFimPrevisto = new JLabel("Fim previsto");
		lblFimPrevisto.setBounds(12, 66, 113, 15);
		panelDadosAluguel.add(lblFimPrevisto);

		JLabel lblValorPrevisto = new JLabel("Valor previsto");
		lblValorPrevisto.setBounds(12, 93, 113, 15);
		panelDadosAluguel.add(lblValorPrevisto);

		JLabel lblLocador = new JLabel("Locador");
		lblLocador.setBounds(12, 120, 66, 15);
		panelDadosAluguel.add(lblLocador);

		txtBike = new JTextField();
		txtBike.setEditable(false);
		txtBike.setBounds(119, 10, 285, 19);
		panelDadosAluguel.add(txtBike);
		txtBike.setColumns(10);

		txtInicio = new JTextField();
		txtInicio.setEditable(false);
		txtInicio.setBounds(119, 37, 285, 19);
		panelDadosAluguel.add(txtInicio);
		txtInicio.setColumns(10);

		txtFim = new JTextField();
		txtFim.setEditable(false);
		txtFim.setBounds(118, 66, 286, 19);
		panelDadosAluguel.add(txtFim);
		txtFim.setColumns(10);

		txtValor = new JTextField();
		txtValor.setEditable(false);
		txtValor.setBounds(119, 93, 285, 19);
		panelDadosAluguel.add(txtValor);
		txtValor.setColumns(10);

		txtLocador = new JTextField();
		txtLocador.setEditable(false);
		txtLocador.setBounds(119, 118, 285, 19);
		panelDadosAluguel.add(txtLocador);
		txtLocador.setColumns(10);

		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(txtBike.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um item antes de prosseguir.");
					return;
				}
				
				Object[] options = { "Cancelar", "Confirmar" };
				int resp = JOptionPane.showOptionDialog(null,"Deseja confirmar o início do aluguel?",
						"ATENÇÃO", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
				
				if(resp > 0) {
					
				}

				dispose();
			}
		});
		btnIniciar.setForeground(new Color(255, 255, 255));
		btnIniciar.setBackground(new Color(60, 179, 113));
		btnIniciar.setBounds(314, 351, 114, 25);
		getContentPane().add(btnIniciar);

		JButton btnCancelarAlugul = new JButton("Cancelar aluguel");
		btnCancelarAlugul.setBackground(new Color(220, 20, 60));
		btnCancelarAlugul.setForeground(new Color(255, 255, 255));
		btnCancelarAlugul.setBounds(138, 351, 165, 25);
		getContentPane().add(btnCancelarAlugul);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(12, 351, 114, 25);

		cbAlugueisAgendados.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Aluguel alug = (Aluguel) cbAlugueisAgendados.getSelectedItem();
				txtBike.setText(alug.getBicicleta().getModelo());
				txtInicio.setText(alug.getDtInicio().toString());
				txtFim.setText(alug.getDtFimPrevisto().toString());
				txtLocador.setText(alug.getLocador().getNome());
				txtValor.setText(String.valueOf(alug.getValorPrevisto()));
			}
		});
		getContentPane().add(btnCancelar);
	}

	public void setPosition() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}
