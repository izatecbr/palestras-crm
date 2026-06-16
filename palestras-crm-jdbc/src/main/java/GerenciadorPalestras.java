import java.time.LocalDate;
import java.util.List;

public class GerenciadorPalestras {
    public static void main(String[] args) {
        PalestraDAO dao  =new PalestraDAO();

        List<Palestra> palestras = dao.listar();

        for(Palestra p : palestras){
            System.out.println(p.getTitulo());
            System.out.println(p.getId());

        }
    }
}
