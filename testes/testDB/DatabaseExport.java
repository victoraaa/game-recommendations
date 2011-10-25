/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testDB;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author Luisa
 */
public class DatabaseExport {
    public static void main(String[] args) throws Exception
    {
        // database connection
        Class driverClass = Class.forName("org.postgresql.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:postgresql://comp13.redecasd.ita.br/findyourgameDB", "comp13", "xupafolgado");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("dataset.xml"));
        
    }
}
