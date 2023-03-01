import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthDLListTest {

    @org.junit.jupiter.api.Test
    void verifyIntegrity() {
    }

    @org.junit.jupiter.api.Test
    void deleteFirstFile() {
    }

    @org.junit.jupiter.api.Test
    void deleteLastFile() {
    }

    @org.junit.jupiter.api.Test
    void retrieveNodeFile() {
    }

    @Test
    void insertFileNode() {
        AuthDLList authDLList = new AuthDLList();
        System.out.println("Check the head");
        assertEquals(null, authDLList.tail);
        assertEquals(null, authDLList.head);
    }
}