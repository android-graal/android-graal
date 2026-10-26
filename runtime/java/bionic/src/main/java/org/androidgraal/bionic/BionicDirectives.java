package org.androidgraal.bionic;

import java.util.List;
import org.graalvm.nativeimage.c.CContext;

public final class BionicDirectives implements CContext.Directives {

    @Override
    public List<String> getHeaderFiles() {
        return List.of("<arpa/inet.h>", "<ctype.h>", "<dlfcn.h>", "<errno.h>", "<fcntl.h>", "<linux/if_addr.h>",
                "<linux/netlink.h>", "<linux/rtnetlink.h>", "<linux/vm_sockets.h>", "<malloc.h>", "<net/if.h>",
                "<net/if_arp.h>", "<netdb.h>", "<netinet/icmp6.h>", "<netinet/if_ether.h>", "<netinet/in.h>",
                "<netinet/ip_icmp.h>", "<netinet/tcp.h>", "<netinet/udp.h>", "<netpacket/packet.h>", "<poll.h>",
                "<signal.h>", "<stdio.h>", "<stdlib.h>", "<string.h>", "<sys/capability.h>", "<sys/ioctl.h>",
                "<sys/mman.h>", "<sys/prctl.h>", "<sys/resource.h>", "<sys/sendfile.h>", "<sys/socket.h>",
                "<sys/stat.h>", "<sys/statvfs.h>", "<sys/syscall.h>", "<sys/system_properties.h>", "<sys/time.h>",
                "<sys/uio.h>", "<sys/utsname.h>", "<sys/wait.h>", "<sys/xattr.h>", "<termios.h>", "<time.h>",
                "<unistd.h>");
    }
}
