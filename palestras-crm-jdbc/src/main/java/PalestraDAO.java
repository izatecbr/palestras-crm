import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PalestraDAO {
    private Connection conexao;

    public PalestraDAO() {
        FabricaConexao.criarConexao();
        this.conexao = FabricaConexao.getConexao();
    }

    public void salvar(Palestra palestra) {

        String sql = "INSERT INTO palestra (titulo, palestrante, data) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, palestra.getTitulo());
            stmt.setString(2, palestra.getPalestrante());
            stmt.setObject(3, palestra.getData()); // LocalDate funciona direto no JDBC moderno

            stmt.executeUpdate();

            System.out.println("Palestra salva com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar palestra: " + e.getMessage());
        }
    }

    public List<Palestra> listar() {

        String sql = "SELECT id, titulo, palestrante, data FROM palestra";

        List<Palestra> palestras = new ArrayList<>();

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Palestra p = new Palestra();

                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setPalestrante(rs.getString("palestrante"));
                p.setData(rs.getObject("data", java.time.LocalDate.class));

                palestras.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar palestras: " + e.getMessage());
        }

        return palestras;
    }
}
