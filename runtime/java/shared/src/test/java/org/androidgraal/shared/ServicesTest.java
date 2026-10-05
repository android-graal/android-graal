package org.androidgraal.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.ServiceConfigurationError;
import org.junit.jupiter.api.Test;

class ServicesTest {

    public interface Single {}

    public interface Missing {}

    public interface Twice {}

    public interface Broken {}

    public static final class SingleProvider implements Single {}

    public static final class TwiceOne implements Twice {}

    public static final class TwiceTwo implements Twice {}

    @Test
    void theOneProviderIsInstantiatedFreshPerCall() {
        Single first = Services.one(Single.class);

        assertInstanceOf(SingleProvider.class, first);
        assertNotSame(first, Services.one(Single.class));
    }

    @Test
    void allHasEveryProviderInOrder() {
        List<Twice> all = Services.all(Twice.class);

        assertEquals(2, all.size());
        assertInstanceOf(TwiceOne.class, all.get(0));
        assertInstanceOf(TwiceTwo.class, all.get(1));
        assertEquals(List.of(), Services.all(Missing.class));
    }

    @Test
    void noProviderFailsNamingTheService() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> Services.one(Missing.class));

        assertEquals("0 providers of " + Missing.class.getName(), e.getMessage());
    }

    @Test
    void twoProvidersFailNamingBoth() {
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> Services.one(Twice.class));

        assertEquals("2 providers of " + Twice.class.getName() + ": " + TwiceOne.class.getName() + ", "
                + TwiceTwo.class.getName(), e.getMessage());
    }

    @Test
    void aProviderThatCannotLoadThrowsTheServiceLoadersError() {
        assertThrows(ServiceConfigurationError.class, () -> Services.all(Broken.class));
    }
}
