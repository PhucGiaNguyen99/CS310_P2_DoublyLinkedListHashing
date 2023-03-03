import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthDLListTest {

    @Test
    void verifyIntegrityWhenLinkedListIsEmpty() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", null);
        authDLList.insertFileNode("Feb", Hashing.cryptHash("123456789&Jan"));
        authDLList.deleteLastFile(Hashing.cryptHash(Hashing.cryptHash("123456789&Jan") + "&" + "Feb"));
        authDLList.deleteFirstFile(Hashing.cryptHash("123456789&Jan"));
        assertTrue(AuthDLList.verifyIntegrity(authDLList, null));
    }

    @org.junit.jupiter.api.Test
    void verifyIntegrity() throws IntegrityCheckFailedException, EmptyDLListException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", null);
        authDLList.insertFileNode("Feb", Hashing.cryptHash("123456789&Jan"));
        authDLList.deleteLastFile(Hashing.cryptHash(Hashing.cryptHash("123456789&Jan") + "&" + "Feb"));
        authDLList.deleteFirstFile(Hashing.cryptHash("123456789&Jan"));
        authDLList.insertFileNode("Mar", null);
        authDLList.insertFileNode("Apr", Hashing.cryptHash("123456789&Mar"));
        assertTrue(AuthDLList.verifyIntegrity(authDLList, Hashing.cryptHash(Hashing.cryptHash("123456789&Mar") + "&Apr")));
    }

    @org.junit.jupiter.api.Test
    void verifyIntegrityDeleteOneNode() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    @org.junit.jupiter.api.Test
    void verifyIntegrityDeleteEmptyList() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    @Test
    void verifyIntegrityInsertDeleteFirst() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    @Test
    void verifyIntegrityInsertDeleteLast() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteFirstFileForwardOneNode() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteFirstFileBackwardOneNode() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteFirstFileForwardNodes() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteFirstFileBackwardNodes() throws IntegrityCheckFailedException, EmptyDLListException {
    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteLastFileForwardOneNode() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    @org.junit.jupiter.api.Test
    void deleteLastFileBackwardOneNode() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteLastFileForwardNodes() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    // PASSED
    @org.junit.jupiter.api.Test
    void deleteLastFileBackwardNodes() throws IntegrityCheckFailedException, EmptyDLListException {

    }


    @org.junit.jupiter.api.Test
    void retrieveNodeFile() throws IntegrityCheckFailedException, FileNotFoundException {


    }


    // PASSED
    @Test
    void insertFileNodeForFileForward() throws IntegrityCheckFailedException {

    }

    // PASSED
    @Test
    // Test backward of the linked list
    void insertFileNodeForFileBackward() throws IntegrityCheckFailedException {

    }

    @Test
    void insertFileNodeForDigestForward() throws IntegrityCheckFailedException, EmptyDLListException {

    }

    // PASSED
    @Test
    // Test backward of the linked list
    void insertFileNodeForDigestBackward() throws IntegrityCheckFailedException, EmptyDLListException, FileNotFoundException {
        AuthDLList authDLList = new AuthDLList();
        authDLList.insertFileNode("Jan", null);
        authDLList.insertFileNode("Feb", Hashing.cryptHash("123456789&Jan"));
        authDLList.insertFileNode("Mar", Hashing.cryptHash(Hashing.cryptHash("123456789&Jan") + "&Feb"));
        authDLList.insertFileNode("Apr", Hashing.cryptHash(Hashing.cryptHash(Hashing.cryptHash("123456789&Jan") + "&Feb") + "&Mar"));
        assertEquals("Apr", AuthDLList.retrieveNodeFile(authDLList, Hashing.cryptHash(Hashing.cryptHash(Hashing.cryptHash(Hashing.cryptHash("123456789&Jan") + "&Feb") + "&Mar") + "&Apr"), "Ka").file);
    }

    @Test
    void testGeneralExceptVerifyIntegrity() throws IntegrityCheckFailedException, EmptyDLListException {

    }
}