import java.util.ArrayList;
import java.util.List;

public class Album<T extends Artista> extends Midia {
	private T artista;
	private List<Musica> musicas;

	public Album(String nome, T artista, int anoLancamento, MusicaGenero genero) {
		super(nome, anoLancamento, genero);
		this.artista = artista;
		this.musicas = new ArrayList<Musica>();
	}

	public T getArtista() {
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

	public void addMusica(Musica musica) {
		this.musicas.add(musica);
	}

	public List<Musica> getMaisPopulares() {
		musicas.sort((Musica m1, Musica m2) -> Integer.compare(m2.getStreams(), m1.getStreams()));

		List<Musica> musicasMaisPopulares = musicas.subList(0, Math.min(5, musicas.size()));
		return musicasMaisPopulares;
	}

	public List<Musica> getMenosPopulares() {
		musicas.sort((Musica m1, Musica m2) -> Integer.compare(m1.getStreams(), m2.getStreams()));

		List<Musica> musicasMenosPopulares = musicas.subList(0, Math.min(5, musicas.size()));
		return musicasMenosPopulares;
	}

	public List<Musica> buscaMusicaPorNome(String nome) {
		return this.musicas.stream()
				.filter(musica -> musica.getNome().toLowerCase().contains(nome.toLowerCase()))
				.toList();
	}

	@Override
	public String toString() {
		return String.format(
				"Album: %s, Artista: %s, Ano: %d, Gênero: %s, Qtd. de faixas: %d, Total Streams: %d, Média Streams: %d",
				getNome(), artista.getNomeArtista(), getAnoLancamento(), getGenero(), this.getMusicas().size(),
				getTotalStreams(),
				getMediaStreams());
	}
}
