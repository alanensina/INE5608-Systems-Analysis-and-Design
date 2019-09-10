package app;

import java.time.LocalDate;

import dao.LocatarioDAO;
import model.Endereco;
import model.Locatario;

public class Main {

	public static void main(String[] args) {
		//inicializa(args);
		LocatarioDAO ldao = new LocatarioDAO();
		Endereco end = new Endereco();
		Locatario loc = new Locatario();
		
		end.setLogradouro("");
		end.setBairro("");
		end.setCidade("");
		end.setEstado("");
		end.setComplemento("");
		end.setNumero("");
		
		loc.setNome("");
		loc.setSenha("");
		loc.setCelular("");
		loc.setCpf("");
		loc.setDataCadastro(LocalDate.now());
		loc.setLogin("");
		loc.setEndereco(end);
		
		ldao.salvar(loc, end);		
	}
}