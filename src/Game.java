import java.util.Scanner;

public class Game {

    private Player player;
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hosgeldiniz!!");
        System.out.print("Lutfen bir isim giriniz : ");
        String playerName = input.nextLine();
        player = new Player(playerName);
        System.out.println("Ulu maceraci " + player.getName() + ", adaya hosgeldin!");
        System.out.println("Lutfen bir karakter seciniz..");
        player.selectChar();

        Location location = null;

        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("\t\t\t\tBolgeler");
            System.out.println("///////////////////////////////////////");
            System.out.println("1 - Guvenli Ev" + "                 \n" +
                    "     .-. _______|\n" +
                    "     |=|/     /  \\\n" +
                    "     | |_____|_\"\"_|\n" +
                    "     |_|_[ ]_|____|");
            System.out.println();
            System.out.println("2 - Market" + " \n" +
                    "      _M_\n" +
                    "      /\\-\\\n" +
                    "     _||\"|_\n" +
                    "    ~^~^~^~^");
            System.out.println();
            System.out.println("3 - Magara" + "            \n" +
                    "    /_\\   \n" +
                    "   /-.,\\--\\   \n" +
                    "  /_____\\__\\");
            System.out.println();
            System.out.println("4 - Orman" + "\n" + "      /|\\      \n" +
                    "     /*\\ \\     \n" +
                    "    /*\\ \\ \\    \n" +
                    "   / /\\/\\/ \\   \n" +
                    "      ||      \n" +
                    "      ||      ");
            System.out.println();
            System.out.println("5 - Nehir" + "\n" +
                    " ---        ---       ---     ---    ---  /\n" +
                    "      --                  --       --   /\n" +
                    "---            ---          ---       /\n" +
                    "     ----               ----        /\n" +
                    "-----         -----         ----- / \n");
            System.out.println();
            System.out.println("6 - Maden" + "  ,  /\\  .\n" +
                    " //`-||-'\\\\\n" +
                    "(| -=||=- |)\n" +
                    " \\\\,-||-.//\n" +
                    "  `  ||  '\n" +
                    "     ||\n" +
                    "     ||\n" +
                    "     ||\n" +
                    "     ||\n" +
                    "     ||\n" +
                    "     ()\n" +
                    "\n");
            System.out.println("0  -  Cikis yap");
            System.out.print("Lutfen gitmek istediginiz bolgeyi seciniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (!(isAward("Food"))) {
                        location = new Cave(player);
                        break;
                    } else {
                        System.out.println("!!Burasi temiz!!");
                        location = new SafeHouse(player);
                        break;
                    }
                case 4:
                    if (!(isAward("Firewood"))) {
                        location = new Forest(player);
                        break;
                    } else {
                        System.out.println("!!Burasi temiz!!");
                        location = new SafeHouse(player);
                        break;
                    }
                case 5:
                    if (!(isAward("Water"))) {
                        location = new River(player);
                        break;
                    } else {
                        System.out.println("!!Burasi temiz!!");
                        location = new SafeHouse(player);
                        break;
                    }
                case 6:
                    if(!(isAward("*"))){
                        location = new Mine(player);
                        break;
                    }else {
                        System.out.println("!!Burasi temiz!!");
                        location = new SafeHouse(player);
                        break;
                    }
                default:
                    System.out.println("Lutfen menu icerisinden bir sayi giriniz..");
                    location = new SafeHouse(player);
            }
            if (location == null) {
                System.out.println("\nOyundan cikis yapiliyor..");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("!GAME OVER!");
                break;
            }

            if (isWin("Food", "Firewood", "Water","*")) {
                System.out.println("\n\n\n\n\n\n00      !!OYUNU KAZANDIN!!      00");
                System.out.println("\n\n\n\n\n\n||||       TEBRIKLER       ||||");
                break;
            }

        }
    }

    public boolean isAward(String award) {
        return this.player.getAward().contains(award);
    }

    public boolean isWin(String award1, String award2, String award3,String award4) {
        if (isAward(award1) && isAward(award2) && isAward(award3) && isAward(award4)) {
            return true;
        } else {
            return false;
        }
    }
}
