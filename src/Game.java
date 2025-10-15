import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import factories.GameFactory;
import factories.HumanFactory;
import factories.OrcFactory;
import interfaces.Character;

public class Game {
    private Scanner scanner;
    private GameFactory factory;
    private List<Character> playerCharacters;
    private List<Character> enemyCharacters;
    
    public Game() {
        this.scanner = new Scanner(System.in);
        this.playerCharacters = new ArrayList<>();
        this.enemyCharacters = new ArrayList<>();
    }
    
    public void startGame() {
        System.out.println("Choose your faction and create your warriors to fight!");
        System.out.println();
        
        while (true) {
            showMainMenu();
            int choice = getIntInput("Enter your choice (1-5): ");
            
            switch (choice) {
                case 1:
                    createPlayerCharacter();
                    break;
                case 2:
                    createEnemyCharacter();
                    break;
                case 3:
                    battleCharacters();
                    break;
                case 4:
                    showGameInfo();
                    break;
                case 5:
                    System.out.println("Thanks for playing! Farewell, warrior!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
    
    private void showMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create Player Character");
        System.out.println("2. Create Enemy Character");
        System.out.println("3. Battle Characters");
        System.out.println("4. Game Information");
        System.out.println("5. Exit Game");
        System.out.println();
    }
    
    private void createPlayerCharacter() {
        System.out.println("\n=== Character Creation ===");

        System.out.println("Choose your faction:");
        System.out.println("1. Human Kingdom");
        System.out.println("2. Orc Tribes");
        
        int factionChoice = getIntInput("Enter faction choice (1-2): ");
        
        if (factionChoice == 1) {
            factory = new HumanFactory();
            System.out.println("You chose the Human Kingdom!");
        } else if (factionChoice == 2) {
            factory = new OrcFactory();
            System.out.println("You chose the Orc Tribes!");
        } else {
            System.out.println("Invalid choice! Returning to main menu.");
            return;
        }
        
        System.out.println("\nChoose character class:");
        System.out.println("1. Warrior (High health, strong attack)");
        System.out.println("2. Mage (Lower health, magical attack)");
        
        int classChoice = getIntInput("Enter class choice (1-2): ");
        String characterName = getStringInput("Enter character name: ");
        
        Character character;
        if (classChoice == 1) {
            character = factory.createWarrior(characterName);
            System.out.println("\nWarrior created successfully!");
        } else if (classChoice == 2) {
            character = factory.createMage(characterName);
            System.out.println("\nMage created successfully!");
        } else {
            System.out.println("Invalid choice! Returning to main menu.");
            return;
        }
        
        playerCharacters.add(character);
        displayCharacterInfo(character);
        System.out.println("Character added!");
    }
    
    private void createEnemyCharacter() {
        System.out.println("\n=== Enemy Character Creation ===");
        
        System.out.println("Choose enemy faction:");
        System.out.println("1. Human Kingdom");
        System.out.println("2. Orc Tribes");
        
        int factionChoice = getIntInput("Enter faction choice (1-2): ");
        
        if (factionChoice == 1) {
            factory = new HumanFactory();
            System.out.println("You chose Human enemies!");
        } else if (factionChoice == 2) {
            factory = new OrcFactory();
            System.out.println("You chose Orc enemies!");
        } else {
            System.out.println("Invalid choice! Returning to main menu.");
            return;
        }
        
        System.out.println("\nChoose enemy class:");
        System.out.println("1. Warrior (High health, strong attack)");
        System.out.println("2. Mage (Lower health, magical attack)");
        
        int classChoice = getIntInput("Enter class choice (1-2): ");
        String characterName = getStringInput("Enter enemy name: ");
        
        Character character;
        if (classChoice == 1) {
            character = factory.createWarrior(characterName);
            System.out.println("\nEnemy Warrior created successfully!");
        } else if (classChoice == 2) {
            character = factory.createMage(characterName);
            System.out.println("\nEnemy Mage created successfully!");
        } else {
            System.out.println("Invalid choice! Returning to main menu.");
            return;
        }
        
        enemyCharacters.add(character);
        displayCharacterInfo(character);
        System.out.println("Enemy added!");
    }
    
    private void battleCharacters() {
        System.out.println("\n=== Battle Arena ===");
        
        List<Character> alivePlayers = getAliveCharacters(playerCharacters);
        List<Character> aliveEnemies = getAliveCharacters(enemyCharacters);
        
        if (alivePlayers.isEmpty()) {
            System.out.println("You need to create at least one alive player character first!");
            return;
        }
        
        if (aliveEnemies.isEmpty()) {
            System.out.println("You need to create at least one alive enemy character first!");
            return;
        }
        
        System.out.println("Select your character:");
        for (int i = 0; i < alivePlayers.size(); i++) {
            System.out.println((i + 1) + ". " + alivePlayers.get(i).getName() + 
                             " (HP: " + alivePlayers.get(i).getHealth() + ")");
        }
        
        int playerChoice = getIntInput("Enter player choice (1-" + alivePlayers.size() + "): ") - 1;
        if (playerChoice < 0 || playerChoice >= alivePlayers.size()) {
            System.out.println("Invalid choice!");
            return;
        }      
        
        System.out.println("\nSelect enemy character:");
        for (int i = 0; i < aliveEnemies.size(); i++) {
            System.out.println((i + 1) + ". " + aliveEnemies.get(i).getName() + 
                             " (HP: " + aliveEnemies.get(i).getHealth() + ")");
        }
        
        int enemyChoice = getIntInput("Enter enemy choice (1-" + aliveEnemies.size() + "): ") - 1;
        if (enemyChoice < 0 || enemyChoice >= aliveEnemies.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        Character player = alivePlayers.get(playerChoice);
        Character enemy = aliveEnemies.get(enemyChoice);
        
        System.out.println("\nBattle Setup:");
        displayCharacterInfo(player);
        System.out.println();
        displayCharacterInfo(enemy);
        System.out.println();

        System.out.println("=== BATTLE! ===");
        interactiveBattle(player, enemy);
    }
    
    private void interactiveBattle(Character player, Character enemy) {
        int round = 1;
        
        while (player.isAlive() && enemy.isAlive() && round <= 20) {
            System.out.println("\n--- Round " + round + " ---");
            System.out.println("Current Status:");
            System.out.println(player.getName() + " HP: " + Math.max(0, player.getHealth()) + 
                             " | " + enemy.getName() + " HP: " + Math.max(0, enemy.getHealth()));
            System.out.println();
            
            if (player.isAlive()) {
                System.out.println("Your turn, " + player.getName() + "!");
                System.out.println("1. Attack");
                System.out.println("2. Check Status");
                System.out.println("3. Surrender");
                
                int action = getIntInput("Choose your action (1-3): ");
                
                switch (action) {
                    case 1:
                        player.attack(enemy);
                        System.out.println();
                        break;
                    case 2:
                        System.out.println("\n=== " + player.getName() + " Status ===");
                        displayCharacterInfo(player);
                        System.out.println("\n=== " + enemy.getName() + " Status ===");
                        displayCharacterInfo(enemy);
                        continue; 
                    case 3:
                        System.out.println(player.getName() + " surrenders!");
                        System.out.println(enemy.getName() + " wins the battle!");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
                
                if (!enemy.isAlive()) {
                    System.out.println(enemy.getName() + " has been defeated!");
                    System.out.println(player.getName() + " wins!");
                    break;
                }
            }
            
            if (enemy.isAlive()) {
                System.out.println(enemy.getName() + "'s turn!");
                System.out.println("Press Enter to continue...");
                scanner.nextLine(); 
                enemy.attack(player);
                System.out.println();
                
                if (!player.isAlive()) {
                    System.out.println(player.getName() + " has been defeated!");
                    System.out.println(enemy.getName() + " wins the battle!");
                    break;
                }
            }
            
            round++;
        }
        
        if (round > 20) {
            System.out.println("The battle ended in a draw after 20 rounds!");
        }
        
        System.out.println("\n=== BATTLE ENDED ===");
        
        if (!player.isAlive()) {
            playerCharacters.remove(player);
            System.out.println(player.getName() + " has been removed (defeated).");
        }
        if (!enemy.isAlive()) {
            enemyCharacters.remove(enemy);
            System.out.println(enemy.getName() + " has been removed (defeated).");
        }
        
        System.out.println("Press Enter to return to main menu...");
        scanner.nextLine();
    }
    
    private List<Character> getAliveCharacters(List<Character> characters) {
        List<Character> aliveCharacters = new ArrayList<>();
        for (Character character : characters) {
            if (character.isAlive()) {
                aliveCharacters.add(character);
            }
        }
        return aliveCharacters;
    }
    
    private void displayCharacterInfo(Character character) {
        System.out.println("Character: " + character.getName());
        System.out.println("Description: " + character.getDescription());
        System.out.println("Health: " + character.getHealth());
        System.out.println("Attack Power: " + character.getAttack());
        System.out.println("Defense: " + character.getDefense());
    }
    
    private void showGameInfo() {
        System.out.println("\n=== Game Information ===");
        System.out.println();
        System.out.println("- HumanFactory creates human characters with steel weapons and armor");
        System.out.println("- OrcFactory creates orc characters with bone/club weapons and hide armor");
        System.out.println();
        System.out.println("Character Types:");
        System.out.println("- Warriors: High health, strong melee attacks");
        System.out.println("- Mages: Lower health, magical attacks");
        System.out.println();
        System.out.println("Equipment is randomly assigned based on the faction");
        System.out.println();
    }
    
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
            return getIntInput(prompt);
        }
    }
    
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
