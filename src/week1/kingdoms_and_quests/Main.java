package week1.kingdoms_and_quests;

public class Main {
    public static void main(String[] args) {
        Kingdom eldorado = new Kingdom("Eldorado", "King Roderick");

        Quest dragonQuest = new Quest("Slay the Mountain Dragon", "Clear the beast from the northern pass", 250, 500);
        Quest herbQuest = new Quest("Gather Moonflowers", "Harvest 10 flowers for the apothecary", 50, 80);

        eldorado.postQuest(dragonQuest);
        eldorado.postQuest(herbQuest);

        eldorado.displayAvailableQuests();

        System.out.println();
        Player adventurer = new Player("Sir Galahad", 100);
        adventurer.acceptQuest(dragonQuest);
        adventurer.finishQuest(dragonQuest);

        System.out.println("\n" + adventurer);
    }
}