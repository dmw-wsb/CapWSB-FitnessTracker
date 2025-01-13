package com.capgemini.wsb.fitnesstracker.training.internal;

/**
 * Enum representing different types of physical activities.
 * Each activity type is associated with a display name for better readability.
 */
public enum ActivityType {

    RUNNING("Running"),
    CYCLING("Cycling"),
    WALKING("Walking"),
    SWIMMING("Swimming"),
    TENNIS("Tennis"); // Corrected spelling from "Tenis" to "Tennis"

    private final String displayName;

    /**
     * Constructor to initialize the activity type with its display name.
     *
     * @param displayName the human-readable name of the activity type
     */
    ActivityType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Retrieves the display name of the activity type.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }
}
