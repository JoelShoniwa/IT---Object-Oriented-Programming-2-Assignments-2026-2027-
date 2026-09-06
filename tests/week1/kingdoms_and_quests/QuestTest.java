package week1.kingdoms_and_quests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestTest {

    private Quest quest;

    @BeforeEach
    void setUp() {
        quest = new Quest("Defeat Goblin King", "Clear the cavern", 150, 75);
    }

    @Test
    void testInitialState() {
        assertEquals("Defeat Goblin King", quest.getTitle());
        assertEquals("Clear the cavern", quest.getDescription());
        assertEquals(150, quest.getExperienceReward());
        assertEquals(75, quest.getGoldReward());
        assertFalse(quest.isCompleted(), "Quest should start uncompleted");
    }

    @Test
    void testCompleteQuest() {
        quest.completeQuest();
        assertTrue(quest.isCompleted(), "Quest status should be completed after calling completeQuest()");
    }
}