
package fr.fortytwo.cinema.models;

import java.util.Objects;

public class AuthLogs {

    private String ipAdress;
    private String attemptedAt;
    private Integer timeSpent;
    private Long userId;

    public AuthLogs(String ipAdress, String attemptedAt, Integer timeSpent, Long userId) {
        this.ipAdress = ipAdress;
        this.attemptedAt = attemptedAt;
        this.timeSpent = timeSpent;
        this.userId = userId;
    }

    public AuthLogs() {
    }

    public String getIpAdress() {
        return this.ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getAttemptedAt() {
        return this.attemptedAt;
    }

    public void setAttemptedAt(String attemptedAt) {
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
                " ipAdress='" + getIpAdress() + "'" +
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
        return Objects.equals(ipAdress, authLogs.ipAdress) && Objects.equals(attemptedAt, authLogs.attemptedAt)
                && Objects.equals(timeSpent, authLogs.timeSpent) && Objects.equals(userId, authLogs.userId);
    }

    @Override

    public int hashCode() {
        return Objects.hash(ipAdress, attemptedAt, timeSpent, userId);
    }

}