package emu.grasscutter.game.quest.content;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueContent;
import lombok.val;

import static emu.grasscutter.game.quest.enums.QuestContent.QUEST_CONTENT_ADD_QUEST_PROGRESS;

@QuestValueContent(QUEST_CONTENT_ADD_QUEST_PROGRESS)
public class ContentAddQuestProgress extends BaseContent {

    @Override
    public boolean execute(GameQuest quest, QuestData.QuestContentCondition condition, String paramStr, int... params) {
        val progressId = condition.getParam()[0];
        val currentCount = quest.getOwner().getQuestProgressCountMap().getOrDefault(progressId, -1);

        // if the condition count is 0 I think it is safe to assume that the
        // condition count from EXEC only needs to be 1
        return params[0] == progressId && currentCount >= condition.getCount();
    }

}
