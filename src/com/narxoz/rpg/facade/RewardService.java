package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult==null||battleResult.getWinner()==null) {
            return "Error, no data";}
        if (battleResult.getWinner().contains("Malenia")) {
            return "Defeat: No rewards";}
        int rounds=battleResult.getRounds();

        if (rounds<=3){
            return "Mythic Loot + 5k Gold";
        } else if (rounds<=7) {
            return "Rare Sword + 1k Gold";
        } else if (rounds<=12){
            return "Common Sack + 200 Gold";
        } else {
            return "Rusty Key + 10 Gold";
        }}
}