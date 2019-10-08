package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.swing.JOptionPane;

import model.Endereco;
import model.Locador;
import model.Locatario;

public class UtilsService {
	private static Properties prop = getProp();

	public static String converterMD5(String texto) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(texto.getBytes(), 0, texto.length());
		return new BigInteger(1, m.digest()).toString(16);
	}

	public static Properties getProp() {
		try {
			Properties props = new Properties();
			FileInputStream file = new FileInputStream("./resources/utils/strings.properties");
			props.load(file);
			return props;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static Properties getSqls() {
		try {
			Properties sqls = new Properties();
			FileInputStream file = new FileInputStream("./resources/utils/sql.properties");
			sqls.load(file);
			return sqls;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static boolean validaCampoObrigatorio(String campo) {
		campo = campo.replace( "." , "");
		campo = campo.replace( "/" , "");
		campo = campo.replace( "-" , "");
		campo = campo.replace( "(" , "");
		campo = campo.replace( ")" , "");
		campo = campo.replace( " " , "");
		
		return campo.isEmpty();
	}
}
