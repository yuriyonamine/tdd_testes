package br.com.caelum.leilao.dominio;

public class Avaliador {
	private double maiorLance = Double.NEGATIVE_INFINITY;
	private double menorLance = Double.POSITIVE_INFINITY;
	private double mediaLance = 0;

	public void avalia(Leilao leilao) {
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

		mediaLance = valorTotalLances / leilao.getLances().size();
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
