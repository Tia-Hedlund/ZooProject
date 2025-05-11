public class Wood extends Item{
    public Wood(){
        setPrice(10);
    }

    public void use(Habitat habitat, Inventory inventory){
        if (inventory.upgradedWithWood(habitat)) {// eftersom metoden upgradedWithFruit kräver creature som parameter{
            habitat.upgrade();
            System.out.println(habitat.getHabitatName() + "has been upgraded to " + habitat.getHabitatLevel());
        }
    }

}
