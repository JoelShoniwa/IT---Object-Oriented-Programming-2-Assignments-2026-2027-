package week1.kingdoms_and_quests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;
    private Quest quest;

    @BeforeEach
    void setUp() {
        player = new Player("Arthur", 100);
        quest = new Quest("Retrieve Holy Grail", "Find the relic", 200, 100);
    }

    @Test
    void testInitialStats() {
        assertEquals("Arthur", player.getName());
        assertEquals(100, player.getHealth());
        assertEquals(0, player.getGold());
        assertEquals(0, player.getExperience());
        assertTrue(player.getActiveQuests().isEmpty());
    }

    @Test
    void testAcceptQuest() {
        player.acceptQuest(quest);
        assertEquals(1, player.getActiveQuests().size());
        assertTrue(player.getActiveQuests().contains(quest));
    }

    @Test
    void testCannotAcceptDuplicateQuest() {
        player.acceptQuest(quest);
        player.acceptQuest(quest);
        assertEquals(1, player.getActiveQuests().size(), "Should not add duplicate quest to active list");
    }

    @Test
    void testFinishQuestAwardsRewards() {
        player.acceptQuest(quest);
        player.finishQuest(quest);

        assertTrue(quest.isCompleted());
        assertEquals(100, player.getGold());
        assertEquals(200, player.getExperience());
    }

    @Test
    void testCannotFinishUnacceptedQuest() {
        player.finishQuest(quest);
        assertFalse(quest.isCompleted());
        assertEquals(0, player.getGold());
        assertEquals(0, player.getExperience());
    }
}