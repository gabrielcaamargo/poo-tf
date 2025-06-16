package entities;

public class Musica {
	private String nome;
	private double duracao;

	public Musica(String nome, double duracao) {
		this.nome = nome;
		this.duracao = duracao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}