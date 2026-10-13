package android.system;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StructsTest {

    @Test
    void structStatPrintsEveryFieldByName() {
        StructStat stat = new StructStat(1, 2, 3, 4, 5, 6, 7, 8, new StructTimespec(9, 10), new StructTimespec(11, 12),
                new StructTimespec(13, 14), 15, 16);
        assertEquals("StructStat[st_atim=StructTimespec[tv_nsec=10,tv_sec=9],st_atime=9,st_blksize=15,st_blocks=16,"
                + "st_ctim=StructTimespec[tv_nsec=14,tv_sec=13],st_ctime=13,st_dev=1,st_gid=6,st_ino=2,st_mode=3,"
                + "st_mtim=StructTimespec[tv_nsec=12,tv_sec=11],st_mtime=11,st_nlink=4,st_rdev=7,st_size=8,st_uid=5]",
                stat.toString());
    }

    @Test
    void structStatVfsPrintsEveryField() {
        StructStatVfs vfs = new StructStatVfs(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        assertEquals("StructStatVfs[f_bavail=5,f_bfree=4,f_blocks=3,f_bsize=1,f_favail=8,f_ffree=7,f_files=6,"
                + "f_flag=10,f_frsize=2,f_fsid=9,f_namemax=11]", vfs.toString());
    }

    @Test
    void structUtsnameQuotesStringsAndPrintsNullBare() {
        StructUtsname uts = new StructUtsname("Linux", "localhost", "6.1.0", "#1 SMP", null);
        assertEquals("StructUtsname[machine=null,nodename=\"localhost\",release=\"6.1.0\",sysname=\"Linux\","
                + "version=\"#1 SMP\"]", uts.toString());
    }

    @Test
    void structPollfdPrintsUserDataByItsType() {
        StructPollfd pollfd = new StructPollfd();
        pollfd.events = 1;
        pollfd.revents = 4;
        assertEquals("StructPollfd[events=1,fd=null,revents=4,userData=null]", pollfd.toString());
        pollfd.userData = "key";
        assertEquals("StructPollfd[events=1,fd=null,revents=4,userData=\"key\"]", pollfd.toString());
        pollfd.userData = 'c';
        assertEquals("StructPollfd[events=1,fd=null,revents=4,userData='c']", pollfd.toString());
        pollfd.userData = new int[] {1, 2};
        assertEquals("StructPollfd[events=1,fd=null,revents=4,userData=[1, 2]]", pollfd.toString());
        pollfd.userData = 7L;
        assertEquals("StructPollfd[events=1,fd=null,revents=4,userData=7]", pollfd.toString());
    }

    @Test
    void int64RefPrintsItsValue() {
        assertEquals("Int64Ref[value=-42]", new Int64Ref(-42).toString());
    }

    @Test
    void structTimespecPrintsBothFields() {
        assertEquals("StructTimespec[tv_nsec=999999999,tv_sec=-1]", new StructTimespec(-1, 999_999_999).toString());
    }

    @Test
    void structTimevalPrintsBothFields() {
        assertEquals("StructTimeval[tv_sec=-2,tv_usec=500000]", StructTimeval.fromMillis(-1500).toString());
    }
}
