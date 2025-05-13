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
            // Saves the item (fruit wood or fish) that the player chose to buy in a String "boughtItem"
            String boughtItem = item.getClass().getSimpleName().toLowerCase();

            // Add the quantity bought to the total of that item
            // in the inventory Hashmap "items", the previous amount of that item + quantity, is added.
            inventory.getItems().put(boughtItem, inventory.getItems().getOrDefault(boughtItem, 0) + quantity);

            // Reduce the total price from the Zoo total money.
            // the Zoo's money is set to the difference between the Zoos previous money and the cost of the users chosen quantity.
            Zoo.setMoney(zoo.getMoney()-item.getPrice()*quantity);

            // Update storageUsed with the increaseStorageUsed method from inventory.
            inventory.increaseStorageUsed(quantity);
        }
        else{
            System.out.println("gick inte");
        }
    }


    public boolean canBuyHabitat(Zoo zoo, Habitat habitat){
        if (zoo.getMoney() >= habitat.getPrice() && !zoo.getHabitats().contains(habitat)){
            return true;
        }
        else{
            return false;
        }
    }

    public void buyHabitat(Zoo zoo, Habitat habitat){
        if(canBuyHabitat(zoo, habitat)){
            // Reduce the total price of the habitat from the Zoo's total money.
            Zoo.setMoney(zoo.getMoney()-habitat.getPrice());
            // the bought habitat is added to the Arraylist habitats in zoo.
            zoo.addHabitatToZoo(habitat);
        }
        else{
            System.out.println("gick inte att köpa habitat");
        }
    }

    // lägg till att den inte går att köpa om det inte finns en biome den kan bo i
    public boolean canBuyCreature(Zoo zoo, Creature creature, Habitat habitat){
        if (zoo.getMoney() >= creature.getPrice() && habitat.canAddCreatureToHabitat(creature, zoo)){
            return true;
        }
        else{
            return false;
        }
    }

    public void buyCreature(Zoo zoo, Creature creature, Habitat habitat){
        if(canBuyCreature(zoo, creature, habitat)){
            // Reduce the total price of the habitat from the Zoo's total money.
            Zoo.setMoney(zoo.getMoney()-creature.getPrice());
            // the bought habitat is added to the Arraylist habitats in zoo.
            zoo.addCreatureToZoo(creature);
        }
        else{
            System.out.println("gick inte att köpa creature");
        }
    }




}
