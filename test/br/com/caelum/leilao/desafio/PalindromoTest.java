package br.com.caelum.leilao.desafio;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.desafio.Palindromo;

public class PalindromoTest {

	@Test
	public void deveSerPalindromo() {
		Palindromo palindromo = new Palindromo();
		
		Assert.assertTrue(palindromo.ehPalindromo("Socorram-me subi no onibus em Marrocos"));
		Assert.assertTrue(palindromo.ehPalindromo("Anotaram a data da maratona"));
				
		
	}

}
