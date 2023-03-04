package SelectCategory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

public class WordGenerator {

	private static List<String> lines = null;//initialize variable
	

	public static void main(String[] args) {
		String word = WordGenerator.generate("C:\\Users\\USER\\Desktop\\GameProject\\GameProject\\src\\Animalwords.txt");
		System.out.println(word);
	}
	
	public static String generate(String path) {

		try {
			lines = Files.readAllLines(new File(path).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		return getRandomItem().toLowerCase();
	}
	public static String getRandomItem() {
		Random rand = new Random();
		int num = rand.nextInt(lines.size());// random num 0 to list size
		return lines.get(num);// get word
	}
	
	

}
