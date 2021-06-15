package model.game.animals.predatoranimals;

import controller.mission.time.TimeManager;
import model.game.animals.Animal;
import model.game.missionmodel.MissionMap;
import model.game.products.CagedPredators.CagedBear;
import model.game.products.CagedPredators.CagedLion;
import model.game.products.CagedPredators.CagedTiger;

public abstract class PredatorAnimal extends Animal {
    int cagesLeft;
    final int cagesNeeded;
    public PredatorAnimal(TimeManager timeManager,int x, int y, changes.PredatorAnimal predatorAnimal) {
        super(timeManager,predatorAnimal.animalName, x, y, predatorAnimal.speed);
        cagesNeeded = predatorAnimal.cageCommands;
        this.cagesLeft = cagesNeeded;
    }
    public boolean cageTry() {
        cagesLeft -= 1;
        if (cagesLeft == 0){
            if (this instanceof Lion) MissionMap.putProduct(new CagedLion(timeManager,x,y));
            if (this instanceof Bear) MissionMap.putProduct(new CagedBear(timeManager,x,y));
            if (this instanceof Tiger) MissionMap.putProduct(new CagedTiger(timeManager,x,y));
            return true;
        }
        return false;
    }
    public void cageBreak() {
        if (cagesLeft<cagesNeeded)
            cagesLeft += 1;
    }

    public int getCagesLeft() {
        return cagesLeft;
    }
}
