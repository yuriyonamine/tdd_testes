package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao {

	private String descricao;
	private List<Lance> lances;

	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}

	public void propoe(Lance lance) {

		if (lances.isEmpty() || podeDarLance(lance.getUsuario())) {
			lances.add(lance);
		}
	}

	private boolean podeDarLance(Usuario usuario) {
		return !lances.get(lances.size() - 1).getUsuario().equals(usuario)
				&& quantidadeDeLancesDo(usuario) < 5;
	}

	private int quantidadeDeLancesDo(Usuario usuario) {
		int quantidadeLances = 0;
		for (Lance lancesContados : lances) {
			if (lancesContados.getUsuario().equals(usuario)) {
				quantidadeLances++;
			}
		}
		return quantidadeLances;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public void dobraUltimoLanceDo(Usuario usuario) {
		Lance ultimoLanceDoUsuario = ultimoLanceDo(usuario);
		propoe(new Lance(usuario, ultimoLanceDoUsuario.getValor() * 2));
	}

	private Lance ultimoLanceDo(Usuario usuario) {
		Lance ultimoLanceDoUsuario  = null;
		
		for (int index = lances.size() - 1; index >= 0; index--) {
			Lance lance = lances.get(index);

			if (lance.getUsuario().equals(usuario)) {
				ultimoLanceDoUsuario = lance;
				break;
			}
		}
		
		return ultimoLanceDoUsuario;
	}

}
