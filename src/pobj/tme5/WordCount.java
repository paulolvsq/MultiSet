package pobj.tme5; 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import pobj.util.Chrono;

public class WordCount {
	/**
	 * prend en paramètre un MultiSet<String> ms qui contient des String 
	 * charge un fichier et le découpe en mots
	 * accumule les mots dans le MultiSet<String> ms passé en paramètre
	 * les extrait dans une liste puis les trie et affiche les 10 mots les plus fréquents
	 * @param MultiSet<String> ms
	 */
	public static void wordcountHashMultiSet(MultiSet<String> ms) {
		String file = "data/WarAndPeace.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				for(String word : line.split("\\P{L}+")) {
					if(word.equals("")) continue;
					else ms.add(word);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		List<String> elements = ms.elements();
		HashMultiSet<String> tmp = new HashMultiSet<String>(elements);
		HashMultiSetComparator<String> comparator = new HashMultiSetComparator<String>(tmp);
		elements.sort(comparator);
		for(int i = 0; i < 10; i++) {
			System.out.println(elements.get(i));
		}
		/*for(int i = elements.size()-1; i > elements.size()-11; i--) {
			System.out.println(elements.get(i));
		}*/
	}
	/**
	 * prend en paramètre un MultiSet<String> ms qui contient des String 
	 * charge un fichier et le découpe en mots
	 * accumule les mots dans le MultiSet<String> ms passé en paramètre
	 * les extrait dans une liste puis les trie et affiche les 10 mots les plus fréquents
	 * @param MultiSet<String> ms
	 */
	/*public static void wordcountNaiveMultiSet(MultiSet<String> ms) {
		String file = "data/WarAndPeace.txt";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while((line = br.readLine()) != null) {
				for(String word : line.split("\\P{L}+")) {
					if(word.equals("")) continue;
					else ms.add(word);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		List<String> elements = ms.elements();
		NaiveMultiSet<String> tmp = new NaiveMultiSet<String>(elements);
		NaiveMultiSetComparator<String> comparator = new NaiveMultiSetComparator<String>(tmp);
		elements.sort(comparator);
		for(int i = 0; i < 10; i++) {
			System.out.println(elements.get(i));
		}
	}*/
	/**
	 * main qui lance deux exécutions pour comparer l'efficacité de l'implémentation de deux MultiSet différents
	 * @param args
	 */
	public static void main(String[] args) {
		//HashMultiSet<String> bouquin = new HashMultiSet<String>();
		MultiSet<String> tmp = new HashMultiSet<>();
		MultiSetDecorator<String> bouquin = new MultiSetDecorator<String>(tmp);
		Chrono chrono = new Chrono();
		wordcountHashMultiSet(bouquin);
		chrono.stop();
		System.out.println(bouquin.toString());
		try {
			@SuppressWarnings("resource")
			PrintWriter writer = new PrintWriter("affichage_warandpeace_q5-2.txt", "UTF-8");
			writer.println(bouquin.toString());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		}
		/*
		Chrono chrono2 = new Chrono();
		wordcountNaiveMultiSet(new NaiveMultiSet<String>());
		chrono2.stop();
		*/
	}

}
