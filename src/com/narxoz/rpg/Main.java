package com.narxoz.rpg;

import com.narxoz.rpg.decorator.*;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        HeroProfile hero = new HeroProfile("Adi Irgimbayev", 150);
        BossEnemy boss = new BossEnemy("Malenia, Blade of Miquella", 250, 25);
        AttackAction basic = new BasicAttack("Standard Strike", 20);
        AttackAction firePoison=new FireRuneDecorator(new PoisonCoatingDecorator(basic));
        AttackAction ultimateAction=new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(basic)));

        printActionInfo("Base",basic);
        printActionInfo("Fire + Poison",firePoison);
        printActionInfo("ULTIMATE (Fire + Poison + Crit)",ultimateAction);

        System.out.println("\n---Facade Dungeon Run---");
        DungeonFacade facade =new DungeonFacade().setRandomSeed(42L);

        AdventureResult result=facade.runAdventure(hero,boss,ultimateAction);

        System.out.println("\n---Adventure Summary---");
        System.out.println("Winner: "+result.getWinner());
        System.out.println("Total Rounds: "+result.getRounds());
        System.out.println("Final Reward: "+result.getReward());

        System.out.println("\n---Detailed Battle Log---");
        for (String line:result.getLog()){
            System.out.println(">> "+line);}
        System.out.println("\n---Demo Complete---");
    }

    private static void printActionInfo(String type,AttackAction action){
        System.out.println("["+type+"] Action: "+action.getActionName());
        System.out.println("   Damage: "+action.getDamage());
        System.out.println("   Effects: "+action.getEffectSummary());
    }
}