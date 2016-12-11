package projetobd;

import java.sql.Connection;
import java.sql.DriverManager;

public class ProjetoBD{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        Connection connection;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl",
                    "8910542",
                    "bd2016");
            IntGraf intGraf = new IntGraf(connection);
            intGraf.setVisible(true);
            
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return;
    }
    
}
