public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Magaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println();
        System.out.println("\t\t\t\t\tMagazaya Hosgeldin!");
        System.out.println();
        System.out.println("Bakiye : " + this.getPlayer().getMoney());
        boolean showMenu = true;
        while (showMenu) {
            System.out.println();
            System.out.println("1   -   Silahlar");
            System.out.println("2   -   Zirhlar");
            System.out.println("0   -   Cikis yap");
            System.out.print("Seciniz  : ");
            int selectCase = Location.input.nextInt();
            while (selectCase < 0 || selectCase > 2) {
                System.out.println("Gecersiz deger girdiniz, lutfen tekrar deneyin..");
                selectCase = Location.input.nextInt();
            }
            switch (selectCase) {
                case 0:
                    System.out.println("\nTekrar bekleriz :)");
                    showMenu = false;
                    break;
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("\t\t\t\t\t\tSilahlar");
        for (Weapon w : Weapon.weapons()) {
            System.out.println("|ID : " + w.getId() + "|   |Ismi : " + w.getName() + "|" + "\t|Fiyat : " + w.getPrice() + "|  |Hasar :  " + w.getDamage() + "|");
        }
        System.out.println("0  -  Cikis yap");
    }

    public void buyWeapon() {
        System.out.print("\nBir silah seciniz : ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.println("Gecersiz deger, lutfen tekrar deneyin..");
            selectWeaponID = input.nextInt();
        }
        if (selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println();
                    System.out.println("Bakiye yetersiz !");
                } else {
                    System.out.println();
                    System.out.println(selectedWeapon.getName() + " silahini basariyla satin aldiniz !");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Kalan bakiye : " + this.getPlayer().getMoney());
                }
            }
        }
    }


    public void printArmor() {
        System.out.println("Zirhlar");
        for (Armor a : Armor.armors()) {
            System.out.println("|ID : " + a.getId() + "|   |Ismi : " + a.getName() + "|" + "\t|Fiyat : " + a.getPrice() + "|  |Koruma :  " + a.getBlock() + "|");
        }
        System.out.println("0  -  Cikis yap");
    }

    public void buyArmor() {
        System.out.print("Bir zirh seciniz : ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.println("Gecersiz deger, lutfen tekrar deneyin..");
            selectArmorID = input.nextInt();
        }

        if (selectArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Bakiye yetersiz ! ");
                } else {
                    System.out.println(selectedArmor.getName() + " zirhini basariyla satin aldiniz !");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Kalan bakiye : " + this.getPlayer().getMoney());
                }
            }
        }

    }
}
