package android.bluetooth;

/**
 * A connected or connecting Bluetooth socket.
 *
 * <p>The interface for Bluetooth Sockets is similar to that of TCP sockets: {@link java.net.Socket}
 * and {@link java.net.ServerSocket}. On the server side, use a {@link BluetoothServerSocket} to
 * create a listening server socket. When a connection is accepted by the {@link
 * BluetoothServerSocket}, it will return a new {@link BluetoothSocket} to manage the connection. On
 * the client side, use a single {@link BluetoothSocket} to both initiate an outgoing connection and
 * to manage the connection.
 *
 * <p>The most common type of Bluetooth socket is RFCOMM, which is the type supported by the Android
 * APIs. RFCOMM is a connection-oriented, streaming transport over Bluetooth. It is also known as
 * the Serial Port Profile (SPP).
 *
 * <p>To create a {@link BluetoothSocket} for connecting to a known device, use {@link
 * BluetoothDevice#createRfcommSocketToServiceRecord
 * BluetoothDevice.createRfcommSocketToServiceRecord()}. Then call {@link #connect()} to attempt a
 * connection to the remote device. This call will block until a connection is established or the
 * connection fails.
 *
 * <p>To create a {@link BluetoothSocket} as a server (or "host"), see the {@link
 * BluetoothServerSocket} documentation.
 *
 * <p>Once the socket is connected, whether initiated as a client or accepted as a server, open the
 * IO streams by calling {@link #getInputStream} and {@link #getOutputStream} in order to retrieve
 * {@link java.io.InputStream} and {@link java.io.OutputStream} objects, respectively, which are
 * automatically connected to the socket.
 *
 * <p>{@link BluetoothSocket} is thread safe. In particular, {@link #close} will always immediately
 * abort ongoing operations and close the socket.
 *
 * <p><div class="special reference">
 *
 * <h3>Developer Guides</h3>
 *
 * <p>For more information about using Bluetooth, read the <a
 * href="{@docRoot}guide/topics/connectivity/bluetooth.html">Bluetooth</a> developer guide. </div>
 *
 * @see BluetoothServerSocket
 * @see java.io.InputStream
 * @see java.io.OutputStream
 */
public final class BluetoothSocket {
    /**
     * L2CAP socket
     */
    public static final int TYPE_L2CAP = 3;

    /**
     * L2CAP socket on LE transport
     */
    public static final int TYPE_LE = 4;

    /**
     * RFCOMM socket
     */
    public static final int TYPE_RFCOMM = 1;

    /**
     * SCO socket
     */
    public static final int TYPE_SCO = 2;

    private BluetoothSocket() {
    }
}
