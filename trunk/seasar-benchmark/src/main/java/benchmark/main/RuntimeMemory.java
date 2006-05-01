package benchmark.main;

import java.math.BigDecimal;

/**
 * @author manhole
 */
public class RuntimeMemory {

    private long free_;

    private long total_;

    private long max_;

    private static BigDecimal KILO = new BigDecimal(1024);

    private static BigDecimal MEGA = KILO.multiply(KILO);

    protected static final BigDecimal ZERO = new BigDecimal("0");

    public RuntimeMemory() {
        snapshotMemory();
    }

    private void snapshotMemory() {
        Runtime rt = Runtime.getRuntime();
        free_ = rt.freeMemory();
        total_ = rt.totalMemory();
        // since JDK 1.2
        max_ = rt.maxMemory();
    }

    public long getFree() {
        return free_;
    }

    public long getTotal() {
        return total_;
    }

    public long getMax() {
        return max_;
    }

    /**
     * TotalMemory - FreeMemory
     */
    public long getUse() {
        return getTotal() - getFree();
    }

    /**
     * (TotalMemory - FreeMemory) / TotalMemory
     */
    public String usageRateAsString() {
        return usageRate().toString();
    }

    /**
     * round (ROUND_HALF_EVEN) to three decimal places
     */
    public BigDecimal usageRate() {
        if (free_ == 0 || total_ == 0) {
            return ZERO;
        } else {
            return new BigDecimal(getUse()).divide(new BigDecimal(total_), 3,
                    BigDecimal.ROUND_HALF_EVEN);
        }
    }

    public static void main(String[] args) {
        RuntimeMemory memory = new RuntimeMemory();
        System.out.println("free=" + memory.getFree());
        System.out.println("total=" + memory.getTotal());
        System.out.println("max=" + memory.getMax());
        System.out.println("rate=" + memory.usageRateAsString());
        System.out.println("rate=" + memory.usageRate());
        System.out.println("rateGraph=" + memory.usageRateGraph(40));
    }

    public String usageRateGraph(final int width) {
        int useCharLength = usageRate().movePointLeft(2).multiply(
                new BigDecimal(width)).intValue();

        StringBuffer sb = new StringBuffer(width + 2);
        sb.append('[');
        int i = 1;
        for (; i <= useCharLength; i++) {
            sb.append('*');
        }
        for (; i <= width; i++) {
            sb.append(' ');
        }
        sb.append(']');

        return sb.toString();
    }

    public String toStringKilobyte() {
        StringBuffer sb = new StringBuffer();
        sb.append("free=").append(kilo(free_));
        sb.append(", use=").append(kilo(getUse()));
        sb.append(", total=").append(kilo(total_));
        sb.append(", max=").append(kilo(max_));
        sb.append(", usageRate=").append(usageRateAsString());
        return sb.toString();
    }

    public String toStringMegabyte() {
        StringBuffer sb = new StringBuffer();
        sb.append("free=").append(mega(free_));
        sb.append(", use=").append(mega(getUse()));
        sb.append(", total=").append(mega(total_));
        sb.append(", max=").append(mega(max_));
        sb.append(", usageRate=").append(usageRateAsString());
        return sb.toString();
    }

    public String kilo(long l) {
        return new BigDecimal(l).divide(KILO, 1, BigDecimal.ROUND_HALF_EVEN)
                .toString()
                + "K";
    }

    public String mega(long l) {
        return new BigDecimal(l).divide(MEGA, 1, BigDecimal.ROUND_HALF_EVEN)
                .toString()
                + "M";
    }

}
