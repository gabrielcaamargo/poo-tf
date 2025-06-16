package entities;

import enums.MusicaGenero;

public class Musica {
	private String nome;
	private double duracao;
	private MusicaGenero genero;
	private int streams;

	public Musica(String nome, double duracao, MusicaGenero genero, int streams) {
		this.nome = nome;
		this.duracao = duracao;
		this.genero = genero;
		this.streams = streams;
	}

	public String getNome() {
		return nome;
	}

	public double getDuracao() {
		return duracao;
	}

	public MusicaGenero getGenero() {
		return genero;
	}

	public int getStreams() {
		return streams;
	}
}