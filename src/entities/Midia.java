package entities;

import enums.MusicaGenero;

public class Midia {
	private String nome;
	private int anoLancamento;
	private MusicaGenero genero;

	public Midia(String nome, int anoLancamento, MusicaGenero genero) {
		this.nome = nome;
		this.anoLancamento = anoLancamento;
		this.genero = genero;
	}

	public String getNome() {
		return nome;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public MusicaGenero getGenero() {
		return genero;
	}
}
