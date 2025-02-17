package emu.grasscutter.game.dungeons;

import emu.grasscutter.game.dungeons.dungeon_results.BaseDungeonResult;
import emu.grasscutter.server.packet.send.PacketDungeonSettleNotify;

public class BasicDungeonSettleListener implements DungeonSettleListener {

    @Override
    public void onDungeonSettle(DungeonManager dungeonManager, BaseDungeonResult.DungeonEndReason endReason) {
        var scene = dungeonManager.getScene();
        var dungeonData = dungeonManager.getDungeonData();
        // TODO time taken and chests handling
        DungeonEndStats stats = new DungeonEndStats(scene.getKilledMonsterCount(), 0, 0, endReason);

        scene.broadcastPacket(new PacketDungeonSettleNotify(new BaseDungeonResult(dungeonData, stats)));
    }
}
