package android.bluetooth;

import android.os.Parcelable;

/**
 * Represents a Bluetooth class, which describes general characteristics and capabilities of a
 * device. For example, a Bluetooth class will specify the general device type such as a phone, a
 * computer, or headset, and whether it's capable of services such as audio or telephony.
 *
 * <p>Every Bluetooth class is composed of zero or more service classes, and exactly one device
 * class. The device class is further broken down into major and minor device class components.
 *
 * <p>{@link BluetoothClass} is useful as a hint to roughly describe a device (for example to show
 * an icon in the UI), but does not reliably describe which Bluetooth profiles or services are
 * actually supported by a device. Accurate service discovery is done through SDP requests, which
 * are automatically performed when creating an RFCOMM socket with {@link
 * BluetoothDevice#createRfcommSocketToServiceRecord} and {@link
 * BluetoothAdapter#listenUsingRfcommWithServiceRecord}
 *
 * <p>Use {@link BluetoothDevice#getBluetoothClass} to retrieve the class for a remote device.
 * <!--
 * The Bluetooth class is a 32 bit field. The format of these bits is defined at
 * http://www.bluetooth.org/Technical/AssignedNumbers/baseband.htm
 * (login required). This class contains that 32 bit field, and provides
 * constants and methods to determine which Service Class(es) and Device Class
 * are encoded in that field.
 * -->
 */
public final class BluetoothClass implements Parcelable {
    public static final int PROFILE_A2DP = 1;

    public static final int PROFILE_HEADSET = 0;

    public static final int PROFILE_HID = 3;

    private BluetoothClass() {
    }

    /**
     * Defines all device class constants.
     *
     * <p>Each {@link BluetoothClass} encodes exactly one device class, with major and minor
     * components.
     *
     * <p>The constants in {@link BluetoothClass.Device} represent a combination of major and minor
     * device components (the complete device class). The constants in {@link
     * BluetoothClass.Device.Major} represent only major device classes.
     *
     * <p>See {@link BluetoothClass.Service} for service class constants.
     */
    public static final class Device {
        public static final int AUDIO_VIDEO_CAMCORDER = 1076;

        public static final int AUDIO_VIDEO_CAR_AUDIO = 1056;

        public static final int AUDIO_VIDEO_HANDSFREE = 1032;

        public static final int AUDIO_VIDEO_HEADPHONES = 1048;

        public static final int AUDIO_VIDEO_HIFI_AUDIO = 1064;

        public static final int AUDIO_VIDEO_LOUDSPEAKER = 1044;

        public static final int AUDIO_VIDEO_MICROPHONE = 1040;

        public static final int AUDIO_VIDEO_PORTABLE_AUDIO = 1052;

        public static final int AUDIO_VIDEO_SET_TOP_BOX = 1060;

        public static final int AUDIO_VIDEO_UNCATEGORIZED = 1024;

        public static final int AUDIO_VIDEO_VCR = 1068;

        public static final int AUDIO_VIDEO_VIDEO_CAMERA = 1072;

        public static final int AUDIO_VIDEO_VIDEO_CONFERENCING = 1088;

        public static final int AUDIO_VIDEO_VIDEO_DISPLAY_AND_LOUDSPEAKER = 1084;

        public static final int AUDIO_VIDEO_VIDEO_GAMING_TOY = 1096;

        public static final int AUDIO_VIDEO_VIDEO_MONITOR = 1080;

        public static final int AUDIO_VIDEO_WEARABLE_HEADSET = 1028;

        public static final int COMPUTER_DESKTOP = 260;

        public static final int COMPUTER_HANDHELD_PC_PDA = 272;

        public static final int COMPUTER_LAPTOP = 268;

        public static final int COMPUTER_PALM_SIZE_PC_PDA = 276;

        public static final int COMPUTER_SERVER = 264;

        public static final int COMPUTER_UNCATEGORIZED = 256;

        public static final int COMPUTER_WEARABLE = 280;

        public static final int HEALTH_BLOOD_PRESSURE = 2308;

        public static final int HEALTH_DATA_DISPLAY = 2332;

        public static final int HEALTH_GLUCOSE = 2320;

        public static final int HEALTH_PULSE_OXIMETER = 2324;

        public static final int HEALTH_PULSE_RATE = 2328;

        public static final int HEALTH_THERMOMETER = 2312;

        public static final int HEALTH_UNCATEGORIZED = 2304;

        public static final int HEALTH_WEIGHING = 2316;

        public static final int PERIPHERAL_KEYBOARD = 1344;

        public static final int PERIPHERAL_KEYBOARD_POINTING = 1472;

        public static final int PERIPHERAL_NON_KEYBOARD_NON_POINTING = 1280;

        public static final int PERIPHERAL_POINTING = 1408;

        public static final int PHONE_CELLULAR = 516;

        public static final int PHONE_CORDLESS = 520;

        public static final int PHONE_ISDN = 532;

        public static final int PHONE_MODEM_OR_GATEWAY = 528;

        public static final int PHONE_SMART = 524;

        public static final int PHONE_UNCATEGORIZED = 512;

        public static final int TOY_CONTROLLER = 2064;

        public static final int TOY_DOLL_ACTION_FIGURE = 2060;

        public static final int TOY_GAME = 2068;

        public static final int TOY_ROBOT = 2052;

        public static final int TOY_UNCATEGORIZED = 2048;

        public static final int TOY_VEHICLE = 2056;

        public static final int WEARABLE_GLASSES = 1812;

        public static final int WEARABLE_HELMET = 1808;

        public static final int WEARABLE_JACKET = 1804;

        public static final int WEARABLE_PAGER = 1800;

        public static final int WEARABLE_UNCATEGORIZED = 1792;

        public static final int WEARABLE_WRIST_WATCH = 1796;

        private Device() {
        }

        /**
         * Defines all major device class constants.
         *
         * <p>See {@link BluetoothClass.Device} for minor classes.
         */
        public static final class Major {
            public static final int AUDIO_VIDEO = 1024;

            public static final int COMPUTER = 256;

            public static final int HEALTH = 2304;

            public static final int IMAGING = 1536;

            public static final int MISC = 0;

            public static final int NETWORKING = 768;

            public static final int PERIPHERAL = 1280;

            public static final int PHONE = 512;

            public static final int TOY = 2048;

            public static final int UNCATEGORIZED = 7936;

            public static final int WEARABLE = 1792;

            private Major() {
            }
        }
    }

    /**
     * Defines all service class constants.
     *
     * <p>Each {@link BluetoothClass} encodes zero or more service classes.
     */
    public static final class Service {
        public static final int AUDIO = 2097152;

        public static final int CAPTURE = 524288;

        public static final int INFORMATION = 8388608;

        /**
         * Represent devices LE audio service
         */
        public static final int LE_AUDIO = 16384;

        public static final int LIMITED_DISCOVERABILITY = 8192;

        public static final int NETWORKING = 131072;

        public static final int OBJECT_TRANSFER = 1048576;

        public static final int POSITIONING = 65536;

        public static final int RENDER = 262144;

        public static final int TELEPHONY = 4194304;

        private Service() {
        }
    }
}
