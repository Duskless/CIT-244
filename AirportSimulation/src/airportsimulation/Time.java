/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airportsimulation;

/**
 *
 * @author mfaux02
 */
public class Time implements Comparable {

    private byte hour;
    private byte minute;
    private Meridiem meridiem;
    private int day;

    Time(String time) {
        String[] split = time.split(" ");
        if (split[1].equals("PM")) {
            meridiem = Meridiem.PM;
        } else {
            meridiem = Meridiem.AM;
        }
        day = Integer.valueOf(split[3]);

        split = split[0].split(":");
        if (Byte.valueOf(split[0]) >= 1 & Byte.valueOf(split[0]) <= 12) {
            hour = Byte.valueOf(split[0]);
        } else {
            hour = 12;
        }
        if (Byte.valueOf(split[1]) >= 1 & Byte.valueOf(split[1]) <= 59) {
            minute = Byte.valueOf(split[1]);
        } else {
            minute = 0;
        }
        day = 0;
    }

    Time() {
        hour = 12;
        minute = 0;
        meridiem = Meridiem.AM;
        day = 0;
    }

    @Override
    public String toString() {

        String time;
        time = hour + ":";
        if (minute < 10) {
            time += "0";
        }
        time += minute + " " + meridiem + " Day " + day;
        return time;
    }

    public void doTick() {
        minute++;
        if (minute > 59) {
            minute = 0;
            hour++;
            if (hour == 12) {
                if (meridiem.index > 0) {
                    meridiem = Meridiem.AM;
                    setDay(getDay() + 1);
                } else {
                    meridiem = Meridiem.PM;
                }
            }
            if (hour > 12) {
                hour = 1;
            }
        }
    }

    public void doTick(int amount) {

        int mins = minute + amount;
        while (mins > 59) {
            mins -= 60;
            hour++;
            if (hour == 12) {
                if (meridiem.index > 0) {
                    meridiem = Meridiem.AM;
                    setDay(getDay() + 1);
                } else {
                    meridiem = Meridiem.PM;
                }
            }
            if (hour > 12) {
                hour = 1;
            }

        }
        minute = (byte) mins;
    }

    /**
     * @return the hour
     */
    public byte getHour() {
        return hour;
    }

    /**
     * @param hour the hour to set
     */
    public void setHour(byte hour) {
        this.hour = hour;
    }

    /**
     * @return the minute
     */
    public byte getMinute() {
        return minute;
    }

    /**
     * @param minute the minute to set
     */
    public void setMinute(byte minute) {
        this.minute = minute;
    }

    /**
     * @return the meridiem
     */
    public Meridiem getMeridiem() {
        return meridiem;
    }

    /**
     * @param meridiem the meridiem to set
     */
    public void setMeridiem(Meridiem meridiem) {
        this.meridiem = meridiem;
    }

    @Override
    public int compareTo(Object t) {
        Time time = (Time) t;

        int time1 = 0;
        int time2 = 0;

        if (this.meridiem == Meridiem.AM) {
            time1 = 0;
        } else {
            time1 = 12;
        }

        if (this.hour != 12) {
            time1 += this.hour;
        }

        if (time.getMeridiem() == Meridiem.AM) {
            time2 = 0;
        } else {
            time2 = 12;
        }

        if (time.getHour() != 12) {
            time2 += time.getHour();
        }

        if (this.day != time.getDay()) {
            time1 += this.day * 24;
            time2 += time.getDay() * 24;
        }

        int diffInMins = this.minute - time.getMinute();

        diffInMins += (time1 - time2) * 60;

        return diffInMins;

    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

}


