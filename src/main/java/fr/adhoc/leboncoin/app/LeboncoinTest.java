package fr.adhoc.leboncoin.app;

 
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.*;
import fr.adhoc.leboncoin.utils.DbUtils;
 
import fr.adhoc.leboncoin.service.UtilisateurService;
import fr.adhoc.leboncoin.model.Utilisateur;
 
import fr.adhoc.leboncoin.service.ProduitService;
import fr.adhoc.leboncoin.model.Produit;
 
import fr.adhoc.leboncoin.service.OffreService;
import fr.adhoc.leboncoin.model.Offre;
 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext; 


public class LeboncoinTest {

/**
* @param args
* @throws Exception
*/

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");

        UtilisateurService myUtService = (UtilisateurService)context.getBean("utilisateurService");
        ProduitService myPrService = (ProduitService)context.getBean("produitService");

        System.out.println("Liste des Utilisateurs");

        for(Utilisateur ut: myUtService.findAllUtilisateurs()){
            List<Produit> listeproduits = myPrService.findProduitsByUtilisateur(ut.getId());
            System.out.println(ut.getId() + "\t" + ut.getNom() + " : "+ listeproduits);

        }  
    }

}