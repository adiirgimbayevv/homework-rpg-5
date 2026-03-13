package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random=new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random=new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result=new AdventureResult();
        int round=0;
        while (hero.isAlive()&&boss.isAlive()&&round<15) {
            round++;

            int finalDamage=action.getDamage();
            if (random.nextInt(100)<15) {
                finalDamage*=2;
                result.addLine("!!! CRITICAL HIT !!!");}

            boss.takeDamage(finalDamage);
            result.addLine("Round "+round+": "+hero.getName()+" hits for "+finalDamage+" damage ("+action.getActionName()+").");
            if (!boss.isAlive()) break;

            int bossDmg=boss.getAttackPower();
            if (random.nextInt(100)<10){
                result.addLine("The Boss missed his attack!");
            } else {
                hero.takeDamage(bossDmg);
                result.addLine("Boss hits back for "+bossDmg+" HP.");}
        }

        result.setRounds(round);
        result.setWinner(hero.isAlive()?hero.getName():boss.getName());
        return result;}
}
