package dominio;

import java.util.List;

public class Playlist {
  private String nome;
  private List<Musica> musicas;

  public Playlist(String nome, List<Musica> musicas) {
    this.nome = nome;
    this.musicas = musicas;
  }
  public String getNome() {
    return nome;
  }
  public void setNome(String nome) {
    this.nome = nome;
  }
  public List<Musica> getMusicas() {
    return musicas;
  }
  public void setMusicas(List<Musica> musicas) {
    this.musicas = musicas;
  }
  
}