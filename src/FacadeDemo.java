import facade.GameFacade;

public class FacadeDemo {
    
    public static void main(String[] args) {
        GameFacade gameFacade = new GameFacade();
        
        gameFacade.createHero("Arthur", "Human", "Warrior");
        gameFacade.createHero("Merlin", "Human", "Mage");
        gameFacade.createEnemy("Goblin", "Orc", "Warrior");
        gameFacade.createEnemy("Witch", "Orc", "Mage");
        
        gameFacade.showPartyStatus();
        System.out.println();

        String battleResult = gameFacade.quickBattle();
        System.out.println("Battle Result: " + battleResult);
        System.out.println();
        
        GameFacade quickGame = new GameFacade();
        quickGame.quickSetup(); 
        System.out.println();
        
        quickGame.autoBattleSequence();
        System.out.println();
    }
}
