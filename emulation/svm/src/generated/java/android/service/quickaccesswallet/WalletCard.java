package android.service.quickaccesswallet;

import android.os.Parcelable;

/**
 * A {@link WalletCard} can represent anything that a user might carry in their wallet -- a credit
 * card, library card, transit pass, etc. Cards are identified by a String identifier and contain a
 * card type, card image, card image content description, and a {@link PendingIntent} to be used if
 * the user clicks on the card. Cards may be displayed with an icon and label, though these are
 * optional. Non-payment cards will also have a second image that will be displayed when the card is
 * tapped.
 */
public final class WalletCard implements Parcelable {
    /**
     * Non-payment cards refer to any cards that are not used for cash-equivalent payment, including
     * event tickets, flights, offers, loyalty cards, gift cards and transit tickets.
     */
    public static final int CARD_TYPE_NON_PAYMENT = 2;

    /**
     * Payment cards refer to credit cards, debit cards or any other cards in the wallet used to
     * make cash-equivalent payments.
     */
    public static final int CARD_TYPE_PAYMENT = 1;

    /**
     * Unknown cards refer to cards whose types are unspecified.
     */
    public static final int CARD_TYPE_UNKNOWN = 0;

    @Override
    public int describeContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        throw new UnsupportedOperationException();
    }

    private WalletCard() {
    }
}
