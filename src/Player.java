import java.util.Scanner;

public class Player {
    private int damage;
    private int health;
    private int originalHealth;
    private int money;
    private String award = "";
    private String name;
    private String charName;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar() {
        Samuray samuray = new Samuray();
        Knight knight = new Knight();
        Archer archer = new Archer();
        System.out.println("\t\t\t\t\t\t\tKarakterler");
        System.out.println("*******************************************************************");
        System.out.println("\t\t\t\t\tID: " + samuray.getId() + "  Karakter : " + samuray.getName() + "\n" + "\t\t\t________________________________________" + "\n" + "\t|     Hasar: " + samuray.getDamage() + "    "
                + "|     Saglik: " + samuray.getHealth() + "    " + "|     Para: " + samuray.getMoney() + "     |");
        System.out.println();
        System.out.println("\t\t\t\t\tID: " + archer.getId() + "  Karakter : " + archer.getName() + "\n" + "\t\t\t________________________________________" + "\n" + "\t|     Hasar: " + archer.getDamage() + "    "
                + "|     Saglik: " + archer.getHealth() + "    " + "|     Para: " + archer.getMoney() + "     |");
        System.out.println();
        System.out.println("\t\t\t\t\tID: " + knight.getId() + "  Karakter : " + knight.getName() + "\n" + "\t\t\t________________________________________" + "\n" + "\t|     Hasar: " + knight.getDamage() + "    "
                + "|     Saglik: " + knight.getHealth() + "    " + "|     Para: " + knight.getMoney() + "      |");

        System.out.println("*******************************************************************");
        System.out.print("Bir karakter ID'si giriniz : ");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samuray());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samuray());
        }
        System.out.println("Secilen karakter: " + this.getCharName());
    }

    public void selectLoc() {

    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = this.award + " " + award;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        if (health < 0) {
            return 0;
        }
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public void printInfo() {
        System.out.println();
        System.out.println(
                " Silahiniz : " + this.getInventory().getWeapon().getName() +
                        "\n Zirhiniz : " + this.getInventory().getArmor().getName() +
                        "\n Zirh korumasi : " + this.getInventory().getArmor().getBlock() +
                        "\n Hasariniz : " + this.getTotalDamage() +
                        "\n Saglik : " + this.getHealth() +
                        "\n Para : " + this.getMoney() +
                        "\n Odulleriniz :" + this.getAward());
    }


    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
