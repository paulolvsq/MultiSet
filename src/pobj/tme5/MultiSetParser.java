package pobj.tme5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiSetParser {
	
	public static MultiSet<String> parse(String nom) throws InvalidMultiSetFormat {
		MultiSet<String> tmp = new HashMultiSet<>();
		MultiSet<String> decorateur = new MultiSetDecorator<>(tmp);
		int numeroLigne = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(nom));
			String ligne;
			while((ligne = br.readLine()) != null) {
				numeroLigne++;
				String[] stock = ligne.split(":", 2);
				decorateur.add(stock[0], Integer.parseInt(stock[1]));
			}
			br.close();
		} catch (FileNotFoundException f) {
			throw new InvalidMultiSetFormat("Erreur : fichier non trouvé. Vérifiez que le chemin entré est valide.", f.getCause());
		} catch (IOException ioe) {
			throw new InvalidMultiSetFormat("Erreur : format de l'entrée invalide. Veuillez vérifier si l'entrée respecte le schéma : clé:valeur à  la ligne "+numeroLigne, ioe.getCause());
		} catch (NumberFormatException nfe) {
			throw new InvalidMultiSetFormat("Erreur : il faut un entier (Integer) dans la ligne : "+numeroLigne, nfe.getCause());
		} catch (Exception e) {
			throw new InvalidMultiSetFormat("Erreur : format du MultiSet invalide, vérifiez la ligne "+numeroLigne, e.getCause());
		}
		return decorateur;
	}

}
