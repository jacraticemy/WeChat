package cn.nexuslink.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by 罗浩 on 2017/4/25.
 */
public class HashPasswordUtil {
    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // The following constants may be changed without breaking existing hashes.
    public static final int SALT_BYTE_SIZE = 24;
    public static final int HASH_BYTE_SIZE = 24;
    public static final int PBKDF2_ITERATIONS = 1000;

    public static final int ITERATION_INDEX = 0;
    public static final int SALT_INDEX = 1;
    public static final int PBKDF2_INDEX = 2;

    /**
     * Returns a salted PBKDF2 hash of the password.
     * 返回已加盐的PBKDF2哈希值
     * 格式 迭代次数:盐值:哈希值
     * @param password the password to hash
     * @return a salted PBKDF2 hash of the password
     */
    public static String createPasswordHash(String password)throws NoSuchAlgorithmException, InvalidKeySpecException {
        return createPasswordHash(password.toCharArray());
    }

    /**
     * Returns a salted PBKDF2 hash of the password.
     *
     * @param password  the password to hash
     * @return a salted PBKDF2 hash of the password
     */
    public static String createPasswordHash(char[] password)throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt=getSalt();
        // Hash the password
        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }
    /**
     * Validates a password using a hash.
     *
     * @param password      the password to check
     * @param correctHash   the hash of the valid password
     * @return true if the password is correct, false if not
     */
    public static boolean validatePassword(String password, String correctHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return validatePassword(password.toCharArray(), correctHash);
    }

    /**
     * Validates a password using a hash.
     * 验证密码
     * @param password      the password to check
     * @param correctHash   the hash of the valid password
     * @return true if the password is correct, false if not
     */
    public static boolean validatePassword(char[] password, String correctHash)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Decode the hash into its parameters
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[ITERATION_INDEX]);
        byte[] salt = fromHex(params[SALT_INDEX]);
        byte[] hash = fromHex(params[PBKDF2_INDEX]);
        // Compute the hash of the provided password, using the same salt,
        // iteration count, and hash length
        byte[] testHash = pbkdf2(password, salt, iterations, hash.length);
        // Compare the hashes in constant time. The password is correct if
        // both hashes match.
        return slowEquals(hash, testHash);
    }

    /**
     * 生成盐值
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSalt() throws NoSuchAlgorithmException
    {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[SALT_BYTE_SIZE];
        sr.nextBytes(salt);
        return salt;
    }
    //========================private method============================
    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a
     *            the first byte array
     * @param b
     *            the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++)
            diff |= a[i] ^ b[i];
        return diff == 0;
    }

    /**
     * 完成PBKDF2 加密密码
     * @param password      密码
     * @param salt the      盐值
     * @param iterations    迭代次数(缓慢因子)
     * @param bytes         hash的字节长度
     * @return the PBDKF2 hash of the password
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations,
                                 int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return skf.generateSecret(spec).getEncoded();
    }

    /**
     * 十六进制字符的字符串转换成字节数组。
     *
     * @param hex the hex string
     * @return the hex string decoded into a byte array
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(
                    hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    /**
     * 将字节数组转换为十六进制字符串。
     * @param array the byte array to convert
     * @return a length*2 character string encoding the byte array
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

    public static void main(String[] args) {
        String password="123456";
        String salt;
        try {
            salt = getSalt().toString();
            String hash=createPasswordHash(password);
            System.out.println(hash);
            long begin=System.currentTimeMillis();
            System.out.println(validatePassword(password, hash));
            System.out.println("time:"+(System.currentTimeMillis()-begin));
        } catch (Exception e) {
        }
    }
}
