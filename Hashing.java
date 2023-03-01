//TODO: Complete java docs and code in missing spots.
//This code is already made for you, you just need to call Hashing.cryptHash whenever you want to generate the hash of a particular String. The output is formatted in HexaDecimal  

public class Hashing extends SHA512 {
    public static String cryptHash(String s) {
        String digest = hashSHA512(s);
        return digest.substring(0,128);
    }
}
