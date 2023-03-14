package service;

import exception.IpFormatException;
import exception.OctetFormatException;
import exception.OctetOutOfRangeException;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class IpConverter implements Ipv4Convertable {

    private final BiConsumer<StringBuilder, Integer> ipFormatBuilder = (sb, v) -> {
        if (sb.length() > 0)
            sb.append(".");
        sb.append(v);
    };

    @Override
    public String intToIpv4(int value) {
        return IntStream.of(24, 16, 8, 0)
                .boxed()
                .map(offset -> (value >> offset) & 0xFF)
                .collect(StringBuilder::new, ipFormatBuilder, StringBuilder::append)
                .toString();
    }

    @Override
    public int ipv4ToInt(String value) {
        Integer[] validOctets = validateIp(value);
        return convertToInt(validOctets);
    }

    private Integer[] validateIp(String value) {
        String[] octets = value.split("\\.");
        if (octets.length != 4)
            throw new IpFormatException();
        return Arrays.stream(octets)
                .map(this::validateOctet)
                .toArray(Integer[]::new);
    }

    private int validateOctet(String value) {
        try {
            int octet = Integer.parseInt(value);
            if (octet > 255 || octet < 0)
                throw new OctetOutOfRangeException(octet);
            return octet;
        } catch (NumberFormatException e) {
            throw new OctetFormatException(value);
        }
    }

    private int convertToInt(Integer[] octets) {
        int res = octets[0];
        for (int i = 0; i < 3; i++) {
            res <<= 8;
            res |= octets[i + 1];
        }
        return res;
    }
}
