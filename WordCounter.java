import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class WordCounter {

    private static final Set<String> COMMON_WORDS = new HashSet<>(
            Set.of("the", "and", "is", "it", "in", "to", "of", "for", "with", "on", "this", "that", "from", "as"));

    public static void main(String[] args) {
        String text = getTextInput();
        if (text.isEmpty()) {
            System.out.println("No text entered. Exiting the program.");
            return;
        }

        Map<String, Integer> wordFrequency = countWords(text);

        System.out.println("\nWord Count Statistics:");
        System.out.println("Total words: " + wordFrequency.values().stream().mapToInt(Integer::intValue).sum());
        System.out.println("Total unique words: " + wordFrequency.size());

        
    }

    private static String getTextInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose an option:");
        System.out.println("1. Enter text manually");
        System.out.println("2. Provide a file");

        String text = "";
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice == 1) {
            System.out.print("Enter the text: ");
            text = scanner.nextLine();
        } else if (choice == 2) {
            System.out.print("Enter the file name: ");
            String filename = scanner.nextLine();
            try {
                text = readFile(filename);
            } catch (FileNotFoundException e) {
                System.out.println("File not found. Exiting the program.");
                System.exit(0);
            }
        } else {
            System.out.println("Invalid choice. Exiting the program.");
            System.exit(0);
        }
        scanner.close();
        return text;
    }

    private static String readFile(String filename) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
        }
        return sb.toString();
    }

    private static Map<String, Integer> countWords(String text) {
        String[] words = text.toLowerCase().split("[\\s.,!?;:]+");
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) {
            if (!COMMON_WORDS.contains(word)) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        return wordFrequency;
    }
}












