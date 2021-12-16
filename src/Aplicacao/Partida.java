package Aplicacao;

import java.util.Scanner;

import Exception.ErroPartida;
import Tabuleiro.TabuleiroDamas;

public class Partida {

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		Jogadores ordemJogada = new Jogadores();
		TabuleiroDamas jogadas = new TabuleiroDamas(ordemJogada);
		String inicio = "Z";

		jogadas.printarTabuleiro();

		while (!inicio.equals("B") && !inicio.equals("P")) {
			try {
				System.out.print("\nJogada inicial (P/B): ");
				inicio = sc.nextLine().toUpperCase();
				ordemJogada = new Jogadores(inicio);
				jogadas = new TabuleiroDamas(ordemJogada);
			} catch (ErroPartida erro) {
				System.out.println(erro.getMessage());
			}
		}
		
			
		while (true) {
			try {
				String posicaoPeca = "A99";
				jogadas.converterPeca(posicaoPeca);
				jogadas.identificarPeca();
				while (!jogadas.posicaoPossivel()) {
					System.out.println();
					System.out.print("Peca " + jogadas.getPecaSemCor() + " a ser movida: ");
					posicaoPeca = sc.nextLine();
					jogadas.converterPeca(posicaoPeca);
					jogadas.posicaoPeca(posicaoPeca);
				}
				String movimento = "A99";
				jogadas.converterPeca(movimento);
				while (!jogadas.verificarMovimento(movimento)) {
					System.out.print("\nMovimento do jogador " + jogadas.getPecaSemCor() + " (linha, coluna): ");
					movimento = sc.nextLine();
					jogadas.converterPeca(movimento);
					if (jogadas.movimentacaoPossivel()) {
						jogadas.colocarPeca();
					}
				}
			} catch (ArrayIndexOutOfBoundsException erroArray) {
				System.out.println("Movimentacao invalida, posicao inexistente");
			} 
		}

	}

	// num = linha
	// letra = coluna
	// 1a

}
