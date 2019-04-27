package prova;

import prova.musica.Musica;
import prova.musica.MusicaDao;
import prova.musica.Estilos;
import prova.bd.Conexao;
import java.util.List;

public class Prova {

    public static void main(String[] args) {
        
        //Musica track01 = new Musica();
        
        //track01.setTitulo("Bonde dos Solteiros");
        //track01.setEstilos(Estilos.Sertanejo);
        //track01.setArtista("Fred & Gustavo");
        //track01.setAnoLancamento(2014);
        //track01.setTamanho(2.4);
        
           
           
        Conexao conexao = new Conexao();
        conexao.conectar();
        
        MusicaDao dao = new MusicaDao(conexao);
        //dao.inserir(track01);
        //dao.remover();
        
        
        List<Musica> musicas = dao.listar();
        
        for(Musica item: musicas){
        
        System.out.println("ID: " + item.getId());
        System.out.println("Titulo: " + item.getTitulo());
        System.out.println("Artista: " + item.getArtista());
        System.out.println("Ano Lancamento: " + item.getAnoLancamento());
        System.out.println("Tamanho: " + item.getTamanho());
        //System.out.println("Gênero: " + item.getEstilo);
        System.out.println("--------------------------------------------");
                
        
        conexao.fechar();
        }
    }
    
}
