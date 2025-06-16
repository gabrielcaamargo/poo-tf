package entities;

import java.util.ArrayList;
import java.util.List;

import enums.MusicaGenero;

public class Banda extends Artista {
	private MusicaGenero genero;
	private int anoFormacao;
	private List<Artista> integrantes;
	private List<Album> albuns;

	public Banda(String nome, String nacionalidade, MusicaGenero genero, int anoFormacao,
			List<Album> albuns) {
		super(nome, nacionalidade);

		this.genero = genero;
		this.anoFormacao = anoFormacao;
		this.integrantes = new ArrayList<Artista>();
		this.albuns = albuns;
	}

	public void addIntegrante(Artista integrante) {
		this.integrantes.add(integrante);
	}

	@Override
	public String toString() {
		return String.format("Banda: %s, Nacionalidade: %s, Gênero: %s, Ano de Formação: %d, Integrantes: %s",
				getNomeArtista(), getNacionalidade(), genero, anoFormacao, integrantes);
	}

}
