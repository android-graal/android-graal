package android.net.wifi;

import android.os.Parcelable;

/**
 *  The Network Suggestion object is used to provide a Wi-Fi network for consideration when
 *  auto-connecting to networks. Apps cannot directly create this object, they must use
 *  {@link WifiNetworkSuggestion.Builder#build()} to obtain an instance of this object.
 * <p>
 *  Apps can provide a list of such networks to the platform using
 *  {@link WifiManager#addNetworkSuggestions(List)}.
 */
public final class WifiNetworkSuggestion implements Parcelable {
    /**
     * With this option, the randomized MAC address will periodically get re-randomized, and
     * the randomized MAC address will change if the suggestion is removed and then added back.
     */
    public static final int RANDOMIZATION_NON_PERSISTENT = 1;

    /**
     * Generate a randomized MAC from a secret seed and information from the Wi-Fi configuration
     * (SSID or Passpoint profile) and reuse it for all connections to this network. The
     * randomized MAC address for this network will stay the same for each subsequent association
     * until the device undergoes factory reset.
     */
    public static final int RANDOMIZATION_PERSISTENT = 0;

    private WifiNetworkSuggestion() {
    }
}
