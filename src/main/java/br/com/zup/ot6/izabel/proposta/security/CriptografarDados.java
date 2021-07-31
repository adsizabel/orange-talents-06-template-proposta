package br.com.zup.ot6.izabel.proposta.security;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class CriptografarDados {
	
	static String salt = "e606bfd5cf9f198e";
	
	public static String criptografar(String dado) {
		TextEncryptor dadoTextEncryptor = Encryptors.text(dado, salt);
		dado = dadoTextEncryptor.encrypt(dado);
		return dado;
	}
	
	public static String descriptografar(String dado) {
		TextEncryptor dadoTextEncryptor = Encryptors.text(dado, salt);
		dado = dadoTextEncryptor.decrypt(dado);
		return dado;
	}


}
