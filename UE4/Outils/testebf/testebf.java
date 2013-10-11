// TimeStamp  (dos) : 28 Octobre 04 11:17  --  x:\testebf.java

     import java.io.* ;
     import java.lang.* ;
     import org.xml.sax.* ;
     import org.xml.sax.helpers.DefaultHandler;
     import javax.xml.parsers.SAXParserFactory;
     import javax.xml.parsers.ParserConfigurationException;
     import javax.xml.parsers.SAXParser;

     public class testebf { // Teste Si Bien Forme

       public static void main(String tab_args[]) {

           if (tab_args.length == 0) {
              System.out.println() ;
              System.out.println(" syntaxe : testbf fichier") ;
              System.exit(-1) ;
           } ; // fin de test sur le nombre de param�tres

           String nf = tab_args[0] ; // nom du fichier XML � traiter

           DefaultHandler handler = new DefaultHandler();
           SAXParserFactory factory = SAXParserFactory.newInstance();

           try { SAXParser saxParser = factory.newSAXParser();
                 saxParser.parse( new File( nf ), handler );
           } catch (Throwable t) {
                System.out.println(" Document "+nf+" mal form�.") ;
                System.out.println() ;
                System.out.println( "ereur d�tect�e :") ;
                t.printStackTrace ();
                System.exit(-1) ;
           } ; // fin de try

           System.out.println(" le document "+nf+" est bien form�.") ;

 } // fin de la m�thode main()

 } ; // fin de public class
