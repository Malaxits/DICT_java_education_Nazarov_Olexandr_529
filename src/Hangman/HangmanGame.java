package Hangman;

import java.util.*;

public class HangmanGame {
    public static void main(String[] args) {
        System.out.println("HANGMAN");
        System.out.println("The game will be available soon.");

        List<String> words = Arrays.asList("python", "java", "php", "javascript");
        boolean play = true;

        while (play) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Type \"play\" to play the game, \"exit\" to quit:\n>");
            String menu = scanner.nextLine().toLowerCase();

            while (!menu.equals("play") && !menu.equals("exit")) {
                System.out.print("Type \"play\" to play the game, \"exit\" to quit:\n>");
                menu = scanner.nextLine().toLowerCase();
            }

            if (menu.equals("play")) {
                String word = getRandomWord(words);
                List<Character> rememberedLetters = new ArrayList<>();
                List<Character> inputLetters = new ArrayList<>();
                Set<Character> allLetters = new HashSet<>();
                for (char c : word.toCharArray()) {
                    allLetters.add(c);
                }

                int life = 8;

                while (life > 0) {
                    String guessedWord = getGuessedWord(word, rememberedLetters);
                    System.out.println(guessedWord);
                    System.out.print("Input a letter: ");
                    char letter = scanner.nextLine().toLowerCase().charAt(0);

                    if (Character.isLetter(letter)) {
                        if (letterInWord(letter, word) && !rememberedLetters.contains(letter) && !inputLetters.contains(letter)) {
                            rememberedLetters.add(letter);
                            allLetters.remove(letter);
                        } else if (rememberedLetters.contains(letter) || inputLetters.contains(letter)) {
                            System.out.println("You've already guessed this letter");
                        } else {
                            System.out.println("That letter doesn't appear in the word");
                            life--;
                        }
                    } else {
                        System.out.println("Please enter a valid letter");
                    }

                    if (allLetters.isEmpty()) {
                        System.out.println("Congratulations! You guessed the word: " + word);
                        break;
                    }
                }

                if (life == 0) {
                    System.out.println("You ran out of attempts. The correct word was: " + word);
                }
            } else if (menu.equals("exit")) {
                play = false;
            }
        }
    }

    private static String getRandomWord(List<String> words) {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    private static String getGuessedWord(String word, List<Character> rememberedLetters) {
        return word.chars()
                .mapToObj(c -> rememberedLetters.contains((char) c) ? (char) c : '_')
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    private static boolean letterInWord(char letter, String word) {
        return word.indexOf(letter) != -1;
    }
}
