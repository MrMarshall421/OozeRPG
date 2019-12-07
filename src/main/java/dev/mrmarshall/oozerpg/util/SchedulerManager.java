/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.util;

import dev.mrmarshall.oozerpg.OozeRPG;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class SchedulerManager {

    public void mysticalCooldown(UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (OozeRPG.getInstance().getHumanMovementSkills().getMysticalCooldown().contains(uuid)) {
                    Player p = Bukkit.getPlayer(uuid);

                    OozeRPG.getInstance().getHumanMovementSkills().getMysticalCooldown().remove(uuid);
                    p.removePotionEffect(PotionEffectType.SPEED);
                }
            }
        }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 20 * 25);
    }

    public void hatredCooldown(UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                OozeRPG.getInstance().getElfUtilitySkills().getHatredCooldown().remove(uuid);
            }
        }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 20 * 20);
    }

    public void fierceAngerCooldown(UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                OozeRPG.getInstance().getDwarfUtilitySkills().getFierceAngerCooldown().remove(uuid);
            }
        }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 20 * 8);
    }

    public void darkdepthsCooldown(UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                OozeRPG.getInstance().getDwarfUtilitySkills().getDarkdepthsCooldown().remove(uuid);
            }
        }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 20 * 9);
    }

    public void beefyCooldown(UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                OozeRPG.getInstance().getDwarfUtilitySkills().getBeefyCooldown().remove(uuid);
            }
        }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 20 * 30);
    }

    public void unbreakableCooldown(UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                OozeRPG.getInstance().getDwarfUtilitySkills().getUnbreakableCooldown().remove(uuid);
            }
        }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 20 * 40);
    }

    public void thickskinCooldown(UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                OozeRPG.getInstance().getDwarfCombatSkills().getThickskinCooldown().remove(uuid);
            }
        }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 20 * 2);
    }

    public void welltaughtCooldown(UUID uuid) {
        new BukkitRunnable() {
            @Override
            public void run() {
                OozeRPG.getInstance().getDwarfCombatSkills().getWelltaughtCooldown().remove(uuid);
            }
        }.runTaskLaterAsynchronously(OozeRPG.getInstance(), 20 * 10);
    }

    public void sleightTimer(UUID uuid, double sleightSkillBuff) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (OozeRPG.getInstance().getElfUtilitySkills().getSleightTimer().contains(uuid)) {
                    Player p = Bukkit.getPlayer(uuid);

                    if ((p.getHealth() + sleightSkillBuff) <= p.getMaxHealth()) {
                        if (!(p.getFireTicks() > 0) && !(p.hasPotionEffect(PotionEffectType.POISON) && !(p.hasPotionEffect(PotionEffectType.WITHER) && !(p.hasPotionEffect(PotionEffectType.BLINDNESS) && !(p.hasPotionEffect(PotionEffectType.CONFUSION)))))) {
                            p.setHealth(p.getHealth() + sleightSkillBuff);
                        }
                    } else if (p.getHealth() < p.getMaxHealth()) {
                        p.setHealth(p.getMaxHealth());
                    }
                } else {
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(OozeRPG.getInstance(), 20 * 2, 20 * 2);
    }
}
