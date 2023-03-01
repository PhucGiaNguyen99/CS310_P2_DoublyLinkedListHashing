
public class AuthDLList {
    public static final String startDigest = "123456789";
    public Node head;
    public Node tail;

    /**
     * Check the authentication of the linked list by using proofCheck and the linked list from server.
     *
     * @param currentList current array list
     * @param check       given proofCheck to compare with the last digest
     * @return true if all 3 checks are passed. Otherwise, return false
     * @throws IntegrityCheckFailedException
     */
    public static boolean verifyIntegrity(AuthDLList currentList, String check) throws IntegrityCheckFailedException {
        // starting from head
        Node currNode = currentList.head;

        boolean initial = true;

        while (currNode != null) {
            if (initial) {

                // Check digest 1 = CHF (startDigest+"&"+file 1)
                String hash = Hashing.cryptHash(AuthDLList.startDigest + "&" + currNode.file);
                if (!currNode.digest.equals(hash)) {
                    throw new IntegrityCheckFailedException();
                }
                initial = false;

                // move to the next node
                currNode = currNode.next;
            }
            // if reaching the end of the linked list, check if proofCheck is equal to digest n
            else if (currNode == currentList.tail) {
                            /*
                                    Develop Code here
                            */
                // if false, return false
                if (!check.equals(currentList.tail.digest)) {
                    return false;
                }

                // if true, and already reaches tail, but move 1 more to break the loop
                currNode = currNode.next;
            } else {
				  /*
                                    Develop Code here for checking any node in the list
                            */


            }

            currNode = currNode.next;
        }

        return true;
    }

    /**
     * Insert a node at the end of the Doubly linked list.
     *
     * @param data  data of the new node
     * @param check proofCheck used for verifyIntegrity()
     * @return the digest of the last node
     * @throws IntegrityCheckFailedException
     */
    public String insertFileNode(String data, String check) throws IntegrityCheckFailedException {
        //if (verifyIntegrity(head, check)) {
        Node newNode = new Node();
        newNode.file = data;
        newNode.next = null;

        // if the linked list is empty, then make the new node as head
        if (head == null) {
            head = newNode;
            // newNode.digest = Hashing.cryptHash(AuthDLList.startDigest + "&" + newNode.file);
            newNode.digest = AuthDLList.startDigest + "&" + newNode.file;

        } else {
            tail.next = newNode;
            // newNode.digest = Hashing.cryptHash(tail.digest + "&" + newNode.file);
            newNode.digest = Hashing.cryptHash(tail.digest + "&" + newNode.file);

        }
        newNode.previous = tail;
        tail = newNode;
        // already set file, previous, next and digest of newNode, which is our new tail
        //}
        return tail.digest;
    }

    /**
     * Delete the first node of the Doubly Linked list.
     *
     * @param check given proofCheck for verifyIntegrity()
     * @return proofCheck of the linked list
     * @throws IntegrityCheckFailedException
     * @throws EmptyDLListException
     */
    public String deleteFirstFile(String check) throws IntegrityCheckFailedException, EmptyDLListException {
		/*
			Develop Code here
		*/
        //if (verifyIntegrity(currentList, check)) {

        // for regular doubly linked list
        if (head != null) {
            // save the current head
            Node tempNode = head;

            // if there is only one node. Now tail is empty, so Throw emptyDLinkedlistException......................................................................................................
            if (head.next == null) {
                tail = null;
                head = null;
            } else {
                head.next.previous = null;
                head = head.next;
            }

            head.previous = null;
            // head.digest = Hashing.cryptHash(AuthDLList.startDigest + "&" + head.file);
            head.digest = AuthDLList.startDigest + "&" + head.file;

            return tail.digest;
        }
        // if teh linked list is empty, throw emptyDLinkedListException
        else {
            throw new EmptyDLListException();
        }

        // }

    }


    public String deleteLastFile(String check) throws IntegrityCheckFailedException, EmptyDLListException {
        //if (verifyIntegrity(currentList, check)) {
        // if the linked list is empty
        if (head == null) {
            throw new EmptyDLListException();
        }
        // if there is only one node in the linked list
        else if (head.next == null) {
            head = null;
            tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;

            // return new tail's digest as the proofCheck
            return tail.digest;
        }
        //}
        return null;
    }


    // current is the given object of the Doubly linked list
    public static Node retrieveNodeFile(AuthDLList current, String check, String file) throws
            IntegrityCheckFailedException, FileNotFoundException {

        // searching from the head of the linked list
        Node tempNode = current.head;
        //if (verifyIntegrity(current, check)) {
        if (current.head != null) {
            while (tempNode != null) {
                if (tempNode.file.equals(file)) {
                    return tempNode;
                }
                tempNode = tempNode.next;
            }
            throw new FileNotFoundException();
        } else {
            throw new FileNotFoundException();
        }
        // }
        //return null;
    }


}
