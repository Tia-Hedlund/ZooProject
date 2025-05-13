public class Shop {
    private String name;

    public Shop(String name){
        this.name = name;
    }

    // glöm inte att fråga användare om quantity
    public boolean CanBuyItems(Zoo zoo, Buyable item, Inventory inventory, int quantity){
        if (zoo.getMoney() >= item.getPrice()*quantity && ((zoo.getMaxStorage()-inventory.getStorageUsed())>=quantity)){
            return true;
        }
        else{
            return false;
        }
    }

    public void CanBuyHabitat(Habitat habitat){
        if (zoo.getMoney() >= habitat.getPrice && )
    }

    // lägg till att den inte går att köpa om det inte finns en biome den kan bo i
    public boolean CanBuyCreature(Zoo zoo, Creature creature){
        if (zoo.getMoney() >= creature.getPrice() && !zoo.getCreatures().contains(creature)){
            return true;
        }
        else{
            return false;
        }
    }


}
