package week1.kingdoms_and_quests;

public class Quest {
    private String title;
    private String description;
    private int experienceReward;
    private int goldReward;
    private boolean completed;

    public Quest(String title, String description, int experienceReward, int goldReward) {
        this.title = title;
        this.description = description;
        this.experienceReward = experienceReward;
        this.goldReward = goldReward;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getExperienceReward() {
        return experienceReward;
    }

    public int getGoldReward() {
        return goldReward;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void completeQuest() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return String.format("Quest: %s | Status: %s | XP: %d | Gold: %d",
                title, (completed ? "Completed" : "Pending"), experienceReward, goldReward);
    }
}