package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class UtilsService {

	public UtilsService() {
	}

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

}
