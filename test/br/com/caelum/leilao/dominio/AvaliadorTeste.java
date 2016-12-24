package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class AvaliadorTeste {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario maria;
	private Usuario yudi;

	@Before
	public void setUp() {
		leiloeiro = new Avaliador();
		maria = new Usuario("Maria");
		joao = new Usuario("João");
		yudi = new Usuario("Yudi");
		
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao,200.0)
				.lance(maria, 450.0)
				.lance(joao, 120.0)
				.lance(maria, 700.0)
				.lance(joao, 630)
				.lance(maria, 230.0)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void devePegarMaiorValorEmOrdemCrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100)
				.lance(maria, 200)
				.lance(yudi, 300)
				.lance(yudi, 400)
				.lance(joao, 1100)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(1100, leiloeiro.getMaiorLance(), 0.0001);
	}

	@Test
	public void devePegarMenorValorEmOrdemCrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100)
				.lance(maria, 200)
				.lance(yudi, 300)
				.lance(yudi, 400)
				.lance(joao, 500)
				.lance(joao, 1100)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(100, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 400.0)
				.lance(maria, 300.0)
				.lance(joao, 200.0)
				.lance(maria, 100.0)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void devePegarApenasUmLance() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 500)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(500, leiloeiro.getMenorLance(), 0.0001);
		assertEquals(500, leiloeiro.getMaiorLance(), 0.0001);
	}

	@Test
	public void deveRetornarValorMedio() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 500)
				.lance(maria, 200)
				.lance(yudi, 300)
				.lance(maria, 400)
				.lance(joao, 100)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(300, leiloeiro.getMediaLance(), 0.0001);
	}

	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.lance(joao, 300.0)
				.lance(maria, 400.0)
				.constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.00001);
		assertEquals(300, maiores.get(1).getValor(), 0.00001);
		assertEquals(200, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 100.0)
				.lance(maria, 200.0)
				.constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 1000)
				.lance(joao, 2000)
				.constroi();

		leiloeiro.avalia(leilao);

		assertEquals(1000, leilao.getLances().get(0).getValor(), 0.0001);
		assertEquals(1, leilao.getLances().size());

	}

	@Test
	public void naoDeveAceitarMaisQueCincoLancesDeUmMesmoUsuario() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 1000)
				.lance(maria, 1000)
				.lance(joao, 2000)
				.lance(maria, 2000)
				.lance(joao, 3000)
				.constroi();
		
		leilao.propoe(new Lance(maria, 3000));
		leilao.propoe(new Lance(joao, 4000));
		leilao.propoe(new Lance(maria, 4000));
		leilao.propoe(new Lance(joao, 5000));
		leilao.propoe(new Lance(maria, 5000));
		leilao.propoe(new Lance(joao, 6000));

		leiloeiro.avalia(leilao);

		int ultimoLanceIndex = leilao.getLances().size() - 1;
		assertEquals(10, leilao.getLances().size());
		assertEquals(5000, leilao.getLances().get(ultimoLanceIndex).getValor(),
				0.0001);
	}

	@Test
	public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(joao, 15000)
				.lance(maria, 20000)
				.constroi();
		
		leilao.dobraUltimoLanceDo(joao);

		Lance ultimoLance = leilao.getLances().get(
				leilao.getLances().size() - 1);

		assertEquals(3, leilao.getLances().size());
		assertEquals(30000, ultimoLance.getValor(), 0.0001);
	}

	@Test
	public void naoDeveDobrarSemLancesAnteriores() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.constroi();
		
		leilao.dobraUltimoLanceDo(joao);

		assertEquals(0, leilao.getLances().size());
	}

	@Test
	public void naoDeveDobrarQuandoForUmLanceSeguidoDoMesmoUsuario() {
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(yudi, 5000)
				.constroi();
		
		leilao.dobraUltimoLanceDo(yudi);

		Lance ultimoLance = leilao.getLances().get(
				leilao.getLances().size() - 1);
		assertEquals(1, leilao.getLances().size());
		assertEquals(5000, ultimoLance.getValor(), 0.0001);

	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeilaoSemLances(){
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.constroi();
		
		leiloeiro.avalia(leilao);
	}
}
