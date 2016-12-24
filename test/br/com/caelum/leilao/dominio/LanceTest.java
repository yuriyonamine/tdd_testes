package br.com.caelum.leilao.dominio;

import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;

public class LanceTest {
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarLanceComValorZero(){
		Usuario yudi = new Usuario("Yudi");
		
		new CriadorDeLeilao().para("Playstation 3")
				.lance(yudi, 0)
				.constroi();
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAceitarLanceNegativos(){
		Usuario yudi = new Usuario("Yudi");
		
		new CriadorDeLeilao().para("Playstation 3")
				.lance(yudi, -5)
				.constroi();
		
	}
}
