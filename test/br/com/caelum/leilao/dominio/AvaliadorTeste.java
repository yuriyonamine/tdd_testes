package br.com.caelum.leilao.dominio;

import org.junit.Assert;
import org.junit.Test;

public class AvaliadorTeste {

	@Test
	public void devePegarMaiorValor() {
		Usuario joao = new Usuario(1, "Joao");
		Usuario carlos = new Usuario(2, "Carlos");
		Usuario bocinha = new Usuario(3, "Bocinha");

		Leilao leilao = new Leilao("Leilão 10");

		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(carlos, 200));
		leilao.propoe(new Lance(bocinha, 300));
		leilao.propoe(new Lance(bocinha, 400));
		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(carlos, 100));
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(bocinha, 200));
		leilao.propoe(new Lance(joao, 1100));

		Avaliador avaliadorLeilao = new Avaliador();
		avaliadorLeilao.avalia(leilao);

		Assert.assertEquals(1100, avaliadorLeilao.getMaiorLance(), 0.0001);
	}

	@Test
	public void devePegarMValor() {
		Usuario joao = new Usuario(1, "Joao");
		Usuario carlos = new Usuario(2, "Carlos");
		Usuario bocinha = new Usuario(3, "Bocinha");

		Leilao leilao = new Leilao("Leilão 10");

		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(carlos, 200));
		leilao.propoe(new Lance(bocinha, 300));
		leilao.propoe(new Lance(bocinha, 400));
		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(carlos, 100));
		leilao.propoe(new Lance(joao, 200));
		leilao.propoe(new Lance(bocinha, 200));
		leilao.propoe(new Lance(joao, 1100));

		Avaliador avaliadorLeilao = new Avaliador();
		avaliadorLeilao.avalia(leilao);

		Assert.assertEquals(100, avaliadorLeilao.getMenorLance(), 0.0001);
	}

	@Test
	public void deveRetornarValorMedio() {
		Usuario joao = new Usuario(1, "Joao");
		Usuario carlos = new Usuario(2, "Carlos");
		Usuario bocinha = new Usuario(3, "Bocinha");

		Leilao leilao = new Leilao("Leilão 10");

		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(carlos, 200));
		leilao.propoe(new Lance(bocinha, 300));
		leilao.propoe(new Lance(bocinha, 400));
		leilao.propoe(new Lance(joao, 100));

		Avaliador avaliadorLeilao = new Avaliador();
		avaliadorLeilao.avalia(leilao);

		Assert.assertEquals(300, avaliadorLeilao.getMediaLance(), 0.0001);
	}

}
