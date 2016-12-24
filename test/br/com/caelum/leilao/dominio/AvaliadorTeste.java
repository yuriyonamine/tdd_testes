package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class AvaliadorTeste {

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");

		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 450.0));
		leilao.propoe(new Lance(joao, 120.0));
		leilao.propoe(new Lance(maria, 700.0));
		leilao.propoe(new Lance(joao, 630.0));
		leilao.propoe(new Lance(maria, 230.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void devePegarMaiorValorEmOrdemCrescente() {
		Usuario joao = new Usuario(1, "Joao");
		Usuario carlos = new Usuario(2, "Carlos");
		Usuario bocinha = new Usuario(3, "Bocinha");

		Leilao leilao = new Leilao("Boneca");

		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(carlos, 200));
		leilao.propoe(new Lance(bocinha, 300));
		leilao.propoe(new Lance(bocinha, 400));
		leilao.propoe(new Lance(joao, 1100));

		Avaliador avaliadorLeilao = new Avaliador();
		avaliadorLeilao.avalia(leilao);

		assertEquals(1100, avaliadorLeilao.getMaiorLance(), 0.0001);
	}

	@Test
	public void devePegarMenorValorEmOrdemCrescente() {
		Usuario joao = new Usuario(1, "Joao");
		Usuario carlos = new Usuario(2, "Carlos");
		Usuario bocinha = new Usuario(3, "Bocinha");

		Leilao leilao = new Leilao("Shampoo");

		leilao.propoe(new Lance(joao, 100));
		leilao.propoe(new Lance(carlos, 200));
		leilao.propoe(new Lance(bocinha, 300));
		leilao.propoe(new Lance(bocinha, 400));
		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(joao, 1100));

		Avaliador avaliadorLeilao = new Avaliador();
		avaliadorLeilao.avalia(leilao);

		assertEquals(100, avaliadorLeilao.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
		Usuario joao = new Usuario("Joao");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 400.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(joao, 200.0));
		leilao.propoe(new Lance(maria, 100.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void devePegarApenasUmLance() {
		Usuario joao = new Usuario(1, "Joao");

		Leilao leilao = new Leilao("Carro");

		leilao.propoe(new Lance(joao, 500));

		Avaliador avaliadorLeilao = new Avaliador();
		avaliadorLeilao.avalia(leilao);

		assertEquals(500, avaliadorLeilao.getMenorLance(), 0.0001);
		assertEquals(500, avaliadorLeilao.getMaiorLance(), 0.0001);
	}

	@Test
	public void deveRetornarValorMedio() {
		Usuario joao = new Usuario(1, "Joao");
		Usuario carlos = new Usuario(2, "Carlos");
		Usuario bocinha = new Usuario(3, "Bocinha");

		Leilao leilao = new Leilao("Mini game");

		leilao.propoe(new Lance(joao, 500));
		leilao.propoe(new Lance(carlos, 200));
		leilao.propoe(new Lance(bocinha, 300));
		leilao.propoe(new Lance(carlos, 400));
		leilao.propoe(new Lance(joao, 100));

		Avaliador avaliadorLeilao = new Avaliador();
		avaliadorLeilao.avalia(leilao);

		assertEquals(300, avaliadorLeilao.getMediaLance(), 0.0001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.00001);
		assertEquals(300, maiores.get(1).getValor(), 0.00001);
		assertEquals(200, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		Usuario joao = new Usuario("João");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
	}

	@Test
	public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(0, maiores.size());
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new Leilao("Video Game");

		Usuario usuario = new Usuario("Quero");
		leilao.propoe(new Lance(usuario, 1000));
		leilao.propoe(new Lance(usuario, 2000));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		assertEquals(1000, leilao.getLances().get(0).getValor(), 0.0001);
		assertEquals(1, leilao.getLances().size());

	}

	@Test
	public void naoDeveAceitarMaisQueCincoLancesDeUmMesmoUsuario() {
		Leilao leilao = new Leilao("Video Game");

		Usuario romero = new Usuario("Romero");
		Usuario steve = new Usuario("Steve");

		leilao.propoe(new Lance(romero, 1000));
		leilao.propoe(new Lance(steve, 1000));
		leilao.propoe(new Lance(romero, 2000));
		leilao.propoe(new Lance(steve, 2000));
		leilao.propoe(new Lance(romero, 3000));
		leilao.propoe(new Lance(steve, 3000));
		leilao.propoe(new Lance(romero, 4000));
		leilao.propoe(new Lance(steve, 4000));
		leilao.propoe(new Lance(romero, 5000));
		leilao.propoe(new Lance(steve, 5000));
		leilao.propoe(new Lance(romero, 6000));

		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);

		int ultimoLanceIndex = leilao.getLances().size() - 1;
		assertEquals(10, leilao.getLances().size());
		assertEquals(5000, leilao.getLances().get(ultimoLanceIndex).getValor(),
				0.0001);
	}

	@Test
	public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new Leilao("Preisteichu");

		Usuario brito = new Usuario("Brito");
		Usuario junior = new Usuario("Junior");

		leilao.propoe(new Lance(brito, 15000));
		leilao.propoe(new Lance(junior, 20000));
		leilao.dobraUltimoLanceDo(brito);

		Lance ultimoLance = leilao.getLances().get(
				leilao.getLances().size() - 1);

		assertEquals(3, leilao.getLances().size());
		assertEquals(30000, ultimoLance.getValor(), 0.0001);
	}
}
