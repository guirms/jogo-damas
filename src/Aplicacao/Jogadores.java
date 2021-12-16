package Aplicacao;

import Exception.ErroPartida;

public class Jogadores {

	private String inicio;
	private String[] jogadas = new String[2];

	public Jogadores() {
	}

	public Jogadores(String inicio) throws ErroPartida {
		if (inicio.equals("P")) {
			this.jogadas[0] = "P";
			this.jogadas[1] = "B";
		} else if (inicio.equals("B")) {
			this.jogadas[0] = "B";
			this.jogadas[1] = "P";
		} else {
			throw new ErroPartida("Somente sao aceitas as letras P e B");
		}
	}

	public Jogadores(String[] jogadas) {
		this.jogadas = jogadas;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String[] getJogadas() {
		return jogadas;
	}

	public void setJogadas(String[] jogadas) {
		this.jogadas = jogadas;
	}

}
