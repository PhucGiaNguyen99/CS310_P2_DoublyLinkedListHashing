import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthDLListTest {

    @org.junit.jupiter.api.Test
    void verifyIntegrity() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteLastFile("Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.insertFileNode("May", "Something");
        authDLList.deleteLastFile("Something");
        //authDLList.deleteLastFile("Something");
        authDLList.insertFileNode("Jun", "Something");
        authDLList.insertFileNode("Jul", "Something");
        //authDLList.deleteFirstFile("Something");
        System.out.println("Tail's digest: " + authDLList.tail.digest);
        assertTrue(AuthDLList.verifyIntegrity(authDLList, "123456789&Feb&Apr&Jun&Jul"));
    }

    @Test
    void verifyIntegrityInsertDeleteFirst() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        assertTrue(AuthDLList.verifyIntegrity(authDLList, ""));
    }

    @Test
    void verifyIntegrityInsertDeleteLast() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        //authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteLastFile("Something");
        assertTrue(AuthDLList.verifyIntegrity(authDLList, "123456789&Feb"));
    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteFirstFileForwardOneNode() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.deleteFirstFile("Something");
        assertEquals("", authDLList.toStringBackwardFile());
    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteFirstFileBackwardOneNode() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.deleteFirstFile("Something");
        assertEquals("", authDLList.toStringBackwardFile());
    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteFirstFileForwardNodes() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        assertEquals("", authDLList.toStringForwardFile());
    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteFirstFileBackwardNodes() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        assertEquals("Apr ", authDLList.toStringBackwardFile());
    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteLastFileForwardOneNode() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.deleteLastFile("Something");
        assertEquals("", authDLList.toStringForwardFile());
    }

    @org.junit.jupiter.api.Test
    void deleteLastFileBackwardOneNode() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.deleteLastFile("Something");
        assertEquals("", authDLList.toStringBackwardFile());
    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteLastFileForwardNodes() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.deleteLastFile("Something");
        authDLList.deleteLastFile("Something");
        authDLList.deleteLastFile("Something");
        authDLList.deleteLastFile("Something");
        assertEquals("", authDLList.toStringForwardFile());
    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteLastFileBackwardNodes() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.deleteLastFile("Something");
        authDLList.deleteLastFile("Something");
        authDLList.deleteLastFile("Something");
        assertEquals("Jan ", authDLList.toStringBackwardFile());
    }


    @org.junit.jupiter.api.Test
    void retrieveNodeFile() throws IntegrityCheckFailedException, FileNotFoundException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.insertFileNode("May", "Something");
        Node foundNode = AuthDLList.retrieveNodeFile(authDLList, "Something", "Jan");
        assertEquals("Jan", foundNode.file);
    }


    // PASSED
    @Test
    void insertFileNodeForFileForward() throws IntegrityCheckFailedException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.insertFileNode("May", "Something");
        assertEquals("Jan Feb Mar Apr May ", authDLList.toStringForwardFile());
    }

    // PASSED
    @Test
    // Test backward of the linked list
    void insertFileNodeForFileBackward() throws IntegrityCheckFailedException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.insertFileNode("May", "Something");
        assertEquals("May Apr Mar Feb Jan ", authDLList.toStringBackwardFile());
    }

    @Test
    void insertFileNodeForDigestForward() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.deleteLastFile("Something");
        authDLList.deleteLastFile("Something");
        authDLList.deleteLastFile("Something");
        System.out.println("Tail: " + authDLList.tail.digest);

        //authDLList.deleteLastFile("Something");
        //authDLList.insertFileNode("May", "Something");
        assertEquals("123456789&Jan ->", authDLList.toStringForwardDigest());
    }

    // PASSED
    @Test
    // Test backward of the linked list
    void insertFileNodeForDigestBackward() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.insertFileNode("May", "Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteFirstFile("Something");
        System.out.println("Tail: " + authDLList.tail.digest);
        assertEquals("Apr&May ->123456789&Apr ->", authDLList.toStringBackwardDigest());
    }

    @Test
    void testGeneralExceptVerifyIntegrity() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", "Something");
        authDLList.insertFileNode("Feb", "Something");
        authDLList.insertFileNode("Mar", "Something");
        authDLList.deleteFirstFile("Something");
        authDLList.deleteLastFile("Something");
        authDLList.insertFileNode("Apr", "Something");
        authDLList.insertFileNode("May", "Something");
        authDLList.deleteLastFile("Something");
        authDLList.deleteLastFile("Something");
        System.out.println("Tail: " + authDLList.tail.digest);
        assertEquals("123456789&Feb ->", authDLList.toStringForwardDigest());
    }
}