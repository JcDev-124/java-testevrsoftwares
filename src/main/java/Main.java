
import br.com.vrsoftware.dao.DaoFactory;
import br.com.vrsoftware.dao.MainDao;
import br.com.vrsoftware.view.SistemaView;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Julio
 */
public class Main {

    public static void main(String[] args) {
        try {

            SistemaView v = new SistemaView();
            v.setVisible(true);
            v.setLocationRelativeTo(null);
            MainDao dao = DaoFactory.createMainDao();
            // dao.criarEstruturaBD();
            dao.criarTabelasBD();
            dao.criarTriggerDB();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
