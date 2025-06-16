package entities;

import java.util.List;

public class Artista {
	private String nome;
	private String nacionalidade;
	private int anoNascimento;
	private List<Album> albuns;

	public Artista(String nome, String nacionalidade, int anoNascimento, List<Album> albuns) {
		this.nome = nome;
		this.nacionalidade = nacionalidade;
		this.anoNascimento = anoNascimento;
		this.albuns = albuns;
	}

	public String getNome() {
		return nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public int getAnoNascimento() {
		return anoNascimento;
	}

	public List<Album> getAlbuns() {
		return albuns;
	}

	public void addAlbum(Album album) {
		this.albuns.add(album);
	}
}
