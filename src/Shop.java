public class Shop {
    private String name;

    public Shop(String name){
        this.name = name;
    }

    // glöm inte att fråga användare om quantity
    public boolean canBuyItems(Zoo zoo, Buyable item, Inventory inventory, int quantity){
        if (zoo.getMoney() >= item.getPrice()*quantity && ((zoo.getMaxStorage()-inventory.getStorageUsed())>=quantity)){
            return true;
        }
        else{
            return false;
        }
    }

    public void buyItems(Zoo zoo, Buyable item, Inventory inventory, int quantity){
        if (canBuyItems(zoo, item, inventory, quantity)){


        }
    }


    public boolean CanBuyHabitat(Zoo zoo, Habitat habitat){
        if (zoo.getMoney() >= habitat.getPrice() && !zoo.getHabitats().contains(habitat)){
            return true;
        }
        else{
            return false;
        }
    }

    // lägg till att den inte går att köpa om det inte finns en biome den kan bo i
    public boolean CanBuyCreature(Zoo zoo, Creature creature, Habitat habitat){
        if (zoo.getMoney() >= creature.getPrice() && habitat.canAddCreatureToHabitat(creature, zoo)){
            return true;
        }
        else{
            return false;
        }
    }




}
