package prova.musica;

import prova.bd.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

public class MusicaDao {

    Conexao conexao;

    public MusicaDao(Conexao conexao) {
        this.conexao = conexao;
    }
    
    public void remover(int id){
        String sql = "DELETE FROM musica WHERE id = ?";
        try{
            PreparedStatement cursor = conexao.getConexao().prepareStatement(sql);
            cursor.setInt(1, id);
            cursor.executeUpdate();
            cursor.close();
            
        }catch(SQLException ex){
            System.out.println("Não foi possível remover o registro");
        }
    }
    
    public List<Musica> listar(){
        List<Musica> lista = new ArrayList();
        
        String sql = "SELECT * FROM musica";
        
        try{
            PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();
            
            while(resultado.next()){
                Musica linha = new Musica();
                linha.setId(resultado.getInt("id"));
                linha.setTitulo(resultado.getString("titulo"));
                linha.setArtista(resultado.getString("artista"));
                linha.setAnoLancamento(resultado.getInt("ano_lancamento"));
		linha.setTamanho(resultado.getDouble("tamanho"));
                switch (resultado.getString("estilo")) {
                    case "Sertanejo":
                        linha.setEstilo(Estilos.Sertanejo);
                        break;
                    case "Eletronica":
                        linha.setEstilo(Estilos.Eletronica);
                        break;
                    case "Rock":
                        linha.setEstilo(Estilos.Rock);
                        break;
                    default:
                        linha.setEstilo(Estilos.Gospel);
                }
                lista.add(linha);
            }
            resultado.close();
            pst.close();
            
        }catch(SQLException ex){
            System.out.println("Não foi possível listar as músicas");
        }
        
        return lista;
    }
    
    public void atualizar(Musica musica, int id){
        String sql = "UPDATE musica SET titulo = ?, estilo = ?,"
                + "artista = ?, tamanho = ?, data_lancamento = ?,"
                + "WHERE id = ?";
        
        try{
            PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
            pst.setString(1, musica.getTitulo());            
            pst.setString(2, musica.getArtista());
            pst.setInt(3, musica.getAnoLancamento());
            pst.setDouble(4, musica.getTamanho());
            pst.setString(5, musica.getEstilo().toString());
            pst.setInt(6, id);
            
            pst.executeUpdate();
        }catch(SQLException ex){
            System.out.println("Não foi possível atualizar");
        }
    }
    
    public Musica ler(int id) {
        Musica musica = new Musica();

        String sql = "SELECT * FROM musica WHERE id = ?";

        try {
            PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next() == true) {
                musica.setId(rs.getInt("id"));
                musica.setTitulo(rs.getString("titulo"));
                musica.setArtista(rs.getString("artista"));		
		musica.setAnoLancamento(rs.getInt("ano_lancamento"));
                musica.setTamanho(rs.getDouble("tamanho"));
                switch (rs.getString("estilo")) {
                    case "Sertanejo":
                        musica.setEstilo(Estilos.Sertanejo);
                        break;
                    case "Eletronica":
                        musica.setEstilo(Estilos.Eletronica);
                        break;
                    case "Rock":
                        musica.setEstilo(Estilos.Rock);
                        break;
                    default:
                        musica.setEstilo(Estilos.Gospel);
                }
            } else {
                System.out.println("Música não encontrada");
            }
            pst.close();
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Erro ao selecionar a música");
        }
        return musica;
    }

    public void inserir(Musica musica) {
        try {
            String sql = "INSERT INTO musica"
                    + "(titulo, artista, ano_lancamento, tamanho, estilo)"
                    + "values (?, ?, ?, ?, ?)";

            PreparedStatement pst = conexao.getConexao().prepareStatement(sql);
            pst.setString(1, musica.getTitulo());
            pst.setString(2, musica.getArtista());            
            pst.setInt(3, musica.getAnoLancamento());
            pst.setDouble(4, musica.getTamanho());
            pst.setString(5, musica.getEstilo().toString());
            pst.executeUpdate();

            pst.close();

        } catch (SQLException ex) {
            System.out.println("Erro ao inserir a música");
        }
    }

   
    

}
