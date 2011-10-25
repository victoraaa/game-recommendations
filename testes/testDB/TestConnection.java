/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testDB;

import java.io.FileInputStream;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 *
 * @author Luisa
 */
public class TestConnection extends DatabaseTestCase{

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
       return null;
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(new FileInputStream("WEB-INF/dataset.xml"));
    }
    
}
