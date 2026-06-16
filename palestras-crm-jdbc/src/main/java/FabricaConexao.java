import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaConexao {
    /**
     Definimos uma variável estática para evitar
     a criar uma conexão a cada execução
     */
    private static Connection conexao;

    /**
     Este método devera ser chamado somente na inicialização do seu sistema
     através da sua classe principal (main)
     */
    public static void criarConexao(){
        String url = "jdbc:postgresql://localhost:5432/palestras-db";
        String user = "postgres";
        String password = "postgres";

        try {
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }
    public static Connection getConexao(){
        //quando chamar o método a mesma conexão sempre será retomada
        return conexao;
    }
}
