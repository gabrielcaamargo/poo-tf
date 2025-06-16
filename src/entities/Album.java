package entities;

import java.util.List;

import enums.MusicaGenero;

public class Album extends Midia {
	private String artista;
	private List<Musica> musicas;

	public Album(String nome, String artista, int anoLancamento, MusicaGenero genero, List<Musica> musicas) {
		super(nome, anoLancamento, genero);
		this.artista = artista;
		this.musicas = musicas;
	}

	public String getArtista() {
		return artista;
	}

	public List<Musica> getMusicas() {
		return musicas;
	}

	public int getTotalStreams() {
		int totalStreams = 0;
		for (Musica musica : musicas) {
			totalStreams += musica.getStreams();
		}
		return totalStreams;
	}

	public int getMediaStreams() {
		if (musicas.isEmpty()) {
			return 0;
		}

		return this.getTotalStreams() / musicas.size();
	}

	public List<Musica> getMaisPopulares() {
		musicas.sort((Musica m1, Musica m2) -> Integer.compare(m2.getStreams(), m1.getStreams()));

		List<Musica> musicasMaisPopulares = musicas.subList(0, Math.min(5, musicas.size()));
		return musicasMaisPopulares;
	}
}
