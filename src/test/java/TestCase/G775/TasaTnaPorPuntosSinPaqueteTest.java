package TestCase.G775;

import Config.Accion;
import Config.BaseTest;
import Tools.SQLDatabaseConnection;
import Tools.Screenshot;
import Tools.Tna;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class TasaTnaPorPuntosSinPaqueteTest extends BaseTest {

    String NroEntrevista = "1283719";


    @BeforeTest
    public void IniciarSimulacion() throws InterruptedException {

        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Accion accion = new Accion ( driver );

        //Inicio Como usuario de Plataforma
        bd.CambiarUsuario ( "SERPILLOE" );

        //Logueamos
        accion.login ().Ingresar ( "QA" );

        //Menu Ejecutar
        accion.menu ().Ejecutar ();
        //Abrir BandejaTareas
        accion.ejecutar ().Programa ( "hxwf900" );

        //Iniciamos simulacion para entrevista generada
        accion.bandejaTareas ().ejecutarEntrevista ( NroEntrevista );


    }

    //Cliente tiene paquete BPN PLus Existente

    @Test
    public void TestSinPaquete() throws InterruptedException, IOException {
        Thread.sleep ( 3000 );
        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Accion accion = new Accion ( driver );
        Screenshot screenshot = new Screenshot ( driver );

        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevista );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 0 -> CA. COMUN = Sin paquete
        accion.simulacion ().Paquete ( paquetes.get ( 0 ) );
        Thread.sleep ( 3000 );


        double ptosGenerales = 0.0; //Sin Paquete
        double ptosParticulares = 0.0; //Sin Paquete
        double tnaSimulado = Double.parseDouble ( accion.simulacion ().Tna () );

        System.out.println ( "Tasa Simulada :" + accion.simulacion ().Tna () );
        System.out.println ( "Tasa Base :" + tnaBase );
        System.out.println ( "Puntos Grales:" + ptosGenerales );
        System.out.println ( "Puntos Particulares:" + ptosParticulares );
        screenshot.takeScreenshot ( "C:\\Users\\floresnes\\Downloads\\TestSinPaquete.jpg" );

        Assert.assertTrue ( tnaBase == tnaSimulado );

    }

    @Test
    public void TestConPaqueteConPtosParticulares() throws InterruptedException, IOException {
        Thread.sleep ( 3000 );
        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Accion accion = new Accion ( driver );
        Screenshot screenshot = new Screenshot ( driver );

        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevista );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 3 -> BPN SELECTO
        accion.simulacion ().Paquete ( paquetes.get ( 3 ) );
        Thread.sleep ( 5000 );

        String sqlPuntosGrales = "select JBNYC5TABO from JBNYC5 where JBNYC5Desc='" + paquetes.get ( 3 ) + "'";
        String sqlPuntosParticulares = "select BNQFCD3Bon from  BNQFCD3 where BNQFCD3PQT=3 and BNQFCD3Top=201";

        double ptosGenerales = Double.parseDouble ( bd.getValue ( sqlPuntosGrales ) );
        double ptosParticulares = Double.parseDouble ( bd.getValue ( sqlPuntosParticulares ) );
        double tnaSimulado = Double.parseDouble ( accion.simulacion ().Tna () );

        System.out.println ( "Tasa Simulada :" + accion.simulacion ().Tna () );
        System.out.println ( "Tasa Base :" + tnaBase );
        System.out.println ( "Puntos Grales:" + ptosGenerales );
        System.out.println ( "Puntos Particulares:" + ptosParticulares );
        screenshot.takeScreenshot ( "C:\\Users\\floresnes\\Downloads\\TestConPaqueteConPtosParticulares.jpg" );


        Assert.assertTrue ( tnaSimulado == tnaBase - ptosParticulares );

    }

    @Test
    public void TestConPaquetePuntosSinPtoParticulares() throws InterruptedException, IOException {
        Thread.sleep ( 3000 );
        //Instanciamos clases que usaremos
        SQLDatabaseConnection bd = new SQLDatabaseConnection ();
        Accion accion = new Accion ( driver );
        Screenshot screenshot = new Screenshot ( driver );

        //Linea 309/201
        //TNA BASE = 73
        Tna tna = new Tna ();
        double tnaBase = tna.getTnaEntrevista ( "309", "201", NroEntrevista );


        //Lista de Paquete
        List<String> paquetes = Arrays.asList ( "CA. COMUN", "BPN CLASICO", "BPN PLUS",
                                                "BPN SELECTO", "BPN UNICO", "BPN UNICO +" );

        //Elegimos Paquete 4 -> BPN UNICO
        accion.simulacion ().Paquete ( paquetes.get ( 4 ) );
        Thread.sleep ( 5000 );

        String sqlPuntosGrales = "select JBNYC5TABO from JBNYC5 where JBNYC5Desc='" + paquetes.get ( 4 ) + "'";
        String sqlPuntosParticulares = "select BNQFCD3Bon from  BNQFCD3 where BNQFCD3PQT=2 and BNQFCD3Top=201";

        double ptosGenerales = Double.parseDouble ( bd.getValue ( sqlPuntosGrales ) );
        double ptosParticulares = Double.parseDouble ( bd.getValue ( sqlPuntosParticulares ) );
        double tnaSimulado = Double.parseDouble ( accion.simulacion ().Tna () );

        System.out.println ( "Tasa Simulada :" + accion.simulacion ().Tna () );
        System.out.println ( "Tasa Base :" + tnaBase );
        System.out.println ( "Puntos Grales:" + ptosGenerales );
        System.out.println ( "Puntos Particulares:" + ptosParticulares );
        screenshot.takeScreenshot ( "C:\\Users\\floresnes\\Downloads\\TestConPaquetePuntosSinPtoParticulares.jpg" );

        Assert.assertTrue ( tnaSimulado == tnaBase - ptosGenerales );

    }
}
