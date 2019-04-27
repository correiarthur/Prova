package prova.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final String usuario = "root";
    private final String senha = "";
    private final String url = "jdbc:mariadb://localhost:3306/musica";
    private Connection conexao;

    public Connection getConexao() {
        return conexao;
    }

    public void conectar() {
        try {
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException ex) {
            System.out.println("Não foi possível conectar ao banco de dados");
        }
    }

    public void fechar() {
        try {
            this.conexao.close();
        } catch (SQLException ex) {
            System.out.println("Conexão não pode ser encerrada");
        }
    }
}