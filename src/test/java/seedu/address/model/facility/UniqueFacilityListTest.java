package seedu.address.model.facility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1;
import static seedu.address.testutil.TypicalFacilities.KENT_RIDGE_SPORT_HALL_5_COURT_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.facility.exceptions.DuplicateFacilityException;
import seedu.address.model.facility.exceptions.FacilityNotFoundException;
import seedu.address.testutil.FacilityBuilder;

public class UniqueFacilityListTest {
    public final UniqueFacilityList uniqueFacilityList = new UniqueFacilityList();
    public final Facility facility = new Facility(
            new FacilityName("Court 1"),
            new Location("Kent Ridge Sports Hall"),
            new Time("13:00"),
            new Capacity("5"));

    @Test
    public void add_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> uniqueFacilityList.add(null));
    }

    @Test
    public void remove_nullFacility_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilityList.remove(null));
    }

    @Test
    public void remove_facilityDoesNotExist_throwsFacilityNotFoundException() {
        assertThrows(FacilityNotFoundException.class, () -> uniqueFacilityList.remove(facility));
    }

    @Test
    public void remove_existingFacility_removesFacility() {
        uniqueFacilityList.add(facility);
        uniqueFacilityList.remove(facility);
    }

    @Test
    public void resetFacilities_clearsFacilityList() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        uniqueFacilityList.resetFacilities();
        UniqueFacilityList expectedUniqueFacilityList = new UniqueFacilityList();
        assertEquals(expectedUniqueFacilityList, uniqueFacilityList);
    }

    @Test
    public void setFacility_nullTargetFacility_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilityList
                .setFacility(null, KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1));
    }

    @Test
    public void setFacility_nullEditedFacility_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueFacilityList
                .setFacility(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1, null));
    }

    @Test
    public void setFacility_targetFacilityNotInList_throwsFacilityNotFoundException() {
        assertThrows(FacilityNotFoundException.class, () -> uniqueFacilityList
                .setFacility(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1, KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1));
    }

    @Test
    public void setFacility_editedFacilityIsSameFacility_success() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        uniqueFacilityList
                .setFacility(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1, KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        UniqueFacilityList expectedUniqueFacilityList = new UniqueFacilityList();
        expectedUniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        assertEquals(expectedUniqueFacilityList, uniqueFacilityList);
    }

    @Test
    public void setFacility_editedFacilityHasSameParameter_success() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        Facility editedFacil = new FacilityBuilder(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1).build();
        uniqueFacilityList.setFacility(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1, editedFacil);
        UniqueFacilityList expectedUniqueFacilityList = new UniqueFacilityList();
        expectedUniqueFacilityList.add(editedFacil);
        assertEquals(expectedUniqueFacilityList, uniqueFacilityList);
    }

    @Test
    public void setFacility_editedFacilityHasDifferentParameter_success() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        uniqueFacilityList
                .setFacility(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1, KENT_RIDGE_SPORT_HALL_5_COURT_1);
        UniqueFacilityList expectedUniqueFacilityList = new UniqueFacilityList();
        expectedUniqueFacilityList.add(KENT_RIDGE_SPORT_HALL_5_COURT_1);
        assertEquals(expectedUniqueFacilityList, uniqueFacilityList);
    }

    @Test
    public void setFacility_editedFacilityHasNonUniqueParameter_throwsDuplicateFacilityException() {
        uniqueFacilityList.add(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1);
        uniqueFacilityList.add(KENT_RIDGE_SPORT_HALL_5_COURT_1);
        assertThrows(DuplicateFacilityException.class, () -> uniqueFacilityList
                .setFacility(KENT_RIDGE_OUTDOOR_TENNIS_COURTS_COURT_1, KENT_RIDGE_SPORT_HALL_5_COURT_1));
    }
}
