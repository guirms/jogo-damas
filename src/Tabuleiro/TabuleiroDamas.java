package Tabuleiro;

import java.text.Normalizer;

import Aplicacao.Jogadores;
import Exception.ErroPartida;

public class TabuleiroDamas extends Jogadores {

	private Integer linhaTabuleiro;
	private Integer colunaTabuleiro;
	private Integer linhaTabuleiroPeca;
	private Integer colunaTabuleiroPeca;
	private Integer numJogadas = 0;
	private Integer numMovimentos = 0;
	private String peca;
	private String pecaSemCor;
	private String[][] tabuleiro = new String[8][9];
	private static final String ANSI_RESET = "\u001B[0m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	private String letrasTabuleiro = ANSI_BLUE_BACKGROUND + "  A B C D E F G H" + ANSI_RESET;
	private String pecaAmarela = ANSI_YELLOW + "B" + ANSI_RESET;
	private String pecaPreta = "P";
	private String traco = "-";
	private String alfabeto = "abcdefghijklmnopqrstuvwxyz";
	Jogadores ordemJogada;

	public TabuleiroDamas() {
	}

	public TabuleiroDamas(Jogadores ordemJogada) {
		this.ordemJogada = ordemJogada;
	}

	public String getPecaSemCor() {
		return pecaSemCor;
	}

	public void getTabuleiro() throws InterruptedException, ArrayIndexOutOfBoundsException {
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[i].length; j++) {
				if (!(tabuleiro[i][j] == pecaAmarela || tabuleiro[i][j] == pecaPreta || tabuleiro[i][j] == traco)) {
					if (j == 0) {
						this.tabuleiro[i][j] = ANSI_BLUE_BACKGROUND + Integer.toString(8 - i) + ANSI_RESET;
					} else if ((i == 0 || i == 2) && j % 2 == 0) {
						this.tabuleiro[i][j] = pecaAmarela;
					} else if (i == 1 && j % 2 != 0) {
						this.tabuleiro[i][j] = pecaAmarela;
					} else if ((i == 5 || i == 7) && j % 2 == 0) {
						this.tabuleiro[i][j] = pecaPreta;
					} else if (i == 6 && j % 2 != 0) {
						this.tabuleiro[i][j] = pecaPreta;
					} else {
						this.tabuleiro[i][j] = "-";
					}
				}
			}
		}
	}

	public void printarTabuleiro() throws ArrayIndexOutOfBoundsException, InterruptedException {
		System.out.print("\033[H\033[2J");
		System.out.flush();
		getTabuleiro();
		System.out.println(letrasTabuleiro);
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[0].length; j++) {
				System.out.print(tabuleiro[i][j] + " ");
			}
			System.out.println(" ");
		}
	}

	public void converterPeca(String movimento) throws ArrayIndexOutOfBoundsException {
		if (verificarErroDigitacao(movimento)) {
			String[] semEspaco = movimento.split(" ");
			int numeroMovimento = Integer.parseInt(movimento.replaceAll("[\\D]", ""));
			this.linhaTabuleiro = 8 - numeroMovimento;
			String stringConvertido = Normalizer.normalize(semEspaco[0], Normalizer.Form.NFD);
			String letraMovimento = stringConvertido.replaceAll("[^a-zA-Z ]", "").toLowerCase();
			this.colunaTabuleiro = alfabeto.indexOf(letraMovimento) + 1;
		} else {
		}
	}

	public void posicaoPeca(String movimento) throws ArrayIndexOutOfBoundsException {
		if (verificarErroDigitacao(movimento)) {
			String[] semEspaco = movimento.split(" ");
			int numeroMovimento = Integer.parseInt(movimento.replaceAll("[\\D]", ""));
			this.linhaTabuleiroPeca = 8 - numeroMovimento;
			String stringConvertido = Normalizer.normalize(semEspaco[0], Normalizer.Form.NFD);
			String letraMovimento = stringConvertido.replaceAll("[^a-zA-Z ]", "").toLowerCase();
			this.colunaTabuleiroPeca = alfabeto.indexOf(letraMovimento) + 1;
		}
		else {
			System.out.println("Erro de digitação encontrado, digite novamente");
		}
		
	}

	public boolean verificarMovimento(String movimento) {
		if ((movimento.replaceAll("[^a-zA-Z]", "").length() == 0) || !movimento.matches(".*\\d.*")) {
			return false;
		} else if (colunaTabuleiro < 0 || colunaTabuleiro > 7) {
			return false;
		} else if (linhaTabuleiro < 0 || linhaTabuleiro > 7) {
			return false;
		} else if (peca == pecaAmarela && ((linhaTabuleiro - linhaTabuleiroPeca != 1)
				|| (Math.abs(colunaTabuleiro - colunaTabuleiroPeca) != 1))) {
			return false;
		} else if (peca == pecaPreta && ((linhaTabuleiro - linhaTabuleiroPeca != -1)
				|| (Math.abs(colunaTabuleiro - colunaTabuleiroPeca) != 1))) {
			return false;
		} else {
			return true;
		}
	}

	public boolean movimentacaoPossivel() {
		String erro = "Movimentacao invalida";
		if (!verificarMovimento("a1")) {
			System.out.println(erro);
			return false;
		} else {
			return true;
		}
	}

	public boolean primeiraJogada() {
		if (numMovimentos == 0 && ((peca == pecaAmarela && linhaTabuleiroPeca != 2)
				|| (peca == pecaPreta && linhaTabuleiroPeca != 5))) {
			System.out.println("Peca inicial incorreta");
			return true;
		} else {
			return false;
		}
	}

	public boolean posicaoPossivel() throws InterruptedException, ArrayIndexOutOfBoundsException {
		int result = 0;
		if (linhaTabuleiro == -91) {
		} else if (posicaoImpossivel()) {
			if (tabuleiro[linhaTabuleiro][colunaTabuleiro] != peca) {
				System.out.println("Posicao invalida, nenhuma peca " + pecaSemCor + " econtrada");
			} else if (primeiraJogada()) {
			} else {
				result = 1;
			}
		} else {
			System.out.println("Posicao inexistente");
		}
		if (result == 1) {
			return true;
		} else {
			return false;
		}

	}

	public boolean posicaoImpossivel() {
		if ((colunaTabuleiro < 0 || colunaTabuleiro > 7) || (linhaTabuleiro < 0 || colunaTabuleiro > 7)) {
			return false;
		} else {
			return true;
		}
	}

	public void identificarPeca() throws InterruptedException, ArrayIndexOutOfBoundsException {
		getTabuleiro();
		this.peca = ordemJogada.getJogadas()[numJogadas];
		this.pecaSemCor = peca;
		if (peca == "B") {
			this.peca = ANSI_YELLOW + "B" + ANSI_RESET;
		}
		if (numJogadas > 1) {
			this.numJogadas = 0;
		}
	}

	public void colocarPeca() throws ArrayIndexOutOfBoundsException, InterruptedException {
		this.numMovimentos = 1;
		this.numJogadas += 1;
		getTabuleiro();
		this.tabuleiro[linhaTabuleiroPeca][colunaTabuleiroPeca] = traco;
		this.tabuleiro[linhaTabuleiro][colunaTabuleiro] = peca;
		printarTabuleiro();
	}

	public boolean verificarErroDigitacao(String mensagem) {
		if (mensagem.replaceAll("[^a-zA-Z]", "").length() == 0 || !mensagem.matches(".*\\d.*")) {
			return false;
		} else {
			return true;
		}
	}
}
