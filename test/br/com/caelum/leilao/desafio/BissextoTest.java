package br.com.caelum.leilao.desafio;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.desafio.VerificadorAnoBissexto;

public class BissextoTest {

	@Test
	public void deveRetornarAnoBissexto() {
		VerificadorAnoBissexto bissexto = new VerificadorAnoBissexto();

		Assert.assertEquals(true, bissexto.isAnoBissexto(2016));
		Assert.assertEquals(true, bissexto.isAnoBissexto(400));
		Assert.assertEquals(true, bissexto.isAnoBissexto(2000));

	}

	@Test
	public void naoDeveRetornarAnoBissexto() {
		VerificadorAnoBissexto bissexto = new VerificadorAnoBissexto();

		Assert.assertFalse(bissexto.isAnoBissexto(3));
		Assert.assertFalse(bissexto.isAnoBissexto(99));
		Assert.assertFalse(bissexto.isAnoBissexto(100));
	}
	}
