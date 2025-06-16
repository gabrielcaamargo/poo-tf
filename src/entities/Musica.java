package entities;

import enums.MusicaGenero;

public class Musica extends Midia {
	private double duracao;
	private int streams;

	public Musica(String nome, MusicaGenero genero, int anoLancamento, double duracao, int streams) {
		super(nome, anoLancamento, genero);
		this.duracao = duracao;
		this.streams = streams;
	}

	public double getDuracao() {
		return duracao;
	}

	public int getStreams() {
		return streams;
	}
}