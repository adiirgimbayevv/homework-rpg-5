package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero,BossEnemy boss,AttackAction action) {
        if (hero==null||boss==null||action==null) {
            return "Preparation Failed: Missing essential combat data";}
        if (!hero.isAlive()) {
            return "Preparation Failed: Hero is already defeated";}
        return "--- Preparation Complete ---\n"+"Hero: "+hero.getName()+" (HP: "+hero.getHealth()+")\n"+"Target: "+boss.getName()+"\n"+"Selected Attack: "+action.getActionName()+"\n"+"Effects Active: "+action.getEffectSummary();}
}