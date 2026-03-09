package presentation;

import dao.IDao;
import metier.IMetier;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Presentation2 {
    public static void main(String[] args) throws Exception {

        // On lit le nom de la classe DAO depuis le fichier de configuration
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();

        // On utilise la réflexion pour charger la classe DAO + création d'une instance par newInstance()
        Class<?> cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

        // On lit aussi ici le nom de la classe Métier depuis le fichier de configuration
        String metierClassName = scanner.nextLine();
        Class<?> cMetier = Class.forName(metierClassName);
        IMetier metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

        // Injection
        Method setDaoMethod = cMetier.getMethod("setDao", IDao.class);
        setDaoMethod.invoke(metier, dao);

        System.out.println("Résultats = " + metier.calcul());

        scanner.close();
    }
}

