package android.service.quickaccesswallet;

import android.os.Parcelable;

/**
 * Represents a request from the {@link QuickAccessWalletService wallet app} to the Quick Access
 * Wallet in System UI. Background events may necessitate that the Quick Access Wallet update its
 * view. For example, if the wallet application handles an NFC payment while the Quick Access Wallet
 * is being shown, it needs to tell the Quick Access Wallet so that the wallet can be dismissed and
 * Activity showing the payment can be displayed to the user.
 */
public final class WalletServiceEvent implements Parcelable {
    /**
     * An NFC payment has started. If the Quick Access Wallet is in a system window, it will need to
     * be dismissed so that an Activity showing the payment can be displayed.
     */
    public static final int TYPE_NFC_PAYMENT_STARTED = 1;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private WalletServiceEvent() {
    }
}
