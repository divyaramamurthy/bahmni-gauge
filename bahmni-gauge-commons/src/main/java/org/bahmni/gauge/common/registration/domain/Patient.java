package org.bahmni.gauge.common.registration.domain;

import org.bahmni.gauge.common.clinical.domain.Diagnosis;
import org.bahmni.gauge.common.clinical.domain.Order;
import org.bahmni.gauge.common.clinical.domain.Specimen;
import org.bahmni.gauge.data.Model;
import org.bahmni.gauge.data.ModelMetaData;
import org.bahmni.gauge.rest.BahmniRestClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@ModelMetaData(mrs_name = "patient")
public class Patient extends Model {

    private String uuid;
    private String prefix;
    private String idNumber;
    private String identifier;
    private String firstName;
    private String lastName;
    private String gender;
    private String age;
    private String dateOfBirth;
    private String district;
    private String village;
    private String country;
    private String photo;
    private String gramPanchayat;
    private boolean isAdmitted = false;
    private String bedNumber;
    private String location;
    private String locationUuid;
    private String visitType;
    private String visitTypeUuid;
    private String height;
    private String weight;
    private String registrationFee;
    private List<Diagnosis> diagnoses = new ArrayList<>();
    private List<Specimen> specimens = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Specimen> getSpecimens() {
        return specimens;
    }

    public void setSpecimens(List<Specimen> specimens) {
        this.specimens = specimens;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationUuid() {
        if (location != null && locationUuid == null) {
            locationUuid = BahmniRestClient.get().getUuidwithDisplayOnPartialUrl(this.location, "location");
        }
        return locationUuid;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getVisitTypeUuid() {
        if (visitType != null && visitTypeUuid == null) {
            visitTypeUuid = BahmniRestClient.get().getUuidwithDisplayOnPartialUrl(this.visitType, "visittype");
        }
     //   visitTypeUuid = "c1e42932-3f10-11e4-adec-0800271c1b75";
        return visitTypeUuid;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(String registrationFee) {
        this.registrationFee = registrationFee;
    }


    public List<Diagnosis> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<Diagnosis> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public String getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(String bedNumber) {
        this.bedNumber = bedNumber;
    }

    public String getGramPanchayat() {
        return gramPanchayat;
    }

    public void setGramPanchayat(String gramPanchayat) {
        this.gramPanchayat = gramPanchayat;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public boolean isAdmitted() {
        return isAdmitted;
    }

    public void setAdmitted(boolean admitted) {
        isAdmitted = admitted;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) throws ParseException {
        SimpleDateFormat userFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat serverFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        //SimpleDateFormat serverFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        this.dateOfBirth = serverFormat.format(userFormat.parse(dateOfBirth));
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

