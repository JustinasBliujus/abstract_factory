package facade;
import factories.*;
import interfaces.Character;
import java.util.*;

public class GameFacade {
    private HumanFactory humanFactory;
    private OrcFactory orcFactory;
    private List<Character> playerParty;
    private List<Character> enemyParty;
    private Random random;
    
    public GameFacade() {
        this.humanFactory = new HumanFactory();
        this.orcFactory = new OrcFactory();
        this.playerParty = new ArrayList<>();
        this.enemyParty = new ArrayList<>();
        this.random = new Random();
    }
    
    public Character createHero(String name, String faction, String characterClass) {
        System.out.println("=== Creating Hero ===");
        
        GameFactory factory = getFactory(faction);
        Character character = createCharacter(factory, characterClass, name);
        
        equipCharacter(character, faction);
        
        playerParty.add(character);
        System.out.println("Hero created and equipped: " + character.getName());
        return character;
    }
    
    public Character createEnemy(String name, String faction, String characterClass) {
        System.out.println("=== Creating Enemy ===");
        
        GameFactory factory = getFactory(faction);
        Character character = createCharacter(factory, characterClass, name);
        
        equipCharacter(character, faction);
        
        enemyParty.add(character);
        System.out.println("Enemy created and equipped: " + character.getName());
        return character;
    }
    
    public String quickBattle() {
        System.out.println("\n=== Quick Battle ===");
        
        if (playerParty.isEmpty() || enemyParty.isEmpty()) {
            return "Cannot battle: Need both player and enemy characters!";
        }
        
        Character player = getRandomAliveCharacter(playerParty);
        Character enemy = getRandomAliveCharacter(enemyParty);
        
        if (player == null || enemy == null) {
            return "Cannot battle: No alive characters available!";
        }
        
        System.out.println("Battle: " + player.getName() + " vs " + enemy.getName());
        
        int rounds = 0;
        while (player.isAlive() && enemy.isAlive() && rounds < 10) {
            rounds++;
            System.out.println("\n--- Round " + rounds + " ---");

            if (player.isAlive()) {
                player.attack(enemy);
                if (!enemy.isAlive()) {
                    System.out.println(player.getName() + " wins!");
                    enemyParty.remove(enemy);
                    return player.getName() + "defeated " + enemy.getName();
                }
            }
            
            if (enemy.isAlive()) {
                enemy.attack(player);
                if (!player.isAlive()) {
                    System.out.println(enemy.getName() + " wins!");
                    playerParty.remove(player);
                    return enemy.getName() + "defeated " + player.getName();
                }
            }
        }
        return "Battle ended in a draw after " + rounds + " rounds!";
    }

    public void showPartyStatus() {
        System.out.println("\n=== Party Status ===");
        
        System.out.println("PLAYER PARTY:");
        if (playerParty.isEmpty()) {
            System.out.println("No heroes in party");
        } else {
            for (Character character : playerParty) {
                String status = character.isAlive() ? "Alive" : "Dead";
                System.out.println("  " + character.getName() + " - HP: " + 
                                 Math.max(0, character.getHealth()) + " " + status);
            }
        }
        
        System.out.println("\nENEMY PARTY:");
        if (enemyParty.isEmpty()) {
            System.out.println("No enemies in party");
        } else {
            for (Character character : enemyParty) {
                String status = character.isAlive() ? "Alive" : "Dead";
                System.out.println("  " + character.getName() + " - HP: " + 
                                 Math.max(0, character.getHealth()) + " " + status);
            }
        }
    }
    
    public void quickSetup() {
        System.out.println("=== Quick Setup ===");
        
        createHero("Aragorn", "Human", "Warrior");
        createHero("Gandalf", "Human", "Mage");
        createEnemy("Uruk", "Orc", "Warrior");
        createEnemy("Shaman", "Orc", "Mage");
        
        System.out.println("Quick setup complete! Ready for battle!");
    }
    
    public void autoBattleSequence() {
        System.out.println("\n=== Auto Battle Sequence ===");
        
        int battleCount = 0;
        while (!playerParty.isEmpty() && !enemyParty.isEmpty() && battleCount < 5) {
            battleCount++;
            System.out.println("\n--- Battle " + battleCount + " ---");
            String result = quickBattle();
            System.out.println("Result: " + result);
            showPartyStatus();
        }
        
        if (playerParty.isEmpty()) {
            System.out.println("\nENEMIES WON!");
        } else if (enemyParty.isEmpty()) {
            System.out.println("\nHEROES WON!");
        } else {
            System.out.println("\nA DRAW...");
        }
    }
     
    private GameFactory getFactory(String faction) {
        switch (faction.toLowerCase()) {
            case "human":
                return humanFactory;
            case "orc":
                return orcFactory;
            default:
                return humanFactory; 
        }
    }
    
    private Character createCharacter(GameFactory factory, String characterClass, String name) {
        switch (characterClass.toLowerCase()) {
            case "warrior":
                return factory.createWarrior(name);
            case "mage":
                return factory.createMage(name);
            default:
                return factory.createWarrior(name); 
        }
    }
    
    private void equipCharacter(Character character, String faction) {
        System.out.println("  Equipping " + character.getName() + " with " + faction + " gear...");
    }
    
    private Character getRandomAliveCharacter(List<Character> party) {
        List<Character> aliveCharacters = new ArrayList<>();
        for (Character character : party) {
            if (character.isAlive()) {
                aliveCharacters.add(character);
            }
        }
        
        if (aliveCharacters.isEmpty()) {
            return null;
        }
        
        return aliveCharacters.get(random.nextInt(aliveCharacters.size()));
    }
    
    public List<Character> getPlayerParty() { return new ArrayList<>(playerParty); }
    public List<Character> getEnemyParty() { return new ArrayList<>(enemyParty); }
}
