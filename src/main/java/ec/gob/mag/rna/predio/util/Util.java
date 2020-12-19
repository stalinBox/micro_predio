package ec.gob.mag.rna.predio.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

public class Util {

	/**
	 * METODO PARA VERIFICAR LA CEDULA DE CIUDADANIA ECUATORIANA
	 * 
	 * @param cedula
	 * @return boolean
	 */
	public boolean verificarCedula(String cedula) {
		int total = 0;
		int tamanoLongitudCedula = 10;
		int[] coeficientes = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		int numeroProviancias = 24;
		int tercerdigito = 6;
		if (cedula.matches("[0-9]*") && cedula.length() == tamanoLongitudCedula) {
			int provincia = Integer.parseInt(cedula.charAt(0) + "" + cedula.charAt(1));
			int digitoTres = Integer.parseInt(cedula.charAt(2) + "");
			if ((provincia > 0 && provincia <= numeroProviancias) && digitoTres < tercerdigito) {
				int digitoVerificadorRecibido = Integer.parseInt(cedula.charAt(9) + "");
				for (int i = 0; i < coeficientes.length; i++) {
					int valor = Integer.parseInt(coeficientes[i] + "") * Integer.parseInt(cedula.charAt(i) + "");
					total = valor >= 10 ? total + (valor - 9) : total + valor;
				}
				int digitoVerificadorObtenido = total >= 10 ? (total % 10) != 0 ? 10 - (total % 10) : (total % 10)
						: total;
				if (digitoVerificadorObtenido == digitoVerificadorRecibido) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public static String cleanBlanks(String str) {
		str = str.replaceAll(" +", " ");
		str = str.trim();
		return str;
	}

	public static int randomBetween(int start, int end) {
		int dif = end - start;
		if (dif > 0) {
			Random random = new Random();
			return start + random.nextInt((int) dif);
		}
		return -1;
	}

	public static String generateStringRandom(int numberCharacteres) {
		char character;
		int numberRandom;
		String password = "";
		for (int i = 0; i < numberCharacteres; i++) {
			numberRandom = randomBetween(33, 125);
			character = (char) numberRandom;
			password = password + character;
		}
		return password;
	}

	public static String encryptMD5(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] digest = md.digest();
			return DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String generatePassword(int numberCharacteres) {
		return Util.encryptMD5(generateStringRandom(numberCharacteres));
	}

	public static Date dateNow() {
		Date dateIn = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(dateIn.toInstant(), ZoneId.systemDefault());
		return Date.from(ldt.atZone(ZoneId.of("UTC-05:00")).toInstant());
	}

}
