package android.health.connect.datatypes;

import android.os.Parcelable;

/**
 * A class to capture the FHIR resource data. This is the class used for all supported FHIR resource
 * types, which is a subset of the resource list on <a
 * href="https://build.fhir.org/resourcelist.html">the official FHIR website</a>. The list of
 * supported types will likely expand in future releases.
 *
 * <p>FHIR stands for the <a href="https://hl7.org/fhir/">Fast Healthcare Interoperability Resources
 * </a> standard.
 */
public final class FhirResource implements Parcelable {
    /**
     * FHIR resource type for <a
     * href="https://www.hl7.org/fhir/allergyintolerance.html">AllergyIntolerance</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_ALLERGY_INTOLERANCE = 2;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/condition.html">FHIR
     * Condition</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_CONDITION = 4;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/encounter.html">FHIR
     * Encounter</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_ENCOUNTER = 12;

    /**
     * FHIR resource type for <a href="https://www.hl7.org/fhir/immunization.html">Immunization</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_IMMUNIZATION = 1;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/location.html">FHIR Location</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_LOCATION = 13;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/medication.html">FHIR
     * Medication</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_MEDICATION = 6;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/medicationrequest.html">FHIR
     * MedicationRequest</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_MEDICATION_REQUEST = 7;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/medicationstatement.html">FHIR
     * MedicationStatement</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_MEDICATION_STATEMENT = 8;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/observation.html">FHIR
     * Observation</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_OBSERVATION = 3;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/organization.html">FHIR
     * Organization</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_ORGANIZATION = 14;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/patient.html">FHIR Patient</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_PATIENT = 9;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/practitioner.html">FHIR
     * Practitioner</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_PRACTITIONER = 10;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/practitionerrole.html">FHIR
     * PractitionerRole</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_PRACTITIONER_ROLE = 11;

    /**
     * FHIR resource type for a <a href="https://www.hl7.org/fhir/procedure.html">FHIR
     * Procedure</a>.
     */
    public static final int FHIR_RESOURCE_TYPE_PROCEDURE = 5;

    private FhirResource() {
    }
}
