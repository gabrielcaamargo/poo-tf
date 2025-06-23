import java.util.ArrayList;
import java.util.List;

public class Artista {
	private String nomeArtista;
	private String nacionalidade;
	private String funcao;
	private List<Album> albuns;

	public Artista(String nomeArtista, String nacionalidade, String funcao) {
		this.nomeArtista = nomeArtista;
		this.nacionalidade = nacionalidade;
		this.funcao = funcao;
		this.albuns = new ArrayList<>();
	}

	public Artista(String nomeArtista, String nacionalidade) {
		this.nomeArtista = nomeArtista;
		this.nacionalidade = nacionalidade;
		this.albuns = new ArrayList<>();
	}

	public String getNomeArtista() {
		return nomeArtista;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public String getFuncao() {
		return funcao;
	}

	public List<Album> getAlbuns() {
		return albuns;
	}

	public void addAlbum(Album album) {
		this.albuns.add(album);
	}

	@Override
	public String toString() {
		return String.format("Artista: %s, Nacionalidade: %s, Função: %s", nomeArtista, nacionalidade, funcao);
	}
}
