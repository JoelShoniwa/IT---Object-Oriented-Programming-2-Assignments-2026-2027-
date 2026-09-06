package week1.kingdoms_and_quests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KingdomTest {

    private Kingdom kingdom;
    private Quest quest;

    @BeforeEach
    void setUp() {
        kingdom = new Kingdom("Camelot", "King Arthur");
        quest = new Quest("Slay the Hydra", "Defeat the monster in the swamp", 300, 250);
    }

    @Test
    void testInitialDetails() {
        assertEquals("Camelot", kingdom.getName());
        assertEquals("King Arthur", kingdom.getRuler());
        assertTrue(kingdom.getQuestBoard().isEmpty());
    }

    @Test
    void testPostQuest() {
        kingdom.postQuest(quest);
        assertEquals(1, kingdom.getQuestBoard().size());
        assertEquals(quest, kingdom.getQuestBoard().get(0));
    }
}