package week1.kingdoms_and_quests;

import java.util.ArrayList;
import java.util.List;

public class Kingdom {
    private String name;
    private String ruler;
    private List<Quest> questBoard;

    public Kingdom(String name, String ruler) {
        this.name = name;
        this.ruler = ruler;
        this.questBoard = new ArrayList<>();
    }

    public void postQuest(Quest quest) {
        questBoard.add(quest);
        System.out.println("New quest posted in " + name + ": " + quest.getTitle());
    }

    public List<Quest> getQuestBoard() {
        return questBoard;
    }

    public String getName() {
        return name;
    }

    public String getRuler() {
        return ruler;
    }

    public void displayAvailableQuests() {
        System.out.println("\n--- Quests Available in " + name + " ---");
        for (Quest q : questBoard) {
            if (!q.isCompleted()) {
                System.out.println("- " + q);
            }
        }
    }
}