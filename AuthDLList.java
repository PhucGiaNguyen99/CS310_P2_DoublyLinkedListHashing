/**
 * Project 2: BLOCKED CHAIN AUTHENTICATED STORAGE
 *
 * Author: Phuc Nguyen
 */
public class AuthDLList {
    /**
     * Given start digest for the linked list.
     */
    public static final String startDigest = "123456789";

    /**
     * Head of the linked list.
     */
    public Node head;

    /**
     * Tail of the linked list.
     */
    public Node tail;

    /**
     * Check checks the authentication of the linked list by using the proofCheck and the linked list from server.
     *
     * @param currentList object of class AuthDLList
     * @param check       given proofCheck
     * @return true if all checks are passed. Otherwise, return false
     * @throws IntegrityCheckFailedException if verifyIntegrity() returns false
     */
    public static boolean verifyIntegrity(AuthDLList currentList, String check) throws IntegrityCheckFailedException {
        // traverse the linked list forwards from head
        Node currNode = currentList.head;

        // if the linked list is empty, check if check is null. If yes, return true. Otherwise, return false
        if (currNode == null) {
            if (check != null) {
                return false;
            }
        }

        // boolean for first node of linked list
        boolean initial = true;


        while (currNode != null) {
            // if currentNode is head, then check whether its digest equals to startDigest+"&"+currentNode.file
            if (initial) {

                // Check if the digest of head = hash(startDigest+"&"+file 1)
                String hash = Hashing.cryptHash(AuthDLList.startDigest + "&" + currNode.file);
                if (!currNode.digest.equals(hash)) {
                    System.out.println("Return false in HEAD");
                    return false;
                }
                // if there's only one node in the linked list, check if the digest of head equals to check. Return false if it's not equal
                if (currNode.next == null) {
                    if (!currNode.digest.equals(check)) {
                        System.out.println("Return false in case there is only one node");
                        return false;
                    }
                }
                initial = false;
            }
            // if currentNode is tail, check if its digest equals to proofCheck
            else if (currNode == currentList.tail) {
                // if false, return false
                if (!(currNode.digest.equals(check))) {
                    return false;
                }
            }
            // if currentNode is between head and tail, check whether its digest equals to the hash of its previous node's digest +"&"+currentNode.file
            else {
                String hash = Hashing.cryptHash(currNode.previous.digest + "&" + currNode.file);
                if (!currNode.digest.equals(hash)) {
                    throw new IntegrityCheckFailedException();
                }
            }
            currNode = currNode.next;
        }
        return true;
    }

    /**
     * Insert a node at the end of the linked list.
     *
     * @param data  data of the new node
     * @param check given proofCheck
     * @return the digest of the last node
     * @throws IntegrityCheckFailedException if verifyIntegrity() returns false
     */
    public String insertFileNode(String data, String check) throws IntegrityCheckFailedException {
        if (verifyIntegrity(this, check)) {
            Node newNode = new Node();
            newNode.file = data;
            newNode.next = null;

            // if the linked list is empty, then make the new node as head. And set its digest as for head
            if (head == null) {
                head = newNode;
                newNode.digest = Hashing.cryptHash(AuthDLList.startDigest + "&" + newNode.file);
            }
            // otherwise, set next of tail to the new node, and set digest for new node
            else {
                tail.next = newNode;
                newNode.digest = Hashing.cryptHash(tail.digest + "&" + newNode.file);
            }
            newNode.previous = tail;
            tail = newNode;
            return tail.digest;
        } else {
            throw new IntegrityCheckFailedException();
        }
    }

    /**
     * Delete the first node of the Doubly Linked list.
     *
     * @param check given proofCheck for verifyIntegrity()
     * @return digest of the last node
     * @throws IntegrityCheckFailedException if verifyIntegrity returns false
     * @throws EmptyDLListException          if the linked list is empty
     */
    public String deleteFirstFile(String check) throws IntegrityCheckFailedException, EmptyDLListException {
        if (verifyIntegrity(this, check)) {
            // if linked list is empty, throw EmptyDLListException()
            if (head == null) {
                throw new EmptyDLListException();
            }
            else {
                // if there is only one node in the linked list. Set both head and tail to null and return null
                if (head.next == null) {
                    tail = null;
                    head = null;
                    return null;
                }
                // otherwise, delete the first node and set the new head with the digest for head. Then we need to modify digests of all following nodes
                else {
                    head.next.previous = null;
                    head = head.next;
                    head.previous = null;
                    head.digest = Hashing.cryptHash(AuthDLList.startDigest + "&" + head.file);

                    // run loop from second node to tail to update digests
                    Node currentNode = head.next;
                    while (currentNode != null) {
                        currentNode.digest = Hashing.cryptHash(currentNode.previous.digest + "&" + currentNode.file);
                        currentNode = currentNode.next;
                    }
                    return tail.digest;
                }
            }
        }
        // if the linked list is empty, throw emptyDLinkedListException
        else {
            throw new IntegrityCheckFailedException();
        }
    }

    /**
     * Delete the last node of the Linked list.
     *
     * @param check given proofCheck for verifyIntegrity()
     * @return digest of the last node
     * @throws IntegrityCheckFailedException if verifyIntegrity returns false
     * @throws EmptyDLListException          if the linked list is empty
     */
    public String deleteLastFile(String check) throws IntegrityCheckFailedException, EmptyDLListException {
        if (verifyIntegrity(this, check)) {
            // if the linked list is empty, throw EmptyDLLException()
            if (head == null) {
                throw new EmptyDLListException();
            }
            // if there is only one node in the linked list, set both head and tail to null and return null
            else if (head.next == null) {
                head = null;
                tail = null;
                return null;
            }
            // otherwise, set tail to the previous node of tail
            else {
                tail = tail.previous;
                tail.next = null;

                // return new tail's digest as the proofCheck
                return tail.digest;
            }
        } else {
            throw new IntegrityCheckFailedException();
        }
    }


    /**
     * Retrieve given node in the linked list.
     *
     * @param current object of class AuthDLList
     * @param check   given proofCheck
     * @param file    file of node to be retrieved
     * @return node of given file
     * @throws IntegrityCheckFailedException if verifyIntegrity() returns false
     * @throws FileNotFoundException         if finally cannot find any node of given file
     */
    public static Node retrieveNodeFile(AuthDLList current, String check, String file) throws
            IntegrityCheckFailedException, FileNotFoundException {
        if (verifyIntegrity(current, check)) {
            // searching from the head of the linked list
            Node tempNode = current.head;
            if (current.head != null) {
                while (tempNode != null) {
                    if (tempNode.file.equals(file)) {
                        return tempNode;
                    }
                    tempNode = tempNode.next;
                }
                throw new FileNotFoundException();
            } else {
                return null;
            }
        } else {
            throw new IntegrityCheckFailedException();
        }
    }


}
