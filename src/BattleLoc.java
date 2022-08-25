import java.util.Random;

public class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;


    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("Suan buradasiniz : " + this.getName());
        System.out.println("Dikkatli ol!!  Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yasiyor!");
        System.out.print("<S>avas veya <K>ac  :  ");
        String selectCase = input.nextLine().toUpperCase();
        selectCase = selectCase.toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getName() + " tum dusmanlari yendiniz !!");
            return true;
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Oldunuz ! :(");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        int count = 0;
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            if (getPlayer().getHealth() > 0) {
                playerStats();
                obstacleStats(i);
            }
            if(isStart()){
                System.out.println("<<<<<<<<<<" + this.getPlayer().getName() + ">>>>>>>>>>" +
                        "\n\t\t BASLIYOR");
                System.out.print("<V>ur veya <K>ac : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz !");
                    this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if(this.getObstacle().getHealth() > 0){
                        System.out.println();
                        System.out.println("Canavar vurusu gerceklestirdi !");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                } else {
                    this.getObstacle().setHealth(-5);
                }
            }else{
                System.out.println("<<<<<<<<<<" + this.getObstacle().getName() + ">>>>>>>>>>" +
                        "\n\t\t BASLIYOR");
                System.out.println();
                System.out.println("Canavar vurusu gerceklestirdi !");
                int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                if(obstacleDamage < 0){
                    obstacleDamage = 0;
                }
                this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                afterHit();
            }

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<V>ur veya <K>ac : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    System.out.println("Siz vurdunuz !");
                    this.getObstacle().setHealth(this.obstacle.getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if(this.getObstacle().getHealth() > 0){
                        System.out.println();
                        System.out.println("Canavar vurusu gerceklestirdi !");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage < 0){
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                } else {
                    return false;
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Dusmani yendiniz !!");
                count++;
                if (count == obsNumber) {
                    switch (this.getName()) {
                        case "Magara":
                            this.getPlayer().setAward(this.getAward());
                            break;
                        case "Nehir":
                            this.getPlayer().setAward(this.getAward());
                            break;
                        case "Orman":
                            this.getPlayer().setAward(this.getAward());
                            break;
                        case "Maden":
                            this.getPlayer().setAward("*");
                            randomAward();
                            break;
                    }
                }
                System.out.println();
                System.out.println(this.getAward() + " esyasi envanterinize eklendi!");
                System.out.println("\n" + this.getObstacle().getAward() + " para kazandiniz !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("\n" + "Guncel paraniz : " + this.getPlayer().getMoney());
            }
        }
        return false;
    }

    boolean isStart() {
        double randomNumber = Math.random() * 2;
        return randomNumber <= 1;
    }

    public void randomAward() {
        int randomNumber = (int) Math.random() * 99;

        if (randomNumber >= 0 && randomNumber < 15) {
            //Silah kazanma ihtimali

            int randomNumber2 = (int) Math.random() * 99;

            Weapon tufek = Weapon.getWeaponObjByID(3);
            Weapon kilic = Weapon.getWeaponObjByID(2);
            Weapon tabanca = Weapon.getWeaponObjByID(1);

            if (randomNumber2 >= 0 && randomNumber2 < 20) {
                System.out.println("Tufek kazandiniz!!");
                this.getPlayer().getInventory().setWeapon(tufek);
            }
            if (randomNumber2 >= 20 && randomNumber2 < 50) {
                System.out.println("Kilic kazandiniz!!");
                this.getPlayer().getInventory().setWeapon(kilic);
            }
            if (randomNumber2 >= 50 && randomNumber2 < 100) {
                System.out.println("Tabanca kazandiniz!!");
                this.getPlayer().getInventory().setWeapon(tabanca);
            }
        }
        if (randomNumber >= 15 && randomNumber < 30) {
            //Zirh kazanma ihtimali

            int randomNumber2 = (int) Math.random() * 99;

            Armor hafif = Armor.getArmorObjByID(1);
            Armor orta = Armor.getArmorObjByID(2);
            Armor agir = Armor.getArmorObjByID(3);

            if (randomNumber2 >= 0 && randomNumber2 < 20) {
                System.out.println("Agir zirh kazandiniz!!");
                this.getPlayer().getInventory().setArmor(agir);
            }
            if (randomNumber2 >= 20 && randomNumber2 < 50) {
                System.out.println("Orta zirh kazandiniz!!");
                this.getPlayer().getInventory().setArmor(orta);
            }
            if (randomNumber2 >= 50 && randomNumber2 < 100) {
                System.out.println("Hafif zirh kazandiniz!!");
                this.getPlayer().getInventory().setArmor(hafif);
            }
        }
        if (randomNumber >= 30 && randomNumber < 55) {
            //Para kazanma ihtimali

            int randomNumber2 = (int) Math.random() * 99;

            if (randomNumber2 >= 0 && randomNumber2 < 20) {
                System.out.println("10 para kazandiniz!!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            }
            if (randomNumber2 >= 20 && randomNumber2 < 50) {
                System.out.println("5 para kazandiniz!!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            }
            if (randomNumber2 >= 50 && randomNumber2 < 100) {
                System.out.println("1 para kazandiniz!!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            }
        }
        if (randomNumber >= 55 && randomNumber < 100) {
            // Hicbir sey kazanmama ihtimali

            System.out.println("Maalesef hicbir sey kazanmadiniz..");
        }

    }

    private void afterHit() {
        System.out.println("Caniniz : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Cani : " + this.getObstacle().getHealth());
        System.out.println();
    }

    private void obstacleStats(int i) {
        System.out.println();
        System.out.println(i + "." + this.getObstacle().getName() + " Degerleri");
        System.out.println("----------------------------");
        System.out.println("Saglik : " + this.getObstacle().getHealth());
        System.out.println("Hasar  : " + this.getObstacle().getDamage());
        System.out.println("Odul : " + this.getObstacle().getAward());
    }

    private void playerStats() {
        System.out.println();
        System.out.println("Oyuncu Degerleri");
        System.out.println("----------------------------");
        System.out.println("Saglik : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zirh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
