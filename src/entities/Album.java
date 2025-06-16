package entities;

import java.util.List;

import enums.MusicaGenero;

public class Album {
	private String nome;
	private String artista;
	private int anoLancamento;
	private MusicaGenero genero;
	private List<Musica> musicas;

	public Album(String nome, String artista, int anoLancamento, MusicaGenero genero, List<Musica> musicas) {
		this.nome = nome;
		this.artista = artista;
		this.anoLancamento = anoLancamento;
		this.genero = genero;
		this.musicas = musicas;
	}

	public String getNome() {
		return nome;
	}

	public String getArtista() {
		return artista;
	}

	public int getAnoLancamento() {
		return anoLancamento;
	}

	public MusicaGenero getGenero() {
		return genero;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}
}
