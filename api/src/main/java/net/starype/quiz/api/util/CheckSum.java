package net.starype.quiz.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

public class CheckSum {
    private ByteBuffer checkSum;

    public static final CheckSum NIL = fromByteBuffer(ByteBuffer.allocate(1));

    private CheckSum(ByteBuffer checkSum, boolean __) {
        this.checkSum = checkSum;
    }

    private CheckSum(ByteBuffer buffer) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(buffer);
            checkSum = ByteBuffer.wrap(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static CheckSum fromRawCheckSum(ByteBuffer buffer) {
        return new CheckSum(buffer, false);
    }

    public static CheckSum fromByteBuffer(ByteBuffer buffer) {
        return new CheckSum(buffer);
    }

    public static CheckSum fromString(String str) {
        return new CheckSum(ByteBuffer.wrap(str.getBytes()));
    }

    public static Optional<CheckSum> fromFile(String filepath) {
        File file = new File(filepath);

        // If the file is readable and does exists then checksum it
        if(file.canRead() && file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                ByteBuffer buffer = ByteBuffer.wrap(fileInputStream.readAllBytes());
                return Optional.of(new CheckSum(buffer));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return Optional.empty();
    }

    public ByteBuffer rawData() {
        return checkSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CheckSum)) return false;
        CheckSum checkSum1 = (CheckSum) o;
        return Objects.equals(checkSum, checkSum1.checkSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(checkSum);
    }
}
