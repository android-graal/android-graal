package android.health.connect;

/**
 * Permissions for accessing the HealthConnect APIs.
 *
 * <p>Apps must support {@link android.content.Intent#ACTION_VIEW_PERMISSION_USAGE} with {@link
 * HealthConnectManager#CATEGORY_HEALTH_PERMISSIONS} category to be granted read/write health data
 * permissions.
 */
public final class HealthPermissions {
    /**
     * Allows an application to read the user's active calories burned data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_ACTIVE_CALORIES_BURNED = "android.permission.health.READ_ACTIVE_CALORIES_BURNED";

    /**
     * Allows an application to read the user's activity intensity data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_ACTIVITY_INTENSITY = "android.permission.health.READ_ACTIVITY_INTENSITY";

    /**
     * Allows an application to read the user's body temperature data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_BASAL_BODY_TEMPERATURE = "android.permission.health.READ_BASAL_BODY_TEMPERATURE";

    /**
     * Allows an application to read the user's basal metabolic rate data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_BASAL_METABOLIC_RATE = "android.permission.health.READ_BASAL_METABOLIC_RATE";

    /**
     * Allows an application to read the user's blood glucose data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_BLOOD_GLUCOSE = "android.permission.health.READ_BLOOD_GLUCOSE";

    /**
     * Allows an application to read the user's blood pressure data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_BLOOD_PRESSURE = "android.permission.health.READ_BLOOD_PRESSURE";

    /**
     * Allows an application to read the user's body fat data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_BODY_FAT = "android.permission.health.READ_BODY_FAT";

    /**
     * Allows an application to read the user's body temperature data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_BODY_TEMPERATURE = "android.permission.health.READ_BODY_TEMPERATURE";

    /**
     * Allows an application to read the user's body water mass data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_BODY_WATER_MASS = "android.permission.health.READ_BODY_WATER_MASS";

    /**
     * Allows an application to read the user's bone mass data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_BONE_MASS = "android.permission.health.READ_BONE_MASS";

    /**
     * Allows an application to read the user's cervical mucus data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_CERVICAL_MUCUS = "android.permission.health.READ_CERVICAL_MUCUS";

    /**
     * Allows an application to read the user's distance data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_DISTANCE = "android.permission.health.READ_DISTANCE";

    /**
     * Allows an application to read the user's elevation gained data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_ELEVATION_GAINED = "android.permission.health.READ_ELEVATION_GAINED";

    /**
     * Allows an application to read the user's exercise data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_EXERCISE = "android.permission.health.READ_EXERCISE";

    /**
     * Allows an application to read {@link ExerciseRoute}.
     *
     * <p>This permission can only be granted manually by a user in Health Connect settings or in
     * the route request activity which can be launched using {@link ACTION_REQUEST_EXERCISE_ROUTE}.
     * Attempts to request the permission by applications will be ignored.
     *
     * <p>Applications should check if the permission has been granted before reading {@link
     * ExerciseRoute}.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_EXERCISE_ROUTES = "android.permission.health.READ_EXERCISE_ROUTES";

    /**
     * Allows an application to read the user's floors climbed data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_FLOORS_CLIMBED = "android.permission.health.READ_FLOORS_CLIMBED";

    /**
     * Allows an application to read the entire history of health data (of any type).
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_HEALTH_DATA_HISTORY = "android.permission.health.READ_HEALTH_DATA_HISTORY";

    /**
     * Allows an application to read health data (of any type) in background.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_HEALTH_DATA_IN_BACKGROUND = "android.permission.health.READ_HEALTH_DATA_IN_BACKGROUND";

    /**
     * Allows an application to read the user's heart rate data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_HEART_RATE = "android.permission.health.READ_HEART_RATE";

    /**
     * Allows an application to read the user's heart rate variability data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_HEART_RATE_VARIABILITY = "android.permission.health.READ_HEART_RATE_VARIABILITY";

    /**
     * Allows an application to read the user's height data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_HEIGHT = "android.permission.health.READ_HEIGHT";

    /**
     * Allows an application to read the user's hydration data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_HYDRATION = "android.permission.health.READ_HYDRATION";

    /**
     * Allows an application to read the user's intermenstrual bleeding data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_INTERMENSTRUAL_BLEEDING = "android.permission.health.READ_INTERMENSTRUAL_BLEEDING";

    /**
     * Allows an application to read the user's lean body mass data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_LEAN_BODY_MASS = "android.permission.health.READ_LEAN_BODY_MASS";

    /**
     * Allows an application to read the user's data about allergies and intolerances.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_ALLERGIES_INTOLERANCES = "android.permission.health.READ_MEDICAL_DATA_ALLERGIES_INTOLERANCES";

    /**
     * Allows an application to read the user's data about medical conditions.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_CONDITIONS = "android.permission.health.READ_MEDICAL_DATA_CONDITIONS";

    /**
     * Allows an application to read the user's laboratory result data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_LABORATORY_RESULTS = "android.permission.health.READ_MEDICAL_DATA_LABORATORY_RESULTS";

    /**
     * Allows an application to read the user's medication data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_MEDICATIONS = "android.permission.health.READ_MEDICAL_DATA_MEDICATIONS";

    /**
     * Allows an application to read the user's personal details.
     *
     * <p>This is demographic information such as name, date of birth, contact details like address
     * or telephone number and so on. For more examples see the <a
     * href="https://www.hl7.org/fhir/patient.html">FHIR Patient resource</a>.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_PERSONAL_DETAILS = "android.permission.health.READ_MEDICAL_DATA_PERSONAL_DETAILS";

    /**
     * Allows an application to read the user's data about the practitioners who have interacted
     * with them in their medical record. This is the information about the clinicians (doctors,
     * nurses, etc) but also other practitioners (masseurs, physiotherapists, etc) who have been
     * involved with the patient.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_PRACTITIONER_DETAILS = "android.permission.health.READ_MEDICAL_DATA_PRACTITIONER_DETAILS";

    /**
     * Allows an application to read the user's pregnancy data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_PREGNANCY = "android.permission.health.READ_MEDICAL_DATA_PREGNANCY";

    /**
     * Allows an application to read the user's data about medical procedures.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_PROCEDURES = "android.permission.health.READ_MEDICAL_DATA_PROCEDURES";

    /**
     * Allows an application to read the user's social history data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_SOCIAL_HISTORY = "android.permission.health.READ_MEDICAL_DATA_SOCIAL_HISTORY";

    /**
     * Allows an application to read the user's data about immunizations and vaccinations.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_VACCINES = "android.permission.health.READ_MEDICAL_DATA_VACCINES";

    /**
     * Allows an application to read the user's information about their encounters with health care
     * practitioners, including things like location, time of appointment, and name of organization
     * the visit was with. Despite the name visit it covers remote encounters such as telephone or
     * videoconference appointments.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_VISITS = "android.permission.health.READ_MEDICAL_DATA_VISITS";

    /**
     * Allows an application to read the user's vital signs data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MEDICAL_DATA_VITAL_SIGNS = "android.permission.health.READ_MEDICAL_DATA_VITAL_SIGNS";

    /**
     * Allows an application to read the user's menstruation data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MENSTRUATION = "android.permission.health.READ_MENSTRUATION";

    /**
     * Allows an application to read user's mindfulness data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_MINDFULNESS = "android.permission.health.READ_MINDFULNESS";

    /**
     * Allows an application to read the user's nutrition data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_NUTRITION = "android.permission.health.READ_NUTRITION";

    /**
     * Allows an application to read the user's ovulation test data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_OVULATION_TEST = "android.permission.health.READ_OVULATION_TEST";

    /**
     * Allows an application to read the user's oxygen saturation data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_OXYGEN_SATURATION = "android.permission.health.READ_OXYGEN_SATURATION";

    /**
     * Allows an application to read the user's training plan data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_PLANNED_EXERCISE = "android.permission.health.READ_PLANNED_EXERCISE";

    /**
     * Allows an application to read the user's power data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_POWER = "android.permission.health.READ_POWER";

    /**
     * Allows an application to read the user's respiratory rate data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_RESPIRATORY_RATE = "android.permission.health.READ_RESPIRATORY_RATE";

    /**
     * Allows an application to read the user's resting heart rate data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_RESTING_HEART_RATE = "android.permission.health.READ_RESTING_HEART_RATE";

    /**
     * Allows an application to read the user's sexual activity data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_SEXUAL_ACTIVITY = "android.permission.health.READ_SEXUAL_ACTIVITY";

    /**
     * Allows an application to read the user's skin temperature data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_SKIN_TEMPERATURE = "android.permission.health.READ_SKIN_TEMPERATURE";

    /**
     * Allows an application to read the user's sleep data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_SLEEP = "android.permission.health.READ_SLEEP";

    /**
     * Allows an application to read the user's speed data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_SPEED = "android.permission.health.READ_SPEED";

    /**
     * Allows an application to read the user's steps data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_STEPS = "android.permission.health.READ_STEPS";

    /**
     * Allows an application to read the user's total calories burned data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_TOTAL_CALORIES_BURNED = "android.permission.health.READ_TOTAL_CALORIES_BURNED";

    /**
     * Allows an application to read the user's vo2 maximum data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_VO2_MAX = "android.permission.health.READ_VO2_MAX";

    /**
     * Allows an application to read the user's weight data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_WEIGHT = "android.permission.health.READ_WEIGHT";

    /**
     * Allows an application to read the user's wheelchair pushes data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String READ_WHEELCHAIR_PUSHES = "android.permission.health.READ_WHEELCHAIR_PUSHES";

    /**
     * Allows an application to write the user's calories burned data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_ACTIVE_CALORIES_BURNED = "android.permission.health.WRITE_ACTIVE_CALORIES_BURNED";

    /**
     * Allows an application to write the user's activity intensity data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_ACTIVITY_INTENSITY = "android.permission.health.WRITE_ACTIVITY_INTENSITY";

    /**
     * Allows an application to write the user's basal body temperature data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_BASAL_BODY_TEMPERATURE = "android.permission.health.WRITE_BASAL_BODY_TEMPERATURE";

    /**
     * Allows an application to write the user's basal metabolic rate data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_BASAL_METABOLIC_RATE = "android.permission.health.WRITE_BASAL_METABOLIC_RATE";

    /**
     * Allows an application to write the user's blood glucose data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_BLOOD_GLUCOSE = "android.permission.health.WRITE_BLOOD_GLUCOSE";

    /**
     * Allows an application to write the user's blood pressure data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_BLOOD_PRESSURE = "android.permission.health.WRITE_BLOOD_PRESSURE";

    /**
     * Allows an application to write the user's body fat data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_BODY_FAT = "android.permission.health.WRITE_BODY_FAT";

    /**
     * Allows an application to write the user's body temperature data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_BODY_TEMPERATURE = "android.permission.health.WRITE_BODY_TEMPERATURE";

    /**
     * Allows an application to write the user's body water mass data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_BODY_WATER_MASS = "android.permission.health.WRITE_BODY_WATER_MASS";

    /**
     * Allows an application to write the user's bone mass data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_BONE_MASS = "android.permission.health.WRITE_BONE_MASS";

    /**
     * Allows an application to write the user's cervical mucus data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_CERVICAL_MUCUS = "android.permission.health.WRITE_CERVICAL_MUCUS";

    /**
     * Allows an application to write the user's distance data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_DISTANCE = "android.permission.health.WRITE_DISTANCE";

    /**
     * Allows an application to write the user's elevation gained data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_ELEVATION_GAINED = "android.permission.health.WRITE_ELEVATION_GAINED";

    /**
     * Allows an application to write the user's exercise data. Additional permission {@link
     * HealthPermissions#WRITE_EXERCISE_ROUTE} is required to write user's exercise route.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_EXERCISE = "android.permission.health.WRITE_EXERCISE";

    /**
     * Allows an application to write the user's exercise route.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_EXERCISE_ROUTE = "android.permission.health.WRITE_EXERCISE_ROUTE";

    /**
     * Allows an application to write the user's floors climbed data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_FLOORS_CLIMBED = "android.permission.health.WRITE_FLOORS_CLIMBED";

    /**
     * Allows an application to write the user's heart rate data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_HEART_RATE = "android.permission.health.WRITE_HEART_RATE";

    /**
     * Allows an application to write the user's heart rate variability data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_HEART_RATE_VARIABILITY = "android.permission.health.WRITE_HEART_RATE_VARIABILITY";

    /**
     * Allows an application to write the user's height data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_HEIGHT = "android.permission.health.WRITE_HEIGHT";

    /**
     * Allows an application to write the user's hydration data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_HYDRATION = "android.permission.health.WRITE_HYDRATION";

    /**
     * Allows an application to write the user's intermenstrual bleeding data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_INTERMENSTRUAL_BLEEDING = "android.permission.health.WRITE_INTERMENSTRUAL_BLEEDING";

    /**
     * Allows an application to write the user's lean body mass data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_LEAN_BODY_MASS = "android.permission.health.WRITE_LEAN_BODY_MASS";

    /**
     * Allows an application to write the user's medical data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_MEDICAL_DATA = "android.permission.health.WRITE_MEDICAL_DATA";

    /**
     * Allows an application to write the user's menstruation data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_MENSTRUATION = "android.permission.health.WRITE_MENSTRUATION";

    /**
     * Allows an application to write user's mindfulness data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_MINDFULNESS = "android.permission.health.WRITE_MINDFULNESS";

    /**
     * Allows an application to write the user's nutrition data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_NUTRITION = "android.permission.health.WRITE_NUTRITION";

    /**
     * Allows an application to write the user's ovulation test data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_OVULATION_TEST = "android.permission.health.WRITE_OVULATION_TEST";

    /**
     * Allows an application to write the user's oxygen saturation data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_OXYGEN_SATURATION = "android.permission.health.WRITE_OXYGEN_SATURATION";

    /**
     * Allows an application to write the user's training plan data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_PLANNED_EXERCISE = "android.permission.health.WRITE_PLANNED_EXERCISE";

    /**
     * Allows an application to write the user's power data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_POWER = "android.permission.health.WRITE_POWER";

    /**
     * Allows an application to write the user's respiratory rate data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_RESPIRATORY_RATE = "android.permission.health.WRITE_RESPIRATORY_RATE";

    /**
     * Allows an application to write the user's resting heart rate data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_RESTING_HEART_RATE = "android.permission.health.WRITE_RESTING_HEART_RATE";

    /**
     * Allows an application to write the user's sexual activity data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_SEXUAL_ACTIVITY = "android.permission.health.WRITE_SEXUAL_ACTIVITY";

    /**
     * Allows an application to write the user's skin temperature data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_SKIN_TEMPERATURE = "android.permission.health.WRITE_SKIN_TEMPERATURE";

    /**
     * Allows an application to write the user's sleep data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_SLEEP = "android.permission.health.WRITE_SLEEP";

    /**
     * Allows an application to write the user's speed data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_SPEED = "android.permission.health.WRITE_SPEED";

    /**
     * Allows an application to write the user's steps data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_STEPS = "android.permission.health.WRITE_STEPS";

    /**
     * Allows an application to write the user's total calories burned data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_TOTAL_CALORIES_BURNED = "android.permission.health.WRITE_TOTAL_CALORIES_BURNED";

    /**
     * Allows an application to write the user's vo2 maximum data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_VO2_MAX = "android.permission.health.WRITE_VO2_MAX";

    /**
     * Allows an application to write the user's weight data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_WEIGHT = "android.permission.health.WRITE_WEIGHT";

    /**
     * Allows an application to write the user's wheelchair pushes data.
     *
     * <p>Protection level: dangerous.
     */
    public static final String WRITE_WHEELCHAIR_PUSHES = "android.permission.health.WRITE_WHEELCHAIR_PUSHES";

    private HealthPermissions() {
    }
}
