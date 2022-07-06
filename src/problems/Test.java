package problems;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

class Version implements Comparable<Version>{
    private final int version;
    private final int serial;
    private final int patch;
    Version(int version, int serial, int patch){
        this.version = version;
        this.serial = serial;
        this.patch = patch;
    }
    public boolean equals(Object obj){
        if(obj == this) return true;
        if(obj!=null && obj.getClass() != this.getClass()) return false;
        Version vno = (Version) obj;
        return vno.patch == this.patch && vno.serial == this.serial && vno.version == this.version;
    }
    public int hashCode(){
        int hashValue = 11;
        hashValue = 31*hashValue + this.version;
        hashValue = 31*hashValue + this.serial;
        hashValue = 31*hashValue + this.patch;
        return hashValue;
    }

    public int compareTo(Version ver){
        int versionDiff = this.version - ver.version;
        if(versionDiff != 0) return versionDiff;
        int serialDiff = this.serial - ver.serial;
        if(serialDiff != 0) return serialDiff;
        int patchDiff = this.patch - ver.patch;
        if(patchDiff != 0) return patchDiff;
        return 0;
    }

    public String toString() {
        return "(" + this.version + "." + this.serial + "." + this.patch + ")";
    }
}

public class Test {
    public static void main(String[] args) {
        Version version1 = new Version(1,2,3);
        Version version2 = new Version(2,2,3);
        Version version3 = new Version(1,2,3);
        Version version4 = new Version(3,2,3);
        System.out.println(version1.equals(version2));
        System.out.println(version1.hashCode());
        System.out.println(version1.hashCode());
        System.out.println(version1);
        List<Version> versionList = Arrays.asList(version1, version2, version3, version4);
        System.out.println(versionList);
        Set<Version> treeSet = new TreeSet<>(versionList);
        System.out.println(treeSet);
    }
}
