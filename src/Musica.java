public class Musica extends Midia {
	private int duracao;
	private int streams;

	public Musica(String nome, MusicaGenero genero, int anoLancamento, int duracao, int streams) {
		super(nome, anoLancamento, genero);
		this.duracao = duracao;
		this.streams = streams;
	}

	public int getStreams() {
		return streams;
	}

	public String duracaoEmMinutos() {
		int minutos = duracao / 60;
		int segundos = duracao % 60;
		return String.format("%d:%02d", minutos, segundos);
	}

	@Override
	public String toString() {
		return String.format("Musica: %s, Duração: %s, Gênero: %s, Ano: %d, Streams: %d",
				getNome(), duracaoEmMinutos(), getGenero(), getAnoLancamento(), streams);
	}

}