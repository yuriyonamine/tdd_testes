package br.com.caelum.leilao.desafio;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.desafio.Palindromo;

public class PalindromoTest {

	@Test
	public void deveIdentificarPalindromo() {
		Palindromo palindromo = new Palindromo();

		Assert.assertTrue(palindromo
				.ehPalindromo("Socorram-me subi no onibus em Marrocos"));
	}

	@Test
	public void deveIdentificarPalindromoComCaracteresInvalidos() {
		Palindromo palindromo = new Palindromo();

		Assert.assertTrue(palindromo
				.ehPalindromo("Anotaram-a data da maratona"));
	}

	@Test
	public void deveIdentificarSeNaoEhPalindromo() {
		Palindromo palindromo = new Palindromo();

		Assert.assertFalse(palindromo
				.ehPalindromo("a data da maratona"));
	}

}
