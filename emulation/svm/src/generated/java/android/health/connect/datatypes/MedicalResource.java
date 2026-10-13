package android.health.connect.datatypes;

import android.os.Parcelable;

/**
 * A class to capture the user's medical data. This is the class used for all medical resource
 * types.
 *
 * <p>The data representation follows the <a href="https://hl7.org/fhir/">Fast Healthcare
 * Interoperability Resources (FHIR)</a> standard.
 */
public final class MedicalResource implements Parcelable {
    /**
     * Medical resource type labelling data as allergies or intolerances.
     */
    public static final int MEDICAL_RESOURCE_TYPE_ALLERGIES_INTOLERANCES = 2;

    /**
     * Medical resource type labelling data as medical conditions (clinical condition, problem,
     * diagnosis etc).
     */
    public static final int MEDICAL_RESOURCE_TYPE_CONDITIONS = 7;

    /**
     * Medical resource type labelling data as results (Laboratory or pathology).
     */
    public static final int MEDICAL_RESOURCE_TYPE_LABORATORY_RESULTS = 6;

    /**
     * Medical resource type labelling data as medication related.
     */
    public static final int MEDICAL_RESOURCE_TYPE_MEDICATIONS = 9;

    /**
     * Medical resource type labelling data as related to personal details, including demographic
     * information such as name, date of birth, and contact details such as address or telephone
     * numbers.
     */
    public static final int MEDICAL_RESOURCE_TYPE_PERSONAL_DETAILS = 10;

    /**
     * Medical resource type labelling data as related to practitioners. This is information about
     * the doctors, nurses, masseurs, physios, etc who have been involved with the user.
     */
    public static final int MEDICAL_RESOURCE_TYPE_PRACTITIONER_DETAILS = 11;

    /**
     * Medical resource type labelling data as to do with pregnancy.
     */
    public static final int MEDICAL_RESOURCE_TYPE_PREGNANCY = 3;

    /**
     * Medical resource type labelling data as procedures (actions taken on or for a patient).
     */
    public static final int MEDICAL_RESOURCE_TYPE_PROCEDURES = 8;

    /**
     * Medical resource type labelling data as social history.
     */
    public static final int MEDICAL_RESOURCE_TYPE_SOCIAL_HISTORY = 4;

    /**
     * Medical resource type labelling data as vaccines.
     */
    public static final int MEDICAL_RESOURCE_TYPE_VACCINES = 1;

    /**
     * Medical resource type labelling data as related to an encounter with a practitioner. This
     * includes visits to healthcare providers and remote encounters such as telephone and
     * videoconference appointments, and information about the time, location and organization who
     * is being met.
     */
    public static final int MEDICAL_RESOURCE_TYPE_VISITS = 12;

    /**
     * Medical resource type labelling data as vital signs.
     */
    public static final int MEDICAL_RESOURCE_TYPE_VITAL_SIGNS = 5;

    private MedicalResource() {
    }
}
