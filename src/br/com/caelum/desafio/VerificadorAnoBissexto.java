package br.com.caelum.desafio;

public class VerificadorAnoBissexto {

	public boolean isAnoBissexto(int ano) {
		if(ano % 400 ==0 ){
			return true;
		}else if(ano % 100 != 0 && ano % 4 ==0){
			return true;
		}
		return false;
	}

}
