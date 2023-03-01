
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
            // if currentNode is head, then check whether its digest equals to startDigest+"&"+currentNode.file
            if (initial) {

                // Check digest 1 = CHF (startDigest+"&"+file 1)
                //String hash = Hashing.cryptHash(AuthDLList.startDigest + "&" + currNode.file);
                String hash = (AuthDLList.startDigest + "&" + currNode.file);

                if (!currNode.digest.equals(hash)) {
                    throw new IntegrityCheckFailedException();
                }

                // if it's matched, moved to remaining nodes
                initial = false;
            }
            // if currentNode is tail, check if its digest equals to proofCheck
            else if (currNode == currentList.tail) {
                // if false, return false
                if (!check.equals(currentList.tail.digest)) {
                    return false;
                }

            }
            // if currentNode is between head and tail, check whether its digest equals to the hash of its previous node's digest +"&"+currentNode.file
            else {
                //String hash = Hashing.cryptHash(currNode.previous.digest + "&" + currNode.file);
                String hash = (currNode.previous.digest + "&" + currNode.file);

                if (!currNode.digest.equals(hash)) {
                    throw new IntegrityCheckFailedException();
                }
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
            newNode.digest = (tail.digest + "&" + newNode.file);

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
                return null;
            } else {
                head.next.previous = null;
                head = head.next;
                head.previous = null;
                // head.digest = Hashing.cryptHash(AuthDLList.startDigest + "&" + head.file);
                head.digest = AuthDLList.startDigest + "&" + head.file;
                // after changing digest of head, digests of all following nodes are changed, so we need to run while loop from node 2 to tail to update digests
                Node currentNode = head.next;
                while (currentNode != null) {
                    currentNode.digest = currentNode.previous.digest + "&" + currentNode.file;
                    currentNode=currentNode.next;
                }
                return tail.digest;
            }
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
    // STATIC
    // current is an object of the class AuthDList
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
            return null;
        } else {
            throw new FileNotFoundException();
        }
        // }
        //return null;
    }

    // Need to change it to private later
    // Using for Testing
    public String toStringBackwardFile() {
        Node temp = tail;
        String resultString = "";
        while (temp != null) {
            resultString += temp.file + " ";
            temp = temp.previous;
        }
        return resultString;
    }

    // Need to change it to private later
    // Using for Testing
    public String toStringForwardFile() {
        Node temp = head;
        String resultString = "";
        while (temp != null) {
            resultString += temp.file + " ";
            temp = temp.next;
        }
        return resultString;
    }

    // Need to change it to private later
    // Using for Testing
    public String toStringBackwardDigest() {
        Node temp = tail;
        String resultString = "";
        while (temp != null) {
            resultString += temp.digest + " ->";
            temp = temp.previous;
        }
        return resultString;
    }

    // Need to change it to private later
    // Using for Testing
    public String toStringForwardDigest() {
        Node temp = head;
        String resultString = "";
        while (temp != null) {
            resultString += temp.digest + " ->";
            temp = temp.next;
        }
        return resultString;
    }


}
