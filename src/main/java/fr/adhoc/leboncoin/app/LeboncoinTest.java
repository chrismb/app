package fr.adhoc.leboncoin.app;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import fr.adhoc.leboncoin.utils.DbUtils;
import fr.adhoc.leboncoin.dao.impl.UtilisateurDaoImpl;
import fr.adhoc.leboncoin.dao.UtilisateurDao;
import fr.adhoc.leboncoin.model.Utilisateur;


public class LeboncoinTest {


    private static DbUtils myDbUtils;


	public LeboncoinTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Statement stmt;
		ResultSet rslt = null;
        myDbUtils = new DbUtils();
		
        stmt = myDbUtils.getStatement();//Cree un stmt pour la bd correspondant a la connexion conn

        
        String str="";
        Scanner user_input = new Scanner( System.in );
 
        //Ajout d'un nouvel utilisateur
        System.out.println("Ajout d'un utilisateur");
        //user_input = new Scanner( System.in );
        //Fixe le nom
        System.out.print("Votre nom : ");//Pour l'instant on ne teste pas si il appartient a la BD
        String U_nom = user_input.next( );
        
        //Fixe le mail
        System.out.print("Votre mail : ");//Pour l'instant on ne teste pas si il appartient a la BD
        String mail = user_input.next( );

        UtilisateurDao myUtDao = new UtilisateurDaoImpl();
        
        myUtDao.create(U_nom,mail);

        System.out.println("Liste des Utilisateurs");

        for(Utilisateur ut: myUtDao.findAll()){
            System.out.println(ut.getID() + "\t" + ut.getNom());
        }
        
    	
        myDbUtils.getConnection().close();
	}

}
