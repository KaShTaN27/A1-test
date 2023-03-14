package service;

import exception.IpFormatException;
import exception.OctetFormatException;
import exception.OctetOutOfRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class IpConverterTest {

    private final Ipv4Convertable ipConverter = new IpConverter();

    private static Stream<Arguments> ipv4AndIntRepresentation() {
        return Stream.of(
                Arguments.of("0.0.0.0", 0),
                Arguments.of("127.0.0.1", 2130706433),
                Arguments.of("192.168.100.1", -1062706175),
                Arguments.of("255.255.255.0", -256),
                Arguments.of("255.255.255.255", -1)
        );
    }

    private static Stream<Arguments> invalidFormatIp() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of("."),
                Arguments.of(".."),
                Arguments.of("127001"),
                Arguments.of("127.0.1"),
                Arguments.of("127.0...1"),
                Arguments.of("127.0.0.0.1")
        );
    }

    private static Stream<Arguments> invalidFormatOctet() {
        return Stream.of(
                Arguments.of("abra.cadabra.0.1"),
                Arguments.of("127.+.300.1"),
                Arguments.of("192.:.0.1"),
                Arguments.of("192.168.100.1zero"),
                Arguments.of("127.0.0.1,2")
        );
    }

    @DisplayName("Convert IPv4 to int")
    @MethodSource("ipv4AndIntRepresentation")
    @ParameterizedTest(name = "[{index}] {0} == {1}")
    void convertIpv4ToInt_IfValid(String ipv4, int intIp) {
        int ip = ipConverter.ipv4ToInt(ipv4);
        assertEquals(intIp, ip);
    }

    @DisplayName("Convert int to IPv4")
    @MethodSource("ipv4AndIntRepresentation")
    @ParameterizedTest(name = "[{index}] {1} == {0}")
    void convertIntToIpv4_IfValid(String ipv4, int intIp) {
        String ip = ipConverter.intToIpv4(intIp);
        assertEquals(ipv4, ip);
    }

    @ParameterizedTest
    @MethodSource("invalidFormatIp")
    @DisplayName("Catch invalid ip format exception")
    void convertIpv4ToInt_IfIpFormatInvalid(String ipv4) {
        assertThrows(IpFormatException.class, () -> ipConverter.ipv4ToInt(ipv4));
    }

    @ParameterizedTest
    @MethodSource("invalidFormatOctet")
    @DisplayName("Catch invalid octet format exception")
    void convertIpv4ToInt_IfOctetFormatInvalid(String ipv4) {
        assertThrows(OctetFormatException.class, () -> ipConverter.ipv4ToInt(ipv4));
    }

    @Test
    @DisplayName("Catch octet out of range exception")
    void convertIpv4ToInt_IfOctetOutOfRange() {
        assertThrows(OctetOutOfRangeException.class, () -> ipConverter.ipv4ToInt("255.255.256.0"));
        assertThrows(OctetOutOfRangeException.class, () -> ipConverter.ipv4ToInt("127.0.0.-1"));
    }
}