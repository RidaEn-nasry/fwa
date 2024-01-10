
package fr.fortytwo.cinema.models;

import java.sql.Timestamp;
import java.util.Objects;

public class AuthLogs {

    private String ipAddress;
   private Timestamp attemptedAt; 
    private Integer timeSpent;
    private Long userId;

    public AuthLogs(String ipAddress, Timestamp attemptedAt, Integer timeSpent, Long userId) {
        this.ipAddress = ipAddress;
        this.attemptedAt = attemptedAt;
        this.timeSpent = timeSpent;
        this.userId = userId;
    }

    public AuthLogs() {
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Timestamp getAttemptedAt() {
        return this.attemptedAt;
    }

    
    public void setAttemptedAt(Timestamp attemptedAt) {
        this.attemptedAt = attemptedAt;
    }

    public Integer getTimeSpent() {
        return this.timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" +
                " ipAddress='" + getIpAddress() + "'" +
                ", attemptedAt='" + getAttemptedAt() + "'" +
                ", timeSpent='" + getTimeSpent() + "'" +
                ", userId='" + getUserId() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AuthLogs)) {
            return false;
        }
        AuthLogs authLogs = (AuthLogs) o;
        return Objects.equals(ipAddress, authLogs.ipAddress) && Objects.equals(attemptedAt, authLogs.attemptedAt)
                && Objects.equals(timeSpent, authLogs.timeSpent) && Objects.equals(userId, authLogs.userId);
    }

    @Override

    public int hashCode() {
        return Objects.hash(ipAddress, attemptedAt, timeSpent, userId);
    }

}