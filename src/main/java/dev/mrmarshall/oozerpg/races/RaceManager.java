/*
 * Copyright (c) 2019. MrMarshall Development. The commercial usage of this content is only allowed with an exclusive permission by MrMarshall Developments.
 */

package dev.mrmarshall.oozerpg.races;

import dev.mrmarshall.oozerpg.OozeRPG;

import java.util.UUID;

public class RaceManager {

    public void selectRace(UUID uuid, String race) {
        OozeRPG.getInstance().getPlayerDataHandler().setPlayerRace(uuid, race);
    }
}
