package interfaces;

public interface Character {
    String getName();
    int getHealth();
    int getAttack();
    int getDefense();
    void attack(Character target);
    void takeDamage(int damage);
    boolean isAlive();
    String getDescription();
}
