package week1.kingdoms_and_quests;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int health;
    private int gold;
    private int experience;
    private List<Quest> activeQuests;

    public Player(String name, int health) {
        this.name = name;
        this.health = health;
        this.gold = 0;
        this.experience = 0;
        this.activeQuests = new ArrayList<>();
    }

    public void acceptQuest(Quest quest) {
        if (!activeQuests.contains(quest)) {
            activeQuests.add(quest);
            System.out.println(name + " accepted the quest: " + quest.getTitle());
        }
    }

    public void finishQuest(Quest quest) {
        if (activeQuests.contains(quest) && !quest.isCompleted()) {
            quest.completeQuest();
            this.gold += quest.getGoldReward();
            this.experience += quest.getExperienceReward();
            System.out.println(name + " finished: " + quest.getTitle() +
                    " (+" + quest.getGoldReward() + " gold, +" + quest.getExperienceReward() + " XP)");
        }
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public int getExperience() {
        return experience;
    }

    public List<Quest> getActiveQuests() {
        return activeQuests;
    }

    @Override
    public String toString() {
        return String.format("Player: %s | HP: %d | Gold: %d | XP: %d | Active Quests: %d",
                name, health, gold, experience, activeQuests.size());
    }
}