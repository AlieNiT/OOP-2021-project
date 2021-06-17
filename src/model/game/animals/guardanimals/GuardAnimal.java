package model.game.animals.guardanimals;

import model.game.animals.Animal;

public abstract class GuardAnimal extends Animal {
    public GuardAnimal(String name,int x, int y) {
        super(null,name,x, y, 1);
    }
    public void move(int mapSize, int[] productDirection){
        if (productDirection != null){
            if (productDirection[0]!=0) x += productDirection[0]/Math.abs(productDirection[0]);
            else if(productDirection[1]!=0) y += productDirection[1]/Math.abs(productDirection[1]);
        }
        else this.move(mapSize);
    }
}
