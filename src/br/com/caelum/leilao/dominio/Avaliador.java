package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Avaliador {
	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;
	private double mediaLance = 0;

	public void avalia(Leilao leilao) {	
		
		if(leilao.getLances().isEmpty()){
            throw new RuntimeException("Não é possível avaliar um leilão sem lances");
		}
		
		double valorTotalLances = 0;
		for (Lance lance : leilao.getLances()) {
			if (lance.getValor() > maiorLance) {
				maiorLance = lance.getValor();
			}

			if (lance.getValor() < menorLance) {
				menorLance = lance.getValor();
			}
			valorTotalLances += lance.getValor();
		}

		calculaMediaLances(leilao, valorTotalLances);
		pegaOsMaioresNo(leilao);
	}

	private void calculaMediaLances(Leilao leilao, double valorTotalLances) {
		mediaLance = valorTotalLances / leilao.getLances().size();
	}

	private void pegaOsMaioresNo(Leilao leilao) {
		maiores = new ArrayList<Lance>(leilao.getLances());
		Collections.sort(maiores, new Comparator<Lance>() {
			public int compare(Lance o1, Lance o2) {
				if(o1.getValor() < o2.getValor()) return 1;
				if(o1.getValor() > o2.getValor()) return -1;
				return 0;
			}
		});
		maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
	}

	public List<Lance> getTresMaiores() {
		return this.maiores;
	}

	public double getMediaLance() {
		return mediaLance;
	}

	public double getMaiorLance() {
		return maiorLance;
	}

	public double getMenorLance() {
		return menorLance;
	}


}
