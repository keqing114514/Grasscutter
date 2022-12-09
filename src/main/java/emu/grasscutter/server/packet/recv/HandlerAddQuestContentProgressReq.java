package emu.grasscutter.server.packet.recv;

import emu.grasscutter.game.quest.enums.QuestContent;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.AddQuestContentProgressReqOuterClass.AddQuestContentProgressReq;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketAddQuestContentProgressRsp;
import lombok.val;

@Opcodes(PacketOpcodes.AddQuestContentProgressReq)
public class HandlerAddQuestContentProgressReq extends PacketHandler {

    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        var req = AddQuestContentProgressReq.parseFrom(payload);
        /*//Find all conditions in quest that are the same as the given one
        Stream<QuestData.QuestContentCondition> finishCond = GameData.getQuestDataMap().get(req.getParam()).getFinishCond().stream();
        Stream<QuestData.QuestContentCondition> failCond = GameData.getQuestDataMap().get(req.getParam()).getFailCond().stream();
        List<QuestData.QuestContentCondition> allCondMatch = Stream.concat(failCond,finishCond).filter(p -> p.getType().getValue() == req.getContentType()).toList();
        for (QuestData.QuestContentCondition cond : allCondMatch ) {
            session.getPlayer().getQuestManager().queueEvent(QuestContent.getContentTriggerByValue(req.getContentType()), cond.getParam());
        }*/
        val type = QuestContent.getContentTriggerByValue(req.getContentType());
        if(type!=null) {
            session.getPlayer().getQuestManager().queueEvent(type, req.getParam());
        }
        session.send(new PacketAddQuestContentProgressRsp(req.getContentType()));
    }

}
